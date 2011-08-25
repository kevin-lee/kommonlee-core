/**
 * 
 */
package org.elixirian.kommonlee.util.number;

import static java.util.Arrays.*;
import static org.elixirian.kommonlee.util.number.BytesTotal.*;
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
public class BytesTotalTest
{
  private static final int HOW_MANY = 30;

  /* @formatter:off */
	private static final byte[] NUMBERS1 = { Byte.MIN_VALUE, -5,   -4,  -3,  -2,  -1,  0,  1,  2,  3,  4,  5,  Byte.MAX_VALUE };
	private static final byte[] NUMBERS2 = { Byte.MIN_VALUE, -100, -90, -80, -70, -60, 10, 20, 30, 40, 50, 60, Byte.MAX_VALUE };
	/* @formatter:on */

  private static final Random random = new Random();
  private static final byte[] NUMBERS3 = getRandomNumbers(50);
  private static final byte[] NUMBERS4 = getRandomNumbers(50);

  private static byte[] getRandomNumbers(final int length)
  {
    final byte[] numbers = new byte[length];
    random.nextBytes(numbers);
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
  public final void testSumByteByte()
  {
    final byte[] numbers1 = NUMBERS1;
    final byte[] numbers2 = NUMBERS2;

    for (final byte n1 : numbers1)
    {
      for (final byte n2 : numbers2)
      {
        final int expected = n1 + n2;
        final int actual = sum(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertTrue(expected == actual);
      }
    }

    final byte[] numbers3 = NUMBERS3;
    final byte[] numbers4 = NUMBERS4;

    for (final byte n1 : numbers3)
    {
      for (final byte n2 : numbers4)
      {
        final int expected = n1 + n2;
        final int actual = sum(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertTrue(expected == actual);
      }
    }
  }

  @Test
  public final void testSumByteByteByte()
  {
    final byte[] numbers1 = NUMBERS1;
    final byte[] numbers2 = NUMBERS2;

    for (final byte n1 : numbers1)
    {
      for (final byte n2 : numbers2)
      {
        for (final byte n3 : numbers1)
        {
          final int expected = n1 + n2 + n3;
          final int actual = sum(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertTrue(expected == actual);
        }
      }
    }

    final byte[] numbers3 = NUMBERS3;
    final byte[] numbers4 = NUMBERS4;

    for (final byte n1 : numbers3)
    {
      for (final byte n2 : numbers4)
      {
        for (final byte n3 : numbers3)
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
  public final void testSumByteByteByteByte()
  {
    final byte[] numbers1 = NUMBERS1;
    final byte[] numbers2 = NUMBERS2;

    for (final byte n1 : numbers1)
    {
      for (final byte n2 : numbers2)
      {
        for (final byte n3 : numbers1)
        {
          for (final byte n4 : numbers2)
          {
            final int expected = n1 + n2 + n3 + n4;
            final int actual = sum(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertTrue(expected == actual);
          }
        }
      }
    }

    final byte[] numbers3 = NUMBERS3;
    final byte[] numbers4 = NUMBERS4;

    for (final byte n1 : numbers3)
    {
      for (final byte n2 : numbers4)
      {
        for (final byte n3 : numbers3)
        {
          for (final byte n4 : numbers4)
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
  public final void testSumByteByteByteByteByte()
  {
    final byte[] numbers1 = NUMBERS1;
    final byte[] numbers2 = NUMBERS2;

    for (final byte n1 : numbers1)
    {
      for (final byte n2 : numbers2)
      {
        for (final byte n3 : numbers1)
        {
          for (final byte n4 : numbers2)
          {
            for (final byte n5 : numbers1)
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

    final byte[] numbers3 = NUMBERS3;
    final byte[] numbers4 = NUMBERS4;

    for (final byte n1 : numbers3)
    {
      for (final byte n2 : numbers4)
      {
        for (final byte n3 : numbers3)
        {
          for (final byte n4 : numbers4)
          {
            for (final byte n5 : numbers3)
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
  public final void testSumByteByteByteByteByteMoreBytes()
  {
    final byte[] numbers1 = NUMBERS1;
    final byte[] numbers2 = NUMBERS2;

    for (final byte n1 : numbers1)
    {
      for (final byte n2 : numbers2)
      {
        for (final byte n3 : numbers1)
        {
          for (final byte n4 : numbers2)
          {
            for (final byte n5 : numbers1)
            {
              final int expected1 = n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5;
              final int actual1 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertTrue(expected1 == actual1);
              final int expected2 = n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5;
              final int actual2 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertTrue(expected2 == actual2);
            }
          }
        }
      }
    }

    final byte[] numbers3 = new byte[HOW_MANY];
    System.arraycopy(NUMBERS3, 0, numbers3, 0, HOW_MANY);
    final byte[] numbers4 = new byte[HOW_MANY];
    System.arraycopy(NUMBERS4, 0, numbers4, 0, HOW_MANY);

    for (final byte n1 : numbers3)
    {
      for (final byte n2 : numbers4)
      {
        for (final byte n3 : numbers3)
        {
          for (final byte n4 : numbers4)
          {
            for (final byte n5 : numbers3)
            {
              final int expected3 = n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5;
              final int actual3 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertTrue(expected3 == actual3);
              final int expected4 = n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5;
              final int actual4 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertTrue(expected4 == actual4);
            }
          }
        }
      }
    }
  }

  @Test
  public final void testSumByteByteByteByteByteByteArray()
  {
    final byte[] numbers1 = NUMBERS1;
    final byte[] numbers2 = NUMBERS2;

    for (final byte n1 : numbers1)
    {
      for (final byte n2 : numbers2)
      {
        for (final byte n3 : numbers1)
        {
          for (final byte n4 : numbers2)
          {
            for (final byte n5 : numbers1)
            {
              int total1 = n1 + n2 + n3 + n4 + n5;
              for (final byte n : numbers1)
                total1 += n;

              @SuppressWarnings("boxing")
              final Integer expected1 = total1;
              @SuppressWarnings("boxing")
              final Integer actual1 = sum(n1, n2, n3, n4, n5, numbers1);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              int total2 = n1 + n2 + n3 + n4 + n5;
              for (final byte n : numbers2)
                total2 += n;

              @SuppressWarnings("boxing")
              final Integer expected2 = total2;
              @SuppressWarnings("boxing")
              final Integer actual2 = sum(n1, n2, n3, n4, n5, numbers2);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
            }
          }
        }
      }
    }

    final byte[] numbers3 = new byte[HOW_MANY];
    System.arraycopy(NUMBERS3, 0, numbers3, 0, HOW_MANY);
    final byte[] numbers4 = new byte[HOW_MANY];
    System.arraycopy(NUMBERS4, 0, numbers4, 0, HOW_MANY);

    int total3 = 0;
    for (final byte n : numbers3)
      total3 += n;

    int total4 = 0;
    for (final byte n : numbers4)
      total4 += n;

    for (final byte n1 : numbers3)
    {
      for (final byte n2 : numbers4)
      {
        for (final byte n3 : numbers3)
        {
          for (final byte n4 : numbers4)
          {
            for (final byte n5 : numbers3)
            {
              final int expected3 = n1 + n2 + n3 + n4 + n5 + total3;
              final int actual3 = sum(n1, n2, n3, n4, n5, numbers3);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertTrue(expected3 == actual3);
              final int expected4 = n1 + n2 + n3 + n4 + n5 + total4;
              final int actual4 = sum(n1, n2, n3, n4, n5, numbers4);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertTrue(expected4 == actual4);
            }
          }
        }
      }
    }
  }

  @Test
  public final void testSumByteArray()
  {
    final byte[] numbers1 = NUMBERS1;
    final byte[] numbers2 = NUMBERS2;

    int total1 = 0;
    for (final byte n : numbers1)
      total1 += n;

    int total2 = 0;
    for (final byte n : numbers2)
      total2 += n;

    final int expected1 = total1;
    final int actual1 = sum(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);
    final int expected2 = total2;
    final int actual2 = sum(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertTrue(expected2 == actual2);

    final byte[] numbers3 = NUMBERS3;
    final byte[] numbers4 = NUMBERS4;

    int total3 = 0;
    for (final byte n : numbers3)
      total3 += n;

    int total4 = 0;
    for (final byte n : numbers4)
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
  public final void testTotalBoxedByteBoxedByte()
  {
    final Byte[] numbers1 = convertToBoxed(NUMBERS1);
    final Byte[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Byte n1 : numbers1)
    {
      for (final Byte n2 : numbers2)
      {
        final int expected = n1.byteValue() + n2.byteValue();
        final int actual = total(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertTrue(expected == actual);
      }
    }

    final Byte[] numbers3 = convertToBoxed(NUMBERS3);
    final Byte[] numbers4 = convertToBoxed(NUMBERS4);

    for (final Byte n1 : numbers3)
    {
      for (final Byte n2 : numbers4)
      {
        final int expected = n1.byteValue() + n2.byteValue();
        final int actual = total(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertTrue(expected == actual);
      }
    }
  }

  @Test
  public final void testTotalBoxedByteBoxedByteBoxedByte()
  {
    final Byte[] numbers1 = convertToBoxed(NUMBERS1);
    final Byte[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Byte n1 : numbers1)
    {
      for (final Byte n2 : numbers2)
      {
        for (final Byte n3 : numbers1)
        {
          final int expected = n1.byteValue() + n2.byteValue() + n3.byteValue();
          final int actual = total(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertTrue(expected == actual);
        }
      }
    }

    final Byte[] numbers3 = convertToBoxed(NUMBERS3);
    final Byte[] numbers4 = convertToBoxed(NUMBERS4);

    for (final Byte n1 : numbers3)
    {
      for (final Byte n2 : numbers4)
      {
        for (final Byte n3 : numbers3)
        {
          final int expected = n1.byteValue() + n2.byteValue() + n3.byteValue();
          final int actual = total(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertTrue(expected == actual);
        }
      }
    }
  }

  @Test
  public final void testTotalBoxedByteBoxedByteBoxedByteBoxedByte()
  {
    final Byte[] numbers1 = convertToBoxed(NUMBERS1);
    final Byte[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Byte n1 : numbers1)
    {
      for (final Byte n2 : numbers2)
      {
        for (final Byte n3 : numbers1)
        {
          for (final Byte n4 : numbers2)
          {
            final int expected = n1.byteValue() + n2.byteValue() + n3.byteValue() + n4.byteValue();
            final int actual = total(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertTrue(expected == actual);
          }
        }
      }
    }

    final Byte[] numbers3 = convertToBoxed(NUMBERS3);
    final Byte[] numbers4 = convertToBoxed(NUMBERS4);

    for (final Byte n1 : numbers3)
    {
      for (final Byte n2 : numbers4)
      {
        for (final Byte n3 : numbers3)
        {
          for (final Byte n4 : numbers4)
          {
            final int expected = n1.byteValue() + n2.byteValue() + n3.byteValue() + n4.byteValue();
            final int actual = total(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertTrue(expected == actual);
          }
        }
      }
    }
  }

  @Test
  public final void testTotalBoxedByteBoxedByteBoxedByteBoxedByteBoxedByte()
  {
    final Byte[] numbers1 = convertToBoxed(NUMBERS1);
    final Byte[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Byte n1 : numbers1)
    {
      for (final Byte n2 : numbers2)
      {
        for (final Byte n3 : numbers1)
        {
          for (final Byte n4 : numbers2)
          {
            for (final Byte n5 : numbers1)
            {
              final int expected = n1.byteValue() + n2.byteValue() + n3.byteValue() + n4.byteValue() + n5.byteValue();
              final int actual = total(n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected + "\nactual:   " + actual);
              assertTrue(expected == actual);
            }
          }
        }
      }
    }

    final Byte[] numbers3 = convertToBoxed(NUMBERS3);
    final Byte[] numbers4 = convertToBoxed(NUMBERS4);

    for (final Byte n1 : numbers3)
    {
      for (final Byte n2 : numbers4)
      {
        for (final Byte n3 : numbers3)
        {
          for (final Byte n4 : numbers4)
          {
            for (final Byte n5 : numbers3)
            {
              final int expected = n1.byteValue() + n2.byteValue() + n3.byteValue() + n4.byteValue() + n5.byteValue();
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
  public final void testTotalBoxedByteBoxedByteBoxedByteBoxedByteBoxedByteMoreBoxedBytes()
  {
    final Byte[] numbers1 = convertToBoxed(NUMBERS1);
    final Byte[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Byte n1 : numbers1)
    {
      for (final Byte n2 : numbers2)
      {
        for (final Byte n3 : numbers1)
        {
          for (final Byte n4 : numbers2)
          {
            for (final Byte n5 : numbers1)
            {
              @SuppressWarnings("boxing")
              final int expected1 = n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5;
              final int actual1 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertTrue(expected1 == actual1);

              @SuppressWarnings("boxing")
              final int expected2 = n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5;
              final int actual2 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertTrue(expected2 == actual2);
            }
          }
        }
      }
    }

    final Byte[] numbers3 = new Byte[HOW_MANY];
    System.arraycopy(convertToBoxed(NUMBERS3), 0, numbers3, 0, HOW_MANY);
    final Byte[] numbers4 = new Byte[HOW_MANY];
    System.arraycopy(convertToBoxed(NUMBERS4), 0, numbers4, 0, HOW_MANY);

    for (final Byte n1 : numbers3)
    {
      for (final Byte n2 : numbers4)
      {
        for (final Byte n3 : numbers3)
        {
          for (final Byte n4 : numbers4)
          {
            for (final Byte n5 : numbers3)
            {
              @SuppressWarnings("boxing")
              final int expected3 = n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5;
              final int actual3 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertTrue(expected3 == actual3);

              @SuppressWarnings("boxing")
              final int expected4 = n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5;
              final int actual4 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertTrue(expected4 == actual4);
            }
          }
        }
      }
    }
  }

  @Test
  public final void testTotalBoxedByteBoxedByteBoxedByteBoxedByteBoxedByteBoxedByteArray()
  {
    final Byte[] numbers1 = convertToBoxed(NUMBERS1);
    final Byte[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Byte n1 : numbers1)
    {
      for (final Byte n2 : numbers2)
      {
        for (final Byte n3 : numbers1)
        {
          for (final Byte n4 : numbers2)
          {
            for (final Byte n5 : numbers1)
            {
              @SuppressWarnings("boxing")
              int total1 = n1 + n2 + n3 + n4 + n5;
              for (final Byte n : numbers1)
                total1 += n.byteValue();

              @SuppressWarnings("boxing")
              final Integer expected1 = total1;
              @SuppressWarnings("boxing")
              final Integer actual1 = total(n1, n2, n3, n4, n5, numbers1);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              @SuppressWarnings("boxing")
              int total2 = n1 + n2 + n3 + n4 + n5;
              for (final Byte n : numbers2)
                total2 += n.byteValue();

              @SuppressWarnings("boxing")
              final Integer expected2 = total2;
              @SuppressWarnings("boxing")
              final Integer actual2 = total(n1, n2, n3, n4, n5, numbers2);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
            }
          }
        }
      }
    }

    final Byte[] numbers3 = new Byte[HOW_MANY];
    System.arraycopy(convertToBoxed(NUMBERS3), 0, numbers3, 0, HOW_MANY);
    final Byte[] numbers4 = new Byte[HOW_MANY];
    System.arraycopy(convertToBoxed(NUMBERS4), 0, numbers4, 0, HOW_MANY);

    int total3 = 0;
    for (final Byte n : numbers3)
      total3 += n.byteValue();

    int total4 = 0;
    for (final Byte n : numbers4)
      total4 += n.byteValue();

    for (final Byte n1 : numbers3)
    {
      for (final Byte n2 : numbers4)
      {
        for (final Byte n3 : numbers3)
        {
          for (final Byte n4 : numbers4)
          {
            for (final Byte n5 : numbers3)
            {
              @SuppressWarnings("boxing")
              final int expected3 = n1 + n2 + n3 + n4 + n5 + total3;
              final int actual3 = total(n1, n2, n3, n4, n5, numbers3);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertTrue(expected3 == actual3);

              @SuppressWarnings("boxing")
              final int expected4 = n1 + n2 + n3 + n4 + n5 + total4;
              final int actual4 = total(n1, n2, n3, n4, n5, numbers4);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertTrue(expected4 == actual4);
            }
          }
        }
      }
    }
  }

  @Test
  public final void testTotalBoxedByteArray()
  {
    final Byte[] numbers1 = convertToBoxed(NUMBERS1);
    final Byte[] numbers2 = convertToBoxed(NUMBERS2);

    int total1 = 0;
    for (final Byte n : numbers1)
      total1 += n.byteValue();

    int total2 = 0;
    for (final Byte n : numbers2)
      total2 += n.byteValue();

    final int expected1 = total1;
    final int actual1 = total(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);
    final int expected2 = total2;
    final int actual2 = total(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertTrue(expected2 == actual2);

    final Byte[] numbers3 = convertToBoxed(NUMBERS3);
    final Byte[] numbers4 = convertToBoxed(NUMBERS4);

    int total3 = 0;
    for (final Byte n : numbers3)
      total3 += n.byteValue();

    int total4 = 0;
    for (final Byte n : numbers4)
      total4 += n.byteValue();

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
  public final void testTotalIterableOfByte()
  {
    final Iterable<Byte> numbers1 = asList(convertToBoxed(NUMBERS1));
    final Iterable<Byte> numbers2 = asList(convertToBoxed(NUMBERS2));

    int total1 = 0;
    for (final Byte n : numbers1)
      total1 += n.byteValue();

    int total2 = 0;
    for (final Byte n : numbers2)
      total2 += n.byteValue();

    final int expected1 = total1;
    final int actual1 = total(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);
    final int expected2 = total2;
    final int actual2 = total(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertTrue(expected2 == actual2);

    final Iterable<Byte> numbers3 = asList(convertToBoxed(NUMBERS3));
    final Iterable<Byte> numbers4 = asList(convertToBoxed(NUMBERS4));

    int total3 = 0;
    for (final Byte n : numbers3)
      total3 += n.byteValue();

    int total4 = 0;
    for (final Byte n : numbers4)
      total4 += n.byteValue();

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
