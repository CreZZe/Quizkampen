package quizkampen;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author nikalsh
 */
public class Hasher {

    private static final MessageDigest SHA256 = Hasher.getAlgorithm("SHA-256");
    private static final Hex HEX = new Hex();

    private static MessageDigest getAlgorithm(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hasher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String toSHA256Hex(String input) {
        byte[] hash = Hasher.SHA256.digest(input.getBytes());
        hash = HEX.encode(hash);
        return new String(hash, 0, hash.length);
    }
    
    
    
}
