/**
 * 
 */
package org.elixirian.kommonlee.util;

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
    throw new AssertionError(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
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

  public static boolean isNotArray(final Object object)
  {
    return !isArray(object);
  }

  public static String toStringOfArray(final boolean[] array)
  {
    return null == array ? "null" : toStringOfArray0(array);
  }

  static String toStringOfArray0(final boolean[] array)
  {
    final StringBuilder stringBuilder = new StringBuilder("[");
    for (final boolean each : array)
    {
      stringBuilder.append(each)
          .append(", ");
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

  public static String toStringOfArray(final byte[] array)
  {
    return null == array ? "null" : toStringOfArray0(array);
  }

  static String toStringOfArray0(final byte[] array)
  {
    final StringBuilder stringBuilder = new StringBuilder("[");
    for (final byte each : array)
    {
      stringBuilder.append(each)
          .append(", ");
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

  public static String toStringOfArray(final short[] array)
  {
    return null == array ? "null" : toStringOfArray0(array);
  }

  static String toStringOfArray0(final short[] array)
  {
    final StringBuilder stringBuilder = new StringBuilder("[");
    for (final short each : array)
    {
      stringBuilder.append(each)
          .append(", ");
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

  public static String toStringOfArray(final char[] array)
  {
    return null == array ? "null" : toStringOfArray0(array);
  }

  static String toStringOfArray0(final char[] array)
  {
    final StringBuilder stringBuilder = new StringBuilder("[");
    for (final char each : array)
    {
      stringBuilder.append(each)
          .append(", ");
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

  public static String toStringOfArray(final int[] array)
  {
    return null == array ? "null" : toStringOfArray0(array);
  }

  static String toStringOfArray0(final int[] array)
  {
    final StringBuilder stringBuilder = new StringBuilder("[");
    for (final int each : array)
    {
      stringBuilder.append(each)
          .append(", ");
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

  public static String toStringOfArray(final long[] array)
  {
    return null == array ? "null" : toStringOfArray0(array);
  }

  static String toStringOfArray0(final long[] array)
  {
    final StringBuilder stringBuilder = new StringBuilder("[");
    for (final long each : array)
    {
      stringBuilder.append(each)
          .append(", ");
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

  public static String toStringOfArray(final float[] array)
  {
    return null == array ? "null" : toStringOfArray0(array);
  }

  static String toStringOfArray0(final float[] array)
  {
    final StringBuilder stringBuilder = new StringBuilder("[");
    for (final float each : array)
    {
      stringBuilder.append(each)
          .append(", ");
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

  public static String toStringOfArray(final double[] array)
  {
    return null == array ? "null" : toStringOfArray0(array);
  }

  static String toStringOfArray0(final double[] array)
  {
    final StringBuilder stringBuilder = new StringBuilder("[");
    for (final double each : array)
    {
      stringBuilder.append(each)
          .append(", ");
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

  public static String toStringOfArray(final Object[] array)
  {
    return null == array ? "null" : toStringOfArray0(array);
  }

  static String toStringOfArray0(final Object[] array)
  {
    final StringBuilder stringBuilder = new StringBuilder("[");
    for (final Object each : array)
    {
      /* @formatter:off */
			stringBuilder.append(Objects
			      .toStringOfMaybeArrayObject(each))
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