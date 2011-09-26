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
public class RuntimeUnsupportedEncodingExceptionTest
{
  /**
   * Test method for
   * {@link org.elixirian.kommonlee.io.exception.RuntimeUnsupportedEncodingException#ElixirianUnsupportedEncodingException()}
   * .
   */
  @Test
  public final void testElixirianUnsupportedEncodingException()
  {
    final Exception e = new RuntimeUnsupportedEncodingException();
    assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
    assertThat(e, is(instanceOf(RuntimeIoException.class)));
    assertThat(e, is(instanceOf(RuntimeUnsupportedEncodingException.class)));
    assertThat(e.getMessage(), is(nullValue()));
  }

  /**
   * Test method for
   * {@link org.elixirian.kommonlee.io.exception.RuntimeUnsupportedEncodingException#ElixirianUnsupportedEncodingException(java.lang.String, java.lang.Throwable)}
   * .
   */
  @Test
  public final void testElixirianUnsupportedEncodingExceptionStringThrowable()
  {
    final String message = "Test Exception";
    final Throwable throwable = new Throwable("Test Throwable");
    final Exception e = new RuntimeUnsupportedEncodingException(message, throwable);
    assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
    assertThat(e, is(instanceOf(RuntimeIoException.class)));
    assertThat(e, is(instanceOf(RuntimeUnsupportedEncodingException.class)));
    assertThat(e.getMessage(), is(equalTo(message)));
    assertThat(e.getMessage(), is(sameInstance(message)));
    assertThat(e.getCause(), is(equalTo(throwable)));
    assertThat(e.getCause(), is(sameInstance(throwable)));
  }

  /**
   * Test method for
   * {@link org.elixirian.kommonlee.io.exception.RuntimeUnsupportedEncodingException#ElixirianUnsupportedEncodingException(java.lang.String)}
   * .
   */
  @Test
  public final void testElixirianUnsupportedEncodingExceptionString()
  {
    final String message = "Test Exception";
    final Exception e = new RuntimeUnsupportedEncodingException(message);
    assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
    assertThat(e, is(instanceOf(RuntimeIoException.class)));
    assertThat(e, is(instanceOf(RuntimeUnsupportedEncodingException.class)));
    assertThat(e.getMessage(), is(equalTo(message)));
    assertThat(e.getMessage(), is(sameInstance(message)));
  }

  /**
   * Test method for
   * {@link org.elixirian.kommonlee.io.exception.RuntimeUnsupportedEncodingException#ElixirianUnsupportedEncodingException(java.lang.Throwable)}
   * .
   */
  @Test
  public final void testElixirianUnsupportedEncodingExceptionThrowable()
  {
    final Throwable throwable = new Throwable("Test Throwable");
    final Exception e = new RuntimeUnsupportedEncodingException(throwable);
    assertThat(e, is(instanceOf(ElixirianRuntimeException.class)));
    assertThat(e, is(instanceOf(RuntimeIoException.class)));
    assertThat(e, is(instanceOf(RuntimeUnsupportedEncodingException.class)));
    assertThat(e.getMessage(), is(equalTo("java.lang.Throwable: Test Throwable")));
    assertThat(e.getCause(), is(equalTo(throwable)));
    assertThat(e.getCause(), is(sameInstance(throwable)));
  }

}
