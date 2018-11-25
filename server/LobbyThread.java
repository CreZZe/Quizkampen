package server;

import server.OLD.NewGameRequestListener;
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

    private ClientHandler clientSocket;
    private List<ClientHandler> clientList;
    private String input = "";

    public LobbyThread(ClientHandler currSock) {
        clientSocket = currSock;
        System.out.println(currSock);

    }

    @Override
    public void run() {
        try {


            //PROTOCOL SHOULD GO HERE
            String REQUEST = "";
            String GAMEREQUEST = "";
            String REGISTER_REQUEST = "";
            OUTER:
            while ((REQUEST = clientSocket.readLine()) != null) {
                
                if (REQUEST.equalsIgnoreCase("register")){
                    
                    clientSocket.println("let's register");
                    while ((REGISTER_REQUEST = clientSocket.readLine()) != null){
                        
                        
                        

                    }
                    
                }
                
                
                
                
                
                

                //this happens when client presses "Start nytt spel" in 
                if (REQUEST.equalsIgnoreCase("newgame")) {
                    clientSocket.println("starting new game");
                    while ((GAMEREQUEST = clientSocket.readLine()) != null) {
                        if (GAMEREQUEST.equalsIgnoreCase("round is done over here")) {
                            clientSocket.println("round is done on server too");
                            continue OUTER;

                        }
                    }
                }
                //Otherwise just echo
                clientSocket.println(REQUEST);
            }
        } catch (IOException ex) {
            Logger.getLogger(LobbyThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
