package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author nikalsh
 */
public class ClientHandler {
    
    //Class to keep track of individual clients
    
    private boolean AUTHORIZED_USER;
    //if authoirzed we can read and write statistics, saved games, etc for the specific user
    //such as
    private int totalScore;
    private int timeSpentInGame;
    private int gamesWon;
    private int gamesLost;
    private Game[] currentGames; //game saves - read this value when user is authorized 
    
    
    
    
    private Socket clientSocket;
    private PrintWriter toClient;
    private BufferedReader fromClient;
    private boolean isNowPlaying;
    //score for keeping track of score of individual game
    private int score;
    
    

    public ClientHandler(Socket socket) throws IOException {

        clientSocket = socket;
        toClient = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
        fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
    }

    public void println(String input) {
        toClient.println(input);
    }

    public String readLine() throws IOException {
        return fromClient.readLine();
    }

    public boolean isPlaying() {
        return isNowPlaying;
    }

    public void putInGameRoom() {
        isNowPlaying = true;
    }

//    public synchronized void putInLobby() {
//        System.out.println(Thread.currentThread());
//        Thread.currentThread().interrupt();
//        this.notify();
//        isNowPlaying = false;
//    }

    public Socket getSocket() {
        return this.clientSocket;
    }

    public synchronized void pauseThread() {
        while (isNowPlaying) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        System.out.println("Thread resumed");
    }

}
