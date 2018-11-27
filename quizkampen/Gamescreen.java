package quizkampen;

import java.util.ArrayList;
import java.util.Arrays;
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

public class Gamescreen {

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

    Button giveUp, nextGame, back;

    int nrOfRounds, nrOfQuestions;

    public Gamescreen(Stage window, Scene startScene, int windowWidth, int windowHeight) {
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
        opponentName = new Label(getOpponentName());

        giveUp = new Button("Ge upp");
        nextGame = new Button("Nästa Runda");
        back = new Button("<");

        nrOfRounds = getNrOfRounds();
        nrOfQuestions = getNrOfQuestions();

        playerScore = getPlayerScore();
        opponentScore = getopponentScore();
        categories = getCategories();
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
        botMenu.getChildren().add(nextGame);

        totalScore.getStyleClass().add("startingWelcome2");
        totalScore.setStyle("-fx-padding: 10px;");

        header.getChildren().add(userName);
        header.getChildren().add(totalScore);
        header.getChildren().add(opponentName);

        header.setAlignment(Pos.CENTER);

        root.setTop(header);
        root.setCenter(sp);
        root.setBottom(botMenu);

        nextGame.setOnAction(e -> {
            
            Scene categoryScene = new Scene(new Categoryscreen(window, startScene, windowWidth, windowHeight).getGUI(), windowWidth, windowHeight);
            categoryScene.getStylesheets().add("Styling.css");
            window.setScene(categoryScene);
        });

        giveUp.setOnAction(e -> {
            window.setScene(startScene);
            System.out.println(Quizkampen.client.sendRequestAndGetResponse("back"));
        });

    }

    private ArrayList<String> getCategories() {
        ArrayList<String> temp = new ArrayList(Arrays.asList("Japanese Anime & Manga", "Videogames"));
        ArrayList<String> tempReturn = new ArrayList();
        for (int i = 0; i < getNrOfRounds(); i++) {
            tempReturn.add("");
        }
        for (int i = 0; i < temp.size(); i++) {
            tempReturn.set(i, temp.get(i));
        }
        return tempReturn;
    }

    private ArrayList<Boolean> getPlayerScore() {
        ArrayList<Boolean> temp = new ArrayList(Arrays.asList(true, true, true, true, false, false));
        ArrayList<Boolean> tempReturn = new ArrayList();
        for (int i = 0; i < (getNrOfQuestions() * getNrOfRounds()); i++) {
            tempReturn.add(null);
        }
        for (int i = 0; i < temp.size(); i++) {
            tempReturn.set(i, temp.get(i));
        }

        return tempReturn;
    }

    private ArrayList<Boolean> getopponentScore() {
        ArrayList<Boolean> temp = new ArrayList(Arrays.asList(true, true, false, true, true, true));
        ArrayList<Boolean> tempReturn = new ArrayList();
        for (int i = 0; i < (getNrOfQuestions() * getNrOfRounds()); i++) {
            tempReturn.add(null);
        }
        for (int i = 0; i < temp.size(); i++) {
            tempReturn.set(i, temp.get(i));
        }

        return tempReturn;
    }

    private String getUsername() {
        return "xXx420_DABxXx";
    }

    private String getOpponentName() {
        return "kalleKarlsson__";
    }

    private int getNrOfRounds() {
        return 5;
    }

    private int getNrOfQuestions() {
        return 3;
    }

    public BorderPane getGUI() {
        return root;
    }

}
