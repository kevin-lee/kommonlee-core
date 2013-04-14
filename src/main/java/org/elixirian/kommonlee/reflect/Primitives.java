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

import static org.elixirian.kommonlee.util.MessageFormatter.*;
import static org.elixirian.kommonlee.validation.Assertions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
 * @version 0.0.1 (2010-11-09)
 */
public final class Primitives
{
	private static final Map<Class<?>, Object> PRIMITIVE_DEFAULT_VALUE_OBJECT_MAP;

	private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_BOXED_PRIMITIVE_MAP;
	private static final Map<Class<?>, Class<?>> BOXED_PRIMITIVE_TO_PRIMITIVE_MAP;

	static
	{
		final Map<Class<?>, Object> map = new HashMap<Class<?>, Object>(8);
		map.put(byte.class, Byte.valueOf((byte) 0));
		map.put(short.class, Short.valueOf((short) 0));
		map.put(int.class, Integer.valueOf(0));
		map.put(long.class, Long.valueOf(0L));
		map.put(float.class, Float.valueOf(0.0F));
		map.put(double.class, Double.valueOf(0.0D));
		map.put(char.class, Character.valueOf('\u0000'));
		map.put(boolean.class, Boolean.FALSE);
		PRIMITIVE_DEFAULT_VALUE_OBJECT_MAP = Collections.unmodifiableMap(map);

		final Map<Class<?>, Class<?>> primitiveToBoxedMap = new HashMap<Class<?>, Class<?>>(8);
		primitiveToBoxedMap.put(byte.class, Byte.class);
		primitiveToBoxedMap.put(short.class, Short.class);
		primitiveToBoxedMap.put(int.class, Integer.class);
		primitiveToBoxedMap.put(long.class, Long.class);
		primitiveToBoxedMap.put(float.class, Float.class);
		primitiveToBoxedMap.put(double.class, Double.class);
		primitiveToBoxedMap.put(char.class, Character.class);
		primitiveToBoxedMap.put(boolean.class, Boolean.class);
		PRIMITIVE_TO_BOXED_PRIMITIVE_MAP = Collections.unmodifiableMap(primitiveToBoxedMap);

		final Map<Class<?>, Class<?>> boxedToPrimitiveMap = new HashMap<Class<?>, Class<?>>(8);
		boxedToPrimitiveMap.put(Byte.class, byte.class);
		boxedToPrimitiveMap.put(Short.class, short.class);
		boxedToPrimitiveMap.put(Integer.class, int.class);
		boxedToPrimitiveMap.put(Long.class, long.class);
		boxedToPrimitiveMap.put(Float.class, float.class);
		boxedToPrimitiveMap.put(Double.class, double.class);
		boxedToPrimitiveMap.put(Character.class, char.class);
		boxedToPrimitiveMap.put(Boolean.class, boolean.class);
		BOXED_PRIMITIVE_TO_PRIMITIVE_MAP = Collections.unmodifiableMap(boxedToPrimitiveMap);
	}

	private Primitives() throws IllegalAccessException
	{
		throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
	}

	/**
	 * Returns the default value of the given primitive type class in the boxed primitive type object. It throws an
	 * {@link IllegalStateException}, if the given class type is unknown primitive type which means it is none of byte,
	 * short, int, long, float, double, char and boolean.
	 * <p>
	 * Reference: <a href="http://java.sun.com/docs/books/jls/second_edition/html/typesValues.doc.html#96595">4.5.5
	 * Initial Values of Variables</a>
	 * </p>
	 * <ul>
	 * <li>Each class variable, instance variable, or array component is initialized with a default value when it is
	 * created (§15.9, §15.10):
	 * <ul>
	 * <li>For type byte, the default value is zero, that is, the value of (byte)0.</li>
	 * <li>For type short, the default value is zero, that is, the value of (short)0.</li>
	 * <li>For type int, the default value is zero, that is, 0.</li>
	 * <li>For type long, the default value is zero, that is, 0L.</li>
	 * <li>For type float, the default value is positive zero, that is, 0.0f.</li>
	 * <li>For type double, the default value is positive zero, that is, 0.0d.</li>
	 * <li>For type char, the default value is the null character, that is, '\u0000'.</li>
	 * <li>For type boolean, the default value is false.</li>
	 * <li>For all reference types (§4.3), the default value is null.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 *
	 * @param <T>
	 * @param primitiveType
	 *          the given primitive type.
	 * @return The default value Object of the given type.
	 * @throws IllegalStateException
	 *           If the given type is not a primitive type.
	 */
	public static <T> T getPrimitiveDefaultValueObject(final Class<T> primitiveType) throws IllegalStateException
	{
		final Object value =
			assertNotNull(PRIMITIVE_DEFAULT_VALUE_OBJECT_MAP.get(primitiveType),
					format("It is not a primitive type. Unknown type: [class: %s]", primitiveType));
		return getBoxedPrimitiveOf0(primitiveType).cast(value); // primitiveType.cast(value);
	}

	private static <T> Class<T> getBoxedPrimitiveOf0(final Class<T> primitiveType)
	{
		@SuppressWarnings("unchecked")
		final Class<T> foundType = (Class<T>) PRIMITIVE_TO_BOXED_PRIMITIVE_MAP.get(primitiveType);
		return foundType;
	}

	public static <T> Class<T> getBoxedPrimitiveOf(final Class<T> primitiveType)
	{
		final Class<T> foundType = getBoxedPrimitiveOf0(primitiveType);
		/* @formatter:off */
		return null == foundType ?
					(BOXED_PRIMITIVE_TO_PRIMITIVE_MAP.containsKey(primitiveType) ?
						primitiveType :
						null) :
				foundType;
		/* @formatter:on */
	}

	private static <T> Class<T> getPrimitiveOf0(final Class<T> boxedPrimitiveType)
	{
		@SuppressWarnings("unchecked")
		final Class<T> foundType = (Class<T>) BOXED_PRIMITIVE_TO_PRIMITIVE_MAP.get(boxedPrimitiveType);
		return foundType;
	}

	public static <T> Class<T> getPrimitiveOf(final Class<T> boxedPrimitiveType)
	{
		final Class<T> foundType = getPrimitiveOf0(boxedPrimitiveType);
		/* @formatter:off */
		return null == foundType ?
					(PRIMITIVE_TO_BOXED_PRIMITIVE_MAP.containsKey(boxedPrimitiveType) ?
						boxedPrimitiveType :
						null) :
					foundType;
		/* @formatter:on */
	}

	public static <T> boolean isPrimitive(final Class<T> type)
	{
		return PRIMITIVE_TO_BOXED_PRIMITIVE_MAP.containsKey(type);
	}

	public static <T> boolean isBoxedPrimitive(final Class<T> type)
	{
		return BOXED_PRIMITIVE_TO_PRIMITIVE_MAP.containsKey(type);
	}
}
