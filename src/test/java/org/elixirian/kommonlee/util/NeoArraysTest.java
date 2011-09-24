/**
 * 
 */
package org.elixirian.kommonlee.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <pre>
 *     ____________    ___________  ____   _______ _________ _______ _______________  ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /     \/   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /          /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2011-08-25)
 */
public class NeoArraysTest
{
  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
  }

  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @SuppressWarnings("boxing")
  @Test
  public final void testIsArrayWithNotArray()
  {
    System.out.println("\n##### NeoArraysTest.testIsArrayWithNotArray() starts");
    /* given */
    final String input1 = new String();
    final Integer input2 = new Integer(0);
    final Object input3 = new Object();
    final Object input4 = null;
    final Object[] input5 = null;

    /* when */
    final boolean actual1 = NeoArrays.isArray(input1);
    final boolean actual2 = NeoArrays.isArray(input2);
    final boolean actual3 = NeoArrays.isArray(input3);
    final boolean actual4 = NeoArrays.isArray(input4);
    final boolean actual5 = NeoArrays.isArray(input5);

    /* then */
    assertThat(actual1, is(Boolean.FALSE));
    assertThat(actual2, is(Boolean.FALSE));
    assertThat(actual3, is(Boolean.FALSE));
    assertThat(actual4, is(Boolean.FALSE));
    assertThat(actual5, is(Boolean.FALSE));
    System.out.println("\n##### NeoArraysTest.testIsArrayWithNotArray() ends");
  }

  @SuppressWarnings("boxing")
  @Test
  public final void testIsArray()
  {
    System.out.println("\n##### NeoArraysTest.testIsArray() starts");
    /* given */
    final String[] array1 = new String[0];
    final Integer[] array2 = new Integer[0];
    final Object[] array3 = new Object[0];
    final int[] array4 = new int[0];

    /* when */
    final boolean actual1 = NeoArrays.isArray(array1);
    final boolean actual2 = NeoArrays.isArray(array2);
    final boolean actual3 = NeoArrays.isArray(array3);
    final boolean actual4 = NeoArrays.isArray(array4);

    /* then */
    assertThat(actual1, is(Boolean.TRUE));
    assertThat(actual2, is(Boolean.TRUE));
    assertThat(actual3, is(Boolean.TRUE));
    assertThat(actual4, is(Boolean.TRUE));
    System.out.println("\n##### NeoArraysTest.testIsArray() ends");
  }

  private static <T> String getExpectedToString(final T[] array)
  {
    if (null == array)
    {
      return "null";
    }
    final StringBuilder stringBuilder = new StringBuilder("[");
    for (final T t : array)
    {
      if (null == t)
      {
        stringBuilder.append("null")
            .append(", ");
      }
      else
      {
        stringBuilder.append(t.getClass()
            .isArray() ? getExpectedToString((Object[]) t) : t)
            .append(", ");
      }
    }
    final int length = stringBuilder.length();
    if (1 < length)
    {
      stringBuilder.delete(length - 2, length);
    }
    return stringBuilder.append("]")
        .toString();
  }

  @Test
  public final void testToStringOfArray()
  {
    System.out.println("\n##### NeoArraysTest.testToStringOfArray() starts");
    /* given */
    final String[] array1 =
      { "Hello", "Kevin", "This is a test array", "1", "2", "3", "a", "b", "c", "d", "e", null, "", "blah blah", "end" };
    final String expected1 = "[Hello, Kevin, This is a test array, 1, 2, 3, a, b, c, d, e, null, , blah blah, end]";

    @SuppressWarnings("boxing")
    final Integer[] array2 = { -100, -10, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100 };
    final String expected2 = "[-100, -10, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100]";

    final String[] array3 = {};
    final String expected3 = "[]";

    final Object[] array4 = null;
    final Object expected4 = "null";

    /* when */
    System.out.println("\nexpected:\n" + expected1);
    System.out.println("actual:");
    final String actual1 = NeoArrays.toStringOfArray(array1);
    System.out.println(actual1);

    System.out.println("\nexpected:\n" + expected2);
    System.out.println("actual:");
    final String actual2 = NeoArrays.toStringOfArray(array2);
    System.out.println(actual2);

    System.out.println("\nexpected:\n" + expected3);
    System.out.println("actual:");
    final String actual3 = NeoArrays.toStringOfArray(array3);
    System.out.println(actual3);

    System.out.println("\nexpected:\n" + null);
    System.out.println("actual:");
    final String actual4 = NeoArrays.toStringOfArray(array4);
    System.out.println(actual4);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));
    assertThat(actual2, is(equalTo(expected2)));
    assertThat(actual3, is(equalTo(expected3)));
    assertThat(actual4, is(equalTo(expected4)));
    System.out.println("\n##### NeoArraysTest.testToStringOfArray() ends");
  }

  @Test
  public final void testToStringOfArrayWith2DArray()
  {
    System.out.println("\n##### NeoArraysTest.testToStringOfArrayWith2DArray() starts");
    /* given */
    final String[][] array1 =
      { { "Hello", "Kevin" }, { "This is a test array" }, { "1", "2" }, { "3", "a" }, { "b", "c" }, { "d", "e" },
          { null, "" }, { "blah blah", "end" } };
    final String expected1 =
      "[[Hello, Kevin], [This is a test array], [1, 2], [3, a], [b, c], [d, e], [null, ], [blah blah, end]]";

    @SuppressWarnings("boxing")
    final Integer[][] array2 = { { -100, -10, -1 }, { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 9, 10, 100 } };
    final String expected2 = "[[-100, -10, -1], [0, 1, 2], [3, 4, 5], [6, 7, 8], [9, 10, 100]]";

    final String[][] array3 = { {} };
    final String expected3 = "[[]]";

    final Object[][] array4 = null;
    final Object expected4 = "null";

    /* when */
    System.out.println("\nexpected:\n" + expected1);
    System.out.println("actual:");
    final String actual1 = NeoArrays.toStringOfArray(array1);
    System.out.println(actual1);

    System.out.println("\nexpected:\n" + expected2);
    System.out.println("actual:");
    final String actual2 = NeoArrays.toStringOfArray(array2);
    System.out.println(actual2);

    System.out.println("\nexpected:\n" + expected3);
    System.out.println("actual:");
    final String actual3 = NeoArrays.toStringOfArray(array3);
    System.out.println(actual3);

    System.out.println("\nexpected:\n" + null);
    System.out.println("actual:");
    final String actual4 = NeoArrays.toStringOfArray(array4);
    System.out.println(actual4);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));
    assertThat(actual2, is(equalTo(expected2)));
    assertThat(actual3, is(equalTo(expected3)));
    assertThat(actual4, is(equalTo(expected4)));
    System.out.println("\n##### NeoArraysTest.testToStringOfArrayWith2DArray() ends");
  }

  @Test
  public final void testToStringOfArrayWith3DArray()
  {
    System.out.println("\n##### NeoArraysTest.testToStringOfArrayWith3DArray() starts");
    /* given */
    final String[][][] array1 =
      { { { "Hello", "Kevin" }, { "This is a test array" }, { "1", "2" } },
          { { "3", "a" }, { "b", "c" }, { "d", "e" } }, { { null, "" }, {}, { "blah blah", "end" } } };
    final String expected1 =
      "[[[Hello, Kevin], [This is a test array], [1, 2]], [[3, a], [b, c], [d, e]], [[null, ], [], [blah blah, end]]]";

    @SuppressWarnings("boxing")
    final Integer[][][] array2 =
      { { { -100, -10, -1 }, { 0, 1, 2 } }, { {}, { 3, 4, 5 }, {} }, { { 6, 7, 8 }, {}, { 9, 10, 100 } } };
    final String expected2 = "[[[-100, -10, -1], [0, 1, 2]], [[], [3, 4, 5], []], [[6, 7, 8], [], [9, 10, 100]]]";

    final String[][][] array3 = { { {}, {} }, { {} } };
    final String expected3 = "[[[], []], [[]]]";

    final Object[][] array4 = null;
    final Object expected4 = "null";

    /* when */
    System.out.println("\nexpected:\n" + expected1);
    System.out.println("actual:");
    final String actual1 = NeoArrays.toStringOfArray(array1);
    System.out.println(actual1);

    System.out.println("\nexpected:\n" + expected2);
    System.out.println("actual:");
    final String actual2 = NeoArrays.toStringOfArray(array2);
    System.out.println(actual2);

    System.out.println("\nexpected:\n" + expected3);
    System.out.println("actual:");
    final String actual3 = NeoArrays.toStringOfArray(array3);
    System.out.println(actual3);

    System.out.println("\nexpected:\n" + null);
    System.out.println("actual:");
    final String actual4 = NeoArrays.toStringOfArray(array4);
    System.out.println(actual4);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));
    assertThat(actual2, is(equalTo(expected2)));
    assertThat(actual3, is(equalTo(expected3)));
    assertThat(actual4, is(equalTo(expected4)));
    System.out.println("\n##### NeoArraysTest.testToStringOfArrayWith3DArray() ends");
  }
}
