
package quizkampen;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Settingsscreen {
    Stage window;
    Scene startScene;
    
    BorderPane root;
    
    VBox content;
    
    HBox synSkada, color;
    
    ComboBox colorBox, synSkadaBox;
    
    Label synSkadaLabel, colorLabel;
    
    Button exit;
    
    int windowWidth, windowHeight;
    
    String colorValue, eyeProblemValue;
    
    public Settingsscreen(Stage window, Scene startScene, int windowWidth, int windowHeight) throws IOException {
        this.window = window;
        this.startScene = startScene;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        
        root = new BorderPane();
         
        values();
        
        Properties p = new Properties();        
        FileOutputStream out = new FileOutputStream("src/quizkampen/ClientSettings.properties");
        
        
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
        synSkadaLabel = new Label("Synskada:");
        synSkadaLabel.setAlignment(Pos.CENTER);

        synSkadaBox = new ComboBox();
        synSkadaBox.getItems().addAll(
            "NEJ",
            "JA"
        );
        synSkadaBox.setValue(eyeProblemValue);

        colorLabel = new Label("Färgtema:");
        colorLabel.setAlignment(Pos.CENTER);

        colorBox = new ComboBox();
        colorBox.getItems().addAll(
            "BLÅ",
            "GRÖN"
        );
        colorBox.setValue(colorValue);

        exit = new Button("<");

        exit.setOnAction(e -> {
            try {
                Quizkampen.client.sendRequestAndGetResponse("back");
    
                p.setProperty("color", colorBox.getValue().toString());
                p.setProperty("eyeProblem", synSkadaBox.getValue().toString());
                p.store(out, null);
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Settingsscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            window.setScene(startScene);
        });
        
        window.setOnCloseRequest(e -> {
            try {
                p.setProperty("color", colorBox.getValue().toString());
                p.setProperty("eyeProblem", synSkadaBox.getValue().toString());
                p.store(out, null);
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Settingsscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        synSkada = new HBox();
        synSkada.setId("synSkada");

        color = new HBox();
        color.setId("color");

        content = new VBox(20);
        content.setId("settingsContent");

        synSkada.getChildren().addAll(synSkadaLabel, synSkadaBox);
        color.getChildren().addAll(colorLabel, colorBox);
        content.getChildren().addAll(synSkada, color);
        root.setCenter(content);
        root.setBottom(exit);
    }
    
    public BorderPane getGUI() {
        return root;
    }
    
    private void values() throws IOException {
        Properties p = new Properties();
        FileInputStream in = new FileInputStream("src/quizkampen/ClientSettings.properties");
        
        p.load(in);
        
        colorValue = p.getProperty("color", "BLÅ");
        eyeProblemValue = p.getProperty("eyeProblem", "NEJ");
                
        in.close();
    }
}
