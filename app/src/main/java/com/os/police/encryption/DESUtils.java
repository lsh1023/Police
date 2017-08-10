package com.os.police.encryption;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by LSH on 2017/6/5.
 * 主要功能:DES对称加密
 */

public class DESUtils {

    private DESUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 生成秘钥
     *
     * @return
     * @throws Exception
     */
    private static byte[] initKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        SecretKey secretKey = keyGen.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * DES加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrycpt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherBytes = cipher.doFinal(data);
        return cipherBytes;
    }

    /**
     * DES解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] deceypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainBytes = cipher.doFinal(data);
        return plainBytes;
    }

}
