package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserHandler {
    File f = new File("src/server/DB/Users/users.txt");
    /*
    USER DB ENTRY FORMAT
    - USER
    - PASS
    - SALT
    - MAIL
    */
    // Kontrollerar om den angivna användaren finns. Ska isåfall logga in
    public boolean login(String username, String password) throws FileNotFoundException {
        try (Scanner scan = new Scanner(f)) {

            while (scan.hasNextLine()) {
                String user = scan.nextLine();
                String pass = scan.nextLine();
                String salt = scan.nextLine();
                scan.nextLine(); // Läsa in raden för mail som inte behöver användas AKA inloggningen handskas inte med mailen

                if (    user.toLowerCase().equals(username.toLowerCase()) 
                        && password.equals(Hasher.hashWithSalt(salt, pass)));
                    return true; 
            }
            return false;
        }
    }    
    
    // Kontrollerar om användaren är upptagen. Annars läggs användaren till i databasen
    public boolean register(String username, String password, String mail) throws FileNotFoundException, IOException {
        try (Scanner scan = new Scanner(f);) {
            
            while (scan.hasNextLine()) {
                String user = scan.nextLine();
                scan.nextLine(); // Lösenordet behövs inte då flera användare kan ha samma lösenord
                scan.nextLine(); // TODO: kontrollera om saltet är unikt, om inte - generera nytt salt
                String mailAddress = scan.nextLine();
                
                if (    user.toLowerCase().equals(username.toLowerCase())
                        || mailAddress.toLowerCase().equals(mail.toLowerCase()))
                    return false;
            }
            
            String[] hash = Hasher.hash(password);
            String salt = hash[0];
            String hashedPw = hash[1];
            
            try (PrintWriter pw = new PrintWriter(new FileWriter(f, true))) {
                pw.println(username);
                pw.println(salt);
                pw.println(hashedPw);
                pw.println(mail);
            }
            return true;
        }
    }
    
    // Hitta användaren för att kontrollera om användarnamnet är upptaget
    public boolean findUsername(String username) throws FileNotFoundException {
        try (Scanner scan = new Scanner(f)) {
           while (scan.hasNextLine()) {
                String user = scan.nextLine();
                scan.nextLine(); // Lösenordet behövcs inte för namnkontroll
                scan.nextLine(); // Salt behövs inte för namnkontroll
                scan.nextLine(); // Mailen behövs inte för namnkontroll
                
                if (    user.toLowerCase().equals(username.toLowerCase()))
                    return true;
            }
            return false;
        }
    }
    
    // Hitta e-postadressen för att kontrollera om användarnamnet är upptaget
    public boolean findMail(String mail) throws FileNotFoundException {
        try (Scanner scan = new Scanner(f)) {
           while (scan.hasNextLine()) {
                scan.nextLine(); // Användarnamnet behövs inte för mailkontroll
                scan.nextLine(); // Lösenordet behövs inte för mailkontroll
                scan.nextLine(); // Salt behövs inte för mailkontroll
                String mailaddress = scan.nextLine();
                
                if (    mailaddress.toLowerCase().equals(mail.toLowerCase()))
                    return true;
            }
            return false;
        }
    }
    
    // Kontrollera så att formatet på e-postadressen är korrekt (text@email.com)
    public boolean validateMail(String mail) {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(mail);
        
        if (m.find() && m.group().equals(mail))
            return true;
        else {
            return false;
        }
    }
    
    public boolean validatePass(String pass) {
        Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
        Matcher m = p.matcher(pass);
        
        if (m.find() && m.group().equals(pass))
            return true;
        else
            return false;
    }
    
    public boolean validateFields(String username, String password, String mail) {
        if (username.equals("") || password.equals("") || mail.equals("")) {
            return false;
        }
        return true;
    }
}
