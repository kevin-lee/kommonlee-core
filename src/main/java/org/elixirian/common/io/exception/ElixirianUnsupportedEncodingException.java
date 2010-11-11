/**
 * 
 */
package org.elixirian.common.io.exception;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-01-09)
 */
public class ElixirianUnsupportedEncodingException extends ElixirianIoException
{
	private static final long serialVersionUID = -8352857644431063705L;

	/**
	 * 
	 */
	public ElixirianUnsupportedEncodingException()
	{
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ElixirianUnsupportedEncodingException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ElixirianUnsupportedEncodingException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public ElixirianUnsupportedEncodingException(Throwable cause)
	{
		super(cause);
	}

}
