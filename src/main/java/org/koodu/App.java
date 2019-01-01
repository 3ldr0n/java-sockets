package org.koodu;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        try {
            Thread t = new Server(port);
            t.start();
        } catch (IOException e) {
            System.out.println("Port " + port + " alredy in use.");
        }
    }
}
