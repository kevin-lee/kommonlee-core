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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.elixirian.kommonlee.util.CommonConstants;

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
 * @version 0.0.1 (2011-02-17)
 */
public final class Lists
{
  private Lists() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  /**
   * Returns a new instance of {@link ArrayList} to store the E type element. The initial capacity of the ArrayList
   * object is ten.
   * 
   * @param <E>
   *          the type of element the ArrayList contains.
   * @return a new instance of {@link ArrayList} to store the E type element.
   */
  public static <E> ArrayList<E> newArrayList()
  {
    return new ArrayList<E>();
  }

  /**
   * Returns a new instance of {@link ArrayList} containing the elements of the specified collection, in the order they
   * are returned by the collection's iterator.
   * 
   * @param <E>
   *          the type of element the ArrayList contains.
   * @param elements
   *          the elements the given ArrayList contains.
   * @return a new instance of {@link ArrayList} containing the elements of the specified collection, in the order they
   *         are returned by the collection's iterator.
   */
  public static <E> ArrayList<E> newArrayList(final Collection<? extends E> elements)
  {
    return new ArrayList<E>(elements);
  }

  /**
   * Returns a new instance of {@link ArrayList} containing the elements in the given {@link Iterable}.
   * 
   * @param <E>
   *          the type of element the Iterable contains.
   * @param elements
   *          the elements the given Iterable contains.
   * @return a new instance of {@link ArrayList} containing the elements in the given {@link Iterable}.
   */
  public static <E> ArrayList<E> newArrayList(final Iterable<? extends E> elements)
  {
    final ArrayList<E> list = newArrayList();
    for (final E e : elements)
      list.add(e);
    return list;
  }

  /**
   * Returns a new instance of {@link ArrayList} containing the elements in the given {@link Iterator}.
   * 
   * @param <E>
   *          the type of element the Iterator contains.
   * @param elements
   *          the elements the given Iterator contains.
   * @return a new instance of {@link ArrayList} containing the elements in the given {@link Iterator}.
   */
  public static <E> ArrayList<E> newArrayList(final Iterator<? extends E> elements)
  {
    final ArrayList<E> list = newArrayList();
    while (elements.hasNext())
      list.add(elements.next());
    return list;
  }

  public static <E> ArrayList<E> newArrayList(final E... elements)
  {
    final ArrayList<E> list = newArrayList();
    Collections.addAll(list, elements);
    return list;
  }

  public static <E> ArrayList<E> newArrayListWithInitialCapacity(final int initialCapacity)
  {
    return new ArrayList<E>(initialCapacity);
  }

  /**
   * Returns a new instance of {@link ArrayList} containing the elements of the specified collection, in the order they
   * are returned by the collection's iterator. If the given Collection is null, it returns an immutable empty list
   * returned from Collections.emptyList().
   * 
   * @param <E>
   *          the type of element the ArrayList contains.
   * @param elements
   *          the elements the given ArrayList contains.
   * @return a new instance of {@link ArrayList} containing the elements of the specified collection, in the order they
   *         are returned by the collection's iterator. If the given Collection is null, it returns an immutable empty
   *         list returned from Collections.emptyList().
   */
  public static <E> List<E> newArrayListOrEmptyListIfNull(final Collection<? extends E> elements)
  {
    return null == elements ? Collections.<E> emptyList() : newArrayList(elements);
  }

  /**
   * Returns a new instance of {@link ArrayList} containing the elements in the given {@link Iterable}. If the given
   * Iterable is null, it returns an immutable empty list returned from Collections.emptyList().
   * 
   * @param <E>
   *          the type of element the Iterable contains.
   * @param elements
   *          the elements the given Iterable contains.
   * @return a new instance of {@link ArrayList} containing the elements in the given {@link Iterable}. If the given
   *         Iterable is null, it returns an immutable empty list returned from Collections.emptyList().
   */
  public static <E> List<E> newArrayListOrEmptyListIfNull(final Iterable<? extends E> elements)
  {
    return null == elements ? Collections.<E> emptyList() : newArrayList(elements);
  }

  /**
   * Returns a new instance of {@link ArrayList} containing the elements in the given {@link Iterator}. If the given
   * Iterator is null, it returns an immutable empty list returned from Collections.emptyList().
   * 
   * @param <E>
   *          the type of element the Iterator contains.
   * @param elements
   *          the elements the given Iterator contains.
   * @return a new instance of {@link ArrayList} containing the elements in the given {@link Iterator}. If the given
   *         Iterator is null, it returns an immutable empty list returned from Collections.emptyList().
   */
  public static <E> List<E> newArrayListOrEmptyListIfNull(final Iterator<? extends E> elements)
  {
    return null == elements ? Collections.<E> emptyList() : newArrayList(elements);
  }

  /**
   * Returns a new instance of {@link ArrayList} containing the given elements. If the given elements array (varargs) is
   * null, it returns an immutable empty list returned from Collections.emptyList(). Note: It doesn't mean that it
   * returns an empty list if only one element is given and it's null , but the entire elements varargs (array) is null
   * then it returns an empty list.
   * 
   * <pre>
   * List&lt;String&gt; list = newArrayListOrImmutableEmptyListIfNull(null);
   * // list is an instance of ArrayList containing one element that is a null reference.
   * // list.size() == 1 and list.get(0) == null
   * </pre>
   * 
   * <pre>
   * String[] array = null;
   * List&lt;String&gt; list = newArrayListOrImmutableEmptyListIfNull(array);
   * // (or newArrayListOrImmutableEmptyListIfNull((String[]) null)
   * // list is an empty list.
   * // list.size() == 0 and list.get(0) -&gt; IndexOutOfBoundsException
   * </pre>
   * 
   * @param elements
   *          the given elements
   * @return a new instance of {@link ArrayList} containing the given elements. If the given elements array (varargs) is
   *         null, it returns an immutable empty list returned from Collections.emptyList().
   */
  public static <E> List<E> newArrayListOrEmptyListIfNull(final E... elements)
  {
    return null == elements ? Collections.<E> emptyList() : newArrayList(elements);
  }

  /**
   * Returns an immutable empty list returned from Collections.emptyList() if the given list parameter contains a null
   * reference. Otherwise it returns the parameter list back to the caller.
   * 
   * @param list
   *          the given List object. It can be null.
   * @return an immutable empty list returned from Collections.emptyList() if the given list parameter contains a null
   *         reference. Otherwise it returns the parameter list back to the caller.
   */
  public static <E> List<E> immutableEmptyListIfNull(final List<E> list)
  {
    return null == list ? Collections.<E> emptyList() : list;
  }

  /**
   * Returns a new instance of {@link LinkedList} to store the E type element.
   * 
   * @param <E>
   *          the type of element the list contains.
   * @return a new instance of {@link LinkedList} to store the E type element.
   */
  public static <E> LinkedList<E> newLinkedList()
  {
    return new LinkedList<E>();
  }

  /**
   * Returns a new instance of {@link LinkedList} containing the elements of the specified collection, in the order they
   * are returned by the collection's iterator.
   * 
   * @param <E>
   *          the type of element the list contains.
   * @param elements
   * @return a new instance of {@link LinkedList} containing the elements of the specified collection, in the order they
   *         are returned by the collection's iterator.
   */
  public static <E> LinkedList<E> newLinkedList(final Collection<? extends E> elements)
  {
    return new LinkedList<E>(elements);
  }

  public static <E> LinkedList<E> newLinkedList(final Iterable<? extends E> elements)
  {
    final LinkedList<E> list = newLinkedList();
    for (final E e : elements)
      list.add(e);
    return list;
  }

  public static <E> LinkedList<E> newLinkedList(final Iterator<? extends E> elements)
  {
    final LinkedList<E> list = newLinkedList();
    while (elements.hasNext())
      list.add(elements.next());
    return list;
  }

  public static <E> LinkedList<E> newLinkedList(final E... elements)
  {
    final LinkedList<E> list = newLinkedList();
    Collections.addAll(list, elements);
    return list;
  }
}