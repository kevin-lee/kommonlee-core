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
package org.elixirian.kommonlee.collect.mutable;

import java.util.Iterator;

import org.elixirian.kommonlee.collect.Kollection;
import org.elixirian.kommonlee.collect.ReadableWritableCollection;
import org.elixirian.kommonlee.type.function.Condition1;
import org.elixirian.kommonlee.type.function.Function1;

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
public interface MutableCollection<E> extends ReadableWritableCollection<E>
{
  @Override
  Iterator<E> iterator();

  @Override
  int length();

  @Override
  boolean isEmpty();

  @Override
  boolean isNotEmpty();

  @Override
  boolean contains(Object element);

  @Override
  boolean containsAll(Kollection<?> kollection);

  @Override
  MutableCollection<E> select(Condition1<? super E> condition);

  @Override
  <R> MutableCollection<R> map(Function1<? super E, R> function);

  @Override
  <R> MutableCollection<R> mapSelectively(Condition1<? super E> condition, Function1<? super E, R> function);

  @Override
  boolean remove(E element);

  @Override
  boolean removeAll(Kollection<?> elements);

  @Override
  boolean retainAll(Kollection<?> elements);

  @Override
  void clear();

  @Override
  boolean add(E element);

  @Override
  boolean addAll(Kollection<? extends E> commonCollection);

  @Override
  int hashCode();

  @Override
  boolean equals(Object element);
}
