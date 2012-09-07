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
package org.elixirian.kommonlee.util.string;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;
import static org.elixirian.kommonlee.util.MessageFormatter.format;
import static org.elixirian.kommonlee.util.Objects.mustNotBeNull;
import static org.elixirian.kommonlee.util.Strings.isNullOrEmptyString;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.elixirian.kommonlee.type.GenericBuilder;
import org.elixirian.kommonlee.type.function.Condition1;
import org.elixirian.kommonlee.type.function.Function1;
import org.elixirian.kommonlee.type.function.Function2;
import org.elixirian.kommonlee.util.CommonConstants;

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
 * @version 0.0.1 (2010-11-14)
 */
public final class ToStringGlues
{
	private ToStringGlues() throws IllegalAccessException
	{
		throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
	}

	private static abstract class Builder<E, T extends ToStringGlue<?>> implements GenericBuilder<T>
	{
		protected Set<Object> ignoredElementSet;
		protected Map<Object, Object> replacedElementMap;
		protected Function1<? super E, ? extends E> mapper;
		protected Map<Object, Function1<? super E, ? extends E>> elementMapperMap;

		protected Set<Condition1<? super E>> conditionToBeIgnoredSet;
		protected Map<Condition1<? super E>, Object> conditionallyReplacedElementMap;
		protected Map<Condition1<? super E>, Function1<? super E, ? extends E>> conditionallyMappedElementMap;
		protected String separator;

		public Builder()
		{
			this.ignoredElementSet = new LinkedHashSet<Object>();
			this.replacedElementMap = new LinkedHashMap<Object, Object>();
			this.elementMapperMap = new LinkedHashMap<Object, Function1<? super E, ? extends E>>();

			this.conditionToBeIgnoredSet = new LinkedHashSet<Condition1<? super E>>();
			this.conditionallyReplacedElementMap = new LinkedHashMap<Condition1<? super E>, Object>();
			this.conditionallyMappedElementMap =
				new LinkedHashMap<Condition1<? super E>, Function1<? super E, ? extends E>>();
		}

		public Builder<E, T> ignoreNull()
		{
			return ignore(null);
		}

		public <N extends E> Builder<E, T> replaceNull(final N toReplace)
		{
			return replace(null, toReplace);
		}

		public Builder<E, T> ignore(final E toBeIgnored)
		{
			if (replacedElementMap.containsKey(toBeIgnored))
			{
				throw new IllegalArgumentException(format("%s is already set to be replaced by another element. "
						+ "You cannot have one element to be both ignored and replaced.", toBeIgnored));
			}
			if (elementMapperMap.containsKey(toBeIgnored))
			{
				throw new IllegalArgumentException(format("%s is already set to be mapped by a function (%s)."
						+ "You cannot have one element to be both ignored and mapped.", toBeIgnored,
						elementMapperMap.get(toBeIgnored)));
			}
			ignoredElementSet.add(toBeIgnored);
			return this;
		}

		public <C extends Condition1<? super E>> Builder<E, T> ignoreIf(final C condition)
		{

			if (conditionallyReplacedElementMap.containsKey(condition))
			{
				throw new IllegalArgumentException(format(
						"The given condition (%s) is already set to be replaced by another element (%s). "
								+ "You cannot have one condition to be used to both ignore and replace an element.", condition,
						conditionallyReplacedElementMap.get(condition)));
			}
			if (conditionallyMappedElementMap.containsKey(condition))
			{
				throw new IllegalArgumentException(format(
						"The given condition (%s) is already set to be mapped by another function (%s). "
								+ "You cannot have one condition to be used to both ignore and map an element.", condition,
						conditionallyMappedElementMap.get(condition)));
			}
			conditionToBeIgnoredSet.add(condition);
			return this;
		}

		public <N extends E> Builder<E, T> replace(final E toBeReplaced, final N by)
		{
			if (ignoredElementSet.contains(toBeReplaced))
			{
				throw new IllegalArgumentException(format("%s is already set to be ignored. "
						+ "You cannot have one element to be both ignored and replaced by another %s.", toBeReplaced, by));
			}
			if (elementMapperMap.containsKey(toBeReplaced))
			{
				throw new IllegalArgumentException(format("%s is already set to be mapped by a function (%s)."
						+ "You cannot have one element to be both replaced and mapped.", toBeReplaced,
						elementMapperMap.get(toBeReplaced)));
			}
			replacedElementMap.put(toBeReplaced, by);
			return this;
		}

		public <N extends E, F extends Function1<? super E, ? extends E>> Builder<E, T> mapWith(final E toBeMapped,
				final F function)
		{
			if (ignoredElementSet.contains(toBeMapped))
			{
				throw new IllegalArgumentException(format("%s is already set to be ignored. "
						+ "You cannot have one element to be ignored and mapped.", toBeMapped));
			}
			if (replacedElementMap.containsKey(toBeMapped))
			{
				throw new IllegalArgumentException(format("%s is already set to be replaced by another element (%s). "
						+ "You cannot have one element to be both replaced and mapped by another.", toBeMapped,
						replacedElementMap.get(toBeMapped)));
			}
			mustNotBeNull(function, "The function to map cannot be null");
			elementMapperMap.put(toBeMapped, function);
			return this;
		}

		public <N extends E, F extends Function1<? super E, ? extends E>> Builder<E, T> mapIfNotMappedWithAny(
				final F function)
		{
			mustNotBeNull(function, "The function to map cannot be null");
			mapper = function;
			return this;
		}

		public <N extends E> Builder<E, T> doNotMapEvenIfNotMappedWithAny()
		{
			mapper = null;
			return this;
		}

		public <C extends Condition1<? super E>, N extends E> Builder<E, T> replaceIf(final C condition, final N by)
		{
			if (conditionToBeIgnoredSet.contains(condition))
			{
				throw new IllegalArgumentException(format("The given condition (%s) is already set to be ignored. "
						+ "You cannot have one condition to be used to both ignore and replace an element by another %s.",
						condition, by));
			}
			if (conditionallyMappedElementMap.containsKey(condition))
			{
				throw new IllegalArgumentException(format(
						"The given condition (%s) is already set to be mapped by another function (%s). "
								+ "You cannot have one condition to be used to both replace and map an element.", condition,
						conditionallyMappedElementMap.get(condition)));
			}
			conditionallyReplacedElementMap.put(condition, by);
			return this;
		}

		public <C extends Condition1<? super E>, F extends Function1<E, E>> Builder<E, T> mapIf(final C condition,
				final F mapBy)
		{
			if (conditionToBeIgnoredSet.contains(condition))
			{
				throw new IllegalArgumentException(format("The given condition (%s) is already set to be ignored. "
						+ "You cannot have one condition to be used to both ignore and map an element.", condition, mapBy));
			}
			if (conditionallyReplacedElementMap.containsKey(condition))
			{
				throw new IllegalArgumentException(format(
						"The given condition (%s) is already set to be used to replace an element by another (%s). "
								+ "You cannot have one condition to be used to both replace and map an element by another.", condition,
						conditionallyReplacedElementMap.get(condition)));
			}
			mustNotBeNull(mapBy, "The function to map cannot be null");
			conditionallyMappedElementMap.put(condition, mapBy);
			return this;
		}

		public Builder<E, T> withSeparator(final String separator)
		{
			this.separator = separator;
			return this;
		}

		protected GlueFunctionWithSeparator<E> whichGlueFunction()
		{
			/* @formatter:off */
			return (ignoredElementSet.isEmpty() &&
							replacedElementMap.isEmpty() &&
							null == mapper &&
							elementMapperMap.isEmpty() &&
							conditionToBeIgnoredSet.isEmpty() &&
							conditionallyReplacedElementMap.isEmpty() &&
							conditionallyMappedElementMap.isEmpty()) ?
						new SimpleGlueFunctionWithSeparator<E>(separator) :
						new ReplacingGlueFunctionWithSeparator<E>(unmodifiableSet(ignoredElementSet),
																  										unmodifiableSet(conditionToBeIgnoredSet),
																  										unmodifiableMap(replacedElementMap),
																  										unmodifiableMap(conditionallyReplacedElementMap),
																  										unmodifiableMap(elementMapperMap),
																  										unmodifiableMap(conditionallyMappedElementMap),
																  										mapper,
																  										separator);
			/* @formatter:on */
		}

		protected GlueFunctionWithoutSeparator<E> whichGlueFunctionWithoutSeparator()
		{
			/* @formatter:off */
			return (ignoredElementSet.isEmpty() &&
							replacedElementMap.isEmpty() &&
							null == mapper &&
							elementMapperMap.isEmpty() &&
							conditionToBeIgnoredSet.isEmpty() &&
							conditionallyReplacedElementMap.isEmpty() &&
							conditionallyMappedElementMap.isEmpty()) ?
						new SimpleGlueFunctionWithoutSeparator<E>() :
						new ReplacingGlueFunctionWithoutSeparator<E>(unmodifiableSet(ignoredElementSet),
																												 unmodifiableSet(conditionToBeIgnoredSet),
																												 unmodifiableMap(replacedElementMap),
																												 unmodifiableMap(conditionallyReplacedElementMap),
																	  										 unmodifiableMap(elementMapperMap),
																												 unmodifiableMap(conditionallyMappedElementMap),
																												 mapper);
			/* @formatter:on */
		}

		@Override
		public abstract T build();
	}

	public static final class ForIterableBuilder<E> extends Builder<E, IterableToStringGlue<E>>
	{
		@Override
		public ForIterableBuilder<E> ignoreNull()
		{
			super.ignoreNull();
			return this;
		}

		@Override
		public <N extends E> ForIterableBuilder<E> replaceNull(final N toReplace)
		{
			super.replaceNull(toReplace);
			return this;
		}

		@Override
		public ForIterableBuilder<E> ignore(final E toBeIgnored)
		{
			super.ignore(toBeIgnored);
			return this;
		}

		@Override
		public <N extends E> ForIterableBuilder<E> replace(final E toBeReplaced, final N by)
		{
			super.replace(toBeReplaced, by);
			return this;
		}

		@Override
		public <N extends E, F extends Function1<? super E, ? extends E>> ForIterableBuilder<E> mapWith(final E toBeMapped,
				final F function)
		{
			super.mapWith(toBeMapped, function);
			return this;
		}

		@Override
		public <N extends E, F extends Function1<? super E, ? extends E>> ForIterableBuilder<E> mapIfNotMappedWithAny(
				final F function)
		{
			super.mapIfNotMappedWithAny(function);
			return this;
		}

		@Override
		public <N extends E> ForIterableBuilder<E> doNotMapEvenIfNotMappedWithAny()
		{
			super.doNotMapEvenIfNotMappedWithAny();
			return this;
		}

		@Override
		public <C extends Condition1<? super E>> ForIterableBuilder<E> ignoreIf(final C condition)
		{
			super.ignoreIf(condition);
			return this;
		}

		@Override
		public <C extends Condition1<? super E>, N extends E> ForIterableBuilder<E> replaceIf(final C condition, final N by)
		{
			super.replaceIf(condition, by);
			return this;
		}

		@Override
		public <C extends Condition1<? super E>, F extends Function1<E, E>> ForIterableBuilder<E> mapIf(final C condition,
				final F mapBy)
		{
			super.mapIf(condition, mapBy);
			return this;
		}

		@Override
		public ForIterableBuilder<E> withSeparator(final String separator)
		{
			super.withSeparator(separator);
			return this;
		}

		@Override
		public IterableToStringGlue<E> build()
		{
			/* @formatter:off */
			return isNullOrEmptyString(separator) ?
							new IterableToStringGlueWithoutSeparator<E>(
									whichGlueFunctionWithoutSeparator()) :
							new IterableToStringGlueWithSeparator<E>(
									whichGlueFunction());
			/* @formatter:on */
		}
	}

	public static final class ForArgsBuilder<E> extends Builder<E, ArgsToStringGlue<E>>
	{
		@Override
		public ForArgsBuilder<E> ignoreNull()
		{
			super.ignoreNull();
			return this;
		}

		@Override
		public <N extends E> ForArgsBuilder<E> replaceNull(final N toReplace)
		{
			super.replaceNull(toReplace);
			return this;
		}

		@Override
		public ForArgsBuilder<E> ignore(final E toBeIgnored)
		{
			super.ignore(toBeIgnored);
			return this;
		}

		@Override
		public <N extends E> ForArgsBuilder<E> replace(final E toBeReplaced, final N by)
		{
			super.replace(toBeReplaced, by);
			return this;
		}

		@Override
		public <N extends E, F extends Function1<? super E, ? extends E>> ForArgsBuilder<E> mapWith(final E toBeMapped,
				final F function)
		{
			super.mapWith(toBeMapped, function);
			return this;
		}

		@Override
		public <N extends E, F extends Function1<? super E, ? extends E>> ForArgsBuilder<E> mapIfNotMappedWithAny(
				final F function)
		{
			super.mapIfNotMappedWithAny(function);
			return this;
		}

		@Override
		public <N extends E> ForArgsBuilder<E> doNotMapEvenIfNotMappedWithAny()
		{
			super.doNotMapEvenIfNotMappedWithAny();
			return this;
		}

		@Override
		public <C extends Condition1<? super E>> ForArgsBuilder<E> ignoreIf(final C condition)
		{
			super.ignoreIf(condition);
			return this;
		}

		@Override
		public <C extends Condition1<? super E>, N extends E> ForArgsBuilder<E> replaceIf(final C condition, final N by)
		{
			super.replaceIf(condition, by);
			return this;
		}

		@Override
		public <C extends Condition1<? super E>, F extends Function1<E, E>> ForArgsBuilder<E> mapIf(final C condition,
				final F mapBy)
		{
			super.mapIf(condition, mapBy);
			return this;
		}

		@Override
		public ForArgsBuilder<E> withSeparator(final String separator)
		{
			super.withSeparator(separator);
			return this;
		}

		@Override
		public ArgsToStringGlue<E> build()
		{
			/* @formatter:off */
			return isNullOrEmptyString(separator) ?
							new ArgsToStringGlueWithoutSeparator<E>(
									whichGlueFunctionWithoutSeparator()) :
							new ArgsToStringGlueWithSeparator<E>(
									whichGlueFunction());
			/* @formatter:on */
		}
	}

	public static <E> ForIterableBuilder<E> builderForIterable()
	{
		return new ForIterableBuilder<E>();
	}

	public static <E> ForArgsBuilder<E> builderForArgs()
	{
		return new ForArgsBuilder<E>();
	}

	interface GlueFunction<T> extends Function2<StringBuilder, T, GlueFunction<T>>
	{
		@Override
		GlueFunction<T> apply(StringBuilder stringBuilder, T value);
	}

	interface GlueFunctionWithoutSeparator<T> extends GlueFunction<T>
	{
		@Override
		GlueFunctionWithoutSeparator<T> apply(StringBuilder stringBuilder, T value);
	}

	interface GlueFunctionWithSeparator<T> extends GlueFunction<T>
	{
		@Override
		GlueFunctionWithSeparator<T> apply(StringBuilder stringBuilder, T value);

		String getSeparator();
	}

	static abstract class SimpleGlueFunction<T> implements GlueFunction<T>
	{
		@Override
		public abstract SimpleGlueFunction<T> apply(StringBuilder stringBuilder, T value);
	}

	private static final class SimpleGlueFunctionWithSeparator<T> extends SimpleGlueFunction<T> implements
			GlueFunctionWithSeparator<T>
	{
		private String separator;

		private SimpleGlueFunctionWithSeparator(final String separator)
		{
			this.separator = separator;
		}

		@Override
		public SimpleGlueFunctionWithSeparator<T> apply(final StringBuilder stringBuilder, final T value)
		{
			if (0 != stringBuilder.length())
			{
				/* it is not the first element so add the separator. */
				stringBuilder.append(separator);
			}
			stringBuilder.append(value);
			return this;
		}

		@Override
		public String getSeparator()
		{
			return separator;
		}
	}

	private static final class SimpleGlueFunctionWithoutSeparator<T> extends SimpleGlueFunction<T> implements
			GlueFunctionWithoutSeparator<T>
	{
		@Override
		public SimpleGlueFunctionWithoutSeparator<T> apply(final StringBuilder stringBuilder, final T value)
		{
			stringBuilder.append(value);
			return this;
		}
	}

	static abstract class ReplacingGlueFunction<T> implements GlueFunction<T>
	{
		protected final Set<Object> ignoredElementSet;
		protected final Set<Condition1<? super T>> conditionToBeIgnoredSet;

		protected final Map<Object, Object> replacedElementMap;
		protected final Map<Condition1<? super T>, Object> conditionallyReplacedElementMap;

		protected final Map<Object, Function1<? super T, ? extends T>> elementMapperMap;
		protected final Map<Condition1<? super T>, Function1<? super T, ? extends T>> conditionallyMappedElementMap;
		protected final Function1<? super T, ? extends T> mapper;

		public ReplacingGlueFunction(final Set<Object> ignoredElementSet,
				final Set<Condition1<? super T>> conditionToBeIgnoredSet, final Map<Object, Object> replacedElementMap,
				final Map<Condition1<? super T>, Object> conditionallyReplacedElementMap,
				final Map<Object, Function1<? super T, ? extends T>> elementMapperMap,
				final Map<Condition1<? super T>, Function1<? super T, ? extends T>> conditionallyMappedElementMap,
				final Function1<? super T, ? extends T> mapper)
		{
			this.ignoredElementSet = ignoredElementSet;
			this.conditionToBeIgnoredSet = conditionToBeIgnoredSet;
			this.replacedElementMap = replacedElementMap;
			this.conditionallyReplacedElementMap = conditionallyReplacedElementMap;
			this.elementMapperMap = elementMapperMap;
			this.conditionallyMappedElementMap = conditionallyMappedElementMap;
			this.mapper = mapper;
		}

		@Override
		public ReplacingGlueFunction<T> apply(final StringBuilder stringBuilder, final T value)
		{
			if (ignoredElementSet.contains(value))
				return this;
			for (final Condition1<? super T> condition : conditionToBeIgnoredSet)
			{
				if (condition.isMet(value))
				{
					return this;
				}
			}

			addSeparatorIfNecessary(stringBuilder);

			if (replacedElementMap.containsKey(value))
			{
				final Object newObject = replacedElementMap.get(value);
				stringBuilder.append(newObject);
				return this;
			}

			for (final Entry<Condition1<? super T>, Object> entry : conditionallyReplacedElementMap.entrySet())
			{
				if (entry.getKey()
						.isMet(value))
				{
					stringBuilder.append(entry.getValue());
					return this;
				}
			}

			final Function1<? super T, ? extends T> function = elementMapperMap.get(value);
			if (null != function)
			{
				stringBuilder.append(function.apply(value));
				return this;
			}

			for (final Entry<Condition1<? super T>, Function1<? super T, ? extends T>> entry : conditionallyMappedElementMap.entrySet())
			{
				if (entry.getKey()
						.isMet(value))
				{
					final T newValue = entry.getValue()
							.apply(value);
					stringBuilder.append(newValue);
					return this;
				}
			}

			if (null != mapper)
			{
				stringBuilder.append(mapper.apply(value));
				return this;
			}

			stringBuilder.append(value);
			return this;
		}

		protected abstract void addSeparatorIfNecessary(StringBuilder stringBuilder);
	}

	private static final class ReplacingGlueFunctionWithSeparator<T> extends ReplacingGlueFunction<T> implements
			GlueFunctionWithSeparator<T>
	{
		private final String separator;

		public ReplacingGlueFunctionWithSeparator(final Set<Object> ignoredElementSet,
				final Set<Condition1<? super T>> conditionToBeIgnoredSet, final Map<Object, Object> replacedElementMap,
				final Map<Condition1<? super T>, Object> conditionallyReplacedElementMap,
				final Map<Object, Function1<? super T, ? extends T>> elementMapperMap,
				final Map<Condition1<? super T>, Function1<? super T, ? extends T>> conditionallyMappedElementMap,
				final Function1<? super T, ? extends T> mapper, final String separator)
		{
			super(ignoredElementSet, conditionToBeIgnoredSet, replacedElementMap, conditionallyReplacedElementMap,
					elementMapperMap, conditionallyMappedElementMap, mapper);
			this.separator = separator;
		}

		@Override
		public String getSeparator()
		{
			return separator;
		}

		@Override
		public ReplacingGlueFunctionWithSeparator<T> apply(final StringBuilder stringBuilder, final T value)
		{
			super.apply(stringBuilder, value);
			return this;
		}

		@Override
		protected void addSeparatorIfNecessary(final StringBuilder stringBuilder)
		{
			if (0 != stringBuilder.length())
			{
				/* it is not the first element so add the separator. */
				stringBuilder.append(separator);
			}
		}
	}

	private static final class ReplacingGlueFunctionWithoutSeparator<T> extends ReplacingGlueFunction<T> implements
			GlueFunctionWithoutSeparator<T>
	{
		public ReplacingGlueFunctionWithoutSeparator(final Set<Object> ignoredElementSet,
				final Set<Condition1<? super T>> conditionToBeIgnoredSet, final Map<Object, Object> replacedElementMap,
				final Map<Condition1<? super T>, Object> conditionallyReplacedElementMap,
				final Map<Object, Function1<? super T, ? extends T>> elementMapperMap,
				final Map<Condition1<? super T>, Function1<? super T, ? extends T>> conditionallyMappedElementMap,
				final Function1<? super T, ? extends T> mapper)
		{
			super(ignoredElementSet, conditionToBeIgnoredSet, replacedElementMap, conditionallyReplacedElementMap,
					elementMapperMap, conditionallyMappedElementMap, mapper);
		}

		@Override
		public ReplacingGlueFunctionWithoutSeparator<T> apply(final StringBuilder stringBuilder, final T value)
		{
			super.apply(stringBuilder, value);
			return this;
		}

		@Override
		protected void addSeparatorIfNecessary(@SuppressWarnings("unused") final StringBuilder stringBuilder)
		{
			/* do nothing! */
		}
	}

	private static final class IterableToStringGlueWithoutSeparator<T> implements IterableToStringGlue<T>
	{
		private final GlueFunctionWithoutSeparator<T> glueFunctionWithoutSeparator;

		private IterableToStringGlueWithoutSeparator(final GlueFunctionWithoutSeparator<T> glueFunction)
		{
			this.glueFunctionWithoutSeparator = glueFunction;
		}

		@Override
		public String glue(final Iterable<? extends T> source)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			for (final T value : source)
			{
				glueFunctionWithoutSeparator.apply(stringBuilder, value);
			}
			return stringBuilder.toString();
		}
	}

	private static final class IterableToStringGlueWithSeparator<T> implements IterableToStringGlue<T>
	{
		private final GlueFunctionWithSeparator<T> glueFunctionWithSeparator;

		private IterableToStringGlueWithSeparator(final GlueFunctionWithSeparator<T> glueFunctionWithSeparator)
		{
			this.glueFunctionWithSeparator = glueFunctionWithSeparator;
		}

		@Override
		public String glue(final Iterable<? extends T> source)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			for (final T value : source)
			{
				glueFunctionWithSeparator.apply(stringBuilder, value);
			}
			return stringBuilder.toString();
		}
	}

	private static class ArgsToStringGlueWithSeparator<E> implements ArgsToStringGlue<E>
	{
		private final GlueFunctionWithSeparator<E> glueFunctionWithSeparator;

		private ArgsToStringGlueWithSeparator(final GlueFunctionWithSeparator<E> glueFunctionWithSeparator)
		{
			this.glueFunctionWithSeparator = glueFunctionWithSeparator;
		}

		@Override
		public String glue(final E[] values)
		{
			return glue0(values);
		}

		private <T extends E> String glue0(final T[] values)
		{
			final StringBuilder stringBuilder = new StringBuilder();

			for (final T value : values)
			{
				glueFunctionWithSeparator.apply(stringBuilder, value);
			}
			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			/* @formatter:off */
			glueFunctionWithSeparator.apply(stringBuilder, value1)
									 .apply(stringBuilder, value2);
			/* @formatter:on */
			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2, final T value3)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			/* @formatter:off */
			glueFunctionWithSeparator.apply(stringBuilder, value1)
			 						 .apply(stringBuilder, value2)
									 .apply(stringBuilder, value3);
			/* @formatter:on */
			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2, final T value3, final T value4)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			/* @formatter:off */
			glueFunctionWithSeparator.apply(stringBuilder, value1)
									 .apply(stringBuilder, value2)
									 .apply(stringBuilder, value3)
									 .apply(stringBuilder, value4);
			/* @formatter:on */
			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2, final T value3, final T value4, final T value5)
		{
			return glue0(value1, value2, value3, value4, value5);
		}

		private <T extends E> String glue0(final T value1, final T value2, final T value3, final T value4, final T value5)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			/* @formatter:off */
			glueFunctionWithSeparator.apply(stringBuilder, value1)
									 .apply(stringBuilder, value2)
									 .apply(stringBuilder, value3)
									 .apply(stringBuilder, value4)
									 .apply(stringBuilder, value5);
			/* @formatter:on */
			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2, final T value3, final T value4, final T value5,
				final T... rest)
		{
			final StringBuilder stringBuilder = new StringBuilder(glue0(value1, value2, value3, value4, value5));
			for (final T value : rest)
			{
				glueFunctionWithSeparator.apply(stringBuilder, value);
			}
			return stringBuilder.toString();
		}
	}

	private static class ArgsToStringGlueWithoutSeparator<E> implements ArgsToStringGlue<E>
	{
		private final GlueFunctionWithoutSeparator<E> glueFunctionWithoutSeparator;

		private ArgsToStringGlueWithoutSeparator(final GlueFunctionWithoutSeparator<E> glueFunction)
		{
			this.glueFunctionWithoutSeparator = glueFunction;
		}

		@Override
		public String glue(final E[] values)
		{
			return glue0(values);
		}

		private String glue0(final E[] values)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			for (final E value : values)
				glueFunctionWithoutSeparator.apply(stringBuilder, value);

			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			/* @formatter:off */
			glueFunctionWithoutSeparator.apply(stringBuilder, value1)
						.apply(stringBuilder, value2);
			/* @formatter:on */
			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2, final T value3)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			/* @formatter:off */
			glueFunctionWithoutSeparator.apply(stringBuilder, value1)
						.apply(stringBuilder, value2)
						.apply(stringBuilder, value3);
			/* @formatter:on */
			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2, final T value3, final T value4)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			/* @formatter:off */
			glueFunctionWithoutSeparator.apply(stringBuilder, value1)
						.apply(stringBuilder, value2)
						.apply(stringBuilder, value3)
						.apply(stringBuilder, value4);
			/* @formatter:on */
			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2, final T value3, final T value4, final T value5)
		{
			return glue0(value1, value2, value3, value4, value5);
		}

		private <T extends E> String glue0(final T value1, final T value2, final T value3, final T value4, final T value5)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			/* @formatter:off */
			glueFunctionWithoutSeparator.apply(stringBuilder, value1)
						.apply(stringBuilder, value2)
						.apply(stringBuilder, value3)
						.apply(stringBuilder, value4)
						.apply(stringBuilder, value5);
			/* @formatter:on */
			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2, final T value3, final T value4, final T value5,
				final T... rest)
		{
			/* @formatter:off */
			return new StringBuilder(glue0(value1, value2, value3, value4, value5))
						.append(glue0(rest))
					.toString();
			/* @formatter:on */
		}
	}
}