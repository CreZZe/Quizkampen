package quizkampen;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Questionscreen {

    BorderPane root;
    GridPane answerButtons;
    HBox header;

    Stage window;
    Scene startScene;
    int windowWidth, windowHeight;

    ProgressBar pb;
    Label questionLabel, cueCard, score1, score2;

    Button a, b, c, d;
    ArrayList<Button> buttonArray;

    String question, ansA, ansB, ansC, ansD;
    String question2, ansA2, ansB2, ansC2, ansD2;
    String rightAnswer;

    Timeline timeline;
    boolean first = true;
    ButtonClicked buttonClicked = new ButtonClicked();
    int time = 10;

    public Questionscreen(Stage window, Scene startScene, int windowWidth, int windowHeight) {
        buttonArray = new ArrayList();

        this.window = window;
        this.startScene = startScene;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        getQnA();

        a = new Button(ansA);
        b = new Button(ansB);
        c = new Button(ansC);
        d = new Button(ansD);

        buttonArray.add(a);
        buttonArray.add(b);
        buttonArray.add(c);
        buttonArray.add(d);

        root = new BorderPane();
        answerButtons = new GridPane();

        pb = new ProgressBar();
        pb.setProgress(0);

        header = new HBox();
        score1 = new Label();
        score2 = new Label();

        header.getChildren().add(score1);
        header.getChildren().add(score2);
        header.getChildren().add(pb);

        score1.getStyleClass().add("progressLabel");
        score2.getStyleClass().add("progressLabel");
        pb.getStyleClass().add("progressBar");

        questionLabel = new Label(question);
        cueCard = new Label("Fråga 1");
        questionLabel.getStyleClass().add("questionLabel");
        cueCard.getStyleClass().add("questionLabel");

        a.getStyleClass().add("answerButtons");
        b.getStyleClass().add("answerButtons");
        c.getStyleClass().add("answerButtons");
        d.getStyleClass().add("answerButtons");

        a.setOnAction(buttonClicked);
        b.setOnAction(buttonClicked);
        c.setOnAction(buttonClicked);
        d.setOnAction(buttonClicked);

        answerButtons.add(a, 0, 0);
        answerButtons.add(b, 1, 0);
        answerButtons.add(c, 0, 1);
        answerButtons.add(d, 1, 1);

        answerButtons.setVisible(false);

        cueCard.setOnMousePressed(e -> {
            timer();
            root.setCenter(questionLabel);
            questionLabel.setDisable(true);
            header.setVisible(true);
            answerButtons.setVisible(true);

        });

        root.setTop(header);
        root.setCenter(cueCard);
        root.setBottom(answerButtons);
    }

    public class ButtonClicked implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            timeline.stop();

            for (Button btn : buttonArray) {
                if (btn.getText().equals(getRightAnswer())) {
                    btn.setStyle("-fx-background-color: linear-gradient(#7FDB1D 0%, #6FBF1A 25%, #66AF18 50%, #5A9A15 100%);");
                    if (first) {
                        if (((Button) event.getSource()).equals(btn)) {
                            score1.setStyle("-fx-background-color: linear-gradient(#7FDB1D 0%, #6FBF1A 25%, #66AF18 50%, #5A9A15 100%);");
                        } else {
                            ((Button) event.getSource()).setStyle("-fx-background-color: red;");
                            score1.setStyle("-fx-background-color: red;");
                        }
                    } else {
                        if (((Button) event.getSource()).equals(btn)) {
                            score2.setStyle("-fx-background-color: linear-gradient(#7FDB1D 0%, #6FBF1A 25%, #66AF18 50%, #5A9A15 100%);");
                        } else {
                            ((Button) event.getSource()).setStyle("-fx-background-color: red;");
                            score2.setStyle("-fx-background-color: red;");
                        }
                    }

                }
            }
            questionDone();
        }
    }

    private void questionDone() {
        timeline.stop();

        for (Button btn : buttonArray) {
            if (btn.getText().equals(getRightAnswer())) {
                btn.setStyle("-fx-background-color: linear-gradient(#7FDB1D 0%, #6FBF1A 25%, #66AF18 50%, #5A9A15 100%);");
            }
        }

        a.setDisable(true);
        b.setDisable(true);
        c.setDisable(true);
        d.setDisable(true);

        root.setOnMousePressed(e -> {
            if (first) {
                cueCard.setText("Fråga 2");
                root.setCenter(cueCard);
                answerButtons.setVisible(false);
                a.setDisable(false);
                b.setDisable(false);
                c.setDisable(false);
                d.setDisable(false);

            } else {
                //cueCard.setText("Rond slut!");
                root.setCenter(cueCard);
                answerButtons.setVisible(false);
                RoundDone();
            }
        });

        cueCard.setOnMouseClicked(e -> {
            if (first) {
                reset();
            } else {
                RoundDone();
            }
        });
    }

    private void reset() {
        first = false;
        questionLabel.setText(question);
        questionLabel.setDisable(true);
        root.setCenter(questionLabel);
        header.setVisible(true);
        answerButtons.setVisible(true);

        header.getChildren().remove(pb);
        pb = new ProgressBar();
        pb.setProgress(0);
        pb.getStyleClass().add("progressBar");
        header.getChildren().add(pb);

        a.setStyle("-fx-background-color: linear-gradient(#12a8ed 0%, #1097d5 25%, #0e86bc 50%, #0c75a6 100%);");
        b.setStyle("-fx-background-color: linear-gradient(#12a8ed 0%, #1097d5 25%, #0e86bc 50%, #0c75a6 100%);");
        c.setStyle("-fx-background-color: linear-gradient(#12a8ed 0%, #1097d5 25%, #0e86bc 50%, #0c75a6 100%);");
        d.setStyle("-fx-background-color: linear-gradient(#12a8ed 0%, #1097d5 25%, #0e86bc 50%, #0c75a6 100%);");

        getQnA();

        a.setDisable(false);
        b.setDisable(false);
        c.setDisable(false);
        d.setDisable(false);
        timer();
    }

    private void RoundDone() {
        timeline.stop();
        cueCard.setText("Rundan slut!");
        cueCard.setDisable(true);
        questionLabel.setDisable(true);
        
        System.out.println(Quizkampen.client.sendRequestAndGetResponse("round is done over here"));
        
        root.setOnMousePressed(e -> {
            Scene lobbyScene = new Scene(new Lobbyscreen(window, startScene, windowWidth, windowHeight).getGUI(), windowWidth, windowHeight);
            lobbyScene.getStylesheets().add("Styling.css");
            window.setScene(lobbyScene);
        });
    }

    private void getQnA() {
        
        
        if (first) {
            question = "lorem ipsum 1?";
            ansA = "Right answer";
            ansB = "Wrong anser 1";
            ansC = "Wrong answer 2";
            ansD = "Wrong answer 3";

        } else {
            question = "lorem ipsum 2?";
            ansA = "Right answer";
            ansB = "Wrong anser 1";
            ansC = "Wrong answer 2";
            ansD = "Wrong answer 3";
        }
    }

    private String getRightAnswer() {
        return "Right answer";
    }

    private void timer() {
        
        IntegerProperty seconds = new SimpleIntegerProperty();
        pb.progressProperty().bind(seconds.divide(10000.0));

        timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)), new KeyFrame(Duration.seconds(time), e -> {
            for (Button btn : buttonArray) {
                if (btn.getText().equals(getRightAnswer())) {
                    btn.setStyle("-fx-background-color: linear-gradient(#7FDB1D 0%, #6FBF1A 25%, #66AF18 50%, #5A9A15 100%);");
                }
            }
            if (first) { score1.setStyle("-fx-background-color: red;"); }
            else { score2.setStyle("-fx-background-color: red;"); }
            questionDone();
        }, new KeyValue(seconds, 10000)
        ));
        timeline.setCycleCount(1);
        timeline.play();
        pb.progressProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            double progress = newValue == null ? 0 : newValue.doubleValue();
            int red = (int) (progress*2*255);
            int green = 510-red;
            pb.setStyle(String.format("-fx-accent: rgba(%s, %s, 0, 1);", red, green));
        });
    }

    public BorderPane getGUI() {
        return root;
    }
}