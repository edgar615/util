package com.edgar.util.event;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Edgar on 2017/3/8.
 *
 * @author Edgar  Date 2017/3/8
 */
public class EventTest {

  @Test
  public void testMessage() {
    String from = UUID.randomUUID().toString();
    String to = UUID.randomUUID().toString();
    String group = UUID.randomUUID().toString();
    EventAction action = Message.create("test", new HashMap<>());
    Event event = Event.create(from, to, group, action);

    Assert.assertTrue(event.action() instanceof Message);
    Assert.assertEquals(from, event.head().from());
    Assert.assertEquals(to, event.head().to());
    Assert.assertEquals(group, event.head().group());
    Assert.assertEquals(0, event.head().sequence());
    Assert.assertNotNull(event.head().timestamp());
    Assert.assertNotNull(event.head().id());
  }

  @Test
  public void testRequest() {
    String from = UUID.randomUUID().toString();
    String to = UUID.randomUUID().toString();
    String group = UUID.randomUUID().toString();
    EventAction action = Request.create("test","get", new HashMap<>());
    Event event = Event.create(from, to, group, action);

    Assert.assertTrue(event.action() instanceof Request);
    Assert.assertEquals(from, event.head().from());
    Assert.assertEquals(to, event.head().to());
    Assert.assertEquals(group, event.head().group());
    Assert.assertEquals(0, event.head().sequence());
    Assert.assertNotNull(event.head().timestamp());
    Assert.assertNotNull(event.head().id());
  }

  @Test
  public void testResponse() {
    String from = UUID.randomUUID().toString();
    String to = UUID.randomUUID().toString();
    String group = UUID.randomUUID().toString();
    EventAction action = Response.create(0,"get", new HashMap<>());
    Event event = Event.create(from, to, group, action);

    Assert.assertTrue(event.action() instanceof Response);
    Assert.assertEquals(from, event.head().from());
    Assert.assertEquals(to, event.head().to());
    Assert.assertEquals(group, event.head().group());
    Assert.assertEquals(0, event.head().sequence());
    Assert.assertNotNull(event.head().timestamp());
    Assert.assertNotNull(event.head().id());
  }
}
