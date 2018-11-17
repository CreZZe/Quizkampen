package server;

import java.util.List;

/**
 *
 * @author nikalsh
 */
public class GameRoomThread extends Thread {

    private ClientHandler gameClientSocket;
    private List<ClientHandler> gameClientList;
    private String input = "";
    private int roomId;

    public GameRoomThread(ClientHandler currSock, int roomId) {
        gameClientSocket = currSock;
    }

    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread());
            while ((input = gameClientSocket.readLine()) != null) {
                gameClientList = GameRoom.getClientList();

                System.out.format("Room %s: %s: %s\r\n", roomId, gameClientSocket.hashCode(), input);
                for (int j = 0; j < gameClientList.size(); j++) {
                    if (!gameClientList.get(j).equals(gameClientSocket)) {
                        gameClientList.get(j).println(input);
                    }
                }

            }
        } catch (Exception ex) {

        }

    }

}
