/**
 * 
 */
package org.elixirian.common.io.exception;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.elixirian.common.exception.ElixirianRuntimeException;
import org.elixirian.common.io.exception.ElixirianFileNotFoundException;
import org.elixirian.common.io.exception.ElixirianIoException;
import org.junit.Test;


/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-02-24)
 */
public class ElixirianFileNotFoundExceptionTest
{
	/**
	 * Test method for {@link org.elixirian.common.io.exception.ElixirianFileNotFoundException#ElixirianFileNotFoundException()}.
	 */
	@Test
	public final void testElixirianFileNotFoundException()
	{
		final Exception e = new ElixirianFileNotFoundException();
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(ElixirianIoException.class)));
		assertThat(e, is(instanceOf(ElixirianFileNotFoundException.class)));
		assertThat(e.getMessage(), is(nullValue()));
	}

	/**
	 * Test method for
	 * {@link org.elixirian.common.io.exception.ElixirianFileNotFoundException#ElixirianFileNotFoundException(java.lang.String, java.lang.Throwable)}
	 * .
	 */
	@Test
	public final void testElixirianFileNotFoundExceptionStringThrowable()
	{
		final String message = "Test Exception";
		final Throwable throwable = new Throwable("Test Throwable");
		final Exception e = new ElixirianFileNotFoundException(message, throwable);
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(ElixirianIoException.class)));
		assertThat(e, is(instanceOf(ElixirianFileNotFoundException.class)));
		assertThat(e.getMessage(), is(equalTo(message)));
		assertThat(e.getMessage(), is(sameInstance(message)));
		assertThat(e.getCause(), is(equalTo(throwable)));
		assertThat(e.getCause(), is(sameInstance(throwable)));
	}

	/**
	 * Test method for
	 * {@link org.elixirian.common.io.exception.ElixirianFileNotFoundException#ElixirianFileNotFoundException(java.lang.String)}.
	 */
	@Test
	public final void testElixirianFileNotFoundExceptionString()
	{
		final String message = "Test Exception";
		final Exception e = new ElixirianFileNotFoundException(message);
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(ElixirianIoException.class)));
		assertThat(e, is(instanceOf(ElixirianFileNotFoundException.class)));
		assertThat(e.getMessage(), is(equalTo(message)));
		assertThat(e.getMessage(), is(sameInstance(message)));
	}

	/**
	 * Test method for
	 * {@link org.elixirian.common.io.exception.ElixirianFileNotFoundException#ElixirianFileNotFoundException(java.lang.Throwable)}
	 * .
	 */
	@Test
	public final void testElixirianFileNotFoundExceptionThrowable()
	{
		final Throwable throwable = new Throwable("Test Throwable");
		final Exception e = new ElixirianFileNotFoundException(throwable);
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(ElixirianIoException.class)));
		assertThat(e, is(instanceOf(ElixirianFileNotFoundException.class)));
		assertThat(e.getMessage(), is(equalTo("java.lang.Throwable: Test Throwable")));
		assertThat(e.getCause(), is(equalTo(throwable)));
		assertThat(e.getCause(), is(sameInstance(throwable)));
	}

}
