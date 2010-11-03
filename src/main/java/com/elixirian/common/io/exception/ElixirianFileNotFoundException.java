/**
 * 
 */
package com.elixirian.common.io.exception;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-01-09)
 */
public class ElixirianFileNotFoundException extends ElixirianIoException
{
	private static final long serialVersionUID = 6404612073848465058L;

	/**
	 * 
	 */
	public ElixirianFileNotFoundException()
	{
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ElixirianFileNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ElixirianFileNotFoundException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public ElixirianFileNotFoundException(Throwable cause)
	{
		super(cause);
	}

}
