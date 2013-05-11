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
package org.elixirian.kommonlee.nio.util;

import static org.elixirian.kommonlee.test.CommonTestHelper.*;
import static org.elixirian.kommonlee.util.collect.Lists.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.elixirian.kommonlee.io.ByteArrayConsumer;
import org.elixirian.kommonlee.io.ByteArrayConsumingContainer;
import org.elixirian.kommonlee.io.ByteArrayProducer;
import org.elixirian.kommonlee.io.CharArrayConsumer;
import org.elixirian.kommonlee.io.CharArrayConsumingContainer;
import org.elixirian.kommonlee.io.DataConsumers;
import org.elixirian.kommonlee.io.IoCommonConstants;
import org.elixirian.kommonlee.test.CommonTestHelper.Accessibility;
import org.elixirian.kommonlee.util.NeoArrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-13)
 */
public class NioUtilTest
{
  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  private List<Byte> byteList;
  private List<Character> charList;
  private String string;

  /**
   * @throws java.lang.Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception
  {
    final List<Byte> byteList = new ArrayList<Byte>();
    this.string = readFile(getTestFile(), byteList);
    this.byteList = Collections.unmodifiableList(byteList);
    charList = Arrays.asList(NeoArrays.convertToBoxedPrimitive(string.toCharArray()));
  }

  private List<Character> toCharListWithCharSet(final String string, final Charset charset)
  {
    final String stringWithDifferentCharset = new String(string.getBytes(), charset);
    final List<Character> charList = newArrayList();
    for (final char c : stringWithDifferentCharset.toCharArray())
    {
      @SuppressWarnings("boxing")
      final Character character = c;
      charList.add(character);
    }
    return charList;
  }

  private String readFile(final File file, final List<Byte> byteList)
  {
    FileInputStream fileInputStream = null;
    try
    {
      fileInputStream = new FileInputStream(file);
      final byte[] buffer = new byte[8192];
      int bytesRead = fileInputStream.read(buffer);
      while (-1 != bytesRead)
      {
        for (int i = 0; i < bytesRead; i++)
        {
          @SuppressWarnings("boxing")
          final Byte bt = buffer[i];
          byteList.add(bt);
        }
        bytesRead = fileInputStream.read(buffer);
      }
      return new String(NeoArrays.convertToPrimitive(byteList.toArray(new Byte[0])));
    }
    catch (final FileNotFoundException e)
    {
      throw new AssertionError(e);
    }
    catch (final IOException e)
    {
      throw new AssertionError(e);
    }
    finally
    {
      if (null != fileInputStream)
      {
        try
        {
          fileInputStream.close();
        }
        catch (final IOException e)
        {
        }
      }
    }
  }

  private File getTestFile()
  {
    try
    {
      return new File(new URI(this.getClass()
          .getResource("/file4testing.txt")
          .toString()));
    }
    catch (final URISyntaxException e)
    {
      throw new UnsupportedOperationException(e);
    }
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception
  {
  }

  @Test(expected = IllegalAccessException.class)
  public final void testNioFileUtil() throws Exception
  {
    testNotAccessibleConstructor(NioUtil.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
  }

  /**
   * Test method for {@link org.elixirian.kommonlee.nio.util.NioUtil#closeQuietly(java.io.Closeable)}.
   */
  @Test
  public final void testCloseQuietly()
  {
    final boolean[] called = { false };
    Closeable closeable = new Closeable() {
      @Override
      public void close() throws IOException
      {
        called[0] = true;
      }
    };

    assertFalse(called[0]);
    /* test */
    NioUtil.closeQuietly(closeable);
    assertTrue(called[0]);

    called[0] = false;
    closeable = new Closeable() {
      @Override
      public void close() throws IOException
      {
        throw new IOException("for testing") {
          private static final long serialVersionUID = 1L;

          @Override
          public void printStackTrace()
          {
            System.out.println(getMessage());
            called[0] = true;
          }
        };
      }
    };

    assertFalse(called[0]);
    /* test */
    NioUtil.closeQuietly(closeable);
    assertTrue(called[0]);
  }

  private static class ByteArrayConsumer4Testing implements ByteArrayConsumer
  {
    private final List<Byte> byteList = newArrayList();

    @Override
    public void consume(final byte[] bytes, final int offset, final int count)
    {
      for (int i = offset; i < offset + count; i++)
      {
        final Byte b = bytes[i];
        byteList.add(b);
      }
    }

    public List<Byte> getByteList()
    {
      return Collections.unmodifiableList(byteList);
    }

    @Override
    public String toString()
    {
      return new String(NeoArrays.convertToPrimitive(byteList.toArray(new Byte[0])));
    }
  }

  @Test
  public void testReadInputStream()
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      final ByteArrayConsumer4Testing byteArrayConsumer = new ByteArrayConsumer4Testing();

      /* test */
      NioUtil.readInputStream(this.getClass()
          .getResourceAsStream("/file4testing.txt"), bufferSize, byteArrayConsumer);

      assertThat(byteArrayConsumer.getByteList(), is(equalTo(this.byteList)));
      assertThat(byteArrayConsumer.toString(), is(equalTo(this.string)));
    }
  }

  @Test
  public void testReadInputStreamWithByteArrayConsumingContainer()
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      final ByteArrayConsumingContainer byteArrayConsumingContainer = DataConsumers.newByteArrayConsumingContainer();

      /* test */
      NioUtil.readInputStream(this.getClass()
          .getResourceAsStream("/file4testing.txt"), bufferSize, byteArrayConsumingContainer);

      assertThat(byteArrayConsumingContainer.getDataList(), is(equalTo(this.byteList)));
      assertThat(byteArrayConsumingContainer.toString(), is(equalTo(this.string)));
    }
  }

  private static class CharArrayConsumer4Testing implements CharArrayConsumer
  {
    private final List<Character> charList = newArrayList();

    @Override
    public void consume(final char[] chars, final int offset, final int count)
    {
      for (int i = offset, size = offset + count; i < size; i++)
      {
        charList.add(Character.valueOf(chars[i]));
      }
    }

    public List<Character> getCharList()
    {
      return Collections.unmodifiableList(charList);
    }

    @Override
    public String toString()
    {
      return new String(NeoArrays.convertToPrimitive(charList.toArray(new Character[0])));
    }
  }

  @Test
  public void testReadInputStreamWithCharArrayConsumer()
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      final CharArrayConsumer4Testing charArrayConsumer = new CharArrayConsumer4Testing();

      /* test */
      NioUtil.readInputStream(this.getClass()
          .getResourceAsStream("/file4testing.txt"), bufferSize, IoCommonConstants.UTF_8, charArrayConsumer);

      assertThat(charArrayConsumer.getCharList(),
          is(equalTo(toCharListWithCharSet(this.string, IoCommonConstants.UTF_8))));
      assertThat(charArrayConsumer.toString(), is(equalTo(this.string)));
    }
  }

  @Test
  public void testReadInputStreamWithCharArrayConsumingContainer()
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      final CharArrayConsumingContainer charArrayConsumingContainer = DataConsumers.newCharArrayConsumingContainer();

      /* test */
      NioUtil.readInputStream(this.getClass()
          .getResourceAsStream("/file4testing.txt"), bufferSize, IoCommonConstants.UTF_8, charArrayConsumingContainer);

      assertThat(charArrayConsumingContainer.getDataList(),
          is(equalTo(toCharListWithCharSet(this.string, IoCommonConstants.UTF_8))));
      assertThat(charArrayConsumingContainer.toString(), is(equalTo(this.string)));
    }
  }

  @Test
  public void testReadFile() throws URISyntaxException
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      final ByteArrayConsumer4Testing byteArrayConsumer = new ByteArrayConsumer4Testing();

      /* test */
      NioUtil.readFile(new File(new URI(this.getClass()
          .getResource("/file4testing.txt")
          .toString())), bufferSize, byteArrayConsumer);

      assertThat(byteArrayConsumer.getByteList(), is(equalTo(this.byteList)));
      assertThat(byteArrayConsumer.toString(), is(equalTo(this.string)));
    }
  }

  @Test
  public void testReadFileWithByteArrayConsumingContainer() throws URISyntaxException
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      final ByteArrayConsumingContainer byteArrayConsumingContainer = DataConsumers.newByteArrayConsumingContainer();

      /* test */
      NioUtil.readFile(new File(new URI(this.getClass()
          .getResource("/file4testing.txt")
          .toString())), bufferSize, byteArrayConsumingContainer);

      assertThat(byteArrayConsumingContainer.getDataList(), is(equalTo(this.byteList)));
      assertThat(byteArrayConsumingContainer.toString(), is(equalTo(this.string)));
    }
  }

  @Test
  public void testReadFileWithCharArrayConsumer() throws URISyntaxException
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      final CharArrayConsumer4Testing charArrayConsumer = new CharArrayConsumer4Testing();

      /* test */
      NioUtil.readFile(new File(new URI(this.getClass()
          .getResource("/file4testing.txt")
          .toString())), bufferSize, IoCommonConstants.UTF_8, charArrayConsumer);

      assertThat(charArrayConsumer.getCharList(), is(equalTo(this.charList)));
      assertThat(new String(NeoArrays.convertToPrimitive(charArrayConsumer.getCharList()
          .toArray(new Character[0]))), is(equalTo(this.string)));
    }
  }

  @Test
  public void testReadFileWithCharArrayConsumingContainer() throws URISyntaxException
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      final CharArrayConsumingContainer charArrayConsumingContainer = DataConsumers.newCharArrayConsumingContainer();

      /* test */
      NioUtil.readFile(new File(new URI(this.getClass()
          .getResource("/file4testing.txt")
          .toString())), bufferSize, IoCommonConstants.UTF_8, charArrayConsumingContainer);

      assertThat(charArrayConsumingContainer.getDataList(), is(equalTo(this.charList)));
      assertThat(new String(NeoArrays.convertToPrimitive(charArrayConsumingContainer.getDataList()
          .toArray(new Character[0]))), is(equalTo(this.string)));
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReadFileWith0SizeBuffer() throws Exception
  {
    ByteArrayConsumer byteArrayConsumer = null;

    try
    {
      byteArrayConsumer = mock(ByteArrayConsumer.class);
      doAnswer(new Answer<Void>() {
        @Override
        public Void answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
        {
          return null;
        }
      }).when(byteArrayConsumer)
          .consume(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());

      NioUtil.readFile(getTestFile(), 1, byteArrayConsumer);
    }
    catch (final Exception e)
    {
      e.printStackTrace();
      fail("No exception should be thrown here!");
    }

    /* test */
    NioUtil.readFile(getTestFile(), 0, byteArrayConsumer);
  }

  private static class ByteArrayProducer4Testing implements ByteArrayProducer
  {
    private final byte[] byteArray;
    private int position;
    private int left;

    public ByteArrayProducer4Testing(final byte[] byteArray)
    {
      this.byteArray = byteArray;
      this.left = byteArray.length;
    }

    public void reset()
    {
      this.position = 0;
      this.left = byteArray.length;
    }

    @Override
    public int produce(final byte[] bytes)
    {
      if (0 < left)
      {
        final int byteLength = bytes.length;
        final int count = byteLength < left ? byteLength : left;
        System.arraycopy(byteArray, position, bytes, 0, count);
        position += count;
        left -= count;
        return count;
      }
      return -1;
    }

    @Override
    public int length()
    {
      return byteArray.length;
    }
  }

  @Test
  public void testWriteFile()
  {
    final File file = new File(temporaryFolder.getRoot(), "file4testing2.txt");

    final String expected = this.string;
    final ByteArrayProducer4Testing byteArrayProducer = new ByteArrayProducer4Testing(expected.getBytes());
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      /* test */
      NioUtil.writeFile(file, bufferSize, byteArrayProducer);

      final List<Byte> byteList = new ArrayList<Byte>();
      final String string = readFile(file, byteList);
      assertThat(byteList, is(equalTo(this.byteList)));
      assertThat(string, is(equalTo(this.string)));

      assertTrue(file.delete());
      byteArrayProducer.reset();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWriteFileWith0SizeBuffer()
  {
    File file = null;
    byte[] byteArray = null;
    try
    {
      file = new File(temporaryFolder.getRoot(), "file4testing2.txt");

      final String expected = this.string;
      byteArray = new byte[expected.length()];
      for (int i = 0, size = expected.length(); i < size; i++)
      {
        byteArray[i] = (byte) expected.charAt(i);
      }
    }
    catch (final Exception e)
    {
      e.printStackTrace();
      fail("No exception should be thrown here!");
    }

    final ByteArrayProducer byteArrayProducer = new ByteArrayProducer4Testing(byteArray);
    /* test */
    NioUtil.writeFile(file, 0, byteArrayProducer);
  }

  /**
   * Test method for {@link org.elixirian.kommonlee.nio.util.NioUtil#copyFile(java.io.File, java.io.File, int)}.
   */
  @Test
  public final void testCopyFileFileFileInt()
  {
    final File sourceFile = getTestFile();

    /* test */
    NioUtil.copyFile(sourceFile, new File(temporaryFolder.getRoot(), "file4testing2.txt"));

    final File file = new File(temporaryFolder.getRoot(), "file4testing2.txt");

    final List<Byte> expectedByteList = new ArrayList<Byte>();
    final String expectedString = readFile(sourceFile, expectedByteList);

    final List<Byte> resultByteList = new ArrayList<Byte>();
    final String resultString = readFile(file, resultByteList);

    assertThat(resultByteList, is(equalTo(expectedByteList)));
    assertThat(resultString.toString(), is(equalTo(expectedString)));
  }
}
