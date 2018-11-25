package server.OLD;

import java.util.ArrayList;
import java.util.List;
import server.ClientHandler;

/**
 *
 * @author nikalsh
 */
public class GameRoom {

    private static List<ClientHandler> gameClientList;
    private List<GameRoomThread> gameThreads;
    private GameRoomThread gameThread;
    private int roomId = 0;
    private int playerCount = 0;

    public GameRoom(ClientHandler socket) {
        roomId++;
        gameClientList = new ArrayList<>();
        gameThreads = new ArrayList<>();
        add(socket);

    }

    public void add(ClientHandler currSock) {
        playerCount++;
        System.out.format("putting %s in room %s players#: %s\r\n", currSock, this,playerCount);

        gameClientList.add(currSock);
        runNewThread(currSock);
    }

    public void runNewThread(ClientHandler currSock) {
        gameThread = new GameRoomThread(currSock, roomId);
        gameThreads.add(gameThread);
        gameThread.start();
    }

    public static List<ClientHandler> getClientList() {
        return gameClientList;
    }

    public int getRoomCount() {
        return roomId;
    }

    public boolean hasEmptySpot() {
        return gameClientList.size() < 2;
    }

    public int numOfPlayers() {
        return gameClientList.size();
    }

}
