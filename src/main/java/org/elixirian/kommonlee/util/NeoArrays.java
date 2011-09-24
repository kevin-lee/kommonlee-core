/**
 * 
 */
package org.elixirian.kommonlee.util;

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
 * @version 0.0.1 (2011-07-10)
 */
public final class NeoArrays
{
  private NeoArrays()
  {
  }

  /**
   * Checks if the given object is an array.
   * 
   * @param object
   *          the given array
   * @return true if the given of is an array. false otherwise.
   */
  public static boolean isArray(final Object object)
  {
    return null != object && object.getClass()
        .isArray();
  }

  public static <T> String toStringOfArray(final T[] array)
  {
    return null == array ? "null" : toStringOfArray0(array);
  }

  static <T> String toStringOfArray0(final T[] array)
  {
    final StringBuilder stringBuilder = new StringBuilder("[");
    for (final T each : array)
    {
      /* @formatter:off */
			stringBuilder.append(isArray(each) ?
					toStringOfArray((Object[]) each) :
					each)
				.append(", ");
			/* @formatter:on */
    }
    if (1 < stringBuilder.length())
    {
      stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]");
    }
    else
    {
      stringBuilder.append("]");
    }
    return stringBuilder.toString();
  }
}