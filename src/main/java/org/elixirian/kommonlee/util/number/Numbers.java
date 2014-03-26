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

import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.selector.ArgsSelector;
import org.elixirian.kommonlee.type.selector.Selector1;
import org.elixirian.kommonlee.util.CommonConstants;

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
  private Numbers() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
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

  public static abstract class NumberIterableSelector<T extends Number, C extends Collection<T>> implements
      Selector1<Iterable<T>, Condition1<T>, C>
  {
    @Override
    public C select(final Condition1<T> condition, final Iterable<T> numbers)
    {
      final C selectedCollection = newCollection();
      for (final T value : numbers)
        if (condition.isMet(value))
          selectedCollection.add(value);

      return selectedCollection;
    }

    protected abstract C newCollection();
  }

  private static final class NumberIterableToArrayListSelector<T extends Number> extends
      NumberIterableSelector<T, ArrayList<T>>
  {
    @Override
    protected ArrayList<T> newCollection()
    {
      return new ArrayList<T>();
    }
  }

  private static final class NumberIterableToHashSetSelector<T extends Number> extends
      NumberIterableSelector<T, HashSet<T>>
  {
    @Override
    protected HashSet<T> newCollection()
    {
      return new HashSet<T>();
    }
  }

  private static final class NumberIterableToLinkedHashSetSelector<T extends Number> extends
      NumberIterableSelector<T, LinkedHashSet<T>>
  {
    @Override
    protected LinkedHashSet<T> newCollection()
    {
      return new LinkedHashSet<T>();
    }
  }

  public static <T extends Number> NumberIterableSelector<T, ArrayList<T>> numberIterableToArrayListSelector()
  {
    return new NumberIterableToArrayListSelector<T>();
  }

  private static final NumberIterableSelector<Byte, ArrayList<Byte>> BYTE_ITERABLE_TO_ARRAY_LIST_SELECTOR =
    numberIterableToArrayListSelector();
  private static final NumberIterableSelector<Short, ArrayList<Short>> SHORT_ITERABLE_TO_ARRAY_LIST_SELECTOR =
    numberIterableToArrayListSelector();
  private static final NumberIterableSelector<Integer, ArrayList<Integer>> INTEGER_ITERABLE_TO_ARRAY_LIST_SELECTOR =
    numberIterableToArrayListSelector();
  private static final NumberIterableSelector<Long, ArrayList<Long>> LONG_ITERABLE_TO_ARRAY_LIST_SELECTOR =
    numberIterableToArrayListSelector();
  private static final NumberIterableSelector<Float, ArrayList<Float>> FLOAT_ITERABLE_TO_ARRAY_LIST_SELECTOR =
    numberIterableToArrayListSelector();
  private static final NumberIterableSelector<Double, ArrayList<Double>> DOUBLE_ITERABLE_TO_ARRAY_LIST_SELECTOR =
    numberIterableToArrayListSelector();
  private static final NumberIterableSelector<BigInteger, ArrayList<BigInteger>> BIG_INTEGER_ITERABLE_TO_ARRAY_LIST_SELECTOR =
    numberIterableToArrayListSelector();
  private static final NumberIterableSelector<BigDecimal, ArrayList<BigDecimal>> BIG_DECIMAL_ITERABLE_TO_ARRAY_LIST_SELECTOR =
    numberIterableToArrayListSelector();

  public static NumberIterableSelector<Byte, ArrayList<Byte>> byteIterableToArrayListSelector()
  {
    return BYTE_ITERABLE_TO_ARRAY_LIST_SELECTOR;
  }

  public static NumberIterableSelector<Short, ArrayList<Short>> shortIterableToArrayListSelector()
  {
    return SHORT_ITERABLE_TO_ARRAY_LIST_SELECTOR;
  }

  public static NumberIterableSelector<Integer, ArrayList<Integer>> integerIterableToArrayListSelector()
  {
    return INTEGER_ITERABLE_TO_ARRAY_LIST_SELECTOR;
  }

  public static NumberIterableSelector<Long, ArrayList<Long>> longIterableToArrayListSelector()
  {
    return LONG_ITERABLE_TO_ARRAY_LIST_SELECTOR;
  }

  public static NumberIterableSelector<Float, ArrayList<Float>> floatIterableToArrayListSelector()
  {
    return FLOAT_ITERABLE_TO_ARRAY_LIST_SELECTOR;
  }

  public static NumberIterableSelector<Double, ArrayList<Double>> doubleIterableToArrayListSelector()
  {
    return DOUBLE_ITERABLE_TO_ARRAY_LIST_SELECTOR;
  }

  public static NumberIterableSelector<BigInteger, ArrayList<BigInteger>> bigIntegerIterableToArrayListSelector()
  {
    return BIG_INTEGER_ITERABLE_TO_ARRAY_LIST_SELECTOR;
  }

  public static NumberIterableSelector<BigDecimal, ArrayList<BigDecimal>> bigDecimalIterableToArrayListSelector()
  {
    return BIG_DECIMAL_ITERABLE_TO_ARRAY_LIST_SELECTOR;
  }

  public static <T extends Number> NumberIterableSelector<T, HashSet<T>> numberIterableToHashSetSelector()
  {
    return new NumberIterableToHashSetSelector<T>();
  }

  private static final NumberIterableSelector<Byte, HashSet<Byte>> BYTE_ITERABLE_TO_HASH_SET_SELECTOR =
    numberIterableToHashSetSelector();
  private static final NumberIterableSelector<Short, HashSet<Short>> SHORT_ITERABLE_TO_HASH_SET_SELECTOR =
    numberIterableToHashSetSelector();
  private static final NumberIterableSelector<Integer, HashSet<Integer>> INTEGER_ITERABLE_TO_HASH_SET_SELECTOR =
    numberIterableToHashSetSelector();
  private static final NumberIterableSelector<Long, HashSet<Long>> LONG_ITERABLE_TO_HASH_SET_SELECTOR =
    numberIterableToHashSetSelector();
  private static final NumberIterableSelector<Float, HashSet<Float>> FLOAT_ITERABLE_TO_HASH_SET_SELECTOR =
    numberIterableToHashSetSelector();
  private static final NumberIterableSelector<Double, HashSet<Double>> DOUBLE_ITERABLE_TO_HASH_SET_SELECTOR =
    numberIterableToHashSetSelector();
  private static final NumberIterableSelector<BigInteger, HashSet<BigInteger>> BIG_INTEGER_ITERABLE_TO_HASH_SET_SELECTOR =
    numberIterableToHashSetSelector();
  private static final NumberIterableSelector<BigDecimal, HashSet<BigDecimal>> BIG_DECIMAL_ITERABLE_TO_HASH_SET_SELECTOR =
    numberIterableToHashSetSelector();

  public static NumberIterableSelector<Byte, HashSet<Byte>> byteIterableToHashSetSelector()
  {
    return BYTE_ITERABLE_TO_HASH_SET_SELECTOR;
  }

  public static NumberIterableSelector<Short, HashSet<Short>> shortIterableToHashSetSelector()
  {
    return SHORT_ITERABLE_TO_HASH_SET_SELECTOR;
  }

  public static NumberIterableSelector<Integer, HashSet<Integer>> integerIterableToHashSetSelector()
  {
    return INTEGER_ITERABLE_TO_HASH_SET_SELECTOR;
  }

  public static NumberIterableSelector<Long, HashSet<Long>> longIterableToHashSetSelector()
  {
    return LONG_ITERABLE_TO_HASH_SET_SELECTOR;
  }

  public static NumberIterableSelector<Float, HashSet<Float>> floatIterableToHashSetSelector()
  {
    return FLOAT_ITERABLE_TO_HASH_SET_SELECTOR;
  }

  public static NumberIterableSelector<Double, HashSet<Double>> doubleIterableToHashSetSelector()
  {
    return DOUBLE_ITERABLE_TO_HASH_SET_SELECTOR;
  }

  public static NumberIterableSelector<BigInteger, HashSet<BigInteger>> bigIntegerIterableToHashSetSelector()
  {
    return BIG_INTEGER_ITERABLE_TO_HASH_SET_SELECTOR;
  }

  public static NumberIterableSelector<BigDecimal, HashSet<BigDecimal>> bigDecimalIterableToHashSetSelector()
  {
    return BIG_DECIMAL_ITERABLE_TO_HASH_SET_SELECTOR;
  }

  public static <T extends Number> NumberIterableSelector<T, LinkedHashSet<T>> numberIterableToLinkedHashSetSelector()
  {
    return new NumberIterableToLinkedHashSetSelector<T>();
  }

  private static final NumberIterableSelector<Byte, LinkedHashSet<Byte>> BYTE_ITERABLE_TO_LINKED_HASH_SET_SELECTOR =
    numberIterableToLinkedHashSetSelector();
  private static final NumberIterableSelector<Short, LinkedHashSet<Short>> SHORT_ITERABLE_TO_LINKED_HASH_SET_SELECTOR =
    numberIterableToLinkedHashSetSelector();
  private static final NumberIterableSelector<Integer, LinkedHashSet<Integer>> INTEGER_ITERABLE_TO_LINKED_HASH_SET_SELECTOR =
    numberIterableToLinkedHashSetSelector();
  private static final NumberIterableSelector<Long, LinkedHashSet<Long>> LONG_ITERABLE_TO_LINKED_HASH_SET_SELECTOR =
    numberIterableToLinkedHashSetSelector();
  private static final NumberIterableSelector<Float, LinkedHashSet<Float>> FLOAT_ITERABLE_TO_LINKED_HASH_SET_SELECTOR =
    numberIterableToLinkedHashSetSelector();
  private static final NumberIterableSelector<Double, LinkedHashSet<Double>> DOUBLE_ITERABLE_TO_LINKED_HASH_SET_SELECTOR =
    numberIterableToLinkedHashSetSelector();
  private static final NumberIterableSelector<BigInteger, LinkedHashSet<BigInteger>> BIG_INTEGER_ITERABLE_TO_LINKED_HASH_SET_SELECTOR =
    numberIterableToLinkedHashSetSelector();
  private static final NumberIterableSelector<BigDecimal, LinkedHashSet<BigDecimal>> BIG_DECIMAL_ITERABLE_TO_LINKED_HASH_SET_SELECTOR =
    numberIterableToLinkedHashSetSelector();

  public static NumberIterableSelector<Byte, LinkedHashSet<Byte>> byteIterableToLinkedHashSetSelector()
  {
    return BYTE_ITERABLE_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static NumberIterableSelector<Short, LinkedHashSet<Short>> shortIterableToLinkedHashSetSelector()
  {
    return SHORT_ITERABLE_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static NumberIterableSelector<Integer, LinkedHashSet<Integer>> integerIterableToLinkedHashSetSelector()
  {
    return INTEGER_ITERABLE_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static NumberIterableSelector<Long, LinkedHashSet<Long>> longIterableToLinkedHashSetSelector()
  {
    return LONG_ITERABLE_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static NumberIterableSelector<Float, LinkedHashSet<Float>> floatIterableToLinkedHashSetSelector()
  {
    return FLOAT_ITERABLE_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static NumberIterableSelector<Double, LinkedHashSet<Double>> doubleIterableToLinkedHashSetSelector()
  {
    return DOUBLE_ITERABLE_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static NumberIterableSelector<BigInteger, LinkedHashSet<BigInteger>> bigIntegerIterableToLinkedHashSetSelector()
  {
    return BIG_INTEGER_ITERABLE_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static NumberIterableSelector<BigDecimal, LinkedHashSet<BigDecimal>> bigDecimalIterableToLinkedHashSetSelector()
  {
    return BIG_DECIMAL_ITERABLE_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static abstract class NumbersSelector<T extends Number, C extends Collection<T>> implements
      ArgsSelector<T, Condition1<T>, C>
  {
    protected abstract C newCollection();

    @Override
    public C select(final Condition1<T> condition, final T first)
    {
      final C selectedCollection = newCollection();
      if (condition.isMet(first))
        selectedCollection.add(first);
      return selectedCollection;
    }

    @Override
    public C select(final Condition1<T> condition, final T first, final T second)
    {
      final C selectedCollection = newCollection();
      if (condition.isMet(first))
        selectedCollection.add(first);
      if (condition.isMet(second))
        selectedCollection.add(second);
      return selectedCollection;
    }

    @Override
    public C select(final Condition1<T> condition, final T first, final T second, final T third)
    {
      final C selectedCollection = newCollection();
      if (condition.isMet(first))
        selectedCollection.add(first);
      if (condition.isMet(second))
        selectedCollection.add(second);
      if (condition.isMet(third))
        selectedCollection.add(third);
      return selectedCollection;
    }

    @Override
    public C select(final Condition1<T> condition, final T first, final T second, final T third, final T fourth)
    {
      final C selectedCollection = newCollection();
      if (condition.isMet(first))
        selectedCollection.add(first);
      if (condition.isMet(second))
        selectedCollection.add(second);
      if (condition.isMet(third))
        selectedCollection.add(third);
      if (condition.isMet(fourth))
        selectedCollection.add(fourth);
      return selectedCollection;
    }

    @Override
    public C select(final Condition1<T> condition, final T first, final T second, final T third, final T fourth,
        final T fifth)
    {
      final C selectedCollection = newCollection();
      if (condition.isMet(first))
        selectedCollection.add(first);
      if (condition.isMet(second))
        selectedCollection.add(second);
      if (condition.isMet(third))
        selectedCollection.add(third);
      if (condition.isMet(fourth))
        selectedCollection.add(fourth);
      if (condition.isMet(fifth))
        selectedCollection.add(fifth);
      return selectedCollection;
    }

    @Override
    public C select(final Condition1<T> condition, final T first, final T second, final T third, final T fourth,
        final T fifth, final T... rest)
    {
      final C selectedCollection = newCollection();
      if (condition.isMet(first))
        selectedCollection.add(first);
      if (condition.isMet(second))
        selectedCollection.add(second);
      if (condition.isMet(third))
        selectedCollection.add(third);
      if (condition.isMet(fourth))
        selectedCollection.add(fourth);
      if (condition.isMet(fifth))
        selectedCollection.add(fifth);
      select0(selectedCollection, condition, rest);
      return selectedCollection;
    }

    private void select0(final C selectedCollection, final Condition1<T> condition, final T[] numbers)
    {
      for (final T value : numbers)
      {
        if (condition.isMet(value))
          selectedCollection.add(value);
      }
    }

    @Override
    public C select(final T[] source, final Condition1<T> condition)
    {
      final C selectedCollection = newCollection();
      select0(selectedCollection, condition, source);
      return selectedCollection;
    }
  }

  private static final class NumbersToArrayListSelector<T extends Number> extends NumbersSelector<T, ArrayList<T>>
  {
    @Override
    protected ArrayList<T> newCollection()
    {
      return new ArrayList<T>();
    }
  }

  private static final class NumbersToHashSetSelector<T extends Number> extends NumbersSelector<T, HashSet<T>>
  {
    @Override
    protected HashSet<T> newCollection()
    {
      return new HashSet<T>();
    }
  }

  private static final class NumbersToLinkedHashSetSelector<T extends Number> extends
      NumbersSelector<T, LinkedHashSet<T>>
  {
    @Override
    protected LinkedHashSet<T> newCollection()
    {
      return new LinkedHashSet<T>();
    }
  }

  public static <T extends Number> NumbersSelector<T, ArrayList<T>> numbersToArrayListSelector()
  {
    return new NumbersToArrayListSelector<T>();
  }

  private static final NumbersSelector<Byte, ArrayList<Byte>> BYTES_TO_ARRAY_LIST_SELECTOR =
    numbersToArrayListSelector();
  private static final NumbersSelector<Short, ArrayList<Short>> SHORTS_TO_ARRAY_LIST_SELECTOR =
    numbersToArrayListSelector();
  private static final NumbersSelector<Integer, ArrayList<Integer>> INTEGERS_TO_ARRAY_LIST_SELECTOR =
    numbersToArrayListSelector();
  private static final NumbersSelector<Long, ArrayList<Long>> LONGS_TO_ARRAY_LIST_SELECTOR =
    numbersToArrayListSelector();
  private static final NumbersSelector<Float, ArrayList<Float>> FLOATS_TO_ARRAY_LIST_SELECTOR =
    numbersToArrayListSelector();
  private static final NumbersSelector<Double, ArrayList<Double>> DOUBLES_TO_ARRAY_LIST_SELECTOR =
    numbersToArrayListSelector();
  private static final NumbersSelector<BigInteger, ArrayList<BigInteger>> BIG_INTEGERS_TO_ARRAY_LIST_SELECTOR =
    numbersToArrayListSelector();
  private static final NumbersSelector<BigDecimal, ArrayList<BigDecimal>> BIG_DECIMALS_TO_ARRAY_LIST_SELECTOR =
    numbersToArrayListSelector();

  public static NumbersSelector<Byte, ArrayList<Byte>> bytesToArrayListSelector()
  {
    return BYTES_TO_ARRAY_LIST_SELECTOR;
  }

  public static NumbersSelector<Short, ArrayList<Short>> shortsToArrayListSelector()
  {
    return SHORTS_TO_ARRAY_LIST_SELECTOR;
  }

  public static NumbersSelector<Integer, ArrayList<Integer>> integersToArrayListSelector()
  {
    return INTEGERS_TO_ARRAY_LIST_SELECTOR;
  }

  public static NumbersSelector<Long, ArrayList<Long>> longsToArrayListSelector()
  {
    return LONGS_TO_ARRAY_LIST_SELECTOR;
  }

  public static NumbersSelector<Float, ArrayList<Float>> floatsToArrayListSelector()
  {
    return FLOATS_TO_ARRAY_LIST_SELECTOR;
  }

  public static NumbersSelector<Double, ArrayList<Double>> doublesToArrayListSelector()
  {
    return DOUBLES_TO_ARRAY_LIST_SELECTOR;
  }

  public static NumbersSelector<BigInteger, ArrayList<BigInteger>> bigIntegersToArrayListSelector()
  {
    return BIG_INTEGERS_TO_ARRAY_LIST_SELECTOR;
  }

  public static NumbersSelector<BigDecimal, ArrayList<BigDecimal>> bigDecimalsToArrayListSelector()
  {
    return BIG_DECIMALS_TO_ARRAY_LIST_SELECTOR;
  }

  public static <T extends Number> NumbersSelector<T, HashSet<T>> numbersToHashSetSelector()
  {
    return new NumbersToHashSetSelector<T>();
  }

  private static final NumbersSelector<Byte, HashSet<Byte>> BYTES_TO_HASH_SET_SELECTOR = numbersToHashSetSelector();
  private static final NumbersSelector<Short, HashSet<Short>> SHORTS_TO_HASH_SET_SELECTOR = numbersToHashSetSelector();
  private static final NumbersSelector<Integer, HashSet<Integer>> INTEGERS_TO_HASH_SET_SELECTOR =
    numbersToHashSetSelector();
  private static final NumbersSelector<Long, HashSet<Long>> LONGS_TO_HASH_SET_SELECTOR = numbersToHashSetSelector();
  private static final NumbersSelector<Float, HashSet<Float>> FLOATS_TO_HASH_SET_SELECTOR = numbersToHashSetSelector();
  private static final NumbersSelector<Double, HashSet<Double>> DOUBLES_TO_HASH_SET_SELECTOR =
    numbersToHashSetSelector();
  private static final NumbersSelector<BigInteger, HashSet<BigInteger>> BIG_INTEGERS_TO_HASH_SET_SELECTOR =
    numbersToHashSetSelector();
  private static final NumbersSelector<BigDecimal, HashSet<BigDecimal>> BIG_DECIMALS_TO_HASH_SET_SELECTOR =
    numbersToHashSetSelector();

  public static NumbersSelector<Byte, HashSet<Byte>> bytesToHashSetSelector()
  {
    return BYTES_TO_HASH_SET_SELECTOR;
  }

  public static NumbersSelector<Short, HashSet<Short>> shortsToHashSetSelector()
  {
    return SHORTS_TO_HASH_SET_SELECTOR;
  }

  public static NumbersSelector<Integer, HashSet<Integer>> integersToHashSetSelector()
  {
    return INTEGERS_TO_HASH_SET_SELECTOR;
  }

  public static NumbersSelector<Long, HashSet<Long>> longsToHashSetSelector()
  {
    return LONGS_TO_HASH_SET_SELECTOR;
  }

  public static NumbersSelector<Float, HashSet<Float>> floatsToHashSetSelector()
  {
    return FLOATS_TO_HASH_SET_SELECTOR;
  }

  public static NumbersSelector<Double, HashSet<Double>> doublesToHashSetSelector()
  {
    return DOUBLES_TO_HASH_SET_SELECTOR;
  }

  public static NumbersSelector<BigInteger, HashSet<BigInteger>> bigIntegersToHashSetSelector()
  {
    return BIG_INTEGERS_TO_HASH_SET_SELECTOR;
  }

  public static NumbersSelector<BigDecimal, HashSet<BigDecimal>> bigDecimalsToHashSetSelector()
  {
    return BIG_DECIMALS_TO_HASH_SET_SELECTOR;
  }

  public static <T extends Number> NumbersSelector<T, LinkedHashSet<T>> numbersToLinkedHashSetSelector()
  {
    return new NumbersToLinkedHashSetSelector<T>();
  }

  private static final NumbersSelector<Byte, LinkedHashSet<Byte>> BYTES_TO_LINKED_HASH_SET_SELECTOR =
    numbersToLinkedHashSetSelector();
  private static final NumbersSelector<Short, LinkedHashSet<Short>> SHORTS_TO_LINKED_HASH_SET_SELECTOR =
    numbersToLinkedHashSetSelector();
  private static final NumbersSelector<Integer, LinkedHashSet<Integer>> INTEGERS_TO_LINKED_HASH_SET_SELECTOR =
    numbersToLinkedHashSetSelector();
  private static final NumbersSelector<Long, LinkedHashSet<Long>> LONGS_TO_LINKED_HASH_SET_SELECTOR =
    numbersToLinkedHashSetSelector();
  private static final NumbersSelector<Float, LinkedHashSet<Float>> FLOATS_TO_LINKED_HASH_SET_SELECTOR =
    numbersToLinkedHashSetSelector();
  private static final NumbersSelector<Double, LinkedHashSet<Double>> DOUBLES_TO_LINKED_HASH_SET_SELECTOR =
    numbersToLinkedHashSetSelector();
  private static final NumbersSelector<BigInteger, LinkedHashSet<BigInteger>> BIG_INTEGERS_TO_LINKED_HASH_SET_SELECTOR =
    numbersToLinkedHashSetSelector();
  private static final NumbersSelector<BigDecimal, LinkedHashSet<BigDecimal>> BIG_DECIMALS_TO_LINKED_HASH_SET_SELECTOR =
    numbersToLinkedHashSetSelector();

  public static NumbersSelector<Byte, LinkedHashSet<Byte>> bytesToLinkedHashSetSelector()
  {
    return BYTES_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static NumbersSelector<Short, LinkedHashSet<Short>> shortsToLinkedHashSetSelector()
  {
    return SHORTS_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static NumbersSelector<Integer, LinkedHashSet<Integer>> integersToLinkedHashSetSelector()
  {
    return INTEGERS_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static NumbersSelector<Long, LinkedHashSet<Long>> longsToLinkedHashSetSelector()
  {
    return LONGS_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static NumbersSelector<Float, LinkedHashSet<Float>> floatsToLinkedHashSetSelector()
  {
    return FLOATS_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static NumbersSelector<Double, LinkedHashSet<Double>> doublesToLinkedHashSetSelector()
  {
    return DOUBLES_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static NumbersSelector<BigInteger, LinkedHashSet<BigInteger>> bigIntegersToLinkedHashSetSelector()
  {
    return BIG_INTEGERS_TO_LINKED_HASH_SET_SELECTOR;
  }

  public static NumbersSelector<BigDecimal, LinkedHashSet<BigDecimal>> bigDecimalsToLinkedHashSetSelector()
  {
    return BIG_DECIMALS_TO_LINKED_HASH_SET_SELECTOR;
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
