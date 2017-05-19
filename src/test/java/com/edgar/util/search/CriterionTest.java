package com.edgar.util.search;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class CriterionTest {

  @Test
  public void testEquals() {
    Criterion criteria = new Criterion("a", Op.IS_NOT_NULL);
    Criterion criteria2 = new Criterion("a", Op.IS_NOT_NULL);
    Set<Criterion> criterias = new HashSet<Criterion>();
    criterias.add(criteria);
    criterias.add(criteria2);
    Assert.assertEquals(criteria, criteria2);
    Assert.assertTrue(criterias.size() == 1);
  }

  @Test
  public void testContain() {
    Set<Criterion> criterias = new HashSet<>();
    criterias.add(new Criterion("a", Op.EQ, "b"));
    criterias.add(new Criterion("a", Op.EQ, "b"));
    Assert.assertEquals(1, criterias.size());
    Assert.assertTrue(criterias.contains(new Criterion("a", Op.EQ,
                                                       "b")));
  }
}
