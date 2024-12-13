public class Polybius {

    // Definition of the Polybius square
    private static final char[][] polybiusSquare = {
            { 'a', 'b', 'c', 'd', 'e' },
            { 'f', 'g', 'h', 'i', 'k' },
            { 'l', 'm', 'n', 'o', 'p' },
            { 'q', 'r', 's', 't', 'u' },
            { 'v', 'w', 'x', 'y', 'z' },
    };

    /**
     * Method to encrypt a string using the Polybius Square
     * @param inputString The message to encrypt
     * @return The encrypted message
     */
    public static String Encrypt(String inputString) {

        String outputString = "";
        String encryptedLetter;

        // Iterate over every character of the input string
        for (char letter: inputString.toCharArray()) {

            if((int) letter < 97 || (int) letter > 122) {
                outputString = outputString.concat(String.valueOf(letter));
                continue;
            }
            if(letter == 'j') {
                outputString = outputString.concat("24");
            }

            outerloop:
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    // Check the Polybius square to see if the letter is the current one
                    if(polybiusSquare[j][k] == Character.toLowerCase(letter)) {
                        encryptedLetter = (j + 1) + "" + (k + 1);
                        outputString = outputString.concat(encryptedLetter);
                        // If the letter is found, note its index and leave the loop
                        break outerloop;
                    }
                }
            }

        }

        return outputString;
    }

    public static String Decrypt(String inputString) {

        String outputString = "";
        int row, col;
        char letter;

        for (int i = 0; i < inputString.length(); i+=2) {
            /*
            To get the char as an int :
            Get the ascii index of the char and remove the ascii index of the '0' char
            */
            row = inputString.charAt(i) - '0' - 1; // We remove 1 to get the table index
            col = inputString.charAt(i + 1) - '0' - 1;
            // Get the letter at the found index and append to the result
            letter = polybiusSquare[row][col];
            outputString = outputString.concat(String.valueOf(letter));
        }

        return outputString;
    }

    /**
     * Checks if the input string is valid for Polybius square encryption (only lowercase letters)
     * @param inputString The input string
     * @return If the string is valid or not
     */
    public static Boolean IsValidString(String inputString) {

        return inputString.matches("^[a-z]+$");

    }
}
