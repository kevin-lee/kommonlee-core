/**
 * 
 */
package org.elixirian.kommonlee.exception.security;

import org.elixirian.kommonlee.exception.ExtraExceptionInformation;
import org.elixirian.kommonlee.exception.InformativeRuntimeException;

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
 * @version 0.0.1 (2012-06-05)
 */
public class ElixirianSecurityException extends InformativeRuntimeException
{
	private static final long serialVersionUID = 4599788105021802070L;

	public ElixirianSecurityException(final ExtraExceptionInformation extraExceptionInformation)
	{
		super(extraExceptionInformation);
	}

	public ElixirianSecurityException(final String message, final ExtraExceptionInformation extraExceptionInformation)
	{
		super(message, extraExceptionInformation);
	}

	public ElixirianSecurityException(final String message, final Throwable cause,
			final ExtraExceptionInformation extraExceptionInformation)
	{
		super(message, cause, extraExceptionInformation);
	}

	public ElixirianSecurityException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	public ElixirianSecurityException(final String message)
	{
		super(message);
	}

	public ElixirianSecurityException(final Throwable cause, final ExtraExceptionInformation extraExceptionInformation)
	{
		super(cause, extraExceptionInformation);
	}

	public ElixirianSecurityException(final Throwable cause)
	{
		super(cause);
	}

}
