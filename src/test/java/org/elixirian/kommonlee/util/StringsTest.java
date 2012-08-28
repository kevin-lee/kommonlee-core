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

import static org.elixirian.kommonlee.test.CommonTestHelper.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.elixirian.kommonlee.test.CommonTestHelper.Accessibility;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-04-29)
 */
public class StringsTest
{
	@Test(expected = IllegalAccessException.class)
	public final void testStrings() throws Exception
	{
		testNotAccessibleConstructor(Strings.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
	}

	/**
	 * Test method for {@link org.elixirian.kommonlee.util.Strings#nullSafeTrim(java.lang.String)}.
	 */
	@Test
	public final void testNullSafeTrim()
	{
		assertThat(Strings.nullSafeTrim(null), equalTo(""));
		assertThat(Strings.nullSafeTrim(""), equalTo(""));
		assertThat(Strings.nullSafeTrim(" "), equalTo(""));
		assertThat(Strings.nullSafeTrim("test"), equalTo("test"));
		assertThat(Strings.nullSafeTrim(new String("something else")), equalTo(new String("something else")));
		assertThat(Strings.nullSafeTrim("test "), equalTo("test ".trim()));
		assertThat(Strings.nullSafeTrim(" test"), equalTo(" test".trim()));
		assertThat(Strings.nullSafeTrim("  test "), equalTo("  test ".trim()));
		assertThat(Strings.nullSafeTrim(" test  "), equalTo(" test  ".trim()));
		assertThat(Strings.nullSafeTrim("    te st   "), equalTo("    te st   ".trim()));
	}

	@Test
	public final void testNullThenEmpty()
	{
		assertThat(Strings.nullThenEmpty(null), equalTo(""));
		assertThat(Strings.nullThenEmpty(""), equalTo(""));
		assertThat(Strings.nullThenEmpty(" "), equalTo(" "));
		assertThat(Strings.nullThenEmpty("test"), equalTo("test"));
		assertThat(Strings.nullThenEmpty(new String("something else")), equalTo(new String("something else")));
	}

	@Test
	public final void testEmptyThenNull()
	{
		assertThat(Strings.emptyThenNull(null), equalTo(null));
		assertThat(Strings.emptyThenNull(""), equalTo(null));
		assertThat(Strings.emptyThenNull(" "), equalTo(" "));
		assertThat(Strings.emptyThenNull("test"), equalTo("test"));
		assertThat(Strings.emptyThenNull(new String("something else")), equalTo(new String("something else")));
	}

	/**
		 * Test method for {@link org.elixirian.kommonlee.util.Strings#isNullOrEmptyString(java.lang.String)}.
		 */
		@Test
		public final void testIsNullOrEmptyString()
		{
			final String nullReference = null;
			final String emptyString = "";
			final String notEmpty = "something";

			assertThat(Boolean.valueOf(Strings.isNullOrEmptyString(nullReference)), equalTo(Boolean.TRUE));
			assertThat(Boolean.valueOf(Strings.isNullOrEmptyString(emptyString)), equalTo(Boolean.TRUE));
			assertThat(Boolean.valueOf(Strings.isNullOrEmptyString(notEmpty)), equalTo(Boolean.FALSE));
		}

	/**
			 * Test method for {@link org.elixirian.kommonlee.util.Strings#isNeitherNullNorEmptyString(java.lang.String)}.
			 */
			@Test
			public final void testIsNeitherNullNorEmptyStringString()
			{
				final String notEmpty = "something";
				final String nullReference = null;
				final String emptyString = "";

				assertThat(Boolean.valueOf(Strings.isNeitherNullNorEmptyString(notEmpty)), equalTo(Boolean.TRUE));
				assertThat(Boolean.valueOf(Strings.isNeitherNullNorEmptyString(nullReference)), equalTo(Boolean.FALSE));
				assertThat(Boolean.valueOf(Strings.isNeitherNullNorEmptyString(emptyString)), equalTo(Boolean.FALSE));
			}

	@Test
	public final void testRepeat()
	{

		for (int i = 0; i < 5; i++)
		{
			assertThat(Strings.repeat("", i), equalTo(""));
		}

		final String value = "Kevin Lee ";

		final String[] expected = {
		/* 0 */
		"",
		/* 1 */
		value,
		/* 2 */
		value + value,
		/* 3 */
		value + value + value,
		/* 4 */
		value + value + value + value,
		/* 5 */
		value + value + value + value + value };

		for (int i = 0, size = expected.length; i < size; i++)
		{
			assertThat(Strings.repeat(value, i), equalTo(expected[i]));
		}

		final String value2 = "sdfjslfj20, 93%u4#fv@mdv397gtbw";
		final String[] expected2 = new String[100];
		for (int i = 0; i < 100; i++)
		{
			final StringBuilder stringBuilder = new StringBuilder(value2.length() * i);
			for (int j = 0; j < i; j++)
			{
				stringBuilder.append(value2);
			}
			expected2[i] = stringBuilder.toString();
		}

		for (int i = 0; i < 100; i++)
		{
			assertThat(Strings.repeat(value2, i), equalTo(expected2[i]));
		}
	}

	@Test
	public final void testRepeatWithNegativeTimesValue()
	{
		final int howManyExpected = 5;
		int exceptionCount = 0;
		for (int i = -howManyExpected; i < 0; i++)
		{
			try
			{
				Strings.repeat("", i);
			}
			catch (final IllegalArgumentException e)
			{
				exceptionCount++;
			}
		}
		assertEquals(howManyExpected, exceptionCount);

		final String value = "Kevin Lee ";
		exceptionCount = 0;
		for (int i = -howManyExpected; i < 0; i++)
		{
			try
			{
				Strings.repeat(value, i);
			}
			catch (final IllegalArgumentException e)
			{
				exceptionCount++;
			}
		}
		assertEquals(howManyExpected, exceptionCount);
	}

	@Test
	public void testFindMatchedStringString()
	{
		/* given */
		final String patternString1 = "\\d+";
		final String value1 = "abc123456fgfg";
		final String[] expected1 = { "123456" };

		/* when */
		final String[] actual1 = Strings.findMatched(patternString1, value1);

		/* then */
		assertThat(actual1, is(equalTo(expected1)));

		/* given */
		final String patternString2 = "\\d+";
		final String value2 = "abcdefghij";
		final String[] expected2 = {};

		/* when */
		final String[] actual2 = Strings.findMatched(patternString2, value2);

		/* then */
		assertThat(actual2, is(equalTo(expected2)));

		/* given */
		final String patternString3 = "(\\d+)[\\D]*(\\d+)";
		final String value3 = "abc123456fgfg123999";
		final String[] expected3 = { "123456fgfg123999", "123456", "123999" };

		/* when */
		final String[] actual3 = Strings.findMatched(patternString3, value3);

		/* then */
		assertThat(actual3, is(equalTo(expected3)));

		/* given */
		final String patternString4 = "[\\D]*(\\d+)[\\D]*(\\d+)";
		final String value4 = "abc123456fgfg123999";
		final String[] expected4 = { "abc123456fgfg123999", "123456", "123999" };

		/* when */
		final String[] actual4 = Strings.findMatched(patternString4, value4);

		/* then */
		assertThat(actual4, is(equalTo(expected4)));

		/* given */
		final String patternString5 = "\\d+";
		final String value5 = "123999";
		final String[] expected5 = { "123999" };

		/* when */
		final String[] actual5 = Strings.findMatched(patternString5, value5);

		/* then */
		assertThat(actual5, is(equalTo(expected5)));
	}

	@Test
	public void testFindMatchedPatternString()
	{
		/* given */
		final String patternString1 = "\\d+";
		final Pattern pattern1 = Pattern.compile(patternString1);
		final String value1 = "abc123456fgfg";
		final String[] expected1 = { "123456" };

		/* when */
		final String[] actual1 = Strings.findMatched(pattern1, value1);

		/* then */
		assertThat(actual1, is(equalTo(expected1)));

		/* given */
		final String patternString2 = "\\d+";
		final Pattern pattern2 = Pattern.compile(patternString2);
		final String value2 = "abcdefghij";
		final String[] expected2 = {};

		/* when */
		final String[] actual2 = Strings.findMatched(pattern2, value2);

		/* then */
		assertThat(actual2, is(equalTo(expected2)));

		/* given */
		final String patternString3 = "(\\d+)[\\D]*(\\d+)";
		final Pattern pattern3 = Pattern.compile(patternString3);
		final String value3 = "abc123456fgfg123999";
		final String[] expected3 = { "123456fgfg123999", "123456", "123999" };

		/* when */
		final String[] actual3 = Strings.findMatched(pattern3, value3);

		/* then */
		assertThat(actual3, is(equalTo(expected3)));

		/* given */
		final String patternString4 = "[\\D]*(\\d+)[\\D]*(\\d+)";
		final Pattern pattern4 = Pattern.compile(patternString4);
		final String value4 = "abc123456fgfg123999";
		final String[] expected4 = { "abc123456fgfg123999", "123456", "123999" };

		/* when */
		final String[] actual4 = Strings.findMatched(pattern4, value4);

		/* then */
		assertThat(actual4, is(equalTo(expected4)));

		/* given */
		final String patternString5 = "\\d+";
		final Pattern pattern5 = Pattern.compile(patternString5);
		final String value5 = "123999";
		final String[] expected5 = { "123999" };

		/* when */
		final String[] actual5 = Strings.findMatched(pattern5, value5);

		/* then */
		assertThat(actual5, is(equalTo(expected5)));
	}

	@Test
	public void testMatchEntirelyStringString()
	{
		/* given */
		final String patternString1 = "\\d+";
		final String value1 = "abc123456fgfg";
		final String[] expected1 = {};

		/* when */
		final String[] actual1 = Strings.matchEntirely(patternString1, value1);

		/* then */
		assertThat(actual1, is(equalTo(expected1)));

		/* given */
		final String patternString2 = "\\d+";
		final String value2 = "abcdefghij";
		final String[] expected2 = {};

		/* when */
		final String[] actual2 = Strings.matchEntirely(patternString2, value2);

		/* then */
		assertThat(actual2, is(equalTo(expected2)));

		/* given */
		final String patternString3 = "(\\d+)[\\D]*(\\d+)";
		final String value3 = "abc123456fgfg123999";
		final String[] expected3 = {};

		/* when */
		final String[] actual3 = Strings.matchEntirely(patternString3, value3);

		/* then */
		assertThat(actual3, is(equalTo(expected3)));

		/* given */
		final String patternString4 = "[\\D]*(\\d+)[\\D]*(\\d+)";
		final String value4 = "abc123456fgfg123999";
		final String[] expected4 = { "abc123456fgfg123999", "123456", "123999" };

		/* when */
		final String[] actual4 = Strings.matchEntirely(patternString4, value4);

		/* then */
		assertThat(actual4, is(equalTo(expected4)));

		/* given */
		final String patternString5 = "\\d+";
		final String value5 = "123999";
		final String[] expected5 = { "123999" };

		/* when */
		final String[] actual5 = Strings.matchEntirely(patternString5, value5);

		/* then */
		assertThat(actual5, is(equalTo(expected5)));
	}

	@Test
	public void testMatchEntirelyPatternString()
	{
		/* given */
		final String patternString1 = "\\d+";
		final Pattern pattern1 = Pattern.compile(patternString1);
		final String value1 = "abc123456fgfg";
		final String[] expected1 = {};

		/* when */
		final String[] actual1 = Strings.matchEntirely(pattern1, value1);

		/* then */
		assertThat(actual1, is(equalTo(expected1)));

		/* given */
		final String patternString2 = "\\d+";
		final Pattern pattern2 = Pattern.compile(patternString2);
		final String value2 = "abcdefghij";
		final String[] expected2 = {};

		/* when */
		final String[] actual2 = Strings.matchEntirely(pattern2, value2);

		/* then */
		assertThat(actual2, is(equalTo(expected2)));

		/* given */
		final String patternString3 = "(\\d+)[\\D]*(\\d+)";
		final Pattern pattern3 = Pattern.compile(patternString3);
		final String value3 = "abc123456fgfg123999";
		final String[] expected3 = {};

		/* when */
		final String[] actual3 = Strings.matchEntirely(pattern3, value3);

		/* then */
		assertThat(actual3, is(equalTo(expected3)));

		/* given */
		final String patternString4 = "[\\D]*(\\d+)[\\D]*(\\d+)";
		final Pattern pattern4 = Pattern.compile(patternString4);
		final String value4 = "abc123456fgfg123999";
		final String[] expected4 = { "abc123456fgfg123999", "123456", "123999" };

		/* when */
		final String[] actual4 = Strings.matchEntirely(pattern4, value4);

		/* then */
		assertThat(actual4, is(equalTo(expected4)));

		/* given */
		final String patternString5 = "\\d+";
		final Pattern pattern5 = Pattern.compile(patternString5);
		final String value5 = "123999";
		final String[] expected5 = { "123999" };

		/* when */
		final String[] actual5 = Strings.matchEntirely(pattern5, value5);

		/* then */
		assertThat(actual5, is(equalTo(expected5)));
	}
}
