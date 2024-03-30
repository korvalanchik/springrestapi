package com.example.springrestapi;

import java.security.SecureRandom;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class KeyGenerator {

    public static void main(String[] args) {
        int keyLength = 512; // Key length in bits
        SecretKey secretKey = generateKey(keyLength);
        byte[] encodedKey = secretKey.getEncoded();
        System.out.println("Generated Key (Base64): " + java.util.Base64.getEncoder().encodeToString(encodedKey));
    }

    public static SecretKey generateKey(int keyLength) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[keyLength / 8];
        secureRandom.nextBytes(keyBytes);
        return new SecretKeySpec(keyBytes, "HmacSHA512");
    }
}