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

import org.elixirian.kommonlee.type.Pair;
import org.elixirian.kommonlee.type.functional.Condition1;
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
 * @version 0.0.1 (2014-03-26)
 * @param <E>
 *          element type
 * @param <NK>
 *          key type
 * @param <NE>
 *          new value type
 * @param <C>
 *          condition
 * @param <F>
 *          mapper to convert an element to a key value pair.
 */
public class ArrayToHashMapSelectableMapper<E, NK, NE, C extends Condition1<? super E>, F extends Function1<? super E, ? extends Pair<NK, NE>>>
    extends ArrayToMapSelectableMapper<E, NK, NE, C, F, HashMap<NK, NE>>
{

  ArrayToHashMapSelectableMapper(final HashMapCreator<NK, NE> hashMapCreator)
  {
    super(hashMapCreator);
    // TODO Auto-generated constructor stub
  }

  /* @formatter:off */
	public static <E,
								 NK,
								 NE,
								 C extends Condition1<? super E>,
								 F extends Function1<? super E, ? extends Pair<NK, NE>>>
					ArrayToHashMapSelectableMapper<E, NK, NE, C, F>
		newInstance(final HashMapCreator<NK, NE> hashMapCreator)
	{
		return new ArrayToHashMapSelectableMapper<E, NK, NE, C, F>(hashMapCreator);
	}
	/* @formatter:on */
}