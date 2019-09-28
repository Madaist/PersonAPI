package com.example.demo.encryption;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Encryption {

    public static byte[] encrypt(String toEncrypt) throws Exception {

        String key = "7FC71AAE37641CB3";
        SecureRandom secureRandom = new SecureRandom(key.getBytes());
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        byte[] iv = {(byte) 0xFA, (byte) 0x63, (byte) 0x10, (byte) 0x12, (byte) 0xBA, (byte) 0xC1, (byte) 0x06, (byte) 0x9A};
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));

        byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());

        return encrypted;
    }

    public static String decrypt(byte[] toDecrypt) throws Exception {

        String key = "7FC71AAE37641CB3";
        SecureRandom secureRandom = new SecureRandom(key.getBytes());
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        byte[] iv = {(byte) 0xFA, (byte) 0x63, (byte) 0x10, (byte) 0x12, (byte) 0xBA, (byte) 0xC1, (byte) 0x06, (byte) 0x9A};
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

        byte[] decrypted = cipher.doFinal(toDecrypt);

        return new String(decrypted);
    }




    /*public static byte[] encrypt(String toEncrypt, String key) throws Exception {

        SecureRandom secureRandom = new SecureRandom(key.getBytes());
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
        keyGenerator.init(secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("DESede");

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());

        return encrypted;
    }

    public static String decrypt(byte[] toDecrypt, String key) throws Exception {

        SecureRandom secureRandom = new SecureRandom(key.getBytes());
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
        keyGenerator.init(secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(toDecrypt);

        return new String(decrypted);
    }*/

}