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
 * @version 0.0.2 (2010-04-01)
 * @version 0.0.3 (2010-04-20) more test cases are added.
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

		result = MessageFormatter.formatMessage("Hello, [%s]. I am [%s].");
		System.out.println("Message:  \"Hello, [%s]. I am [%s].\"" + "\nArgument: " + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, []. I am []."));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("%s", "test");
		System.out.println("Message:  \"%s\"" + "\nArgument: \"test\"" + "\nResult:   " + result);
		assertThat(result, equalTo("test"));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("%s", "test", "another");
		System.out.println("Message:  \"%s\"" + "\nArgument: \"test\", \"another\"" + "\nResult:   " + result);
		assertThat(result, equalTo("test [another]"));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("%s %s.", "test");
		System.out.println("Message:  \"%s %s.\"" + "\nArgument: \"test\"" + "\nResult:   " + result);
		assertThat(result, equalTo("test ."));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("%s");
		System.out.println("Message:  \"%s\"" + "\nArgument: " + "\nResult:   " + result);
		assertThat(result, equalTo(""));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("%s%s%s");
		System.out.println("Message:  \"%s%s%s\"" + "\nArgument: " + "\nResult:   " + result);
		assertThat(result, equalTo(""));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("%s%%s%s%%s");
		System.out.println("Message:  \"%s%%s%s%%s\"" + "\nArgument: " + "\nResult:   " + result);
		assertThat(result, equalTo("%s%s"));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("%s", (Object) null);
		System.out.println("Message:  \"%s\"" + "\nArgument: (Object) null" + "\nResult:   " + result);
		assertThat(result, equalTo("null"));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("Hello, World", (Object) null);
		System.out.println("Message:  \"Hello, World\"" + "\nArgument: (Object) null" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, World [null]"));
		System.out.println("===================================================");

		result = MessageFormatter.formatMessage("Hello, %s", (Object) null);
		System.out.println("Message:  \"Hello, %s\"" + "\nArgument: (Object) null" + "\nResult:   " + result);
		assertThat(result, equalTo("Hello, null"));
		System.out.println("===================================================");

	}

	/**
	 * Test method for {@link com.elixirian.common.string.MessageFormatter#formatMessage(java.lang.String, java.lang.Object[])}.
	 */
	@Test(expected = NullPointerException.class)
	public final void testFormatMessageWithInvalidArgument()
	{
		MessageFormatter.formatMessage("Hello, World", null);
	}
}
