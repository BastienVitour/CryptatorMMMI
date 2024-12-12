public class AESData {

    public String getEncryptedText() {
        return encryptedText;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getIv() {
        return iv;
    }

    private String encryptedText;
    private String secretKey;
    private String iv;

    /**
     * Define the class parameters
     * @param encryptedText Define the encrypted text
     * @param secretKey Define the secret key
     * @param iv Define the iv
     */
    public AESData(String encryptedText, String secretKey, String iv) {
        this.encryptedText = encryptedText;
        this.secretKey = secretKey;
        this.iv = iv;
    }
    
}
