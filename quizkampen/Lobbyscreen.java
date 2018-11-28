package quizkampen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lobbyscreen {

    BorderPane root;

    HBox topMenu, user;
    VBox content, center, activeGamesButtons;

    Label title, avatar, userName;

    ArrayList<String> games;

    Button refreshButton, statsButton, settingsButton;
    Button newGameButton;
    Button exitButton;

    Stage window;
    Scene startScene;
    int windowWidth, windowHeight;
    int nrOfActiveGames = 1;

    public Lobbyscreen(Stage window, Scene startScene, int windowWidth, int windowHeight, String css) throws IOException {

//        Quizkampen.client.setName(Quizkampen.client.sendRequestAndGetResponse("name"));
        this.window = window;
        this.startScene = startScene;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        root = new BorderPane();

        content = new VBox(0);
        activeGamesButtons = new VBox(0);

        activeGamesButtons.setSpacing(0);

        topMenu = new HBox();
        topMenu.getStyleClass().add("hbox");

        user = new HBox();

        title = new Label("Quizkampen");
        title.getStyleClass().add("startingWelcome2");

        avatar = new Label();
        avatar.setId("avatar");

        userName = new Label("Användarnamn: \n"
                + Quizkampen.client.name);

        refreshButton = new Button();
        refreshButton.setId("refreshButton");

        statsButton = new Button();
        statsButton.setId("statsButton");

        settingsButton = new Button();
        settingsButton.setId("settingsButton");

        newGameButton = new Button();
        newGameButton.setId("nyttSpelButton");

        exitButton = new Button("Logga ut");

        exitButton.setOnAction(e -> {
            System.out.println(Quizkampen.client.sendRequestAndGetResponse("back"));
            window.setScene(startScene);
        });

        newGameButton.setOnAction(e -> {

            Scene scoreScene = null;
            try {
                System.out.println(Quizkampen.client.sendRequestAndGetResponse("starta nytt spel"));

                scoreScene = new Scene(new Scorescreen(window, startScene, windowWidth, windowHeight, css).getGUI(), windowWidth, windowHeight);
            } catch (IOException ex) {
                Logger.getLogger(Lobbyscreen.class.getName()).log(Level.SEVERE, null, ex);
            }

            scoreScene.getStylesheets().setAll(css);

            window.setScene(scoreScene);
        });
        ArrayList<Button> activeGames = new ArrayList();
        for (int i = 1; i < nrOfActiveGames; i++) {
            Button game = new Button("spel " + i);
            activeGames.add(game);
            game.getStyleClass().add("activeGamesButton");
            game.setStyle("-fx-padding: 0px;");
        }

        ScrollPane sp = new ScrollPane();
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);

        topMenu.getChildren().add(title);
        topMenu.getChildren().add(refreshButton);
        topMenu.getChildren().add(statsButton);
        topMenu.getChildren().add(settingsButton);

        user.getChildren().add(avatar);
        user.getChildren().add(userName);
        activeGamesButtons.getChildren().addAll(activeGames);

        content.getChildren().add(user);
        content.getChildren().add(newGameButton);
        content.getChildren().addAll(activeGamesButtons);

        sp.setContent(content);

        root.setTop(topMenu);
        root.setCenter(sp);
        root.setBottom(exitButton);

    }

    public void updateUsernameLabel(String userName) {
        this.userName.setText(userName);
    }

    public void getCurrentGames() {

    }

    public BorderPane getGUI() {
        userName.setText("Användarnamn: \n" + Quizkampen.client.name);
        return root;
    }
}
