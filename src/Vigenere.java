import java.util.Scanner;

public class Vigenere{

    // Declaring a static Scanner object for reading user input
    public static Scanner sc = new Scanner(System.in);

    // Private static method to handle user input
    private static String EntryChain(){

        String ch; // Variable to store the input string

        while (true) {
            // Prompting the user to enter a string to be encrypted
            System.out.println("Specify the password to encrypt: ");
            ch = sc.nextLine().replace(" ", ""); // Reading the input and replace spaces by nothing

            // Validate if the input contains only letters in lowercase
            if (VerifyChainKey(ch)) {
                break; // Exit the loop if the input is valid
            } else {
                System.out.println("Error: The input must contain only letters in lowercase. Please try again.");
            }
        }

        return ch; // Returning the user input
    }

    // Private static method to handle user input
    private static String EntryKey(){

        String ch; // Variable to store the input string

        while (true) {
            // Prompting the user to enter a string to be encryted
            System.out.println("Specify the key to be able to encrypt : ");
            ch = sc.nextLine().replace(" ", ""); // Reading the input and replace spaces by nothing

            // Validate if the input contains only letters in lowercase
            if(VerifyChainKey(ch)) {
                break;
            } else {
                System.out.println("Error: the input must contain only letters in lowercase. Please try again.");
            }

        }

        return ch; // Returning the user input
    }

    // Private static method to verify if the user input contain only letters
    private static Boolean VerifyChainKey(String str){

        return str.matches("[a-z]+"); // Regular expression to check if the string contains only alphabetic characters

    }

    // Method to perform Vigenère encryption using Ci = (Pi + Ki) mod 26
    private static String VigenereEncrypt(String chainText, String key) {
        StringBuilder encrypted = new StringBuilder(); // StringBuilder to build the encrypted string
        int keyLength = key.length();

        for (int i = 0; i < chainText.length(); i++) {
            // Find the positions of plaintext and key characters
            int plainCharIndex = chainText.charAt(i) - 'a'; // Alphabetic index for the plaintext character
            int keyCharIndex = key.charAt(i % keyLength) - 'a'; // Alphabetic index for the key character

            // Apply the Vigenère formula Ci = (Pi + Ki) mod 26
            int encryptedCharIndex = (plainCharIndex + keyCharIndex) % 26;

            // Convert the index back to a character
            char encryptedChar = (char) ('a' + encryptedCharIndex);
            encrypted.append(encryptedChar); // Append the encrypted character to the result
        }

        return encrypted.toString(); // Return the encrypted string
    }

    // Main method - entry point of the program
    public static void main(String[] args){

        // Calling the entryCh() method to get the string from the user
        String chain = EntryChain();
        // Calling the entryKey() method to specify the key
        String key = EntryKey();

        // Encrypting the plaintext using the Vigenère cipher
        String encrypted = VigenereEncrypt(chain, key);

        // Displaying the input string back to the user
        System.out.println("The password to encrypt : " + chain);
        // Displaying the key to the user
        System.out.println("The key to be able to encrypt : " + key);
        // Displaying the encrypted password
        System.out.println("The encrypted password: " + encrypted);

    }

}
