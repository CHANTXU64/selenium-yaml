package net.chantx.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.apache.commons.lang3.RandomStringUtils;

public class ArgsTest extends Args {
  @Test
  public void testIsMatch () {
    assertTrue(Args.isMatch("___ARG_0___"));
    assertTrue(Args.isMatch("___ARG_1___"));
    assertTrue(Args.isMatch("___ARG_20000___"));
    assertTrue(Args.isMatch("___ARG_a___"));
    assertTrue(Args.isMatch("___ARG_0bcCez1___"));
    assertFalse(Args.isMatch("__ARG_100__"));
    assertFalse(Args.isMatch("___ARG____"));
    assertFalse(Args.isMatch("abcdefg"));
    assertFalse(Args.isMatch("___ARG_-1___"));
    assertTrue(Args.isMatch("___ARG_0___abc___ARG_1___"));
    assertTrue(Args.isMatch("___ARG_-1___abc___ARG_0_____"));
    assertTrue(Args.isMatch("___ARG_0______ARG_1___"));
    for (int i = 1; i <= 100; ++i) {
      String key = RandomStringUtils.randomAlphanumeric(i);
      assertTrue(Args.isMatch("___ARG_" + key + "___"));
    }
  }

  @Test
  public void testGetArgKey () {
    assertEquals("0", Args.getArgKey("___ARG_0___"));
    assertEquals("1", Args.getArgKey("___ARG_1___"));
    assertEquals("41341", Args.getArgKey("___ARG_41341___"));
    assertEquals("aeC4Zz3", Args.getArgKey("___ARG_aeC4Zz3___"));
    for (int i = 1; i <= 100; ++i) {
      String key = RandomStringUtils.randomAlphanumeric(i);
      assertEquals(key, Args.getArgKey("___ARG_" + key + "___"));
    }
  }

  @Test
  public void testReplaceFirstMatch () {
    for (int i = 1; i <= 50; ++i) {
      String value_0 = RandomStringUtils.randomPrint(4, 30);
      String value_1 = RandomStringUtils.randomPrint(4, 30);
      String key_0 = RandomStringUtils.randomAlphanumeric(i);
      String key_1 = RandomStringUtils.randomAlphanumeric(i);
      String key = RandomStringUtils.randomAlphanumeric(i);
      if (key_0.equals(key_1) || key_0.equals(key) || key_1.equals(key)) {
        continue;
      }
      Map<String, String> args = new HashMap<String, String>();
      args.put(key_0, value_0);
      args.put(key_1, value_1);
      assertEquals(args.get(key_0),
          Args.replaceFirstMatch("___ARG_" + key_0 + "___", args));
      assertEquals(args.get(key_1),
          Args.replaceFirstMatch("___ARG_" + key_1 + "___", args));
      assertEquals(args.get(key_0),
          Args.replaceFirstMatch("___ARG_" + key_0 + "___", args));
      assertEquals(args.get(key_0) + "___ARG_" + key_1 + "___",
          Args.replaceFirstMatch("___ARG_" + key_0 + "______ARG_" + key_1
            + "___", args));
      assertEquals("___" + args.get(key_0),
          Args.replaceFirstMatch("______ARG_" + key_0 + "___", args));
      assertEquals("abcd" + args.get(key_1) + "ARG_100___ARG_" + key + "___",
          Args.replaceFirstMatch("abcd___ARG_" + key_1 + "___ARG_100___ARG_"
            + key + "___", args));
      assertEquals("___ARG_" + key_0 + "___",
          Args.replaceFirstMatch("___ARG_" + key + "______ARG_"
            + key_0 + "___", args));
    }
  }

  @Test
  public void testReplace () {
    for (int i = 1; i <= 50; ++i) {
      String value_0 = RandomStringUtils.randomPrint(4, 30);
      String value_1 = RandomStringUtils.randomPrint(4, 30);
      String key_0 = RandomStringUtils.randomAlphanumeric(i);
      String key_1 = RandomStringUtils.randomAlphanumeric(i);
      String key = RandomStringUtils.randomAlphanumeric(i);
      if (key_0.equals(key_1) || key_0.equals(key) || key_1.equals(key)) {
        continue;
      }
      Map<String, String> args = new HashMap<String, String>();
      args.put(key_0, value_0);
      args.put(key_1, value_1);
      assertEquals(args.get(key_0),
          Args.replace("___ARG_" + key_0 + "___", args));
      assertEquals(args.get(key_1),
          Args.replace("___ARG_" + key_1 + "___", args));
      assertEquals(args.get(key_0),
          Args.replace("___ARG_" + key_0 + "___", args));
      assertEquals(args.get(key_0) + args.get(key_1),
          Args.replace("___ARG_" + key_0 + "______ARG_" + key_1 + "___", args));
      assertEquals("___" + args.get(key_0),
          Args.replace("______ARG_" + key_0 + "___", args));
      assertEquals("abcd" + args.get(key_1) + "ARG_100",
          Args.replace("abcd___ARG_" + key_1 + "___ARG_100___ARG_"
            + key + "___", args));
      assertEquals(args.get(key_0),
          Args.replace("___ARG_" + key + "______ARG_" + key_0 + "___", args));
    }
  }
}
