public class Polybius {

    // Definition of the Polybius square
    private static char[][] polybiusSquare = {
            { 'A', 'B', 'C', 'D', 'E' },
            { 'F', 'G', 'H', 'I', 'K' },
            { 'L', 'M', 'N', 'O', 'P' },
            { 'Q', 'R', 'S', 'T', 'U' },
            { 'V', 'W', 'X', 'Y', 'Z' },
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

            outerloop:
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    // Check the Polybius square to see if the letter is the current one
                    if(polybiusSquare[j][k] == Character.toUpperCase(letter)) {
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
        return inputString;
    }
}
