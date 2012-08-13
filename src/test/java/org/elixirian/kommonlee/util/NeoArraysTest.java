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

		TestObject(int id)
		{
			this.id = id;
		}

		@Override
		public int hashCode()
		{
			return id;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (!(obj instanceof TestObject))
				return false;
			TestObject that = (TestObject) obj;
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
		;
		System.out.println("\nexpected:");
		System.out.println(toStringOf(expected));
		final Object[] actual = { new TestObject(1), null, new TestObject(5), new TestObject(6), new TestObject(7) };
		;
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
}
