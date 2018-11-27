package quizkampen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Categoryscreen {

    Stage window;
    Scene startScene;
    int windowWidth;
    int windowHeight;
    VBox center, top;
//    PieChart pc;
//    PieChart.Data c1, c2, c3;
    Button c1, c2, c3;

    String category1, category2, category3;
    BorderPane root;
    ButtonClicked buttonClicked = new ButtonClicked();

    Label category;

    public Categoryscreen(Stage window, Scene startScene, int windowWidth, int windowHeight) {
        this.window = window;
        this.startScene = startScene;
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        top = new VBox(30);
        center = new VBox(30);

        getCategories();

        root = new BorderPane();
        c1 = new Button(category1);
        c2 = new Button(category2);
        c3 = new Button(category3);
        c1.getStyleClass().add("categoryButtons");
        c2.getStyleClass().add("categoryButtons");
        c3.getStyleClass().add("categoryButtons");

        category = new Label("Välj kategori!");
        category.getStyleClass().add("startingWelcome");

        c1.setOnAction(buttonClicked);
        c2.setOnAction(buttonClicked);
        c3.setOnAction(buttonClicked);

        center.getChildren().add(c1);
        center.getChildren().add(c2);
        center.getChildren().add(c3);

        top.getChildren().add(category);

        root.setTop(top);
        root.setCenter(center);

        center.setAlignment(Pos.TOP_CENTER);
        top.setAlignment(Pos.TOP_CENTER);

    }

    private class ButtonClicked implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            Button tempButton = (Button) event.getSource();
            sendCategory(tempButton.getText());
            Scene questionScene = new Scene(new Questionscreen(window, startScene, windowWidth, windowHeight).getGUI(), windowWidth, windowHeight);
            questionScene.getStylesheets().add("Styling.css");
            window.setScene(questionScene);
        }
    }

    private void getCategories() {

        String[] categories = Quizkampen.client.sendRequestAndGetResponse("category").split("@@@", 3);

        category1 = categories[0];
        category2 = categories[1];
        category3 = categories[2];
    }

    private void sendCategory(String category) {
        System.out.println(category);
    }

    public BorderPane getGUI() {
        return root;
    }
}
