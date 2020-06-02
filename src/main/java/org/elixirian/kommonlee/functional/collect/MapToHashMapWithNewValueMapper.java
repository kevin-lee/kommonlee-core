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
package org.elixirian.kommonlee.functional.collect;

import java.util.HashMap;
import java.util.Map;

import org.elixirian.kommonlee.type.functional.Function1;

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
 * @version 0.0.1 (2011-07-23)
 * @param <K>
 *          Key
 * @param <E>
 *          Element
 * @param <T>
 *          Input Map type
 * @param <NE>
 *          New value after function applied.
 * @param <F>
 *          Function to map value
 */
public class MapToHashMapWithNewValueMapper<K, E, T extends Map<? extends K, ? extends E>, NE, F extends Function1<? super E, NE>>
    extends
    MapToMapWithNewValueMapper<K, E, Map<? extends K, ? extends E>, NE, Function1<? super E, NE>, HashMap<K, NE>>
{
  MapToHashMapWithNewValueMapper(final HashMapCreator<K, NE> mapCreator)
  {
    super(mapCreator);
  }

  /* @formatter:off */
  public static <K,
                 E,
                 T extends Map<? extends K, ? extends E>,
                 NE,
                 F extends Function1<? super E, NE>>
    MapToHashMapWithNewValueMapper<K, E, T, NE, F> newInstance(final HashMapCreator<K, NE> mapCreator)
  {
    return new MapToHashMapWithNewValueMapper<K, E, T, NE, F>(mapCreator);
  }
  /* @formatter:on */
}