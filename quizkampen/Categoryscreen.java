package quizkampen;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

    SettingsLoader load;
    
    public Categoryscreen(Stage window, Scene startScene, int windowWidth, int windowHeight) throws IOException {
        this.window = window;
        this.startScene = startScene;
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        
        load = new SettingsLoader();
        
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
        
//        pc = new PieChart();
//        Pane center = new Pane();


        category = new Label("Välj kategori!");
        category.getStyleClass().add("startingWelcome");
        
//        ObservableList<PieChart.Data> pieChartData
//                = FXCollections.observableArrayList(
//                        new PieChart.Data(category1, 1),
//                        new PieChart.Data(category2, 1),
//                        new PieChart.Data(category3, 1));
//        final PieChart chart = new PieChart(pieChartData);
//        chart.setLabelLineLength(10);
//        chart.clockwiseProperty().setValue(Boolean.TRUE);
//        chart.setLegendVisible(false);
//        
//        
//        for (final PieChart.Data data : chart.getData()) {
//            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
//                    new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent e) {
//                    if (e.getSource().toString().contains("data0")) {
//                        System.out.println(category1);
//                    } else if (e.getSource().toString().contains("data1")) {
//                        System.out.println(category2);
//                    } else if (e.getSource().toString().contains("data2")) {
//                        System.out.println(category3);
//                    }
//                    
//                }
//            });
//        }
//        int i = 500;
//        left.getChildren().add(r);
//        right.getChildren().add(r);
//center.setMinSize(i, i);
        
        
//        center.getChildren().add(chart);
//        root.setLeft(left);
//        root.setRight(right);
//        root.setTop(category);


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
    
    private class ButtonClicked implements EventHandler<ActionEvent>{
        

        @Override
        public void handle(ActionEvent event) {
            
            Button tempButton = (Button)event.getSource();
            sendCategory(tempButton.getText());
            Scene questionScene = null;
            try {
                questionScene = new Scene(new Questionscreen(window, startScene, windowWidth, windowHeight).getGUI(), windowWidth, windowHeight);
            } catch (IOException ex) {
                Logger.getLogger(Categoryscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (load.getColor().equals("BLÅ"))
                questionScene.getStylesheets().setAll("Styling.css");
            else
                questionScene.getStylesheets().setAll("Styling.css", "green-theme.css");
            
            window.setScene(questionScene);
        }
    }

    private void getCategories() {
        category1 = "Video Games";
        category2 = "Music";
        category3 = "Geography and alot of other stuffs";
    }
    private void sendCategory(String category) {
        System.out.println(category);
    }

    public BorderPane getGUI() {
        return root;
    }
}
