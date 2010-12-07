/**
 * 
 */
package org.elixirian.common.util;

import static org.elixirian.common.test.CommonTestHelper.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.elixirian.common.test.CommonTestHelper.Accessibility;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-05-03)
 */
public class StringsTest
{
	@Test(expected = IllegalAccessException.class)
	public void testStrings() throws Exception
	{
		testNotAccessibleConstructor(Strings.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
	}

	/**
	 * Test method for {@link org.elixirian.common.util.Strings#nullSafeTrim(java.lang.String)}.
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
	 * Test method for {@link org.elixirian.common.util.Strings#isEmpty(java.lang.String)}.
	 */
	@Test
	public final void testIsEmpty()
	{
		final String nullReference = null;
		final String emptyString = "";
		final String notEmpty = "something";

		assertThat(Boolean.valueOf(Strings.isEmpty(nullReference)), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(Strings.isEmpty(emptyString)), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(Strings.isEmpty(notEmpty)), equalTo(Boolean.FALSE));
	}

	/**
	 * Test method for {@link org.elixirian.common.util.Strings#isNotEmpty(java.lang.String)}.
	 */
	@Test
	public final void testIsNotEmpty()
	{
		final String notEmpty = "something";
		final String nullReference = null;
		final String emptyString = "";

		assertThat(Boolean.valueOf(Strings.isNotEmpty(notEmpty)), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(Strings.isNotEmpty(nullReference)), equalTo(Boolean.FALSE));
		assertThat(Boolean.valueOf(Strings.isNotEmpty(emptyString)), equalTo(Boolean.FALSE));
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
			catch (IllegalArgumentException e)
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
			catch (IllegalArgumentException e)
			{
				exceptionCount++;
			}
		}
		assertEquals(howManyExpected, exceptionCount);
	}
}
