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
 *     ___  _____                                              _____
 *    /   \/    / ______ __________________  ______ __ ______ /    /   ______  ______  
 *   /        / _/ __  // /  /   / /  /   /_/ __  // //     //    /   /  ___ \/  ___ \ 
 *  /        \ /  /_/ _/  _  _  /  _  _  //  /_/ _/   __   //    /___/  _____/  _____/
 * /____/\____\/_____//__//_//_/__//_//_/ /_____//___/ /__//________/\_____/ \_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____
 *    /   \/    /_________  ___ ____ __ ______
 *   /        / /  ___ \  \/  //___// //     /
 *  /        \ /  _____/\    //   //   __   /
 * /____/\____\\_____/   \__//___//___/ /__/
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
