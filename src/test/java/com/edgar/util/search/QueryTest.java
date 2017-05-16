package com.edgar.util.search;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Edgar on 2017/5/16.
 *
 * @author Edgar  Date 2017/5/16
 */
public class QueryTest {

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
    Query query = Query.fromStr(data);
    Assert.assertTrue(query.criterias().contains(new Criteria("foo", Op.EQ, "bar")));
    Assert.assertTrue(query.criterias().contains(new Criteria("stars", Op.GT, "10")));
    Assert.assertTrue(query.criterias().contains(new Criteria("created_on", Op.GOE, "13435454")));
    Assert.assertTrue(query.criterias().contains(new Criteria("deleted_on", Op.LT, "13435454")));
    Assert.assertTrue(query.criterias().contains(new Criteria("applyNum", Op.LOE, "100")));
    Assert.assertTrue(query.criterias().contains(new Criteria("availableNum", Op.GOE, "100")));
    Assert.assertTrue(query.criterias().contains(new Criteria("addOn", Op.BETWEEN, "1", "10")));
    Assert.assertTrue(query.criterias().contains(new Criteria("username", Op.SW, "edgar")));
    Assert.assertTrue(query.criterias().contains(new Criteria("fullname", Op.EW, "edgar")));
    Assert.assertTrue(query.criterias().contains(new Criteria("login_name", Op.CN, "edgar")));


    Assert.assertTrue(query.criterias().contains(new Criteria("foo", Op.NE, "bar2")));
    Assert.assertTrue(query.criterias().contains(new Criteria("stars", Op.LOE, "100")));
    Assert.assertTrue(query.criterias().contains(new Criteria("created_on", Op.LT, "23435454")));
    Assert.assertTrue(query.criterias().contains(new Criteria("deleted_on", Op.GOE, "23435454")));
    Assert.assertTrue(query.criterias().contains(new Criteria("applyNum", Op.GT, "200")));
    Assert.assertTrue(query.criterias().contains(new Criteria("availableNum", Op.LT, "200")));
    Assert.assertTrue(query.criterias().contains(new Criteria("addOn", Op.LT, "20")));
    Assert.assertTrue(query.criterias().contains(new Criteria("addOn", Op.GT, "30")));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidQuotes() {
    String data = "foo:bar stars:>:10 addOn:1..10";
    Query.fromStr(data);
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLike() {
    String data = "-username:edgar*";
    Query.fromStr(data);
    Assert.fail();
  }
}
