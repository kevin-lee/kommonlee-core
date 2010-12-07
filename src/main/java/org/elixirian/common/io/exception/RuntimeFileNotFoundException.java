/**
 * 
 */
package org.elixirian.common.io.exception;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-01-09)
 */
public class RuntimeFileNotFoundException extends RuntimeIoException
{
	private static final long serialVersionUID = 6404612073848465058L;

	/**
	 * 
	 */
	public RuntimeFileNotFoundException()
	{
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RuntimeFileNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public RuntimeFileNotFoundException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public RuntimeFileNotFoundException(Throwable cause)
	{
		super(cause);
	}

}
