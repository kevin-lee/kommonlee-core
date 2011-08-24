/**
 * 
 */
package org.elixirian.common.util.number;

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
public final class BytesTotal
{
  private BytesTotal()
  {
  }

  public static int sum(final byte number1, final byte number2)
  {
    return number1 + number2;
  }

  public static int sum(final byte number1, final byte number2, final byte number3)
  {
    return number1 + number2 + number3;
  }

  public static int sum(final byte number1, final byte number2, final byte number3, final byte number4)
  {
    return number1 + number2 + number3 + number4;
  }

  public static int sum(final byte number1, final byte number2, final byte number3, final byte number4,
      final byte number5)
  {
    return number1 + number2 + number3 + number4 + number5;
  }

  public static int sum(final byte number1, final byte number2, final byte number3, final byte number4,
      final byte number5, final byte... rest)
  {
    int total = sum(number1, number2, number3, number4, number5);
    for (final byte each : rest)
    {
      total += each;
    }
    return total;
  }

  public static int sum(final byte[] numbers)
  {
    int total = 0;
    for (final byte each : numbers)
    {
      total += each;
    }
    return total;
  }

  public static int total(final Byte number1, final Byte number2)
  {
    return number1.byteValue() + number2.byteValue();
  }

  public static int total(final Byte number1, final Byte number2, final Byte number3)
  {
    return number1.byteValue() + number2.byteValue() + number3.byteValue();
  }

  public static int total(final Byte number1, final Byte number2, final Byte number3, final Byte number4)
  {
    return number1.byteValue() + number2.byteValue() + number3.byteValue() + number4.byteValue();
  }

  public static int total(final Byte number1, final Byte number2, final Byte number3, final Byte number4,
      final Byte number5)
  {
    return number1.byteValue() + number2.byteValue() + number3.byteValue() + number4.byteValue() + number5.byteValue();
  }

  public static int total(final Byte number1, final Byte number2, final Byte number3, final Byte number4,
      final Byte number5, final Byte... rest)
  {
    int total = total(number1, number2, number3, number4, number5);
    for (final Byte each : rest)
    {
      total += each.byteValue();
    }
    return total;
  }

  public static int total(final Byte[] numbers)
  {
    int total = 0;
    for (final Byte each : numbers)
    {
      total += each.byteValue();
    }
    return total;
  }

  public static int total(final Iterable<Byte> numbers)
  {
    int total = 0;
    for (final byte each : numbers)
    {
      total += each;
    }
    return total;
  }
}