/**
 * 
 */
package org.elixirian.kommonlee.io;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-10-28)
 */
public class StringBuildWriterTest
{
  private char[] chars;
  private String string;

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
    final int size = 256;
    chars = new char[size];
    for (int i = 0; i < size; i++)
    {
      chars[i] = (char) i;
    }
    string = new String(chars);
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public final void testWriteInt() throws IOException
  {
    for (int i = 0; i < 255; i++)
    {
      final StringBuildWriter stringBuildWriter = new StringBuildWriter();
      stringBuildWriter.write(i);
      assertThat(stringBuildWriter.getStringBuilder()
          .toString(), is(equalTo(String.valueOf((char) i))));
    }
  }

  @Test
  public final void testWriteCharArray()
  {
    final StringBuildWriter stringBuildWriter = new StringBuildWriter();
    stringBuildWriter.write(chars);
    assertThat(stringBuildWriter.getStringBuilder()
        .toString(), is(equalTo(new StringBuilder().append(chars)
        .toString())));
  }

  @Test
  public final void testWriteCharArrayIntInt()
  {
    final Random ramdom = new Random();

    for (int repeatIndex = 0; repeatIndex < 100; repeatIndex++)
    {
      for (int i = 0, size = chars.length; i < size; i++)
      {
        final int length = size - i - ramdom.nextInt(size - i);

        final StringBuildWriter stringBuildWriter = new StringBuildWriter();
        stringBuildWriter.write(chars, i, length);

        assertThat(stringBuildWriter.getStringBuilder()
            .toString(), is(equalTo(new StringBuilder().append(chars, i, length)
            .toString())));
      }
      for (int i = chars.length - 1, size = chars.length + 1; i > 0; i--)
      {
        final int length = ramdom.nextInt(size - i);

        final StringBuildWriter stringBuildWriter = new StringBuildWriter();
        stringBuildWriter.write(chars, i, length);

        assertThat(stringBuildWriter.getStringBuilder()
            .toString(), is(equalTo(new StringBuilder().append(chars, i, length)
            .toString())));
      }
    }
  }

  @Test
  public final void testFlush()
  {
    /* nothing should happen */
    final StringBuildWriter stringBuildWriter = new StringBuildWriter();
    stringBuildWriter.flush();
    assertEquals(0, stringBuildWriter.getStringBuilder()
        .length());
  }

  @Test
  public final void testClose()
  {
    /* nothing should happen */
    final StringBuildWriter stringBuildWriter = new StringBuildWriter();
    stringBuildWriter.close();
    assertEquals(0, stringBuildWriter.getStringBuilder()
        .length());
  }

  @Test
  public final void testStringBuildWriter()
  {
    final StringBuildWriter stringBuildWriter = new StringBuildWriter();
    assertThat(stringBuildWriter.getStringBuilder(), is(notNullValue()));
    assertEquals(0, stringBuildWriter.getStringBuilder()
        .length());
  }

  @Test
  public final void testStringBuildWriterInt()
  {
    final int initialSize = 12345;
    final StringBuildWriter stringBuildWriter = new StringBuildWriter(initialSize);
    assertThat(stringBuildWriter.getStringBuilder(), is(notNullValue()));
    assertEquals(initialSize, stringBuildWriter.getStringBuilder()
        .capacity());
  }

  @Test
  public final void testWriteString()
  {
    final StringBuildWriter stringBuildWriter = new StringBuildWriter();
    stringBuildWriter.write(string);
    assertThat(stringBuildWriter.getStringBuilder()
        .toString(), is(equalTo(string)));
  }

  @Test
  public final void testWriteStringIntInt()
  {
    final Random ramdom = new Random();

    for (int repeatIndex = 0; repeatIndex < 100; repeatIndex++)
    {
      for (int i = 0, size = string.length(); i < size; i++)
      {
        final int length = size - i - ramdom.nextInt(size - i);

        final StringBuildWriter stringBuildWriter = new StringBuildWriter();
        stringBuildWriter.write(string, i, length);

        assertThat(stringBuildWriter.getStringBuilder()
            .toString(), is(equalTo(string.substring(i, i + length))));
      }
      for (int i = string.length() - 1, size = string.length() + 1; i > 0; i--)
      {
        final int length = ramdom.nextInt(size - i);

        final StringBuildWriter stringBuildWriter = new StringBuildWriter();
        stringBuildWriter.write(string, i, length);

        assertThat(stringBuildWriter.getStringBuilder()
            .toString(), is(equalTo(string.substring(i, i + length))));
      }
    }
  }

  @Test
  public final void testAppendCharSequence()
  {
    final StringBuildWriter stringBuildWriter = new StringBuildWriter();
    stringBuildWriter.append(string);
    assertThat(stringBuildWriter.getStringBuilder()
        .toString(), is(equalTo(string)));
  }

  @Test
  public final void testAppendCharSequenceIntInt()
  {
    final Random ramdom = new Random();

    for (int repeatIndex = 0; repeatIndex < 100; repeatIndex++)
    {
      for (int i = 0, size = string.length(); i < size; i++)
      {
        final int length = size - i - ramdom.nextInt(size - i);
        final int endIndex = i + length;

        final StringBuildWriter stringBuildWriter = new StringBuildWriter();
        stringBuildWriter.append(string, i, endIndex);

        assertThat(stringBuildWriter.getStringBuilder()
            .toString(), is(equalTo(string.substring(i, endIndex))));
      }
      for (int i = string.length() - 1, size = string.length() + 1; i > 0; i--)
      {
        final int length = ramdom.nextInt(size - i);
        final int endIndex = i + length;

        final StringBuildWriter stringBuildWriter = new StringBuildWriter();
        stringBuildWriter.append(string, i, endIndex);

        assertThat(stringBuildWriter.getStringBuilder()
            .toString(), is(equalTo(string.substring(i, endIndex))));
      }
    }
  }

  @Test
  public final void testAppendChar()
  {
    final StringBuildWriter stringBuildWriter = new StringBuildWriter();
    final StringBuilder expected = new StringBuilder();
    for (int i = 0, size = chars.length; i < size; i++)
    {
      stringBuildWriter.append(chars[i]);
      expected.append(chars[i]);
      assertThat(stringBuildWriter.getStringBuilder()
          .toString(), is(equalTo(expected.toString())));
    }
  }

  @Test
  public final void testToString()
  {
    final StringBuildWriter stringBuildWriter = new StringBuildWriter();
    final StringBuilder expected = new StringBuilder();
    for (int i = 0, size = string.length(); i < size; i++)
    {
      stringBuildWriter.write(string.substring(i));
      expected.append(string.substring(i));
      assertThat(stringBuildWriter.toString(), is(equalTo(expected.toString())));
    }
  }

  @Test
  public final void testGetStringBuilder()
  {
    final StringBuildWriter stringBuildWriter = new StringBuildWriter();
    assertThat(stringBuildWriter.getStringBuilder(), is(notNullValue()));
    assertThat(stringBuildWriter.getStringBuilder(), is(instanceOf(StringBuilder.class)));
  }

}
