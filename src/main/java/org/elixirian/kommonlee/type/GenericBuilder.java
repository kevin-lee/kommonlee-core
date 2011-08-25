/**
 * 
 */
package org.elixirian.kommonlee.type;

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
 * This is an interface to implement GenericBuilder which create an instance of the type generified in this interface.
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-01-07)
 * @param <T>
 *          the type to be built.
 */
public interface GenericBuilder<T>
{
	/**
	 * Create an instance of the type T.
	 * 
	 * @return an instance of the type T.
	 */
	T build();
}