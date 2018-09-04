package com.github.edgar615.util.search;

import com.github.edgar615.util.base.StringUtils;
import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.assertj.core.util.Strings;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Edgar on 2017/5/16.
 *
 * @author Edgar  Date 2017/5/16
 */
public class ExampleTest {

    @Test
    public void testSimple() {
        String data = "foo:bar";
        Example example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("foo", Op.EQ, "bar")));

        data = "stars:>10";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("stars", Op.GT, "10")));
        data = " created_on:>=13435454";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("created_on", Op.GE, "13435454")));
        data = " deleted_on:<13435454";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("deleted_on", Op.LT, "13435454")));
        data = " applyNum:*..100";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("applyNum", Op.LE, "100")));

        data = " availableNum:100..*";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("availableNum", Op.GE, "100")));
        data = " addOn:1..10";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("addOn", Op.BETWEEN, "1", "10")));
        data = " username:edgar*";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("username", Op.SW, "edgar")));
        data = " fullname:*edgar";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("fullname", Op.EW, "edgar")));
        data = " login_name:*edgar*";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("login_name", Op.CN, "edgar")));

        data = " -foo:bar2";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("foo", Op.NE, "bar2")));
        data = " -stars:>100";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("stars", Op.LE, "100")));
        data = " -created_on:>=23435454";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("created_on", Op.LT, "23435454")));
        data = " -deleted_on:<23435454";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("deleted_on", Op.GE, "23435454")));
        data = " -applyNum:*..200";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("applyNum", Op.GT, "200")));
        data = " -availableNum:200..*";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("availableNum", Op.LT, "200")));
        data = " -addOn:20..30";
        example = Example.create().addQuery(data);
        Assert.assertTrue(example.criteria().contains(new Criterion("addOn", Op.LT, "20")));
        Assert.assertTrue(example.criteria().contains(new Criterion("addOn", Op.GT, "30")));

        data = "foo :bar -foo: bar2";
        example = Example.create().addQuery(data);
        System.out.println(example);
        Assert.assertTrue(example.criteria().contains(new Criterion("foo", Op.EQ, "bar")));
        Assert.assertTrue(example.criteria().contains(new Criterion("foo", Op.NE, "bar2")));
    }


    @Test
    public void testFromString() {
        Example example = Example.create().addQuery(null);
        Assert.assertTrue(example.criteria().isEmpty());
        example = Example.create().addQuery("        ");
        Assert.assertTrue(example.criteria().isEmpty());
        example = Example.create().addQuery("    aaa    ");
        Assert.assertTrue(example.criteria().isEmpty());
        example = Example.create().addQuery("    :foo    ");
        Assert.assertTrue(example.criteria().isEmpty());
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
        example = Example.create().addQuery(data);
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

//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidQuotes() {
//    String data = "foo:bar stars:>:10 addOn:1..10";
//    Example example = Example.create().addQuery(data);
//    Assert.fail();
//  }

//  @Test
//  public void testTrimBlank() {
//    String data = " foo:  bar fda   -name: edgar stars:    > 10   addOn :   1  ..  2  login_name  "
//                  + ":   *   edgar   * ";
//    Example example = Example.create().addQuery(data);
//    System.out.println(example);
//    Assert.assertTrue(example.criteria().contains(new Criterion("foo", Op.EQ, "bar fda")));
//    Assert.assertTrue(example.criteria().contains(new Criterion("name", Op.NE, "edgar")));
//    Assert.assertTrue(example.criteria().contains(new Criterion("stars", Op.GT, "10")));
//    Assert.assertTrue(example.criteria().contains(new Criterion("addOn", Op.BETWEEN, "1", "2")));
//    Assert.assertTrue(
//            example.criteria().contains(new Criterion("login_name", Op.CN, "   edgar   ")));
//  }

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
        Example oldExample = Example.create().addQuery(data).orderBy("foo,-bar").addField("addOn")
                .addField("created_on");
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

    @Test
    public void testInvalid() {
//    String data = "foo:barbar: bar bar bar bar"
//                  + " stars:>10: username: hah : hah";
//    String data = "foo:bar"
//                  + " -availableNum:200..*"
//                  + " -addOn:20..30";
//    String data = "foo:bar";
//    String data = " foo:  bar fda   -name: edgar stars:    > 10   addOn :   1  ..  2  login_name  "
//                  + ":   *   edgar   * ";
        String data = "foo :bar foo: bar2";
        //用空格分隔，从第一个开始遍历，如果遇到已空格开头，中间包含:的，说明是一个查询参数的开头
        List<String> spliterList = StringUtils.splitRemainDelimiter(data.trim(), " ");
        System.out.println(spliterList);
        List<StringBuilder> queryList = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        for (int i = 0; i < spliterList.size(); i++) {
            String s = spliterList.get(i);
            if (i == 0) {
                query.append(s);
            } else {
                String prev = spliterList.get(i - 1);
                if (s.indexOf(":") > 0 && CharMatcher.whitespace().matchesAllOf(prev)) {
                    queryList.add(query);
                    query = new StringBuilder();
                    query.append(s);
                } else {
                    query.append(s);
                }
            }
        }
        if (!Strings.isNullOrEmpty(query.toString())) {
            queryList.add(query);
        }
        System.out.println(queryList);
        List<Map.Entry<String, String>> criteria = new ArrayList<>();
        for (StringBuilder s : queryList) {
            //第一个:就是分隔符
            String s1 = s.toString();
            int index = s1.indexOf(":");
            String field = s.substring(0, index).trim();
            String value = s.substring(index + 1).trim();
            criteria.add(Maps.immutableEntry(field, value));
        }
        System.out.println(criteria);
//    Example example = Example.create().addQuery(data);
//    System.out.println(example);
    }


}
