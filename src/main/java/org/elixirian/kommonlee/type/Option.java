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
package org.elixirian.kommonlee.type;

import static org.elixirian.kommonlee.util.Objects.*;

import java.util.NoSuchElementException;

import org.elixirian.kommonlee.functional.VoidFunction1;
import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.functional.Function1;
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
 * @version 0.0.1 (2014-04-19)
 */
public final class Option<T>
{
  static final Option<Object> NONE = new Option<Object>(null);

  public static <T> Option<T> none()
  {
    @SuppressWarnings("unchecked")
    final Option<T> none = (Option<T>) NONE;
    return none;
  }

  private final T t;

  Option(final T t)
  {
    this.t = t;
  }

  public boolean isNull()
  {
    return t == null;
  }

  public boolean isNotNull()
  {
    return !isNull();
  }

  public T get()
  {
    if (isNull())
    {
      throw new NoSuchElementException("There is no value but null.");
    }
    return t;
  }

  public T orElseUse(final T alternative)
  {
    return t != null ? t : alternative;
  }

  public T orElseGet(final Suppliable<T> suppliable)
  {
    Objects.mustNotBeNull(suppliable, "The suppliable must not be null.");
    return t != null ? t : suppliable.supply();
  }

  public Option<T> ifTrue(final Condition1<T> condition)
  {
    Objects.mustNotBeNull(condition, "The condition must not be null.");
    if (isNull())
    {
      return this;
    }
    return condition.isMet(t) ? this : Option.<T> none();
  }

  public Option<T> ifFalse(final Condition1<T> condition)
  {
    Objects.mustNotBeNull(condition, "The condition must not be null.");
    if (isNull())
    {
      return this;
    }
    return !condition.isMet(t) ? this : Option.<T> none();
  }

  public <N> Option<N> map(final Function1<T, N> mapper)
  {
    Objects.mustNotBeNull(mapper, "The mapper must not be null.");
    if (isNull())
    {
      return none();
    }
    return optionOf(mapper.apply(t));
  }

  public Option<T> doThis(final VoidFunction1<T> function)
  {
    Objects.mustNotBeNull(function, "The function must not be null.");
    if (isNotNull())
    {
      function.apply(t);
    }
    return this;
  }

  public void orElseDo(final Runnable runnable)
  {
    Objects.mustNotBeNull(runnable, "The runnable must not be null.");
    if (isNull())
    {
      runnable.run();
    }
  }

  public <EX extends Throwable> T getOrThrow(final Suppliable<EX> exceptionSuppliable) throws EX
  {
    Objects.mustNotBeNull(exceptionSuppliable, "The exceptionSuppliable must not be null.");
    if (isNotNull())
    {
      return t;
    }
    throw exceptionSuppliable.supply();
  }

  @Override
  public int hashCode()
  {
    return hash(t);
  }

  @Override
  public boolean equals(final Object option)
  {
    if (this == option)
    {
      return true;
    }
    final Option<?> that = castIfInstanceOf(Option.class, option);
    /* @formatter:off */
      return that != null &&
              equal(this.t,
                    that.t);
      /* @formatter:on */
  }

  @Override
  public String toString()
  {
    return t == null ? "None()" : "Some(" + t + ")";
  }

  public static <T> Option<T> optionOf(final T t)
  {
    @SuppressWarnings("unchecked")
    final Option<T> option = (Option<T>) (t == null ? NONE : new Option<T>(t));
    return option;
  }
}
