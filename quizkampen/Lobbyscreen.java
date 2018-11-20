
package quizkampen;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Lobbyscreen {
    BorderPane root;
    
    VBox content;
    
    public Lobbyscreen(Stage window, Scene startScene, int windowWidth, int windowHeight) {
        root = new BorderPane();
        
        // Allt mellan kommentarerna bör tas bort och designas efter Quizkampens-lobbyskärm
        // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        content = new VBox(20);
        
        Button Hello = new Button("Hello World!");
        Button exitButton = new Button("Logga ut");
        
        exitButton.setOnAction(e -> window.setScene(startScene));
        
        content.getChildren().add(Hello);
        
        root.setCenter(content);
        root.setBottom(exitButton);
        // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^    
    }
    
    public BorderPane getGUI() {
        return root;
    }
}
