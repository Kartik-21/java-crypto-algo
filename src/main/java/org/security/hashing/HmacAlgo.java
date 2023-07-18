package org.security.hashing;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacAlgo {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        String message = "Kartik";
        String key = "my-secret-key";

        // Create the HMAC SHA-256 instance
        Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        hmacSHA256.init(secretKey);
        byte[] hmacBytes = hmacSHA256.doFinal(message.getBytes());
        String hmacString = bytesToHex(hmacBytes);
        System.out.println("HMAC: " + hmacString);

        ///using apache library
        HmacUtils hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, key);
        byte[] hmac = hmacUtils.hmac(message.getBytes());
        String hmacHex = Hex.encodeHexString(hmac);
        System.out.println("HMAC: " + hmacHex);

        System.out.println(hmacString.equals(hmacHex));

    }

    // Helper method to convert a byte array to a hexadecimal string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
}
