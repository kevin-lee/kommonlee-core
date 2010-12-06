/**
 * 
 */
package org.elixirian.common.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-09-30)
 */
public final class Generics
{
	private Generics()
	{
	}

	// public static <K, V> Pair<K, V> getGenericInfoKV(Type type)
	// {
	// final K key = extractFromParameterizedType(type, 0);
	// final V value = extractFromParameterizedType(type, 1);
	// return new KeyValuePair<K, V>(key, value);
	// }

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
