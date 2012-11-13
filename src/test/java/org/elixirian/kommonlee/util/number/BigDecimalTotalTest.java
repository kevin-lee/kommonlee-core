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
import static org.elixirian.kommonlee.util.number.BigDecimalTotal.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
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
public class BigDecimalTotalTest
{
  private static final BigDecimal[] NUMBERS1 = { BigDecimal.valueOf(Double.MIN_VALUE), BigDecimal.valueOf(-5.5D),
      BigDecimal.valueOf(-2.3D), BigDecimal.valueOf(-1.1234D), BigDecimal.valueOf(0), BigDecimal.valueOf(1.0D),
      BigDecimal.valueOf(2.3D), BigDecimal.valueOf(5.5D), BigDecimal.valueOf(Double.MAX_VALUE) };
  private static final BigDecimal[] NUMBERS2 = { BigDecimal.valueOf(Double.MIN_VALUE), BigDecimal.valueOf(-100.20D),
      BigDecimal.valueOf(-80.88D), BigDecimal.valueOf(-70.765D), BigDecimal.valueOf(10.00D),
      BigDecimal.valueOf(20.23D), BigDecimal.valueOf(50.001D), BigDecimal.valueOf(Double.MAX_VALUE) };

  private static final Random random = new Random();
  private static final BigDecimal[] NUMBERS3 = getRandomNumbers(10);
  private static final BigDecimal[] NUMBERS4 = getRandomNumbers(15);

  private static BigDecimal[] getRandomNumbers(final int length)
  {
    final BigDecimal[] numbers = new BigDecimal[length];
    for (int i = 0; i < length; i++)
    {
      numbers[i] = BigDecimal.valueOf(random.nextDouble());
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
  public final void testTotalBigDecimalBigDecimal()
  {
    final BigDecimal[] numbers1 = NUMBERS1;
    final BigDecimal[] numbers2 = NUMBERS2;

    for (final BigDecimal n1 : numbers1)
    {
      for (final BigDecimal n2 : numbers2)
      {
        final BigDecimal expected = n1.add(n2);
        final BigDecimal actual = total(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertThat(actual, is(equalTo(expected)));
      }
    }

    final BigDecimal[] numbers3 = NUMBERS3;
    final BigDecimal[] numbers4 = NUMBERS4;

    for (final BigDecimal n1 : numbers3)
    {
      for (final BigDecimal n2 : numbers4)
      {
        final BigDecimal expected = n1.add(n2);
        final BigDecimal actual = total(n1, n2);
        // System.out.println("expected: " + expected + "\nactual:   " + actual);
        assertThat(actual, is(equalTo(expected)));
      }
    }
  }

  @Test
  public final void testTotalBigDecimalBigDecimalBigDecimal()
  {
    final BigDecimal[] numbers1 = NUMBERS1;
    final BigDecimal[] numbers2 = NUMBERS2;

    for (final BigDecimal n1 : numbers1)
    {
      for (final BigDecimal n2 : numbers2)
      {
        for (final BigDecimal n3 : numbers1)
        {
          final BigDecimal expected = n1.add(n2)
              .add(n3);
          final BigDecimal actual = total(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertThat(actual, is(equalTo(expected)));
        }
      }
    }

    final BigDecimal[] numbers3 = NUMBERS3;
    final BigDecimal[] numbers4 = NUMBERS4;

    for (final BigDecimal n1 : numbers3)
    {
      for (final BigDecimal n2 : numbers4)
      {
        for (final BigDecimal n3 : numbers3)
        {
          final BigDecimal expected = n1.add(n2)
              .add(n3);
          final BigDecimal actual = total(n1, n2, n3);
          // System.out.println("expected: " + expected + "\nactual:   " + actual);
          assertThat(actual, is(equalTo(expected)));
        }
      }
    }
  }

  @Test
  public final void testTotalBigDecimalBigDecimalBigDecimalBigDecimal()
  {
    final BigDecimal[] numbers1 = NUMBERS1;
    final BigDecimal[] numbers2 = NUMBERS2;

    for (final BigDecimal n1 : numbers1)
    {
      for (final BigDecimal n2 : numbers2)
      {
        for (final BigDecimal n3 : numbers1)
        {
          for (final BigDecimal n4 : numbers2)
          {
            final BigDecimal expected = n1.add(n2)
                .add(n3)
                .add(n4);
            final BigDecimal actual = total(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertThat(actual, is(equalTo(expected)));
          }
        }
      }
    }

    final BigDecimal[] numbers3 = NUMBERS3;
    final BigDecimal[] numbers4 = NUMBERS4;

    for (final BigDecimal n1 : numbers3)
    {
      for (final BigDecimal n2 : numbers4)
      {
        for (final BigDecimal n3 : numbers3)
        {
          for (final BigDecimal n4 : numbers4)
          {
            final BigDecimal expected = n1.add(n2)
                .add(n3)
                .add(n4);
            final BigDecimal actual = total(n1, n2, n3, n4);
            // System.out.println("expected: " + expected + "\nactual:   " + actual);
            assertThat(actual, is(equalTo(expected)));
          }
        }
      }
    }
  }

  @Test
  public final void testTotalBigDecimalBigDecimalBigDecimalBigDecimalBigDecimal()
  {
    final BigDecimal[] numbers1 = NUMBERS1;
    final BigDecimal[] numbers2 = NUMBERS2;

    for (final BigDecimal n1 : numbers1)
    {
      for (final BigDecimal n2 : numbers2)
      {
        for (final BigDecimal n3 : numbers1)
        {
          for (final BigDecimal n4 : numbers2)
          {
            for (final BigDecimal n5 : numbers1)
            {

              final BigDecimal expected = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);
              final BigDecimal actual = total(n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected + "\nactual:   " + actual);
              assertThat(actual, is(equalTo(expected)));
            }
          }
        }
      }
    }

    final BigDecimal[] numbers3 = NUMBERS3;
    final BigDecimal[] numbers4 = NUMBERS4;

    for (final BigDecimal n1 : numbers3)
    {
      for (final BigDecimal n2 : numbers4)
      {
        for (final BigDecimal n3 : numbers3)
        {
          for (final BigDecimal n4 : numbers4)
          {
            for (final BigDecimal n5 : numbers3)
            {
              final BigDecimal expected = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);
              final BigDecimal actual = total(n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected + "\nactual:   " + actual);
              assertThat(actual, is(equalTo(expected)));
            }
          }
        }
      }
    }
  }

  @Test
  public final void testTotalBigDecimalBigDecimalBigDecimalBigDecimalBigDecimalMoreBigDecimals()
  {
    final BigDecimal[] numbers1 = NUMBERS1;
    final BigDecimal[] numbers2 = NUMBERS2;

    for (final BigDecimal n1 : numbers1)
    {
      for (final BigDecimal n2 : numbers2)
      {
        for (final BigDecimal n3 : numbers1)
        {
          for (final BigDecimal n4 : numbers2)
          {
            for (final BigDecimal n5 : numbers1)
            {
              final BigDecimal expected3 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5)
                  .add(n1)
                  .add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);

              final BigDecimal actual3 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              final BigDecimal expected4 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5)
                  .add(n1)
                  .add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);

              final BigDecimal actual4 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertThat(actual4, is(equalTo(expected4)));
            }
          }
        }
      }
    }

    final BigDecimal[] numbers3 = new BigDecimal[NUMBERS3.length];
    System.arraycopy(NUMBERS3, 0, numbers3, 0, NUMBERS3.length);
    final BigDecimal[] numbers4 = new BigDecimal[NUMBERS4.length];
    System.arraycopy(NUMBERS4, 0, numbers4, 0, NUMBERS4.length);

    for (final BigDecimal n1 : numbers3)
    {
      for (final BigDecimal n2 : numbers4)
      {
        for (final BigDecimal n3 : numbers3)
        {
          for (final BigDecimal n4 : numbers4)
          {
            for (final BigDecimal n5 : numbers3)
            {
              final BigDecimal expected1 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5)
                  .add(n1)
                  .add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);

              final BigDecimal actual1 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              final BigDecimal expected2 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5)
                  .add(n1)
                  .add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);

              final BigDecimal actual2 = total(n1, n2, n3, n4, n5, n1, n2, n3, n4, n5);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
            }
          }
        }
      }
    }
  }

  @Test
  public final void testTotalBigDecimalBigDecimalBigDecimalBigDecimalBigDecimalBigDecimalArray()
  {
    final BigDecimal[] numbers1 = NUMBERS1;
    final BigDecimal[] numbers2 = NUMBERS2;

    for (final BigDecimal n1 : numbers1)
    {
      for (final BigDecimal n2 : numbers2)
      {
        for (final BigDecimal n3 : numbers1)
        {
          for (final BigDecimal n4 : numbers2)
          {
            for (final BigDecimal n5 : numbers1)
            {
              BigDecimal total1 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);
              for (final BigDecimal n : numbers1)
                total1 = total1.add(n);

              final BigDecimal expected3 = total1;

              final BigDecimal actual3 = total(n1, n2, n3, n4, n5, numbers1);
              // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
              assertThat(actual3, is(equalTo(expected3)));

              BigDecimal total2 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);
              for (final BigDecimal n : numbers2)
                total2 = total2.add(n);

              final BigDecimal expected4 = total2;

              final BigDecimal actual4 = total(n1, n2, n3, n4, n5, numbers2);
              // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
              assertThat(actual4, is(equalTo(expected4)));
            }
          }
        }
      }
    }

    final BigDecimal[] numbers3 = new BigDecimal[NUMBERS3.length];
    System.arraycopy(NUMBERS3, 0, numbers3, 0, NUMBERS3.length);
    final BigDecimal[] numbers4 = new BigDecimal[NUMBERS4.length];
    System.arraycopy(NUMBERS4, 0, numbers4, 0, NUMBERS4.length);

    for (final BigDecimal n1 : numbers3)
    {
      for (final BigDecimal n2 : numbers4)
      {
        for (final BigDecimal n3 : numbers3)
        {
          for (final BigDecimal n4 : numbers4)
          {
            for (final BigDecimal n5 : numbers3)
            {
              BigDecimal total3 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);
              for (final BigDecimal n : numbers3)
                total3 = total3.add(n);

              final BigDecimal expected1 = total3;

              final BigDecimal actual1 = total(n1, n2, n3, n4, n5, numbers3);
              // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
              assertThat(actual1, is(equalTo(expected1)));

              BigDecimal total4 = n1.add(n2)
                  .add(n3)
                  .add(n4)
                  .add(n5);
              for (final BigDecimal n : numbers4)
                total4 = total4.add(n);

              final BigDecimal expected2 = total4;

              final BigDecimal actual2 = total(n1, n2, n3, n4, n5, numbers4);
              // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
              assertThat(actual2, is(equalTo(expected2)));
            }
          }
        }
      }
    }
  }

  @Test
  public final void testTotalBigDecimalArray()
  {
    final BigDecimal[] numbers1 = NUMBERS1;
    final BigDecimal[] numbers2 = NUMBERS2;

    BigDecimal total1 = BigDecimal.ZERO;
    for (final BigDecimal n : numbers1)
      total1 = total1.add(n);

    BigDecimal total2 = BigDecimal.ZERO;
    for (final BigDecimal n : numbers2)
      total2 = total2.add(n);

    final BigDecimal expected1 = total1;
    final BigDecimal actual1 = total(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertThat(actual1, is(equalTo(expected1)));
    final BigDecimal expected2 = total2;
    final BigDecimal actual2 = total(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertThat(actual2, is(equalTo(expected2)));

    final BigDecimal[] numbers3 = NUMBERS3;
    final BigDecimal[] numbers4 = NUMBERS4;

    BigDecimal total3 = BigDecimal.ZERO;
    for (final BigDecimal n : numbers3)
      total3 = total3.add(n);

    BigDecimal total4 = BigDecimal.ZERO;
    for (final BigDecimal n : numbers4)
      total4 = total4.add(n);

    final BigDecimal expected3 = total3;
    final BigDecimal actual3 = total(numbers3);
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertThat(actual3, is(equalTo(expected3)));
    final BigDecimal expected4 = total4;
    final BigDecimal actual4 = total(numbers4);
    // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
    assertThat(actual4, is(equalTo(expected4)));
  }

  @SuppressWarnings({ "unchecked" })
  private static final ValueContainer<BigDecimal>[] VALUE_CONTAINERS = new ValueContainer[] {
      new ValueContainer<BigDecimal>(BigDecimal.valueOf(Double.MIN_VALUE)),
      new ValueContainer<BigDecimal>(BigDecimal.valueOf(-5.50D)),
      new ValueContainer<BigDecimal>(BigDecimal.valueOf(-1.1234D)),
      new ValueContainer<BigDecimal>(BigDecimal.valueOf(0.00D)),
      new ValueContainer<BigDecimal>(BigDecimal.valueOf(1.111D)),
      new ValueContainer<BigDecimal>(BigDecimal.valueOf(3.141592D)),
      new ValueContainer<BigDecimal>(BigDecimal.valueOf(5.32D)),
      new ValueContainer<BigDecimal>(BigDecimal.valueOf(Double.MAX_VALUE)) };

  private static final ValueContainer<BigDecimal>[] RANDOM_VALUE_CONTAINERS = getRandomValues(20);

  private static ValueContainer<BigDecimal>[] getRandomValues(final int length)
  {
    @SuppressWarnings("unchecked")
    final ValueContainer<BigDecimal>[] values = new ValueContainer[length];
    for (int i = 0; i < length; i++)
    {
      final ValueContainer<BigDecimal> valueContainer =
        new ValueContainer<BigDecimal>(BigDecimal.valueOf(random.nextDouble()));
      values[i] = valueContainer;
    }
    return values;
  }

  @Test
  public final void testTotalArrayOfT()
  {
    final ValueContainer<BigDecimal>[] numbers1 = VALUE_CONTAINERS;

    BigDecimal total1 = BigDecimal.ZERO;
    for (final ValueContainer<BigDecimal> n : numbers1)
      total1 = total1.add(n.getValue());

    final BigDecimal expected1 = total1;
    final BigDecimal actual1 = total(numbers1, new Function1<ValueContainer<BigDecimal>, BigDecimal>() {
      @Override
      public BigDecimal apply(final ValueContainer<BigDecimal> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertThat(actual1, is(equalTo(expected1)));

    final ValueContainer<BigDecimal>[] numbers2 = RANDOM_VALUE_CONTAINERS;

    BigDecimal total2 = BigDecimal.ZERO;
    for (final ValueContainer<BigDecimal> n : numbers2)
      total2 = total2.add(n.getValue());

    final BigDecimal expected2 = total2;
    final BigDecimal actual2 = total(numbers2, new Function1<ValueContainer<BigDecimal>, BigDecimal>() {
      @Override
      public BigDecimal apply(final ValueContainer<BigDecimal> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertThat(actual2, is(equalTo(expected2)));
  }

  @Test
  public final void testTotalIterableOfBigDecimal()
  {
    final Iterable<BigDecimal> numbers1 = asList(NUMBERS1);
    final Iterable<BigDecimal> numbers2 = asList(NUMBERS2);

    BigDecimal total1 = BigDecimal.ZERO;
    for (final BigDecimal n : numbers1)
      total1 = total1.add(n);

    BigDecimal total2 = BigDecimal.ZERO;
    for (final BigDecimal n : numbers2)
      total2 = total2.add(n);

    final BigDecimal expected1 = total1;
    final BigDecimal actual1 = total(numbers1);
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertThat(actual1, is(equalTo(expected1)));
    final BigDecimal expected2 = total2;
    final BigDecimal actual2 = total(numbers2);
    // System.out.println("expected: " + expected2 + "\nactual:   " + actual2);
    assertThat(actual2, is(equalTo(expected2)));

    final Iterable<BigDecimal> numbers3 = asList(NUMBERS3);
    final Iterable<BigDecimal> numbers4 = asList(NUMBERS4);

    BigDecimal total3 = BigDecimal.ZERO;
    for (final BigDecimal n : numbers3)
      total3 = total3.add(n);

    BigDecimal total4 = BigDecimal.ZERO;
    for (final BigDecimal n : numbers4)
      total4 = total4.add(n);

    final BigDecimal expected3 = total3;
    final BigDecimal actual3 = total(numbers3);
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertThat(actual3, is(equalTo(expected3)));
    final BigDecimal expected4 = total4;
    final BigDecimal actual4 = total(numbers4);
    // System.out.println("expected: " + expected4 + "\nactual:   " + actual4);
    assertThat(actual4, is(equalTo(expected4)));
  }

  @Test
  public final void testTotalIterableOfT()
  {
    final Iterable<ValueContainer<BigDecimal>> numbers1 = asList(VALUE_CONTAINERS);

    BigDecimal total1 = BigDecimal.ZERO;
    for (final ValueContainer<BigDecimal> n : numbers1)
      total1 = total1.add(n.getValue());

    final BigDecimal expected1 = total1;
    final BigDecimal actual1 = total(numbers1, new Function1<ValueContainer<BigDecimal>, BigDecimal>() {
      @Override
      public BigDecimal apply(final ValueContainer<BigDecimal> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected1 + "\nactual:   " + actual1);
    assertThat(actual1, is(equalTo(expected1)));

    final Iterable<ValueContainer<BigDecimal>> numbers2 = asList(RANDOM_VALUE_CONTAINERS);

    BigDecimal total2 = BigDecimal.ZERO;
    for (final ValueContainer<BigDecimal> n : numbers2)
      total2 = total2.add(n.getValue());

    final BigDecimal expected2 = total2;
    final BigDecimal actual2 = total(numbers2, new Function1<ValueContainer<BigDecimal>, BigDecimal>() {
      @Override
      public BigDecimal apply(final ValueContainer<BigDecimal> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertThat(actual2, is(equalTo(expected2)));
    final BigDecimal actual2_2 = total(numbers2, new ToBigDecimalMapper<ValueContainer<BigDecimal>>() {
      @Override
      public BigDecimal apply(final ValueContainer<BigDecimal> input)
      {
        return input.getValue();
      }
    });
    // System.out.println("expected: " + expected3 + "\nactual:   " + actual3);
    assertThat(actual2_2, is(equalTo(expected2)));
  }
}
