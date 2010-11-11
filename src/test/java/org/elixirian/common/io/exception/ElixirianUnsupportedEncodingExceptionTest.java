/**
 * 
 */
package org.elixirian.common.io.exception;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.elixirian.common.exception.ElixirianRuntimeException;
import org.elixirian.common.io.exception.ElixirianIoException;
import org.elixirian.common.io.exception.ElixirianUnsupportedEncodingException;
import org.junit.Test;


/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-02-24)
 */
public class ElixirianUnsupportedEncodingExceptionTest
{
	/**
	 * Test method for
	 * {@link org.elixirian.common.io.exception.ElixirianUnsupportedEncodingException#ElixirianUnsupportedEncodingException()}.
	 */
	@Test
	public final void testElixirianUnsupportedEncodingException()
	{
		final Exception e = new ElixirianUnsupportedEncodingException();
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(ElixirianIoException.class)));
		assertThat(e, is(instanceOf(ElixirianUnsupportedEncodingException.class)));
		assertThat(e.getMessage(), is(nullValue()));
	}

	/**
	 * Test method for
	 * {@link org.elixirian.common.io.exception.ElixirianUnsupportedEncodingException#ElixirianUnsupportedEncodingException(java.lang.String, java.lang.Throwable)}
	 * .
	 */
	@Test
	public final void testElixirianUnsupportedEncodingExceptionStringThrowable()
	{
		final String message = "Test Exception";
		final Throwable throwable = new Throwable("Test Throwable");
		final Exception e = new ElixirianUnsupportedEncodingException(message, throwable);
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(ElixirianIoException.class)));
		assertThat(e, is(instanceOf(ElixirianUnsupportedEncodingException.class)));
		assertThat(e.getMessage(), is(equalTo(message)));
		assertThat(e.getMessage(), is(sameInstance(message)));
		assertThat(e.getCause(), is(equalTo(throwable)));
		assertThat(e.getCause(), is(sameInstance(throwable)));
	}

	/**
	 * Test method for
	 * {@link org.elixirian.common.io.exception.ElixirianUnsupportedEncodingException#ElixirianUnsupportedEncodingException(java.lang.String)}
	 * .
	 */
	@Test
	public final void testElixirianUnsupportedEncodingExceptionString()
	{
		final String message = "Test Exception";
		final Exception e = new ElixirianUnsupportedEncodingException(message);
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(ElixirianIoException.class)));
		assertThat(e, is(instanceOf(ElixirianUnsupportedEncodingException.class)));
		assertThat(e.getMessage(), is(equalTo(message)));
		assertThat(e.getMessage(), is(sameInstance(message)));
	}

	/**
	 * Test method for
	 * {@link org.elixirian.common.io.exception.ElixirianUnsupportedEncodingException#ElixirianUnsupportedEncodingException(java.lang.Throwable)}
	 * .
	 */
	@Test
	public final void testElixirianUnsupportedEncodingExceptionThrowable()
	{
		final Throwable throwable = new Throwable("Test Throwable");
		final Exception e = new ElixirianUnsupportedEncodingException(throwable);
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(ElixirianIoException.class)));
		assertThat(e, is(instanceOf(ElixirianUnsupportedEncodingException.class)));
		assertThat(e.getMessage(), is(equalTo("java.lang.Throwable: Test Throwable")));
		assertThat(e.getCause(), is(equalTo(throwable)));
		assertThat(e.getCause(), is(sameInstance(throwable)));
	}

}
