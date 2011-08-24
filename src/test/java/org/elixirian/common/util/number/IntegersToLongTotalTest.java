/**
 * 
 */
package org.elixirian.common.util.number;

import static java.util.Arrays.*;
import static org.elixirian.common.util.number.IntegersToLongTotal.*;
import static org.elixirian.common.util.number.Numbers.*;
import static org.hamcrest.CoreMatchers.*;
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
public class IntegersToLongTotalTest
{
  private static final int HOW_MANY = 30;

  /* @formatter:off */
	private static final int[] NUMBERS1 = { Integer.MIN_VALUE, -5,   -4,  -3,  -2,  -1,  0,  1,  2,  3,  4,  5,  Integer.MAX_VALUE };
	private static final int[] NUMBERS2 = { Integer.MIN_VALUE, -100, -90, -80, -70, -60, 10, 20, 30, 40, 50, 60, Integer.MAX_VALUE };
	/* @formatter:on */

  private static final Random random = new Random();
  private static final int[] NUMBERS3 = getRandomNumbers(50);
  private static final int[] NUMBERS4 = getRandomNumbers(50);

  private static int[] getRandomNumbers(final int length)
  {
    final int[] numbers = new int[length];
    for (int i = 0; i < length; i++)
    {
      numbers[i] = random.nextInt();
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
  public final void testSumIntInt()
  {
    final int[] numbers1 = NUMBERS1;
    final int[] numbers2 = NUMBERS2;

    for (final int n1 : numbers1)
    {
      for (final int n2 : numbers2)
      {
        final long expected = (long) n1 + n2;
        final long actual = sum(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertTrue(expected == actual);
      }
    }

    final int[] numbers3 = NUMBERS3;
    final int[] numbers4 = NUMBERS4;

    for (final int n1 : numbers3)
    {
      for (final int n2 : numbers4)
      {
        final long expected = (long) n1 + n2;
        final long actual = sum(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertTrue(expected == actual);
      }
    }
  }

  @Test
  public final void testSumIntIntInt()
  {
    final int[] numbers1 = NUMBERS1;
    final int[] numbers2 = NUMBERS2;

    for (final int n1 : numbers1)
    {
      for (final int n2 : numbers2)
      {
        for (final int n3 : numbers1)
        {
          final long expected = (long) n1 + n2 + n3;
          final long actual = sum(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertTrue(expected == actual);
        }
      }
    }

    final int[] numbers3 = NUMBERS3;
    final int[] numbers4 = NUMBERS4;

    for (final int n1 : numbers3)
    {
      for (final int n2 : numbers4)
      {
        for (final int n3 : numbers3)
        {
          final long expected = (long) n1 + n2 + n3;
          final long actual = sum(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertTrue(expected == actual);
        }
      }
    }
  }

  @Test
  public final void testSumIntIntIntInt()
  {
    final int[] numbers1 = NUMBERS1;
    final int[] numbers2 = NUMBERS2;

    for (final int n1 : numbers1)
    {
      for (final int n2 : numbers2)
      {
        for (final int n3 : numbers1)
        {
          for (final int n4 : numbers2)
          {
            final long expected = (long) n1 + n2 + n3 + n4;
            final long actual = sum(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertTrue(expected == actual);
          }
        }
      }
    }

    final int[] numbers3 = NUMBERS3;
    final int[] numbers4 = NUMBERS4;

    for (final int n1 : numbers3)
    {
      for (final int n2 : numbers4)
      {
        for (final int n3 : numbers3)
        {
          for (final int n4 : numbers4)
          {
            final long expected = (long) n1 + n2 + n3 + n4;
            final long actual = sum(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertTrue(expected == actual);
          }
        }
      }
    }
  }

  @Test
  public final void testSumIntIntIntIntInt()
  {
    final int[] numbers1 = NUMBERS1;
    final int[] numbers2 = NUMBERS2;

    for (final int n1 : numbers1)
    {
      for (final int n2 : numbers2)
      {
        for (final int n3 : numbers1)
        {
          for (final int n4 : numbers2)
          {
            for (final int n5 : numbers1)
            {
              final long expected = (long) n1 + n2 + n3 + n4 + n5;
              final long actual = sum(n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected + "\nactual:   " + actual);
              assertTrue(expected == actual);
            }
          }
        }
      }
    }

    final int[] numbers3 = NUMBERS3;
    final int[] numbers4 = NUMBERS4;

    for (final int n1 : numbers3)
    {
      for (final int n2 : numbers4)
      {
        for (final int n3 : numbers3)
        {
          for (final int n4 : numbers4)
          {
            for (final int n5 : numbers3)
            {
              final long expected = (long) n1 + n2 + n3 + n4 + n5;
              final long actual = sum(n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected + "\nactual:   " + actual);
              assertTrue(expected == actual);
            }
          }
        }
      }
    }
  }

  @Test
  public final void testSumIntIntIntIntIntMoreInts()
  {
    final int[] numbers1 = NUMBERS1;
    final int[] numbers2 = NUMBERS2;

    for (final int n1 : numbers1)
    {
      for (final int n2 : numbers2)
      {
        for (final int n3 : numbers1)
        {
          for (final int n4 : numbers2)
          {
            for (final int n5 : numbers1)
            {
              final long expected1 = (long) n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5;
              final long actual1 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertTrue(expected1 == actual1);
              final long expected2 = (long) n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5;
              final long actual2 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertTrue(expected2 == actual2);
            }
          }
        }
      }
    }

    final int[] numbers3 = new int[HOW_MANY];
    System.arraycopy(NUMBERS3, 0, numbers3, 0, HOW_MANY);
    final int[] numbers4 = new int[HOW_MANY];
    System.arraycopy(NUMBERS4, 0, numbers4, 0, HOW_MANY);

    for (final int n1 : numbers3)
    {
      for (final int n2 : numbers4)
      {
        for (final int n3 : numbers3)
        {
          for (final int n4 : numbers4)
          {
            for (final int n5 : numbers3)
            {
              final long expected3 = (long) n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5;
              final long actual3 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertTrue(expected3 == actual3);
              final long expected4 = (long) n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5;
              final long actual4 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertTrue(expected4 == actual4);
            }
          }
        }
      }
    }
  }

  @Test
  public final void testSumIntIntIntIntIntIntArray()
  {
    final int[] numbers1 = NUMBERS1;
    final int[] numbers2 = NUMBERS2;

    for (final int n1 : numbers1)
    {
      for (final int n2 : numbers2)
      {
        for (final int n3 : numbers1)
        {
          for (final int n4 : numbers2)
          {
            for (final int n5 : numbers1)
            {
              long total1 = (long) n1 + n2 + n3 + n4 + n5;
              for (final int n : numbers1)
                total1 += n;

              @SuppressWarnings("boxing")
              final Long expected1 = total1;
              @SuppressWarnings("boxing")
              final Long actual1 = sum(n1, n2, n3, n4, n5, numbers1);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              long total2 = (long) n1 + n2 + n3 + n4 + n5;
              for (final int n : numbers2)
                total2 += n;

              @SuppressWarnings("boxing")
              final Long expected2 = total2;
              @SuppressWarnings("boxing")
              final Long actual2 = sum(n1, n2, n3, n4, n5, numbers2);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
            }
          }
        }
      }
    }

    final int[] numbers3 = new int[HOW_MANY];
    System.arraycopy(NUMBERS3, 0, numbers3, 0, HOW_MANY);
    final int[] numbers4 = new int[HOW_MANY];
    System.arraycopy(NUMBERS4, 0, numbers4, 0, HOW_MANY);

    for (final int n1 : numbers3)
    {
      for (final int n2 : numbers4)
      {
        for (final int n3 : numbers3)
        {
          for (final int n4 : numbers4)
          {
            for (final int n5 : numbers3)
            {
              long total3 = (long) n1 + n2 + n3 + n4 + n5;
              for (final int n : numbers3)
                total3 += n;

              @SuppressWarnings("boxing")
              final Long expected3 = total3;
              @SuppressWarnings("boxing")
              final Long actual3 = sum(n1, n2, n3, n4, n5, numbers3);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              long total4 = (long) n1 + n2 + n3 + n4 + n5;
              for (final int n : numbers4)
                total4 += n;

              @SuppressWarnings("boxing")
              final Long expected4 = total4;
              @SuppressWarnings("boxing")
              final Long actual4 = sum(n1, n2, n3, n4, n5, numbers4);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertThat(actual4, is(equalTo(expected4)));
            }
          }
        }
      }
    }
  }

  @Test
  public final void testSumIntArray()
  {
    final int[] numbers1 = NUMBERS1;
    final int[] numbers2 = NUMBERS2;

    long total1 = 0;
    for (final int n : numbers1)
      total1 += n;

    long total2 = 0;
    for (final int n : numbers2)
      total2 += n;

    final long expected1 = total1;
    final long actual1 = sum(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);
    final long expected2 = total2;
    final long actual2 = sum(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertTrue(expected2 == actual2);

    final int[] numbers3 = NUMBERS3;
    final int[] numbers4 = NUMBERS4;

    long total3 = 0;
    for (final int n : numbers3)
      total3 += n;

    long total4 = 0;
    for (final int n : numbers4)
      total4 += n;

    final long expected3 = total3;
    final long actual3 = sum(numbers3);
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertTrue(expected3 == actual3);
    final long expected4 = total4;
    final long actual4 = sum(numbers4);
    // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
    assertTrue(expected4 == actual4);
  }

  @Test
  public final void testTotalIntegerInteger()
  {
    final Integer[] numbers1 = convertToBoxed(NUMBERS1);
    final Integer[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Integer n1 : numbers1)
    {
      for (final Integer n2 : numbers2)
      {
        @SuppressWarnings("boxing")
        final long expected = (long) n1.intValue() + n2;
        final long actual = total(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertTrue(expected == actual);
      }
    }

    final Integer[] numbers3 = convertToBoxed(NUMBERS3);
    final Integer[] numbers4 = convertToBoxed(NUMBERS4);

    for (final Integer n1 : numbers3)
    {
      for (final Integer n2 : numbers4)
      {
        @SuppressWarnings("boxing")
        final long expected = (long) n1.intValue() + n2;
        final long actual = total(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertTrue(expected == actual);
      }
    }
  }

  @Test
  public final void testTotalIntegerIntegerInteger()
  {
    final Integer[] numbers1 = convertToBoxed(NUMBERS1);
    final Integer[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Integer n1 : numbers1)
    {
      for (final Integer n2 : numbers2)
      {
        for (final Integer n3 : numbers1)
        {
          @SuppressWarnings("boxing")
          final long expected = (long) n1.intValue() + n2 + n3;
          final long actual = total(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertTrue(expected == actual);
        }
      }
    }

    final Integer[] numbers3 = convertToBoxed(NUMBERS3);
    final Integer[] numbers4 = convertToBoxed(NUMBERS4);

    for (final Integer n1 : numbers3)
    {
      for (final Integer n2 : numbers4)
      {
        for (final Integer n3 : numbers3)
        {
          @SuppressWarnings("boxing")
          final long expected = (long) n1.intValue() + n2 + n3;
          final long actual = total(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertTrue(expected == actual);
        }
      }
    }
  }

  @Test
  public final void testTotalIntegerIntegerIntegerInteger()
  {
    final Integer[] numbers1 = convertToBoxed(NUMBERS1);
    final Integer[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Integer n1 : numbers1)
    {
      for (final Integer n2 : numbers2)
      {
        for (final Integer n3 : numbers1)
        {
          for (final Integer n4 : numbers2)
          {
            @SuppressWarnings("boxing")
            final long expected = (long) n1.intValue() + n2 + n3 + n4;
            final long actual = total(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertTrue(expected == actual);
          }
        }
      }
    }

    final Integer[] numbers3 = convertToBoxed(NUMBERS3);
    final Integer[] numbers4 = convertToBoxed(NUMBERS4);

    for (final Integer n1 : numbers3)
    {
      for (final Integer n2 : numbers4)
      {
        for (final Integer n3 : numbers3)
        {
          for (final Integer n4 : numbers4)
          {
            @SuppressWarnings("boxing")
            final long expected = (long) n1.intValue() + n2 + n3 + n4;
            final long actual = total(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertTrue(expected == actual);
          }
        }
      }
    }
  }

  @Test
  public final void testTotalIntegerIntegerIntegerIntegerInteger()
  {
    final Integer[] numbers1 = convertToBoxed(NUMBERS1);
    final Integer[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Integer n1 : numbers1)
    {
      for (final Integer n2 : numbers2)
      {
        for (final Integer n3 : numbers1)
        {
          for (final Integer n4 : numbers2)
          {
            for (final Integer n5 : numbers1)
            {
              @SuppressWarnings("boxing")
              final long expected = (long) n1.intValue() + n2 + n3 + n4 + n5;
              final long actual = total(n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected + "\nactual:   " + actual);
              assertTrue(expected == actual);
            }
          }
        }
      }
    }

    final Integer[] numbers3 = convertToBoxed(NUMBERS3);
    final Integer[] numbers4 = convertToBoxed(NUMBERS4);

    for (final Integer n1 : numbers3)
    {
      for (final Integer n2 : numbers4)
      {
        for (final Integer n3 : numbers3)
        {
          for (final Integer n4 : numbers4)
          {
            for (final Integer n5 : numbers3)
            {
              @SuppressWarnings("boxing")
              final long expected = (long) n1.intValue() + n2 + n3 + n4 + n5;
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
  public final void testTotalIntegerIntegerIntegerIntegerIntegerMoreIntegers()
  {
    final Integer[] numbers1 = convertToBoxed(NUMBERS1);
    final Integer[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Integer n1 : numbers1)
    {
      for (final Integer n2 : numbers2)
      {
        for (final Integer n3 : numbers1)
        {
          for (final Integer n4 : numbers2)
          {
            for (final Integer n5 : numbers1)
            {
              @SuppressWarnings("boxing")
              final Long expected1 = ((long) n1.intValue() + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Long actual1 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              @SuppressWarnings("boxing")
              final Long expected2 = ((long) n1.intValue() + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Long actual2 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
            }
          }
        }
      }
    }

    final Integer[] numbers3 = new Integer[HOW_MANY];
    System.arraycopy(convertToBoxed(NUMBERS3), 0, numbers3, 0, HOW_MANY);
    final Integer[] numbers4 = new Integer[HOW_MANY];
    System.arraycopy(convertToBoxed(NUMBERS4), 0, numbers4, 0, HOW_MANY);

    for (final Integer n1 : numbers3)
    {
      for (final Integer n2 : numbers4)
      {
        for (final Integer n3 : numbers3)
        {
          for (final Integer n4 : numbers4)
          {
            for (final Integer n5 : numbers3)
            {
              @SuppressWarnings("boxing")
              final Long expected3 = ((long) n1.intValue() + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Long actual3 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              @SuppressWarnings("boxing")
              final Long expected4 = ((long) n1.intValue() + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Long actual4 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertThat(actual4, is(equalTo(expected4)));
            }
          }
        }
      }
    }
  }

  @Test
  public final void testTotalIntegerIntegerIntegerIntegerIntegerIntegerArray()
  {
    final Integer[] numbers1 = convertToBoxed(NUMBERS1);
    final Integer[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Integer n1 : numbers1)
    {
      for (final Integer n2 : numbers2)
      {
        for (final Integer n3 : numbers1)
        {
          for (final Integer n4 : numbers2)
          {
            for (final Integer n5 : numbers1)
            {
              @SuppressWarnings("boxing")
              long total1 = (long) n1.intValue() + n2 + n3 + n4 + n5;
              for (final Integer n : numbers1)
                total1 += n.intValue();

              @SuppressWarnings("boxing")
              final Long expected1 = total1;
              @SuppressWarnings("boxing")
              final Long actual1 = total(n1, n2, n3, n4, n5, numbers1);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              @SuppressWarnings("boxing")
              long total2 = (long) n1.intValue() + n2 + n3 + n4 + n5;
              for (final Integer n : numbers2)
                total2 += n.intValue();

              @SuppressWarnings("boxing")
              final Long expected2 = total2;
              @SuppressWarnings("boxing")
              final Long actual2 = total(n1, n2, n3, n4, n5, numbers2);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
            }
          }
        }
      }
    }

    final Integer[] numbers3 = new Integer[HOW_MANY];
    System.arraycopy(convertToBoxed(NUMBERS3), 0, numbers3, 0, HOW_MANY);
    final Integer[] numbers4 = new Integer[HOW_MANY];
    System.arraycopy(convertToBoxed(NUMBERS4), 0, numbers4, 0, HOW_MANY);

    for (final Integer n1 : numbers3)
    {
      for (final Integer n2 : numbers4)
      {
        for (final Integer n3 : numbers3)
        {
          for (final Integer n4 : numbers4)
          {
            for (final Integer n5 : numbers3)
            {
              @SuppressWarnings("boxing")
              long total3 = (long) n1.intValue() + n2 + n3 + n4 + n5;
              for (final Integer n : numbers3)
                total3 += n.intValue();

              @SuppressWarnings("boxing")
              final Long expected3 = total3;
              @SuppressWarnings("boxing")
              final Long actual3 = total(n1, n2, n3, n4, n5, numbers3);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              @SuppressWarnings("boxing")
              long total4 = (long) n1.intValue() + n2 + n3 + n4 + n5;
              for (final Integer n : numbers4)
                total4 += n.intValue();

              @SuppressWarnings("boxing")
              final Long expected4 = total4;
              @SuppressWarnings("boxing")
              final Long actual4 = total(n1, n2, n3, n4, n5, numbers4);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertThat(actual4, is(equalTo(expected4)));
            }
          }
        }
      }
    }
  }

  @Test
  public final void testTotalIntegerArray()
  {
    final Integer[] numbers1 = convertToBoxed(NUMBERS1);
    final Integer[] numbers2 = convertToBoxed(NUMBERS2);

    long total1 = 0;
    for (final Integer n : numbers1)
      total1 += n.intValue();

    long total2 = 0;
    for (final Integer n : numbers2)
      total2 += n.intValue();

    final long expected1 = total1;
    final long actual1 = total(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);
    final long expected2 = total2;
    final long actual2 = total(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertTrue(expected2 == actual2);

    final Integer[] numbers3 = convertToBoxed(NUMBERS3);
    final Integer[] numbers4 = convertToBoxed(NUMBERS4);

    long total3 = 0;
    for (final Integer n : numbers3)
      total3 += n.intValue();

    long total4 = 0;
    for (final Integer n : numbers4)
      total4 += n.intValue();

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
  public final void testTotalIterableOfInteger()
  {
    final Iterable<Integer> numbers1 = asList(convertToBoxed(NUMBERS1));
    final Iterable<Integer> numbers2 = asList(convertToBoxed(NUMBERS2));

    long total1 = 0;
    for (final Integer n : numbers1)
      total1 += n.intValue();

    long total2 = 0;
    for (final Integer n : numbers2)
      total2 += n.intValue();

    final long expected1 = total1;
    final long actual1 = total(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);
    final long expected2 = total2;
    final long actual2 = total(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertTrue(expected2 == actual2);

    final Iterable<Integer> numbers3 = asList(convertToBoxed(NUMBERS3));
    final Iterable<Integer> numbers4 = asList(convertToBoxed(NUMBERS4));

    long total3 = 0;
    for (final Integer n : numbers3)
      total3 += n.intValue();

    long total4 = 0;
    for (final Integer n : numbers4)
      total4 += n.intValue();

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
