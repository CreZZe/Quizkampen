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

        private boolean isFinished = false;

        String category;

        ClientHandler client;

        public Player(ClientHandler client) {
            this.client = client;
            category = QuestionGenerator.getACategory();
            QuestionObject question = QuestionGenerator.getQuestionObject(category);

        }

        public boolean turnIsFinished() {
            return isFinished;
        }

        public void finishTurn() {
            isFinished = true;
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

        }

        public QuestionObject getNextQuestion() {
            currQ++;
            return questions[currQ];

        }

        public void incrementScore() {
            score++;
        }

        public boolean isRoundFinished() {

            if (!(currQ <= totalQ)) {
                currPlayer.finishTurn();
            }
            return !(currQ <= totalQ);
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

    }

    public void add(ClientHandler client) {
        currPlayer = new Player(client);
        currRound = new Round(currPlayer);
        playerList.add(currPlayer);
        roundList.add(currRound);

    }

    public boolean isJoinable() {
        return playerList.size() < 2 && currPlayer.turnIsFinished();
    }

    public Round currRound() {
        return currRound;
    }

    public Player currPlayer() {
        return currPlayer;
    }

}
