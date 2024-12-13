import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    // Static method to hash a string using MD5
    public static String hashMD5(String input) {
        return hash(input, "MD5");
    }

    // Static method to hash a string using SHA-256
    public static String hashSHA256(String input) {
        return hash(input, "SHA-256");
    }

    // Static method to hash a string using MD5 or SHA-256
    public static String hash(String input, String algorithm) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(input.getBytes()); // Update the digest with the input bytes
            byte[] hashedBytes = md.digest(); // Perform the hash operation

            // Convert the hashed bytes to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b); // Mask the byte to ensure positive values
                if (hex.length() == 1) hexString.append('0'); // Pad with leading zero if needed
                hexString.append(hex);
            }
            return hexString.toString(); // Return the hashed string
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash algorithm not available", e); // Handle the exception
        }
    }

}
