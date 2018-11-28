package quizkampen;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Quizkampen extends Application {

    Stage window;

    Scene startingScene;

    int windowWidth = 400;
    int windowHeight = 550;
    public static Client client;

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        client = new Client();

        SettingsLoader load = new SettingsLoader();
        
        window = primaryStage;
        Startscreen s = new Startscreen(window, windowWidth, windowHeight);

        startingScene = new Scene(s.getGUI(), windowWidth, windowHeight);
        s.setScene(startingScene);
        
        /*
            Om properties-color = blå
                Endast Styling.css
        
            Om properties-color = grön
                Styling.css & default-theme.css
        */

        if (load.getColor().equals("BLÅ")) 
            startingScene.getStylesheets().setAll("Styling.css");
        else
            startingScene.getStylesheets().setAll("Styling.css", "green-theme.css");

        window.setTitle("Quizkampen");
        window.setMinWidth(400);
        window.setMinHeight(550);
        window.setScene(startingScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
