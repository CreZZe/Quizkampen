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
public class GameRoomThread {

    int playerCount = 0;
    List<ClientSocket> gameClientList;
//    ClientSocket currSock;
    String input = "";
    int threadCounter = 0;

    public GameRoomThread() {
        gameClientList = new ArrayList<>();

    

    }

    public void addPlayer(ClientSocket socket) {
        gameClientList.add(socket);
        ClientSocket currSock = socket;
        playerCount++;
        threadCounter++;

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
        thread.setName("GameRoom: " + threadCounter);
        thread.start();

    }

    public boolean hasEmptySpot() {

        return (playerCount < 2);
    }
    
    public int num(){
        
        return playerCount;
    }
}
