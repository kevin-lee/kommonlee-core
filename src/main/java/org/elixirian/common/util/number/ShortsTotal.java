/**
 * 
 */
package org.elixirian.common.util.number;

/**
 * <pre>
 *     ____________    ___________  ____   _______ _________ _______ _____________    ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /   \  /   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /     \/   /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____  __________  ___________ ___    ____
 *    /   \/    / /      \   \/   /_    _//   \  /   /
 *   /        /  /    ___/\      / /   / /     \/   /
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

	public static int total(final short number1, final short number2)
	{
		return number1 + number2;
	}

	public static int total(final short number1, final short number2, final short number3)
	{
		return number1 + number2 + number3;
	}

	public static int total(final short number1, final short number2, final short number3, final short number4)
	{
		return number1 + number2 + number3 + number4;
	}

	public static int total(final short number1, final short number2, final short number3, final short number4,
			final short number5)
	{
		return number1 + number2 + number3 + number4 + number5;
	}

	public static int total(final short number1, final short number2, final short number3, final short number4,
			final short number5, final short... rest)
	{
		return total(number1, number2, number3, number4, number5) + total(rest);
	}

	public static int total(final short[] numbers)
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
		return total(number1.shortValue(), number2.shortValue(), number3.shortValue(), number4.shortValue(),
				number5.shortValue())
				+ total(rest);
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