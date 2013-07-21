package org.elixirian.kommonlee.collect.primitive.mutable;

import static org.elixirian.kommonlee.util.collect.Lists.*;
import static org.fest.assertions.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import org.elixirian.kommonlee.collect.KollectionUtil;
import org.elixirian.kommonlee.test.CauseCheckableExpectedException;
import org.elixirian.kommonlee.type.functional.BreakOrContinue;
import org.elixirian.kommonlee.type.functional.primitive.BreakableByteFunction1;
import org.elixirian.kommonlee.type.functional.primitive.ByteCondition1;
import org.elixirian.kommonlee.type.functional.primitive.ByteToByteFunction1;
import org.elixirian.kommonlee.type.functional.primitive.VoidByteFunction1;
import org.elixirian.kommonlee.util.NeoArrays;
import org.elixirian.kommonlee.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class MutableByteArrayListTest
{
  @Rule
  public CauseCheckableExpectedException causeCheckableExpectedException = CauseCheckableExpectedException.none();

  @BeforeClass
  public static void setUpBeforeClass()
  {
  }

  @AfterClass
  public static void tearDownAfterClass()
  {
  }

  @Before
  public void setUp()
  {
  }

  @After
  public void tearDown()
  {
  }

  private static byte[] getBytes()
  {
    return new byte[] { Byte.MIN_VALUE, -100, -55, -10, -5, -2, -1, 0, 1, 2, 5, 10, 55, 100, Byte.MAX_VALUE };
  }

  @Test
  public final void testMutableByteArrayListByteArrayIntInt()
  {
    /* given */
    final byte[] elements = getBytes();
    final int expectedLength = 5;
    final byte[] expectedBytes = Arrays.copyOf(elements, expectedLength);
    final int expectedIncreaseBy = 16;

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(elements, expectedLength, expectedIncreaseBy);
    final byte[] actualBytes = mutableByteList.toArray();
    final int actualLength = mutableByteList.length();
    final int actualIncreaseBy = mutableByteList.getIncreaseBy();

    /* then */
    assertThat(actualBytes).isEqualTo(expectedBytes);
    assertThat(actualLength).isEqualTo(expectedLength);
    assertThat(actualIncreaseBy).isEqualTo(expectedIncreaseBy);
  }

  @Test
  public final void testMutableByteArrayListIntInt()
  {
    /* given */
    final byte[] expectedBytes = new byte[0];
    final int expectedLength = 0;
    final int initialCapacity = 5;
    final int expectedIncreaseBy = 16;

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(initialCapacity, expectedIncreaseBy);
    final byte[] actualBytes = mutableByteList.toArray();
    final int actualLength = mutableByteList.length();
    final int actualIncreaseBy = mutableByteList.getIncreaseBy();

    /* then */
    assertThat(actualBytes).isEqualTo(expectedBytes);
    assertThat(actualLength).isEqualTo(expectedLength);
    assertThat(actualIncreaseBy).isEqualTo(expectedIncreaseBy);
  }

  @Test
  public final void testMutableByteArrayListByteArray()
  {
    /* given */
    final byte[] elements = getBytes();
    final int expectedLength = elements.length;
    final byte[] expectedBytes = Arrays.copyOf(elements, expectedLength);
    final int expectedIncreaseBy = MutableByteArrayList.DEFAULT_INCREASE_BY;

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(elements);
    final byte[] actualBytes = mutableByteList.toArray();
    final int actualLength = mutableByteList.length();
    final int actualIncreaseBy = mutableByteList.getIncreaseBy();

    /* then */
    assertThat(actualBytes).isEqualTo(expectedBytes);
    assertThat(actualLength).isEqualTo(expectedLength);
    assertThat(actualIncreaseBy).isEqualTo(expectedIncreaseBy);
  }

  @Test
  public final void testMutableByteArrayList()
  {
    /* given */
    final int expectedLength = 0;
    final byte[] expectedBytes = new byte[0];
    final int expectedIncreaseBy = MutableByteArrayList.DEFAULT_INCREASE_BY;

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList();
    final byte[] actualBytes = mutableByteList.toArray();
    final int actualLength = mutableByteList.length();
    final int actualIncreaseBy = mutableByteList.getIncreaseBy();

    /* then */
    assertThat(actualBytes).isEqualTo(expectedBytes);
    assertThat(actualLength).isEqualTo(expectedLength);
    assertThat(actualIncreaseBy).isEqualTo(expectedIncreaseBy);
  }

  @Test
  public final void testGet()
  {
    /* given */
    final byte[] elements = getBytes();
    final int expectedLength = elements.length;
    final byte[] expectedBytes = Arrays.copyOf(elements, expectedLength);

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(elements);
    final int actualLength = mutableByteList.length();

    /* then */
    for (int i = 0; i < actualLength; i++)
    {
      assertThat(mutableByteList.get(i)).isEqualTo(expectedBytes[i]);
    }
    assertThat(actualLength).isEqualTo(expectedLength);
  }

  @Test
  public final void testIndexOfByteInt()
  {
    /* given */
    final byte[] elements = { -100, 1, 3, 10, -100, 5, 1 };

    final int expectedLength = elements.length;

    final byte[] targets = { -100, 1, 3, 10, 5 };
    final int[][] indicesArray =
      { { 0, 2, 5, elements.length - 1 }, { 0, 2, elements.length - 1 }, { 0, 1, 3, elements.length - 1 },
          { 0, 2, 4, elements.length - 1 }, { 0, 3, 5, elements.length - 1 } };
    final int[][] expectedIndicesArray =
      { { 0, 4, -1, -1 }, { 1, 6, 6 }, { 2, 2, -1, -1 }, { 3, 3, -1, -1 }, { 5, 5, 5, -1 } };

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(Arrays.copyOf(elements, expectedLength));
    final int actualLength = mutableByteList.length();

    /* then */
    for (int i = 0; i < targets.length; i++)
    {
      final int[] indices = indicesArray[i];
      final int[] expectedIndices = expectedIndicesArray[i];
      for (int index = 0; index < indices.length; index++)
      {
        assertThat(mutableByteList.indexOf(targets[i], indices[index])).describedAs("i: " + i + ", index: " + index)
            .isEqualTo(expectedIndices[index]);
      }
    }
    assertThat(actualLength).isEqualTo(expectedLength);
  }

  @Test
  public final void testIndexOfByte()
  {
    /* given */
    final byte[] elements = getBytes();
    final int expectedLength = elements.length;
    final byte[] expectedBytes = Arrays.copyOf(elements, expectedLength);

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(Arrays.copyOf(elements, expectedLength));
    final int actualLength = mutableByteList.length();

    /* then */
    for (int i = 0; i < expectedLength; i++)
    {
      assertThat(mutableByteList.indexOf(expectedBytes[i])).isEqualTo(i);
    }
    assertThat(actualLength).isEqualTo(expectedLength);
  }

  @Test
  public final void testLastIndexOfByteInt()
  {
    /* given */
    final byte[] elements = { -100, 1, 3, 10, -100, 5, 1, 1 };

    final int expectedLength = elements.length;

    final byte[] targets = { -100, 1, 3, 10, 5 };
    final int[][] indicesArray =
      { { elements.length, 5, 2, 1 }, { elements.length, 2, 1 }, { elements.length, 3, 1 }, { elements.length, 4, 2 },
          { elements.length, 6, 5, 3 } };
    final int[][] expectedIndicesArray = { { 4, 4, 0, 0 }, { 7, 1, -1 }, { 2, 2, -1 }, { 3, 3, -1 }, { 5, 5, -1, -1 } };

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(Arrays.copyOf(elements, expectedLength));
    final int actualLength = mutableByteList.length();

    /* then */
    for (int i = 0; i < targets.length; i++)
    {
      final int[] indices = indicesArray[i];
      final int[] expectedIndices = expectedIndicesArray[i];
      for (int index = 0; index < indices.length; index++)
      {
        assertThat(mutableByteList.lastIndexOf(targets[i], indices[index])).describedAs("i: " + i + ", index: " + index)
            .isEqualTo(expectedIndices[index]);
      }
    }
    assertThat(actualLength).isEqualTo(expectedLength);
  }

  @Test
  public final void testLastIndexOfByte()
  {
    /* given */
    final byte[] elements = { -100, 1, 3, 10, -100, 5, 1, 1 };

    final int expectedLength = elements.length;

    final byte[] targets = { -100, 1, 3, 10, 5 };
    final int[] expectedIndices = { 4, 7, 2, 3, 5 };

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(Arrays.copyOf(elements, expectedLength));
    final int actualLength = mutableByteList.length();

    /* then */
    for (int i = 0; i < targets.length; i++)
    {
      assertThat(mutableByteList.lastIndexOf(targets[i])).describedAs("i: " + i)
          .isEqualTo(expectedIndices[i]);
    }
    assertThat(actualLength).isEqualTo(expectedLength);
  }

  @Test
  public final void testExists()
  {
    /* given */
    final byte[] elements = { Byte.MIN_VALUE, -100, -55, -10, -5, -2, -1, 0, 1, 2, 5, 10, 55, 100, Byte.MAX_VALUE };
    final int expectedLength = elements.length;
    final byte[] targets = { Byte.MIN_VALUE, -111, -55, -22, -5, 0, 3, 10, 100, 123, Byte.MAX_VALUE };
    final boolean[] expected = { true, false, true, false, true, true, false, true, true, false, true };

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(elements);
    final int actualLength = mutableByteList.length();

    /* then */
    for (int i = 0; i < targets.length; i++)
    {
      final int index = i;
      assertThat(mutableByteList.exists(new ByteCondition1() {
        @Override
        public boolean isMet(final byte input)
        {
          return input == targets[index];
        }
      })).isEqualTo(expected[i]);
    }
    assertThat(actualLength).isEqualTo(expectedLength);
  }

  @Test
  public final void testContains()
  {
    /* given */
    final byte[] elements = { Byte.MIN_VALUE, -100, -55, -10, -5, -2, -1, 0, 1, 2, 5, 10, 55, 100, Byte.MAX_VALUE };
    final int expectedLength = elements.length;
    final byte[] targets = { Byte.MIN_VALUE, -111, -55, -22, -5, 0, 3, 10, 100, 123, Byte.MAX_VALUE };
    final boolean[] expected = { true, false, true, false, true, true, false, true, true, false, true };

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(elements);
    final int actualLength = mutableByteList.length();

    /* then */
    for (int i = 0; i < targets.length; i++)
    {
      assertThat(mutableByteList.contains(targets[i])).isEqualTo(expected[i]);
    }
    assertThat(actualLength).isEqualTo(expectedLength);
  }

  @Test
  public final void testContainsAllByteArray()
  {
    /* given */
    final byte[] elements = { Byte.MIN_VALUE, -100, -55, -10, -5, -2, -1, 0, 1, 2, 5, 10, 55, 100, Byte.MAX_VALUE };
    final int expectedLength = elements.length;
    final byte[] targets = Arrays.copyOfRange(elements, 3, expectedLength);

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(elements);

    /* then */
    assertThat(mutableByteList.containsAll(targets)).isTrue();
  }

  @Test
  public final void testContainsAllByteArray2()
  {
    /* given */
    final byte[] elements = { Byte.MIN_VALUE, -100, -55, -10, -5, -2, -1, 0, 1, 2, 5, 10, 55, 100, Byte.MAX_VALUE };
    final int expectedLength = elements.length;
    final byte[] targets = new byte[expectedLength];

    System.arraycopy(elements, 0, targets, 0, expectedLength - 1);
    targets[expectedLength - 1] = 8;

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(elements);

    /* then */
    assertThat(mutableByteList.containsAll(targets)).isFalse();
  }

  @Test
  public final void testContainsAllByteList()
  {
    /* given */
    final byte[] elements = { Byte.MIN_VALUE, -100, -55, -10, -5, -2, -1, 0, 1, 2, 5, 10, 55, 100, Byte.MAX_VALUE };
    final int expectedLength = elements.length;
    final MutableByteList targets = new MutableByteArrayList(Arrays.copyOfRange(elements, 3, expectedLength));

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(elements);

    /* then */
    assertThat(mutableByteList.containsAll(targets)).isTrue();
  }

  @Test
  public final void testContainsAllByteList2()
  {
    /* given */
    final byte[] elements = { Byte.MIN_VALUE, -100, -55, -10, -5, -2, -1, 0, 1, 2, 5, 10, 55, 100, Byte.MAX_VALUE };
    final int expectedLength = elements.length;
    final MutableByteList targets =
      new MutableByteArrayList(NeoArrays.convertToPrimitive(Arrays.asList(NeoArrays.convertToBoxedPrimitive(elements))
          .subList(0, expectedLength - 3)
          .toArray(new Byte[0])));
    targets.add((byte) 77);

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(elements);

    /* then */
    assertThat(mutableByteList.containsAll(targets)).isFalse();
  }

  @Test
  public final void testSelect()
  {
    /* given */
    final byte[] elements = { Byte.MIN_VALUE, -100, -55, -10, -5, -2, -1, 0, 1, 2, 5, 10, 55, 100, Byte.MAX_VALUE };

    final MutableByteList expected = new MutableByteArrayList();
    for (final byte b : elements)
    {
      if (0 < b)
      {
        expected.add(b);
      }
    }

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(elements);
    final MutableByteList actual = mutableByteList.select(new ByteCondition1() {
      @Override
      public boolean isMet(final byte input)
      {
        return 0 < input;
      }
    });

    /* then */
    assertThat(actual).describedAs(
        "actual: " + Objects.toStringOf(actual) + "\nexpected: " + Objects.toStringOf(expected))
        .isEqualTo(expected);
    assertThat(actual.toArray()).describedAs(
        "actual: " + Objects.toStringOf(actual) + "\nexpected: " + Objects.toStringOf(expected))
        .isEqualTo(expected.toArray());
    assertThat(actual.hashCode()).isEqualTo(expected.hashCode());
  }

  @Test
  public final void testMapToByte()
  {
    /* given */
    final byte[] elements = { -10, -5, -2, -1, 0, 1, 2, 5, 10 };

    final MutableByteList expected = new MutableByteArrayList();
    for (final byte b : elements)
    {
      expected.add((byte) (b << 1));
    }

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(elements);
    final MutableByteList actual = mutableByteList.mapToByte(new ByteToByteFunction1() {
      @Override
      public byte apply(final byte input)
      {
        return (byte) (input * 2);
      }
    });

    /* then */
    assertThat(actual).describedAs("actual: " + actual + "\nexpected: " + expected)
        .isEqualTo(expected);
    assertThat(actual.toArray()).describedAs(
        "actual: " + Objects.toStringOf(actual) + "\nexpected: " + Objects.toStringOf(expected))
        .isEqualTo(expected.toArray());
    assertThat(actual.hashCode()).isEqualTo(expected.hashCode());
  }

  @Test
  public final void testMapToByteSelectively()
  {
    /* given */
    final byte[] elements = { -10, -5, -2, -1, 0, 1, 2, 5, 10 };

    final MutableByteList expected = new MutableByteArrayList();
    for (final byte b : elements)
    {
      if (0 < b)
      {
        expected.add((byte) (b << 1));
      }
    }

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(elements);
    final MutableByteList actual = mutableByteList.mapToByteSelectively(new ByteCondition1() {
      @Override
      public boolean isMet(final byte input)
      {
        return 0 < input;
      }
    }, new ByteToByteFunction1() {
      @Override
      public byte apply(final byte input)
      {
        return (byte) (input * 2);
      }
    });

    /* then */
    assertThat(actual).describedAs("actual: " + actual + "\nexpected: " + expected)
        .isEqualTo(expected);
    assertThat(actual.toArray()).describedAs(
        "actual: " + Objects.toStringOf(actual) + "\nexpected: " + Objects.toStringOf(expected))
        .isEqualTo(expected.toArray());
    assertThat(actual.hashCode()).isEqualTo(expected.hashCode());
  }

  @Test
  public final void testForEach()
  {
    /* given */
    final byte[] elements = { -10, -5, -2, -1, 0, 1, 2, 5, 10 };

    int expected = 0;
    for (final byte b : elements)
    {
      expected += b;
    }

    /* when */
    final int[] total = new int[] { 0 };

    final MutableByteList mutableByteList = new MutableByteArrayList(20, 8);
    mutableByteList.addAll(elements);

    mutableByteList.forEach(new VoidByteFunction1() {
      @Override
      public void apply(final byte input)
      {
        total[0] += input;
      }
    });
    final int actual = total[0];

    /* then */
    assertThat(actual).describedAs("actual: " + actual + "\nexpected: " + expected)
        .isEqualTo(expected);
  }

  @Test
  public final void testForEach2()
  {
    /* given */
    final byte[] elements = { -10, -5, -2, -1, 1, 2, 5, 10 };

    int expected = 1;
    for (final byte b : elements)
    {
      expected *= b;
    }

    /* when */

    final int[] total = new int[] { 1 };

    final MutableByteList mutableByteList = new MutableByteArrayList(30, 8);
    mutableByteList.addAll(elements);

    mutableByteList.forEach(new VoidByteFunction1() {
      @Override
      public void apply(final byte input)
      {
        total[0] *= input;
      }
    });
    final int actual = total[0];

    /* then */
    assertThat(actual).describedAs("actual: " + actual + "\nexpected: " + expected)
        .isEqualTo(expected);
  }

  @Test
  public final void testBreakableForEach()
  {
    /* given */
    final byte[] elements = { -10, -5, -2, -1, 0, 1, 2, 5, -5, -3, 10 - 11 };

    final List<Byte> expected = new ArrayList<Byte>();
    for (final byte b : elements)
    {
      if (0 <= b)
      {
        break;
      }
      expected.add(Byte.valueOf(b));
    }
    final MutableByteList mutableByteList = new MutableByteArrayList(20, 8);
    mutableByteList.addAll(elements);

    /* when */

    final List<Byte> actual = new ArrayList<Byte>();
    mutableByteList.breakableForEach(new BreakableByteFunction1() {
      @Override
      public BreakOrContinue apply(final byte input)
      {
        if (0 <= input)
        {
          return BreakOrContinue.BREAK;
        }
        actual.add(Byte.valueOf(input));
        return BreakOrContinue.CONTINUE;
      }
    });

    /* then */
    assertThat(actual).describedAs("actual: " + actual + "\nexpected: " + expected)
        .isEqualTo(expected);
  }

  @Test
  public final void testHowMany()
  {
    /* given */
    final byte[] elements = { -10, -5, -2, -1, 0, 1, 2, 5, 10, -11, 99, -111, 50 };

    int expected = 0;
    for (final byte b : elements)
    {
      if (0 < b)
      {
        expected++;
      }
    }
    final MutableByteList mutableByteList = new MutableByteArrayList(20, 8);
    mutableByteList.addAll(elements);

    /* when */

    final int actual = mutableByteList.howMany(new ByteCondition1() {
      @Override
      public boolean isMet(final byte input)
      {
        return 0 < input;
      }
    });

    /* then */
    assertThat(actual).describedAs("actual: " + actual + "\nexpected: " + expected)
        .isEqualTo(expected);
  }

  @Test
  public final void testSubList()
  {
    /* given */
    final byte[] elements = getBytes();
    final int from = 2;
    final int to = 5;
    final MutableByteList expected =
      new MutableByteArrayList(NeoArrays.convertToPrimitive(Arrays.asList(NeoArrays.convertToBoxedPrimitive(elements))
          .subList(from, to)
          .toArray(new Byte[0])));
    final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);
    mutableByteList.addAll(elements);

    /* when */
    final MutableByteList actual = mutableByteList.subList(from, to);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToArray()
  {
    /* given */
    final byte[] elements = getBytes();
    final byte[] expected = Arrays.copyOf(elements, elements.length);
    final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);
    mutableByteList.addAll(elements);

    /* when */
    final byte[] actual = mutableByteList.toArray();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIterator()
  {
    /* given */
    final byte[] elements = getBytes();
    final Iterator<Byte> expected = Arrays.asList(NeoArrays.convertToBoxedPrimitive(elements))
        .iterator();
    final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);
    mutableByteList.addAll(elements);

    /* when */
    final Iterator<Byte> actual = mutableByteList.iterator();

    /* then */
    assertThat(KollectionUtil.equalElements(actual, expected)).isTrue();
  }

  @Test
  public final void testIteratorWithRemoving()
  {
    /* given */
    final byte[] elements = getBytes();
    final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);
    mutableByteList.addAll(elements);

    /* expect */
    causeCheckableExpectedException.expect(IllegalStateException.class);

    /* when */
    final Iterator<Byte> actual = mutableByteList.iterator();
    actual.remove();

    /* otherwise */
    fail("IllegalStateException is not thrown when iterator.remove() is called with never calling its next() method.");
  }

  @Test
  public final void testIteratorWithRemoving2()
  {
    /* given */
    final byte[] elements = getBytes();
    final Iterator<Byte> expected = newArrayList(NeoArrays.convertToBoxedPrimitive(elements)).iterator();
    final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);
    mutableByteList.addAll(elements);

    /* when */
    final Iterator<Byte> actual = mutableByteList.iterator();
    actual.next();
    actual.remove();
    expected.next();
    expected.remove();

    /* then */
    assertThat(mutableByteList.length()).isEqualTo(elements.length - 1);
    assertThat(KollectionUtil.equalElements(actual, expected)).isTrue();
  }

  @Test
  public final void testIteratorWithRemoving3()
  {
    /* given */
    final byte[] elements = getBytes();
    final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);
    mutableByteList.addAll(elements);

    /* expect */
    causeCheckableExpectedException.expect(ConcurrentModificationException.class);

    /* when */
    for (final Byte b : mutableByteList)
    {
      mutableByteList.remove(b.byteValue());
    }

    /* Otherwise */
    fail("ConcurrentModificationException is not thrown when removing element inside for(foreach) loop.");
  }

  @Test
  public final void testLength()
  {
    /* given */
    final byte[] elements = getBytes();
    final int expected = elements.length;

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(100, 8);
    mutableByteList.addAll(elements);
    final int actual = mutableByteList.length();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testLength2()
  {
    /* given */
    final int expected = 0;

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(100, 8);
    final int actual = mutableByteList.length();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSize()
  {
    /* given */
    final byte[] elements = getBytes();
    final int expected = elements.length;

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(100, 8);
    mutableByteList.addAll(elements);
    final int actual = mutableByteList.size();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSize2()
  {
    /* given */
    final int expected = 0;

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(100, 8);
    final int actual = mutableByteList.size();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIsEmpty()
  {
    /* given */
    final MutableByteList mutableByteList = new MutableByteArrayList(100, 8);

    /* when */
    final boolean actual = mutableByteList.isEmpty();

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsEmpty2()
  {
    /* given */
    final byte[] elements = getBytes();

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(100, 8);
    mutableByteList.addAll(elements);
    final boolean actual = mutableByteList.isEmpty();

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNotEmpty()
  {
    /* given */
    final byte[] elements = getBytes();

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(100, 8);
    mutableByteList.addAll(elements);
    final boolean actual = mutableByteList.isNotEmpty();

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNotEmpty2()
  {
    /* given */
    final MutableByteList mutableByteList = new MutableByteArrayList(100, 8);

    /* when */
    final boolean actual = mutableByteList.isNotEmpty();

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testToBoxedArray()
  {
    /* given */
    final byte[] elements = getBytes();
    final Byte[] expected = NeoArrays.convertToBoxedPrimitive(elements);

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);
    mutableByteList.addAll(elements);
    final Byte[] actual = mutableByteList.toBoxedArray();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testConvertTo()
  {
    /* given */
    final byte[] elements = getBytes();
    final List<Byte> expected = Arrays.asList(NeoArrays.convertToBoxedPrimitive(elements));

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);
    mutableByteList.addAll(elements);
    final List<Byte> actual = mutableByteList.convertTo();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testAdd()
  {
    /* given */
    final byte[] elements = getBytes();
    final byte[] expected = Arrays.copyOf(elements, elements.length);

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);
    for (final byte b : elements)
    {
      final boolean result = mutableByteList.add(b);

      /* then */
      assertThat(result).isTrue();
    }

    /* then */
    final byte[] actual = mutableByteList.toArray();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testAddAllByteArray()
  {
    /* given */
    final byte[] elements = getBytes();
    final byte[] expected = Arrays.copyOf(elements, elements.length);

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);
    final boolean result = mutableByteList.addAll(elements);

    /* then */
    final byte[] actual = mutableByteList.toArray();
    assertThat(actual).isEqualTo(expected);
    assertThat(result).isTrue();
  }

  @Test
  public final void testAddAllByteList()
  {
    /* given */
    final byte[] elements = getBytes();
    final byte[] expected = Arrays.copyOf(elements, elements.length);
    final MutableByteList input = new MutableByteArrayList(elements);
    final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);

    /* when */
    final boolean result = mutableByteList.addAll(input);

    /* then */
    final byte[] actual = mutableByteList.toArray();
    assertThat(actual).isEqualTo(expected);
    assertThat(result).isTrue();
  }

  @Test
  public final void testRemove()
  {
    /* given */
    final byte[] elements = getBytes();
    final byte[] expected = Arrays.copyOf(elements, elements.length);

    final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);
    mutableByteList.addAll(elements);

    assertThat(mutableByteList.length()).isEqualTo(elements.length);
    for (int i = 0; i < elements.length; i++)
    {
      /* when */
      final boolean result = mutableByteList.remove(elements[i]);

      /* then */
      assertThat(result).isTrue();
      assertThat(mutableByteList.length()).isEqualTo(expected.length - i - 1);
      assertThat(mutableByteList.toArray()).isEqualTo(Arrays.copyOfRange(expected, i + 1, elements.length));
    }
    assertThat(mutableByteList.isEmpty()).isTrue();
  }

  @Test
  public final void testRemoveAt()
  {
    /* given */
    final byte[] elements = getBytes();
    final List<Byte> expected = Arrays.asList(NeoArrays.convertToBoxedPrimitive(elements));

    for (int i = 0; i < elements.length; i++)
    {
      final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);
      mutableByteList.addAll(elements);
      assertThat(mutableByteList.length()).isEqualTo(elements.length);

      /* when */
      final byte actual = mutableByteList.removeAt(i);

      /* then */
      assertThat(actual).isEqualTo(expected.get(i));
      assertThat(mutableByteList.length()).isEqualTo(expected.size() - 1);
      final ArrayList<Byte> expectedList = newArrayList(expected.toArray(new Byte[0]));
      expectedList.remove(i);
      assertThat(mutableByteList.toArray()).isEqualTo(NeoArrays.convertToPrimitive(expectedList.toArray(new Byte[0])));
    }
  }

  @Test
  public final void testRemoveAtWithInvalidIndex()
  {
    /* given */
    final byte[] elements = getBytes();
    final int invalidIndex = -1;

    final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);
    mutableByteList.addAll(elements);
    assertThat(mutableByteList.length()).isEqualTo(elements.length);

    /* expect */
    causeCheckableExpectedException.expect(IndexOutOfBoundsException.class);

    /* when */
    mutableByteList.removeAt(invalidIndex);

    /* otherwise */
    fail("IndexOutOfBoundsException was not thrown for invalid index. index: " + invalidIndex);
  }

  @Test
  public final void testRemoveAtWithInvalidIndex2()
  {
    /* given */
    final byte[] elements = getBytes();
    final int invalidIndex = elements.length;

    final MutableByteList mutableByteList = new MutableByteArrayList(50, 8);
    mutableByteList.addAll(elements);
    assertThat(mutableByteList.length()).isEqualTo(elements.length);

    /* expect */
    causeCheckableExpectedException.expect(IndexOutOfBoundsException.class);

    /* when */
    mutableByteList.removeAt(invalidIndex);

    /* otherwise */
    fail("IndexOutOfBoundsException was not thrown for invalid index. index: " + invalidIndex);
  }

  @Test
  public final void testTrim()
  {
    /* given */
    final byte[] elements = getBytes();

    final int initialCapacity = 100;
    /* when */
    final MutableByteArrayList mutableByteList = new MutableByteArrayList(initialCapacity, 10);
    mutableByteList.addAll(elements);

    assertThat(mutableByteList.getCapacity()).isEqualTo(initialCapacity);
    mutableByteList.trim();

    /* then */
    assertThat(mutableByteList.getCapacity()).isEqualTo(elements.length);
  }

  @Test
  public final void testClear()
  {
    /* given */
    final byte[] elements = getBytes();

    /* when */
    final MutableByteList mutableByteList = new MutableByteArrayList(elements);
    assertThat(mutableByteList.length()).isEqualTo(elements.length);
    mutableByteList.clear();

    /* then */
    assertThat(mutableByteList.length()).isEqualTo(0);
  }

}
