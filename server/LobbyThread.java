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

    public void addListeners(NewGameRequestListener listener) {
        listOfNewGameRequestListeners = new ArrayList<>();
        listOfNewGameRequestListeners.add(listener);
    }

    @Override
    public void run() {
        try {
            String input = "";
            while ((input = clientSocket.readLine()) != null){
                
                
                //Just echo for now
                clientSocket.println(input);
                
                
                
                
                
                
                
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(LobbyThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
