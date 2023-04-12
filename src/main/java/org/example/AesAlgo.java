package org.example;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AesAlgo {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String msg = "Kartik";
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);


        /*
         *  encrypted ciphertext is printed as a Base64-encoded string for readability.
         * */

        // TODO: Generate Any key a 256-bit AES key
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(256);
//        SecretKey secretKey = keyGenerator.generateKey();

        // TODO:Generate a random key
//        SecureRandom random = new SecureRandom();
//        byte[] keyBytes = new byte[32];
//        random.nextBytes(keyBytes);

        // TODO: Generate own 256-bit key
        byte[] key = DigestUtils.sha256("this is my key");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        System.out.println(secretKeySpec.getAlgorithm());
        System.out.println(secretKeySpec.getEncoded());

        // Initialize the cipher with the secret key and the encryption mode
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        // Encrypt the plaintext
        byte[] cipherText = cipher.doFinal(msgBytes);

        // Print the ciphertext as a Base64-encoded string
        String cipherBase64Text = Base64.getEncoder().encodeToString(cipherText);
        System.out.println(cipherBase64Text);

        // Initialize the cipher with the secret key and the decryption mode
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, cipher.getParameters());

        // Decrypt the ciphertext
        byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(cipherBase64Text));

        System.out.println(new String(decryptedText, StandardCharsets.UTF_8));
    }
}
