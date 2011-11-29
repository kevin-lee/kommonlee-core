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