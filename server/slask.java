//
//import server.ClientHandler;
//import static server.Game.QuestionGenerator;
//import server.QuestionObject;
//
//public class Game {
//
//    class Player {
//
//        int scorePerRound = 0;
//
//        String category;
//
//        ClientHandler client;
//
//        public Player(ClientHandler client) {
//            this.client = client;
//
//        }
//
//        public void incrementScore() {
//            scorePerRound++;
//        }
//
//    }
//
//    class Round {
//
//        Player currPlayer;
//        int score = 0;
//        QuestionObject[] questions;
//        int totalQ = 2;
//        int currQ = -1;
//
//        public Round(Player currPlayer) {
//            this.currPlayer = currPlayer;
//            questions = new QuestionObject[2];
//
//            for (int i = 0; i < questions.length; i++) {
//                questions[i] = QuestionGenerator.getQuestionObject(QuestionGenerator.getACategory());
//
//            }
////            questions[0].printMe();
//        }
//
//        public QuestionObject getNextQuestion() {
//            currQ++;
//            return questions[currQ];
//
//        }
//
//        public int incrementScore() {
//            currPlayer.incrementScore();
//            return currPlayer.scorePerRound;
//        }
//
//        public boolean isRoundFinished() {
//
//            return currQ + 1 == totalQ;
//        }
//
//    
//}
