package server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author nikalsh
 */
public class Hasher {
    
    private static final MessageDigest SHA256 = Hasher.getAlgorithm("SHA-256");
    private static final Hex HEX = new Hex();

    private Hasher() {
    }

    private static MessageDigest getAlgorithm(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hasher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @param password
     * @return array[0] = salt array[1] = the hash (hashed(salt+password))
     */
    public static String[] hash(String password) {

        String salt = Hasher.salter();
        String toHash = salt + password;
        byte[] hash = Hasher.SHA256.digest(toHash.getBytes());

        hash = HEX.encode(hash);
        String[] result = new String[]{salt, new String(hash, 0, hash.length)};
        return result;
    }

    private static String salter() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[32];
        random.nextBytes(salt);

        salt = HEX.encode(salt);
        return new String(salt, 0, salt.length);
    }

    public static String hashWithSalt(String salt, String password) {

        String toHash = salt + password;
        byte[] hash = Hasher.SHA256.digest(toHash.getBytes());

        hash = HEX.encode(hash);
        String result = new String(hash, 0, hash.length);
        return result;
    }

    public static boolean testHasher(String password) {
        String[] hash = Hasher.hash(password);
        String tryHash = hashWithSalt(hash[0], password);
        System.out.println(tryHash.equals(hash[1]));
        
     return tryHash.equals(hash[1]);
        
    }

}
