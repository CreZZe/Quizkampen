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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import server.GetProperties;

public class Questionscreen {
    

    private enum Colors {
        GREEN("-fx-background-color: linear-gradient(#7FDB1D 0%, #6FBF1A 25%, #66AF18 50%, #5A9A15 100%);"),
        RED("-fx-background-color: linear-gradient(#FF3939 0%, #D83030 25%, #BF2B2B 50%, #AA2626 100%);"),
        BLUE("-fx-background-color: linear-gradient(#12a8ed 0%, #1097d5 25%, #0e86bc 50%, #0c75a6 100%);");
        private final String stringValue;

        Colors(final String s) {
            stringValue = s;
        }

        public String toString() {
            return stringValue;
        }
    }

    BorderPane root;
    GridPane answerButtons;
    HBox header;
    VBox headDivider;
    StackPane timer;

    Stage window;
    Scene startScene;
    int windowWidth, windowHeight;

    ProgressBar timerBar;
    Label questionLabel, cueCard, timerLabel;
    ArrayList<Label> scoreLabels;

    Button a, b, c, d;
    ArrayList<Button> buttonArray;

    String question, ansA, ansB, ansC, ansD;

    String rightAnswer;
    String right;

    Timeline timeline;

    ButtonClicked buttonClicked = new ButtonClicked();
    int time = 20;
    int nrOfQuestions;
    int questionCounter = 0;
    boolean wait = true;
    
    public void updateButtons() {
        a.setText(ansA);
        b.setText(ansB);
        c.setText(ansC);
        d.setText(ansD);
        questionLabel.setText(question);
    }

    public Questionscreen(Stage window, Scene startScene, int windowWidth, int windowHeight) {
        
        
        this.window = window;
        this.startScene = startScene;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        root = new BorderPane();
        header = new HBox();
        headDivider = new VBox();
        timer = new StackPane();
        answerButtons = new GridPane();
        
        setNrOfQuestions();

        scoreLabels = new ArrayList();
        for (int i = 0; i < nrOfQuestions; i++) {
            Label score = new Label();
            score.getStyleClass().add("scoreLabel");
            scoreLabels.add(score);
        }

        timerBar = new ProgressBar();
        timerBar.getStyleClass().add("progressBar");
        timerBar.setProgress(0);

        timerLabel = new Label();
        timerLabel.getStyleClass().add("timerLabel");

        timer.getChildren().add(timerBar);
        timer.getChildren().add(timerLabel);

        questionLabel = new Label();
        questionLabel.getStyleClass().add("questionLabel");
        

        cueCard = new Label(String.format("Fråga: %s", (questionCounter + 1)));
        cueCard.getStyleClass().add("questionLabel");

        a = new Button();
        b = new Button();
        c = new Button();
        d = new Button();

        buttonArray = new ArrayList();
        buttonArray.add(a);
        buttonArray.add(b);
        buttonArray.add(c);
        buttonArray.add(d);

        a.getStyleClass().add("answerButtons");
        b.getStyleClass().add("answerButtons");
        c.getStyleClass().add("answerButtons");
        d.getStyleClass().add("answerButtons");

        answerButtons.add(a, 0, 0);
        answerButtons.add(b, 1, 0);
        answerButtons.add(c, 0, 1);
        answerButtons.add(d, 1, 1);

        header.getChildren().addAll(scoreLabels);

        headDivider.getChildren().add(header);
        headDivider.getChildren().add(timer);
        headDivider.setAlignment(Pos.TOP_LEFT);
        answerButtons.setVisible(false);

        root.setTop(headDivider);
        root.setCenter(cueCard);
        root.setBottom(answerButtons);

        cueCard.setOnMousePressed(e -> {
            
            getQnA();
            updateButtons();
            timer();
            setRight();
            
            root.setCenter(questionLabel);
            questionLabel.setDisable(true);
            header.setVisible(true);
            headDivider.setVisible(true);
            answerButtons.setVisible(true);
            System.out.println("");
        });
        

        a.setOnAction(buttonClicked);
        b.setOnAction(buttonClicked);
        c.setOnAction(buttonClicked);
        d.setOnAction(buttonClicked);
    }

    public class ButtonClicked implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //            Quizkampen.client.sendRequestAndGetResponse()            
            timeline.stop();

            for (Button btn : buttonArray) {
                if (btn.getText().equals(right)) {
                    btn.setStyle(Colors.GREEN.toString());

                    if (((Button) event.getSource()).equals(btn)) {
                        btn.setStyle(Colors.GREEN.toString());
                        if (((Button) event.getSource()).equals(btn)) {
                            scoreLabels.get(questionCounter).setStyle(Colors.GREEN.toString());
                        }
                    } else {
                        ((Button) event.getSource()).setStyle(Colors.RED.toString());
                        scoreLabels.get(questionCounter).setStyle(Colors.RED.toString());
                    }
                }
            }
            questionDone();
        }
    }

    private void questionDone() {
        for (Button btn : buttonArray) {
            if (btn.getText().equals(right)) {
                btn.setStyle(Colors.GREEN.toString());
            }
        }
        questionCounter++;
        cueCard.setDisable(false);
        wait = false;
        a.setDisable(true);
        b.setDisable(true);
        c.setDisable(true);
        d.setDisable(true);

        root.setOnMousePressed(e -> {
            if (!wait) {
                if (questionCounter < nrOfQuestions) {
                    cueCard.setText(String.format("Fråga: %s", questionCounter + 1));
                    root.setCenter(cueCard);
                    answerButtons.setVisible(false);
                    a.setDisable(false);
                    b.setDisable(false);
                    c.setDisable(false);
                    d.setDisable(false);
                } else {
                    root.setCenter(cueCard);
                    answerButtons.setVisible(false);
                    RoundDone();
                }
            }
        });

        cueCard.setOnMouseClicked(e -> {
            if (questionCounter < nrOfQuestions) {
                reset();
            } else {
                RoundDone();
            }
        });
    }

    private void reset() {
        questionLabel.setDisable(false);
        timeline.stop();
        questionLabel.setText(question);

        cueCard.setDisable(true);
        root.setCenter(questionLabel);
        header.setVisible(true);
        answerButtons.setVisible(true);

        timer.getChildren().remove(timerBar);
        timerBar = new ProgressBar();
        timerBar.setProgress(0);
        timerBar.getStyleClass().add("progressBar");

        timer.getChildren().remove(timerBar);
        timer.getChildren().remove(timerLabel);
        timer.getChildren().add(timerBar);
        timer.getChildren().add(timerLabel);

        a.setStyle(Colors.BLUE.toString());
        b.setStyle(Colors.BLUE.toString());
        c.setStyle(Colors.BLUE.toString());
        d.setStyle(Colors.BLUE.toString());

        a.setDisable(false);
        b.setDisable(false);
        c.setDisable(false);
        d.setDisable(false);

        questionLabel.setDisable(true);
        timer();
        wait = true;
    }

    private void RoundDone() {
        timeline.stop();
        cueCard.setText("Rundan slut!");
        cueCard.setDisable(true);
        questionLabel.setDisable(true);

//        System.out.println(Quizkampen.client.sendRequestAndGetResponse("round is done over here"));
        root.setOnMousePressed(e -> {
            System.out.println(Quizkampen.client.sendRequestAndGetResponse("back"));
            Scene gameScene = new Scene(new Gamescreen(window, startScene, windowWidth, windowHeight).getGUI(), windowWidth, windowHeight);
            gameScene.getStylesheets().add("Styling.css");
            window.setScene(gameScene);

        });
    }

    private void timer() {

        IntegerProperty seconds = new SimpleIntegerProperty();
        timerBar.progressProperty().bind(seconds.divide(1000.0));

        timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)), new KeyFrame(Duration.seconds(time), e -> {
            for (Button btn : buttonArray) {
                if (btn.getText().equals(right)) {
                    btn.setStyle(Colors.GREEN.toString());
                }
            }
            scoreLabels.get(questionCounter).setStyle(Colors.RED.toString());
            
            questionDone();
        }, new KeyValue(seconds, 1000)
        ));

        timeline.setCycleCount(1);
        timeline.play();

        timerBar.progressProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            double progress = newValue == null ? 0 : newValue.doubleValue();
            int red = (int) (progress * 2 * 255);
            int green = 510 - red;
            timerBar.setStyle(String.format("-fx-accent: rgba(%s, %s, 0, 1);", red, green));
            int sLeft = time - (int) (progress * time);

            timerLabel.setText("" + sLeft);
        });
    }

    private void getQnA() {
        String questionsAndAnswers = Quizkampen.client.sendRequestAndGetResponse("question");
        System.out.println("recieved: " + questionsAndAnswers);
        String[] arr = questionsAndAnswers.split("@@@", 5);

        question = arr[0];
        ansA = arr[1];
        ansB = arr[2];
        ansC = arr[3];
        ansD = arr[4];
    }

    public void setRight() {
        right = Quizkampen.client.sendRequestAndGetResponse("right");
        System.out.println("recieved: " + right);
    }
    
    public void setNrOfQuestions() {
        
        GetProperties properties = new GetProperties();
        nrOfQuestions = properties.getQuestionsPerRound();
        
    }

    public BorderPane getGUI() {
        return root;
    }
}
