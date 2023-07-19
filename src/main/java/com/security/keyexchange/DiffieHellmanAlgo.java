package com.security.keyexchange;

import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;

public class DiffieHellmanAlgo {

    public static void main(String[] args) throws Exception {

        // Generate DH parameters
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        DHParameterSpec parameterSpec = ((DHPublicKey) keyPair.getPublic()).getParams();

        // Alice generates her public-private key pair
        KeyPair aliceKeyPair = keyPairGenerator.generateKeyPair();
        DHPublicKey alicePublicKey = (DHPublicKey) aliceKeyPair.getPublic();
        DHPrivateKey alicePrivateKey = (DHPrivateKey) aliceKeyPair.getPrivate();

        // Bob generates her public-private key pair
        KeyPair bobKeyPair = keyPairGenerator.generateKeyPair();
        DHPublicKey bobPublicKey = (DHPublicKey) bobKeyPair.getPublic();
        DHPrivateKey bobPrivateKey = (DHPrivateKey) bobKeyPair.getPrivate();

        // Alice and Bob exchange public keys
        byte[] alicePublicKeyBytes = alicePublicKey.getEncoded();
        byte[] bobPublicKeyBytes = bobPublicKey.getEncoded();

        // Alice and Bob initialize KeyAgreement with their private keys and the other party's public key
        KeyAgreement aliceKeyAgreement = KeyAgreement.getInstance("DH");
        aliceKeyAgreement.init(alicePrivateKey);
        aliceKeyAgreement.doPhase(bobPublicKey, true);

        KeyAgreement bobKeyAgreement = KeyAgreement.getInstance("DH");
        bobKeyAgreement.init(bobPrivateKey);
        bobKeyAgreement.doPhase(alicePublicKey, true);

        // Alice and Bob generate their shared secret keys
        byte[] aliceSharedSecret = aliceKeyAgreement.generateSecret();
        byte[] bobSharedSecret = bobKeyAgreement.generateSecret();

        // Compare the shared secrets to ensure they match
        boolean sharedSecretsMatch = MessageDigest.isEqual(aliceSharedSecret, bobSharedSecret);
        System.out.println("Shared Secrets Match: " + sharedSecretsMatch);
    }

}
