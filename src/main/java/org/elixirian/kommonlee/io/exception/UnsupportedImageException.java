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
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-08-06)
 */
public class UnsupportedImageException extends RuntimeIoException
{
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public UnsupportedImageException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public UnsupportedImageException(final String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public UnsupportedImageException(final Throwable cause)
	{
		super(cause);
	}

}
