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
public final class DoublesTotal
{
  private DoublesTotal()
  {
  }

  public static double sum(final double number1, final double number2)
  {
    return number1 + number2;
  }

  public static double sum(final double number1, final double number2, final double number3)
  {
    return number1 + number2 + number3;
  }

  public static double sum(final double number1, final double number2, final double number3, final double number4)
  {
    return number1 + number2 + number3 + number4;
  }

  public static double sum(final double number1, final double number2, final double number3, final double number4,
      final double number5)
  {
    return number1 + number2 + number3 + number4 + number5;
  }

  public static double sum(final double number1, final double number2, final double number3, final double number4,
      final double number5, final double... rest)
  {
    double total = sum(number1, number2, number3, number4, number5);
    for (final double each : rest)
    {
      total += each;
    }
    return total;
  }

  public static double sum(final double[] numbers)
  {
    double total = 0;
    for (final double each : numbers)
    {
      total += each;
    }
    return total;
  }

  public static double total(final Double number1, final Double number2)
  {
    return number1.doubleValue() + number2.doubleValue();
  }

  public static double total(final Double number1, final Double number2, final Double number3)
  {
    return number1.doubleValue() + number2.doubleValue() + number3.doubleValue();
  }

  public static double total(final Double number1, final Double number2, final Double number3, final Double number4)
  {
    return number1.doubleValue() + number2.doubleValue() + number3.doubleValue() + number4.doubleValue();
  }

  public static double total(final Double number1, final Double number2, final Double number3, final Double number4,
      final Double number5)
  {
    return number1.doubleValue() + number2.doubleValue() + number3.doubleValue() + number4.doubleValue()
        + number5.doubleValue();
  }

  public static double total(final Double number1, final Double number2, final Double number3, final Double number4,
      final Double number5, final Double... rest)
  {
    double total = total(number1, number2, number3, number4, number5);
    for (final Double each : rest)
    {
      total += each.doubleValue();
    }
    return total;
  }

  public static double total(final Double[] numbers)
  {
    double total = 0;
    for (final Double each : numbers)
    {
      total += each.doubleValue();
    }
    return total;
  }

  public static double total(final Iterable<Double> numbers)
  {
    double total = 0;
    for (final Double each : numbers)
    {
      total += each.doubleValue();
    }
    return total;
  }
}