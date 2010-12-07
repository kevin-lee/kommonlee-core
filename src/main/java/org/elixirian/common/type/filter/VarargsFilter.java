/**
 * 
 */
package org.elixirian.common.type.filter;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-14)
 * @param <T>
 *            input
 * @param <C>
 *            condition
 * @param <R>
 *            result
 */
public interface VarargsFilter<T, C, R> extends Filter<T, C, R>, TwoArgsFilter<T, C, R>, ThreeArgsFilter<T, C, R>,
		FourArgsFilter<T, C, R>, FiveArgsFilter<T, C, R>
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
}