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
public interface FourArgsFilter<T, C, R>
{
	R filter(C condition, T first, T second, T third, T fourth);
}