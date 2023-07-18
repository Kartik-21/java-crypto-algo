package org.security.hashing;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MdSha {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String msg = "Kartik";
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);


         /*
        * MD5: This is the original version of the MD5 algorithm, and it generates a 128-bit hash value.
          MD5-96: This version of the algorithm generates a 96-bit hash value, which is truncated from the original 128-bit hash value. It is often used in IPsec protocols.
          MD5-hmac: This is a keyed-hash message authentication code (HMAC) version of the MD5 algorithm, which is used for verifying the authenticity of a message.
        * */

        ///TODO: using apache liberty md5
        String md5Hex = DigestUtils.md5Hex(msg);
        System.out.println(md5Hex);


        /*
        * SHA-0: This was the first version of the algorithm, but it was quickly replaced by SHA-1 due to security vulnerabilities.
          SHA-1: This is a widely used hash function that generates a 160-bit hash value from the input data. It is no longer considered secure and has been deprecated in favor of newer versions.
          SHA-2: This is a family of hash functions that includes SHA-224, SHA-256, SHA-384, and SHA-512. They generate hash values of 224, 256, 384, and 512 bits, respectively. SHA-256 and SHA-512 are the most commonly used versions in practice.
          SHA-3: This is the most recent addition to the SHA family of hash functions. It includes four different versions, SHA-3-224, SHA-3-256, SHA-3-384, and SHA-3-512, and is designed to be more secure and resistant to cryptanalysis than the earlier versions.
        * */

        ////TODO: using own function SHA-2
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        byte[] digest = messageDigest.digest(msgBytes);
        System.out.println(toHexString(digest));

        ///TODO: using apache liberty SHA-2
        String sha512Hex = DigestUtils.sha512Hex(msg);
        System.out.println(sha512Hex);

        ///TODO: using apache liberty SHA-3 (AVAILABLE IN NEW VERSION JAVA)
        String sha3512Hex = DigestUtils.sha3_512Hex(msg);
        System.out.println(sha3512Hex);


    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

}