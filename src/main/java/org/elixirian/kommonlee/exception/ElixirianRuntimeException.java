/**
 * 
 */
package org.elixirian.kommonlee.exception;

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
