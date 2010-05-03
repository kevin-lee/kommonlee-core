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
	private static class Person
	{
		private final String surname;
		private final String givenName;

		public Person(String surname, String givenName)
		{
			this.surname = surname;
			this.givenName = givenName;
		}

		@Override
		public String toString()
		{
			return givenName + " " + surname;
		}
	}

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

	/**
	 * Test method for {@link com.elixirian.common.util.Strings#format(java.lang.String, java.lang.Object...)}.
	 */
	@Test
	public final void testFormat()
	{
		String result = Strings.format("Hello, %s", "World");
		System.out.println("Message:  \"Hello, %s\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World"));
		System.out.println("===================================================");

		result = Strings.format("Hello, %s.", "World");
		System.out.println("Message:  \"Hello, %s.\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World."));
		System.out.println("===================================================");

		result =
			Strings.format("Hello, %s. My name is %s. This %s is to check if %s works well.", "World", new Person("Lee", "Kevin"), "test",
					Strings.class);
		System.out.println("Message:  \"Hello, %s. My name is %s. This %s is to check if %s works well.\""
				+ "\nArgument: \"World\", new Person(\"Lee\", \"Kevin\"), \"test\", Strings.class" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World. My name is Kevin Lee. This test is to check if " + Strings.class.toString()
				+ " works well."));
		System.out.println("===================================================");

		result = Strings.format("Hello, %%s%s", "World");
		System.out.println("Message:  \"Hello, %%s%s\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, %sWorld"));
		System.out.println("===================================================");

		result = Strings.format("Hello, %s. How are you %s?", "World", "today");
		System.out.println("Message:  \"Hello, %s. How are you %s?\"" + "\nArgument: \"World\", \"today\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World. How are you today?"));
		System.out.println("===================================================");

		result = Strings.format("Hello, %%%s%s%s.", "World");
		System.out.println("Message:  \"Hello, %%%s%s%s.\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, %%sWorld."));
		System.out.println("===================================================");

		result = Strings.format("Hello, %%%s%s%s.%s", "World");
		System.out.println("Message:  \"Hello, %%%s%s%s.%s\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, %%sWorld."));
		System.out.println("===================================================");

		result = Strings.format("Hello, %%%s%s%%s.", "World");
		System.out.println("Message:  \"Hello, %%%s%s%%s.\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, %%sWorld%s."));
		System.out.println("===================================================");

		result = Strings.format("Hello, %%%s%%s%s.", "World");
		System.out.println("Message:  \"Hello, %%%s%%s%s.\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, %%s%sWorld."));
		System.out.println("===================================================");

		result = Strings.format("Hello, %s. How are you %s?", "World", "today", "sender", "is", new Person("Lee", "Kevin"));
		System.out.println("Message:  \"Hello, %s. How are you %s?\""
				+ "\nArgument: \"World\", \"today\", \"sender\", \"is\", new Person(\"Lee\", \"Kevin\")" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World. How are you today? [sender, is, Kevin Lee]"));
		System.out.println("===================================================");

		result = Strings.format("", "Hello", "World");
		System.out.println("Message:  \"\"" + "\nArgument: \"Hello\", \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("[Hello, World]"));
		System.out.println("===================================================");

		result = Strings.format("Hello, World");
		System.out.println("Message:  \"Hello, World\"" + "\nArgument: " + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World"));
		System.out.println("===================================================");

		result = Strings.format("Hello, [%s]. I am [%s].");
		System.out.println("Message:  \"Hello, [%s]. I am [%s].\"" + "\nArgument: " + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, []. I am []."));
		System.out.println("===================================================");

		result = Strings.format("%s", "test");
		System.out.println("Message:  \"%s\"" + "\nArgument: \"test\"" + "\nResult:   " + result);
		assertThat(result, equalTo("test"));
		System.out.println("===================================================");

		result = Strings.format("%s", "test", "another");
		System.out.println("Message:  \"%s\"" + "\nArgument: \"test\", \"another\"" + "\nResult:   " + result);
		assertThat(result, equalTo("test [another]"));
		System.out.println("===================================================");

		result = Strings.format("%s %s.", "test");
		System.out.println("Message:  \"%s %s.\"" + "\nArgument: \"test\"" + "\nResult:   " + result);
		assertThat(result, equalTo("test ."));
		System.out.println("===================================================");

		result = Strings.format("%s");
		System.out.println("Message:  \"%s\"" + "\nArgument: " + "\nResult:   " + result);
		assertThat(result, equalTo(""));
		System.out.println("===================================================");

		result = Strings.format("%s%s%s");
		System.out.println("Message:  \"%s%s%s\"" + "\nArgument: " + "\nResult:   " + result);
		assertThat(result, equalTo(""));
		System.out.println("===================================================");

		result = Strings.format("%s%%s%s%%s");
		System.out.println("Message:  \"%s%%s%s%%s\"" + "\nArgument: " + "\nResult:   " + result);
		assertThat(result, equalTo("%s%s"));
		System.out.println("===================================================");

		result = Strings.format("%s", (Object) null);
		System.out.println("Message:  \"%s\"" + "\nArgument: (Object) null" + "\nResult:   " + result);
		assertThat(result, equalTo("null"));
		System.out.println("===================================================");

		result = Strings.format("Hello, World", (Object) null);
		System.out.println("Message:  \"Hello, World\"" + "\nArgument: (Object) null" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World [null]"));
		System.out.println("===================================================");

		result = Strings.format("Hello, %s", (Object) null);
		System.out.println("Message:  \"Hello, %s\"" + "\nArgument: (Object) null" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, null"));
		System.out.println("===================================================");

	}

	/**
	 * Test method for {@link com.elixirian.common.util.Strings#format(java.lang.String, java.lang.Object...)}.
	 */
	@Test(expected = NullPointerException.class)
	public final void testFormatWithInvalidArgument()
	{
		Strings.format("Hello, World", null);
	}
}
