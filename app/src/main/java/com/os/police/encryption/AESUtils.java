package com.os.police.encryption;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by LSH on 2017/6/5.
 * 主要功能:AES对称加密
 */

public class AESUtils {

    private AESUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 生产秘钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] initkey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(25);
        SecretKey secretKey = keyGen.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * AES加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrycpt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher=Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        byte[] cipherBytes=cipher.doFinal(data);
        return cipherBytes;
    }

    /**
     * AES解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainBytes = cipher.doFinal(data);
        return plainBytes;
    }

}
