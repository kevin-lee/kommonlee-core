/**
 * 
 */
package org.elixirian.kommonlee.util.number;

/**
 * <pre>
 *     ____________    ___________  ____   _______ _________ _______ _______________  ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /     \/   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /          /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
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