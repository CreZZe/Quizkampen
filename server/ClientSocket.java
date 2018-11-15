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
public class ClientSocket {

    private Socket clientSocket;
    private PrintWriter toClient;
    private BufferedReader fromClient;
    private boolean isNowPlaying;

    public ClientSocket(Socket socket) throws IOException {

        clientSocket = socket;
        toClient = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
        fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void println(String input) {

        toClient.println(clientSocket.hashCode() + ": " + input);

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

    public void putInLobby() {
        isNowPlaying = false;
    }

    public Socket getSocket() {
        return this.clientSocket;
    }

    public synchronized void pauseThread() {
        // This guard only loops once for each special event, which may not
        // be the event we're waiting for.
        while (isNowPlaying) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Thread resumed");
    }

}
