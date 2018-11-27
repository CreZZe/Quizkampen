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

        window = primaryStage;
        Startscreen s = new Startscreen(window, windowWidth, windowHeight);

        startingScene = new Scene(s.getGUI(), windowWidth, windowHeight);
        s.setScene(startingScene);
        startingScene.getStylesheets().add("Styling.css");

        window.setTitle("Quizkampen");
        window.setMinWidth(400);
        window.setMinHeight(550);
        window.setScene(startingScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void printStackTrace() {
        System.out.println("Printing stack trace:");

        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        
        for (int i = 1; i < elements.length; i++) {
            StackTraceElement s = elements[i];
            System.out.println("\tat " + s.getClassName() + "." + s.getMethodName()
                    + "(" + s.getFileName() + ":" + s.getLineNumber() + ")");
        }
    }

}
