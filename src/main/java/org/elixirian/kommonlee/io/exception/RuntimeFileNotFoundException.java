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
 *     ___  _____
 *    /   \/    /_________  ___ ____ __ ______
 *   /        / /  ___ \  \/  //___// //     /
 *  /        \ /  _____/\    //   //   __   /
 * /____/\____\\_____/   \__//___//___/ /__/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-01-09)
 */
public class RuntimeFileNotFoundException extends RuntimeIoException
{
  private static final long serialVersionUID = 6404612073848465058L;

  /**
	 * 
	 */
  public RuntimeFileNotFoundException()
  {
    super();
  }

  /**
   * @param message
   * @param cause
   */
  public RuntimeFileNotFoundException(final String message, final Throwable cause)
  {
    super(message, cause);
  }

  /**
   * @param message
   */
  public RuntimeFileNotFoundException(final String message)
  {
    super(message);
  }

  /**
   * @param cause
   */
  public RuntimeFileNotFoundException(final Throwable cause)
  {
    super(cause);
  }

}
