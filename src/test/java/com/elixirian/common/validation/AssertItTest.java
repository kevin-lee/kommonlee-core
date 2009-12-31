/**
 * 
 */
package com.elixirian.common.validation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.01 (2009-12-29)
 */
public class AssertItTest
{

	private static final String NO_EXCEPTION_SHOULD_BE_THROWN_HERE = "No exception should be thrown here!\n";

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

	@Test(expected = IllegalStateException.class)
	public final void testAssertIt() throws Exception
	{
		Constructor<AssertIt> constructor = AssertIt.class.getDeclaredConstructor(new Class<?>[] {});
		assertThat("The no arg constuctor does not exist in " + AssertIt.class.getName(), constructor, notNullValue());
		assertFalse(constructor.isAccessible());
		constructor.setAccessible(true);
		try
		{
			constructor.newInstance(new Object[] {});
		}
		catch (Exception e)
		{
			e = (Exception) e.getCause();
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Test method for {@link com.elixirian.common.validation.AssertIt#isNotNull(java.lang.Object, java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testIsNotNull()
	{
		try
		{
			AssertIt.isNotNull(new Object(), "It it null!!!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(NO_EXCEPTION_SHOULD_BE_THROWN_HERE + e.getMessage());
		}
		AssertIt.isNotNull(null, "It it null!!!");
	}

	/**
	 * Test method for {@link com.elixirian.common.validation.AssertIt#isNull(java.lang.Object, java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testIsNull()
	{
		try
		{
			AssertIt.isNull(null, "It is not null!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(NO_EXCEPTION_SHOULD_BE_THROWN_HERE + e.getMessage());
		}
		AssertIt.isNull(new Object(), "It is not null!");
	}

	/**
	 * Test method for {@link com.elixirian.common.validation.AssertIt#isNotEmpty(java.lang.String, java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testIsNotEmpty()
	{
		final String notEmpty = "something";
		final String nullReference = null;
		final String emptyString = "";

		try
		{
			AssertIt.isNotEmpty(notEmpty, "It is empty!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(NO_EXCEPTION_SHOULD_BE_THROWN_HERE + e.getMessage());
		}

		boolean isIllegalArgumentExceptionThrown = false;

		try
		{
			AssertIt.isNotEmpty(nullReference, "It is empty!");
		}
		catch (IllegalArgumentException e)
		{
			isIllegalArgumentExceptionThrown = true;
		}

		assertThat(Boolean.valueOf(isIllegalArgumentExceptionThrown), equalTo(Boolean.TRUE));

		AssertIt.isNotEmpty(emptyString, "It is empty!");
	}

	/**
	 * Test method for {@link com.elixirian.common.validation.AssertIt#isEmpty(java.lang.String, java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testIsEmpty()
	{
		final String nullReference = null;
		final String emptyString = "";
		final String notEmpty = "something";

		try
		{
			AssertIt.isEmpty(nullReference, "It is not empty!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(NO_EXCEPTION_SHOULD_BE_THROWN_HERE + e.getMessage());
		}

		try
		{
			AssertIt.isEmpty(emptyString, "It is not empty!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(NO_EXCEPTION_SHOULD_BE_THROWN_HERE + e.getMessage());
		}

		AssertIt.isEmpty(notEmpty, "It is not empty!");
	}

	/**
	 * Test method for {@link com.elixirian.common.validation.AssertIt#isTrue(boolean, java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testIsTrue()
	{
		try
		{
			AssertIt.isTrue(new Integer(2).equals(new Integer(2)), "It is not true");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(NO_EXCEPTION_SHOULD_BE_THROWN_HERE + e.getMessage());
		}

		AssertIt.isTrue(new Integer(3).equals(new Integer(1)), "It is not true");
	}
}
