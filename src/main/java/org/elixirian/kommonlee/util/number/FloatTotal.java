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
public final class FloatTotal
{
  private FloatTotal() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  public static float sum(final float number1, final float number2)
  {
    return number1 + number2;
  }

  public static float sum(final float number1, final float number2, final float number3)
  {
    return number1 + number2 + number3;
  }

  public static float sum(final float number1, final float number2, final float number3, final float number4)
  {
    return number1 + number2 + number3 + number4;
  }

  public static float sum(final float number1, final float number2, final float number3, final float number4,
      final float number5)
  {
    return number1 + number2 + number3 + number4 + number5;
  }

  public static float sum(final float number1, final float number2, final float number3, final float number4,
      final float number5, final float... rest)
  {
    float total = sum(number1, number2, number3, number4, number5);
    for (final float each : rest)
    {
      total += each;
    }
    return total;
  }

  public static float sum(final float[] numbers)
  {
    float total = 0;
    for (final float each : numbers)
    {
      total += each;
    }
    return total;
  }

  public static float total(final Float number1, final Float number2)
  {
    return number1.floatValue() + number2.floatValue();
  }

  public static float total(final Float number1, final Float number2, final Float number3)
  {
    return number1.floatValue() + number2.floatValue() + number3.floatValue();
  }

  public static float total(final Float number1, final Float number2, final Float number3, final Float number4)
  {
    return number1.floatValue() + number2.floatValue() + number3.floatValue() + number4.floatValue();
  }

  public static float total(final Float number1, final Float number2, final Float number3, final Float number4,
      final Float number5)
  {
    return number1.floatValue() + number2.floatValue() + number3.floatValue() + number4.floatValue()
        + number5.floatValue();
  }

  public static float total(final Float number1, final Float number2, final Float number3, final Float number4,
      final Float number5, final Float... rest)
  {
    float total = total(number1, number2, number3, number4, number5);
    for (final Float each : rest)
    {
      total += each.floatValue();
    }
    return total;
  }

  public static float total(final Float[] numbers)
  {
    float total = 0;
    for (final Float each : numbers)
    {
      total += each.floatValue();
    }
    return total;
  }

  public static <T> float total(final T[] numberSource, final Function1<T, Float> floatMapper)
  {
    float total = 0;
    for (final T each : numberSource)
    {
      @SuppressWarnings("boxing")
      final float f = floatMapper.apply(each);
      total += f;
    }
    return total;
  }

  public static float total(final Iterable<Float> numbers)
  {
    float total = 0;
    for (final Float each : numbers)
    {
      total += each.floatValue();
    }
    return total;
  }

  public static <T> float total(final Iterable<T> numberSource, final Function1<T, Float> floatMapper)
  {
    float total = 0;
    for (final T each : numberSource)
    {
      @SuppressWarnings("boxing")
      final float f = floatMapper.apply(each);
      total += f;
    }
    return total;
  }
}