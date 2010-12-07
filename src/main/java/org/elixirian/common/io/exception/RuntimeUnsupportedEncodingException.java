/**
 * 
 */
package org.elixirian.common.io.exception;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-01-09)
 */
public class RuntimeUnsupportedEncodingException extends RuntimeIoException
{
	private static final long serialVersionUID = -8352857644431063705L;

	/**
	 * 
	 */
	public RuntimeUnsupportedEncodingException()
	{
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RuntimeUnsupportedEncodingException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public RuntimeUnsupportedEncodingException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public RuntimeUnsupportedEncodingException(Throwable cause)
	{
		super(cause);
	}

}
