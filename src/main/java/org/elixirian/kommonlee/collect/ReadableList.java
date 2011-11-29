/**
 * 
 */
package org.elixirian.kommonlee.collect;

import java.util.Iterator;

import org.elixirian.kommonlee.type.Condition1;
import org.elixirian.kommonlee.type.Function1;

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
   *          toIndex (exclusive)
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
  E[] toArray();

  @Override
  E[] toArray(E[] elements);

  @Override
  int hashCode();

  @Override
  boolean equals(Object element);

  E get(int index);

  ReadableList<E> subList(int fromIndex, int toIndex);
}
