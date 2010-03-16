/**
 * 
 */
package com.elixirian.common.string;

import static com.elixirian.common.test.CommonTestHelper.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-02-23)
 */
public class MessageFormatterTest
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

	/**
	 * Test method for {@link com.elixirian.common.string.MessageFormatter#MessageFormatter() }
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalStateException.class)
	public void testMessageFormatter() throws Exception
	{
		testNotAccessibleConstructor(MessageFormatter.class, classArrayOf(), objectArrayOf());
	}

	/**
	 * Test method for {@link com.elixirian.common.string.MessageFormatter#formatMessage(java.lang.String, java.lang.Object[])}.
	 */
	@Test
	public final void testFormatMessage()
	{
		String result = MessageFormatter.formatMessage("Hello, %s", "World");
		System.out.println("Message:  \"Hello, %s\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World"));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("Hello, %s.", "World");
		System.out.println("Message:  \"Hello, %s.\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World."));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("Hello, %s. My name is %s. This %s is to check if %s works well.", "World", new Person(
				"Lee", "Kevin"), "test", MessageFormatter.class);
		System.out.println("Message:  \"Hello, %s. My name is %s. This %s is to check if %s works well.\""
				+ "\nArgument: \"World\", new Person(\"Lee\", \"Kevin\"), \"test\", MessageFormatter.class" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World. My name is Kevin Lee. This test is to check if " + MessageFormatter.class.toString()
				+ " works well."));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("Hello, %%s%s", "World");
		System.out.println("Message:  \"Hello, %%s%s\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, %sWorld"));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("Hello, %s. How are you %s?", "World", "today");
		System.out.println("Message:  \"Hello, %s. How are you %s?\"" + "\nArgument: \"World\", \"today\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World. How are you today?"));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("Hello, %%%s%s%s.", "World");
		System.out.println("Message:  \"Hello, %%%s%s%s.\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, %%sWorld."));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("Hello, %%%s%s%s.%s", "World");
		System.out.println("Message:  \"Hello, %%%s%s%s.%s\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, %%sWorld."));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("Hello, %%%s%s%%s.", "World");
		System.out.println("Message:  \"Hello, %%%s%s%%s.\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, %%sWorld%s."));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("Hello, %%%s%%s%s.", "World");
		System.out.println("Message:  \"Hello, %%%s%%s%s.\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, %%s%sWorld."));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("Hello, %s. How are you %s?", "World", "today", "sender", "is", new Person("Lee", "Kevin"));
		System.out.println("Message:  \"Hello, %s. How are you %s?\""
				+ "\nArgument: \"World\", \"today\", \"sender\", \"is\", new Person(\"Lee\", \"Kevin\")" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World. How are you today? [sender, is, Kevin Lee]"));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("", "Hello", "World");
		System.out.println("Message:  \"\"" + "\nArgument: \"Hello\", \"World\"" + "\nResult:   " + result);
		assertThat(result, equalTo("[Hello, World]"));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("Hello, World");
		System.out.println("Message:  \"Hello, World\"" + "\nArgument: " + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World"));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("Hello, World", (Object) null);
		System.out.println("Message:  \"Hello, World\"" + "\nArgument: (Object) null" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World [null]"));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("Hello, World", null);
		System.out.println("Message:  \"Hello, World\"" + "\nArgument: null" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World"));
		System.out.println("===================================================");
	}
}
