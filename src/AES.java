import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AES {

    /**
     * Encrypt a string using AES encryption
     * @param inputString The string to encrypt
     * @return An object containing the encrypted result, the generated secret key and the initialisation vector
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static AESData Encrypt(String inputString) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {

        // Init the variables
        String secretKey = GenerateSecretKey(256);
        String iv = GenerateInitialisationVector();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        byte[] keyBytes = secretKey.getBytes();
        SecretKey secretKeyParam = new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");
        IvParameterSpec ivParam = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeyParam, ivParam);
        byte[] cipherText = cipher.doFinal(inputString.getBytes());

        StringBuilder outputText = new StringBuilder();
        for (byte b : cipherText) {
            outputText.append(b).append(" ");
        }

        return new AESData(outputText.toString(), secretKey, iv);

    }

    /**
     * Decrypt a string using AES encryption
     * @param inputString The string to decrypt
     * @param secretKey The secret key used to decrypt the string
     * @param iv The initialisation vector used to decrypt the string
     * @return The decrypted string
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String Decrypt(String inputString, String secretKey, String iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] keyBytes = secretKey.getBytes();
        SecretKey key = new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");

        IvParameterSpec ivParam = new IvParameterSpec(iv.getBytes());

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, ivParam);

        String[] splitInputString = inputString.split(" ");
        byte[] inputStringBytes = new byte[splitInputString.length];

        for (int i = 0; i < splitInputString.length; i++) {
            inputStringBytes[i] = Byte.decode(splitInputString[i]);
        }

        byte[] plainText = cipher.doFinal(inputStringBytes);
        return new String(plainText);

    }

    /**
     * Generates a secret key using an LFSR
     * @param size The size of the key to generate
     * @return The secret key
     */
    private static String GenerateSecretKey(int size) {
        String initialKey = LFSR.RandomHexString(size + "AESSeed", 15);
        initialKey = initialKey.substring(initialKey.length() - size / 8);

        return initialKey;
    }

    /**
     * Generates the initialisation vector
     * @return The initialisation vector
     */
    private static String GenerateInitialisationVector() {

        String iv = LFSR.RandomHexString("128" + "IVSeed", 15);
        iv = iv.substring(iv.length() - 16);

        return iv;

    }

}

