package br.com.codein.mobiagecore.domain.utils;

import javax.crypto.*;
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
    private static String plainText2 = "1234";

    private static Cipher cipherEncrypt;
    private static Cipher cipherDecrypt;
    private static SecretKeySpec secretSpec;

    static {
        try {
            salt = "29wLO5fUE6BFgehVmwEe";
            byte[] saltBytes = salt.getBytes();
            ivBytes = "FOFMORYRSMOEQODT".getBytes("UTF-8");
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(plainText2.toCharArray(), saltBytes, iterations, keySize);
            secretKey = skf.generateSecret(spec);
            secretSpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

            cipherEncrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipherEncrypt.init(Cipher.ENCRYPT_MODE, secretSpec, new IvParameterSpec(ivBytes));

        } catch (Exception ex) {

        }


    }

    public static String encrypt(char[] plaintext) throws Exception {
        byte[] encryptedTextBytes = cipherEncrypt.doFinal(String.valueOf(plaintext).getBytes("UTF-8"));
        return DatatypeConverter.printBase64Binary(encryptedTextBytes);
    }

    public static String decrypt(char[] encryptedText) throws Exception {
        cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretSpec, new IvParameterSpec(ivBytes));
        byte[] encryptedTextBytes = DatatypeConverter.parseBase64Binary(new String(encryptedText));

        byte[] decryptedTextBytes = null;

        try {
            decryptedTextBytes = cipherDecrypt.doFinal(encryptedTextBytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        assert decryptedTextBytes != null;
        return new String(decryptedTextBytes);

    }
}