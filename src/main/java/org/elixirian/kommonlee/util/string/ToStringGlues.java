/**
 * 
 */
package org.elixirian.kommonlee.util.string;

import static java.util.Collections.*;
import static org.elixirian.kommonlee.util.MessageFormatter.*;
import static org.elixirian.kommonlee.util.Objects.*;
import static org.elixirian.kommonlee.util.Strings.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.elixirian.kommonlee.type.GenericBuilder;
import org.elixirian.kommonlee.type.function.Function2;

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
      ignoredElementSet.add(toBeIgnored);
      return this;
    }

    public <N extends E> Builder<E, T> replace(final E toBeReplaced, final N by)
    {
      if (ignoredElementSet.contains(toBeReplaced))
      {
        throw new IllegalArgumentException(format("%s is already set to be ignored. "
            + "You cannot have one element to be both ignored and replaced by another %s.", toBeReplaced, by));
      }
      replacedElementMap.put(toBeReplaced, by);
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
    public ForIterableBuilder<E> withSeparator(final String separator)
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
    public ForArgsBuilder<E> withSeparator(final String separator)
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
    GlueFunction<T> apply(StringBuilder stringBuilder, T value);
  }

  private interface GlueFunctionWithoutSeparator<T> extends GlueFunction<T>
  {
    @Override
    GlueFunctionWithoutSeparator<T> apply(StringBuilder stringBuilder, T value);
  }

  private interface GlueFunctionWithSeparator<T> extends GlueFunction<T>
  {
    @Override
    GlueFunctionWithSeparator<T> apply(StringBuilder stringBuilder, T value);

    String getSeparator();
  }

  private static abstract class SimpleGlueFunction<T> implements GlueFunction<T>
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

  private static abstract class ReplacingGlueFunction<T> implements GlueFunction<T>
  {
    protected final Set<Object> ignoredElementSet;
    protected final Map<Object, Object> replacedElementMap;

    protected ReplacingGlueFunction(final Set<Object> ignoredElementSet, final Map<Object, Object> replacedElementMap)
    {
      this.ignoredElementSet = ignoredElementSet;
      this.replacedElementMap = replacedElementMap;
    }

    @Override
    public abstract ReplacingGlueFunction<T> apply(StringBuilder stringBuilder, T value);
  }

  private static final class ReplacingGlueFunctionWithSeparator<T> extends ReplacingGlueFunction<T> implements
      GlueFunctionWithSeparator<T>
  {
    private final String separator;

    private ReplacingGlueFunctionWithSeparator(final Set<Object> ignoredElementSet,
        final Map<Object, Object> replacedElementMap, final String separator)
    {
      super(ignoredElementSet, replacedElementMap);
      this.separator = separator;
    }

    @Override
    public ReplacingGlueFunctionWithSeparator<T> apply(final StringBuilder stringBuilder, final T value)
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

    private ReplacingGlueFunctionWithoutSeparator(final Set<Object> ignoredElementSet,
        final Map<Object, Object> replacedElementMap)
    {
      super(ignoredElementSet, replacedElementMap);
    }

    @Override
    public ReplacingGlueFunctionWithoutSeparator<T> apply(final StringBuilder stringBuilder, final T value)
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