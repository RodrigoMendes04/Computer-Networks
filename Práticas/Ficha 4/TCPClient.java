//Ficha 4 - Exercicio 1
import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String[] args) throws Exception {
        // Verifica se o endereço do servidor foi passado como argumento
        if (args.length < 1) {
            System.out.println("Uso: java TCPClient <hostname>");
            return;
        }

        String hostname = args[0];
        int port = 6789; // Porta padrão do servidor

        // Criação do socket do cliente
        Socket clientSocket = new Socket(hostname, port);
        System.out.println("Conectado ao servidor " + hostname + " na porta " + port);

        // Criação dos streams de entrada e saída
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer =
                new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Loop para envio e recebimento de mensagens
        System.out.println("Digite linhas para enviar ao servidor. Pressione Ctrl+D (Unix/Linux/MacOS) ou Ctrl+Z (Windows) para sair.");
        String userInput;
        while ((userInput = inFromUser.readLine()) != null) {
            // Envia a linha digitada pelo usuário ao servidor
            outToServer.writeBytes(userInput + '\n');

            // Recebe e imprime a resposta do servidor
            String serverResponse = inFromServer.readLine();
            System.out.println("DO SERVIDOR: " + serverResponse);
        }

        // Fechamento do socket ao término da entrada
        System.out.println("Encerrando conexão com o servidor.");
        clientSocket.close();
    }
}
