package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public class gameProtocol {

        public gameProtocol() {
        }

        public void process(String request) {

        }
    }

    @Override
    public void run() {
        try {

            String REQUEST = "";
            while ((REQUEST = clientSocket.readLine()) != null) {

                //this happens when client presses "Start nytt spel" in 
                if (REQUEST.equalsIgnoreCase("newgame")) {
                    clientSocket.println("starting new game");
                    
                    gameProtocol prot = new gameProtocol();

                    while (true) {

                        prot.process(REQUEST);

                    }

                }

                //Otherwise just echo
                clientSocket.println(REQUEST);

            }
        } catch (IOException ex) {
            Logger.getLogger(LobbyThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addListeners(NewGameRequestListener listener) {
        listOfNewGameRequestListeners = new ArrayList<>();
        listOfNewGameRequestListeners.add(listener);
    }

}
