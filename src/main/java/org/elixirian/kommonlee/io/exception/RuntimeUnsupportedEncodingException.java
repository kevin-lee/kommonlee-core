/**
 * 
 */
package org.elixirian.kommonlee.io.exception;

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
public class RuntimeUnsupportedEncodingException extends RuntimeIoException
{
  private static final long serialVersionUID = -8352857644431063705L;

  /**
	 * 
	 */
  public RuntimeUnsupportedEncodingException()
  {
    super();
  }

  /**
   * @param message
   * @param cause
   */
  public RuntimeUnsupportedEncodingException(final String message, final Throwable cause)
  {
    super(message, cause);
  }

  /**
   * @param message
   */
  public RuntimeUnsupportedEncodingException(final String message)
  {
    super(message);
  }

  /**
   * @param cause
   */
  public RuntimeUnsupportedEncodingException(final Throwable cause)
  {
    super(cause);
  }

}
