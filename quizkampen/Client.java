/*
 * Javautveckling 2018
 */
package quizkampen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikalsh
 */
public class Client {

    public String name;
    private Socket server;
    private String input = "";
    private InetAddress ip;
    private int port = 55555;
    private PrintWriter toServer;
    private BufferedReader fromServer;
    private BufferedReader cin;

    public Client() throws IOException, InterruptedException {
        name = "Anonym";
        cin = new BufferedReader(new InputStreamReader(System.in));
        try {

            System.out.println("trying to connect to port: " + port);
            ip = InetAddress.getLocalHost();
            System.out.println(ip);
            server = new Socket(ip, port);
            System.out.println("connected to server " + ip + ": " + port);

            toServer = new PrintWriter(new OutputStreamWriter(server.getOutputStream(), "UTF-8"), true);
            fromServer = new BufferedReader(new InputStreamReader(server.getInputStream(), "UTF-8"));
//            startClient();

        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException exa) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, exa);

        }

    }

    public String sendRequestAndGetResponse(String request) {
        try {
            toServer.println(request);
            System.out.println("Sent request: " + request);
            String response = fromServer.readLine();
            return response;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ERROR: server could not respond";
    }

    public void startClient() throws IOException, InterruptedException {

        Thread listenerThread = new Thread(() -> {
            while (true) {

            }
        });
        listenerThread.start();

    }

//        while ((input = cin.readLine()) != null) {
//            if (input.equalsIgnoreCase("bye")) {
//                server.close();
//                listenerThread.interrupt();
//                System.out.println("disconnecting...");
//            }
//
//            toServer.println(input);
//            toServer.println("i am socket: " + this.hashCode());
//            toServer.flush();
//        }
//    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client();
    }
}
