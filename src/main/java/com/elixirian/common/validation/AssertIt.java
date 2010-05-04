/**
 * 
 */
package com.elixirian.common.validation;

import static com.elixirian.common.util.MessageFormatter.*;

import com.elixirian.common.util.CommonConstants;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2009-11-28)
 */
public final class AssertIt
{
	private AssertIt()
	{
		throw new IllegalStateException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
	}

	public static <T> T isNotNull(final T object, final String message, final Object... args)
	{
		if (null == object)
		{
			throw new IllegalArgumentException(format(message, args));
		}
		return object;
	}

	public static <T> T isNull(final T object, final String message, final Object... args)
	{
		if (null != object)
		{
			throw new IllegalArgumentException(format(message, args));
		}
		return object;
	}

	public static String isNotEmpty(final String value, final String message, final Object... args)
	{
		if (null == value || 0 == value.length())
		{
			throw new IllegalArgumentException(format(message, args));
		}
		return value;
	}

	public static String isEmpty(final String value, final String message, final Object... args)
	{
		if (null != value && 0 != value.length())
		{
			throw new IllegalArgumentException(format(message, args));
		}
		return value;
	}

	public static void isTrue(final boolean expresion, final String message, final Object... args)
	{
		if (!expresion)
		{
			throw new IllegalArgumentException(format(message, args));
		}
	}

	public static void isFalse(final boolean expresion, final String message, final Object... args)
	{
		if (expresion)
		{
			throw new IllegalArgumentException(format(message, args));
		}
	}
}
