package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nikalsh
 */
public class Game {

    class Player {

        int scorePerRound = 0;

        String category;

        ClientHandler client;

        public Player(ClientHandler client) {
            this.client = client;

        }

        public void incrementScore() {
            scorePerRound++;
        }

    }

    class Round {

        QuestionObject[] questions;
        Player currPlayer;
        int score = 0;
        int totalQ = 2;
        int currQ = -1;

        public Round(Player currPlayer) {
            questions = new QuestionObject[2];
            this.currPlayer = currPlayer;

            for (int i = 0; i < questions.length; i++) {
                questions[i] = QuestionGenerator.getQuestionObject(QuestionGenerator.getACategory());

            }
//            questions[0].printMe();
        }

        public QuestionObject getNextQuestion() {
            currQ++;
            return questions[currQ];

        }

        public void incrementScore() {
            currPlayer.incrementScore();
        }

        public boolean isRoundFinished() {

        return currQ+1 == totalQ;
        }

    }

    private List<Round> roundList;
    private List<Player> playerList;
    public static ServerProt QuestionGenerator = new ServerProt();
    private Player currPlayer;
    private Round currRound;

    public Game(ClientHandler client) {
        playerList = new ArrayList<>();
        roundList = new ArrayList<>();

        currPlayer = new Player(client);
        currRound = new Round(currPlayer);
        playerList.add(currPlayer);
        roundList.add(currRound);
        System.out.println("players: " + playerList.size());
        System.out.println("first round");

    }

    public void add(ClientHandler client) {
        currPlayer = new Player(client);
        currRound = new Round(currPlayer);
        playerList.add(currPlayer);
        roundList.add(currRound);

        System.out.println("players: " + playerList.size());
        System.out.println("second round");

    }

    public boolean isJoinable(ClientHandler client) {
//        for (Player player : playerList) {
//            if (player.client.equals(client)){
//                return false;
//            }
//        }

        return playerList.size() < 2 && currRound().isRoundFinished();
    }

    public Round currRound() {
        return currRound;
    }

    public Player currPlayer() {
        return currPlayer;
    }

}
