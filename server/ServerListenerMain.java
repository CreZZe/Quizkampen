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

    ServerProtocol p = new ServerProtocol();

    List<ClientSocket> clientList;
    List<GameRoomThread> roomList;

    private int port = 0;
    private ServerSocket listener;
//    private ClientSocket clientSocket;

//    private BufferedReader fromClient;
    private String input = "";
    private String output = "";

    private BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));

    public void enableCIN() {

    }

//    public void println(Object o) {
//
//        System.out.println(o);
//    }
    public ServerListenerMain(int port) throws IOException {
        this.port = port;
        clientList = new ArrayList<>();
        listener = new ServerSocket(this.port);
        enableCIN();

        while (true) {
            try {

                roomList = new ArrayList<>();
                ClientSocket clientSocket = new ClientSocket(listener.accept());

                System.out.println("ServerListener: New socket: " + clientSocket.getSocket().getInetAddress());

                clientList.add(clientSocket);

                Thread newServerThread = new Thread(() -> {

                    //IGNORE
                    try {

                        System.out.println(Thread.currentThread());
                        while ((input = clientSocket.readLine()) != null) {

                            System.out.println(input);
                            if (input.equalsIgnoreCase("new game")) {

                                System.out.println("roomlistEmpty: " + roomList.isEmpty());
                                if (roomList.isEmpty()) {

                                    GameRoomThread room = new GameRoomThread();
                                    roomList.add(room);

                                    clientSocket.putInGameRoom();
                                    clientList.remove(clientSocket);
                                    room.addPlayer(clientSocket);
                                    System.out.println("putting " + clientSocket + " in room " + room + "#players: " + room.num());

                                    clientSocket.pauseThread();

                                } else {
                                    for (GameRoomThread room : roomList) {
                                        System.out.println(room);
                                        System.out.println(room.hasEmptySpot());
                                        if (room.hasEmptySpot()) {

                                            clientSocket.putInGameRoom();
                                            room.addPlayer(clientSocket);
                                            clientList.remove(clientSocket);

//                                              clientSocket.wait();
                                            System.out.println("putting " + clientSocket + " in room " + room + " #players: " + room.num());

                                            clientSocket.pauseThread();

                                        }
                                    }
                                }

                            }

                            //end of ignore
                            //SEND CLIENT INPUT TO ALL OTHER CLIENTS
                            for (int j = 0; j < clientList.size(); j++) {
                                if (!clientList.get(j).equals(clientSocket)) {
                                    clientList.get(j).println(input);
                                }
                            }

                        }

                    } catch (IOException ex) {
                        Logger.getLogger(ServerListenerMain.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
                newServerThread.setName("LobbyThread");
                newServerThread.start();

            } catch (IOException ex) {
                Logger.getLogger(ServerListenerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        ServerListenerMain server = new ServerListenerMain(55555);
    }

}
