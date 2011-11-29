/**
 * 
 */
package org.elixirian.kommonlee.collect;

import static org.elixirian.kommonlee.collect.Lists.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
 * @version 0.0.1 (2011-10-13)
 */
public class ReadableArrayList<E> extends AbstractReadableList<E> implements ReadableList<E>
{
  private Object[] elements;

  private int length;

  protected ReadableArrayList(final Object... elements)
  {
    final int length = elements.length;
    this.elements = new Object[length];
    System.arraycopy(elements, 0, this.elements, 0, length);
    this.length = this.elements.length;
  }

  protected ReadableArrayList(final Collection<? extends E> collection)
  {
    this(collection.toArray());
  }

  protected ReadableArrayList(final Kollection<? extends E> kollection)
  {
    this(kollection.toArray());
  }

  @Override
  public ArrayList<E> convertTo()
  {
    @SuppressWarnings("unchecked")
    final E[] genericElements = (E[]) elements;
    final ArrayList<E> list = newArrayList(genericElements);
    return list;
  }

  @Override
  public int length()
  {
    return length;
  }

  @Override
  public boolean contains(final Object element)
  {
    return 0 <= indexOf0(element, 0);
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
  public ReadableArrayList<E> select(final Condition1<? super E> condition)
  {
    final List<E> list = newArrayList();
    for (final Object object : this.elements)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) object;
      if (condition.isMet(element))
      {
        list.add(element);
      }
    }
    return new ReadableArrayList<E>(list);
  }

  @Override
  public <R> ReadableArrayList<R> map(final Function1<? super E, R> function)
  {
    final List<R> list = newArrayList();
    for (final Object object : this.elements)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) object;
      list.add(function.apply(element));
    }
    return new ReadableArrayList<R>(list);
  }

  @Override
  public <R> ReadableArrayList<R> mapSelectively(final Condition1<? super E> condition,
      final Function1<? super E, R> function)
  {
    final List<R> list = newArrayList();
    for (final Object object : this.elements)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) object;
      if (condition.isMet(element))
      {
        list.add(function.apply(element));
      }
    }
    return new ReadableArrayList<R>(list);
  }

  @Override
  public E[] toArray()
  {
    @SuppressWarnings("unchecked")
    final E[] copyOf = (E[]) Arrays.copyOf(this.elements, length);
    return copyOf;
  }

  @Override
  public E[] toArray(final E[] elements)
  {
    if (length == elements.length)
    {
      System.arraycopy(this.elements, 0, elements, 0, length);
      return elements;
    }
    @SuppressWarnings("unchecked")
    final E[] copyOf = (E[]) Arrays.copyOf(this.elements, length, elements.getClass());
    return copyOf;
  }

  @Override
  public E get(final int index)
  {
    ensureIndex(index, length);
    @SuppressWarnings("unchecked")
    final E element = (E) this.elements[index];
    return element;
  }

  @Override
  public ReadableList<E> subList(final int fromIndex, final int toIndex)
  {
    ensureRange(fromIndex, toIndex, length);
    final int howMany = toIndex - fromIndex;
    final Object[] subElements = new Object[howMany];
    System.arraycopy(this.elements, fromIndex, subElements, 0, howMany);
    return new ReadableArrayList<E>(subElements);
  }

}
