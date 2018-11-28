package quizkampen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.GetProperties;

public class Scorescreen {

    GetProperties properties;

    BorderPane root;
    GridPane answerButtons;
    HBox header;
    VBox center;
    HBox botMenu;
    Label totalScore;
    Label userName, opponentName;

    ArrayList playerScore;
    ArrayList opponentScore;

    ArrayList<String> categories;
    ScrollPane sp;
    Stage window;
    Scene startScene;
    int windowWidth, windowHeight;

    Button giveUp, newRound, back;

    int nrOfRounds, nrOfQuestions;

    public Scorescreen(Stage window, Scene startScene, int windowWidth, int windowHeight, String css) throws IOException {
        properties = new GetProperties();
        this.window = window;
        this.startScene = startScene;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        root = new BorderPane();
        sp = new ScrollPane();
        header = new HBox();
        botMenu = new HBox(12);
        center = new VBox(10);

        userName = new Label(getUsername());

        giveUp = new Button("Ge upp");
        newRound = new Button("Nästa Runda");
        back = new Button("<");

        nrOfRounds = getNrOfRounds();
        nrOfQuestions = getNrOfQuestions();

        opponentName = new Label(getOpponentName());
        playerScore = getPlayerScore();
        opponentScore = getOpponentScore();

        categories = getAllCategories();

        int playerTotScore = 0;
        int opponentTotScore = 0;

        for (Object object : playerScore) {
            if (object == null) {

            } else if ((boolean) object) {
                playerTotScore++;
            }
        }
        for (Object object : opponentScore) {
            if (object == null) {

            } else if ((boolean) object) {
                opponentTotScore++;
            }
        }
        totalScore = new Label(String.format("%d - %d", playerTotScore, opponentTotScore));

        ArrayList<VBox> rounds = new ArrayList();
        int ps = 0;
        int os = 0;

        for (int i = 0; i < nrOfRounds; i++) {
            VBox vbox = new VBox();

            HBox playerScoreBox = new HBox();
            HBox opponentScoreBox = new HBox();

            HBox top = new HBox();
            HBox bot = new HBox();
            Label roundNr = new Label("RUNDA " + (i + 1));
            TextArea category = new TextArea(categories.get(i));
            category.setDisable(true);

            roundNr.getStyleClass().add("gameScreenRoundLabel");

            bot.getStyleClass().add("gameScreenLabel");

            top.getChildren().add(roundNr);

            ArrayList<Label> playerScoreArray = new ArrayList();
            for (int j = 0; j < nrOfQuestions; j++) {
                Label label = new Label();
                label.getStyleClass().add("scoreLabelGameScreen");
                if (null == playerScore.get(ps)) {

                } else if (true == (boolean) playerScore.get(ps)) {
                    label.setStyle("-fx-background-color: linear-gradient(#7FDB1D 0%, #6FBF1A 25%, #66AF18 50%, #5A9A15 100%);");
                } else if (false == (boolean) playerScore.get(ps)) {
                    label.setStyle("-fx-background-color: linear-gradient(#FF3939 0%, #D83030 25%, #BF2B2B 50%, #AA2626 100%);");
                }
                playerScoreArray.add(label);
                HBox.setHgrow(label, Priority.ALWAYS);
                ps++;
            }

            ArrayList<Label> opponentScoreArray = new ArrayList();
            for (int j = 0; j < nrOfQuestions; j++) {
                Label label = new Label();
                label.getStyleClass().add("scoreLabelGameScreen");
                if (null == opponentScore.get(os)) {

                } else if (true == (boolean) opponentScore.get(os)) {
                    label.setStyle("-fx-background-color: linear-gradient(#7FDB1D 0%, #6FBF1A 25%, #66AF18 50%, #5A9A15 100%);");
                } else if (false == (boolean) opponentScore.get(os)) {
                    label.setStyle("-fx-background-color: linear-gradient(#FF3939 0%, #D83030 25%, #BF2B2B 50%, #AA2626 100%);");
                }
                opponentScoreArray.add(label);
                HBox.setHgrow(label, Priority.ALWAYS);
                os++;
            }

            playerScoreBox.setAlignment(Pos.CENTER);
            opponentScoreBox.setAlignment(Pos.CENTER);

            bot.getChildren().addAll(playerScoreArray);
            bot.getChildren().add(category);
            bot.getChildren().addAll(opponentScoreArray);

            top.setAlignment(Pos.TOP_CENTER);
            bot.setAlignment(Pos.CENTER);
            bot.setMaxHeight(50);

            vbox.setAlignment(Pos.TOP_CENTER);

            vbox.getChildren().add(top);
            vbox.getChildren().add(bot);
            rounds.add(vbox);
        }

        center.getChildren().addAll(rounds);

        sp.setContent(center);

        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);

        botMenu.getChildren().add(back);
        botMenu.getChildren().add(giveUp);
        botMenu.getChildren().add(newRound);

        totalScore.getStyleClass().add("startingWelcome2");
        totalScore.setStyle("-fx-padding: 10px;");

        header.getChildren().add(userName);
        header.getChildren().add(totalScore);
        header.getChildren().add(opponentName);

        header.setAlignment(Pos.CENTER);

        root.setTop(header);
        root.setCenter(sp);
        root.setBottom(botMenu);

        newRound.setOnAction(e -> {

            Scene categoryScene = null;

            try {

                categoryScene = new Scene(new Categoryscreen(window, startScene, windowWidth, windowHeight, css).getGUI(), windowWidth, windowHeight);
            } catch (IOException ex) {
                Logger.getLogger(Scorescreen.class.getName()).log(Level.SEVERE, null, ex);
            }

            categoryScene.getStylesheets().setAll(css);

            window.setScene(categoryScene);

        });

        giveUp.setOnAction(e -> {
            window.setScene(startScene);
            System.out.println(Quizkampen.client.sendRequestAndGetResponse("back"));
        });

        back.setOnAction(e -> {
            Scene lobbyScene = null;
            try {
                lobbyScene = new Scene(new Lobbyscreen(window, startScene, windowWidth, windowHeight, css).getGUI(), windowWidth, windowHeight);
            } catch (IOException ex) {
                Logger.getLogger(Scorescreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            lobbyScene.getStylesheets().add(css);
            window.setScene(lobbyScene);
            System.out.println(Quizkampen.client.sendRequestAndGetResponse("back"));
        });

    }

    private ArrayList<String> getAllCategories() {

        String allCat = Quizkampen.client.sendRequestAndGetResponse("allcategories");
        ArrayList<String> tempReturn = new ArrayList();

        ArrayList<String> temp = new ArrayList(Arrays.asList("Japanese Anime & Manga", "Videogames"));

        for (int i = 0; i < nrOfRounds; i++) {
            tempReturn.add("");
        }

        if (allCat.equals("")) {

            return tempReturn;
        }
        String[] allCatArr = allCat.split("@@@");

        for (int i = 0; i < allCatArr.length; i++) {
            tempReturn.set(i, allCatArr[i]);
        }
        return tempReturn;
    }

    private ArrayList<Boolean> getPlayerScore() {

        String ourScores = Quizkampen.client.sendRequestAndGetResponse("playerscore");

        ArrayList<Boolean> temp = new ArrayList<>();
        System.out.println("myScore : " + ourScores);
        for (int i = 0; i < ourScores.length(); i++) {
            char aScore = ourScores.charAt(i);

            if (aScore == '1') {
                temp.add(true);
            } else {
                temp.add(false);
            }

        }

        ArrayList<Boolean> tempReturn = new ArrayList();

        for (int i = 0; i < (nrOfQuestions * nrOfRounds); i++) {

            tempReturn.add(null);
        }

        System.out.println(temp.size());
        for (int i = 0; i < temp.size(); i++) {
            System.out.println(i + ": we are here");
            tempReturn.set(i, temp.get(i));
        }

        return tempReturn;
    }

    private ArrayList<Boolean> getOpponentScore() {
        String theirScores = Quizkampen.client.sendRequestAndGetResponse("opponentscore");

        ArrayList<Boolean> temp = new ArrayList<>();
        System.out.println("their score: " + theirScores);
        for (int i = 0; i < theirScores.length(); i++) {

            char aScore = theirScores.charAt(i);

            if (aScore == '1') {
                temp.add(true);
            } else {
                temp.add(false);
            }

        }

        ArrayList<Boolean> tempReturn = new ArrayList();

        for (int i = 0; i < (nrOfQuestions * nrOfRounds); i++) {

            tempReturn.add(null);
        }
        for (int i = 0; i < temp.size() - 1; i++) {
            tempReturn.set(i, temp.get(i));
        }

        return tempReturn;
    }

    private String getUsername() {
        return Quizkampen.client.name;
    }

    private String getOpponentName() {
        return Quizkampen.client.sendRequestAndGetResponse("opponent name");
    }

    private int getNrOfRounds() {

        return properties.getRoundsPerGame();

    }

    private int getNrOfQuestions() {

        return properties.getQuestionsPerRound();
    }

    public BorderPane getGUI() {
        return root;
    }

}
