package org.elixirian.kommonlee.type;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.elixirian.kommonlee.type.Condition1;
import org.junit.Test;

/**
 * This test is to demonstrate how to use the {@link Condition1}.
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-14)
 */
public class ConditionTest
{
	private static class IsOdd implements Condition1<Integer>
	{
		@Override
		public boolean isApplicable(final Integer input)
		{
			return 0 != (input.intValue() & 1);
		}
	}

	private static class IsEven implements Condition1<Integer>
	{
		@Override
		public boolean isApplicable(final Integer input)
		{
			return 0 == (input.intValue() & 1);
		}
	}

	private static class IntegerCollector
	{
		public List<Integer> collect(final List<Integer> integerList, final Condition1<Integer> condition)
		{
			final List<Integer> resultList = new ArrayList<Integer>();
			for (final Integer integer : integerList)
			{
				if (condition.isApplicable(integer))
				{
					resultList.add(integer);
				}
			}
			return resultList;
		}
	}

	private static class IntegerRemover
	{
		public List<Integer> remove(List<Integer> integerList, Condition1<Integer> condition)
		{
			final List<Integer> resultList = new ArrayList<Integer>(integerList);
			for (final Integer integer : integerList)
			{
				if (condition.isApplicable(integer))
				{
					resultList.remove(integer);
				}
			}
			return resultList;
		}
	}

	@Test
	public final void testIsApplicable()
	{
		@SuppressWarnings("boxing")
		final List<Integer> oddNumbers =
			Collections.unmodifiableList(Arrays.asList(-1001, -101, -11, -9, -7, -5, -3, -1, 1, 3, 5, 7, 9, 11, 101,
					1001));

		@SuppressWarnings("boxing")
		final List<Integer> evenNumbers =
			Collections.unmodifiableList(Arrays.asList(-1002, -1000, -102, -100, -12, -10, -8, -6, -4, -2, 0, 2, 4, 6,
					8, 10, 12, 100, 102, 1000, 1002));

		@SuppressWarnings("boxing")
		final List<Integer> integerList =
			Collections.unmodifiableList(Arrays.asList(-1002, -1001, -1000, -102, -101, -100, -12, -11, -10, -9, -8,
					-7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 100, 101, 102, 1000, 1001,
					1002));

		final IntegerCollector integerCollector = new IntegerCollector();

		System.out.println("integerList: " + integerList);
		System.out.println("## expected: ");
		System.out.println("oddNumbers: " + oddNumbers);
		System.out.println("evenNumbers: " + evenNumbers);

		System.out.println("## result: ");
		/* @formatter:off */
		System.out.println("oddNumbers: " + integerCollector.collect(integerList, new Condition1<Integer>() {
			@Override
			public boolean isApplicable(Integer input) { return 0 != (input.intValue() & 1); }
		}));
		System.out.println("evenNumbers: " + integerCollector.collect(integerList, new Condition1<Integer>() {
			@Override
			public boolean isApplicable(Integer input) { return 0 == (input.intValue() & 1); }
		}));
		/* @formatter:on */

		assertThat(integerCollector.collect(integerList, new Condition1<Integer>() {
			@Override
			public boolean isApplicable(Integer input)
			{
				return 0 != (input.intValue() & 1);
			}
		}), is(equalTo(oddNumbers)));

		assertThat(integerCollector.collect(integerList, new Condition1<Integer>() {
			@Override
			public boolean isApplicable(Integer input)
			{
				return 0 == (input.intValue() & 1);
			}
		}), is(equalTo(evenNumbers)));

		assertThat(integerCollector.collect(integerList, new IsOdd()), is(equalTo(oddNumbers)));
		assertThat(integerCollector.collect(integerList, new IsOdd()), is(not(equalTo(evenNumbers))));
		assertThat(integerCollector.collect(integerList, new IsEven()), is(equalTo(evenNumbers)));
		assertThat(integerCollector.collect(integerList, new IsEven()), is(not(equalTo(oddNumbers))));

		final IntegerRemover integerRemover = new IntegerRemover();
		assertThat(integerRemover.remove(integerList, new IsOdd()), is(equalTo(evenNumbers)));
		assertThat(integerRemover.remove(integerList, new IsEven()), is(equalTo(oddNumbers)));
	}

}
