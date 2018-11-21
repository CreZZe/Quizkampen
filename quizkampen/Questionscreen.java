package quizkampen;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Questionscreen {
    
    BorderPane root;
    GridPane answers;
    Label questionLabel;
    Button a, b, c, d;
    
    String question, ansA, ansB, ansC, ansD;
    
    Stage window;
    Scene startScene;
    int windowWidth, windowHeight;
    
    public Questionscreen(Stage window, Scene startScene, int windowWidth, int windowHeight) {
        this.window = window;
        this.startScene = startScene;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        
        question = "tempquestion";
        ansA = "temp1";     ansB = "temp2";
        ansC = "temp3";     ansD = "temp4";
        
        
        root = new BorderPane();
        //answers = new GridPane( );
        
        
        
        questionLabel = new Label(question);
        questionLabel.getStyleClass().add("startingWelcome");
        
//        a = new Button(ansA);   b = new Button(ansB);
//        c = new Button(ansC);   d = new Button(ansD);
        
        
        
//        answers.add(a, 0, 0);
//        answers.add(b, 1, 0);
//        answers.add(c, 0, 1);
//        answers.add(d, 1, 1);
        
        
        root.setTop(questionLabel);
//        root.setCenter(answers);
    }
    
    public BorderPane getGUI() {
        return root;
    }

}
