/**
 * 
 */
package org.elixirian.common.util.number;

import static java.util.Arrays.*;
import static org.elixirian.common.util.number.LongsTotal.*;
import static org.elixirian.common.util.number.Numbers.*;
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2011-03-28)
 */
public class LongsTotalTest
{
	private static final int HOW_MANY = 30;

	/* @formatter:off */
	private static final long[] NUMBERS1 = { Integer.MIN_VALUE, -5,   -4,  -3,  -2,  -1,  Integer.MAX_VALUE,
												 0,  1,  2,  3,  4,  5,  Integer.MAX_VALUE };

	private static final long[] NUMBERS2 = { Integer.MIN_VALUE, -100, -90, -80, -70, -60, Integer.MAX_VALUE,
												 10, 20, 30, 40, 50, 60, Integer.MAX_VALUE };
	/* @formatter:on */

	private static final Random random = new Random();
	private static final long[] NUMBERS3 = getRandomNumbers(50);
	private static final long[] NUMBERS4 = getRandomNumbers(50);

	private static long[] getRandomNumbers(final int length)
	{
		final long[] numbers = new long[length];
		for (int i = 0; i < length; i++)
		{
			numbers[i] = random.nextLong();
		}
		return numbers;
	}

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

	@Test
	public final void testTotalLongLong()
	{
		final long[] numbers1 = NUMBERS1;
		final long[] numbers2 = NUMBERS2;

		for (final long n1 : numbers1)
		{
			for (final long n2 : numbers2)
			{
				final long expected = n1 + n2;
				final long actual = total(n1, n2);
				// System.out.println("expected: " + expected + "\nactual:   " + actual);
				assertTrue(expected == actual);
			}
		}

		final long[] numbers3 = NUMBERS3;
		final long[] numbers4 = NUMBERS4;

		for (final long n1 : numbers3)
		{
			for (final long n2 : numbers4)
			{
				final long expected = n1 + n2;
				final long actual = total(n1, n2);
				// System.out.println("expected: " + expected + "\nactual:   " + actual);
				assertTrue(expected == actual);
			}
		}
	}

	@Test
	public final void testTotalLongLongLong()
	{
		final long[] numbers1 = NUMBERS1;
		final long[] numbers2 = NUMBERS2;

		for (final long n1 : numbers1)
		{
			for (final long n2 : numbers2)
			{
				for (final long n3 : numbers1)
				{
					final long expected = n1 + n2 + n3;
					final long actual = total(n1, n2, n3);
					// System.out.println("expected: " + expected + "\nactual:   " + actual);
					assertTrue(expected == actual);
				}
			}
		}

		final long[] numbers3 = NUMBERS3;
		final long[] numbers4 = NUMBERS4;

		for (final long n1 : numbers3)
		{
			for (final long n2 : numbers4)
			{
				for (final long n3 : numbers3)
				{
					final long expected = n1 + n2 + n3;
					final long actual = total(n1, n2, n3);
					// System.out.println("expected: " + expected + "\nactual:   " + actual);
					assertTrue(expected == actual);
				}
			}
		}
	}

	@Test
	public final void testTotalLongLongLongLong()
	{
		final long[] numbers1 = NUMBERS1;
		final long[] numbers2 = NUMBERS2;

		for (final long n1 : numbers1)
		{
			for (final long n2 : numbers2)
			{
				for (final long n3 : numbers1)
				{
					for (final long n4 : numbers2)
					{
						final long expected = n1 + n2 + n3 + n4;
						final long actual = total(n1, n2, n3, n4);
						// System.out.println("expected: " + expected + "\nactual:   " + actual);
						assertTrue(expected == actual);
					}
				}
			}
		}

		final long[] numbers3 = NUMBERS3;
		final long[] numbers4 = NUMBERS4;

		for (final long n1 : numbers3)
		{
			for (final long n2 : numbers4)
			{
				for (final long n3 : numbers3)
				{
					for (final long n4 : numbers4)
					{
						final long expected = n1 + n2 + n3 + n4;
						final long actual = total(n1, n2, n3, n4);
						// System.out.println("expected: " + expected + "\nactual:   " + actual);
						assertTrue(expected == actual);
					}
				}
			}
		}
	}

	@Test
	public final void testTotalLongLongLongLongLong()
	{
		final long[] numbers1 = NUMBERS1;
		final long[] numbers2 = NUMBERS2;

		for (final long n1 : numbers1)
		{
			for (final long n2 : numbers2)
			{
				for (final long n3 : numbers1)
				{
					for (final long n4 : numbers2)
					{
						for (final long n5 : numbers1)
						{
							final long expected = n1 + n2 + n3 + n4 + n5;
							final long actual = total(n1, n2, n3, n4, n5);
							// System.out.println("expected: " + expected + "\nactual:   " + actual);
							assertTrue(expected == actual);
						}
					}
				}
			}
		}

		final long[] numbers3 = NUMBERS3;
		final long[] numbers4 = NUMBERS4;

		for (final long n1 : numbers3)
		{
			for (final long n2 : numbers4)
			{
				for (final long n3 : numbers3)
				{
					for (final long n4 : numbers4)
					{
						for (final long n5 : numbers3)
						{
							final long expected = n1 + n2 + n3 + n4 + n5;
							final long actual = total(n1, n2, n3, n4, n5);
							// System.out.println("expected: " + expected + "\nactual:   " + actual);
							assertTrue(expected == actual);
						}
					}
				}
			}
		}
	}

	@Test
	public final void testTotalLongLongLongLongLongLongArray()
	{
		final long[] numbers1 = NUMBERS1;
		final long[] numbers2 = NUMBERS2;

		long total1 = 0;
		for (final long n : numbers1)
			total1 += n;

		long total2 = 0;
		for (final long n : numbers2)
			total2 += n;

		for (final long n1 : numbers1)
		{
			for (final long n2 : numbers2)
			{
				for (final long n3 : numbers1)
				{
					for (final long n4 : numbers2)
					{
						for (final long n5 : numbers1)
						{
							final long expected1 = n1 + n2 + n3 + n4 + n5 + total1;
							final long actual1 = total(n1, n2, n3, n4, n5, numbers1);
							// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
							assertTrue(expected1 == actual1);
							final long expected2 = n1 + n2 + n3 + n4 + n5 + total2;
							final long actual2 = total(n1, n2, n3, n4, n5, numbers2);
							// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
							assertTrue(expected2 == actual2);
						}
					}
				}
			}
		}

		final long[] numbers3 = new long[HOW_MANY];
		System.arraycopy(NUMBERS3, 0, numbers3, 0, HOW_MANY);
		final long[] numbers4 = new long[HOW_MANY];
		System.arraycopy(NUMBERS4, 0, numbers4, 0, HOW_MANY);

		long total3 = 0;
		for (final long n : numbers3)
			total3 += n;

		long total4 = 0;
		for (final long n : numbers4)
			total4 += n;

		for (final long n1 : numbers3)
		{
			for (final long n2 : numbers4)
			{
				for (final long n3 : numbers3)
				{
					for (final long n4 : numbers4)
					{
						for (final long n5 : numbers3)
						{
							final long expected3 = n1 + n2 + n3 + n4 + n5 + total3;
							final long actual3 = total(n1, n2, n3, n4, n5, numbers3);
							// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
							assertTrue(expected3 == actual3);

							final long expected4 = n1 + n2 + n3 + n4 + n5 + total4;
							final long actual4 = total(n1, n2, n3, n4, n5, numbers4);
							// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
							assertTrue(expected4 == actual4);
						}
					}
				}
			}
		}
	}

	@Test
	public final void testTotalLongArray()
	{
		final long[] numbers1 = NUMBERS1;
		final long[] numbers2 = NUMBERS2;

		long total1 = 0;
		for (final long n : numbers1)
			total1 += n;

		long total2 = 0;
		for (final long n : numbers2)
			total2 += n;

		final long expected1 = total1;
		final long actual1 = total(numbers1);
		// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
		assertTrue(expected1 == actual1);
		final long expected2 = total2;
		final long actual2 = total(numbers2);
		// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
		assertTrue(expected2 == actual2);

		final long[] numbers3 = NUMBERS3;
		final long[] numbers4 = NUMBERS4;

		long total3 = 0;
		for (final long n : numbers3)
			total3 += n;

		long total4 = 0;
		for (final long n : numbers4)
			total4 += n;

		final long expected3 = total3;
		final long actual3 = total(numbers3);
		// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
		assertTrue(expected3 == actual3);

		final long expected4 = total4;
		final long actual4 = total(numbers4);
		// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
		assertTrue(expected4 == actual4);
	}

	@Test
	public final void testTotalBoxedLongBoxedLong()
	{
		final Long[] numbers1 = convertToBoxed(NUMBERS1);
		final Long[] numbers2 = convertToBoxed(NUMBERS2);

		for (final Long n1 : numbers1)
		{
			for (final Long n2 : numbers2)
			{
				@SuppressWarnings("boxing")
				final long expected = n1 + n2;
				final long actual = total(n1, n2);
				// System.out.println("expected: " + expected + "\nactual:   " + actual);
				assertTrue(expected == actual);
			}
		}

		final Long[] numbers3 = convertToBoxed(NUMBERS3);
		final Long[] numbers4 = convertToBoxed(NUMBERS4);

		for (final Long n1 : numbers3)
		{
			for (final Long n2 : numbers4)
			{
				@SuppressWarnings("boxing")
				final long expected = n1 + n2;
				final long actual = total(n1, n2);
				// System.out.println("expected: " + expected + "\nactual:   " + actual);
				assertTrue(expected == actual);
			}
		}
	}

	@Test
	public final void testTotalBoxedLongBoxedLongBoxedLong()
	{
		final Long[] numbers1 = convertToBoxed(NUMBERS1);
		final Long[] numbers2 = convertToBoxed(NUMBERS2);

		for (final Long n1 : numbers1)
		{
			for (final Long n2 : numbers2)
			{
				for (final Long n3 : numbers1)
				{
					@SuppressWarnings("boxing")
					final long expected = n1 + n2 + n3;
					final long actual = total(n1, n2, n3);
					// System.out.println("expected: " + expected + "\nactual:   " + actual);
					assertTrue(expected == actual);
				}
			}
		}

		final Long[] numbers3 = convertToBoxed(NUMBERS3);
		final Long[] numbers4 = convertToBoxed(NUMBERS4);

		for (final Long n1 : numbers3)
		{
			for (final Long n2 : numbers4)
			{
				for (final Long n3 : numbers3)
				{
					@SuppressWarnings("boxing")
					final long expected = n1 + n2 + n3;
					final long actual = total(n1, n2, n3);
					// System.out.println("expected: " + expected + "\nactual:   " + actual);
					assertTrue(expected == actual);
				}
			}
		}
	}

	@Test
	public final void testTotalBoxedLongBoxedLongBoxedLongBoxedLong()
	{
		final Long[] numbers1 = convertToBoxed(NUMBERS1);
		final Long[] numbers2 = convertToBoxed(NUMBERS2);

		for (final Long n1 : numbers1)
		{
			for (final Long n2 : numbers2)
			{
				for (final Long n3 : numbers1)
				{
					for (final Long n4 : numbers2)
					{
						@SuppressWarnings("boxing")
						final long expected = n1 + n2 + n3 + n4;
						final long actual = total(n1, n2, n3, n4);
						// System.out.println("expected: " + expected + "\nactual:   " + actual);
						assertTrue(expected == actual);
					}
				}
			}
		}

		final Long[] numbers3 = convertToBoxed(NUMBERS3);
		final Long[] numbers4 = convertToBoxed(NUMBERS4);

		for (final Long n1 : numbers3)
		{
			for (final Long n2 : numbers4)
			{
				for (final Long n3 : numbers3)
				{
					for (final Long n4 : numbers4)
					{
						@SuppressWarnings("boxing")
						final long expected = n1 + n2 + n3 + n4;
						final long actual = total(n1, n2, n3, n4);
						// System.out.println("expected: " + expected + "\nactual:   " + actual);
						assertTrue(expected == actual);
					}
				}
			}
		}
	}

	@Test
	public final void testTotalBoxedLongBoxedLongBoxedLongBoxedLongBoxedLong()
	{
		final Long[] numbers1 = convertToBoxed(NUMBERS1);
		final Long[] numbers2 = convertToBoxed(NUMBERS2);

		for (final Long n1 : numbers1)
		{
			for (final Long n2 : numbers2)
			{
				for (final Long n3 : numbers1)
				{
					for (final Long n4 : numbers2)
					{
						for (final Long n5 : numbers1)
						{
							@SuppressWarnings("boxing")
							final long expected = n1 + n2 + n3 + n4 + n5;
							final long actual = total(n1, n2, n3, n4, n5);
							// System.out.println("expected: " + expected + "\nactual:   " + actual);
							assertTrue(expected == actual);
						}
					}
				}
			}
		}

		final Long[] numbers3 = convertToBoxed(NUMBERS3);
		final Long[] numbers4 = convertToBoxed(NUMBERS4);

		for (final Long n1 : numbers3)
		{
			for (final Long n2 : numbers4)
			{
				for (final Long n3 : numbers3)
				{
					for (final Long n4 : numbers4)
					{
						for (final Long n5 : numbers3)
						{
							@SuppressWarnings("boxing")
							final long expected = n1 + n2 + n3 + n4 + n5;
							final long actual = total(n1, n2, n3, n4, n5);
							// System.out.println("expected: " + expected + "\nactual:   " + actual);
							assertTrue(expected == actual);
						}
					}
				}
			}
		}
	}

	@Test
	public final void testTotalBoxedLongBoxedLongBoxedLongBoxedLongBoxedLongBoxedLongArray()
	{
		final Long[] numbers1 = convertToBoxed(NUMBERS1);
		final Long[] numbers2 = convertToBoxed(NUMBERS2);

		long total1 = 0;
		for (final Long n : numbers1)
			total1 += n.longValue();

		long total2 = 0;
		for (final Long n : numbers2)
			total2 += n.longValue();

		for (final Long n1 : numbers1)
		{
			for (final Long n2 : numbers2)
			{
				for (final Long n3 : numbers1)
				{
					for (final Long n4 : numbers2)
					{
						for (final Long n5 : numbers1)
						{
							@SuppressWarnings("boxing")
							final long expected1 = n1 + n2 + n3 + n4 + n5 + total1;
							final long actual1 = total(n1, n2, n3, n4, n5, numbers1);
							// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
							assertTrue(expected1 == actual1);

							@SuppressWarnings("boxing")
							final long expected2 = n1 + n2 + n3 + n4 + n5 + total2;
							final long actual2 = total(n1, n2, n3, n4, n5, numbers2);
							// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
							assertTrue(expected2 == actual2);
						}
					}
				}
			}
		}

		final Long[] numbers3 = new Long[HOW_MANY];
		System.arraycopy(convertToBoxed(NUMBERS3), 0, numbers3, 0, HOW_MANY);
		final Long[] numbers4 = new Long[HOW_MANY];
		System.arraycopy(convertToBoxed(NUMBERS4), 0, numbers4, 0, HOW_MANY);

		long total3 = 0;
		for (final Long n : numbers3)
			total3 += n.longValue();

		long total4 = 0;
		for (final Long n : numbers4)
			total4 += n.longValue();

		for (final Long n1 : numbers3)
		{
			for (final Long n2 : numbers4)
			{
				for (final Long n3 : numbers3)
				{
					for (final Long n4 : numbers4)
					{
						for (final Long n5 : numbers3)
						{
							@SuppressWarnings("boxing")
							final long expected3 = n1 + n2 + n3 + n4 + n5 + total3;
							final long actual3 = total(n1, n2, n3, n4, n5, numbers3);
							// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
							assertTrue(expected3 == actual3);

							@SuppressWarnings("boxing")
							final long expected4 = n1 + n2 + n3 + n4 + n5 + total4;
							final long actual4 = total(n1, n2, n3, n4, n5, numbers4);
							// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
							assertTrue(expected4 == actual4);
						}
					}
				}
			}
		}
	}

	@Test
	public final void testTotalBoxedLongArray()
	{
		final Long[] numbers1 = convertToBoxed(NUMBERS1);
		final Long[] numbers2 = convertToBoxed(NUMBERS2);

		long total1 = 0;
		for (final Long n : numbers1)
			total1 += n.longValue();

		long total2 = 0;
		for (final Long n : numbers2)
			total2 += n.longValue();

		final long expected1 = total1;
		final long actual1 = total(numbers1);
		// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
		assertTrue(expected1 == actual1);
		final long expected2 = total2;
		final long actual2 = total(numbers2);
		// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
		assertTrue(expected2 == actual2);

		final Long[] numbers3 = convertToBoxed(NUMBERS3);
		final Long[] numbers4 = convertToBoxed(NUMBERS4);

		long total3 = 0;
		for (final Long n : numbers3)
			total3 += n.longValue();

		long total4 = 0;
		for (final Long n : numbers4)
			total4 += n.longValue();

		final long expected3 = total3;
		final long actual3 = total(numbers3);
		// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
		assertTrue(expected3 == actual3);

		final long expected4 = total4;
		final long actual4 = total(numbers4);
		// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
		assertTrue(expected4 == actual4);
	}

	@Test
	public final void testTotalIterableOfLong()
	{
		final Iterable<Long> numbers1 = asList(convertToBoxed(NUMBERS1));
		final Iterable<Long> numbers2 = asList(convertToBoxed(NUMBERS2));

		long total1 = 0;
		for (final Long n : numbers1)
			total1 += n.longValue();

		long total2 = 0;
		for (final Long n : numbers2)
			total2 += n.longValue();

		final long expected1 = total1;
		final long actual1 = total(numbers1);
		// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
		assertTrue(expected1 == actual1);
		final long expected2 = total2;
		final long actual2 = total(numbers2);
		// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
		assertTrue(expected2 == actual2);

		final Iterable<Long> numbers3 = asList(convertToBoxed(NUMBERS3));
		final Iterable<Long> numbers4 = asList(convertToBoxed(NUMBERS4));

		long total3 = 0;
		for (final Long n : numbers3)
			total3 += n.longValue();

		long total4 = 0;
		for (final Long n : numbers4)
			total4 += n.longValue();

		final long expected3 = total3;
		final long actual3 = total(numbers3);
		// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
		assertTrue(expected3 == actual3);

		final long expected4 = total4;
		final long actual4 = total(numbers4);
		// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
		assertTrue(expected4 == actual4);
	}

}
