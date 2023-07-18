package org.security.symmetric;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESAlgo {
    public static void main(String[] args) throws Exception {
        String plaintext = "This is a secret message";
        String keyString = "0123456789abcdef"; // 16-character hexadecimal key

        // Convert the key string to a byte array
        byte[] keyBytes = hexStringToByteArray(keyString);

        // Create a DES key specification from the key bytes
        DESKeySpec keySpec = new DESKeySpec(keyBytes);

        // Use a key factory to generate a DES key from the key specification
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(keySpec);

        // Create a DES cipher instance and initialize it for encryption
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Encrypt the plaintext using the cipher
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes());

        // Print the encrypted ciphertext as a hexadecimal string
        System.out.println(byteArrayToHexString(ciphertext));
    }

    // Utility method to convert a hexadecimal string to a byte array
    public static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                                 + Character.digit(hexString.charAt(i+1), 16));
        }
        return bytes;
    }

    // Utility method to convert a byte array to a hexadecimal string
    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
