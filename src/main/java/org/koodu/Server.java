package org.koodu;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;

import java.util.Date;


public class Server extends Thread {

    private ServerSocket serverSocket;

    public Server (int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(5000);
    }

    public void run() {
        System.out.println("Serving on port " + serverSocket.getLocalPort());
        while (true) {
            try (Socket socket = serverSocket.accept()) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                Date today = new Date();
                String response = "HTTP/1.1 200 OK\n" + today;

                System.out.println("Connected to " + socket.getRemoteSocketAddress());

                out.write(response);
            } catch (SocketTimeoutException e) {
                System.out.println("Not receiving requests.");
            } catch (IOException e) {
                System.out.println("Error processing request.");
                break;
            }
        }
    }

}
