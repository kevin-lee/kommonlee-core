/**
 * 
 */
package org.elixirian.common.validation;

import static org.elixirian.common.test.CommonTestHelper.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.elixirian.common.test.CommonTestHelper.Accessibility;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2009-12-29)
 */
public class AssertionsTest
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

	@Test(expected = IllegalAccessException.class)
	public final void testAssertions() throws Exception
	{
		testNotAccessibleConstructor(Assertions.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
	}

	/**
	 * Test method for
	 * {@link org.elixirian.common.validation.Assertions#assertNotNull(java.lang.Object, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testAssertNotNull()
	{
		try
		{
			Assertions.assertNotNull(new Object(), "It is null!!!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(NO_EXCEPTION_SHOULD_BE_THROWN_HERE + e.getMessage());
		}
		Assertions.assertNotNull(null, "It is null!!!");
	}

	/**
	 * Test method for
	 * {@link org.elixirian.common.validation.Assertions#assertNull(java.lang.Object, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testAssertNull()
	{
		try
		{
			Assertions.assertNull(null, "It is not null!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(NO_EXCEPTION_SHOULD_BE_THROWN_HERE + e.getMessage());
		}
		Assertions.assertNull(new Object(), "It is not null!");
	}

	/**
	 * Test method for
	 * {@link org.elixirian.common.validation.Assertions#assertNotEmpty(java.lang.String, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testAssertNotEmpty()
	{
		final String notEmpty = "something";
		final String nullReference = null;
		final String emptyString = "";

		try
		{
			Assertions.assertNotEmpty(notEmpty, "It is empty!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(NO_EXCEPTION_SHOULD_BE_THROWN_HERE + e.getMessage());
		}

		boolean isIllegalArgumentExceptionThrown = false;

		try
		{
			Assertions.assertNotEmpty(nullReference, "It is empty!");
		}
		catch (IllegalArgumentException e)
		{
			isIllegalArgumentExceptionThrown = true;
		}

		assertThat(Boolean.valueOf(isIllegalArgumentExceptionThrown), equalTo(Boolean.TRUE));

		Assertions.assertNotEmpty(emptyString, "It is empty!");
	}

	/**
	 * Test method for
	 * {@link org.elixirian.common.validation.Assertions#assertEmpty(java.lang.String, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testAssertEmpty()
	{
		final String nullReference = null;
		final String emptyString = "";
		final String notEmpty = "something";

		try
		{
			assertThat(Assertions.assertEmpty(nullReference, "It is not empty!"), is(""));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(NO_EXCEPTION_SHOULD_BE_THROWN_HERE + e.getMessage());
		}

		try
		{
			assertThat(Assertions.assertEmpty(emptyString, "It is not empty!"), is(""));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(NO_EXCEPTION_SHOULD_BE_THROWN_HERE + e.getMessage());
		}

		Assertions.assertEmpty(notEmpty, "It is not empty!");
	}

	/**
	 * Test method for
	 * {@link org.elixirian.common.validation.Assertions#assertTrue(boolean, java.lang.String, java.lang.Object[])}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testAssertTrue()
	{
		try
		{
			Assertions.assertTrue(new Integer(2).equals(new Integer(2)), "It is not true.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(NO_EXCEPTION_SHOULD_BE_THROWN_HERE + e.getMessage());
		}

		Assertions.assertTrue(new Integer(3).equals(new Integer(1)), "It is not true.");
	}

	/**
	 * Test method for
	 * {@link org.elixirian.common.validation.Assertions#assertTrue(boolean, java.lang.String, java.lang.Object[])}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testAssertFalse()
	{
		try
		{
			Assertions.assertFalse(new Integer(2).equals(new Integer(3)), "It is not false.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail(NO_EXCEPTION_SHOULD_BE_THROWN_HERE + e.getMessage());
		}

		Assertions.assertFalse(new Integer(5).equals(new Integer(5)), "It is not false.");
	}
}
