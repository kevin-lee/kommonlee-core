/**
 * 
 */
package org.elixirian.common.util.number;

import static java.util.Arrays.*;
import static org.elixirian.common.util.number.FloatsTotal.*;
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
 * @version 0.0.1 (2011-04-08)
 */
public class FloatsTotalTest
{
	private static final int HOW_MANY = 30;

	/* @formatter:off */
	private static final float[] NUMBERS1 = { Integer.MIN_VALUE, -5,   -4,  -3,  -2,  -1,  0,  1,  2,  3,  4,  5,  Integer.MAX_VALUE };
	private static final float[] NUMBERS2 = { Integer.MIN_VALUE, -100, -90, -80, -70, -60, 10, 20, 30, 40, 50, 60, Integer.MAX_VALUE };
	/* @formatter:on */

	private static final Random random = new Random();
	private static final float[] NUMBERS3 = getRandomNumbers(50);
	private static final float[] NUMBERS4 = getRandomNumbers(50);

	private static float[] getRandomNumbers(final int length)
	{
		final float[] numbers = new float[length];
		for (int i = 0; i < length; i++)
		{
			numbers[i] = random.nextFloat();
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
	public final void testTotalFloatFloat()
	{
		final float[] numbers1 = NUMBERS1;
		final float[] numbers2 = NUMBERS2;

		for (final float n1 : numbers1)
		{
			for (final float n2 : numbers2)
			{
				final float expected = n1 + n2;
				final float actual = total(n1, n2);
				// System.out.println("expected: " + expected + "\nactual:   " + actual);
				assertTrue(expected == actual);
			}
		}

		final float[] numbers3 = NUMBERS3;
		final float[] numbers4 = NUMBERS4;

		for (final float n1 : numbers3)
		{
			for (final float n2 : numbers4)
			{
				final float expected = n1 + n2;
				final float actual = total(n1, n2);
				// System.out.println("expected: " + expected + "\nactual:   " + actual);
				assertTrue(expected == actual);
			}
		}
	}

	@Test
	public final void testTotalFloatFloatFloat()
	{
		final float[] numbers1 = NUMBERS1;
		final float[] numbers2 = NUMBERS2;

		for (final float n1 : numbers1)
		{
			for (final float n2 : numbers2)
			{
				for (final float n3 : numbers1)
				{
					final float expected = n1 + n2 + n3;
					final float actual = total(n1, n2, n3);
					// System.out.println("expected: " + expected + "\nactual:   " + actual);
					assertTrue(expected == actual);
				}
			}
		}

		final float[] numbers3 = NUMBERS3;
		final float[] numbers4 = NUMBERS4;

		for (final float n1 : numbers3)
		{
			for (final float n2 : numbers4)
			{
				for (final float n3 : numbers3)
				{
					final float expected = n1 + n2 + n3;
					final float actual = total(n1, n2, n3);
					// System.out.println("expected: " + expected + "\nactual:   " + actual);
					assertTrue(expected == actual);
				}
			}
		}
	}

	@Test
	public final void testTotalFloatFloatFloatFloat()
	{
		final float[] numbers1 = NUMBERS1;
		final float[] numbers2 = NUMBERS2;

		for (final float n1 : numbers1)
		{
			for (final float n2 : numbers2)
			{
				for (final float n3 : numbers1)
				{
					for (final float n4 : numbers2)
					{
						final float expected = n1 + n2 + n3 + n4;
						final float actual = total(n1, n2, n3, n4);
						// System.out.println("expected: " + expected + "\nactual:   " + actual);
						assertTrue(expected == actual);
					}
				}
			}
		}

		final float[] numbers3 = NUMBERS3;
		final float[] numbers4 = NUMBERS4;

		for (final float n1 : numbers3)
		{
			for (final float n2 : numbers4)
			{
				for (final float n3 : numbers3)
				{
					for (final float n4 : numbers4)
					{
						final float expected = n1 + n2 + n3 + n4;
						final float actual = total(n1, n2, n3, n4);
						// System.out.println("expected: " + expected + "\nactual:   " + actual);
						assertTrue(expected == actual);
					}
				}
			}
		}
	}

	@Test
	public final void testTotalFloatFloatFloatFloatFloat()
	{
		final float[] numbers1 = NUMBERS1;
		final float[] numbers2 = NUMBERS2;

		for (final float n1 : numbers1)
		{
			for (final float n2 : numbers2)
			{
				for (final float n3 : numbers1)
				{
					for (final float n4 : numbers2)
					{
						for (final float n5 : numbers1)
						{
							final float expected = n1 + n2 + n3 + n4 + n5;
							final float actual = total(n1, n2, n3, n4, n5);
							// System.out.println("expected: " + expected + "\nactual:   " + actual);
							assertTrue(expected == actual);
						}
					}
				}
			}
		}

		final float[] numbers3 = NUMBERS3;
		final float[] numbers4 = NUMBERS4;

		for (final float n1 : numbers3)
		{
			for (final float n2 : numbers4)
			{
				for (final float n3 : numbers3)
				{
					for (final float n4 : numbers4)
					{
						for (final float n5 : numbers3)
						{
							final float expected = n1 + n2 + n3 + n4 + n5;
							final float actual = total(n1, n2, n3, n4, n5);
							// System.out.println("expected: " + expected + "\nactual:   " + actual);
							assertTrue(expected == actual);
						}
					}
				}
			}
		}
	}

	@Test
	public final void testTotalFloatFloatFloatFloatFloatFloatArray()
	{
		final float[] numbers1 = NUMBERS1;
		final float[] numbers2 = NUMBERS2;

		float total1 = 0;
		for (final float n : numbers1)
			total1 += n;

		float total2 = 0;
		for (final float n : numbers2)
			total2 += n;

		for (final float n1 : numbers1)
		{
			for (final float n2 : numbers2)
			{
				for (final float n3 : numbers1)
				{
					for (final float n4 : numbers2)
					{
						for (final float n5 : numbers1)
						{
							final float expected3 = n1 + n2 + n3 + n4 + n5 + total1;
							final float actual3 = total(n1, n2, n3, n4, n5, numbers1);
							// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
							assertTrue(expected3 == actual3);
							final float expected4 = n1 + n2 + n3 + n4 + n5 + total2;
							final float actual4 = total(n1, n2, n3, n4, n5, numbers2);
							// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
							assertTrue(expected4 == actual4);
						}
					}
				}
			}
		}

		final float[] numbers3 = new float[HOW_MANY];
		System.arraycopy(NUMBERS3, 0, numbers3, 0, HOW_MANY);
		final float[] numbers4 = new float[HOW_MANY];
		System.arraycopy(NUMBERS4, 0, numbers4, 0, HOW_MANY);

		float total3 = 0;
		for (final float n : numbers3)
			total3 += n;

		float total4 = 0;
		for (final float n : numbers4)
			total4 += n;

		for (final float n1 : numbers3)
		{
			for (final float n2 : numbers4)
			{
				for (final float n3 : numbers3)
				{
					for (final float n4 : numbers4)
					{
						for (final float n5 : numbers3)
						{
							final float expected1 = n1 + n2 + n3 + n4 + n5 + total3;
							final float actual1 = total(n1, n2, n3, n4, n5, numbers3);
							// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
							assertTrue(expected1 == actual1);
							final float expected2 = n1 + n2 + n3 + n4 + n5 + total4;
							final float actual2 = total(n1, n2, n3, n4, n5, numbers4);
							// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
							assertTrue(expected2 == actual2);
						}
					}
				}
			}
		}
	}

	@Test
	public final void testTotalFloatArray()
	{
		final float[] numbers1 = NUMBERS1;
		final float[] numbers2 = NUMBERS2;

		float total1 = 0;
		for (final float n : numbers1)
			total1 += n;

		float total2 = 0;
		for (final float n : numbers2)
			total2 += n;

		final float expected1 = total1;
		final float actual1 = total(numbers1);
		// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
		assertTrue(expected1 == actual1);
		final float expected2 = total2;
		final float actual2 = total(numbers2);
		// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
		assertTrue(expected2 == actual2);

		final float[] numbers3 = NUMBERS3;
		final float[] numbers4 = NUMBERS4;

		float total3 = 0;
		for (final float n : numbers3)
			total3 += n;

		float total4 = 0;
		for (final float n : numbers4)
			total4 += n;

		final float expected3 = total3;
		final float actual3 = total(numbers3);
		// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
		assertTrue(expected3 == actual3);

		final float expected4 = total4;
		final float actual4 = total(numbers4);
		// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
		assertTrue(expected4 == actual4);
	}

	@Test
	public final void testTotalBoxedFloatBoxedFloat()
	{
		final Float[] numbers1 = convertToBoxed(NUMBERS1);
		final Float[] numbers2 = convertToBoxed(NUMBERS2);

		for (final Float n1 : numbers1)
		{
			for (final Float n2 : numbers2)
			{
				@SuppressWarnings("boxing")
				final float expected = n1 + n2;

				@SuppressWarnings("boxing")
				final float actual = total(n1, n2);
				// System.out.println("expected: " + expected + "\nactual:   " + actual);
				assertTrue(expected == actual);
			}
		}

		final Float[] numbers3 = convertToBoxed(NUMBERS3);
		final Float[] numbers4 = convertToBoxed(NUMBERS4);

		for (final Float n1 : numbers3)
		{
			for (final Float n2 : numbers4)
			{
				@SuppressWarnings("boxing")
				final float expected = n1 + n2;

				@SuppressWarnings("boxing")
				final float actual = total(n1, n2);
				// System.out.println("expected: " + expected + "\nactual:   " + actual);
				assertTrue(expected == actual);
			}
		}
	}

	@Test
	public final void testTotalBoxedFloatBoxedFloatBoxedFloat()
	{
		final Float[] numbers1 = convertToBoxed(NUMBERS1);
		final Float[] numbers2 = convertToBoxed(NUMBERS2);

		for (final Float n1 : numbers1)
		{
			for (final Float n2 : numbers2)
			{
				for (final Float n3 : numbers1)
				{
					@SuppressWarnings("boxing")
					final float expected = n1 + n2 + n3;

					@SuppressWarnings("boxing")
					final float actual = total(n1, n2, n3);
					// System.out.println("expected: " + expected + "\nactual:   " + actual);
					assertTrue(expected == actual);
				}
			}
		}

		final Float[] numbers3 = convertToBoxed(NUMBERS3);
		final Float[] numbers4 = convertToBoxed(NUMBERS4);

		for (final Float n1 : numbers3)
		{
			for (final Float n2 : numbers4)
			{
				for (final Float n3 : numbers3)
				{
					@SuppressWarnings("boxing")
					final float expected = n1 + n2 + n3;

					@SuppressWarnings("boxing")
					final float actual = total(n1, n2, n3);
					// System.out.println("expected: " + expected + "\nactual:   " + actual);
					assertTrue(expected == actual);
				}
			}
		}
	}

	@Test
	public final void testTotalBoxedFloatBoxedFloatBoxedFloatBoxedFloat()
	{
		final Float[] numbers1 = convertToBoxed(NUMBERS1);
		final Float[] numbers2 = convertToBoxed(NUMBERS2);

		for (final Float n1 : numbers1)
		{
			for (final Float n2 : numbers2)
			{
				for (final Float n3 : numbers1)
				{
					for (final Float n4 : numbers2)
					{
						@SuppressWarnings("boxing")
						final float expected = n1 + n2 + n3 + n4;

						@SuppressWarnings("boxing")
						final float actual = total(n1, n2, n3, n4);
						// System.out.println("expected: " + expected + "\nactual:   " + actual);
						assertTrue(expected == actual);
					}
				}
			}
		}

		final Float[] numbers3 = convertToBoxed(NUMBERS3);
		final Float[] numbers4 = convertToBoxed(NUMBERS4);

		for (final Float n1 : numbers3)
		{
			for (final Float n2 : numbers4)
			{
				for (final Float n3 : numbers3)
				{
					for (final Float n4 : numbers4)
					{
						@SuppressWarnings("boxing")
						final float expected = n1 + n2 + n3 + n4;

						@SuppressWarnings("boxing")
						final float actual = total(n1, n2, n3, n4);
						// System.out.println("expected: " + expected + "\nactual:   " + actual);
						assertTrue(expected == actual);
					}
				}
			}
		}
	}

	@Test
	public final void testTotalBoxedFloatBoxedFloatBoxedFloatBoxedFloatBoxedFloat()
	{
		final Float[] numbers1 = convertToBoxed(NUMBERS1);
		final Float[] numbers2 = convertToBoxed(NUMBERS2);

		for (final Float n1 : numbers1)
		{
			for (final Float n2 : numbers2)
			{
				for (final Float n3 : numbers1)
				{
					for (final Float n4 : numbers2)
					{
						for (final Float n5 : numbers1)
						{
							@SuppressWarnings("boxing")
							final float expected = n1 + n2 + n3 + n4 + n5;

							@SuppressWarnings("boxing")
							final float actual = total(n1, n2, n3, n4, n5);
							// System.out.println("expected: " + expected + "\nactual:   " + actual);
							assertTrue(expected == actual);
						}
					}
				}
			}
		}

		final Float[] numbers3 = convertToBoxed(NUMBERS3);
		final Float[] numbers4 = convertToBoxed(NUMBERS4);

		for (final Float n1 : numbers3)
		{
			for (final Float n2 : numbers4)
			{
				for (final Float n3 : numbers3)
				{
					for (final Float n4 : numbers4)
					{
						for (final Float n5 : numbers3)
						{
							@SuppressWarnings("boxing")
							final float expected = n1 + n2 + n3 + n4 + n5;

							@SuppressWarnings("boxing")
							final float actual = total(n1, n2, n3, n4, n5);
							// System.out.println("expected: " + expected + "\nactual:   " + actual);
							assertTrue(expected == actual);
						}
					}
				}
			}
		}
	}

	@Test
	public final void testTotalBoxedFloatBoxedFloatBoxedFloatBoxedFloatBoxedFloatBoxedFloatArray()
	{
		final Float[] numbers1 = convertToBoxed(NUMBERS1);
		final Float[] numbers2 = convertToBoxed(NUMBERS2);

		float total1 = 0;
		for (final Float n : numbers1)
			total1 += n.floatValue();

		float total2 = 0;
		for (final Float n : numbers2)
			total2 += n.floatValue();

		for (final Float n1 : numbers1)
		{
			for (final Float n2 : numbers2)
			{
				for (final Float n3 : numbers1)
				{
					for (final Float n4 : numbers2)
					{
						for (final Float n5 : numbers1)
						{
							@SuppressWarnings("boxing")
							final float expected3 = n1 + n2 + n3 + n4 + n5 + total1;
							final float actual3 = total(n1, n2, n3, n4, n5, numbers1);
							// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
							assertTrue(expected3 == actual3);

							@SuppressWarnings("boxing")
							final float expected4 = n1 + n2 + n3 + n4 + n5 + total2;
							final float actual4 = total(n1, n2, n3, n4, n5, numbers2);
							// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
							assertTrue(expected4 == actual4);
						}
					}
				}
			}
		}

		final Float[] numbers3 = new Float[HOW_MANY];
		System.arraycopy(convertToBoxed(NUMBERS3), 0, numbers3, 0, HOW_MANY);
		final Float[] numbers4 = new Float[HOW_MANY];
		System.arraycopy(convertToBoxed(NUMBERS4), 0, numbers4, 0, HOW_MANY);

		float total3 = 0;
		for (final Float n : numbers3)
			total3 += n.floatValue();

		float total4 = 0;
		for (final Float n : numbers4)
			total4 += n.floatValue();

		for (final Float n1 : numbers3)
		{
			for (final Float n2 : numbers4)
			{
				for (final Float n3 : numbers3)
				{
					for (final Float n4 : numbers4)
					{
						for (final Float n5 : numbers3)
						{
							@SuppressWarnings("boxing")
							final float expected1 = n1 + n2 + n3 + n4 + n5 + total3;
							final float actual1 = total(n1, n2, n3, n4, n5, numbers3);
							// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
							assertTrue(expected1 == actual1);

							@SuppressWarnings("boxing")
							final float expected2 = n1 + n2 + n3 + n4 + n5 + total4;
							final float actual2 = total(n1, n2, n3, n4, n5, numbers4);
							// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
							assertTrue(expected2 == actual2);
						}
					}
				}
			}
		}
	}

	@Test
	public final void testTotalBoxedFloatArray()
	{
		final Float[] numbers1 = convertToBoxed(NUMBERS1);
		final Float[] numbers2 = convertToBoxed(NUMBERS2);

		float total1 = 0;
		for (final Float n : numbers1)
			total1 += n.floatValue();

		float total2 = 0;
		for (final Float n : numbers2)
			total2 += n.floatValue();

		final float expected1 = total1;
		final float actual1 = total(numbers1);
		// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
		assertTrue(expected1 == actual1);
		final float expected2 = total2;
		final float actual2 = total(numbers2);
		// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
		assertTrue(expected2 == actual2);

		final Float[] numbers3 = convertToBoxed(NUMBERS3);
		final Float[] numbers4 = convertToBoxed(NUMBERS4);

		float total3 = 0;
		for (final Float n : numbers3)
			total3 += n.floatValue();

		float total4 = 0;
		for (final Float n : numbers4)
			total4 += n.floatValue();

		final float expected3 = total3;
		final float actual3 = total(numbers3);
		// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
		assertTrue(expected3 == actual3);

		final float expected4 = total4;
		final float actual4 = total(numbers4);
		// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
		assertTrue(expected4 == actual4);
	}

	@Test
	public final void testTotalIterableOfFloat()
	{
		final Iterable<Float> numbers1 = asList(convertToBoxed(NUMBERS1));
		final Iterable<Float> numbers2 = asList(convertToBoxed(NUMBERS2));

		float total1 = 0;
		for (final float n : numbers1)
			total1 += n;

		float total2 = 0;
		for (final float n : numbers2)
			total2 += n;

		final float expected1 = total1;
		final float actual1 = total(numbers1);
		// System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
		assertTrue(expected1 == actual1);
		final float expected2 = total2;
		final float actual2 = total(numbers2);
		// System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
		assertTrue(expected2 == actual2);

		final Iterable<Float> numbers3 = asList(convertToBoxed(NUMBERS3));
		final Iterable<Float> numbers4 = asList(convertToBoxed(NUMBERS4));

		float total3 = 0;
		for (final float n : numbers3)
			total3 += n;

		float total4 = 0;
		for (final float n : numbers4)
			total4 += n;

		final float expected3 = total3;
		final float actual3 = total(numbers3);
		// System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
		assertTrue(expected3 == actual3);

		final float expected4 = total4;
		final float actual4 = total(numbers4);
		// System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
		assertTrue(expected4 == actual4);
	}

}
