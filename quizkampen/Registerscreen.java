package quizkampen;

import server.UserHandler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

public class Registerscreen {

    UserHandler handler;

    BorderPane root;

    HBox exitButton;
    VBox content, typeContent, usernameContent, passwordContent, mailContent;

    TextField usernameField, mailField;
    PasswordField passwordField;

    Label registerLabel, usernameLabel, passwordLabel, mailLabel, alreadyInUse;

    Button submit, exit;

    public Registerscreen(Stage window, Scene startScene, int windowWidth, int windowHeight) {
        handler = new UserHandler();
        root = new BorderPane();

        exitButton = new HBox();

        content = new VBox(20);
        content.setPadding(new Insets(40, 10, 0, 10));
        typeContent = new VBox(10);
        typeContent.getStyleClass().add("typeContent");
        usernameContent = new VBox(5);
        passwordContent = new VBox(5);
        mailContent = new VBox(5);

        alreadyInUse = new Label("Användaren är upptagen!");
        alreadyInUse.getStyleClass().add("alreadyInUse");

        registerLabel = new Label("Registrering");
        registerLabel.getStyleClass().add("headerLabel");
        usernameLabel = new Label("Användarnamn");
        passwordLabel = new Label("Lösenord");
        mailLabel = new Label("E-post");

        usernameField = new TextField();
        passwordField = new PasswordField();
        mailField = new TextField();

        submit = new Button("Spara användare");
        exit = new Button("<");

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
                
                String response = Quizkampen.client.sendRequestAndGetResponse("asd");
                if (!response.equalsIgnoreCase("success")) {
                    System.out.println(response);
                }
                register();
            } catch (IOException ex) {
                Logger.getLogger(Registerscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        exit.setOnAction(e -> window.setScene(startScene));

        content.getChildren().add(typeContent);
        typeContent.getChildren().addAll(registerLabel, usernameContent, passwordContent, mailContent, submit);
        usernameContent.getChildren().addAll(usernameLabel, usernameField);
        passwordContent.getChildren().addAll(passwordLabel, passwordField);
        mailContent.getChildren().addAll(mailLabel, mailField);
        exitButton.getChildren().add(exit);

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
                if (handler.validateMail(mail) && handler.validatePass(pass)) {
                    if (handler.register(user, pass, mail)) {
                        write.println(user);
                        write.println(pass);
                        write.println(mail);

                        usernameField.setText("");
                        passwordField.setText("");
                        mailField.setText("");
                    } else { //Ha en kontroll som kanske kontrollerar om det är specifikt mailadressen eller användarnamnet som är upptaget
                        if (handler.findUsername(user)) {
                            usernameField.setStyle(""
                                    + "-fx-border-color: red;");
                        }

                        if (handler.findMail(mail)) {
                            mailField.setStyle(""
                                    + "-fx-border-color: red;");
                        }

                        typeContent.getChildren().add(1, alreadyInUse);
                    }
                } else {
                    if (!handler.validateMail(mail)) {
                        mailField.setStyle(""
                                + "-fx-border-color: red;");
                    }
                    if (!handler.validatePass(pass)) {
                        passwordField.setStyle(""
                                + "-fx-border-color: red;");
                    }
                }
            } else {
                if (usernameField.getText().equals("")) {
                    usernameField.setStyle(""
                            + "-fx-border-color: red;");
                }
                if (passwordField.getText().equals("")) {
                    passwordField.setStyle(""
                            + "-fx-border-color: red;");
                }
                if (mailField.getText().equals("")) {
                    mailField.setStyle(""
                            + "-fx-border-color: red;");
                }
            }

        }
    }
}
