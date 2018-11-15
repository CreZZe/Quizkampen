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
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
//teest 
public class Client {

    private Socket server;
    private String input = "";
    private InetAddress ip;
    private int port = 55555;
    private PrintWriter toServer;
    private BufferedReader fromServer;
    private LocalDateTime nu = LocalDateTime.now();

    BufferedReader cin;

    public Client() throws IOException, InterruptedException {
        cin = new BufferedReader(new InputStreamReader(System.in));
        try {

            System.out.println("trying to connect to port: " + port);
            ip = InetAddress.getLocalHost();
            System.out.println(ip);
            server = new Socket(ip, port);
            
            System.out.println("connected to server " + ip + ": " + port);

            toServer = new PrintWriter(new OutputStreamWriter(server.getOutputStream()), true);
            fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
            sendAndRecieveLoop();

        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException exa) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, exa);

        }   

    }

    public void sendAndRecieveLoop() throws IOException, InterruptedException {

        Thread listenerThread = new Thread(() -> {

            while (true) {

                try {
                    System.out.println(fromServer.readLine());
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        listenerThread.start();

        while ((input = cin.readLine()) != null) {
            if (input.equalsIgnoreCase("bye")) {
                server.close();
                listenerThread.interrupt();
                System.out.println("disconnecting...");
            }

            toServer.println(input);
            toServer.flush();
        }

    }
    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client();
    }
}