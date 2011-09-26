/**
 * 
 */
package org.elixirian.kommonlee.io.util;

import static org.elixirian.kommonlee.collect.Lists.*;
import static org.elixirian.kommonlee.test.CommonTestHelper.*;
import static org.elixirian.kommonlee.util.MessageFormatter.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.elixirian.kommonlee.io.ByteArrayConsumer;
import org.elixirian.kommonlee.io.ByteArrayProducer;
import org.elixirian.kommonlee.io.CharArrayConsumer;
import org.elixirian.kommonlee.io.CharArrayProducer;
import org.elixirian.kommonlee.io.IoCommonConstants;
import org.elixirian.kommonlee.io.StringConsumer;
import org.elixirian.kommonlee.io.exception.RuntimeIoException;
import org.elixirian.kommonlee.test.CauseCheckableExpectedException;
import org.elixirian.kommonlee.test.CommonTestHelper.Accessibility;
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
 *     ___  _____
 *    /   \/    /_________  ___ ____ __ ______
 *   /        / /  ___ \  \/  //___// //     /
 *  /        \ /  _____/\    //   //   __   /
 * /____/\____\\_____/   \__//___//___/ /__/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-04-02)
 */
public class IoUtilTest
{
  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @Rule
  public CauseCheckableExpectedException causeCheckableExpectedException = CauseCheckableExpectedException.none();

  private List<Byte> byteList;
  private List<Character> characterList;
  private List<String> stringList;
  private StringBuilder stringBuilder;
  private StringBuilder lineStringBuilder;

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
    byteList = newArrayList();
    characterList = newArrayList();
    stringList = newArrayList();
    stringBuilder = new StringBuilder();
    readFile(getTestFile(), byteList, stringBuilder);

    for (final Byte eachByte : byteList)
    {
      final char c = (char) (eachByte.byteValue());
      characterList.add(Character.valueOf(c));
    }

    lineStringBuilder = new StringBuilder();
    final StringBuilder tmpStringBuilder = new StringBuilder();
    for (final Character eachCharacter : characterList)
    {
      @SuppressWarnings("boxing")
      final char c = eachCharacter;
      if (c == '\n')
      {
        stringList.add(tmpStringBuilder.toString());
        lineStringBuilder.append(tmpStringBuilder.toString());
        tmpStringBuilder.delete(0, tmpStringBuilder.length());
      }
      else
      {
        tmpStringBuilder.append(c);
      }
    }
    if (0 < tmpStringBuilder.length())
    {
      stringList.add(tmpStringBuilder.toString());
      lineStringBuilder.append(tmpStringBuilder.toString());
      tmpStringBuilder.delete(0, tmpStringBuilder.length());
    }
  }

  private void readFile(final File file, final List<Byte> byteList, final StringBuilder stringBuilder)
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
          byteList.add(Byte.valueOf(buffer[i]));
          stringBuilder.append((char) buffer[i]);
        }
        bytesRead = fileInputStream.read(buffer);
      }
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
    return new File(this.getClass()
        .getResource("/file4testing.txt")
        .getFile()
        .replace("%20", " "));
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception
  {
  }

  @Test(expected = IllegalAccessException.class)
  public void testFileUtil() throws Exception
  {
    testNotAccessibleConstructor(IoUtil.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
  }

  @Test
  public void testCloseQuietly()
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
    IoUtil.closeQuietly(closeable);
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
    IoUtil.closeQuietly(closeable);
    assertTrue(called[0]);
  }

  @Test
  public void testAssertBufferSize()
  {
    for (int i = 1; i < 51; i++)
      IoUtil.assertBufferSize(i);
  }

  @Test
  public void testAssertBufferSizeWithNonPositiveInt()
  {
    for (int i = -50; i < 1; i++)
    {
      boolean exceptionThrown = false;
      try
      {
        IoUtil.assertBufferSize(i);
      }
      catch (final IllegalArgumentException e)
      {
        exceptionThrown = true;
      }
      assertTrue(format("expected exception [%s] is not thrown.", IllegalArgumentException.class), exceptionThrown);
    }
  }

  private static class ByteArrayConsumer4Testing implements ByteArrayConsumer
  {
    private final List<Byte> byteList;
    private final StringBuilder stringBuilder;

    public ByteArrayConsumer4Testing(final List<Byte> byteList, final StringBuilder stringBuilder)
    {
      this.byteList = byteList;
      this.stringBuilder = stringBuilder;
    }

    @Override
    public void consume(final byte[] bytes, final int offset, final int count)
    {
      for (int i = offset, size = offset + count; i < size; i++)
      {
        byteList.add(Byte.valueOf(bytes[i]));
        stringBuilder.append((char) bytes[i]);
      }
    }
  }

  @Test
  public void testReadInputStream()
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      final ByteArrayConsumer4Testing byteArrayConsumer =
        new ByteArrayConsumer4Testing(new ArrayList<Byte>(), new StringBuilder());

      /* test */
      IoUtil.readInputStream(this.getClass()
          .getResourceAsStream("/file4testing.txt"), bufferSize, byteArrayConsumer);

      assertThat(byteArrayConsumer.byteList, is(equalTo(this.byteList)));
      assertThat(byteArrayConsumer.stringBuilder.toString(), is(equalTo(this.stringBuilder.toString())));
    }
  }

  @Test
  public void testReadFile()
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      final ByteArrayConsumer4Testing byteArrayConsumer =
        new ByteArrayConsumer4Testing(new ArrayList<Byte>(), new StringBuilder());

      /* test */
      IoUtil.readFile(new File(this.getClass()
          .getResource("/file4testing.txt")
          .getFile()
          .replace("%20", " ")), bufferSize, byteArrayConsumer);

      assertThat(byteArrayConsumer.byteList, is(equalTo(this.byteList)));
      assertThat(byteArrayConsumer.stringBuilder.toString(), is(equalTo(this.stringBuilder.toString())));
    }
  }

  @Test
  public void testReadFileWith0SizeBuffer() throws Exception
  {
    /* given */
    ByteArrayConsumer byteArrayConsumer = null;

    byteArrayConsumer = mock(ByteArrayConsumer.class);
    doAnswer(new Answer<Void>() {
      @Override
      public Void answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return null;
      }
    }).when(byteArrayConsumer)
        .consume(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());

    IoUtil.readFile(getTestFile(), 1, byteArrayConsumer);

    /* expect */
    causeCheckableExpectedException.expect(IllegalArgumentException.class);

    /* when / then: the expected exception should be thrown. */
    IoUtil.readFile(getTestFile(), 0, byteArrayConsumer);

    /* otherwise */
    fail(format("The expected exception [%s] is not thrown.", IllegalArgumentException.class));
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
  public void testWriteOutputStream() throws IOException
  {
    System.out.println("\n### IoUtilTest.testWriteOutputStream()");
    /* given */
    final String expected = this.stringBuilder.toString();
    final byte[] byteArray = new byte[expected.length()];
    final ByteArrayProducer4Testing byteArrayProducer = new ByteArrayProducer4Testing(byteArray);
    for (int i = 0, size = expected.length(); i < size; i++)
    {
      byteArray[i] = (byte) expected.charAt(i);
    }

    final OutputStream outputStream = mock(OutputStream.class);
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      final List<Byte> byteList = new ArrayList<Byte>();
      final StringBuilder stringBuilder = new StringBuilder();

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
          for (int i = offset; i < count; i++)
          {
            final byte each = bytes[i];
            byteList.add(Byte.valueOf(each));
            stringBuilder.append((char) each);
          }
          return null;
        }
      }).when(outputStream)
          .write(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());

      /* when */
      IoUtil.writeOutputStream(outputStream, bufferSize, byteArrayProducer);

      /* then */
      // System.out.println(format("\n\n# expected:\n%s\n\n# actual:\n%s", this.byteList, byteList));
      assertThat(byteList, is(equalTo(this.byteList)));
      // System.out.println(format("\n\n# expected:\n%s\n\n# actual:\n%s", this.stringBuilder, stringBuilder));
      assertThat(stringBuilder.toString(), is(equalTo(this.stringBuilder.toString())));

      verify(outputStream, times((int) Math.ceil((double) expected.length() / bufferSize))).write(
          Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());
      reset(outputStream);
      byteArrayProducer.reset();
    }
  }

  @Test
  public void testWriteFile()
  {
    final File file = new File(temporaryFolder.getRoot(), "file4testing2.txt");

    final String expected = this.stringBuilder.toString();
    final byte[] byteArray = new byte[expected.length()];
    final ByteArrayProducer4Testing byteArrayProducer = new ByteArrayProducer4Testing(byteArray);
    for (int i = 0, size = expected.length(); i < size; i++)
    {
      byteArray[i] = (byte) expected.charAt(i);
    }
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      /* test */
      IoUtil.writeFile(file, bufferSize, byteArrayProducer);

      final List<Byte> byteList = new ArrayList<Byte>();
      final StringBuilder stringBuilder = new StringBuilder();

      readFile(file, byteList, stringBuilder);
      assertThat(byteList, is(equalTo(this.byteList)));
      assertThat(stringBuilder.toString(), is(equalTo(this.stringBuilder.toString())));

      assertTrue(file.delete());
      byteArrayProducer.reset();
    }
  }

  @Test
  public void testWriteFileWith0SizeBuffer()
  {
    /* given */
    File file = null;
    byte[] byteArray = null;
    file = new File(temporaryFolder.getRoot(), "file4testing2.txt");

    final String expected = this.stringBuilder.toString();
    byteArray = new byte[expected.length()];
    for (int i = 0, size = expected.length(); i < size; i++)
    {
      byteArray[i] = (byte) expected.charAt(i);
    }

    final ByteArrayProducer byteArrayProducer = new ByteArrayProducer4Testing(byteArray);

    /* expect */
    causeCheckableExpectedException.expect(IllegalArgumentException.class);

    /* when / then: the expected exception should be thrown. */
    IoUtil.writeFile(file, 0, byteArrayProducer);

    /* otherwise */
    fail(format("The expected exception [%s] is not thrown.", IllegalArgumentException.class));
  }

  private static class CharArrayProducer4Testing implements CharArrayProducer
  {
    private final char[] charArray;
    private int position;
    private int left;

    public CharArrayProducer4Testing(final char[] charArray)
    {
      this.charArray = charArray;
      this.left = charArray.length;
    }

    public void reset()
    {
      this.position = 0;
      this.left = charArray.length;
    }

    @Override
    public int produce(final char[] bytes)
    {
      if (0 < left)
      {
        final int charLength = bytes.length;
        final int count = charLength < left ? charLength : left;
        System.arraycopy(charArray, position, bytes, 0, count);
        position += count;
        left -= count;
        return count;
      }
      return -1;
    }

    @Override
    public int length()
    {
      return charArray.length;
    }
  }

  @Test
  public void testWriteOutputStreamCharArrayProducer() throws IOException
  {
    System.out.println("\n### IoUtilTest.testWriteOutputStreamCharArrayProducer()");
    /* given */
    final String expected = this.stringBuilder.toString();
    final char[] charArray = new char[expected.length()];
    final CharArrayProducer4Testing charArrayProducer = new CharArrayProducer4Testing(charArray);
    for (int i = 0, size = expected.length(); i < size; i++)
    {
      charArray[i] = expected.charAt(i);
    }
    final OutputStream outputStream = mock(OutputStream.class);
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      final List<Character> charList = new ArrayList<Character>();
      final StringBuilder stringBuilder = new StringBuilder();

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
          for (int i = offset; i < count; i++)
          {
            final byte each = bytes[i];
            charList.add(Character.valueOf((char) each));
            stringBuilder.append((char) each);
          }
          return null;
        }
      }).when(outputStream)
          .write(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());

      /* when */
      IoUtil.writeOutputStream(outputStream, bufferSize, IoCommonConstants.UTF_8, charArrayProducer);

      /* then */
      // System.out.println(format("\n\n# expected:\n%s\n\n" + "# actual:\n%s", this.characterList, charList));
      assertThat(charList, is(equalTo(this.characterList)));
      // System.out.println(format("\n\n# expected:\n%s\n\n" + "# actual:\n%s", this.stringBuilder, stringBuilder));
      assertThat(stringBuilder.toString(), is(equalTo(this.stringBuilder.toString())));

      verify(outputStream, times(1)).write(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());
      reset(outputStream);
      charArrayProducer.reset();
    }
  }

  @Test
  public void testWriteFileCharArrayProducer()
  {
    /* given */
    final File file = new File(temporaryFolder.getRoot(), "file4testing2.txt");

    final String expected = this.stringBuilder.toString();
    final char[] charArray = new char[expected.length()];
    final CharArrayProducer4Testing charArrayProducer = new CharArrayProducer4Testing(charArray);
    for (int i = 0, size = expected.length(); i < size; i++)
    {
      charArray[i] = expected.charAt(i);
    }
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      /* when */
      IoUtil.writeFile(file, bufferSize, IoCommonConstants.UTF_8, charArrayProducer);

      /* then */
      final List<Character> charList = new ArrayList<Character>();
      final StringBuilder stringBuilder = new StringBuilder();
      readCharsFromFile(file, charList, stringBuilder);
      assertThat(charList, is(equalTo(this.characterList)));
      // System.out.println(format("\n\n# expected:\n%s\n\n" + "# actual:\n%s", this.stringBuilder, stringBuilder));
      assertThat(stringBuilder.toString(), is(equalTo(this.stringBuilder.toString())));

      assertTrue(file.delete());
      charArrayProducer.reset();
    }
  }

  private void readCharsFromFile(final File file, final List<Character> charList, final StringBuilder stringBuilder)
  {
    InputStreamReader inputStreamReader = null;
    FileInputStream fileInputStream = null;
    try
    {
      fileInputStream = new FileInputStream(file);
      inputStreamReader = new InputStreamReader(fileInputStream);
      final char[] buffer = new char[8192];
      int charsRead = inputStreamReader.read(buffer);
      while (-1 != charsRead)
      {
        for (int i = 0; i < charsRead; i++)
        {
          charList.add(Character.valueOf(buffer[i]));
          stringBuilder.append(buffer[i]);
        }
        charsRead = inputStreamReader.read(buffer);
      }
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
      if (null != inputStreamReader)
      {
        try
        {
          inputStreamReader.close();
        }
        catch (final IOException e)
        {
        }
      }
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

  /**
   * Test method for {@link org.elixirian.kommonlee.io.util.IoUtil#copyFile(java.io.File, java.io.File, int)}.
   * 
   * @throws IOException
   */
  @Test
  public void testCopyFile() throws IOException
  {
    final int bufferSize = 4096;
    final File folder = temporaryFolder.newFolder("testFolder");

    final File sourceFile = getTestFile();

    final File targetFile = new File(folder, "copyOfTestFile");

    IoUtil.copyFile(sourceFile, targetFile, bufferSize);
    assertEquals(sourceFile.length(), targetFile.length());

    final List<Byte> expectedLineList = new ArrayList<Byte>();
    final StringBuilder expectedStringBuilder = new StringBuilder();
    readFile(sourceFile, expectedLineList, expectedStringBuilder);

    final List<Byte> resultList = new ArrayList<Byte>();
    final StringBuilder resultStringBuilder = new StringBuilder();
    readFile(targetFile, resultList, resultStringBuilder);

    assertThat(expectedLineList, is(equalTo(resultList)));
    assertThat(expectedStringBuilder.toString(), is(equalTo(resultStringBuilder.toString())));
  }

  /**
   * Test method for {@link org.elixirian.kommonlee.io.util.IoUtil#copyFile(java.io.File, java.io.File, int)}.
   * 
   * @throws IOException
   */
  @Test(expected = RuntimeIoException.class)
  public void testCopyFileWithNotExistingFile() throws IOException
  {
    IoUtil.copyFile(new File(temporaryFolder.newFolder("testFolder"), "someFileWhichDoesNotExist"), null, 1024);
  }

  private static class CharArrayConsumer4Testing implements CharArrayConsumer
  {
    private final List<Character> characterList;
    private final StringBuilder stringBuilder;

    public CharArrayConsumer4Testing(final List<Character> characterList, final StringBuilder stringBuilder)
    {
      this.characterList = characterList;
      this.stringBuilder = stringBuilder;
    }

    @Override
    public void consume(final char[] chars, final int offset, final int count) throws IOException
    {
      for (int i = offset, size = offset + count; i < size; i++)
      {
        characterList.add(Character.valueOf(chars[i]));
        stringBuilder.append(chars[i]);
      }
    }
  }

  @Test
  public void testReadInputStreamWithCharArrayConsumer()
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      /* given */
      final CharArrayConsumer4Testing charArrayConsumer =
        new CharArrayConsumer4Testing(new ArrayList<Character>(), new StringBuilder());

      /* when */
      IoUtil.readInputStream(this.getClass()
          .getResourceAsStream("/file4testing.txt"), bufferSize, IoCommonConstants.UTF_8, charArrayConsumer);

      /* then */
      // System.out.println();
      // System.out.println(format("# expected:\n%s\n# actual:\n%s", this.characterList,
      // charArrayConsumer.characterList));
      assertThat(charArrayConsumer.characterList, is(equalTo(this.characterList)));
      // System.out.println();
      // System.out.println(format("# expected:\n%s\n# actual:\n%s", this.stringBuilder,
      // charArrayConsumer.stringBuilder));
      assertThat(charArrayConsumer.stringBuilder.toString(), is(equalTo(this.stringBuilder.toString())));
    }
  }

  private static class StringConsumer4Testing implements StringConsumer
  {
    private final List<String> stringList;
    private final StringBuilder stringBuilder;

    public StringConsumer4Testing(final List<String> stringList, final StringBuilder stringBuilder)
    {
      this.stringList = stringList;
      this.stringBuilder = stringBuilder;
    }

    @Override
    public void consume(final String value) throws IOException
    {
      stringList.add(value);
      stringBuilder.append(value);
    }
  }

  @Test
  public void testReadInputStreamWithStringConsumer()
  {
    for (int bufferSize = 1; bufferSize < 128; bufferSize++)
    {
      /* given */
      final StringConsumer4Testing stringArrayConsumer =
        new StringConsumer4Testing(new ArrayList<String>(), new StringBuilder());

      /* when */
      IoUtil.readInputStream(this.getClass()
          .getResourceAsStream("/file4testing.txt"), bufferSize, IoCommonConstants.UTF_8, stringArrayConsumer);

      /* then */
      // System.out.println();
      // System.out.println(format("# expected:\n%s\n# actual:\n%s", this.stringList, stringArrayConsumer.stringList));
      assertThat(stringArrayConsumer.stringList, is(equalTo(this.stringList)));
      // System.out.println();
      // System.out.println(format("# expected:\n%s\n# actual:\n%s", this.lineStringBuilder,
      // stringArrayConsumer.stringBuilder));
      assertThat(stringArrayConsumer.stringBuilder.toString(), is(equalTo(this.lineStringBuilder.toString())));
    }
  }
}
