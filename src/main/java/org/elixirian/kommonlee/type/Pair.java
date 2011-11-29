/**
 * 
 */
package org.elixirian.kommonlee.type;

/**
 * <pre>
 *     ___  _____                                              _____
 *    /   \/    / ______ __________________  ______ __ ______ /    /   ______  ______  
 *   /        / _/ __  // /  /   / /  /   /_/ __  // //     //    /   /  ___ \/  ___ \ 
 *  /        \ /  /_/ _/  _  _  /  _  _  //  /_/ _/   __   //    /___/  _____/  _____/
 * /____/\____\/_____//__//_//_/__//_//_/ /_____//___/ /__//________/\_____/ \_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
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
	L getFirst();

	/**
	 * Returns the second object (R) in this {@link Pair}.
	 * 
	 * @return the second object (R) in this {@link Pair}.
	 */
	R getSecond();
}