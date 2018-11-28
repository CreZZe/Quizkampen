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

        String scores = "";

        String category;

        ClientHandler client;

        public Player(ClientHandler client) {
            this.client = client;

        }

        public void score(boolean b) {
            scores += (b == true ? 1 : 0);
        }

        public String getScores() {

            return scores;

        }
//
//        private void nextRound() {
//            currRound = new Round();
//            roundList.add(currRound);
//        }

//        public void score(boolean b) {
//            currRound.score(b);
//        }
//        public boolean isRoundFinished() {
//
//            return currRound.isRoundFinished();
//        }
//        public Round currRound() {
//            return currRound;
//        }
//        
//        public String getScores(){
//            
//          
//            return currRound.scores;
//            
//        }
    }

    class Round {

        public boolean isFinished = false;
        GetProperties properties = new GetProperties();
        private String category;

        public ServerProt QuestionGenerator;

        QuestionObject[] questions;

        int totalQ = properties.questionsPerRound;
        int currQ = -1;

        public boolean isRoundFinished() {

            return currQ + 1 == totalQ;

        }

        public String getAllCategories() {
//            String toReturn = "";
//            for (QuestionObject question : questions) {
//                
//                toReturn += question.category+"@@@";
//                
//            }
            return category;
        }

        public Round() {

            QuestionGenerator = new ServerProt();
            questions = new QuestionObject[totalQ];

        }

        public void finishRound() {
            this.isFinished = true;
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

    }

    private List<Player> playerList;
    private List<Round> roundList;

    private Player currPlayer;
    private Round currRound;

    public Round currRound() {
        return currRound;
    }

    public Game(ClientHandler client) {
        playerList = new ArrayList<>();
        roundList = new ArrayList<>();

        currPlayer = new Player(client);
        playerList.add(currPlayer);

        System.out.println("players: " + playerList.size());
//        System.out.println("first round");

    }

    public void add(ClientHandler client) {
        currPlayer = new Player(client);
        playerList.add(currPlayer);

        System.out.println("players: " + playerList.size());
//        System.out.println("second round");

    }

    public List<Player> getPlayers() {
        return playerList;
    }

    public boolean isJoinable(ClientHandler client) {

        return playerList.size() < 2;
    }

    public Player currPlayer() {
        return currPlayer;
    }

    public boolean areAllRoundsFinished() {

        return roundList.stream().allMatch(t -> t.isRoundFinished());
    }

    public String getAllCategories() {
        String category = "";

        for (Round round : roundList) {

            category += round.category + "@@@";
        }
//        System.out.println(category);
        return category;

    }

}
