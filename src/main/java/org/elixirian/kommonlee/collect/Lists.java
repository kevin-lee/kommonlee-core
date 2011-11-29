package org.elixirian.kommonlee.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

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
  private Lists()
  {
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