public class Cesar {

    /**
     * Creation of a function that verify if the text can be encrypted or decrypted
     * @param inputString The message to encrypt
     * @return if the message can be encrypted or decrypted
     */
    public static Boolean IsValidString(String inputString) {
        boolean lower = false;
        boolean upper = false;

        for (String character : inputString.split("")) {
            if (character.matches("[a-z]+")) {
                lower = true;
            }
            else if (character.matches("[A-Z]+")) {
                upper = true;
            }
            else {
                return false;
            }
        }
        if (lower && !upper) {
            return true;
        }
        else if (upper && !lower) {
            return true;
        }
        else if (lower && upper){
            return false;
        }

        return false;

    }

    /**
     * Creation of an encrypt function
     * @param plainText The message to encrypt
     * @param rot The offset of the character
     * @return the encrypted message
     */
    public static String Encrypt(String plainText, int rot) {
        StringBuilder encryptedText = new StringBuilder();


        /* we check that the string is composed only of letter */
        if(IsValidString(plainText)) {

            /* We go through the string and for each character we apply the rotation */
            for (char c : plainText.toCharArray()) {
                int ascii = (int) c;

                /* Check for lowercase character and use 97 for calculations */
                if (ascii >= 97 && ascii <= 122) {
                    ascii = ((ascii - 97 + rot) % 26 + 26) % 26 + 97;

                }
                /* Check for uppercase character and use 65 for calculations */
                else if (ascii >= 65 && ascii <= 90) {
                    ascii = ((ascii - 65 + rot) % 26+ 26) % 26 + 65;
                }
                encryptedText.append((char) ascii);
            }
        }
        else {
            encryptedText.append("Veuillez entrer un texte  avec que des lettres soit majuscule soit minuscule");
        }

        return encryptedText.toString();
    }

    /**
     * Creation of a Decrypt function
     * @param encryptedText The message to encrypted
     * @param rot The offset of the character
     * @return the decrypted message
     */
    public static String Decrypt(String encryptedText, int rot) {
        StringBuilder plainText = new StringBuilder();

        /* we check that the string is composed only of letter */
        if(IsValidString(encryptedText)) {

            /* We go through the string and for each character we remove the rotation */
            for (char c : encryptedText.toCharArray()) {
                int ascii = (int) c;

                /* Check for lowercase character and use 97 for calculations */
                if (ascii >= 97 && ascii <= 122) {
                    ascii = ((ascii - 97 - rot) % 26 + 26) % 26 + 97;
                }
                /* Check for uppercase character and use 65 for calculations */
                else if (ascii >= 65 && ascii <= 91) {
                    ascii = ((ascii - 65 - rot) % 26 + 26) % 26 + 65;
                }

                plainText.append((char) ascii);
            }
        }
        else {
            plainText.append("Veuillez entrer un texte  avec que des lettres soit majuscule soit minuscule");
        }

        return plainText.toString();
    }
}
