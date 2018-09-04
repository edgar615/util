package com.github.edgar615.util.base;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密的工具类.
 *
 * @author Edgar  Date 2016/8/23
 */
public class EncryptUtils {

    public static final String CHARSET_UTF8 = "UTF-8";

    public static final String HMACSHA1 = "HMACSHA1";

    public static final String HMACSHA256 = "HMACSHA256";

    public static final String HMACSHA512 = "HMACSHA512";

    public static final String HMACMD5 = "HMACMD5";

    public static final String MD5 = "MD5";

    private EncryptUtils() {
        throw new AssertionError("Not instantiable: " + EncryptUtils.class);
    }

    /**
     * HMACSHA1加密
     *
     * @param data   源字符串
     * @param secret 密钥
     * @return 加密后的字符串
     * @throws IOException
     */
    public static String encryptHmacSha1(String data, String secret) throws IOException {
        return byte2hex(encryptHMAC(data, secret, HMACSHA1));
    }

    /**
     * HMACSHA256加密
     *
     * @param data   源字符串
     * @param secret 密钥
     * @return 加密后的字符串
     * @throws IOException
     */
    public static String encryptHmacSha256(String data, String secret) throws IOException {
        return byte2hex(encryptHMAC(data, secret, HMACSHA256));
    }

    /**
     * HMACSHA512加密
     *
     * @param data   源字符串
     * @param secret 密钥
     * @return 加密后的字符串
     * @throws IOException
     */
    public static String encryptHmacSha512(String data, String secret) throws IOException {
        return byte2hex(encryptHMAC(data, secret, HMACSHA512));
    }

    /**
     * HMACMD5加密
     *
     * @param data   源字符串
     * @param secret 密钥
     * @return 加密后的字符串
     * @throws IOException
     */
    public static String encryptHmacMd5(String data, String secret) throws IOException {
        return byte2hex(encryptHMAC(data, secret, HMACMD5));
    }

    /**
     * HMA加密，支持下列算法
     * HMACSHA256 HMACSHA512 HMACMD5
     *
     * @param data
     * @param secret
     * @param algorithm
     * @return
     * @throws IOException
     */
    public static byte[] encryptHMAC(String data, String secret,
                                     String algorithm) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(CHARSET_UTF8), algorithm);
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(CHARSET_UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    /**
     * MD5加密
     *
     * @param data 源字符串
     * @return
     * @throws IOException
     */
    public static String encryptMD5(String data) throws IOException {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        md5.update(data.getBytes(CHARSET_UTF8));
        return byte2hex(md5.digest());
    }

    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public static byte[] hex2byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}