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

import org.elixirian.kommonlee.type.functional.Function1;
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
public final class ShortTotal
{
  private ShortTotal() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  public static int sum(final short number1, final short number2)
  {
    return number1 + number2;
  }

  public static int sum(final short number1, final short number2, final short number3)
  {
    return number1 + number2 + number3;
  }

  public static int sum(final short number1, final short number2, final short number3, final short number4)
  {
    return number1 + number2 + number3 + number4;
  }

  public static int sum(final short number1, final short number2, final short number3, final short number4,
      final short number5)
  {
    return number1 + number2 + number3 + number4 + number5;
  }

  public static int sum(final short number1, final short number2, final short number3, final short number4,
      final short number5, final short... rest)
  {
    int total = sum(number1, number2, number3, number4, number5);
    for (final short each : rest)
    {
      total += each;
    }
    return total;
  }

  public static int sum(final short[] numbers)
  {
    int total = 0;
    for (final short each : numbers)
    {
      total += each;
    }
    return total;
  }

  public static int total(final Short number1, final Short number2)
  {
    return number1.shortValue() + number2.shortValue();
  }

  public static int total(final Short number1, final Short number2, final Short number3)
  {
    return number1.shortValue() + number2.shortValue() + number3.shortValue();
  }

  public static int total(final Short number1, final Short number2, final Short number3, final Short number4)
  {
    return number1.shortValue() + number2.shortValue() + number3.shortValue() + number4.shortValue();
  }

  public static int total(final Short number1, final Short number2, final Short number3, final Short number4,
      final Short number5)
  {
    return number1.shortValue() + number2.shortValue() + number3.shortValue() + number4.shortValue()
        + number5.shortValue();
  }

  public static int total(final Short number1, final Short number2, final Short number3, final Short number4,
      final Short number5, final Short... rest)
  {
    int total = total(number1, number2, number3, number4, number5);
    for (final Short each : rest)
    {
      total += each.shortValue();
    }
    return total;
  }

  public static int total(final Short[] numbers)
  {
    int total = 0;
    for (final Short each : numbers)
    {
      total += each.shortValue();
    }
    return total;
  }

  public static <T> int total(final T[] numberSource, final Function1<T, Short> shortMapper)
  {
    int total = 0;
    for (final T each : numberSource)
    {
      @SuppressWarnings("boxing")
      final short s = shortMapper.apply(each);
      total += s;
    }
    return total;
  }

  public static int total(final Iterable<Short> numbers)
  {
    int total = 0;
    for (final Short each : numbers)
    {
      total += each.shortValue();
    }
    return total;
  }

  public static <T> int total(final Iterable<T> numberSource, final Function1<T, Short> shortMapper)
  {
    int total = 0;
    for (final T each : numberSource)
    {
      @SuppressWarnings("boxing")
      final short s = shortMapper.apply(each);
      total += s;
    }
    return total;
  }
}