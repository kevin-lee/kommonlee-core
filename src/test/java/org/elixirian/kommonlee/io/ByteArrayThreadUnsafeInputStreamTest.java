package org.elixirian.kommonlee.io;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ByteArrayThreadUnsafeInputStreamTest
{
  private byte[] expected;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
  }

  private static byte[] getByte()
  {
    final byte[] bytes = new byte[] { Byte.MIN_VALUE, -100, -55, -10, -5, -2, 0, 2, 5, 10, 55, 100, Byte.MAX_VALUE };
    return Arrays.copyOf(bytes, bytes.length);
  }

  @Before
  public void setUp() throws Exception
  {
    expected = getByte();
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @SuppressWarnings("resource")
  @Test
  public final void testByteArrayThreadUnsafeInputStreamByteArray() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    /* when */
    new ByteArrayThreadUnsafeInputStream(input) {
      void _junit_test()
      {
        final byte[] actual = buf;
        /* then */
        assertThat(actual, is(equalTo(expected)));
        assertThat(pos, is(equalTo(0)));
        assertThat(mark, is(equalTo(0)));
        assertThat(count, is(equalTo(expected.length)));
      }
    }._junit_test();
  }

  @SuppressWarnings("resource")
  @Test
  public final void testByteArrayThreadUnsafeInputStreamByteArrayIntInt() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);
    final int offset = 2;
    final int length = 3;

    /* when */
    new ByteArrayThreadUnsafeInputStream(input, offset, length) {
      void _junit_test()
      {
        final byte[] actual = buf;
        /* then */
        assertThat(actual, is(equalTo(expected)));
        assertThat(pos, is(equalTo(offset)));
        assertThat(mark, is(equalTo(offset)));
        assertThat(count, is(equalTo(offset + length)));
      }
    }._junit_test();
  }

  @Test
  public final void testRead() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    for (int i = 0; i < expected.length; i++)
    {
      /* when */
      final int actual = byteArrayThreadUnsafeInputStream.read();

      /* then */
      assertThat("Index: " + i, actual, is(equalTo(expected[i] & 0xFF)));
    }
  }

  @Test
  public final void testReadByteArray() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    final int length = 3;
    final byte[] buffer = new byte[length];

    /* when */
    int howManyRead = byteArrayThreadUnsafeInputStream.read(buffer);
    int current = 0;
    while (-1 < howManyRead)
    {
      for (int i = 0; i < howManyRead; i++)
      {
        /* then */
        assertThat("Index: " + i, buffer[i], is(equalTo(expected[i + current])));
      }
      current += howManyRead;
      howManyRead = byteArrayThreadUnsafeInputStream.read(buffer);
    }
  }

  @Test
  public final void testReadByteArray2() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    final int length = 3;
    final byte[] buffer = new byte[length];

    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    /* when */
    int howManyRead = byteArrayThreadUnsafeInputStream.read(buffer);
    final int current = 0;
    while (-1 < howManyRead)
    {
      byteArrayOutputStream.write(buffer, 0, howManyRead);
      howManyRead = byteArrayThreadUnsafeInputStream.read(buffer);
    }
    /* then */
    assertThat(byteArrayOutputStream.toByteArray(), is(equalTo(expected)));
  }

  @Test
  public final void testReadByteArrayIntInt() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    final int length = 3;

    for (int n = 0; n < length; n++)
    {
      final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
        new ByteArrayThreadUnsafeInputStream(input);
      final byte[] buffer = new byte[length];

      /* when */
      int howManyRead = byteArrayThreadUnsafeInputStream.read(buffer, n, length - n);
      int current = 0;
      while (-1 < howManyRead)
      {
        for (int i = n; i < n + howManyRead; i++)
        {
          /* then */
          assertThat("Index: " + i, buffer[i], is(equalTo(expected[current])));
          current++;
        }
        howManyRead = byteArrayThreadUnsafeInputStream.read(buffer, n, length - n);
      }
    }
  }

  @Test
  public final void testReadByteArrayIntInt2() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    final int length = 3;

    for (int n = 0; n < length; n++)
    {
      final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
        new ByteArrayThreadUnsafeInputStream(input);
      final byte[] buffer = new byte[length];

      /* when */
      int howManyRead = byteArrayThreadUnsafeInputStream.read(buffer, n, length - n);
      final int current = 0;
      while (-1 < howManyRead)
      {
        byteArrayOutputStream.write(buffer, n, howManyRead);
        howManyRead = byteArrayThreadUnsafeInputStream.read(buffer, n, length - n);
      }
      assertThat(byteArrayOutputStream.toByteArray(), is(equalTo(expected)));
    }
  }

  @Test
  public final void testSkip() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    final int howManyToSkip = 3;
    final long skipped = byteArrayThreadUnsafeInputStream.skip(howManyToSkip);

    /* then */
    assertThat(skipped, is(equalTo(3L)));
    assertThat(byteArrayThreadUnsafeInputStream.getCurrentPosition(), is(equalTo(howManyToSkip)));
  }

  @Test
  public final void testSkip2() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    final int howManyToSkip = 3;
    final long skipped = byteArrayThreadUnsafeInputStream.skip(howManyToSkip);

    /* then */
    assertThat(skipped, is(equalTo(3L)));
    assertThat(byteArrayThreadUnsafeInputStream.getCurrentPosition(), is(equalTo(howManyToSkip)));
  }

  @Test
  public final void testAvailable() throws Exception
  {
    /* given */
    final int expected = this.expected.length;
    final byte[] input = Arrays.copyOf(this.expected, expected);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    final int actual = byteArrayThreadUnsafeInputStream.available();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testAvailable2() throws Exception
  {
    /* given */
    final int howManyRead = 3;
    final int expected = this.expected.length - howManyRead;
    final byte[] input = Arrays.copyOf(this.expected, this.expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    for (int i = 0; i < howManyRead; i++)
    {
      byteArrayThreadUnsafeInputStream.read();
    }
    final int actual = byteArrayThreadUnsafeInputStream.available();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testClose() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    byteArrayThreadUnsafeInputStream.close();

    /* then */
    assertThat(byteArrayThreadUnsafeInputStream.getCurrentPosition(), is(equalTo(0)));
  }

  @Test
  public final void testMark() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    byteArrayThreadUnsafeInputStream.mark(0);

    /* then */
    assertThat(byteArrayThreadUnsafeInputStream.getMark(), is(equalTo(0)));
  }

  @Test
  public final void testMark2() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    byteArrayThreadUnsafeInputStream.read();
    byteArrayThreadUnsafeInputStream.mark(0);

    /* then */
    assertThat(byteArrayThreadUnsafeInputStream.getMark(), is(equalTo(1)));

    /* when */
    byteArrayThreadUnsafeInputStream.read();
    byteArrayThreadUnsafeInputStream.read();
    byteArrayThreadUnsafeInputStream.read();
    byteArrayThreadUnsafeInputStream.mark(0);

    /* then */
    assertThat(byteArrayThreadUnsafeInputStream.getMark(), is(equalTo(4)));
  }

  @Test
  public final void testReset() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    byteArrayThreadUnsafeInputStream.read();
    byteArrayThreadUnsafeInputStream.read();
    byteArrayThreadUnsafeInputStream.read();
    assertThat(byteArrayThreadUnsafeInputStream.getCurrentPosition(), is(equalTo(3)));

    byteArrayThreadUnsafeInputStream.reset();

    /* then */
    assertThat(byteArrayThreadUnsafeInputStream.getCurrentPosition(), is(equalTo(0)));
  }

  @Test
  public final void testReset2() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    byteArrayThreadUnsafeInputStream.read();
    byteArrayThreadUnsafeInputStream.read();
    byteArrayThreadUnsafeInputStream.read();
    byteArrayThreadUnsafeInputStream.mark(0);
    assertThat(byteArrayThreadUnsafeInputStream.getCurrentPosition(), is(equalTo(3)));

    byteArrayThreadUnsafeInputStream.read();
    byteArrayThreadUnsafeInputStream.read();
    byteArrayThreadUnsafeInputStream.read();
    assertThat(byteArrayThreadUnsafeInputStream.getCurrentPosition(), is(equalTo(6)));

    byteArrayThreadUnsafeInputStream.reset();

    /* then */
    assertThat(byteArrayThreadUnsafeInputStream.getCurrentPosition(), is(equalTo(3)));
  }

  @Test
  public final void testMarkSupported() throws Exception
  {
    /* given */
    final Boolean expected = Boolean.TRUE;
    final byte[] input = new byte[0];

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    final Boolean actual = byteArrayThreadUnsafeInputStream.markSupported();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testToByteArrayStartingFromMark() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    final byte[] actual = byteArrayThreadUnsafeInputStream.toByteArrayStartingFromMark();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testToByteArrayStartingFromMark2() throws Exception
  {
    /* given */
    final int expectedMarkPosition = 3;
    final int expectedLength = this.expected.length - expectedMarkPosition;
    final byte[] expected = new byte[expectedLength];
    System.arraycopy(this.expected, expectedMarkPosition, expected, 0, expectedLength);
    final byte[] input = Arrays.copyOf(this.expected, this.expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    byteArrayThreadUnsafeInputStream.read();
    byteArrayThreadUnsafeInputStream.read();
    byteArrayThreadUnsafeInputStream.read();
    byteArrayThreadUnsafeInputStream.mark(0);
    final byte[] actual = byteArrayThreadUnsafeInputStream.toByteArrayStartingFromMark();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    assertThat(byteArrayThreadUnsafeInputStream.getMark(), is(equalTo(expectedMarkPosition)));
  }

  @Test
  public final void testToByteArray() throws Exception
  {
    /* given */
    final byte[] input = Arrays.copyOf(expected, expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    final byte[] actual = byteArrayThreadUnsafeInputStream.toByteArray();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testGetCurrentPosition() throws Exception
  {
    /* given */
    final int expected = 0;
    final byte[] input = Arrays.copyOf(this.expected, this.expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    final int actual = byteArrayThreadUnsafeInputStream.getCurrentPosition();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testGetCurrentPosition2() throws Exception
  {
    /* given */
    final int expected = 3;
    final byte[] input = Arrays.copyOf(this.expected, this.expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    for (int i = 0; i < expected; i++)
    {
      byteArrayThreadUnsafeInputStream.read();
    }
    final int actual = byteArrayThreadUnsafeInputStream.getCurrentPosition();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testGetMark() throws Exception
  {
    /* given */
    final int expected = 0;
    final byte[] input = Arrays.copyOf(this.expected, this.expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    final int actual = byteArrayThreadUnsafeInputStream.getMark();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testGetMark2() throws Exception
  {
    /* given */
    final int expected = 0;
    final byte[] input = Arrays.copyOf(this.expected, this.expected.length);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    for (int i = 0; i < 3; i++)
    {
      byteArrayThreadUnsafeInputStream.read();
    }
    final int actual = byteArrayThreadUnsafeInputStream.getMark();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testIsNotEmpty() throws Exception
  {
    /* given */
    final Boolean expected = Boolean.TRUE;
    final int expectedLength = this.expected.length;
    final byte[] input = Arrays.copyOf(this.expected, expectedLength);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    final Boolean actual = byteArrayThreadUnsafeInputStream.isNotEmpty();
    final int actualLength = byteArrayThreadUnsafeInputStream.length();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    assertThat(actualLength, is(not(equalTo(0))));
    assertThat(actualLength, is(equalTo(expectedLength)));
  }

  @Test
  public final void testIsNotEmpty2() throws Exception
  {
    /* given */
    final Boolean expected = Boolean.FALSE;
    final int expectedLength = this.expected.length;
    final byte[] input = new byte[0];

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    final Boolean actual = byteArrayThreadUnsafeInputStream.isNotEmpty();
    final int actualLength = byteArrayThreadUnsafeInputStream.length();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    assertThat(actualLength, is(equalTo(0)));
  }

  @Test
  public final void testIsEmpty() throws Exception
  {
    /* given */
    final Boolean expected = Boolean.TRUE;
    final byte[] input = new byte[0];

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    final Boolean actual = byteArrayThreadUnsafeInputStream.isEmpty();
    final int actualLength = byteArrayThreadUnsafeInputStream.length();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    assertThat(actualLength, is(equalTo(0)));
  }

  @Test
  public final void testIsEmpty2() throws Exception
  {
    /* given */
    final Boolean expected = Boolean.FALSE;
    final int expectedLength = this.expected.length;
    final byte[] input = Arrays.copyOf(this.expected, expectedLength);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    final Boolean actual = byteArrayThreadUnsafeInputStream.isEmpty();
    final int actualLength = byteArrayThreadUnsafeInputStream.length();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    assertThat(actualLength, is(not(equalTo(0))));
    assertThat(actualLength, is(equalTo(expectedLength)));
  }

  @Test
  public final void testLength() throws Exception
  {
    /* given */
    final int expected = this.expected.length;
    final byte[] input = Arrays.copyOf(this.expected, expected);

    final ByteArrayThreadUnsafeInputStream byteArrayThreadUnsafeInputStream =
      new ByteArrayThreadUnsafeInputStream(input);

    /* when */
    final int actual = byteArrayThreadUnsafeInputStream.length();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }
}
