package com.edgar.util.crypto;

import com.edgar.util.exception.DefaultErrorCode;
import com.edgar.util.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Mac;

/**
 * Created by Edgar on 2017/8/18.
 * <p>
 * 从vert.x jwt中复制
 *
 * @author Edgar  Date 2017/8/18
 */
public class KeyStoreSign {

  private static final Logger LOGGER = LoggerFactory.getLogger(KeyStoreSign.class);

  private final Map<String, Crypto> cryptoMap;

  public KeyStoreSign(InputStream inputStream, String type, String keyStorePassword) {
    try {
      KeyStore ks = KeyStore.getInstance(type);
      ks.load(inputStream, keyStorePassword.toCharArray());

      Map<String, Crypto> tmp = new HashMap<>();
      // load MACs
      for (String alg : Arrays.asList("HS256", "HS384", "HS512")) {
        try {
          Mac mac = getMac(ks, keyStorePassword.toCharArray(), alg);
          if (mac != null) {
            tmp.put(alg, new CryptoMac(mac));
          } else {
            LOGGER.info(alg + " not available");
          }
        } catch (RuntimeException e) {
          LOGGER.error(alg + " not supported", e);
          throw SystemException.create(DefaultErrorCode.INVALID_ARGS)
                  .set("details", alg + " not supported");
        }
      }

      // load SIGNATUREs
      final Map<String, String> alias = new HashMap<String, String>() {{
        put("RS256", "SHA256withRSA");
        put("RS384", "SHA384withRSA");
        put("RS512", "SHA512withRSA");
        put("ES256", "SHA256withECDSA");
        put("ES384", "SHA384withECDSA");
        put("ES512", "SHA512withECDSA");
      }};

      for (String alg : Arrays.asList("RS256", "RS384", "RS512", "ES256", "ES384", "ES512")) {
        try {
          X509Certificate certificate = getCertificate(ks, alg);
          PrivateKey privateKey = getPrivateKey(ks, "123456".toCharArray(), alg);
          if (certificate != null && privateKey != null) {
            tmp.put(alg, new CryptoSignature(alias.get(alg), certificate, privateKey));
          } else {
            LOGGER.info(alg + " not available");
          }
        } catch (RuntimeException e) {
          LOGGER.error(alg + " not supported", e);
         throw e;
        }
      }
      cryptoMap = Collections.unmodifiableMap(tmp);
    } catch (Exception e) {
      throw SystemException.wrap(DefaultErrorCode.UNKOWN, e);
    }
  }

  public String sign(String algorithm, String input) {
    Crypto crypto = cryptoMap.get(algorithm);

    if (crypto == null) {
      LOGGER.error("Algorithm:{} not supported", algorithm);
      throw SystemException.create(DefaultErrorCode.INVALID_ARGS)
              .set("details", "Algorithm not supported");
    }
    return Base64.getUrlEncoder().withoutPadding()
            .encodeToString(crypto.sign(input.getBytes()));
//    return new String(crypto.sign(input.getBytes(Charsets.UTF_8)));
  }

  /**
   * Creates a new Message Authentication Code
   *
   * @param keyStore a valid JKS
   * @param alias    algorithm to use e.g.: HmacSHA256
   * @return Mac implementation
   */
  private Mac getMac(final KeyStore keyStore, final char[] keyStorePassword, final String alias) {
    try {
      final Key secretKey = keyStore.getKey(alias, keyStorePassword);

      // key store does not have the requested algorithm
      if (secretKey == null) {
        return null;
      }

      Mac mac = Mac.getInstance(secretKey.getAlgorithm());
      mac.init(secretKey);

      return mac;
    } catch (NoSuchAlgorithmException | InvalidKeyException | UnrecoverableKeyException |
            KeyStoreException e) {
      throw new RuntimeException(e);
    }
  }

  private X509Certificate getCertificate(final KeyStore keyStore, final String alias) {
    try {
      return (X509Certificate) keyStore.getCertificate(alias);

    } catch (KeyStoreException e) {
      throw new RuntimeException(e);
    }
  }

  private PrivateKey getPrivateKey(final KeyStore keyStore, final char[] keyStorePassword,
                                   final String alias) {
    try {
      return (PrivateKey) keyStore.getKey(alias, keyStorePassword);

    } catch (NoSuchAlgorithmException | UnrecoverableKeyException | KeyStoreException e) {
      throw new RuntimeException(e);
    }
  }
}
