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
package org.elixirian.kommonlee.util.type;

import static org.elixirian.kommonlee.util.MessageFormatter.*;
import static org.elixirian.kommonlee.util.Objects.*;

import org.elixirian.kommonlee.type.Pair;
import org.elixirian.kommonlee.type.Tuple2;

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
 * The default implementation of the {@link Pair} interface. It is an immutable pair object.
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-09-14)
 * @param <T1>
 * @param <T2>
 */
public class ImmutableTuple2<T1, T2> implements Tuple2<T1, T2>
{
  private final T1 value1;
  private final T2 value2;

  public ImmutableTuple2(final T1 value1, final T2 value2)
  {
    this.value1 = value1;
    this.value2 = value2;
  }

  @Override
  public T1 getValue1()
  {
    return _1();
  }

  @Override
  public T1 _1()
  {
    return value1;
  }

  @Override
  public T2 getValue2()
  {
    return _2();
  }

  @Override
  public T2 _2()
  {
    return value2;
  }

  @Override
  public int hashCode()
  {
    return hash(value1, value2);
  }

  @Override
  public boolean equals(final Object pair)
  {
    if (this == pair)
    {
      return true;
    }
    final Pair<?, ?> that = castIfInstanceOf(Pair.class, pair);
    /* @formatter:off */
    return null != that &&
        (equal(this.value1, that._1()) &&
         equal(this.value2, that._2()));
    /* @formatter:on */
  }

  @Override
  public String toString()
  {
    /* @formatter:off */
    return format("(%s, %s)", value1, value2);
    /* @formatter:on */
  }
}