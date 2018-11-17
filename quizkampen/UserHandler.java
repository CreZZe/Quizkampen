package quizkampen;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserHandler {
    File f = new File("src/users.txt");
    
    
    public boolean login(String username, String password) throws FileNotFoundException {
        try (Scanner scan = new Scanner(f)) {

            while (scan.hasNextLine()) {
                String user = scan.nextLine();
                String pass = scan.nextLine();
                scan.nextLine(); // Läsa in raden för mail som inte behöver användas AKA inloggningen handskas inte med mailen

                if (    user.toLowerCase().equals(username.toLowerCase()) 
                        && pass.equals(password))
                    return true; 
            }
            return false;
        }
    }
    
    // Border och bakgrund som blir röd-ish för att indikera fel inlogg
    
    
    // Kanske göra om så den returnerar en int för olika alternativ. T.ex. 1 för fel format på mailaddressen
    public boolean register(String username, String password, String mail) throws FileNotFoundException, IOException {
        try (Scanner scan = new Scanner(f);) {
            
            while (scan.hasNextLine()) {
                String user = scan.nextLine();
                scan.nextLine(); // Läsa in lösenordet som inte behöver användas AKA man kan ha samma lösenord på två olika konton
                String mailAddress = scan.nextLine();
                
                if (    user.toLowerCase().equals(username.toLowerCase())
                        || mailAddress.toLowerCase().equals(mail.toLowerCase()))
                    return false;
            }
            return true;
        }
    }
    
    public boolean validateMail(String mail) {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(mail);
        
        if (m.find() && m.group().equals(mail))
            return true;
        else {
            return false;
        }
    }
    
    //Två metoder som tar emot inmatning som inparametrar
        // En för inloggning
        // En för registrering
    
    
}
