/**
 * 
 */
package com.elixirian.common.util;

import static com.elixirian.common.test.CommonTestHelper.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-05-03)
 */
public class StringsTest
{
	@Test(expected = IllegalStateException.class)
	public void testStrings() throws Exception
	{
		testNotAccessibleConstructor(Strings.class, classArrayOf(), objectArrayOf());
	}

	/**
	 * Test method for {@link com.elixirian.common.util.Strings#nullSafeTrim(java.lang.String)}.
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

	/**
	 * Test method for {@link com.elixirian.common.util.Strings#isEmpty(java.lang.String)}.
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
	 * Test method for {@link com.elixirian.common.util.Strings#isNotEmpty(java.lang.String)}.
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

}
