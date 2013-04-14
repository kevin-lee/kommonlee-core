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
import static org.elixirian.kommonlee.util.Objects.isNotNull;
import static org.elixirian.kommonlee.util.Strings.isNullOrEmptyString;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.elixirian.kommonlee.type.GenericBuilder;
import org.elixirian.kommonlee.util.string.ToStringGlues.GlueFunction;
import org.elixirian.kommonlee.util.string.ToStringGlues.GlueFunctionWithSeparator;
import org.elixirian.kommonlee.util.string.ToStringGlues.GlueFunctionWithoutSeparator;

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
public final class StringGlues
{
  private StringGlues()
  {
    // TODO finish it
  }

  private static abstract class Builder<T extends ToStringGlue<?>> implements GenericBuilder<T>
  {
    protected Set<String> ignoredElementSet;
    protected Map<String, String> replacedElementMap;
    protected String separator;

    public Builder()
    {
      this.ignoredElementSet = new HashSet<String>();
      this.replacedElementMap = new HashMap<String, String>();
    }

    public Builder<T> ignoreNull()
    {
      return ignore(null);
    }

    public Builder<T> replaceNull(final String toReplace)
    {
      return replace(null, toReplace);
    }

    public Builder<T> ignore(final String toBeIgnored)
    {
      if (replacedElementMap.containsKey(toBeIgnored))
      {
        throw new IllegalArgumentException(format("%s is already set to be replaced by another element. "
            + "You cannot have one element to be both ignored and replaced.", toBeIgnored));
      }
      ignoredElementSet.add(toBeIgnored);
      return this;
    }

    public Builder<T> replace(final String toBeReplaced, final String by)
    {
      if (ignoredElementSet.contains(toBeReplaced))
      {
        throw new IllegalArgumentException(format("%s is already set to be ignored. "
            + "You cannot have one element to be both ignored and replaced by another %s.", toBeReplaced, by));
      }
      replacedElementMap.put(toBeReplaced, by);
      return this;
    }

    public Builder<T> withSeparator(final String separator)
    {
      this.separator = separator;
      return this;
    }

    protected StringGlueFunctionWithSeparator whichGlueFunction()
    {
      /* @formatter:off */
			return (ignoredElementSet.isEmpty() && replacedElementMap.isEmpty()) ?
						new SimpleStringGlueFunctionWithSeparator(separator) :
						new ReplacingStringGlueFunctionWithSeparator(unmodifiableSet(ignoredElementSet),
																  unmodifiableMap(replacedElementMap), separator);
			/* @formatter:on */
    }

    protected StringGlueFunctionWithoutSeparator whichGlueFunctionWithoutSeparator()
    {
      /* @formatter:off */
			return (ignoredElementSet.isEmpty() && replacedElementMap.isEmpty()) ?
						new SimpleStringGlueFunctionWithoutSeparator() :
						new ReplacingStringGlueFunctionWithoutSeparator(unmodifiableSet(ignoredElementSet),
																	 unmodifiableMap(replacedElementMap));
			/* @formatter:on */
    }

    @Override
    public abstract T build();
  }

  public static final class ForIterableBuilder extends Builder<StringIterableToStringGlue>
  {
    @Override
    public ForIterableBuilder ignoreNull()
    {
      super.ignoreNull();
      return this;
    }

    @Override
    public ForIterableBuilder replaceNull(final String toReplace)
    {
      super.replaceNull(toReplace);
      return this;
    }

    @Override
    public ForIterableBuilder ignore(final String toBeIgnored)
    {
      super.ignore(toBeIgnored);
      return this;
    }

    @Override
    public ForIterableBuilder replace(final String toBeReplaced, final String by)
    {
      super.replace(toBeReplaced, by);
      return this;
    }

    @Override
    public ForIterableBuilder withSeparator(final String separator)
    {
      super.withSeparator(separator);
      return this;
    }

    @Override
    public StringIterableToStringGlue build()
    {
      final StringGlueFunctionWithoutSeparator glueFunctionWithoutSeparator = whichGlueFunctionWithoutSeparator();
      final StringGlueFunctionWithSeparator glueFunctionWithSeparator = whichGlueFunction();
      /* @formatter:off */
			return isNullOrEmptyString(separator) ?
					new IterableToStringGlueWithoutSeparator(glueFunctionWithoutSeparator) :
					new StringIterableToStringGlueWithSeparator(glueFunctionWithSeparator);
			/* @formatter:on */
    }
  }

  public static final class ForArgsBuilder extends Builder<StringArgsToStringGlue>
  {
    @Override
    public ForArgsBuilder ignoreNull()
    {
      super.ignoreNull();
      return this;
    }

    @Override
    public ForArgsBuilder replaceNull(final String toReplace)
    {
      super.replaceNull(toReplace);
      return this;
    }

    @Override
    public ForArgsBuilder ignore(final String toBeIgnored)
    {
      super.ignore(toBeIgnored);
      return this;
    }

    @Override
    public ForArgsBuilder replace(final String toBeReplaced, final String by)
    {
      super.replace(toBeReplaced, by);
      return this;
    }

    @Override
    public ForArgsBuilder withSeparator(final String separator)
    {
      super.withSeparator(separator);
      return this;
    }

    @Override
    public StringArgsToStringGlue build()
    {
      final StringGlueFunctionWithoutSeparator glueFunctionWithoutSeparator = whichGlueFunctionWithoutSeparator();
      final StringGlueFunctionWithSeparator glueFunctionWithSeparator = whichGlueFunction();
      return isNullOrEmptyString(separator) ? new StringArgsToStringGlueWithoutSeparator(glueFunctionWithoutSeparator)
          : new StringArgsToStringGlueWithSeparator(glueFunctionWithSeparator);
    }
  }

  public static ForIterableBuilder builderForIterable()
  {
    return new ForIterableBuilder();
  }

  public static ForArgsBuilder builderForArgs()
  {
    return new ForArgsBuilder();
  }

  private interface StringGlueFunctionWithoutSeparator extends GlueFunctionWithoutSeparator<String>
  {
    @Override
    StringGlueFunctionWithoutSeparator apply(StringBuilder stringBuilder, String value);
  }

  private interface StringGlueFunctionWithSeparator extends GlueFunctionWithSeparator<String>
  {
    @Override
    StringGlueFunctionWithSeparator apply(StringBuilder stringBuilder, String value);

    @Override
    String getSeparator();
  }

  private static abstract class SimpleStringGlueFunction implements GlueFunction<String>
  {
    @Override
    public abstract SimpleStringGlueFunction apply(StringBuilder stringBuilder, String value);
  }

  private static final class SimpleStringGlueFunctionWithSeparator extends SimpleStringGlueFunction implements
      StringGlueFunctionWithSeparator
  {
    private String separator;

    private SimpleStringGlueFunctionWithSeparator(final String separator)
    {
      this.separator = separator;
    }

    @Override
    public SimpleStringGlueFunctionWithSeparator apply(final StringBuilder stringBuilder, final String value)
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

  private static final class SimpleStringGlueFunctionWithoutSeparator extends SimpleStringGlueFunction implements
      StringGlueFunctionWithoutSeparator
  {
    @Override
    public SimpleStringGlueFunctionWithoutSeparator apply(final StringBuilder stringBuilder, final String value)
    {
      stringBuilder.append(value);
      return this;
    }
  }

  private static abstract class ReplacingStringGlueFunction implements GlueFunction<String>
  {
    protected final Set<String> ignoredElementSet;
    protected final Map<String, String> replacedElementMap;

    protected ReplacingStringGlueFunction(final Set<String> ignoredElementSet,
        final Map<String, String> replacedElementMap)
    {
      this.ignoredElementSet = ignoredElementSet;
      this.replacedElementMap = replacedElementMap;
    }

    @Override
    public abstract ReplacingStringGlueFunction apply(StringBuilder stringBuilder, String value);
  }

  private static final class ReplacingStringGlueFunctionWithSeparator extends ReplacingStringGlueFunction implements
      StringGlueFunctionWithSeparator
  {
    private final String separator;

    private ReplacingStringGlueFunctionWithSeparator(final Set<String> ignoredElementSet,
        final Map<String, String> replacedElementMap, final String separator)
    {
      super(ignoredElementSet, replacedElementMap);
      this.separator = separator;
    }

    @Override
    public ReplacingStringGlueFunctionWithSeparator apply(final StringBuilder stringBuilder, final String value)
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

  private static final class ReplacingStringGlueFunctionWithoutSeparator extends ReplacingStringGlueFunction implements
      StringGlueFunctionWithoutSeparator
  {

    private ReplacingStringGlueFunctionWithoutSeparator(final Set<String> ignoredElementSet,
        final Map<String, String> replacedElementMap)
    {
      super(ignoredElementSet, replacedElementMap);
    }

    @Override
    public ReplacingStringGlueFunctionWithoutSeparator apply(final StringBuilder stringBuilder, final String value)
    {
      if (ignoredElementSet.contains(value))
        return this;

      final Object object = replacedElementMap.get(value);
      stringBuilder.append(isNotNull(object) ? object : value);
      return this;
    }
  }

  private static final class IterableToStringGlueWithoutSeparator implements StringIterableToStringGlue
  {
    private final StringGlueFunctionWithoutSeparator glueFunctionWithoutSeparator;

    private IterableToStringGlueWithoutSeparator(final StringGlueFunctionWithoutSeparator glueFunction)
    {
      this.glueFunctionWithoutSeparator = glueFunction;
    }

    @Override
    public String glue(final Iterable<String> source)
    {
      final StringBuilder stringBuilder = new StringBuilder();
      for (final String value : source)
      {
        glueFunctionWithoutSeparator.apply(stringBuilder, value);
      }
      return stringBuilder.toString();
    }
  }

  private static final class StringIterableToStringGlueWithSeparator implements StringIterableToStringGlue
  {
    private final StringGlueFunctionWithSeparator glueFunctionWithSeparator;

    private StringIterableToStringGlueWithSeparator(final StringGlueFunctionWithSeparator glueFunctionWithSeparator)
    {
      this.glueFunctionWithSeparator = glueFunctionWithSeparator;
    }

    @Override
    public String glue(final Iterable<String> source)
    {
      final StringBuilder stringBuilder = new StringBuilder();
      for (final String value : source)
      {
        glueFunctionWithSeparator.apply(stringBuilder, value);
      }
      return stringBuilder.toString();
    }
  }

  private static class StringArgsToStringGlueWithSeparator implements StringArgsToStringGlue
  {
    private final StringGlueFunctionWithSeparator glueFunctionWithSeparator;

    private StringArgsToStringGlueWithSeparator(final StringGlueFunctionWithSeparator glueFunctionWithSeparator)
    {
      this.glueFunctionWithSeparator = glueFunctionWithSeparator;
    }

    @Override
    public String glue(final String[] values)
    {
      return glue0(values);
    }

    private String glue0(final String[] values)
    {
      final StringBuilder stringBuilder = new StringBuilder();

      for (final String value : values)
      {
        glueFunctionWithSeparator.apply(stringBuilder, value);
      }
      return stringBuilder.toString();
    }

    @Override
    public String glue(final String value1, final String value2)
    {
      final StringBuilder stringBuilder = new StringBuilder();
      /* @formatter:off */
			glueFunctionWithSeparator.apply(stringBuilder, value1)
									 .apply(stringBuilder, value2);
			/* @formatter:on */
      return stringBuilder.toString();
    }

    @Override
    public String glue(final String value1, final String value2, final String value3)
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
    public String glue(final String value1, final String value2, final String value3, final String value4)
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
    public String glue(final String value1, final String value2, final String value3, final String value4,
        final String value5)
    {
      return glue0(value1, value2, value3, value4, value5);
    }

    private String glue0(final String value1, final String value2, final String value3, final String value4,
        final String value5)
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
    public String glue(final String value1, final String value2, final String value3, final String value4,
        final String value5, final String... rest)
    {
      final StringBuilder stringBuilder = new StringBuilder(glue0(value1, value2, value3, value4, value5));
      for (final String value : rest)
      {
        glueFunctionWithSeparator.apply(stringBuilder, value);
      }
      return stringBuilder.toString();
    }
  }

  private static class StringArgsToStringGlueWithoutSeparator implements StringArgsToStringGlue
  {
    private final StringGlueFunctionWithoutSeparator glueFunctionWithoutSeparator;

    private StringArgsToStringGlueWithoutSeparator(final StringGlueFunctionWithoutSeparator glueFunction)
    {
      this.glueFunctionWithoutSeparator = glueFunction;
    }

    @Override
    public String glue(final String[] values)
    {
      return glue0(values);
    }

    private String glue0(final String[] values)
    {
      final StringBuilder stringBuilder = new StringBuilder();
      for (final String value : values)
        glueFunctionWithoutSeparator.apply(stringBuilder, value);

      return stringBuilder.toString();
    }

    @Override
    public String glue(final String value1, final String value2)
    {
      final StringBuilder stringBuilder = new StringBuilder();
      /* @formatter:off */
			glueFunctionWithoutSeparator.apply(stringBuilder, value1)
						.apply(stringBuilder, value2);
			/* @formatter:on */
      return stringBuilder.toString();
    }

    @Override
    public String glue(final String value1, final String value2, final String value3)
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
    public String glue(final String value1, final String value2, final String value3, final String value4)
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
    public String glue(final String value1, final String value2, final String value3, final String value4,
        final String value5)
    {
      return glue0(value1, value2, value3, value4, value5);
    }

    private String glue0(final String value1, final String value2, final String value3, final String value4,
        final String value5)
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
    public String glue(final String value1, final String value2, final String value3, final String value4,
        final String value5, final String... rest)
    {
      /* @formatter:off */
			return new StringBuilder(glue0(value1, value2, value3, value4, value5))
						.append(glue0(rest))
					.toString();
			/* @formatter:on */
    }
  }

}