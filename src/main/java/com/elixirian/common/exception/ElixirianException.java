/**
 * 
 */
package com.elixirian.common.exception;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-01-09)
 */
public class ElixirianException extends Exception
{
	private static final long serialVersionUID = 4589887208885608852L;

	public ElixirianException()
	{
		super();
	}

	public ElixirianException(String message)
	{
		super(message);
	}

	public ElixirianException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ElixirianException(Throwable cause)
	{
		super(cause);
	}
}
