package org.elixirian.kommonlee.collect.immutable;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.elixirian.kommonlee.collect.KollectionUtil;
import org.elixirian.kommonlee.functional.BreakableFunction1;
import org.elixirian.kommonlee.functional.IndexedBreakableFunction1;
import org.elixirian.kommonlee.functional.IndexedVoidFunction1;
import org.elixirian.kommonlee.functional.VoidFunction1;
import org.elixirian.kommonlee.io.util.IoUtil;
import org.elixirian.kommonlee.test.CauseCheckableExpectedException;
import org.elixirian.kommonlee.type.functional.BreakOrContinue;
import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.functional.Function1;
import org.elixirian.kommonlee.type.functional.Function2;
import org.elixirian.kommonlee.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ImmutableArrayListTest
{
  @Rule
  public CauseCheckableExpectedException causeCheckableExpectedException = CauseCheckableExpectedException.none();

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

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

  @Test
  public final void testHashCode()
  {
    /* given */
    final int expected = 1;
    final ImmutableArrayList<Integer> list = ImmutableArrayList.emptyList();

    /* when */
    final int actual = list.hashCode();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testHashCode2()
  {
    /* given */
    final int expected = 1;
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(new Integer[0]);

    /* when */
    final int actual = list.hashCode();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testHashCode3()
  {
    /* given */
    final Integer[] elements = { 1, 2, 3 };
    final int expected = Objects.hash(elements);
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final int actual = list.hashCode();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIsEmpty1_1()
  {
    /* given */
    final Integer[] elements = {};
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final boolean actual = list.isEmpty();

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsEmpty1_2()
  {
    /* given */
    final ImmutableArrayList<Integer> list = ImmutableArrayList.emptyList();

    /* when */
    final boolean actual = list.isEmpty();

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsEmpty2_1()
  {
    /* given */
    final Integer[] elements = { 1, 2, 3 };
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final boolean actual = list.isEmpty();

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNotEmpty1_1()
  {
    /* given */
    final Integer[] elements = { 1, 2, 3 };
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final boolean actual = list.isNotEmpty();

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNotEmpty2_1()
  {
    /* given */
    final Integer[] elements = {};
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final boolean actual = list.isNotEmpty();

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNotEmpty2_2()
  {
    /* given */
    final ImmutableArrayList<Integer> list = ImmutableArrayList.emptyList();

    /* when */
    final boolean actual = list.isNotEmpty();

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testContains()
  {
    /* given */
    final Integer[] elements = { 1 };
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final boolean actual = list.contains(1);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testContains2()
  {
    /* given */
    final Integer[] elements = {};
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final boolean actual = list.contains(1);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testContains3()
  {
    /* given */
    final Integer[] elements = { 2, 3 };
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final boolean actual = list.contains(1);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testContains4()
  {
    /* given */
    final ImmutableArrayList<Integer> list = ImmutableArrayList.emptyList();

    /* when */
    final boolean actual = list.contains(1);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testEquals()
  {
    /* given */
    final Integer[] elements = { 1, 2, 3 };
    final ImmutableArrayList<Integer> expected =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));
    final boolean actual = list.equals(expected);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testEquals2()
  {
    /* given */
    final Integer[] elements = { 1, 2, 3, 4, 5 };
    final ImmutableArrayList<Integer> expected =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length - 1));
    final boolean actual = list.equals(expected);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testEquals3()
  {
    /* given */
    final Integer[] elements = {};
    final ImmutableArrayList<Integer> expected =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));
    final boolean actual = list.equals(expected);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testEquals4()
  {
    /* given */
    final Integer[] elements1 = { 1 };
    final Integer[] elements2 = { 1, 2 };
    final ImmutableArrayList<Integer> expected =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements1, elements1.length));

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements1, elements2.length));
    final boolean actual = list.equals(expected);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testEquals5()
  {
    /* given */
    final ImmutableArrayList<Integer> expected = ImmutableArrayList.emptyList();

    /* when */
    final ImmutableArrayList<Integer> list = ImmutableArrayList.emptyList();
    final boolean actual = list.equals(expected);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testNotEquals()
  {
    /* given */
    final Integer[] elements = { 1, 2, 3 };
    final ImmutableArrayList<Integer> expected =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));
    final boolean actual = list.notEquals(expected);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testNotEquals2()
  {
    /* given */
    final Integer[] elements = { 1, 2, 3, 4, 5 };
    final ImmutableArrayList<Integer> expected =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length - 1));
    final boolean actual = list.notEquals(expected);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testNotEquals3()
  {
    /* given */
    final Integer[] elements = {};
    final ImmutableArrayList<Integer> expected =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));
    final boolean actual = list.notEquals(expected);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testNotEquals4()
  {
    /* given */
    final Integer[] elements1 = { 1 };
    final Integer[] elements2 = { 1, 2 };
    final ImmutableArrayList<Integer> expected =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements1, elements1.length));

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements1, elements2.length));
    final boolean actual = list.notEquals(expected);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testNotEquals5()
  {
    /* given */
    final ImmutableArrayList<Integer> expected = ImmutableArrayList.emptyList();

    /* when */
    final ImmutableArrayList<Integer> list = ImmutableArrayList.emptyList();
    final boolean actual = list.notEquals(expected);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testDefaultImmutableArrayList()
  {
    /* given */
    final ImmutableArrayList<Integer> expected = ImmutableArrayList.listOf(1);

    /* when */
    final ImmutableArrayList<Integer> actual = new DefaultImmutableArrayList(1);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToArray1()
  {
    /* given */
    final Integer[] expected = { 1, 2, 3, 4, 5 };

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(expected, expected.length));
    final Object[] actual = list.toArray();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToArray1_2()
  {
    /* given */
    final Integer[] expected = {};

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(expected, expected.length));
    final Object[] actual = list.toArray();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToArray1_3()
  {
    /* given */
    final Integer[] expected = {};

    /* when */
    final ImmutableArrayList<Integer> list = ImmutableArrayList.emptyList();
    final Object[] actual = list.toArray();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToArray2()
  {
    /* given */
    final Integer[] expected = { 1, 2, 3, 4, 5 };

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(expected, expected.length));
    final Integer[] actual = list.toArray(new Integer[0]);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToArray2_1()
  {
    /* given */
    final Integer[] expected = { 1, 2, 3, 4, 5 };

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(expected, expected.length));
    final Integer[] actual = list.toArray(new Integer[1]);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToArray2_2()
  {
    /* given */
    final Integer[] expected = { 1, 2, 3, 4, 5 };

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(expected, expected.length));
    final Integer[] actual = list.toArray(new Integer[expected.length]);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToArray2_3()
  {
    /* given */
    final Integer[] expected = {};

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(expected, expected.length));
    final Integer[] actual = list.toArray(new Integer[expected.length]);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToArray2_4()
  {
    /* given */
    final Integer[] expected = {};

    /* when */
    final ImmutableArrayList<Integer> list = ImmutableArrayList.emptyList();
    final Integer[] actual = list.toArray(new Integer[expected.length]);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testConvertTo()
  {
    /* given */
    final List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(expected.toArray());
    final List<Integer> actual = list.convertTo();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testConvertTo2()
  {
    /* given */
    final List<Integer> expected = Collections.emptyList();

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(expected.toArray());
    final List<Integer> actual = list.convertTo();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIterator()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-5, -4, -3, -2, -1, 0, 999, 1, 2, 3, 4, 5);
    final Iterator<Integer> expected = elements.iterator();

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());
    final Iterator<Integer> actual = list.iterator();

    /* then */
    assertThat(KollectionUtil.equalElements(actual, expected)).isTrue();
  }

  @Test
  public final void testLength()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(1, 2, 3, 4, 5);
    final int expected = elements.size();

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());
    final int actual = list.length();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testLength2()
  {
    /* given */
    final List<Integer> elements = Collections.emptyList();
    final int expected = 0;

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());
    final int actual = list.length();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testLength3()
  {
    /* given */
    final int expected = 0;

    /* when */
    final ImmutableArrayList<Integer> list = ImmutableArrayList.emptyList();
    final int actual = list.length();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFind()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(1, 2, 3, 4, 5);
    final Integer expected = 3;

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());
    final Integer actual = list.find(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return 2 < input.intValue();
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFind2()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(1, 2, 3, 4, 5);

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());
    final Integer actual = list.find(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return 0 > input.intValue();
      }
    });

    /* then */
    assertThat(actual).isNull();
  }

  @Test
  public final void testFind3()
  {
    /* given */
    final List<Integer> elements = Collections.emptyList();

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());
    final Integer actual = list.find(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return true;
      }
    });

    /* then */
    assertThat(actual).isNull();
  }

  @Test
  public final void testFind4()
  {
    /* given */
    final List<Integer> elements = Collections.emptyList();

    /* when */
    final ImmutableArrayList<Integer> list = ImmutableArrayList.emptyList();
    final Integer actual = list.find(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return true;
      }
    });

    /* then */
    assertThat(actual).isNull();
  }

  @Test
  public final void testSelect()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);
    final ImmutableList<Integer> expected = new DefaultImmutableArrayList<Integer>(1, 2, 3, 4, 5);

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());
    final ImmutableList<Integer> actual = list.select(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return 0 < input.intValue();
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual.length()).isEqualTo(expected.length());
  }

  @Test
  public final void testSelect2()
  {
    /* given */
    final List<Integer> elements = Collections.emptyList();
    final ImmutableList<Integer> expected = new DefaultImmutableArrayList<Integer>();

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());
    final ImmutableList<Integer> actual = list.select(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return true;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual.length()).isZero();
  }

  @Test
  public final void testSelect3()
  {
    /* given */
    final ImmutableList<Integer> expected = new DefaultImmutableArrayList<Integer>();

    /* when */
    final ImmutableArrayList<Integer> list = ImmutableArrayList.emptyList();
    final ImmutableList<Integer> actual = list.select(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return true;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual.length()).isZero();
  }

  @Test
  public final void testMap()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);
    final ImmutableList<String> expected =
      new DefaultImmutableArrayList<String>("-5", "-4", "-3", "-2", "-1", "0", "1", "2", "3", "4", "5");

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());
    final ImmutableList<String> actual = list.map(new Function1<Integer, String>() {
      @Override
      public String apply(final Integer input)
      {
        return String.valueOf(input);
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual.length()).isEqualTo(expected.length());
  }

  @Test
  public final void testMapSelectively()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);
    final ImmutableList<String> expected = new DefaultImmutableArrayList<String>("1", "2", "3", "4", "5");

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());
    final ImmutableList<String> actual = list.mapSelectively(new Condition1<Integer>() {

      @Override
      public boolean isMet(final Integer input)
      {
        return 0 < input.intValue();
      }
    }, new Function1<Integer, String>() {
      @Override
      public String apply(final Integer input)
      {
        return String.valueOf(input);
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual.length()).isEqualTo(expected.length());
  }

  @Test
  public final void testForEach()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(0, 1, 2, 3, 4, 5);
    int expected = 0;
    for (final Integer integer : elements)
    {
      expected += integer.intValue();
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final int[] actual = { 0 };

    list.forEach(new VoidFunction1<Integer>() {

      @Override
      public void apply(final Integer input)
      {
        actual[0] += input.intValue();
      }
    });

    /* then */
    assertThat(actual[0]).isEqualTo(expected);
  }

  @Test
  public final void testIndexedForEach()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-100, 4, -5, -1, 0, 1, 2, -2, 3, 5);
    final Integer lookingFor = 3;
    final int length = elements.size();
    int index = -1;
    for (int i = 0; i < length; i++)
    {
      if (elements.get(i)
          .intValue() > 0)
      {
        index = i;
      }
    }
    final int expected = index;

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final int[] actual = { -1 };

    list.forEach(new IndexedVoidFunction1<Integer>() {

      @Override
      public void apply(final int index, final Integer input)
      {
        if (input.intValue() > 0)
        {
          actual[0] = index;
        }
      }
    });

    /* then */
    assertThat(actual[0]).isEqualTo(expected);
  }

  @Test
  public final void testBreakableForEach()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-5, -4, -3, -2, -1, 0, 999, 1, 2, 3, 4, 5);
    int expected = -999;

    for (final Integer integer : elements)
    {
      if (0 < integer.intValue())
      {
        expected = integer.intValue();
        break;
      }
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final int[] actual = { -777 };

    list.breakableForEach(new BreakableFunction1<Integer>() {
      @Override
      public BreakOrContinue apply(final Integer input)
      {
        if (0 < input.intValue())
        {
          actual[0] = input.intValue();
          return BreakOrContinue.BREAK;
        }
        return BreakOrContinue.CONTINUE;
      }
    });

    /* then */
    assertThat(actual[0]).isEqualTo(expected);
  }

  @Test
  public final void testIndexedBreakableForEach()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-100, 4, -5, -1, 0, 7, -10, 1, 2, -2, 3, 5);
    final Integer lookingFor = 3;
    final int length = elements.size();
    int index = -1;
    for (int i = 0; i < length; i++)
    {
      if (elements.get(i)
          .intValue() > 0)
      {
        index = i;
        break;
      }
    }
    final int expected = index;

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final int[] actual = { -1 };

    list.breakableForEach(new IndexedBreakableFunction1<Integer>() {

      @Override
      public BreakOrContinue apply(final int index, final Integer input)
      {
        if (input.intValue() > 0)
        {
          actual[0] = index;
          return BreakOrContinue.BREAK;
        }
        return BreakOrContinue.CONTINUE;

      }
    });

    /* then */
    assertThat(actual[0]).isEqualTo(expected);
  }

  @Test
  public final void testFoldLeft1_1()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(0, 1, 2, 3, 4, 5);
    int expected = 0;
    for (final Integer integer : elements)
    {
      expected += integer.intValue();
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Integer actual = list.foldLeft(0, new Function2<Integer, Integer, Integer>() {
      @Override
      public Integer apply(final Integer input1, final Integer input2)
      {
        return input1.intValue() + input2.intValue();
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldLeft1_2()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(0, 1, 2, 3, 4, 5);
    int expected = 0;
    for (final Integer integer : elements)
    {
      expected += integer.intValue();
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Integer actual = list.foldLeft(0, new Function2<Number, Integer, Integer>() {
      @Override
      public Integer apply(final Number input1, final Integer input2)
      {
        return input1.intValue() + input2.intValue();
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldLeft1_3()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(0, 1, 2, 3, 4, 5);
    int expected = 0;
    for (final Integer integer : elements)
    {
      expected += integer.intValue();
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Integer actual = list.foldLeft(0, new Function2<Integer, Number, Integer>() {
      @Override
      public Integer apply(final Integer input1, final Number input2)
      {
        return input1.intValue() + input2.intValue();
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldLeft1_4()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(0, 1, 2, 3, 4, 5);
    int expected = 0;
    for (final Integer integer : elements)
    {
      expected += integer.intValue();
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Integer actual = list.foldLeft(0, new Function2<Number, Number, Integer>() {
      @Override
      public Integer apply(final Number input1, final Number input2)
      {
        return input1.intValue() + input2.intValue();
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldLeft1_5()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(0, 1, 2, 3, 4, 5);
    int expected = 0;
    for (final Integer integer : elements)
    {
      expected += integer.intValue();
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Number actual = list.foldLeft((Number) 0, new Function2<Number, Number, Number>() {
      @Override
      public Number apply(final Number input1, final Number input2)
      {
        return input1.intValue() + input2.intValue();
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldLeft1_6()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(0, 1, 2, 3, 4, 5);
    long expected = 0;
    for (final Integer integer : elements)
    {
      expected += integer.intValue();
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Long actual = list.foldLeft(0L, new Function2<Number, Number, Long>() {
      @Override
      public Long apply(final Number input1, final Number input2)
      {
        return (long) (input1.intValue() + input2.intValue());
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldLeft2_1()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(0, 1, 2, 3, 4, 5);
    int expected = 0;
    for (final Integer integer : elements)
    {
      expected += integer.intValue();
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Integer actual = list.<Integer, Function2<Integer, Integer, Integer>> foldLeft(0)
        .apply(new Function2<Integer, Integer, Integer>() {
          @Override
          public Integer apply(final Integer input1, final Integer input2)
          {
            return input1.intValue() + input2.intValue();
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldLeft2_2()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(0, 1, 2, 3, 4, 5);
    int expected = 0;
    for (final Integer integer : elements)
    {
      expected += integer.intValue();
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Integer actual = list.<Integer, Function2<Number, Integer, Integer>> foldLeft(0)
        .apply(new Function2<Number, Integer, Integer>() {
          @Override
          public Integer apply(final Number input1, final Integer input2)
          {
            return input1.intValue() + input2.intValue();
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldLeft2_3()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(0, 1, 2, 3, 4, 5);
    int expected = 0;
    for (final Integer integer : elements)
    {
      expected += integer.intValue();
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Integer actual = list.<Integer, Function2<Integer, Number, Integer>> foldLeft(0)
        .apply(new Function2<Integer, Number, Integer>() {
          @Override
          public Integer apply(final Integer input1, final Number input2)
          {
            return input1.intValue() + input2.intValue();
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldLeft2_4()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(0, 1, 2, 3, 4, 5);
    int expected = 0;
    for (final Integer integer : elements)
    {
      expected += integer.intValue();
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Integer actual = list.<Integer, Function2<Number, Number, Integer>> foldLeft(0)
        .apply(new Function2<Number, Number, Integer>() {
          @Override
          public Integer apply(final Number input1, final Number input2)
          {
            return input1.intValue() + input2.intValue();
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldLeft2_5()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(0, 1, 2, 3, 4, 5);
    int expected = 0;
    for (final Integer integer : elements)
    {
      expected += integer.intValue();
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Number actual = list.<Number, Function2<Number, Number, Number>> foldLeft((Number) 0)
        .apply(new Function2<Number, Number, Number>() {
          @Override
          public Number apply(final Number input1, final Number input2)
          {
            return input1.intValue() + input2.intValue();
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldLeft2_6()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(0, 1, 2, 3, 4, 5);
    long expected = 0;
    for (final Integer integer : elements)
    {
      expected += integer.intValue();
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Long actual = list.<Long, Function2<Number, Number, Long>> foldLeft(0L)
        .apply(new Function2<Number, Number, Long>() {
          @Override
          public Long apply(final Number input1, final Number input2)
          {
            return (long) (input1.intValue() + input2.intValue());
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldRight1_1()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(5, 4, 3, 2, 1);
    double expected = 1;
    final int size = elements.size();
    for (int i = size - 1; i >= 0; i--)
    {
      expected = elements.get(i)
          .intValue() / expected;
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Double actual = list.foldRight(1D, new Function2<Integer, Double, Double>() {
      @Override
      public Double apply(final Integer input1, final Double input2)
      {
        return input1.intValue() / input2.doubleValue();
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldRight1_2()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(5, 4, 3, 2, 1);
    final int scale = 5;
    BigDecimal expected = BigDecimal.ONE;
    final int size = elements.size();
    for (int i = size - 1; i >= 0; i--)
    {
      expected = new BigDecimal(elements.get(i)
          .intValue()).divide(expected, scale, RoundingMode.HALF_UP);
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final BigDecimal actual = list.foldRight(BigDecimal.ONE, new Function2<Integer, BigDecimal, BigDecimal>() {
      @Override
      public BigDecimal apply(final Integer input1, final BigDecimal input2)
      {
        return new BigDecimal(input1.intValue()).divide(input2, scale, RoundingMode.HALF_UP);
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldRight1_3()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(5, 4, 3, 2, 1);
    final int scale = 5;
    BigDecimal expected = BigDecimal.ONE;
    final int size = elements.size();
    for (int i = size - 1; i >= 0; i--)
    {
      expected = new BigDecimal(elements.get(i)
          .intValue()).divide(expected, scale, RoundingMode.HALF_UP);
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final BigDecimal actual = list.foldRight(BigDecimal.ONE, new Function2<Number, Number, BigDecimal>() {
      @Override
      public BigDecimal apply(final Number input1, final Number input2)
      {
        return new BigDecimal(input1.intValue()).divide((BigDecimal) input2, scale, RoundingMode.HALF_UP);
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldRight1_4()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(5, 4, 3, 2, 1);
    final int scale = 5;
    BigDecimal expected = BigDecimal.ONE;
    final int size = elements.size();
    for (int i = size - 1; i >= 0; i--)
    {
      expected = new BigDecimal(elements.get(i)
          .intValue()).divide(expected, scale, RoundingMode.HALF_UP);
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final BigDecimal actual = list.foldRight(BigDecimal.ONE, new Function2<Number, BigDecimal, BigDecimal>() {
      @Override
      public BigDecimal apply(final Number input1, final BigDecimal input2)
      {
        return new BigDecimal(input1.intValue()).divide(input2, scale, RoundingMode.HALF_UP);
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFoldRight1_5()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(5, 4, 3, 2, 1);
    final int scale = 5;
    BigDecimal expected = BigDecimal.ONE;
    final int size = elements.size();
    for (int i = size - 1; i >= 0; i--)
    {
      expected = new BigDecimal(elements.get(i)
          .intValue()).divide(expected, scale, RoundingMode.HALF_UP);
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final BigDecimal actual = list.foldRight(BigDecimal.ONE, new Function2<Integer, Number, BigDecimal>() {
      @Override
      public BigDecimal apply(final Integer input1, final Number input2)
      {
        return new BigDecimal(input1.intValue()).divide((BigDecimal) input2, scale, RoundingMode.HALF_UP);
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testReduce()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(0, 1, 2, 3, 4, 5);
    int expected = 0;
    for (final Integer integer : elements)
    {
      expected += integer.intValue();
    }

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Integer actual = list.reduce(new Function2<Integer, Integer, Integer>() {
      @Override
      public Integer apply(final Integer input1, final Integer input2)
      {
        return input1.intValue() + input2.intValue();
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testReduce2()
  {
    /* given */
    final List<String> elements = Arrays.asList("Hello, ", "Kevin", " ", "Lee");
    final StringBuilder stringBuilder = new StringBuilder();

    for (final String element : elements)
    {
      stringBuilder.append(element);
    }
    final String expected = stringBuilder.toString();

    /* when */
    final ImmutableList<String> list = new DefaultImmutableArrayList<String>(elements.toArray());

    final String actual = list.reduce(new Function2<String, String, String>() {
      @Override
      public String apply(final String input1, final String input2)
      {
        return input1 + input2;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testReduce3()
  {
    /* given */
    final List<String> elements = Arrays.asList("First", "Second", "Third", "Fourth");
    final StringBuilder stringBuilder = new StringBuilder();

    for (final String element : elements)
    {
      stringBuilder.append(element)
          .append(", ");
    }
    final String expected = stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length())
        .toString();

    /* when */
    final ImmutableList<String> list = new DefaultImmutableArrayList<String>(elements.toArray());

    final String actual = list.reduce(new Function2<String, String, String>() {
      @Override
      public String apply(final String input1, final String input2)
      {
        return input1 + ", " + input2;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testReduceWithOneElement()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(999);
    final int expected = 999;

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Integer actual = list.reduce(new Function2<Integer, Integer, Integer>() {
      @Override
      public Integer apply(final Integer input1, final Integer input2)
      {
        return input1.intValue() * input2.intValue();
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testReduceWithNoElement()
  {
    /* given */
    final List<Integer> elements = Arrays.asList();

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final Integer actual = list.reduce(new Function2<Integer, Integer, Integer>() {
      @Override
      public Integer apply(final Integer input1, final Integer input2)
      {
        return input1.intValue() * input2.intValue();
      }
    });

    /* then */
    assertThat(actual).isNull();
  }

  @Test
  public final void testGet()
  {
    /* given */
    final Integer expected = 999;

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(999);
    final Integer actual = list.get(0);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGet2()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);
    final int expectedLength = elements.size();

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());
    for (int i = 0; i < expectedLength; i++)
    {
      final Integer expected = elements.get(i);
      final Integer actual = list.get(i);
      /* then */
      assertThat(actual).isEqualTo(expected);

    }

    assertThat(list.length()).isEqualTo(expectedLength);
  }

  @Test
  public final void testSubList()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);
    final int fromIndex = 3;
    final int toIndex = elements.size() - 2;
    final Integer[] expected = elements.subList(fromIndex, toIndex)
        .toArray(new Integer[0]);
    final int expectedLength = expected.length;
    final List<Integer> expectedList = Arrays.asList(expected);

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final ImmutableList<Integer> actualSubList = list.subList(fromIndex, toIndex);
    final Object[] actual = actualSubList.toArray();

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actualSubList.convertTo()).hasSameSizeAs(expectedList);
    assertThat(actualSubList).hasSameSizeAs(expectedList);
    assertThat(actualSubList.length()).isEqualTo(expectedLength);
  }

  @Test
  public final void testEmptyList1_1() throws NoSuchMethodException, SecurityException, InstantiationException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException
  {
    /* given */
    final Constructor<EmptyImmutableArrayList> constructor = EmptyImmutableArrayList.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    final ImmutableArrayList<Integer> expected = constructor.newInstance();

    /* when */
    final ImmutableArrayList<Integer> actual = ImmutableArrayList.emptyList();

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).isNotSameAs(expected);
  }

  @Test
  public final void testEmptyList1_2()
  {
    /* given */
    final ImmutableArrayList<Integer> expected =
      (ImmutableArrayList<Integer>) EmptyImmutableArrayList.EMPTY_IMMUTABLE_ARRAY_LIST;

    /* when */
    final ImmutableArrayList<Integer> actual = ImmutableArrayList.emptyList();

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).isSameAs(expected);
  }

  @Test
  public final void testEmptyList1_3()
  {
    /* given */
    final ImmutableArrayList<Integer> expected = ImmutableArrayList.emptyList();

    /* when */
    final ImmutableArrayList<Integer> actual = ImmutableArrayList.emptyList();

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).isSameAs(expected);
  }

  @Test
  public final void testEmptyList2_1()
  {
    /* given */
    final ImmutableArrayList<Integer> expected = new DefaultImmutableArrayList<Integer>(0);

    /* when */
    final ImmutableArrayList<Integer> actual = ImmutableArrayList.emptyList();

    /* then */
    assertThat(actual).isNotEqualTo(expected);
  }

  @Test
  public final void testGetEmptyImmutableArrayList()
  {
    /* given */
    final ImmutableArrayList<Integer> expected = ImmutableArrayList.emptyList();

    /* when */
    final ImmutableArrayList<Integer> actual = ImmutableArrayList.emptyList();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testListOfTArray()
  {
    /* given */
    final ImmutableArrayList<Integer> expected = ImmutableArrayList.emptyList();

    /* when */
    final ImmutableArrayList<Integer> actual = ImmutableArrayList.listOf();

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).isSameAs(expected);
  }

  @Test
  public final void testListOfTArray2()
  {
    /* given */
    final ImmutableArrayList<Integer> expected = new DefaultImmutableArrayList<Integer>();

    /* when */
    final ImmutableArrayList<Integer> actual = ImmutableArrayList.listOf();

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).isNotSameAs(expected);
  }

  @Test
  public final void testListOfTArray3()
  {
    /* given */
    final Integer[] elements = { -7, -2, -1, 0, 1, 2, 7 };
    final ImmutableArrayList<Integer> expected =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final ImmutableArrayList<Integer> actual = ImmutableArrayList.listOf(Arrays.copyOf(elements, elements.length));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testListOfTArray4()
  {
    /* given */
    final ImmutableArrayList<Integer> expected = new DefaultImmutableArrayList<Integer>(-1);

    /* when */
    final ImmutableArrayList<Integer> actual = ImmutableArrayList.listOf(1);

    /* then */
    assertThat(actual).isNotEqualTo(expected);
  }

  @Test
  public final void testListOfTArrayInt()
  {
    /* given */
    final Integer[] elements = { -7, -2, -1, 0, 1, 2, 7 };
    final ImmutableArrayList<Integer> expected = ImmutableArrayList.emptyList();

    /* when */
    final ImmutableArrayList<Integer> actual = ImmutableArrayList.listOf(Arrays.copyOf(elements, elements.length), 0);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testListOfTArrayInt2()
  {
    /* given */
    final Integer[] elements = { -7, -2, -1, 0, 1, 2, 7 };
    final ImmutableArrayList<Integer> expected = ImmutableArrayList.emptyList();

    /* when */
    final ImmutableArrayList<Integer> actual = ImmutableArrayList.listOf(Arrays.copyOf(elements, elements.length), 0);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testListOfTArrayInt3()
  {
    /* given */
    final Integer[] elements = { -7, -2, -1, 0, 1, 2, 7 };
    final ImmutableArrayList<Integer> expected = new DefaultImmutableArrayList<Integer>();

    /* when */
    final ImmutableArrayList<Integer> actual = ImmutableArrayList.listOf(Arrays.copyOf(elements, elements.length), 0);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testListOfTArrayInt4()
  {
    /* given */
    final Integer[] elements = { -7, -2, -1, 0, 1, 2, 7 };

    /* when */
    final ImmutableArrayList<Integer> actual = ImmutableArrayList.listOf(Arrays.copyOf(elements, elements.length), 0);

    /* then */
    assertThat(actual.isEmpty()).isTrue();
    assertThat(actual.isNotEmpty()).isFalse();
  }

  @Test
  public final void testListOfTArrayInt5()
  {
    /* given */
    final Integer[] elements = { -7, -2, -1, 0, 1, 2, 7 };
    final ImmutableArrayList<Integer> expected =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final ImmutableArrayList<Integer> actual = ImmutableArrayList.listOf(Arrays.copyOf(elements, elements.length), 0);

    /* then */
    assertThat(actual).isNotEqualTo(expected);
  }

  @Test
  public final void testListOfTArrayInt6()
  {
    /* given */
    final Integer[] elements = { -7, -2, -1, 0, 1, 2, 7 };
    final int expectedLength = 3;
    final ImmutableArrayList<Integer> expected =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, expectedLength));

    /* when */
    final ImmutableArrayList<Integer> actual =
      ImmutableArrayList.listOf(Arrays.copyOf(elements, elements.length), expectedLength);

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual.length()).isEqualTo(expected.length());
    assertThat(actual.length()).isEqualTo(expectedLength);
  }

  @Test
  public final void testListOfTArrayInt7()
  {
    /* given */
    final Integer[] elements = { -7, -2, -1, 0, 1, 2, 7 };
    final int expectedLength = elements.length;
    final int length = 999;
    final ImmutableArrayList<Integer> expected =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, expectedLength));

    /* when */
    final ImmutableArrayList<Integer> actual =
      ImmutableArrayList.listOf(Arrays.copyOf(elements, elements.length), length);

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual.length()).isEqualTo(expected.length());
    assertThat(actual.length()).isEqualTo(expectedLength);
  }

  @Test
  public final void testHowMany()
  {
    /* given */
    final Integer[] elements = { -7, -2, -1, 0, 1, 2, 7 };

    int count = 0;

    for (final Integer integer : elements)
    {
      if (0 < integer.intValue())
      {
        count++;
      }
    }
    final int expected = 3;
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */

    final int actual = list.howMany(new Condition1<Integer>() {

      @Override
      public boolean isMet(final Integer input)
      {
        return 0 < input.intValue();
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testHowMany2()
  {
    /* given */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(-10, -5, -3, -2, -1, 0, 1, 2);
    final int expected = 5;

    /* when */

    final int actual = list.howMany(new Condition1<Integer>() {

      @Override
      public boolean isMet(final Integer input)
      {
        return 0 > input.intValue();
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testHowMany3()
  {
    /* given */
    final Integer[] elements = { -7, -2, -1, 0 };
    final int expected = 0;
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */

    final int actual = list.howMany(new Condition1<Integer>() {

      @Override
      public boolean isMet(final Integer input)
      {
        return 0 < input.intValue();
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testHowMany4()
  {
    /* given */
    final int expected = 0;
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>();

    /* when */

    final int actual = list.howMany(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return true;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testHowMany5()
  {
    /* given */
    final int expected = 0;
    final ImmutableArrayList<Integer> list = ImmutableArrayList.emptyList();

    /* when */

    final int actual = list.howMany(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return true;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIndexOfEInt()
  {
    /* given */
    final Integer[] elements = { -100, 1, 3, 10, -100, 5, 1 };

    final int expectedLength = elements.length;

    final Integer[] targets = { -100, 1, 3, 10, 5 };
    final int[][] indicesArray =
      { { 0, 2, 5, elements.length - 1 }, { 0, 2, elements.length - 1 }, { 0, 1, 3, elements.length - 1 },
          { 0, 2, 4, elements.length - 1 }, { 0, 3, 5, elements.length - 1 } };
    final int[][] expectedIndicesArray =
      { { 0, 4, -1, -1 }, { 1, 6, 6 }, { 2, 2, -1, -1 }, { 3, 3, -1, -1 }, { 5, 5, 5, -1 } };

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, expectedLength));
    final int actualLength = list.length();

    for (int i = 0; i < targets.length; i++)
    {
      final int[] indices = indicesArray[i];
      final int[] expectedIndices = expectedIndicesArray[i];
      for (int index = 0; index < indices.length; index++)
      {
        /* then */
        assertThat(list.indexOf(targets[i], indices[index])).describedAs("i: " + i + ", index: " + index)
            .isEqualTo(expectedIndices[index]);
        System.out.println("i(elementIndex): " + i + ", index: " + index + "\n" + "targets[i]: " + targets[i]
            + ", indices[index](fromIndex): " + indices[index] + ", expectedIndices[index]: " + expectedIndices[index]);
        System.out.println("↑---success---\n");
      }
    }
    assertThat(actualLength).isEqualTo(expectedLength);
  }

  @Test
  public final void testIndexOfEInt2()
  {
    /* given */
    final Integer[] elements = { -100, 1, 3, 10, -100, 5, 1 };
    final int expectedLength = elements.length;
    final int invlidIndex = -1;

    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, expectedLength));

    /* expect */
    causeCheckableExpectedException.expect(IndexOutOfBoundsException.class);

    /* when */
    list.indexOf(1, invlidIndex);

    /* Otherwise */
    fail("IndexOutOfBoundsException is not thrown for the invalid index (It must be 0 <= index < " + expectedLength
        + "). index: " + invlidIndex);
  }

  @Test
  public final void testIndexOfEInt3()
  {
    /* given */
    final Integer[] elements = { -100, 1, 3, 10, -100, 5, 1 };
    final int expectedLength = elements.length;
    final int index = 0;

    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, expectedLength));

    /* when */
    list.indexOf(1, index);

    /* then */
    /* No exception must be thrown. */
  }

  @Test
  public final void testIndexOfEInt4()
  {
    /* given */
    final Integer[] elements = { -100, 1, 3, 10, -100, 5, 1 };
    final int expectedLength = elements.length;
    final int invlidIndex = elements.length;

    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, expectedLength));

    /* expect */
    causeCheckableExpectedException.expect(IndexOutOfBoundsException.class);

    /* when */
    list.indexOf(1, invlidIndex);

    /* Otherwise */
    fail("IndexOutOfBoundsException is not thrown for the invalid index (It must be 0 <= index < " + expectedLength
        + "). index: " + invlidIndex);
  }

  @Test
  public final void testIndexOfEInt5()
  {
    /* given */
    final Integer[] elements = { -100, 1, 3, 10, -100, 5, 1 };
    final int expectedLength = elements.length;
    final int index = elements.length - 1;

    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, expectedLength));

    /* when */
    list.indexOf(1, index);

    /* then */
    /* No exception must be thrown. */
  }

  @Test
  public final void testIndexOfE()
  {
    /* given */
    final List<Integer> expected = Arrays.asList(-9, -5, -1, 0, 1, 5, 7, 9, 10);
    final int expectedLength = expected.size();

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(expected.toArray());
    final int actualLength = list.length();

    /* then */
    for (int i = 0; i < expectedLength; i++)
    {
      assertThat(list.indexOf(expected.get(i))).isEqualTo(i);
    }
    assertThat(actualLength).isEqualTo(expectedLength);
  }

  @Test
  public final void testIndexOfE2()
  {
    /* given */
    final Integer[] expected = { -9, -5, -1, 0, 1, 5, 7, 9, 10 };
    final int expectedLength = expected.length;

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(expected, expectedLength));
    final int actualLength = list.length();

    /* then */
    for (int i = 0; i < expectedLength; i++)
    {
      assertThat(list.indexOf(expected[i])).isEqualTo(i);
    }
    assertThat(actualLength).isEqualTo(expectedLength);
  }

  @Test
  public final void testIndexOfConditionInt()
  {
    /* given */
    final int lookFor = 5;
    final Integer[] elements = { -100, 1, 5, 3, 10, -100, 5, 1 };
    final int expected = Arrays.asList(elements)
        .indexOf(lookFor);

    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final int actual = list.indexOf(new Condition1<Integer>() {

      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() == lookFor;
      }
    }, 0);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIndexOfConditionInt2()
  {
    /* given */
    final int lookFor = 5;
    final Integer[] elements = { -100, 1, lookFor, 3, 10, -100, lookFor, 1 };
    final int fromIndex = Arrays.asList(elements)
        .indexOf(lookFor) + 1;

    int index = -1;
    for (int i = fromIndex; i < elements.length; i++)
    {
      if (elements[i] == lookFor)
      {
        index = i;
      }
    }
    final int expected = index;

    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final int actual = list.indexOf(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() == lookFor;
      }
    }, fromIndex);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIndexOfCondition()
  {
    /* given */
    final int lookFor = 5;
    final Integer[] elements = { -100, 1, 5, 3, 10, -100, 5, 1 };
    final int expected = Arrays.asList(elements)
        .indexOf(lookFor);

    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* when */
    final int actual = list.indexOf(new Condition1<Integer>() {

      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() == lookFor;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testLastIndexOfEInt()
  {
    /* given */
    final Integer[] elements = { -100, 1, 3, 10, -100, 5, 1, 1 };

    final int expectedLength = elements.length;

    final Integer[] targets = { -100, 1, 3, 10, 5 };
    final int[][] indicesArray =
      { { elements.length, 5, 2, 1 }, { elements.length, 2, 1 }, { elements.length, 3, 1 }, { elements.length, 4, 2 },
          { elements.length, 6, 5, 3 } };
    final int[][] expectedIndicesArray = { { 4, 4, 0, 0 }, { 7, 1, -1 }, { 2, 2, -1 }, { 3, 3, -1 }, { 5, 5, -1, -1 } };

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, expectedLength));
    final int actualLength = list.length();

    for (int i = 0; i < targets.length; i++)
    {
      final int[] indices = indicesArray[i];
      final int[] expectedIndices = expectedIndicesArray[i];
      for (int index = 0; index < indices.length; index++)
      {
        /* then */
        assertThat(list.lastIndexOf(targets[i], indices[index])).describedAs("i: " + i + ", index: " + index)
            .isEqualTo(expectedIndices[index]);
        System.out.println("i(elementIndex): " + i + ", index: " + index + "\n" + "targets[i]: " + targets[i]
            + ", indices[index](toIndex): " + indices[index] + ", expectedIndices[index]: " + expectedIndices[index]);
        System.out.println("↑---success---\n");
      }
    }
    assertThat(actualLength).isEqualTo(expectedLength);
  }

  @Test
  public final void testLastIndexOfEInt2()
  {
    /* given */
    final Integer[] elements = { -100, 1, 3, 10, -100, 5, 1 };
    final int expectedLength = elements.length;
    final int invlidIndex = 0;

    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, expectedLength));

    /* expect */
    causeCheckableExpectedException.expect(IndexOutOfBoundsException.class);

    /* when */
    list.lastIndexOf(1, invlidIndex);

    /* Otherwise */
    fail("IndexOutOfBoundsException is not thrown for the invalid index (It must be 0 < index <= " + expectedLength
        + "). index: " + invlidIndex);
  }

  @Test
  public final void testLastIndexOfEInt3()
  {
    /* given */
    final Integer[] elements = { -100, 1, 3, 10, -100, 5, 1 };
    final int expectedLength = elements.length;
    final int index = 1;

    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, expectedLength));

    /* when */
    list.lastIndexOf(1, index);

    /* then */
    /* No exception must be thrown. */
  }

  @Test
  public final void testLastIndexOfEInt4()
  {
    /* given */
    final Integer[] elements = { -100, 1, 3, 10, -100, 5, 1 };
    final int expectedLength = elements.length;
    final int invlidIndex = expectedLength + 1;

    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, expectedLength));

    /* expect */
    causeCheckableExpectedException.expect(IndexOutOfBoundsException.class);

    /* when */
    list.lastIndexOf(1, invlidIndex);

    /* Otherwise */
    fail("IndexOutOfBoundsException is not thrown for the invalid index (It must be 0 < index <= " + expectedLength
        + "). index: " + invlidIndex);
  }

  @Test
  public final void testLastIndexOfEInt5()
  {
    /* given */
    final Integer[] elements = { -100, 1, 3, 10, -100, 5, 1 };
    final int expectedLength = elements.length;
    final int index = expectedLength;

    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, expectedLength));

    /* when */
    list.lastIndexOf(1, index);

    /* then */
    /* No exception must be thrown. */
  }

  @Test
  public final void testLastIndexOfE()
  {
    /* given */
    final Integer[] elements = { -100, 1, 3, 10, -100, 5, 1, 1 };

    final int expectedLength = elements.length;

    final Integer[] targets = { -100, 1, 3, 10, 5 };
    final int[] expectedIndices = { 4, 7, 2, 3, 5 };

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, expectedLength));
    final int actualLength = list.length();

    /* then */
    for (int i = 0; i < targets.length; i++)
    {
      /* @formatter:off */
      assertThat(list
                .lastIndexOf(
                             targets[i]))
          .describedAs("i: " + i)
          .isEqualTo(expectedIndices[i]);
      /* @formatter:on */
    }
    assertThat(actualLength).isEqualTo(expectedLength);
  }

  @Test
  public final void testLastIndexOfConditionInt()
  {
    /* given */
    final Integer[] elements = { -100, 1, 3, 10, -100, 5, 1, 1 };

    int index = -1;

    for (int i = elements.length - 1; i >= 0; i--)
    {
      if (elements[i].intValue() < 0)
      {
        index = i;
        break;
      }
    }
    final int expected = index;

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));
    final int actualLength = list.length();

    final int actual = list.lastIndexOf(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() < 0;
      }
    }, elements.length);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testLastIndexOfConditionInt2()
  {
    /* given */
    final Integer[] elements = { -100, 1, 3, 10, -100, 5, 1, 1 };

    int toIndex = -1;

    for (int i = elements.length - 1; i >= 0; i--)
    {
      if (elements[i].intValue() < 0)
      {
        toIndex = i;
        break;
      }
    }

    int index = -1;
    for (int i = toIndex - 1; i >= 0; i--)
    {
      if (elements[i].intValue() < 0)
      {
        index = i;
        break;
      }
    }
    final int expected = index;

    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));
    final int actualLength = list.length();

    /* when */
    final int actual = list.lastIndexOf(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() < 0;
      }
    }, toIndex);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testLastIndexOfCondition()
  {
    /* given */
    final Integer[] elements = { -100, 1, 3, 10, -100, 5, 1, 1 };

    final int lookFor = -100;

    int index = -1;
    for (int i = elements.length - 1; i >= 0; i--)
    {
      if (elements[i].intValue() == lookFor)
      {
        index = i;
        break;
      }
    }

    final int expected = index;

    /* when */
    final ImmutableArrayList<Integer> list =
      new DefaultImmutableArrayList<Integer>(Arrays.copyOf(elements, elements.length));

    /* then */
    final int actual = list.lastIndexOf(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() == lookFor;
      }
    });
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSize()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(1, 2, 3, 4, 5);
    final int expected = elements.size();

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());
    final int actual = list.size();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSize2()
  {
    /* given */
    final List<Integer> elements = Collections.emptyList();
    final int expected = 0;
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    /* when */
    final int actual = list.size();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSize3()
  {
    /* given */
    final int expected = 0;
    final ImmutableArrayList<Integer> list = ImmutableArrayList.emptyList();

    /* when */
    final int actual = list.size();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testExists()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-5, -4, -3, -2, -1, 0, 999, 1, 2, 3, 4, 5);
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    /* when */

    final boolean actual = list.exists(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return 0 < input.intValue();
      }
    });

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testExists2()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-5, -4, -3, -2, -1, 0, 999, 1, 2, 3, 4, 5);
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    /* when */

    final boolean actual = list.exists(new Condition1<Number>() {
      @Override
      public boolean isMet(final Number input)
      {
        return 0 < input.intValue();
      }
    });

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testExists3()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-5, -4, -3, -2, -1, 0);
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    /* when */

    final boolean actual = list.exists(new Condition1<Number>() {
      @Override
      public boolean isMet(final Number input)
      {
        return 0 < input.intValue();
      }
    });

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testExists4()
  {
    /* given */
    final List<Integer> elements = Collections.emptyList();
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    /* when */
    final boolean actual = list.exists(new Condition1<Number>() {
      @Override
      public boolean isMet(final Number input)
      {
        return true;
      }
    });

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testExists5()
  {
    /* given */
    final ImmutableArrayList<Integer> list = ImmutableArrayList.emptyList();

    /* when */
    final boolean actual = list.exists(new Condition1<Number>() {
      @Override
      public boolean isMet(final Number input)
      {
        return true;
      }
    });

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testContainsAll()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-5, -4, -3, -2, -1, 0, 999, 1, 2, 3, 4, 5);
    final ImmutableArrayList<Integer> input = new DefaultImmutableArrayList<Integer>(2, -1, -3, 0, 3 - 2, 999, 1);

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final boolean actual = list.containsAll(input);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testContainsAll2()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-5, -4, -3, -2, -1, 0, 999, 1, 2, 3, 4, 5);
    final ImmutableArrayList<Integer> input = new DefaultImmutableArrayList<Integer>(2, -1, -3, 0, 3, 111, -2, 999, 1);

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final boolean actual = list.containsAll(input);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testToString()
  {
    /* given */
    final List<Integer> elements = Arrays.asList(-5, -4, -3, -2, -1, 0, 999, 1, 2, 3, 4, 5);
    final StringBuilder stringBuilder = new StringBuilder("[");

    for (final Integer integer : elements)
    {
      stringBuilder.append(integer)
          .append(", ");
    }
    if ("ImmutableList{".length() < stringBuilder.length())
    {
      stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]");
    }
    final String expected = stringBuilder.toString();

    /* when */
    final ImmutableArrayList<Integer> list = new DefaultImmutableArrayList<Integer>(elements.toArray());

    final String actual = list.toString();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSerialization()
  {
    /* given */
    final File file = new File(temporaryFolder.getRoot(), "test-list-serialized.txt");
    final ImmutableList<String> expected = ImmutableLists.listOf("Kevin", "Lee", "Hello");

    FileOutputStream fileOutputStream = null;
    ObjectOutputStream outputStream = null;

    /* when */
    try
    {
      fileOutputStream = new FileOutputStream(file);
      outputStream = new ObjectOutputStream(fileOutputStream);
      outputStream.writeObject(expected);
    }
    catch (final IOException e)
    {
      throw new AssertionError(e);
    }
    finally
    {
      IoUtil.closeQuietly(fileOutputStream, outputStream);
    }

    FileInputStream fileInputStream = null;
    ObjectInputStream inputStream = null;

    ImmutableList<String> actual = null;

    try
    {
      fileInputStream = new FileInputStream(file);
      inputStream = new ObjectInputStream(fileInputStream);

      try
      {
        actual = (ImmutableList<String>) inputStream.readObject();
      }
      catch (final ClassNotFoundException e)
      {
        throw new AssertionError(e);
      }
    }
    catch (final IOException e)
    {
      throw new AssertionError(e);
    }
    finally
    {
      IoUtil.closeQuietly(fileInputStream, inputStream);
    }

    /* then */
    assertThat(actual).hasSameSizeAs(expected);
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).containsExactlyElementsOf(expected);
  }

  @Test
  public final void testSerializationForEmptyList()
  {
    /* given */
    final File file = new File(temporaryFolder.getRoot(), "test-list-serialized.txt");
    final ImmutableList<String> expected = ImmutableLists.emptyList();

    FileOutputStream fileOutputStream = null;
    ObjectOutputStream outputStream = null;

    /* when */
    try
    {
      fileOutputStream = new FileOutputStream(file);
      outputStream = new ObjectOutputStream(fileOutputStream);
      outputStream.writeObject(expected);
    }
    catch (final IOException e)
    {
      throw new AssertionError(e);
    }
    finally
    {
      IoUtil.closeQuietly(fileOutputStream, outputStream);
    }

    FileInputStream fileInputStream = null;
    ObjectInputStream inputStream = null;

    ImmutableList<String> actual = null;

    try
    {
      fileInputStream = new FileInputStream(file);
      inputStream = new ObjectInputStream(fileInputStream);

      try
      {
        actual = (ImmutableList<String>) inputStream.readObject();
      }
      catch (final ClassNotFoundException e)
      {
        throw new AssertionError(e);
      }
    }
    catch (final IOException e)
    {
      throw new AssertionError(e);
    }
    finally
    {
      IoUtil.closeQuietly(fileInputStream, inputStream);
    }

    /* then */
    assertThat(actual).hasSameSizeAs(expected);
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).containsExactlyElementsOf(expected);
  }

}
