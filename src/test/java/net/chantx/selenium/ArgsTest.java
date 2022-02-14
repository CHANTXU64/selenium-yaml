package net.chantx.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.apache.commons.lang3.RandomStringUtils;

public class ArgsTest extends Args {
  @Test
  public void testIsMatch () {
    assertTrue(Args.isMatch("___ARG_0___"));
    assertTrue(Args.isMatch("___ARG_1___"));
    assertTrue(Args.isMatch("___ARG_20000___"));
    assertFalse(Args.isMatch("__ARG_100__"));
    assertFalse(Args.isMatch("abcdefg"));
    assertFalse(Args.isMatch("___ARG_-1___"));
    assertTrue(Args.isMatch("___ARG_0___abc___ARG_1___"));
    assertTrue(Args.isMatch("___ARG_-1___abc___ARG_0_____"));
    assertTrue(Args.isMatch("___ARG_0______ARG_1___"));
  }

  @Test
  public void testGetArgIndexNumber () {
    assertEquals(0, Args.getArgIndexNumber("___ARG_0___"));
    assertEquals(1, Args.getArgIndexNumber("___ARG_1___"));
    assertEquals(41341, Args.getArgIndexNumber("___ARG_41341___"));
    assertNotEquals(100, Args.getArgIndexNumber("___ARG_99___"));
    assertNotEquals(98, Args.getArgIndexNumber("___ARG_99___"));
    assertNotEquals(1000, Args.getArgIndexNumber("___ARG_1001___"));
  }

  @Test
  public void testReplaceFirstMatch () {
    for (int i = 0; i < 30; ++i) {
      String arg_0 = RandomStringUtils.randomPrint(4, 30);
      String arg_1 = RandomStringUtils.randomPrint(4, 30);
      String arg_2 = RandomStringUtils.randomPrint(4, 30);
      String[] args = new String[] {arg_0, arg_1, arg_2};
      assertEquals(args[0], Args.replaceFirstMatch("___ARG_0___", args));
      assertEquals(args[1], Args.replaceFirstMatch("___ARG_1___", args));
      assertEquals(args[2], Args.replaceFirstMatch("___ARG_2___", args));
      assertEquals(args[0] + "___ARG_2___",
          Args.replaceFirstMatch("___ARG_0______ARG_2___", args));
      assertEquals("___" + args[0],
          Args.replaceFirstMatch("______ARG_0___", args));
      assertEquals("abcd" + args[1] + "ARG_100___ARG_3___",
          Args.replaceFirstMatch("abcd___ARG_1___ARG_100___ARG_3___", args));
      assertEquals("___ARG_0___",
          Args.replaceFirstMatch("___ARG_4______ARG_0___", args));
    }
  }

  @Test
  public void testReplace () {
    for (int i = 0; i < 30; ++i) {
      String arg_0 = RandomStringUtils.randomPrint(4, 30);
      String arg_1 = RandomStringUtils.randomPrint(4, 30);
      String arg_2 = RandomStringUtils.randomPrint(4, 30);
      String[] args = new String[] {arg_0, arg_1, arg_2};
      assertEquals(args[0], Args.replace("___ARG_0___", args));
      assertEquals(args[1], Args.replace("___ARG_1___", args));
      assertEquals(args[2], Args.replace("___ARG_2___", args));
      assertEquals(args[0] + args[2],
          Args.replace("___ARG_0______ARG_2___", args));
      assertEquals("___" + args[0],
          Args.replace("______ARG_0___", args));
      assertEquals("abcd" + args[1] + "ARG_100",
          Args.replace("abcd___ARG_1___ARG_100___ARG_3___", args));
      assertEquals(args[0],
          Args.replace("___ARG_4______ARG_0___", args));
    }
  }
}
