package server;

import server.OLD.NewGameRequestListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikalsh
 */
public class LobbyThread extends Thread {

    private ClientHandler clientSocket;
    private List<ClientHandler> clientList;
    private String input = "";

    //Scene constans
    private static final String LOGIN_SCENE = "login";
    private static final String REGISTER_SCENE = "register";
    private static final String GAME_SCENE = "game";
    private static final String SETTINGS_SCENE = "settings";
    private static final String MAIN_MENU_SCENE = "main";
    private static final String PRE_GAME_SCENE = "pregame";

    //ACTIONS IN SCENES
    private static final String BACK = "back";
    private static final String PLAY = "play";

    //MAIN MENU REQUESTS
    private static final String NYSPELA_BUTTON = "nyspela";
    private static final String LOGIN_BUTTON = "login";
    private static final String REGISTER_BUTTON = "register";
    private static final String SETTINGS_BUTTON = "settings";
    private static final String EXIT_BUTTON = "exit";

    //REGISTER REQUEST
    private static final String INCOMING_FORM = "form";

    private String scene = "";
    private String MAIN_MENU_REQUEST = "";
    private String GAME_REQUEST = "";
    private String PRE_GAME_REQUEST = "";
    private String REGISTER_REQUEST = "";
    private String FORM_REQUEST = "";

    private UserHandler handler = new UserHandler();

    public LobbyThread(ClientHandler currSock) {
        clientSocket = currSock;
        System.out.println(currSock);

    }

    @Override
    public void run() {
        try {
            scene = MAIN_MENU_SCENE;

            //PROTOCOL SHOULD GO HERE
            MAIN_MENU_LOOP:
            while ((MAIN_MENU_REQUEST = clientSocket.readLine()) != null) {

                if (scene.equalsIgnoreCase(MAIN_MENU_SCENE)) {

                    switch (MAIN_MENU_REQUEST) {
                        case NYSPELA_BUTTON:
                            preGame(MAIN_MENU_REQUEST);

                            continue MAIN_MENU_LOOP;

                        case REGISTER_BUTTON:

                            register(MAIN_MENU_REQUEST);
                            continue MAIN_MENU_LOOP;

                        case "login":

                            break;

                        case "settings":
                            break;

                        case "exitapp":
                            //SAVE AUTHORIZED USER STATS
                            //GRACEFULLY DISCONNECT
                            break;
                        default:
                            break;
                    }
                }

                //DO NOT ECHO
                clientSocket.println("nothing");
            }

        } catch (IOException ex) {
            Logger.getLogger(LobbyThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preGame(String MAIN_MENU_REQUEST) throws IOException {

        scene = PRE_GAME_SCENE;
        clientSocket.println(MAIN_MENU_REQUEST);

        while ((PRE_GAME_REQUEST = clientSocket.readLine()) != null) {

            switch (PRE_GAME_REQUEST) {
                case PLAY:
                    play(PRE_GAME_REQUEST);
                    break;

                case BACK:
                    clientSocket.println(BACK);
                    scene = MAIN_MENU_SCENE;
                    break;

                default:
                    clientSocket.println("ACTION NOT RECOGNIZED BY SERVER: " + PRE_GAME_REQUEST);
            }
        }
    }

    public void play(String PRE_GAME_REQUEST) {

    }

    public void register(String MAIN_MENU_REQUEST) throws IOException {
        scene = REGISTER_SCENE;

        clientSocket.println(MAIN_MENU_REQUEST);
        while ((REGISTER_REQUEST = clientSocket.readLine()) != null) {

            switch (REGISTER_REQUEST) {

                case INCOMING_FORM:
                    trySaveUser(INCOMING_FORM);

                    break;

                case BACK:
                    clientSocket.println(BACK);
                    scene = MAIN_MENU_SCENE;
                    break;

                default:
                    clientSocket.println("ACTION NOT RECOGNIZED BY SERVER: " + REGISTER_REQUEST);
                    break;
            }

        }
    }

    public void trySaveUser(String INCOMING_FORM) throws IOException {

//        scene = NEW_STATE_SCENE;
//        while ((FORM_REQUEST = clientSocket.readLine()) != null) {
            clientSocket.println("proccessing forms..");
            
            String theUserToFind = clientSocket.readLine();
            clientSocket.println(Boolean.toString(handler.findUsername(theUserToFind)));

            String validPass = clientSocket.readLine();
            clientSocket.println(Boolean.toString(handler.validatePass(validPass)));

            String validMail = clientSocket.readLine();
            clientSocket.println(Boolean.toString(handler.validateMail(validMail)));

            String theMailToFind = clientSocket.readLine();
            clientSocket.println(Boolean.toString(handler.findMail(theMailToFind)));

            String validFields = clientSocket.readLine();
            String[] splitFields = validFields.split(",", 3);

            clientSocket.println(Boolean.toString(handler.validateFields(splitFields[0], splitFields[1], splitFields[2])));

            String toRegisterFields = clientSocket.readLine();
            String[] splitRegister = toRegisterFields.split(",", 3);

            clientSocket.println(Boolean.toString(handler.register(splitRegister[0], splitRegister[1], splitRegister[2])));
//            break;
//        }
    }

}
