package com.github.edgar615.util.search;

import com.github.edgar615.util.base.MapUtils;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edgar on 2017/10/23.
 *
 * @author Edgar  Date 2017/10/23
 */
public class MapPredicateTest {

    @Test
    public void testEq() {
        Criterion criterion = new Criterion("foo", Op.EQ, "1");
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", "2"), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 1), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 1l), criterion));
    }

    @Test
    public void testNe() {
        Criterion criterion = new Criterion("foo", Op.NE, "1");
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "2"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 1), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 1l), criterion));
    }

    @Test
    public void testIsNull() {

        Criterion criterion = new Criterion("foo", Op.IS_NULL);
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Map<String, Object> map = new HashMap<>();
        map.put("foo", null);
        Assert.assertTrue(new MapPredicate().apply(map, criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", "2"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 1), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 1l), criterion));
    }

    @Test
    public void testIsNotNull() {

        Criterion criterion = new Criterion("foo", Op.IS_NOT_NULL);
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Map<String, Object> map = new HashMap<>();
        map.put("foo", null);
        Assert.assertFalse(new MapPredicate().apply(map, criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "2"), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 1), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 1l), criterion));
    }

    @Test
    public void testGt() {
        Criterion criterion = new Criterion("foo", Op.GT, 1);
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "2"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 2), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 1), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", '1'), criterion));
    }

    @Test
    public void testGe() {
        Criterion criterion = new Criterion("foo", Op.GE, 1);
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 2), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 0), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", "0"), criterion));
    }

    @Test
    public void testLt() {
        Criterion criterion = new Criterion("foo", Op.LT, 1);
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "0"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 0), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 1), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", '1'), criterion));
    }

    @Test
    public void testLe() {
        Criterion criterion = new Criterion("foo", Op.LE, 1);
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 1), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 2), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", '2'), criterion));
    }

    @Test
    public void testBw() {
        Criterion criterion = new Criterion("foo", Op.BETWEEN, 1, 5);
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 5), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 6), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", '6'), criterion));
    }

    @Test
    public void testBw2() {
        Criterion criterion = new Criterion("foo", Op.BETWEEN, 1, null);
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 5), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 6), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 0), criterion));
    }

    @Test
    public void testBw3() {
        Criterion criterion = new Criterion("foo", Op.BETWEEN, null, 5);
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 5), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 6), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 0), criterion));
    }


    @Test
    public void testIn() {
        Criterion criterion = new Criterion("foo", Op.IN, Lists.newArrayList(1, '2', "3"));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 2), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 3), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 4), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", "bar"), criterion));

        criterion = new Criterion("foo", Op.IN, 1);
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
    }

    @Test
    public void testNoIn() {
        Criterion criterion = new Criterion("foo", Op.NOT_IN, Lists.newArrayList(1, '2', "3"));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 2), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 3), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 4), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "bar"), criterion));

        criterion = new Criterion("foo", Op.IN, 1);
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", "2"), criterion));
    }

    @Test
    public void testSw() {
        Criterion criterion = new Criterion("foo", Op.SW, "bar");
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "bar"), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "bar124"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", "124bar"), criterion));

        criterion = new Criterion("foo", Op.SW, "1");
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 1343), criterion));
    }

    @Test
    public void testEw() {
        Criterion criterion = new Criterion("foo", Op.EW, "bar");
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "bar"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", "bar124"), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "124bar"), criterion));
        criterion = new Criterion("foo", Op.EW, "1");
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", 3431), criterion));
    }

    @Test
    public void testCn() {
        Criterion criterion = new Criterion("foo", Op.CN, "bar");
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", "1"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of(), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "bar"), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "bar124"), criterion));
        Assert.assertTrue(new MapPredicate().apply(ImmutableMap.of("foo", "124bar"), criterion));
        Assert.assertFalse(new MapPredicate().apply(ImmutableMap.of("foo", 3431), criterion));
    }

    @Test
    public void testCheckMap() {
        Criteria criteria = Criteria.create()
                .equalsTo("foo", "bar")
                .greaterThan("addOn", Instant.now().getEpochSecond() - 30)
                .notEqualsTo("state", 1);
        Map<String, Object> map = new HashMap<>();
        map.put("foo", "bar");
        Assert.assertFalse(MapUtils.check(map, criteria.criteria()));
        map.put("addOn", Instant.now().getEpochSecond());
        Assert.assertFalse(MapUtils.check(map, criteria.criteria()));
        map.put("state", 1);
        Assert.assertFalse(MapUtils.check(map, criteria.criteria()));
        map.put("state", 2);
        Assert.assertTrue(MapUtils.check(map, criteria.criteria()));
    }
}
