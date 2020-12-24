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

package com.github.edgar615.util.net;

import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;

public class IpUtilsTest {

  @Test
  public void testIpType() {
    Assert.assertTrue(IPUtils.isIPv4Address("192.168.1.1"));
    Assert.assertFalse(IPUtils.isIPv6Address("192.168.1.1"));

    Assert.assertFalse(IPUtils.isIPv4Address("0:0:0:0:0:0:0:1"));
    Assert.assertTrue(IPUtils.isIPv6Address("0:0:0:0:0:0:0:1"));
    System.out.println(IPUtils.ipv6ToNumber("fe80:1295:8030:49ec:1fc6:57fa:0000:0000"));
    System.out.println(IPUtils.numberToIPv6(new BigInteger("338288901855302628794420965398450012160")));
    System.out.println(IPUtils.ipv6ToNumber("A00:a00:100:f261::F15"));
    System.out.println(IPUtils.numberToIPv6(new BigInteger("13292482782255824857586684748009312021")));
  }
}
