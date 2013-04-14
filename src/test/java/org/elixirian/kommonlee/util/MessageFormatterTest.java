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

import org.elixirian.kommonlee.test.CommonTestHelper.Accessibility;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-05-04)
 */
public class MessageFormatterTest
{
  @Test(expected = IllegalAccessException.class)
  public void testMessageFormatter() throws Exception
  {
    testNotAccessibleConstructor(MessageFormatter.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
  }

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
   * Test method for {@link org.elixirian.kommonlee.util.MessageFormatter#format(String, Object...)}.
   */
  @Test
  public final void testFormat()
  {
    String result = MessageFormatter.format("Hello, %s", "World");
    System.out.println("Message:  \"Hello, %s\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
    assertThat(result, equalTo("Hello, World"));
    System.out.println("===================================================");

    result = MessageFormatter.format("Hello, %s.", "World");
    System.out.println("Message:  \"Hello, %s.\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
    assertThat(result, equalTo("Hello, World."));
    System.out.println("===================================================");

    result =
      MessageFormatter.format("Hello, %s. My name is %s. This %s is to check if %s works well.", "World", new Person(
          "Lee", "Kevin"), "test", MessageFormatter.class);
    System.out.println("Message:  \"Hello, %s. My name is %s. This %s is to check if %s works well.\""
        + "\nArgument: \"World\", new Person(\"Lee\", \"Kevin\"), \"test\", MessageFormatter.class" + "\nResult:   "
        + result);
    assertThat(result,
        equalTo("Hello, World. My name is Kevin Lee. This test is to check if " + MessageFormatter.class.toString()
            + " works well."));
    System.out.println("===================================================");

    result = MessageFormatter.format("Hello, %%s%s", "World");
    System.out.println("Message:  \"Hello, %%s%s\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
    assertThat(result, equalTo("Hello, %sWorld"));
    System.out.println("===================================================");

    result = MessageFormatter.format("Hello, %s. How are you %s?", "World", "today");
    System.out.println("Message:  \"Hello, %s. How are you %s?\"" + "\nArgument: \"World\", \"today\"" + "\nResult:   "
        + result);
    assertThat(result, equalTo("Hello, World. How are you today?"));
    System.out.println("===================================================");

    result = MessageFormatter.format("Hello, %%%s%s%s.", "World");
    System.out.println("Message:  \"Hello, %%%s%s%s.\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
    assertThat(result, equalTo("Hello, %%sWorld."));
    System.out.println("===================================================");

    result = MessageFormatter.format("Hello, %%%s%s%s.%s", "World");
    System.out.println("Message:  \"Hello, %%%s%s%s.%s\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
    assertThat(result, equalTo("Hello, %%sWorld."));
    System.out.println("===================================================");

    result = MessageFormatter.format("Hello, %%%s%s%%s.", "World");
    System.out.println("Message:  \"Hello, %%%s%s%%s.\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
    assertThat(result, equalTo("Hello, %%sWorld%s."));
    System.out.println("===================================================");

    result = MessageFormatter.format("Hello, %%%s%%s%s.", "World");
    System.out.println("Message:  \"Hello, %%%s%%s%s.\"" + "\nArgument: \"World\"" + "\nResult:   " + result);
    assertThat(result, equalTo("Hello, %%s%sWorld."));
    System.out.println("===================================================");

    result =
      MessageFormatter.format("Hello, %s. How are you %s?", "World", "today", "sender", "is",
          new Person("Lee", "Kevin"));
    System.out.println("Message:  \"Hello, %s. How are you %s?\""
        + "\nArgument: \"World\", \"today\", \"sender\", \"is\", new Person(\"Lee\", \"Kevin\")" + "\nResult:   "
        + result);
    assertThat(result, equalTo("Hello, World. How are you today? [sender, is, Kevin Lee]"));
    System.out.println("===================================================");

    result = MessageFormatter.format("", "Hello", "World");
    System.out.println("Message:  \"\"" + "\nArgument: \"Hello\", \"World\"" + "\nResult:   " + result);
    assertThat(result, equalTo("[Hello, World]"));
    System.out.println("===================================================");

    result = MessageFormatter.format("Hello, World");
    System.out.println("Message:  \"Hello, World\"" + "\nArgument: " + "\nResult:   " + result);
    assertThat(result, equalTo("Hello, World"));
    System.out.println("===================================================");

    result = MessageFormatter.format("Hello, [%s]. I am [%s].");
    System.out.println("Message:  \"Hello, [%s]. I am [%s].\"" + "\nArgument: " + "\nResult:   " + result);
    assertThat(result, equalTo("Hello, []. I am []."));
    System.out.println("===================================================");

    result = MessageFormatter.format("%s", "test");
    System.out.println("Message:  \"%s\"" + "\nArgument: \"test\"" + "\nResult:   " + result);
    assertThat(result, equalTo("test"));
    System.out.println("===================================================");

    result = MessageFormatter.format("%s", "test", "another");
    System.out.println("Message:  \"%s\"" + "\nArgument: \"test\", \"another\"" + "\nResult:   " + result);
    assertThat(result, equalTo("test [another]"));
    System.out.println("===================================================");

    result = MessageFormatter.format("%s %s.", "test");
    System.out.println("Message:  \"%s %s.\"" + "\nArgument: \"test\"" + "\nResult:   " + result);
    assertThat(result, equalTo("test ."));
    System.out.println("===================================================");

    result = MessageFormatter.format("%s");
    System.out.println("Message:  \"%s\"" + "\nArgument: " + "\nResult:   " + result);
    assertThat(result, equalTo(""));
    System.out.println("===================================================");

    result = MessageFormatter.format("%s%s%s");
    System.out.println("Message:  \"%s%s%s\"" + "\nArgument: " + "\nResult:   " + result);
    assertThat(result, equalTo(""));
    System.out.println("===================================================");

    result = MessageFormatter.format("%s%%s%s%%s");
    System.out.println("Message:  \"%s%%s%s%%s\"" + "\nArgument: " + "\nResult:   " + result);
    assertThat(result, equalTo("%s%s"));
    System.out.println("===================================================");

    result = MessageFormatter.format("%s", (Object) null);
    System.out.println("Message:  \"%s\"" + "\nArgument: (Object) null" + "\nResult:   " + result);
    assertThat(result, equalTo("null"));
    System.out.println("===================================================");

    result = MessageFormatter.format("%s, %s", (Object) null, (Object) null);
    System.out.println("Message:  \"%s, %s\"" + "\nArgument: (Object) null, (Object) null" + "\nResult:   " + result);
    assertThat(result, equalTo("null, null"));
    System.out.println("===================================================");

    result = MessageFormatter.format("Hello, World", (Object) null);
    System.out.println("Message:  \"Hello, World\"" + "\nArgument: (Object) null" + "\nResult:   " + result);
    assertThat(result, equalTo("Hello, World [null]"));
    System.out.println("===================================================");

    result = MessageFormatter.format("Hello, World", (Object) null, (Object) null);
    System.out.println("Message:  \"Hello, World\"" + "\nArgument: (Object) null, (Object) null" + "\nResult:   "
        + result);
    assertThat(result, equalTo("Hello, World [null, null]"));
    System.out.println("===================================================");

    result = MessageFormatter.format("Hello, %s", (Object) null);
    System.out.println("Message:  \"Hello, %s\"" + "\nArgument: (Object) null" + "\nResult:   " + result);
    assertThat(result, equalTo("Hello, null"));
    System.out.println("===================================================");

    result = MessageFormatter.format("Hello, %s", (Object) null, (Object) null);
    System.out.println("Message:  \"Hello, %s\"" + "\nArgument: (Object) null, (Object) null" + "\nResult:   " + result);
    assertThat(result, equalTo("Hello, null [null]"));
    System.out.println("===================================================");

    result = MessageFormatter.format(null);
    System.out.println("Message:  null" + "\nArgument: " + "\nResult:   " + result);
    assertThat(result, equalTo("null"));
    System.out.println("===================================================");

    result = MessageFormatter.format(null, "Kevin", "Lee");
    System.out.println("Message:  null" + "\nArgument: \"Kevin\", \"Lee\"" + "\nResult:   " + result);
    assertThat(result, equalTo("null [Kevin, Lee]"));
    System.out.println("===================================================");

    result = MessageFormatter.format(null, (Object) null);
    System.out.println("Message:  null" + "\nArgument: (Object) null" + "\nResult:   " + result);
    assertThat(result, equalTo("null [null]"));
    System.out.println("===================================================");

  }

  /**
   * Test method for {@link org.elixirian.kommonlee.util.MessageFormatter#format(String, Object...)}.
   */
  @Test(expected = NullPointerException.class)
  public final void testFormatWithInvalidArgument()
  {
    MessageFormatter.format("Hello, World", (Object[]) null);
  }

}
