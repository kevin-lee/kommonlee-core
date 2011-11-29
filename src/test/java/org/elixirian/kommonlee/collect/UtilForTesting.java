/**
 * 
 */
package org.elixirian.kommonlee.collect;

import static org.elixirian.kommonlee.util.Objects.*;

import java.util.Comparator;

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
 * @version 0.0.1 (2011-06-19)
 */
public final class UtilForTesting
{
  private UtilForTesting()
  {
  }

  static class ComparableObject implements Comparable<ComparableObject>
  {
    private final int key;

    public ComparableObject(final int key)
    {
      this.key = key;
    }

    public int getKey()
    {
      return key;
    }

    @Override
    public int compareTo(final ComparableObject that)
    {
      return this.key < that.getKey() ? -1 : this.key == that.getKey() ? 0 : 1;
    }

    @Override
    public int hashCode()
    {
      return key;
    }

    @Override
    public boolean equals(final Object comparableObject)
    {
      if (identical(this, comparableObject))
        return true;
      final ComparableObject that = castIfInstanceOf(ComparableObject.class, comparableObject);
      return isNotNull(that) && equal(this.key, that.getKey());
    }

    @Override
    public String toString()
    {
      return toStringBuilder(this).add("key", key)
          .toString();
    }

  }

  static final Comparator<? super String> STRING_COMPARATOR = new Comparator<String>() {
    @Override
    public int compare(final String o1, final String o2)
    {
      if (null == o1)
        return null == o2 ? 0 : 1;
      if (null == o2)
        return -1;
      return o1.compareTo(o2);
    }
  };

  static final Comparator<? super Object> OBJECT_COMPARATOR = new Comparator<Object>() {
    @Override
    public int compare(final Object o1, final Object o2)
    {
      if (null == o1)
        return null == o2 ? 0 : 1;
      if (null == o2)
        return -1;
      return o1.toString()
          .compareTo(o2.toString());
    }
  };
}
