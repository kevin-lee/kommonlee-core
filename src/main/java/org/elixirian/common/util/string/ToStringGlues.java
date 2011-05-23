/**
 * 
 */
package org.elixirian.common.util.string;

import static org.elixirian.common.util.MessageFormatter.*;
import static org.elixirian.common.util.Objects.*;
import static org.elixirian.common.util.Strings.*;
import static java.util.Collections.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.elixirian.common.type.Function2;
import org.elixirian.common.type.GenericBuilder;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-14)
 */
public final class ToStringGlues
{
	private ToStringGlues()
	{
	}

	private static abstract class Builder<E, T extends ToStringGlue<?>> implements GenericBuilder<T>
	{
		protected Set<Object> ignoredElementSet;
		protected Map<Object, Object> replacedElementMap;
		protected String separator;

		public Builder()
		{
			this.ignoredElementSet = new HashSet<Object>();
			this.replacedElementMap = new HashMap<Object, Object>();
		}

		public Builder<E, T> ignoreNull()
		{
			return ignore(null);
		}

		public <N extends E> Builder<E, T> replaceNull(N toReplace)
		{
			return replace(null, toReplace);
		}

		public Builder<E, T> ignore(E toBeIgnored)
		{
			if (replacedElementMap.containsKey(toBeIgnored))
			{
				throw new IllegalArgumentException(format("%s is already set to be replaced by another element. "
						+ "You cannot have one element to be both ignored and replaced.", toBeIgnored));
			}
			ignoredElementSet.add(toBeIgnored);
			return this;
		}

		public <N extends E> Builder<E, T> replace(E toBeReplaced, N by)
		{
			if (ignoredElementSet.contains(toBeReplaced))
			{
				throw new IllegalArgumentException(format("%s is already set to be ignored. "
						+ "You cannot have one element to be both ignored and replaced by another %s.", toBeReplaced, by));
			}
			replacedElementMap.put(toBeReplaced, by);
			return this;
		}

		public Builder<E, T> withSeparator(String separator)
		{
			this.separator = separator;
			return this;
		}

		protected GlueFunctionWithSeparator<E> whichGlueFunction()
		{
			/* @formatter:off */
			return (ignoredElementSet.isEmpty() && replacedElementMap.isEmpty()) ?
						new SimpleGlueFunctionWithSeparator<E>(separator) :
						new ReplacingGlueFunctionWithSeparator<E>(unmodifiableSet(ignoredElementSet),
																  unmodifiableMap(replacedElementMap), separator);
			/* @formatter:on */
		}

		protected GlueFunctionWithoutSeparator<E> whichGlueFunctionWithoutSeparator()
		{
			/* @formatter:off */
			return (ignoredElementSet.isEmpty() && replacedElementMap.isEmpty()) ?
						new SimpleGlueFunctionWithoutSeparator<E>() :
						new ReplacingGlueFunctionWithoutSeparator<E>(unmodifiableSet(ignoredElementSet),
																	 unmodifiableMap(replacedElementMap));
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
		public <N extends E> ForIterableBuilder<E> replaceNull(N toReplace)
		{
			super.replaceNull(toReplace);
			return this;
		}

		@Override
		public ForIterableBuilder<E> ignore(E toBeIgnored)
		{
			super.ignore(toBeIgnored);
			return this;
		}

		@Override
		public <N extends E> ForIterableBuilder<E> replace(E toBeReplaced, N by)
		{
			super.replace(toBeReplaced, by);
			return this;
		}

		@Override
		public ForIterableBuilder<E> withSeparator(String separator)
		{
			super.withSeparator(separator);
			return this;
		}

		@Override
		public IterableToStringGlue<E> build()
		{
			final GlueFunctionWithoutSeparator<E> glueFunctionWithoutSeparator = whichGlueFunctionWithoutSeparator();
			final GlueFunctionWithSeparator<E> glueFunctionWithSeparator = whichGlueFunction();
			/* @formatter:off */
			return isEmpty(separator) ?
					new IterableToStringGlueWithoutSeparator<E>(glueFunctionWithoutSeparator) :
					new IterableToStringGlueWithSeparator<E>(glueFunctionWithSeparator);
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
		public <N extends E> ForArgsBuilder<E> replaceNull(N toReplace)
		{
			super.replaceNull(toReplace);
			return this;
		}

		@Override
		public ForArgsBuilder<E> ignore(E toBeIgnored)
		{
			super.ignore(toBeIgnored);
			return this;
		}

		@Override
		public <N extends E> ForArgsBuilder<E> replace(E toBeReplaced, N by)
		{
			super.replace(toBeReplaced, by);
			return this;
		}

		@Override
		public ForArgsBuilder<E> withSeparator(String separator)
		{
			super.withSeparator(separator);
			return this;
		}

		@Override
		public ArgsToStringGlue<E> build()
		{
			final GlueFunctionWithoutSeparator<E> glueFunctionWithoutSeparator = whichGlueFunctionWithoutSeparator();
			final GlueFunctionWithSeparator<E> glueFunctionWithSeparator = whichGlueFunction();
			return isEmpty(separator) ? new ArgsToStringGlueWithoutSeparator<E>(glueFunctionWithoutSeparator)
					: new ArgsToStringGlueWithSeparator<E>(glueFunctionWithSeparator);
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

	private interface GlueFunction<T> extends Function2<StringBuilder, T, GlueFunction<T>>
	{
		@Override
		GlueFunction<T> perform(StringBuilder stringBuilder, T value);
	}

	private interface GlueFunctionWithoutSeparator<T> extends GlueFunction<T>
	{
		@Override
		GlueFunctionWithoutSeparator<T> perform(StringBuilder stringBuilder, T value);
	}

	private interface GlueFunctionWithSeparator<T> extends GlueFunction<T>
	{
		@Override
		GlueFunctionWithSeparator<T> perform(StringBuilder stringBuilder, T value);

		String getSeparator();
	}

	private static abstract class SimpleGlueFunction<T> implements GlueFunction<T>
	{
		@Override
		public abstract SimpleGlueFunction<T> perform(StringBuilder stringBuilder, T value);
	}

	private static final class SimpleGlueFunctionWithSeparator<T> extends SimpleGlueFunction<T> implements
			GlueFunctionWithSeparator<T>
	{
		private String separator;

		private SimpleGlueFunctionWithSeparator(String separator)
		{
			this.separator = separator;
		}

		@Override
		public SimpleGlueFunctionWithSeparator<T> perform(final StringBuilder stringBuilder, final T value)
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
		public SimpleGlueFunctionWithoutSeparator<T> perform(final StringBuilder stringBuilder, final T value)
		{
			stringBuilder.append(value);
			return this;
		}
	}

	private static abstract class ReplacingGlueFunction<T> implements GlueFunction<T>
	{
		protected final Set<Object> ignoredElementSet;
		protected final Map<Object, Object> replacedElementMap;

		protected ReplacingGlueFunction(Set<Object> ignoredElementSet, Map<Object, Object> replacedElementMap)
		{
			this.ignoredElementSet = ignoredElementSet;
			this.replacedElementMap = replacedElementMap;
		}

		@Override
		public abstract ReplacingGlueFunction<T> perform(StringBuilder stringBuilder, T value);
	}

	private static final class ReplacingGlueFunctionWithSeparator<T> extends ReplacingGlueFunction<T> implements
			GlueFunctionWithSeparator<T>
	{
		private final String separator;

		private ReplacingGlueFunctionWithSeparator(Set<Object> ignoredElementSet, Map<Object, Object> replacedElementMap,
				String separator)
		{
			super(ignoredElementSet, replacedElementMap);
			this.separator = separator;
		}

		@Override
		public ReplacingGlueFunctionWithSeparator<T> perform(final StringBuilder stringBuilder, final T value)
		{
			if (ignoredElementSet.contains(value))
				return this;

			final Object object = replacedElementMap.get(value);
			if (0 != stringBuilder.length())
			{
				/* it is not the first element so add the separator. */
				stringBuilder.append(separator);
			}
			stringBuilder.append(isNotNull(object) ? object : value);
			return this;
		}

		@Override
		public String getSeparator()
		{
			return separator;
		}
	}

	private static final class ReplacingGlueFunctionWithoutSeparator<T> extends ReplacingGlueFunction<T> implements
			GlueFunctionWithoutSeparator<T>
	{

		private ReplacingGlueFunctionWithoutSeparator(Set<Object> ignoredElementSet, Map<Object, Object> replacedElementMap)
		{
			super(ignoredElementSet, replacedElementMap);
		}

		@Override
		public ReplacingGlueFunctionWithoutSeparator<T> perform(final StringBuilder stringBuilder, final T value)
		{
			if (ignoredElementSet.contains(value))
				return this;

			final Object object = replacedElementMap.get(value);
			stringBuilder.append(isNotNull(object) ? object : value);
			return this;
		}
	}

	private static final class IterableToStringGlueWithoutSeparator<T> implements IterableToStringGlue<T>
	{
		private final GlueFunctionWithoutSeparator<T> glueFunctionWithoutSeparator;

		private IterableToStringGlueWithoutSeparator(GlueFunctionWithoutSeparator<T> glueFunction)
		{
			this.glueFunctionWithoutSeparator = glueFunction;
		}

		@Override
		public String glue(Iterable<? extends T> source)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			for (final T value : source)
			{
				glueFunctionWithoutSeparator.perform(stringBuilder, value);
			}
			return stringBuilder.toString();
		}
	}

	private static final class IterableToStringGlueWithSeparator<T> implements IterableToStringGlue<T>
	{
		private final GlueFunctionWithSeparator<T> glueFunctionWithSeparator;

		private IterableToStringGlueWithSeparator(GlueFunctionWithSeparator<T> glueFunctionWithSeparator)
		{
			this.glueFunctionWithSeparator = glueFunctionWithSeparator;
		}

		@Override
		public String glue(Iterable<? extends T> source)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			for (final T value : source)
			{
				glueFunctionWithSeparator.perform(stringBuilder, value);
			}
			return stringBuilder.toString();
		}
	}

	private static class ArgsToStringGlueWithSeparator<E> implements ArgsToStringGlue<E>
	{
		private final GlueFunctionWithSeparator<E> glueFunctionWithSeparator;

		private ArgsToStringGlueWithSeparator(GlueFunctionWithSeparator<E> glueFunctionWithSeparator)
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
				glueFunctionWithSeparator.perform(stringBuilder, value);
			}
			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			/* @formatter:off */
			glueFunctionWithSeparator.perform(stringBuilder, value1)
									 .perform(stringBuilder, value2);
			/* @formatter:on */
			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2, final T value3)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			/* @formatter:off */
			glueFunctionWithSeparator.perform(stringBuilder, value1)
			 						 .perform(stringBuilder, value2)
									 .perform(stringBuilder, value3);
			/* @formatter:on */
			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2, final T value3, final T value4)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			/* @formatter:off */
			glueFunctionWithSeparator.perform(stringBuilder, value1)
									 .perform(stringBuilder, value2)
									 .perform(stringBuilder, value3)
									 .perform(stringBuilder, value4);
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
			glueFunctionWithSeparator.perform(stringBuilder, value1)
									 .perform(stringBuilder, value2)
									 .perform(stringBuilder, value3)
									 .perform(stringBuilder, value4)
									 .perform(stringBuilder, value5);
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
				glueFunctionWithSeparator.perform(stringBuilder, value);
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
				glueFunctionWithoutSeparator.perform(stringBuilder, value);

			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			/* @formatter:off */
			glueFunctionWithoutSeparator.perform(stringBuilder, value1)
						.perform(stringBuilder, value2);
			/* @formatter:on */
			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2, final T value3)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			/* @formatter:off */
			glueFunctionWithoutSeparator.perform(stringBuilder, value1)
						.perform(stringBuilder, value2)
						.perform(stringBuilder, value3);
			/* @formatter:on */
			return stringBuilder.toString();
		}

		@Override
		public <T extends E> String glue(final T value1, final T value2, final T value3, final T value4)
		{
			final StringBuilder stringBuilder = new StringBuilder();
			/* @formatter:off */
			glueFunctionWithoutSeparator.perform(stringBuilder, value1)
						.perform(stringBuilder, value2)
						.perform(stringBuilder, value3)
						.perform(stringBuilder, value4);
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
			glueFunctionWithoutSeparator.perform(stringBuilder, value1)
						.perform(stringBuilder, value2)
						.perform(stringBuilder, value3)
						.perform(stringBuilder, value4)
						.perform(stringBuilder, value5);
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