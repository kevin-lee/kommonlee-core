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
public final class BigDecimalTotal
{
  private BigDecimalTotal() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  public static BigDecimal total(final BigDecimal number1, final BigDecimal number2)
  {
    return number1.add(number2);
  }

  public static BigDecimal total(final BigDecimal number1, final BigDecimal number2, final BigDecimal number3)
  {
    return number1.add(number2)
        .add(number3);
  }

  public static BigDecimal total(final BigDecimal number1, final BigDecimal number2, final BigDecimal number3,
      final BigDecimal number4)
  {
    return number1.add(number2)
        .add(number3)
        .add(number4);
  }

  public static BigDecimal total(final BigDecimal number1, final BigDecimal number2, final BigDecimal number3,
      final BigDecimal number4, final BigDecimal number5)
  {
    return number1.add(number2)
        .add(number3)
        .add(number4)
        .add(number5);
  }

  public static BigDecimal total(final BigDecimal number1, final BigDecimal number2, final BigDecimal number3,
      final BigDecimal number4, final BigDecimal number5, final BigDecimal... rest)
  {
    BigDecimal total = total(number1, number2, number3, number4, number5);
    for (final BigDecimal each : rest)
    {
      total = total.add(each);
    }
    return total;
  }

  public static BigDecimal total(final BigDecimal[] numbers)
  {
    BigDecimal total = BigDecimal.ZERO;
    for (final BigDecimal each : numbers)
    {
      total = total.add(each);
    }
    return total;
  }

  public static <T> BigDecimal total(final T[] numberSource, final Function1<T, BigDecimal> bigDecimalMapper)
  {
    BigDecimal total = BigDecimal.ZERO;
    for (final T each : numberSource)
    {
      final BigDecimal bigDecimal = bigDecimalMapper.apply(each);
      total = total.add(bigDecimal);
    }
    return total;
  }

  public static BigDecimal total(final Iterable<BigDecimal> numbers)
  {
    BigDecimal total = BigDecimal.ZERO;
    for (final BigDecimal each : numbers)
    {
      total = total.add(each);
    }
    return total;
  }

  public static <T> BigDecimal total(final Iterable<T> numberSource, final Function1<T, BigDecimal> bigDecimalMapper)
  {
    BigDecimal total = BigDecimal.ZERO;
    for (final T each : numberSource)
    {
      final BigDecimal bigDecimal = bigDecimalMapper.apply(each);
      total = total.add(bigDecimal);
    }
    return total;
  }
}