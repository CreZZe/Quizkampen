/*
 * Javautveckling 2018
 */

package quizkampen;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class serverListener {

    public class ClientSocket {

        private Socket clientSocket;
        private PrintWriter toClient;

        public ClientSocket(Socket s) throws IOException {
            clientSocket = s;
            toClient = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
        }

        public void println(String s) {
            toClient.println(s);
        }

        public Socket s() {
            return this.clientSocket;
        }

    }

    List<PrintWriter> printList;
    List<ClientSocket> clientList;

    private int port = 55555;
    private ServerSocket listener;
//    private Socket clientSocket;
    private ClientSocket clientSocket;
//    private ProtocolPractice protocol;

    private BufferedReader fromClient;
    private String input = "";
    private String output = "";

    public serverListener() throws IOException {
        clientList = new ArrayList<>();
        listener = new ServerSocket(port);
        while (true) {
            try {

                clientSocket = new ClientSocket(listener.accept());

                System.out.println("ServerListener: New socket: " + clientSocket.s().getInetAddress());

//                serverThread ServerThread = new serverThread(client);
//                PrintWriter toClient = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);


                BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.s().getInputStream()));
                clientList.add(clientSocket);
                
//                serverThread sThread = new serverThread(clientSocket.s());
                
                Thread newServerThread = new Thread(() -> {
                    try {

//                        protocol = new ProtocolPractice();

                        while ((input = fromClient.readLine()) != null) {
                            System.out.println(input);

                            for (int j = 0; j < clientList.size(); j++) {
                                clientList.get(j).println(input);
                            }
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(serverThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                newServerThread.start();
                

            } catch (IOException ex) {
                Logger.getLogger(serverListener.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        serverListener server = new serverListener();
    }

}