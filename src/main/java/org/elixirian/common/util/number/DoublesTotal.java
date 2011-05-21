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
public final class DoublesTotal
{
	private DoublesTotal()
	{
	}

	public static double total(final double number1, final double number2)
	{
		return number1 + number2;
	}

	public static double total(final double number1, final double number2, final double number3)
	{
		return number1 + number2 + number3;
	}

	public static double total(final double number1, final double number2, final double number3, final double number4)
	{
		return number1 + number2 + number3 + number4;
	}

	public static double total(final double number1, final double number2, final double number3, final double number4,
			final double number5)
	{
		return number1 + number2 + number3 + number4 + number5;
	}

	public static double total(final double number1, final double number2, final double number3, final double number4,
			final double number5, final double... rest)
	{
		return total(number1, number2, number3, number4, number5) + total(rest);
	}

	public static double total(final double[] numbers)
	{
		double total = 0;
		for (final double each : numbers)
		{
			total += each;
		}
		return total;
	}

	public static double total(final Double number1, final Double number2, final Double number3, final Double number4,
			final Double number5, final Double... rest)
	{
		return total(number1.doubleValue(), number2.doubleValue(), number3.doubleValue(), number4.doubleValue(),
				number5.doubleValue()) + total(rest);
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