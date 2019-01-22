import java.net.Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Client {

    public static String serverName;
    public static int port;

    public static void main(String[] args) {
        try {
            serverName = args[0];
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing arguments.");
            System.exit(-1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not enough arguments.");
            System.exit(-1);
        }

        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);
            
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            
            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
