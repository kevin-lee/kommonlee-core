/**
 * This project is licensed under the Apache License, Version 2.0
 * if the following condition is met:
 * (otherwise it cannot be used by anyone but the author, Kevin, only)
 *
 * The original KommonLee project is owned by Lee, Seong Hyun (Kevin).
 *
 * -What does it mean to you?
 * Nothing, unless you want to take the ownership of
 * "the original project" (not yours or forked & modified one).
 * You are free to use it for both non-commercial and commercial projects
 * and free to modify it as the Apache License allows.
 *
 * -So why is this condition necessary?
 * It is only to protect the original project (See the case of Java).
 *
 *
 * Copyright 2009 Lee, Seong Hyun (Kevin)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.elixirian.kommonlee.util;

import static org.elixirian.kommonlee.util.Objects.toStringOf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * @version 0.0.1 (2010-02-23)
 * @version 0.0.2 (2010-03-16) bug fixed: It does not remove the extra "%s"s nor does it escape the rest of the "%%s"s
 *          after all the arguments replace the "%s"s but there are still "%s"s or "%%s"s left =fixed=> It removes all
 *          the extra "%s"s and escapes the rest of the "%%s"s.
 * @version 0.0.3 (2010-04-01) null check and empty array check are removed for better performance (removing
 *          optimisation has led to even more optimised code).
 * @version 0.0.4 (2010-04-06) {@link #nullSafeTrim(String)} is added.
 * @version 0.0.5 (2010-04-20) refactoring: more optimisation.
 * @version 0.0.6 (2010-04-29) formatMessage ({@link #format(String, Object...)}) is taken from
 *          {@code com.elixirian.common.string.MessageFormatter}.
 * @version 0.0.7 (2010-05-04) the format() method is moved to the {@link MessageFormatter} class.
 * @version 0.0.8 (2010-06-03) {@link #repeat(String, int)} is added.
 * @version 0.0.9 (2010-10-31) {@link #nullThenEmpty(String)} and {@link #emptyThenNull(String)} are added.
 * @version 0.0.10 (2010-11-15) {@link IllegalStateException} is removed from the constructor.
 */
public final class Strings
{
	private Strings() throws IllegalAccessException
	{
		throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
	}

	/**
	 * Returns a copy of the given string, with leading and trailing whitespace omitted. If the given String contains
	 * <code>null</code> reference it returns an "" (empty String).
	 * 
	 * @param value
	 *          the given String value
	 * @return the result of value.trim(). If the value is null, return "" (empty String).
	 */
	public static String nullSafeTrim(final String value)
	{
		return (null == value ? "" : value.trim());
	}

	/**
	 * Returns an empty String ({@code ""}) if the given value is {@code null}. If it is not {@code null}, returns the
	 * given value itself.
	 * 
	 * @param value
	 *          the given String value to check for nullity.
	 * @return an empty String ({@code ""}) if the given value is {@code null}. If it is not {@code null}, returns the
	 *         given value itself
	 */
	public static String nullThenEmpty(final String value)
	{
		return null == value ? "" : value;
	}

	/**
	 * Returns {@code null} if the given String variable is empty (its length is 0, {@code ""}) or it is null. If it is
	 * not empty, returns the given value itself.
	 * 
	 * @param value
	 *          the given String value to check for emptiness.
	 * @return {@code null} if the given String variable is empty (its length is 0, {@code ""}) or it is null. If it is
	 *         not empty, returns the given value itself.
	 */
	public static String emptyThenNull(final String value)
	{
		return isNullOrEmptyString(value) ? null : value;
	}

	/**
	 * Checks if the given String value is empty. It means the given String parameter contains the null reference or no
	 * String value which means its length is 0.
	 * 
	 * @param value
	 *          the given String value.
	 * @return true if the given String parameter contains the null reference or it has no String value (null == value ||
	 *         0 == value.length()). false if the given String parameter contains a reference pointing to any String value
	 *         having the length of which is greater than 0.
	 */
	public static boolean isNullOrEmptyString(final String value)
	{
		return (null == value || 0 == value.length());
	}

	/**
	 * Checks if the given String value is not empty. It means the given String parameter contains neither the null
	 * reference nor any String with 0 length.
	 * 
	 * @param value
	 *          the given String value.
	 * @return true if the given String parameter contains a reference pointing to any String object the length of which
	 *         is greater than 0 (null != value && 0 < value.length()). false if it contains null or a String value with 0
	 *         length.
	 */
	public static boolean isNeitherNullNorEmptyString(final String value)
	{
		return !isNullOrEmptyString(value);
	}

	/**
	 * Repeats the given String the given number of times.
	 * 
	 * <pre>
	 * Strings.repeat("", -1);
	 * Result: {@link IllegalArgumentException}
	 * 
	 * Strings.repeat("", 0);
	 * Result: ""
	 * 
	 * Strings.repeat("", 10);
	 * Result: ""
	 * 
	 * Strings.repeat("Kevin ", -1);
	 * Result: {@link IllegalArgumentException}
	 * 
	 * Strings.repeat("Kevin ", 0);
	 * Result: ""
	 * 
	 * Strings.repeat("Kevin ", 1);
	 * Result: "Kevin "
	 * 
	 * Strings.repeat("Kevin ", 2);
	 * Result: "Kevin Kevin "
	 * 
	 * Strings.repeat("Kevin ", 5);
	 * Result: "Kevin Kevin Kevin Kevin Kevin "
	 * </pre>
	 * 
	 * @param value
	 *          the given String value.
	 * @param times
	 *          the given number of times to repeat.
	 * @return The String repeated the given number of times.
	 * @exception IllegalArgumentException
	 *              if the value of the times parameter is a negative value.
	 */
	public static String repeat(final String value, final int times) throws IllegalArgumentException
	{
		if (0 > times)
		{
			throw new IllegalArgumentException(
					"The value of the times parameter cannot be a negative number. It must be a non-negative number.");
		}
		final String valueToUse = toStringOf(value);
		final StringBuilder stringBuilder = new StringBuilder(valueToUse.length() * times);
		for (int i = 0; i < times; i++)
		{
			stringBuilder.append(valueToUse);
		}
		return stringBuilder.toString();
	}

	/**
	 * Returns a String array containing the result of matching the given String value against the given regular
	 * expression after passing find() test using Matcher object created by matcher() method in the Pattern object created
	 * with the given regular expression String. The first element in the array is the given String value itself. If no
	 * matching one is found (if the find() test fails), it returns an empty String array.
	 * 
	 * @param regex
	 *          the given Regular Expression String.
	 * @param value
	 *          the given String value
	 * @return a String array containing the result of matching the given String value against the given regular
	 *         expression after passing find() test using Matcher object created by matcher() method in the Pattern object
	 *         created with the given regular expression String. The first element in the array is the given String value
	 *         itself. If no matching one is found, it returns an empty String array.
	 * @see Pattern#compile(String)
	 * @see Pattern#matcher(CharSequence)
	 * @see Matcher#find()
	 */
	public static String[] findMatched(final String regex, final String value)
	{
		final Pattern pattern = Pattern.compile(regex);
		return findMatched(pattern, value);
	}

	/**
	 * Returns a String array containing the result of matching the given String value against the given Pattern object
	 * containing a regular expression after passing find() test using Matcher object created by matcher() method in the
	 * given Pattern object. The first element in the array is the given String value itself. If no matching one is found
	 * (if the find() test fails), it returns an empty String array.
	 * 
	 * @param compiledRegex
	 *          the given Pattern object containing a regular expression.
	 * @param value
	 *          the given String value
	 * @return a String array containing the result of matching the given String value against the given Pattern object
	 *         containing a regular expression after passing find() test using Matcher object created by matcher() method
	 *         in the given Pattern object. The first element in the array is the given String value itself. If no
	 *         matching one is found, it returns an empty String array.
	 * @see Pattern#matcher(CharSequence)
	 * @see Matcher#find()
	 */
	public static String[] findMatched(final Pattern compiledRegex, final String value)
	{
		final Matcher matcher = compiledRegex.matcher(value);
		if (matcher.find())
		{
			final int length = matcher.groupCount() + 1;
			final String[] textFound = new String[length];
			for (int i = 0; i < length; i++)
			{
				textFound[i] = matcher.group(i);
			}
			return textFound;
		}
		return NeoArrays.EMPTY_STRING_ARRAY;
	}

	/**
	 * Returns a String array containing the result of matching the given String value against the given regular
	 * expression after passing matches() test using Matcher object created by matcher() method in the Pattern object
	 * created with the given regular expression String. The first element in the array is the given String value itself.
	 * If no matching one is found (if the matches() test fails), it returns an empty String array.
	 * 
	 * @param regex
	 *          the given Regular Expression String.
	 * @param value
	 *          the given String value
	 * @return a String array containing the result of matching the given String value against the given regular
	 *         expression after passing matches() test using Matcher object created by matcher() method in the Pattern
	 *         object created with the given regular expression String. The first element in the array is the given String
	 *         value itself. If no matching one is found (if the matches() test fails), it returns an empty String array.
	 * @see Pattern#compile(String)
	 * @see Pattern#matcher(CharSequence)
	 * @see Matcher#matches()
	 */
	public static String[] matchEntirely(final String regex, final String value)
	{
		final Pattern pattern = Pattern.compile(regex);
		return matchEntirely(pattern, value);
	}

	/**
	 * Returns a String array containing the result of matching the given String value against the given Pattern object
	 * containing a regular expression after passing matches() test using Matcher object created by matcher() method in
	 * the given Pattern object. The first element in the array is the given String value itself. If no matching one is
	 * found (if the matches() test fails), it returns an empty String array.
	 * 
	 * @param compiledRegex
	 *          the given Pattern object containing a regular expression.
	 * @param value
	 *          the given String value
	 * @return a String array containing the result of matching the given String value against the given Pattern object
	 *         containing a regular expression after passing matches() test using Matcher object created by matcher()
	 *         method in the given Pattern object. The first element in the array is the given String value itself. If no
	 *         matching one is found (if the matches() test fails), it returns an empty String array.
	 * @see Pattern#matcher(CharSequence)
	 * @see Matcher#matches()
	 */
	public static String[] matchEntirely(final Pattern compiledRegex, final String value)
	{
		final Matcher matcher = compiledRegex.matcher(value);
		if (matcher.matches())
		{
			final int length = matcher.groupCount() + 1;
			final String[] textMatched = new String[length];
			for (int i = 0; i < length; i++)
			{
				textMatched[i] = matcher.group(i);
			}
			return textMatched;
		}
		return NeoArrays.EMPTY_STRING_ARRAY;
	}
}