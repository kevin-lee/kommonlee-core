/**
 * 
 */
package org.elixirian.kommonlee.util;

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
 * @version 0.0.1 (2010-12-04)
 */
public final class Logical
{
	private Logical()
	{
	}

	public static boolean and(final boolean condition1, final boolean condition2)
	{
		return condition1 & condition2;
	}

	public static boolean and(final boolean condition1, final boolean condition2, final boolean condition3)
	{
		return condition1 & condition2 & condition3;
	}

	public static boolean and(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4)
	{
		return condition1 & condition2 & condition3 & condition4;
	}

	public static boolean and(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4, final boolean condition5)
	{
		return condition1 & condition2 & condition3 & condition4 & condition5;
	}

	public static boolean and(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4, final boolean condition5, final boolean... conditions)
	{
		if (and(condition1, condition2, condition3, condition4, condition5))
		{
			int count = 0;
			for (final boolean condition : conditions)
			{
				if (condition)
					count++;
			}
			return conditions.length == count;
		}
		return false;
	}

	public static boolean or(final boolean condition1, final boolean condition2)
	{
		return condition1 | condition2;
	}

	public static boolean or(final boolean condition1, final boolean condition2, final boolean condition3)
	{
		return condition1 | condition2 | condition3;
	}

	public static boolean or(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4)
	{
		return condition1 | condition2 | condition3 | condition4;
	}

	public static boolean or(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4, final boolean condition5)
	{
		return condition1 | condition2 | condition3 | condition4 | condition5;
	}

	public static boolean or(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4, final boolean condition5, final boolean... conditions)
	{
		boolean isTrue = or(condition1, condition2, condition3, condition4, condition5);
		for (final boolean condition : conditions)
		{
			if (condition)
				isTrue = true;
		}
		return isTrue;
	}

	/* XOR */
	public static boolean xor(final boolean condition1, final boolean condition2)
	{
		return condition1 ^ condition2;
	}

	public static boolean xor(final boolean condition1, final boolean condition2, final boolean condition3)
	{
		return condition1 ^ condition2 ^ condition3;
	}

	public static boolean xor(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4)
	{
		return condition1 ^ condition2 ^ condition3 ^ condition4;
	}

	public static boolean xor(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4, final boolean condition5)
	{
		return condition1 ^ condition2 ^ condition3 ^ condition4 ^ condition5;
	}

	public static boolean xor(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4, final boolean condition5, final boolean... conditions)
	{
		final boolean result = xor(condition1, condition2, condition3, condition4, condition5);

		int count = 0;
		for (final boolean condition : conditions)
		{
			if (condition)
				count++;
		}
		/* @formatter:off */
		return 0 == count ? 
					result : 
					(count & 1) != 0 ? 
						!result : 
						result;
		/* @formatter:on */
	}

	/* NOR */
	public static boolean nor(final boolean condition1, final boolean condition2)
	{
		return !Conditional.or(condition1, condition2);
	}

	public static boolean nor(final boolean condition1, final boolean condition2, final boolean condition3)
	{
		return !Conditional.or(condition1, condition2, condition3);
	}

	public static boolean nor(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4)
	{
		return !Conditional.or(condition1, condition2, condition3, condition4);
	}

	public static boolean nor(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4, final boolean condition5)
	{
		return !Conditional.or(condition1, condition2, condition3, condition4, condition5);
	}

	public static boolean nor(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4, final boolean condition5, final boolean... conditions)
	{
		return !Conditional.or(condition1, condition2, condition3, condition4, condition5, conditions);
	}
}
