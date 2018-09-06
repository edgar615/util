package com.github.edgar615.util.validation;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * RuleManager的测试类.
 *
 * @author Edgar
 * @create 2018-09-06 10:24
 **/
public class RuleManagerTest {

  @Test
  public void testToParsableString() {
    List<Rule> rules = new ArrayList<>();
    rules.add(Rule.alphaNumber());
    rules.add(Rule.alpha());
    rules.add(Rule.alphaSpace());
    rules.add(Rule.alphaUnderscore());
    rules.add(Rule.bool());
    rules.add(Rule.byteRule());
    rules.add(Rule.decimal(2));
    rules.add(Rule.digits(3));
    rules.add(Rule.doubleRule());
    rules.add(Rule.email());
    rules.add(Rule.equals("foo"));
    rules.add(Rule.fixLength(10));
    rules.add(Rule.floatRule());
    rules.add(Rule.intRule());
    rules.add(Rule.iso8601Date());
    rules.add(Rule.iso8601Datetime());
    rules.add(Rule.iso8601Time());
    rules.add(Rule.list());
    rules.add(Rule.longRule());
    rules.add(Rule.map());
    rules.add(Rule.maxLength(5));
    rules.add(Rule.max(100));
    rules.add(Rule.minLength(12));
    rules.add(Rule.min(66));
    rules.add(Rule.optional(Lists.newArrayList(1, 2, 3)));
    rules.add(Rule.prohibited());
    rules.add(Rule.regex("[0-9]+"));
    rules.add(Rule.required());
    rules.add(Rule.shortRule());
    rules.add(Rule.datetime());
    String str = RuleManager.instance().toParsableString(rules);
    String expected = "alphaNumber|alpha|alphaSpace|alphaUnderscore|bool|byte|decimal:2|digits:3"
        + "|double|email|equals:foo|fixLength:10|float|int|ISO8601Date|ISO8601Datetime|ISO8601Time"
        + "|list|long|map|maxLength:5|max:100|minLength:12|min:66|optional:1,2,3|prohibited|regex:[0-9]+|required|short|datetime";
    Assert.assertEquals(expected, str);
  }

  @Test
  public void testParse() {
    String string = "alphaNumber|alpha|alphaSpace|alphaUnderscore|bool|byte|decimal:2|digits:3"
        + "|double|email|equals:foo|fixLength:10|float|int|ISO8601Date|ISO8601Datetime|ISO8601Time"
        + "|list|long|map|maxLength:5|max:100|minLength:12|min:66|optional:1,2,3|prohibited|regex:[0-9]+|required|short|datetime";
    List<Rule> rules = RuleManager.instance().parse(string);
    Assert.assertTrue(rules.get(0) instanceof AlphaNumberRule);
    Assert.assertTrue(rules.get(1) instanceof AlphaRule);
    Assert.assertTrue(rules.get(2) instanceof AlphaSpaceRule);
    Assert.assertTrue(rules.get(3) instanceof AlphaUnderscoreRule);
    Assert.assertTrue(rules.get(4) instanceof BoolRule);
    Assert.assertTrue(rules.get(5) instanceof ByteRule);
    Assert.assertTrue(rules.get(6) instanceof DecimalRule);
    Assert.assertEquals(((DecimalRule) rules.get(6)).point(), 2);
    Assert.assertTrue(rules.get(7) instanceof DigitsRule);
    Assert.assertEquals(((DigitsRule) rules.get(7)).length(), 3);
    Assert.assertTrue(rules.get(8) instanceof DoubleRule);
    Assert.assertTrue(rules.get(9) instanceof EmailRule);
    Assert.assertTrue(rules.get(10) instanceof EqualsRule);
    Assert.assertEquals(((EqualsRule) rules.get(10)).value(), "foo");
    Assert.assertTrue(rules.get(11) instanceof FixLengthRule);
    Assert.assertEquals(((FixLengthRule) rules.get(11)).value(), 10);
    Assert.assertTrue(rules.get(12) instanceof FloatRule);
    Assert.assertTrue(rules.get(13) instanceof IntRule);
    Assert.assertTrue(rules.get(14) instanceof ISO8601DateRule);
    Assert.assertTrue(rules.get(15) instanceof ISO8601DateTimeRule);
    Assert.assertTrue(rules.get(16) instanceof ISO8601TimeRule);
    Assert.assertTrue(rules.get(17) instanceof ListRule);
    Assert.assertTrue(rules.get(18) instanceof LongRule);
    Assert.assertTrue(rules.get(19) instanceof MapRule);
    Assert.assertTrue(rules.get(20) instanceof MaxLengthRule);
    Assert.assertEquals(((MaxLengthRule) rules.get(20)).value(), 5);
    Assert.assertTrue(rules.get(21) instanceof MaxRule);
    Assert.assertEquals(((MaxRule) rules.get(21)).value(), 100);
    Assert.assertTrue(rules.get(22) instanceof MinLengthRule);
    Assert.assertEquals(((MinLengthRule) rules.get(22)).value(), 12);
    Assert.assertTrue(rules.get(23) instanceof MinRule);
    Assert.assertEquals(((MinRule) rules.get(23)).value(), 66);
    Assert.assertTrue(rules.get(24) instanceof OptionalRule);
    Assert.assertEquals(((OptionalRule) rules.get(24)).value().size(), 3);
    Assert.assertTrue(rules.get(25) instanceof ProhibitedRule);
    Assert.assertTrue(rules.get(26) instanceof RegexRule);
    Assert.assertEquals(((RegexRule) rules.get(26)).value(), "[0-9]+");
    Assert.assertTrue(rules.get(27) instanceof RequiredRule);
    Assert.assertTrue(rules.get(28) instanceof ShortRule);
    Assert.assertTrue(rules.get(29) instanceof DateTimeRule);
  }
}
