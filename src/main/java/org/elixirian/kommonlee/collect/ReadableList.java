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
import java.util.List;

import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.functional.Function1;

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
 * @version 0.0.1 (2011-09-18)
 */
public interface ReadableList<E> extends ReadableCollection<E>
{
  @Override
  Iterator<E> iterator();

  @Override
  int length();

  @Override
  boolean isEmpty();

  @Override
  boolean isNotEmpty();

  /**
   * @param element
   * @param fromIndex
   *          toIndex (inclusive)
   * @return
   */
  int indexOf(E element, int fromIndex);

  int indexOf(E element);

  /**
   * @param element
   * @param toIndex
   *          toIndex (exclusive) so calling {@link #lastIndexOf(Object, int)} with the length of this list is the same
   *          as {@link #lastIndexOf(Object)}
   * 
   *          <pre>
   *          e.g.)
   *          length = 5
   *          lastIndexOf(someObject, 5) == lastIndexOf(someObject)
   * </pre>
   * @return
   */
  int lastIndexOf(E element, int toIndex);

  int lastIndexOf(E element);

  @Override
  boolean contains(Object element);

  @Override
  boolean containsAll(Kollection<?> kollection);

  @Override
  ReadableList<E> select(Condition1<? super E> condition);

  @Override
  <R> ReadableList<R> map(Function1<? super E, R> function);

  @Override
  <R> ReadableList<R> mapSelectively(Condition1<? super E> condition, Function1<? super E, R> function);

  @Override
  Object[] toArray();

  @Override
  E[] toArray(E[] elements);

  @Override
  int hashCode();

  @Override
  boolean equals(Object element);

  E get(int index);

  ReadableList<E> subList(int fromIndex, int toIndex);

  @Override
  List<E> convertTo();
}
