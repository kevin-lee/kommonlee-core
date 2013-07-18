package org.elixirian.kommonlee.io;

import static org.fest.assertions.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ByteArrayThreadUnsafeOutputStreamTest
{

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

  private static byte[] getByte()
  {
    final byte[] bytes = new byte[] { Byte.MIN_VALUE, -100, -55, -10, -5, -2, 0, 2, 5, 10, 55, 100, Byte.MAX_VALUE };
    return Arrays.copyOf(bytes, bytes.length);
  }

  @Test
  public final void testByteArrayThreadUnsafeOutputStream() throws Exception
  {
    /* given */
    final int expectedCapacity = ByteArrayThreadUnsafeOutputStream.DEFAULT_INITIAL_CAPACITY;
    final int expectedCount = 0;
    final int expectedIncreaseBy = ByteArrayThreadUnsafeOutputStream.DEFAULT_INCREASE_SIZE_BY;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    final int actualCapacity = byteArrayThreadUnsafeOutputStream.getCapacity();
    final int actualCount = byteArrayThreadUnsafeOutputStream.length();
    final int actualIncreaseBy = byteArrayThreadUnsafeOutputStream.getIncreaseSizeBy();

    /* then */
    assertThat(actualCapacity).isEqualTo(expectedCapacity);
    assertThat(actualCount).isEqualTo(expectedCount);
    assertThat(actualIncreaseBy).isEqualTo(expectedIncreaseBy);
  }

  @Test
  public final void testByteArrayThreadUnsafeOutputStreamInt() throws Exception
  {
    /* given */
    final int expectedCapacity = 111;
    final int expectedCount = 0;
    final int expectedIncreaseBy = ByteArrayThreadUnsafeOutputStream.DEFAULT_INCREASE_SIZE_BY;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream =
      new ByteArrayThreadUnsafeOutputStream(expectedCapacity);
    final int actualCapacity = byteArrayThreadUnsafeOutputStream.getCapacity();
    final int actualCount = byteArrayThreadUnsafeOutputStream.length();
    final int actualIncreaseBy = byteArrayThreadUnsafeOutputStream.getIncreaseSizeBy();

    /* then */
    assertThat(actualCapacity).isEqualTo(expectedCapacity);
    assertThat(actualCount).isEqualTo(expectedCount);
    assertThat(actualIncreaseBy).isEqualTo(expectedIncreaseBy);
  }

  @Test
  public final void testByteArrayThreadUnsafeOutputStreamIntInt() throws Exception
  {
    /* given */
    final int expectedCapacity = 111;
    final int expectedCount = 0;
    final int expectedIncreaseBy = 16;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream =
      new ByteArrayThreadUnsafeOutputStream(expectedCapacity, expectedIncreaseBy);
    final int actualCapacity = byteArrayThreadUnsafeOutputStream.getCapacity();
    final int actualCount = byteArrayThreadUnsafeOutputStream.length();
    final int actualIncreaseBy = byteArrayThreadUnsafeOutputStream.getIncreaseSizeBy();

    /* then */
    assertThat(actualCapacity).isEqualTo(expectedCapacity);
    assertThat(actualCount).isEqualTo(expectedCount);
    assertThat(actualIncreaseBy).isEqualTo(expectedIncreaseBy);
  }

  @Test
  public final void testWriteInt() throws Exception
  {
    /* given */
    final byte[] expected = getByte();
    final int expectedCount = expected.length;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    for (final byte b : expected)
    {
      byteArrayThreadUnsafeOutputStream.write(b);
    }
    final int actualCount = byteArrayThreadUnsafeOutputStream.length();
    final byte[] actual = byteArrayThreadUnsafeOutputStream.toByteArray();

    /* then */
    assertThat(actualCount).isEqualTo(expectedCount);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testWriteByteArray() throws Exception
  {
    /* given */
    final byte[] expected = getByte();
    final int expectedCount = expected.length;
    final byte[] input = Arrays.copyOf(expected, expectedCount);

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    byteArrayThreadUnsafeOutputStream.write(input);
    final int actualCount = byteArrayThreadUnsafeOutputStream.length();
    final byte[] actual = byteArrayThreadUnsafeOutputStream.toByteArray();

    /* then */
    assertThat(actualCount).isEqualTo(expectedCount);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testWriteByteArrayIntInt() throws Exception
  {
    /* given */
    final byte[] expected = getByte();
    final int expectedCount = expected.length;
    final byte[] input = Arrays.copyOf(expected, expectedCount);

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    byteArrayThreadUnsafeOutputStream.write(input, 0, expectedCount);

    final int actualCount = byteArrayThreadUnsafeOutputStream.length();
    final byte[] actual = byteArrayThreadUnsafeOutputStream.toByteArray();

    /* then */
    assertThat(actualCount).isEqualTo(expectedCount);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testWriteByteArrayIntInt2() throws Exception
  {
    /* given */
    final byte[] expected = getByte();
    final int expectedCount = expected.length;
    final byte[] input = Arrays.copyOf(expected, expectedCount);

    /* when */

    for (int howMany = 1; howMany < expectedCount; howMany++)
    {
      final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream =
        new ByteArrayThreadUnsafeOutputStream();
      int off = 0;
      for (off = 0; off + howMany < expectedCount; off += howMany)
      {
        byteArrayThreadUnsafeOutputStream.write(input, off, howMany);
      }
      if (off + howMany >= expectedCount && off < expectedCount)
      {
        byteArrayThreadUnsafeOutputStream.write(input, off, expectedCount - off);
      }
      final int actualCount = byteArrayThreadUnsafeOutputStream.length();
      final byte[] actual = byteArrayThreadUnsafeOutputStream.toByteArray();

      /* then */
      assertThat(actualCount).isEqualTo(expectedCount);
      assertThat(actual).isEqualTo(expected);

    }
  }

  @Test
  public final void testWriteTo() throws Exception
  {
    /* given */
    final byte[] expected = getByte();
    final int expectedLength = expected.length;

    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    for (final byte b : expected)
    {
      byteArrayThreadUnsafeOutputStream.write(b);
    }

    final byte[][] bytesOfBytes = new byte[1][1];
    final int[] offSetAndLength = new int[2];

    final OutputStream outputStream = mock(OutputStream.class);
    doAnswer(new Answer<Void>() {
      @Override
      public Void answer(final InvocationOnMock invocation) throws Throwable
      {
        final Object[] args = invocation.getArguments();
        final byte[] bytes = (byte[]) args[0];
        final int off = (Integer) args[1];
        final int length = (Integer) args[2];

        bytesOfBytes[0] = new byte[length];
        System.arraycopy(bytes, off, bytesOfBytes[0], 0, length);

        offSetAndLength[0] = off;
        offSetAndLength[1] = length;
        return null;
      }
    }).when(outputStream)
        .write(Mockito.any(byte[].class), anyInt(), anyInt());

    /* when */
    byteArrayThreadUnsafeOutputStream.writeTo(outputStream);

    /* then */
    assertThat(bytesOfBytes[0]).isEqualTo(expected);
    assertThat(offSetAndLength[0]).isEqualTo(0);
    assertThat(offSetAndLength[1]).isEqualTo(expectedLength);
  }

  @Test
  public final void testClose() throws Exception
  {
    /* given */
    final int expectedCapacity = ByteArrayThreadUnsafeOutputStream.DEFAULT_INITIAL_CAPACITY;
    final int expectedCount = 0;
    final int expectedIncreaseBy = ByteArrayThreadUnsafeOutputStream.DEFAULT_INCREASE_SIZE_BY;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    byteArrayThreadUnsafeOutputStream.close();

    final int actualCapacity = byteArrayThreadUnsafeOutputStream.getCapacity();
    final int actualCount = byteArrayThreadUnsafeOutputStream.length();
    final int actualIncreaseBy = byteArrayThreadUnsafeOutputStream.getIncreaseSizeBy();

    /* then */
    // close does NOTHING!
    assertThat(actualCapacity).isEqualTo(expectedCapacity);
    assertThat(actualCount).isEqualTo(expectedCount);
    assertThat(actualIncreaseBy).isEqualTo(expectedIncreaseBy);
  }

  @Test
  public final void testReset() throws Exception
  {
    /* given */
    final byte[] expected = new byte[0];
    final int expectedCount = 0;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    for (final byte b : getByte())
    {
      byteArrayThreadUnsafeOutputStream.write(b);
    }
    byteArrayThreadUnsafeOutputStream.reset();
    final int actualCount = byteArrayThreadUnsafeOutputStream.length();
    final byte[] actual = byteArrayThreadUnsafeOutputStream.toByteArray();

    /* then */
    assertThat(actualCount).isEqualTo(expectedCount);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToByteArray() throws Exception
  {
    /* given */
    final byte[] expected = getByte();
    final int expectedCount = expected.length;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    for (final byte b : expected)
    {
      byteArrayThreadUnsafeOutputStream.write(b);
    }
    final int actualCount = byteArrayThreadUnsafeOutputStream.length();
    final byte[] actual = byteArrayThreadUnsafeOutputStream.toByteArray();

    /* then */
    assertThat(actualCount).isEqualTo(expectedCount);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToByteArray2() throws Exception
  {
    /* given */
    final byte[] expected = new byte[0];
    final int expectedCount = 0;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    final int actualCount = byteArrayThreadUnsafeOutputStream.length();
    final byte[] actual = byteArrayThreadUnsafeOutputStream.toByteArray();

    /* then */
    assertThat(actualCount).isEqualTo(expectedCount);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSize() throws Exception
  {
    /* given */
    final byte[] expected = getByte();
    final int expectedSize = expected.length;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    for (final byte b : expected)
    {
      byteArrayThreadUnsafeOutputStream.write(b);
    }
    final int actualSize = byteArrayThreadUnsafeOutputStream.size();

    /* then */
    assertThat(actualSize).isEqualTo(expectedSize);
    assertThat(actualSize).isEqualTo(byteArrayThreadUnsafeOutputStream.length());
  }

  @Test
  public final void testGetIncreaseSizeBy() throws Exception
  {
    /* given */
    final int expectedCapacity = 1;
    final int expectedCount = 0;
    final int expectedIncreaseBy = 999;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream =
      new ByteArrayThreadUnsafeOutputStream(expectedCapacity, expectedIncreaseBy);
    final int actualCapacity = byteArrayThreadUnsafeOutputStream.getCapacity();
    final int actualCount = byteArrayThreadUnsafeOutputStream.length();
    final int actualIncreaseBy = byteArrayThreadUnsafeOutputStream.getIncreaseSizeBy();

    /* then */
    assertThat(actualCapacity).isEqualTo(expectedCapacity);
    assertThat(actualCount).isEqualTo(expectedCount);
    assertThat(actualIncreaseBy).isEqualTo(expectedIncreaseBy);
  }

  @Test
  public final void testToString() throws Exception
  {
    /* given */
    final String expected = "Hello, World";
    final int expectedLength = expected.length();
    final byte[] expectedByte = expected.getBytes();
    final int expectedByteCount = expectedByte.length;
    final byte[] input = Arrays.copyOf(expectedByte, expectedByteCount);

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    byteArrayThreadUnsafeOutputStream.write(input);
    final String actual = byteArrayThreadUnsafeOutputStream.toString();
    final int actualCount = actual.length();

    /* then */
    assertThat(actualCount).isEqualTo(expectedLength);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToStringString() throws Exception
  {
    /* given */
    final String expected = "Hello, World! 안녕!";
    final int expectedLength = expected.length();
    final byte[] expectedByte = expected.getBytes();
    final int expectedByteCount = expectedByte.length;
    final byte[] input = Arrays.copyOf(expectedByte, expectedByteCount);

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    byteArrayThreadUnsafeOutputStream.write(input);
    final String actual = byteArrayThreadUnsafeOutputStream.toString("UTF-8");
    final int actualCount = actual.length();

    /* then */
    assertThat(actualCount).isEqualTo(expectedLength);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToStringString2() throws Exception
  {
    /* given */
    final String expected = "Hello, World! 안녕!";
    final int expectedLength = expected.length();
    final byte[] expectedByte = expected.getBytes(IoCommonConstants.UTF_8);
    final int expectedByteCount = expectedByte.length;
    final byte[] input = Arrays.copyOf(expectedByte, expectedByteCount);

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    byteArrayThreadUnsafeOutputStream.write(input);
    final String actual = byteArrayThreadUnsafeOutputStream.toString("UTF-8");
    final int actualCount = actual.length();

    /* then */
    assertThat(actualCount).isEqualTo(expectedLength);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToStringString3() throws Exception
  {
    /* given */
    final String expected = "Hello, World! 안녕!";
    final int expectedLength = expected.length();
    final byte[] expectedByte = expected.getBytes(IoCommonConstants.ISO_8859_1);
    final int expectedByteCount = expectedByte.length;
    final byte[] input = Arrays.copyOf(expectedByte, expectedByteCount);

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    byteArrayThreadUnsafeOutputStream.write(input);
    final String actual = byteArrayThreadUnsafeOutputStream.toString("UTF-8");
    final int actualCount = actual.length();

    /* then */
    assertThat(actualCount).isEqualTo(expectedLength);
    assertThat(actual).isNotEqualTo(expected);
  }

  @Test
  public final void testToStringString4() throws Exception
  {
    /* given */
    final String expected = "Hello, World! 안녕!";
    final int expectedLength = expected.length();
    final byte[] expectedByte = expected.getBytes(IoCommonConstants.ISO_8859_1);
    final int expectedByteCount = expectedByte.length;
    final byte[] input = Arrays.copyOf(expectedByte, expectedByteCount);

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    byteArrayThreadUnsafeOutputStream.write(input);
    final String actual = byteArrayThreadUnsafeOutputStream.toString("ISO-8859-1");
    final int actualCount = actual.length();

    /* then */
    assertThat(actualCount).isEqualTo(expectedLength);
    assertThat(actual).isNotEqualTo(expected);
  }

  @Test
  public final void testToStringString5() throws Exception
  {
    /* given */
    final String expected = "Hello, World! 안녕!";
    final int expectedLength = new String(expected.getBytes(), IoCommonConstants.ISO_8859_1).length();
    final byte[] expectedByte = expected.getBytes(IoCommonConstants.UTF_8);
    final int expectedByteCount = expectedByte.length;
    final byte[] input = Arrays.copyOf(expectedByte, expectedByteCount);

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    byteArrayThreadUnsafeOutputStream.write(input);
    final String actual = byteArrayThreadUnsafeOutputStream.toString("ISO-8859-1");
    final int actualCount = actual.length();

    /* then */
    assertThat(actualCount).isEqualTo(expectedLength);
    assertThat(actual).isEqualTo(new String(expectedByte, IoCommonConstants.ISO_8859_1));
  }

  @Test
  public final void testToStringCharset() throws Exception
  {
    /* given */
    final String expected = "Hello, World! 안녕!";
    final int expectedLength = expected.length();
    final byte[] expectedByte = expected.getBytes();
    final int expectedByteCount = expectedByte.length;
    final byte[] input = Arrays.copyOf(expectedByte, expectedByteCount);

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    byteArrayThreadUnsafeOutputStream.write(input);
    final String actual = byteArrayThreadUnsafeOutputStream.toString(Charset.forName("UTF-8"));
    final int actualCount = actual.length();

    /* then */
    assertThat(actualCount).isEqualTo(expectedLength);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToStringCharset2() throws Exception
  {
    /* given */
    final String expected = "Hello, World! 안녕!";
    final int expectedLength = expected.length();
    final byte[] expectedByte = expected.getBytes(IoCommonConstants.UTF_8);
    final int expectedByteCount = expectedByte.length;
    final byte[] input = Arrays.copyOf(expectedByte, expectedByteCount);

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    byteArrayThreadUnsafeOutputStream.write(input);
    final String actual = byteArrayThreadUnsafeOutputStream.toString(Charset.forName("UTF-8"));
    final int actualCount = actual.length();

    /* then */
    assertThat(actualCount).isEqualTo(expectedLength);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToStringCharset3() throws Exception
  {
    /* given */
    final String expected = "Hello, World! 안녕!";
    final int expectedLength = expected.length();
    final byte[] expectedByte = expected.getBytes(IoCommonConstants.ISO_8859_1);
    final int expectedByteCount = expectedByte.length;
    final byte[] input = Arrays.copyOf(expectedByte, expectedByteCount);

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    byteArrayThreadUnsafeOutputStream.write(input);
    final String actual = byteArrayThreadUnsafeOutputStream.toString(Charset.forName("UTF-8"));
    final int actualCount = actual.length();

    /* then */
    assertThat(actualCount).isEqualTo(expectedLength);
    assertThat(actual).isNotEqualTo(expected);
  }

  @Test
  public final void testToStringCharset4() throws Exception
  {
    /* given */
    final String expected = "Hello, World! 안녕!";
    final int expectedLength = expected.length();
    final byte[] expectedByte = expected.getBytes(IoCommonConstants.ISO_8859_1);
    final int expectedByteCount = expectedByte.length;
    final byte[] input = Arrays.copyOf(expectedByte, expectedByteCount);

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    byteArrayThreadUnsafeOutputStream.write(input);
    final String actual = byteArrayThreadUnsafeOutputStream.toString(Charset.forName("ISO-8859-1"));
    final int actualCount = actual.length();

    /* then */
    assertThat(actualCount).isEqualTo(expectedLength);
    assertThat(actual).isNotEqualTo(expected);
  }

  @Test
  public final void testToStringCharset5() throws Exception
  {
    /* given */
    final String expected = "Hello, World! 안녕!";
    final int expectedLength = new String(expected.getBytes(), IoCommonConstants.ISO_8859_1).length();
    final byte[] expectedByte = expected.getBytes(IoCommonConstants.UTF_8);
    final int expectedByteCount = expectedByte.length;
    final byte[] input = Arrays.copyOf(expectedByte, expectedByteCount);

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    byteArrayThreadUnsafeOutputStream.write(input);
    final String actual = byteArrayThreadUnsafeOutputStream.toString(Charset.forName("ISO-8859-1"));
    final int actualCount = actual.length();

    /* then */
    assertThat(actualCount).isEqualTo(expectedLength);
    assertThat(actual).isEqualTo(new String(expectedByte, IoCommonConstants.ISO_8859_1));
  }

  @Test
  public final void testToStringInt() throws Exception
  {
    for (int hibyte = 0; hibyte <= 16; hibyte++)
    {
      /* given */
      final String original = "Hello, World! 안녕!";
      final String expected = new String(original.getBytes(), hibyte, 0, original.getBytes().length);
      final int expectedLength = expected.length();
      final byte[] expectedByte = original.getBytes();
      final int expectedByteCount = expectedByte.length;
      final byte[] input = Arrays.copyOf(expectedByte, expectedByteCount);

      /* when */
      final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream =
        new ByteArrayThreadUnsafeOutputStream();
      byteArrayThreadUnsafeOutputStream.write(input);
      final String actual = byteArrayThreadUnsafeOutputStream.toString(hibyte);
      final int actualCount = actual.length();

      /* then */
      assertThat(actualCount).isEqualTo(expectedLength);
      assertThat(actual).isEqualTo(expected);
    }
  }

  @Test
  public final void testIsNotEmpty() throws Exception
  {
    /* given */
    final boolean expected = false;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    final boolean actual = byteArrayThreadUnsafeOutputStream.isNotEmpty();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIsNotEmpty2() throws Exception
  {
    /* given */
    final boolean expected = true;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    for (final byte b : getByte())
    {
      byteArrayThreadUnsafeOutputStream.write(b);
    }
    final boolean actual = byteArrayThreadUnsafeOutputStream.isNotEmpty();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIsEmpty() throws Exception
  {
    /* given */
    final boolean expected = false;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    for (final byte b : getByte())
    {
      byteArrayThreadUnsafeOutputStream.write(b);
    }
    final boolean actual = byteArrayThreadUnsafeOutputStream.isEmpty();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIsEmpty2() throws Exception
  {
    /* given */
    final boolean expected = true;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    final boolean actual = byteArrayThreadUnsafeOutputStream.isEmpty();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testLength() throws Exception
  {
    /* given */
    final byte[] expected = getByte();
    final int expectedLength = expected.length;

    /* when */
    final ByteArrayThreadUnsafeOutputStream byteArrayThreadUnsafeOutputStream = new ByteArrayThreadUnsafeOutputStream();
    for (final byte b : expected)
    {
      byteArrayThreadUnsafeOutputStream.write(b);
    }
    final int actualLength = byteArrayThreadUnsafeOutputStream.length();

    /* then */
    assertThat(actualLength).isEqualTo(expectedLength);
    assertThat(actualLength).isEqualTo(byteArrayThreadUnsafeOutputStream.size());
  }

}
