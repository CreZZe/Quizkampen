package server;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nikalsh
 */
public class LobbyThread extends Thread {

    private List<NewGameRequestListener> listOfNewGameRequestListeners;
    private ClientHandler clientSocket;
    private List<ClientHandler> clientList;
    private String input = "";

    public LobbyThread(ClientHandler currSock) {
        clientSocket = currSock;
        System.out.println(currSock);

    }

    public void addListeners(NewGameRequestListener listener) {
        listOfNewGameRequestListeners = new ArrayList<>();
        listOfNewGameRequestListeners.add(listener);
    }

    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread());
            System.out.println(clientSocket);
            while ((input = clientSocket.readLine()) != null) {
                clientList = Lobby.getClientList();

                System.out.format("Lobby: %s: %s\r\n", clientSocket.hashCode(), input);

                if (input.equalsIgnoreCase("new game")) {
//
                    for (NewGameRequestListener listOfNewGameRequestListener : listOfNewGameRequestListeners) {
                        listOfNewGameRequestListener.addToGameRoom(clientSocket);
                    }

                } else {

                    for (int j = 0; j < clientList.size(); j++) {
                        if (!clientList.get(j).equals(clientSocket)) {
                            clientList.get(j).println(clientSocket.hashCode() + ": " + input);
                        }
                    }
                }
            }
        } catch (Exception ex) {

        }

    }

}
