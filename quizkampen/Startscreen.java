
package quizkampen;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    int windowWidth;
    int windowHeight;
    
    public Startscreen(Stage window, int windowWidth, int windowHeight) {
        this.window = window;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        
        root = new BorderPane();
                
        // Create boxes for top-, center- and bottomelements
        VBox buttons = new VBox(35);
        HBox text = new HBox();
        HBox exitButton = new HBox(30);

        
        // Create buttonelements & styling
        Button nySpela = new Button("SPELA GRATIS");
        Button login = new Button("INLOGGNING");
        Button register = new Button("REGISTRERING");
        Button exit = new Button("EXIT");
        
        nySpela.getStyleClass().add("centerButtons");
        login.getStyleClass().add("centerButtons");
        register.getStyleClass().add("centerButtons");
        
        
        // Add actionhandling
        login.setOnAction(e -> {
<<<<<<< HEAD
=======
            Scene loginScene = new Scene(new Loginscreen(window, startScene, windowWidth, windowHeight).getGUI(), windowWidth, windowHeight);
            loginScene.getStylesheets().add("Styling.css");
            window.setScene(loginScene);
>>>>>>> 0a855b47381a2572debb7eeb056fb76f2f451b02
        });
        register.setOnAction(e -> {
            Scene registerScene = new Scene(new Registerscreen(window, startScene, windowWidth, windowHeight).getGUI(), windowWidth, windowHeight);
            registerScene.getStylesheets().add("Styling.css");
            window.setScene(registerScene);
        });
        exit.setOnAction(e -> System.exit(0));
        
        // Create & adjust labelelements
        Label startingWelcome = new Label("QUIZKAMPEN");
        
        startingWelcome.getStyleClass().add("startingWelcome");
        
        // Add the elements to the top, center and bottom boxes
        text.getChildren().add(startingWelcome);
        buttons.getChildren().addAll(nySpela, login, register, exit);
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
