/**
 * 
 */
package org.elixirian.common.io.exception;

import org.elixirian.common.exception.ElixirianRuntimeException;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-01-09)
 */
public class ElixirianIoException extends ElixirianRuntimeException
{
	private static final long serialVersionUID = 4804330258609079383L;

	/**
	 * 
	 */
	public ElixirianIoException()
	{
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ElixirianIoException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ElixirianIoException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public ElixirianIoException(Throwable cause)
	{
		super(cause);
	}

}
