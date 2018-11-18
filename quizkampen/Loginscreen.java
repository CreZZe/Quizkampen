
package quizkampen;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    
    TextField usernameField;
    PasswordField passwordField;
    Button loginButton;
    
    public Loginscreen(Stage window, Scene startScene) {
        handler = new UserHandler();
        root = new BorderPane();
        
        HBox exitButton = new HBox();
        HBox buttons = new HBox(48);
        
        VBox content = new VBox(20);
        content.setPadding(new Insets(40, 10, 0, 10));
        VBox typeContent = new VBox(15);
        typeContent.getStyleClass().add("typeContent");
        VBox usernameContent = new VBox(5);
        VBox passwordContent = new VBox(5);
        
        Label loginLabel = new Label("Logga in");
        loginLabel.getStyleClass().add("headerLabel");
        Label usernameLabel = new Label("Användarnamn");
        Label passwordLabel = new Label("Lösenord");
        
        usernameField = new TextField();
        passwordField = new PasswordField();
        
        loginButton = new Button("Logga in");
        Button forgotLogin = new Button("Glömt lösenord");
        Button exit = new Button("<");
        
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
        exit.setOnAction(e -> window.setScene(startScene));
        
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
        String user = usernameField.getText();
        String pass = passwordField.getText();
        
        if (handler.login(user, pass))
            System.out.println("Inloggad");
        else
            System.out.println("Fel inlogg!");
    }
}
