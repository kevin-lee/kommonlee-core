/**
 * 
 */
package com.elixirian.common.util;

import static com.elixirian.common.test.CommonTestHelper.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-05-03)
 */
public class ObjectsTest
{
	private static class SomeObject
	{
		private final String name;

		public SomeObject(String name)
		{
			this.name = name;
		}

		@Override
		public boolean equals(Object someObject)
		{
			if (this == someObject)
			{
				return true;
			}
			if (null == someObject)
			{
				return false;
			}
			if (!(someObject instanceof SomeObject))
			{
				return false;
			}
			SomeObject that = (SomeObject) someObject;
			return (this.name == that.name || (null != this.name && this.name.equals(that.name)));
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public String toString()
		{
			return "I am " + name + ".";
		}
	}

	@Test(expected = IllegalStateException.class)
	public void testObjects() throws Exception
	{
		testNotAccessibleConstructor(Objects.class, classArrayOf(), objectArrayOf());
	}

	/**
	 * Test method for {@link com.elixirian.common.util.Objects#equals(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObjectObject()
	{
		assertThat(Boolean.valueOf(Objects.equals(null, null)), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(Objects.equals("", "")), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(Objects.equals("test", "test")), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(Objects.equals(new String("1234"), new String("1234"))), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(Objects.equals(new SomeObject("Kevin"), new SomeObject("Kevin"))), equalTo(Boolean.TRUE));

		assertThat(Boolean.valueOf(Objects.equals(null, new Object())), equalTo(Boolean.FALSE));
		assertThat(Boolean.valueOf(Objects.equals("", "1")), equalTo(Boolean.FALSE));
		assertThat(Boolean.valueOf(Objects.equals("test1", "test2")), equalTo(Boolean.FALSE));
		assertThat(Boolean.valueOf(Objects.equals(new String("1234"), new String("4321"))), equalTo(Boolean.FALSE));
		assertThat(Boolean.valueOf(Objects.equals(new SomeObject("Kevin"), new SomeObject("Lee"))), equalTo(Boolean.FALSE));
	}

	/**
	 * Test method for {@link com.elixirian.common.util.Objects#deepEquals(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testDeepEquals()
	{
		assertThat(Boolean.valueOf(Objects.deepEquals(null, null)), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(Objects.deepEquals("", "")), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(Objects.deepEquals("test", "test")), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(Objects.deepEquals(new String("1234"), new String("1234"))), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(Objects.deepEquals(new SomeObject("Kevin"), new SomeObject("Kevin"))), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(Objects.deepEquals(new String[] { "Hello", "Kevin", "Lee" }, new String[] { "Hello", "Kevin", "Lee" })),
				equalTo(Boolean.TRUE));
		assertThat(
				Boolean.valueOf(Objects.deepEquals(new SomeObject[] { new SomeObject("Kevin"), new SomeObject("Lee"),
						new SomeObject("SeongHyun") }, new SomeObject[] { new SomeObject("Kevin"), new SomeObject("Lee"),
						new SomeObject("SeongHyun") })), equalTo(Boolean.TRUE));

		assertThat(Boolean.valueOf(Objects.deepEquals(null, new Object())), equalTo(Boolean.FALSE));
		assertThat(Boolean.valueOf(Objects.deepEquals("", "1")), equalTo(Boolean.FALSE));
		assertThat(Boolean.valueOf(Objects.deepEquals("test1", "test2")), equalTo(Boolean.FALSE));
		assertThat(Boolean.valueOf(Objects.deepEquals(new String("1234"), new String("4321"))), equalTo(Boolean.FALSE));
		assertThat(Boolean.valueOf(Objects.deepEquals(new SomeObject("Kevin"), new SomeObject("Tom"))), equalTo(Boolean.FALSE));
		assertThat(Boolean.valueOf(Objects.deepEquals(new String[] { "Hello", "Lee", "Kevin" }, new String[] { "Hello", "Kevin", "Lee" })),
				equalTo(Boolean.FALSE));
		assertThat(
				Boolean.valueOf(Objects.deepEquals(new SomeObject[] { new SomeObject("SeongHyun"), new SomeObject("Lee"),
						new SomeObject("Kevin") }, new SomeObject[] { new SomeObject("Kevin"), new SomeObject("Lee"),
						new SomeObject("SeongHyun") })), equalTo(Boolean.FALSE));
	}

	/**
	 * Test method for {@link com.elixirian.common.util.Objects#hashCode(java.lang.Object)}.
	 */
	@Test
	public final void testHashCodeObject()
	{
		assertThat(Integer.valueOf(Objects.hashCode(null)), equalTo(Integer.valueOf(0)));
		assertThat(Integer.valueOf(Objects.hashCode("test")), equalTo(Integer.valueOf("test".hashCode())));
		assertThat(Integer.valueOf(Objects.hashCode(new String("test"))), equalTo(Integer.valueOf(new String("test").hashCode())));
		assertThat(Integer.valueOf(Objects.hashCode(new SomeObject("Kevin"))), equalTo(Integer.valueOf(new SomeObject("Kevin").hashCode())));
	}

	/**
	 * Test method for {@link com.elixirian.common.util.Objects#hash(java.lang.Object[])}.
	 */
	@Test
	public final void testHash()
	{
		assertThat(Integer.valueOf(Objects.hash(null)), equalTo(Integer.valueOf(Arrays.hashCode((Object[]) null))));
		assertThat(Integer.valueOf(Objects.hash((Object) null)), equalTo(Integer.valueOf(Arrays.hashCode(new Object[] { null }))));
		assertThat(Integer.valueOf(Objects.hash("test")), equalTo(Integer.valueOf(Arrays.hashCode(new String[] { "test" }))));
		assertThat(Integer.valueOf(Objects.hash(new String("test"))),
				equalTo(Integer.valueOf(Arrays.hashCode(new String[] { new String("test") }))));
		assertThat(Integer.valueOf(Objects.hash(new SomeObject("Kevin"))),
				equalTo(Integer.valueOf(Arrays.hashCode(new SomeObject[] { new SomeObject("Kevin") }))));
		assertThat(Integer.valueOf(Objects.hash("test", new String("something else"), new SomeObject("Kevin"))),
				equalTo(Integer.valueOf(Arrays.hashCode(new Object[] { "test", new String("something else"), new SomeObject("Kevin") }))));
	}

	/**
	 * Test method for {@link com.elixirian.common.util.Objects#toString(java.lang.Object)}.
	 */
	@Test
	public final void testToStringObject()
	{
		assertThat(Objects.toString(null), equalTo("null"));
		assertThat(Objects.toString("test"), equalTo("test"));
		assertThat(Objects.toString(new String("something else")), equalTo("something else"));
		Object object = new Object();
		assertThat(Objects.toString(object), equalTo(object.toString()));
		assertThat(Objects.toString(new SomeObject("Kevin")), equalTo(new SomeObject("Kevin").toString()));
		SomeObject someObject = new SomeObject("Lee");
		assertThat(Objects.toString(someObject), equalTo(someObject.toString()));
	}

	/**
	 * Test method for {@link com.elixirian.common.util.Objects#toString(java.lang.Object, java.lang.String)}.
	 */
	@Test
	public final void testToStringObjectString()
	{
		final String nullDefault = "It is null!!!";
		assertThat(Objects.toString(null, nullDefault), equalTo(nullDefault));
		assertThat(Objects.toString("test", nullDefault), equalTo("test"));
		assertThat(Objects.toString(new String("something else"), nullDefault), equalTo("something else"));
		Object object = new Object();
		assertThat(Objects.toString(object, nullDefault), equalTo(object.toString()));
		assertThat(Objects.toString(new SomeObject("Kevin"), nullDefault), equalTo(new SomeObject("Kevin").toString()));
		SomeObject someObject = new SomeObject("Lee");
		assertThat(Objects.toString(someObject, nullDefault), equalTo(someObject.toString()));
	}

	/**
	 * Test method for {@link com.elixirian.common.util.Objects#compare(java.lang.Object, java.lang.Object, java.util.Comparator)}.
	 */
	@Test
	public final void testCompare()
	{
		final Comparator<SomeObject> comparator = new Comparator<SomeObject>()
		{
			@Override
			public int compare(SomeObject o1, SomeObject o2)
			{
				return null == o1 ? -1 : null == o2 ? 1 : compareEach(o1.name, o2.name);
			}

			private int compareEach(String name1, String name2)
			{
				if (null == name1)
				{
					return -1;
				}
				for (int i = 0, size = name1.length(); i < size; i++)
				{
					if (i >= name2.length())
					{
						return 1;
					}
					else if (name1.charAt(i) < name2.charAt(i))
					{
						return -1;
					}
					else if (name1.charAt(i) > name2.charAt(i))
					{
						return 1;
					}
				}
				return (name1.length() < name2.length() ? -1 : 0);
			}
		};
		assertThat(Integer.valueOf(Objects.compare(null, null, comparator)), equalTo(Integer.valueOf(0)));

		SomeObject someObject1 = new SomeObject("Kevin");
		SomeObject someObject2 = new SomeObject("Kevin");
		assertThat(Integer.valueOf(Objects.compare(someObject1, someObject2, comparator)),
				equalTo(Integer.valueOf(comparator.compare(someObject1, someObject2))));

		someObject1 = new SomeObject("Kevin");
		someObject2 = someObject1;
		assertThat(Integer.valueOf(Objects.compare(someObject1, someObject2, comparator)),
				equalTo(Integer.valueOf(comparator.compare(someObject1, someObject2))));

		someObject1 = null;
		someObject2 = new SomeObject("Kevin");
		assertThat(Integer.valueOf(Objects.compare(someObject1, someObject2, comparator)),
				equalTo(Integer.valueOf(comparator.compare(someObject1, someObject2))));

		someObject1 = new SomeObject("Kevin");
		someObject2 = null;
		assertThat(Integer.valueOf(Objects.compare(someObject1, someObject2, comparator)),
				equalTo(Integer.valueOf(comparator.compare(someObject1, someObject2))));

		someObject1 = new SomeObject("KevinLee");
		someObject2 = new SomeObject("Kevin");
		assertThat(Integer.valueOf(Objects.compare(someObject1, someObject2, comparator)),
				equalTo(Integer.valueOf(comparator.compare(someObject1, someObject2))));

		someObject1 = new SomeObject("Kevin");
		someObject2 = new SomeObject("Lee");
		assertThat(Integer.valueOf(Objects.compare(someObject1, someObject2, comparator)),
				equalTo(Integer.valueOf(comparator.compare(someObject1, someObject2))));
	}

	/**
	 * Test method for {@link com.elixirian.common.util.Objects#nonNull(java.lang.Object)}.
	 */
	@Test
	public final void testNonNullT()
	{
		assertThat(Objects.nonNull("test"), equalTo("test"));
		assertThat(Objects.nonNull(new String("something else")), equalTo(new String("something else")));
		assertThat(Objects.nonNull(new SomeObject("Kevin")), equalTo(new SomeObject("Kevin")));
		boolean exceptionThrown = false;
		try
		{
			Objects.nonNull(null);
		}
		catch (NullPointerException e)
		{
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	/**
	 * Test method for {@link com.elixirian.common.util.Objects#nonNull(java.lang.Object, java.lang.String)}.
	 */
	@Test
	public final void testNonNullTString()
	{
		final String message = "It is null!!!!!";
		assertThat(Objects.nonNull("test", message), equalTo("test"));
		assertThat(Objects.nonNull(new String("something else"), message), equalTo(new String("something else")));
		assertThat(Objects.nonNull(new SomeObject("Kevin"), message), equalTo(new SomeObject("Kevin")));
		boolean exceptionThrown = false;
		try
		{
			Objects.nonNull(null, message);
		}
		catch (NullPointerException e)
		{
			exceptionThrown = message.equals(e.getMessage());
		}
		assertTrue(exceptionThrown);
	}

	private static class SomePojo
	{
		private final Long id;
		private final String name;
		private final boolean registered;
		private final byte byteValue;
		private final short shortValue;
		private final int intValue;
		private final long longValue;
		private final float floatValue;
		private final double doubleValue;

		public SomePojo(Long id, String name, boolean registered, byte byteValue, short shortValue, int intValue,
				long longValue, float floatValue, double doubleValue)
		{
			this.id = id;
			this.name = name;
			this.registered = registered;
			this.byteValue = byteValue;
			this.shortValue = shortValue;
			this.intValue = intValue;
			this.longValue = longValue;
			this.floatValue = floatValue;
			this.doubleValue = doubleValue;
		}

		@Override
		public String toString()
		{
			return Objects.toStringBuilder(this)
					.add("id", id)
					.add("name", name)
					.add("registered", registered)
					.add("byteValue", byteValue)
					.add("shortValue", shortValue)
					.add("intValue", intValue)
					.add("longValue", longValue)
					.add("floatValue", floatValue)
					.add("doubleValue", doubleValue)
					.toString();
		}
	}

	@Test
	public final void testToStringBuilder()
	{
		final String expected =
			"SomePojo{id=100, name=Kevin, registered=true, byteValue=10, shortValue=20, intValue=100, longValue=1000000, floatValue=12.34, doubleValue=56.78}";
		final SomePojo somePojo =
			new SomePojo(Long.valueOf(100L), "Kevin", true, (byte) 10, (short) 20, 100, 1000000L, 12.34F, 56.78D);
		assertThat(somePojo.toString(), is(equalTo(expected)));

		final String expected2 =
			"ObjectsTest{id=999, name=Kevin Lee, registered=false, byteValue=11, shortValue=123, intValue=123456, longValue=11111111111, floatValue=10.1, doubleValue=12345.12345}";
		final Long id = Long.valueOf(999L);
		final String name = "Kevin Lee";
		final boolean registered = false;
		final byte byteValue = 11;
		final short shortValue = 123;
		final int intValue = 123456;
		final long longValue = 11111111111L;
		final float floatValue = 10.10F;
		final double doubleValue = 12345.12345;
		assertThat(Objects.toStringBuilder(this)
				.add("id", id)
				.add("name", name)
				.add("registered", registered)
				.add("byteValue", byteValue)
				.add("shortValue", shortValue)
				.add("intValue", intValue)
				.add("longValue", longValue)
				.add("floatValue", floatValue)
				.add("doubleValue", doubleValue)
				.toString(), is(equalTo(expected2)));

		assertThat(Objects.toStringBuilder(this)
				.value("")
				.toString(), is(equalTo(getClass().getSimpleName() + "{}")));

		assertThat(Objects.toStringBuilder(this)
				.toString(), is(equalTo(getClass().getSimpleName() + "{}")));

		assertThat(Objects.toStringBuilder(this)
				.getFieldSeparator(), is(equalTo(Objects.ToStringBuilder.DEFAULT_FIELD_SEPARATOR)));
		assertThat(Objects.toStringBuilder(this)
				.getNameValueSeparator(), is(equalTo(Objects.ToStringBuilder.DEFAULT_NAME_VALUE_SEPARATOR)));
	}

	@Test
	public final void testToStringBuilderWithExtraValue()
	{
		final String expected2 =
			"ObjectsTest{Some extra information. blah blah blah, id=999, name=Kevin Lee, registered=false, byteValue=11, shortValue=123, intValue=123456, longValue=11111111111, floatValue=10.1, doubleValue=12345.12345}";
		final Long id = Long.valueOf(999L);
		final String name = "Kevin Lee";
		final boolean registered = false;
		final byte byteValue = 11;
		final short shortValue = 123;
		final int intValue = 123456;
		final long longValue = 11111111111L;
		final float floatValue = 10.10F;
		final double doubleValue = 12345.12345;
		assertThat(Objects.toStringBuilder(this)
				.value("Some extra information. blah blah blah")
				.add("id", id)
				.add("name", name)
				.add("registered", registered)
				.add("byteValue", byteValue)
				.add("shortValue", shortValue)
				.add("intValue", intValue)
				.add("longValue", longValue)
				.add("floatValue", floatValue)
				.add("doubleValue", doubleValue)
				.toString(), is(equalTo(expected2)));
	}

	@Test
	public final void testToStringBuilderWithExtraValueAndNewLines()
	{
		final String expected2 =
			"Object{Some extra information. blah blah blah, \nid=999, name=Kevin Lee, registered=false, \nbyteValue=11, shortValue=123, intValue=123456, longValue=11111111111, floatValue=10.1, doubleValue=12345.12345}";
		final Long id = Long.valueOf(999L);
		final String name = "Kevin Lee";
		final boolean registered = false;
		final byte byteValue = 11;
		final short shortValue = 123;
		final int intValue = 123456;
		final long longValue = 11111111111L;
		final float floatValue = 10.10F;
		final double doubleValue = 12345.12345;
		assertThat(Objects.toStringBuilder(new Object())
				.value("Some extra information. blah blah blah")
				.newLine()
				.add("id", id)
				.add("name", name)
				.add("registered", registered)
				.newLine()
				.add("byteValue", byteValue)
				.add("shortValue", shortValue)
				.add("intValue", intValue)
				.add("longValue", longValue)
				.add("floatValue", floatValue)
				.add("doubleValue", doubleValue)
				.toString(), is(equalTo(expected2)));
	}

	@Test
	public final void testToStringBuilderWithExtraValuesWithoutSeparatorAndNewLines()
	{
		final String expected2 =
			"ObjectsTest{Some extra information. blah blah blah\nid=999, name=Kevin Lee, registered=false, \nbyteValue=11, shortValue=123, intValue=123456, longValue=11111111111, floatValue=10.1, doubleValue=12345.12345}";
		final Long id = Long.valueOf(999L);
		final String name = "Kevin Lee";
		final boolean registered = false;
		final byte byteValue = 11;
		final short shortValue = 123;
		final int intValue = 123456;
		final long longValue = 11111111111L;
		final float floatValue = 10.10F;
		final double doubleValue = 12345.12345;
		assertThat(Objects.toStringBuilder(this)
				.valueWithNoSeparator("Some extra information. blah blah blah")
				.newLine()
				.add("id", id)
				.add("name", name)
				.add("registered", registered)
				.newLine()
				.add("byteValue", byteValue)
				.add("shortValue", shortValue)
				.add("intValue", intValue)
				.add("longValue", longValue)
				.add("floatValue", floatValue)
				.add("doubleValue", doubleValue)
				.toString(), is(equalTo(expected2)));
	}

	@Test(expected = NullPointerException.class)
	public final void testToStringBuilderWithNullObject()
	{
		Objects.toStringBuilder(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testToStringBuilderWithNullName()
	{
		Objects.toStringBuilder(this)
				.add(null, "Kevin")
				.toString();
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testToStringBuilderWithEmptyName()
	{
		Objects.toStringBuilder(this)
				.add("", "Kevin")
				.toString();
	}

	@Test
	public final void testToStringBuilderObjectStringString()
	{
		final String expected =
			"ObjectsTest{id: 999 | name: Kevin Lee | registered: false | byteValue: 11 | shortValue: 123 | intValue: 123456 | longValue: 11111111111 | floatValue: 10.1 | doubleValue: 12345.12345}";
		final Long id = Long.valueOf(999L);
		final String name = "Kevin Lee";
		final boolean registered = false;
		final byte byteValue = 11;
		final short shortValue = 123;
		final int intValue = 123456;
		final long longValue = 11111111111L;
		final float floatValue = 10.10F;
		final double doubleValue = 12345.12345;
		assertThat(Objects.toStringBuilder(this, " | ", ": ")
				.add("id", id)
				.add("name", name)
				.add("registered", registered)
				.add("byteValue", byteValue)
				.add("shortValue", shortValue)
				.add("intValue", intValue)
				.add("longValue", longValue)
				.add("floatValue", floatValue)
				.add("doubleValue", doubleValue)
				.toString(), is(equalTo(expected)));
	}

	@Test(expected = NullPointerException.class)
	public final void testToStringBuilderObjectStringStringWithNullObject()
	{
		Objects.toStringBuilder(null, " | ", ": ");
	}

	@Test(expected = NullPointerException.class)
	public final void testToStringBuilderObjectStringStringWithNullFieldSeparator()
	{
		Objects.toStringBuilder(this, null, ": ");
	}

	@Test(expected = NullPointerException.class)
	public final void testToStringBuilderObjectStringStringWithNullNameValueSeparator()
	{
		Objects.toStringBuilder(this, " | ", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testToStringBuilderObjectStringStringWithNullName()
	{
		Objects.toStringBuilder(this, " | ", ": ")
				.add(null, "Kevin");
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testToStringBuilderObjectStringStringWithEmptyName()
	{
		Objects.toStringBuilder(this, " | ", ": ")
				.add("", "Kevin");
	}
}
