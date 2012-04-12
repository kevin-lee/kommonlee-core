/**
 * 
 */
package org.elixirian.kommonlee.collect;

import static org.elixirian.kommonlee.collect.Lists.*;
import static org.elixirian.kommonlee.util.Objects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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
 * @version 0.0.1 (2011-09-28)
 */
public class ImmutableArrayList<E> extends AbstractReadableList<E> implements ImmutableList<E>
{
  private final Object[] elements;

  private final int length;

  private static class EmptyImmutableArrayList<E> extends ImmutableArrayList<E>
  {
    private static final Object[] EMPTY_ELEMENTS = new Object[0];

    EmptyImmutableArrayList()
    {
    }

    @Override
    public E[] toArray()
    {
      @SuppressWarnings("unchecked")
      final E[] emptyElements = (E[]) EMPTY_ELEMENTS;
      return emptyElements;
    }

    @Override
    public E[] toArray(final E[] elements)
    {
      return elements;
    }

    @Override
    public int howMany(@SuppressWarnings("unused") final Condition1<? super E> conditionToMeet)
    {
      return 0;
    }

    private static final McHammerIterator<?> EMPTY_MC_HAMMER_ITERATOR = new McHammerIterator<Object>() {

      @Override
      public boolean hasNext()
      {
        return false;
      }

      @Override
      public Object next()
      {
        throw new NoSuchElementException();
      }
    };

    @Override
    public Iterator<E> iterator()
    {
      @SuppressWarnings("unchecked")
      final Iterator<E> emptyMcHammerIterator = (Iterator<E>) EMPTY_MC_HAMMER_ITERATOR;
      return emptyMcHammerIterator;
    }

    @Override
    public int length()
    {
      return 0;
    }

    @Override
    public boolean isEmpty()
    {
      return true;
    }

    @Override
    public boolean isNotEmpty()
    {
      return false;
    }

    @Override
    public boolean contains(@SuppressWarnings("unused") final Object element)
    {
      return false;
    }

    @Override
    public boolean containsAll(@SuppressWarnings("unused") final Kollection<?> kollection)
    {
      return false;
    }

    @Override
    public ImmutableArrayList<E> select(@SuppressWarnings("unused") final Condition1<? super E> condition)
    {
      return this;
    }

    @Override
    public <R> ImmutableArrayList<R> map(@SuppressWarnings("unused") final Function1<? super E, R> function)
    {
      @SuppressWarnings("unchecked")
      final ImmutableArrayList<R> immutableArrayList = (ImmutableArrayList<R>) this;
      return immutableArrayList;
    }

    @Override
    public <R> ImmutableArrayList<R> mapSelectively(@SuppressWarnings("unused") final Condition1<? super E> condition,
        @SuppressWarnings("unused") final Function1<? super E, R> function)
    {
      @SuppressWarnings("unchecked")
      final ImmutableArrayList<R> immutableArrayList = (ImmutableArrayList<R>) this;
      return immutableArrayList;
    }

    @Override
    public E get(@SuppressWarnings("unused") final int index)
    {
      throw new IndexOutOfBoundsException("This is an empty ImmutableArrayList.");
    }

    @Override
    public int indexOf(@SuppressWarnings("unused") final E element)
    {
      return -1;
    }

    @Override
    public int indexOf(@SuppressWarnings("unused") final E element, @SuppressWarnings("unused") final int fromIndex)
    {
      return -1;
    }

    @Override
    public int lastIndexOf(@SuppressWarnings("unused") final E element)
    {
      return -1;
    }

    @Override
    public int lastIndexOf(@SuppressWarnings("unused") final E element, @SuppressWarnings("unused") final int toIndex)
    {
      return -1;
    }

    @Override
    public ImmutableArrayList<E> subList(@SuppressWarnings("unused") final int fromIndex,
        @SuppressWarnings("unused") final int toIndex)
    {
      return this;
    }

  }

  public static final ImmutableArrayList<?> EMPTY_IMMUTABLE_ARRAY_LIST = new EmptyImmutableArrayList<Object>();

  ImmutableArrayList(final Collection<? extends E> collection)
  {
    final int length = collection.size();
    final Object[] elements = collection.toArray();
    this.elements = new Object[length];
    System.arraycopy(elements, 0, this.elements, 0, length);
    this.length = length;
  }

  ImmutableArrayList(final Kollection<? extends E> kollection)
  {
    final int length = kollection.length();
    final Object[] elements = kollection.toArray();
    this.elements = new Object[length];
    System.arraycopy(elements, 0, this.elements, 0, length);
    this.length = length;
  }

  ImmutableArrayList(final Object[] elements, final int howMany)
  {
    final int length = Math.min(elements.length, howMany);
    this.elements = new Object[length];
    System.arraycopy(elements, 0, this.elements, 0, length);
    this.length = length;
  }

  ImmutableArrayList(final Object... elements)
  {
    final int length = elements.length;
    this.elements = new Object[length];
    System.arraycopy(elements, 0, this.elements, 0, length);
    this.length = length;
  }

  private static <T> ImmutableArrayList<T> getEmptyImmutableArrayList(final int langth)
  {
    if (0 == langth)
    {
      @SuppressWarnings("unchecked")
      final ImmutableArrayList<T> emptyImmutableArrayList = (ImmutableArrayList<T>) EMPTY_IMMUTABLE_ARRAY_LIST;
      return emptyImmutableArrayList;
    }
    return null;
  }

  public static <T> ImmutableArrayList<T> copyOf(final Collection<? extends T> collection)
  {
    final ImmutableArrayList<T> emptyImmutableArrayList = getEmptyImmutableArrayList(collection.size());
    return null == emptyImmutableArrayList ? new ImmutableArrayList<T>(collection) : emptyImmutableArrayList;
  }

  public static <T> ImmutableArrayList<T> copyOf(final Kollection<? extends T> commonCollection)
  {
    final ImmutableArrayList<T> emptyImmutableArrayList = getEmptyImmutableArrayList(commonCollection.length());
    return null == emptyImmutableArrayList ? new ImmutableArrayList<T>(commonCollection) : emptyImmutableArrayList;
  }

  public static <T> ImmutableArrayList<T> listOf(final T... elements)
  {
    final ImmutableArrayList<T> emptyImmutableArrayList = getEmptyImmutableArrayList(elements.length);
    return null == emptyImmutableArrayList ? new ImmutableArrayList<T>(elements) : emptyImmutableArrayList;
  }

  public static <T> ImmutableArrayList<T> listOf(final T[] elements, final int howMany)
  {
    final int length = Math.min(elements.length, howMany);

    final ImmutableArrayList<T> emptyImmutableArrayList = getEmptyImmutableArrayList(length);
    return null == emptyImmutableArrayList ? new ImmutableArrayList<T>(elements, length) : emptyImmutableArrayList;
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
  public ArrayList<E> convertTo()
  {
    @SuppressWarnings("unchecked")
    final ArrayList<E> list = newArrayList((E[]) this.elements);
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
  public ImmutableArrayList<E> select(final Condition1<? super E> condition)
  {
    final List<E> list = newArrayList();
    // final Object[] arrayOfObject = new Object[length];
    final int i = 0;
    for (final Object object : this.elements)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) object;
      if (condition.isMet(element))
      {
        list.add(element);
        // arrayOfObject[i++] = element;
      }
    }
    // return new ImmutableArrayList<E>(list);
    // @SuppressWarnings("unchecked")
    // final E[] arrayOfE = (E[]) arrayOfObject;
    // return listOf(arrayOfE, i);
    return copyOf(list);
  }

  @Override
  public <R> ImmutableArrayList<R> map(final Function1<? super E, R> function)
  {
    final Object[] array = new Object[length];
    int i = 0;
    for (final Object object : this.elements)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) object;
      array[i++] = function.apply(element);
    }
    @SuppressWarnings("unchecked")
    final R[] arrayOfR = (R[]) array;
    return listOf(arrayOfR);
  }

  @Override
  public <R> ImmutableArrayList<R> mapSelectively(final Condition1<? super E> condition,
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
    return copyOf(list);
  }

  @Override
  public void forEach(final Function1<? super E, Void> function)
  {
    for (final Object object : this.elements)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) object;
      function.apply(element);
    }
  }

  @Override
  public E get(final int index)
  {
    ensureIndex(index, length);
    @SuppressWarnings("unchecked")
    final E e = (E) this.elements[index];
    return e;
  }

  @Override
  public ImmutableArrayList<E> subList(final int fromIndex, final int toIndex)
  {
    ensureRange(fromIndex, toIndex, length);
    final int howMany = toIndex - fromIndex;
    final Object[] subElements = new Object[howMany];
    System.arraycopy(this.elements, fromIndex, subElements, 0, howMany);
    return new ImmutableArrayList<E>(subElements);
  }

  @Override
  public int hashCode()
  {
    return hash(this.elements);
  }

  @Override
  public boolean equals(final Object immutableArrayList)
  {
    if (this == immutableArrayList)
    {
      return true;
    }
    @SuppressWarnings("unchecked")
    final ImmutableArrayList<E> that = castIfInstanceOf(ImmutableArrayList.class, immutableArrayList);
    return isNotNull(that) && deepEqual(this.elements, that.toArray());
  }

}
