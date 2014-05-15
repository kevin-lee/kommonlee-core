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
package org.elixirian.kommonlee.util;

import static org.elixirian.kommonlee.util.collect.Lists.*;

import java.lang.reflect.Array;
import java.util.List;

import org.elixirian.kommonlee.collect.KollectionUtil;

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
 * @version 0.0.1 (2011-07-10)
 * @version 0.0.2 (2012-05-09) equal methods and notEqual methods to compare array types including primitive ones are
 *          added.
 */
public final class NeoArrays
{
  /* @formatter:off */
  public static final byte[]    EMPTY_BYTE_ARRAY    = new byte[0];
  public static final char[]    EMPTY_CHAR_ARRAY    = new char[0];
  public static final short[]   EMPTY_SHORT_ARRAY   = new short[0];
  public static final int[]     EMPTY_INT_ARRAY     = new int[0];
  public static final long[]    EMPTY_LONG_ARRAY    = new long[0];
  public static final float[]   EMPTY_FLOAT_ARRAY   = new float[0];
  public static final double[]  EMPTY_DOUBLE_ARRAY  = new double[0];
  public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
  public static final String[]  EMPTY_STRING_ARRAY  = new String[0];
  public static final Object[]  EMPTY_OBJECT_ARRAY  = new Object[0];
  /* @formatter:on */

  private NeoArrays() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
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

  /**
   * Returns {@code true} if each element in the left array is equal to the one at the same index in the right array.
   * {@code false} otherwise. The both arrays must have the same length. Otherwise these are not equal.
   *
   * @param left
   *          a byte array
   * @param right
   *          a byte array to be compared with for equality
   * @return {@code true} if each element in the left array is equal to the one at the same index in the right array.
   *         {@code false} otherwise.
   */
  public static boolean equal(final byte[] left, final byte[] right)
  {
    if (left == right)
      return true;

    if (null == left || null == right)
      return false;

    if (left.length != right.length)
      return false;

    final int length = left.length;
    for (int i = 0; i < length; i++)
    {
      if (left[i] != right[i])
        return false;
    }
    return true;
  }

  public static boolean equal(final byte[] left, final int fromOfLeft, final int toOfLeft, final byte[] right,
      final int fromOfRight, final int toOfRight)
  {
    if (null == left)
    {
      return null == right;
    }
    if (null == right)
    {
      return false;
    }

    KollectionUtil.checkRange(left.length, fromOfLeft, "from of left array", toOfLeft, "to of left array");
    KollectionUtil.checkRange(right.length, fromOfRight, "from of right array", toOfRight, "to of right array");

    final int howManyOfLeft = toOfLeft - fromOfLeft;
    final int howManyOfRight = toOfRight - fromOfRight;

    if (howManyOfLeft != howManyOfRight)
    {
      return false;
    }

    int leftIndex = fromOfLeft;
    int rightIndex = fromOfRight;
    while (toOfLeft > leftIndex && toOfRight > rightIndex)
    {
      if (left[leftIndex] != right[rightIndex])
      {
        return false;
      }
      leftIndex++;
      rightIndex++;
    }
    return true;
  }

  /**
   * Returns {@code true} if any element in one array is not equal to the one at the same index in the other array.
   * {@code false} otherwise.
   *
   * @param left
   *          a byte array
   * @param right
   *          a byte array to be compared with for being not equal.
   * @return {@code true} if any element in one array is not equal to the one at the same index in the other array.
   *         {@code false} otherwise.
   */
  public static boolean notEqual(final byte[] left, final byte[] right)
  {
    return !equal(left, right);
  }

  /**
   * Returns {@code true} if each element in the left array is equal to the one at the same index in the right array.
   * The both arrays must have the same length. Otherwise these are not equal.
   *
   * @param left
   *          a char array
   * @param right
   *          a char array to be compared with for equality
   * @return {@code true} if each element in the left array is equal to the one at the same index in the right array.
   *         {@code false} otherwise.
   */
  public static boolean equal(final char[] left, final char[] right)
  {
    if (left == right)
      return true;

    if (null == left || null == right)
      return false;

    if (left.length != right.length)
      return false;

    final int length = left.length;
    for (int i = 0; i < length; i++)
    {
      if (left[i] != right[i])
        return false;
    }
    return true;
  }

  /**
   * Returns {@code true} if any element in one array is not equal to the one at the same index in the other array.
   * {@code false} otherwise.
   *
   * @param left
   *          a char array
   * @param right
   *          a char array to be compared with for being not equal.
   * @return {@code true} if any element in one array is not equal to the one at the same index in the other array.
   *         {@code false} otherwise.
   */
  public static boolean notEqual(final char[] left, final char[] right)
  {
    return !equal(left, right);
  }

  /**
   * Returns {@code true} if each element in the left array is equal to the one at the same index in the right array.
   * The both arrays must have the same length. Otherwise these are not equal.
   *
   * @param left
   *          a short array
   * @param right
   *          a short array to be compared with for equality
   * @return {@code true} if each element in the left array is equal to the one at the same index in the right array.
   *         {@code false} otherwise.
   */
  public static boolean equal(final short[] left, final short[] right)
  {
    if (left == right)
      return true;

    if (null == left || null == right)
      return false;

    if (left.length != right.length)
      return false;

    final int length = left.length;
    for (int i = 0; i < length; i++)
    {
      if (left[i] != right[i])
        return false;
    }
    return true;
  }

  /**
   * Returns {@code true} if any element in one array is not equal to the one at the same index in the other array.
   * {@code false} otherwise.
   *
   * @param left
   *          a short array
   * @param right
   *          a short array to be compared with for being not equal.
   * @return {@code true} if any element in one array is not equal to the one at the same index in the other array.
   *         {@code false} otherwise.
   */
  public static boolean notEqual(final short[] left, final short[] right)
  {
    return !equal(left, right);
  }

  /**
   * Returns {@code true} if each element in the left array is equal to the one at the same index in the right array.
   * The both arrays must have the same length. Otherwise these are not equal.
   *
   * @param left
   *          a int array
   * @param right
   *          a int array to be compared with for equality
   * @return {@code true} if each element in the left array is equal to the one at the same index in the right array.
   *         {@code false} otherwise.
   */
  public static boolean equal(final int[] left, final int[] right)
  {
    if (left == right)
      return true;

    if (null == left || null == right)
      return false;

    if (left.length != right.length)
      return false;

    final int length = left.length;
    for (int i = 0; i < length; i++)
    {
      if (left[i] != right[i])
        return false;
    }
    return true;
  }

  /**
   * Returns {@code true} if any element in one array is not equal to the one at the same index in the other array.
   * {@code false} otherwise.
   *
   * @param left
   *          an int array
   * @param right
   *          an int array to be compared with for being not equal.
   * @return {@code true} if any element in one array is not equal to the one at the same index in the other array.
   *         {@code false} otherwise.
   */
  public static boolean notEqual(final int[] left, final int[] right)
  {
    return !equal(left, right);
  }

  /**
   * Returns {@code true} if each element in the left array is equal to the one at the same index in the right array.
   * The both arrays must have the same length. Otherwise these are not equal.
   *
   * @param left
   *          a long array
   * @param right
   *          a long array be compared with for equality
   * @return {@code true} if each element in the left array is equal to the one at the same index in the right array.
   *         {@code false} otherwise.
   */
  public static boolean equal(final long[] left, final long[] right)
  {
    if (left == right)
      return true;

    if (null == left || null == right)
      return false;

    if (left.length != right.length)
      return false;

    final int length = left.length;
    for (int i = 0; i < length; i++)
    {
      if (left[i] != right[i])
        return false;
    }
    return true;
  }

  /**
   * Returns {@code true} if any element in one array is not equal to the one at the same index in the other array.
   * {@code false} otherwise.
   *
   * @param left
   *          a long array
   * @param right
   *          a long array to be compared with for being not equal.
   * @return {@code true} if any element in one array is not equal to the one at the same index in the other array.
   *         {@code false} otherwise.
   */
  public static boolean notEqual(final long[] left, final long[] right)
  {
    return !equal(left, right);
  }

  /**
   * Returns {@code true} if each element in the left array is equal to the one at the same index in the right array.
   * The both arrays must have the same length. Otherwise these are not equal.
   *
   * @param left
   *          a float array
   * @param right
   *          a float array to be compared with for equality
   * @return {@code true} if each element in the left array is equal to the one at the same index in the right array.
   *         {@code false} otherwise.
   */
  public static boolean equal(final float[] left, final float[] right)
  {
    if (left == right)
      return true;

    if (null == left || null == right)
      return false;

    if (left.length != right.length)
      return false;

    final int length = left.length;
    for (int i = 0; i < length; i++)
    {
      if (left[i] != right[i])
        return false;
    }
    return true;
  }

  /**
   * Returns {@code true} if any element in one array is not equal to the one at the same index in the other array.
   * {@code false} otherwise.
   *
   * @param left
   *          a float array
   * @param right
   *          a float array to be compared with for being not equal.
   * @return {@code true} if any element in one array is not equal to the one at the same index in the other array.
   *         {@code false} otherwise.
   */
  public static boolean notEqual(final float[] left, final float[] right)
  {
    return !equal(left, right);
  }

  /**
   * Returns {@code true} if each element in the left array is equal to the one at the same index in the right array.
   * The both arrays must have the same length. Otherwise these are not equal.
   *
   * @param left
   *          a double array
   * @param right
   *          a double array to be compared with for equality
   * @return {@code true} if each element in the left array is equal to the one at the same index in the right array.
   *         {@code false} otherwise.
   */
  public static boolean equal(final double[] left, final double[] right)
  {
    if (left == right)
      return true;

    if (null == left || null == right)
      return false;

    if (left.length != right.length)
      return false;

    final int length = left.length;
    for (int i = 0; i < length; i++)
    {
      if (left[i] != right[i])
        return false;
    }
    return true;
  }

  /**
   * Returns {@code true} if any element in one array is not equal to the one at the same index in the other array.
   * {@code false} otherwise.
   *
   * @param left
   *          a double array
   * @param right
   *          a double array to be compared with for being not equal.
   * @return {@code true} if any element in one array is not equal to the one at the same index in the other array.
   *         {@code false} otherwise.
   */
  public static boolean notEqual(final double[] left, final double[] right)
  {
    return !equal(left, right);
  }

  /**
   * Returns {@code true} if each element in the left array is equal to the one at the same index in the right array.
   * The both arrays must have the same length. Otherwise these are not equal.
   *
   * @param left
   *          a boolean array
   * @param right
   *          a boolean array to be compared with for equality
   * @return {@code true} if each element in the left array is equal to the one at the same index in the right array.
   *         {@code false} otherwise.
   */
  public static boolean equal(final boolean[] left, final boolean[] right)
  {
    if (left == right)
      return true;

    if (null == left || null == right)
      return false;

    if (left.length != right.length)
      return false;

    final int length = left.length;
    for (int i = 0; i < length; i++)
    {
      if (left[i] != right[i])
        return false;
    }
    return true;
  }

  /**
   * Returns {@code true} if any element in one array is not equal to the one at the same index in the other array.
   * {@code false} otherwise.
   *
   * @param left
   *          a boolean array
   * @param right
   *          a boolean array to be compared with for being not equal.
   * @return {@code true} if any element in one array is not equal to the one at the same index in the other array.
   *         {@code false} otherwise.
   */
  public static boolean notEqual(final boolean[] left, final boolean[] right)
  {
    return !equal(left, right);
  }

  /**
   * Returns {@code true} if each element in the left array is equal to the one at the same index in the right array.
   * The both arrays must have the same length. Otherwise these are not equal.
   *
   * @param left
   *          a Object array
   * @param right
   *          a Object array to be compared with for equality
   * @return {@code true} if each element in the left array is equal to the one at the same index in the right array.
   *         {@code false} otherwise.
   */
  public static boolean equal(final Object[] left, final Object[] right)
  {
    if (left == right)
      return true;

    if (null == left || null == right)
      return false;

    if (left.length != right.length)
      return false;

    final int length = left.length;
    for (int i = 0; i < length; i++)
    {
      if (Objects.notEqual(left[i], right[i]))
        return false;
    }
    return true;
  }

  /**
   * Returns {@code true} if any element in one array is not equal to the one at the same index in the other array.
   * {@code false} otherwise.
   *
   * @param left
   *          a Object array
   * @param right
   *          a Object array to be compared with for being not equal.
   * @return {@code true} if any element in one array is not equal to the one at the same index in the other array.
   *         {@code false} otherwise.
   */
  public static boolean notEqual(final Object[] left, final Object[] right)
  {
    return !equal(left, right);
  }

  /**
   * Returns {@code true} if each element in the left array is equal to the one at the same index in the right array.
   * The both arrays must have the same length. Otherwise these are not equal.
   *
   * @param left
   *          a Object array
   * @param right
   *          a Object array to be compared with for equality
   * @return {@code true} if each element in the left array is equal to the one at the same index in the right array.
   *         {@code false} otherwise.
   */
  public static boolean deepEqual(final Object left, final Object right)
  {
    if (left == right)
      return true;

    if (null == left || null == right)
      return false;

    if (left instanceof byte[] && right instanceof byte[])
    {
      return equal((byte[]) left, (byte[]) right);
    }
    if (left instanceof char[] && right instanceof char[])
    {
      return equal((char[]) left, (char[]) right);
    }
    if (left instanceof short[] && right instanceof short[])
    {
      return equal((short[]) left, (short[]) right);
    }
    if (left instanceof int[] && right instanceof int[])
    {
      return equal((int[]) left, (int[]) right);
    }
    if (left instanceof long[] && right instanceof long[])
    {
      return equal((long[]) left, (long[]) right);
    }
    if (left instanceof float[] && right instanceof float[])
    {
      return equal((float[]) left, (float[]) right);
    }
    if (left instanceof double[] && right instanceof double[])
    {
      return equal((double[]) left, (double[]) right);
    }
    if (left instanceof boolean[] && right instanceof boolean[])
    {
      return equal((boolean[]) left, (boolean[]) right);
    }
    if (left instanceof Object[] && right instanceof Object[])
    {
      return deepEqualArray((Object[]) left, (Object[]) right);
    }
    return left.equals(right);
  }

  /**
   * Returns {@code true} if any element in one array is not equal to the one at the same index in the other array.
   * {@code false} otherwise.
   *
   * @param left
   *          a Object array
   * @param right
   *          a Object array to be compared with for being not equal.
   * @return {@code true} if any element in one array is not equal to the one at the same index in the other array.
   *         {@code false} otherwise.
   */
  public static boolean notDeepEqual(final Object left, final Object right)
  {
    return !deepEqual(left, right);
  }

  /**
   * Returns {@code true} if each element in the left array is equal to the one at the same index in the right array.
   * The both arrays must have the same length. Otherwise these are not equal.
   *
   * @param left
   *          a Object array
   * @param right
   *          a Object array to be compared with for equality
   * @return {@code true} if each element in the left array is equal to the one at the same index in the right array.
   *         {@code false} otherwise.
   */
  public static boolean deepEqualArray(final Object[] left, final Object[] right)
  {
    if (left == right)
      return true;

    if (null == left || null == right)
      return false;

    if (left.length != right.length)
      return false;

    final int length = left.length;
    for (int i = 0; i < length; i++)
    {
      if (notDeepEqual(left[i], right[i]))
        return false;
    }
    return true;
  }

  /**
   * Returns {@code true} if any element in one array is not equal to the one at the same index in the other array.
   * {@code false} otherwise.
   *
   * @param left
   *          a Object array
   * @param right
   *          a Object array to be compared with for being not equal.
   * @return {@code true} if any element in one array is not equal to the one at the same index in the other array.
   *         {@code false} otherwise.
   */
  public static boolean notDeepEqualArray(final Object[] left, final Object[] right)
  {
    return !deepEqualArray(left, right);
  }

  public static byte[] convertToPrimitive(final Byte[] bytes)
  {
    final int length = bytes.length;
    final byte[] primitives = new byte[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final byte x = bytes[i];
      primitives[i] = x;
    }
    return primitives;
  }

  public static Byte[] convertToBoxedPrimitive(final byte[] bytes)
  {
    final int length = bytes.length;
    final Byte[] boxedPrimitives = new Byte[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Byte x = bytes[i];
      boxedPrimitives[i] = x;
    }
    return boxedPrimitives;
  }

  public static char[] convertToPrimitive(final Character[] characters)
  {
    final int length = characters.length;
    final char[] primitives = new char[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final char x = characters[i];
      primitives[i] = x;
    }
    return primitives;
  }

  public static Character[] convertToBoxedPrimitive(final char[] chars)
  {
    final int length = chars.length;
    final Character[] boxedPrimitives = new Character[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Character x = chars[i];
      boxedPrimitives[i] = x;
    }
    return boxedPrimitives;
  }

  public static short[] convertToPrimitive(final Short[] shorts)
  {
    final int length = shorts.length;
    final short[] primitives = new short[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final short x = shorts[i];
      primitives[i] = x;
    }
    return primitives;
  }

  public static Short[] convertToBoxedPrimitive(final short[] shorts)
  {
    final int length = shorts.length;
    final Short[] boxedPrimitives = new Short[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Short x = shorts[i];
      boxedPrimitives[i] = x;
    }
    return boxedPrimitives;
  }

  public static int[] convertToPrimitive(final Integer[] integers)
  {
    final int length = integers.length;
    final int[] primitives = new int[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final int x = integers[i];
      primitives[i] = x;
    }
    return primitives;
  }

  public static Integer[] convertToBoxedPrimitive(final int[] ints)
  {
    final int length = ints.length;
    final Integer[] boxedPrimitives = new Integer[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Integer x = ints[i];
      boxedPrimitives[i] = x;
    }
    return boxedPrimitives;
  }

  public static long[] convertToPrimitive(final Long[] longs)
  {
    final int length = longs.length;
    final long[] primitives = new long[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final long x = longs[i];
      primitives[i] = x;
    }
    return primitives;
  }

  public static Long[] convertToBoxedPrimitive(final long[] longs)
  {
    final int length = longs.length;
    final Long[] boxedPrimitives = new Long[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Long x = longs[i];
      boxedPrimitives[i] = x;
    }
    return boxedPrimitives;
  }

  public static float[] convertToPrimitive(final Float[] floats)
  {
    final int length = floats.length;
    final float[] primitives = new float[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final float x = floats[i];
      primitives[i] = x;
    }
    return primitives;
  }

  public static Float[] convertToBoxedPrimitive(final float[] floats)
  {
    final int length = floats.length;
    final Float[] boxedPrimitives = new Float[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Float x = floats[i];
      boxedPrimitives[i] = x;
    }
    return boxedPrimitives;
  }

  public static double[] convertToPrimitive(final Double[] doubles)
  {
    final int length = doubles.length;
    final double[] primitives = new double[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final double x = doubles[i];
      primitives[i] = x;
    }
    return primitives;
  }

  public static Double[] convertToBoxedPrimitive(final double[] doubles)
  {
    final int length = doubles.length;
    final Double[] boxedPrimitives = new Double[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Double x = doubles[i];
      boxedPrimitives[i] = x;
    }
    return boxedPrimitives;
  }

  public static boolean[] convertToPrimitive(final Boolean[] booleans)
  {
    final int length = booleans.length;
    final boolean[] primitives = new boolean[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final boolean x = booleans[i];
      primitives[i] = x;
    }
    return primitives;
  }

  public static Boolean[] convertToBoxedPrimitive(final boolean[] booleans)
  {
    final int length = booleans.length;
    final Boolean[] boxedPrimitives = new Boolean[length];
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("boxing")
      final Boolean x = booleans[i];
      boxedPrimitives[i] = x;
    }
    return boxedPrimitives;
  }

  /**
   * @param elements
   *          the given byte array.
   * @param from
   *          the initial index of the range to be included for the hash calculation, inclusive
   * @param to
   *          the final index of the range to be included for the hash calculation, exclusive. (This index may lie
   *          outside the array.)
   * @return a content-based hash code for the given <tt>bytes</tt> 'elements' array.
   */
  public static int hash(final byte[] elements, final int from, final int to)
  {
    if (null == elements)
    {
      return 0;
    }
    KollectionUtil.checkRange(elements.length, from, to);

    int result = 1;
    for (int i = from; i < to; i++)
    {
      result = 31 * result + elements[i];
    }
    return result;
  }

  public static <E> E[] glue(final E head, final E... tail)
  {
    Objects.mustNotBeNull(
        tail,
        "The rest of elements (tail) must not be null. If there is only one element that should be passed to this method, just do glue(element) instead of glue(element, null).");
    /* @formatter:off */
    @SuppressWarnings("unchecked")
    final E[] newArray = (E[]) Array.newInstance(tail
                                                .getClass()
                                                .getComponentType(),
                                                 1 + tail.length);
    /* @formatter:on */
    newArray[0] = head;
    System.arraycopy(tail, 0, newArray, 1, tail.length);
    return newArray;
  }

  public static <E> List<E> glueToList(final E head, final E... tail)
  {
    Objects.mustNotBeNull(
        tail,
        "The rest of elements (tail) must not be null. If there is only one element that should be passed to this method, just do glue(element) instead of glue(element, null).");
    final List<E> result = newArrayListWithInitialCapacity(1 + tail.length);
    result.add(head);
    for (final E each : tail)
    {
      result.add(each);
    }
    return result;
  }
}