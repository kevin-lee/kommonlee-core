/**
 * 
 */
package org.elixirian.common.exception;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-01-09)
 */
public class ElixirianRuntimeException extends RuntimeException
{
	private static final long serialVersionUID = -5681761677784619703L;

	public ElixirianRuntimeException()
	{
		super();
	}

	public ElixirianRuntimeException(String message)
	{
		super(message);
	}

	public ElixirianRuntimeException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ElixirianRuntimeException(Throwable cause)
	{
		super(cause);
	}
}
