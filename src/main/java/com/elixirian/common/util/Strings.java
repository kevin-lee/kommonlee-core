package com.elixirian.common.util;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-02-23)
 * @version 0.0.2 (2010-03-16) bug fixed: It does not remove the extra "%s"s nor does it escape the rest of the "%%s"s after all the
 *          arguments replace the "%s"s but there are still "%s"s or "%%s"s left =fixed=> It removes all the extra "%s"s and escapes the
 *          rest of the "%%s"s.
 * @version 0.0.3 (2010-04-01) null check and empty array check are removed for better performance (removing optimisation has led to even
 *          more optimised code).
 * @version 0.0.4 (2010-04-06) {@link #nullSafeTrim(String)} is added.
 * @version 0.0.5 (2010-04-20) refactoring: more optimisation.
 * @version 0.0.6 (2010-04-29) formatMessage ({@link #format(String, Object...)}) is taken from {@code
 *          com.elixirian.common.string.MessageFormatter}.
 * @version 0.0.7 (2010-05-04) the format() method is moved to the {@link MessageFormatter} class.
 */
public final class Strings
{
	private Strings()
	{
		throw new IllegalStateException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
	}

	/**
	 * Returns a copy of the given string, with leading and trailing whitespace omitted. If the given String contains <code>null</code>
	 * reference it returns an "" (empty String).
	 * 
	 * @param value
	 *            the given String value
	 * @return the result of value.trim(). If the value is null, return "" (empty String).
	 */
	public static String nullSafeTrim(String value)
	{
		return (null == value ? "" : value.trim());
	}

	public static boolean isEmpty(String value)
	{
		return (null == value || 0 == value.length());
	}

	public static boolean isNotEmpty(String value)
	{
		return (null != value && 0 < value.length());
	}

}