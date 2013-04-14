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
package org.elixirian.kommonlee.util.collect;

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
