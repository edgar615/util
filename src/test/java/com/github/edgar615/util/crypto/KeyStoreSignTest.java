package com.github.edgar615.util.crypto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Edgar on 2017/8/18.
 *
 * @author Edgar  Date 2017/8/18
 */
public class KeyStoreSignTest {

  public static void main(String[] args) throws FileNotFoundException {
    FileInputStream inputStream = new FileInputStream("h:/keystore.jceks");
    KeyStoreSign sign = new KeyStoreSign(inputStream, "jceks", "secret");
    System.out.println(sign.sign("ES256", "hello"));
  }
}
