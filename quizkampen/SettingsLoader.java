
package quizkampen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsLoader {

//    private void setValues(String ID, String value) throws FileNotFoundException, IOException {
//        Properties p = new Properties();
//        FileOutputStream out = new FileOutputStream("src/quizkampen/ClientSettings.properties");
//        
//        p.setProperty(ID, value);
//        p.store(out, null);
//        
//        out.close();
//        
//        values();
//    }
    String color;
    
    public SettingsLoader() throws FileNotFoundException, IOException {
        Properties p = new Properties();
        FileInputStream in = new FileInputStream("src/quizkampen/ClientSettings.properties");
        
        p.load(in);
        
        color = p.getProperty("color", "BLÅ");
        
        in.close();
    }
    
    public String getColor() {
        return color;
    }
}
