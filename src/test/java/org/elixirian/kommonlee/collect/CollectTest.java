package org.elixirian.kommonlee.collect;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.elixirian.kommonlee.collect.immutable.ImmutableArrayList;
import org.elixirian.kommonlee.type.function.Condition1;
import org.elixirian.kommonlee.type.function.Function1;
import org.elixirian.kommonlee.util.NeoArrays;
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
		final ReadableList<String> list = ImmutableArrayList.listOf("Kevin", "Lee", "blah", "blah blah", null, "not null");
		final ReadableList<Integer> list2 =
			ImmutableArrayList.listOf(1, 2, 3, 4, 5, 6, 7, 88, 999, 4, 5, 6, 7, 88, 999, 2, 3, 4, 5, 6, 7, 5, 5, 6, 232, 8);
		final ReadableList<?> list3 =
			ImmutableArrayList.listOf(1, 2, 3, 4, 5, 6, 7, 88, 999, 2, 3, 4, 5, 6, 7, 5, 5, 6, 232, 8);

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
		final ImmutableArrayList<Integer> emptyList =
			(ImmutableArrayList<Integer>) ImmutableArrayList.EMPTY_IMMUTABLE_ARRAY_LIST;
		/* @formatter:off */
		final ImmutableArrayList<Integer> resultFromEmptyListSelect =
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
				+ (resultFromEmptyListSelect == ImmutableArrayList.EMPTY_IMMUTABLE_ARRAY_LIST));

		/* @formatter:off */
    System.out.println("list2.map: "+ list2.map(new Function1<Integer, String>()
        { @Override public String apply(final Integer input) { return -100 > input.intValue() ? "Fewer than -100" : "NO"; } }));
    /* @formatter:on */

		/* @formatter:off */
    final ImmutableArrayList<String> resultFromEmptyListMapMethod = emptyList.map(new Function1<Integer, String>()
        { @Override public String apply(final Integer input) { return -100 > input.intValue() ? "Fewer than -100" : "NO"; } });
    /* @formatter:on */
		System.out.println("someList.map: " + resultFromEmptyListMapMethod);
		System.out.println("resultFromEmptyList.map() == ImmutableArrayList.EMPTY_IMMUTABLE_ARRAY_LIST: "
				+ (resultFromEmptyListMapMethod == ImmutableArrayList.EMPTY_IMMUTABLE_ARRAY_LIST));

		/* @formatter:off */
    System.out.println(list2.mapSelectively(new Condition1<Integer>()
        { @Override public boolean isMet(final Integer input) { return 5 < input.intValue(); } },
        new Function1<Integer, String>()
        { @Override public String apply(final Integer input) { return "{Yeah bigger than five: " + input + "}"; } }));
    /* @formatter:on */

		System.out.println("Objects.toStringOf(list2.toArray()): " + Objects.toStringOf(list2.toArray()));
	}

}
