/**
 * 
 */
package org.elixirian.common.type;

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
 * This interface is to create a Pair object which stores two objects in order to pass as a parameter or to return as a
 * result or to store in the data structure or just to have two different or the same types in one object for any
 * purposes.
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-09-14)
 * @param <L>
 * @param <R>
 */
public interface Pair<L, R>
{
	/**
	 * Returns the first object (L) in this {@link Pair}.
	 * 
	 * @return the first object (L) in this {@link Pair}.
	 */
	L getLeft();

	/**
	 * Returns the second object (R) in this {@link Pair}.
	 * 
	 * @return the second object (R) in this {@link Pair}.
	 */
	R getRight();
}