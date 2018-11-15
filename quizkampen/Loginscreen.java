
package quizkampen;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Loginscreen {

    BorderPane root;
    
    public Loginscreen(Stage window, Scene startScene) {
        root = new BorderPane();
        
        HBox exitButton = new HBox();
        HBox buttons = new HBox(68);
        
        VBox content = new VBox(20);
        VBox typeContent = new VBox(15);
        typeContent.getStyleClass().add("typeContent");
        VBox usernameContent = new VBox(5);
        VBox passwordContent = new VBox(5);
        
        Label loginLabel = new Label("Logga in");
        loginLabel.getStyleClass().add("headerLabel");
        Label usernameLabel = new Label("Användarnamn");
        Label passwordLabel = new Label("Lösenord");
        
        TextField usernameField = new TextField();
        TextField passwordField = new TextField();
        
        Button loginButton = new Button("Logga in");
        Button forgotLogin = new Button("Glömt lösenord");
        Button exit = new Button("<");
        
        loginButton.getStyleClass().add("loginButtons");
        forgotLogin.getStyleClass().add("loginButtons");
        
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
}
