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

        Round currRound;
        List<Round> roundList;

        String category;

        ClientHandler client;

        public Player(ClientHandler client) {
            this.client = client;
            roundList = new ArrayList<>();

            nextRound();
        }

        private void nextRound() {
            currRound = new Round();
            roundList.add(currRound);
        }

        public void score() {
            currRound.score();
        }

        public boolean isRoundFinished() {

            return currRound.isRoundFinished();
        }

        public boolean areAllRoundsFinished() {

            return roundList.stream().allMatch(t -> t.isRoundFinished());
        }

        public Round currRound() {
            return currRound;
        }

        public int getScore() {
            return currRound.getScore();
        }

    }

    class Round {
        
        GetProperties properties = new GetProperties();

        
        public ServerProt QuestionGenerator;
        int score;

        QuestionObject[] questions;
<<<<<<< HEAD
        int totalQ = 2;
        int currQ = -1;

        public boolean isRoundFinished() {

            return currQ + 1 == totalQ;

        }

        public Round() {
            QuestionGenerator = new ServerProt();
            questions = new QuestionObject[2];
=======
        Player currPlayer;
        int score = 0;
        int totalQ = properties.questionsPerRound;
        int currQ = -1;

        public Round(Player currPlayer) {
            questions = new QuestionObject[totalQ];
            this.currPlayer = currPlayer;
>>>>>>> 1ec7ab0f3286075b6278cc6390cfda117137a887

        }

        public void generateQuestionsFromCategory(String CATEGORY) {
            System.out.println(CATEGORY);
            for (int i = 0; i < questions.length; i++) {
                questions[i] = QuestionGenerator.getQuestionObject(CATEGORY);

            }
        }

        public QuestionObject getNextQuestion() {
            currQ++;
            System.out.println(questions[currQ]);
            return questions[currQ];

        }

        public void score() {
            score++;
        }

        public int getScore() {
            return score;
        }

    }

    private List<Player> playerList;
    
    private Player currPlayer;

    public Game(ClientHandler client) {
        playerList = new ArrayList<>();

        currPlayer = new Player(client);
        playerList.add(currPlayer);

        System.out.println("players: " + playerList.size());
        System.out.println("first round");

    }

    public void add(ClientHandler client) {
        currPlayer = new Player(client);
        playerList.add(currPlayer);

        System.out.println("players: " + playerList.size());
        System.out.println("second round");

    }

    public List<Player> getPlayers() {
        return playerList;
    }

    public boolean isJoinable(ClientHandler client) {

        return playerList.size() < 2 && currPlayer().isRoundFinished();
    }

    public Player currPlayer() {
        return currPlayer;
    }

}
