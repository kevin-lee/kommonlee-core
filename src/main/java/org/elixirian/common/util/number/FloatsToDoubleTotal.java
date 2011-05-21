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
public final class FloatsToDoubleTotal
{
	private FloatsToDoubleTotal()
	{
	}

	public static double total(final float number1, final float number2)
	{
		return (double) number1 + number2;
	}

	public static double total(final float number1, final float number2, final float number3)
	{
		return (double) number1 + number2 + number3;
	}

	public static double total(final float number1, final float number2, final float number3, final float number4)
	{
		return (double) number1 + number2 + number3 + number4;
	}

	public static double total(final float number1, final float number2, final float number3, final float number4,
			final float number5)
	{
		return (double) number1 + number2 + number3 + number4 + number5;
	}

	public static double total(final float number1, final float number2, final float number3, final float number4,
			final float number5, final float... rest)
	{
		return total(number1, number2, number3, number4, number5) + total(rest);
	}

	public static double total(final float[] numbers)
	{
		double total = 0;
		for (final float each : numbers)
		{
			total += each;
		}
		return total;
	}

	public static double total(final Float number1, final Float number2, final Float number3, final Float number4,
			final Float number5, final Float... rest)
	{
		return total(number1.floatValue(), number2.floatValue(), number3.floatValue(), number4.floatValue(),
				number5.floatValue())
				+ total(rest);
	}

	public static double total(final Float[] numbers)
	{
		double total = 0;
		for (final Float each : numbers)
		{
			total += each.floatValue();
		}
		return total;
	}

	public static double total(final Iterable<Float> numbers)
	{
		double total = 0;
		for (final Float each : numbers)
		{
			total += each.floatValue();
		}
		return total;
	}
}