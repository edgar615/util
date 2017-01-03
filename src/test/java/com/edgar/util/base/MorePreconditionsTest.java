package com.edgar.util.base;

import com.google.common.collect.ImmutableList;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by edgar on 16-3-31.
 */
public class MorePreconditionsTest {


  @Test(expected = IllegalArgumentException.class)
  public void testNullOrEmpty() {
    MorePreconditions.checkNotEmpty(ImmutableList.of(1));
    MorePreconditions.checkNotEmpty(ImmutableList.of());
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIntRange() {
    MorePreconditions.checkArgumentRange(3, 1, 3);
    MorePreconditions.checkArgumentRange(3, 4, 40, "%s必须在4和40之间");
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDoubleRange() {
    MorePreconditions.checkArgumentRange(3.0, 1, 3);
    MorePreconditions.checkArgumentRange(3.0, 4, 40, "%s必须在4和40之间");
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAll() {
    MorePreconditions.checkAllArguments("all", true, true);
    MorePreconditions.checkAllArguments("all", true, false);
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAny() {
    MorePreconditions.checkAnyArguments("all", true, false);
    MorePreconditions.checkAnyArguments("all", false, false);
    Assert.fail();
  }
}
