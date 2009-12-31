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
 * @version 0.01 (2009 12 29)
 */
public class ValidateItTest
{

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
	public final void testValidateIt() throws Exception
	{
		Constructor<ValidateIt> constructor = ValidateIt.class.getDeclaredConstructor(new Class<?>[] {});
		assertThat("The no arg constuctor does not exist in " + ValidateIt.class.getName(), constructor, notNullValue());
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
	 * Test method for {@link com.elixirian.common.validation.ValidateIt#isEmpty(java.lang.String)}.
	 */
	@Test
	public final void testIsEmpty()
	{
		final String nullReference = null;
		final String emptyString = "";
		final String notEmpty = "something";

		assertThat(Boolean.valueOf(ValidateIt.isEmpty(nullReference)), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(ValidateIt.isEmpty(emptyString)), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(ValidateIt.isEmpty(notEmpty)), equalTo(Boolean.FALSE));
	}

	/**
	 * Test method for {@link com.elixirian.common.validation.ValidateIt#isNotEmpty(java.lang.String)}.
	 */
	@Test
	public final void testIsNotEmpty()
	{
		final String notEmpty = "something";
		final String nullReference = null;
		final String emptyString = "";

		assertThat(Boolean.valueOf(ValidateIt.isNotEmpty(notEmpty)), equalTo(Boolean.TRUE));
		assertThat(Boolean.valueOf(ValidateIt.isNotEmpty(nullReference)), equalTo(Boolean.FALSE));
		assertThat(Boolean.valueOf(ValidateIt.isNotEmpty(emptyString)), equalTo(Boolean.FALSE));
	}

}
