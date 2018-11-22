
package quizkampen;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Settingsscreen {
    Stage window;
    Scene startScene;
    
    BorderPane root;
    
    VBox content;
    
    Button Hello, exit;
    
    int windowWidth, windowHeight;
    
    public Settingsscreen(Stage window, Scene startScene, int windowWidth, int windowHeight) {
        this.window = window;
        this.startScene = startScene;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        
        root = new BorderPane();
        
        /*
            Main content
        
            Dropdown-menyer med olika alternativ
                T.ex. Knappfärg med olika färgalternativ (Ändra till en annan gradient fast i den valda färgen, t.ex. grön
                
            Kanske välja om man har synskada
                Ökar fontsizen om den är i-tickad
                Det är en flip som man kan välja (JA NEJ)
                    Är den på JA så lyser den grönt
                    Är den på NEJ så är den grå eller lyser rött
        */
        
        
        
        Hello = new Button("Hello World!");
        
        exit = new Button("<");
        
        exit.setOnAction(e -> window.setScene(startScene));
        
        content = new VBox(20);
        
        content.getChildren().add(Hello);
        root.setCenter(content);
        root.setBottom(exit);
    }
    
    public BorderPane getGUI() {
        return root;
    }
}
