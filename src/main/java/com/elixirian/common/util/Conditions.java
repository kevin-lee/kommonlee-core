/**
 * 
 */
package com.elixirian.common.util;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-10-31)
 */
public final class Conditions
{
	private Conditions()
	{
		throw new IllegalStateException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
	}

	public static boolean and(final boolean condition1, final boolean condition2)
	{
		return condition1 && condition2;
	}

	public static boolean and(final boolean condition1, final boolean condition2, final boolean condition3)
	{
		return condition1 && condition2 && condition3;
	}

	public static boolean and(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4)
	{
		return condition1 && condition2 && condition3 && condition4;
	}

	public static boolean and(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4, final boolean condition5)
	{
		return condition1 && condition2 && condition3 && condition4 && condition5;
	}

	public static boolean and(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4, final boolean condition5, final boolean... conditions)
	{
		if (and(condition1, condition2, condition3, condition4, condition5))
		{
			for (final boolean condition : conditions)
			{
				if (!condition)
					return false;
			}
			return true;
		}
		return false;
	}

	public static boolean or(final boolean condition1, final boolean condition2)
	{
		return condition1 || condition2;
	}

	public static boolean or(final boolean condition1, final boolean condition2, final boolean condition3)
	{
		return condition1 || condition2 || condition3;
	}

	public static boolean or(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4)
	{
		return condition1 || condition2 || condition3 || condition4;
	}

	public static boolean or(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4, final boolean condition5)
	{
		return condition1 || condition2 || condition3 || condition4 || condition5;
	}

	public static boolean or(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4, final boolean condition5, final boolean... conditions)
	{
		if (or(condition1, condition2, condition3, condition4, condition5))
			return true;

		for (final boolean condition : conditions)
		{
			if (condition)
				return true;
		}
		return false;
	}

	/* NOR */
	public static boolean nor(final boolean condition1, final boolean condition2)
	{
		return !or(condition1, condition2);
	}

	public static boolean nor(final boolean condition1, final boolean condition2, final boolean condition3)
	{
		return !or(condition1, condition2, condition3);
	}

	public static boolean nor(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4)
	{
		return !or(condition1, condition2, condition3, condition4);
	}

	public static boolean nor(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4, final boolean condition5)
	{
		return !or(condition1, condition2, condition3, condition4, condition5);
	}

	public static boolean nor(final boolean condition1, final boolean condition2, final boolean condition3,
			final boolean condition4, final boolean condition5, final boolean... conditions)
	{
		return !or(condition1, condition2, condition3, condition4, condition5, conditions);
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

}
