/**
 * 
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
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-14)
 */
public final class ShortsTotal
{
  private ShortsTotal()
  {
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

  public static int total(final Iterable<Short> numbers)
  {
    int total = 0;
    for (final Short each : numbers)
    {
      total += each.shortValue();
    }
    return total;
  }

}