/**
 * 
 */
package org.elixirian.kommonlee.util.number;

import static java.util.Arrays.*;
import static org.elixirian.kommonlee.util.number.IntegersTotal.*;
import static org.elixirian.kommonlee.util.number.Numbers.*;
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
 * @version 0.0.1 (2010-11-14)
 */
public class IntegersTotalTest
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
        final int expected = n1 + n2;
        final int actual = sum(n1, n2);
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
        final int expected = n1 + n2;
        final int actual = sum(n1, n2);
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
          final int expected = n1 + n2 + n3;
          final int actual = sum(n1, n2, n3);
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
          final int expected = n1 + n2 + n3;
          final int actual = sum(n1, n2, n3);
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
            final int expected = n1 + n2 + n3 + n4;
            final int actual = sum(n1, n2, n3, n4);
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
            final int expected = n1 + n2 + n3 + n4;
            final int actual = sum(n1, n2, n3, n4);
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
              final int expected = n1 + n2 + n3 + n4 + n5;
              final int actual = sum(n1, n2, n3, n4, n5);
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
              final int expected = n1 + n2 + n3 + n4 + n5;
              final int actual = sum(n1, n2, n3, n4, n5);
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
              @SuppressWarnings("boxing")
              final Integer expected3 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Integer actual3 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              @SuppressWarnings("boxing")
              final Integer expected4 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Integer actual4 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertThat(actual4, is(equalTo(expected4)));
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
              @SuppressWarnings("boxing")
              final Integer expected1 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Integer actual1 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              @SuppressWarnings("boxing")
              final Integer expected2 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Integer actual2 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
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
              int total1 = n1 + n2 + n3 + n4 + n5;
              for (final int n : numbers1)
                total1 += n;

              @SuppressWarnings("boxing")
              final Integer expected3 = total1;
              @SuppressWarnings("boxing")
              final Integer actual3 = sum(n1, n2, n3, n4, n5, numbers1);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              int total2 = n1 + n2 + n3 + n4 + n5;
              for (final int n : numbers2)
                total2 += n;

              @SuppressWarnings("boxing")
              final Integer expected4 = total2;
              @SuppressWarnings("boxing")
              final Integer actual4 = sum(n1, n2, n3, n4, n5, numbers2);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertThat(actual4, is(equalTo(expected4)));
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
              int total3 = n1 + n2 + n3 + n4 + n5;
              for (final int n : numbers3)
                total3 += n;

              @SuppressWarnings("boxing")
              final Integer expected1 = total3;
              @SuppressWarnings("boxing")
              final Integer actual1 = sum(n1, n2, n3, n4, n5, numbers3);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              int total4 = n1 + n2 + n3 + n4 + n5;
              for (final int n : numbers4)
                total4 += n;

              @SuppressWarnings("boxing")
              final Integer expected2 = total4;
              @SuppressWarnings("boxing")
              final Integer actual2 = sum(n1, n2, n3, n4, n5, numbers4);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
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

    int total1 = 0;
    for (final int n : numbers1)
      total1 += n;

    int total2 = 0;
    for (final int n : numbers2)
      total2 += n;

    final int expected1 = total1;
    final int actual1 = sum(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);
    final int expected2 = total2;
    final int actual2 = sum(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertTrue(expected2 == actual2);

    final int[] numbers3 = NUMBERS3;
    final int[] numbers4 = NUMBERS4;

    int total3 = 0;
    for (final int n : numbers3)
      total3 += n;

    int total4 = 0;
    for (final int n : numbers4)
      total4 += n;

    final int expected3 = total3;
    final int actual3 = sum(numbers3);
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertTrue(expected3 == actual3);
    final int expected4 = total4;
    final int actual4 = sum(numbers4);
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
        final int expected = n1 + n2;
        final int actual = total(n1, n2);
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
        final int expected = n1 + n2;
        final int actual = total(n1, n2);
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
          final int expected = n1 + n2 + n3;
          final int actual = total(n1, n2, n3);
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
          final int expected = n1 + n2 + n3;
          final int actual = total(n1, n2, n3);
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
            final int expected = n1 + n2 + n3 + n4;
            final int actual = total(n1, n2, n3, n4);
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
            final int expected = n1 + n2 + n3 + n4;
            final int actual = total(n1, n2, n3, n4);
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
              final int expected = n1 + n2 + n3 + n4 + n5;
              final int actual = total(n1, n2, n3, n4, n5);
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
              final int expected = n1 + n2 + n3 + n4 + n5;
              final int actual = total(n1, n2, n3, n4, n5);
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
              final Integer expected3 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Integer actual3 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              @SuppressWarnings("boxing")
              final Integer expected4 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Integer actual4 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertThat(actual4, is(equalTo(expected4)));
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
              final Integer expected1 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Integer actual1 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              @SuppressWarnings("boxing")
              final Integer expected2 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Integer actual2 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
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
              int total1 = n1 + n2 + n3 + n4 + n5;
              for (final Integer n : numbers1)
                total1 += n.intValue();

              @SuppressWarnings("boxing")
              final Integer expected3 = total1;
              @SuppressWarnings("boxing")
              final Integer actual3 = total(n1, n2, n3, n4, n5, numbers1);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              @SuppressWarnings("boxing")
              int total2 = n1 + n2 + n3 + n4 + n5;
              for (final Integer n : numbers2)
                total2 += n.intValue();

              @SuppressWarnings("boxing")
              final Integer expected4 = total2;
              @SuppressWarnings("boxing")
              final Integer actual4 = total(n1, n2, n3, n4, n5, numbers2);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertThat(actual4, is(equalTo(expected4)));
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
              int total3 = n1 + n2 + n3 + n4 + n5;
              for (final Integer n : numbers3)
                total3 += n.intValue();

              @SuppressWarnings("boxing")
              final Integer expected1 = total3;
              @SuppressWarnings("boxing")
              final Integer actual1 = total(n1, n2, n3, n4, n5, numbers3);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              @SuppressWarnings("boxing")
              int total4 = n1 + n2 + n3 + n4 + n5;
              for (final Integer n : numbers4)
                total4 += n.intValue();

              @SuppressWarnings("boxing")
              final Integer expected2 = total4;
              @SuppressWarnings("boxing")
              final Integer actual2 = total(n1, n2, n3, n4, n5, numbers4);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
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

    int total1 = 0;
    for (final Integer n : numbers1)
      total1 += n.intValue();

    int total2 = 0;
    for (final Integer n : numbers2)
      total2 += n.intValue();

    final int expected1 = total1;
    final int actual1 = total(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);
    final int expected2 = total2;
    final int actual2 = total(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertTrue(expected2 == actual2);

    final Integer[] numbers3 = convertToBoxed(NUMBERS3);
    final Integer[] numbers4 = convertToBoxed(NUMBERS4);

    int total3 = 0;
    for (final Integer n : numbers3)
      total3 += n.intValue();

    int total4 = 0;
    for (final Integer n : numbers4)
      total4 += n.intValue();

    final int expected3 = total3;
    final int actual3 = total(numbers3);
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertTrue(expected3 == actual3);
    final int expected4 = total4;
    final int actual4 = total(numbers4);
    // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
    assertTrue(expected4 == actual4);
  }

  @Test
  public final void testTotalIterableOfInteger()
  {
    final Iterable<Integer> numbers1 = asList(convertToBoxed(NUMBERS1));
    final Iterable<Integer> numbers2 = asList(convertToBoxed(NUMBERS2));

    int total1 = 0;
    for (final Integer n : numbers1)
      total1 += n.intValue();

    int total2 = 0;
    for (final Integer n : numbers2)
      total2 += n.intValue();

    final int expected1 = total1;
    final int actual1 = total(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);
    final int expected2 = total2;
    final int actual2 = total(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertTrue(expected2 == actual2);

    final Iterable<Integer> numbers3 = asList(convertToBoxed(NUMBERS3));
    final Iterable<Integer> numbers4 = asList(convertToBoxed(NUMBERS4));

    int total3 = 0;
    for (final Integer n : numbers3)
      total3 += n.intValue();

    int total4 = 0;
    for (final Integer n : numbers4)
      total4 += n.intValue();

    final int expected3 = total3;
    final int actual3 = total(numbers3);
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertTrue(expected3 == actual3);
    final int expected4 = total4;
    final int actual4 = total(numbers4);
    // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
    assertTrue(expected4 == actual4);
  }

}
