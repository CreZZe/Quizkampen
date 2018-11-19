/*
 * Javautveckling 2018
 */
package server;

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
public class ServerListenerMain {

    private int port = 0;
    private ServerSocket listener;
    private BufferedReader cin;
    private String cinput = "";
    private Lobby lobby;

    public ServerListenerMain(int port) throws IOException {

        lobby = new Lobby();
        this.port = port;
        listener = new ServerSocket(this.port);
        enableConsoleInput();

        while (true) {
            ClientHandler clientSocket = new ClientHandler(listener.accept());
            System.out.println("ServerListener: New socket: " + clientSocket.getSocket().getInetAddress());
            lobby.add(clientSocket);
        }
    }

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
                Logger.getLogger(ServerListenerMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        cinThread.start();
    }

    public static void main(String[] args) throws IOException {
        ServerListenerMain server = new ServerListenerMain(55555);
    }

}
