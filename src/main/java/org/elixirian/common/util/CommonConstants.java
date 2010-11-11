package org.elixirian.common.util;

/**
 * This is a utility class, which has common constants.
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-03-07)
 */
public final class CommonConstants
{
	/**
	 * " cannot be instantiated."
	 */
	public static final String CANNOT_BE_INSTANTIATED = " cannot be instantiated.";

	private CommonConstants()
	{
		throw new IllegalStateException(getClass().getName() + CANNOT_BE_INSTANTIATED);
	}

}