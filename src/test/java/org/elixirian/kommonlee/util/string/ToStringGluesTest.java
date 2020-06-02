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
package org.elixirian.kommonlee.util.string;

import static java.util.Arrays.*;
import static java.util.Collections.*;
import static org.elixirian.kommonlee.util.MessageFormatter.*;
import static org.elixirian.kommonlee.util.Objects.*;
import static org.elixirian.kommonlee.util.Strings.*;
import static org.elixirian.kommonlee.util.string.ToStringGlues.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.functional.Function1;
import org.elixirian.kommonlee.util.string.ToStringGlues.ForIterableBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-14)
 */
public class ToStringGluesTest
{
  private static final String[] STRINGS = { "Kevin", "Lee", "", "blah", "   ", "Test String", "blah", "blah", "Hello",
      "Kevin", null, "blah blah", "Hello", ", ", "World", "blah", ", ", "blah", "", " ", "Kevin", " ", "Lee", " ",
      "0123456789", "Kevin", null, "Lee", " ", "blah", null, "blah blah", "   ", "Kevin", "   ", "Testing", "#3374665",
      "Not Kevin", "StringGlue" };
  private static final List<String> STRING_LIST = asList(STRINGS);

  private static class Person
  {
    private final String surname;
    private final String givenName;

    Person(final String surname, final String givenName)
    {
      this.surname = surname;
      this.givenName = givenName;
    }

    public String getSurname()
    {
      return surname;
    }

    public String getGivenName()
    {
      return givenName;
    }

    @Override
    public int hashCode()
    {
      return hash(surname, givenName);
    }

    @Override
    public boolean equals(final Object person)
    {
      if (identical(this, person))
      {
        return true;
      }
      final Person that = castIfInstanceOf(Person.class, person);
      /* @formatter:off */
      return isNotNull(that)
          && (equal(this.surname, that.getSurname()) &&
              equal(this.givenName, that.getGivenName()));
      /* @formatter:on */
    }

    @Override
    public String toString()
    {
      return "{" + givenName + " " + surname + "}";

    }
  }

  private static final Person[] PEOPLE = new Person[] { new Person("Lee", "Kevin"), new Person("", ""), null,
      new Person("Kent", "Clark"), new Person("", ""), new Person("Wayne", "Bruce"), null,
      new Person("Parker", "Peter"), new Person("Lee", ""), new Person("Jordan", "Hal"), new Person("Banner", "Bruce"),
      new Person("Person", "Unknown") };

  private static final List<Person> PERSON_LIST = asList(PEOPLE);

  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
  }

  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public final void testBuilderForIterable()
  {
    System.out.println("\nToStringGluesTest.testBuilderForIterable()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      stringBuilder.append(each);
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparator()
  {
    System.out.println("\nToStringGluesTest.testIterableToStringGlueWithoutSeparator()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      stringBuilder.append(each);
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNull()
  {
    /*
     * without separator and ignore null.
     */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null != each)
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreOneSpace()
  {
    /*
     * without separator and ignore null.
     */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (!" ".equals(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .ignore(" ")
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNullAndEmptyString()
  {
    /*
     * without separator, ignore null, ignore empty String ("").
     */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null != each && !"".equals(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .ignoreNull()
        .ignore("")
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNullEmptyStringAndOtherStrings()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and other String values.
     */
    final String[] ignoreStrings = { "Kevin", "Hello" };
    final Set<String> ignoreSet = unmodifiableSet(new HashSet<String>(asList(ignoreStrings)));
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null != each && !"".equals(each) && !ignoreSet.contains(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .ignoreNull()
        .ignore("")
        .ignore(ignoreStrings[0])
        .ignore(ignoreStrings[1])
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNullEmptyStringAndOtherStringsReplaceStrings()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and other String values.
     */
    final String[] ignoreStrings = { "blah", "blah blah", ", " };
    final Set<String> ignoreSet = unmodifiableSet(new HashSet<String>(asList(ignoreStrings)));

    final String[][] replaceStrings = { { "Kevin", "Clark" }, { "Lee", "Kent" } };
    final Map<String, String> tmpMap = new HashMap<String, String>();
    tmpMap.put(replaceStrings[0][0], replaceStrings[0][1]);
    tmpMap.put(replaceStrings[1][0], replaceStrings[1][1]);
    final Map<String, String> replaceMap = unmodifiableMap(tmpMap);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null != each && !"".equals(each) && !ignoreSet.contains(each))
      {
        final String newValue = replaceMap.get(each);
        stringBuilder.append(null == newValue ? each : newValue);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .ignoreNull()
        .ignore("")
        .ignore(ignoreStrings[0])
        .ignore(ignoreStrings[1])
        .ignore(ignoreStrings[2])
        .replace(replaceStrings[0][0], replaceStrings[0][1])
        .replace(replaceStrings[1][0], replaceStrings[1][1])
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNullEmptyStringOneSpaceAndOtherStringsReplaceStrings()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and other String values.
     */
    final String[] ignoreStrings = { "blah", "blah blah", ", ", " " };
    final Set<String> ignoreSet = unmodifiableSet(new HashSet<String>(asList(ignoreStrings)));

    final String[][] replaceStrings = { { "Kevin", "Clark" }, { "Lee", "Kent" } };
    final Map<String, String> tmpMap = new HashMap<String, String>();
    tmpMap.put(replaceStrings[0][0], replaceStrings[0][1]);
    tmpMap.put(replaceStrings[1][0], replaceStrings[1][1]);
    final Map<String, String> replaceMap = unmodifiableMap(tmpMap);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null != each && !"".equals(each) && !ignoreSet.contains(each))
      {
        final String newValue = replaceMap.get(each);
        stringBuilder.append(null == newValue ? each : newValue);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .ignoreNull()
        .ignore("")
        .ignore(ignoreStrings[0])
        .ignore(ignoreStrings[1])
        .ignore(ignoreStrings[2])
        .ignore(ignoreStrings[3])
        .replace(replaceStrings[0][0], replaceStrings[0][1])
        .replace(replaceStrings[1][0], replaceStrings[1][1])
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreAll()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and other String values.
     */
    final Set<String> ignoreSet = unmodifiableSet(new HashSet<String>(STRING_LIST));

    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = builderForIterable();
    for (final String each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<String> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreAllButFirst()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and all the String values in the STRING_LIST but the
     * first one.
     */
    final Set<String> tmpSet = new HashSet<String>(STRING_LIST);
    tmpSet.remove(STRING_LIST.get(0));
    final Set<String> ignoreSet = unmodifiableSet(tmpSet);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = builderForIterable();
    for (final String each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<String> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreAllButLast()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and all the String values in the STRING_LIST but the
     * last one.
     */
    final Set<String> tmpSet = new HashSet<String>(STRING_LIST);
    tmpSet.remove(STRING_LIST.get(STRING_LIST.size() - 1));
    final Set<String> ignoreSet = unmodifiableSet(tmpSet);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = builderForIterable();
    for (final String each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<String> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreByCondition()
  {
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (isNeitherNullNorEmptyString(each))
      {
        boolean allSpaceChars = true;
        for (final char c : each.toCharArray())
        {
          if (' ' != c)
          {
            allSpaceChars = false;
            break;
          }
        }
        if (!allSpaceChars)
        {
          stringBuilder.append(each);
        }
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = builderForIterable();
    /* @formatter:off */
    final IterableToStringGlue<String> stringGlue = builder
        .ignoreIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return isNullOrEmptyString(value);
          }
        })
        .ignoreIf(new Condition1<String>() {
          Pattern pattern = Pattern.compile("[ ]+");

          @Override
          public boolean isMet(final String value)
          {
            return pattern.matcher(value)
                .matches();
          }
        })
      .build();
    /* @formatter:on */

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorWithConditionalReplacement()
  {
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null == each)
      {
        stringBuilder.append("[NULL]");
        continue;
      }
      if (each.isEmpty())
      {
        stringBuilder.append("[EMPTY]");
        continue;
      }
      boolean allSpaceChars = true;
      for (final char c : each.toCharArray())
      {
        if (' ' != c)
        {
          allSpaceChars = false;
          break;
        }
      }
      if (allSpaceChars)
      {
        stringBuilder.append("[WHITE SPACE]");
      }
      else
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = builderForIterable();
    /* @formatter:off */
    final IterableToStringGlue<String> stringGlue = builder
        .replaceIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return null == value;
          }
        }, "[NULL]")
        .replaceIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return null != value && value.isEmpty();
          }
        }, "[EMPTY]")
        .replaceIf(new Condition1<String>() {
          Pattern pattern = Pattern.compile("[ ]+");

          @Override
          public boolean isMet(final String value)
          {
            if (isNullOrEmptyString(value))
            {
              return true;
            }
            return pattern.matcher(value)
                .matches();
          }
        }, "[WHITE SPACE]")
      .build();
    /* @formatter:on */

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorWithMapping()
  {
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null == each)
      {
        stringBuilder.append("[what: null, value: '" + each + "']");
        continue;
      }
      if (each.isEmpty())
      {
        stringBuilder.append("[what: empty, value: '" + each + "']");
        continue;
      }
      if (" ".equals(each))
      {
        stringBuilder.append("[what: one whitespace, value: '" + each + "']");
      }
      else
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = builderForIterable();
    /* @formatter:off */
    final IterableToStringGlue<String> stringGlue = builder
        .mapWith(null, new Function1<String, String>() {
          @Override
          public String apply(final String value)
          {
            return "[what: null, value: '" + value + "']";
          }})
        .mapWith("", new Function1<String, String>() {
          @Override
          public String apply(final String value)
          {
            return "[what: empty, value: '" + value + "']";
          }})
        .mapWith(" ", new Function1<String, String>() {
          @Override
          public String apply(final String value)
          {
            return "[what: one whitespace, value: '" + value + "']";
          }})
      .build();
    /* @formatter:on */

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorWithConditionalMapping()
  {
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null == each)
      {
        stringBuilder.append("[what: null, value: '" + each + "']");
        continue;
      }
      if (each.isEmpty())
      {
        stringBuilder.append("[what: empty, value: '" + each + "']");
        continue;
      }
      boolean allSpaceChars = true;
      for (final char c : each.toCharArray())
      {
        if (' ' != c)
        {
          allSpaceChars = false;
          break;
        }
      }
      if (allSpaceChars)
      {
        stringBuilder.append("[what: all whitespace, value: '" + each + "']");
      }
      else
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = builderForIterable();
    /* @formatter:off */
    final IterableToStringGlue<String> stringGlue = builder
        .mapIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return null == value;
          }
        }, new Function1<String, String>() {

          @Override
          public String apply(final String value)
          {
            return "[what: null, value: '" + value + "']";
          }})
        .mapIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return null != value && value.isEmpty();
          }
        }, new Function1<String, String>() {

          @Override
          public String apply(final String value)
          {
            return "[what: empty, value: '" + value + "']";
          }})
        .mapIf(new Condition1<String>() {
          Pattern pattern = Pattern.compile("[ ]+");

          @Override
          public boolean isMet(final String value)
          {
            if (isNullOrEmptyString(value))
            {
              return true;
            }
            return pattern.matcher(value)
                .matches();
          }
        }, new Function1<String, String>() {

          @Override
          public String apply(final String value)
          {
            return "[what: all whitespace, value: '" + value + "']";
          }})
        .build();
    /* @formatter:on */

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorWithMappingAll()
  {
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      stringBuilder.append("{value:\"" + each + "\"}");
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = builderForIterable();
    /* @formatter:off */
    final IterableToStringGlue<String> stringGlue = builder
        .mapIfNotMappedWithAny(new Function1<String, String>() {

          @Override
          public String apply(final String value)
          {
            return "{value:\"" + value + "\"}";
          }})
      .build();
    /* @formatter:on */

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparator()
  {
    /* with separator (", ") */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      stringBuilder.append(each)
          .append(", ");
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNull()
  {
    /*
     * with separator (", ") and ignore null.
     */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreOneSpace()
  {
    /*
     * with separator (", ") and ignore null.
     */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (!" ".equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ")
        .ignore(" ")
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNullAndEmptyString()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("").
     */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null != each && !"".equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNullEmptyStringAndOtherStrings()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("") and other String values.
     */
    final String[] ignoreStrings = { "Kevin", "Hello" };
    final Set<String> ignoreSet = unmodifiableSet(new HashSet<String>(asList(ignoreStrings)));
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null != each && !"".equals(each) && !ignoreSet.contains(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .ignore(ignoreStrings[0])
        .ignore(ignoreStrings[1])
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNullEmptyStringAndOtherStringsReplaceStrings()
  {
    /*
     * with separator (", "), ignore null, empty String (""), ", " and other String values.
     */
    final String[] ignoreStrings = { "blah", "blah blah", ", " };
    final Set<String> ignoreSet = unmodifiableSet(new HashSet<String>(asList(ignoreStrings)));

    final String[][] replaceStrings = { { "Kevin", "Clark" }, { "Lee", "Kent" } };
    final Map<String, String> tmpMap = new HashMap<String, String>();
    tmpMap.put(replaceStrings[0][0], replaceStrings[0][1]);
    tmpMap.put(replaceStrings[1][0], replaceStrings[1][1]);
    final Map<String, String> replaceMap = unmodifiableMap(tmpMap);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null != each && !"".equals(each) && !ignoreSet.contains(each))
      {
        final String newValue = replaceMap.get(each);
        stringBuilder.append(null == newValue ? each : newValue)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .ignore(ignoreStrings[0])
        .ignore(ignoreStrings[1])
        .ignore(ignoreStrings[2])
        .replace(replaceStrings[0][0], replaceStrings[0][1])
        .replace(replaceStrings[1][0], replaceStrings[1][1])
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNullEmptyStringOneSpaceAndOtherStringsReplaceStrings()
  {
    System.out.println("\nToStringGluesTest.testIterableToStringGlueWithSeparatorIgnoreNullEmptyStringOneSpaceAndOtherStringsReplaceStrings()");
    /*
     * with separator (", "), ignore null, empty String (""), ", ", one space (" "), and other String values.
     */
    final String[] ignoreStrings = { "blah", "blah blah", ", ", " " };
    final Set<String> ignoreSet = unmodifiableSet(new HashSet<String>(asList(ignoreStrings)));

    final String[][] replaceStrings = { { "Kevin", "Clark" }, { "Lee", "Kent" } };
    final Map<String, String> tmpMap = new HashMap<String, String>();
    tmpMap.put(replaceStrings[0][0], replaceStrings[0][1]);
    tmpMap.put(replaceStrings[1][0], replaceStrings[1][1]);
    final Map<String, String> replaceMap = unmodifiableMap(tmpMap);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null != each && !"".equals(each) && !" ".equals(each) && !ignoreSet.contains(each))
      {
        final String newValue = replaceMap.get(each);
        stringBuilder.append(null == newValue ? each : newValue)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<String> stringGlue = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .ignore(ignoreStrings[0])
        .ignore(ignoreStrings[1])
        .ignore(ignoreStrings[2])
        .ignore(ignoreStrings[3])
        .replace(replaceStrings[0][0], replaceStrings[0][1])
        .replace(replaceStrings[1][0], replaceStrings[1][1])
        .build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreAll()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("") and other String values.
     */
    final Set<String> ignoreSet = unmodifiableSet(new HashSet<String>(STRING_LIST));

    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ");
    for (final String each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<String> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreAllButFirst()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("") and all the String values in the STRING_LIST but the
     * first one.
     */
    final Set<String> tmpSet = new HashSet<String>(STRING_LIST);
    tmpSet.remove(STRING_LIST.get(0));
    final Set<String> ignoreSet = unmodifiableSet(tmpSet);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ");
    for (final String each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<String> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreAllButLast()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("") and all the String values in the STRING_LIST but the
     * last one.
     */
    final Set<String> tmpSet = new HashSet<String>(STRING_LIST);
    tmpSet.remove(STRING_LIST.get(STRING_LIST.size() - 1));
    final Set<String> ignoreSet = unmodifiableSet(tmpSet);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ");
    for (final String each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<String> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreByCondition()
  {
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (isNeitherNullNorEmptyString(each))
      {
        boolean allSpaceChars = true;
        for (final char c : each.toCharArray())
        {
          if (' ' != c)
          {
            allSpaceChars = false;
            break;
          }
        }
        if (!allSpaceChars)
        {
          stringBuilder.append(each)
              .append(", ");
        }
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ");
    /* @formatter:off */
    final IterableToStringGlue<String> stringGlue = builder
        .ignoreIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return isNullOrEmptyString(value);
          }
        })
        .ignoreIf(new Condition1<String>() {
          Pattern pattern = Pattern.compile("[ ]+");

          @Override
          public boolean isMet(final String value)
          {
            return pattern.matcher(value)
                .matches();
          }
        })
      .build();
    /* @formatter:on */

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorConditionalReplacement()
  {
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null == each)
      {
        stringBuilder.append("[NULL]")
            .append(", ");
        continue;
      }
      if (each.isEmpty())
      {
        stringBuilder.append("[EMPTY]")
            .append(", ");
        continue;
      }
      boolean allSpaceChars = true;
      for (final char c : each.toCharArray())
      {
        if (' ' != c)
        {
          allSpaceChars = false;
          break;
        }
      }
      if (allSpaceChars)
      {
        stringBuilder.append("[WHITE SPACE]");
      }
      else
      {
        stringBuilder.append(each);
      }
      stringBuilder.append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ");
    /* @formatter:off */
    final IterableToStringGlue<String> stringGlue = builder
        .replaceIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return null == value;
          }
        }, "[NULL]")
        .replaceIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return null != value && value.isEmpty();
          }
        }, "[EMPTY]")
        .replaceIf(new Condition1<String>() {
          Pattern pattern = Pattern.compile("[ ]+");

          @Override
          public boolean isMet(final String value)
          {
            if (isNullOrEmptyString(value))
            {
              return true;
            }
            return pattern.matcher(value)
                .matches();
          }
        }, "[WHITE SPACE]")
      .build();
    /* @formatter:on */

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorWithMapping()
  {
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null == each)
      {
        stringBuilder.append("[what: null, value: '" + each + "']")
            .append(", ");
        continue;
      }
      if (each.isEmpty())
      {
        stringBuilder.append("[what: empty, value: '" + each + "']")
            .append(", ");
        continue;
      }
      if (" ".equals(each))
      {
        stringBuilder.append("[what: one whitespace, value: '" + each + "']");
      }
      else
      {
        stringBuilder.append(each);
      }
      stringBuilder.append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ");
    /* @formatter:off */
    final IterableToStringGlue<String> stringGlue = builder
        .mapWith(null, new Function1<String, String>() {
          @Override
          public String apply(final String value)
          {
            return "[what: null, value: '" + value + "']";
          }})
        .mapWith("", new Function1<String, String>() {
          @Override
          public String apply(final String value)
          {
            return "[what: empty, value: '" + value + "']";
          }})
        .mapWith(" ", new Function1<String, String>() {
          @Override
          public String apply(final String value)
          {
            return "[what: one whitespace, value: '" + value + "']";
          }})
      .build();
    /* @formatter:on */

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorWithConditionalMapping()
  {
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null == each)
      {
        stringBuilder.append("[what: null, value: '" + each + "']")
            .append(", ");
        continue;
      }
      if (each.isEmpty())
      {
        stringBuilder.append("[what: empty, value: '" + each + "']")
            .append(", ");
        continue;
      }
      boolean allSpaceChars = true;
      for (final char c : each.toCharArray())
      {
        if (' ' != c)
        {
          allSpaceChars = false;
          break;
        }
      }
      if (allSpaceChars)
      {
        stringBuilder.append("[what: all whitespace, value: '" + each + "']");
      }
      else
      {
        stringBuilder.append(each);
      }
      stringBuilder.append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ");
    /* @formatter:off */
    final IterableToStringGlue<String> stringGlue = builder
        .mapIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return null == value;
          }
        }, new Function1<String, String>() {

          @Override
          public String apply(final String value)
          {
            return "[what: null, value: '" + value + "']";
          }})
        .mapIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return null != value && value.isEmpty();
          }
        }, new Function1<String, String>() {

          @Override
          public String apply(final String value)
          {
            return "[what: empty, value: '" + value + "']";
          }})
        .mapIf(new Condition1<String>() {
          Pattern pattern = Pattern.compile("[ ]+");

          @Override
          public boolean isMet(final String value)
          {
            if (isNullOrEmptyString(value))
            {
              return true;
            }
            return pattern.matcher(value)
                .matches();
          }
        }, new Function1<String, String>() {

          @Override
          public String apply(final String value)
          {
            return "[what: all whitespace, value: '" + value + "']";
          }})
        .build();
    /* @formatter:on */

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorWithMappingAll()
  {
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      stringBuilder.append("{value:\"" + each + "\"}")
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<String> builder = ToStringGlues.<String> builderForIterable()
        .withSeparator(", ");
    /* @formatter:off */
    final IterableToStringGlue<String> stringGlue = builder
        .mapIfNotMappedWithAny(new Function1<String, String>() {

          @Override
          public String apply(final String value)
          {
            return "{value:\"" + value + "\"}";
          }})
      .build();
    /* @formatter:on */

    /* when */
    final String actual = stringGlue.glue(STRING_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      stringBuilder.append(each);
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithoutSeparator()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithoutSeparator()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      stringBuilder.append(each);
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingArray()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingArray()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingTwoArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingTwoArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 2))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingThreeArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingThreeArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 3))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingFourArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingFourArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 4))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2], STRINGS[3]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingFiveArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingFiveArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 5))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2], STRINGS[3], STRINGS[4]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingManyArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingManyArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, STRING_LIST.size()))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual =
      stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2], STRINGS[3], STRINGS[4],
          STRING_LIST.subList(5, STRING_LIST.size())
              .toArray(new String[STRING_LIST.size() - 5]));
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingArray()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingArray()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingTwoArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingTwoArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 2))
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingThreeArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingThreeArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 3))
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingFourArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingFourArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 4))
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2], STRINGS[3]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingFiveArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingFiveArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 5))
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2], STRINGS[3], STRINGS[4]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingManyArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingManyArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, STRING_LIST.size()))
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual =
      stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2], STRINGS[3], STRINGS[4],
          STRING_LIST.subList(5, STRING_LIST.size())
              .toArray(new String[STRING_LIST.size() - 5]));
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyGluingArray()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingArray()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null != each && !"".equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyIgnoreConditionallyGluingArray()
  {
    System.out.println("ToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyIgnoreConditionallyGluingArray()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST)
    {
      if (null != each && !"".equals(each))
      {
        if (!each.contains("blah"))
        {
          boolean allSpaceChars = true;
          for (final char c : each.toCharArray())
          {
            if (' ' != c)
            {
              allSpaceChars = false;
              break;
            }
          }
          if (!allSpaceChars)
          {
            stringBuilder.append(each)
                .append(", ");
          }
        }
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .ignoreIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return value.contains("blah");
          }
        })
        .ignoreIf(new Condition1<String>() {
          Pattern pattern = Pattern.compile("[ ]+");

          @Override
          public boolean isMet(final String value)
          {
            return pattern.matcher(value)
                .matches();
          }
        })
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyGluingTwoArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingTwoArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 2))
    {
      if (null != each && !"".equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignore("")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyIgnoreConditionallyGluingTwoArgs()
  {
    System.out.println("ToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyIgnoreConditionallyGluingTwoArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 2))
    {
      if (null != each && !"".equals(each) && !"Lee".equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignore("")
        .ignoreNull()
        .ignoreIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return "Lee".equals(value);
          }
        })
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyGluingThreeArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingThreeArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 3))
    {
      if (null != each && !"".equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyIgnoreConditionallyGluingThreeArgs()
  {
    System.out.println("ToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyIgnoreConditionallyGluingThreeArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 3))
    {
      if (null != each && !"".equals(each) && !"Lee".equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .ignoreIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return "Lee".equals(value);
          }
        })
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyGluingFourArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingFourArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 4))
    {
      if (null != each && !"".equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2], STRINGS[3]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyIgnoreConditionallyGluingFourArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingFourArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 4))
    {
      if (null != each && !"".equals(each) && !"Lee".equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .ignoreIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return "Lee".equals(value);
          }
        })
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2], STRINGS[3]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyGluingFiveArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingFiveArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 5))
    {
      if (null != each && !"".equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2], STRINGS[3], STRINGS[4]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyIgnoreConditionallyGluingFiveArgs()
  {
    System.out.println("ToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyIgnoreConditionallyGluingFiveArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, 5))
    {
      if (null != each && !"".equals(each) && !"blah".equalsIgnoreCase(each))
      {
        boolean allSpaceChars = true;
        for (final char c : each.toCharArray())
        {
          if (' ' != c)
          {
            allSpaceChars = false;
            break;
          }
        }
        if (!allSpaceChars)
        {
          stringBuilder.append(each)
              .append(", ");
        }
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .ignoreIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return "blah".equalsIgnoreCase(value);
          }
        })
        .ignoreIf(new Condition1<String>() {
          Pattern pattern = Pattern.compile("[ ]+");

          @Override
          public boolean isMet(final String value)
          {
            return pattern.matcher(value)
                .matches();
          }
        })
        .build();

    /* when */
    final String actual = stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2], STRINGS[3], STRINGS[4]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyGluingManyArgs()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingManyArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, STRING_LIST.size()))
    {
      if (null != each && !"".equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .build();

    /* when */
    final String actual =
      stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2], STRINGS[3], STRINGS[4],
          STRING_LIST.subList(5, STRING_LIST.size())
              .toArray(new String[STRING_LIST.size() - 5]));
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyIgnoreConditionallyGluingManyArgs()
  {
    System.out.println("ToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyIgnoreConditionallyGluingManyArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, STRING_LIST.size()))
    {
      if (null != each && !"".equals(each) && !each.contains("blah"))
      {
        boolean allSpaceChars = true;
        for (final char c : each.toCharArray())
        {
          if (' ' != c)
          {
            allSpaceChars = false;
            break;
          }
        }
        if (!allSpaceChars)
        {
          stringBuilder.append(each)
              .append(", ");
        }
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .ignoreIf(new Condition1<String>() {
          Pattern pattern = Pattern.compile("blah");

          @Override
          public boolean isMet(final String value)
          {
            return pattern.matcher(value)
                .find();
          }
        })
        .ignoreIf(new Condition1<String>() {
          Pattern pattern = Pattern.compile("[ ]+");

          @Override
          public boolean isMet(final String value)
          {
            return pattern.matcher(value)
                .matches();
          }
        })
        .build();

    /* when */
    final String actual =
      stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2], STRINGS[3], STRINGS[4],
          STRING_LIST.subList(5, STRING_LIST.size())
              .toArray(new String[STRING_LIST.size() - 5]));
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyIgnoreConditionallyReplacingConditionallyMappingConditionallyGluingManyArgs()
  {
    System.out.println("ToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyIgnoreConditionallyReplacingConditionallyMappingConditionallyGluingManyArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final String each : STRING_LIST.subList(0, STRING_LIST.size()))
    {
      if (null != each && !"".equals(each))
      {
        if (each.equals("blah"))
        {
          stringBuilder.append("BLAH")
              .append(", ");
          continue;
        }

        if ("blah blah".equals(each))
        {
          stringBuilder.append("[[")
              .append(each)
              .append("]]")
              .append(", ");
          continue;
        }
        if (", ".equals(each))
        {
          stringBuilder.append("','")
              .append(", ");
          continue;
        }

        if (each.startsWith("#"))
        {
          final String stringFromIndex1 = each.substring(1);
          final char[] charArray = stringFromIndex1.toCharArray();
          boolean isNumber = 0 < charArray.length;
          for (final char c : charArray)
          {
            if (!('0' <= c && '9' >= c))
            {
              isNumber = false;
              break;
            }
          }
          if (isNumber)
          {
            stringBuilder.append(stringFromIndex1)
                .append(", ");
            continue;
          }
        }
        if ("Not Kevin".equals(each))
        {
          stringBuilder.append("Kevin")
              .append(", ");
          continue;
        }

        boolean allSpaceChars = true;
        for (final char c : each.toCharArray())
        {
          if (' ' != c)
          {
            allSpaceChars = false;
            break;
          }
        }
        if (!allSpaceChars)
        {
          stringBuilder.append("{value:\"")
              .append(each)
              .append("\"}")
              .append(", ");
        }
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<String> stringGlue = ToStringGlues.<String> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore("")
        .ignoreIf(new Condition1<String>() {
          Pattern pattern = Pattern.compile("[ ]+");

          @Override
          public boolean isMet(final String value)
          {
            return pattern.matcher(value)
                .matches();
          }
        })
        .replace("blah", "BLAH")
        .replaceIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return ", ".equals(value);
          }
        }, "','")
        .mapWith("blah blah", new Function1<String, String>() {

          @Override
          public String apply(final String value)
          {
            return "[[" + value + "]]";
          }
        })
        .mapIf(new Condition1<String>() {
          Pattern pattern = Pattern.compile("^#[\\d]+");

          @Override
          public boolean isMet(final String value)
          {
            return pattern.matcher(value)
                .matches();
          }
        }, new Function1<String, String>() {

          @Override
          public String apply(final String value)
          {
            return value.substring(1);
          }
        })
        .mapIf(new Condition1<String>() {
          @Override
          public boolean isMet(final String value)
          {
            return "Not Kevin".equals(value);
          }
        }, new Function1<String, String>() {
          @Override
          public String apply(final String value)
          {
            return value.substring("Not ".length());
          }
        })
        .mapIfNotMappedWithAny(new Function1<String, String>() {
          @Override
          public String apply(final String value)
          {
            return "{value:\"" + value + "\"}";
          }
        })
        .build();

    /* when */
    final String actual =
      stringGlue.glue(STRINGS[0], STRINGS[1], STRINGS[2], STRINGS[3], STRINGS[4],
          STRING_LIST.subList(5, STRING_LIST.size())
              .toArray(new String[STRING_LIST.size() - 5]));
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  /*
   * #################################################################################################################
   * Person
   * #################################################################################################################
   */

  @Test
  public final void testBuilderForIterable2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForIterable2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      stringBuilder.append(each);
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparator2()
  {
    System.out.println("\nToStringGluesTest.testIterableToStringGlueWithoutSeparator2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      stringBuilder.append(each);
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNull2()
  {
    /*
     * without separator and ignore null.
     */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (null != each)
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreEmptyObject()
  {
    final Person person = new Person("", "");

    /*
     * without separator and ignore null.
     */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (!person.equals(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .ignore(person)
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNullAndEmptyObject()
  {
    final Person person = new Person("", "");
    /*
     * without separator, ignore null, ignore empty String ("").
     */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (null != each && !person.equals(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .ignoreNull()
        .ignore(person)
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNullEmptyObjectAndSomePeople()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and other String values.
     */
    final Person person = new Person("", "");
    final Person[] ignorePeople = { new Person("Lee", "Kevin"), new Person("Parker", "Peter") };
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(asList(ignorePeople)));
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (null != each && !person.equals(each) && !ignoreSet.contains(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .ignoreNull()
        .ignore(person)
        .ignore(ignorePeople[0])
        .ignore(ignorePeople[1])
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNullEmptyObjectAndSomePeopleReplacePeople()
  {
    /*
     * without separator, ignore null, ignore empty names new Person("", "") and other String values.
     */
    final Person person = new Person("", "");
    final Person[] ignorePeople = { new Person("Parker", "Peter"), new Person("Banner", "Bruce") };
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(asList(ignorePeople)));

    final Person[][] forReplacement =
      { { new Person("Lee", "Kevin"), new Person("Clark", "Kent") },
          { new Person("Lee", "Seong"), new Person("", "Superman") } };
    final Map<Person, Person> tmpMap = new HashMap<Person, Person>();
    tmpMap.put(forReplacement[0][0], forReplacement[0][1]);
    tmpMap.put(forReplacement[1][0], forReplacement[1][1]);
    final Map<Person, Person> replaceMap = unmodifiableMap(tmpMap);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (null != each && !person.equals(each) && !ignoreSet.contains(each))
      {
        final Person newValue = replaceMap.get(each);
        stringBuilder.append(null == newValue ? each : newValue);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .ignoreNull()
        .ignore(person)
        .ignore(ignorePeople[0])
        .ignore(ignorePeople[1])
        .replace(forReplacement[0][0], forReplacement[0][1])
        .replace(forReplacement[1][0], forReplacement[1][1])
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNullEmptyObjectAndSomePeopleReplacePeople2()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and other String values.
     */
    final Person person = new Person("", "");
    final Person[] ignorePeople =
      { new Person("Parker", "Peter"), new Person("Banner", "Bruce"), new Person("Wayne", "Bruce") };
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(asList(ignorePeople)));

    final Person[][] forReplacement =
      { { new Person("Lee", "Kevin"), new Person("Clark", "Kent") },
          { new Person("Lee", "Seong"), new Person("", "Superman") } };
    final Map<Person, Person> tmpMap = new HashMap<Person, Person>();
    tmpMap.put(forReplacement[0][0], forReplacement[0][1]);
    tmpMap.put(forReplacement[1][0], forReplacement[1][1]);
    final Map<Person, Person> replaceMap = unmodifiableMap(tmpMap);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (null != each && !person.equals(each) && !ignoreSet.contains(each))
      {
        final Person newValue = replaceMap.get(each);
        stringBuilder.append(null == newValue ? each : newValue);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .ignoreNull()
        .ignore(person)
        .ignore(ignorePeople[0])
        .ignore(ignorePeople[1])
        .ignore(ignorePeople[2])
        .replace(forReplacement[0][0], forReplacement[0][1])
        .replace(forReplacement[1][0], forReplacement[1][1])
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNullEmptyObjectReplacePersonIgnoreConditionallyReplaceConditionallyMapConditionally()
  {
    final Person[] ignorePeople = { new Person("Parker", "Peter"), new Person("Banner", "Bruce") };
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(asList(ignorePeople)));

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (null != each && !ignoreSet.contains(each)
          && !(isNullOrEmptyString(each.getSurname()) && isNullOrEmptyString(each.getGivenName())))
      {
        if (new Person("Wayne", "Bruce").equals(each))
        {
          stringBuilder.append(new Person("Kent", "Clark"));
          continue;
        }
        if (isNullOrEmptyString(each.getGivenName()) && isNeitherNullNorEmptyString(each.getSurname()))
        {
          stringBuilder.append(new Person("Someone", "Without Given Name"));
          continue;
        }
        if (new Person("Jordan", "Hal").equals(each))
        {
          stringBuilder.append(new Person("Green Lantern", each.getGivenName() + " is"));
          continue;
        }
        if (new Person("Person", "Unknown").equals(each))
        {
          stringBuilder.append(new Person(each.getSurname(), "Who Am I?"));
          continue;
        }
        stringBuilder.append(new Person(each.getSurname(), each.getGivenName()) {
          @Override
          public String toString()
          {
            /* @formatter:off */
                return toStringBuilder(this)
                    .add("givenName", getGivenName())
                    .add("surname", getSurname())
                    .toString();
                /* @formatter:on */
          }
        });
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .ignoreNull()
        .ignore(ignorePeople[0])
        .ignore(ignorePeople[1])
        .ignoreIf(new Condition1<Person>() {
          @Override
          public boolean isMet(final Person person)
          {
            return isNullOrEmptyString(person.getSurname()) && isNullOrEmptyString(person.getGivenName());
          }
        })
        .replace(new Person("Wayne", "Bruce"), new Person("Kent", "Clark"))
        .replaceIf(new Condition1<Person>() {
          @Override
          public boolean isMet(final Person person)
          {
            return isNullOrEmptyString(person.getGivenName()) && isNeitherNullNorEmptyString(person.getSurname());
          }
        }, new Person("Someone", "Without Given Name"))
        .mapWith(new Person("Jordan", "Hal"), new Function1<Person, Person>() {

          @Override
          public Person apply(final Person person)
          {
            return new Person("Green Lantern", person.getGivenName() + " is");
          }
        })
        .mapIf(new Condition1<Person>() {

          @Override
          public boolean isMet(final Person person)
          {
            return new Person("Person", "Unknown").equals(person);
          }
        }, new Function1<Person, Person>() {

          @Override
          public Person apply(final Person person)
          {
            return new Person(person.getSurname(), "Who Am I?");
          }
        })
        .mapIfNotMappedWithAny(new Function1<Person, Person>() {

          @Override
          public Person apply(final Person person)
          {
            return new Person(person.getSurname(), person.getGivenName()) {
              @Override
              public String toString()
              {
                /* @formatter:off */
                return toStringBuilder(this)
                    .add("givenName", getGivenName())
                    .add("surname", getSurname())
                    .toString();
                /* @formatter:on */
              }
            };
          }
        })
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreAll2()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and other String values.
     */
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(PERSON_LIST));

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<Person> builder = builderForIterable();
    for (final Person each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<Person> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreAllButFirst2()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and all the String values in the STRING_LIST but the
     * first one.
     */
    final Set<Person> tmpSet = new HashSet<Person>(PERSON_LIST);
    tmpSet.remove(PERSON_LIST.get(0));
    final Set<Person> ignoreSet = unmodifiableSet(tmpSet);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<Person> builder = builderForIterable();
    for (final Person each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<Person> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreAllButLast2()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and all the String values in the STRING_LIST but the
     * last one.
     */
    final Set<Person> tmpSet = new HashSet<Person>(PERSON_LIST);
    tmpSet.remove(PERSON_LIST.get(PERSON_LIST.size() - 1));
    final Set<Person> ignoreSet = unmodifiableSet(tmpSet);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<Person> builder = builderForIterable();
    for (final Person each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<Person> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparator2()
  {
    /* with separator (", ") */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      stringBuilder.append(each)
          .append(", ");
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNull2()
  {
    /*
     * with separator (", ") and ignore null.
     */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreEmptyObject()
  {
    /*
     * with separator (", ") and ignore null.
     */
    final Person person = new Person("", "");
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (!person.equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ")
        .ignore(person)
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNullAndEmptyObject()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("").
     */
    final Person person = new Person("", "");
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (null != each && !person.equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(person)
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNullEmptyObjectAndSomePeople()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("") and other String values.
     */
    final Person empty = new Person("", "");
    final Person[] ignorePeople = { new Person("Lee", "Kevin"), new Person("Kent", "Clark") };
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(asList(ignorePeople)));
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (null != each && !empty.equals(each) && !ignoreSet.contains(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .ignore(ignorePeople[0])
        .ignore(ignorePeople[1])
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNullEmptyObjectAndSomePeopleReplacePeople()
  {
    /*
     * with separator (", "), ignore null, empty String (""), ", " and other String values.
     */
    final Person empty = new Person("", "");
    final Person[] ignorePeople = { new Person("Parker", "Peter"), new Person("Banner", "Bruce") };
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(asList(ignorePeople)));

    final Person[][] forReplacement =
      { { new Person("Lee", "Kevin"), new Person("Clark", "Kent") },
          { new Person("Lee", "Seong"), new Person("", "Superman") } };
    final Map<Person, Person> tmpMap = new HashMap<Person, Person>();
    tmpMap.put(forReplacement[0][0], forReplacement[0][1]);
    tmpMap.put(forReplacement[1][0], forReplacement[1][1]);
    final Map<Person, Person> replaceMap = unmodifiableMap(tmpMap);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (null != each && !empty.equals(each) && !ignoreSet.contains(each))
      {
        final Person newValue = replaceMap.get(each);
        stringBuilder.append(null == newValue ? each : newValue)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .ignore(ignorePeople[0])
        .ignore(ignorePeople[1])
        .replace(forReplacement[0][0], forReplacement[0][1])
        .replace(forReplacement[1][0], forReplacement[1][1])
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNullEmptyObjectAndSomePeopleReplacePeople2()
  {
    /*
     * with separator (", "), ignore null, empty String (""), ", ", one space (" "), and other String values.
     */
    final Person empty = new Person("", "");
    final Person[] ignorePeople =
      { new Person("Parker", "Peter"), new Person("Banner", "Bruce"), new Person("Wayne", "Bruce") };
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(asList(ignorePeople)));

    final Person[][] forReplacement =
      { { new Person("Lee", "Kevin"), new Person("Clark", "Kent") },
          { new Person("Lee", "Seong"), new Person("", "Superman") } };
    final Map<Person, Person> tmpMap = new HashMap<Person, Person>();
    tmpMap.put(forReplacement[0][0], forReplacement[0][1]);
    tmpMap.put(forReplacement[1][0], forReplacement[1][1]);
    final Map<Person, Person> replaceMap = unmodifiableMap(tmpMap);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (null != each && !empty.equals(each) && !" ".equals(each) && !ignoreSet.contains(each))
      {
        final Person newValue = replaceMap.get(each);
        stringBuilder.append(null == newValue ? each : newValue)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .ignore(ignorePeople[0])
        .ignore(ignorePeople[1])
        .ignore(ignorePeople[2])
        .replace(forReplacement[0][0], forReplacement[0][1])
        .replace(forReplacement[1][0], forReplacement[1][1])
        .build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreAll2()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("") and other String values.
     */
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(PERSON_LIST));

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<Person> builder = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ");
    for (final Person each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<Person> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreAllButFirst2()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("") and all the String values in the STRING_LIST but the
     * first one.
     */
    final Set<Person> tmpSet = new HashSet<Person>(PERSON_LIST);
    tmpSet.remove(PERSON_LIST.get(0));
    final Set<Person> ignoreSet = unmodifiableSet(tmpSet);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<Person> builder = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ");
    for (final Person each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<Person> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreAllButLast2()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("") and all the String values in the STRING_LIST but the
     * last one.
     */
    final Set<Person> tmpSet = new HashSet<Person>(PERSON_LIST);
    tmpSet.remove(STRING_LIST.get(STRING_LIST.size() - 1));
    final Set<Person> ignoreSet = unmodifiableSet(tmpSet);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<Person> builder = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ");
    for (final Person each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<Person> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(PERSON_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgs2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      stringBuilder.append(each);
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithoutSeparator2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithoutSeparator2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      stringBuilder.append(each);
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingArray2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingArray2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingTwoArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingTwoArgs2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 2))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE[0], PEOPLE[1]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingThreeArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingThreeArgs()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 3))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingFourArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingFourArgs2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 4))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingFiveArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingFiveArgs2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 5))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], PEOPLE[4]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingManyArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingManyArgs2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, PERSON_LIST.size()))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual =
      stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], PEOPLE[4], PERSON_LIST.subList(5, PERSON_LIST.size())
          .toArray(new Person[PERSON_LIST.size() - 5]));
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingArray2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullGluingArray2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingTwoArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullGluingTwoArgs2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 2))
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE[0], PEOPLE[1]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingThreeArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullGluingThreeArgs2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 3))
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingFourArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullGluingFourArgs2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 4))
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingFiveArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullGluingFiveArgs2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 5))
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], PEOPLE[4]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingManyArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullGluingManyArgs2()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, PERSON_LIST.size()))
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual =
      stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], PEOPLE[4], PERSON_LIST.subList(5, PERSON_LIST.size())
          .toArray(new Person[PERSON_LIST.size() - 5]));
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingArray2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyGluingArray2()");

    /* without separator */
    final Person empty = new Person("", "");
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST)
    {
      if (null != each && !empty.equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingTwoArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyGluingTwoArgs2()");

    /* without separator */
    final Person empty = new Person("", "");
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 2))
    {
      if (null != each && !empty.equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE[0], PEOPLE[1]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingThreeArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyGluingThreeArgs2()");

    /* without separator */
    final Person empty = new Person("", "");
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 3))
    {
      if (null != each && !empty.equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingFourArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyGluingFourArgs2()");

    /* without separator */
    final Person empty = new Person("", "");
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 4))
    {
      if (null != each && !empty.equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingFiveArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyGluingFiveArgs2()");

    /* without separator */
    final Person empty = new Person("", "");
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 5))
    {
      if (null != each && !empty.equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .build();

    /* when */
    final String actual = stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], PEOPLE[4]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingManyArgs2()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyGluingManyArgs2()");

    /* without separator */
    final Person empty = new Person("", "");
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, PERSON_LIST.size()))
    {
      if (null != each && !empty.equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .build();

    /* when */
    final String actual =
      stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], PEOPLE[4], PERSON_LIST.subList(5, PERSON_LIST.size())
          .toArray(new Person[PERSON_LIST.size() - 5]));
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  /*
   * #################################################################################################################
   * Member
   * #################################################################################################################
   */

  private static class Member extends Person
  {
    private final Long memberId;

    public Member(final String surname, final String givenName, final Long memberId)
    {
      super(surname, givenName);
      this.memberId = memberId;
    }

    @SuppressWarnings("unused")
    public Long getMemberId()
    {
      return memberId;
    }

    /**
     * @return the hashCode of the super object.
     */
    @Override
    public int hashCode()
    {
      return super.hashCode();
    }

    /**
     * Checks if the given object is equal to this object.
     * 
     * @param member
     *          the given Object to be compared.
     * @return true if the surname and givenName of this object are the same as the given object's.
     */
    @Override
    public boolean equals(final Object member)
    {
      if (identical(this, member))
        return true;
      final Member that = (Member) member;
      return isNotNull(that) && super.equals(that);
    }

    @Override
    public String toString()
    {
      return toStringBuilder(this).add("name", super.toString())
          .add("memberId", memberId)
          .toString();
    }
  }

  @SuppressWarnings("boxing")
  private static final Member[] MEMBERS = new Member[] { new Member("Lee", "Kevin", 1L),
      new Member("Kent", "Clark", 2L), new Member("", "", 0L), null, new Member("", "", 0L),
      new Member("Wayne", "Bruce", 3L), null, new Member("Parker", "Peter", 4L), new Member("", "", 0L),
      new Member("Jordan", "Hal", 5L), new Member("Banner", "Bruce", 6L) };

  private static final List<Member> MEMBER_LIST = asList(MEMBERS);

  @Test
  public final void testBuilderForIterable3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForIterable3()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      stringBuilder.append(each);
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparator3()
  {
    System.out.println("\nToStringGluesTest.testIterableToStringGlueWithoutSeparator3()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      stringBuilder.append(each);
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNull3()
  {
    /*
     * without separator and ignore null.
     */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (null != each)
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreEmptyObject2()
  {
    @SuppressWarnings("boxing")
    final Member empty = new Member("", "", 0L);

    /*
     * without separator and ignore null.
     */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (!empty.equals(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .ignore(empty)
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNullAndEmptyObject2()
  {
    @SuppressWarnings("boxing")
    final Member empty = new Member("", "", 0L);
    /*
     * without separator, ignore null, ignore empty String ("").
     */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (null != each && !empty.equals(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .ignoreNull()
        .ignore(empty)
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNullEmptyObjectAndSomeMembers()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and other String values.
     */
    @SuppressWarnings("boxing")
    final Person empty = new Member("", "", 0L);
    final Person[] ignoreThese = { new Member("Lee", "Kevin", null), new Member("Parker", "Peter", null) };
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(asList(ignoreThese)));
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (null != each && !empty.equals(each) && !ignoreSet.contains(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .ignoreNull()
        .ignore(empty)
        .ignore(ignoreThese[0])
        .ignore(ignoreThese[1])
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNullEmptyObjectAndSomeMembersReplaceMembers()
  {
    /*
     * without separator, ignore null, ignore empty names new Person("", "") and other String values.
     */
    @SuppressWarnings("boxing")
    final Person empty = new Member("", "", 0L);
    final Person[] ignoreThese = { new Member("Parker", "Peter", null), new Member("Banner", "Bruce", null) };
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(asList(ignoreThese)));

    @SuppressWarnings("boxing")
    final Person[][] forReplacement =
      { { new Member("Lee", "Kevin", null), new Member("Clark", "Kent", null) },
          { new Member("Lee", "Seong", 100L), new Member("", "Superman", 1000L) } };
    final Map<Person, Person> tmpMap = new HashMap<Person, Person>();
    tmpMap.put(forReplacement[0][0], forReplacement[0][1]);
    tmpMap.put(forReplacement[1][0], forReplacement[1][1]);
    final Map<Person, Person> replaceMap = unmodifiableMap(tmpMap);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (null != each && !empty.equals(each) && !ignoreSet.contains(each))
      {
        final Person newValue = replaceMap.get(each);
        stringBuilder.append(null == newValue ? each : newValue);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .ignoreNull()
        .ignore(empty)
        .ignore(ignoreThese[0])
        .ignore(ignoreThese[1])
        .replace(forReplacement[0][0], forReplacement[0][1])
        .replace(forReplacement[1][0], forReplacement[1][1])
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreNullEmptyObjectAndSomeMembersReplaceMembers2()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and other String values.
     */
    @SuppressWarnings("boxing")
    final Person empty = new Member("", "", 0L);
    final Person[] ignoreThese =
      { new Member("Parker", "Peter", null), new Member("Banner", "Bruce", null), new Member("Wayne", "Bruce", null) };
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(asList(ignoreThese)));

    @SuppressWarnings("boxing")
    final Person[][] forReplacement =
      { { new Member("Lee", "Kevin", null), new Member("Clark", "Kent", null) },
          { new Member("Lee", "Seong", 100L), new Member("", "Superman", 1000L) } };
    final Map<Person, Person> tmpMap = new HashMap<Person, Person>();
    tmpMap.put(forReplacement[0][0], forReplacement[0][1]);
    tmpMap.put(forReplacement[1][0], forReplacement[1][1]);
    final Map<Person, Person> replaceMap = unmodifiableMap(tmpMap);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (null != each && !empty.equals(each) && !ignoreSet.contains(each))
      {
        final Person newValue = replaceMap.get(each);
        stringBuilder.append(null == newValue ? each : newValue);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .ignoreNull()
        .ignore(empty)
        .ignore(ignoreThese[0])
        .ignore(ignoreThese[1])
        .ignore(ignoreThese[2])
        .replace(forReplacement[0][0], forReplacement[0][1])
        .replace(forReplacement[1][0], forReplacement[1][1])
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreAll3()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and other String values.
     */
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(MEMBER_LIST));

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<Person> builder = builderForIterable();
    for (final Person each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<Person> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreAllButFirst3()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and all the String values in the STRING_LIST but the
     * first one.
     */
    final Set<Person> tmpSet = new HashSet<Person>(MEMBER_LIST);
    tmpSet.remove(MEMBER_LIST.get(0));
    final Set<Person> ignoreSet = unmodifiableSet(tmpSet);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<Person> builder = builderForIterable();
    for (final Person each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<Person> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithoutSeparatorIgnoreAllButLast3()
  {
    /*
     * without separator, ignore null, ignore empty String ("") and all the String values in the STRING_LIST but the
     * last one.
     */
    final Set<Person> tmpSet = new HashSet<Person>(MEMBER_LIST);
    tmpSet.remove(MEMBER_LIST.get(MEMBER_LIST.size() - 1));
    final Set<Person> ignoreSet = unmodifiableSet(tmpSet);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each);
      }
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<Person> builder = builderForIterable();
    for (final Person each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<Person> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparator3()
  {
    /* with separator (", ") */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      stringBuilder.append(each)
          .append(", ");
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNull3()
  {
    /*
     * with separator (", ") and ignore null.
     */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreEmptyObject2()
  {
    /*
     * with separator (", ") and ignore null.
     */
    final Person empty = new Member("", "", null);
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (!empty.equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ")
        .ignore(empty)
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNullAndEmptyObject2()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("").
     */
    final Person empty = new Member("", "", null);
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (null != each && !empty.equals(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNullEmptyObjectAndSomeMembers()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("") and other String values.
     */
    @SuppressWarnings("boxing")
    final Person empty = new Member("", "", 0L);
    final Person[] ignoreThese = { new Member("Lee", "Kevin", null), new Member("Kent", "Clark", null) };
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(asList(ignoreThese)));
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (null != each && !empty.equals(each) && !ignoreSet.contains(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .ignore(ignoreThese[0])
        .ignore(ignoreThese[1])
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNullEmptyObjectAndSomeMembersReplaceMembers()
  {
    /*
     * with separator (", "), ignore null, empty String (""), ", " and other String values.
     */
    @SuppressWarnings("boxing")
    final Person empty = new Member("", "", 0L);
    final Person[] ignoreThese = { new Member("Parker", "Peter", null), new Member("Banner", "Bruce", null) };
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(asList(ignoreThese)));

    @SuppressWarnings("boxing")
    final Person[][] forReplacement =
      { { new Member("Lee", "Kevin", null), new Member("Clark", "Kent", null) },
          { new Member("Lee", "Seong", 100L), new Member("", "Superman", 1000L) } };
    final Map<Person, Person> tmpMap = new HashMap<Person, Person>();
    tmpMap.put(forReplacement[0][0], forReplacement[0][1]);
    tmpMap.put(forReplacement[1][0], forReplacement[1][1]);
    final Map<Person, Person> replaceMap = unmodifiableMap(tmpMap);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (null != each && !empty.equals(each) && !ignoreSet.contains(each))
      {
        final Person newValue = replaceMap.get(each);
        stringBuilder.append(null == newValue ? each : newValue)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .ignore(ignoreThese[0])
        .ignore(ignoreThese[1])
        .replace(forReplacement[0][0], forReplacement[0][1])
        .replace(forReplacement[1][0], forReplacement[1][1])
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreNullEmptyObjectAndSomeMembersReplaceMembers2()
  {
    /*
     * with separator (", "), ignore null, empty String (""), ", ", one space (" "), and other String values.
     */
    @SuppressWarnings("boxing")
    final Person empty = new Member("", "", 0L);
    final Person[] ignoreThese =
      { new Member("Parker", "Peter", null), new Member("Banner", "Bruce", null), new Member("Wayne", "Bruce", null) };
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(asList(ignoreThese)));

    @SuppressWarnings("boxing")
    final Person[][] forReplacement =
      { { new Member("Lee", "Kevin", null), new Member("Clark", "Kent", null) },
          { new Member("Lee", "Seong", 100L), new Member("", "Superman", 1000L) } };
    final Map<Person, Person> tmpMap = new HashMap<Person, Person>();
    tmpMap.put(forReplacement[0][0], forReplacement[0][1]);
    tmpMap.put(forReplacement[1][0], forReplacement[1][1]);
    final Map<Person, Person> replaceMap = unmodifiableMap(tmpMap);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (null != each && !empty.equals(each) && !" ".equals(each) && !ignoreSet.contains(each))
      {
        final Person newValue = replaceMap.get(each);
        stringBuilder.append(null == newValue ? each : newValue)
            .append(", ");
      }
    }
    stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final IterableToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .ignore(ignoreThese[0])
        .ignore(ignoreThese[1])
        .ignore(ignoreThese[2])
        .replace(forReplacement[0][0], forReplacement[0][1])
        .replace(forReplacement[1][0], forReplacement[1][1])
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreAll3()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("") and other String values.
     */
    final Set<Person> ignoreSet = unmodifiableSet(new HashSet<Person>(MEMBER_LIST));

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<Person> builder = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ");
    for (final Person each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<Person> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreAllButFirst3()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("") and all the String values in the STRING_LIST but the
     * first one.
     */
    final Set<Person> tmpSet = new HashSet<Person>(MEMBER_LIST);
    tmpSet.remove(MEMBER_LIST.get(0));
    final Set<Person> ignoreSet = unmodifiableSet(tmpSet);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<Person> builder = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ");
    for (final Person each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<Person> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIterableToStringGlueWithSeparatorIgnoreAllButLast3()
  {
    /*
     * with separator (", "), ignore null, ignore empty String ("") and all the String values in the STRING_LIST but the
     * last one.
     */
    final Set<Person> tmpSet = new HashSet<Person>(MEMBER_LIST);
    tmpSet.remove(STRING_LIST.get(STRING_LIST.size() - 1));
    final Set<Person> ignoreSet = unmodifiableSet(tmpSet);

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (!ignoreSet.contains(each))
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ForIterableBuilder<Person> builder = ToStringGlues.<Person> builderForIterable()
        .withSeparator(", ");
    for (final Person each : ignoreSet)
    {
      builder.ignore(each);
    }
    final IterableToStringGlue<Person> stringGlue = builder.build();

    /* when */
    final String actual = stringGlue.glue(MEMBER_LIST);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgs3()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      stringBuilder.append(each);
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithoutSeparator3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithoutSeparator3()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      stringBuilder.append(each);
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingArray3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingArray3()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingTwoArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingTwoArgs3()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST.subList(0, 2))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS[0], MEMBERS[1]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    /* without separator */
    final StringBuilder stringBuilder2 = new StringBuilder();

    stringBuilder2.append(PERSON_LIST.get(0))
        .append(", ")
        .append(MEMBER_LIST.get(1))
        .append(", ");
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 = stringGlue.glue(PEOPLE[0], MEMBERS[1]);
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingThreeArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingThreeArgs3()");

    /* without separator */
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST.subList(0, 3))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS[0], MEMBERS[1], MEMBERS[2]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    /* without separator */
    final StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(MEMBER_LIST.get(0))
        .append(", ")
        .append(PERSON_LIST.get(1))
        .append(", ")
        .append(MEMBER_LIST.get(2))
        .append(", ");
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 = stringGlue.glue(MEMBERS[0], PEOPLE[1], MEMBERS[2]);
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingFourArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingFourArgs3()");

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST.subList(0, 4))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS[0], MEMBERS[1], MEMBERS[2], MEMBERS[3]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    final StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(MEMBER_LIST.get(0))
        .append(", ")
        .append(MEMBER_LIST.get(1))
        .append(", ")
        .append(PERSON_LIST.get(2))
        .append(", ")
        .append(PERSON_LIST.get(3))
        .append(", ");
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 = stringGlue.glue(MEMBERS[0], MEMBERS[1], PEOPLE[2], PEOPLE[3]);
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingFiveArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingFiveArgs3()");

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST.subList(0, 5))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS[0], MEMBERS[1], MEMBERS[2], MEMBERS[3], MEMBERS[4]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    final StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(PERSON_LIST.get(0))
        .append(", ")
        .append(MEMBER_LIST.get(1))
        .append(", ")
        .append(MEMBER_LIST.get(2))
        .append(", ")
        .append(MEMBER_LIST.get(3))
        .append(", ")
        .append(PERSON_LIST.get(4))
        .append(", ");
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 = stringGlue.glue(PEOPLE[0], MEMBERS[1], MEMBERS[2], MEMBERS[3], PEOPLE[4]);
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorGluingManyArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorGluingManyArgs3()");

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST.subList(0, MEMBER_LIST.size()))
    {
      stringBuilder.append(each)
          .append(", ");
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .build();

    /* when */
    final String actual =
      stringGlue.glue(MEMBERS[0], MEMBERS[1], MEMBERS[2], MEMBERS[3], MEMBERS[4],
          MEMBER_LIST.subList(5, MEMBER_LIST.size())
              .toArray(new Member[MEMBER_LIST.size() - 5]));
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    /* */
    final StringBuilder stringBuilder2 = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 4))
    {
      stringBuilder2.append(each)
          .append(", ");
    }
    for (final Person each : MEMBER_LIST.subList(4, MEMBER_LIST.size()))
    {
      stringBuilder2.append(each)
          .append(", ");
    }
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 =
      stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], MEMBERS[4],
          MEMBER_LIST.subList(5, MEMBER_LIST.size())
              .toArray(new Member[MEMBER_LIST.size() - 5]));
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* */
    final StringBuilder stringBuilder3 = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 4))
    {
      stringBuilder3.append(each)
          .append(", ");
    }
    for (final Person each : MEMBER_LIST.subList(4, MEMBER_LIST.size()))
    {
      stringBuilder3.append(each)
          .append(", ");
    }
    for (final Person each : PERSON_LIST)
    {
      stringBuilder3.append(each)
          .append(", ");
    }
    if (0 < stringBuilder3.length())
    {
      stringBuilder3.delete(stringBuilder3.length() - ", ".length(), stringBuilder3.length());
    }
    final String expected3 = stringBuilder3.toString();
    System.out.println(format("expected:\n%s", expected3));

    /* when */
    final String actual3 =
      stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], MEMBERS[4], MEMBERS[5], MEMBERS[6], MEMBERS[7],
          MEMBERS[8], MEMBERS[9], MEMBERS[10], PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], PEOPLE[4], PEOPLE[5],
          PEOPLE[6], PEOPLE[7], PEOPLE[8], PEOPLE[9], PEOPLE[10], PEOPLE[11]);
    System.out.println(format("actual:\n%s\n", actual3));

    /* then */
    assertThat(actual3, is(equalTo(expected3)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingArray3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullGluingArray3()");

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      if (null != each)
      {
        stringBuilder.append(each)
            .append(", ");
      }
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  private static class AppendPersonIf
  {
    public <P extends Person> AppendPersonIf perform(final StringBuilder stringBuilder, final P person,
        final Condition1<? super Person> condition, final Condition1<? super Person>... conditions)
    {
      boolean isApplicable = condition.isMet(person);
      for (final Condition1<? super Person> each : conditions)
      {
        isApplicable &= each.isMet(person);
      }
      if (isApplicable)
      {
        stringBuilder.append(person)
            .append(", ");
      }
      return this;
    }
  }

  private static final AppendPersonIf APPEND_PERSON_WITH_SEPARATOR_IF = new AppendPersonIf();

  private static final Condition1<? super Person> NOT_NULL = new Condition1<Person>() {
    @Override
    public boolean isMet(final Person person)
    {
      return isNotNull(person);
    }
  };

  private static final Condition1<? super Person> NOT_EMPTY_PERSON = new Condition1<Person>() {
    private final Person person = new Person("", "");

    @Override
    public boolean isMet(final Person person)
    {
      return !equal(this.person, person);
    }
  };

  private static final Condition1<? super Person> NOT_EMPTY_MEMBER = new Condition1<Person>() {
    private final Member person = new Member("", "", null);

    @Override
    public boolean isMet(final Person person)
    {
      return !equal(this.person, person);
    }
  };

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingTwoArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullGluingTwoArgs3()");

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST.subList(0, 2))
    {
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder, each, NOT_NULL);
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS[0], MEMBERS[1]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    /* */
    final StringBuilder stringBuilder2 = new StringBuilder();
    APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder2, PERSON_LIST.get(0), NOT_NULL)
        .perform(stringBuilder2, MEMBER_LIST.get(1), NOT_NULL);
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 = stringGlue.glue(PEOPLE[0], MEMBERS[1]);
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingThreeArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullGluingThreeArgs3()");

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST.subList(0, 3))
    {
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder, each, NOT_NULL);
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS[0], MEMBERS[1], MEMBERS[2]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    /* */
    final StringBuilder stringBuilder2 = new StringBuilder();
    APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder2, PERSON_LIST.get(0), NOT_NULL)
        .perform(stringBuilder2, MEMBER_LIST.get(1), NOT_NULL)
        .perform(stringBuilder2, MEMBER_LIST.get(2), NOT_NULL);
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 = stringGlue.glue(PEOPLE[0], MEMBERS[1], MEMBERS[2]);
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingFourArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullGluingFourArgs3()");

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST.subList(0, 4))
    {
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder, each, NOT_NULL);
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS[0], MEMBERS[1], MEMBERS[2], MEMBERS[3]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    /* */
    final StringBuilder stringBuilder2 = new StringBuilder();
    APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder2, PERSON_LIST.get(0), NOT_NULL)
        .perform(stringBuilder2, MEMBER_LIST.get(1), NOT_NULL)
        .perform(stringBuilder2, PERSON_LIST.get(2), NOT_NULL)
        .perform(stringBuilder2, MEMBER_LIST.get(3), NOT_NULL);
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 = stringGlue.glue(PEOPLE[0], MEMBERS[1], PEOPLE[2], MEMBERS[3]);
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingFiveArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullGluingFiveArgs3()");

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST.subList(0, 5))
    {
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder, each, NOT_NULL);
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS[0], MEMBERS[1], MEMBERS[2], MEMBERS[3], MEMBERS[4]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    final StringBuilder stringBuilder2 = new StringBuilder();
    APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder2, PERSON_LIST.get(0), NOT_NULL)
        .perform(stringBuilder2, MEMBER_LIST.get(1), NOT_NULL)
        .perform(stringBuilder2, PERSON_LIST.get(2), NOT_NULL)
        .perform(stringBuilder2, PERSON_LIST.get(3), NOT_NULL)
        .perform(stringBuilder2, MEMBER_LIST.get(4), NOT_NULL);
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 = stringGlue.glue(PEOPLE[0], MEMBERS[1], PEOPLE[2], PEOPLE[3], MEMBERS[4]);
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullGluingManyArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullGluingManyArgs3()");

    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST.subList(0, MEMBER_LIST.size()))
    {
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder, each, NOT_NULL);
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .build();

    /* when */
    final String actual =
      stringGlue.glue(MEMBERS[0], MEMBERS[1], MEMBERS[2], MEMBERS[3], MEMBERS[4],
          MEMBER_LIST.subList(5, MEMBER_LIST.size())
              .toArray(new Member[MEMBER_LIST.size() - 5]));
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    /* */
    final StringBuilder stringBuilder2 = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 4))
    {
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder2, each, NOT_NULL);
    }
    for (final Person each : MEMBER_LIST.subList(4, MEMBER_LIST.size()))
    {
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder2, each, NOT_NULL);
    }
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 =
      stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], MEMBERS[4],
          MEMBER_LIST.subList(5, MEMBER_LIST.size())
              .toArray(new Member[MEMBER_LIST.size() - 5]));
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* */
    final StringBuilder stringBuilder3 = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 4))
    {
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder3, each, NOT_NULL);
    }
    for (final Person each : MEMBER_LIST.subList(4, MEMBER_LIST.size()))
    {
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder3, each, NOT_NULL);
    }
    for (final Person each : PERSON_LIST)
    {
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder3, each, NOT_NULL);
    }
    for (final Person each : MEMBER_LIST)
    {
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder3, each, NOT_NULL);
    }
    if (0 < stringBuilder3.length())
    {
      stringBuilder3.delete(stringBuilder3.length() - ", ".length(), stringBuilder3.length());
    }
    final String expected3 = stringBuilder3.toString();
    System.out.println(format("expected:\n%s", expected3));

    /* when */
    final String actual3 =
      stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], MEMBERS[4], MEMBERS[5], MEMBERS[6], MEMBERS[7],
          MEMBERS[8], MEMBERS[9], MEMBERS[10], PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], PEOPLE[4], PEOPLE[5],
          PEOPLE[6], PEOPLE[7], PEOPLE[8], PEOPLE[9], PEOPLE[10], PEOPLE[11], MEMBERS[0], MEMBERS[1], MEMBERS[2],
          MEMBERS[3], MEMBERS[4], MEMBERS[5], MEMBERS[6], MEMBERS[7], MEMBERS[8], MEMBERS[9], MEMBERS[10]);
    System.out.println(format("actual:\n%s\n", actual3));

    /* then */
    assertThat(actual3, is(equalTo(expected3)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingArray3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingArray3()");

    final Person empty = new Member("", "", null);
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST)
    {
      @SuppressWarnings({ "unchecked", "unused" })
      final AppendPersonIf appendPersonIf =
        APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder, each, NOT_NULL, NOT_EMPTY_MEMBER);
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingTwoArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingTwoArgs3()");

    final Person empty = new Member("", "", null);
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST.subList(0, 2))
    {
      @SuppressWarnings({ "unchecked", "unused" })
      final AppendPersonIf appendPersonIf =
        APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder, each, NOT_NULL, NOT_EMPTY_MEMBER);
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS[0], MEMBERS[1]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    /* */
    final StringBuilder stringBuilder2 = new StringBuilder();
    @SuppressWarnings({ "unchecked", "unused" })
    final AppendPersonIf appendPersonIf =
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder2, PERSON_LIST.get(0), NOT_NULL, NOT_EMPTY_PERSON)
          .perform(stringBuilder2, MEMBER_LIST.get(1), NOT_NULL, NOT_EMPTY_MEMBER);
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 = stringGlue.glue(PEOPLE[0], MEMBERS[1]);
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingThreeArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingThreeArgs3()");

    final Person empty = new Member("", "", null);
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST.subList(0, 3))
    {
      @SuppressWarnings({ "unchecked", "unused" })
      final AppendPersonIf appendPersonIf =
        APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder, each, NOT_NULL, NOT_EMPTY_MEMBER);
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS[0], MEMBERS[1], MEMBERS[2]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    /* */
    final StringBuilder stringBuilder2 = new StringBuilder();
    @SuppressWarnings({ "unchecked", "unused" })
    final AppendPersonIf appendPersonIf =
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder2, PERSON_LIST.get(0), NOT_NULL, NOT_EMPTY_PERSON)
          .perform(stringBuilder2, MEMBER_LIST.get(1), NOT_NULL, NOT_EMPTY_MEMBER)
          .perform(stringBuilder2, MEMBER_LIST.get(2), NOT_NULL, NOT_EMPTY_MEMBER);
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 = stringGlue.glue(PEOPLE[0], MEMBERS[1], MEMBERS[2]);
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingFourArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingFourArgs3()");

    final Person empty = new Member("", "", null);
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST.subList(0, 4))
    {
      @SuppressWarnings({ "unchecked", "unused" })
      final AppendPersonIf appendPersonIf =
        APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder, each, NOT_NULL, NOT_EMPTY_MEMBER);
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS[0], MEMBERS[1], MEMBERS[2], MEMBERS[3]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    /* */
    final StringBuilder stringBuilder2 = new StringBuilder();
    @SuppressWarnings({ "unchecked", "unused" })
    final AppendPersonIf appendPersonIf =
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder2, PERSON_LIST.get(0), NOT_NULL, NOT_EMPTY_PERSON)
          .perform(stringBuilder2, MEMBER_LIST.get(1), NOT_NULL, NOT_EMPTY_MEMBER)
          .perform(stringBuilder2, PERSON_LIST.get(2), NOT_NULL, NOT_EMPTY_PERSON)
          .perform(stringBuilder2, MEMBER_LIST.get(3), NOT_NULL, NOT_EMPTY_MEMBER);
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 = stringGlue.glue(PEOPLE[0], MEMBERS[1], PEOPLE[2], MEMBERS[3]);
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingFiveArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingFiveArgs3()");

    final Person empty = new Member("", "", null);
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Person each : MEMBER_LIST.subList(0, 5))
    {
      @SuppressWarnings({ "unchecked", "unused" })
      final AppendPersonIf appendPersonIf =
        APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder, each, NOT_NULL, NOT_EMPTY_MEMBER);
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .build();

    /* when */
    final String actual = stringGlue.glue(MEMBERS[0], MEMBERS[1], MEMBERS[2], MEMBERS[3], MEMBERS[4]);
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    /* */
    final StringBuilder stringBuilder2 = new StringBuilder();
    @SuppressWarnings({ "unchecked", "unused" })
    final AppendPersonIf appendPersonIf =
      APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder2, PERSON_LIST.get(0), NOT_NULL, NOT_EMPTY_PERSON)
          .perform(stringBuilder2, MEMBER_LIST.get(1), NOT_NULL, NOT_EMPTY_MEMBER)
          .perform(stringBuilder2, PERSON_LIST.get(2), NOT_NULL, NOT_EMPTY_PERSON)
          .perform(stringBuilder2, MEMBER_LIST.get(3), NOT_NULL, NOT_EMPTY_MEMBER)
          .perform(stringBuilder2, MEMBER_LIST.get(4), NOT_NULL, NOT_EMPTY_MEMBER);
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 = stringGlue.glue(PEOPLE[0], MEMBERS[1], PEOPLE[2], MEMBERS[3], MEMBERS[4]);
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));
  }

  @Test
  public final void testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingManyArgs3()
  {
    System.out.println("\nToStringGluesTest.testBuilderForArgsWithSeparatorIgnoreNullIgnoreEmptyObjectGluingManyArgs3()");

    final Person empty = new Member("", "", null);
    final StringBuilder stringBuilder = new StringBuilder();
    for (final Member each : MEMBER_LIST.subList(0, MEMBER_LIST.size()))
    {
      @SuppressWarnings({ "unchecked", "unused" })
      final AppendPersonIf appendPersonIf =
        APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder, each, NOT_NULL, NOT_EMPTY_MEMBER);
    }
    if (0 < stringBuilder.length())
    {
      stringBuilder.delete(stringBuilder.length() - ", ".length(), stringBuilder.length());
    }
    final String expected = stringBuilder.toString();
    System.out.println(format("expected:\n%s", expected));

    /* given */
    final ArgsToStringGlue<Person> stringGlue = ToStringGlues.<Person> builderForArgs()
        .withSeparator(", ")
        .ignoreNull()
        .ignore(empty)
        .build();

    /* when */
    final String actual =
      stringGlue.glue(MEMBERS[0], MEMBERS[1], MEMBERS[2], MEMBERS[3], MEMBERS[4],
          MEMBER_LIST.subList(5, MEMBER_LIST.size())
              .toArray(new Member[MEMBER_LIST.size() - 5]));
    System.out.println(format("actual:\n%s\n", actual));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    /* */
    final StringBuilder stringBuilder2 = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 4))
    {
      @SuppressWarnings({ "unchecked", "unused" })
      final AppendPersonIf appendPersonIf =
        APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder2, each, NOT_NULL, NOT_EMPTY_PERSON);
    }
    for (final Member each : MEMBER_LIST.subList(4, MEMBER_LIST.size()))
    {
      @SuppressWarnings({ "unchecked", "unused" })
      final AppendPersonIf appendPersonIf =
        APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder2, each, NOT_NULL, NOT_EMPTY_MEMBER);
    }
    if (0 < stringBuilder2.length())
    {
      stringBuilder2.delete(stringBuilder2.length() - ", ".length(), stringBuilder2.length());
    }
    final String expected2 = stringBuilder2.toString();
    System.out.println(format("expected:\n%s", expected2));

    /* when */
    final String actual2 =
      stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], MEMBERS[4],
          MEMBER_LIST.subList(5, MEMBER_LIST.size())
              .toArray(new Member[MEMBER_LIST.size() - 5]));
    System.out.println(format("actual:\n%s\n", actual2));

    /* then */
    assertThat(actual2, is(equalTo(expected2)));

    /* */
    final StringBuilder stringBuilder3 = new StringBuilder();
    for (final Person each : PERSON_LIST.subList(0, 4))
    {
      @SuppressWarnings({ "unchecked", "unused" })
      final AppendPersonIf appendPersonIf =
        APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder3, each, NOT_NULL, NOT_EMPTY_PERSON);
    }
    for (final Member each : MEMBER_LIST.subList(4, MEMBER_LIST.size()))
    {
      @SuppressWarnings({ "unchecked", "unused" })
      final AppendPersonIf appendPersonIf =
        APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder3, each, NOT_NULL, NOT_EMPTY_MEMBER);
    }
    for (final Person each : PERSON_LIST)
    {
      @SuppressWarnings({ "unchecked", "unused" })
      final AppendPersonIf appendPersonIf =
        APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder3, each, NOT_NULL, NOT_EMPTY_PERSON);
    }
    for (final Member each : MEMBER_LIST)
    {
      @SuppressWarnings({ "unchecked", "unused" })
      final AppendPersonIf appendPersonIf =
        APPEND_PERSON_WITH_SEPARATOR_IF.perform(stringBuilder3, each, NOT_NULL, NOT_EMPTY_MEMBER);
    }
    if (0 < stringBuilder3.length())
    {
      stringBuilder3.delete(stringBuilder3.length() - ", ".length(), stringBuilder3.length());
    }
    final String expected3 = stringBuilder3.toString();
    System.out.println(format("expected:\n%s", expected3));

    /* when */
    final String actual3 =
      stringGlue.glue(PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], MEMBERS[4], MEMBERS[5], MEMBERS[6], MEMBERS[7],
          MEMBERS[8], MEMBERS[9], MEMBERS[10], PEOPLE[0], PEOPLE[1], PEOPLE[2], PEOPLE[3], PEOPLE[4], PEOPLE[5],
          PEOPLE[6], PEOPLE[7], PEOPLE[8], PEOPLE[9], PEOPLE[10], PEOPLE[11], MEMBERS[0], MEMBERS[1], MEMBERS[2],
          MEMBERS[3], MEMBERS[4], MEMBERS[5], MEMBERS[6], MEMBERS[7], MEMBERS[8], MEMBERS[9], MEMBERS[10]);
    System.out.println(format("actual:\n%s\n", actual3));

    /* then */
    assertThat(actual3, is(equalTo(expected3)));
  }
}
