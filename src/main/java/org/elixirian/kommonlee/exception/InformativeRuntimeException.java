/**
 * 
 */
package org.elixirian.kommonlee.exception;

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
public abstract class InformativeRuntimeException extends ElixirianRuntimeException
{
	private static final long serialVersionUID = -6976968193484353401L;

	private final ExtraExceptionInformation extraExceptionInformation;

	public InformativeRuntimeException(final String message)
	{
		this(message, null, null);
	}

	public InformativeRuntimeException(final Throwable cause)
	{
		this(null, cause, null);
	}

	public InformativeRuntimeException(final String message, final Throwable cause)
	{
		this(message, cause, null);
	}

	public InformativeRuntimeException(final ExtraExceptionInformation extraExceptionInformation)
	{
		this(null, null, extraExceptionInformation);
	}

	public InformativeRuntimeException(final String message, final ExtraExceptionInformation extraExceptionInformation)
	{
		this(message, null, extraExceptionInformation);
	}

	public InformativeRuntimeException(final Throwable cause, final ExtraExceptionInformation extraExceptionInformation)
	{
		this(null, cause, extraExceptionInformation);
	}

	public InformativeRuntimeException(final String message, final Throwable cause,
			final ExtraExceptionInformation extraExceptionInformation)
	{
		super(message, cause);
		this.extraExceptionInformation =
			null == extraExceptionInformation ? ExtraExceptionInformation.EMPTY_EXTRA_EXCEPTION_INFORMATION
					: extraExceptionInformation;
	}

	public ExtraExceptionInformation getExceptionInformation()
	{
		return extraExceptionInformation;
	}

}
