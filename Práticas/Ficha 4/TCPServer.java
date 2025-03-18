//Ficha 4 - Exercicio 1
import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String[] args) throws Exception {
        // Criação do socket do servidor na porta 6789
        ServerSocket welcomeSocket = new ServerSocket(6789);

        System.out.println("Servidor TCP ativo na porta 6789...");

        // Loop infinito para aceitar conexões de clientes
        while (true) {
            // Aguarda a conexão de um cliente
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Conexão estabelecida com o cliente: " + connectionSocket.getInetAddress());

            // Criação dos streams de entrada e saída
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            // Loop para manter a conexão persistente
            String clientSentence;
            while ((clientSentence = inFromClient.readLine()) != null) {
                // Converte a mensagem recebida em maiúsculas
                String capitalizedSentence = clientSentence.toUpperCase() + '\n';
                // Envia a mensagem convertida de volta para o cliente
                outToClient.writeBytes(capitalizedSentence);
            }

            // Quando readLine retorna null, a conexão foi fechada pelo cliente
            System.out.println("Conexão encerrada pelo cliente.");
            connectionSocket.close();
        }
    }
}
