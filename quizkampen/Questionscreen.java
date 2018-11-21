package quizkampen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Questionscreen {
    
    BorderPane root;
    GridPane answers;
    HBox progressBar;
    
    Label questionLabel, progressLabel1, progressLabel2;
    Button a, b, c, d;
    
    String question1, question2, ansA, ansB, ansC, ansD;
    
    String rightAnswer;
    
    boolean first = true;
    
    Stage window;
    Scene startScene;
    int windowWidth, windowHeight;
    
    ButtonClicked buttonClicked = new ButtonClicked();
    
    public Questionscreen(Stage window, Scene startScene, int windowWidth, int windowHeight) {
        this.window = window;
        this.startScene = startScene;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        
        question1 = "First Question?";
        ansA = "Right answer";     ansB = "Wrong anser 1";
        ansC = "Wrong answer 2";     ansD = "Wrong answer 3";
        
        question2 = "Second Question";
        
        rightAnswer = ansA;
        
        root = new BorderPane();
        answers = new GridPane( );
        
        progressBar = new HBox();
        progressLabel1 = new Label();
        progressLabel2 = new Label();
        
        progressBar.getChildren().add(progressLabel1);
        progressBar.getChildren().add(progressLabel2);
        
        progressLabel1.getStyleClass().add("progressLabel");
        progressLabel2.getStyleClass().add("progressLabel");
        
        questionLabel = new Label("Fråga 1");
        questionLabel.getStyleClass().add("questionLabel");
        
        a = new Button(ansA);   b = new Button(ansB);
        c = new Button(ansC);   d = new Button(ansD);
        
        a.getStyleClass().add("answerButtons");
        b.getStyleClass().add("answerButtons");
        c.getStyleClass().add("answerButtons");
        d.getStyleClass().add("answerButtons");
        
        
        
        a.setOnAction(buttonClicked);
        b.setOnAction(buttonClicked);
        c.setOnAction(buttonClicked);
        d.setOnAction(buttonClicked);
        
        answers.add(a, 0, 0);
        answers.add(b, 1, 0);
        answers.add(c, 0, 1);
        answers.add(d, 1, 1);
        
        
        answers.setVisible(false);
        
        
        questionLabel.setOnMousePressed(e -> {
            questionLabel.setText(question1);
            progressBar.setVisible(true);
            answers.setVisible(true);
        });
        
        root.setTop(progressBar);
        root.setCenter(questionLabel);
        root.setBottom(answers);
    }
    
    public class ButtonClicked implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String s = event.getSource().toString();
            
            if (first) {
                if (s.contains(rightAnswer)) {
                    progressLabel1.setStyle("-fx-background-color: linear-gradient(#7FDB1D 0%, #6FBF1A 25%, #66AF18 50%, #5A9A15 100%);");
                    first = false;
                } else {
                    progressLabel1.setStyle("-fx-background-color: red;");
                    first = false;
                }
            } else {
                if (s.contains(rightAnswer)) {
                    progressLabel2.setStyle("-fx-background-color: linear-gradient(#7FDB1D 0%, #6FBF1A 25%, #66AF18 50%, #5A9A15 100%);");
                } else {
                    progressLabel2.setStyle("-fx-background-color: red;");
                }
            }
            
            questionLabel.setText(question2);
        }
    }
    
    
    
    
    public BorderPane getGUI() {
        return root;
    }

}


