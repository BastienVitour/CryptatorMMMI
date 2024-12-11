import java.util.Scanner;

public class Vigenere{

    // Private static method to verify if the user input contain only letters
    private static Boolean VerifyChainKey(String str){

        return str.matches("[a-z]+"); // Regular expression to check if the string contains only alphabetic characters

    }

    // Method to perform Vigenère encryption using Ci = (Pi + Ki) mod 26
    public static String VigenereEncrypt(String chainText, String key) {
        StringBuilder encrypted = new StringBuilder(); // StringBuilder to build the encrypted string
        int keyLength = key.length();

        for (int i = 0; i < chainText.length(); i++) {
            // Find the positions of plaintext and key characters
            int plainCharIndex = chainText.charAt(i) - 'a'; // Alphabetic index for the plaintext character
            int keyCharIndex = key.charAt(i % keyLength) - 'a'; // Alphabetic index for the key character

            // Apply the Vigenere formula Ci = (Pi + Ki) mod 26
            int encryptedCharIndex = (plainCharIndex + keyCharIndex) % 26;

            // Convert the index back to a character
            char encryptedChar = (char) ('a' + encryptedCharIndex);
            encrypted.append(encryptedChar); // Append the encrypted character to the result
        }

        return encrypted.toString(); // Return the encrypted string
    }

    // Method to perform Vigenère decryption using Pi = (Ci - Ki + 26) mod 26
    private static String VigenereDecrypt(String encryptedText, String key) {
        StringBuilder decrypted = new StringBuilder(); // StringBuilder to build the decrypted string
        int keyLength = key.length();

        for (int i = 0; i < encryptedText.length(); i++) {
            // Find the positions of ciphertext and key characters
            int encryptedCharIndex = encryptedText.charAt(i) - 'a'; // Alphabetic index for the ciphertext character
            int keyCharIndex = key.charAt(i % keyLength) - 'a'; // Alphabetic index for the key character

            // Apply the Vigenère formula Pi = (Ci - Ki + 26) mod 26
            int decryptedCharIndex = (encryptedCharIndex - keyCharIndex + 26) % 26;

            // Convert the index back to a character
            char decryptedChar = (char) ('a' + decryptedCharIndex);
            decrypted.append(decryptedChar); // Append the decrypted character to the result
        }

        return decrypted.toString(); // Return the decrypted string
    }

}
