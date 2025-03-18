import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.*;

public class Server {
    // Buffer prealocado para os dados recebidos
    static private final ByteBuffer buffer = ByteBuffer.allocate(16384);

    // Decodificador para texto UTF-8
    static private final Charset charset = Charset.forName("UTF8");
    static private final CharsetDecoder decoder = charset.newDecoder();

    // Lista para gerenciar os clientes conectados
    static private final ArrayList<SocketChannel> clients = new ArrayList<>();

    // Selector para gerenciar os eventos
    static private Selector selector;

    public static void main(String args[]) throws Exception {
        // Parse da porta a partir da linha de comando
        int port = Integer.parseInt(args[0]);

        try {
            // Criação de ServerSocketChannel
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false); // Modo não bloqueante

            // Bind à porta fornecida
            ServerSocket ss = ssc.socket();
            InetSocketAddress isa = new InetSocketAddress(port);
            ss.bind(isa);

            // Criar selector para selecionar eventos
            selector = Selector.open();

            // Registrar o ServerSocketChannel para aceitar conexões
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Servidor ouvindo na porta " + port);

            while (true) {
                // Verificar atividade
                int num = selector.select();

                // Se não houver atividade, continue
                if (num == 0) {
                    continue;
                }

                // Processar as chaves ativas
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();

                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove(); // Remover a chave da lista processada

                    if (key.isAcceptable()) {
                        // Nova conexão de cliente
                        acceptConnection(ss);

                    } else if (key.isReadable()) {
                        // Dados recebidos de um cliente
                        SocketChannel sc = (SocketChannel) key.channel();
                        boolean ok = processInput(sc, key);

                        // Se a conexão estiver fechada, remover o cliente
                        if (!ok) {
                            key.cancel();
                            clients.remove(sc);
                            try {
                                sc.close();
                            } catch (IOException e) {
                                System.err.println("Erro ao fechar socket: " + e);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e);
        }
    }

    // Aceitar nova conexão
    private static void acceptConnection(ServerSocket ss) throws IOException {
        Socket s = ss.accept();
        System.out.println("Conexão recebida de " + s);

        SocketChannel sc = s.getChannel();
        sc.configureBlocking(false);

        // Registrar o cliente no selector
        sc.register(selector, SelectionKey.OP_READ);
        clients.add(sc);
    }

    // Processar entrada do cliente
    private static boolean processInput(SocketChannel sc, SelectionKey key) throws IOException {
        buffer.clear();
        sc.read(buffer);
        buffer.flip();

        if (buffer.limit() == 0) {
            return false; // Conexão encerrada
        }

        String message = decoder.decode(buffer).toString().trim();

        // Verificar se o pseudônimo já foi registrado
        String nickname = (String) key.attachment();

        if (nickname == null) {
            // Primeiro envio é o pseudônimo
            key.attach(message);
            sc.write(ByteBuffer.wrap(("Pseudônimo registrado como: " + message + "\n").getBytes()));
        } else {
            // Difundir mensagem com pseudônimo
            String fullMessage = nickname + ": " + message + "\n";

            for (SocketChannel client : clients) {
                if (client != sc) { // Não enviar para o remetente
                    client.write(ByteBuffer.wrap(fullMessage.getBytes()));
                }
            }
        }

        return true;
    }
}
