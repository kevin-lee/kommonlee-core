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

import org.elixirian.kommonlee.type.Tuple8;

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
 * The default implementation of the {@link Tuple8} interface. It is an immutable tuple object.
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-04-19)
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * @param <T4>
 * @param <T5>
 * @param <T6>
 * @param <T7>
 * @param <T8>
 */
public class ImmutableTuple8<T1, T2, T3, T4, T5, T6, T7, T8> implements Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>
{
  private final T1 value1;
  private final T2 value2;
  private final T3 value3;
  private final T4 value4;
  private final T5 value5;
  private final T6 value6;
  private final T7 value7;
  private final T8 value8;

  public ImmutableTuple8(final T1 value1, final T2 value2, final T3 value3, final T4 value4, final T5 value5,
      final T6 value6, final T7 value7, final T8 value8)
  {
    this.value1 = value1;
    this.value2 = value2;
    this.value3 = value3;
    this.value4 = value4;
    this.value5 = value5;
    this.value6 = value6;
    this.value7 = value7;
    this.value8 = value8;
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
  public T3 getValue3()
  {
    return _3();
  }

  @Override
  public T3 _3()
  {
    return value3;
  }

  @Override
  public T4 getValue4()
  {
    return _4();
  }

  @Override
  public T4 _4()
  {
    return value4;
  }

  @Override
  public T5 getValue5()
  {
    return _5();
  }

  @Override
  public T5 _5()
  {
    return value5;
  }

  @Override
  public T6 getValue6()
  {
    return _6();
  }

  @Override
  public T6 _6()
  {
    return value6;
  }

  @Override
  public T7 getValue7()
  {
    return _7();
  }

  @Override
  public T7 _7()
  {
    return value7;
  }

  @Override
  public T8 getValue8()
  {
    return _8();
  }

  @Override
  public T8 _8()
  {
    return value8;
  }

  @Override
  public int hashCode()
  {
    return hash(value1, value2, value3, value4, value5, value6, value7, value8);
  }

  @Override
  public boolean equals(final Object tuple)
  {
    if (this == tuple)
    {
      return true;
    }
    final Tuple8<?, ?, ?, ?, ?, ?, ?, ?> that = castIfInstanceOf(Tuple8.class, tuple);
    /* @formatter:off */
    return null != that &&
        (equal(this.value1, that._1()) &&
         equal(this.value2, that._2()) &&
         equal(this.value3, that._3()) &&
         equal(this.value4, that._4()) &&
         equal(this.value5, that._5()) &&
         equal(this.value6, that._6()) &&
         equal(this.value7, that._7()) &&
         equal(this.value8, that._8())
         );
    /* @formatter:on */
  }

  @Override
  public String toString()
  {
    return format("(%s, %s, %s, %s, %s, %s, %s, %s)", value1, value2, value3, value4, value5, value6, value7, value8);
  }
}