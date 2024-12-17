package com.kartik.symmetric;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Advanced Encryption Standard
 * <p>
 * AES/ECB/PKCS5Padding -- Electronic Codebook - not required the iv
 * AES/CBC/PKCS5Padding -- Cipher Block Chaining
 * <p>
 * encrypted ciphertext is printed as a Base64-encoded string for readability.
 */
public class AESAlgo {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

        String message = "Kartik Demo";
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        final byte[] key = DigestUtils.sha256("this is my key");

        // Step 2: Predefined Initialization Vector (IV)
        String predefinedIV = "abcdefghijklmnop"; // 16 bytes for IV
        IvParameterSpec ivSpec = new IvParameterSpec(predefinedIV.getBytes());

        //TODO: Generate Any key a 256-bit AES key
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(256);
//        SecretKey secretKey = keyGenerator.generateKey();

        //TODO:Generate a random key
//        SecureRandom random = new SecureRandom();
//        byte[] keyBytes = new byte[32];
//        random.nextBytes(keyBytes);

        //TODO: Generate own 256-bit key
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        // Initialize the cipher with the secret key and the encryption mode
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);

        // Encrypt the plaintext
        byte[] cipherText = cipher.doFinal(messageBytes);

        // Print the ciphertext as a Base64-encoded string
        String cipherBase64Text = Base64.getEncoder().encodeToString(cipherText);
        System.out.println(cipherBase64Text);

        // Initialize the cipher with the secret key and the decryption mode
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);

        // Decrypt the ciphertext
        byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(cipherBase64Text));

        System.out.println(new String(decryptedText, StandardCharsets.UTF_8));
    }
}
