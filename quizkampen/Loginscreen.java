
package quizkampen;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Loginscreen {

    BorderPane root;
    
    public Loginscreen() {
        root = new BorderPane();
        
        HBox loginText = new HBox();
        HBox exitButton = new HBox();
        
        VBox content = new VBox(40);
        VBox usernameContent = new VBox(10);
        VBox passwordContent = new VBox(10);
        
        Label loginLabel = new Label("Logga in");
        Label usernameLabel = new Label("Ange ett användarnamn");
        Label passwordLabel = new Label("Ange ett lösenord");
        
        TextField usernameField = new TextField();
        TextField passwordField = new TextField();
        
        Button exit = new Button("<");
        
        exitButton.getChildren().add(exit);
        loginText.getChildren().add(loginLabel);
        content.getChildren().addAll(usernameContent, passwordContent);
        usernameContent.getChildren().addAll(usernameLabel, usernameField);
        passwordContent.getChildren().addAll(passwordLabel, passwordField);
        
        loginText.setAlignment(Pos.CENTER);
        
        root.setTop(loginText);
        root.setCenter(content);
        root.setBottom(exitButton);
    }
    
    public BorderPane getGUI() {
        return root;
    }
}
