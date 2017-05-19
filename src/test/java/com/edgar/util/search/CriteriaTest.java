package com.edgar.util.search;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Edgar on 2017/5/16.
 *
 * @author Edgar  Date 2017/5/16
 */
public class CriteriaTest {

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
    Criteria criteria = Criteria.fromStr(data);
    Assert.assertTrue(criteria.criteria().contains(new Criterion("foo", Op.EQ, "bar")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("stars", Op.GT, "10")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("created_on", Op.GOE, "13435454")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("deleted_on", Op.LT, "13435454")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("applyNum", Op.LOE, "100")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("availableNum", Op.GOE, "100")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("addOn", Op.BETWEEN, "1", "10")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("username", Op.SW, "edgar")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("fullname", Op.EW, "edgar")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("login_name", Op.CN, "edgar")));


    Assert.assertTrue(criteria.criteria().contains(new Criterion("foo", Op.NE, "bar2")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("stars", Op.LOE, "100")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("created_on", Op.LT, "23435454")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("deleted_on", Op.GOE, "23435454")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("applyNum", Op.GT, "200")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("availableNum", Op.LT, "200")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("addOn", Op.LT, "20")));
    Assert.assertTrue(criteria.criteria().contains(new Criterion("addOn", Op.GT, "30")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidQuotes() {
    String data = "foo:bar stars:>:10 addOn:1..10";
    Criteria.fromStr(data);
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLike() {
    String data = "-username:edgar*";
    Criteria.fromStr(data);
    Assert.fail();
  }
}
