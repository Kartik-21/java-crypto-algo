package org.example;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

public class DSADemo {
    public static void main(String[] args) throws Exception {
        // Generate a DSA key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(1024); // key size
        KeyPair keyPair = keyGen.generateKeyPair();
        
        // Sign a message
        byte[] message = "Hello, world!".getBytes("UTF-8");
        Signature signature = Signature.getInstance("SHA256withDSA");
        signature.initSign(keyPair.getPrivate());
        signature.update(message);
        byte[] sigBytes = signature.sign();
        
        // Verify the signature
        Signature verifier = Signature.getInstance("SHA256withDSA");
        verifier.initVerify(keyPair.getPublic());
        verifier.update(message);
        boolean verified = verifier.verify(sigBytes);
        
        System.out.println("Signature verified: " + verified);
    }
}
