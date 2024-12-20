package com.kartik.symmetric;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.nio.charset.StandardCharsets;

//not used now
public class TripleDESAlgo {

    private static final String ALGORITHM = "DESede";
    private static final String SECRET_KEY = "MySecretKey12345";

    public static void main(String[] args) throws Exception {
        String plainText = "Hello World";

        // Generate the key
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        DESedeKeySpec keySpec = new DESedeKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        SecretKey key = keyFactory.generateSecret(keySpec);

        // Create the cipher
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Encrypt the plaintext
        byte[] encryptedText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // Print the encrypted text
        System.out.println("Encrypted Text: " + new String(encryptedText, StandardCharsets.UTF_8));

        // Create a new cipher for decryption
        cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        // Decrypt the encrypted text
        byte[] decryptedText = cipher.doFinal(encryptedText);

        // Print the decrypted text
        System.out.println("Decrypted Text: " + new String(decryptedText, StandardCharsets.UTF_8));
    }
}
