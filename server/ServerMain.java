package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author nikalsh
 */
public class ServerMain {

    public static void main(String[] args) throws IOException {
        
        
        
        Properties p = new Properties();
        
        
        try{
            
            p.load(new FileInputStream("src/server/PropertiesForServer.properties"));
        }
        
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        int roundsPerGame = Integer.parseInt(p.getProperty("roundsPerGame"));
        int questionsPerRound = Integer.parseInt(p.getProperty("questionsPerRound"));
        
        System.out.println("rounds per game: " + roundsPerGame);
        System.out.println("questions per round: " + questionsPerRound);
        
        
        //SEVER ENTRY POINT
        //Run ServerMain to put server in listen & accept mode
        //See Acceptor.java
        Acceptor server = new Acceptor(55555);

    }
}
