
/**
 *
 * @author marcu
 * aer awesome
 */

package server;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public class GetProperties {
    
    int questionsPerRound;
    int roundsPerGame;
        
    GetProperties(){
    Properties p = new Properties();
        try{

            Path currentRelativePath = Paths.get("");
            p.load(new FileInputStream(currentRelativePath.toAbsolutePath().toString()+"/src/server/PropertiesForServer.properties/"));
            
        }
        catch(Exception e){
            System.out.println("Properties for server NOT FOUND");
           //e.printStackTrace();
        }
        
        String temp1=p.getProperty("questionsPerRound", "1");
        questionsPerRound=Integer.parseInt(temp1);
        
        String temp2=p.getProperty("roundsPerGame", "1");
        roundsPerGame=Integer.parseInt(temp2);
    }
    
    public int getQuestionsPerRound(){
        return questionsPerRound;
    }
    public int getRoundsPerGame(){
        return roundsPerGame;
        
        
    }
        
//        public static void main(String args[]){
//            //this is the testing function, uncomment to test that it works
//            
//            GetProperties p = new GetProperties();
//            System.out.println(p.getQuestionsPerRound());
//            System.out.println(p.getRoundsPerGame());
//        }
    
}
