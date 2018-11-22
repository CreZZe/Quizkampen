package server.OpenTriviaDataBaseAPIHook;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 * @author nikalsh
 */
public class OpenTDBHookMAIN {
//   https://opentdb.com/api_config.php     
//      GLOBAL COUNT
//      https://opentdb.com/api_count_global.php    

    private String token = "";
    private DatabaseIO databaseIO;
    private HttpURLConnection connect;
    private HookConfig hookConfig;
    private BufferedReader in;
    private URL apiRequest;
    private String currDir = System.getProperty("user.dir");
    private String databaseDir = "";
    private String tokenDir = "";
    private int cofficient;

    public OpenTDBHookMAIN(int cofficient) throws ProtocolException, IOException {
        this.cofficient = cofficient;
        this.setDatabaseDirectory("\\src\\quizkampen\\questions\\");
        this.setTokenDirectory("\\src\\server\\opentriviadatabaseapihook\\");

        databaseIO = new DatabaseIO(tokenDir, databaseDir);

        if (!databaseIO.wasTokenCreatedWithinExpirationDuration()) {
            apiRequest = new URL(ApiConstants.NEW_TOKEN);
            generateToken(apiRequest);

        } else {
            hookConfig = new HookConfig(databaseIO.getTokenFromFile());
            apiRequest = new URL(hookConfig.getQuestionRequest());
            for (int i = 0; i < this.cofficient; i++) {
                generateQuestions(apiRequest);

            }

        }

    }

    public void generateToken(URL request) throws IOException {
        sendGET(request);
        in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        String JSON = in.readLine();
        in.close();

        DAOToken daoToken = new DAOToken();
        daoToken = new ObjectMapper().readValue(JSON, DAOToken.class);

        databaseIO.writeTokenToFile(daoToken.getToken());
    }

    public void generateQuestions(URL request) throws IOException {
        sendGET(request);
        in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        String JSON = in.readLine();
        in.close();

        DAOQuestions daoQuestions = new DAOQuestions();
        daoQuestions = new ObjectMapper().readValue(JSON, DAOQuestions.class);

        databaseIO.writeQuestionsToFiles(daoQuestions);
    }

    public void sendGET(URL url) throws IOException {
        connect = (HttpURLConnection) url.openConnection();
        connect.setRequestMethod("GET");

    }

    public void setDatabaseDirectory(String path) {
        databaseDir = currDir + path;
    }

    public void setTokenDirectory(String path) {
        tokenDir = currDir + path;
    }

    public static void main(String[] args) throws IOException {
        OpenTDBHookMAIN hook = new OpenTDBHookMAIN(1);

    }

}
