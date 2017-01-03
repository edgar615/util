package com.edgar.util.validation;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edgar on 2016/4/13.
 *
 * @author Edgar  Date 2016/4/13
 */
public class ValidationTest {

  @Test
  public void testEx() {
    ValidationException validationException = new ValidationException("haha");
    System.out.println(validationException.getMessage());
    System.out.println(validationException.toString());
    validationException.printStackTrace();
  }

  @Test(expected = ValidationException.class)
  public void testValidator() {
    Multimap<String, Rule> rules = ArrayListMultimap.create();
    rules.put("username", Rule.required());
    rules.put("username", Rule.maxLength(16));
    rules.put("password", Rule.required());
    rules.put("profile", Rule.required());
    rules.put("interest", Rule.required());

    Map<String, Object> params = new HashMap<>();
    params.put("username", "edgar");
//     jsonObject.put("profile", new JsonObject().put("age", 30));
//    jsonObject.put("interest", new JsonArray().add(1).add(2));

    try {
      Validations.validate(params, rules);
    } catch (ValidationException e) {
      Assert.assertEquals(3, e.getErrorDetail().size());
      throw e;
    }
    Assert.fail();
  }

  @Test(expected = ValidationException.class)
  public void testValidator2() {
    Multimap<String, Rule> rules = ArrayListMultimap.create();
    rules.put("username", Rule.required());
    rules.put("username", Rule.maxLength(16));
    rules.put("password", Rule.required());
    rules.put("profile", Rule.required());
    rules.put("profile", Rule.list());
    rules.put("interest", Rule.map());

    Map<String, Object> params = new HashMap<>();
    params.put("username", "edgar");
    params.put("profile", "edgar");
    params.put("interest", "edgar");
//     jsonObject.put("profile", new JsonObject().put("age", 30));
//    jsonObject.put("interest", new JsonArray().add(1).add(2));

    try {
      Validations.validate(params, rules);
    } catch (ValidationException e) {
      Assert.assertEquals(3, e.getErrorDetail().size());
      throw e;
    }
    Assert.fail();
  }

  @Test(expected = ValidationException.class)
  public void testValidator3() {
    Multimap<String, Rule> rules = ArrayListMultimap.create();
    rules.put("username", Rule.required());
    rules.put("username", Rule.maxLength(16));
    rules.put("password", Rule.required());
    rules.put("profile", Rule.required());
    rules.put("profile", Rule.list());
    rules.put("interest", Rule.map());

    Map<String, Object> params = new HashMap<>();
    params.put("username", "edgar");
    params.put("profile", new ArrayList<>());
    params.put("interest", new HashMap<>());
//     jsonObject.put("profile", new JsonObject().put("age", 30));
//    jsonObject.put("interest", new JsonArray().add(1).add(2));

    try {
      Validations.validate(params, rules);
    } catch (ValidationException e) {
      Assert.assertEquals(1, e.getErrorDetail().size());
      throw e;
    }
    Assert.fail();
  }

  public void testValidator4() {
    Multimap<String, Rule> rules = ArrayListMultimap.create();
    rules.put("username", Rule.required());
    rules.put("username", Rule.maxLength(16));
    rules.put("password", Rule.required());
    rules.put("profile", Rule.required());
    rules.put("profile", Rule.list());
    rules.put("interest", Rule.map());

    Map<String, Object> params = new HashMap<>();
    params.put("username", "edgar");
    params.put("password", "edgar");
    params.put("profile", new ArrayList<>());
    params.put("interest", new HashMap<>());
//     jsonObject.put("profile", new JsonObject().put("age", 30));
//    jsonObject.put("interest", new JsonArray().add(1).add(2));

    try {
      Validations.validate(params, rules);
    } catch (ValidationException e) {
//            Assert.assertEquals(1, e.getErrorDetail().size());
      Assert.fail();
      throw e;
    }
  }
}
