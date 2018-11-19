
package quizkampen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

public class Registerscreen {

    UserHandler handler;
    
    BorderPane root;
    
    VBox typeContent;
    
    TextField usernameField;
    PasswordField passwordField;
    TextField mailField;
    
    Label alreadyInUse;
    
    public Registerscreen(Stage window, Scene startScene) {
        handler = new UserHandler();
        root = new BorderPane();
        
        HBox exitButton = new HBox();
        
        
        VBox content = new VBox(20);
        content.setPadding(new Insets(40, 10, 0, 10));
        typeContent = new VBox(10);
        typeContent.getStyleClass().add("typeContent");
        VBox usernameContent = new VBox(5);
        VBox passwordContent = new VBox(5);
        VBox emailContent = new VBox(5);
        
        alreadyInUse = new Label("Användaren är upptagen!");
        alreadyInUse.getStyleClass().add("alreadyInUse");
        
        Label registerLabel = new Label("Registrering");
        registerLabel.getStyleClass().add("headerLabel");
        Label usernameLabel = new Label("Användarnamn");
        Label passwordLabel = new Label("Lösenord");
        Label emailLabel = new Label("E-post");
        
        usernameField = new TextField();
        passwordField = new PasswordField();
        mailField = new TextField();
        
        Button submit = new Button("Spara användare");
        Button exit = new Button("<");
        
        submit.getStyleClass().add("loginButtons");

        //Actionhandler för tillbaka knappen
        usernameField.setOnAction(e -> {
            try {
                register();
            } catch (IOException ex) {
                Logger.getLogger(Registerscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        passwordField.setOnAction(e -> {
            try {
                register();
            } catch (IOException ex) {
                Logger.getLogger(Registerscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        mailField.setOnAction(e -> {
            try {
                register();
            } catch (IOException ex) {
                Logger.getLogger(Registerscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        submit.setOnAction(e -> {
            try {
                register();
            } catch (IOException ex) {
                Logger.getLogger(Registerscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        exit.setOnAction(e -> window.setScene(startScene));
        
        content.getChildren().add(typeContent);
        typeContent.getChildren().addAll(registerLabel, usernameContent, passwordContent, emailContent, submit);
        usernameContent.getChildren().addAll(usernameLabel, usernameField);
        passwordContent.getChildren().addAll(passwordLabel, passwordField);
        emailContent.getChildren().addAll(emailLabel, mailField);
        exitButton.getChildren().add(exit);
        
        
        //root.setTop(registerText);
        root.setCenter(content);
        root.setBottom(exitButton);
    }
    
    public BorderPane getGUI() {
        return root;
    }
    
    private void register() throws IOException {
        try (PrintWriter write = new PrintWriter(
                            new FileWriter(
                            new File("src/users.txt"), true))) {
        
            String user = usernameField.getText();
            String pass = passwordField.getText();
            String mail = mailField.getText();
            
            usernameField.setStyle(""
                                + "-fx-border: none;");
            passwordField.setStyle(""
                                + "-fx-border: none;");
            mailField.setStyle(""
                                + "-fx-border: none;");
            
            typeContent.getChildren().remove(alreadyInUse);
            
            if (handler.validateFields(user, pass, mail)) {
                if (handler.validateMail(mail)) {
                    if (handler.register(user, pass, mail)) {
                        write.println(user);
                        write.println(pass);
                        write.println(mail);

                        usernameField.setText("");
                        passwordField.setText("");
                        mailField.setText("");                        
                    }
                    else { //Ha en kontroll som kanske kontrollerar om det är specifikt mailadressen eller användarnamnet som är upptaget
                        typeContent.getChildren().add(1, alreadyInUse);
                    }
                }
                else {
                    if (!handler.validateMail(mail)) {
                        mailField.setStyle(""
                                + "-fx-border-color: red;"
                                + "-fx-border-radius: 3px;");
                    }
                }
            }
            else {
                if (usernameField.getText().equals(""))
                    usernameField.setStyle(""
                                + "-fx-border-color: red;");
                if (passwordField.getText().equals(""))
                    passwordField.setStyle(""
                                + "-fx-border-color: red;");
                if (mailField.getText().equals(""))
                    mailField.setStyle(""
                                + "-fx-border-color: red;");
            }
                
        }
    }
}
