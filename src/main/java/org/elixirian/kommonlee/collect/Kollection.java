/**
 * 
 */
package org.elixirian.kommonlee.collect;

import java.util.Collection;
import java.util.Iterator;

import org.elixirian.kommonlee.type.Condition1;
import org.elixirian.kommonlee.type.EmptinessCheckable;
import org.elixirian.kommonlee.type.Function1;
import org.elixirian.kommonlee.type.LengthMeasurable;
import org.elixirian.kommonlee.type.SizeMeasurable;

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
public interface Kollection<E> extends Iterable<E>, LengthMeasurable, SizeMeasurable, EmptinessCheckable
{
  @Override
  Iterator<E> iterator();

  @Override
  int length();

  /**
   * It must be the same as {@link #length()}.
   */
  @Override
  int size();

  @Override
  boolean isEmpty();

  @Override
  boolean isNotEmpty();

  boolean contains(Object element);

  boolean containsAll(Kollection<?> kollection);

  Kollection<E> select(Condition1<? super E> condition);

  <R> Kollection<R> map(Function1<? super E, R> function);

  <R> Kollection<R> mapSelectively(Condition1<? super E> condition, Function1<? super E, R> function);

  int howMany(Condition1<? super E> conditionToMeet);

  E[] toArray();

  E[] toArray(E[] elements);

  Collection<E> convertTo();

  @Override
  int hashCode();

  @Override
  boolean equals(Object element);

  @Override
  String toString();
}
