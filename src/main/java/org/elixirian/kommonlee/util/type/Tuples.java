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
 * @version 0.0.1 (2013-03-25)
 */
public final class Tuples
{
  private Tuples() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  public static <T1, T2> ImmutablePair<T1, T2> pair(final T1 value1, final T2 value2)
  {
    return tuple2(value1, value2);
  }

  public static <T1, T2> ImmutableTuple2<T1, T2> tuple2(final T1 value1, final T2 value2)
  {
    return new ImmutableTuple2<T1, T2>(value1, value2);
  }

  public static <T1, T2, T3> ImmutableTuple3<T1, T2, T3> tuple3(final T1 value1, final T2 value2, final T3 value3)
  {
    return new ImmutableTuple3<T1, T2, T3>(value1, value2, value3);
  }

  public static <T1, T2, T3, T4> ImmutableTuple4<T1, T2, T3, T4> tuple4(final T1 value1, final T2 value2,
      final T3 value3, final T4 value4)
  {
    return new ImmutableTuple4<T1, T2, T3, T4>(value1, value2, value3, value4);
  }

  public static <T1, T2, T3, T4, T5> ImmutableTuple5<T1, T2, T3, T4, T5> tuple5(final T1 value1, final T2 value2,
      final T3 value3, final T4 value4, final T5 value5)
  {
    return new ImmutableTuple5<T1, T2, T3, T4, T5>(value1, value2, value3, value4, value5);
  }

  public static <T1, T2, T3, T4, T5, T6> ImmutableTuple6<T1, T2, T3, T4, T5, T6> tuple6(final T1 value1,
      final T2 value2, final T3 value3, final T4 value4, final T5 value5, final T6 value6)
  {
    return new ImmutableTuple6<T1, T2, T3, T4, T5, T6>(value1, value2, value3, value4, value5, value6);
  }

  public static <T1, T2, T3, T4, T5, T6, T7> ImmutableTuple7<T1, T2, T3, T4, T5, T6, T7> tuple7(final T1 value1,
      final T2 value2, final T3 value3, final T4 value4, final T5 value5, final T6 value6, final T7 value7)
  {
    return new ImmutableTuple7<T1, T2, T3, T4, T5, T6, T7>(value1, value2, value3, value4, value5, value6, value7);
  }
}