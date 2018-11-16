package quizkampen;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
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
                                
        Startscreen s = new Startscreen(window, windowWidth, windowHeight);        
        
        startingScene = new Scene(s.getGUI(), windowWidth, windowHeight);
        s.setScene(startingScene);
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