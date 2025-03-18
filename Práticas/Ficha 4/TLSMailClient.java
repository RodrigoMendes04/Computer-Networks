import java.io.*;
import java.net.*;
import javax.net.ssl.*;

class TLSMailClient {
    public static void main(String[] args) throws Exception {
        String server = "smtp.fc.up.pt"; // Endereço do servidor SMTP
        int port = 25; // Porta do servidor SMTP

        // Socket inicial para conexão com o servidor SMTP
        Socket clientSocket = new Socket(server, port);
        System.out.println("Conectado ao servidor SMTP.");

        // Streams para comunicação com o servidor em texto claro (antes do TLS)
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        // Recebe o código inicial "220 Ready" do servidor
        System.out.println("FROM SERVER: " + inFromServer.readLine());

        // Envia o comando STARTTLS para iniciar a comunicação segura
        outToServer.writeBytes("STARTTLS\r\n");
        System.out.println("FROM SERVER: " + inFromServer.readLine());

        // Cria um socket TLS (encriptado)
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket tlsSocket = (SSLSocket) sslSocketFactory.createSocket(clientSocket, server, port, true);

        // Streams para comunicação segura (após STARTTLS)
        BufferedReader tlsInFromServer = new BufferedReader(new InputStreamReader(tlsSocket.getInputStream()));
        DataOutputStream tlsOutToServer = new DataOutputStream(tlsSocket.getOutputStream());

        // Envia o comando HELO com o domínio do cliente
        tlsOutToServer.writeBytes("HELO example.com\r\n");
        System.out.println("FROM SERVER: " + tlsInFromServer.readLine());

        // Envia o comando MAIL FROM com o endereço de e-mail do remetente
        tlsOutToServer.writeBytes("MAIL FROM:<seu_email@example.com>\r\n");
        System.out.println("FROM SERVER: " + tlsInFromServer.readLine());

        // Envia o comando RCPT TO com o endereço de e-mail do destinatário
        tlsOutToServer.writeBytes("RCPT TO:<destinatario@example.com>\r\n");
        System.out.println("FROM SERVER: " + tlsInFromServer.readLine());

        // Envia o comando DATA para iniciar a mensagem de e-mail
        tlsOutToServer.writeBytes("DATA\r\n");
        System.out.println("FROM SERVER: " + tlsInFromServer.readLine());

        // Envia o corpo do e-mail seguido de um ponto na linha final
        tlsOutToServer.writeBytes("Subject: Teste SMTP\r\n");
        tlsOutToServer.writeBytes("\r\n");
        tlsOutToServer.writeBytes("Este é um e-mail de teste enviado via cliente SMTP em Java.\r\n");
        tlsOutToServer.writeBytes(".\r\n");
        System.out.println("FROM SERVER: " + tlsInFromServer.readLine());

        // Envia o comando QUIT para encerrar a conexão
        tlsOutToServer.writeBytes("QUIT\r\n");
        System.out.println("FROM SERVER: " + tlsInFromServer.readLine());

        // Fecha os sockets
        tlsSocket.close();
        clientSocket.close();
        System.out.println("Conexão encerrada.");
    }
}
