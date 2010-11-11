/**
 * 
 */
package org.elixirian.common.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.elixirian.common.util.MapToAppendableGlue;
import org.elixirian.common.util.SimpleAppendingAction.AppendingActionWithSeparator;
import org.elixirian.common.util.SimpleAppendingAction.AppendingActionWithoutSeparator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-06)
 */
public class MapToAppendableGlueTest
{
	private static final Map<String, Object> map = new LinkedHashMap<String, Object>();
	private static final String id = "id";
	private static final String name = "name";
	private static final String number = "number";
	private static final String address = "address";
	private static final Long idValue = Long.valueOf(999L);
	private static final String nameValue = "Kevin";
	private static final Integer numberValue = Integer.valueOf(1);
	private static final String addressValue = "123 ABC Street";
	private static final String keyValueSeparator = ":";
	private static final String entrySeparator = ", ";
	private static final String expected = id + keyValueSeparator + idValue + entrySeparator + name + keyValueSeparator
			+ nameValue + entrySeparator + number + keyValueSeparator + numberValue + entrySeparator + address
			+ keyValueSeparator + addressValue;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		map.put(id, idValue);
		map.put(name, nameValue);
		map.put(number, numberValue);
		map.put(address, addressValue);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * Test method for {@link org.elixirian.common.util.MapToAppendableGlue#glue(java.lang.Appendable, java.util.Map)}.
	 */
	@Test
	public final void testGlue()
	{
		final StringBuilder stringBuilder = new StringBuilder();
		final MapToAppendableGlue mapToAppendableGlue =
			MapToAppendableGlue.newMapToAppendableGlue(keyValueSeparator, entrySeparator);
		final StringBuilder returnedStringBuilder = mapToAppendableGlue.glue(stringBuilder, map);
		assertThat(returnedStringBuilder, is(stringBuilder));
		assertThat(returnedStringBuilder.toString(), equalTo(expected));
	}

	/**
	 * Test method for {@link org.elixirian.common.util.MapToAppendableGlue#glue(java.lang.Appendable, java.util.Map)}.
	 */
	@Test(expected = NullPointerException.class)
	public final void testGlueNullAppendable()
	{
		MapToAppendableGlue.newMapToAppendableGlue(keyValueSeparator, entrySeparator)
				.glue(null, map);
	}

	/**
	 * Test method for {@link org.elixirian.common.util.MapToAppendableGlue#glue(java.lang.Appendable, java.util.Map)}.
	 */
	@Test(expected = NullPointerException.class)
	public final void testGlueNullMap()
	{
		MapToAppendableGlue.newMapToAppendableGlue(keyValueSeparator, entrySeparator)
				.glue(new StringBuilder(), null);
	}

	/**
	 * Test method for {@link org.elixirian.common.util.MapToAppendableGlue#glue(java.lang.Appendable, java.util.Map)}.
	 */
	@Test(expected = AssertionError.class)
	public final void testGlueThrowingIllegalArgumentException()
	{
		final String message = "IOException for testing!";
		final Appendable appendable = new Appendable()
		{

			@SuppressWarnings("unused")
			@Override
			public Appendable append(CharSequence csq, int start, int end) throws IOException
			{
				throw new UnsupportedOperationException();
			}

			@Override
			public Appendable append(@SuppressWarnings("unused") char c) throws IOException
			{
				throw new UnsupportedOperationException();
			}

			@SuppressWarnings("unused")
			@Override
			public Appendable append(CharSequence csq) throws IOException
			{
				throw new IOException(message);
			}
		};

		try
		{
			MapToAppendableGlue.newMapToAppendableGlue(":", ", ")
					.glue(appendable, map);
		}
		catch (AssertionError e)
		{
			assertThat(e.getCause(), is(instanceOf(IOException.class)));
			assertThat(e.getCause()
					.getMessage(), equalTo(message));
			throw e;
		}
	}

	/**
	 * Test method for {@link org.elixirian.common.util.MapToAppendableGlue#newMapToAppendableGlue(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testNewMapToAppendableGlue()
	{
		MapToAppendableGlue mapToAppendableGlue =
			MapToAppendableGlue.newMapToAppendableGlue(keyValueSeparator, entrySeparator);
		assertThat(mapToAppendableGlue.getKeyValueGlue(), is(instanceOf(AppendingActionWithSeparator.class)));
		assertThat(mapToAppendableGlue.getEntryGlue(), is(instanceOf(AppendingActionWithSeparator.class)));

		mapToAppendableGlue = MapToAppendableGlue.newMapToAppendableGlue("", entrySeparator);
		assertThat(mapToAppendableGlue.getKeyValueGlue(), is(instanceOf(AppendingActionWithoutSeparator.class)));
		assertThat(mapToAppendableGlue.getEntryGlue(), is(instanceOf(AppendingActionWithSeparator.class)));

		mapToAppendableGlue = MapToAppendableGlue.newMapToAppendableGlue(keyValueSeparator, "");
		assertThat(mapToAppendableGlue.getKeyValueGlue(), is(instanceOf(AppendingActionWithSeparator.class)));
		assertThat(mapToAppendableGlue.getEntryGlue(), is(instanceOf(AppendingActionWithoutSeparator.class)));

		mapToAppendableGlue = MapToAppendableGlue.newMapToAppendableGlue("", "");
		assertThat(mapToAppendableGlue.getKeyValueGlue(), is(instanceOf(AppendingActionWithoutSeparator.class)));
		assertThat(mapToAppendableGlue.getEntryGlue(), is(instanceOf(AppendingActionWithoutSeparator.class)));
	}

	/**
	 * Test method for {@link org.elixirian.common.util.MapToAppendableGlue#newMapToAppendableGlue(java.lang.String, java.lang.String)}.
	 */
	@Test(expected = NullPointerException.class)
	public final void testNewMapToAppendableGlueWithNullKeyValueSeparator()
	{
		MapToAppendableGlue.newMapToAppendableGlue(null, "");
	}

	/**
	 * Test method for {@link org.elixirian.common.util.MapToAppendableGlue#newMapToAppendableGlue(java.lang.String, java.lang.String)}.
	 */
	@Test(expected = NullPointerException.class)
	public final void testNewMapToAppendableGlueWithNullEntrySeparator()
	{
		MapToAppendableGlue.newMapToAppendableGlue("", null);
	}

}
