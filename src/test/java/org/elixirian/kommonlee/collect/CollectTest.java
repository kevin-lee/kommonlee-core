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
package org.elixirian.kommonlee.collect;

import org.elixirian.kommonlee.collect.immutable.ImmutableList;
import org.elixirian.kommonlee.collect.immutable.ImmutableLists;
import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.functional.Function1;
import org.elixirian.kommonlee.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CollectTest
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

  @Test
  public final void test()
  {
    final ReadableList<String> list =
      new ReadableArrayList<String>("Kevin", "Lee", "blah", "blah blah", null, "not null");
    final ReadableList<Integer> list2 =
      new ReadableArrayList<Integer>(1, 2, 3, 4, 5, 6, 7, 88, 999, 4, 5, 6, 7, 88, 999, 2, 3, 4, 5, 6, 7, 5, 5, 6, 232,
          8);
    final ReadableList<?> list3 =
      new ReadableArrayList<Object>(1, 2, 3, 4, 5, 6, 7, 88, 999, 2, 3, 4, 5, 6, 7, 5, 5, 6, 232, 8);

    System.out.println(list);
    System.out.println(list2);
    System.out.println(list3);

    System.out.println(list2.select(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return 5 < input.intValue();
      }
    }));

    /* @formatter:off */
    System.out.println(list2.map(new Function1<Integer, Boolean>()
      { @Override public Boolean apply(final Integer input) { return 5 < input.intValue(); } }));
    /* @formatter:on */

    /* @formatter:off */
    System.out.println(list2.mapSelectively(new Condition1<Integer>()
      { @Override public boolean isMet(final Integer input) { return 5 < input.intValue(); } },
      new Function1<Integer, String>()
      { @Override public String apply(final Integer input) { return "{Yeah bigger than five: " + input + "}"; } }));
    /* @formatter:on */
  }

  @Test
  public final void test1()
  {
    System.out.println("\nCollectTest.test1()");
    final ReadableList<String> list = ImmutableLists.listOf("Kevin", "Lee", "blah", "blah blah", null, "not null");
    final ReadableList<Integer> list2 =
      ImmutableLists.listOf(1, 2, 3, 4, 5, 6, 7, 88, 999, 4, 5, 6, 7, 88, 999, 2, 3, 4, 5, 6, 7, 5, 5, 6, 232, 8);
    final ReadableList<?> list3 =
      ImmutableLists.listOf(1, 2, 3, 4, 5, 6, 7, 88, 999, 2, 3, 4, 5, 6, 7, 5, 5, 6, 232, 8);

    System.out.println(list);
    System.out.println(list2);
    System.out.println(list3);

    System.out.println("list2.select 5 < input.intValue(): " + list2.select(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return 5 < input.intValue();
      }
    }));

    @SuppressWarnings("unchecked")
    final ImmutableList<Integer> emptyList = ImmutableLists.emptyList();
    /* @formatter:off */
		final ImmutableList<Integer> resultFromEmptyListSelect =
				emptyList.select(
						new Condition1<Integer>() {
							@Override
							public boolean isMet(final Integer input)
							{
								return 5 < input.intValue();
							}
						});
		/* @formatter:on */

    System.out.println("emptyList.select: " + resultFromEmptyListSelect);
    System.out.println("emptyList.select == ImmutableArrayList.EMPTY_IMMUTABLE_ARRAY_LIST): "
        + (resultFromEmptyListSelect == ImmutableLists.<Integer> emptyList()));

    /* @formatter:off */
    System.out.println("list2.map: "+ list2.map(new Function1<Integer, String>()
        { @Override public String apply(final Integer input) { return -100 > input.intValue() ? "Fewer than -100" : "NO"; } }));
    /* @formatter:on */

    /* @formatter:off */
    final ImmutableList<String> resultFromEmptyListMapMethod = emptyList.map(new Function1<Integer, String>()
        { @Override public String apply(final Integer input) { return -100 > input.intValue() ? "Fewer than -100" : "NO"; } });
    /* @formatter:on */
    System.out.println("someList.map: " + resultFromEmptyListMapMethod);
    System.out.println("resultFromEmptyList.map() == ImmutableArrayList.EMPTY_IMMUTABLE_ARRAY_LIST: "
        + (resultFromEmptyListMapMethod == ImmutableLists.<String> emptyList()));

    /* @formatter:off */
    System.out.println(list2.mapSelectively(new Condition1<Integer>()
        { @Override public boolean isMet(final Integer input) { return 5 < input.intValue(); } },
        new Function1<Integer, String>()
        { @Override public String apply(final Integer input) { return "{Yeah bigger than five: " + input + "}"; } }));
    /* @formatter:on */

    System.out.println("Objects.toStringOf(list2.toArray()): " + Objects.toStringOf(list2.toArray()));
  }

}
