import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HMAC {

    // Static method to generate HMAC hash
    public static String generateHash(String message, String salt, String pepper, String secretKey) {
        try {
            // Combine message and secret key (salt and pepper can be omitted if not needed)
            String combinedMessage = salt + message + pepper + secretKey;

            // Create a MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(combinedMessage.getBytes()); // Perform hashing

            // Convert hash bytes to hexadecimal for readability
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    // Convert bytes to hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
