package server;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nikalsh
 */
public class Lobby implements NewGameRequestListener {

    private static List<ClientHandler> clientList;
    private List<LobbyThread> lobbyThreads;
    private List<GameRoom> gameRoomList;
    private LobbyThread lt;

    public Lobby() {
        clientList = new ArrayList<>();
        lobbyThreads = new ArrayList<>();
        gameRoomList = new ArrayList<>();
    }

    public void add(ClientHandler currSock) {
        clientList.add(currSock);
        runNewThread(currSock);
    }

    public void runNewThread(ClientHandler currSock) {
        lt = new LobbyThread(currSock);
        lt.addListeners(this);
        lobbyThreads.add(lt);
        lt.start();
    }

    public static List<ClientHandler> getClientList() {
        return clientList;
    }

//    @Override
    private void newGameRoom(ClientHandler socketToAdd) {
        GameRoom gameRoom = new GameRoom(socketToAdd);
        gameRoomList.add(gameRoom);

    }

//    @Override
    private void addToExistingGameRoom(ClientHandler socketToAdd) {
        gameRoomList.get(gameRoomList.size() - 1).add(socketToAdd);
    }

    @Override
    public void addToGameRoom(ClientHandler clientSocket) {

        System.out.println("are we here?");
        if (gameRoomList.isEmpty()) {

            newGameRoom(clientSocket);

            clientList.remove(clientSocket);

            clientSocket.putInGameRoom();
            clientSocket.pauseThread();

        } else {
            for (GameRoom room : gameRoomList) {
                System.out.println(room);
                System.out.println(room.hasEmptySpot());

                if (room.hasEmptySpot()) {

                    addToExistingGameRoom(clientSocket);

                    clientList.remove(clientSocket);

                    clientSocket.putInGameRoom();
                    clientSocket.pauseThread();

                }
            }
        }
    }

    @Override
    public void test() {
        System.out.println("TESTING TESTING TESTING");
    }
}
