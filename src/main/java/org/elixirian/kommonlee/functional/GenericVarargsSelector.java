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
package org.elixirian.kommonlee.functional;

import java.util.Collection;

import org.elixirian.kommonlee.functional.collect.CollectionCreator;
import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.selector.ArgsSelector;

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
 * @version 0.0.1 (2011-08-27)
 * @param <T>
 * @param <C>
 * @param <R>
 */
public class GenericVarargsSelector<T, C extends Condition1<T>, R extends Collection<T>> implements
		ArgsSelector<T, C, R>
{
	private final CollectionCreator<T, ? extends R> collectionCreator;

	public <CC extends CollectionCreator<T, ? extends R>> GenericVarargsSelector(final CC collectionCreator)
	{
		this.collectionCreator = collectionCreator;
	}

	@Override
	public R select(final C condition, final T first)
	{
		final R result = collectionCreator.createCollection();
		if (condition.isMet(first))
			result.add(first);
		return result;
	}

	@Override
	public R select(final C condition, final T first, final T second)
	{
		final R result = collectionCreator.createCollection();
		if (condition.isMet(first))
			result.add(first);
		if (condition.isMet(second))
			result.add(second);
		return result;
	}

	@Override
	public R select(final C condition, final T first, final T second, final T third)
	{
		final R result = collectionCreator.createCollection();
		if (condition.isMet(first))
			result.add(first);
		if (condition.isMet(second))
			result.add(second);
		if (condition.isMet(third))
			result.add(third);
		return result;
	}

	@Override
	public R select(final C condition, final T first, final T second, final T third, final T fourth)
	{
		final R result = collectionCreator.createCollection();
		if (condition.isMet(first))
			result.add(first);
		if (condition.isMet(second))
			result.add(second);
		if (condition.isMet(third))
			result.add(third);
		if (condition.isMet(fourth))
			result.add(fourth);
		return result;
	}

	@Override
	public R select(final C condition, final T first, final T second, final T third, final T fourth, final T fifth)
	{
		final R result = collectionCreator.createCollection();
		if (condition.isMet(first))
			result.add(first);
		if (condition.isMet(second))
			result.add(second);
		if (condition.isMet(third))
			result.add(third);
		if (condition.isMet(fourth))
			result.add(fourth);
		if (condition.isMet(fifth))
			result.add(fifth);
		return result;
	}

	@Override
	public R select(final C condition, final T first, final T second, final T third, final T fourth, final T fifth,
			final T... rest)
	{
		final R result = select(condition, first, second, third, fourth, fifth);
		for (final T element : rest)
		{
			if (condition.isMet(element))
			{
				result.add(element);
			}
		}
		return result;
	}

	@Override
	public R select(final C condition, final T[] values)
	{
		final R result = collectionCreator.createCollection();
		for (final T element : values)
		{
			if (condition.isMet(element))
			{
				result.add(element);
			}
		}
		return result;
	}

}
