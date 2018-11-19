<<<<<<< OURS
/*
 * Javautveckling 2018
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerListenerMain {

    public class ClientSocket {

        private Socket clientSocket;
        private PrintWriter toClient;
        private BufferedReader fromClient;
        private boolean clientIsInLobby = true;

        public ClientSocket(Socket s) throws IOException {

            clientSocket = s;
            toClient = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }

        public void println(String s) {
            if (clientIsInLobby) {
                toClient.println(clientSocket.hashCode() + ": " + s);
            }
        }

        public Socket getSocket() {
            return this.clientSocket;
        }

        public String readLine() throws IOException {
            return fromClient.readLine();
        }

        public void softRemoveFromLobby() {
            clientIsInLobby = false;
        }

        public boolean isInLobby() {
            return clientIsInLobby;
        }

    }

    public class GameRoom{

        int playerCount = 0;
        List<ClientSocket>  gameClientList;
        ClientSocket currSock;
        String input = "";

        public GameRoom(ClientSocket socket) {
            gameClientList = new ArrayList<>();
            gameClientList.add(socket);
            playerCount++;
            ClientSocket currSock = socket;
            currSock.println("now talking in room " + this + "current # clients: " + playerCount);
            Thread thread = new Thread(() -> {

                
                    try {
                        while ((input = currSock.readLine()) != null) {

                             for (int j = 0; j < gameClientList.size(); j++) {
                                    if (!gameClientList.get(j).equals(currSock)) {
                                        gameClientList.get(j).println(input);
                                    }
                                }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ServerListenerMain.class.getName()).log(Level.SEVERE, null, ex);
                    }

                

            });
            thread.start();

        }

        public void addPlayer(ClientSocket socket) {
            gameClientList.add(socket);
            ClientSocket currSock = socket;
            playerCount++;
            currSock.println("now talking in room " + this + "current # clients: " + playerCount);
            Thread thread = new Thread(() -> {
                
                
                    try {
                        while ((input = currSock.readLine()) != null) {

                            for (int j = 0; j < gameClientList.size(); j++) {
                                    if (!gameClientList.get(j).equals(currSock)) {
                                        gameClientList.get(j).println(input);
                                    }
                                }

                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ServerListenerMain.class.getName()).log(Level.SEVERE, null, ex);
                    }

                
            
            });
            thread.start();

        }

        public boolean hasEmptySpot() {

            return (playerCount < 2);
        }
    }

    List<PrintWriter> printList;
    List<ClientSocket> clientList;
    List<GameRoom> roomList;

    private int port = 55555;
    private ServerSocket listener;
//    private ClientSocket clientSocket;

//    private BufferedReader fromClient;
    private String input = "";
    private String output = "";

    public ServerListenerMain() throws IOException {
        clientList = new ArrayList<>();
        listener = new ServerSocket(port);
        while (true) {
            try {

                roomList = new ArrayList<>();
                ClientSocket clientSocket = new ClientSocket(listener.accept());

                System.out.println("ServerListener: New socket: " + clientSocket.getSocket().getInetAddress());

                clientList.add(clientSocket);

                Thread newServerThread = new Thread(() -> {
                    try {

                        while ((input = clientSocket.readLine()) != null) {
                            System.out.println(input);

                            if (input.equalsIgnoreCase("new game")) {
                                System.out.println("roomlistEmpty: " + roomList.isEmpty());
                                if (roomList.isEmpty()) {
                                    clientList.remove(clientSocket);
                                    roomList.add(new GameRoom(clientSocket));
//                                    clientSocket.softRemoveFromLobby();
//                                    while (!clientSocket.isInLobby()) {
//                                        this.wait();
//                                    }
                                } else {
                                    for (GameRoom room : roomList) {
                                        System.out.println(room);
                                        System.out.println(room.hasEmptySpot());
                                        if (room.hasEmptySpot()) {
                                            clientList.remove(clientSocket);
                                            System.out.println(clientSocket + " added to " + room);
                                            room.addPlayer(clientSocket);
//                                            clientSocket.softRemoveFromLobby();
//                                            while (!clientSocket.isInLobby()) {
//                                                this.wait();
//                                            }
                                        }
                                    }
                                }

                            } else {

                                for (int j = 0; j < clientList.size(); j++) {
                                    if (!clientList.get(j).equals(clientSocket)) {
                                        clientList.get(j).println(input);
                                    }
                                }
                            }

                        }

                    } catch (IOException ex) {
                        Logger.getLogger(ServerListenerMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                    catch (InterruptedException ex) {
//                        Logger.getLogger(ServerListenerMain.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                });
                newServerThread.start();

            } catch (IOException ex) {
                Logger.getLogger(ServerListenerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        ServerListenerMain server = new ServerListenerMain();
    }

}
=======
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
>>>>>>> THEIRS
