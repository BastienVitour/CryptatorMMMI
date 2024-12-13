import java.util.Arrays;

public class Rc4 {
    private static final int[] S = new int[256];
    private static final int[] T = new int[256];

    /**
     * Creation of an initialise function
     * @param key The message to encrypt
     */
    public static void Initialise(String key) {
        byte[] keyByte = key.getBytes();
        /* We verify if the key byte is between 1 and 256 */
        if (keyByte.length < 1 || keyByte.length > 256) {
            throw new IllegalArgumentException(
                    "key must be between 1 and 256 bytes");
        } else {
            int keylen = keyByte.length;
            /* we fill the 2 table */
            for (int i = 0; i < 256; i++) {
                S[i] = i;
                T[i] = keyByte[i % keylen];
            }
            int j = 0;

            /* I mix the first table thanks to the 2nd */
            for (int i = 0; i < 256; i++) {
                j = (j + S[i] + T[i]) & 0xFF;
                int tmp= S[i];
                S[i] = S[j];
                S[j] = tmp;
            }
        }
    }

    /**
     * Creation of an encrypt function
     * @param plainText The message to encrypt
     * @param key The key to encrypt
     * @return returns the message encrypted as a string of the decimal value
     */
    public static String Encrypt(String plainText, String key) {
        Initialise(key);
        byte[] plain = plainText.getBytes();
        int[] cipherText = new int[plainText.length()];
        int i = 0;
        int j = 0;
        int k = 0;
        int t = 0;
        /* for the length of the plainText we recover the new value */
        for (int counter = 0; counter < plainText.length(); counter++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            int tmp = S[j];
            S[j] = S[i];
            S[i] = tmp;
            t = (S[i] + S[j]) & 0xFF;
            k = S[t];
            cipherText[counter] = (plain[counter] ^ k);
        }
        /* Transform rendering to string */
        StringBuilder result = new StringBuilder();
        for (int value : cipherText) {
            result.append(value).append(" ");
        }

        return result.toString();
    }

    /**
     * Creation of a decrypt function
     * @param encryptedText The message to decrypt
     * @param key The key to encrypt
     * @return returns the message decrypted as a string of the decimal value
     */
    public static String Decrypt(String encryptedText, String key) {
        /* I change the start value from a string to an int table*/
        String[] stringNumbers = encryptedText.split(" ");
        int[] cipherText = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            cipherText[i] = Integer.parseInt(stringNumbers[i]);
        }
        /* On utilise le même processus que pour le chiffrement */
        Initialise(key);
        int[] plainText = new int[cipherText.length];
        int i = 0;
        int j = 0;
        int k = 0;
        int t = 0;
        for (int counter = 0; counter < cipherText.length; counter++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            int tmp = S[j];
            S[j] = S[i];
            S[i] = tmp;
            t = (S[i] + S[j]) & 0xFF;
            k = S[t];
            plainText[counter] = (cipherText[counter] ^ k);
        }
        StringBuilder result = new StringBuilder();
        for (int value : plainText) {
            result.append((char) value);
        }
        return result.toString();
    }


}
