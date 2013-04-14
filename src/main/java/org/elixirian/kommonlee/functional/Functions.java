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

import java.util.Comparator;

import org.elixirian.kommonlee.functional.string.NotEmptyStringCondition;
import org.elixirian.kommonlee.functional.string.PrefixAndSuffixAdder;
import org.elixirian.kommonlee.functional.string.StringArrayToTrimmedStringListSelector;
import org.elixirian.kommonlee.functional.string.StringArrayTrimmer;
import org.elixirian.kommonlee.functional.string.ToLowerCaseConverter;
import org.elixirian.kommonlee.type.Pair;
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
  public static class SimplePair<L, R> implements Pair<L, R>
  {
    private final L first;
    private final R second;

    public SimplePair(final L first, final R second)
    {
      this.first = first;
      this.second = second;
    }

    @Override
    public L getValue1()
    {
      return first;
    }

    @Override
    public R getValue2()
    {
      return second;
    }

    @Override
    public int hashCode()
    {
      return hash(first, second);
    }

    @Override
    public boolean equals(final Object pair)
    {
      if (this == pair)
      {
        return true;
      }
      final SimplePair<?, ?> that = castIfInstanceOf(SimplePair.class, pair);
      /* @formatter:off */
      return null != that &&
             (equal(this.first,  that.getValue1()) &&
              equal(this.second, that.getValue2()));
      /* @formatter:on */
    }

    @Override
    public String toString()
    {
      /* @formatter:off */
      return toStringBuilder(this)
              .add("first", first)
              .add("second", second)
            .toString();
      /* @formatter:on */
    }
  }

  public static final StringArrayToTrimmedStringListSelector STRING_ARRAY_TO_LIST_TRIM_SELECTOR =
    new StringArrayToTrimmedStringListSelector();

  private static final NotNullCondition<Object> NOT_NULL_CONDITION = new NotNullCondition<Object>();

  private static final NotEmptyStringCondition NOT_EMPTY_STRING_CONDITION = new NotEmptyStringCondition();

  private static final StringArrayTrimmer STRING_ARRAY_TRIMMER = new StringArrayTrimmer();

  private static final ToLowerCaseConverter TO_LOWER_CASE_CONVERTER = new ToLowerCaseConverter();

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

  public static StringArrayToTrimmedStringListSelector stringArrayToTrimmedStringListSelector()
  {
    return STRING_ARRAY_TO_LIST_TRIM_SELECTOR;
  }

  public static StringArrayTrimmer stringArrayTrimmer()
  {
    return STRING_ARRAY_TRIMMER;
  }

  public static PrefixAndSuffixAdder newPrefixAndSuffixAdder(final String prefix, final String suffix)
  {
    return new PrefixAndSuffixAdder(prefix, suffix);
  }

  public static ToLowerCaseConverter toLowerCaseConverter()
  {
    return TO_LOWER_CASE_CONVERTER;
  }

  public static <L, R> SimplePair<L, R> newPair(final L first, final R second)
  {
    return new SimplePair<L, R>(first, second);
  }
}
