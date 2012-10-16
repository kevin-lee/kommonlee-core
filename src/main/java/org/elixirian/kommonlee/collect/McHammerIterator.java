/**
 * This project is licensed under the Apache License, Version 2.0
 * if the following condition is met:
 * (otherwise it cannot be used by anyone but the author, Kevin, only)
 *
 * The original KommonLee project is owned by Lee, Seong Hyun (Kevin).
 *
 * -What does it mean to you?
 * Nothing, unless you want to take the ownership of
 * "the original project" (not yours or forked & modified one).
 * You are free to use it for both non-commercial and commercial projects
 * and free to modify it as the Apache License allows.
 *
 * -So why is this condition necessary?
 * It is only to protect the original project (See the case of Java).
 *
 *
 * Copyright 2009 Lee, Seong Hyun (Kevin)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

	/**
	 * McHammerIterator: U Can't Touch This! You always get {@link UnsupportedOperationException} when you call this
	 * method.
	 * 
	 * @exception UnsupportedOperationException
	 *              always!!!
	 */
	@Override
	public final void remove()
	{
		throw new UnsupportedOperationException(
				"McHammerIterator: U Can't Touch This!\nYou cannot remove any element through this Iterator. It is an object of UnmodifiableIterator.");
	}
}
