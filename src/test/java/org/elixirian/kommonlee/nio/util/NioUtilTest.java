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

import static org.assertj.core.api.Assertions.*;
import static org.elixirian.kommonlee.test.CommonTestHelper.*;
import static org.elixirian.kommonlee.util.MessageFormatter.*;
import static org.elixirian.kommonlee.util.collect.Lists.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.elixirian.kommonlee.test.CauseCheckableExpectedException;
import org.elixirian.kommonlee.test.CommonTestHelper.Accessibility;
import org.elixirian.kommonlee.util.NeoArrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.InOrder;
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

  @Rule
  public CauseCheckableExpectedException causeCheckableExpectedException = CauseCheckableExpectedException.none();

  private List<Byte> byteList;
  private byte[] byteArray;
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
    this.byteArray = this.string.getBytes();
    charList = Arrays.asList(NeoArrays.convertToBoxedPrimitive(string.toCharArray()));
  }

  @After
  public void tearDown() throws Exception
  {
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

  private static <T> T any(final Class<T> theClass)
  {
    return Mockito.any(theClass);
  }

  private int computeHowManyForReading(final int total, final int bufferSize)
  {
    return (int) Math.ceil((double) total / bufferSize) + 1;
  }

  private int computeHowManyForWriting(final int total, final int bufferSize)
  {
    return (int) Math.ceil((double) total / bufferSize);
  }

  @Test(expected = IllegalAccessException.class)
  public final void testNioFileUtil() throws Exception
  {
    testNotAccessibleConstructor(NioUtil.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
  }

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

    assertThat(called[0]).isFalse();
    /* test */
    NioUtil.closeQuietly(closeable);
    assertThat(called[0]).isTrue();

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

    assertThat(called[0]).isFalse();
    /* test */
    NioUtil.closeQuietly(closeable);
    assertThat(called[0]).isTrue();
  }

  @Test
  public void testCloseQuietlyCloseableAndCloseableVarargs() throws IOException
  {
    /* given */
    final Closeable closeable1 = mock(Closeable.class, "closeable1");
    final Closeable closeable2 = mock(Closeable.class, "closeable2");
    final Closeable closeable3 = mock(Closeable.class, "closeable3");

    /* when */
    NioUtil.closeQuietly(closeable1, closeable2, closeable3);

    /* then */

    final InOrder inOrder = inOrder(closeable1, closeable2, closeable3);

    inOrder.verify(closeable1, times(1))
        .close();
    inOrder.verify(closeable2, times(1))
        .close();
    inOrder.verify(closeable3, times(1))
        .close();
  }

  private static class ByteArrayConsumer4Testing implements ByteArrayConsumer
  {
    private final List<Byte> byteList = newArrayList();

    @Override
    public void consume(final byte[] bytes, final int offset, final int count)
    {
      for (int i = offset; i < offset + count; i++)
      {
        @SuppressWarnings("boxing")
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

      assertThat(byteArrayConsumer.getByteList()).isEqualTo(this.byteList);
      assertThat(byteArrayConsumer.toString()).isEqualTo(this.string);
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

      assertThat(byteArrayConsumingContainer.getDataList()).isEqualTo(this.byteList);
      assertThat(byteArrayConsumingContainer.toString()).isEqualTo(this.string);
    }
  }

  @Test
  public void testReadInputStreamToByteArray()
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      final InputStream inputStream = this.getClass()
          .getResourceAsStream("/file4testing.txt");

      /* test */
      final byte[] actual = NioUtil.readInputStreamToByteArray(inputStream, bufferSize);

      assertThat(actual).isEqualTo(this.byteArray);
      assertThat(actual.length).isEqualTo(this.byteArray.length);
      assertThat(new String(actual)).isEqualTo(this.string);
    }
  }

  @Test
  public void testReadInputStreamToByteArrayWithZeroSizeBuffer()
  {
    /* given */
    final InputStream inputStream = this.getClass()
        .getResourceAsStream("/file4testing.txt");

    /* expect */
    causeCheckableExpectedException.expect(IllegalArgumentException.class);

    /* when */
    NioUtil.readInputStreamToByteArray(inputStream, 0);

    /* otherwise */
    fail(format("The expected exception [%s] is not thrown.", IllegalArgumentException.class));
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

      assertThat(charArrayConsumer.getCharList()).isEqualTo(toCharListWithCharSet(this.string, IoCommonConstants.UTF_8));
      assertThat(charArrayConsumer.toString()).isEqualTo(this.string);
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

      assertThat(charArrayConsumingContainer.getDataList()).isEqualTo(
          toCharListWithCharSet(this.string, IoCommonConstants.UTF_8));
      assertThat(charArrayConsumingContainer.toString()).isEqualTo(this.string);
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

      assertThat(byteArrayConsumer.getByteList()).isEqualTo(this.byteList);
      assertThat(byteArrayConsumer.toString()).isEqualTo(this.string);
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

      assertThat(byteArrayConsumingContainer.getDataList()).isEqualTo(this.byteList);
      assertThat(byteArrayConsumingContainer.toString()).isEqualTo(this.string);
    }
  }

  @Test
  public void testReadFileToByteArray() throws URISyntaxException
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      /* given */
      final File file = new File(new URI(this.getClass()
          .getResource("/file4testing.txt")
          .toString()));

      /* when */
      final byte[] actual = NioUtil.readFileToByteArray(file, bufferSize);

      /* then */
      assertThat(actual).isEqualTo(this.byteArray);
      assertThat(actual.length).isEqualTo(this.byteArray.length);
      assertThat(new String(actual)).isEqualTo(this.string);
    }
  }

  @Test
  public void testReadFileToByteArrayWithZeroSizeBuffer() throws URISyntaxException
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      /* given */
      final File file = new File(new URI(this.getClass()
          .getResource("/file4testing.txt")
          .toString()));

      /* expect */
      causeCheckableExpectedException.expect(IllegalArgumentException.class);

      /* when */
      NioUtil.readFileToByteArray(file, 0);

      /* otherwise */
      fail(format("The expected exception [%s] is not thrown.", IllegalArgumentException.class));
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

      assertThat(charArrayConsumer.getCharList()).isEqualTo(this.charList);
      assertThat(new String(NeoArrays.convertToPrimitive(charArrayConsumer.getCharList()
          .toArray(new Character[0])))).isEqualTo(this.string);
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

      assertThat(charArrayConsumingContainer.getDataList()).isEqualTo(this.charList);
      assertThat(new String(NeoArrays.convertToPrimitive(charArrayConsumingContainer.getDataList()
          .toArray(new Character[0])))).isEqualTo(this.string);
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

    @Override
    public byte[] toByteArray()
    {
      return Arrays.copyOf(byteArray, byteArray.length);
    }
  }

  @Test
  public void testWriteOutputStream() throws IOException
  {
    /* given */
    final String expected = this.string;
    final ByteArrayProducer4Testing byteArrayProducer = new ByteArrayProducer4Testing(expected.getBytes());

    final OutputStream outputStream = mock(OutputStream.class);

    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      final List<Byte> byteListForWriting = new ArrayList<Byte>();

      doAnswer(new Answer<Void>() {
        @Override
        public Void answer(final InvocationOnMock invocation) throws Throwable
        {
          final Object[] params = invocation.getArguments();
          final byte[] bytes = (byte[]) params[0];

          @SuppressWarnings("boxing")
          final int offset = (Integer) params[1];

          @SuppressWarnings("boxing")
          final int count = (Integer) params[2];
          for (int i = offset; i < offset + count; i++)
          {
            final byte each = bytes[i];
            byteListForWriting.add(Byte.valueOf(each));
          }
          return null;
        }
      }).when(outputStream)
          .write(any(byte[].class), anyInt(), anyInt());

      /* when */
      NioUtil.writeOutputStream(outputStream, bufferSize, byteArrayProducer);

      /* then */
      assertThat(byteListForWriting).isEqualTo(this.byteList);
      assertThat(new String(NeoArrays.convertToPrimitive(byteListForWriting.toArray(new Byte[0])))).isEqualTo(
          this.string);

      final InOrder writeOrder = inOrder(outputStream);
      writeOrder.verify(outputStream, times(computeHowManyForWriting(byteListForWriting.size(), bufferSize)))
          .write(any(byte[].class), anyInt(), anyInt());
      writeOrder.verify(outputStream, atLeastOnce())
          .close();

      reset(outputStream);

      byteArrayProducer.reset();
    }
  }

  @Test
  public void testWriteOutputStreamWith0SizeBuffer() throws IOException
  {

    try
    {
      NioUtil.writeOutputStream(null, 1, null);
      fail("This line must not be reachable.");
    }
    catch (final NullPointerException e)
    {
      // NullPointerException should be caught.
    }

    /* expect */
    causeCheckableExpectedException.expect(IllegalArgumentException.class)
        .expectMessageContains("buffer size must be greater than 0");

    /* when / then: the expected exception should be thrown. */
    NioUtil.writeOutputStream(null, 0, null);

    /* otherwise */
    fail(format("The expected exception [%s] is not thrown.", IllegalArgumentException.class));
  }

  @Test
  public void testWriteFile()
  {
    /* given */
    final File file = new File(temporaryFolder.getRoot(), "file4testing2.txt");

    final String expected = this.string;
    final ByteArrayProducer4Testing byteArrayProducer = new ByteArrayProducer4Testing(expected.getBytes());
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {

      /* when */
      NioUtil.writeFile(file, bufferSize, byteArrayProducer);

      /* then */
      final List<Byte> byteList = new ArrayList<Byte>();
      final String string = readFile(file, byteList);
      assertThat(byteList).isEqualTo(this.byteList);
      assertThat(string).isEqualTo(this.string);

      assertThat(file.delete()).isTrue();
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

  @Test
  public final void testCopyIntInputStreamOutputStream() throws IOException
  {

    /* given */
    final InputStream inputStream = mock(InputStream.class);

    final OutputStream outputStream = mock(OutputStream.class);
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      /* @formatter:off */
      final List<Byte> byteListForReading = Arrays.asList(NeoArrays
                                                         .convertToBoxedPrimitive(this
                                                                                 .string
                                                                                 .getBytes()));
      final int size = byteListForReading.size();
      /* @formatter:on */
      final int[] position = new int[] { 0 };

      doAnswer(new Answer<Integer>() {
        @SuppressWarnings("boxing")
        @Override
        public Integer answer(final InvocationOnMock invocation) throws Throwable
        {
          if (size <= position[0])
          {
            return -1;
          }

          final Object[] params = invocation.getArguments();

          final byte[] bytes = (byte[]) params[0];

          final int offset = (Integer) params[1];

          final int count = (Integer) params[2];

          final int howMany = size < position[0] + count ? (size - position[0]) : count;
          final int length = offset + howMany;

          for (int i = offset; i < length; i++)
          {
            bytes[i] = byteListForReading.get(position[0]);
            position[0]++;
          }
          return howMany;
        }
      }).when(inputStream)
          .read(any(byte[].class), anyInt(), anyInt());

      final List<Byte> byteListForWriting = new ArrayList<Byte>();
      doAnswer(new Answer<Void>() {
        @Override
        public Void answer(final InvocationOnMock invocation) throws Throwable
        {
          final Object[] params = invocation.getArguments();
          final byte[] bytes = (byte[]) params[0];

          @SuppressWarnings("boxing")
          final int offset = (Integer) params[1];

          @SuppressWarnings("boxing")
          final int count = (Integer) params[2];
          for (int i = offset; i < offset + count; i++)
          {
            final byte each = bytes[i];
            byteListForWriting.add(Byte.valueOf(each));
          }
          return null;
        }
      }).when(outputStream)
          .write(any(byte[].class), anyInt(), anyInt());

      /* when */
      NioUtil.copy(bufferSize, inputStream, outputStream);

      /* then */
      // System.out.println(format("\n\n# expected:\n%s\n\n# actual:\n%s", this.string,
      // new String(NeoArrays.convertToPrimitive(byteListForWriting.toArray(new Byte[0])))));
      assertThat(
          new String(NeoArrays.convertToPrimitive(byteListForWriting.toArray(new Byte[0])), IoCommonConstants.UTF_8)).isEqualTo(
          this.string);
      // System.out.println(format("\n\n# expected:\n%s\n\n# actual:\n%s", byteListForReading, byteListForWriting));
      /* @formatter:off */
      assertThat(byteListForWriting).isEqualTo(byteListForReading);
      /* @formatter:on */

      final InOrder order = inOrder(inputStream);
      order.verify(inputStream, times(computeHowManyForReading(byteListForReading.size(), bufferSize)))
          .read(any(byte[].class), anyInt(), anyInt());
      order.verify(inputStream, atLeastOnce())
          .close();

      final InOrder writeOrder = inOrder(outputStream);
      writeOrder.verify(outputStream, times(computeHowManyForWriting(byteListForWriting.size(), bufferSize)))
          .write(any(byte[].class), anyInt(), anyInt());
      writeOrder.verify(outputStream, atLeastOnce())
          .close();

      reset(outputStream);
      reset(inputStream);
    }
  }

  @Test
  public final void testCopyInputStreamOutputStream() throws IOException
  {

    /* given */
    final InputStream inputStream = mock(InputStream.class);

    final OutputStream outputStream = mock(OutputStream.class);
    final int bufferSize = IoCommonConstants.BUFFER_SIZE_512Ki;

    /* @formatter:off */
    final List<Byte> byteListForReading = Arrays.asList(NeoArrays
        .convertToBoxedPrimitive(this
            .string
            .getBytes()));
    final int size = byteListForReading.size();
    /* @formatter:on */
    final int[] position = new int[] { 0 };

    doAnswer(new Answer<Integer>() {
      @SuppressWarnings("boxing")
      @Override
      public Integer answer(final InvocationOnMock invocation) throws Throwable
      {
        if (size <= position[0])
        {
          return -1;
        }

        final Object[] params = invocation.getArguments();

        final byte[] bytes = (byte[]) params[0];

        final int offset = (Integer) params[1];

        final int count = (Integer) params[2];

        final int howMany = size < position[0] + count ? (size - position[0]) : count;
        final int length = offset + howMany;

        for (int i = offset; i < length; i++)
        {
          bytes[i] = byteListForReading.get(position[0]);
          position[0]++;
        }
        return howMany;
      }
    }).when(inputStream)
        .read(any(byte[].class), anyInt(), anyInt());

    final List<Byte> byteListForWriting = new ArrayList<Byte>();
    doAnswer(new Answer<Void>() {
      @Override
      public Void answer(final InvocationOnMock invocation) throws Throwable
      {
        final Object[] params = invocation.getArguments();
        final byte[] bytes = (byte[]) params[0];

        @SuppressWarnings("boxing")
        final int offset = (Integer) params[1];

        @SuppressWarnings("boxing")
        final int count = (Integer) params[2];
        for (int i = offset; i < offset + count; i++)
        {
          final byte each = bytes[i];
          byteListForWriting.add(Byte.valueOf(each));
        }
        return null;
      }
    }).when(outputStream)
        .write(any(byte[].class), anyInt(), anyInt());

    /* when */
    NioUtil.copy(inputStream, outputStream);

    /* then */
    // System.out.println(format("\n\n# expected:\n%s\n\n# actual:\n%s", this.string,
    // new String(NeoArrays.convertToPrimitive(byteListForWriting.toArray(new Byte[0])))));
    assertThat(
        new String(NeoArrays.convertToPrimitive(byteListForWriting.toArray(new Byte[0])), IoCommonConstants.UTF_8)).isEqualTo(
        this.string);
    // System.out.println(format("\n\n# expected:\n%s\n\n# actual:\n%s", byteListForReading, byteListForWriting));
    assertThat(byteListForWriting).isEqualTo(byteListForReading);

    final InOrder order = inOrder(inputStream);
    order.verify(inputStream, times(computeHowManyForReading(byteListForReading.size(), bufferSize)))
        .read(any(byte[].class), anyInt(), anyInt());
    order.verify(inputStream, atLeastOnce())
        .close();

    final InOrder writeOrder = inOrder(outputStream);
    writeOrder.verify(outputStream, times(computeHowManyForWriting(byteListForWriting.size(), bufferSize)))
        .write(any(byte[].class), anyInt(), anyInt());
    writeOrder.verify(outputStream, atLeastOnce())
        .close();

    reset(outputStream);
    reset(inputStream);
  }

  @Test
  public final void testCopyFileFileFile()
  {
    final File sourceFile = getTestFile();

    /* test */
    NioUtil.copyFile(sourceFile, new File(temporaryFolder.getRoot(), "file4testing2.txt"));

    final File file = new File(temporaryFolder.getRoot(), "file4testing2.txt");

    final List<Byte> expectedByteList = new ArrayList<Byte>();
    final String expectedString = readFile(sourceFile, expectedByteList);

    final List<Byte> resultByteList = new ArrayList<Byte>();
    final String resultString = readFile(file, resultByteList);

    assertThat(resultByteList).isEqualTo(expectedByteList);
    assertThat(resultString.toString()).isEqualTo(expectedString);
  }
}
