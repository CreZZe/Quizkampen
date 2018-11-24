/*
 * Javautveckling 2018
 */
package server;

import util.Colors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikalsh
 */
public class Accepter {

    private BufferedReader cin;
    private String cinput = "";

    //These are the crucial variables, ignore those above for now
    private Lobby lobby;
    private int port = 0;
    private ServerSocket listener;
    private ClientHandler clientHandler;

    public Accepter(int port) throws IOException {
        lobby = new Lobby();
        this.port = port;
        listener = new ServerSocket(this.port);
        startInfiniteListenAndAcceptLoop();
    }

    private void startInfiniteListenAndAcceptLoop() throws IOException {
        System.out.println("now listening for connections on port: " + port + "..");
        while (true) {
            clientHandler = new ClientHandler(listener.accept());
            //Row above is BLOCKING until a client tries to connect to us
            //When a client connects, listener.accept() is invoked, 
            //which outputs a "Socket" which we use as input in a ClientHandler instance constructor
            //the ClientHandler instance is added to "Lobby"
            System.out.println("ServerListener: New socket: " + clientHandler.getSocket().getInetAddress());
            System.out.println("putting socket in lobby");
            lobby.add(clientHandler);
        }
    }

    //IGNORE this section below for now 
    //--------------------------------------------------------------------------
    //Enables server in/output, useful for implementing commands
    //i e list all current lobbies, game rooms and their corresponding active threads
    public void enableConsoleInput() throws UnsupportedEncodingException {
        cin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));

        Thread cinThread = new Thread(() -> {

            try {
                while ((cinput = cin.readLine()) != null) {
                    System.out.println(Colors.colorize(cinput, "green"));
                }
            } catch (IOException ex) {
                Logger.getLogger(Accepter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        cinThread.start();
    }

}
