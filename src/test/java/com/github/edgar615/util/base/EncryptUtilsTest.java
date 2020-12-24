/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.edgar615.util.base;

import java.io.IOException;
import org.junit.Test;

/**
 * Created by Edgar on 2016/8/23.
 *
 * @author Edgar  Date 2016/8/23
 */
public class EncryptUtilsTest {

  @Test
  public void testHmac() throws Exception {
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
