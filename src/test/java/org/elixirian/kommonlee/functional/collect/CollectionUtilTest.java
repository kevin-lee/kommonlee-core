package org.elixirian.kommonlee.functional.collect;

import static org.assertj.core.api.Assertions.*;
import static org.elixirian.kommonlee.functional.collect.CollectionUtil.*;
import static org.elixirian.kommonlee.util.collect.Lists.*;
import static org.elixirian.kommonlee.util.collect.Sets.*;
import static org.elixirian.kommonlee.util.type.Tuples.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.elixirian.kommonlee.functional.BreakableFunction1;
import org.elixirian.kommonlee.functional.VoidFunction1;
import org.elixirian.kommonlee.functional.string.StringArrayToTrimmedStringListSelector;
import org.elixirian.kommonlee.type.Tuple2;
import org.elixirian.kommonlee.type.functional.BreakOrContinue;
import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.functional.Function1;
import org.elixirian.kommonlee.util.collect.Maps;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CollectionUtilTest
{

  public static final Integer[] INTEGERS = { 3, 19, -2, 0, 55, 123, 100, 1, 1, -99, 0, 3, -7, 19, 100, -4, 55, -7, 100,
      -2 };
  public static final Integer[] POSITIVE_INTEGERS = { 3, 19, 55, 123, 100, 1, 1, 3, 19, 100, 55, 100 };
  public static final String[] INTEGER_STRINGS = { "#(3)", "#(19)", "#(-2)", "#(0)", "#(55)", "#(123)", "#(100)",
      "#(1)", "#(1)", "#(-99)", "#(0)", "#(3)", "#(-7)", "#(19)", "#(100)", "#(-4)", "#(55)", "#(-7)", "#(100)",
      "#(-2)" };
  public static final String[] POSITIVE_INTEGERS_STRINGS = { "#(3)", "#(19)", "#(55)", "#(123)", "#(100)", "#(1)",
      "#(1)", "#(3)", "#(19)", "#(100)", "#(55)", "#(100)" };

  public static final String[] STRINGS_WITH_WHITE_SPACES = { "   tESt   ", "\tblah test blah  \t", " \ttest2\t ",
      "  abcd\r\n", "\n\r1234\r", "\raaaa\n", "\r  \nsome TEST 1234\n  \r", "   \r  \nsome test100\n  \r   " };
  public static final String[] STRINGS_WITHOUT_WHITE_SPACES = { "tESt", "blah test blah", "test2", "abcd", "1234",
      "aaaa", "some TEST 1234", "some test100" };
  public static final String[] TEST_STRINGS_WITHOUT_WHITE_SPACES = { "tESt", "blah test blah", "test2",
      "some TEST 1234", "some test100" };

  private Integer[] integers()
  {
    return Arrays.copyOf(INTEGERS, INTEGERS.length);
  }

  private Integer[] positiveIntegers()
  {
    return Arrays.copyOf(POSITIVE_INTEGERS, POSITIVE_INTEGERS.length);
  }

  private Integer[] positiveIntegers(final int howMany)
  {
    final Integer[] integers = Arrays.copyOf(INTEGERS, INTEGERS.length);
    final List<Integer> result = newArrayList();

    for (int i = 0; i < howMany; i++)
    {
      if (integers[i] > 0)
      {
        result.add(integers[i]);
      }
    }
    return result.toArray(new Integer[0]);
  }

  private Integer[] firstPositiveIntegers()
  {
    final Integer[] integerArray = Arrays.copyOf(INTEGERS, INTEGERS.length);
    final List<Integer> firstPositiveIntegers = newArrayList();
    for (final Integer i : integerArray)
    {
      if (i < 0)
      {
        break;
      }
      firstPositiveIntegers.add(i);
    }
    return firstPositiveIntegers.toArray(new Integer[0]);
  }

  private String[] integerStrings()
  {
    return Arrays.copyOf(INTEGER_STRINGS, INTEGER_STRINGS.length);
  }

  private String[] positiveIntegerStrings()
  {
    return Arrays.copyOf(POSITIVE_INTEGERS_STRINGS, POSITIVE_INTEGERS_STRINGS.length);
  }

  private Map<String, BigDecimal> stringToBigDecimalMap()
  {
    return Maps.<String, BigDecimal> hashMapBuilder()
        .put("#(-7)", new BigDecimal("-7"))
        .put("#(123)", new BigDecimal("123"))
        .put("#(100)", new BigDecimal("100"))
        .put("#(55)", new BigDecimal("55"))
        .put("#(19)", new BigDecimal("19"))
        .put("#(-4)", new BigDecimal("-4"))
        .put("#(-2)", new BigDecimal("-2"))
        .put("#(3)", new BigDecimal("3"))
        .put("#(-99)", new BigDecimal("-99"))
        .put("#(0)", new BigDecimal("0"))
        .put("#(1)", new BigDecimal("1"))
        .build();
  }

  private Map<Integer, BigDecimal> integerToBigDecimalMap()
  {
    return Maps.<Integer, BigDecimal> hashMapBuilder()
        .put(-7, new BigDecimal("-7"))
        .put(123, new BigDecimal("123"))
        .put(100, new BigDecimal("100"))
        .put(55, new BigDecimal("55"))
        .put(19, new BigDecimal("19"))
        .put(-4, new BigDecimal("-4"))
        .put(-2, new BigDecimal("-2"))
        .put(3, new BigDecimal("3"))
        .put(-99, new BigDecimal("-99"))
        .put(0, new BigDecimal("0"))
        .put(1, new BigDecimal("1"))
        .build();
  }

  private Map<String, BigDecimal> stringToBigDecimalMapFromPositiveIntegers()
  {
    return Maps.<String, BigDecimal> hashMapBuilder()
        .put("#(123)", new BigDecimal("123"))
        .put("#(100)", new BigDecimal("100"))
        .put("#(55)", new BigDecimal("55"))
        .put("#(19)", new BigDecimal("19"))
        .put("#(3)", new BigDecimal("3"))
        .put("#(1)", new BigDecimal("1"))
        .build();
  }

  private String[] stringWithWhiteSpaces()
  {
    return Arrays.copyOf(STRINGS_WITH_WHITE_SPACES, STRINGS_WITH_WHITE_SPACES.length);
  }

  private String[] stringsWithoutWhiteSpaces()
  {
    return Arrays.copyOf(STRINGS_WITHOUT_WHITE_SPACES, STRINGS_WITHOUT_WHITE_SPACES.length);
  }

  private String[] testStringsWithoutWhiteSpaces()
  {
    return Arrays.copyOf(TEST_STRINGS_WITHOUT_WHITE_SPACES, TEST_STRINGS_WITHOUT_WHITE_SPACES.length);
  }

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

  static class IsPositiveInteger implements Condition1<Integer>
  {
    @Override
    public boolean isMet(final Integer input)
    {
      return input.intValue() > 0;
    }

  }

  @Test
  public final void testArrayToArrayListSelector() throws Exception
  {
    /* given */
    final Integer[] integers = INTEGERS;
    final List<Integer> expected = Arrays.asList(POSITIVE_INTEGERS);

    /* when */
    final ArrayToArrayListSelector<Integer> arrayToArrayListSelector = CollectionUtil.arrayToArrayListSelector();
    final List<Integer> actual = arrayToArrayListSelector.select(integers, new IsPositiveInteger());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testArrayToArraySelector() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final Integer[] expected = positiveIntegers();

    /* when */
    final ArrayToArraySelector<Integer, Condition1<Integer>> arrayToArraySelector =
      CollectionUtil.<Integer, Condition1<Integer>> arrayToArraySelector();
    final Integer[] actual = arrayToArraySelector.select(integers, new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() > 0;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testElementCountSelector() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final int expected = Arrays.asList(positiveIntegers())
        .size();

    /* when */
    final ElementCountSelector<Integer> elementCountSelector = CollectionUtil.elementCountSelector();
    final int actual = elementCountSelector.select(integers, new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() > 0;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIterableToArrayListSelector() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final List<Integer> expected = Arrays.asList(positiveIntegers());

    /* when */
    final IterableToArrayListSelector<Integer> iterableToArrayListSelector =
      CollectionUtil.iterableToArrayListSelector();
    final List<Integer> actual = iterableToArrayListSelector.select(integers, new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() > 0;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIterableToHashSetSelector() throws Exception
  {
    /* given */
    final List<Integer> integerArray = Arrays.asList(integers());
    final Set<Integer> expected = newHashSet(positiveIntegers());

    /* when */
    final IterableToHashSetSelector<Integer> iterableToHashSetSelector = CollectionUtil.iterableToHashSetSelector();
    final Set<Integer> actual = iterableToHashSetSelector.select(integerArray, new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() > 0;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testArrayToArrayMapper() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final String[] expected = integerStrings();

    /* when */
    final ArrayToArrayMapper<Integer, Function1<Integer, String>, String> arrayToArrayMapper =
      CollectionUtil.<Integer, Function1<Integer, String>, String> arrayToArrayMapper();
    final String[] actual = arrayToArrayMapper.apply(String.class, integers, new Function1<Integer, String>() {
      @Override
      public String apply(final Integer input)
      {
        return "#(" + input + ")";
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testArrayToArrayListMapper() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final List<String> expected = Arrays.asList(integerStrings());

    /* when */
    final ArrayToCollectionMapper<Integer, String, Function1<Integer, String>, ArrayList<String>> arrayToArrayListMapper =
      CollectionUtil.<Integer, String, Function1<Integer, String>> arrayToArrayListMapper();
    final List<String> actual = arrayToArrayListMapper.apply(integers, new Function1<Integer, String>() {
      @Override
      public String apply(final Integer input)
      {
        return "#(" + input + ")";
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testArrayToHashSetMapper() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final Set<String> expected = newHashSet(integerStrings());

    /* when */
    final ArrayToCollectionMapper<Integer, String, Function1<Integer, String>, HashSet<String>> arrayToHashSetMapper =
      CollectionUtil.<Integer, String, Function1<Integer, String>> arrayToHashSetMapper();
    final Set<String> actual = arrayToHashSetMapper.apply(integers, new Function1<Integer, String>() {
      @Override
      public String apply(final Integer input)
      {
        return "#(" + input + ")";
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testArrayToMapMapper() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final Map<String, BigDecimal> expected = stringToBigDecimalMap();

    /* when */
    final ArrayToMapMapper<Integer, String, BigDecimal, Function1<Integer, Tuple2<String, BigDecimal>>, HashMap<String, BigDecimal>> arrayToMapMapper =
      CollectionUtil.<Integer, String, BigDecimal, Function1<Integer, Tuple2<String, BigDecimal>>> arrayToMapMapper();
    final Map<String, BigDecimal> actual =
      arrayToMapMapper.apply(integers, new Function1<Integer, Tuple2<String, BigDecimal>>() {
        @Override
        public Tuple2<String, BigDecimal> apply(final Integer input)
        {
          return tuple2("#(" + input + ")", new BigDecimal(String.valueOf(input.intValue())));
        }
      });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectorFromArray() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final Integer[] expected = positiveIntegers();

    /* when */
    final Integer[] actual = selector().fromArray()
        .toArray()
        .select(integers, new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectorFromArray2() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final List<Integer> expected = Arrays.asList(positiveIntegers());

    /* when */
    final List<Integer> actual = selector().fromArray()
        .toArrayList()
        .select(integers, new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectorFromArray3() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final Set<Integer> expected = newHashSet(positiveIntegers());

    /* when */
    final Set<Integer> actual = selector().fromArray()
        .toHashSet()
        .select(integers, new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectorFromIterable() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final List<Integer> expected = Arrays.asList(positiveIntegers());

    /* when */
    final List<Integer> actual = selector().fromIterable()
        .toArrayList()
        .select(integers, new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectorFromIterable2() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final Set<Integer> expected = newHashSet(positiveIntegers());

    /* when */
    final Set<Integer> actual = selector().fromIterable()
        .toHashSet()
        .select(integers, new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testMapper() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final String[] expected = integerStrings();

    /* when */
    final String[] actual = mapper().fromArray()
        .toArray()
        .map(String.class, integers, new Function1<Integer, String>() {
          @Override
          public String apply(final Integer input)
          {
            return "#(" + input + ")";
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testMapper2() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final List<String> expected = Arrays.asList(integerStrings());

    /* when */
    final List<String> actual = mapper().fromArray()
        .toArrayList()
        .map(integers, new Function1<Integer, String>() {
          @Override
          public String apply(final Integer input)
          {
            return "#(" + input + ")";
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testMapper3() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final Set<String> expected = newHashSet(integerStrings());

    /* when */
    final Set<String> actual = mapper().fromArray()
        .toHashSet()
        .map(integers, new Function1<Integer, String>() {
          @Override
          public String apply(final Integer input)
          {
            return "#(" + input + ")";
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testMapperFromIterable() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final List<String> expected = Arrays.asList(integerStrings());

    /* when */
    final List<String> actual = mapper().fromIterable()
        .toArrayList()
        .map(integers, new Function1<Integer, String>() {
          @Override
          public String apply(final Integer input)
          {
            return "#(" + input + ")";
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testMapperFromIterable2() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final Set<String> expected = newHashSet(integerStrings());

    /* when */
    final Set<String> actual = mapper().fromIterable()
        .toHashSet()
        .map(integers, new Function1<Integer, String>() {
          @Override
          public String apply(final Integer input)
          {
            return "#(" + input + ")";
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testMapperFromIterable3() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final Map<String, BigDecimal> expected = stringToBigDecimalMap();

    /* when */
    final Map<String, BigDecimal> actual = mapper().fromIterable()
        .toHashMap()
        .map(integers, new Function1<Integer, Tuple2<String, BigDecimal>>() {
          @Override
          public Tuple2<String, BigDecimal> apply(final Integer input)
          {
            return tuple2("#(" + input + ")", new BigDecimal(String.valueOf(input)));
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testMapper4() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final Map<String, BigDecimal> expected = stringToBigDecimalMap();

    /* when */
    final Map<String, BigDecimal> actual = mapper().fromArray()
        .toHashMap()
        .map(integers, new Function1<Integer, Tuple2<String, BigDecimal>>() {
          @Override
          public Tuple2<String, BigDecimal> apply(final Integer input)
          {
            return tuple2("#(" + input + ")", new BigDecimal(String.valueOf(input)));
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectableMapper() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final String[] expected = positiveIntegerStrings();

    /* when */
    final String[] actual = selectableMapper().fromArray()
        .toArray()
        .mapSelectively(String.class, integers, new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }
        }, new Function1<Integer, String>() {
          @Override
          public String apply(final Integer input)
          {
            return "#(" + input + ")";
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectableMapper2() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final List<String> expected = Arrays.asList(positiveIntegerStrings());

    /* when */
    final List<String> actual = selectableMapper().fromArray()
        .toArrayList()
        .mapSelectively(integers, new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }
        }, new Function1<Integer, String>() {
          @Override
          public String apply(final Integer input)
          {
            return "#(" + input + ")";
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectableMapper3() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final Set<String> expected = newHashSet(positiveIntegerStrings());

    /* when */
    final Set<String> actual = selectableMapper().fromArray()
        .toHashSet()
        .mapSelectively(integers, new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }
        }, new Function1<Integer, String>() {
          @Override
          public String apply(final Integer input)
          {
            return "#(" + input + ")";
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectableMapper4() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final Map<String, BigDecimal> expected = stringToBigDecimalMapFromPositiveIntegers();

    /* when */
    final Map<String, BigDecimal> actual = selectableMapper().fromArray()
        .toHashMap()
        .mapSelectively(integers, new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }
        }, new Function1<Integer, Tuple2<String, BigDecimal>>() {
          @Override
          public Tuple2<String, BigDecimal> apply(final Integer input)
          {
            return tuple2("#(" + input + ")", new BigDecimal(String.valueOf(input)));
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectableMapperFromIterable() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final List<String> expected = Arrays.asList(positiveIntegerStrings());

    /* when */
    final List<String> actual = selectableMapper().fromIterable()
        .toArrayList()
        .mapSelectively(integers, new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }
        }, new Function1<Integer, String>() {
          @Override
          public String apply(final Integer input)
          {
            return "#(" + input + ")";
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectableMapperFromIterable2() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final Set<String> expected = newHashSet(positiveIntegerStrings());

    /* when */
    final Set<String> actual = selectableMapper().fromIterable()
        .toHashSet()
        .mapSelectively(integers, new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }
        }, new Function1<Integer, String>() {
          @Override
          public String apply(final Integer input)
          {
            return "#(" + input + ")";
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectableMapperFromIterable3() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final Map<String, BigDecimal> expected = stringToBigDecimalMapFromPositiveIntegers();

    /* when */
    final Map<String, BigDecimal> actual = selectableMapper().fromIterable()
        .toHashMap()
        .mapSelectively(integers, new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }
        }, new Function1<Integer, Tuple2<String, BigDecimal>>() {
          @Override
          public Tuple2<String, BigDecimal> apply(final Integer input)
          {
            return tuple2("#(" + input + ")", new BigDecimal(String.valueOf(input)));
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testStringArrayToTrimmedStringListSelector() throws Exception
  {
    /* given */
    final String[] strings = stringWithWhiteSpaces();
    final List<String> expected = Arrays.asList(stringsWithoutWhiteSpaces());

    /* when */
    final StringArrayToTrimmedStringListSelector stringArrayToTrimmedStringListSelector =
      CollectionUtil.stringArrayToTrimmedStringListSelector();
    final List<String> actual = stringArrayToTrimmedStringListSelector.select(strings, new Condition1<String>() {

      @Override
      public boolean isMet(final String input)
      {
        return true;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testStringArrayToTrimmedStringListSelector2() throws Exception
  {
    /* given */
    final String[] strings = stringWithWhiteSpaces();
    final List<String> expected = Arrays.asList(testStringsWithoutWhiteSpaces());

    /* when */
    final StringArrayToTrimmedStringListSelector stringArrayToTrimmedStringListSelector =
      CollectionUtil.stringArrayToTrimmedStringListSelector();
    final List<String> actual = stringArrayToTrimmedStringListSelector.select(strings, new Condition1<String>() {

      @Override
      public boolean isMet(final String input)
      {
        return input.toLowerCase()
            .contains("test");
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetElementCountSelector() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final int expected = positiveIntegers().length;

    /* when */
    final int actual = CollectionUtil.<Integer> getElementCountSelector()
        .select(integers, new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectCT() throws Exception
  {
    /* given */
    final Collection<Integer> integers = Arrays.asList(integers());
    final Collection<Integer> expected = Arrays.asList(positiveIntegers());

    /* when */
    final List<Integer> actual = select(integers, new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() > 0;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectCT2() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final List<Integer> expected = Arrays.asList(positiveIntegers());

    /* when */
    final List<Integer> actual = select(integers, new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() > 0;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectCT3() throws Exception
  {
    /* given */
    final Set<Integer> integers = newHashSet(integers());
    final Set<Integer> expected = newHashSet(positiveIntegers());

    /* when */
    final Set<Integer> actual = select(integers, new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() > 0;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testSelectCEArray() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final List<Integer> expected = Arrays.asList(positiveIntegers());

    /* when */
    final List<Integer> actual = select(integers, new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() > 0;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testMap() throws Exception
  {
    /* given */
    final Map<String, BigDecimal> input = stringToBigDecimalMap();
    final Map<Integer, BigDecimal> expected = integerToBigDecimalMap();

    /* when */
    final Map<Integer, BigDecimal> actual = map(input, new Function1<String, Integer>() {
      @Override
      public Integer apply(final String input)
      {
        return Integer.valueOf(input.substring(input.indexOf("#(") + "#(".length(), input.length() - 1));
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testForEachFT() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final List<Integer> expected = Arrays.asList(positiveIntegers());
    final List<Integer> actual = newArrayList();

    /* when */
    forEach(integers, new VoidFunction1<Integer>() {
      @Override
      public void apply(final Integer input)
      {
        if (input.intValue() > 0)
        {
          actual.add(input);
        }
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testForEachFT2() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final List<Integer> expected = Arrays.asList(firstPositiveIntegers());
    final List<Integer> actual = newArrayList();

    /* when */
    forEach(integers, new BreakableFunction1<Integer>() {

      @Override
      public BreakOrContinue apply(final Integer input)
      {
        if (input.intValue() <= 0)
        {
          return BreakOrContinue.BREAK;
        }
        actual.add(input);
        return BreakOrContinue.CONTINUE;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testForEachFEArray() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final List<Integer> expected = Arrays.asList(positiveIntegers());
    final List<Integer> actual = newArrayList();

    /* when */
    forEach(integers, new VoidFunction1<Integer>() {
      @Override
      public void apply(final Integer input)
      {
        if (input.intValue() > 0)
        {
          actual.add(input);
        }
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testForEachFEArray2() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final List<Integer> expected = Arrays.asList(firstPositiveIntegers());
    final List<Integer> actual = newArrayList();

    /* when */
    forEach(integers, new BreakableFunction1<Integer>() {
      @Override
      public BreakOrContinue apply(final Integer input)
      {
        if (input.intValue() <= 0)
        {
          return BreakOrContinue.BREAK;
        }
        actual.add(input);
        return BreakOrContinue.CONTINUE;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIterableToArrayListMapper() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final List<String> expected = Arrays.asList(integerStrings());

    /* when */
    final List<String> actual = CollectionUtil.<Integer, String> iterableToArrayListMapper()
        .apply(integers, new Function1<Integer, String>() {
          @Override
          public String apply(final Integer input)
          {
            return "#(" + input + ")";
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIterableToArrayListSelectableMapper() throws Exception
  {
    /* given */
    final List<Integer> integers = Arrays.asList(integers());
    final List<String> expected = Arrays.asList(positiveIntegerStrings());

    /* when */
    final List<String> actual = CollectionUtil.<Integer, String> iterableToArrayListSelectableMapper()
        .apply(integers, new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }
        }, new Function1<Integer, String>() {
          @Override
          public String apply(final Integer input)
          {
            return "#(" + input + ")";
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);

  }

  @Test
  public final void testGenericVarargsToListSelector() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final List<Integer> expected = Arrays.asList(positiveIntegers(1));

    /* when */
    final List<Integer> actual = CollectionUtil.<Integer> genericVarargsToListSelector()
        .select(new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }

        }, integers[0]);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGenericVarargsToListSelector2() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final List<Integer> expected = Arrays.asList(positiveIntegers(2));

    /* when */
    final List<Integer> actual = CollectionUtil.<Integer> genericVarargsToListSelector()
        .select(new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }

        }, integers[0], integers[1]);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGenericVarargsToListSelector3() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final List<Integer> expected = Arrays.asList(positiveIntegers(3));

    /* when */
    final List<Integer> actual = CollectionUtil.<Integer> genericVarargsToListSelector()
        .select(new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }

        }, integers[0], integers[1], integers[2]);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGenericVarargsToListSelector4() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final List<Integer> expected = Arrays.asList(positiveIntegers(4));

    /* when */
    final List<Integer> actual = CollectionUtil.<Integer> genericVarargsToListSelector()
        .select(new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }

        }, integers[0], integers[1], integers[2], integers[3]);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGenericVarargsToListSelector5() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final List<Integer> expected = Arrays.asList(positiveIntegers(5));

    /* when */
    final List<Integer> actual = CollectionUtil.<Integer> genericVarargsToListSelector()
        .select(new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }

        }, integers[0], integers[1], integers[2], integers[3], integers[4]);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGenericVarargsToListSelector6() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final List<Integer> expected = Arrays.asList(positiveIntegers(6));

    /* when */
    final List<Integer> actual = CollectionUtil.<Integer> genericVarargsToListSelector()
        .select(new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }

        }, integers[0], integers[1], integers[2], integers[3], integers[4], integers[5]);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGenericVarargsToListSelector7() throws Exception
  {
    /* given */
    final Integer[] integers = integers();
    final List<Integer> expected = Arrays.asList(positiveIntegers(7));

    /* when */
    final List<Integer> actual = CollectionUtil.<Integer> genericVarargsToListSelector()
        .select(new Condition1<Integer>() {
          @Override
          public boolean isMet(final Integer input)
          {
            return input.intValue() > 0;
          }

        }, integers[0], integers[1], integers[2], integers[3], integers[4], integers[5], integers[6]);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

}
