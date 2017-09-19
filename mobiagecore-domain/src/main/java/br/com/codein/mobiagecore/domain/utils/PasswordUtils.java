package br.com.codein.mobiagecore.domain.utils;

import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class PasswordUtils {

    private static String salt;
    private static int iterations = 65536;
    private static int keySize = 128;
    private static byte[] ivBytes;
    private static SecretKey secretKey;
    private static String password = "22a48f692056962fa773b31e7d98466f9477e75fbec453cceab0585e95dc65f3";

    static{
        try {
            salt = getSalt();
            byte[] saltBytes = salt.getBytes();

            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, keySize);
            secretKey = skf.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretSpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretSpec);
            AlgorithmParameters params = cipher.getParameters();
            ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(char[] plaintext) throws Exception {
        SecretKeySpec secretSpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretSpec);

        byte[] encryptedTextBytes = cipher.doFinal(String.valueOf(plaintext).getBytes("UTF-8"));

        return DatatypeConverter.printBase64Binary(encryptedTextBytes);
    }

    public static String decrypt(char[] encryptedText) throws Exception {
        byte[] encryptedTextBytes = DatatypeConverter.parseBase64Binary(new String(encryptedText));
        SecretKeySpec secretSpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretSpec, new IvParameterSpec(ivBytes));

        byte[] decryptedTextBytes = null;

        try {
            decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        assert decryptedTextBytes != null;
        return new String(decryptedTextBytes);

    }

    public static String getSalt() throws Exception {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[20];
        sr.nextBytes(salt);
        return new String(salt);
    }
}