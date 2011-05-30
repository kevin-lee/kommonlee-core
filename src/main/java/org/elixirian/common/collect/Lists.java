package org.elixirian.common.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

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
   * @return a new instance of {@link ArrayList} containing the elements of the specified collection, in the order they
   *         are returned by the collection's iterator.
   */
  public static <E> ArrayList<E> newArrayList(final Collection<? extends E> elements)
  {
    return new ArrayList<E>(elements);
  }

  public static <E> ArrayList<E> newArrayList(final Iterable<? extends E> elements)
  {
    final ArrayList<E> list = newArrayList();
    for (final E e : elements)
      list.add(e);
    return list;
  }

  public static <E> ArrayList<E> newArrayList(final Iterator<? extends E> elements)
  {
    final ArrayList<E> list = newArrayList();
    while (elements.hasNext())
      list.add(elements.next());
    return list;
  }

  public static <E> ArrayList<E> newArrayList(final E... e)
  {
    final ArrayList<E> list = newArrayList();
    Collections.addAll(list, e);
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

  public static <E> LinkedList<E> newLinkedList(final E... e)
  {
    final LinkedList<E> list = newLinkedList();
    Collections.addAll(list, e);
    return list;
  }
}