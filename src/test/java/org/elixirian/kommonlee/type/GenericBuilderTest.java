package org.elixirian.kommonlee.type;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.elixirian.kommonlee.type.GenericBuilder;
import org.junit.Test;

/**
 * This test is to demonstrate how to use the {@link GenericBuilder} interface.
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-14)
 */
public class GenericBuilderTest
{
	private static class SomePojo
	{
		private final String surname;
		private final String givenName;
		private final int number;
		private final String email;

		private SomePojo(final String surname, final String givenName, final int number, final String email)
		{
			this.surname = surname;
			this.givenName = givenName;
			this.number = number;
			this.email = email;
		}

		private SomePojo(final SomePojoBuilder somePojoBuilder)
		{
			this.surname = somePojoBuilder.surname;
			this.givenName = somePojoBuilder.givenName;
			this.number = somePojoBuilder.number;
			this.email = somePojoBuilder.email;
		}

		/* @formatter:off */
		public String getSurname() 	{ return surname; }
		public String getGivenName() { return givenName; }
		public int getNumber() { return number; }
		public String getEmail() { return email; }
		/* @formatter:on */

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + (null == surname ? 0 : surname.hashCode());
			result = prime * result + (null == givenName ? 0 : givenName.hashCode());
			result = prime * result + number;
			result = prime * result + (null == email ? 0 : email.hashCode());
			return result;
		}

		@Override
		public boolean equals(final Object obj)
		{
			/* @formatter:off */
			if (this == obj) return true;
			if (!(obj instanceof SomePojo)) return false;
			final SomePojo that = (SomePojo) obj;
			return (this.surname == that.getSurname() || 
						(null != this.surname && this.surname.equals(that.getSurname()))) &&
					(this.givenName == that.getGivenName() || 
						(null != this.givenName && this.givenName.equals(that.getGivenName()))) && 
					this.number == that.getNumber() &&
					(this.email == that.getEmail() || 
						(null != this.email && this.email.equals(that.getEmail())));
			/* @formatter:on */
		}
	}

	private static class SomePojoBuilder implements GenericBuilder<SomePojo>
	{
		private String surname;
		private String givenName;
		private int number;
		private String email;

		public SomePojoBuilder setSurname(final String surname)
		{
			this.surname = surname;
			return this;
		}

		public SomePojoBuilder setGivenName(final String givenName)
		{
			this.givenName = givenName;
			return this;
		}

		public SomePojoBuilder setNumber(final int number)
		{
			this.number = number;
			return this;
		}

		public SomePojoBuilder setEmail(final String email)
		{
			this.email = email;
			return this;
		}

		@Override
		public SomePojo build()
		{
			return new SomePojo(this);
		}
	}

	@Test
	public final void testBuild()
	{
		final String surname = "Lee";
		final String givenName = "Kevin";
		final int number = 123;
		final String email = "kevin@test.test";

		final SomePojo expected = new SomePojo(surname, givenName, number, email);
		final SomePojo result = new SomePojoBuilder().setSurname(surname)
				.setGivenName(givenName)
				.setNumber(number)
				.setEmail(email)
				.build();
		assertThat(result, is(notNullValue()));
		assertThat(result, is(equalTo(expected)));

		assertThat(result.getSurname(), is(equalTo(expected.getSurname())));
		assertThat(result.getGivenName(), is(equalTo(expected.getGivenName())));
		assertEquals(result.getNumber(), expected.getNumber());
		assertThat(result.getEmail(), is(equalTo(expected.getEmail())));
	}

}
