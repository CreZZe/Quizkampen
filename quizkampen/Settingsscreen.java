
package quizkampen;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Settingsscreen {
    Stage window;
    Scene startScene;
    
    BorderPane root;
    
    VBox content;
    
    ComboBox synSkadaBox, colorBox;
    
    Label synSkadaLabel, colorLabel;
    
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
        synSkadaLabel = new Label("Synskada: ");
        
        synSkadaBox = new ComboBox();
        synSkadaBox.getItems().addAll(
            "NEJ",
            "JA"
        );
        synSkadaBox.setValue("NEJ");
        
        colorLabel = new Label("Färgtema: ");
        
        colorBox = new ComboBox();
        colorBox.getItems().addAll(
            "Blå",
            "Grön",
            "Gul",
            "Röd",
            "Lila"
        );
        colorBox.setValue("Blå");
        
        Hello = new Button("Hello World!");
        
        exit = new Button("<");
        
        exit.setOnAction(e -> window.setScene(startScene));
        
        content = new VBox(20);
        
        content.getChildren().addAll(synSkadaLabel, synSkadaBox, colorLabel, colorBox);
        root.setCenter(content);
        root.setBottom(exit);
    }
    
    public BorderPane getGUI() {
        return root;
    }
}
