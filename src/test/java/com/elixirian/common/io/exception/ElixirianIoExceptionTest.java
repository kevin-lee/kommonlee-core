/**
 * 
 */
package com.elixirian.common.io.exception;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.elixirian.common.exception.ElixirianRuntimeException;
import com.elixirian.common.io.exception.ElixirianIoException;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-02-24)
 */
public class ElixirianIoExceptionTest
{
	/**
	 * Test method for {@link com.elixirian.common.io.exception.ElixirianIoException#ElixirianIoException()}.
	 */
	@Test
	public final void testElixirianIoException()
	{
		final Exception e = new ElixirianIoException();
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(ElixirianIoException.class)));
		assertThat(e.getMessage(), is(nullValue()));
	}

	/**
	 * Test method for
	 * {@link com.elixirian.common.io.exception.ElixirianIoException#ElixirianIoException(java.lang.String, java.lang.Throwable)}.
	 */
	@Test
	public final void testElixirianIoExceptionStringThrowable()
	{
		final String message = "Test Exception";
		final Throwable throwable = new Throwable("Test Throwable");
		final Exception e = new ElixirianIoException(message, throwable);
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(ElixirianIoException.class)));
		assertThat(e.getMessage(), is(equalTo(message)));
		assertThat(e.getMessage(), is(sameInstance(message)));
		assertThat(e.getCause(), is(equalTo(throwable)));
		assertThat(e.getCause(), is(sameInstance(throwable)));
	}

	/**
	 * Test method for {@link com.elixirian.common.io.exception.ElixirianIoException#ElixirianIoException(java.lang.String)}.
	 */
	@Test
	public final void testElixirianIoExceptionString()
	{
		final String message = "Test Exception";
		final Exception e = new ElixirianIoException(message);
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(ElixirianIoException.class)));
		assertThat(e.getMessage(), is(equalTo(message)));
		assertThat(e.getMessage(), is(sameInstance(message)));
	}

	/**
	 * Test method for {@link com.elixirian.common.io.exception.ElixirianIoException#ElixirianIoException(java.lang.Throwable)}.
	 */
	@Test
	public final void testElixirianIoExceptionThrowable()
	{
		final Throwable throwable = new Throwable("Test Throwable");
		final Exception e = new ElixirianIoException(throwable);
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(ElixirianIoException.class)));
		assertThat(e.getMessage(), is(equalTo("java.lang.Throwable: Test Throwable")));
		assertThat(e.getCause(), is(equalTo(throwable)));
		assertThat(e.getCause(), is(sameInstance(throwable)));
	}

}
