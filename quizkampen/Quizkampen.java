package quizkampen;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author 224Mi
 */
public class Quizkampen extends Application {
    // testing testing marcus dj igen
    
    
    Stage window;
    
    Scene startingScene;
    
    int windowWidth = 400;
    int windowHeight = 550;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
                
        startingScene = new Scene(new Startscreen(window, windowWidth, windowHeight).getGUI(), windowWidth, windowHeight);
        startingScene.getStylesheets().add("Styling.css");
        
        
        
        window.setTitle("Quizkampen");
        window.setScene(startingScene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}