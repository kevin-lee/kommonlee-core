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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

import org.elixirian.kommonlee.type.filter.ArgsFilter;
import org.elixirian.kommonlee.type.filter.Filter1;
import org.elixirian.kommonlee.type.function.Condition1;

/**
 * <pre>
 *     ___  _____                                              _____
 *    /   \/    / ______ __________________  ______ __ ______ /    /   ______  ______  
 *   /        / _/ __  // /  /   / /  /   /_/ __  // //     //    /   /  ___ \/  ___ \ 
 *  /        \ /  /_/ _/  _  _  /  _  _  //  /_/ _/   __   //    /___/  _____/  _____/
 * /____/\____\/_____//__//_//_/__//_//_/ /_____//___/ /__//________/\_____/ \_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-14)
 */
public final class Numbers
{
  private Numbers()
  {
  }

  private static final Condition1<Integer> ODD_INTEGER = new Condition1<Integer>() {
    @Override
    public boolean isMet(final Integer input)
    {
      return 0 != (input.intValue() & 1);
    }
  };

  public static Condition1<Integer> oddInteger()
  {
    return ODD_INTEGER;
  }

  private static final Condition1<Integer> EVEN_INTEGER = new Condition1<Integer>() {
    @Override
    public boolean isMet(final Integer input)
    {
      return 0 == (input.intValue() & 1);
    }
  };

  public static Condition1<Integer> evenInteger()
  {
    return EVEN_INTEGER;
  }

  private static final Condition1<Long> ODD_LONG = new Condition1<Long>() {
    @Override
    public boolean isMet(final Long input)
    {
      return 0 != (input.longValue() & 1);
    }
  };

  public static Condition1<Long> oddLong()
  {
    return ODD_LONG;
  }

  private static final Condition1<Long> EVEN_LONG = new Condition1<Long>() {
    @Override
    public boolean isMet(final Long input)
    {
      return 0 == (input.longValue() & 1);
    }
  };

  public static Condition1<Long> evenLong()
  {
    return EVEN_LONG;
  }

  public static abstract class NumberIterableFilter<T extends Number, C extends Collection<T>> implements
      Filter1<Iterable<T>, Condition1<T>, C>
  {
    @Override
    public C filter(final Condition1<T> condition, final Iterable<T> numbers)
    {
      final C filteredCollection = newCollection();
      for (final T value : numbers)
        if (condition.isMet(value))
          filteredCollection.add(value);

      return filteredCollection;
    }

    protected abstract C newCollection();
  }

  private static final class NumberIterableToArrayListFilter<T extends Number> extends
      NumberIterableFilter<T, ArrayList<T>>
  {
    @Override
    protected ArrayList<T> newCollection()
    {
      return new ArrayList<T>();
    }
  }

  private static final class NumberIterableToHashSetFilter<T extends Number> extends
      NumberIterableFilter<T, HashSet<T>>
  {
    @Override
    protected HashSet<T> newCollection()
    {
      return new HashSet<T>();
    }
  }

  private static final class NumberIterableToLinkedHashSetFilter<T extends Number> extends
      NumberIterableFilter<T, LinkedHashSet<T>>
  {
    @Override
    protected LinkedHashSet<T> newCollection()
    {
      return new LinkedHashSet<T>();
    }
  }

  public static <T extends Number> NumberIterableFilter<T, ArrayList<T>> numberIterableToArrayListFilter()
  {
    return new NumberIterableToArrayListFilter<T>();
  }

  private static final NumberIterableFilter<Byte, ArrayList<Byte>> BYTE_ITERABLE_TO_ARRAY_LIST_FILTER =
    numberIterableToArrayListFilter();
  private static final NumberIterableFilter<Short, ArrayList<Short>> SHORT_ITERABLE_TO_ARRAY_LIST_FILTER =
    numberIterableToArrayListFilter();
  private static final NumberIterableFilter<Integer, ArrayList<Integer>> INTEGER_ITERABLE_TO_ARRAY_LIST_FILTER =
    numberIterableToArrayListFilter();
  private static final NumberIterableFilter<Long, ArrayList<Long>> LONG_ITERABLE_TO_ARRAY_LIST_FILTER =
    numberIterableToArrayListFilter();
  private static final NumberIterableFilter<Float, ArrayList<Float>> FLOAT_ITERABLE_TO_ARRAY_LIST_FILTER =
    numberIterableToArrayListFilter();
  private static final NumberIterableFilter<Double, ArrayList<Double>> DOUBLE_ITERABLE_TO_ARRAY_LIST_FILTER =
    numberIterableToArrayListFilter();
  private static final NumberIterableFilter<BigInteger, ArrayList<BigInteger>> BIG_INTEGER_ITERABLE_TO_ARRAY_LIST_FILTER =
    numberIterableToArrayListFilter();
  private static final NumberIterableFilter<BigDecimal, ArrayList<BigDecimal>> BIG_DECIMAL_ITERABLE_TO_ARRAY_LIST_FILTER =
    numberIterableToArrayListFilter();

  public static NumberIterableFilter<Byte, ArrayList<Byte>> byteIterableToArrayListFilter()
  {
    return BYTE_ITERABLE_TO_ARRAY_LIST_FILTER;
  }

  public static NumberIterableFilter<Short, ArrayList<Short>> shortIterableToArrayListFilter()
  {
    return SHORT_ITERABLE_TO_ARRAY_LIST_FILTER;
  }

  public static NumberIterableFilter<Integer, ArrayList<Integer>> integerIterableToArrayListFilter()
  {
    return INTEGER_ITERABLE_TO_ARRAY_LIST_FILTER;
  }

  public static NumberIterableFilter<Long, ArrayList<Long>> longIterableToArrayListFilter()
  {
    return LONG_ITERABLE_TO_ARRAY_LIST_FILTER;
  }

  public static NumberIterableFilter<Float, ArrayList<Float>> floatIterableToArrayListFilter()
  {
    return FLOAT_ITERABLE_TO_ARRAY_LIST_FILTER;
  }

  public static NumberIterableFilter<Double, ArrayList<Double>> doubleIterableToArrayListFilter()
  {
    return DOUBLE_ITERABLE_TO_ARRAY_LIST_FILTER;
  }

  public static NumberIterableFilter<BigInteger, ArrayList<BigInteger>> bigIntegerIterableToArrayListFilter()
  {
    return BIG_INTEGER_ITERABLE_TO_ARRAY_LIST_FILTER;
  }

  public static NumberIterableFilter<BigDecimal, ArrayList<BigDecimal>> bigDecimalIterableToArrayListFilter()
  {
    return BIG_DECIMAL_ITERABLE_TO_ARRAY_LIST_FILTER;
  }

  public static <T extends Number> NumberIterableFilter<T, HashSet<T>> numberIterableToHashSetFilter()
  {
    return new NumberIterableToHashSetFilter<T>();
  }

  private static final NumberIterableFilter<Byte, HashSet<Byte>> BYTE_ITERABLE_TO_HASH_SET_FILTER =
    numberIterableToHashSetFilter();
  private static final NumberIterableFilter<Short, HashSet<Short>> SHORT_ITERABLE_TO_HASH_SET_FILTER =
    numberIterableToHashSetFilter();
  private static final NumberIterableFilter<Integer, HashSet<Integer>> INTEGER_ITERABLE_TO_HASH_SET_FILTER =
    numberIterableToHashSetFilter();
  private static final NumberIterableFilter<Long, HashSet<Long>> LONG_ITERABLE_TO_HASH_SET_FILTER =
    numberIterableToHashSetFilter();
  private static final NumberIterableFilter<Float, HashSet<Float>> FLOAT_ITERABLE_TO_HASH_SET_FILTER =
    numberIterableToHashSetFilter();
  private static final NumberIterableFilter<Double, HashSet<Double>> DOUBLE_ITERABLE_TO_HASH_SET_FILTER =
    numberIterableToHashSetFilter();
  private static final NumberIterableFilter<BigInteger, HashSet<BigInteger>> BIG_INTEGER_ITERABLE_TO_HASH_SET_FILTER =
    numberIterableToHashSetFilter();
  private static final NumberIterableFilter<BigDecimal, HashSet<BigDecimal>> BIG_DECIMAL_ITERABLE_TO_HASH_SET_FILTER =
    numberIterableToHashSetFilter();

  public static NumberIterableFilter<Byte, HashSet<Byte>> byteIterableToHashSetFilter()
  {
    return BYTE_ITERABLE_TO_HASH_SET_FILTER;
  }

  public static NumberIterableFilter<Short, HashSet<Short>> shortIterableToHashSetFilter()
  {
    return SHORT_ITERABLE_TO_HASH_SET_FILTER;
  }

  public static NumberIterableFilter<Integer, HashSet<Integer>> integerIterableToHashSetFilter()
  {
    return INTEGER_ITERABLE_TO_HASH_SET_FILTER;
  }

  public static NumberIterableFilter<Long, HashSet<Long>> longIterableToHashSetFilter()
  {
    return LONG_ITERABLE_TO_HASH_SET_FILTER;
  }

  public static NumberIterableFilter<Float, HashSet<Float>> floatIterableToHashSetFilter()
  {
    return FLOAT_ITERABLE_TO_HASH_SET_FILTER;
  }

  public static NumberIterableFilter<Double, HashSet<Double>> doubleIterableToHashSetFilter()
  {
    return DOUBLE_ITERABLE_TO_HASH_SET_FILTER;
  }

  public static NumberIterableFilter<BigInteger, HashSet<BigInteger>> bigIntegerIterableToHashSetFilter()
  {
    return BIG_INTEGER_ITERABLE_TO_HASH_SET_FILTER;
  }

  public static NumberIterableFilter<BigDecimal, HashSet<BigDecimal>> bigDecimalIterableToHashSetFilter()
  {
    return BIG_DECIMAL_ITERABLE_TO_HASH_SET_FILTER;
  }

  public static <T extends Number> NumberIterableFilter<T, LinkedHashSet<T>> numberIterableToLinkedHashSetFilter()
  {
    return new NumberIterableToLinkedHashSetFilter<T>();
  }

  private static final NumberIterableFilter<Byte, LinkedHashSet<Byte>> BYTE_ITERABLE_TO_LINKED_HASH_SET_FILTER =
    numberIterableToLinkedHashSetFilter();
  private static final NumberIterableFilter<Short, LinkedHashSet<Short>> SHORT_ITERABLE_TO_LINKED_HASH_SET_FILTER =
    numberIterableToLinkedHashSetFilter();
  private static final NumberIterableFilter<Integer, LinkedHashSet<Integer>> INTEGER_ITERABLE_TO_LINKED_HASH_SET_FILTER =
    numberIterableToLinkedHashSetFilter();
  private static final NumberIterableFilter<Long, LinkedHashSet<Long>> LONG_ITERABLE_TO_LINKED_HASH_SET_FILTER =
    numberIterableToLinkedHashSetFilter();
  private static final NumberIterableFilter<Float, LinkedHashSet<Float>> FLOAT_ITERABLE_TO_LINKED_HASH_SET_FILTER =
    numberIterableToLinkedHashSetFilter();
  private static final NumberIterableFilter<Double, LinkedHashSet<Double>> DOUBLE_ITERABLE_TO_LINKED_HASH_SET_FILTER =
    numberIterableToLinkedHashSetFilter();
  private static final NumberIterableFilter<BigInteger, LinkedHashSet<BigInteger>> BIG_INTEGER_ITERABLE_TO_LINKED_HASH_SET_FILTER =
    numberIterableToLinkedHashSetFilter();
  private static final NumberIterableFilter<BigDecimal, LinkedHashSet<BigDecimal>> BIG_DECIMAL_ITERABLE_TO_LINKED_HASH_SET_FILTER =
    numberIterableToLinkedHashSetFilter();

  public static NumberIterableFilter<Byte, LinkedHashSet<Byte>> byteIterableToLinkedHashSetFilter()
  {
    return BYTE_ITERABLE_TO_LINKED_HASH_SET_FILTER;
  }

  public static NumberIterableFilter<Short, LinkedHashSet<Short>> shortIterableToLinkedHashSetFilter()
  {
    return SHORT_ITERABLE_TO_LINKED_HASH_SET_FILTER;
  }

  public static NumberIterableFilter<Integer, LinkedHashSet<Integer>> integerIterableToLinkedHashSetFilter()
  {
    return INTEGER_ITERABLE_TO_LINKED_HASH_SET_FILTER;
  }

  public static NumberIterableFilter<Long, LinkedHashSet<Long>> longIterableToLinkedHashSetFilter()
  {
    return LONG_ITERABLE_TO_LINKED_HASH_SET_FILTER;
  }

  public static NumberIterableFilter<Float, LinkedHashSet<Float>> floatIterableToLinkedHashSetFilter()
  {
    return FLOAT_ITERABLE_TO_LINKED_HASH_SET_FILTER;
  }

  public static NumberIterableFilter<Double, LinkedHashSet<Double>> doubleIterableToLinkedHashSetFilter()
  {
    return DOUBLE_ITERABLE_TO_LINKED_HASH_SET_FILTER;
  }

  public static NumberIterableFilter<BigInteger, LinkedHashSet<BigInteger>> bigIntegerIterableToLinkedHashSetFilter()
  {
    return BIG_INTEGER_ITERABLE_TO_LINKED_HASH_SET_FILTER;
  }

  public static NumberIterableFilter<BigDecimal, LinkedHashSet<BigDecimal>> bigDecimalIterableToLinkedHashSetFilter()
  {
    return BIG_DECIMAL_ITERABLE_TO_LINKED_HASH_SET_FILTER;
  }

  public static abstract class NumbersFilter<T extends Number, C extends Collection<T>> implements
      ArgsFilter<T, Condition1<T>, C>
  {
    protected abstract C newCollection();

    @Override
    public C filter(final Condition1<T> condition, final T first)
    {
      final C filteredCollection = newCollection();
      if (condition.isMet(first))
        filteredCollection.add(first);
      return filteredCollection;
    }

    @Override
    public C filter(final Condition1<T> condition, final T first, final T second)
    {
      final C filteredCollection = newCollection();
      if (condition.isMet(first))
        filteredCollection.add(first);
      if (condition.isMet(second))
        filteredCollection.add(second);
      return filteredCollection;
    }

    @Override
    public C filter(final Condition1<T> condition, final T first, final T second, final T third)
    {
      final C filteredCollection = newCollection();
      if (condition.isMet(first))
        filteredCollection.add(first);
      if (condition.isMet(second))
        filteredCollection.add(second);
      if (condition.isMet(third))
        filteredCollection.add(third);
      return filteredCollection;
    }

    @Override
    public C filter(final Condition1<T> condition, final T first, final T second, final T third, final T fourth)
    {
      final C filteredCollection = newCollection();
      if (condition.isMet(first))
        filteredCollection.add(first);
      if (condition.isMet(second))
        filteredCollection.add(second);
      if (condition.isMet(third))
        filteredCollection.add(third);
      if (condition.isMet(fourth))
        filteredCollection.add(fourth);
      return filteredCollection;
    }

    @Override
    public C filter(final Condition1<T> condition, final T first, final T second, final T third, final T fourth,
        final T fifth)
    {
      final C filteredCollection = newCollection();
      if (condition.isMet(first))
        filteredCollection.add(first);
      if (condition.isMet(second))
        filteredCollection.add(second);
      if (condition.isMet(third))
        filteredCollection.add(third);
      if (condition.isMet(fourth))
        filteredCollection.add(fourth);
      if (condition.isMet(fifth))
        filteredCollection.add(fifth);
      return filteredCollection;
    }

    @Override
    public C filter(final Condition1<T> condition, final T first, final T second, final T third, final T fourth,
        final T fifth, final T... rest)
    {
      final C filteredCollection = newCollection();
      if (condition.isMet(first))
        filteredCollection.add(first);
      if (condition.isMet(second))
        filteredCollection.add(second);
      if (condition.isMet(third))
        filteredCollection.add(third);
      if (condition.isMet(fourth))
        filteredCollection.add(fourth);
      if (condition.isMet(fifth))
        filteredCollection.add(fifth);
      filter0(filteredCollection, condition, rest);
      return filteredCollection;
    }

    private void filter0(final C filteredCollection, final Condition1<T> condition, final T[] numbers)
    {
      for (final T value : numbers)
      {
        if (condition.isMet(value))
          filteredCollection.add(value);
      }
    }

    @Override
    public C filter(final Condition1<T> condition, final T[] source)
    {
      final C filteredCollection = newCollection();
      filter0(filteredCollection, condition, source);
      return filteredCollection;
    }
  }

  private static final class NumbersToArrayListFilter<T extends Number> extends NumbersFilter<T, ArrayList<T>>
  {
    @Override
    protected ArrayList<T> newCollection()
    {
      return new ArrayList<T>();
    }
  }

  private static final class NumbersToHashSetFilter<T extends Number> extends NumbersFilter<T, HashSet<T>>
  {
    @Override
    protected HashSet<T> newCollection()
    {
      return new HashSet<T>();
    }
  }

  private static final class NumbersToLinkedHashSetFilter<T extends Number> extends NumbersFilter<T, LinkedHashSet<T>>
  {
    @Override
    protected LinkedHashSet<T> newCollection()
    {
      return new LinkedHashSet<T>();
    }
  }

  public static <T extends Number> NumbersFilter<T, ArrayList<T>> numbersToArrayListFilter()
  {
    return new NumbersToArrayListFilter<T>();
  }

  private static final NumbersFilter<Byte, ArrayList<Byte>> BYTES_TO_ARRAY_LIST_FILTER = numbersToArrayListFilter();
  private static final NumbersFilter<Short, ArrayList<Short>> SHORTS_TO_ARRAY_LIST_FILTER = numbersToArrayListFilter();
  private static final NumbersFilter<Integer, ArrayList<Integer>> INTEGERS_TO_ARRAY_LIST_FILTER =
    numbersToArrayListFilter();
  private static final NumbersFilter<Long, ArrayList<Long>> LONGS_TO_ARRAY_LIST_FILTER = numbersToArrayListFilter();
  private static final NumbersFilter<Float, ArrayList<Float>> FLOATS_TO_ARRAY_LIST_FILTER = numbersToArrayListFilter();
  private static final NumbersFilter<Double, ArrayList<Double>> DOUBLES_TO_ARRAY_LIST_FILTER =
    numbersToArrayListFilter();
  private static final NumbersFilter<BigInteger, ArrayList<BigInteger>> BIG_INTEGERS_TO_ARRAY_LIST_FILTER =
    numbersToArrayListFilter();
  private static final NumbersFilter<BigDecimal, ArrayList<BigDecimal>> BIG_DECIMALS_TO_ARRAY_LIST_FILTER =
    numbersToArrayListFilter();

  public static NumbersFilter<Byte, ArrayList<Byte>> bytesToArrayListFilter()
  {
    return BYTES_TO_ARRAY_LIST_FILTER;
  }

  public static NumbersFilter<Short, ArrayList<Short>> shortsToArrayListFilter()
  {
    return SHORTS_TO_ARRAY_LIST_FILTER;
  }

  public static NumbersFilter<Integer, ArrayList<Integer>> integersToArrayListFilter()
  {
    return INTEGERS_TO_ARRAY_LIST_FILTER;
  }

  public static NumbersFilter<Long, ArrayList<Long>> longsToArrayListFilter()
  {
    return LONGS_TO_ARRAY_LIST_FILTER;
  }

  public static NumbersFilter<Float, ArrayList<Float>> floatsToArrayListFilter()
  {
    return FLOATS_TO_ARRAY_LIST_FILTER;
  }

  public static NumbersFilter<Double, ArrayList<Double>> doublesToArrayListFilter()
  {
    return DOUBLES_TO_ARRAY_LIST_FILTER;
  }

  public static NumbersFilter<BigInteger, ArrayList<BigInteger>> bigIntegersToArrayListFilter()
  {
    return BIG_INTEGERS_TO_ARRAY_LIST_FILTER;
  }

  public static NumbersFilter<BigDecimal, ArrayList<BigDecimal>> bigDecimalsToArrayListFilter()
  {
    return BIG_DECIMALS_TO_ARRAY_LIST_FILTER;
  }

  public static <T extends Number> NumbersFilter<T, HashSet<T>> numbersToHashSetFilter()
  {
    return new NumbersToHashSetFilter<T>();
  }

  private static final NumbersFilter<Byte, HashSet<Byte>> BYTES_TO_HASH_SET_FILTER = numbersToHashSetFilter();
  private static final NumbersFilter<Short, HashSet<Short>> SHORTS_TO_HASH_SET_FILTER = numbersToHashSetFilter();
  private static final NumbersFilter<Integer, HashSet<Integer>> INTEGERS_TO_HASH_SET_FILTER = numbersToHashSetFilter();
  private static final NumbersFilter<Long, HashSet<Long>> LONGS_TO_HASH_SET_FILTER = numbersToHashSetFilter();
  private static final NumbersFilter<Float, HashSet<Float>> FLOATS_TO_HASH_SET_FILTER = numbersToHashSetFilter();
  private static final NumbersFilter<Double, HashSet<Double>> DOUBLES_TO_HASH_SET_FILTER = numbersToHashSetFilter();
  private static final NumbersFilter<BigInteger, HashSet<BigInteger>> BIG_INTEGERS_TO_HASH_SET_FILTER =
    numbersToHashSetFilter();
  private static final NumbersFilter<BigDecimal, HashSet<BigDecimal>> BIG_DECIMALS_TO_HASH_SET_FILTER =
    numbersToHashSetFilter();

  public static NumbersFilter<Byte, HashSet<Byte>> bytesToHashSetFilter()
  {
    return BYTES_TO_HASH_SET_FILTER;
  }

  public static NumbersFilter<Short, HashSet<Short>> shortsToHashSetFilter()
  {
    return SHORTS_TO_HASH_SET_FILTER;
  }

  public static NumbersFilter<Integer, HashSet<Integer>> integersToHashSetFilter()
  {
    return INTEGERS_TO_HASH_SET_FILTER;
  }

  public static NumbersFilter<Long, HashSet<Long>> longsToHashSetFilter()
  {
    return LONGS_TO_HASH_SET_FILTER;
  }

  public static NumbersFilter<Float, HashSet<Float>> floatsToHashSetFilter()
  {
    return FLOATS_TO_HASH_SET_FILTER;
  }

  public static NumbersFilter<Double, HashSet<Double>> doublesToHashSetFilter()
  {
    return DOUBLES_TO_HASH_SET_FILTER;
  }

  public static NumbersFilter<BigInteger, HashSet<BigInteger>> bigIntegersToHashSetFilter()
  {
    return BIG_INTEGERS_TO_HASH_SET_FILTER;
  }

  public static NumbersFilter<BigDecimal, HashSet<BigDecimal>> bigDecimalsToHashSetFilter()
  {
    return BIG_DECIMALS_TO_HASH_SET_FILTER;
  }

  public static <T extends Number> NumbersFilter<T, LinkedHashSet<T>> numbersToLinkedHashSetFilter()
  {
    return new NumbersToLinkedHashSetFilter<T>();
  }

  private static final NumbersFilter<Byte, LinkedHashSet<Byte>> BYTES_TO_LINKED_HASH_SET_FILTER =
    numbersToLinkedHashSetFilter();
  private static final NumbersFilter<Short, LinkedHashSet<Short>> SHORTS_TO_LINKED_HASH_SET_FILTER =
    numbersToLinkedHashSetFilter();
  private static final NumbersFilter<Integer, LinkedHashSet<Integer>> INTEGERS_TO_LINKED_HASH_SET_FILTER =
    numbersToLinkedHashSetFilter();
  private static final NumbersFilter<Long, LinkedHashSet<Long>> LONGS_TO_LINKED_HASH_SET_FILTER =
    numbersToLinkedHashSetFilter();
  private static final NumbersFilter<Float, LinkedHashSet<Float>> FLOATS_TO_LINKED_HASH_SET_FILTER =
    numbersToLinkedHashSetFilter();
  private static final NumbersFilter<Double, LinkedHashSet<Double>> DOUBLES_TO_LINKED_HASH_SET_FILTER =
    numbersToLinkedHashSetFilter();
  private static final NumbersFilter<BigInteger, LinkedHashSet<BigInteger>> BIG_INTEGERS_TO_LINKED_HASH_SET_FILTER =
    numbersToLinkedHashSetFilter();
  private static final NumbersFilter<BigDecimal, LinkedHashSet<BigDecimal>> BIG_DECIMALS_TO_LINKED_HASH_SET_FILTER =
    numbersToLinkedHashSetFilter();

  public static NumbersFilter<Byte, LinkedHashSet<Byte>> bytesToLinkedHashSetFilter()
  {
    return BYTES_TO_LINKED_HASH_SET_FILTER;
  }

  public static NumbersFilter<Short, LinkedHashSet<Short>> shortsToLinkedHashSetFilter()
  {
    return SHORTS_TO_LINKED_HASH_SET_FILTER;
  }

  public static NumbersFilter<Integer, LinkedHashSet<Integer>> integersToLinkedHashSetFilter()
  {
    return INTEGERS_TO_LINKED_HASH_SET_FILTER;
  }

  public static NumbersFilter<Long, LinkedHashSet<Long>> longsToLinkedHashSetFilter()
  {
    return LONGS_TO_LINKED_HASH_SET_FILTER;
  }

  public static NumbersFilter<Float, LinkedHashSet<Float>> floatsToLinkedHashSetFilter()
  {
    return FLOATS_TO_LINKED_HASH_SET_FILTER;
  }

  public static NumbersFilter<Double, LinkedHashSet<Double>> doublesToLinkedHashSetFilter()
  {
    return DOUBLES_TO_LINKED_HASH_SET_FILTER;
  }

  public static NumbersFilter<BigInteger, LinkedHashSet<BigInteger>> bigIntegersToLinkedHashSetFilter()
  {
    return BIG_INTEGERS_TO_LINKED_HASH_SET_FILTER;
  }

  public static NumbersFilter<BigDecimal, LinkedHashSet<BigDecimal>> bigDecimalsToLinkedHashSetFilter()
  {
    return BIG_DECIMALS_TO_LINKED_HASH_SET_FILTER;
  }

  /* convert primitive to boxed primitive */

  /* byte */
  public static Byte[] convertToBoxed(final byte[] numbers)
  {
    final int length = numbers.length;
    final Byte[] result = new Byte[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Byte castedNumber = numbers[i];
      result[i] = castedNumber;
    }
    return result;
  }

  public static ArrayList<Byte> convertToArrayListOfBoxed(final byte[] numbers)
  {
    final int length = numbers.length;
    final ArrayList<Byte> result = new ArrayList<Byte>(length);
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Byte castedNumber = numbers[i];
      result.add(castedNumber);
    }
    return result;
  }

  public static byte[] convertToPrimitive(final Byte[] numbers)
  {
    final int length = numbers.length;
    final byte[] result = new byte[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final byte castedNumber = numbers[i];
      result[i] = castedNumber;
    }
    return result;
  }

  /* short */
  public static Short[] convertToBoxed(final short[] numbers)
  {
    final int length = numbers.length;
    final Short[] result = new Short[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Short castedNumber = numbers[i];
      result[i] = castedNumber;
    }
    return result;
  }

  public static ArrayList<Short> convertToArrayListOfBoxed(final short[] numbers)
  {
    final int length = numbers.length;
    final ArrayList<Short> result = new ArrayList<Short>(length);
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Short castedNumber = numbers[i];
      result.add(castedNumber);
    }
    return result;
  }

  public static short[] convertToPrimitive(final Short[] numbers)
  {
    final int length = numbers.length;
    final short[] result = new short[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final short castedNumber = numbers[i];
      result[i] = castedNumber;
    }
    return result;
  }

  /* int */
  public static Integer[] convertToBoxed(final int[] numbers)
  {
    final int length = numbers.length;
    final Integer[] result = new Integer[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Integer castedNumber = numbers[i];
      result[i] = castedNumber;
    }
    return result;
  }

  public static ArrayList<Integer> convertToArrayListOfBoxed(final int[] numbers)
  {
    final int length = numbers.length;
    final ArrayList<Integer> result = new ArrayList<Integer>(length);
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Integer castedNumber = numbers[i];
      result.add(castedNumber);
    }
    return result;
  }

  public static int[] convertToPrimitive(final Integer[] numbers)
  {
    final int length = numbers.length;
    final int[] result = new int[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final int castedNumber = numbers[i];
      result[i] = castedNumber;
    }
    return result;
  }

  /* long */
  public static Long[] convertToBoxed(final long[] numbers)
  {
    final int length = numbers.length;
    final Long[] result = new Long[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Long castedNumber = numbers[i];
      result[i] = castedNumber;
    }
    return result;
  }

  public static ArrayList<Long> convertToArrayListOfBoxed(final long[] numbers)
  {
    final int length = numbers.length;
    final ArrayList<Long> result = new ArrayList<Long>(length);
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Long castedNumber = numbers[i];
      result.add(castedNumber);
    }
    return result;
  }

  public static long[] convertToPrimitive(final Long[] numbers)
  {
    final int length = numbers.length;
    final long[] result = new long[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final long castedNumber = numbers[i];
      result[i] = castedNumber;
    }
    return result;
  }

  /* float */
  public static Float[] convertToBoxed(final float[] numbers)
  {
    final int length = numbers.length;
    final Float[] result = new Float[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Float castedNumber = numbers[i];
      result[i] = castedNumber;
    }
    return result;
  }

  public static ArrayList<Float> convertToArrayListOfBoxed(final float[] numbers)
  {
    final int length = numbers.length;
    final ArrayList<Float> result = new ArrayList<Float>(length);
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Float castedNumber = numbers[i];
      result.add(castedNumber);
    }
    return result;
  }

  public static float[] convertToPrimitive(final Float[] numbers)
  {
    final int length = numbers.length;
    final float[] result = new float[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final float castedNumber = numbers[i];
      result[i] = castedNumber;
    }
    return result;
  }

  /* double */
  public static Double[] convertToBoxed(final double[] numbers)
  {
    final int length = numbers.length;
    final Double[] result = new Double[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Double castedNumber = numbers[i];
      result[i] = castedNumber;
    }
    return result;
  }

  public static ArrayList<Double> convertToArrayListOfBoxed(final double[] numbers)
  {
    final int length = numbers.length;
    final ArrayList<Double> result = new ArrayList<Double>(length);
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Double castedNumber = numbers[i];
      result.add(castedNumber);
    }
    return result;
  }

  public static double[] convertToPrimitive(final Double[] numbers)
  {
    final int length = numbers.length;
    final double[] result = new double[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final double castedNumber = numbers[i];
      result[i] = castedNumber;
    }
    return result;
  }

}
