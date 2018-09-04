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
