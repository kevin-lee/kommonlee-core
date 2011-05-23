/**
 * 
 */
package org.elixirian.common.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.elixirian.common.test.CauseCheckableExpectedException;
import org.elixirian.common.util.SimpleAppendingAction.AppendingActionWithSeparator;
import org.elixirian.common.util.SimpleAppendingAction.AppendingActionWithoutSeparator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-06)
 */
public class MapToAppendableGlueTest
{
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
	private static final Map<String, Object> map = initMap();

	@Rule
	public CauseCheckableExpectedException expectedException = CauseCheckableExpectedException.none();

	private static Map<String, Object> initMap()
	{
		final Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put(id, idValue);
		map.put(name, nameValue);
		map.put(number, numberValue);
		map.put(address, addressValue);
		return Collections.unmodifiableMap(map);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
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

	@Test
	public final void testGlue()
	{
		final StringBuilder stringBuilder = new StringBuilder();
		final MapToAppendableGlue mapToAppendableGlue =
			MapToAppendableGlue.newMapToAppendableGlue(keyValueSeparator, entrySeparator);
		final StringBuilder returnedStringBuilder = mapToAppendableGlue.glue(stringBuilder, map);
		assertThat(returnedStringBuilder, is(equalTo(stringBuilder)));
		assertThat(returnedStringBuilder.toString(), is(equalTo(expected)));
	}

	@Test(expected = NullPointerException.class)
	public final void testGlueNullAppendable()
	{
		MapToAppendableGlue.newMapToAppendableGlue(keyValueSeparator, entrySeparator)
				.glue(null, map);
	}

	@Test(expected = NullPointerException.class)
	public final void testGlueNullMap()
	{
		MapToAppendableGlue.newMapToAppendableGlue(keyValueSeparator, entrySeparator)
				.glue(new StringBuilder(), null);
	}

	@Test
	public final void testGlueThrowingIllegalArgumentException()
	{
		/* given */
		final String message = "IOException for testing!";
		final Appendable appendable = new Appendable() {

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

		/* expect */
		expectedException.expect(IllegalArgumentException.class)
				.expectCause(IOException.class)
				.expectCauseMessage(is(equalTo(message)));

		/* when / then the expected exception should be thrown */
		MapToAppendableGlue.newMapToAppendableGlue(":", ", ")
				.glue(appendable, map);

		/* otherwise */
		fail();
	}

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

	@Test(expected = NullPointerException.class)
	public final void testNewMapToAppendableGlueWithNullKeyValueSeparator()
	{
		MapToAppendableGlue.newMapToAppendableGlue(null, "");
	}

	@Test(expected = NullPointerException.class)
	public final void testNewMapToAppendableGlueWithNullEntrySeparator()
	{
		MapToAppendableGlue.newMapToAppendableGlue("", null);
	}

}
