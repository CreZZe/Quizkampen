package quizkampen;

import server.UserHandler;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Loginscreen {

    UserHandler handler;
    BorderPane root;

    HBox exitButton, buttons;
    VBox content, typeContent, usernameContent, passwordContent;

    Label loginLabel, usernameLabel, passwordLabel;

    TextField usernameField;
    PasswordField passwordField;

    Button loginButton, forgotLogin, exit;

    Stage window;
    Scene startScene;

    int windowWidth, windowHeight;

    public Loginscreen(Stage window, Scene startScene, int windowWidth, int windowHeight) {
        this.window = window;
        this.startScene = startScene;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        handler = new UserHandler();
        root = new BorderPane();

        exitButton = new HBox();
        buttons = new HBox(48);

        content = new VBox(20);
        content.setPadding(new Insets(40, 10, 0, 10));
        typeContent = new VBox(15);
        typeContent.getStyleClass().add("typeContent");
        usernameContent = new VBox(5);
        passwordContent = new VBox(5);

        loginLabel = new Label("Logga in");
        loginLabel.getStyleClass().add("headerLabel");
        usernameLabel = new Label("Användarnamn");
        passwordLabel = new Label("Lösenord");

        usernameField = new TextField();
        passwordField = new PasswordField();

        loginButton = new Button("Logga in");
        forgotLogin = new Button("Glömt lösenord");
        exit = new Button("<");

        loginButton.getStyleClass().add("loginButtons");
        forgotLogin.getStyleClass().add("loginButtons");

        usernameField.setOnAction(e -> {
            try {
                login();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Loginscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        passwordField.setOnAction(e -> {
            try {
                login();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Loginscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        loginButton.setOnAction(e -> {
            try {
                login();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Loginscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        exit.setOnAction(e -> {
            System.out.println(Quizkampen.client.sendRequestAndGetResponse("back"));
            window.setScene(startScene);

        });

        exitButton.getChildren().add(exit);
        buttons.getChildren().addAll(loginButton, forgotLogin);

        content.getChildren().add(typeContent);
        typeContent.getChildren().addAll(loginLabel, usernameContent, passwordContent, buttons);
        usernameContent.getChildren().addAll(usernameLabel, usernameField);
        passwordContent.getChildren().addAll(passwordLabel, passwordField);

        root.setCenter(content);
        root.setBottom(exitButton);
    }

    public BorderPane getGUI() {
        return root;
    }

    private void login() throws FileNotFoundException {

        System.out.println(Quizkampen.client.sendRequestAndGetResponse("loginsubmit"));

        String user = usernameField.getText();
        String pass = passwordField.getText();

        boolean login = (Quizkampen.client.sendRequestAndGetResponse(user + "," + pass)).equals("true");

        if (login) {

            Lobbyscreen lby = new Lobbyscreen(window, startScene, windowWidth, windowHeight);

            Scene lobbyScreen = new Scene(lby.getGUI(), windowWidth, windowHeight);
            lobbyScreen.getStylesheets().add("Styling.css");
            lby.updateUsernameLabel(user);

            Quizkampen.client.name = user;
            System.out.println(user);

            System.out.println("login successful");

            window.setScene(lobbyScreen);
        } else {
            usernameField.setStyle(""
                    + "-fx-border-color: red;"
                    + "-fx-border-radius: 3px;");
            passwordField.setStyle(""
                    + "-fx-border-color: red;"
                    + "-fx-border-radius: 3px;");
        }

    }
}
