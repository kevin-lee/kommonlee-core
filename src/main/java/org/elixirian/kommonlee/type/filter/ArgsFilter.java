/**
 * 
 */
package org.elixirian.kommonlee.type.filter;

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
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-14)
 * @param <T>
 *          input
 * @param <C>
 *          condition
 * @param <R>
 *          result
 */
public interface ArgsFilter<T, C, R> extends Filter1<T, C, R>, Filter2<T, C, R>, Filter3<T, C, R>, Filter4<T, C, R>,
		Filter5<T, C, R>, VarargsFilter<T, C, R>, ArrayFilter<T, C, R>
{
	@Override
	R filter(C condition, T first);

	@Override
	R filter(C condition, T first, T second);

	@Override
	R filter(C condition, T first, T second, T third);

	@Override
	R filter(C condition, T first, T second, T third, T fourth);

	@Override
	R filter(C condition, T first, T second, T third, T fourth, T fifth);

	@Override
  R filter(C condition, T first, T second, T third, T fourth, T fifth, T... rest);

	@Override
  R filter(C condition, T[] values);
}