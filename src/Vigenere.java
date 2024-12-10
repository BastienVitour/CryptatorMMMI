import java.util.Scanner;

public class Vigenere{

    // Declaring a static Scanner object for reading user input
    public static Scanner sc = new Scanner(System.in);

    // Private static method to handle user input
    private static String EntryChain(){

        String ch; // Variable to store the input string

        // Prompting the user to enter a string to be encrypted
        System.out.println("Specify the password to encrypt : ");
        ch = sc.nextLine().trim(); // Reading the input and removing spaces

        return ch; // Returning the user input
    }

    // Private static method to handle user input
    private static String EntryKey(){

        String ch; // Variable to store the input string

        // Prompting the user to enter a string and specify the key
        System.out.println("Specify the key to be able to encrypt : ");
        ch = sc.nextLine().trim(); // Reading the input and removing spaces

        return ch; // Returning the user input
    }

    // Main method - entry point of the program
    public static void main(String[] args){

        // Calling the entryCh() method to get the string from the user
        String chaine = EntryChain();
        // Calling the entryKey() method to specify the key
        String key = EntryKey();

        // Displaying the input string back to the user
        System.out.println("The password to encrypt : " + chaine);
        // Displaying the key to the user
        System.out.println("The key to be able to encrypt : " + key);

    }

}
