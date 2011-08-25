/**
 * 
 */
package org.elixirian.kommonlee.io.exception;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.elixirian.kommonlee.exception.ElixirianRuntimeException;
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
public class RuntimeFileNotFoundExceptionTest
{
  /**
   * Test method for
   * {@link org.elixirian.kommonlee.io.exception.RuntimeFileNotFoundException#ElixirianFileNotFoundException()}.
   */
  @Test
  public final void testElixirianFileNotFoundException()
  {
    final Exception e = new RuntimeFileNotFoundException();
    assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
    assertThat(e, is(instanceOf(RuntimeIoException.class)));
    assertThat(e, is(instanceOf(RuntimeFileNotFoundException.class)));
    assertThat(e.getMessage(), is(nullValue()));
  }

  /**
   * Test method for
   * {@link org.elixirian.kommonlee.io.exception.RuntimeFileNotFoundException#ElixirianFileNotFoundException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public final void testElixirianFileNotFoundExceptionStringThrowable()
  {
    final String message = "Test Exception";
    final Throwable throwable = new Throwable("Test Throwable");
    final Exception e = new RuntimeFileNotFoundException(message, throwable);
    assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
    assertThat(e, is(instanceOf(RuntimeIoException.class)));
    assertThat(e, is(instanceOf(RuntimeFileNotFoundException.class)));
    assertThat(e.getMessage(), is(equalTo(message)));
    assertThat(e.getMessage(), is(sameInstance(message)));
    assertThat(e.getCause(), is(equalTo(throwable)));
    assertThat(e.getCause(), is(sameInstance(throwable)));
  }

  /**
   * Test method for
   * {@link org.elixirian.kommonlee.io.exception.RuntimeFileNotFoundException#ElixirianFileNotFoundException(java.lang.String)}
   * .
   */
  @Test
  public final void testElixirianFileNotFoundExceptionString()
  {
    final String message = "Test Exception";
    final Exception e = new RuntimeFileNotFoundException(message);
    assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
    assertThat(e, is(instanceOf(RuntimeIoException.class)));
    assertThat(e, is(instanceOf(RuntimeFileNotFoundException.class)));
    assertThat(e.getMessage(), is(equalTo(message)));
    assertThat(e.getMessage(), is(sameInstance(message)));
  }

  /**
   * Test method for
   * {@link org.elixirian.kommonlee.io.exception.RuntimeFileNotFoundException#ElixirianFileNotFoundException(java.lang.Throwable)}
   * .
   */
  @Test
  public final void testElixirianFileNotFoundExceptionThrowable()
  {
    final Throwable throwable = new Throwable("Test Throwable");
    final Exception e = new RuntimeFileNotFoundException(throwable);
    assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
    assertThat(e, is(instanceOf(RuntimeIoException.class)));
    assertThat(e, is(instanceOf(RuntimeFileNotFoundException.class)));
    assertThat(e.getMessage(), is(equalTo("java.lang.Throwable: Test Throwable")));
    assertThat(e.getCause(), is(equalTo(throwable)));
    assertThat(e.getCause(), is(sameInstance(throwable)));
  }

}
