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

import java.util.Iterator;

import org.elixirian.kommonlee.type.function.Condition1;

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
public abstract class AbstractKollection<E> implements Kollection<E>
{
  @Override
  public int size()
  {
    return length();
  }

  @Override
  public boolean isEmpty()
  {
    return 0 == length();
  }

  @Override
  public boolean isNotEmpty()
  {
    return !isEmpty();
  }

  @Override
  public boolean contains(final Object element)
  {
    final Iterator<E> iterator = iterator();
    if (null == element)
    {
      while (iterator.hasNext())
      {
        if (null == iterator.next())
        {
          return true;
        }
      }
      return false;
    }
    while (iterator.hasNext())
    {
      if (element.equals(iterator.next()))
      {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsAll(final Kollection<?> kollection)
  {
    for (final Object element : kollection)
    {
      if (!contains(element))
      {
        return false;
      }
    }
    return true;
  }

  @Override
  public int howMany(final Condition1<? super E> conditionToMeet)
  {
    int count = 0;
    final Iterator<E> iterator = iterator();
    while (iterator.hasNext())
    {
      final E element = iterator.next();
      if (conditionToMeet.isMet(element))
      {
        count++;
      }
    }
    return count;
  }

  @Override
  public String toString()
  {
    return Kollections.toStringOf(this);
  }
}
