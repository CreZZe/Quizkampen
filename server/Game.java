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
        
        
        
        int currRound = 1;
        String category;

        Map<String, Integer> roundScores = new HashMap<>();

        ClientHandler client;

        public Player(ClientHandler client) {
            this.client = client;
            category = QuestionGenerator.getACategory();
            QuestionObject question = QuestionGenerator.getQuestionObject(category);

        }

    }
    
    class Round {
        
        List<QuestionObject> questionList;

        public Round() {
        questionList = new ArrayList<>();
        questionList.add(QuestionGenerator.getQuestionObject(QuestionGenerator.getACategory()));
        questionList.add(QuestionGenerator.getQuestionObject(QuestionGenerator.getACategory()));        
        }
        
        
        
        
    }
    
    List<Round> roundList;
    List<Player> playerList;
    ServerProt QuestionGenerator = new ServerProt();

    public Game(ClientHandler client) {
        playerList.add(new Player(client));

    }

    public void join(ClientHandler client) {
        System.out.println(client + " joined " + this);

        playerList.add(new Player(client));
    }

    public boolean isJoinable() {
        return playerList.size() < 2;
    }

}
