/**
 * 
 */
package org.elixirian.common.collect;

import static org.elixirian.common.util.Objects.*;

import java.util.Comparator;

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
