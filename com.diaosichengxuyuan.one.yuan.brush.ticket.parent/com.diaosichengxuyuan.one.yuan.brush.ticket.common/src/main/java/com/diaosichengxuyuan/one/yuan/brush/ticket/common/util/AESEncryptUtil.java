package com.diaosichengxuyuan.one.yuan.brush.ticket.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * 加解密工具类
 *
 * @author liuhaipeng
 * @date 2019/6/27
 */
@Slf4j
public class AESEncryptUtil {

    private static final String ALGORITHM = "AES";

    private static final String CIPHER_MODE = "AES/ECB/PKCS5Padding";

    private static final String ENCODE = "UTF-8";

    private static final int SECRET_KEY_SIZE = 16;

    /**
     * 加密
     *
     * @param password 明文
     * @param key      密钥
     * @return 密码
     */
    public static String encrypt(String password, String key) {
        if(StringUtils.isEmpty(password)) {
            throw new RuntimeException("明文为空");
        }

        if(StringUtils.isEmpty(key)) {
            throw new RuntimeException("密钥为空");
        }

        try {
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            byte[] newKey = getSecretKey(key);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(newKey, ALGORITHM));
            byte[] encryptedPassword = cipher.doFinal(password.getBytes(ENCODE));
            return new Base64().encodeToString(encryptedPassword);
        } catch(Exception e) {
            log.error("AES加密失败", e);
            throw new RuntimeException("AES加密失败");
        }
    }

    /**
     * 解密
     *
     * @param password 密文
     * @param key      密钥
     * @return 明文
     */
    public static String decrypt(String password, String key) {
        if(StringUtils.isEmpty(password)) {
            throw new RuntimeException("密文为空");
        }

        if(StringUtils.isEmpty(key)) {
            throw new RuntimeException("密钥为空");
        }

        try {
            byte[] decodeBytes = Base64.decodeBase64(password);
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            byte[] newKey = getSecretKey(key);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(newKey, ALGORITHM));
            byte[] decryptPassword = cipher.doFinal(decodeBytes);
            return new String(decryptPassword, ENCODE);
        } catch(Exception e) {
            log.error("AES解密失败", e);
            throw new RuntimeException("AES解密失败");
        }
    }

    public static byte[] getSecretKey(String key) throws UnsupportedEncodingException {
        final byte paddingChar = 0;
        byte[] newKey = new byte[SECRET_KEY_SIZE];
        byte[] originalKey = key.getBytes(ENCODE);
        for(int i = 0; i < newKey.length; i++) {
            if(i < originalKey.length) {
                newKey[i] = originalKey[i];
            } else {
                newKey[i] = paddingChar;
            }
        }

        return newKey;
    }

    public static void main(String[] args) {
        String str = "123";
        System.out.println("str:" + str);

        String encryptStr = encrypt(str, "Aa12345");
        System.out.println("encrypt:" + encryptStr);

        String decryptStr = decrypt(encryptStr, "Aa12345");

        System.out.println("decryptStr:" + decryptStr);
    }

}
