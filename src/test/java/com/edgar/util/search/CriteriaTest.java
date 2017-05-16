package com.edgar.util.search;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class CriteriaTest {

  @Test
  public void testEquals() {
    Criteria criteria = new Criteria("a", Op.IS_NOT_NULL);
    Criteria criteria2 = new Criteria("a", Op.IS_NOT_NULL);
    Set<Criteria> criterias = new HashSet<Criteria>();
    criterias.add(criteria);
    criterias.add(criteria2);
    Assert.assertEquals(criteria, criteria2);
    Assert.assertTrue(criterias.size() == 1);
  }

  @Test
  public void testContain() {
    Set<Criteria> criterias = new HashSet<>();
    criterias.add(new Criteria("a", Op.EQ, "b"));
    criterias.add(new Criteria("a", Op.EQ, "b"));
    Assert.assertEquals(1, criterias.size());
    Assert.assertTrue(criterias.contains(new Criteria("a", Op.EQ,
                                                      "b")));
  }
}
