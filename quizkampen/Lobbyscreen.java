
package quizkampen;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lobbyscreen {
    BorderPane root;
    
    HBox topMenu, user;
    VBox content, center;
     
    Label title, avatar, userName;
    
    Button refreshButton, statsButton, settingsButton;
    Button newGameButton;
    Button exitButton;
    
    Stage window;
    Scene startScene;
    int windowWidth, windowHeight;
    
    
    public Lobbyscreen(Stage window, Scene startScene, int windowWidth, int windowHeight) {
        this.window = window;
        this.startScene = startScene;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        
        root = new BorderPane();
        
        
        content = new VBox(20);
        
        topMenu = new HBox();
        topMenu.getStyleClass().add("hbox");
        
        user = new HBox();
        
        title = new Label("Quizkampen"); 
        title.getStyleClass().add("startingWelcome2");
        
        avatar = new Label();
        avatar.setId("avatar");
        
        userName = new Label("Användarnamn: \n"
                                    + "Insert unsername here...");
        
        refreshButton = new Button();
        refreshButton.setId("refreshButton");
        
        statsButton = new Button();
        statsButton.setId("statsButton");
        
        settingsButton = new Button();
        settingsButton.setId("settingsButton");
        
        newGameButton = new Button();
        newGameButton.setId("nyttSpelButton");
        
        exitButton = new Button("Logga ut");
        
        exitButton.setOnAction(e -> window.setScene(startScene));
        
        newGameButton.setOnAction(e -> {
            Scene questionScene = new Scene(new Questionscreen(window, startScene, windowWidth, windowHeight).getGUI(), windowWidth, windowHeight);
            questionScene.getStylesheets().add("Styling.css");
            window.setScene(questionScene);
        });

        
        topMenu.getChildren().add(title);
        topMenu.getChildren().add(refreshButton);
        topMenu.getChildren().add(statsButton);
        topMenu.getChildren().add(settingsButton);
        
        user.getChildren().add(avatar);
        user.getChildren().add(userName);
        
        content.getChildren().add(user);
        content.getChildren().add(newGameButton);
        
        
        root.setTop(topMenu);
        root.setCenter(content);
        root.setBottom(exitButton);
        
    }
    
    public BorderPane getGUI() {
        return root;
    }
}
