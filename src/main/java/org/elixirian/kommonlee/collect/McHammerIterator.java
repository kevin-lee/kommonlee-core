/**
 * 
 */
package org.elixirian.kommonlee.collect;

import java.util.NoSuchElementException;

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
 * @version 0.0.1 (2011-10-13)
 */
public abstract class McHammerIterator<E> implements UnmodifiableIterator<E>
{
	public static final McHammerIterator<?> EMPTY_MC_HAMMER_ITERATOR = new McHammerIterator<Object>() {

		@Override
		public boolean hasNext()
		{
			return false;
		}

		@Override
		public Object next()
		{
			throw new NoSuchElementException();
		}
	};

	public static <T> McHammerIterator<T> emptyMcHammerIterator()
	{
		@SuppressWarnings("unchecked")
		final McHammerIterator<T> emptyMcHammerIterator = (McHammerIterator<T>) EMPTY_MC_HAMMER_ITERATOR;
		return emptyMcHammerIterator;
	}

	@Override
	public abstract boolean hasNext();

	@Override
	public abstract E next();

	@Override
	public final void remove()
	{
		throw new UnsupportedOperationException(
				"McHammerIterator: U Can't Touch This!\nYou cannot remove any element through this Iterator. It is an object of UnmodifiableIterator.");
	}
}
