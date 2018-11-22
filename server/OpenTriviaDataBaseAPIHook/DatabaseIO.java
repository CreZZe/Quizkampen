package server.OpenTriviaDataBaseAPIHook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author nikalsh
 */
class DatabaseIO {

    private BufferedReader fr;
    private PrintWriter fos;
    private String tokenDir;
    private String databaseDir;
    File tokenFile;

    public DatabaseIO(String tokenDir, String databaseDir) throws UnsupportedEncodingException, FileNotFoundException {

        this.tokenDir = tokenDir;
        this.databaseDir = databaseDir;
        tokenFile = new File(this.tokenDir + "\\token.txt");
    }

    public boolean wasTokenCreatedWithinExpirationDuration() throws FileNotFoundException, IOException {

        //TODO take into account token activity
        //method currently only tracks time elapsed since token was created
        //can be used for now since method won't be invoked frequently
        long hours = 0;
        String token = "";

        if (tokenFile.isFile()) {

            fr = new BufferedReader(new FileReader(tokenFile));
            token = fr.readLine();
            System.out.println("Current token: " + token);
            hours = getHoursElapsedSince(LocalDateTime.parse(fr.readLine()));
            fr.close();

            if (hours > 6) {

                System.out.println("Token invalid! (>6 hours)");
                return false;
            }
            String isPlural = (hours <= 1 ? "hour" : "hours");
            System.out.format("Token valid for %s more %s\r\nToken generated %s %s ago\r\n" , 
                    6 - hours, (6 - hours == 1 ? "hour" : "hours"),
                    hours, (hours == 1 ? "hour" : "hours"));
            return true;
        }

        System.out.println("No token found!");
        System.out.println("Run this program again to start generating questions");
        return false;
    }

    public String getTokenFromFile() throws FileNotFoundException, IOException {

        fr = new BufferedReader(new FileReader(tokenFile));
        String token = fr.readLine();
        fr.close();
        return token;
    }

    public long getHoursElapsedSince(LocalDateTime earlier) {
        LocalDateTime now = LocalDateTime.now();
        return ChronoUnit.HOURS.between(earlier, now);
    }

    public void writeTokenToFile(String token) throws FileNotFoundException, UnsupportedEncodingException {
        fos = getPw(tokenFile, false);
        fos.println(token);
        fos.flush();
        fos.println(LocalDateTime.now());
        fos.close();
    }

    //            URLDecoder.decode(tokenDir, tokenDir)
    public void writeQuestionsToFiles(DAOQuestions daoQuestions) throws UnsupportedEncodingException, FileNotFoundException {
        DAOResults[] results = daoQuestions.getResults();

        for (int i = 0; i < results.length; i++) {

            fos = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    new File(databaseDir + results[i].getCategory() + ".txt"), true), "UTF-8")));

            fos.print(decode(results[i].getQuestion()) + ", ");

            fos.print(decode(results[i].getCorrect_answer()) + ", ");
            for (int j = 0; j < results[i].getIncorrect_answers().length; j++) {

                fos.print(decode(results[i].getIncorrect_answers()[j]) + 
                        (j == results[i].getIncorrect_answers().length - 1 ? "\r\n" : ", "));
            }
            fos.close();
        }

    }

    private String decode(String s) throws UnsupportedEncodingException {
        return URLDecoder.decode(s, "UTF-8");
    }

    public PrintWriter getPw(File file, boolean append) throws UnsupportedEncodingException, FileNotFoundException {

        return (append == true
                ? new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tokenFile, true), "UTF-8")))
                : new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tokenFile), "UTF-8"))));

    }

}
