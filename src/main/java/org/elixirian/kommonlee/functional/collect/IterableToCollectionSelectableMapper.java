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
package org.elixirian.kommonlee.functional.collect;

import java.util.Collection;

import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.functional.Function1;
import org.elixirian.kommonlee.type.functional.Function3;

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
 * @version 0.0.1 (2011-07-23)
 * @param <E>
 * @param <T>
 * @param <C>
 * @param <NE>
 * @param <F>
 * @param <R>
 */
public class IterableToCollectionSelectableMapper<E, T extends Iterable<? extends E>, C extends Condition1<E>, NE, F extends Function1<? super E, NE>, R extends Collection<NE>>
		implements Function3<C, F, T, R>
{
	private final CollectionCreator<NE, ? extends R> collectionCreator;

	public <CC extends CollectionCreator<NE, ? extends R>> IterableToCollectionSelectableMapper(final CC collectionCreator)
	{
		this.collectionCreator = collectionCreator;
	}

	@Override
	public R apply(final C condition, final F function, final T source)
	{
		final R result = collectionCreator.createCollection();
		for (final E element : source)
		{
			if (condition.isMet(element))
			{
				result.add(function.apply(element));
			}
		}
		return result;
	}

	/* @formatter:off */
	public static <E,
									 T extends Iterable<? extends E>,
									 C extends Condition1<E>,
									 NE,
									 F extends Function1<? super E, NE>,
									 R extends Collection<NE>,
									 CC extends CollectionCreator<NE, ? extends R>>
					IterableToCollectionSelectableMapper<E, T, C, NE, F, R>
		newInstance(final CC collectionCreator)
	{
		return new IterableToCollectionSelectableMapper<E, T, C, NE, F, R>(collectionCreator);
	}
	/* @formatter:on */
}