/**
 * 
 */
package org.elixirian.common.io.exception;

import org.elixirian.common.exception.ElixirianRuntimeException;

/**
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
	public RuntimeIoException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public RuntimeIoException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public RuntimeIoException(Throwable cause)
	{
		super(cause);
	}

}
