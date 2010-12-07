/**
 * 
 */
package org.elixirian.common.util;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-10-31)
 * @version 0.0.2 (2010-11-15) {@link IllegalStateException} is removed from the constructor.
 * @version 0.0.3 (2010-11-22) Its name is changed from Conditions to LogicalOperations.
 * @version 0.0.4 (2010-12-04) Its name is changed from LogicalOperations to {@link Conditional}.
 */
public final class Conditional
{
	private Conditional()
	{
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

	public static char ternary(final boolean condition, final char trueThen, final char otherwise)
	{
		return condition ? trueThen : otherwise;
	}

	public static byte ternary(final boolean condition, final byte trueThen, final byte otherwise)
	{
		return condition ? trueThen : otherwise;
	}

	public static short ternary(final boolean condition, final short trueThen, final short otherwise)
	{
		return condition ? trueThen : otherwise;
	}

	public static int ternary(final boolean condition, final int trueThen, final int otherwise)
	{
		return condition ? trueThen : otherwise;
	}

	public static long ternary(final boolean condition, final long trueThen, final long otherwise)
	{
		return condition ? trueThen : otherwise;
	}

	public static float ternary(final boolean condition, final float trueThen, final float otherwise)
	{
		return condition ? trueThen : otherwise;
	}

	public static double ternary(final boolean condition, final double trueThen, final double otherwise)
	{
		return condition ? trueThen : otherwise;
	}

	public static <T> T ternary(final boolean condition, final T trueThen, final T otherwise)
	{
		return condition ? trueThen : otherwise;
	}
}
