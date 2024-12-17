package com.kartik.asymmetric;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class RsaAlgo {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        String msg = "Kartik";
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);

        // Generate a key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Create a Cipher object and initialize it with the public key
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        // Encrypt the plaintext
        byte[] ciphertext = cipher.doFinal(msgBytes);
        String s = Base64.getEncoder().encodeToString(ciphertext);
        System.out.println(s);

        // Decrypt the ciphertext
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(s));

        String m = new String(decryptedText, StandardCharsets.UTF_8);
        System.out.println(m);

    }
}
