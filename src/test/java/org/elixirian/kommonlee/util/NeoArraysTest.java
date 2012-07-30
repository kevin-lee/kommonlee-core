/**
 * 
 */
package org.elixirian.kommonlee.util;

import static org.elixirian.kommonlee.util.Objects.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.elixirian.kommonlee.test.CauseCheckableExpectedException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
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

  @Test
  public final void testIsNotArray()
  {
    System.out.println("\n##### NeoArraysTest.testIsNotArray() starts");
    /* given */
    final String notArray1 = new String();
    final Integer notArray2 = new Integer(0);
    final Object notArray3 = new Object();
    final int notArray4 = 5;

    /* when */
    final boolean actual1 = NeoArrays.isNotArray(notArray1);
    final boolean actual2 = NeoArrays.isNotArray(notArray2);
    final boolean actual3 = NeoArrays.isNotArray(notArray3);
    final boolean actual4 = NeoArrays.isNotArray(notArray4);

    /* then */
    assertThat(actual1, is(Boolean.TRUE));
    assertThat(actual2, is(Boolean.TRUE));
    assertThat(actual3, is(Boolean.TRUE));
    assertThat(actual4, is(Boolean.TRUE));

    /* given */
    final String[] array1 = new String[0];
    final Integer[] array2 = new Integer[0];
    final Object[] array3 = new Object[0];
    final int[] array4 = new int[0];

    /* when */
    final boolean actual2_1 = NeoArrays.isNotArray(array1);
    final boolean actual2_2 = NeoArrays.isNotArray(array2);
    final boolean actual2_3 = NeoArrays.isNotArray(array3);
    final boolean actual2_4 = NeoArrays.isNotArray(array4);

    /* then */
    assertThat(actual2_1, is(Boolean.FALSE));
    assertThat(actual2_2, is(Boolean.FALSE));
    assertThat(actual2_3, is(Boolean.FALSE));
    assertThat(actual2_4, is(Boolean.FALSE));
    System.out.println("\n##### NeoArraysTest.testIsNotArray() ends");
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

  @Test
  public final void testToStringOfArrayBooleanArray()
  {
    /* given */
    final String expected1 = "[true, false, false, true, false, true, true, true, false, true, true, false, false]";
    final boolean[] array1 = { true, false, false, true, false, true, true, true, false, true, true, false, false };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final boolean[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* given */
    final String expected = "null";
    final boolean[] array = null;

    /* when */
    final String actual = NeoArrays.toStringOfArray(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Rule
  public CauseCheckableExpectedException expectedException = CauseCheckableExpectedException.none();

  @Test
  public final void testToStringOfArray0BooleanArray()
  {
    /* given */
    final String expected1 = "[true, false, false, true, false, true, true, true, false, true, true, false, false]";
    final boolean[] array1 = { true, false, false, true, false, true, true, true, false, true, true, false, false };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray0(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final boolean[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray0(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* Exceptional */
    /* given */
    final boolean[] array = null;

    /* expected */
    expectedException.expect(NullPointerException.class);

    /* when */
    NeoArrays.toStringOfArray0(array);

    /* otherwise-fail */
    fail();
  }

  @Test
  public final void testToStringOfArrayByteArray()
  {
    /* given */
    final String expected1 = "[-100, -10, -5, -3, -1, 0, 0, 1, 3, 5, 10, 100]";
    final byte[] array1 = { -100, -10, -5, -3, -1, -0, 0, 1, 3, 5, 10, 100 };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final byte[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* given */
    final String expected = "null";
    final byte[] array = null;

    /* when */
    final String actual = NeoArrays.toStringOfArray(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testToStringOfArray0ByteArray()
  {
    /* given */
    final String expected1 = "[-100, -10, -5, -3, -1, 0, 0, 1, 3, 5, 10, 100]";
    final byte[] array1 = { -100, -10, -5, -3, -1, -0, 0, 1, 3, 5, 10, 100 };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray0(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final byte[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray0(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* Exceptional */
    /* given */
    final byte[] array = null;

    /* expected */
    expectedException.expect(NullPointerException.class);

    /* when */
    NeoArrays.toStringOfArray0(array);

    /* otherwise-fail */
    fail();
  }

  @Test
  public final void testToStringOfArrayShortArray()
  {
    /* given */
    final String expected1 =
      "[" + Short.MIN_VALUE + ", -999, -100, -10, -5, -3, -1, 0, 0, 1, 3, 5, 10, 100, 999, " + Short.MAX_VALUE + "]";
    final short[] array1 =
      { Short.MIN_VALUE, -999, -100, -10, -5, -3, -1, -0, 0, 1, 3, 5, 10, 100, 999, Short.MAX_VALUE };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final short[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* given */
    final String expected = "null";
    final short[] array = null;

    /* when */
    final String actual = NeoArrays.toStringOfArray(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testToStringOfArray0ShortArray()
  {
    /* given */
    final String expected1 =
      "[" + Short.MIN_VALUE + ", -999, -100, -10, -5, -3, -1, 0, 0, 1, 3, 5, 10, 100, 999, " + Short.MAX_VALUE + "]";
    final short[] array1 =
      { Short.MIN_VALUE, -999, -100, -10, -5, -3, -1, -0, 0, 1, 3, 5, 10, 100, 999, Short.MAX_VALUE };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray0(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final short[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray0(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* Exceptional */
    /* given */
    final short[] array = null;

    /* expected */
    expectedException.expect(NullPointerException.class);

    /* when */
    NeoArrays.toStringOfArray0(array);

    /* otherwise-fail */
    fail();
  }

  @Test
  public final void testToStringOfArrayCharArray()
  {
    /* given */
    final String expected1 = "[a, b, c, d, e, f, g, h, i, j, k, \n, \t, \r]";
    final char[] array1 = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', '\n', '\t', '\r' };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final char[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* given */
    final String expected = "null";
    final char[] array = null;

    /* when */
    final String actual = NeoArrays.toStringOfArray(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testToStringOfArray0CharArray()
  {
    /* given */
    final String expected1 = "[a, b, c, d, e, f, g, h, i, j, k, \n, \t, \r]";
    final char[] array1 = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', '\n', '\t', '\r' };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray0(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final short[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray0(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* Exceptional */
    /* given */
    final short[] array = null;

    /* expected */
    expectedException.expect(NullPointerException.class);

    /* when */
    NeoArrays.toStringOfArray0(array);

    /* otherwise-fail */
    fail();
  }

  @Test
  public final void testToStringOfArrayIntArray()
  {
    /* given */
    final String expected1 =
      "[" + Integer.MIN_VALUE + ", -777777, -50000, " + Short.MIN_VALUE
          + ", -999, -100, -10, -5, -3, -1, 0, 0, 1, 3, 5, 10, 100, 999, " + Short.MAX_VALUE + ", 50000, 777777, "
          + Integer.MAX_VALUE + "]";
    final int[] array1 =
      { Integer.MIN_VALUE, -777777, -50000, Short.MIN_VALUE, -999, -100, -10, -5, -3, -1, -0, 0, 1, 3, 5, 10, 100, 999,
          Short.MAX_VALUE, 50000, 777777, Integer.MAX_VALUE };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final int[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* given */
    final String expected = "null";
    final int[] array = null;

    /* when */
    final String actual = NeoArrays.toStringOfArray(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testToStringOfArray0IntArray()
  {
    /* given */
    final String expected1 =
      "[" + Integer.MIN_VALUE + ", -777777, -50000, " + Short.MIN_VALUE
          + ", -999, -100, -10, -5, -3, -1, 0, 0, 1, 3, 5, 10, 100, 999, " + Short.MAX_VALUE + ", 50000, 777777, "
          + Integer.MAX_VALUE + "]";
    final int[] array1 =
      { Integer.MIN_VALUE, -777777, -50000, Short.MIN_VALUE, -999, -100, -10, -5, -3, -1, -0, 0, 1, 3, 5, 10, 100, 999,
          Short.MAX_VALUE, 50000, 777777, Integer.MAX_VALUE };
    /* when */
    final String actual1 = NeoArrays.toStringOfArray0(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final int[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray0(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* Exceptional */
    /* given */
    final int[] array = null;

    /* expected */
    expectedException.expect(NullPointerException.class);

    /* when */
    NeoArrays.toStringOfArray0(array);

    /* otherwise-fail */
    fail();
  }

  @Test
  public final void testToStringOfArrayLongArray()
  {
    /* given */
    final String expected1 =
      "[" + Long.MIN_VALUE + ", " + Integer.MIN_VALUE + ", -777777, -50000, " + Short.MIN_VALUE
          + ", -999, -100, -10, -5, -3, -1, 0, 0, 1, 3, 5, 10, 100, 999, " + Short.MAX_VALUE + ", 50000, 777777, "
          + Integer.MAX_VALUE + ", " + Long.MAX_VALUE + "]";
    final long[] array1 =
      { Long.MIN_VALUE, Integer.MIN_VALUE, -777777, -50000, Short.MIN_VALUE, -999, -100, -10, -5, -3, -1, -0, 0, 1, 3,
          5, 10, 100, 999, Short.MAX_VALUE, 50000, 777777, Integer.MAX_VALUE, Long.MAX_VALUE };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final long[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* given */
    final String expected = "null";
    final long[] array = null;

    /* when */
    final String actual = NeoArrays.toStringOfArray(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testToStringOfArray0LongArray()
  {
    /* given */
    final String expected1 =
      "[" + Long.MIN_VALUE + ", " + Integer.MIN_VALUE + ", -777777, -50000, " + Short.MIN_VALUE
          + ", -999, -100, -10, -5, -3, -1, 0, 0, 1, 3, 5, 10, 100, 999, " + Short.MAX_VALUE + ", 50000, 777777, "
          + Integer.MAX_VALUE + ", " + Long.MAX_VALUE + "]";
    final long[] array1 =
      { Long.MIN_VALUE, Integer.MIN_VALUE, -777777, -50000, Short.MIN_VALUE, -999, -100, -10, -5, -3, -1, -0, 0, 1, 3,
          5, 10, 100, 999, Short.MAX_VALUE, 50000, 777777, Integer.MAX_VALUE, Long.MAX_VALUE };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray0(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final long[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray0(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* Exceptional */
    /* given */
    final long[] array = null;

    /* expected */
    expectedException.expect(NullPointerException.class);

    /* when */
    NeoArrays.toStringOfArray0(array);

    /* otherwise-fail */
    fail();
  }

  @Test
  public final void testToStringOfArrayFloatArray()
  {
    /* given */
    final String expected1 =
      "[" + Float.MIN_VALUE
          + ", -999.15, -100.33, -10.2, -5.5, -3.2, -1.0, -0.0, 0.0, 1.0, 3.2, 5.5, 10.2, 100.33, 999.15, "
          + Float.MAX_VALUE + "]";
    final float[] array1 =
      { Float.MIN_VALUE, -999.15F, -100.33F, -10.2F, -5.5F, -3.2F, -1.0F, -0F, 0.0F, 1.0F, 3.2F, 5.5F, 10.2F, 100.33F,
          999.15F, Float.MAX_VALUE };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final float[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* given */
    final String expected = "null";
    final float[] array = null;

    /* when */
    final String actual = NeoArrays.toStringOfArray(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testToStringOfArray0FloatArray()
  {
    /* given */
    final String expected1 =
      "[" + Float.MIN_VALUE
          + ", -999.15, -100.33, -10.2, -5.5, -3.2, -1.0, -0.0, 0.0, 1.0, 3.2, 5.5, 10.2, 100.33, 999.15, "
          + Float.MAX_VALUE + "]";
    final float[] array1 =
      { Float.MIN_VALUE, -999.15F, -100.33F, -10.2F, -5.5F, -3.2F, -1.0F, -0F, 0.0F, 1.0F, 3.2F, 5.5F, 10.2F, 100.33F,
          999.15F, Float.MAX_VALUE };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray0(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final float[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray0(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* Exceptional */
    /* given */
    final float[] array = null;

    /* expected */
    expectedException.expect(NullPointerException.class);

    /* when */
    NeoArrays.toStringOfArray0(array);

    /* otherwise-fail */
    fail();
  }

  @Test
  public final void testToStringOfArrayDoubleArray()
  {
    /* given */
    final String expected1 =
      "[" + Double.MIN_VALUE + ", " + 1.4E-60 + ", " + (double) Float.MIN_VALUE + ", " + -999.15 + ", " + -100.33
          + ", " + -10.2 + ", " + -5.5 + ", " + -3.2 + ", " + -1.0 + ", " + 0.0 + ", " + 0.0 + ", " + 1.0 + ", " + 3.2
          + ", " + 5.5 + ", " + 10.2 + ", " + 100.33 + ", " + 999.15 + ", " + (double) Float.MAX_VALUE + ", "
          + 3.4028235E60 + ", " + Double.MAX_VALUE + "]";
    final double[] array1 =
      { Double.MIN_VALUE, 1.4E-60, Float.MIN_VALUE, -999.15, -100.33, -10.2, -5.5, -3.2, -1.0, -0, 0.0, 1.0, 3.2, 5.5,
          10.2, 100.33, 999.15, Float.MAX_VALUE, 3.4028235E60, Double.MAX_VALUE };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final double[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* given */
    final String expected = "null";
    final double[] array = null;

    /* when */
    final String actual = NeoArrays.toStringOfArray(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testToStringOfArray0DoubleArray()
  {
    /* given */
    final String expected1 =
      "[" + Double.MIN_VALUE + ", " + 1.4E-60 + ", " + (double) Float.MIN_VALUE + ", " + -999.15 + ", " + -100.33
          + ", " + -10.2 + ", " + -5.5 + ", " + -3.2 + ", " + -1.0 + ", " + 0.0 + ", " + 0.0 + ", " + 1.0 + ", " + 3.2
          + ", " + 5.5 + ", " + 10.2 + ", " + 100.33 + ", " + 999.15 + ", " + (double) Float.MAX_VALUE + ", "
          + 3.4028235E60 + ", " + Double.MAX_VALUE + "]";
    final double[] array1 =
      { Double.MIN_VALUE, 1.4E-60, Float.MIN_VALUE, -999.15, -100.33, -10.2, -5.5, -3.2, -1.0, -0, 0.0, 1.0, 3.2, 5.5,
          10.2, 100.33, 999.15, Float.MAX_VALUE, 3.4028235E60, Double.MAX_VALUE };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray0(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final double[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray0(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* Exceptional */
    /* given */
    final double[] array = null;

    /* expected */
    expectedException.expect(NullPointerException.class);

    /* when */
    NeoArrays.toStringOfArray0(array);

    /* otherwise-fail */
    fail();
  }

  class TestPojo
  {
    final Long id;
    final String name;

    public TestPojo(final long id, final String name)
    {
      this.id = Long.valueOf(id);
      this.name = name;
    }

    @Override
    public String toString()
    {
      /* @formatter:off */
      return toStringBuilder(this)
          .add("id", id)
          .add("name", name)
          .toString();
      /* @formatter:on */
    }
  }

  @Test
  public final void testToStringOfArrayObjectArray()
  {
    /* given */
    final String expected1 = "[test, Kevin, Lee, TestPojo{id=999, name=Kevin Lee}, 1970-01-01 10:20:34, 123.4567123]";
    final Date date = new Date();
    date.setTime(1234567);
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    final Object[] array1 =
      { new String("test"), "Kevin", "Lee", new TestPojo(999L, "Kevin Lee"), simpleDateFormat.format(date),
          new BigDecimal("123.4567123") };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final Object[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* given */
    final String expected = "null";
    final Object[] array = null;

    /* when */
    final String actual = NeoArrays.toStringOfArray(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testToStringOfArray0ObjectArray()
  {
    /* given */
    final String expected1 = "[test, Kevin, Lee, TestPojo{id=999, name=Kevin Lee}, 1970-01-01 10:20:34, 123.4567123]";
    final Date date = new Date();
    date.setTime(1234567);
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    final Object[] array1 =
      { new String("test"), "Kevin", "Lee", new TestPojo(999L, "Kevin Lee"), simpleDateFormat.format(date),
          new BigDecimal("123.4567123") };

    /* when */
    final String actual1 = NeoArrays.toStringOfArray0(array1);

    /* then */
    assertThat(actual1, is(equalTo(expected1)));

    /* given */
    final String expected2 = "[]";
    final Object[] array2 = {};

    /* when */
    final String actual2 = NeoArrays.toStringOfArray0(array2);

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* Exceptional */
    /* given */
    final Object[] array = null;

    /* expected */
    expectedException.expect(NullPointerException.class);

    /* when */
    NeoArrays.toStringOfArray0(array);

    /* otherwise-fail */
    fail();
  }

  @Test
  public final void testEqualByteArrayByteArray()
  {
    /* given */
    final byte[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final byte[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.equal(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testEqualByteArrayByteArrayWithNotEqual()
  {
    /* given */
    final byte[] expected = { 1, 2, 3, 3, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final byte[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.equal(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testEqualByteArrayByteArrayWithDifferentLength()
  {
    /* given */
    final byte[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final byte[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.equal(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testEqualByteArrayByteArrayWithNull()
  {
    /* given */
    final byte[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final byte[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.equal(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

//  @Test
//  public final void testNotEqualByteArrayByteArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public final void testEqualCharArrayCharArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public final void testNotEqualCharArrayCharArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public final void testEqualShortArrayShortArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public final void testNotEqualShortArrayShortArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public final void testEqualIntArrayIntArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public final void testNotEqualIntArrayIntArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public final void testEqualLongArrayLongArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public final void testNotEqualLongArrayLongArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public final void testEqualFloatArrayFloatArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public final void testNotEqualFloatArrayFloatArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public final void testEqualDoubleArrayDoubleArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public final void testNotEqualDoubleArrayDoubleArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public final void testEqualBooleanArrayBooleanArray()
//  {
//    fail("Not yet implemented");
//  }
//
//  @Test
//  public final void testNotEqualBooleanArrayBooleanArray()
//  {
//    fail("Not yet implemented");
//  }
}
