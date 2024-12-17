package com.kartik.hashing;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * generate the hash value with the help of key
 */
public class HmacAlgo {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {

        String message = "Kartik";
        String key = "my-secret-key";

        //TODO: Create the HMAC SHA-256 instance using java
        Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        hmacSHA256.init(secretKeySpec);
        byte[] hmacBytes = hmacSHA256.doFinal(message.getBytes());
        String hmacString = bytesToHex(hmacBytes);
        System.out.println("HMAC: " + hmacString);

        //TODO: using apache library
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
