/**
 * 
 */
package org.elixirian.common.util.string;

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
 */
public interface ArgsToStringGlue<E> extends ToStringGlue<E[]>
{
	@Override
	String glue(E[] values);

	<T extends E> String glue(T value1, T value2);

	<T extends E> String glue(T value1, T value2, T value3);

	<T extends E> String glue(T value1, T value2, T value3, T value4);

	<T extends E> String glue(T value1, T value2, T value3, T value4, T value5);

	<T extends E> String glue(T value1, T value2, T value3, T value4, T value5, T... rest);
}