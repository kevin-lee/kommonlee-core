/**
 * 
 */
package org.elixirian.common.util.number;

import static java.util.Arrays.*;
import static org.elixirian.common.util.number.DoublesTotal.*;
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
 * @version 0.0.1 (2011-04-11)
 */
public class DoublesTotalTest
{
	private static final int HOW_MANY = 30;

	/* @formatter:off */
	private static final double[] NUMBERS1 = { Integer.MIN_VALUE, -5,   -4,  -3,  -2,  -1,  0,  1,  2,  3,  4,  5,  Integer.MAX_VALUE };
	private static final double[] NUMBERS2 = { Integer.MIN_VALUE, -100, -90, -80, -70, -60, 10, 20, 30, 40, 50, 60, Integer.MAX_VALUE };
	/* @formatter:on */

	private static final Random random = new Random();
	private static final double[] NUMBERS3 = getRandomNumbers(50);
	private static final double[] NUMBERS4 = getRandomNumbers(50);

	private static double[] getRandomNumbers(final int length)
	{
		final double[] numbers = new double[length];
		for (int i = 0; i < length; i++)
		{
			numbers[i] = random.nextDouble();
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
	public final void testTotalDoubleDouble()
	{
		final double[] numbers1 = NUMBERS1;
		final double[] numbers2 = NUMBERS2;

		for (final double n1 : numbers1)
		{
			for (final double n2 : numbers2)
			{
				final double expected = n1 + n2;
				final double actual = total(n1, n2);
				// System.out.println("expected: " + expected + "\nactual:   " + actual);
				assertTrue(expected == actual);
			}
		}

		final double[] numbers3 = NUMBERS3;
		final double[] numbers4 = NUMBERS4;

		for (final double n1 : numbers3)
		{
			for (final double n2 : numbers4)
			{
				final double expected = n1 + n2;
				final double actual = total(n1, n2);
				// System.out.println("expected: " + expected + "\nactual:   " + actual);
				assertTrue(expected == actual);
			}
		}
	}

	@Test
	public final void testTotalDoubleDoubleDouble()
	{
		final double[] numbers1 = NUMBERS1;
		final double[] numbers2 = NUMBERS2;

		for (final double n1 : numbers1)
		{
			for (final double n2 : numbers2)
			{
				for (final double n3 : numbers1)
				{
					final double expected = n1 + n2 + n3;
					final double actual = total(n1, n2, n3);
					// System.out.println("expected: " + expected + "\nactual:   " + actual);
					assertTrue(expected == actual);
				}
			}
		}

		final double[] numbers3 = NUMBERS3;
		final double[] numbers4 = NUMBERS4;

		for (final double n1 : numbers3)
		{
			for (final double n2 : numbers4)
			{
				for (final double n3 : numbers3)
				{
					final double expected = n1 + n2 + n3;
					final double actual = total(n1, n2, n3);
					// System.out.println("expected: " + expected + "\nactual:   " + actual);
					assertTrue(expected == actual);
				}
			}
		}
	}

	@Test
	public final void testTotalDoubleDoubleDoubleDouble()
	{
		final double[] numbers1 = NUMBERS1;
		final double[] numbers2 = NUMBERS2;

		for (final double n1 : numbers1)
		{
			for (final double n2 : numbers2)
			{
				for (final double n3 : numbers1)
				{
					for (final double n4 : numbers2)
					{
						final double expected = n1 + n2 + n3 + n4;
						final double actual = total(n1, n2, n3, n4);
						// System.out.println("expected: " + expected + "\nactual:   " + actual);
						assertTrue(expected == actual);
					}
				}
			}
		}

		final double[] numbers3 = NUMBERS3;
		final double[] numbers4 = NUMBERS4;

		for (final double n1 : numbers3)
		{
			for (final double n2 : numbers4)
			{
				for (final double n3 : numbers3)
				{
					for (final double n4 : numbers4)
					{
						final double expected = n1 + n2 + n3 + n4;
						final double actual = total(n1, n2, n3, n4);
						// System.out.println("expected: " + expected + "\nactual:   " + actual);
						assertTrue(expected == actual);
					}
				}
			}
		}
	}

	@Test
	public final void testTotalDoubleDoubleDoubleDoubleDouble()
	{
		final double[] numbers1 = NUMBERS1;
		final double[] numbers2 = NUMBERS2;

		for (final double n1 : numbers1)
		{
			for (final double n2 : numbers2)
			{
				for (final double n3 : numbers1)
				{
					for (final double n4 : numbers2)
					{
						for (final double n5 : numbers1)
						{
							final double expected = n1 + n2 + n3 + n4 + n5;
							final double actual = total(n1, n2, n3, n4, n5);
							// System.out.println("expected: " + expected + "\nactual:   " + actual);
							assertTrue(expected == actual);
						}
					}
				}
			}
		}

		final double[] numbers3 = NUMBERS3;
		final double[] numbers4 = NUMBERS4;

		for (final double n1 : numbers3)
		{
			for (final double n2 : numbers4)
			{
				for (final double n3 : numbers3)
				{
					for (final double n4 : numbers4)
					{
						for (final double n5 : numbers3)
						{
							final double expected = n1 + n2 + n3 + n4 + n5;
							final double actual = total(n1, n2, n3, n4, n5);
							// System.out.println("expected: " + expected + "\nactual:   " + actual);
							assertTrue(expected == actual);
						}
					}
				}
			}
		}
	}

	@Test
	public final void testTotalDoubleDoubleDoubleDoubleDoubleDoubleArray()
	{
		final double[] numbers1 = NUMBERS1;
		final double[] numbers2 = NUMBERS2;

		double total1 = 0;
		for (final double n : numbers1)
			total1 += n;

		double total2 = 0;
		for (final double n : numbers2)
			total2 += n;

		for (final double n1 : numbers1)
		{
			for (final double n2 : numbers2)
			{
				for (final double n3 : numbers1)
				{
					for (final double n4 : numbers2)
					{
						for (final double n5 : numbers1)
						{
							final double expected1 = n1 + n2 + n3 + n4 + n5 + total1;
							final double actual1 = total(n1, n2, n3, n4, n5, numbers1);
							// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
							assertTrue(expected1 == actual1);
							final double expected2 = n1 + n2 + n3 + n4 + n5 + total2;
							final double actual2 = total(n1, n2, n3, n4, n5, numbers2);
							// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
							assertTrue(expected2 == actual2);
						}
					}
				}
			}
		}

		final double[] numbers3 = new double[HOW_MANY];
		System.arraycopy(NUMBERS3, 0, numbers3, 0, HOW_MANY);
		final double[] numbers4 = new double[HOW_MANY];
		System.arraycopy(NUMBERS4, 0, numbers4, 0, HOW_MANY);

		double total3 = 0;
		for (final double n : numbers3)
			total3 += n;

		double total4 = 0;
		for (final double n : numbers4)
			total4 += n;

		for (final double n1 : numbers3)
		{
			for (final double n2 : numbers4)
			{
				for (final double n3 : numbers3)
				{
					for (final double n4 : numbers4)
					{
						for (final double n5 : numbers3)
						{
							final double expected3 = n1 + n2 + n3 + n4 + n5 + total3;
							final double actual3 = total(n1, n2, n3, n4, n5, numbers3);
							// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
							assertTrue(expected3 == actual3);
							final double expected4 = n1 + n2 + n3 + n4 + n5 + total4;
							final double actual4 = total(n1, n2, n3, n4, n5, numbers4);
							// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
							assertTrue(expected4 == actual4);
						}
					}
				}
			}
		}
	}

	@Test
	public final void testTotalDoubleArray()
	{
		final double[] numbers1 = NUMBERS1;
		final double[] numbers2 = NUMBERS2;

		double total1 = 0;
		for (final double n : numbers1)
			total1 += n;

		double total2 = 0;
		for (final double n : numbers2)
			total2 += n;

		final double expected1 = total1;
		final double actual1 = total(numbers1);
		// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
		assertTrue(expected1 == actual1);
		final double expected2 = total2;
		final double actual2 = total(numbers2);
		// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
		assertTrue(expected2 == actual2);

		final double[] numbers3 = NUMBERS3;
		final double[] numbers4 = NUMBERS4;

		double total3 = 0;
		for (final double n : numbers3)
			total3 += n;

		double total4 = 0;
		for (final double n : numbers4)
			total4 += n;

		final double expected3 = total3;
		final double actual3 = total(numbers3);
		// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
		assertTrue(expected3 == actual3);

		final double expected4 = total4;
		final double actual4 = total(numbers4);
		// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
		assertTrue(expected4 == actual4);
	}

	@Test
	public final void testTotalBoxedDoubleBoxedDouble()
	{
		// System.out.println("\nDoublesTotalTest.testTotalBoxedDoubleBoxedDouble()");
		final Double[] numbers1 = convertToBoxed(NUMBERS1);
		final Double[] numbers2 = convertToBoxed(NUMBERS2);

		for (final Double n1 : numbers1)
		{
			for (final Double n2 : numbers2)
			{
				@SuppressWarnings("boxing")
				final double expected = n1 + n2;

				@SuppressWarnings("boxing")
				final double actual = total(n1, n2);
				// System.out.println("expected: " + expected + "\nactual:   " + actual);
				assertTrue(expected == actual);
			}
		}

		final Double[] numbers3 = convertToBoxed(NUMBERS3);
		final Double[] numbers4 = convertToBoxed(NUMBERS4);

		for (final Double n1 : numbers3)
		{
			for (final Double n2 : numbers4)
			{
				@SuppressWarnings("boxing")
				final double expected = n1 + n2;

				@SuppressWarnings("boxing")
				final double actual = total(n1, n2);
				// System.out.println("expected: " + expected + "\nactual:   " + actual);
				assertTrue(expected == actual);
			}
		}
	}

	@Test
	public final void testTotalBoxedDoubleBoxedDoubleBoxedDouble()
	{
		// System.out.println("\nDoublesTotalTest.testTotalBoxedDoubleBoxedDoubleBoxedDouble()");
		final Double[] numbers1 = convertToBoxed(NUMBERS1);
		final Double[] numbers2 = convertToBoxed(NUMBERS2);

		for (final Double n1 : numbers1)
		{
			for (final Double n2 : numbers2)
			{
				for (final Double n3 : numbers1)
				{
					@SuppressWarnings("boxing")
					final double expected = n1 + n2 + n3;

					@SuppressWarnings("boxing")
					final double actual = total(n1, n2, n3);
					// System.out.println("expected: " + expected + "\nactual:   " + actual);
					assertTrue(expected == actual);
				}
			}
		}

		final Double[] numbers3 = convertToBoxed(NUMBERS3);
		final Double[] numbers4 = convertToBoxed(NUMBERS4);

		for (final Double n1 : numbers3)
		{
			for (final Double n2 : numbers4)
			{
				for (final Double n3 : numbers3)
				{
					@SuppressWarnings("boxing")
					final double expected = n1 + n2 + n3;

					@SuppressWarnings("boxing")
					final double actual = total(n1, n2, n3);
					// System.out.println("expected: " + expected + "\nactual:   " + actual);
					assertTrue(expected == actual);
				}
			}
		}
	}

	@Test
	public final void testTotalBoxedDoubleBoxedDoubleBoxedDoubleBoxedDouble()
	{
		// System.out.println("\nDoublesTotalTest.testTotalBoxedDoubleBoxedDoubleBoxedDoubleBoxedDouble()");
		final Double[] numbers1 = convertToBoxed(NUMBERS1);
		final Double[] numbers2 = convertToBoxed(NUMBERS2);

		for (final Double n1 : numbers1)
		{
			for (final Double n2 : numbers2)
			{
				for (final Double n3 : numbers1)
				{
					for (final Double n4 : numbers2)
					{
						@SuppressWarnings("boxing")
						final double expected = n1 + n2 + n3 + n4;

						@SuppressWarnings("boxing")
						final double actual = total(n1, n2, n3, n4);
						// System.out.println("expected: " + expected + "\nactual:   " + actual);
						assertTrue(expected == actual);
					}
				}
			}
		}

		final Double[] numbers3 = convertToBoxed(NUMBERS3);
		final Double[] numbers4 = convertToBoxed(NUMBERS4);

		for (final Double n1 : numbers3)
		{
			for (final Double n2 : numbers4)
			{
				for (final Double n3 : numbers3)
				{
					for (final Double n4 : numbers4)
					{
						@SuppressWarnings("boxing")
						final double expected = n1 + n2 + n3 + n4;

						@SuppressWarnings("boxing")
						final double actual = total(n1, n2, n3, n4);
						// System.out.println("expected: " + expected + "\nactual:   " + actual);
						assertTrue(expected == actual);
					}
				}
			}
		}
	}

	@Test
	public final void testTotalBoxedDoubleBoxedDoubleBoxedDoubleBoxedDoubleBoxedDouble()
	{
		final Double[] numbers1 = convertToBoxed(NUMBERS1);
		final Double[] numbers2 = convertToBoxed(NUMBERS2);

		for (final Double n1 : numbers1)
		{
			for (final Double n2 : numbers2)
			{
				for (final Double n3 : numbers1)
				{
					for (final Double n4 : numbers2)
					{
						for (final Double n5 : numbers1)
						{
							@SuppressWarnings("boxing")
							final double expected = n1 + n2 + n3 + n4 + n5;

							@SuppressWarnings("boxing")
							final double actual = total(n1, n2, n3, n4, n5);
							// System.out.println("expected: " + expected + "\nactual:   " + actual);
							assertTrue(expected == actual);
						}
					}
				}
			}
		}

		final Double[] numbers3 = convertToBoxed(NUMBERS3);
		final Double[] numbers4 = convertToBoxed(NUMBERS4);

		for (final Double n1 : numbers3)
		{
			for (final Double n2 : numbers4)
			{
				for (final Double n3 : numbers3)
				{
					for (final Double n4 : numbers4)
					{
						for (final Double n5 : numbers3)
						{
							@SuppressWarnings("boxing")
							final double expected = n1 + n2 + n3 + n4 + n5;

							@SuppressWarnings("boxing")
							final double actual = total(n1, n2, n3, n4, n5);
							// System.out.println("expected: " + expected + "\nactual:   " + actual);
							assertTrue(expected == actual);
						}
					}
				}
			}
		}
	}

	@Test
	public final void testTotalBoxedDoubleBoxedDoubleBoxedDoubleBoxedDoubleBoxedDoubleBoxedDoubleArray()
	{
		final Double[] numbers1 = convertToBoxed(NUMBERS1);
		final Double[] numbers2 = convertToBoxed(NUMBERS2);

		double total1 = 0;
		for (final Double n : numbers1)
			total1 += n.doubleValue();

		double total2 = 0;
		for (final Double n : numbers2)
			total2 += n.doubleValue();

		for (final Double n1 : numbers1)
		{
			for (final Double n2 : numbers2)
			{
				for (final Double n3 : numbers1)
				{
					for (final Double n4 : numbers2)
					{
						for (final Double n5 : numbers1)
						{
							@SuppressWarnings("boxing")
							final double expected1 = n1 + n2 + n3 + n4 + n5 + total1;

							final double actual1 = total(n1, n2, n3, n4, n5, numbers1);
							// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
							assertTrue(expected1 == actual1);

							@SuppressWarnings("boxing")
							final double expected2 = n1 + n2 + n3 + n4 + n5 + total2;
							final double actual2 = total(n1, n2, n3, n4, n5, numbers2);
							// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
							assertTrue(expected2 == actual2);
						}
					}
				}
			}
		}

		final Double[] numbers3 = new Double[HOW_MANY];
		System.arraycopy(convertToBoxed(NUMBERS3), 0, numbers3, 0, HOW_MANY);
		final Double[] numbers4 = new Double[HOW_MANY];
		System.arraycopy(convertToBoxed(NUMBERS4), 0, numbers4, 0, HOW_MANY);

		double total3 = 0;
		for (final Double n : numbers3)
			total3 += n.doubleValue();

		double total4 = 0;
		for (final Double n : numbers4)
			total4 += n.doubleValue();

		for (final Double n1 : numbers3)
		{
			for (final Double n2 : numbers4)
			{
				for (final Double n3 : numbers3)
				{
					for (final Double n4 : numbers4)
					{
						for (final Double n5 : numbers3)
						{
							@SuppressWarnings("boxing")
							final double expected3 = n1 + n2 + n3 + n4 + n5 + total3;
							final double actual3 = total(n1, n2, n3, n4, n5, numbers3);
							// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
							assertTrue(expected3 == actual3);

							@SuppressWarnings("boxing")
							final double expected4 = n1 + n2 + n3 + n4 + n5 + total4;
							final double actual4 = total(n1, n2, n3, n4, n5, numbers4);
							// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
							assertTrue(expected4 == actual4);
						}
					}
				}
			}
		}
	}

	@Test
	public final void testTotalBoxedDoubleArray()
	{
		final Double[] numbers1 = convertToBoxed(NUMBERS1);
		final Double[] numbers2 = convertToBoxed(NUMBERS2);

		double total1 = 0;
		for (final Double n : numbers1)
			total1 += n.doubleValue();

		double total2 = 0;
		for (final Double n : numbers2)
			total2 += n.doubleValue();

		final double expected1 = total1;
		final double actual1 = total(numbers1);
		// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
		assertTrue(expected1 == actual1);
		final double expected2 = total2;
		final double actual2 = total(numbers2);
		// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
		assertTrue(expected2 == actual2);

		final Double[] numbers3 = convertToBoxed(NUMBERS3);
		final Double[] numbers4 = convertToBoxed(NUMBERS4);

		double total3 = 0;
		for (final Double n : numbers3)
			total3 += n.doubleValue();

		double total4 = 0;
		for (final Double n : numbers4)
			total4 += n.doubleValue();

		final double expected3 = total3;
		final double actual3 = total(numbers3);
		// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
		assertTrue(expected3 == actual3);

		final double expected4 = total4;
		final double actual4 = total(numbers4);
		// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
		assertTrue(expected4 == actual4);
	}

	@Test
	public final void testTotalIterableOfDouble()
	{
		final Iterable<Double> numbers1 = asList(convertToBoxed(NUMBERS1));
		final Iterable<Double> numbers2 = asList(convertToBoxed(NUMBERS2));

		double total1 = 0;
		for (final Double n : numbers1)
			total1 += n.doubleValue();

		double total2 = 0;
		for (final Double n : numbers2)
			total2 += n.doubleValue();

		final double expected1 = total1;
		final double actual1 = total(numbers1);
		// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
		assertTrue(expected1 == actual1);
		final double expected2 = total2;
		final double actual2 = total(numbers2);
		// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
		assertTrue(expected2 == actual2);

		final Iterable<Double> numbers3 = asList(convertToBoxed(NUMBERS3));
		final Iterable<Double> numbers4 = asList(convertToBoxed(NUMBERS4));

		double total3 = 0;
		for (final Double n : numbers3)
			total3 += n.doubleValue();

		double total4 = 0;
		for (final Double n : numbers4)
			total4 += n.doubleValue();

		final double expected3 = total3;
		final double actual3 = total(numbers3);
		// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
		assertTrue(expected3 == actual3);

		final double expected4 = total4;
		final double actual4 = total(numbers4);
		// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
		assertTrue(expected4 == actual4);
	}

}
