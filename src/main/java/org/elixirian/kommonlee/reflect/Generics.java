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
package org.elixirian.kommonlee.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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
 * @version 0.0.1 (2010-09-30)
 */
public final class Generics
{
	private Generics() throws IllegalAccessException
	{
		throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
	}

	// public static <K, V> Pair<K, V> getGenericInfoKV(Type type)
	// {
	// final K key = extractFromParameterizedType(type, 0);
	// final V value = extractFromParameterizedType(type, 1);
	// return new KeyValuePair<K, V>(key, value);
	// }

	/**
	 * Gets the actual type argument at the specified position from the given {@link Type} if it is
	 * {@link ParameterizedType} .
	 * <p>
	 * e.g.1)
	 *
	 * <pre>
	 * class SomeClass
	 * {
	 *   private Map&lt;String, Integer&gt; map;
	 * }
	 *
	 * final Field field = SomeClass.class.getDeclaredField(&quot;map&quot;);
	 *
	 * final Type actual0 = Generics.extractFromParameterizedType(field.getGenericType(), 0);
	 * final Type actual1 = Generics.extractFromParameterizedType(field.getGenericType(), 1);
	 *
	 * actual0 == String.class
	 * actual1 == Integer.class
	 * </pre>
	 *
	 * </p>
	 * <p>
	 * e.g.2)
	 *
	 * <pre>
	 * abstract class GenerifiedClass&lt;T, E&gt;
	 * {
	 * }
	 *
	 * final Type genericSuperClass = new GenerifiedClass&lt;Date, BigDecimal&gt;() {}.getClass()
	 *     .getGenericSuperclass();
	 *
	 * final Type actual0 = Generics.extractFromParameterizedType(genericSuperClass, 0);
	 * final Type actual1 = Generics.extractFromParameterizedType(genericSuperClass, 1);
	 *
	 * actual0 == Date.class
	 * actual1 == BigDecimal.class
	 * </pre>
	 *
	 * </p>
	 *
	 * @param type
	 *          the given type from which the actual type argument should be extracted.
	 * @param index
	 *          the position of the actual type argument.
	 * @return the extracted actual type argument of the parameterised type at the specified position if the given type is
	 *         an instance of {@link ParameterizedType}. null if the type is not an instance of {@link ParameterizedType}
	 */
	public static <T extends Type> T extractFromParameterizedType(final Type type, final int index)
	{
		if (type instanceof ParameterizedType)
		{
			final ParameterizedType parameterizedType = ((ParameterizedType) type);
			// System.out.println("RowType: " + parameterizedType.getRawType() + " | OwnerType: "
			// + parameterizedType.getOwnerType());

			@SuppressWarnings("unchecked")
			final T typeArgumentInIndex = (T) parameterizedType.getActualTypeArguments()[index];
			return typeArgumentInIndex;
		}
		return null;
	}
}
