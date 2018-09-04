package com.github.edgar615.util.base;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by Edgar on 2016/8/23.
 *
 * @author Edgar  Date 2016/8/23
 */
public class EncryptUtilsTest {

    @Test
    public void testHmac() throws IOException {
        String str =
                "appKey=XXXXX&body={\"username\":\"foo\","
                        + "\"password\":\"bar\"}&nonce=123456&signMethod=HMACMD5&timestamp=1471958856&v=1.0";

        String str2 = "body={\"foo\":\"bar\"}";
        System.out.println(str2);
        System.out.println(EncryptUtils.encryptMD5("aa" + str + "aa"));
        System.out.println(EncryptUtils.encryptHmacMd5(str, "aa"));
        System.out.println(EncryptUtils.encryptHmacSha256(str, "aa"));
        System.out.println(EncryptUtils.encryptHmacSha512(str, "aa"));
    }
}
