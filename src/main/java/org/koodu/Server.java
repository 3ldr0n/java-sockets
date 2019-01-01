package org.koodu;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.SocketTimeoutException;


public class Server extends Thread {

    private ServerSocket serverSocket;

    public Server (int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(5000);
    }

    public void run() {
        System.out.println("Serving on port " + serverSocket.getLocalPort());
        while (true) {
            try {
                Socket server = serverSocket.accept();
                System.out.println("Connected to " + server.getRemoteSocketAddress());

                DataInputStream in = new DataInputStream(server.getInputStream());
            
                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("You connected to " + server.getLocalSocketAddress());

                server.close();
            } catch (SocketTimeoutException e) {
                System.out.println("Not receiving requests.");
            } catch (IOException e) {
                System.out.println("Error processing request.");
                break;
            }
        }
    }

}
