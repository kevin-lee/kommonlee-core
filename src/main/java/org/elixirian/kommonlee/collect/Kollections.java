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

import static org.elixirian.kommonlee.util.Objects.*;

import java.util.Iterator;

import org.elixirian.kommonlee.type.functional.Function1;
import org.elixirian.kommonlee.util.CommonConstants;
import org.elixirian.kommonlee.util.IterableToAppendableGlue;
import org.elixirian.kommonlee.util.Objects;

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
 * @version 0.0.1 (2011-10-14)
 */
public final class Kollections
{
	private Kollections() throws IllegalAccessException
	{
		throw new IllegalAccessException(getClass() + CommonConstants.CANNOT_BE_INSTANTIATED);
	}

	public static int hashIterator(final Iterator<?> iterator)
	{
		if (null == iterator)
			return 0;

		int result = Objects.HASH_SEED;
		while (iterator.hasNext())
		{
			final Object element = iterator.next();
			result = hash(result, element);
		}
		return result;
	}

	public static boolean equalReadableLists(final ReadableList<?> first, final ReadableList<?> second)
	{
		if (first.length() != second.length())
		{
			return false;
		}
		final int length = first.length();
		for (int i = 0; i < length; i++)
		{
			if (notEqual(first.get(i), second.get(i)))
			{
				return false;
			}
		}
		return true;
	}

	public static boolean equalElements(final Iterator<?> first, final Iterator<?> second)
	{
		while (first.hasNext() && second.hasNext())
		{
			if (notEqual(first.next(), second.next()))
			{
				return false;
			}
		}
		return !(first.hasNext() || second.hasNext());
	}

	private static final IterableToAppendableGlue ITERABLE_TO_APPENDABLE_GLUE =
		IterableToAppendableGlue.withSeparator(", ");

	public static String toStringOf(final Kollection<?> kollection)
	{
		if (null == kollection)
		{
			return "null";
		}
		final String kollectionName = kollection.getClass()
				.getSimpleName();
		final Kollection<?> functionAppliedKollection = kollection.map(new Function1<Object, Object>() {
			@Override
			public Object apply(final Object input)
			{
				return kollection == input ? "{this (" + kollectionName + ") Kollection}" : input;
			}
		});

		final StringBuilder stringBuilder = new StringBuilder(kollectionName).append("[");
		return ITERABLE_TO_APPENDABLE_GLUE.glue(stringBuilder, functionAppliedKollection)
				.append("]")
				.toString();
	}
}
