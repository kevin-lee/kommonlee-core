/**
 * This project is licensed under the Apache License, Version 2.0
 * if the following condition is met:
 * (otherwise it cannot be used by anyone but the author, Kevin, only)
 *
 * The original KommonLee project is owned by Lee, Seong Hyun (Kevin).
 *
 * -What does it mean to you?
 * Nothing, unless you want to take the ownership of
 * "the original project" (not yours or forked & modified one).
 * You are free to use it for both non-commercial and commercial projects
 * and free to modify it as the Apache License allows.
 *
 * -So why is this condition necessary?
 * It is only to protect the original project (See the case of Java).
 *
 *
 * Copyright 2009 Lee, Seong Hyun (Kevin)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 *     ___  _____                                              _____
 *    /   \/    / ______ __________________  ______ __ ______ /    /   ______  ______  
 *   /        / _/ __  // /  /   / /  /   /_/ __  // //     //    /   /  ___ \/  ___ \ 
 *  /        \ /  /_/ _/  _  _  /  _  _  //  /_/ _/   __   //    /___/  _____/  _____/
 * /____/\____\/_____//__//_//_/__//_//_/ /_____//___/ /__//________/\_____/ \_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
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
    @SuppressWarnings("boxing")
    final Boolean actual1 = NeoArrays.isNotArray(notArray1);

    @SuppressWarnings("boxing")
    final Boolean actual2 = NeoArrays.isNotArray(notArray2);

    @SuppressWarnings("boxing")
    final Boolean actual3 = NeoArrays.isNotArray(notArray3);

    @SuppressWarnings("boxing")
    final Boolean actual4 = NeoArrays.isNotArray(notArray4);

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
    @SuppressWarnings("boxing")
    final Boolean actual2_1 = NeoArrays.isNotArray(array1);

    @SuppressWarnings("boxing")
    final Boolean actual2_2 = NeoArrays.isNotArray(array2);

    @SuppressWarnings("boxing")
    final Boolean actual2_3 = NeoArrays.isNotArray(array3);

    @SuppressWarnings("boxing")
    final Boolean actual2_4 = NeoArrays.isNotArray(array4);

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
  public final void testEqualByteArrayByteArrayWithBothNulls()
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

  @Test
  public final void testNotEqualByteArrayByteArray()
  {
    /* given */
    final byte[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 11 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final byte[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualByteArrayByteArrayWithEqual()
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
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testNotEqualByteArrayByteArrayWithDifferentLength()
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
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualByteArrayByteArrayWithNull()
  {
    /* given */
    final byte[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final byte[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualByteArrayByteArrayWithBothNulls()
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
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testEqualCharArrayCharArray()
  {
    /* given */
    final char[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final char[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
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
  public final void testEqualCharArrayCharArrayWithNotEqual()
  {
    /* given */
    final char[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final char[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 11 };
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
  public final void testEqualCharArrayCharArrayWithDifferentLength()
  {
    /* given */
    final char[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final char[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
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
  public final void testEqualCharArrayCharArrayWithNull()
  {
    /* given */
    final char[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final char[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
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
  public final void testEqualCharArrayCharArrayWithBothNulls()
  {
    /* given */
    final char[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final char[] actual = null;
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
  public final void testNotEqualCharArrayCharArray()
  {
    /* given */
    final char[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final char[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 11 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualCharArrayCharArrayWithEqual()
  {
    /* given */
    final char[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final char[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testNotEqualCharArrayCharArrayWithDifferentLength()
  {
    /* given */
    final char[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final char[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualCharArrayCharArrayWithNull()
  {
    /* given */
    final char[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final char[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualCharArrayCharArrayWithBothNulls()
  {
    /* given */
    final char[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final char[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testEqualShortArrayShortArray()
  {
    /* given */
    final short[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final short[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
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
  public final void testEqualShortArrayShortArrayWithNotEqual()
  {
    /* given */
    final short[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final short[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 11 };
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
  public final void testEqualShortArrayShortArrayWithDifferentLength()
  {
    /* given */
    final short[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final short[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
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
  public final void testEqualShortArrayShortArrayWithNull()
  {
    /* given */
    final short[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final short[] actual = null;
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
  public final void testEqualShortArrayShortArrayWithBothNulls()
  {
    /* given */
    final short[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final short[] actual = null;
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
  public final void testNotEqualShortArrayShortArray()
  {
    /* given */
    final short[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final short[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 11 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualShortArrayShortArrayWithEqual()
  {
    /* given */
    final short[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final short[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testNotEqualShortArrayShortArrayWithDifferentLength()
  {
    /* given */
    final short[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final short[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualShortArrayShortArrayWithNull()
  {
    /* given */
    final short[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final short[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualShortArrayShortArrayWithBothNulls()
  {
    /* given */
    final short[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final short[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testEqualIntArrayIntArray()
  {
    /* given */
    final int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final int[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
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
  public final void testEqualIntArrayIntArrayWithNotEqual()
  {
    /* given */
    final int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final int[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 11 };
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
  public final void testEqualIntArrayIntArrayWithDifferentLength()
  {
    /* given */
    final int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final int[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
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
  public final void testEqualIntArrayIntArrayWithNull()
  {
    /* given */
    final int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final int[] actual = null;
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
  public final void testEqualIntArrayIntArrayWithBothNulls()
  {
    /* given */
    final int[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final int[] actual = null;
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
  public final void testNotEqualIntArrayIntArray()
  {/* given */
    final int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final int[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 11 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualIntArrayIntArrayWithEqual()
  {/* given */
    final int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final int[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testNotEqualIntArrayIntArrayWithDifferentLength()
  {/* given */
    final int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final int[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualIntArrayIntArrayWithNull()
  {/* given */
    final int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final int[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualIntArrayIntArrayWithBothNulls()
  {/* given */
    final int[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final int[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testEqualLongArrayLongArray()
  {
    /* given */
    final long[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final long[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
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
  public final void testEqualLongArrayLongArrayWithNotEqual()
  {
    /* given */
    final long[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final long[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 11 };
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
  public final void testEqualLongArrayLongArrayWithDifferentLength()
  {
    /* given */
    final long[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final long[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
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
  public final void testEqualLongArrayLongArrayWithNull()
  {
    /* given */
    final long[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final long[] actual = null;
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
  public final void testEqualLongArrayLongArrayWithBothNulls()
  {
    /* given */
    final long[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final long[] actual = null;
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
  public final void testNotEqualLongArrayLongArray()
  {
    /* given */
    final long[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final long[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 11 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualLongArrayLongArrayWithEqual()
  {
    /* given */
    final long[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final long[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testNotEqualLongArrayLongArrayWithDifferentLength()
  {
    /* given */
    final long[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final long[] actual = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualLongArrayLongArrayWithNull()
  {
    /* given */
    final long[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final long[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualLongArrayLongArrayWithBothNulls()
  {
    /* given */
    final long[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final long[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testEqualFloatArrayFloatArray()
  {
    /* given */
    final float[] expected = { 1.0F, 2.5F, 3.7F, 4.1F, 5, 6.45F, 7.123F, 8.01F, 9.09F, 10.11F };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final float[] actual = { 1.0F, 2.5F, 3.7F, 4.1F, 5, 6.45F, 7.123F, 8.01F, 9.09F, 10.11F };
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
  public final void testEqualFloatArrayFloatArrayWithNotEqual()
  {
    /* given */
    final float[] expected = { 1.0F, 2.5F, 3.7F, 4.1F, 5, 6.45F, 7.123F, 8.01F, 9.09F, 10.11F };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final float[] actual = { 1.0F, 2.5F, 3.7F, 4.1F, 5, 6.45F, 7.123F, 8.01F, 9.09F, 10.12F };
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
  public final void testEqualFloatArrayFloatArrayWithDifferentLength()
  {
    /* given */
    final float[] expected = { 1.0F, 2.5F, 3.7F, 4.1F, 5, 6.45F, 7.123F, 8.01F, 9.09F, 10.11F };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final float[] actual = { 1.0F, 2.5F, 3.7F, 4.1F, 5, 6.45F, 7.123F, 8.01F, 9.09F };
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
  public final void testEqualFloatArrayFloatArrayWithNull()
  {
    /* given */
    final float[] expected = { 1.0F, 2.5F, 3.7F, 4.1F, 5, 6.45F, 7.123F, 8.01F, 9.09F, 10.11F };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final float[] actual = null;
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
  public final void testEqualFloatArrayFloatArrayWithBothNulls()
  {
    /* given */
    final float[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final float[] actual = null;
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
  public final void testNotEqualFloatArrayFloatArray()
  {
    /* given */
    final float[] expected = { 1.0F, 2.5F, 3.7F, 4.1F, 5, 6.45F, 7.123F, 8.01F, 9.09F, 10.11F };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final float[] actual = { 1.0F, 2.5F, 3.7F, 4.1F, 5, 6.45F, 7.123F, 8.01F, 9.09F, 10.12F };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualFloatArrayFloatArrayWithEqual()
  {
    /* given */
    final float[] expected = { 1.0F, 2.5F, 3.7F, 4.1F, 5, 6.45F, 7.123F, 8.01F, 9.09F, 10.11F };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final float[] actual = { 1.0F, 2.5F, 3.7F, 4.1F, 5, 6.45F, 7.123F, 8.01F, 9.09F, 10.11F };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testNotEqualFloatArrayFloatArrayWithDifferentLength()
  {
    /* given */
    final float[] expected = { 1.0F, 2.5F, 3.7F, 4.1F, 5, 6.45F, 7.123F, 8.01F, 9.09F, 10.11F };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final float[] actual = { 1.0F, 2.5F, 3.7F, 4.1F, 5, 6.45F, 7.123F, 8.01F, 9.09F };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualFloatArrayFloatArrayWithNull()
  {
    /* given */
    final float[] expected = { 1.0F, 2.5F, 3.7F, 4.1F, 5, 6.45F, 7.123F, 8.01F, 9.09F, 10.11F };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final float[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualFloatArrayFloatArrayWithBothNulls()
  {
    /* given */
    final float[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final float[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testEqualDoubleArrayDoubleArray()
  {
    /* given */
    final double[] expected = { 1.0D, 2.5D, 3.7D, 4.1D, 5, 6.45D, 7.123D, 8.01D, 9.09D, 10.11D };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final double[] actual = { 1.0D, 2.5D, 3.7D, 4.1D, 5, 6.45D, 7.123D, 8.01D, 9.09D, 10.11D };
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
  public final void testEqualDoubleArrayDoubleArrayWithNotEqual()
  {
    /* given */
    final double[] expected = { 1.0D, 2.5D, 3.7D, 4.1D, 5, 6.45D, 7.123D, 8.01D, 9.09D, 10.11D };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final double[] actual = { 1.0D, 2.5D, 3.7D, 4.1D, 5, 6.45D, 7.123D, 8.01D, 9.09D, 10.12D };
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
  public final void testEqualDoubleArrayDoubleArrayWithDifferentLength()
  {
    /* given */
    final double[] expected = { 1.0D, 2.5D, 3.7D, 4.1D, 5, 6.45D, 7.123D, 8.01D, 9.09D, 10.11D };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final double[] actual = { 1.0D, 2.5D, 3.7D, 4.1D, 5, 6.45D, 7.123D, 8.01D, 9.09D };
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
  public final void testEqualDoubleArrayDoubleArrayWithNull()
  {
    /* given */
    final double[] expected = { 1.0D, 2.5D, 3.7D, 4.1D, 5, 6.45D, 7.123D, 8.01D, 9.09D, 10.11D };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final double[] actual = null;
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
  public final void testEqualDoubleArrayDoubleArrayWithBothNulls()
  {
    /* given */
    final double[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final double[] actual = null;
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
  public final void testNotEqualDoubleArrayDoubleArray()
  {
    /* given */
    final double[] expected = { 1.0D, 2.5D, 3.7D, 4.1D, 5, 6.45D, 7.123D, 8.01D, 9.09D, 10.11D };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final double[] actual = { 1.0D, 2.5D, 3.7D, 4.1D, 5, 6.45D, 7.123D, 8.01D, 9.09D, 10.12D };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualDoubleArrayDoubleArrayWithEqual()
  {
    /* given */
    final double[] expected = { 1.0D, 2.5D, 3.7D, 4.1D, 5, 6.45D, 7.123D, 8.01D, 9.09D, 10.11D };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final double[] actual = { 1.0D, 2.5D, 3.7D, 4.1D, 5, 6.45D, 7.123D, 8.01D, 9.09D, 10.11D };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testNotEqualDoubleArrayDoubleArrayWithDifferentLength()
  {
    /* given */
    final double[] expected = { 1.0D, 2.5D, 3.7D, 4.1D, 5, 6.45D, 7.123D, 8.01D, 9.09D, 10.11D };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final double[] actual = { 1.0D, 2.5D, 3.7D, 4.1D, 5, 6.45D, 7.123D, 8.01D, 9.09D };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualDoubleArrayDoubleArrayWithNull()
  {
    /* given */
    final double[] expected = { 1.0D, 2.5D, 3.7D, 4.1D, 5, 6.45D, 7.123D, 8.01D, 9.09D, 10.11D };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final double[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualDoubleArrayDoubleArrayWithBothNulls()
  {
    /* given */
    final double[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final double[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testEqualBooleanArrayBooleanArray()
  {
    /* given */
    final boolean[] expected = { true, true, true, false, true, false, false, true };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final boolean[] actual = { true, true, true, false, true, false, false, true };
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
  public final void testEqualBooleanArrayBooleanArrayWithNotEqual()
  {
    /* given */
    final boolean[] expected = { true, true, true, false, true, false, false, true };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final boolean[] actual = { true, true, true, false, true, false, false, false };
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
  public final void testEqualBooleanArrayBooleanArrayWithDifferentLength()
  {
    /* given */
    final boolean[] expected = { true, true, true, false, true, false, false, true };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final boolean[] actual = { true, true, true, false, true, false, false };
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
  public final void testEqualBooleanArrayBooleanArrayWithNull()
  {
    /* given */
    final boolean[] expected = { true, true, true, false, true, false, false, true };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final boolean[] actual = null;
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
  public final void testEqualBooleanArrayBooleanArrayWithBothNulls()
  {
    /* given */
    final boolean[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final boolean[] actual = null;
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
  public final void testNotEqualBooleanArrayBooleanArray()
  {
    /* given */
    final boolean[] expected = { true, true, true, false, true, false, false, true };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final boolean[] actual = { true, true, true, false, true, false, false, false };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualBooleanArrayBooleanArrayWithEqual()
  {
    /* given */
    final boolean[] expected = { true, true, true, false, true, false, false, true };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final boolean[] actual = { true, true, true, false, true, false, false, true };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testNotEqualBooleanArrayBooleanArrayWithDifferentLength()
  {
    /* given */
    final boolean[] expected = { true, true, true, false, true, false, false, true };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final boolean[] actual = { true, true, true, false, true, false, false };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualBooleanArrayBooleanArrayWithNull()
  {
    /* given */
    final boolean[] expected = { true, true, true, false, true, false, false, true };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final boolean[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualBooleanArrayBooleanArrayWithBothNulls()
  {
    /* given */
    final boolean[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final boolean[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  private static class TestObject
  {
    final int id;

    TestObject(final int id)
    {
      this.id = id;
    }

    @Override
    public int hashCode()
    {
      return id;
    }

    @Override
    public boolean equals(final Object obj)
    {
      if (this == obj)
        return true;
      if (!(obj instanceof TestObject))
        return false;
      final TestObject that = (TestObject) obj;
      return this.id == that.id;
    }

    @Override
    public String toString()
    {
      /* @formatter:off */
			return new StringBuilder(getClass()
									.getSimpleName())
					.append("{")
					.append("id=")
					.append(id)
					.append("}")
					.toString();
			/* @formatter:on */
    }
  }

  @Test
  public final void testEqualObjectArrayObjectArray()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5),
          new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5),
          new TestObject(6), new TestObject(7) };
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
  public final void testEqualObjectArrayObjectArrayWithNotEqual()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5),
          new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5),
          new TestObject(6), new TestObject(8) };
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
  public final void testEqualObjectArrayObjectArrayWithNestedArray()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
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
  public final void testEqualObjectArrayObjectArrayWithDifferentLength()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5),
          new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5),
          new TestObject(6) };
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
  public final void testEqualObjectArrayObjectArrayWithNull()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5),
          new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = null;
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
  public final void testEqualObjectArrayObjectArrayWithBothNulls()
  {
    /* given */
    final Object[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = null;
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
  public final void testNotEqualObjectArrayObjectArray()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5),
          new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5),
          new TestObject(6), new TestObject(8) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualObjectArrayObjectArrayWithEqual()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5),
          new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5),
          new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testNotEqualObjectArrayObjectArrayWithNestedArray()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualObjectArrayObjectArrayWithDifferentLength()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5),
          new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5),
          new TestObject(6) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualObjectArrayObjectArrayWithNull()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject(2), new TestObject(3), new TestObject(4), new TestObject(5),
          new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotEqualObjectArrayObjectArrayWithBothNulls()
  {
    /* given */
    final Object[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testDeepEqual()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testDeepEqualWithPrimitives()
  {
    /* given */
    final int[] expected = { 1, 2, 3, 4, 5, 6, 7 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final int[] actual = { 1, 2, 3, 4, 5, 6, 7 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testDeepEqualWithPrimitives2()
  {
    /* given */
    final boolean[] expected = { true, true, false, true, false, false, true, false };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final boolean[] actual = { true, true, false, true, false, false, true, false };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testDeepEqualWithNotEqual()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(5) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testDeepEqualWithDifferentLength()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testDeepEqualWithDifferentLength2()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3) }, new TestObject(5),
          new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testDeepEqualWithNull()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testDeepEqualWithNull2()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = { new TestObject(1), null, new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testDeepEqualWithBothNulls()
  {
    /* given */
    final Object[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testDeepEqualWithBothNulls2()
  {
    /* given */
    final Object[] expected = { new TestObject(1), null, new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = { new TestObject(1), null, new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotDeepEqual()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(5) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotDeepEqualWithPrimitives()
  {
    /* given */
    final int[] expected = { 1, 2, 3, 4, 5, 6, 7 };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final int[] actual = { 1, 2, 3, 4, 5, 6, 8 };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotDeepEqualWithPrimitives2()
  {
    /* given */
    final boolean[] expected = { true, true, false, true, false, false, true, false };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final boolean[] actual = { true, true, false, true, false, false, true, true };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotDeepEqualWithEqual()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testNotDeepEqualWithDifferentLength()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotDeepEqualWithDifferentLength2()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3) }, new TestObject(5),
          new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotDeepEqualWithNull()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = { new TestObject(1), null, new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotDeepEqualWithNull2()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotDeepEqualWithBothNulls()
  {
    /* given */
    final Object[] expected = { new TestObject(1), null, new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = { new TestObject(1), null, new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testNotDeepEqualWithBothNulls2()
  {
    /* given */
    final Object[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqual(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testDeepEqualArray()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testDeepEqualArrayWithNotEqual()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(5) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testDeepEqualArrayWithDifferentLength()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testDeepEqualArrayWithDifferentLength2()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3) }, new TestObject(5),
          new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testDeepEqualArrayWithNull()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testDeepEqualArrayWithNull2()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = { new TestObject(1), null, new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testDeepEqualArrayWithBothNulls()
  {
    /* given */
    final Object[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testDeepEqualArrayWithBothNulls2()
  {
    /* given */
    final Object[] expected = { new TestObject(1), null, new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = { new TestObject(1), null, new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.deepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotDeepEqualArray()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(5) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotDeepEqualArrayWithEqual()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testNotDeepEqualArrayWithDifferentLength()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotDeepEqualArrayWithDifferentLength2()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3) }, new TestObject(5),
          new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotDeepEqualArrayWithNull()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = { new TestObject(1), null, new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotDeepEqualArrayWithNull2()
  {
    /* given */
    final Object[] expected =
      { new TestObject(1), new TestObject[] { new TestObject(2), new TestObject(3), new TestObject(4) },
          new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.TRUE)));
  }

  @Test
  public final void testNotDeepEqualArrayWithBothNulls()
  {
    /* given */
    final Object[] expected = { new TestObject(1), null, new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = { new TestObject(1), null, new TestObject(5), new TestObject(6), new TestObject(7) };
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public final void testNotDeepEqualArrayWithBothNulls2()
  {
    /* given */
    final Object[] expected = null;
    System.out.println("\nexpected:");
    System.out.println(toStringOf(expected));
    final Object[] actual = null;
    System.out.println("\nactual:");
    System.out.println(toStringOf(actual));

    /* when */
    System.out.println("result:");
    @SuppressWarnings("boxing")
    final Boolean result = NeoArrays.notDeepEqualArray(expected, actual);
    System.out.println(result);

    /* then */
    assertThat(result, is(equalTo(Boolean.FALSE)));
  }

  @Test
  public void testConvertToPrimitiveByteWithEmptyByteArray()
  {
    /* given */
    final byte[] expected = new byte[0];
    final Byte[] array = new Byte[0];

    /* when */
    final byte[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveByte1()
  {
    /* given */
    final byte[] expected = new byte[] { -1 };
    @SuppressWarnings("boxing")
    final Byte[] array = new Byte[] { -1 };

    /* when */
    final byte[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveByte2()
  {
    /* given */
    final byte[] expected = new byte[] { 0 };
    @SuppressWarnings("boxing")
    final Byte[] array = new Byte[] { 0 };

    /* when */
    final byte[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveByte3()
  {
    /* given */
    final byte[] expected = new byte[] { 1 };
    @SuppressWarnings("boxing")
    final Byte[] array = new Byte[] { 1 };

    /* when */
    final byte[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveByte4()
  {
    /* given */
    final byte[] expected = new byte[] { -1, 0, 1 };
    @SuppressWarnings("boxing")
    final Byte[] array = new Byte[] { -1, 0, 1 };

    /* when */
    final byte[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveByte5()
  {
    /* given */
    final byte[] expected = new byte[] { -100, -50, -10, -5, -1, 0, 1, 5, 10, 50, 100 };
    @SuppressWarnings("boxing")
    final Byte[] array = new Byte[] { -100, -50, -10, -5, -1, 0, 1, 5, 10, 50, 100 };

    /* when */
    final byte[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveByteWithEmptyByteArray()
  {
    /* given */
    final Byte[] expected = new Byte[0];
    final byte[] array = new byte[0];

    /* when */
    final Byte[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveByte1()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Byte[] expected = new Byte[] { -1 };
    final byte[] array = new byte[] { -1 };

    /* when */
    final Byte[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveByte2()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Byte[] expected = new Byte[] { 0 };
    final byte[] array = new byte[] { 0 };

    /* when */
    final Byte[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveByte3()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Byte[] expected = new Byte[] { 1 };
    final byte[] array = new byte[] { 1 };

    /* when */
    final Byte[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveByte4()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Byte[] expected = new Byte[] { -1, 0, 1 };
    final byte[] array = new byte[] { -1, 0, 1 };

    /* when */
    final Byte[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveByte5()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Byte[] expected = new Byte[] { -100, -50, -10, -5, -1, 0, 1, 5, 10, 50, 100 };
    final byte[] array = new byte[] { -100, -50, -10, -5, -1, 0, 1, 5, 10, 50, 100 };

    /* when */
    final Byte[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveCharWithEmptyCharacterArray()
  {
    /* given */
    final char[] expected = new char[0];
    final Character[] array = new Character[0];

    /* when */
    final char[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveChar1()
  {
    /* given */
    final char[] expected = new char[] { 'a' };
    @SuppressWarnings("boxing")
    final Character[] array = new Character[] { 'a' };

    /* when */
    final char[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveChar2()
  {
    /* given */
    final char[] expected = new char[] { 'a', '1' };
    @SuppressWarnings("boxing")
    final Character[] array = new Character[] { 'a', '1' };

    /* when */
    final char[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveChar3()
  {
    /* given */
    final char[] expected = new char[] { 'a', 'b', '1', '2' };
    @SuppressWarnings("boxing")
    final Character[] array = new Character[] { 'a', 'b', '1', '2' };

    /* when */
    final char[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveCharacterWithEmptyCharArray()
  {
    /* given */
    final Character[] expected = new Character[0];
    final char[] array = new char[0];

    /* when */
    final Character[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveCharacter1()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Character[] expected = new Character[] { 'a' };
    final char[] array = new char[] { 'a' };

    /* when */
    final Character[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveCharacter2()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Character[] expected = new Character[] { 'a', '1' };
    final char[] array = new char[] { 'a', '1' };

    /* when */
    final Character[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveCharacter3()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Character[] expected = new Character[] { 'a', 'b', '1', '2' };
    final char[] array = new char[] { 'a', 'b', '1', '2' };

    /* when */
    final Character[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveShortWithEmptyShortArray()
  {
    /* given */
    final short[] expected = new short[0];
    final Short[] array = new Short[0];

    /* when */
    final short[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveShort1()
  {
    /* given */
    final short[] expected = new short[] { -1 };
    @SuppressWarnings("boxing")
    final Short[] array = new Short[] { -1 };

    /* when */
    final short[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveShort2()
  {
    /* given */
    final short[] expected = new short[] { 0 };
    @SuppressWarnings("boxing")
    final Short[] array = new Short[] { 0 };

    /* when */
    final short[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveShort3()
  {
    /* given */
    final short[] expected = new short[] { 1 };
    @SuppressWarnings("boxing")
    final Short[] array = new Short[] { 1 };

    /* when */
    final short[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveShort4()
  {
    /* given */
    final short[] expected = new short[] { -1, 0, 1 };
    @SuppressWarnings("boxing")
    final Short[] array = new Short[] { -1, 0, 1 };

    /* when */
    final short[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveShort5()
  {
    /* given */
    final short[] expected = new short[] { -999, -100, -50, -10, -5, -1, 0, 1, 5, 10, 50, 100, 999 };
    @SuppressWarnings("boxing")
    final Short[] array = new Short[] { -999, -100, -50, -10, -5, -1, 0, 1, 5, 10, 50, 100, 999 };

    /* when */
    final short[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveShortWithEmptyShortArray()
  {
    /* given */
    final Short[] expected = new Short[0];
    final short[] array = new short[0];

    /* when */
    final Short[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveShort1()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Short[] expected = new Short[] { -1 };
    final short[] array = new short[] { -1 };

    /* when */
    final Short[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveShort2()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Short[] expected = new Short[] { 0 };
    final short[] array = new short[] { 0 };

    /* when */
    final Short[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveShort3()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Short[] expected = new Short[] { 1 };
    final short[] array = new short[] { 1 };

    /* when */
    final Short[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveShort4()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Short[] expected = new Short[] { -1, 0, 1 };
    final short[] array = new short[] { -1, 0, 1 };

    /* when */
    final Short[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveShort5()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Short[] expected = new Short[] { -999, -100, -50, -10, -5, -1, 0, 1, 5, 10, 50, 100, 999 };
    final short[] array = new short[] { -999, -100, -50, -10, -5, -1, 0, 1, 5, 10, 50, 100, 999 };

    /* when */
    final Short[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveIntWithEmptyIntegerArray()
  {
    /* given */
    final int[] expected = new int[0];
    final Integer[] array = new Integer[0];

    /* when */
    final int[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveInt1()
  {
    /* given */
    final int[] expected = new int[] { -1 };
    @SuppressWarnings("boxing")
    final Integer[] array = new Integer[] { -1 };

    /* when */
    final int[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveInt2()
  {
    /* given */
    final int[] expected = new int[] { 0 };
    @SuppressWarnings("boxing")
    final Integer[] array = new Integer[] { 0 };

    /* when */
    final int[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveInt3()
  {
    /* given */
    final int[] expected = new int[] { 1 };
    @SuppressWarnings("boxing")
    final Integer[] array = new Integer[] { 1 };

    /* when */
    final int[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveInt4()
  {
    /* given */
    final int[] expected = new int[] { -1, 0, 1 };
    @SuppressWarnings("boxing")
    final Integer[] array = new Integer[] { -1, 0, 1 };

    /* when */
    final int[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveInt5()
  {
    /* given */
    final int[] expected =
      new int[] { Integer.MIN_VALUE, -999, -100, -50, -10, -5, -1, 0, 1, 5, 10, 50, 100, 999, Integer.MAX_VALUE };
    @SuppressWarnings("boxing")
    final Integer[] array =
      new Integer[] { Integer.MIN_VALUE, -999, -100, -50, -10, -5, -1, 0, 1, 5, 10, 50, 100, 999, Integer.MAX_VALUE };

    /* when */
    final int[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveIntegerWithEmptyIntArray()
  {
    /* given */
    final Integer[] expected = new Integer[0];
    final int[] array = new int[0];

    /* when */
    final Integer[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveInteger1()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Integer[] expected = new Integer[] { -1 };
    final int[] array = new int[] { -1 };

    /* when */
    final Integer[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveInteger2()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Integer[] expected = new Integer[] { 0 };
    final int[] array = new int[] { 0 };

    /* when */
    final Integer[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveInteger3()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Integer[] expected = new Integer[] { 1 };
    final int[] array = new int[] { 1 };

    /* when */
    final Integer[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveInteger4()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Integer[] expected = new Integer[] { -1, 0, 1 };
    final int[] array = new int[] { -1, 0, 1 };

    /* when */
    final Integer[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveInteger5()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Integer[] expected =
      new Integer[] { Integer.MIN_VALUE, -999, -100, -50, -10, -5, -1, 0, 1, 5, 10, 50, 100, 999, Integer.MAX_VALUE };
    final int[] array =
      new int[] { Integer.MIN_VALUE, -999, -100, -50, -10, -5, -1, 0, 1, 5, 10, 50, 100, 999, Integer.MAX_VALUE };

    /* when */
    final Integer[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveLongWithEmptyLongArray()
  {
    /* given */
    final long[] expected = new long[0];
    final Long[] array = new Long[0];

    /* when */
    final long[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveLong1()
  {
    /* given */
    final long[] expected = new long[] { -1L };
    @SuppressWarnings("boxing")
    final Long[] array = new Long[] { -1L };

    /* when */
    final long[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveLong2()
  {
    /* given */
    final long[] expected = new long[] { 0L };
    @SuppressWarnings("boxing")
    final Long[] array = new Long[] { 0L };

    /* when */
    final long[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveLong3()
  {
    /* given */
    final long[] expected = new long[] { 1L };
    @SuppressWarnings("boxing")
    final Long[] array = new Long[] { 1L };

    /* when */
    final long[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveLong4()
  {
    /* given */
    final long[] expected = new long[] { -1L, 0L, 1L };
    @SuppressWarnings("boxing")
    final Long[] array = new Long[] { -1L, 0L, 1L };

    /* when */
    final long[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveLong5()
  {
    /* given */
    final long[] expected =
      new long[] { Long.MIN_VALUE, Integer.MIN_VALUE, -999L, -100L, -50L, -10L, -5L, -1L, 0L, 1L, 5L, 10L, 50L, 100L,
          999L, Integer.MAX_VALUE, Long.MAX_VALUE };
    @SuppressWarnings("boxing")
    final Long[] array =
      new Long[] { Long.MIN_VALUE, (long) Integer.MIN_VALUE, -999L, -100L, -50L, -10L, -5L, -1L, 0L, 1L, 5L, 10L, 50L,
          100L, 999L, (long) Integer.MAX_VALUE, Long.MAX_VALUE };

    /* when */
    final long[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveLongWithEmptyLongArray()
  {
    /* given */
    final Long[] expected = new Long[0];
    final long[] array = new long[0];

    /* when */
    final Long[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveLong1()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Long[] expected = new Long[] { -1L };
    final long[] array = new long[] { -1L };

    /* when */
    final Long[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveLong2()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Long[] expected = new Long[] { 0L };
    final long[] array = new long[] { 0L };

    /* when */
    final Long[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveLong3()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Long[] expected = new Long[] { 1L };
    final long[] array = new long[] { 1L };

    /* when */
    final Long[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveLong4()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Long[] expected = new Long[] { -1L, 0L, 1L };
    final long[] array = new long[] { -1L, 0L, 1L };

    /* when */
    final Long[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveLong5()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Long[] expected =
      new Long[] { Long.MIN_VALUE, (long) Integer.MIN_VALUE, -999L, -100L, -50L, -10L, -5L, -1L, 0L, 1L, 5L, 10L, 50L,
          100L, 999L, (long) Integer.MAX_VALUE, Long.MAX_VALUE };
    final long[] array =
      new long[] { Long.MIN_VALUE, Integer.MIN_VALUE, -999L, -100L, -50L, -10L, -5L, -1L, 0L, 1L, 5L, 10L, 50L, 100L,
          999L, Integer.MAX_VALUE, Long.MAX_VALUE };

    /* when */
    final Long[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveFloatWithEmptyFloatArray()
  {
    /* given */
    final float[] expected = new float[0];
    final Float[] array = new Float[0];

    /* when */
    final float[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveFloat1()
  {
    /* given */
    final float[] expected = new float[] { -1.0F };
    @SuppressWarnings("boxing")
    final Float[] array = new Float[] { -1.0F };

    /* when */
    final float[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveFloat2()
  {
    /* given */
    final float[] expected = new float[] { 0.0F };
    @SuppressWarnings("boxing")
    final Float[] array = new Float[] { 0.0F };

    /* when */
    final float[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveFloat3()
  {
    /* given */
    final float[] expected = new float[] { 1.0F };
    @SuppressWarnings("boxing")
    final Float[] array = new Float[] { 1.0F };

    /* when */
    final float[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveFloat4()
  {
    /* given */
    final float[] expected = new float[] { -1.0F, 0.0F, 1.0F };
    @SuppressWarnings("boxing")
    final Float[] array = new Float[] { -1.0F, 0.0F, 1.0F };

    /* when */
    final float[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveFloat5()
  {
    /* given */
    final float[] expected =
      new float[] { Float.MIN_VALUE, -999.0F, -100.0F, -50.0F, -10.0F, -5.0F, -1.0F, 0.0F, 1.0F, 5.0F, 10.0F, 50.0F,
          100.0F, 999.0F, Float.MAX_VALUE };
    @SuppressWarnings("boxing")
    final Float[] array =
      new Float[] { Float.MIN_VALUE, -999.0F, -100.0F, -50.0F, -10.0F, -5.0F, -1.0F, 0.0F, 1.0F, 5.0F, 10.0F, 50.0F,
          100.0F, 999.0F, Float.MAX_VALUE };

    /* when */
    final float[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveFloatWithEmptyFloatArray()
  {
    /* given */
    final Float[] expected = new Float[0];
    final float[] array = new float[0];

    /* when */
    final Float[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveFloat1()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Float[] expected = new Float[] { -1.0F };
    final float[] array = new float[] { -1.0F };

    /* when */
    final Float[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveFloat2()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Float[] expected = new Float[] { 0.0F };
    final float[] array = new float[] { 0.0F };

    /* when */
    final Float[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveFloat3()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Float[] expected = new Float[] { 1.0F };
    final float[] array = new float[] { 1.0F };

    /* when */
    final Float[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveFloat4()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Float[] expected = new Float[] { -1.0F, 0.0F, 1.0F };
    final float[] array = new float[] { -1.0F, 0.0F, 1.0F };

    /* when */
    final Float[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveFloat5()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Float[] expected =
      new Float[] { Float.MIN_VALUE, -999.0F, -100.0F, -50.0F, -10.0F, -5.0F, -1.0F, 0.0F, 1.0F, 5.0F, 10.0F, 50.0F,
          100.0F, 999.0F, Float.MAX_VALUE };
    final float[] array =
      new float[] { Float.MIN_VALUE, -999.0F, -100.0F, -50.0F, -10.0F, -5.0F, -1.0F, 0.0F, 1.0F, 5.0F, 10.0F, 50.0F,
          100.0F, 999.0F, Float.MAX_VALUE };

    /* when */
    final Float[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveDoubleWithEmptyDoubleArray()
  {
    /* given */
    final double[] expected = new double[0];
    final Double[] array = new Double[0];

    /* when */
    final double[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveDouble1()
  {
    /* given */
    final double[] expected = new double[] { -1.0F };
    @SuppressWarnings("boxing")
    final Double[] array = new Double[] { -1.0D };

    /* when */
    final double[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveDouble2()
  {
    /* given */
    final double[] expected = new double[] { 0.0D };
    @SuppressWarnings("boxing")
    final Double[] array = new Double[] { 0.0D };

    /* when */
    final double[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveDouble3()
  {
    /* given */
    final double[] expected = new double[] { 1.0D };
    @SuppressWarnings("boxing")
    final Double[] array = new Double[] { 1.0D };

    /* when */
    final double[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveDouble4()
  {
    /* given */
    final double[] expected = new double[] { -1.0D, 0.0D, 1.0D };
    @SuppressWarnings("boxing")
    final Double[] array = new Double[] { -1.0D, 0.0D, 1.0D };

    /* when */
    final double[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveDouble5()
  {
    /* given */
    final double[] expected =
      new double[] { Double.MIN_VALUE, Float.MIN_VALUE, -999.0D, -100.0D, -50.0D, -10.0D, -5.0D, -1.0D, 0.0D, 1.0D,
          5.0D, 10.0D, 50.0D, 100.0D, 999.0D, Float.MAX_VALUE, Double.MAX_VALUE };
    @SuppressWarnings("boxing")
    final Double[] array =
      new Double[] { Double.MIN_VALUE, (double) Float.MIN_VALUE, -999.0D, -100.0D, -50.0D, -10.0D, -5.0D, -1.0D, 0.0D,
          1.0D, 5.0D, 10.0D, 50.0D, 100.0D, 999.0D, (double) Float.MAX_VALUE, Double.MAX_VALUE };

    /* when */
    final double[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveDoubleWithEmptyDoubleArray()
  {
    /* given */
    final Double[] expected = new Double[0];
    final double[] array = new double[0];

    /* when */
    final Double[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveDouble1()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Double[] expected = new Double[] { -1.0D };
    final double[] array = new double[] { -1.0D };

    /* when */
    final Double[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveDouble2()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Double[] expected = new Double[] { 0.0D };
    final double[] array = new double[] { 0.0D };

    /* when */
    final Double[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveDouble3()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Double[] expected = new Double[] { 1.0D };
    final double[] array = new double[] { 1.0D };

    /* when */
    final Double[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveDouble4()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Double[] expected = new Double[] { -1.0D, 0.0D, 1.0D };
    final double[] array = new double[] { -1.0D, 0.0D, 1.0D };

    /* when */
    final Double[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveDouble5()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Double[] expected =
      new Double[] { Double.MIN_VALUE, (double) Float.MIN_VALUE, -999.0D, -100.0D, -50.0D, -10.0D, -5.0D, -1.0D, 0.0D,
          1.0D, 5.0D, 10.0D, 50.0D, 100.0D, 999.0D, (double) Float.MAX_VALUE, Double.MAX_VALUE };
    final double[] array =
      new double[] { Double.MIN_VALUE, Float.MIN_VALUE, -999.0D, -100.0D, -50.0D, -10.0D, -5.0D, -1.0D, 0.0D, 1.0D,
          5.0D, 10.0D, 50.0D, 100.0D, 999.0D, Float.MAX_VALUE, Double.MAX_VALUE };

    /* when */
    final Double[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveBooleanWithEmptyBooleanArray()
  {
    /* given */
    final boolean[] expected = new boolean[0];
    final Boolean[] array = new Boolean[0];

    /* when */
    final boolean[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveBoolean1()
  {
    /* given */
    final boolean[] expected = new boolean[] { false };
    @SuppressWarnings("boxing")
    final Boolean[] array = new Boolean[] { false };

    /* when */
    final boolean[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveBoolean2()
  {
    /* given */
    final boolean[] expected = new boolean[] { true };
    @SuppressWarnings("boxing")
    final Boolean[] array = new Boolean[] { true };

    /* when */
    final boolean[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveBoolean3()
  {
    /* given */
    final boolean[] expected = new boolean[] { false, true };
    @SuppressWarnings("boxing")
    final Boolean[] array = new Boolean[] { false, true };

    /* when */
    final boolean[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveBoolean4()
  {
    /* given */
    final boolean[] expected = new boolean[] { false, true, false };
    @SuppressWarnings("boxing")
    final Boolean[] array = new Boolean[] { false, true, false };

    /* when */
    final boolean[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToPrimitiveBoolean5()
  {
    /* given */
    final boolean[] expected = new boolean[] { false, true, false, true };
    @SuppressWarnings("boxing")
    final Boolean[] array = new Boolean[] { false, true, false, true };

    /* when */
    final boolean[] actual = NeoArrays.convertToPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveBooleanWithEmptyBooleanArray()
  {
    /* given */
    final Boolean[] expected = new Boolean[0];
    final boolean[] array = new boolean[0];

    /* when */
    final Boolean[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveBoolean1()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Boolean[] expected = new Boolean[] { false };
    final boolean[] array = new boolean[] { false };

    /* when */
    final Boolean[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveBoolean2()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Boolean[] expected = new Boolean[] { true };
    final boolean[] array = new boolean[] { true };

    /* when */
    final Boolean[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveBoolean3()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Boolean[] expected = new Boolean[] { false, true };
    final boolean[] array = new boolean[] { false, true };

    /* when */
    final Boolean[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveBoolean4()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Boolean[] expected = new Boolean[] { false, true, false };
    final boolean[] array = new boolean[] { false, true, false };

    /* when */
    final Boolean[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testConvertToBoxedPrimitiveBoolean5()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Boolean[] expected = new Boolean[] { false, true, false, true };
    final boolean[] array = new boolean[] { false, true, false, true };

    /* when */
    final Boolean[] actual = NeoArrays.convertToBoxedPrimitive(array);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

}
