package quizkampen;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class UserHandler {
    File f = new File("src/users.txt");
    
    Scanner scan;
    
    public void login(String username, String password) throws FileNotFoundException {
        scan = new Scanner(f);
//        System.out.println(user);
        while (scan.hasNextLine()) {
            String user = scan.nextLine();
            String pass = scan.nextLine();
            String mail = scan.nextLine();

            if (!user.equals(username) || !pass.equals(password))
                System.out.println("Fel inlogg!"); // Border och bakgrund som blir röd-ish för att indikera fel inlogg
            else
                System.out.println("Inloggad!");
        }
    }
    
    //Två metoder som tar emot inmatning som inparametrar
        // En för inloggning
        // En för registrering
    
    
}
