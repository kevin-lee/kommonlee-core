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
package org.elixirian.kommonlee.collect.immutable;

import static org.elixirian.kommonlee.collect.KollectionUtil.*;
import static org.elixirian.kommonlee.util.Objects.*;
import static org.elixirian.kommonlee.util.collect.Lists.*;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.elixirian.kommonlee.collect.AbstractReadableList;
import org.elixirian.kommonlee.collect.Kollection;
import org.elixirian.kommonlee.collect.KollectionUtil;
import org.elixirian.kommonlee.collect.McHammerIterator;
import org.elixirian.kommonlee.collect.UnmodifiableIterator;
import org.elixirian.kommonlee.functional.BreakableFunction1;
import org.elixirian.kommonlee.functional.IndexedBreakableFunction1;
import org.elixirian.kommonlee.functional.IndexedVoidFunction1;
import org.elixirian.kommonlee.functional.VoidFunction1;
import org.elixirian.kommonlee.type.functional.BreakOrContinue;
import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.functional.Function1;
import org.elixirian.kommonlee.type.functional.Function2;
import org.elixirian.kommonlee.util.NeoArrays;

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
@SuppressWarnings("serial")
public abstract class ImmutableArrayList<E> extends AbstractReadableList<E> implements ImmutableList<E>, Serializable
{
  static final <E> ImmutableArrayList<E> emptyList()
  {
    @SuppressWarnings("unchecked")
    final ImmutableArrayList<E> emptyImmutableArrayList =
      (ImmutableArrayList<E>) EmptyImmutableArrayList.EMPTY_IMMUTABLE_ARRAY_LIST;
    return emptyImmutableArrayList;
  }

  static <T> ImmutableArrayList<T> getEmptyImmutableArrayList(final int langth)
  {
    if (0 == langth)
    {
      return emptyList();
    }
    return null;
  }

  static <T> ImmutableArrayList<T> copyOf(final Collection<? extends T> collection)
  {
    final ImmutableArrayList<T> emptyImmutableArrayList = getEmptyImmutableArrayList(collection.size());
    return null == emptyImmutableArrayList ? new DefaultImmutableArrayList<T>(collection) : emptyImmutableArrayList;
  }

  static <T> ImmutableArrayList<T> copyOf(final Kollection<? extends T> kollection)
  {
    final ImmutableArrayList<T> emptyImmutableArrayList = getEmptyImmutableArrayList(kollection.length());
    return null == emptyImmutableArrayList ? new DefaultImmutableArrayList<T>(kollection) : emptyImmutableArrayList;
  }

  static <T> ImmutableArrayList<T> listOf(final T... elements)
  {
    final ImmutableArrayList<T> emptyImmutableArrayList = getEmptyImmutableArrayList(elements.length);
    return null == emptyImmutableArrayList ? new DefaultImmutableArrayList<T>(elements) : emptyImmutableArrayList;
  }

  static <T> ImmutableArrayList<T> listOf(final T[] elements, final int howMany)
  {
    final int length = Math.min(elements.length, howMany);

    final ImmutableArrayList<T> emptyImmutableArrayList = getEmptyImmutableArrayList(length);
    return null == emptyImmutableArrayList ? new DefaultImmutableArrayList<T>(elements, length)
        : emptyImmutableArrayList;
  }

  private static class SerializationProxy implements Serializable
  {
    private static final long serialVersionUID = 0L;

    final Object[] elements;

    SerializationProxy(final Object[] elements)
    {
      this.elements = elements;
    }

    Object readResolve()
    {
      return listOf(elements);
    }
  }

  private void readObject(@SuppressWarnings("unused") final ObjectInputStream objectInputStream)
      throws InvalidObjectException
  {
    throw new InvalidObjectException("For serialization, SerializationProxy must be used.");
  }

  protected Object writeReplace()
  {
    return new SerializationProxy(toArray());
  }

  @Override
  public String toString()
  {
    return KollectionUtil.toStringOf("ImmutableList", this);
  }
}

final class EmptyImmutableArrayList<E> extends ImmutableArrayList<E>
{
  private static final long serialVersionUID = 0L;

  static final EmptyImmutableArrayList<?> EMPTY_IMMUTABLE_ARRAY_LIST = new EmptyImmutableArrayList<Object>();

  private EmptyImmutableArrayList()
  {
  }

  @Override
  public Object[] toArray()
  {
    return NeoArrays.EMPTY_OBJECT_ARRAY;
  }

  @Override
  public E[] toArray(final E[] elements)
  {
    mustNotBeNull(elements, "elements cannot be null!");
    @SuppressWarnings("unchecked")
    final E[] newArray = (E[]) Array.newInstance(elements.getClass()
        .getComponentType(), 0);
    return newArray;
  }

  @Override
  public int howMany(@SuppressWarnings("unused") final Condition1<? super E> conditionToMeet)
  {
    return 0;
  }

  @Override
  public <R, F2 extends Function2<? super R, ? super E, R>> R foldLeft(final R startValue,
      @SuppressWarnings("unused") final F2 function)
  {
    return startValue;
  }

  @Override
  public <R, F2 extends Function2<? super R, ? super E, R>> Function1<F2, R> foldLeft(final R startValue)
  {
    return new Function1<F2, R>() {
      @Override
      public R apply(@SuppressWarnings("unused") final F2 function)
      {
        return startValue;
      }
    };
  }

  @Override
  public <R, F2 extends Function2<? super E, ? super R, R>> R foldRight(final R startValue,
      @SuppressWarnings("unused") final F2 function)
  {
    return startValue;
  }

  @Override
  public <R, F2 extends Function2<? super E, ? super R, R>> Function1<F2, R> foldRight(final R startValue)
  {
    return new Function1<F2, R>() {
      @Override
      public R apply(@SuppressWarnings("unused") final F2 function)
      {
        return startValue;
      }
    };
  }

  @Override
  public UnmodifiableIterator<E> iterator()
  {
    final McHammerIterator<E> emptyMcHammerIterator = McHammerIterator.emptyMcHammerIterator();
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
  public E find(@SuppressWarnings("unused") final Condition1<? super E> condition)
  {
    return null;
  }

  @Override
  public EmptyImmutableArrayList<E> select(@SuppressWarnings("unused") final Condition1<? super E> condition)
  {
    return this;
  }

  @Override
  public <R> EmptyImmutableArrayList<R> map(@SuppressWarnings("unused") final Function1<? super E, R> function)
  {
    @SuppressWarnings("unchecked")
    final EmptyImmutableArrayList<R> immutableArrayList = (EmptyImmutableArrayList<R>) this;
    return immutableArrayList;
  }

  @Override
  public <R> EmptyImmutableArrayList<R> mapSelectively(
      @SuppressWarnings("unused") final Condition1<? super E> condition,
      @SuppressWarnings("unused") final Function1<? super E, R> function)
  {
    @SuppressWarnings("unchecked")
    final EmptyImmutableArrayList<R> immutableArrayList = (EmptyImmutableArrayList<R>) this;
    return immutableArrayList;
  }

  /**
   * It does nothing.
   */
  @Override
  public void forEach(@SuppressWarnings("unused") final VoidFunction1<? super E> function)
  {
    return;
  }

  @Override
  public void forEach(@SuppressWarnings("unused") final IndexedVoidFunction1<? super E> function)
  {
    return;
  }

  /**
   * It does nothing.
   */
  @Override
  public void breakableForEach(@SuppressWarnings("unused") final BreakableFunction1<? super E> function)
  {
    return;
  }

  @Override
  public void breakableForEach(@SuppressWarnings("unused") final IndexedBreakableFunction1<? super E> function)
  {
    return;
  }

  @Override
  public E get(@SuppressWarnings("unused") final int index)
  {
    throw new IndexOutOfBoundsException("This is an empty DefaultImmutableArrayList.");
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
  public int indexOf(@SuppressWarnings("unused") final Condition1<? super E> condition)
  {
    return -1;
  }

  @Override
  public int indexOf(@SuppressWarnings("unused") final Condition1<? super E> condition,
      @SuppressWarnings("unused") final int fromIndex)
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
  public int lastIndexOf(@SuppressWarnings("unused") final Condition1<? super E> condition)
  {
    return -1;
  }

  @Override
  public int lastIndexOf(@SuppressWarnings("unused") final Condition1<? super E> condition,
      @SuppressWarnings("unused") final int toIndex)
  {
    return -1;
  }

  @Override
  public EmptyImmutableArrayList<E> subList(@SuppressWarnings("unused") final int fromIndex,
      @SuppressWarnings("unused") final int toIndex)
  {
    return this;
  }

  @Override
  public int hashCode()
  {
    return 1;
  }

  @Override
  public boolean equals(final Object immutableArrayList)
  {
    if (this == immutableArrayList)
    {
      return true;
    }
    final EmptyImmutableArrayList<?> that = castIfInstanceOf(EmptyImmutableArrayList.class, immutableArrayList);
    return null != that;
  }

  @Override
  public List<E> convertTo()
  {
    return Collections.emptyList();
  }

  private Object readResolve()
  {
    return EMPTY_IMMUTABLE_ARRAY_LIST;
  }

  @Override
  public E reduce(@SuppressWarnings("unused") final Function2<? super E, ? super E, E> function)
  {
    return null;
  }
}

@SuppressWarnings("serial")
final class DefaultImmutableArrayList<E> extends ImmutableArrayList<E>
{
  private final transient Object[] elements;

  private final transient int length;

  DefaultImmutableArrayList(final Collection<? extends E> collection)
  {
    final int length = collection.size();
    final Object[] elements = collection.toArray();
    this.elements = Arrays.copyOf(elements, length);
    this.length = this.elements.length;
  }

  DefaultImmutableArrayList(final Kollection<? extends E> kollection)
  {
    final int length = kollection.length();
    final Object[] elements = kollection.toArray();
    this.elements = Arrays.copyOf(elements, length);
    this.length = this.elements.length;
  }

  DefaultImmutableArrayList(final Object[] elements, final int howMany)
  {
    final int length = Math.min(elements.length, howMany);
    this.elements = Arrays.copyOf(elements, length);
    this.length = this.elements.length;
  }

  DefaultImmutableArrayList(final Object... elements)
  {
    final int length = elements.length;
    this.elements = Arrays.copyOf(elements, length);
    this.length = length;
  }

  @Override
  public boolean isEmpty()
  {
    return 0 == length;
  }

  @Override
  public boolean isNotEmpty()
  {
    return !isEmpty();
  }

  @Override
  public Object[] toArray()
  {
    final Object[] copyOf = new Object[length];
    System.arraycopy(elements, 0, copyOf, 0, length);
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
  public List<E> convertTo()
  {
    @SuppressWarnings("unchecked")
    final List<E> list = Collections.unmodifiableList(newArrayList((E[]) this.elements));
    return list;
  }

  @Override
  public UnmodifiableIterator<E> iterator()
  {
    return new McHammerIteratorForReadableList();
  }

  @Override
  public int length()
  {
    return length;
  }

  @Override
  public boolean contains(final Object element)
  {
    return 0 <= indexOf0(element);
  }

  @Override
  public E find(final Condition1<? super E> condition)
  {
    for (final Object object : this.elements)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) object;
      if (condition.isMet(element))
      {
        return element;
      }
    }
    return null;
  }

  @Override
  public ImmutableArrayList<E> select(final Condition1<? super E> condition)
  {
    final Object[] arrayOfObject = new Object[length];
    int i = 0;
    for (final Object object : this.elements)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) object;
      if (condition.isMet(element))
      {
        arrayOfObject[i++] = element;
      }
    }
    @SuppressWarnings("unchecked")
    final ImmutableArrayList<E> copyOf = (ImmutableArrayList<E>) listOf(arrayOfObject, i);
    return copyOf;
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
  public void forEach(final VoidFunction1<? super E> function)
  {
    for (final Object object : this.elements)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) object;
      function.apply(element);
    }
  }

  @Override
  public void forEach(final IndexedVoidFunction1<? super E> function)
  {
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) this.elements[i];
      function.apply(i, element);
    }
  }

  @Override
  public void breakableForEach(final BreakableFunction1<? super E> function)
  {
    for (final Object object : this.elements)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) object;
      if (BreakOrContinue.BREAK == function.apply(element))
      {
        return;
      }
    }
  }

  @Override
  public void breakableForEach(final IndexedBreakableFunction1<? super E> function)
  {
    for (int i = 0; i < length; i++)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) this.elements[i];
      if (BreakOrContinue.BREAK == function.apply(i, element))
      {
        return;
      }
    }
  }

  @Override
  public <R, F2 extends Function2<? super R, ? super E, R>> R foldLeft(final R startValue, final F2 function)
  {
    R result = startValue;
    for (final Object object : this.elements)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) object;
      result = function.apply(result, element);
    }
    return result;
  }

  @Override
  public <R, F2 extends Function2<? super R, ? super E, R>> Function1<F2, R> foldLeft(final R startValue)
  {
    return new Function1<F2, R>() {
      @Override
      public R apply(final F2 function)
      {
        return foldLeft(startValue, function);
      }
    };
  }

  @Override
  public <R, F2 extends Function2<? super E, ? super R, R>> R foldRight(final R startValue, final F2 function)
  {
    R result = startValue;
    for (int i = length - 1; i >= 0; i--)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) elements[i];
      result = function.apply(element, result);
    }
    return result;
  }

  @Override
  public <R, F2 extends Function2<? super E, ? super R, R>> Function1<F2, R> foldRight(final R startValue)
  {
    return new Function1<F2, R>() {

      @Override
      public R apply(final F2 function)
      {
        return foldRight(startValue, function);
      }
    };
  }

  @Override
  public E get(final int index)
  {
    checkIndex(length, index);
    @SuppressWarnings("unchecked")
    final E e = (E) this.elements[index];
    return e;
  }

  @Override
  public ImmutableArrayList<E> subList(final int fromIndex, final int toIndex)
  {
    checkRange(length, fromIndex, toIndex);
    final int howMany = toIndex - fromIndex;
    final Object[] subElements = new Object[howMany];
    System.arraycopy(this.elements, fromIndex, subElements, 0, howMany);
    return new DefaultImmutableArrayList<E>(subElements);
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
    final ImmutableArrayList<?> that = castIfInstanceOf(ImmutableArrayList.class, immutableArrayList);
    /* @formatter:off */
    return null != that &&
          deepEqual(this.elements, that.toArray());
  /* @formatter:on */
  }

  @Override
  public E reduce(final Function2<? super E, ? super E, E> function)
  {
    if (length == 0)
    {
      return null;
    }
    @SuppressWarnings("unchecked")
    E result = (E) this.elements[0];
    if (length == 1)
    {
      return result;
    }
    for (int i = 1; i < length; i++)
    {
      @SuppressWarnings("unchecked")
      final E element = (E) this.elements[i];
      result = function.apply(result, element);
    }
    return result;
  }
}
