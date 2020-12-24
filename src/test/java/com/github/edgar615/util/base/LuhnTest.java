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

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Edgar on 2018/8/17.
 *
 * @author Edgar  Date 2018/8/17
 */
public class LuhnTest {

  @Test
  public void testGenerateCheckNum() {
    int checkNum = Luhn.generateCheckNum("7992739871");
    Assert.assertEquals(3, checkNum);
    Assert.assertEquals("79927398713", Luhn.generate("7992739871"));
    Assert.assertTrue(Luhn.check("79927398713"));
    Assert.assertFalse(Luhn.check("79927398714"));
  }
}
