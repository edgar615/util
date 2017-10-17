package com.github.edgar615.util.search;

import com.google.common.collect.Lists;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Edgar on 2017/5/16.
 *
 * @author Edgar  Date 2017/5/16
 */
public class ExampleTest {

  @Test
  public void testFromString() {
    String data = "foo:bar"
                  + " stars:>10"
                  + " created_on:>=13435454"
                  + " deleted_on:<13435454"
                  + " applyNum:*..100"
                  + " availableNum:100..*"
                  + " addOn:1..10"
                  + " username:edgar*"
                  + " fullname:*edgar"
                  + " login_name:*edgar*"
                  + " -foo:bar2"
                  + " -stars:>100"
                  + " -created_on:>=23435454"
                  + " -deleted_on:<23435454"
                  + " -applyNum:*..200"
                  + " -availableNum:200..*"
                  + " -addOn:20..30";
    Example example = Example.create().addQuery(data);
    System.out.println(example.criteria());
    Assert.assertTrue(example.criteria().contains(new Criterion("foo", Op.EQ, "bar")));
    Assert.assertTrue(example.criteria().contains(new Criterion("stars", Op.GT, "10")));
    Assert.assertTrue(example.criteria().contains(new Criterion("created_on", Op.GE, "13435454")));
    Assert.assertTrue(example.criteria().contains(new Criterion("deleted_on", Op.LT, "13435454")));
    Assert.assertTrue(example.criteria().contains(new Criterion("applyNum", Op.LE, "100")));
    Assert.assertTrue(example.criteria().contains(new Criterion("availableNum", Op.GE, "100")));
    Assert.assertTrue(example.criteria().contains(new Criterion("addOn", Op.BETWEEN, "1", "10")));
    Assert.assertTrue(example.criteria().contains(new Criterion("username", Op.SW, "edgar")));
    Assert.assertTrue(example.criteria().contains(new Criterion("fullname", Op.EW, "edgar")));
    Assert.assertTrue(example.criteria().contains(new Criterion("login_name", Op.CN, "edgar")));


    Assert.assertTrue(example.criteria().contains(new Criterion("foo", Op.NE, "bar2")));
    Assert.assertTrue(example.criteria().contains(new Criterion("stars", Op.LE, "100")));
    Assert.assertTrue(example.criteria().contains(new Criterion("created_on", Op.LT, "23435454")));
    Assert.assertTrue(example.criteria().contains(new Criterion("deleted_on", Op.GE, "23435454")));
    Assert.assertTrue(example.criteria().contains(new Criterion("applyNum", Op.GT, "200")));
    Assert.assertTrue(example.criteria().contains(new Criterion("availableNum", Op.LT, "200")));
    Assert.assertTrue(example.criteria().contains(new Criterion("addOn", Op.LT, "20")));
    Assert.assertTrue(example.criteria().contains(new Criterion("addOn", Op.GT, "30")));
  }

  @Test
  public void testOrderBy() {
    String orderBy = "foo,-bar";
    Example example = Example.create().orderBy(orderBy);
    System.out.println(example);
    Assert.assertTrue(example.orderBy().contains("foo"));
    Assert.assertTrue(example.orderBy().contains("-bar"));
    Assert.assertFalse(example.orderBy().contains("bar"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidQuotes() {
    String data = "foo:bar stars:>:10 addOn:1..10";
    Example example = Example.create().addQuery(data);
    Assert.fail();
  }

  @Test
  public void testTrimBlank() {
    String data = " foo:  bar fda   -name: edgar stars:    > 10   addOn :   1  ..  2  login_name  "
                  + ":   *   edgar   * ";
    Example example = Example.create().addQuery(data);
    System.out.println(example);
    Assert.assertTrue(example.criteria().contains(new Criterion("foo", Op.EQ, "bar fda")));
    Assert.assertTrue(example.criteria().contains(new Criterion("name", Op.NE, "edgar")));
    Assert.assertTrue(example.criteria().contains(new Criterion("stars", Op.GT, "10")));
    Assert.assertTrue(example.criteria().contains(new Criterion("addOn", Op.BETWEEN, "1", "2")));
    Assert.assertTrue(example.criteria().contains(new Criterion("login_name", Op.CN, "   edgar   ")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLike() {
    String data = "-username:edgar*";
    Example example = Example.create().addQuery(data);
    Assert.fail();
  }

  @Test
  public void testRemove() {
    String data = "foo:bar"
            + " stars:>10"
            + " created_on:>=13435454"
            + " deleted_on:<13435454"
            + " applyNum:*..100"
            + " availableNum:100..*"
            + " addOn:1..10"
            + " username:edgar*"
            + " fullname:*edgar"
            + " login_name:*edgar*"
            + " -foo:bar2"
            + " -stars:>100"
            + " -created_on:>=23435454"
            + " -deleted_on:<23435454"
            + " -applyNum:*..200"
            + " -availableNum:200..*"
            + " -addOn:20..30";
    Example oldExample = Example.create().addQuery(data).orderBy("foo,-bar").addField("addOn").addField("created_on");
    Example example = oldExample.removeUndefinedField(Lists.newArrayList("foo", "addOn"));
    System.out.println(example);

    Assert.assertTrue(example.criteria().contains(new Criterion("foo", Op.EQ, "bar")));
    Assert.assertFalse(example.criteria().contains(new Criterion("stars", Op.GT, "10")));
    Assert.assertFalse(example.criteria().contains(new Criterion("created_on", Op.GE, "13435454")));
    Assert.assertFalse(example.criteria().contains(new Criterion("deleted_on", Op.LT, "13435454")));
    Assert.assertFalse(example.criteria().contains(new Criterion("applyNum", Op.LE, "100")));
    Assert.assertFalse(example.criteria().contains(new Criterion("availableNum", Op.GE, "100")));
    Assert.assertTrue(example.criteria().contains(new Criterion("addOn", Op.BETWEEN, "1", "10")));
    Assert.assertFalse(example.criteria().contains(new Criterion("username", Op.SW, "edgar")));
    Assert.assertFalse(example.criteria().contains(new Criterion("fullname", Op.EW, "edgar")));
    Assert.assertFalse(example.criteria().contains(new Criterion("login_name", Op.CN, "edgar")));


    Assert.assertTrue(example.criteria().contains(new Criterion("foo", Op.NE, "bar2")));
    Assert.assertFalse(example.criteria().contains(new Criterion("stars", Op.LE, "100")));
    Assert.assertFalse(example.criteria().contains(new Criterion("created_on", Op.LT, "23435454")));
    Assert.assertFalse(example.criteria().contains(new Criterion("deleted_on", Op.GE, "23435454")));
    Assert.assertFalse(example.criteria().contains(new Criterion("applyNum", Op.GT, "200")));
    Assert.assertFalse(example.criteria().contains(new Criterion("availableNum", Op.LT, "200")));
    Assert.assertTrue(example.criteria().contains(new Criterion("addOn", Op.LT, "20")));
    Assert.assertTrue(example.criteria().contains(new Criterion("addOn", Op.GT, "30")));

    Assert.assertTrue(example.orderBy().contains("foo"));
    Assert.assertFalse(example.orderBy().contains("bar"));

    Assert.assertTrue(example.fields().contains("addOn"));
    Assert.assertFalse(example.fields().contains("created_on"));
  }

}
