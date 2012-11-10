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
import static org.elixirian.kommonlee.util.number.BigIntegerTotal.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigInteger;
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
public class BigIntegerTotalTest
{
  private static final BigInteger[] NUMBERS1 = { BigInteger.valueOf(Long.MIN_VALUE), BigInteger.valueOf(-5),
      BigInteger.valueOf(-2), BigInteger.valueOf(-1), BigInteger.valueOf(0), BigInteger.valueOf(1),
      BigInteger.valueOf(2), BigInteger.valueOf(5), BigInteger.valueOf(Long.MAX_VALUE) };
  private static final BigInteger[] NUMBERS2 = { BigInteger.valueOf(Long.MIN_VALUE), BigInteger.valueOf(-100),
      BigInteger.valueOf(-80), BigInteger.valueOf(-70), BigInteger.valueOf(10), BigInteger.valueOf(20),
      BigInteger.valueOf(50), BigInteger.valueOf(Long.MAX_VALUE) };

  private static final Random random = new Random();
  private static final BigInteger[] NUMBERS3 = getRandomNumbers(15);
  private static final BigInteger[] NUMBERS4 = getRandomNumbers(15);

  private static BigInteger[] getRandomNumbers(final int length)
  {
    final BigInteger[] numbers = new BigInteger[length];
    for (int i = 0; i < length; i++)
    {
      numbers[i] = BigInteger.valueOf(random.nextLong());
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
  public final void testTotalBigIntegerBigInteger()
  {
    final BigInteger[] numbers1 = NUMBERS1;
    final BigInteger[] numbers2 = NUMBERS2;

    for (final BigInteger n1 : numbers1)
    {
      for (final BigInteger n2 : numbers2)
      {
        final BigInteger expected = n1.add(n2);
        final BigInteger actual = total(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertThat(actual, is(equalTo(expected)));
      }
    }

    final BigInteger[] numbers3 = NUMBERS3;
    final BigInteger[] numbers4 = NUMBERS4;

    for (final BigInteger n1 : numbers3)
    {
      for (final BigInteger n2 : numbers4)
      {
        final BigInteger expected = n1.add(n2);
        final BigInteger actual = total(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertThat(actual, is(equalTo(expected)));
      }
    }
  }

  @Test
  public final void testTotalBigIntegerBigIntegerBigInteger()
  {
    final BigInteger[] numbers1 = NUMBERS1;
    final BigInteger[] numbers2 = NUMBERS2;

    for (final BigInteger n1 : numbers1)
    {
      for (final BigInteger n2 : numbers2)
      {
        for (final BigInteger n3 : numbers1)
        {
          final BigInteger expected = n1.add(n2)
              .add(n3);
          final BigInteger actual = total(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertThat(actual, is(equalTo(expected)));
        }
      }
    }

    final BigInteger[] numbers3 = NUMBERS3;
    final BigInteger[] numbers4 = NUMBERS4;

    for (final BigInteger n1 : numbers3)
    {
      for (final BigInteger n2 : numbers4)
      {
        for (final BigInteger n3 : numbers3)
        {
          final BigInteger expected = n1.add(n2)
              .add(n3);
          final BigInteger actual = total(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertThat(actual, is(equalTo(expected)));
        }
      }
    }
  }

  @Test
  public final void testTotalBigIntegerBigIntegerBigIntegerBigInteger()
  {
    final BigInteger[] numbers1 = NUMBERS1;
    final BigInteger[] numbers2 = NUMBERS2;

    for (final BigInteger n1 : numbers1)
    {
      for (final BigInteger n2 : numbers2)
      {
        for (final BigInteger n3 : numbers1)
        {
          for (final BigInteger n4 : numbers2)
          {
            final BigInteger expected = n1.add(n2)
                .add(n3)
                .add(n4);
            final BigInteger actual = total(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertThat(actual, is(equalTo(expected)));
          }
        }
      }
    }

    final BigInteger[] numbers3 = NUMBERS3;
    final BigInteger[] numbers4 = NUMBERS4;

    for (final BigInteger n1 : numbers3)
    {
      for (final BigInteger n2 : numbers4)
      {
        for (final BigInteger n3 : numbers3)
        {
          for (final BigInteger n4 : numbers4)
          {
            final BigInteger expected = n1.add(n2)
                .add(n3)
                .add(n4);
            final BigInteger actual = total(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertThat(actual, is(equalTo(expected)));
          }
        }
      }
    }
  }

  @Test
  public final void testTotalBigIntegerBigIntegerBigIntegerBigIntegerBigInteger()
  {
    final BigInteger[] numbers1 = NUMBERS1;
    final BigInteger[] numbers2 = NUMBERS2;

    for (final BigInteger n1 : numbers1)
    {
      for (final BigInteger n2 : numbers2)
      {
        for (final BigInteger n3 : numbers1)
        {
          for (final BigInteger n4 : numbers2)
          {
            for (final BigInteger n5 : numbers1)
            {

              final BigInteger expected = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);
              final BigInteger actual = total(n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected + "\nactual:   " + actual);
              assertThat(actual, is(equalTo(expected)));
            }
          }
        }
      }
    }

    final BigInteger[] numbers3 = NUMBERS3;
    final BigInteger[] numbers4 = NUMBERS4;

    for (final BigInteger n1 : numbers3)
    {
      for (final BigInteger n2 : numbers4)
      {
        for (final BigInteger n3 : numbers3)
        {
          for (final BigInteger n4 : numbers4)
          {
            for (final BigInteger n5 : numbers3)
            {
              final BigInteger expected = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);
              final BigInteger actual = total(n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected + "\nactual:   " + actual);
              assertThat(actual, is(equalTo(expected)));
            }
          }
        }
      }
    }
  }

  @Test
  public final void testTotalBigIntegerBigIntegerBigIntegerBigIntegerBigIntegerMoreBigIntegers()
  {
    final BigInteger[] numbers1 = NUMBERS1;
    final BigInteger[] numbers2 = NUMBERS2;

    for (final BigInteger n1 : numbers1)
    {
      for (final BigInteger n2 : numbers2)
      {
        for (final BigInteger n3 : numbers1)
        {
          for (final BigInteger n4 : numbers2)
          {
            for (final BigInteger n5 : numbers1)
            {
              final BigInteger expected3 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5)
                  .add(n1)
                  .add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);

              final BigInteger actual3 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              final BigInteger expected4 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5)
                  .add(n1)
                  .add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);

              final BigInteger actual4 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertThat(actual4, is(equalTo(expected4)));
            }
          }
        }
      }
    }

    final BigInteger[] numbers3 = new BigInteger[NUMBERS3.length];
    System.arraycopy(NUMBERS3, 0, numbers3, 0, NUMBERS3.length);
    final BigInteger[] numbers4 = new BigInteger[NUMBERS4.length];
    System.arraycopy(NUMBERS4, 0, numbers4, 0, NUMBERS4.length);

    for (final BigInteger n1 : numbers3)
    {
      for (final BigInteger n2 : numbers4)
      {
        for (final BigInteger n3 : numbers3)
        {
          for (final BigInteger n4 : numbers4)
          {
            for (final BigInteger n5 : numbers3)
            {
              final BigInteger expected1 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5)
                  .add(n1)
                  .add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);

              final BigInteger actual1 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              final BigInteger expected2 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5)
                  .add(n1)
                  .add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);

              final BigInteger actual2 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
            }
          }
        }
      }
    }
  }

  @Test
  public final void testTotalBigIntegerBigIntegerBigIntegerBigIntegerBigIntegerBigIntegerArray()
  {
    final BigInteger[] numbers1 = NUMBERS1;
    final BigInteger[] numbers2 = NUMBERS2;

    for (final BigInteger n1 : numbers1)
    {
      for (final BigInteger n2 : numbers2)
      {
        for (final BigInteger n3 : numbers1)
        {
          for (final BigInteger n4 : numbers2)
          {
            for (final BigInteger n5 : numbers1)
            {
              BigInteger total1 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);
              for (final BigInteger n : numbers1)
                total1 = total1.add(n);

              final BigInteger expected3 = total1;

              final BigInteger actual3 = total(n1, n2, n3, n4, n5, numbers1);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              BigInteger total2 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);
              for (final BigInteger n : numbers2)
                total2 = total2.add(n);

              final BigInteger expected4 = total2;

              final BigInteger actual4 = total(n1, n2, n3, n4, n5, numbers2);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertThat(actual4, is(equalTo(expected4)));
            }
          }
        }
      }
    }

    final BigInteger[] numbers3 = new BigInteger[NUMBERS3.length];
    System.arraycopy(NUMBERS3, 0, numbers3, 0, NUMBERS3.length);
    final BigInteger[] numbers4 = new BigInteger[NUMBERS4.length];
    System.arraycopy(NUMBERS4, 0, numbers4, 0, NUMBERS4.length);

    for (final BigInteger n1 : numbers3)
    {
      for (final BigInteger n2 : numbers4)
      {
        for (final BigInteger n3 : numbers3)
        {
          for (final BigInteger n4 : numbers4)
          {
            for (final BigInteger n5 : numbers3)
            {
              BigInteger total3 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);
              for (final BigInteger n : numbers3)
                total3 = total3.add(n);

              final BigInteger expected1 = total3;

              final BigInteger actual1 = total(n1, n2, n3, n4, n5, numbers3);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              BigInteger total4 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);
              for (final BigInteger n : numbers4)
                total4 = total4.add(n);

              final BigInteger expected2 = total4;

              final BigInteger actual2 = total(n1, n2, n3, n4, n5, numbers4);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
            }
          }
        }
      }
    }
  }

  @Test
  public final void testTotalBigIntegerArray()
  {
    final BigInteger[] numbers1 = NUMBERS1;
    final BigInteger[] numbers2 = NUMBERS2;

    BigInteger total1 = BigInteger.ZERO;
    for (final BigInteger n : numbers1)
      total1 = total1.add(n);

    BigInteger total2 = BigInteger.ZERO;
    for (final BigInteger n : numbers2)
      total2 = total2.add(n);

    final BigInteger expected1 = total1;
    final BigInteger actual1 = total(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertThat(actual1, is(equalTo(expected1)));
    final BigInteger expected2 = total2;
    final BigInteger actual2 = total(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertThat(actual2, is(equalTo(expected2)));

    final BigInteger[] numbers3 = NUMBERS3;
    final BigInteger[] numbers4 = NUMBERS4;

    BigInteger total3 = BigInteger.ZERO;
    for (final BigInteger n : numbers3)
      total3 = total3.add(n);

    BigInteger total4 = BigInteger.ZERO;
    for (final BigInteger n : numbers4)
      total4 = total4.add(n);

    final BigInteger expected3 = total3;
    final BigInteger actual3 = total(numbers3);
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertThat(actual3, is(equalTo(expected3)));
    final BigInteger expected4 = total4;
    final BigInteger actual4 = total(numbers4);
    // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
    assertThat(actual4, is(equalTo(expected4)));
  }

  @SuppressWarnings({ "unchecked" })
  private static final ValueContainer<BigInteger>[] VALUE_CONTAINERS = new ValueContainer[] {
      new ValueContainer<BigInteger>(BigInteger.valueOf(Long.MIN_VALUE)),
      new ValueContainer<BigInteger>(BigInteger.valueOf(-5L)), new ValueContainer<BigInteger>(BigInteger.valueOf(-1L)),
      new ValueContainer<BigInteger>(BigInteger.valueOf(0L)), new ValueContainer<BigInteger>(BigInteger.valueOf(1L)),
      new ValueContainer<BigInteger>(BigInteger.valueOf(3L)), new ValueContainer<BigInteger>(BigInteger.valueOf(5L)),
      new ValueContainer<BigInteger>(BigInteger.valueOf(Long.MAX_VALUE)) };

  private static final ValueContainer<BigInteger>[] RANDOM_VALUE_CONTAINERS = getRandomValues(20);

  private static ValueContainer<BigInteger>[] getRandomValues(final int length)
  {
    @SuppressWarnings("unchecked")
    final ValueContainer<BigInteger>[] values = new ValueContainer[length];
    for (int i = 0; i < length; i++)
    {
      final ValueContainer<BigInteger> valueContainer =
        new ValueContainer<BigInteger>(BigInteger.valueOf(random.nextLong()));
      values[i] = valueContainer;
    }
    return values;
  }

  @Test
  public final void testTotalArrayOfT()
  {
    final ValueContainer<BigInteger>[] numbers1 = VALUE_CONTAINERS;

    BigInteger total1 = BigInteger.ZERO;
    for (final ValueContainer<BigInteger> n : numbers1)
      total1 = total1.add(n.getValue());

    final BigInteger expected1 = total1;
    final BigInteger actual1 = total(numbers1, new Function1<ValueContainer<BigInteger>, BigInteger>() {
      @Override
      public BigInteger apply(final ValueContainer<BigInteger> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertThat(actual1, is(equalTo(expected1)));

    final ValueContainer<BigInteger>[] numbers2 = RANDOM_VALUE_CONTAINERS;

    BigInteger total2 = BigInteger.ZERO;
    for (final ValueContainer<BigInteger> n : numbers2)
      total2 = total2.add(n.getValue());

    final BigInteger expected2 = total2;
    final BigInteger actual2 = total(numbers2, new Function1<ValueContainer<BigInteger>, BigInteger>() {
      @Override
      public BigInteger apply(final ValueContainer<BigInteger> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertThat(actual2, is(equalTo(expected2)));
  }

  @Test
  public final void testTotalIterableOfBigInteger()
  {
    final Iterable<BigInteger> numbers1 = asList(NUMBERS1);
    final Iterable<BigInteger> numbers2 = asList(NUMBERS2);

    BigInteger total1 = BigInteger.ZERO;
    for (final BigInteger n : numbers1)
      total1 = total1.add(n);

    BigInteger total2 = BigInteger.ZERO;
    for (final BigInteger n : numbers2)
      total2 = total2.add(n);

    final BigInteger expected1 = total1;
    final BigInteger actual1 = total(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertThat(actual1, is(equalTo(expected1)));
    final BigInteger expected2 = total2;
    final BigInteger actual2 = total(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertThat(actual2, is(equalTo(expected2)));

    final Iterable<BigInteger> numbers3 = asList(NUMBERS3);
    final Iterable<BigInteger> numbers4 = asList(NUMBERS4);

    BigInteger total3 = BigInteger.ZERO;
    for (final BigInteger n : numbers3)
      total3 = total3.add(n);

    BigInteger total4 = BigInteger.ZERO;
    for (final BigInteger n : numbers4)
      total4 = total4.add(n);

    final BigInteger expected3 = total3;
    final BigInteger actual3 = total(numbers3);
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertThat(actual3, is(equalTo(expected3)));
    final BigInteger expected4 = total4;
    final BigInteger actual4 = total(numbers4);
    // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
    assertThat(actual4, is(equalTo(expected4)));
  }

  @Test
  public final void testTotalIterableOfT()
  {
    final Iterable<ValueContainer<BigInteger>> numbers1 = asList(VALUE_CONTAINERS);

    BigInteger total1 = BigInteger.ZERO;
    for (final ValueContainer<BigInteger> n : numbers1)
      total1 = total1.add(n.getValue());

    final BigInteger expected1 = total1;
    final BigInteger actual1 = total(numbers1, new Function1<ValueContainer<BigInteger>, BigInteger>() {
      @Override
      public BigInteger apply(final ValueContainer<BigInteger> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertThat(actual1, is(equalTo(expected1)));

    final Iterable<ValueContainer<BigInteger>> numbers2 = asList(RANDOM_VALUE_CONTAINERS);

    BigInteger total2 = BigInteger.ZERO;
    for (final ValueContainer<BigInteger> n : numbers2)
      total2 = total2.add(n.getValue());

    final BigInteger expected2 = total2;
    final BigInteger actual2 = total(numbers2, new Function1<ValueContainer<BigInteger>, BigInteger>() {
      @Override
      public BigInteger apply(final ValueContainer<BigInteger> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertThat(actual2, is(equalTo(expected2)));
  }
}
