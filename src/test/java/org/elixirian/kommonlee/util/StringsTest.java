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

import static org.assertj.core.api.Assertions.*;
import static org.elixirian.kommonlee.test.CommonTestHelper.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.elixirian.kommonlee.test.CommonTestHelper.Accessibility;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-04-29)
 */
public class StringsTest
{
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

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
    assertThat(Strings.nullSafeTrim(null)).isEqualTo("");
    assertThat(Strings.nullSafeTrim("")).isEqualTo("");
    assertThat(Strings.nullSafeTrim(" ")).isEqualTo("");
    assertThat(Strings.nullSafeTrim("test")).isEqualTo("test");
    assertThat(Strings.nullSafeTrim(new String("something else"))).isEqualTo(new String("something else"));
    assertThat(Strings.nullSafeTrim("test ")).isEqualTo("test ".trim());
    assertThat(Strings.nullSafeTrim(" test")).isEqualTo(" test".trim());
    assertThat(Strings.nullSafeTrim("  test ")).isEqualTo("  test ".trim());
    assertThat(Strings.nullSafeTrim(" test  ")).isEqualTo(" test  ".trim());
    assertThat(Strings.nullSafeTrim("    te st   ")).isEqualTo("    te st   ".trim());
  }

  @Test
  public final void testNullThenEmpty()
  {
    assertThat(Strings.nullThenEmpty(null)).isEqualTo("");
    assertThat(Strings.nullThenEmpty("")).isEqualTo("");
    assertThat(Strings.nullThenEmpty(" ")).isEqualTo(" ");
    assertThat(Strings.nullThenEmpty("test")).isEqualTo("test");
    assertThat(Strings.nullThenEmpty(new String("something else"))).isEqualTo(new String("something else"));
  }

  @Test
  public final void testEmptyThenNull()
  {
    assertThat(Strings.emptyThenNull(null)).isEqualTo(null);
    assertThat(Strings.emptyThenNull("")).isEqualTo(null);
    assertThat(Strings.emptyThenNull(" ")).isEqualTo(" ");
    assertThat(Strings.emptyThenNull("test")).isEqualTo("test");
    assertThat(Strings.emptyThenNull(new String("something else"))).isEqualTo(new String("something else"));
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

    assertThat(Boolean.valueOf(Strings.isNullOrEmptyString(nullReference))).isEqualTo(true);
    assertThat(Boolean.valueOf(Strings.isNullOrEmptyString(emptyString))).isEqualTo(true);
    assertThat(Boolean.valueOf(Strings.isNullOrEmptyString(notEmpty))).isEqualTo(false);
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

    assertThat(Boolean.valueOf(Strings.isNeitherNullNorEmptyString(notEmpty))).isEqualTo(true);
    assertThat(Boolean.valueOf(Strings.isNeitherNullNorEmptyString(nullReference))).isEqualTo(false);
    assertThat(Boolean.valueOf(Strings.isNeitherNullNorEmptyString(emptyString))).isEqualTo(false);
  }

  @Test
  public final void testRepeat()
  {

    for (int i = 0; i < 5; i++)
    {
      assertThat(Strings.repeat("", i)).isEqualTo("");
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
      assertThat(Strings.repeat(value, i)).isEqualTo(expected[i]);
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
      assertThat(Strings.repeat(value2, i)).isEqualTo(expected2[i]);
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
    assertThat(exceptionCount).isEqualTo(howManyExpected);

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
    assertThat(exceptionCount).isEqualTo(howManyExpected);
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
    assertThat(actual1).isEqualTo(expected1);

    /* given */
    final String patternString2 = "\\d+";
    final String value2 = "abcdefghij";
    final String[] expected2 = {};

    /* when */
    final String[] actual2 = Strings.findMatched(patternString2, value2);

    /* then */
    assertThat(actual2).isEqualTo(expected2);

    /* given */
    final String patternString3 = "(\\d+)[\\D]*(\\d+)";
    final String value3 = "abc123456fgfg123999";
    final String[] expected3 = { "123456fgfg123999", "123456", "123999" };

    /* when */
    final String[] actual3 = Strings.findMatched(patternString3, value3);

    /* then */
    assertThat(actual3).isEqualTo(expected3);

    /* given */
    final String patternString4 = "[\\D]*(\\d+)[\\D]*(\\d+)";
    final String value4 = "abc123456fgfg123999";
    final String[] expected4 = { "abc123456fgfg123999", "123456", "123999" };

    /* when */
    final String[] actual4 = Strings.findMatched(patternString4, value4);

    /* then */
    assertThat(actual4).isEqualTo(expected4);

    /* given */
    final String patternString5 = "\\d+";
    final String value5 = "123999";
    final String[] expected5 = { "123999" };

    /* when */
    final String[] actual5 = Strings.findMatched(patternString5, value5);

    /* then */
    assertThat(actual5).isEqualTo(expected5);
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
    assertThat(actual1).isEqualTo(expected1);

    /* given */
    final String patternString2 = "\\d+";
    final Pattern pattern2 = Pattern.compile(patternString2);
    final String value2 = "abcdefghij";
    final String[] expected2 = {};

    /* when */
    final String[] actual2 = Strings.findMatched(pattern2, value2);

    /* then */
    assertThat(actual2).isEqualTo(expected2);

    /* given */
    final String patternString3 = "(\\d+)[\\D]*(\\d+)";
    final Pattern pattern3 = Pattern.compile(patternString3);
    final String value3 = "abc123456fgfg123999";
    final String[] expected3 = { "123456fgfg123999", "123456", "123999" };

    /* when */
    final String[] actual3 = Strings.findMatched(pattern3, value3);

    /* then */
    assertThat(actual3).isEqualTo(expected3);

    /* given */
    final String patternString4 = "[\\D]*(\\d+)[\\D]*(\\d+)";
    final Pattern pattern4 = Pattern.compile(patternString4);
    final String value4 = "abc123456fgfg123999";
    final String[] expected4 = { "abc123456fgfg123999", "123456", "123999" };

    /* when */
    final String[] actual4 = Strings.findMatched(pattern4, value4);

    /* then */
    assertThat(actual4).isEqualTo(expected4);

    /* given */
    final String patternString5 = "\\d+";
    final Pattern pattern5 = Pattern.compile(patternString5);
    final String value5 = "123999";
    final String[] expected5 = { "123999" };

    /* when */
    final String[] actual5 = Strings.findMatched(pattern5, value5);

    /* then */
    assertThat(actual5).isEqualTo(expected5);
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
    assertThat(actual1).isEqualTo(expected1);

    /* given */
    final String patternString2 = "\\d+";
    final String value2 = "abcdefghij";
    final String[] expected2 = {};

    /* when */
    final String[] actual2 = Strings.matchEntirely(patternString2, value2);

    /* then */
    assertThat(actual2).isEqualTo(expected2);

    /* given */
    final String patternString3 = "(\\d+)[\\D]*(\\d+)";
    final String value3 = "abc123456fgfg123999";
    final String[] expected3 = {};

    /* when */
    final String[] actual3 = Strings.matchEntirely(patternString3, value3);

    /* then */
    assertThat(actual3).isEqualTo(expected3);

    /* given */
    final String patternString4 = "[\\D]*(\\d+)[\\D]*(\\d+)";
    final String value4 = "abc123456fgfg123999";
    final String[] expected4 = { "abc123456fgfg123999", "123456", "123999" };

    /* when */
    final String[] actual4 = Strings.matchEntirely(patternString4, value4);

    /* then */
    assertThat(actual4).isEqualTo(expected4);

    /* given */
    final String patternString5 = "\\d+";
    final String value5 = "123999";
    final String[] expected5 = { "123999" };

    /* when */
    final String[] actual5 = Strings.matchEntirely(patternString5, value5);

    /* then */
    assertThat(actual5).isEqualTo(expected5);
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
    assertThat(actual1).isEqualTo(expected1);

    /* given */
    final String patternString2 = "\\d+";
    final Pattern pattern2 = Pattern.compile(patternString2);
    final String value2 = "abcdefghij";
    final String[] expected2 = {};

    /* when */
    final String[] actual2 = Strings.matchEntirely(pattern2, value2);

    /* then */
    assertThat(actual2).isEqualTo(expected2);

    /* given */
    final String patternString3 = "(\\d+)[\\D]*(\\d+)";
    final Pattern pattern3 = Pattern.compile(patternString3);
    final String value3 = "abc123456fgfg123999";
    final String[] expected3 = {};

    /* when */
    final String[] actual3 = Strings.matchEntirely(pattern3, value3);

    /* then */
    assertThat(actual3).isEqualTo(expected3);

    /* given */
    final String patternString4 = "[\\D]*(\\d+)[\\D]*(\\d+)";
    final Pattern pattern4 = Pattern.compile(patternString4);
    final String value4 = "abc123456fgfg123999";
    final String[] expected4 = { "abc123456fgfg123999", "123456", "123999" };

    /* when */
    final String[] actual4 = Strings.matchEntirely(pattern4, value4);

    /* then */
    assertThat(actual4).isEqualTo(expected4);

    /* given */
    final String patternString5 = "\\d+";
    final Pattern pattern5 = Pattern.compile(patternString5);
    final String value5 = "123999";
    final String[] expected5 = { "123999" };

    /* when */
    final String[] actual5 = Strings.matchEntirely(pattern5, value5);

    /* then */
    assertThat(actual5).isEqualTo(expected5);
  }

  @Test
  public final void testSplitByNumberOfCharsWithNullValue()
  {
    /* given */
    final String value = null;
    final int howMany = 3;

    /* expected */
    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("The value cannot be null.");

    /* when */
    Strings.splitByNumberOfChars(value, howMany);

    /* otherwise-fail */
    fail("No " + IllegalArgumentException.class.getSimpleName() + " was thrown when it was expected for the null value.");
  }

  @Test
  public final void testSplitByNumberOfCharsWithInvalidHowMany()
  {
    /* given */
    final String value = "12345678901234567890";
    final int howMany = 0;

    /* expected */
    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("howMany must be greater than 0. [entered: " + howMany + "]");

    /* when */
    Strings.splitByNumberOfChars(value, howMany);

    /* otherwise-fail */
    fail("No " + IllegalArgumentException.class.getSimpleName() + " was thrown when it was expected for the invalid howMany.");
  }

  @Test
  public final void testSplitByNumberOfChars()
  {
    /* given */
    final List<String> expected = Arrays.asList("12345678901234567890");
    final String value = "12345678901234567890";
    final int howMany = value.length();

    /* when */
    final List<String> actual = Strings.splitByNumberOfChars(value, howMany);

    /* then */
    assertThat(actual).hasSameSizeAs(expected);
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).containsExactlyElementsOf(expected);
  }

  @Test
  public final void testSplitByNumberOfChars2()
  {
    /* given */
    final List<String> expected = Arrays.asList("123", "456", "789", "012", "345", "678", "90");
    final String value = "12345678901234567890";
    final int howMany = 3;

    /* when */
    final List<String> actual = Strings.splitByNumberOfChars(value, howMany);

    /* then */
    assertThat(actual).hasSameSizeAs(expected);
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).containsExactlyElementsOf(expected);
  }

  @Test
  public final void testSplitByNumberOfChars3()
  {
    /* given */
    final List<String> expected = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1", "2", "3", "4", "5", "6", "7", "8",
        "9", "0");
    final String value = "12345678901234567890";
    final int howMany = 1;

    /* when */
    final List<String> actual = Strings.splitByNumberOfChars(value, howMany);

    /* then */
    assertThat(actual).hasSameSizeAs(expected);
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).containsExactlyElementsOf(expected);
  }

  @Test
  public final void testSplitByNumberOfChars4()
  {
    /* given */
    final List<String> expected = Arrays.asList("1234", "5678", "9012", "3456", "7890");
    final String value = "12345678901234567890";
    final int howMany = 4;

    /* when */
    final List<String> actual = Strings.splitByNumberOfChars(value, howMany);

    /* then */
    assertThat(actual).hasSameSizeAs(expected);
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).containsExactlyElementsOf(expected);
  }

  public final void testSplitByNumberOfChars5()
  {
    /* given */
    final List<String> expected = Arrays.asList("12345678901234567890");
    final String value = "12345678901234567890";
    final int howMany = value.length() + 10;

    /* when */
    final List<String> actual = Strings.splitByNumberOfChars(value, howMany);

    /* then */
    assertThat(actual).hasSameSizeAs(expected);
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).containsExactlyElementsOf(expected);
  }

  @Test
  public final void testSplitByNumberOfChars6()
  {
    /* given */
    final List<String> expected = Arrays.asList("1A3", "4bc", "56#", "78$", "012", "*3.", "45 ", "6\t7", "890");
    final String value = "1A34bc56#78$012*3.45 6\t7890";
    final int howMany = 3;

    /* when */
    final List<String> actual = Strings.splitByNumberOfChars(value, howMany);

    /* then */
    assertThat(actual).hasSameSizeAs(expected);
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).containsExactlyElementsOf(expected);
  }

  @Test
  public final void testSplitByNumberOfChars7()
  {
    /* given */
    final List<String> expected = Arrays.asList("1A3", "4b", "c56", "#", "8$0", "12*", "3.4", "5 6", "\t78", "90");
    final String value = "1A34b\rc56#\n8$012*3.45 6\t7890";
    final int howMany = 3;

    /* when */
    final List<String> actual = Strings.splitByNumberOfChars(value, howMany);

    /* then */
    assertThat(actual).hasSameSizeAs(expected);
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).containsExactlyElementsOf(expected);
  }

  @Test
  public final void testSplitByNumberOfChars8()
  {
    /* given */
    final List<String> expected = Arrays.asList("123", "45", "678", "9");
    final String value = "12345\n6789";
    final int howMany = 3;

    /* when */
    final List<String> actual = Strings.splitByNumberOfChars(value, howMany);

    /* then */
    assertThat(actual).hasSameSizeAs(expected);
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).containsExactlyElementsOf(expected);
  }

  @Test
  public final void testSplitByNumberOfChars9()
  {
    /* given */
    final List<String> expected = Arrays.asList("123", "45", "678", "9");
    final String value = "12345\n\r6789";
    final int howMany = 3;

    /* when */
    final List<String> actual = Strings.splitByNumberOfChars(value, howMany);

    /* then */
    assertThat(actual).hasSameSizeAs(expected);
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).containsExactlyElementsOf(expected);
  }

  @Test
  public final void testSplitByNumberOfChars10()
  {
    /* given */
    final List<String> expected = Arrays.asList("123", "45", "6", "789");
    final String value = "12345\n6\n789";
    final int howMany = 3;

    /* when */
    final List<String> actual = Strings.splitByNumberOfChars(value, howMany);

    /* then */
    assertThat(actual).hasSameSizeAs(expected);
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).containsExactlyElementsOf(expected);
  }
}
