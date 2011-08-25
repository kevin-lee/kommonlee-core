package org.elixirian.common.collect;

import static org.elixirian.common.collect.Sets.*;
import static org.elixirian.common.collect.UtilForTesting.*;
import static org.elixirian.common.test.CommonTestHelper.*;
import static org.elixirian.common.util.MessageFormatter.*;
import static org.elixirian.common.util.Objects.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.elixirian.common.collect.UtilForTesting.ComparableObject;
import org.elixirian.common.test.CauseCheckableExpectedException;
import org.elixirian.common.test.CommonTestHelper.Accessibility;
import org.elixirian.common.type.Condition1;
import org.elixirian.common.type.filter.Filter1;
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
public class SetsTest
{

  private static final Condition1<Object> NOT_NULL_CONDITION = new Condition1<Object>() {
    @Override
    public boolean isApplicable(final Object input)
    {
      return isNotNull(input);
    }
  };

  private static final Filter1<Iterable<Integer>, Condition1<Object>, List<Integer>> INTEGER_FILTER =
    new Filter1<Iterable<Integer>, Condition1<Object>, List<Integer>>() {
      @Override
      public List<Integer> filter(final Condition1<Object> condition, final Iterable<Integer> source)
      {
        final List<Integer> list = new ArrayList<Integer>();
        for (final Integer value : source)
          if (condition.isApplicable(value))
            list.add(value);
        return list;
      }
    };

  private static final Filter1<Iterable<String>, Condition1<Object>, List<String>> STRING_FILTER =
    new Filter1<Iterable<String>, Condition1<Object>, List<String>>() {
      @Override
      public List<String> filter(final Condition1<Object> condition, final Iterable<String> source)
      {
        final List<String> list = new ArrayList<String>();
        for (final String value : source)
          if (condition.isApplicable(value))
            list.add(value);
        return list;
      }
    };

  private static final Filter1<List<Object>, Condition1<Object>, List<Object>> OBJECT_FILTER =
    new Filter1<List<Object>, Condition1<Object>, List<Object>>() {
      @Override
      public List<Object> filter(final Condition1<Object> condition, final List<Object> source)
      {
        final List<Object> list = new ArrayList<Object>();
        for (final Object value : source)
          if (condition.isApplicable(value))
            list.add(value);
        return list;
      }
    };

  private static final Filter1<Iterable<ComparableObject>, Condition1<Object>, List<ComparableObject>> COMPARABLE_OBJECT_FILTER =
    new Filter1<Iterable<ComparableObject>, Condition1<Object>, List<ComparableObject>>() {
      @Override
      public List<ComparableObject> filter(final Condition1<Object> condition, final Iterable<ComparableObject> source)
      {
        final List<ComparableObject> list = new ArrayList<ComparableObject>();
        for (final ComparableObject value : source)
          if (condition.isApplicable(value))
            list.add(value);
        return list;
      }
    };

  @SuppressWarnings("boxing")
  private static final Integer[] INTEGERS = { -955, -34, -120, 0, 23, null, 50, -3, 20, 110, 999, 20, 0, -120, 346,
      23267, 21, 111, 55, 923, 375, 632, 6099, 41290, null, 337, 65, 337, 337, 325, 299, 73927, null, 293, 926, 58219,
      86271, 337 };

  private static final List<Integer> INTEGER_LIST = Collections.unmodifiableList(Arrays.asList(INTEGERS));

  private static final Set<Integer> INTEGER_VALUE_HASH_SET = Collections.unmodifiableSet(new HashSet<Integer>(
      INTEGER_LIST));

  private static final Set<Integer> INTEGER_VALUE_LINKED_HASH_SET =
    Collections.unmodifiableSet(new LinkedHashSet<Integer>(INTEGER_LIST));

  private static final Set<Integer> INTEGER_VALUE_TREE_SET = Collections.unmodifiableSet(new TreeSet<Integer>(
      INTEGER_FILTER.filter(NOT_NULL_CONDITION, INTEGER_LIST)));

  private static final Set<Integer> INTEGER_VALUE_TREE_SET_WITH_COMPARATOR;

  private static final String[] STRINGS = { "Hello, ", "Kevin", "Lee", "", "blah", "Test String", "blah", "blah",
      "Hello", "Kevin", null, "blah blah", "Hello", ", ", "World", "blah", ", ", "blah", "", " ", "Kevin", " ", "Lee",
      " ", "0123456789", "Kevin", null, "Lee", " ", "blah", null, "blah blah", "Kevin", "Testing", "Collection" };

  private static final List<String> STRING_LIST = Collections.unmodifiableList(Arrays.asList(STRINGS));

  private static final Set<String> STRING_VALUE_HASH_SET =
    Collections.unmodifiableSet(new HashSet<String>(STRING_LIST));

  private static final Set<String> STRING_VALUE_LINKED_HASH_SET =
    Collections.unmodifiableSet(new LinkedHashSet<String>(STRING_LIST));

  private static final Set<String> STRING_VALUE_TREE_SET = Collections.unmodifiableSet(new TreeSet<String>(
      STRING_FILTER.filter(NOT_NULL_CONDITION, STRING_LIST)));

  private static final Set<String> STRING_VALUE_TREE_SET_WITH_COMPARATOR;

  private static final List<ComparableObject> COMPARABLE_OBJECT_LIST = Collections.unmodifiableList(Arrays.asList(
      new ComparableObject(23), new ComparableObject(20), new ComparableObject(110), new ComparableObject(999),
      new ComparableObject(8), new ComparableObject(20), null, new ComparableObject(0), new ComparableObject(-120),
      new ComparableObject(21), new ComparableObject(111), new ComparableObject(55), new ComparableObject(923),
      new ComparableObject(375), new ComparableObject(632), new ComparableObject(6099), null, new ComparableObject(
          73927), new ComparableObject(293), new ComparableObject(926), new ComparableObject(58219),
      new ComparableObject(41290), new ComparableObject(337), new ComparableObject(65), new ComparableObject(111),
      new ComparableObject(299), new ComparableObject(50), new ComparableObject(-3), new ComparableObject(346),
      new ComparableObject(23267), new ComparableObject(8)));

  private static final List<Object> OBJECT_LIST = Collections.<Object> unmodifiableList(COMPARABLE_OBJECT_LIST);

  private static final Set<Object> OBJECT_HASH_SET;
  private static final Set<Object> OBJECT_LINKED_HASH_SET;
  private static final Set<Object> OBJECT_TREE_SET_WITH_COMPARATOR;
  private static final Set<ComparableObject> COMPARABLE_OBJECT_TREE_SET;

  private static final Comparator<? super Integer> INTEGER_COMPARATOR = new Comparator<Integer>() {
    @Override
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

  static
  {
    final Set<Integer> integerSet = new TreeSet<Integer>(INTEGER_COMPARATOR);
    integerSet.addAll(INTEGER_LIST);
    INTEGER_VALUE_TREE_SET_WITH_COMPARATOR = Collections.unmodifiableSet(integerSet);

    final Set<String> stringSet = new TreeSet<String>(STRING_COMPARATOR);
    stringSet.addAll(STRING_LIST);
    STRING_VALUE_TREE_SET_WITH_COMPARATOR = Collections.unmodifiableSet(stringSet);

    /*
     * The input value for OBJECT_LINKED_HASH_SET should be OBJECT_LIST as OBJECT_LIST will be used as an input value
     * for testing newLinkedHashSet(with some input) method.
     */
    final List<Object> list = new ArrayList<Object>(OBJECT_LIST);
    Set<Object> set = new LinkedHashSet<Object>(list);
    OBJECT_LINKED_HASH_SET = Collections.unmodifiableSet(set);

    Collections.shuffle(list);
    set = new HashSet<Object>(list);
    OBJECT_HASH_SET = Collections.unmodifiableSet(set);

    Collections.shuffle(list);
    set = new TreeSet<Object>(OBJECT_FILTER.filter(NOT_NULL_CONDITION, list));
    Collections.unmodifiableSet(set);

    Collections.shuffle(list);
    set = new TreeSet<Object>(OBJECT_COMPARATOR);
    set.addAll(list);
    OBJECT_TREE_SET_WITH_COMPARATOR = Collections.unmodifiableSet(set);

    final List<ComparableObject> comparableObjectList = new ArrayList<ComparableObject>(COMPARABLE_OBJECT_LIST);
    Collections.shuffle(list);
    Set<ComparableObject> comparableObjectSet =
      new TreeSet<ComparableObject>(COMPARABLE_OBJECT_FILTER.filter(NOT_NULL_CONDITION, comparableObjectList));
    COMPARABLE_OBJECT_TREE_SET = Collections.unmodifiableSet(comparableObjectSet);

    // Collections.shuffle(comparableObjectList);
    // comparableObjectSet = new TreeSet<ComparableObject>(OBJECT_COMPARATOR);
    // set.addAll(comparableObjectList);
    // COMPARABLE_OBJECT_TREE_SET_WITH_COMPARATOR = Collections.unmodifiableSet(comparableObjectSet);
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
  public final void testSets() throws Exception
  {
    expectedException.expect(IllegalAccessException.class);
    testNotAccessibleConstructor(Sets.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
    fail(format("The expected exception [%s] is not thrown.", IllegalAccessError.class));
  }

  @SuppressWarnings("rawtypes")
  private static final Class<HashSet> EXPECTED_HASH_SET_TYPE = HashSet.class;

  @Test
  public final void testNewHashSet()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_HASH_SET_TYPE;

    @SuppressWarnings("rawtypes")
    final Set expected = new HashSet<Object>();

    /* when */
    final Set<?> set1 = newHashSet();

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected)));
    assertEquals(0, set1.size());

    /* given */
    final Set<Integer> expected2 = new HashSet<Integer>();

    /* when */
    final Set<Integer> set2 = newHashSet();

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(0, set2.size());

    /* given */
    @SuppressWarnings("rawtypes")
    final Set expected3 = new HashSet<Object>();

    /* when */
    final Set<?> set3 = newHashSet();

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(0, set3.size());
  }

  @Test
  public final void testNewHashSetCollectionOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_HASH_SET_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_HASH_SET;
    final List<Integer> input1 = INTEGER_LIST;

    /* when */
    final Set<Integer> set1 = newHashSet(input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());

    /* given */
    final Collection<String> expected2 = STRING_VALUE_HASH_SET;
    final List<String> input2 = STRING_LIST;

    /* when */
    final Set<String> set2 = newHashSet(input2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());

    /* given */
    final Collection<Object> expected3 = OBJECT_HASH_SET;
    final List<Object> input3 = OBJECT_LIST;

    /* when */
    final Set<Object> set3 = newHashSet(input3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
  }

  @Test
  public final void testNewHashSetIterableOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_HASH_SET_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_HASH_SET;
    final Iterable<Integer> input1 = INTEGER_LIST;

    /* when */
    final Set<Integer> set1 = newHashSet(input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());

    /* given */
    final Collection<String> expected2 = STRING_VALUE_HASH_SET;
    final Iterable<String> input2 = STRING_LIST;

    /* when */
    final Set<String> set2 = newHashSet(input2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());

    /* given */
    final Collection<Object> expected3 = OBJECT_HASH_SET;
    final Iterable<Object> input3 = OBJECT_LIST;

    /* when */
    final Set<Object> set3 = newHashSet(input3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
  }

  @Test
  public final void testNewHashSetIteratorOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_HASH_SET_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_HASH_SET;
    final Iterator<Integer> input1 = INTEGER_LIST.iterator();

    /* when */
    final Set<Integer> set1 = newHashSet(input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());

    /* given */
    final Collection<String> expected2 = STRING_VALUE_HASH_SET;
    final Iterator<String> input2 = STRING_LIST.iterator();

    /* when */
    final Set<String> set2 = newHashSet(input2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());

    /* given */
    final Collection<Object> expected3 = OBJECT_HASH_SET;
    final Iterator<Object> input3 = OBJECT_LIST.iterator();

    /* when */
    final Set<Object> set3 = newHashSet(input3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
  }

  @Test
  public final void testNewHashSetEArray()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_HASH_SET_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_HASH_SET;
    final Integer[] input1 = INTEGER_LIST.toArray(new Integer[expected1.size()]);

    /* when */
    final Set<Integer> set1 = newHashSet(input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());

    /* given */
    final Collection<String> expected2 = STRING_VALUE_HASH_SET;
    final String[] input2 = STRING_LIST.toArray(new String[expected2.size()]);

    /* when */
    final Set<String> set2 = newHashSet(input2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());

    /* given */
    final Collection<Object> expected3 = OBJECT_HASH_SET;
    final Object[] input3 = OBJECT_LIST.toArray();

    /* when */
    final Set<Object> set3 = newHashSet(input3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
  }

  @Test
  public final void testNewHashSetWithInitialCapacity()
  {
    // do nothing
  }

  @SuppressWarnings("rawtypes")
  private static final Class<LinkedHashSet> EXPECTED_LINKED_HASH_SET_TYPE = LinkedHashSet.class;

  @Test
  public final void testNewLinkedHashSet()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_LINKED_HASH_SET_TYPE;

    @SuppressWarnings("rawtypes")
    final Set expected = new LinkedHashSet<Object>();

    /* when */
    final Set<?> set1 = newLinkedHashSet();

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected)));
    assertEquals(0, set1.size());

    /* given */
    final Set<Integer> expected2 = new LinkedHashSet<Integer>();

    /* when */
    final Set<Integer> set2 = newLinkedHashSet();

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(0, set2.size());

    /* given */
    @SuppressWarnings("rawtypes")
    final Set expected3 = new LinkedHashSet<Object>();

    /* when */
    final Set<?> set3 = newLinkedHashSet();

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(0, set3.size());
  }

  @Test
  public final void testNewLinkedHashSetCollectionOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_LINKED_HASH_SET_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_LINKED_HASH_SET;
    final List<Integer> input1 = INTEGER_LIST;

    /* when */
    final Set<Integer> set1 = newLinkedHashSet(input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());
    assertThat(set1.toArray(), is(equalTo(expected1.toArray())));

    /* given */
    final Collection<String> expected2 = STRING_VALUE_LINKED_HASH_SET;
    final List<String> input2 = STRING_LIST;

    /* when */
    final Set<String> set2 = newLinkedHashSet(input2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());
    assertThat(set2.toArray(), is(equalTo(expected2.toArray())));

    /* given */
    final Collection<Object> expected3 = OBJECT_LINKED_HASH_SET;
    final List<Object> input3 = OBJECT_LIST;

    /* when */
    final Set<Object> set3 = newLinkedHashSet(input3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
    assertThat(set3.toArray(), is(equalTo(expected3.toArray())));
  }

  @Test
  public final void testNewLinkedHashSetIterableOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_LINKED_HASH_SET_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_LINKED_HASH_SET;
    final Iterable<Integer> input1 = INTEGER_LIST;

    /* when */
    final Set<Integer> set1 = newLinkedHashSet(input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());
    assertThat(set1.toArray(), is(equalTo(expected1.toArray())));

    /* given */
    final Collection<String> expected2 = STRING_VALUE_LINKED_HASH_SET;
    final Iterable<String> input2 = STRING_LIST;

    /* when */
    final Set<String> set2 = newLinkedHashSet(input2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());
    assertThat(set2.toArray(), is(equalTo(expected2.toArray())));

    /* given */
    final Collection<Object> expected3 = OBJECT_LINKED_HASH_SET;
    final Iterable<Object> input3 = OBJECT_LIST;

    /* when */
    final Set<Object> set3 = newLinkedHashSet(input3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
    assertThat(set3.toArray(), is(equalTo(expected3.toArray())));
  }

  @Test
  public final void testNewLinkedHashSetIteratorOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_LINKED_HASH_SET_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_LINKED_HASH_SET;
    final Iterator<Integer> input1 = INTEGER_LIST.iterator();

    /* when */
    final Set<Integer> set1 = newLinkedHashSet(input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());
    assertThat(set1.toArray(), is(equalTo(expected1.toArray())));

    /* given */
    final Collection<String> expected2 = STRING_VALUE_LINKED_HASH_SET;
    final Iterator<String> input2 = STRING_LIST.iterator();

    /* when */
    final Set<String> set2 = newLinkedHashSet(input2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());
    assertThat(set2.toArray(), is(equalTo(expected2.toArray())));

    /* given */
    final Collection<Object> expected3 = OBJECT_LINKED_HASH_SET;
    final Iterator<Object> input3 = OBJECT_LIST.iterator();

    /* when */
    final Set<Object> set3 = newLinkedHashSet(input3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
    assertThat(set3.toArray(), is(equalTo(expected3.toArray())));
  }

  @Test
  public final void testNewLinkedHashSetEArray()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_LINKED_HASH_SET_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_LINKED_HASH_SET;
    final Integer[] input1 = INTEGER_LIST.toArray(new Integer[expected1.size()]);

    /* when */
    final Set<Integer> set1 = newLinkedHashSet(input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());
    assertThat(set1.toArray(), is(equalTo(expected1.toArray())));

    /* given */
    final Collection<String> expected2 = STRING_VALUE_LINKED_HASH_SET;
    final String[] input2 = STRING_LIST.toArray(new String[expected2.size()]);

    /* when */
    final Set<String> set2 = newLinkedHashSet(input2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());
    assertThat(set2.toArray(), is(equalTo(expected2.toArray())));

    /* given */
    final Collection<Object> expected3 = OBJECT_LINKED_HASH_SET;
    final Object[] input3 = OBJECT_LIST.toArray();

    /* when */
    final Set<Object> set3 = newLinkedHashSet(input3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
    assertThat(set3.toArray(), is(equalTo(expected3.toArray())));
  }

  @Test
  public final void testNewLinkedHashSetWithInitialCapacity()
  {
    // do nothing
  }

  @SuppressWarnings("rawtypes")
  private static final Class<TreeSet> EXPECTED_TREE_SET_TYPE = TreeSet.class;

  @Test
  public final void testNewTreeSet()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_TREE_SET_TYPE;

    @SuppressWarnings("rawtypes")
    final Set expected = new TreeSet<Object>();

    /* when */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    final Set<? extends Comparable> set1 = newTreeSet();

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected)));
    assertEquals(0, set1.size());

    /* given */
    final Set<Integer> expected2 = new TreeSet<Integer>();

    /* when */
    final TreeSet<Integer> set2 = newTreeSet();

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(0, set2.size());

    /* given */
    final Set<String> expected3 = new TreeSet<String>();

    /* when */
    final TreeSet<String> set3 = newTreeSet();

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(0, set3.size());

    /* given */
    @SuppressWarnings("rawtypes")
    final Set expected4 = new TreeSet<Object>();

    /* when */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    final TreeSet<? extends Comparable> set4 = newTreeSet();

    /* then */
    assertThat(set4, is(instanceOf(expectedType)));
    assertThat(set4.getClass(), is(equalTo(expectedType)));
    assertThat(set4, is(equalTo(expected4)));
    assertEquals(0, set4.size());
  }

  @Test
  public final void testNewTreeSetCollectionOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_TREE_SET_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_TREE_SET;
    final List<Integer> input1 = INTEGER_LIST;

    /* when */
    final TreeSet<Integer> set1 = newTreeSet(INTEGER_FILTER.filter(NOT_NULL_CONDITION, input1));

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());
    assertThat(set1.toArray(), is(equalTo(expected1.toArray())));

    /* given */
    final Collection<String> expected2 = STRING_VALUE_TREE_SET;
    final List<String> input2 = STRING_LIST;

    /* when */
    final TreeSet<String> set2 = newTreeSet(STRING_FILTER.filter(NOT_NULL_CONDITION, input2));

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());
    assertThat(set2.toArray(), is(equalTo(expected2.toArray())));

    /* given */
    final Collection<ComparableObject> expected3 = COMPARABLE_OBJECT_TREE_SET;
    final List<ComparableObject> input3 = COMPARABLE_OBJECT_LIST;

    /* when */
    final TreeSet<ComparableObject> set3 = newTreeSet(COMPARABLE_OBJECT_FILTER.filter(NOT_NULL_CONDITION, input3));

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
    assertThat(set3.toArray(), is(equalTo(expected3.toArray())));
  }

  @Test
  public final void testNewTreeSetIterableOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_TREE_SET_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_TREE_SET;
    final Iterable<Integer> input1 = INTEGER_FILTER.filter(NOT_NULL_CONDITION, INTEGER_LIST);

    /* when */
    final TreeSet<Integer> set1 = newTreeSet(input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());
    assertThat(set1.toArray(), is(equalTo(expected1.toArray())));

    /* given */
    final Collection<String> expected2 = STRING_VALUE_TREE_SET;
    final Iterable<String> input2 = STRING_LIST;

    /* when */
    final TreeSet<String> set2 = newTreeSet(STRING_FILTER.filter(NOT_NULL_CONDITION, input2));

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());
    assertThat(set2.toArray(), is(equalTo(expected2.toArray())));

    /* given */
    final Collection<ComparableObject> expected3 = COMPARABLE_OBJECT_TREE_SET;
    final Iterable<ComparableObject> input3 = COMPARABLE_OBJECT_LIST;

    /* when */
    final TreeSet<ComparableObject> set3 = newTreeSet(COMPARABLE_OBJECT_FILTER.filter(NOT_NULL_CONDITION, input3));

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
    assertThat(set3.toArray(), is(equalTo(expected3.toArray())));
  }

  @Test
  public final void testNewTreeSetIteratorOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_TREE_SET_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_TREE_SET;
    final Iterator<Integer> input1 = INTEGER_FILTER.filter(NOT_NULL_CONDITION, INTEGER_LIST)
        .iterator();

    /* when */
    final TreeSet<Integer> set1 = newTreeSet(input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());
    assertThat(set1.toArray(), is(equalTo(expected1.toArray())));

    /* given */
    final Collection<String> expected2 = STRING_VALUE_TREE_SET;
    final Iterator<String> input2 = STRING_FILTER.filter(NOT_NULL_CONDITION, STRING_LIST)
        .iterator();

    /* when */
    final TreeSet<String> set2 = newTreeSet(input2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());
    assertThat(set2.toArray(), is(equalTo(expected2.toArray())));

    /* given */
    final Collection<ComparableObject> expected3 = COMPARABLE_OBJECT_TREE_SET;
    final Iterator<ComparableObject> input3 =
      COMPARABLE_OBJECT_FILTER.filter(NOT_NULL_CONDITION, COMPARABLE_OBJECT_LIST)
          .iterator();

    /* when */
    final TreeSet<ComparableObject> set3 = newTreeSet(input3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
    assertThat(set3.toArray(), is(equalTo(expected3.toArray())));
  }

  @Test
  public final void testNewTreeSetEArray()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_TREE_SET_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_TREE_SET;
    final Integer[] input1 = expected1.toArray(new Integer[expected1.size()]);

    /* when */
    final TreeSet<Integer> set1 = newTreeSet(input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());
    assertThat(set1.toArray(), is(equalTo(expected1.toArray())));

    /* given */
    final Collection<String> expected2 = STRING_VALUE_TREE_SET;
    final List<String> filteredList1 = STRING_FILTER.filter(NOT_NULL_CONDITION, STRING_LIST);
    final String[] input2 = filteredList1.toArray(new String[filteredList1.size()]);

    /* when */
    final TreeSet<String> set2 = newTreeSet(input2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());
    assertThat(set2.toArray(), is(equalTo(expected2.toArray())));

    /* given */
    final Collection<ComparableObject> expected3 = COMPARABLE_OBJECT_TREE_SET;
    final List<ComparableObject> filteredList2 =
      COMPARABLE_OBJECT_FILTER.filter(NOT_NULL_CONDITION, COMPARABLE_OBJECT_LIST);
    final ComparableObject[] input3 = filteredList2.toArray(new ComparableObject[filteredList2.size()]);

    /* when */
    final TreeSet<ComparableObject> set3 = newTreeSet(input3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
    assertThat(set3.toArray(), is(equalTo(expected3.toArray())));
  }

  @Test
  public final void testNewTreeSetComparatorOfQsuperE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_TREE_SET_TYPE;
    final Comparator<? super Integer> comparator1 = INTEGER_COMPARATOR;
    @SuppressWarnings({ "rawtypes", "cast" })
    final Comparator expectedComparator1 = (Comparator) comparator1;

    /* when */
    final TreeSet<Integer> set1 = Sets.newTreeSet(comparator1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1.comparator(), is(equalTo(expectedComparator1)));
    assertEquals(0, set1.size());
    assertThat(set1.toArray(), is(equalTo(new Object[] {})));

    /* given */
    final Comparator<? super String> comparator2 = STRING_COMPARATOR;
    @SuppressWarnings({ "rawtypes", "cast" })
    final Comparator expectedComparator2 = (Comparator) comparator2;

    /* when */
    final TreeSet<String> set2 = Sets.newTreeSet(comparator2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2.comparator(), is(equalTo(expectedComparator2)));
    assertEquals(0, set2.size());
    assertThat(set2.toArray(), is(equalTo(new Object[] {})));

    /* given */
    final Comparator<? super Object> comparator3 = OBJECT_COMPARATOR;
    @SuppressWarnings({ "rawtypes", "cast" })
    final Comparator expectedComparator3 = (Comparator) comparator3;

    /* when */
    final TreeSet<Object> set3 = Sets.newTreeSet(comparator3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3.comparator(), is(equalTo(expectedComparator3)));
    assertEquals(0, set3.size());
    assertThat(set3.toArray(), is(equalTo(new Object[] {})));
  }

  @Test
  public final void testNewTreeSetComparatorOfQsuperECollectionOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_TREE_SET_TYPE;

    final Comparator<? super Integer> comparator1 = INTEGER_COMPARATOR;
    @SuppressWarnings({ "cast", "rawtypes" })
    final Comparator expectedComparator1 = (Comparator) comparator1;

    final Collection<Integer> expected1 = INTEGER_VALUE_TREE_SET_WITH_COMPARATOR;
    final List<Integer> input1 = INTEGER_LIST;

    /* when */
    final TreeSet<Integer> set1 = newTreeSet(comparator1, input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1.comparator(), is(equalTo(expectedComparator1)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());
    assertThat(set1.toArray(), is(equalTo(expected1.toArray())));

    /* given */
    final Comparator<? super String> comparator2 = STRING_COMPARATOR;
    @SuppressWarnings({ "rawtypes", "cast" })
    final Comparator expectedComparator2 = (Comparator) comparator2;

    final Collection<String> expected2 = STRING_VALUE_TREE_SET_WITH_COMPARATOR;
    final List<String> input2 = STRING_LIST;

    /* when */
    final TreeSet<String> set2 = newTreeSet(comparator2, input2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2.comparator(), is(equalTo(expectedComparator2)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());
    assertThat(set2.toArray(), is(equalTo(expected2.toArray())));

    /* given */
    final Comparator<? super Object> comparator3 = OBJECT_COMPARATOR;
    @SuppressWarnings({ "rawtypes", "cast" })
    final Comparator expectedComparator3 = (Comparator) comparator3;

    final Collection<Object> expected3 = OBJECT_TREE_SET_WITH_COMPARATOR;
    final List<Object> input3 = OBJECT_LIST;

    /* when */
    final TreeSet<Object> set3 = newTreeSet(comparator3, input3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3.comparator(), is(equalTo(expectedComparator3)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
    assertThat(set3.toArray(), is(equalTo(expected3.toArray())));
  }

  @Test
  public final void testNewTreeSetComparatorOfQsuperEIterableOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_TREE_SET_TYPE;

    final Comparator<? super Integer> comparator1 = INTEGER_COMPARATOR;
    @SuppressWarnings({ "cast", "rawtypes" })
    final Comparator expectedComparator1 = (Comparator) comparator1;

    final Collection<Integer> expected1 = INTEGER_VALUE_TREE_SET_WITH_COMPARATOR;
    final Iterable<Integer> input1 = INTEGER_LIST;

    /* when */
    final TreeSet<Integer> set1 = newTreeSet(comparator1, input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1.comparator(), is(equalTo(expectedComparator1)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());
    assertThat(set1.toArray(), is(equalTo(expected1.toArray())));

    /* given */
    final Comparator<? super String> comparator2 = STRING_COMPARATOR;
    @SuppressWarnings({ "rawtypes", "cast" })
    final Comparator expectedComparator2 = (Comparator) comparator2;

    final Collection<String> expected2 = STRING_VALUE_TREE_SET_WITH_COMPARATOR;
    final Iterable<String> input2 = STRING_LIST;

    /* when */
    final TreeSet<String> set2 = newTreeSet(comparator2, input2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2.comparator(), is(equalTo(expectedComparator2)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());
    assertThat(set2.toArray(), is(equalTo(expected2.toArray())));

    /* given */
    final Comparator<? super Object> comparator3 = OBJECT_COMPARATOR;
    @SuppressWarnings({ "rawtypes", "cast" })
    final Comparator expectedComparator3 = (Comparator) comparator3;

    final Collection<Object> expected3 = OBJECT_TREE_SET_WITH_COMPARATOR;
    final Iterable<Object> input3 = OBJECT_LIST;

    /* when */
    final TreeSet<Object> set3 = newTreeSet(comparator3, input3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3.comparator(), is(equalTo(expectedComparator3)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
    assertThat(set3.toArray(), is(equalTo(expected3.toArray())));
  }

  @Test
  public final void testNewTreeSetComparatorOfQsuperEIteratorOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_TREE_SET_TYPE;

    final Comparator<? super Integer> comparator1 = INTEGER_COMPARATOR;
    @SuppressWarnings({ "cast", "rawtypes" })
    final Comparator expectedComparator1 = (Comparator) comparator1;

    final Collection<Integer> expected1 = INTEGER_VALUE_TREE_SET_WITH_COMPARATOR;
    final Iterator<Integer> input1 = INTEGER_LIST.iterator();

    /* when */
    final TreeSet<Integer> set1 = newTreeSet(comparator1, input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1.comparator(), is(equalTo(expectedComparator1)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());
    assertThat(set1.toArray(), is(equalTo(expected1.toArray())));

    /* given */
    final Comparator<? super String> comparator2 = STRING_COMPARATOR;
    @SuppressWarnings({ "rawtypes", "cast" })
    final Comparator expectedComparator2 = (Comparator) comparator2;

    final Collection<String> expected2 = STRING_VALUE_TREE_SET_WITH_COMPARATOR;
    final Iterator<String> input2 = STRING_LIST.iterator();

    /* when */
    final TreeSet<String> set2 = newTreeSet(comparator2, input2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2.comparator(), is(equalTo(expectedComparator2)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());
    assertThat(set2.toArray(), is(equalTo(expected2.toArray())));

    /* given */
    final Comparator<? super Object> comparator3 = OBJECT_COMPARATOR;
    @SuppressWarnings({ "rawtypes", "cast" })
    final Comparator expectedComparator3 = (Comparator) comparator3;

    final Collection<Object> expected3 = OBJECT_TREE_SET_WITH_COMPARATOR;
    final Iterator<Object> input3 = OBJECT_LIST.iterator();

    /* when */
    final TreeSet<Object> set3 = newTreeSet(comparator3, input3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3.comparator(), is(equalTo(expectedComparator3)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
    assertThat(set3.toArray(), is(equalTo(expected3.toArray())));
  }

  @Test
  public final void testNewTreeSetComparatorOfQsuperEEArray()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expectedType = EXPECTED_TREE_SET_TYPE;

    final Comparator<? super Integer> comparator1 = INTEGER_COMPARATOR;
    @SuppressWarnings({ "cast", "rawtypes" })
    final Comparator expectedComparator1 = (Comparator) comparator1;

    final Collection<Integer> expected1 = INTEGER_VALUE_TREE_SET_WITH_COMPARATOR;
    final Integer[] input1 = INTEGER_LIST.toArray(new Integer[expected1.size()]);

    /* when */
    final TreeSet<Integer> set1 = newTreeSet(comparator1, input1);

    /* then */
    assertThat(set1, is(instanceOf(expectedType)));
    assertThat(set1.getClass(), is(equalTo(expectedType)));
    assertThat(set1.comparator(), is(equalTo(expectedComparator1)));
    assertThat(set1, is(equalTo(expected1)));
    assertEquals(expected1.size(), set1.size());
    assertThat(set1.toArray(), is(equalTo(expected1.toArray())));

    /* given */
    final Comparator<? super String> comparator2 = STRING_COMPARATOR;
    @SuppressWarnings({ "rawtypes", "cast" })
    final Comparator expectedComparator2 = (Comparator) comparator2;

    final Collection<String> expected2 = STRING_VALUE_TREE_SET_WITH_COMPARATOR;
    final String[] input2 = STRING_LIST.toArray(new String[STRING_LIST.size()]);

    /* when */
    final TreeSet<String> set2 = newTreeSet(comparator2, input2);

    /* then */
    assertThat(set2, is(instanceOf(expectedType)));
    assertThat(set2.getClass(), is(equalTo(expectedType)));
    assertThat(set2.comparator(), is(equalTo(expectedComparator2)));
    assertThat(set2, is(equalTo(expected2)));
    assertEquals(expected2.size(), set2.size());
    assertThat(set2.toArray(), is(equalTo(expected2.toArray())));

    /* given */
    final Comparator<? super Object> comparator3 = OBJECT_COMPARATOR;
    @SuppressWarnings({ "rawtypes", "cast" })
    final Comparator expectedComparator3 = (Comparator) comparator3;

    final Collection<Object> expected3 = OBJECT_TREE_SET_WITH_COMPARATOR;
    final Object[] input3 = OBJECT_LIST.toArray(new ComparableObject[OBJECT_LIST.size()]);

    /* when */
    final TreeSet<Object> set3 = newTreeSet(comparator3, input3);

    /* then */
    assertThat(set3, is(instanceOf(expectedType)));
    assertThat(set3.getClass(), is(equalTo(expectedType)));
    assertThat(set3.comparator(), is(equalTo(expectedComparator3)));
    assertThat(set3, is(equalTo(expected3)));
    assertEquals(expected3.size(), set3.size());
    assertThat(set3.toArray(), is(equalTo(expected3.toArray())));
  }
}
