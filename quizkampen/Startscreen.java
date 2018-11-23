package quizkampen;

import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Startscreen {

    Stage window;
    Scene startScene;
    BorderPane root;

    VBox buttons;

    HBox text, exitButton;

    Button nySpela, login, register, settings, exit;

    Label startingWelcome;

    int windowWidth, windowHeight;

    public Startscreen(Stage window, int windowWidth, int windowHeight) throws IOException, InterruptedException {

        this.window = window;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        root = new BorderPane();

        // Create boxes for top-, center- and bottomelements
        buttons = new VBox(30);
        text = new HBox();
        exitButton = new HBox(30);

        // Create buttonelements & styling
        nySpela = new Button("SPELA GRATIS");
        login = new Button("INLOGGNING");
        register = new Button("REGISTRERING");
        settings = new Button("INSTÄLLNINGAR");
        exit = new Button("EXIT");

        nySpela.getStyleClass().add("centerButtons");
        login.getStyleClass().add("centerButtons");
        register.getStyleClass().add("centerButtons");

        // Add actionhandling
        nySpela.setOnAction(e -> {
            Scene lobbyScene = new Scene(new Lobbyscreen(window, startScene, windowWidth, windowHeight).getGUI(), windowWidth, windowHeight);
            lobbyScene.getStylesheets().add("Styling.css");
            window.setScene(lobbyScene);
            System.out.println(Quizkampen.client.sendRequestAndGetResponse("nySpela"));

        });
        login.setOnAction(e -> {
            Scene loginScene = new Scene(new Loginscreen(window, startScene, windowWidth, windowHeight).getGUI(), windowWidth, windowHeight);
            loginScene.getStylesheets().add("Styling.css");
            window.setScene(loginScene);
            System.out.println(Quizkampen.client.sendRequestAndGetResponse("login"));

        });
        register.setOnAction(e -> {
            Scene registerScene = new Scene(new Registerscreen(window, startScene, windowWidth, windowHeight).getGUI(), windowWidth, windowHeight);
            registerScene.getStylesheets().add("Styling.css");
            window.setScene(registerScene);
            System.out.println(Quizkampen.client.sendRequestAndGetResponse("register"));

        });
        settings.setOnAction(e -> {
            Scene settingsScene = new Scene(new Settingsscreen(window, startScene, windowWidth, windowHeight).getGUI(), windowWidth, windowHeight);
            settingsScene.getStylesheets().add("Styling.css");
            window.setScene(settingsScene);
            System.out.println(Quizkampen.client.sendRequestAndGetResponse("settings"));

        });

        exit.setOnAction(e -> {
            //TODO: Gracefully disconnect the client before systemexit
            System.exit(0);
        });

        // Create & adjust labelelements
        startingWelcome = new Label("QUIZKAMPEN");
        startingWelcome.getStyleClass().add("startingWelcome");

        // Add the elements to the top, center and bottom boxes
        text.getChildren().add(startingWelcome);
        buttons.getChildren().addAll(nySpela, login, register, settings);
        exitButton.getChildren().add(exit);

        // Add the boxes to the root at the top, center and bottom respectively
        root.setTop(text);
        root.setCenter(buttons);
        root.setBottom(exitButton);

        // Align the elements
        text.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);
        exitButton.setAlignment(Pos.BOTTOM_RIGHT);
    }

    public void setScene(Scene startScene) {
        this.startScene = startScene;
    }

    //Return GUI to be displayed on screen
    public BorderPane getGUI() {
        return root;
    }
}
