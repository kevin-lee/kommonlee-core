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
package org.elixirian.kommonlee.util.number;

import static java.util.Arrays.*;
import static org.elixirian.kommonlee.util.number.Numbers.*;
import static org.elixirian.kommonlee.util.number.ShortTotal.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Random;

import org.elixirian.kommonlee.type.functional.Function1;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-14)
 */
public class ShortTotalTest
{
  private static final int HOW_MANY = 30;

  /* @formatter:off */
	private static final short[] NUMBERS1 =
	  { Short.MIN_VALUE, -7,    -6,   -5,  -4,  -3,  -2, -1,  0,  1,  2,  3,  4,  5,   6 ,   Short.MAX_VALUE, Short.MAX_VALUE, 16383 };
	private static final short[] NUMBERS2 =
	  { Short.MIN_VALUE, -1000, -100, -90, -80, -70, -60, 10, 20, 30, 40, 50, 60, 100, 1000, Short.MAX_VALUE };
	/* @formatter:on */

  private static final Random random = new Random();
  private static final short[] NUMBERS3 = getRandomNumbers(50);
  private static final short[] NUMBERS4 = getRandomNumbers(50);

  private static short[] getRandomNumbers(final int length)
  {
    final short[] numbers = new short[length];
    for (int i = 0; i < length; i++)
    {
      numbers[i] = (short) random.nextInt();
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
  public final void testSumShortShort()
  {
    final short[] numbers1 = NUMBERS1;
    final short[] numbers2 = NUMBERS2;

    for (final short n1 : numbers1)
    {
      for (final short n2 : numbers2)
      {
        final int expected = n1 + n2;
        final int actual = sum(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertTrue(expected == actual);
      }
    }

    final short[] numbers3 = NUMBERS3;
    final short[] numbers4 = NUMBERS4;

    for (final short n1 : numbers3)
    {
      for (final short n2 : numbers4)
      {
        final int expected = n1 + n2;
        final int actual = sum(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertTrue(expected == actual);
      }
    }
  }

  @Test
  public final void testSumShortShortShort()
  {
    final short[] numbers1 = NUMBERS1;
    final short[] numbers2 = NUMBERS2;

    for (final short n1 : numbers1)
    {
      for (final short n2 : numbers2)
      {
        for (final short n3 : numbers1)
        {
          final int expected = n1 + n2 + n3;
          final int actual = sum(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertTrue(expected == actual);
        }
      }
    }

    final short[] numbers3 = NUMBERS3;
    final short[] numbers4 = NUMBERS4;

    for (final short n1 : numbers3)
    {
      for (final short n2 : numbers4)
      {
        for (final short n3 : numbers3)
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
  public final void testSumShortShortShortShort()
  {
    final short[] numbers1 = NUMBERS1;
    final short[] numbers2 = NUMBERS2;

    for (final short n1 : numbers1)
    {
      for (final short n2 : numbers2)
      {
        for (final short n3 : numbers1)
        {
          for (final short n4 : numbers2)
          {
            final int expected = n1 + n2 + n3 + n4;
            final int actual = sum(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertTrue(expected == actual);
          }
        }
      }
    }

    final short[] numbers3 = NUMBERS3;
    final short[] numbers4 = NUMBERS4;

    for (final short n1 : numbers3)
    {
      for (final short n2 : numbers4)
      {
        for (final short n3 : numbers3)
        {
          for (final short n4 : numbers4)
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
  public final void testSumShortShortShortShortShort()
  {
    final short[] numbers1 = NUMBERS1;
    final short[] numbers2 = NUMBERS2;

    for (final short n1 : numbers1)
    {
      for (final short n2 : numbers2)
      {
        for (final short n3 : numbers1)
        {
          for (final short n4 : numbers2)
          {
            for (final short n5 : numbers1)
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

    final short[] numbers3 = NUMBERS3;
    final short[] numbers4 = NUMBERS4;

    for (final short n1 : numbers3)
    {
      for (final short n2 : numbers4)
      {
        for (final short n3 : numbers3)
        {
          for (final short n4 : numbers4)
          {
            for (final short n5 : numbers3)
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
  public final void testSumShortShortShortShortShortMoreShorts()
  {
    final short[] numbers1 = NUMBERS1;
    final short[] numbers2 = NUMBERS2;

    for (final short n1 : numbers1)
    {
      for (final short n2 : numbers2)
      {
        for (final short n3 : numbers1)
        {
          for (final short n4 : numbers2)
          {
            for (final short n5 : numbers1)
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

    final short[] numbers3 = new short[HOW_MANY];
    System.arraycopy(NUMBERS3, 0, numbers3, 0, HOW_MANY);
    final short[] numbers4 = new short[HOW_MANY];
    System.arraycopy(NUMBERS4, 0, numbers4, 0, HOW_MANY);

    for (final short n1 : numbers3)
    {
      for (final short n2 : numbers4)
      {
        for (final short n3 : numbers3)
        {
          for (final short n4 : numbers4)
          {
            for (final short n5 : numbers3)
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
  }

  @Test
  public final void testSumShortShortShortShortShortShortArray()
  {
    final short[] numbers1 = NUMBERS1;
    final short[] numbers2 = NUMBERS2;

    for (final short n1 : numbers1)
    {
      for (final short n2 : numbers2)
      {
        for (final short n3 : numbers1)
        {
          for (final short n4 : numbers2)
          {
            for (final short n5 : numbers1)
            {
              int total1 = n1 + n2 + n3 + n4 + n5;
              for (final short n : numbers1)
                total1 += n;

              @SuppressWarnings("boxing")
              final Integer expected1 = total1;
              @SuppressWarnings("boxing")
              final Integer actual1 = sum(n1, n2, n3, n4, n5, numbers1);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              int total2 = n1 + n2 + n3 + n4 + n5;
              for (final short n : numbers2)
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

    final short[] numbers3 = new short[HOW_MANY];
    System.arraycopy(NUMBERS3, 0, numbers3, 0, HOW_MANY);
    final short[] numbers4 = new short[HOW_MANY];
    System.arraycopy(NUMBERS4, 0, numbers4, 0, HOW_MANY);

    for (final short n1 : numbers3)
    {
      for (final short n2 : numbers4)
      {
        for (final short n3 : numbers3)
        {
          for (final short n4 : numbers4)
          {
            for (final short n5 : numbers3)
            {
              int total3 = n1 + n2 + n3 + n4 + n5;
              for (final short n : numbers3)
                total3 += n;

              @SuppressWarnings("boxing")
              final Integer expected3 = total3;
              @SuppressWarnings("boxing")
              final Integer actual3 = sum(n1, n2, n3, n4, n5, numbers3);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              int total4 = n1 + n2 + n3 + n4 + n5;
              for (final short n : numbers4)
                total4 += n;

              @SuppressWarnings("boxing")
              final Integer expected4 = total4;
              @SuppressWarnings("boxing")
              final Integer actual4 = sum(n1, n2, n3, n4, n5, numbers4);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertThat(actual4, is(equalTo(expected4)));
            }
          }
        }
      }
    }
  }

  @Test
  public final void testSumShortArray()
  {
    final short[] numbers1 = NUMBERS1;
    final short[] numbers2 = NUMBERS2;

    int total1 = 0;
    for (final short n : numbers1)
      total1 += n;

    int total2 = 0;
    for (final short n : numbers2)
      total2 += n;

    final int expected1 = total1;
    final int actual1 = sum(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);
    final int expected2 = total2;
    final int actual2 = sum(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertTrue(expected2 == actual2);

    final short[] numbers3 = NUMBERS3;
    final short[] numbers4 = NUMBERS4;

    int total3 = 0;
    for (final short n : numbers3)
      total3 += n;

    int total4 = 0;
    for (final short n : numbers4)
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
  public final void testTotalBoxedShortBoxedShort()
  {
    final Short[] numbers1 = convertToBoxed(NUMBERS1);
    final Short[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Short n1 : numbers1)
    {
      for (final Short n2 : numbers2)
      {
        final int expected = n1.shortValue() + n2.shortValue();
        final int actual = total(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertTrue(expected == actual);
      }
    }

    final Short[] numbers3 = convertToBoxed(NUMBERS3);
    final Short[] numbers4 = convertToBoxed(NUMBERS4);

    for (final Short n1 : numbers3)
    {
      for (final Short n2 : numbers4)
      {
        final int expected = n1.shortValue() + n2.shortValue();
        final int actual = total(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertTrue(expected == actual);
      }
    }
  }

  @Test
  public final void testTotalBoxedShortBoxedShortBoxedShort()
  {
    final Short[] numbers1 = convertToBoxed(NUMBERS1);
    final Short[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Short n1 : numbers1)
    {
      for (final Short n2 : numbers2)
      {
        for (final Short n3 : numbers1)
        {
          final int expected = n1.shortValue() + n2.shortValue() + n3.shortValue();
          final int actual = total(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertTrue(expected == actual);
        }
      }
    }

    final Short[] numbers3 = convertToBoxed(NUMBERS3);
    final Short[] numbers4 = convertToBoxed(NUMBERS4);

    for (final Short n1 : numbers3)
    {
      for (final Short n2 : numbers4)
      {
        for (final Short n3 : numbers3)
        {
          final int expected = n1.shortValue() + n2.shortValue() + n3.shortValue();
          final int actual = total(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertTrue(expected == actual);
        }
      }
    }
  }

  @Test
  public final void testTotalBoxedShortBoxedShortBoxedShortBoxedShort()
  {
    final Short[] numbers1 = convertToBoxed(NUMBERS1);
    final Short[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Short n1 : numbers1)
    {
      for (final Short n2 : numbers2)
      {
        for (final Short n3 : numbers1)
        {
          for (final Short n4 : numbers2)
          {
            final int expected = n1.shortValue() + n2.shortValue() + n3.shortValue() + n4.shortValue();
            final int actual = total(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertTrue(expected == actual);
          }
        }
      }
    }

    final Short[] numbers3 = convertToBoxed(NUMBERS3);
    final Short[] numbers4 = convertToBoxed(NUMBERS4);

    for (final Short n1 : numbers3)
    {
      for (final Short n2 : numbers4)
      {
        for (final Short n3 : numbers3)
        {
          for (final Short n4 : numbers4)
          {
            final int expected = n1.shortValue() + n2.shortValue() + n3.shortValue() + n4.shortValue();
            final int actual = total(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertTrue(expected == actual);
          }
        }
      }
    }
  }

  @Test
  public final void testTotalBoxedShortBoxedShortBoxedShortBoxedShortBoxedShort()
  {
    final Short[] numbers1 = convertToBoxed(NUMBERS1);
    final Short[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Short n1 : numbers1)
    {
      for (final Short n2 : numbers2)
      {
        for (final Short n3 : numbers1)
        {
          for (final Short n4 : numbers2)
          {
            for (final Short n5 : numbers1)
            {
              final int expected =
                n1.shortValue() + n2.shortValue() + n3.shortValue() + n4.shortValue() + n5.shortValue();
              final int actual = total(n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected + "\nactual:   " + actual);
              assertTrue(expected == actual);
            }
          }
        }
      }
    }

    final Short[] numbers3 = convertToBoxed(NUMBERS3);
    final Short[] numbers4 = convertToBoxed(NUMBERS4);

    for (final Short n1 : numbers3)
    {
      for (final Short n2 : numbers4)
      {
        for (final Short n3 : numbers3)
        {
          for (final Short n4 : numbers4)
          {
            for (final Short n5 : numbers3)
            {
              final int expected =
                n1.shortValue() + n2.shortValue() + n3.shortValue() + n4.shortValue() + n5.shortValue();
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
  public final void testTotalBoxedShortBoxedShortBoxedShortBoxedShortBoxedShortMoreBoxedShorts()
  {
    final Short[] numbers1 = convertToBoxed(NUMBERS1);
    final Short[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Short n1 : numbers1)
    {
      for (final Short n2 : numbers2)
      {
        for (final Short n3 : numbers1)
        {
          for (final Short n4 : numbers2)
          {
            for (final Short n5 : numbers1)
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

    final Short[] numbers3 = new Short[HOW_MANY];
    System.arraycopy(convertToBoxed(NUMBERS3), 0, numbers3, 0, HOW_MANY);
    final Short[] numbers4 = new Short[HOW_MANY];
    System.arraycopy(convertToBoxed(NUMBERS4), 0, numbers4, 0, HOW_MANY);

    for (final Short n1 : numbers3)
    {
      for (final Short n2 : numbers4)
      {
        for (final Short n3 : numbers3)
        {
          for (final Short n4 : numbers4)
          {
            for (final Short n5 : numbers3)
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
  }

  @Test
  public final void testTotalBoxedShortBoxedShortBoxedShortBoxedShortBoxedShortBoxedShortArray()
  {
    final Short[] numbers1 = convertToBoxed(NUMBERS1);
    final Short[] numbers2 = convertToBoxed(NUMBERS2);

    for (final Short n1 : numbers1)
    {
      for (final Short n2 : numbers2)
      {
        for (final Short n3 : numbers1)
        {
          for (final Short n4 : numbers2)
          {
            for (final Short n5 : numbers1)
            {
              @SuppressWarnings("boxing")
              int total1 = n1 + n2 + n3 + n4 + n5;
              for (final Short n : numbers1)
                total1 += n.shortValue();

              @SuppressWarnings("boxing")
              final Integer expected1 = total1;
              @SuppressWarnings("boxing")
              final Integer actual1 = total(n1, n2, n3, n4, n5, numbers1);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              @SuppressWarnings("boxing")
              int total2 = n1 + n2 + n3 + n4 + n5;
              for (final Short n : numbers2)
                total2 += n.shortValue();

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

    final Short[] numbers3 = new Short[HOW_MANY];
    System.arraycopy(convertToBoxed(NUMBERS3), 0, numbers3, 0, HOW_MANY);
    final Short[] numbers4 = new Short[HOW_MANY];
    System.arraycopy(convertToBoxed(NUMBERS4), 0, numbers4, 0, HOW_MANY);

    for (final Short n1 : numbers3)
    {
      for (final Short n2 : numbers4)
      {
        for (final Short n3 : numbers3)
        {
          for (final Short n4 : numbers4)
          {
            for (final Short n5 : numbers3)
            {
              @SuppressWarnings("boxing")
              int total3 = n1 + n2 + n3 + n4 + n5;
              for (final Short n : numbers3)
                total3 += n.shortValue();

              @SuppressWarnings("boxing")
              final Integer expected3 = total3;
              @SuppressWarnings("boxing")
              final Integer actual3 = total(n1, n2, n3, n4, n5, numbers3);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              @SuppressWarnings("boxing")
              int total4 = n1 + n2 + n3 + n4 + n5;
              for (final Short n : numbers4)
                total4 += n.shortValue();

              @SuppressWarnings("boxing")
              final Integer expected4 = total4;
              @SuppressWarnings("boxing")
              final Integer actual4 = total(n1, n2, n3, n4, n5, numbers4);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertThat(actual4, is(equalTo(expected4)));
            }
          }
        }
      }
    }
  }

  @Test
  public final void testTotalBoxedShortArray()
  {
    final Short[] numbers1 = convertToBoxed(NUMBERS1);
    final Short[] numbers2 = convertToBoxed(NUMBERS2);

    int total1 = 0;
    for (final Short n : numbers1)
      total1 += n.shortValue();

    int total2 = 0;
    for (final Short n : numbers2)
      total2 += n.shortValue();

    final int expected1 = total1;
    final int actual1 = total(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);
    final int expected2 = total2;
    final int actual2 = total(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertTrue(expected2 == actual2);

    final Short[] numbers3 = convertToBoxed(NUMBERS3);
    final Short[] numbers4 = convertToBoxed(NUMBERS4);

    int total3 = 0;
    for (final Short n : numbers3)
      total3 += n.shortValue();

    int total4 = 0;
    for (final Short n : numbers4)
      total4 += n.shortValue();

    final int expected3 = total3;
    final int actual3 = total(numbers3);
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertTrue(expected3 == actual3);
    final int expected4 = total4;
    final int actual4 = total(numbers4);
    // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
    assertTrue(expected4 == actual4);
  }

  @SuppressWarnings({ "unchecked", "boxing" })
  private static final ValueContainer<Short>[] VALUE_CONTAINERS = new ValueContainer[] {
      new ValueContainer<Short>(Short.MIN_VALUE), new ValueContainer<Short>((short) -5),
      new ValueContainer<Short>((short) -1), new ValueContainer<Short>((short) 0),
      new ValueContainer<Short>((short) 1), new ValueContainer<Short>((short) 3), new ValueContainer<Short>((short) 5),
      new ValueContainer<Short>(Short.MAX_VALUE) };

  private static final ValueContainer<Short>[] RANDOM_VALUE_CONTAINERS = getRandomValues(50);

  private static ValueContainer<Short>[] getRandomValues(final int length)
  {
    @SuppressWarnings("unchecked")
    final ValueContainer<Short>[] values = new ValueContainer[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final ValueContainer<Short> valueContainer =
        new ValueContainer<Short>((short) random.nextInt(Short.MAX_VALUE + 1));
      values[i] = valueContainer;
    }
    return values;
  }

  @Test
  public final void testTotalArrayOfT()
  {
    final ValueContainer<Short>[] numbers1 = VALUE_CONTAINERS;

    int total1 = 0;
    for (final ValueContainer<Short> n : numbers1)
      total1 += n.getValue();

    final int expected1 = total1;
    final int actual1 = total(numbers1, new Function1<ValueContainer<Short>, Short>() {
      @Override
      public Short apply(final ValueContainer<Short> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);

    final ValueContainer<Short>[] numbers2 = RANDOM_VALUE_CONTAINERS;

    int total2 = 0;
    for (final ValueContainer<Short> n : numbers2)
      total2 += n.getValue();

    final int expected2 = total2;
    final int actual2 = total(numbers2, new Function1<ValueContainer<Short>, Short>() {
      @Override
      public Short apply(final ValueContainer<Short> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertTrue(expected2 == actual2);
  }

  @Test
  public void testTotalIterableOfShort()
  {
    final Iterable<Short> numbers1 = asList(convertToBoxed(NUMBERS1));
    final Iterable<Short> numbers2 = asList(convertToBoxed(NUMBERS2));

    int total1 = 0;
    for (final Short n : numbers1)
      total1 += n.shortValue();

    int total2 = 0;
    for (final Short n : numbers2)
      total2 += n.shortValue();

    final int expected1 = total1;
    final int actual1 = total(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);
    final int expected2 = total2;
    final int actual2 = total(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertTrue(expected2 == actual2);

    final Iterable<Short> numbers3 = asList(convertToBoxed(NUMBERS3));
    final Iterable<Short> numbers4 = asList(convertToBoxed(NUMBERS4));

    int total3 = 0;
    for (final Short n : numbers3)
      total3 += n.shortValue();

    int total4 = 0;
    for (final Short n : numbers4)
      total4 += n.shortValue();

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
  public void testTotalIterableOfT()
  {
    final Iterable<ValueContainer<Short>> numbers1 = asList(VALUE_CONTAINERS);

    int total1 = 0;
    for (final ValueContainer<Short> n : numbers1)
      total1 += n.getValue();

    final int expected1 = total1;
    final int actual1 = total(numbers1, new Function1<ValueContainer<Short>, Short>() {
      @Override
      public Short apply(final ValueContainer<Short> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);

    final Iterable<ValueContainer<Short>> numbers2 = asList(RANDOM_VALUE_CONTAINERS);

    int total2 = 0;
    for (final ValueContainer<Short> n : numbers2)
      total2 += n.getValue();

    final int expected2 = total2;
    final int actual2 = total(numbers2, new Function1<ValueContainer<Short>, Short>() {
      @Override
      public Short apply(final ValueContainer<Short> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertTrue(expected2 == actual2);
  }

}
