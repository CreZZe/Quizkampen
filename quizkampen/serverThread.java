/*
 * Javautveckling 2018
 */

package quizkampen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class serverThread implements Runnable{

    private Socket client;
    private PrintWriter toClient;
    private BufferedReader fromClient;
//    private ProtocolPractice protocol;
    private String input = "";
    private Thread thread;

    public serverThread(Socket s) {
        client = s;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        try {
            toClient = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
            fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
//            protocol = new ProtocolPractice();

            while ((input = fromClient.readLine()) != null) {

//           output = protocol.processInput(input);
                toClient.println(input);

            }
        } catch (IOException ex) {
            Logger.getLogger(serverThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}