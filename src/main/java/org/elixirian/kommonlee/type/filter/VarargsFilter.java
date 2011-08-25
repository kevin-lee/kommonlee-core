/**
 * 
 */
package org.elixirian.kommonlee.type.filter;

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
 * @version 0.0.1 (2010-11-14)
 * @param <T>
 *          input
 * @param <C>
 *          condition
 * @param <R>
 *          result
 */
public interface VarargsFilter<T, C, R> extends Filter1<T, C, R>, Filter2<T, C, R>, Filter3<T, C, R>, Filter4<T, C, R>,
		Filter5<T, C, R>
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

	R filter(C condition, T first, T second, T third, T fourth, T fifth, T... rest);

	R filter(C condition, T[] values);
}