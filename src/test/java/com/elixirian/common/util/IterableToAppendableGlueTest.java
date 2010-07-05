/**
 * 
 */
package com.elixirian.common.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-05)
 */
public class IterableToAppendableGlueTest
{
	private static final String id = "ID: 1";
	private static final String name = "Name: Kevin";
	private static final String address = "Address: ABC Street";
	private static final String separator = ", ";
	private static final String expected = id + separator + name + separator + address;
	private static final List<String> stringList = new ArrayList<String>();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		stringList.add(id);
		stringList.add(name);
		stringList.add(address);
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
	 * Test method for {@link com.elixirian.common.util.IterableToAppendableGlue#newAppenderGlue(java.lang.String)}.
	 */
	@Test
	public final void testNewAppenderGlue()
	{
		IterableToAppendableGlue iterableToAppendableGlue = IterableToAppendableGlue.newAppenderGlue(null);
		assertThat(iterableToAppendableGlue, is(not(nullValue())));
		assertThat(iterableToAppendableGlue.getGlue(), is(instanceOf(AbstractAppendingAction.AppendingActionWithoutSeparator.class)));

		iterableToAppendableGlue = IterableToAppendableGlue.newAppenderGlue("");
		assertThat(iterableToAppendableGlue, is(not(nullValue())));
		assertThat(iterableToAppendableGlue.getGlue(), is(instanceOf(AbstractAppendingAction.AppendingActionWithoutSeparator.class)));
	}

	/**
	 * Test method for {@link com.elixirian.common.util.IterableToAppendableGlue#glue(java.lang.Appendable, java.lang.Iterable)}.
	 */
	@Test
	public final void testGlue()
	{
		final StringBuilder stringBuilder = new StringBuilder();
		final StringBuilder returnedStringBuilder = IterableToAppendableGlue.newAppenderGlue(separator)
				.glue(stringBuilder, stringList);
		assertThat(returnedStringBuilder, is(stringBuilder));
		assertThat(returnedStringBuilder, equalTo(stringBuilder));
		assertThat(returnedStringBuilder.toString(), equalTo(expected));
	}

	/**
	 * Test method for {@link com.elixirian.common.util.IterableToAppendableGlue#glue(java.lang.Appendable, java.lang.Iterable)}.
	 */
	@Test(expected = NullPointerException.class)
	public final void testGlueWithNullAppendable()
	{
		IterableToAppendableGlue.newAppenderGlue(separator)
				.glue(null, stringList);
	}

	/**
	 * Test method for {@link com.elixirian.common.util.IterableToAppendableGlue#glue(java.lang.Appendable, java.lang.Iterable)}.
	 */
	@Test(expected = NullPointerException.class)
	public final void testGlueWithNullIterable()
	{
		IterableToAppendableGlue.newAppenderGlue(separator)
				.glue(new StringBuilder(), null);
	}

	/**
	 * Test method for {@link com.elixirian.common.util.IterableToAppendableGlue#glue(java.lang.Appendable, java.lang.Iterable)}.
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
			IterableToAppendableGlue.newAppenderGlue(separator)
					.glue(appendable, stringList);
		}
		catch (AssertionError e)
		{
			assertThat(e.getCause(), is(instanceOf(IOException.class)));
			assertThat(e.getCause()
					.getMessage(), equalTo(message));
			throw e;
		}
	}

}
