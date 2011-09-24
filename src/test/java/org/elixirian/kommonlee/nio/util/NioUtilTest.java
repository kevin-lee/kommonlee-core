/**
 * 
 */
package org.elixirian.kommonlee.nio.util;

import static org.elixirian.kommonlee.test.CommonTestHelper.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elixirian.kommonlee.io.ByteArrayConsumer;
import org.elixirian.kommonlee.io.ByteArrayProducer;
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
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
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
  private StringBuilder stringBuilder;

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
    byteList = new ArrayList<Byte>();
    stringBuilder = new StringBuilder();
    readFile(getTestFile(), byteList, stringBuilder);

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
      NioUtil.readInputStream(this.getClass()
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
      NioUtil.readFile(new File(this.getClass()
          .getResource("/file4testing.txt")
          .getFile()
          .replace("%20", " ")), bufferSize, byteArrayConsumer);

      assertThat(byteArrayConsumer.byteList, is(equalTo(this.byteList)));
      assertThat(byteArrayConsumer.stringBuilder.toString(), is(equalTo(this.stringBuilder.toString())));
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
      NioUtil.writeFile(file, bufferSize, byteArrayProducer);

      final List<Byte> byteList = new ArrayList<Byte>();
      final StringBuilder stringBuilder = new StringBuilder();

      readFile(file, byteList, stringBuilder);
      assertThat(byteList, is(equalTo(this.byteList)));
      assertThat(stringBuilder.toString(), is(equalTo(this.stringBuilder.toString())));

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

      final String expected = this.stringBuilder.toString();
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
    final StringBuilder expectedStringBuilder = new StringBuilder();
    readFile(sourceFile, expectedByteList, expectedStringBuilder);

    final List<Byte> resultByteList = new ArrayList<Byte>();
    final StringBuilder resultStringBuilder = new StringBuilder();
    readFile(file, resultByteList, resultStringBuilder);

    assertThat(resultByteList, is(equalTo(expectedByteList)));
    assertThat(resultStringBuilder.toString(), is(equalTo(expectedStringBuilder.toString())));
  }
}
