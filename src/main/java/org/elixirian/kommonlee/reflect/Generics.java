/**
 * 
 */
package org.elixirian.kommonlee.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <pre>
 *     ____________    ___________  ____   _______ _________ _______ _______________  ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /     \/   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /          /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 * 
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
