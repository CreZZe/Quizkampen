
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

public class Registerscreen {

    BorderPane root;
    
    public Registerscreen(Stage window, Scene startScene) {
        root = new BorderPane();
        
        HBox registerText = new HBox();
        HBox exitButton = new HBox();
        
        VBox content = new VBox(40);
        VBox usernameContent = new VBox(10);
        VBox passwordContent = new VBox(10);
        
        Label registerLabel = new Label("Registrering");
        Label usernameLabel = new Label("Ange ett användarnamn");
        Label passwordLabel = new Label("Ange ett lösenord");
        
        TextField usernameField = new TextField();
        TextField passwordField = new TextField();
        
        Button submit = new Button("Spara användare");
        Button exit = new Button("<");
        
        //Actionhandler för tillbaka knappen
        exit.setOnAction(e -> window.setScene(startScene));
        
        registerText.getChildren().add(registerLabel); 
        content.getChildren().addAll(usernameContent, passwordContent, submit);
        usernameContent.getChildren().addAll(usernameLabel, usernameField);
        passwordContent.getChildren().addAll(passwordLabel, passwordField);
        exitButton.getChildren().add(exit);
        
        registerText.setAlignment(Pos.CENTER);
        
        root.setTop(registerText);
        root.setCenter(content);
        root.setBottom(exitButton);
    }
    
    public BorderPane getGUI() {
        return root;
    }
}
