package quizkampen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Quizkampen extends Application {    
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

    public static void main(String[] args) {
        launch(args);
    }
    
}