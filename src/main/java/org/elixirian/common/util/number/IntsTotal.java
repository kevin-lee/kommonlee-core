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
public final class IntsTotal
{
	private IntsTotal()
	{
	}

	public static int total(final int number1, final int number2)
	{
		return number1 + number2;
	}

	public static int total(final int number1, final int number2, final int number3)
	{
		return number1 + number2 + number3;
	}

	public static int total(final int number1, final int number2, final int number3, final int number4)
	{
		return number1 + number2 + number3 + number4;
	}

	public static int total(final int number1, final int number2, final int number3, final int number4, final int number5)
	{
		return number1 + number2 + number3 + number4 + number5;
	}

	public static int total(final int number1, final int number2, final int number3, final int number4,
			final int number5, final int... rest)
	{
		return total(number1, number2, number3, number4, number5) + total(rest);
	}

	public static int total(final int[] numbers)
	{
		int total = 0;
		for (final int each : numbers)
		{
			total += each;
		}
		return total;
	}

	public static int total(final Integer number1, final Integer number2)
	{
		return number1.intValue() + number2.intValue();
	}

	public static int total(final Integer number1, final Integer number2, final Integer number3)
	{
		return number1.intValue() + number2.intValue() + number3.intValue();
	}

	public static int total(final Integer number1, final Integer number2, final Integer number3, final Integer number4)
	{
		return number1.intValue() + number2.intValue() + number3.intValue() + number4.intValue();
	}

	public static int total(final Integer number1, final Integer number2, final Integer number3, final Integer number4,
			final Integer number5)
	{
		return number1.intValue() + number2.intValue() + number3.intValue() + number4.intValue() + number5.intValue();
	}

	public static int total(final Integer number1, final Integer number2, final Integer number3, final Integer number4,
			final Integer number5, final Integer... rest)
	{
		return total(number1, number2, number3, number4, number5) + total(rest);
	}

	public static int total(final Integer[] numbers)
	{
		int total = 0;
		for (final Integer each : numbers)
		{
			total += each.intValue();
		}
		return total;
	}

	public static int total(final Iterable<Integer> numbers)
	{
		int total = 0;
		for (final Integer each : numbers)
		{
			total += each.intValue();
		}
		return total;
	}
}