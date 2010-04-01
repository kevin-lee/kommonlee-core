/**
 * 
 */
package com.elixirian.common.validation;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2009-11-28)
 */
public final class ValidateIt
{
	private ValidateIt()
	{
		throw new IllegalStateException("ValidateIt class cannot be instantiated.");
	}

	public static boolean isEmpty(String value)
	{
		return (null == value || 0 == value.length());
	}

	public static boolean isNotEmpty(String value)
	{
		return (null != value && 0 != value.length());
	}
}
