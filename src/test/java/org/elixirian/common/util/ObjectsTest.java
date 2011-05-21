/**
 * 
 */
package org.elixirian.common.util;

import static org.elixirian.common.test.CommonTestHelper.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import org.elixirian.common.test.CommonTestHelper.Accessibility;
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

	@Test(expected = IllegalAccessException.class)
	public final void testObjects() throws Exception
	{
		testNotAccessibleConstructor(Objects.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
	}

	@Test
	public final void testEqualByteByte()
	{
		for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE; i < size; i++)
		{
			assertThat(Boolean.valueOf(Objects.equal((byte) i, (byte) i)), is(equalTo(Boolean.TRUE)));
		}
		for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 1; i < size; i++)
		{
			assertThat(Boolean.valueOf(Objects.equal((byte) i, (byte) (i + 1))), is(equalTo(Boolean.FALSE)));
		}
	}

	@Test
	public final void testEqualCharChar()
	{
		for (int i = Character.MIN_VALUE, size = Character.MAX_VALUE; i < size; i++)
		{
			assertThat(Boolean.valueOf(Objects.equal((char) i, (char) i)), is(equalTo(Boolean.TRUE)));
		}
		for (int i = Character.MIN_VALUE, size = Character.MAX_VALUE - 1; i < size; i++)
		{
			assertThat(Boolean.valueOf(Objects.equal((char) i, (char) (i + 1))), is(equalTo(Boolean.FALSE)));
		}
	}

	@Test
	public final void testEqualShortShort()
	{
		for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
		{
			assertThat(Boolean.valueOf(Objects.equal((short) i, (short) i)), is(equalTo(Boolean.TRUE)));
		}
		for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
		{
			assertThat(Boolean.valueOf(Objects.equal((short) i, (short) (i + 1))), is(equalTo(Boolean.FALSE)));
		}
	}

	@Test
	public final void testEqualIntInt()
	{
		for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
		{
			assertThat(Boolean.valueOf(Objects.equal(i, i)), is(equalTo(Boolean.TRUE)));
		}
		for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
		{
			assertThat(Boolean.valueOf(Objects.equal(i, i + 1)), is(equalTo(Boolean.FALSE)));
		}
	}

	@Test
	public final void testEqualLongLong()
	{
		for (long i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
		{
			assertThat(Boolean.valueOf(Objects.equal(i, i)), is(equalTo(Boolean.TRUE)));
		}
		for (long i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
		{
			assertThat(Boolean.valueOf(Objects.equal(i, i + 1)), is(equalTo(Boolean.FALSE)));
		}
	}

	@Test
	public final void testEqualFloatFloat()
	{
		for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
		{
			assertThat(Boolean.valueOf(Objects.equal(i + 0.50F, i + 0.50F)), is(equalTo(Boolean.TRUE)));
		}
		for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
		{
			assertThat(Boolean.valueOf(Objects.equal(i + 0.50F, i + 0.50F + 1)), is(equalTo(Boolean.FALSE)));
		}
	}

	@Test
	public final void testEqualDoubleDouble()
	{
		for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
		{
			assertThat(Boolean.valueOf(Objects.equal(i + 0.1234D, i + 0.1234D)), is(equalTo(Boolean.TRUE)));
		}
		for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
		{
			assertThat(Boolean.valueOf(Objects.equal(i + 0.1234D, i + 0.1234D + 1)), is(equalTo(Boolean.FALSE)));
		}
	}

	@Test
	public final void testEqualBooleanBoolean()
	{
		assertThat(Boolean.valueOf(Objects.equal(false, false)), is(equalTo(Boolean.TRUE)));
		assertThat(Boolean.valueOf(Objects.equal(false, true)), is(equalTo(Boolean.FALSE)));
		assertThat(Boolean.valueOf(Objects.equal(true, false)), is(equalTo(Boolean.FALSE)));
		assertThat(Boolean.valueOf(Objects.equal(true, true)), is(equalTo(Boolean.TRUE)));
	}

	@Test
	public final void testEqualObjectObject()
	{
		assertThat(Boolean.valueOf(Objects.equal(null, null)), is(equalTo(Boolean.TRUE)));
		assertThat(Boolean.valueOf(Objects.equal("", "")), is(equalTo(Boolean.TRUE)));
		assertThat(Boolean.valueOf(Objects.equal("test", "test")), is(equalTo(Boolean.TRUE)));
		assertThat(Boolean.valueOf(Objects.equal(new String("1234"), new String("1234"))), is(equalTo(Boolean.TRUE)));
		assertThat(Boolean.valueOf(Objects.equal(new SomeObject("Kevin"), new SomeObject("Kevin"))),
				is(equalTo(Boolean.TRUE)));

		assertThat(Boolean.valueOf(Objects.equal(null, new Object())), is(equalTo(Boolean.FALSE)));
		assertThat(Boolean.valueOf(Objects.equal("", "1")), is(equalTo(Boolean.FALSE)));
		assertThat(Boolean.valueOf(Objects.equal("test1", "test2")), is(equalTo(Boolean.FALSE)));
		assertThat(Boolean.valueOf(Objects.equal(new String("1234"), new String("4321"))), is(equalTo(Boolean.FALSE)));
		assertThat(Boolean.valueOf(Objects.equal(new SomeObject("Kevin"), new SomeObject("Lee"))),
				is(equalTo(Boolean.FALSE)));
	}

	@Test
	public final void testDeepEqual()
	{
		assertThat(Boolean.valueOf(Objects.deepEqual(null, null)), is(equalTo(Boolean.TRUE)));
		assertThat(Boolean.valueOf(Objects.deepEqual("", "")), is(equalTo(Boolean.TRUE)));
		assertThat(Boolean.valueOf(Objects.deepEqual("test", "test")), is(equalTo(Boolean.TRUE)));
		assertThat(Boolean.valueOf(Objects.deepEqual(new String("1234"), new String("1234"))),
				is(equalTo(Boolean.TRUE)));
		assertThat(Boolean.valueOf(Objects.deepEqual(new SomeObject("Kevin"), new SomeObject("Kevin"))),
				is(equalTo(Boolean.TRUE)));
		assertThat(
				Boolean.valueOf(Objects.deepEqual(new String[] { "Hello", "Kevin", "Lee" }, new String[] { "Hello",
						"Kevin", "Lee" })), is(equalTo(Boolean.TRUE)));
		assertThat(
				Boolean.valueOf(Objects.deepEqual(new SomeObject[] { new SomeObject("Kevin"), new SomeObject("Lee"),
						new SomeObject("SeongHyun") }, new SomeObject[] { new SomeObject("Kevin"),
						new SomeObject("Lee"), new SomeObject("SeongHyun") })), is(equalTo(Boolean.TRUE)));

		assertThat(Boolean.valueOf(Objects.deepEqual(null, new Object())), is(equalTo(Boolean.FALSE)));
		assertThat(Boolean.valueOf(Objects.deepEqual("", "1")), is(equalTo(Boolean.FALSE)));
		assertThat(Boolean.valueOf(Objects.deepEqual("test1", "test2")), is(equalTo(Boolean.FALSE)));
		assertThat(Boolean.valueOf(Objects.deepEqual(new String("1234"), new String("4321"))),
				is(equalTo(Boolean.FALSE)));
		assertThat(Boolean.valueOf(Objects.deepEqual(new SomeObject("Kevin"), new SomeObject("Tom"))),
				is(equalTo(Boolean.FALSE)));
		assertThat(
				Boolean.valueOf(Objects.deepEqual(new String[] { "Hello", "Lee", "Kevin" }, new String[] { "Hello",
						"Kevin", "Lee" })), is(equalTo(Boolean.FALSE)));
		assertThat(
				Boolean.valueOf(Objects.deepEqual(new SomeObject[] { new SomeObject("SeongHyun"),
						new SomeObject("Lee"), new SomeObject("Kevin") }, new SomeObject[] { new SomeObject("Kevin"),
						new SomeObject("Lee"), new SomeObject("SeongHyun") })), is(equalTo(Boolean.FALSE)));
	}

	@Test
	public final void testHashCodeOfObject()
	{
		assertThat(Integer.valueOf(Objects.hashCodeOf((byte) 64)), is(equalTo(Integer.valueOf(64))));
		assertThat(Integer.valueOf(Objects.hashCodeOf((short) (Byte.MAX_VALUE + 10))),
				is(equalTo(Integer.valueOf(Byte.MAX_VALUE + 10))));
		assertThat(Integer.valueOf(Objects.hashCodeOf((char) (Character.MAX_VALUE - 100))),
				is(equalTo(Integer.valueOf(Character.MAX_VALUE - 100))));
		assertThat(Integer.valueOf(Objects.hashCodeOf(Short.MAX_VALUE + 1000)),
				is(equalTo(Integer.valueOf(Short.MAX_VALUE + 1000))));
		assertThat(
				Integer.valueOf(Objects.hashCodeOf(Integer.MAX_VALUE + 1000L)),
				is(equalTo(Integer.valueOf((int) ((Integer.MAX_VALUE + 1000L) ^ ((Integer.MAX_VALUE + 1000L) >>> 32))))));
		assertThat(Integer.valueOf(Objects.hashCodeOf(10.10F)),
				is(equalTo(Integer.valueOf(Float.floatToIntBits(10.10F)))));
		final long longBits = Double.doubleToLongBits(Float.MAX_VALUE + 100.1234D);
		assertThat(Integer.valueOf(Objects.hashCodeOf(Float.MAX_VALUE + 100.1234D)),
				is(equalTo(Integer.valueOf((int) (longBits ^ (longBits >>> 32))))));
		assertThat(Integer.valueOf(Objects.hashCodeOf(true)), is(equalTo(Integer.valueOf(Boolean.TRUE.hashCode()))));
		assertThat(Integer.valueOf(Objects.hashCodeOf(false)), is(equalTo(Integer.valueOf(Boolean.FALSE.hashCode()))));
		assertThat(Integer.valueOf(Objects.hashCodeOf(null)), is(equalTo(Integer.valueOf(0))));
		assertThat(Integer.valueOf(Objects.hashCodeOf("test")), is(equalTo(Integer.valueOf("test".hashCode()))));
		assertThat(Integer.valueOf(Objects.hashCodeOf(new String("test"))),
				is(equalTo(Integer.valueOf(new String("test").hashCode()))));
		assertThat(Integer.valueOf(Objects.hashCodeOf(new SomeObject("Kevin"))),
				is(equalTo(Integer.valueOf(new SomeObject("Kevin").hashCode()))));
	}

	@Test
	public final void testHash()
	{
		assertThat(Integer.valueOf(Objects.hash((Object[]) null)),
				equalTo(Integer.valueOf(Arrays.hashCode((Object[]) null))));
		assertThat(Integer.valueOf(Objects.hash((Object) null)),
				equalTo(Integer.valueOf(Arrays.hashCode(new Object[] { null }))));
		assertThat(Integer.valueOf(Objects.hash("test")),
				equalTo(Integer.valueOf(Arrays.hashCode(new String[] { "test" }))));
		assertThat(Integer.valueOf(Objects.hash(new String("test"))),
				equalTo(Integer.valueOf(Arrays.hashCode(new String[] { new String("test") }))));
		assertThat(Integer.valueOf(Objects.hash(new SomeObject("Kevin"))),
				equalTo(Integer.valueOf(Arrays.hashCode(new SomeObject[] { new SomeObject("Kevin") }))));
		assertThat(
				Integer.valueOf(Objects.hash("test", new String("something else"), new SomeObject("Kevin"))),
				equalTo(Integer.valueOf(Arrays.hashCode(new Object[] { "test", new String("something else"),
						new SomeObject("Kevin") }))));
	}

	@Test
	public final void testHashes()
	{
		int result = Objects.HASH_PRIME * Objects.HASH_SEED + 1;
		result = Objects.HASH_PRIME * result + 120;
		result = Objects.HASH_PRIME * result + 12345;
		result = Objects.HASH_PRIME * result + (int) (9999L ^ (9999L >>> 32));
		result = Objects.HASH_PRIME * result + Float.floatToIntBits(12.10F);
		long bits = Double.doubleToLongBits(100.12345D);
		result = Objects.HASH_PRIME * result + (int) (bits ^ (bits >>> 32));
		result = Objects.HASH_PRIME * result + 1231;
		result = Objects.HASH_PRIME * result + "Kevin Lee".hashCode();

		/* @formatter:off */
					assertThat(Integer.valueOf(
							Objects.hash(
							Objects.hash(
							Objects.hash(
							Objects.hash(
							Objects.hash(
							Objects.hash(
							Objects.hash(
							Objects.hash(
							Objects.HASH_SEED, 
							(byte) 1), 
							(short) 120), 
							12345), 
							9999L), 
							12.10F), 
							100.12345D), 
							true), 
							"Kevin Lee")), equalTo(Integer.valueOf(result)));
					assertThat(Integer.valueOf(
							Objects.hash(
							Objects.hash(
							Objects.hash(
							Objects.hash(
							Objects.hash(
							Objects.hash(
							Objects.hash(
							Objects.hash(
							(byte) 1), 
							(short) 120), 
							12345), 
							9999L), 
							12.10F), 
							100.12345D), 
							true), 
							"Kevin Lee")), equalTo(Integer.valueOf(result)));
					/* @formatter:on */
	}

	@Test
	public final void testHashByte()
	{
		final int seed = 3;
		final byte value = 5;
		assertThat(Integer.valueOf(Objects.hash(value)),
				is(equalTo(Integer.valueOf(Objects.HASH_PRIME * Objects.HASH_SEED + value))));
		assertThat(Integer.valueOf(Objects.hash(3, value)), equalTo(Integer.valueOf(Objects.HASH_PRIME * seed + value)));

		final byte[] values = { value, 6, 8, 10, 123 };
		int result = Objects.HASH_PRIME * seed + values[0];
		result = Objects.HASH_PRIME * result + values[1];
		result = Objects.HASH_PRIME * result + values[2];
		result = Objects.HASH_PRIME * result + values[3];
		result = Objects.HASH_PRIME * result + values[4];
		assertThat(Integer.valueOf(Objects.hashBytes(3, values[0], values[1], values[2], values[3], values[4])),
				equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(3, values)), equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(3, (byte[]) null)), equalTo(Integer.valueOf(0)));
	}

	@Test
	public final void testHashChar()
	{
		final int seed = 3;
		final char value = Character.MAX_VALUE - 100;
		assertThat(Integer.valueOf(Objects.hash(value)),
				is(equalTo(Integer.valueOf(Objects.HASH_PRIME * Objects.HASH_SEED + value))));
		assertThat(Integer.valueOf(Objects.hash(3, value)), equalTo(Integer.valueOf(Objects.HASH_PRIME * seed + value)));

		final char[] values = { value, 6, 8, 10, 123 };
		int result = Objects.HASH_PRIME * seed + values[0];
		result = Objects.HASH_PRIME * result + values[1];
		result = Objects.HASH_PRIME * result + values[2];
		result = Objects.HASH_PRIME * result + values[3];
		result = Objects.HASH_PRIME * result + values[4];
		assertThat(Integer.valueOf(Objects.hashChars(seed, values[0], values[1], values[2], values[3], values[4])),
				equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(seed, values)), equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(seed, (char[]) null)), equalTo(Integer.valueOf(0)));
	}

	@Test
	public final void testHashShort()
	{
		final int seed = 3;
		final short value = Byte.MAX_VALUE + 100;
		assertThat(Integer.valueOf(Objects.hash(value)),
				is(equalTo(Integer.valueOf(Objects.HASH_PRIME * Objects.HASH_SEED + value))));
		assertThat(Integer.valueOf(Objects.hash(3, value)), equalTo(Integer.valueOf(Objects.HASH_PRIME * seed + value)));

		final short[] values = { value, 6, 8, 10, 123 };
		int result = Objects.HASH_PRIME * seed + values[0];
		result = Objects.HASH_PRIME * result + values[1];
		result = Objects.HASH_PRIME * result + values[2];
		result = Objects.HASH_PRIME * result + values[3];
		result = Objects.HASH_PRIME * result + values[4];
		assertThat(Integer.valueOf(Objects.hashShorts(seed, values[0], values[1], values[2], values[3], values[4])),
				equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(seed, values)), equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(seed, (short[]) null)), equalTo(Integer.valueOf(0)));
	}

	@Test
	public final void testHashInt()
	{
		final int seed = 3;
		final int value = Short.MAX_VALUE + 100;
		assertThat(Integer.valueOf(Objects.hash(value)),
				is(equalTo(Integer.valueOf(Objects.HASH_PRIME * Objects.HASH_SEED + value))));
		assertThat(Integer.valueOf(Objects.hash(3, value)),
				is(equalTo(Integer.valueOf(Objects.HASH_PRIME * seed + value))));

		final int[] values = { value, 6, 8, 10, 123 };
		int result = Objects.HASH_PRIME * seed + values[0];
		result = Objects.HASH_PRIME * result + values[1];
		result = Objects.HASH_PRIME * result + values[2];
		result = Objects.HASH_PRIME * result + values[3];
		result = Objects.HASH_PRIME * result + values[4];
		assertThat(Integer.valueOf(Objects.hashInts(3, values[0], values[1], values[2], values[3], values[4])),
				equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(3, values)), equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(3, (int[]) null)), equalTo(Integer.valueOf(0)));
	}

	@Test
	public final void testHashLong()
	{
		final int seed = 3;
		final long value = Integer.MAX_VALUE + 100L;
		assertThat(Integer.valueOf(Objects.hash(value)),
				is(equalTo(Integer.valueOf(Objects.HASH_PRIME * Objects.HASH_SEED + (int) (value ^ (value >>> 32))))));
		assertThat(Integer.valueOf(Objects.hash(3, value)),
				equalTo(Integer.valueOf(Objects.HASH_PRIME * seed + (int) (value ^ (value >>> 32)))));

		final long[] values = { 5, 6, 8, 10, 123 };
		int result = Objects.HASH_PRIME * seed + (int) (values[0] ^ (values[0] >>> 32));
		result = Objects.HASH_PRIME * result + (int) (values[1] ^ (values[1] >>> 32));
		result = Objects.HASH_PRIME * result + (int) (values[2] ^ (values[2] >>> 32));
		result = Objects.HASH_PRIME * result + (int) (values[3] ^ (values[3] >>> 32));
		result = Objects.HASH_PRIME * result + (int) (values[4] ^ (values[4] >>> 32));
		assertThat(Integer.valueOf(Objects.hashLongs(3, values[0], values[1], values[2], values[3], values[4])),
				equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(3, values)), equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(3, (long[]) null)), equalTo(Integer.valueOf(0)));
	}

	@Test
	public final void testHashFloat()
	{
		final int seed = 3;
		final float value = 5.0F;
		assertThat(Integer.valueOf(Objects.hash(value)),
				is(equalTo(Integer.valueOf(Objects.HASH_PRIME * Objects.HASH_SEED + Float.floatToIntBits(value)))));
		assertThat(Integer.valueOf(Objects.hash(3, value)),
				equalTo(Integer.valueOf(Objects.HASH_PRIME * seed + Float.floatToIntBits(value))));

		final float[] values = { 5, 6, 8, 10, 123 };
		int result = Objects.HASH_PRIME * seed + Float.floatToIntBits(values[0]);
		result = Objects.HASH_PRIME * result + Float.floatToIntBits(values[1]);
		result = Objects.HASH_PRIME * result + Float.floatToIntBits(values[2]);
		result = Objects.HASH_PRIME * result + Float.floatToIntBits(values[3]);
		result = Objects.HASH_PRIME * result + Float.floatToIntBits(values[4]);
		assertThat(Integer.valueOf(Objects.hashFloats(3, values[0], values[1], values[2], values[3], values[4])),
				equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(3, values)), equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(3, (float[]) null)), equalTo(Integer.valueOf(0)));
	}

	@Test
	public final void testHashDouble()
	{
		final double value = Float.MAX_VALUE + 100D;
		long doubleToLongBits = Double.doubleToLongBits(value);
		final int seed = 3;
		assertThat(
				Integer.valueOf(Objects.hash(value)),
				is(equalTo(Integer.valueOf(Objects.HASH_PRIME * Objects.HASH_SEED
						+ (int) (doubleToLongBits ^ (doubleToLongBits >>> 32))))));
		assertThat(
				Integer.valueOf(Objects.hash(3, value)),
				equalTo(Integer.valueOf(Objects.HASH_PRIME * seed
						+ (int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))));

		final double[] values = { value, 6, 8, 10, 123 };
		doubleToLongBits = Double.doubleToLongBits(values[0]);
		int result = Objects.HASH_PRIME * seed + (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
		doubleToLongBits = Double.doubleToLongBits(values[1]);
		result = Objects.HASH_PRIME * result + (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
		doubleToLongBits = Double.doubleToLongBits(values[2]);
		result = Objects.HASH_PRIME * result + (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
		doubleToLongBits = Double.doubleToLongBits(values[3]);
		result = Objects.HASH_PRIME * result + (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
		doubleToLongBits = Double.doubleToLongBits(values[4]);
		result = Objects.HASH_PRIME * result + (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
		assertThat(Integer.valueOf(Objects.hashDoubles(seed, values[0], values[1], values[2], values[3], values[4])),
				equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(seed, values)), equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(seed, (double[]) null)), equalTo(Integer.valueOf(0)));
	}

	@Test
	public final void testHashBoolean()
	{
		final int seed = 3;
		assertThat(Integer.valueOf(Objects.hash(true)),
				is(equalTo(Integer.valueOf(Objects.HASH_PRIME * Objects.HASH_SEED + Boolean.TRUE.hashCode()))));
		assertThat(Integer.valueOf(Objects.hash(3, true)),
				equalTo(Integer.valueOf(Objects.HASH_PRIME * seed + Boolean.TRUE.hashCode())));

		final boolean[] values = { false, true, true, false, true };
		int result = Objects.HASH_PRIME * seed + (values[0] ? 1231 : 1237);
		result = Objects.HASH_PRIME * result + (values[1] ? 1231 : 1237);
		result = Objects.HASH_PRIME * result + (values[2] ? 1231 : 1237);
		result = Objects.HASH_PRIME * result + (values[3] ? 1231 : 1237);
		result = Objects.HASH_PRIME * result + (values[4] ? 1231 : 1237);
		assertThat(Integer.valueOf(Objects.hashBooleans(3, values[0], values[1], values[2], values[3], values[4])),
				equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(3, values)), equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(3, (boolean[]) null)), equalTo(Integer.valueOf(0)));
	}

	@Test
	public final void testHashObject()
	{
		final int seed = 3;
		final String value = "Kevin";
		assertThat(Integer.valueOf(Objects.hash(value)),
				is(equalTo(Integer.valueOf(Objects.HASH_PRIME * Objects.HASH_SEED + value.hashCode()))));
		assertThat(Integer.valueOf(Objects.hash(3, value)),
				equalTo(Integer.valueOf(Objects.HASH_PRIME * seed + value.hashCode())));

		final Object[] values = { value, "Lee", new Object(), new Date(), new ArrayList<String>() };
		int result = Objects.HASH_PRIME * seed + values[0].hashCode();
		result = Objects.HASH_PRIME * result + values[1].hashCode();
		result = Objects.HASH_PRIME * result + values[2].hashCode();
		result = Objects.HASH_PRIME * result + values[3].hashCode();
		result = Objects.HASH_PRIME * result + values[4].hashCode();
		assertThat(Integer.valueOf(Objects.hashObjects(3, values[0], values[1], values[2], values[3], values[4])),
				equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(3, values)), equalTo(Integer.valueOf(result)));
		assertThat(Integer.valueOf(Objects.hash(3, (Object[]) null)), equalTo(Integer.valueOf(0)));
	}

	@Test
	public final void testToStringOfObject()
	{
		assertThat(Objects.toStringOf(null), equalTo("null"));
		assertThat(Objects.toStringOf("test"), equalTo("test"));
		assertThat(Objects.toStringOf(new String("something else")), equalTo("something else"));
		Object object = new Object();
		assertThat(Objects.toStringOf(object), equalTo(object.toString()));
		assertThat(Objects.toStringOf(new SomeObject("Kevin")), equalTo(new SomeObject("Kevin").toString()));
		SomeObject someObject = new SomeObject("Lee");
		assertThat(Objects.toStringOf(someObject), equalTo(someObject.toString()));
	}

	@Test
	public final void testToStringOfObjectString()
	{
		final String nullDefault = "It is null!!!";
		assertThat(Objects.toStringOf(null, nullDefault), equalTo(nullDefault));
		assertThat(Objects.toStringOf("test", nullDefault), equalTo("test"));
		assertThat(Objects.toStringOf(new String("something else"), nullDefault), equalTo("something else"));
		Object object = new Object();
		assertThat(Objects.toStringOf(object, nullDefault), equalTo(object.toString()));
		assertThat(Objects.toStringOf(new SomeObject("Kevin"), nullDefault),
				equalTo(new SomeObject("Kevin").toString()));
		SomeObject someObject = new SomeObject("Lee");
		assertThat(Objects.toStringOf(someObject, nullDefault), equalTo(someObject.toString()));
	}

	@Test
	public final void testCompare()
	{
		final Comparator<SomeObject> comparator = new Comparator<SomeObject>() {
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

	@Test
	public final void testNotNullT()
	{
		assertThat(Objects.notNull("test"), equalTo("test"));
		assertThat(Objects.notNull(new String("something else")), is(equalTo(new String("something else"))));
		assertThat(Objects.notNull(new SomeObject("Kevin")), is(equalTo(new SomeObject("Kevin"))));
		boolean exceptionThrown = false;
		try
		{
			Objects.notNull(null);
		}
		catch (NullPointerException e)
		{
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	@Test
	public final void testNotNullTString()
	{
		final String message = "It is null!!!!!";
		assertThat(Objects.notNull("test", message), is(equalTo("test")));
		assertThat(Objects.notNull(new String("something else"), message), is(equalTo(new String("something else"))));
		assertThat(Objects.notNull(new SomeObject("Kevin"), message), is(equalTo(new SomeObject("Kevin"))));
		boolean exceptionThrown = false;
		try
		{
			Objects.notNull(null, message);
		}
		catch (NullPointerException e)
		{
			exceptionThrown = message.equals(e.getMessage());
		}
		assertTrue(exceptionThrown);
	}

	@Test
	public final void testNullThenUse()
	{
		final Object nullReference = null;
		final Object object1 = new Object();
		final Object object2 = new Object();
		final Object value1 = Objects.nullThenUse(nullReference, object1);
		final Object value2 = Objects.nullThenUse(object1, object2);
		assertThat(value1, is(equalTo(object1)));
		assertThat(value2, is(equalTo(object1)));

		final String string1 = new String("Kevin");
		final String string2 = new String("Lee");
		final String stringValue1 = Objects.nullThenUse((String) null, string1);
		final String stringValue2 = Objects.nullThenUse(string1, string2);
		assertThat(stringValue1, is(equalTo(string1)));
		assertThat(stringValue2, is(equalTo(string1)));

		final Date date1 = new Date();
		final Date date2 = new Date();
		final Date dateValue1 = Objects.nullThenUse((Date) null, date1);
		final Date dateValue2 = Objects.nullThenUse(date1, date2);
		assertThat(dateValue1, is(equalTo(date1)));
		assertThat(dateValue2, is(equalTo(date1)));

		final Integer integer1 = new Integer(100);
		final Integer integer2 = new Integer(999);
		final Integer integerValue1 = Objects.nullThenUse((Integer) null, integer1);
		final Integer integerValue2 = Objects.nullThenUse(integer1, integer2);
		assertThat(integerValue1, is(equalTo(integer1)));
		assertThat(integerValue2, is(equalTo(integer1)));
	}

	@Test
	public final void testIsNull()
	{
		assertTrue(Objects.isNull(null));
		final Object nullObject = null;
		assertTrue(Objects.isNull(nullObject));
		assertFalse(Objects.isNull(""));
		assertFalse(Objects.isNull("Kevin"));
		assertFalse(Objects.isNull(new String("test")));
		assertFalse(Objects.isNull(new Object()));
	}

	@Test
	public final void testIsNotNull()
	{
		assertFalse(Objects.isNotNull(null));
		final Object nullObject = null;
		assertFalse(Objects.isNotNull(nullObject));
		assertTrue(Objects.isNotNull(""));
		assertTrue(Objects.isNotNull("Kevin"));
		assertTrue(Objects.isNotNull(new String("test")));
		assertTrue(Objects.isNotNull(new Object()));
	}

	@Test
	public final void testIdentical()
	{
		final Object object1 = new Object();
		final Object object2 = object1;
		final Object object3 = new Object();
		assertTrue(Objects.identical(object1, object2));
		assertFalse(Objects.identical(object1, object3));

		assertTrue(Objects.identical("Kevin", "Kevin"));
		assertFalse(Objects.identical("Kevin", new String("Kevin")));
	}

	@Test
	public void testCastIfInstanceOf()
	{
		final Object object1 = "Kevin Lee";
		final String string1 = Objects.castIfInstanceOf(String.class, object1);
		assertThat(string1, is(notNullValue()));
		assertThat(string1, is(equalTo("Kevin Lee")));

		final Integer integer = new Integer(1234);
		final Object object2 = new Integer(1234);
		final Integer integer1 = Objects.castIfInstanceOf(Integer.class, object2);
		assertThat(integer1, is(notNullValue()));
		assertThat(integer1, is(equalTo(integer)));

		final Number number1 = Objects.castIfInstanceOf(Number.class, object2);
		assertThat(number1, is(notNullValue()));
		assertThat(number1, is(equalTo((Number) integer)));

		final Integer integer2 = Objects.castIfInstanceOf(Integer.class, object1);
		assertThat(integer2, is(nullValue()));

		final Number number2 = Objects.castIfInstanceOf(Number.class, object1);
		assertThat(number2, is(nullValue()));

		final Integer integer3 = Objects.castIfInstanceOf(Integer.class, new Number() {
			/* @formatter:off */
			private static final long serialVersionUID = 1L;
			@Override public long longValue() { return 0; }
			@Override public int intValue() { return 0; }
			@Override public float floatValue() { return 0; }
			@Override public double doubleValue() 	{ return 0; }
			/* @formatter:on */
		});
		assertThat(integer3, is(nullValue()));
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
