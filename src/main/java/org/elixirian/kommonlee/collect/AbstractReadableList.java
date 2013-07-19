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
package org.elixirian.kommonlee.collect;

import static org.elixirian.kommonlee.collect.KollectionUtil.*;
import static org.elixirian.kommonlee.util.Objects.*;

import java.util.NoSuchElementException;

import org.elixirian.kommonlee.type.functional.Condition1;

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
 * @version 0.0.1 (2011-10-13)
 */
public abstract class AbstractReadableList<E> extends AbstractKollection<E> implements ReadableList<E>
{
  protected class McHammerIteratorForReadableList extends McHammerIterator<E>
  {
    int current = 0;
    int length = 0;

    public McHammerIteratorForReadableList()
    {
      this.length = length();
    }

    @Override
    public boolean hasNext()
    {
      return current != length;
    }

    @Override
    public E next()
    {
      try
      {
        final E next = get(current);
        current++;
        return next;
      }
      catch (final IndexOutOfBoundsException e)
      {
        throw new NoSuchElementException();
      }
    }
  }

  protected int indexOf0(final Object element, final int fromIndex)
  {
    final int length = length();
    checkIndex(length, fromIndex);

    if (null == element)
    {
      for (int i = fromIndex; i < length; i++)
      {
        if (null == get(i))
        {
          return i;
        }
      }
      return -1;
    }
    for (int i = fromIndex; i < length; i++)
    {
      if (element.equals(get(i)))
      {
        return i;
      }
    }
    return -1;
  }

  @Override
  public int indexOf(final E element, final int fromIndex)
  {
    return indexOf0(element, fromIndex);
  }

  @Override
  public int indexOf(final E element)
  {
    return indexOf0(element, 0);
  }

  protected int lastIndexOf0(final Object element, final int toIndex)
  {
    final int length = length();
    checkIndex(length, toIndex);

    if (null == element)
    {
      for (int i = toIndex - 1; i >= 0; i--)
      {
        if (null == get(i))
        {
          return i;
        }
      }
      return -1;
    }
    for (int i = toIndex - 1; i >= 0; i--)
    {
      if (element.equals(get(i)))
      {
        return i;
      }
    }
    return -1;
  }

  @Override
  public int lastIndexOf(final E element, final int toIndex)
  {
    return lastIndexOf0(element, toIndex);
  }

  @Override
  public int lastIndexOf(final E element)
  {
    return lastIndexOf0(element, length());
  }

  @Override
  public int howMany(final Condition1<? super E> conditionToMeet)
  {
    int count = 0;
    final int length = length();
    for (int i = 0; i < length; i++)
    {
      final E element = get(i);
      if (conditionToMeet.isMet(element))
      {
        count++;
      }
    }
    return count;
  }

  @Override
  public int hashCode()
  {
    return KollectionUtil.hashIterator(iterator());
  }

  @Override
  public boolean equals(final Object list)
  {
    if (this == list)
    {
      return true;
    }
    final ReadableList<?> that = castIfInstanceOf(ReadableList.class, list);
    return isNotNull(that) && KollectionUtil.equalReadableLists(this, that);
  }
}
