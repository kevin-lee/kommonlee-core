package org.elixirian.kommonlee.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

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
 * @version 0.0.1 (2011-04-03)
 */
public final class Sets
{
  private Sets()
  {
  }

  public static <E> HashSet<E> newHashSet()
  {
    return new HashSet<E>();
  }

  public static <E> HashSet<E> newHashSet(final Collection<? extends E> elements)
  {
    return new HashSet<E>(elements);
  }

  public static <E> HashSet<E> newHashSet(final Iterable<? extends E> elements)
  {
    final HashSet<E> set = newHashSet();
    for (final E e : elements)
      set.add(e);
    return set;
  }

  public static <E> HashSet<E> newHashSet(final Iterator<? extends E> elements)
  {
    final HashSet<E> set = newHashSet();
    while (elements.hasNext())
      set.add(elements.next());
    return set;
  }

  public static <E> HashSet<E> newHashSet(final E... elements)
  {
    final HashSet<E> set = newHashSet();
    Collections.addAll(set, elements);
    return set;
  }

  public static <E> HashSet<E> newHashSetWithInitialCapacity(final int initialCapacity)
  {
    return new HashSet<E>(initialCapacity);
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet()
  {
    return new LinkedHashSet<E>();
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet(final Collection<? extends E> elements)
  {
    return new LinkedHashSet<E>(elements);
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet(final Iterable<? extends E> elements)
  {
    final LinkedHashSet<E> set = newLinkedHashSet();
    for (final E e : elements)
      set.add(e);
    return set;
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet(final Iterator<? extends E> elements)
  {
    final LinkedHashSet<E> set = newLinkedHashSet();
    while (elements.hasNext())
      set.add(elements.next());
    return set;
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet(final E... elements)
  {
    final LinkedHashSet<E> set = newLinkedHashSet();
    Collections.addAll(set, elements);
    return set;
  }

  public static <E> LinkedHashSet<E> newLinkedHashSetWithInitialCapacity(final int initialCapacity)
  {
    return new LinkedHashSet<E>(initialCapacity);
  }

  public static <E extends Comparable<E>> TreeSet<E> newTreeSet()
  {
    return new TreeSet<E>();
  }

  public static <E extends Comparable<E>> TreeSet<E> newTreeSet(final Collection<? extends E> elements)
  {
    return new TreeSet<E>(elements);
  }

  public static <E extends Comparable<E>> TreeSet<E> newTreeSet(final Iterable<? extends E> elements)
  {
    final TreeSet<E> set = newTreeSet();
    for (final E e : elements)
      set.add(e);
    return set;
  }

  public static <E extends Comparable<E>> TreeSet<E> newTreeSet(final Iterator<? extends E> elements)
  {
    final TreeSet<E> set = newTreeSet();
    while (elements.hasNext())
      set.add(elements.next());
    return set;
  }

  public static <E extends Comparable<E>> TreeSet<E> newTreeSet(final E... elements)
  {
    final TreeSet<E> set = newTreeSet();
    Collections.addAll(set, elements);
    return set;
  }

  public static <E> TreeSet<E> newTreeSet(final Comparator<? super E> comparator)
  {
    return new TreeSet<E>(comparator);
  }

  public static <E> TreeSet<E> newTreeSet(final Comparator<? super E> comparator, final Collection<? extends E> elements)
  {
    final TreeSet<E> set = new TreeSet<E>(comparator);
    set.addAll(elements);
    return set;
  }

  public static <E> TreeSet<E> newTreeSet(final Comparator<? super E> comparator, final Iterable<? extends E> elements)
  {
    final TreeSet<E> set = new TreeSet<E>(comparator);
    for (final E e : elements)
      set.add(e);
    return set;
  }

  public static <E> TreeSet<E> newTreeSet(final Comparator<? super E> comparator, final Iterator<? extends E> elements)
  {
    final TreeSet<E> set = new TreeSet<E>(comparator);
    while (elements.hasNext())
      set.add(elements.next());
    return set;
  }

  public static <E> TreeSet<E> newTreeSet(final Comparator<? super E> comparator, final E... elements)
  {
    final TreeSet<E> set = new TreeSet<E>(comparator);
    Collections.addAll(set, elements);
    return set;
  }
}