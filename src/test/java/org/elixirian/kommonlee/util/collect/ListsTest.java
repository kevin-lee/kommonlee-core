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
package org.elixirian.kommonlee.util.collect;

import static org.elixirian.kommonlee.test.CommonTestHelper.*;
import static org.elixirian.kommonlee.util.MessageFormatter.*;
import static org.elixirian.kommonlee.util.collect.Lists.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.elixirian.kommonlee.test.CauseCheckableExpectedException;
import org.elixirian.kommonlee.test.CommonTestHelper.Accessibility;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

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
 * @version 0.0.1 (2011-02-17)
 */
public class ListsTest
{
  @SuppressWarnings("boxing")
  private static final Integer[] INTEGERS = { -955, -34, -120, 0, 23, 50, -3, 20, 110, 999, 20, 0, -120, 346, 23267,
      21, 111, 55, 923, 375, 632, 6099, 41290, 337, 65, 325, 299, 73927, 293, 926, 58219, 86271, 337 };

  private static final List<Integer> INTEGER_VALUE_LIST = Collections.unmodifiableList(Arrays.asList(INTEGERS));

  private static final String[] STRINGS = { "Hello, ", "Kevin", "Lee", "", "blah", "Test String", "blah", "blah",
      "Hello", "Kevin", null, "blah blah", "Hello", ", ", "World", "blah", ", ", "blah", "", " ", "Kevin", " ", "Lee",
      " ", "0123456789", "Kevin", null, "Lee", " ", "blah", null, "blah blah", "Kevin", "Testing", "Collection" };

  private static final List<String> STRING_VALUE_LIST = Collections.unmodifiableList(Arrays.asList(STRINGS));

  private static final List<Object> OBJECT_LIST;

  static
  {
    final List<Object> list = new ArrayList<Object>(INTEGER_VALUE_LIST);
    list.addAll(STRING_VALUE_LIST);
    OBJECT_LIST = Collections.unmodifiableList(list);
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
  public final void testLists() throws Exception
  {
    expectedException.expect(IllegalAccessException.class);
    testNotAccessibleConstructor(Lists.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
    fail(format("The expected exception [%s] is not thrown.", IllegalAccessError.class));
  }

  @Test
  public void testImmutableEmptyListIfNullForNull()
  {
    /* given */
    final List<String> expected = Collections.emptyList();

    final List<String> list = null;

    /* when */
    final List<String> actual = Lists.immutableEmptyListIfNull(list);

    /* then */
    assertThat(actual, is(notNullValue()));
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void testImmutableEmptyListIfNullForNonNull()
  {
    /* given */
    final List<String> expected = new ArrayList<String>();
    expected.add("Kevin");
    expected.add("Lee");
    expected.add("Test");

    final List<String> list = expected;

    /* when */
    final List<String> actual = Lists.immutableEmptyListIfNull(list);

    /* then */
    assertThat(actual, is(notNullValue()));
    assertThat(actual, is(equalTo(expected)));
  }

  @SuppressWarnings("rawtypes")
  private static final Class<ArrayList> EXPECTED_ARRAY_LIST_TYPE = ArrayList.class;

  @Test
  public final void testNewArrayList()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class<ArrayList> expectedType = EXPECTED_ARRAY_LIST_TYPE;

    @SuppressWarnings("rawtypes")
    final List expected = new ArrayList<Object>();

    /* when */
    final List<?> list = newArrayList();

    /* then */
    assertThat(list, is(instanceOf(expectedType)));
    assertThat(list, is(equalTo(expected)));
    assertEquals(0, list.size());

    /* given */
    final List<Integer> expected2 = new ArrayList<Integer>();

    /* when */
    final List<Integer> list2 = newArrayList();

    /* then */
    assertThat(list2, is(instanceOf(expectedType)));
    assertThat(list2, is(equalTo(expected2)));
    assertEquals(0, list2.size());

    /* given */
    @SuppressWarnings("rawtypes")
    final List expected3 = new ArrayList<Object>();

    /* when */
    final List<?> list3 = newArrayList();

    /* then */
    assertThat(list3, is(instanceOf(expectedType)));
    assertThat(list3, is(equalTo(expected3)));
    assertEquals(0, list3.size());
  }

  @Test
  public final void testNewArrayListCollectionOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class<ArrayList> expectedType = EXPECTED_ARRAY_LIST_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_LIST;

    /* when */
    final List<Integer> list1 = newArrayList(expected1);

    /* then */
    assertThat(list1, is(instanceOf(expectedType)));
    assertThat(list1, is(equalTo(expected1)));
    assertEquals(expected1.size(), list1.size());

    /* given */
    final Collection<String> expected2 = STRING_VALUE_LIST;

    /* when */
    final List<String> list2 = newArrayList(expected2);

    /* then */
    assertThat(list2, is(instanceOf(expectedType)));
    assertThat(list2, is(equalTo(expected2)));
    assertEquals(expected2.size(), list2.size());

    /* given */
    final Collection<Object> expected3 = OBJECT_LIST;

    /* when */
    final List<Object> list3 = newArrayList(expected3);

    /* then */
    assertThat(list3, is(instanceOf(expectedType)));
    assertThat(list3, is(equalTo(expected3)));
    assertEquals(expected3.size(), list3.size());
  }

  @Test
  public final void testNewArrayListIterableOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class<ArrayList> expectedType = EXPECTED_ARRAY_LIST_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_LIST;
    final Iterable<Integer> input1 = expected1;

    /* when */
    final List<Integer> list1 = newArrayList(input1);

    /* then */
    assertThat(list1, is(instanceOf(expectedType)));
    assertThat(list1, is(equalTo(expected1)));
    assertEquals(expected1.size(), list1.size());

    /* given */
    final Collection<String> expected2 = STRING_VALUE_LIST;
    final Iterable<String> input2 = expected2;

    /* when */
    final List<String> list2 = newArrayList(input2);

    /* then */
    assertThat(list2, is(instanceOf(expectedType)));
    assertThat(list2, is(equalTo(expected2)));
    assertEquals(expected2.size(), list2.size());

    /* given */
    final Collection<Object> expected3 = OBJECT_LIST;
    final Iterable<Object> input3 = expected3;

    /* when */
    final List<Object> list3 = newArrayList(input3);

    /* then */
    assertThat(list3, is(instanceOf(expectedType)));
    assertThat(list3, is(equalTo(expected3)));
    assertEquals(expected3.size(), list3.size());
  }

  @Test
  public final void testNewArrayListIteratorOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class<ArrayList> expectedType = EXPECTED_ARRAY_LIST_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_LIST;
    final Iterator<Integer> input1 = expected1.iterator();

    /* when */
    final List<Integer> list1 = newArrayList(input1);

    /* then */
    assertThat(list1, is(instanceOf(expectedType)));
    assertThat(list1, is(equalTo(expected1)));
    assertEquals(expected1.size(), list1.size());

    /* given */
    final Collection<String> expected2 = STRING_VALUE_LIST;
    final Iterator<String> input2 = expected2.iterator();

    /* when */
    final List<String> list2 = newArrayList(input2);

    /* then */
    assertThat(list2, is(instanceOf(expectedType)));
    assertThat(list2, is(equalTo(expected2)));
    assertEquals(expected2.size(), list2.size());

    /* given */
    final Collection<Object> expected3 = OBJECT_LIST;
    final Iterator<Object> input3 = expected3.iterator();

    /* when */
    final List<Object> list3 = newArrayList(input3);

    /* then */
    assertThat(list3, is(instanceOf(expectedType)));
    assertThat(list3, is(equalTo(expected3)));
    assertEquals(expected3.size(), list3.size());
  }

  @Test
  public final void testNewArrayListEArray()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class<ArrayList> expectedType = EXPECTED_ARRAY_LIST_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_LIST;
    final Integer[] input1 = expected1.toArray(new Integer[expected1.size()]);

    /* when */
    final List<Integer> list1 = newArrayList(input1);

    /* then */
    assertThat(list1, is(instanceOf(expectedType)));
    assertThat(list1, is(equalTo(expected1)));
    assertEquals(expected1.size(), list1.size());

    /* given */
    final Collection<String> expected2 = STRING_VALUE_LIST;
    final String[] input2 = expected2.toArray(new String[expected2.size()]);

    /* when */
    final List<String> list2 = newArrayList(input2);

    /* then */
    assertThat(list2, is(instanceOf(expectedType)));
    assertThat(list2, is(equalTo(expected2)));
    assertEquals(expected2.size(), list2.size());

    /* given */
    final Collection<Object> expected3 = OBJECT_LIST;
    final Object[] input3 = expected3.toArray(new Object[expected3.size()]);

    /* when */
    final List<Object> list3 = newArrayList(input3);

    /* then */
    assertThat(list3, is(instanceOf(expectedType)));
    assertThat(list3, is(equalTo(expected3)));
    assertEquals(expected3.size(), list3.size());
  }

  // private static final Random random = new Random(1234);

  @Test
  public final void testNewArrayListWithInitialCapacity()
  {
    // System.out.println("\nListsTest.testNewArrayListWithInitialCapacity() starts");
    // synchronized (this)
    // {
    // /* given */
    // final int seed = 10000;
    // final int size = 4;
    // final Runtime runtime = Runtime.getRuntime();
    // final long totalMemory = runtime.totalMemory();
    //
    // int tempHowMany = 1000000000;
    // while (totalMemory <= (tempHowMany * 3 + tempHowMany * 2 + tempHowMany + 1000))
    // {
    // tempHowMany = seed * (random.nextInt(100) + 1);
    // }
    // final int howMany = tempHowMany;
    // final int expected = howMany * size;
    // final int expected2 = expected * 2;
    // final long difference1;
    // final long difference2;
    // final long difference3;
    //
    // System.out.println("total: " + totalMemory);
    // System.out.println("howMany: " + howMany);
    // System.out.println();
    // long beforeMemory = 0;
    // long afterMemory = 0;
    // final long result1;
    // final long result2;
    // final long result3;
    //
    // /* when */
    // beforeMemory = runtime.freeMemory();
    // newArrayListWithInitialCapacity(howMany);
    // afterMemory = runtime.freeMemory();
    // result1 = beforeMemory - afterMemory;
    // System.out.println("before: " + beforeMemory);
    // System.out.println("after: " + afterMemory);
    // System.out.println("result1: " + result1);
    // System.out.println();
    //
    // beforeMemory = runtime.freeMemory();
    // newArrayListWithInitialCapacity(howMany + howMany);
    // afterMemory = runtime.freeMemory();
    // result2 = beforeMemory - afterMemory;
    // System.out.println("before: " + beforeMemory);
    // System.out.println("after: " + afterMemory);
    // System.out.println("result2: " + result2);
    //
    /* then */
    // System.out.println("------------------------------");
    // System.out.println("expected: " + expected);
    // difference1 = result2 - result1;
    // System.out.println("result2 - result1: " + difference1);
    // assertEquals(expected, difference1);
    // System.out.println();
    //
    // /* when */
    // beforeMemory = runtime.freeMemory();
    // newArrayListWithInitialCapacity(howMany * 3);
    // afterMemory = runtime.freeMemory();
    // result3 = beforeMemory - afterMemory;
    // System.out.println("before: " + beforeMemory);
    // System.out.println("after: " + afterMemory);
    // System.out.println("result3: " + result3);
    //
    // /* then */
    // difference2 = result3 - result2;
    // difference3 = result3 - result1;
    // System.out.println("------------------------------");
    // System.out.println("expected: " + expected);
    // System.out.println("result3 - result2: " + difference2);
    // System.out.println("------------------------------");
    //
    // System.out.println("expected: " + expected2);
    // System.out.println("result3 - result1: " + difference3);
    // System.out.println();
    // assertEquals(expected, difference2);
    // assertEquals(expected2, difference3);
    // }
    // System.out.println("ListsTest.testNewArrayListWithInitialCapacity() ends\n");
  }

  @SuppressWarnings("rawtypes")
  private static final Class<LinkedList> EXPECTED_LINKED_LIST_TYPE = LinkedList.class;

  @Test
  public final void testNewLinkedList()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class<LinkedList> expectedType = EXPECTED_LINKED_LIST_TYPE;

    @SuppressWarnings("rawtypes")
    final List expected = new LinkedList<Object>();

    /* when */
    final List<?> list = newLinkedList();

    /* then */
    assertThat(list, is(instanceOf(expectedType)));
    assertThat(list, is(equalTo(expected)));
    assertEquals(0, list.size());

    /* given */
    final List<Integer> expected2 = new LinkedList<Integer>();

    /* when */
    final List<Integer> list2 = newLinkedList();

    /* then */
    assertThat(list2, is(instanceOf(expectedType)));
    assertThat(list2, is(equalTo(expected2)));
    assertEquals(0, list2.size());

    /* given */
    @SuppressWarnings("rawtypes")
    final List expected3 = new LinkedList<Object>();

    /* when */
    final List<?> list3 = newLinkedList();

    /* then */
    assertThat(list3, is(instanceOf(expectedType)));
    assertThat(list3, is(equalTo(expected3)));
    assertEquals(0, list3.size());
  }

  @Test
  public final void testNewLinkedListCollectionOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class<LinkedList> expectedType = EXPECTED_LINKED_LIST_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_LIST;

    /* when */
    final List<Integer> list1 = newLinkedList(expected1);

    /* then */
    assertThat(list1, is(instanceOf(expectedType)));
    assertThat(list1, is(equalTo(expected1)));
    assertEquals(expected1.size(), list1.size());

    /* given */
    final Collection<String> expected2 = STRING_VALUE_LIST;

    /* when */
    final List<String> list2 = newLinkedList(expected2);

    /* then */
    assertThat(list2, is(instanceOf(expectedType)));
    assertThat(list2, is(equalTo(expected2)));
    assertEquals(expected2.size(), list2.size());

    /* given */
    final Collection<Object> expected3 = OBJECT_LIST;

    /* when */
    final List<Object> list3 = newLinkedList(expected3);

    /* then */
    assertThat(list3, is(instanceOf(expectedType)));
    assertThat(list3, is(equalTo(expected3)));
    assertEquals(expected3.size(), list3.size());
  }

  @Test
  public final void testNewLinkedListIterableOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class<LinkedList> expectedType = EXPECTED_LINKED_LIST_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_LIST;
    final Iterable<Integer> input1 = expected1;

    /* when */
    final List<Integer> list1 = newLinkedList(input1);

    /* then */
    assertThat(list1, is(instanceOf(expectedType)));
    assertThat(list1, is(equalTo(expected1)));
    assertEquals(expected1.size(), list1.size());

    /* given */
    final Collection<String> expected2 = STRING_VALUE_LIST;
    final Iterable<String> input2 = expected2;

    /* when */
    final List<String> list2 = newLinkedList(input2);

    /* then */
    assertThat(list2, is(instanceOf(expectedType)));
    assertThat(list2, is(equalTo(expected2)));
    assertEquals(expected2.size(), list2.size());

    /* given */
    final Collection<Object> expected3 = OBJECT_LIST;
    final Iterable<Object> input3 = expected3;

    /* when */
    final List<Object> list3 = newLinkedList(input3);

    /* then */
    assertThat(list3, is(instanceOf(expectedType)));
    assertThat(list3, is(equalTo(expected3)));
    assertEquals(expected3.size(), list3.size());
  }

  @Test
  public final void testNewLinkedListIteratorOfQextendsE()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class<LinkedList> expectedType = EXPECTED_LINKED_LIST_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_LIST;
    final Iterator<Integer> input1 = expected1.iterator();

    /* when */
    final List<Integer> list1 = newLinkedList(input1);

    /* then */
    assertThat(list1, is(instanceOf(expectedType)));
    assertThat(list1, is(equalTo(expected1)));
    assertEquals(expected1.size(), list1.size());

    /* given */
    final Collection<String> expected2 = STRING_VALUE_LIST;
    final Iterator<String> input2 = expected2.iterator();

    /* when */
    final List<String> list2 = newLinkedList(input2);

    /* then */
    assertThat(list2, is(instanceOf(expectedType)));
    assertThat(list2, is(equalTo(expected2)));
    assertEquals(expected2.size(), list2.size());

    /* given */
    final Collection<Object> expected3 = OBJECT_LIST;
    final Iterator<Object> input3 = expected3.iterator();

    /* when */
    final List<Object> list3 = newLinkedList(input3);

    /* then */
    assertThat(list3, is(instanceOf(expectedType)));
    assertThat(list3, is(equalTo(expected3)));
    assertEquals(expected3.size(), list3.size());
  }

  @Test
  public final void testNewLinkedListEArray()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class<LinkedList> expectedType = EXPECTED_LINKED_LIST_TYPE;

    final Collection<Integer> expected1 = INTEGER_VALUE_LIST;
    final Integer[] input1 = expected1.toArray(new Integer[expected1.size()]);

    /* when */
    final List<Integer> list1 = newLinkedList(input1);

    /* then */
    assertThat(list1, is(instanceOf(expectedType)));
    assertThat(list1, is(equalTo(expected1)));
    assertEquals(expected1.size(), list1.size());

    /* given */
    final Collection<String> expected2 = STRING_VALUE_LIST;
    final String[] input2 = expected2.toArray(new String[expected2.size()]);

    /* when */
    final List<String> list2 = newLinkedList(input2);

    /* then */
    assertThat(list2, is(instanceOf(expectedType)));
    assertThat(list2, is(equalTo(expected2)));
    assertEquals(expected2.size(), list2.size());

    /* given */
    final Collection<Object> expected3 = OBJECT_LIST;
    final Object[] input3 = expected3.toArray(new Object[expected3.size()]);

    /* when */
    final List<Object> list3 = newLinkedList(input3);

    /* then */
    assertThat(list3, is(instanceOf(expectedType)));
    assertThat(list3, is(equalTo(expected3)));
    assertEquals(expected3.size(), list3.size());
  }
}
