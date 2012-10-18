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

import static org.elixirian.kommonlee.util.Objects.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.elixirian.kommonlee.functional.collect.ArrayListCreator;
import org.elixirian.kommonlee.functional.collect.ArrayToArraySelector;
import org.elixirian.kommonlee.functional.collect.ArrayToListSelector;
import org.elixirian.kommonlee.functional.collect.ElementCountSelector;
import org.elixirian.kommonlee.functional.collect.IterableToArrayListSelector;
import org.elixirian.kommonlee.functional.collect.IterableToCollectionMapper;
import org.elixirian.kommonlee.functional.collect.IterableToCollectionSelectableMapper;
import org.elixirian.kommonlee.functional.string.NotEmptyStringCondition;
import org.elixirian.kommonlee.functional.string.PrefixAndSuffixAdder;
import org.elixirian.kommonlee.functional.string.StringArrayToTrimmedStringListSelector;
import org.elixirian.kommonlee.functional.string.StringArrayTrimmer;
import org.elixirian.kommonlee.functional.string.ToLowerCaseConverter;
import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.functional.Function1;
import org.elixirian.kommonlee.util.CommonConstants;
import org.elixirian.kommonlee.util.string.IterableToStringGlue;
import org.elixirian.kommonlee.util.string.ToStringGlues;

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
 * @version 0.0.1 (2011-02-26)
 */
public final class Functions
{
	public static final StringArrayToTrimmedStringListSelector STRING_ARRAY_TO_LIST_TRIM_SELECTOR =
		new StringArrayToTrimmedStringListSelector();

	public static final NotNullCondition<Object> NOT_NULL_CONDITION = new NotNullCondition<Object>();

	public static final NotEmptyStringCondition NOT_EMPTY_STRING_CONDITION = new NotEmptyStringCondition();

	public static final ArrayToListSelector<?, ?> ARRAY_TO_LIST_SELECTOR =
		new ArrayToListSelector<Object, Condition1<Object>>();

	public static final ArrayToArraySelector<?, ?> ARRAY_TO_ARRAY_SELECTOR =
		new ArrayToArraySelector<Object, Condition1<Object>>();

	public static final StringArrayTrimmer STRING_ARRAY_TRIMMER = new StringArrayTrimmer();

	public final static ElementCountSelector<?> ELEMENT_COUNT_SELECTOR = new ElementCountSelector<Object>();

	public static final IterableToArrayListSelector<Object> LIST_TO_ARRAY_LIST_SELECTOR =
		IterableToArrayListSelector.newInstance(ArrayListCreator.getInstance());

	public static final IterableToCollectionMapper<?, ? extends Iterable<?>, ?, ? extends Function1<?, ?>, ? extends List<?>> ITERABLE_TO_COLLECTION_MAPPER =
		IterableToCollectionMapper.newInstance(ArrayListCreator.getInstance());

	public static class IterableToArrayListMapper<E, NE> extends
			IterableToCollectionMapper<E, Iterable<? extends E>, NE, Function1<? super E, NE>, ArrayList<NE>>
	{
		protected IterableToArrayListMapper(final ArrayListCreator<NE> collectionCreator)
		{
			super(collectionCreator);
		}

		public static <E, NE> IterableToArrayListMapper<E, NE> newInstance(final ArrayListCreator<NE> collectionCreator)
		{
			return new IterableToArrayListMapper<E, NE>(collectionCreator);
		}
	}

	public static final IterableToArrayListMapper<?, ?> ITERABLE_TO_ARRAY_LIST_MAPPER =
		IterableToArrayListMapper.newInstance(ArrayListCreator.getInstance());

	public static class IterableToArrayListSelectableMapper<E, NE>
			extends
			IterableToCollectionSelectableMapper<E, Iterable<? extends E>, Condition1<E>, NE, Function1<? super E, NE>, ArrayList<NE>>
	{

		public IterableToArrayListSelectableMapper(final ArrayListCreator<NE> collectionCreator)
		{
			super(collectionCreator);
		}

		public static <E, NE> IterableToArrayListSelectableMapper<E, NE> newInstance(
				final ArrayListCreator<NE> collectionCreator)
		{
			return new IterableToArrayListSelectableMapper<E, NE>(collectionCreator);
		}
	}

	public static final IterableToArrayListSelectableMapper<?, ?> ITERABLE_TO_ARRAY_LIST_SELECTABLE_MAPPER =
		IterableToArrayListSelectableMapper.newInstance(ArrayListCreator.getInstance());

	public static final ToLowerCaseConverter TO_LOWER_CASE_CONVERTER = new ToLowerCaseConverter();

	public static final GenericVarargsSelector<?, ?, ?> GENERIC_VARARGS_SELECTOR =
		new GenericVarargsSelector<Object, Condition1<Object>, List<Object>>(ArrayListCreator.getInstance());

	public static final IterableToStringGlue<Object> ITERABLE_TO_CSV_GLUE = ToStringGlues.builderForIterable()
			.ignoreNull()
			.ignore("")
			.withSeparator(",")
			.build();

	public static final Comparator<Integer> INTEGER_ASCENDING_ORDER = new Comparator<Integer>() {
		@Override
		public int compare(final Integer i1, final Integer i2)
		{
			mustNotBeNull(i1);
			mustNotBeNull(i2);
			return i1.compareTo(i2);
		}
	};

	private Functions() throws IllegalAccessException
	{
		throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
	}

	public static <T> NotNullCondition<T> notNullCondition()
	{
		@SuppressWarnings("unchecked")
		final NotNullCondition<T> notNullCondition = (NotNullCondition<T>) NOT_NULL_CONDITION;
		return notNullCondition;
	}

	public static NotEmptyStringCondition notEmptyStringCondition()
	{
		return NOT_EMPTY_STRING_CONDITION;
	}

	public static <E, C extends Condition1<E>> ArrayToListSelector<E, C> getArrayToListSelector()
	{
		@SuppressWarnings("unchecked")
		final ArrayToListSelector<E, C> arrayToListSelector = (ArrayToListSelector<E, C>) ARRAY_TO_LIST_SELECTOR;
		return arrayToListSelector;
	}

	public static <T, C extends Condition1<T>> ArrayToArraySelector<T, C> getArrayToArraySelector()
	{
		@SuppressWarnings("unchecked")
		final ArrayToArraySelector<T, C> arrayToArraySelector = (ArrayToArraySelector<T, C>) ARRAY_TO_ARRAY_SELECTOR;
		return arrayToArraySelector;
	}

	public static StringArrayToTrimmedStringListSelector stringArrayToTrimmedStringListSelector()
	{
		return STRING_ARRAY_TO_LIST_TRIM_SELECTOR;
	}

	public static StringArrayTrimmer stringArrayTrimmer()
	{
		return STRING_ARRAY_TRIMMER;
	}

	public static <E> ElementCountSelector<E> getElementCountSelector()
	{
		@SuppressWarnings("unchecked")
		final ElementCountSelector<E> elementCountSelector = (ElementCountSelector<E>) ELEMENT_COUNT_SELECTOR;
		return elementCountSelector;
	}

	public static <E> IterableToArrayListSelector<E> iterableToArrayListSelector()
	{
		@SuppressWarnings("unchecked")
		final IterableToArrayListSelector<E> listToArrayListSelector =
			(IterableToArrayListSelector<E>) LIST_TO_ARRAY_LIST_SELECTOR;
		return listToArrayListSelector;
	}

	public static <E, NE> IterableToArrayListMapper<E, NE> iterableToArrayListMapper()
	{
		@SuppressWarnings("unchecked")
		final IterableToArrayListMapper<E, NE> iterableToArrayListMapper =
			(IterableToArrayListMapper<E, NE>) ITERABLE_TO_ARRAY_LIST_MAPPER;
		return iterableToArrayListMapper;
	}

	public static <E, NE> IterableToArrayListSelectableMapper<E, NE> iterableToArrayListSelectableMapper()
	{
		@SuppressWarnings("unchecked")
		final IterableToArrayListSelectableMapper<E, NE> iterableToArrayListSelectableMapper =
			(IterableToArrayListSelectableMapper<E, NE>) ITERABLE_TO_ARRAY_LIST_SELECTABLE_MAPPER;
		return iterableToArrayListSelectableMapper;
	}

	public static PrefixAndSuffixAdder newPrefixAndSuffixAdder(final String prefix, final String suffix)
	{
		return new PrefixAndSuffixAdder(prefix, suffix);
	}

	public static ToLowerCaseConverter toLowerCaseConverter()
	{
		return TO_LOWER_CASE_CONVERTER;
	}

	public static <T> GenericVarargsSelector<T, Condition1<T>, List<T>> genericVarargsToListSelector()
	{
		@SuppressWarnings({ "unchecked" })
		final GenericVarargsSelector<T, Condition1<T>, List<T>> genericVarargsFilter =
			(GenericVarargsSelector<T, Condition1<T>, List<T>>) GENERIC_VARARGS_SELECTOR;
		return genericVarargsFilter;
	}

}
