package com.edgar.util.validation;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Edgar on 2016/3/18.
 *
 * @author Edgar  Date 2016/3/18
 */
public class RuleTest {

    @Test
    public void testMaxLength() {

        Rule rule = Rule.maxLength(5);
        assertTrue(rule.isValid("abc"));
        assertTrue(rule.isValid("abcde"));
        assertFalse(rule.message(), rule.isValid("abcdef"));

        assertTrue(rule.message(), rule.isValid(1));
        assertFalse(rule.message(), rule.isValid(12345678));

        assertTrue(rule.message(), rule.isValid(new Object()));
    }

    @Test
    public void testMinLength() {
        Rule rule = Rule.minLength(5);
        assertTrue(rule.isValid("abcdef"));
        assertTrue(rule.isValid("abcde"));
        assertFalse(rule.message(), rule.isValid("abc"));

        assertFalse(rule.message(), rule.isValid(1));
        assertTrue(rule.message(), rule.isValid(12345678));
        assertTrue(rule.message(), rule.isValid(new Object()));
    }

    @Test
    public void testMax() {
        Rule rule = Rule.max(5);
        assertTrue(rule.isValid(1));
        assertTrue(rule.isValid(5));
        assertFalse(rule.message(), rule.isValid(15));

        assertTrue(rule.isValid("1"));
        assertTrue(rule.isValid("5"));
        assertFalse(rule.message(), rule.isValid("15"));

        assertTrue(rule.message(), rule.isValid(new Object()));

        rule = Rule.max(5.0);
        assertTrue(rule.isValid(1));
        assertTrue(rule.isValid(5));
        assertFalse(rule.message(), rule.isValid(15));

        assertTrue(rule.isValid("1"));
        assertTrue(rule.isValid("5"));
        assertFalse(rule.message(), rule.isValid("15"));

        rule = Rule.max(5.0);
        assertTrue(rule.isValid(1.0));
        assertTrue(rule.isValid(5.0));
        assertFalse(rule.message(), rule.isValid(15.0));

        assertTrue(rule.isValid("1.0"));
        assertTrue(rule.isValid("5.0"));
        assertFalse(rule.message(), rule.isValid("15.0"));
    }

    @Test
    public void testMin() {
        Rule rule = Rule.min(5);
        assertTrue(rule.isValid(10));
        assertTrue(rule.isValid(5));
        assertFalse(rule.message(), rule.isValid(1));

        assertTrue(rule.isValid("10"));
        assertTrue(rule.isValid("5"));
        assertFalse(rule.message(), rule.isValid("1"));

        assertTrue(rule.message(), rule.isValid(new Object()));

        rule = Rule.min(5.0);
        assertTrue(rule.isValid(10));
        assertTrue(rule.isValid(5));
        assertFalse(rule.message(), rule.isValid(1));

        assertTrue(rule.isValid("10"));
        assertTrue(rule.isValid("5"));
        assertFalse(rule.message(), rule.isValid("1"));

        rule = Rule.min(5.0);
        assertTrue(rule.isValid(10.0));
        assertTrue(rule.isValid(5.0));
        assertFalse(rule.message(), rule.isValid(1.0));

        assertTrue(rule.isValid("10.0"));
        assertTrue(rule.isValid("5.0"));
        assertFalse(rule.message(), rule.isValid("1.0"));
    }

    @Test
    public void testRequired() {
        Rule rule = Rule.required();
        assertTrue(rule.isValid("foo"));
        assertFalse(rule.message(), rule.isValid(null));
        assertFalse(rule.message(), rule.isValid(""));
        assertTrue(rule.isValid("     "));

        assertTrue(rule.isValid(new Object()));

    }

    @Test
    public void testRegex() {
        String regex = "[0-9]{11}";
        Rule rule = Rule.regex(regex);
        assertFalse(rule.message(), rule.isValid("abc"));
        assertFalse(rule.message(), rule.isValid("123"));
        assertFalse(rule.message(), rule.isValid("abc123"));
        assertTrue(rule.message(), rule.isValid("12345678901"));

        rule = Rule.regex("/users/[0-9]+/wallet");
        assertTrue(rule.message(), rule.isValid("/users/1/wallet"));
        assertFalse(rule.message(), rule.isValid("/users/a/wallet"));

        rule = Rule.regex("[0-9A-F]{16}");
        assertTrue(rule.message(), rule.isValid("0123456789ABCDEF"));
        assertFalse(rule.message(), rule.isValid("0123456789aBCDEF"));
    }

    @Test
    public void testProhibited() {
        Rule rule = Rule.prohibited();
        assertFalse(rule.message(), rule.isValid("abc"));
        assertTrue(rule.message(), rule.isValid(null));
    }

    @Test
    public void testOptional() {
        Rule rule = Rule.optional(ImmutableList.of(1, 2, 3));
        assertTrue(rule.message(), rule.isValid("1"));
        assertTrue(rule.message(), rule.isValid(1));
        assertTrue(rule.message(), rule.isValid(null));

        assertFalse(rule.message(), rule.isValid("4"));
    }

    @Test
    public void testEmail() {
        Rule rule = Rule.email();
        assertTrue(rule.message(), rule.isValid("1@qq.com"));
        assertTrue(rule.message(), rule.isValid("yuzhou.zhang@csst.com"));
        assertTrue(rule.message(), rule.isValid(null));
        assertTrue(rule.message(), rule.isValid("zyz@126.COM"));
        assertTrue(rule.message(), rule.isValid("zyz@qq"));
        assertFalse(rule.message(), rule.isValid("4"));
    }

    @Test
    public void testEqulas() {

        Rule rule = Rule.equals("5");
        assertTrue(rule.isValid(5));
        assertTrue(rule.isValid("5"));
        assertFalse(rule.message(), rule.isValid("1"));
        assertFalse(rule.message(), rule.isValid(new Object()));
    }

    @Test
    public void testInt() {

        Rule rule = Rule.integer();
        assertTrue(rule.isValid(5));
        assertTrue(rule.isValid("5"));
        assertTrue(rule.message(), rule.isValid("1111111111"));
        assertFalse(rule.message(), rule.isValid(new Object()));
        assertFalse(rule.message(), rule.isValid("11111111111111111111"));
    }

    @Test
    public void testBool() {

        Rule rule = Rule.bool();
        assertTrue(rule.isValid("true"));
        assertTrue(rule.isValid(true));
        assertTrue(rule.isValid("false"));
        assertTrue(rule.isValid(false));
        assertFalse(rule.isValid(1));
        assertFalse(rule.isValid("0"));
        assertFalse(rule.message(), rule.isValid("1111111111"));
        assertFalse(rule.message(), rule.isValid(new Object()));
        assertFalse(rule.message(), rule.isValid("11111111111111111111"));
    }

    @Test
    public void testList() {

        Rule rule = Rule.list();
        assertTrue(rule.isValid(new ArrayList<>()));
        assertTrue(rule.isValid(Arrays.asList("1", "2")));
        assertTrue(rule.isValid(null));
        assertFalse(rule.isValid(new HashMap<>()));
        assertFalse(rule.isValid("true"));
        assertFalse(rule.isValid(true));
        assertFalse(rule.isValid("false"));
        assertFalse(rule.isValid(false));
        assertFalse(rule.isValid(1));
        assertFalse(rule.isValid("0"));
        assertFalse(rule.message(), rule.isValid("1111111111"));
        assertFalse(rule.message(), rule.isValid(new Object()));
        assertFalse(rule.message(), rule.isValid("11111111111111111111"));
    }

    @Test
    public void testMap() {

        Rule rule = Rule.map();
        assertTrue(rule.isValid(null));
        assertTrue(rule.isValid(new HashMap<>()));
        assertTrue(rule.isValid(ImmutableMap.of(1, 1)));
        assertFalse(rule.isValid(new ArrayList<>()));
        assertFalse(rule.isValid(Arrays.asList("1", "2")));
        assertFalse(rule.isValid("true"));
        assertFalse(rule.isValid(true));
        assertFalse(rule.isValid("false"));
        assertFalse(rule.isValid(false));
        assertFalse(rule.isValid(1));
        assertFalse(rule.isValid("0"));
        assertFalse(rule.message(), rule.isValid("1111111111"));
        assertFalse(rule.message(), rule.isValid(new Object()));
        assertFalse(rule.message(), rule.isValid("11111111111111111111"));
    }

}
