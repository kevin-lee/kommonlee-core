/**
 * 
 */
package org.elixirian.common.type.filter;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-12-07)
 * @param <T>
 *            input
 * @param <C>
 *            condition
 * @param <R>
 *            result
 */
public interface ArrayFilter<T, C, R> extends Filter<T[], C, R>
{
	@Override
	R filter(C condition, T[] source);
}
