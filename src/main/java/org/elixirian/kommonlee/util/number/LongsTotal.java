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
public final class LongsTotal
{
  private LongsTotal()
  {
  }

  public static long sum(final long number1, final long number2)
  {
    return number1 + number2;
  }

  public static long sum(final long number1, final long number2, final long number3)
  {
    return number1 + number2 + number3;
  }

  public static long sum(final long number1, final long number2, final long number3, final long number4)
  {
    return number1 + number2 + number3 + number4;
  }

  public static long sum(final long number1, final long number2, final long number3, final long number4,
      final long number5)
  {
    return number1 + number2 + number3 + number4 + number5;
  }

  public static long sum(final long number1, final long number2, final long number3, final long number4,
      final long number5, final long... rest)
  {
    long total = sum(number1, number2, number3, number4, number5);
    for (final long each : rest)
    {
      total += each;
    }
    return total;
  }

  public static long sum(final long[] numbers)
  {
    long total = 0;
    for (final long each : numbers)
    {
      total += each;
    }
    return total;
  }

  public static long total(final Long number1, final Long number2)
  {
    return number1.longValue() + number2.longValue();
  }

  public static long total(final Long number1, final Long number2, final Long number3)
  {
    return number1.longValue() + number2.longValue() + number3.longValue();
  }

  public static long total(final Long number1, final Long number2, final Long number3, final Long number4)
  {
    return number1.longValue() + number2.longValue() + number3.longValue() + number4.longValue();
  }

  public static long total(final Long number1, final Long number2, final Long number3, final Long number4,
      final Long number5)
  {
    return number1.longValue() + number2.longValue() + number3.longValue() + number4.longValue() + number5.longValue();
  }

  public static long total(final Long number1, final Long number2, final Long number3, final Long number4,
      final Long number5, final Long... rest)
  {
    long total = total(number1, number2, number3, number4, number5);
    for (final Long each : rest)
    {
      total += each.longValue();
    }
    return total;
  }

  public static long total(final Long[] numbers)
  {
    long total = 0;
    for (final Long each : numbers)
    {
      total += each.longValue();
    }
    return total;
  }

  public static long total(final Iterable<Long> numbers)
  {
    long total = 0;
    for (final Long each : numbers)
    {
      total += each.longValue();
    }
    return total;
  }
}