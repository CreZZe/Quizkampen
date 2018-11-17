package server;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikalsh
 */
public class Colors {

    private static Field[] fields = Colors.class.getDeclaredFields();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static String colorize(String input, String color) {
        try {
            Field field = null;
            for (Field f : fields) {
                if (color.equalsIgnoreCase(f.getName())) {
                    field = f;
                    break;
                }
            }
            return String.format("%s%s%s", field.get(color), input, ANSI_RESET);

        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(Colors.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
