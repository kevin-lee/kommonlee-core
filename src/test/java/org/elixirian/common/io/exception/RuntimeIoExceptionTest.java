/**
 * 
 */
package org.elixirian.common.io.exception;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.elixirian.common.exception.ElixirianRuntimeException;
import org.elixirian.common.io.exception.RuntimeIoException;
import org.junit.Test;

/**
 * <pre>
 *     ____________    ___________  ____   _______ _________ _______ _______________  ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /     \/   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /          /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-02-24)
 */
public class RuntimeIoExceptionTest
{
	/**
	 * Test method for {@link org.elixirian.common.io.exception.RuntimeIoException#ElixirianIoException()}.
	 */
	@Test
	public final void testElixirianIoException()
	{
		final Exception e = new RuntimeIoException();
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(RuntimeIoException.class)));
		assertThat(e.getMessage(), is(nullValue()));
	}

	/**
	 * Test method for
	 * {@link org.elixirian.common.io.exception.RuntimeIoException#ElixirianIoException(java.lang.String, java.lang.Throwable)}
	 * .
	 */
	@Test
	public final void testElixirianIoExceptionStringThrowable()
	{
		final String message = "Test Exception";
		final Throwable throwable = new Throwable("Test Throwable");
		final Exception e = new RuntimeIoException(message, throwable);
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(RuntimeIoException.class)));
		assertThat(e.getMessage(), is(equalTo(message)));
		assertThat(e.getMessage(), is(sameInstance(message)));
		assertThat(e.getCause(), is(equalTo(throwable)));
		assertThat(e.getCause(), is(sameInstance(throwable)));
	}

	/**
	 * Test method for {@link org.elixirian.common.io.exception.RuntimeIoException#ElixirianIoException(java.lang.String)}
	 * .
	 */
	@Test
	public final void testElixirianIoExceptionString()
	{
		final String message = "Test Exception";
		final Exception e = new RuntimeIoException(message);
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(RuntimeIoException.class)));
		assertThat(e.getMessage(), is(equalTo(message)));
		assertThat(e.getMessage(), is(sameInstance(message)));
	}

	/**
	 * Test method for
	 * {@link org.elixirian.common.io.exception.RuntimeIoException#ElixirianIoException(java.lang.Throwable)}.
	 */
	@Test
	public final void testElixirianIoExceptionThrowable()
	{
		final Throwable throwable = new Throwable("Test Throwable");
		final Exception e = new RuntimeIoException(throwable);
		assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
		assertThat(e, is(instanceOf(RuntimeIoException.class)));
		assertThat(e.getMessage(), is(equalTo("java.lang.Throwable: Test Throwable")));
		assertThat(e.getCause(), is(equalTo(throwable)));
		assertThat(e.getCause(), is(sameInstance(throwable)));
	}

}
