/**
 * 
 */
package org.elixirian.kommonlee.collect;

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
public interface WritableList<E> extends WritableCollection<E>
{
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

  void add(int index, E element);

  @Override
  int hashCode();

  @Override
  boolean equals(Object element);
}
