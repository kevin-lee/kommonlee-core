/**
 * 
 */
package org.elixirian.common.collect;

import static org.elixirian.common.collect.Maps.*;
import static org.elixirian.common.test.CommonTestHelper.*;
import static org.elixirian.common.util.MessageFormatter.*;
import static org.elixirian.common.util.Objects.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.elixirian.common.collect.UtilForTesting.ComparableObject;
import org.elixirian.common.test.CauseCheckableExpectedException;
import org.elixirian.common.test.CommonTestHelper.Accessibility;
import org.elixirian.common.type.Condition1;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * <pre>
 *     ____________    ___________  ____   _______ _________ _______ _______________  ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /     \/   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /          /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
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
 * @version 0.0.1 (2011-02-17)
 */
public class MapsTest
{
  private static final Condition1<Object> NOT_NULL_CONDITION = new Condition1<Object>() {
    public boolean isApplicable(final Object input)
    {
      return isNotNull(input);
    }
  };

  // private static final Filter1<Map<? extends Object, ? extends Object>, Condition1<Object>, Map<? extends Object, ?
  // extends Object>> OBJECT_FILTER =
  // new Filter1<Map<? extends Object, ? extends Object>, Condition1<Object>, Map<? extends Object, ? extends Object>>()
  // {
  // public Map<? extends Object, ? extends Object> filter(final Condition1<Object> condition,
  // final Map<? extends Object, ? extends Object> source)
  // {
  // final Map<Object, Object> map = new HashMap<Object, Object>();
  // for (final Entry<? extends Object, ? extends Object> entry : source.entrySet())
  // if (condition.isApplicable(entry.getKey()))
  // map.put(entry.getKey(), entry.getValue());
  // return map;
  // }
  // };

  @SuppressWarnings("boxing")
  private static final Integer[] INTEGER_KEYS = { -955, -34, -120, 0, 23, null, 50, -3, 20, 110, 999, 20, 0, -120, 346,
      23267, 21, 111, 55, 923, 375, 632, 6099, 41290, 337, 65, 337, 337, 325, 299, 73927, 293, 926, 58219, 86271, 337 };

  private static final String[] STRING_KEYS = { "Hello, ", "Kevin", "Lee", "", "blah", "Test String", "blah", "blah",
      "Hello", "Kevin", null, "blah blah", "Hello", ", ", "World", "blah", ", ", "blah", "", " ", "Kevin", " ", "Lee",
      " ", "0123456789", "Kevin", "Lee", " ", "blah", "blah blah", "Kevin", "Testing", "Collection",
      "#59^%@7445#$&@%^$(", "7(*^(&^(I&^%&^!&~_012348", "abcdefghijklmnopqrstuvwxyz" };

  private static final List<ComparableObject> COMPARABLE_OBJECT_LIST = Collections.unmodifiableList(Arrays.asList(
      new ComparableObject(23), new ComparableObject(20), new ComparableObject(110), new ComparableObject(999),
      new ComparableObject(8), new ComparableObject(20), null, new ComparableObject(0), new ComparableObject(-120),
      new ComparableObject(21), new ComparableObject(111), new ComparableObject(55), new ComparableObject(923),
      new ComparableObject(375), new ComparableObject(632), new ComparableObject(6099), null, new ComparableObject(
          73927), new ComparableObject(293), new ComparableObject(926), new ComparableObject(58219),
      new ComparableObject(41290), new ComparableObject(337), new ComparableObject(65), new ComparableObject(111),
      new ComparableObject(299), new ComparableObject(50), new ComparableObject(-3), new ComparableObject(346),
      new ComparableObject(23267), new ComparableObject(8), new ComparableObject(82), new ComparableObject(61),
      new ComparableObject(333333333), new ComparableObject(108), new ComparableObject(63)));

  private static final Map<Integer, String> INTEGER_TO_STRING_HASH_MAP;

  private static final Map<String, Integer> STRING_TO_INTEGER_HASH_MAP;

  private static final Map<Object, Integer> OBJECT_TO_INTEGER_HASH_MAP;

  private static final Map<Object, String> OBJECT_TO_STRING_HASH_MAP;

  private static final Map<ComparableObject, Integer> COMPARABLE_OBJECT_TO_INTEGER_HASH_MAP;

  private static final Map<ComparableObject, String> COMPARABLE_OBJECT_TO_STRING_HASH_MAP;

  static
  {
    final Map<Integer, String> integerToStringMap = new HashMap<Integer, String>();
    for (int i = 0, length = INTEGER_KEYS.length; i < length; i++)
    {
      integerToStringMap.put(INTEGER_KEYS[i], STRING_KEYS[i]);
    }
    INTEGER_TO_STRING_HASH_MAP = Collections.unmodifiableMap(integerToStringMap);

    final Map<String, Integer> stringToIntegerMap = new HashMap<String, Integer>();
    for (int i = 0, length = STRING_KEYS.length; i < length; i++)
    {
      stringToIntegerMap.put(STRING_KEYS[i], INTEGER_KEYS[i]);
    }
    STRING_TO_INTEGER_HASH_MAP = Collections.unmodifiableMap(stringToIntegerMap);

    final Map<ComparableObject, Integer> comparableObjectToIntegerMap = new HashMap<ComparableObject, Integer>();
    for (int i = 0, length = COMPARABLE_OBJECT_LIST.size(); i < length; i++)
    {
      comparableObjectToIntegerMap.put(COMPARABLE_OBJECT_LIST.get(i), INTEGER_KEYS[i]);
    }
    OBJECT_TO_INTEGER_HASH_MAP =
      Collections.unmodifiableMap(new HashMap<Object, Integer>(comparableObjectToIntegerMap));

    final Map<ComparableObject, String> comparableObjectToStringMap = new HashMap<ComparableObject, String>();
    for (int i = 0, length = COMPARABLE_OBJECT_LIST.size(); i < length; i++)
    {
      comparableObjectToStringMap.put(COMPARABLE_OBJECT_LIST.get(i), STRING_KEYS[i]);
    }
    OBJECT_TO_STRING_HASH_MAP = Collections.unmodifiableMap(new HashMap<Object, String>(comparableObjectToStringMap));

    COMPARABLE_OBJECT_TO_INTEGER_HASH_MAP = Collections.unmodifiableMap(comparableObjectToIntegerMap);
    COMPARABLE_OBJECT_TO_STRING_HASH_MAP = Collections.unmodifiableMap(comparableObjectToStringMap);
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

  @Rule
  public CauseCheckableExpectedException expectedException = CauseCheckableExpectedException.none();

  @Test
  public final void testMaps() throws Exception
  {
    expectedException.expect(IllegalAccessException.class);
    testNotAccessibleConstructor(Maps.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
    fail(format("The expected exception [%s] is not thrown.", IllegalAccessError.class));
  }

  @SuppressWarnings("rawtypes")
  private static final Class<HashMap> EXPECTED_HASH_MAP_TYPE = HashMap.class;

  @Test
  public final void testNewHashMap()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_HASH_MAP_TYPE;

    @SuppressWarnings("rawtypes")
    final Map expected = new HashMap<Object, Object>();

    /* when */
    final Map<Object, Object> map1 = newHashMap();

    /* then */
    assertThat(map1, is(instanceOf(expectedType)));
    assertThat(map1.getClass(), is(equalTo(expectedType)));
    assertThat(map1, is(equalTo(expected)));
    assertEquals(0, map1.size());

    /* given */
    final Map<Integer, String> expected2 = new HashMap<Integer, String>();

    /* when */
    final Map<Integer, String> map2 = newHashMap();

    /* then */
    assertThat(map2, is(instanceOf(expectedType)));
    assertThat(map2.getClass(), is(equalTo(expectedType)));
    assertThat(map2, is(equalTo(expected2)));
    assertEquals(0, map2.size());

    /* given */
    final Map<String, Integer> expected3 = new HashMap<String, Integer>();

    /* when */
    final Map<String, Integer> map3 = newHashMap();

    /* then */
    assertThat(map3, is(instanceOf(expectedType)));
    assertThat(map3.getClass(), is(equalTo(expectedType)));
    assertThat(map3, is(equalTo(expected3)));
    assertEquals(0, map3.size());

    /* given */
    final Map<Object, String> expected4 = new HashMap<Object, String>();

    /* when */
    final Map<Object, String> map4 = newHashMap();

    /* then */
    assertThat(map4, is(instanceOf(expectedType)));
    assertThat(map4.getClass(), is(equalTo(expectedType)));
    assertThat(map4, is(equalTo(expected4)));
    assertEquals(0, map4.size());
  }

  @Test
  public final void testNewHashMapMapOfQextendsKQextendsV()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_HASH_MAP_TYPE;

    final Map<Integer, String> expected1 = INTEGER_TO_STRING_HASH_MAP;
    final Map<Integer, String> input1 = INTEGER_TO_STRING_HASH_MAP;

    /* when */
    final Map<Integer, String> map1 = newHashMap(input1);

    /* then */
    assertThat(map1, is(instanceOf(expectedType)));
    assertThat(map1.getClass(), is(equalTo(expectedType)));
    assertThat(map1, is(equalTo(expected1)));
    assertEquals(expected1.size(), map1.size());

    /* given */
    final Map<String, Integer> expected2 = STRING_TO_INTEGER_HASH_MAP;
    final Map<String, Integer> input2 = STRING_TO_INTEGER_HASH_MAP;

    /* when */
    final Map<String, Integer> map2 = newHashMap(input2);

    /* then */
    assertThat(map2, is(instanceOf(expectedType)));
    assertThat(map2.getClass(), is(equalTo(expectedType)));
    assertThat(map2, is(equalTo(expected2)));
    assertEquals(expected2.size(), map2.size());

    /* given */
    final Map<Object, Integer> expected3 = OBJECT_TO_INTEGER_HASH_MAP;
    final Map<Object, Integer> input3 = OBJECT_TO_INTEGER_HASH_MAP;

    /* when */
    final Map<Object, Integer> map3 = newHashMap(input3);

    /* then */
    assertThat(map3, is(instanceOf(expectedType)));
    assertThat(map3.getClass(), is(equalTo(expectedType)));
    assertThat(map3, is(equalTo(expected3)));
    assertEquals(expected3.size(), map3.size());

    /* given */
    final Map<Object, String> expected4 = OBJECT_TO_STRING_HASH_MAP;
    final Map<Object, String> input4 = OBJECT_TO_STRING_HASH_MAP;

    /* when */
    final Map<Object, String> map4 = newHashMap(input4);

    /* then */
    assertThat(map4, is(instanceOf(expectedType)));
    assertThat(map4.getClass(), is(equalTo(expectedType)));
    assertThat(map4, is(equalTo(expected4)));
    assertEquals(expected4.size(), map4.size());
  }

  @Test
  public final void testNewHashMapWithInitialCapacity()
  {
    // do nothing
  }

  @SuppressWarnings("rawtypes")
  private static final Class<LinkedHashMap> EXPECTED_LINKED_HASH_MAP_TYPE = LinkedHashMap.class;

  @Test
  public final void testNewLinkedHashMap()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_LINKED_HASH_MAP_TYPE;

    @SuppressWarnings("rawtypes")
    final Map expected = new LinkedHashMap<Object, Object>();

    /* when */
    final Map<Object, Object> map1 = newLinkedHashMap();

    /* then */
    assertThat(map1, is(instanceOf(expectedType)));
    assertThat(map1.getClass(), is(equalTo(expectedType)));
    assertThat(map1, is(equalTo(expected)));
    assertEquals(0, map1.size());

    /* given */
    final Map<Integer, String> expected2 = new LinkedHashMap<Integer, String>();

    /* when */
    final Map<Integer, String> map2 = newLinkedHashMap();

    /* then */
    assertThat(map2, is(instanceOf(expectedType)));
    assertThat(map2.getClass(), is(equalTo(expectedType)));
    assertThat(map2, is(equalTo(expected2)));
    assertEquals(0, map2.size());

    /* given */
    final Map<String, Integer> expected3 = new LinkedHashMap<String, Integer>();

    /* when */
    final Map<String, Integer> map3 = newLinkedHashMap();

    /* then */
    assertThat(map3, is(instanceOf(expectedType)));
    assertThat(map3.getClass(), is(equalTo(expectedType)));
    assertThat(map3, is(equalTo(expected3)));
    assertEquals(0, map3.size());

    /* given */
    final Map<Object, String> expected4 = new LinkedHashMap<Object, String>();

    /* when */
    final Map<Object, String> map4 = newLinkedHashMap();

    /* then */
    assertThat(map4, is(instanceOf(expectedType)));
    assertThat(map4.getClass(), is(equalTo(expectedType)));
    assertThat(map4, is(equalTo(expected4)));
    assertEquals(0, map4.size());
  }

  @Test
  public final void testNewLinkedHashMapMapOfQextendsKQextendsV()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_LINKED_HASH_MAP_TYPE;

    final Map<Integer, String> expected1 = INTEGER_TO_STRING_HASH_MAP;
    final Map<Integer, String> input1 = INTEGER_TO_STRING_HASH_MAP;

    /* when */
    final Map<Integer, String> map1 = newLinkedHashMap(input1);

    /* then */
    assertThat(map1, is(instanceOf(expectedType)));
    assertThat(map1.getClass(), is(equalTo(expectedType)));
    assertThat(map1, is(equalTo(expected1)));
    assertEquals(expected1.size(), map1.size());
    assertThat(map1.keySet()
        .toArray(), is(equalTo(expected1.keySet()
        .toArray())));
    assertThat(map1.values()
        .toArray(), is(equalTo(expected1.values()
        .toArray())));

    /* given */
    final Map<String, Integer> expected2 = STRING_TO_INTEGER_HASH_MAP;
    final Map<String, Integer> input2 = STRING_TO_INTEGER_HASH_MAP;

    /* when */
    final Map<String, Integer> map2 = newLinkedHashMap(input2);

    /* then */
    assertThat(map2, is(instanceOf(expectedType)));
    assertThat(map2.getClass(), is(equalTo(expectedType)));
    assertThat(map2, is(equalTo(expected2)));
    assertEquals(expected2.size(), map2.size());
    assertThat(map2.keySet()
        .toArray(), is(equalTo(expected2.keySet()
        .toArray())));
    assertThat(map2.values()
        .toArray(), is(equalTo(expected2.values()
        .toArray())));

    /* given */
    final Map<Object, Integer> expected3 = OBJECT_TO_INTEGER_HASH_MAP;
    final Map<Object, Integer> input3 = OBJECT_TO_INTEGER_HASH_MAP;

    /* when */
    final Map<Object, Integer> map3 = newLinkedHashMap(input3);

    /* then */
    assertThat(map3, is(instanceOf(expectedType)));
    assertThat(map3.getClass(), is(equalTo(expectedType)));
    assertThat(map3, is(equalTo(expected3)));
    assertEquals(expected3.size(), map3.size());
    assertThat(map3.keySet()
        .toArray(), is(equalTo(expected3.keySet()
        .toArray())));
    assertThat(map3.values()
        .toArray(), is(equalTo(expected3.values()
        .toArray())));

    /* given */
    final Map<Object, String> expected4 = OBJECT_TO_STRING_HASH_MAP;
    final Map<Object, String> input4 = OBJECT_TO_STRING_HASH_MAP;

    /* when */
    final Map<Object, String> map4 = newLinkedHashMap(input4);

    /* then */
    assertThat(map4, is(instanceOf(expectedType)));
    assertThat(map4.getClass(), is(equalTo(expectedType)));
    assertThat(map4, is(equalTo(expected4)));
    assertEquals(expected4.size(), map4.size());
    assertThat(map4.keySet()
        .toArray(), is(equalTo(expected4.keySet()
        .toArray())));
    assertThat(map4.values()
        .toArray(), is(equalTo(expected4.values()
        .toArray())));
  }

  @Test
  public final void testNewLinkedHashMapWithInitialCapacity()
  {
    // do nothing
  }

  @SuppressWarnings("rawtypes")
  private static final Class<TreeMap> EXPECTED_TREE_MAP_TYPE = TreeMap.class;

  @Test
  public final void testNewTreeMap()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_TREE_MAP_TYPE;

    @SuppressWarnings("rawtypes")
    final Map expected = new TreeMap<Object, Object>();

    /* when */
    final Map<Object, Object> map1 = newTreeMap();

    /* then */
    assertThat(map1, is(instanceOf(expectedType)));
    assertThat(map1.getClass(), is(equalTo(expectedType)));
    assertThat(map1, is(equalTo(expected)));
    assertEquals(0, map1.size());

    /* given */
    final Map<Integer, String> expected2 = new TreeMap<Integer, String>();

    /* when */
    final Map<Integer, String> map2 = newTreeMap();

    /* then */
    assertThat(map2, is(instanceOf(expectedType)));
    assertThat(map2.getClass(), is(equalTo(expectedType)));
    assertThat(map2, is(equalTo(expected2)));
    assertEquals(0, map2.size());

    /* given */
    final Map<String, Integer> expected3 = new TreeMap<String, Integer>();

    /* when */
    final Map<String, Integer> map3 = newTreeMap();

    /* then */
    assertThat(map3, is(instanceOf(expectedType)));
    assertThat(map3.getClass(), is(equalTo(expectedType)));
    assertThat(map3, is(equalTo(expected3)));
    assertEquals(0, map3.size());

    /* given */
    final Map<Object, String> expected4 = new TreeMap<Object, String>();

    /* when */
    final Map<Object, String> map4 = newTreeMap();

    /* then */
    assertThat(map4, is(instanceOf(expectedType)));
    assertThat(map4.getClass(), is(equalTo(expectedType)));
    assertThat(map4, is(equalTo(expected4)));
    assertEquals(0, map4.size());
  }

  private static final Comparator<? super Integer> INTEGER_COMPARATOR = new Comparator<Integer>() {
    public int compare(final Integer o1, final Integer o2)
    {
      if (null == o1)
        return null == o2 ? 0 : 1;
      if (null == o2)
        return -1;

      @SuppressWarnings("boxing")
      final int i1 = o1;
      @SuppressWarnings("boxing")
      final int i2 = o2;
      return i1 < i2 ? -1 : i1 == i2 ? 0 : 1;
    }
  };

  private static final Comparator<? super String> STRING_COMPARATOR = new Comparator<String>() {
    public int compare(final String o1, final String o2)
    {
      if (null == o1)
        return null == o2 ? 0 : 1;
      if (null == o2)
        return -1;

      return o1.compareTo(o2);
    }
  };

  private static final Comparator<Object> OBJECT_COMPARATOR = new Comparator<Object>() {

    public int compare(final Object o1, final Object o2)
    {
      if (o1 instanceof Comparable && o2 instanceof Comparable)
      {
        @SuppressWarnings({ "unchecked", "rawtypes", "cast" })
        final int result = ((Comparable) o1).compareTo((Comparable) o2);
        return result;
      }
      if (null == o1)
        return null == o2 ? 0 : 1;

      if (null == o2)
        return -1;

      return o1.toString()
          .compareTo(o2.toString());
    }
  };

  @SuppressWarnings("rawtypes")
  @Test
  public final void testNewTreeMapComparatorOfQsuperK()
  {
    /* given */
    final Class expectedType = EXPECTED_TREE_MAP_TYPE;

    final NavigableMap expected = new TreeMap<Object, Object>();
    final Comparator<Object> comparator1 = OBJECT_COMPARATOR;

    /* when */
    final NavigableMap<Object, Object> map1 = newTreeMap(comparator1);

    /* then */
    assertThat(map1, is(instanceOf(expectedType)));
    assertThat(map1.getClass(), is(equalTo(expectedType)));
    assertThat(map1, is(equalTo(expected)));
    assertThat(map1.comparator(), is(equalTo((Comparator) comparator1)));
    assertEquals(0, map1.size());

    /* given */
    final NavigableMap<Integer, String> expected2 = new TreeMap<Integer, String>();
    final Comparator<? super Integer> comparator2 = INTEGER_COMPARATOR;

    /* when */
    final NavigableMap<Integer, String> map2 = newTreeMap(comparator2);

    /* then */
    assertThat(map2, is(instanceOf(expectedType)));
    assertThat(map2.getClass(), is(equalTo(expectedType)));
    assertThat(map2, is(equalTo(expected2)));
    assertThat(map2.comparator(), is(equalTo((Comparator) comparator2)));
    assertEquals(0, map2.size());

    /* given */
    final NavigableMap<String, Integer> expected3 = new TreeMap<String, Integer>();
    final Comparator<? super String> comparator3 = STRING_COMPARATOR;

    /* when */
    final NavigableMap<String, Integer> map3 = newTreeMap(comparator3);

    /* then */
    assertThat(map3, is(instanceOf(expectedType)));
    assertThat(map3.getClass(), is(equalTo(expectedType)));
    assertThat(map3, is(equalTo(expected3)));
    assertThat(map3.comparator(), is(equalTo((Comparator) comparator3)));
    assertEquals(0, map3.size());

    /* given */
    final NavigableMap<Object, String> expected4 = new TreeMap<Object, String>();
    final Comparator<? super Object> comparator4 = OBJECT_COMPARATOR;

    /* when */
    final NavigableMap<Object, String> map4 = newTreeMap(comparator4);

    /* then */
    assertThat(map4, is(instanceOf(expectedType)));
    assertThat(map4.getClass(), is(equalTo(expectedType)));
    assertThat(map4, is(equalTo(expected4)));
    assertThat(map4.comparator(), is(equalTo((Comparator) comparator4)));
    assertEquals(0, map4.size());
  }

  interface GenericMapFilter
  {
    <K, V, T extends Map<? extends K, ? extends V>, C extends Condition1<? super K>> Map<K, V> filter(C condition, T map);
  }

  private static final GenericMapFilter GENERIC_MAP_FILTER = new GenericMapFilter() {
    public <K, V, T extends Map<? extends K, ? extends V>, C extends Condition1<? super K>> Map<K, V> filter(
        C condition, T map)
    {
      final Map<K, V> filteredMap = new HashMap<K, V>();
      for (final Entry<? extends K, ? extends V> entry : map.entrySet())
      {
        if (condition.isApplicable(entry.getKey()))
        {
          filteredMap.put(entry.getKey(), entry.getValue());
        }
      }
      return filteredMap;
    }
  };

  @SuppressWarnings("rawtypes")
  @Test
  public final void testNewTreeMapFromSortedMapSortedMapOfKQextendsV()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_TREE_MAP_TYPE;

    final NavigableMap<Object, Object> tempExpected1 = new TreeMap<Object, Object>();
    tempExpected1.putAll(GENERIC_MAP_FILTER.filter(NOT_NULL_CONDITION, OBJECT_TO_INTEGER_HASH_MAP));
    final SortedMap<Object, Object> expected1 = Collections.unmodifiableSortedMap(tempExpected1);

    final SortedMap<Object, Object> input1 = new TreeMap<Object, Object>();
    input1.putAll(expected1);

    /* when */
    final NavigableMap<Object, Object> map1 = newTreeMapFromSortedMap(input1);

    /* then */
    assertThat(map1, is(instanceOf(expectedType)));
    assertThat(map1.getClass(), is(equalTo(expectedType)));
    assertThat(map1, is(equalTo(expected1)));
    assertThat(map1.comparator(), is(nullValue()));
    assertEquals(expected1.size(), map1.size());
    assertThat(map1.keySet()
        .toArray(), is(equalTo(expected1.keySet()
        .toArray())));
    assertThat(map1.values()
        .toArray(), is(equalTo(expected1.values()
        .toArray())));

    /* given */
    final Comparator<Object> comparator1 = OBJECT_COMPARATOR;

    final SortedMap<Object, Object> input1_1 = new TreeMap<Object, Object>(comparator1);
    input1_1.putAll(expected1);

    /* when */
    final NavigableMap<Object, Object> map1_1 = newTreeMapFromSortedMap(input1_1);

    /* then */
    assertThat(map1_1, is(instanceOf(expectedType)));
    assertThat(map1_1.getClass(), is(equalTo(expectedType)));
    assertThat(map1_1, is(equalTo(expected1)));
    assertThat(map1_1.comparator(), is(equalTo((Comparator) comparator1)));
    assertEquals(expected1.size(), map1_1.size());
    assertThat(map1_1.keySet()
        .toArray(), is(equalTo(expected1.keySet()
        .toArray())));
    assertThat(map1_1.values()
        .toArray(), is(equalTo(expected1.values()
        .toArray())));

    /* given */
    final NavigableMap<Integer, String> tempExpected2 = new TreeMap<Integer, String>();
    tempExpected2.putAll(GENERIC_MAP_FILTER.filter(NOT_NULL_CONDITION, INTEGER_TO_STRING_HASH_MAP));
    final SortedMap<Integer, String> expected2 = Collections.unmodifiableSortedMap(tempExpected2);

    final SortedMap<Integer, String> input2 = new TreeMap<Integer, String>();
    input2.putAll(expected2);

    /* when */
    final TreeMap<Integer, String> map2 = newTreeMapFromSortedMap(input2);

    /* then */
    assertThat(map2, is(instanceOf(expectedType)));
    assertThat(map2.getClass(), is(equalTo(expectedType)));
    assertThat(map2, is(equalTo(expected2)));
    assertThat(map2.comparator(), is(nullValue()));
    assertEquals(expected2.size(), map2.size());
    assertThat(map2.keySet()
        .toArray(), is(equalTo(expected2.keySet()
        .toArray())));
    assertThat(map2.values()
        .toArray(), is(equalTo(expected2.values()
        .toArray())));

    /* given */
    final Comparator<? super Integer> comparator2 = INTEGER_COMPARATOR;

    final SortedMap<Integer, String> input2_1 = new TreeMap<Integer, String>(comparator2);
    input2_1.putAll(expected2);

    /* when */
    final NavigableMap<Integer, String> map2_1 = newTreeMapFromSortedMap(input2_1);

    /* then */
    assertThat(map2_1, is(instanceOf(expectedType)));
    assertThat(map2_1.getClass(), is(equalTo(expectedType)));
    assertThat(map2_1, is(equalTo(expected2)));
    assertThat(map2_1.comparator(), is(equalTo((Comparator) comparator2)));
    assertEquals(expected2.size(), map2_1.size());
    assertThat(map2_1.keySet()
        .toArray(), is(equalTo(expected2.keySet()
        .toArray())));
    assertThat(map2_1.values()
        .toArray(), is(equalTo(expected2.values()
        .toArray())));

    /* given */
    final NavigableMap<String, Integer> tempExpected3 = new TreeMap<String, Integer>();
    tempExpected3.putAll(GENERIC_MAP_FILTER.filter(NOT_NULL_CONDITION, STRING_TO_INTEGER_HASH_MAP));
    final SortedMap<String, Integer> expected3 = Collections.unmodifiableSortedMap(tempExpected3);

    final SortedMap<String, Integer> input3 = new TreeMap<String, Integer>();
    input3.putAll(expected3);

    /* when */
    final NavigableMap<String, Integer> map3 = newTreeMapFromSortedMap(input3);

    /* then */
    assertThat(map3, is(instanceOf(expectedType)));
    assertThat(map3.getClass(), is(equalTo(expectedType)));
    assertThat(map3, is(equalTo(expected3)));
    assertThat(map3.comparator(), is(nullValue()));
    assertEquals(expected3.size(), map3.size());
    assertThat(map3.keySet()
        .toArray(), is(equalTo(expected3.keySet()
        .toArray())));
    assertThat(map3.values()
        .toArray(), is(equalTo(expected3.values()
        .toArray())));

    /* given */
    final Comparator<? super String> comparator3 = STRING_COMPARATOR;

    final SortedMap<String, Integer> input3_1 = new TreeMap<String, Integer>(comparator3);
    input3_1.putAll(expected3);

    /* when */
    final NavigableMap<String, Integer> map3_1 = newTreeMapFromSortedMap(input3_1);

    /* then */
    assertThat(map3_1, is(instanceOf(expectedType)));
    assertThat(map3_1.getClass(), is(equalTo(expectedType)));
    assertThat(map3_1, is(equalTo(expected3)));
    assertThat(map3_1.comparator(), is(equalTo((Comparator) comparator3)));
    assertEquals(expected3.size(), map3_1.size());
    assertThat(map3_1.keySet()
        .toArray(), is(equalTo(expected3.keySet()
        .toArray())));
    assertThat(map3_1.values()
        .toArray(), is(equalTo(expected3.values()
        .toArray())));

    /* given */
    final NavigableMap<Object, String> tempExpected4 = new TreeMap<Object, String>();
    tempExpected4.putAll(GENERIC_MAP_FILTER.filter(NOT_NULL_CONDITION, OBJECT_TO_STRING_HASH_MAP));
    final SortedMap<Object, String> expected4 = Collections.unmodifiableSortedMap(tempExpected4);

    final SortedMap<Object, String> input4 = new TreeMap<Object, String>();
    input4.putAll(expected4);

    /* when */
    final NavigableMap<Object, String> map4 = newTreeMapFromSortedMap(input4);

    /* then */
    assertThat(map4, is(instanceOf(expectedType)));
    assertThat(map4.getClass(), is(equalTo(expectedType)));
    assertThat(map4, is(equalTo(expected4)));
    assertThat(map4.comparator(), is(nullValue()));
    assertEquals(expected4.size(), map4.size());
    assertThat(map4.keySet()
        .toArray(), is(equalTo(expected4.keySet()
        .toArray())));
    assertThat(map4.values()
        .toArray(), is(equalTo(expected4.values()
        .toArray())));

    /* given */
    final Comparator<? super Object> comparator4 = OBJECT_COMPARATOR;

    final SortedMap<Object, String> input4_1 = new TreeMap<Object, String>(comparator4);
    input4_1.putAll(expected4);

    /* when */
    final NavigableMap<Object, String> map4_1 = newTreeMapFromSortedMap(input4_1);

    /* then */
    assertThat(map4_1, is(instanceOf(expectedType)));
    assertThat(map4_1.getClass(), is(equalTo(expectedType)));
    assertThat(map4_1, is(equalTo(expected4)));
    assertThat(map4_1.comparator(), is(equalTo((Comparator) comparator4)));
    assertEquals(expected4.size(), map4_1.size());
    assertThat(map4_1.keySet()
        .toArray(), is(equalTo(expected4.keySet()
        .toArray())));
    assertThat(map4_1.values()
        .toArray(), is(equalTo(expected4.values()
        .toArray())));

    /* given */
    final NavigableMap<Object, Integer> tempExpected5 = new TreeMap<Object, Integer>();
    tempExpected5.putAll(GENERIC_MAP_FILTER.filter(NOT_NULL_CONDITION, OBJECT_TO_INTEGER_HASH_MAP));
    final SortedMap<Object, Integer> expected5 = Collections.unmodifiableSortedMap(tempExpected5);

    final NavigableMap<Object, Integer> input5 = new TreeMap<Object, Integer>();
    input5.putAll(expected5);

    /* when */
    final NavigableMap<Object, Integer> map5 = newTreeMapFromSortedMap(input5);

    /* then */
    assertThat(map5, is(instanceOf(expectedType)));
    assertThat(map5.getClass(), is(equalTo(expectedType)));
    assertThat(map5, is(equalTo(expected5)));
    assertThat(map5.comparator(), is(nullValue()));
    assertEquals(expected5.size(), map5.size());
    assertThat(map5.keySet()
        .toArray(), is(equalTo(expected5.keySet()
        .toArray())));
    assertThat(map5.values()
        .toArray(), is(equalTo(expected5.values()
        .toArray())));

    /* given */
    final Comparator<? super Object> comparator5 = OBJECT_COMPARATOR;

    final SortedMap<Object, Integer> input5_1 = new TreeMap<Object, Integer>(comparator5);
    input5_1.putAll(expected5);

    /* when */
    final NavigableMap<Object, Integer> map5_1 = newTreeMapFromSortedMap(input5_1);

    /* then */
    assertThat(map5_1, is(instanceOf(expectedType)));
    assertThat(map5_1.getClass(), is(equalTo(expectedType)));
    assertThat(map5_1, is(equalTo(expected5)));
    assertThat(map5_1.comparator(), is(equalTo((Comparator) comparator5)));
    assertEquals(expected5.size(), map5_1.size());
    assertThat(map5_1.keySet()
        .toArray(), is(equalTo(expected5.keySet()
        .toArray())));
    assertThat(map5_1.values()
        .toArray(), is(equalTo(expected5.values()
        .toArray())));
  }

  @Test
  public final void testNewTreeMapFromMapMapOfQextendsKQextendsV()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_TREE_MAP_TYPE;

    final NavigableMap<ComparableObject, Object> tempExpected1 = new TreeMap<ComparableObject, Object>();
    tempExpected1.putAll(GENERIC_MAP_FILTER.filter(NOT_NULL_CONDITION, COMPARABLE_OBJECT_TO_INTEGER_HASH_MAP));
    final Map<ComparableObject, Object> expected1 = Collections.unmodifiableMap(tempExpected1);

    final Map<ComparableObject, Object> input1 = new HashMap<ComparableObject, Object>(expected1);
    input1.putAll(expected1);

    /* when */
    final TreeMap<ComparableObject, Object> map1 = newTreeMapFromMap(input1);

    /* then */
    assertThat(map1, is(instanceOf(expectedType)));
    assertThat(map1.getClass(), is(equalTo(expectedType)));
    assertThat(map1, is(equalTo(expected1)));
    assertThat(map1.comparator(), is(nullValue()));
    assertEquals(expected1.size(), map1.size());
    assertThat(map1.keySet()
        .toArray(), is(equalTo(expected1.keySet()
        .toArray())));
    assertThat(map1.values()
        .toArray(), is(equalTo(expected1.values()
        .toArray())));

    final NavigableMap<ComparableObject, Object> tempExpected1_1 = new TreeMap<ComparableObject, Object>();
    tempExpected1_1.putAll(GENERIC_MAP_FILTER.filter(NOT_NULL_CONDITION, COMPARABLE_OBJECT_TO_STRING_HASH_MAP));
    final Map<ComparableObject, Object> expected1_1 = Collections.unmodifiableMap(tempExpected1_1);

    final Map<ComparableObject, Object> input1_1 = new HashMap<ComparableObject, Object>(expected1_1);
    input1_1.putAll(expected1_1);

    /* when */
    final TreeMap<ComparableObject, Object> map1_1 = newTreeMapFromMap(input1_1);

    /* then */
    assertThat(map1_1, is(instanceOf(expectedType)));
    assertThat(map1_1.getClass(), is(equalTo(expectedType)));
    assertThat(map1_1, is(equalTo(expected1_1)));
    assertThat(map1_1.comparator(), is(nullValue()));
    assertEquals(expected1_1.size(), map1_1.size());
    assertThat(map1_1.keySet()
        .toArray(), is(equalTo(expected1_1.keySet()
        .toArray())));
    assertThat(map1_1.values()
        .toArray(), is(equalTo(expected1_1.values()
        .toArray())));

    /* given */
    final NavigableMap<Integer, String> tempExpected2 = new TreeMap<Integer, String>();
    tempExpected2.putAll(GENERIC_MAP_FILTER.filter(NOT_NULL_CONDITION, INTEGER_TO_STRING_HASH_MAP));
    final SortedMap<Integer, String> expected2 = Collections.unmodifiableSortedMap(tempExpected2);

    final Map<Integer, String> input2 = new HashMap<Integer, String>();
    input2.putAll(expected2);

    /* when */
    final TreeMap<Integer, String> map2 = newTreeMapFromMap(input2);

    /* then */
    assertThat(map2, is(instanceOf(expectedType)));
    assertThat(map2.getClass(), is(equalTo(expectedType)));
    assertThat(map2, is(equalTo(expected2)));
    assertThat(map2.comparator(), is(nullValue()));
    assertEquals(expected2.size(), map2.size());
    assertThat(map2.keySet()
        .toArray(), is(equalTo(expected2.keySet()
        .toArray())));
    assertThat(map2.values()
        .toArray(), is(equalTo(expected2.values()
        .toArray())));

    /* given */
    final NavigableMap<String, Integer> tempExpected3 = new TreeMap<String, Integer>();
    tempExpected3.putAll(GENERIC_MAP_FILTER.filter(NOT_NULL_CONDITION, STRING_TO_INTEGER_HASH_MAP));
    final SortedMap<String, Integer> expected3 = Collections.unmodifiableSortedMap(tempExpected3);

    final Map<String, Integer> input3 = new HashMap<String, Integer>();
    input3.putAll(expected3);

    /* when */
    final TreeMap<String, Integer> map3 = newTreeMapFromMap(input3);

    /* then */
    assertThat(map3, is(instanceOf(expectedType)));
    assertThat(map3.getClass(), is(equalTo(expectedType)));
    assertThat(map3, is(equalTo(expected3)));
    assertThat(map3.comparator(), is(nullValue()));
    assertEquals(expected3.size(), map3.size());
    assertThat(map3.keySet()
        .toArray(), is(equalTo(expected3.keySet()
        .toArray())));
    assertThat(map3.values()
        .toArray(), is(equalTo(expected3.values()
        .toArray())));

    /* given */
    final NavigableMap<ComparableObject, String> tempExpected4 = new TreeMap<ComparableObject, String>();
    tempExpected4.putAll(GENERIC_MAP_FILTER.filter(NOT_NULL_CONDITION, COMPARABLE_OBJECT_TO_STRING_HASH_MAP));
    final SortedMap<ComparableObject, String> expected4 = Collections.unmodifiableSortedMap(tempExpected4);

    final Map<ComparableObject, String> input4 = new HashMap<ComparableObject, String>();
    input4.putAll(expected4);

    /* when */
    final TreeMap<ComparableObject, String> map4 = newTreeMapFromMap(input4);

    /* then */
    assertThat(map4, is(instanceOf(expectedType)));
    assertThat(map4.getClass(), is(equalTo(expectedType)));
    assertThat(map4, is(equalTo(expected4)));
    assertThat(map4.comparator(), is(nullValue()));
    assertEquals(expected4.size(), map4.size());
    assertThat(map4.keySet()
        .toArray(), is(equalTo(expected4.keySet()
        .toArray())));
    assertThat(map4.values()
        .toArray(), is(equalTo(expected4.values()
        .toArray())));

    /* given */
    final NavigableMap<ComparableObject, Integer> tempExpected5 = new TreeMap<ComparableObject, Integer>();
    tempExpected5.putAll(GENERIC_MAP_FILTER.filter(NOT_NULL_CONDITION, COMPARABLE_OBJECT_TO_INTEGER_HASH_MAP));
    final SortedMap<ComparableObject, Integer> expected5 = Collections.unmodifiableSortedMap(tempExpected5);

    final Map<ComparableObject, Integer> input5 = new HashMap<ComparableObject, Integer>();
    input5.putAll(expected5);

    /* when */
    final TreeMap<ComparableObject, Integer> map5 = newTreeMapFromMap(input5);

    /* then */
    assertThat(map5, is(instanceOf(expectedType)));
    assertThat(map5.getClass(), is(equalTo(expectedType)));
    assertThat(map5, is(equalTo(expected5)));
    assertThat(map5.comparator(), is(nullValue()));
    assertEquals(expected5.size(), map5.size());
    assertThat(map5.keySet()
        .toArray(), is(equalTo(expected5.keySet()
        .toArray())));
    assertThat(map5.values()
        .toArray(), is(equalTo(expected5.values()
        .toArray())));
  }

  @SuppressWarnings("rawtypes")
  @Test
  public final void testNewTreeMapComparatorOfQsuperKMapOfQextendsKQextendsV()
  {
    /* given */
    final Class expectedType = EXPECTED_TREE_MAP_TYPE;

    final Comparator<Object> comparator1 = OBJECT_COMPARATOR;

    final Map<Object, Object> tempInput1 =
      Collections.unmodifiableMap(new HashMap<Object, Object>(OBJECT_TO_INTEGER_HASH_MAP));

    final NavigableMap<Object, Object> tempExpected1 = new TreeMap<Object, Object>(comparator1);
    tempExpected1.putAll(new HashMap<Object, Object>(tempInput1));
    final SortedMap<Object, Object> expected1 = Collections.unmodifiableSortedMap(tempExpected1);

    final Map<Object, Object> input1 = new HashMap<Object, Object>(tempInput1);

    /* when */
    final TreeMap<Object, Object> map1 = newTreeMap(comparator1, input1);

    /* then */
    assertThat(map1, is(instanceOf(expectedType)));
    assertThat(map1.getClass(), is(equalTo(expectedType)));
    assertThat(map1, is(equalTo(expected1)));
    assertThat(map1.comparator(), is(equalTo((Comparator) comparator1)));
    assertEquals(expected1.size(), map1.size());
    assertThat(map1.keySet()
        .toArray(), is(equalTo(expected1.keySet()
        .toArray())));
    assertThat(map1.values()
        .toArray(), is(equalTo(expected1.values()
        .toArray())));

    /* given */
    final Comparator<Object> comparator1_1 = OBJECT_COMPARATOR;

    final Map<Object, Object> tempInput1_1 = input1;
    final SortedMap<Object, Object> tempExpected1_1 = new TreeMap<Object, Object>(comparator1_1);
    tempExpected1_1.putAll(new HashMap<Object, Object>(tempInput1_1));

    final SortedMap<Object, Object> expected1_1 = Collections.unmodifiableSortedMap(tempExpected1_1);

    final Map<Object, Object> input1_1 = Collections.unmodifiableMap(new HashMap<Object, Object>(tempInput1_1));

    /* when */
    final TreeMap<Object, Object> map1_1 = newTreeMap(comparator1_1, input1_1);

    /* then */
    assertThat(map1_1, is(instanceOf(expectedType)));
    assertThat(map1_1.getClass(), is(equalTo(expectedType)));
    assertThat(map1_1, is(equalTo(expected1_1)));
    assertThat(map1_1.comparator(), is(equalTo((Comparator) comparator1_1)));
    assertEquals(expected1_1.size(), map1_1.size());
    assertThat(map1_1.keySet()
        .toArray(), is(equalTo(expected1_1.keySet()
        .toArray())));
    assertThat(map1_1.values()
        .toArray(), is(equalTo(expected1_1.values()
        .toArray())));

    /* given */
    final Comparator<? super Integer> comparator2 = INTEGER_COMPARATOR;
    final Map<Integer, String> tempInput2 =
      Collections.unmodifiableMap(new HashMap<Integer, String>(INTEGER_TO_STRING_HASH_MAP));

    final NavigableMap<Integer, String> tempExpected2 = new TreeMap<Integer, String>(comparator2);
    tempExpected2.putAll(new HashMap<Integer, String>(tempInput2));
    final SortedMap<Integer, String> expected2 = Collections.unmodifiableSortedMap(tempExpected2);

    final Map<Integer, String> input2 = new HashMap<Integer, String>(tempInput2);

    /* when */
    final TreeMap<Integer, String> map2 = newTreeMap(comparator2, input2);

    /* then */
    assertThat(map2, is(instanceOf(expectedType)));
    assertThat(map2.getClass(), is(equalTo(expectedType)));
    assertThat(map2, is(equalTo(expected2)));
    assertThat(map2.comparator(), is(equalTo((Comparator) comparator2)));
    assertEquals(expected2.size(), map2.size());
    assertThat(map2.keySet()
        .toArray(), is(equalTo(expected2.keySet()
        .toArray())));
    assertThat(map2.values()
        .toArray(), is(equalTo(expected2.values()
        .toArray())));

    /* given */
    final Comparator<? super Integer> comparator2_1 = INTEGER_COMPARATOR;

    final Map<Integer, String> tempInput2_1 = input2;
    final SortedMap<Integer, String> tempExpected2_1 = new TreeMap<Integer, String>(comparator2_1);
    tempExpected2_1.putAll(new HashMap<Integer, String>(tempInput2_1));
    final SortedMap<Integer, String> expected2_1 = Collections.unmodifiableSortedMap(tempExpected2_1);

    final Map<Integer, String> input2_1 = Collections.unmodifiableMap(new HashMap<Integer, String>(tempInput2_1));

    /* when */
    final TreeMap<Integer, String> map2_1 = newTreeMap(comparator2_1, input2_1);

    /* then */
    assertThat(map2_1, is(instanceOf(expectedType)));
    assertThat(map2_1.getClass(), is(equalTo(expectedType)));
    assertThat(map2_1, is(equalTo(expected2_1)));
    assertThat(map2_1.comparator(), is(equalTo((Comparator) comparator2_1)));
    assertEquals(expected2_1.size(), map2_1.size());
    assertThat(map2_1.keySet()
        .toArray(), is(equalTo(expected2_1.keySet()
        .toArray())));
    assertThat(map2_1.values()
        .toArray(), is(equalTo(expected2_1.values()
        .toArray())));

    /* given */
    final Comparator<? super String> comparator3 = STRING_COMPARATOR;
    final Map<String, Integer> tempInput3 =
      Collections.unmodifiableMap(new HashMap<String, Integer>(STRING_TO_INTEGER_HASH_MAP));

    final NavigableMap<String, Integer> tempExpected3 = new TreeMap<String, Integer>(comparator3);
    tempExpected3.putAll(new HashMap<String, Integer>(tempInput3));
    final SortedMap<String, Integer> expected3 = Collections.unmodifiableSortedMap(tempExpected3);

    final Map<String, Integer> input3 = new HashMap<String, Integer>(tempInput3);

    /* when */
    final TreeMap<String, Integer> map3 = newTreeMap(comparator3, input3);

    /* then */
    assertThat(map3, is(instanceOf(expectedType)));
    assertThat(map3.getClass(), is(equalTo(expectedType)));
    assertThat(map3, is(equalTo(expected3)));
    assertThat(map3.comparator(), is(equalTo((Comparator) comparator3)));
    assertEquals(expected3.size(), map3.size());
    assertThat(map3.keySet()
        .toArray(), is(equalTo(expected3.keySet()
        .toArray())));
    assertThat(map3.values()
        .toArray(), is(equalTo(expected3.values()
        .toArray())));

    /* given */
    final Comparator<? super String> comparator3_1 = STRING_COMPARATOR;

    final Map<String, Integer> tempInput3_1 = input3;
    final SortedMap<String, Integer> tempExpected3_1 = new TreeMap<String, Integer>(comparator3_1);
    tempExpected3_1.putAll(new HashMap<String, Integer>(tempInput3_1));
    final SortedMap<String, Integer> expected3_1 = Collections.unmodifiableSortedMap(tempExpected3_1);

    final Map<String, Integer> input3_1 = Collections.unmodifiableMap(new HashMap<String, Integer>(tempInput3_1));

    /* when */
    final TreeMap<String, Integer> map3_1 = newTreeMap(comparator3_1, input3_1);

    /* then */
    assertThat(map3_1, is(instanceOf(expectedType)));
    assertThat(map3_1.getClass(), is(equalTo(expectedType)));
    assertThat(map3_1, is(equalTo(expected3_1)));
    assertThat(map3_1.comparator(), is(equalTo((Comparator) comparator3_1)));
    assertEquals(expected3_1.size(), map3_1.size());
    assertThat(map3_1.keySet()
        .toArray(), is(equalTo(expected3_1.keySet()
        .toArray())));
    assertThat(map3_1.values()
        .toArray(), is(equalTo(expected3_1.values()
        .toArray())));

    /* given */
    final Comparator<? super Object> comparator4 = OBJECT_COMPARATOR;
    final Map<Object, String> tempInput4 =
      Collections.unmodifiableMap(new HashMap<Object, String>(OBJECT_TO_STRING_HASH_MAP));

    final NavigableMap<Object, String> tempExpected4 = new TreeMap<Object, String>(comparator4);
    tempExpected4.putAll(new HashMap<Object, String>(tempInput4));
    final SortedMap<Object, String> expected4 = Collections.unmodifiableSortedMap(tempExpected4);

    final Map<Object, String> input4 = new HashMap<Object, String>(tempInput4);

    /* when */
    final TreeMap<Object, String> map4 = newTreeMap(comparator4, input4);

    /* then */
    assertThat(map4, is(instanceOf(expectedType)));
    assertThat(map4.getClass(), is(equalTo(expectedType)));
    assertThat(map4, is(equalTo(expected4)));
    assertThat(map4.comparator(), is(equalTo((Comparator) comparator4)));
    assertEquals(expected4.size(), map4.size());
    assertThat(map4.keySet()
        .toArray(), is(equalTo(expected4.keySet()
        .toArray())));
    assertThat(map4.values()
        .toArray(), is(equalTo(expected4.values()
        .toArray())));

    /* given */
    final Comparator<? super Object> comparator4_1 = OBJECT_COMPARATOR;

    final Map<Object, String> tempInput4_1 = input4;

    final NavigableMap<Object, String> tempExpected4_1 = new TreeMap<Object, String>(comparator4);
    tempExpected4_1.putAll(new HashMap<Object, String>(tempInput4_1));
    final SortedMap<Object, String> expected4_1 = Collections.unmodifiableSortedMap(tempExpected4_1);

    final Map<Object, String> input4_1 = new HashMap<Object, String>(tempInput4_1);

    /* when */
    final TreeMap<Object, String> map4_1 = newTreeMap(comparator4_1, input4_1);

    /* then */
    assertThat(map4_1, is(instanceOf(expectedType)));
    assertThat(map4_1.getClass(), is(equalTo(expectedType)));
    assertThat(map4_1, is(equalTo(expected4_1)));
    assertThat(map4_1.comparator(), is(equalTo((Comparator) comparator4_1)));
    assertEquals(expected4_1.size(), map4_1.size());
    assertThat(map4_1.keySet()
        .toArray(), is(equalTo(expected4_1.keySet()
        .toArray())));
    assertThat(map4_1.values()
        .toArray(), is(equalTo(expected4_1.values()
        .toArray())));

    /* given */
    final Comparator<? super Object> comparator5 = OBJECT_COMPARATOR;

    final Map<Object, Integer> tempInput5 =
      Collections.unmodifiableMap(new HashMap<Object, Integer>(OBJECT_TO_INTEGER_HASH_MAP));

    final NavigableMap<Object, Integer> tempExpected5 = new TreeMap<Object, Integer>(comparator5);
    tempExpected5.putAll(new HashMap<Object, Integer>(tempInput5));
    final SortedMap<Object, Integer> expected5 = Collections.unmodifiableSortedMap(tempExpected5);

    final Map<Object, Integer> input5 = new HashMap<Object, Integer>(tempInput5);

    /* when */
    final TreeMap<Object, Integer> map5 = newTreeMap(comparator5, input5);

    /* then */
    assertThat(map5, is(instanceOf(expectedType)));
    assertThat(map5.getClass(), is(equalTo(expectedType)));
    assertThat(map5, is(equalTo(expected5)));
    assertThat(map5.comparator(), is(equalTo((Comparator) comparator5)));
    assertEquals(expected5.size(), map5.size());
    assertThat(map5.keySet()
        .toArray(), is(equalTo(expected5.keySet()
        .toArray())));
    assertThat(map5.values()
        .toArray(), is(equalTo(expected5.values()
        .toArray())));

    /* given */
    final Comparator<? super Object> comparator5_1 = OBJECT_COMPARATOR;

    final Map<Object, Integer> tempInput5_1 = tempInput5;

    final NavigableMap<Object, Integer> tempExpected5_1 = new TreeMap<Object, Integer>(comparator5_1);
    tempExpected5_1.putAll(new HashMap<Object, Integer>(tempInput5_1));
    final SortedMap<Object, Integer> expected5_1 = Collections.unmodifiableSortedMap(tempExpected5_1);

    final Map<Object, Integer> input5_1 = new HashMap<Object, Integer>(tempInput5_1);
    input5_1.putAll(expected5_1);

    /* when */
    final TreeMap<Object, Integer> map5_1 = newTreeMap(comparator5_1, input5_1);

    /* then */
    assertThat(map5_1, is(instanceOf(expectedType)));
    assertThat(map5_1.getClass(), is(equalTo(expectedType)));
    assertThat(map5_1, is(equalTo(expected5_1)));
    assertThat(map5_1.comparator(), is(equalTo((Comparator) comparator5_1)));
    assertEquals(expected5_1.size(), map5_1.size());
    assertThat(map5_1.keySet()
        .toArray(), is(equalTo(expected5_1.keySet()
        .toArray())));
    assertThat(map5_1.values()
        .toArray(), is(equalTo(expected5_1.values()
        .toArray())));
  }
}
