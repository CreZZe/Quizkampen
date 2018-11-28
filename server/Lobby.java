package server;

import server.OLD.NewGameRequestListener;
import server.OLD.GameRoom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nikalsh
 */
public class Lobby extends Thread{

    public static Game generateNewGame(ClientHandler client) {
        return new Game(client);
    }

    private static List<ClientHandler> clientList;
    private List<LobbyThread> lobbyThreads;
    public static List<Game> gameList;
    private LobbyThread lt;

    public Lobby() {
        clientList = new ArrayList<>();
        lobbyThreads = new ArrayList<>();
        gameList = new ArrayList<>();
    }

    public synchronized void addGameToList(Game game) {

        gameList.add(game);
    }

    public void add(ClientHandler currSock) {
        clientList.add(currSock);
        runNewThread(currSock);
    }

    public void runNewThread(ClientHandler currSock) {
        lt = new LobbyThread(currSock);
        lobbyThreads.add(lt);
        lt.start();

        //        lt.addListeners(this);
    }

    public static List<ClientHandler> getClientList() {
        return clientList;
    }
    
    
 
    //DONT DELETE BELOW YET, WILL DEAL WITH THIS WHEN WE GET THERE
    //nikalsh
////    @Override
//    private void newGameRoom(ClientHandler socketToAdd) {
//        GameRoom gameRoom = new GameRoom(socketToAdd);
//        gameRoomList.add(gameRoom);
//
//    }
//
////    @Override
//    private void addToExistingGameRoom(ClientHandler socketToAdd) {
//        gameRoomList.get(gameRoomList.size() - 1).add(socketToAdd);
//    }
//    @Override
//    public void addToGameRoom(ClientHandler clientSocket) {
//
//        System.out.println("are we here?");
//        if (gameRoomList.isEmpty()) {
//
//            newGameRoom(clientSocket);
//
//            clientList.remove(clientSocket);
//
//            clientSocket.putInGameRoom();
//            clientSocket.pauseThread();
//
//        } else {
//            for (GameRoom room : gameRoomList) {
//                System.out.println(room);
//                System.out.println(room.hasEmptySpot());
//
//                if (room.hasEmptySpot()) {
//
//                    addToExistingGameRoom(clientSocket);
//
//                    clientList.remove(clientSocket);
//
//                    clientSocket.putInGameRoom();
//                    clientSocket.pauseThread();
//
//                }
//            }
//        }
//    }
}
