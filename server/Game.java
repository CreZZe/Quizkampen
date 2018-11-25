package server;

/**
 *
 * @author nikalsh
 */
public class Game extends Thread {

    private int rounds;
    private int currentRound;
    private ClientHandler[] players;
    private server.QuestionObject[] category;

    public Game() {

        //prepare to read from files and generate questions objects
    }

    @Override
    public void run() {

        //do the game
    }

}
