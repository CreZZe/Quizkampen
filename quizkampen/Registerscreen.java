
package quizkampen;

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

public class Registerscreen {

    BorderPane root;
    
    public Registerscreen(Stage window, Scene startScene) {
        root = new BorderPane();
        
        HBox exitButton = new HBox();
        
        
        VBox content = new VBox(20);
        content.setPadding(new Insets(40, 10, 0, 10));
        VBox typeContent = new VBox(10);
        typeContent.getStyleClass().add("typeContent");
        VBox usernameContent = new VBox(5);
        VBox passwordContent = new VBox(5);
        VBox emailContent = new VBox(5);
        
        Label registerLabel = new Label("Registrering");
        registerLabel.getStyleClass().add("headerLabel");
        Label usernameLabel = new Label("Användarnamn");
        Label passwordLabel = new Label("Lösenord");
        Label emailLabel = new Label("E-post");
        
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        TextField emailField = new TextField();
        
        Button submit = new Button("Spara användare");
        Button exit = new Button("<");
        
        submit.getStyleClass().add("loginButtons");

        //Actionhandler för tillbaka knappen
        exit.setOnAction(e -> window.setScene(startScene));
        
        content.getChildren().add(typeContent);
        typeContent.getChildren().addAll(registerLabel, usernameContent, passwordContent, emailContent, submit);
        usernameContent.getChildren().addAll(usernameLabel, usernameField);
        passwordContent.getChildren().addAll(passwordLabel, passwordField);
        emailContent.getChildren().addAll(emailLabel, emailField);
        exitButton.getChildren().add(exit);
        
        
        //root.setTop(registerText);
        root.setCenter(content);
        root.setBottom(exitButton);
    }
    
    public BorderPane getGUI() {
        return root;
    }
}
