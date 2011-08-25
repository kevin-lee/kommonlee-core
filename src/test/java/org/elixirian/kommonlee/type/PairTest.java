package org.elixirian.kommonlee.type;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.elixirian.kommonlee.type.Pair;
import org.junit.Test;

/**
 * This test is to demonstrate how to use the {@link Pair} interface.
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-14)
 */
public class PairTest
{
	private static final String GIVEN_NAME = "Kevin";
	private static final String SURNAME = "Lee";
	private static final Long NUMBER = Long.valueOf(12345L);

	/* @formatter:off */
	private static class Pojo
	{
		private final String name;
		public Pojo(String name) { 	this.name = name; }
		public String getName()	 { return name; }
	}

	private static class AnotherPojo
	{
		private final Long number;
		public AnotherPojo(Long number) { this.number = number; }
		public Long getNumber() { return number; }
	}
	/* @formatter:on */

	private static class PojoPair implements Pair<Pojo, Pojo>
	{
		private final Pojo left;
		private final Pojo right;

		public PojoPair(Pojo left, Pojo right)
		{
			this.left = left;
			this.right = right;
		}

		@Override
		public Pojo getLeft()
		{
			return left;
		}

		@Override
		public Pojo getRight()
		{
			return right;
		}
	}

	private static class PojoAnotherPojoPair implements Pair<Pojo, AnotherPojo>
	{
		private final Pojo left;
		private final AnotherPojo right;

		public PojoAnotherPojoPair(Pojo left, AnotherPojo right)
		{
			this.left = left;
			this.right = right;
		}

		@Override
		public Pojo getLeft()
		{
			return left;
		}

		@Override
		public AnotherPojo getRight()
		{
			return right;
		}
	}

	@Test
	public final void testGetLeft()
	{
		final Pair<Pojo, Pojo> pojoPair = new PojoPair(new Pojo(GIVEN_NAME), new Pojo(SURNAME));
		assertThat(pojoPair.getLeft()
				.getName(), is(equalTo(GIVEN_NAME)));

		final Pair<Pojo, AnotherPojo> pojoAnotherPojoPair =
			new PojoAnotherPojoPair(new Pojo(GIVEN_NAME), new AnotherPojo(NUMBER));
		assertThat(pojoAnotherPojoPair.getLeft()
				.getName(), is(equalTo(GIVEN_NAME)));
	}

	@Test
	public final void testGetRight()
	{
		final Pair<Pojo, Pojo> pojoPair = new PojoPair(new Pojo(GIVEN_NAME), new Pojo(SURNAME));
		assertThat(pojoPair.getRight()
				.getName(), is(equalTo(SURNAME)));

		final Pair<Pojo, AnotherPojo> pojoAnotherPojoPair =
			new PojoAnotherPojoPair(new Pojo(GIVEN_NAME), new AnotherPojo(NUMBER));
		assertThat(pojoAnotherPojoPair.getRight()
				.getNumber(), is(equalTo(NUMBER)));
	}

	@Test
	public final void testGetLeftAndGetRight()
	{
		final Pair<Pojo, Pojo> pojoPair = new PojoPair(new Pojo(GIVEN_NAME), new Pojo(SURNAME));
		assertThat(pojoPair.getLeft()
				.getName(), is(equalTo(GIVEN_NAME)));
		assertThat(pojoPair.getRight()
				.getName(), is(equalTo(SURNAME)));

		final Pair<Pojo, AnotherPojo> pojoAnotherPojoPair =
			new PojoAnotherPojoPair(new Pojo(GIVEN_NAME), new AnotherPojo(NUMBER));
		assertThat(pojoAnotherPojoPair.getLeft()
				.getName(), is(equalTo(GIVEN_NAME)));
		assertThat(pojoAnotherPojoPair.getRight()
				.getNumber(), is(equalTo(NUMBER)));
	}

}
