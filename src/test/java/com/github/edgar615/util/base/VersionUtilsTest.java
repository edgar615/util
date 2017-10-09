package com.github.edgar615.util.base;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Edgar on 2016/12/5.
 *
 * @author Edgar  Date 2016/12/5
 */
public class VersionUtilsTest {

  @Test
  public void testVersion() {
    Assert.assertTrue(VersionUtils.compareVersion("V1.0.1", "V1.1") < 0);
    Assert.assertTrue(VersionUtils.compareVersion("V1.0.1", "1.1") < 0);
    Assert.assertTrue(VersionUtils.compareVersion("1.0.1", "1.1") < 0);
    Assert.assertTrue(VersionUtils.compareVersion("1.0.1", "V1.1") < 0);

    Assert.assertTrue(VersionUtils.compareVersion("V1.0.1", "V1.0.0") > 0);
    Assert.assertTrue(VersionUtils.compareVersion("V1.0.1", "1.0.0") > 0);
    Assert.assertTrue(VersionUtils.compareVersion("1.0.1", "1.0.0") > 0);
    Assert.assertTrue(VersionUtils.compareVersion("1.0.1", "1.0.0") > 0);

    Assert.assertTrue(VersionUtils.compareVersion("V1.0", "V1.0.0") == 0);
    Assert.assertTrue(VersionUtils.compareVersion("V1.0", "1.0.0") == 0);
    Assert.assertTrue(VersionUtils.compareVersion("1.0", "1.0.0") == 0);
    Assert.assertTrue(VersionUtils.compareVersion("1.0", "1.0.0") == 0);

    Assert.assertTrue(VersionUtils.compareVersion("V1.0", "V0.9.0") > 0);
    Assert.assertTrue(VersionUtils.compareVersion("V1.0", "0.9.0") > 0);
    Assert.assertTrue(VersionUtils.compareVersion("1.0", "0.9.0") > 0);
    Assert.assertTrue(VersionUtils.compareVersion("1.0", "0.9.0") > 0);

    Assert.assertTrue(VersionUtils.compareVersion("0.6", "0.6") >= 0);
  }
}
