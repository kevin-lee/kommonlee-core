/**
 * 
 */
package org.elixirian.common.io.exception;

/**
 * <pre>
 *     ____________    ___________  ____   _______ _________ _______ _______________  ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /     \/   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /          /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 * 
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
