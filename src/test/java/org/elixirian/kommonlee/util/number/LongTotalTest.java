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
import static org.elixirian.kommonlee.util.number.LongTotal.*;
import static org.elixirian.kommonlee.util.number.Numbers.*;
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
 * @version 0.0.1 (2011-03-28)
 */
public class LongTotalTest
{
  // private static final int HOW_MANY = 30;

  /* @formatter:off */
  private static final long[] NUMBERS1 = { Integer.MIN_VALUE, -5,   -4,  -3,  -2,  -1,  Integer.MAX_VALUE,
                         0,  1,  2,  3,  4,  5,  Integer.MAX_VALUE };

  private static final long[] NUMBERS2 = { Integer.MIN_VALUE, -100, -90, -80, -70, -60, Integer.MAX_VALUE,
                         10, 20, 30, 40, 50, 60, Integer.MAX_VALUE };
  /* @formatter:on */

  private static final Random random = new Random();
  private static final long[] NUMBERS3 = getRandomNumbers(3);
  private static final long[] NUMBERS4 = getRandomNumbers(5);

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
  public final void testSumLongLong()
  {
    final long[] numbers1 = NUMBERS1;
    final long[] numbers2 = NUMBERS2;

    for (final long n1 : numbers1)
    {
      for (final long n2 : numbers2)
      {
        final long expected = n1 + n2;
        final long actual = sum(n1, n2);
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
        final long actual = sum(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertTrue(expected == actual);
      }
    }
  }

  @Test
  public final void testSumLongLongLong()
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
          final long actual = sum(n1, n2, n3);
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
          final long actual = sum(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertTrue(expected == actual);
        }
      }
    }
  }

  @Test
  public final void testSumLongLongLongLong()
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
            final long actual = sum(n1, n2, n3, n4);
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
            final long actual = sum(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertTrue(expected == actual);
          }
        }
      }
    }
  }

  @Test
  public final void testSumLongLongLongLongLong()
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
              final long actual = sum(n1, n2, n3, n4, n5);
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
  public final void testSumLongLongLongLongLongMoreLongs()
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
              @SuppressWarnings("boxing")
              final Long expected1 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Long actual1 = sum(n1, n2, n3, n4, n5, n1 + n2 + n3 + n4 + n5);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              @SuppressWarnings("boxing")
              final Long expected2 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Long actual2 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
            }
          }
        }
      }
    }

    final long[] numbers3 = new long[NUMBERS3.length];
    System.arraycopy(NUMBERS3, 0, numbers3, 0, NUMBERS3.length);
    final long[] numbers4 = new long[NUMBERS4.length];
    System.arraycopy(NUMBERS4, 0, numbers4, 0, NUMBERS4.length);

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
              @SuppressWarnings("boxing")
              final Long expected3 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Long actual3 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              @SuppressWarnings("boxing")
              final Long expected4 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Long actual4 = sum(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertThat(actual4, is(equalTo(expected4)));
            }
          }
        }
      }
    }
  }

  @Test
  public final void testSumLongLongLongLongLongLongArray()
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

              long total1 = n1 + n2 + n3 + n4 + n5;
              for (final long n : numbers1)
                total1 += n;

              @SuppressWarnings("boxing")
              final Long expected1 = total1;
              @SuppressWarnings("boxing")
              final Long actual1 = sum(n1, n2, n3, n4, n5, numbers1);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              long total2 = n1 + n2 + n3 + n4 + n5;
              for (final long n : numbers2)
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

    final long[] numbers3 = new long[NUMBERS3.length];
    System.arraycopy(NUMBERS3, 0, numbers3, 0, NUMBERS3.length);
    final long[] numbers4 = new long[NUMBERS4.length];
    System.arraycopy(NUMBERS4, 0, numbers4, 0, NUMBERS4.length);

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
              long total3 = n1 + n2 + n3 + n4 + n5;
              for (final long n : numbers3)
                total3 += n;

              @SuppressWarnings("boxing")
              final Long expected3 = total3;
              @SuppressWarnings("boxing")
              final Long actual3 = sum(n1, n2, n3, n4, n5, numbers3);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              long total4 = n1 + n2 + n3 + n4 + n5;
              for (final long n : numbers4)
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
  public final void testSumLongArray()
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
    final long actual1 = sum(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);
    final long expected2 = total2;
    final long actual2 = sum(numbers2);
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
    final long actual3 = sum(numbers3);
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertTrue(expected3 == actual3);

    final long expected4 = total4;
    final long actual4 = sum(numbers4);
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
  public final void testTotalBoxedLongBoxedLongBoxedLongBoxedLongBoxedLongMoreBoxedLongs()
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
              final Long expected1 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Long actual1 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              @SuppressWarnings("boxing")
              final Long expected2 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Long actual2 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
            }
          }
        }
      }
    }

    final Long[] numbers3 = new Long[NUMBERS3.length];
    System.arraycopy(convertToBoxed(NUMBERS3), 0, numbers3, 0, NUMBERS3.length);
    final Long[] numbers4 = new Long[NUMBERS4.length];
    System.arraycopy(convertToBoxed(NUMBERS4), 0, numbers4, 0, NUMBERS4.length);

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
              final Long expected3 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
              @SuppressWarnings("boxing")
              final Long actual3 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              @SuppressWarnings("boxing")
              final Long expected4 = (n1 + n2 + n3 + n4 + n5 + n1 + n2 + n3 + n4 + n5);
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
  public final void testTotalBoxedLongBoxedLongBoxedLongBoxedLongBoxedLongBoxedLongArray()
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
              long total1 = n1 + n2 + n3 + n4 + n5;
              for (final Long n : numbers1)
                total1 += n.longValue();

              @SuppressWarnings("boxing")
              final Long expected1 = total1;
              @SuppressWarnings("boxing")
              final Long actual1 = total(n1, n2, n3, n4, n5, numbers1);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              @SuppressWarnings("boxing")
              long total2 = n1 + n2 + n3 + n4 + n5;
              for (final Long n : numbers2)
                total2 += n.longValue();

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

    final Long[] numbers3 = new Long[NUMBERS3.length];
    System.arraycopy(convertToBoxed(NUMBERS3), 0, numbers3, 0, NUMBERS3.length);
    final Long[] numbers4 = new Long[NUMBERS4.length];
    System.arraycopy(convertToBoxed(NUMBERS4), 0, numbers4, 0, NUMBERS4.length);

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
              long total3 = n1 + n2 + n3 + n4 + n5;
              for (final Long n : numbers3)
                total3 += n.longValue();

              @SuppressWarnings("boxing")
              final Long expected3 = total3;
              @SuppressWarnings("boxing")
              final Long actual3 = total(n1, n2, n3, n4, n5, numbers3);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              @SuppressWarnings("boxing")
              long total4 = n1 + n2 + n3 + n4 + n5;
              for (final Long n : numbers4)
                total4 += n.longValue();

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

  @SuppressWarnings({ "unchecked", "boxing" })
  private static final ValueContainer<Long>[] VALUE_CONTAINERS = new ValueContainer[] {
      new ValueContainer<Long>(Long.MIN_VALUE), new ValueContainer<Long>(-5L), new ValueContainer<Long>(-1L),
      new ValueContainer<Long>(0L), new ValueContainer<Long>(1L), new ValueContainer<Long>(3L),
      new ValueContainer<Long>(5L), new ValueContainer<Long>(Long.MAX_VALUE) };

  private static final ValueContainer<Long>[] RANDOM_VALUE_CONTAINERS = getRandomValues(50);

  private static ValueContainer<Long>[] getRandomValues(final int length)
  {
    final ValueContainer<Long>[] values = new ValueContainer[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final ValueContainer<Long> valueContainer = new ValueContainer<Long>(random.nextLong());
      values[i] = valueContainer;
    }
    return values;
  }

  @Test
  public final void testTotalArrayOfT()
  {
    final ValueContainer<Long>[] numbers1 = VALUE_CONTAINERS;

    long total1 = 0;
    for (final ValueContainer<Long> n : numbers1)
      total1 += n.getValue();

    final long expected1 = total1;
    final long actual1 = total(numbers1, new Function1<ValueContainer<Long>, Long>() {
      @Override
      public Long apply(final ValueContainer<Long> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);

    final ValueContainer<Long>[] numbers2 = RANDOM_VALUE_CONTAINERS;

    long total2 = 0;
    for (final ValueContainer<Long> n : numbers2)
      total2 += n.getValue();

    final long expected2 = total2;
    final long actual2 = total(numbers2, new Function1<ValueContainer<Long>, Long>() {
      @Override
      public Long apply(final ValueContainer<Long> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertTrue(expected2 == actual2);
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

  @Test
  public final void testTotalIterableOfT()
  {
    final Iterable<ValueContainer<Long>> numbers1 = asList(VALUE_CONTAINERS);

    long total1 = 0;
    for (final ValueContainer<Long> n : numbers1)
      total1 += n.getValue();

    final long expected1 = total1;
    final long actual1 = total(numbers1, new Function1<ValueContainer<Long>, Long>() {
      @Override
      public Long apply(final ValueContainer<Long> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertTrue(expected1 == actual1);

    final Iterable<ValueContainer<Long>> numbers2 = asList(RANDOM_VALUE_CONTAINERS);

    long total2 = 0;
    for (final ValueContainer<Long> n : numbers2)
      total2 += n.getValue();

    final long expected2 = total2;
    final long actual2 = total(numbers2, new Function1<ValueContainer<Long>, Long>() {
      @Override
      public Long apply(final ValueContainer<Long> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertTrue(expected2 == actual2);
  }

}
