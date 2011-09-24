/**
 * 
 */
package org.elixirian.kommonlee.io.exception;

import org.elixirian.kommonlee.exception.ElixirianRuntimeException;

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
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-01-09)
 */
public class RuntimeIoException extends ElixirianRuntimeException
{
  private static final long serialVersionUID = 4804330258609079383L;

  /**
	 * 
	 */
  public RuntimeIoException()
  {
    super();
  }

  /**
   * @param message
   * @param cause
   */
  public RuntimeIoException(final String message, final Throwable cause)
  {
    super(message, cause);
  }

  /**
   * @param message
   */
  public RuntimeIoException(final String message)
  {
    super(message);
  }

  /**
   * @param cause
   */
  public RuntimeIoException(final Throwable cause)
  {
    super(cause);
  }

}
