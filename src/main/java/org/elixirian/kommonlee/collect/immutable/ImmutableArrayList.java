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

import static org.elixirian.kommonlee.util.Objects.*;
import static org.elixirian.kommonlee.util.collect.Lists.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.elixirian.kommonlee.collect.AbstractReadableList;
import org.elixirian.kommonlee.collect.Kollection;
import org.elixirian.kommonlee.collect.McHammerIterator;
import org.elixirian.kommonlee.collect.UnmodifiableIterator;
import org.elixirian.kommonlee.functional.BreakableFunction1;
import org.elixirian.kommonlee.functional.VoidFunction1;
import org.elixirian.kommonlee.type.functional.BreakOrContinue;
import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.functional.Function1;

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
public abstract class ImmutableArrayList<E> extends AbstractReadableList<E> implements ImmutableList<E>
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

  private static final class EmptyImmutableArrayList<E> extends ImmutableArrayList<E>
  {
    private static final Object[] EMPTY_ELEMENTS = new Object[0];

    static final EmptyImmutableArrayList<?> EMPTY_IMMUTABLE_ARRAY_LIST = new EmptyImmutableArrayList<Object>();

    EmptyImmutableArrayList()
    {
      // super(EMPTY_ELEMENTS, 0);
    }

    @Override
    public Object[] toArray()
    {
      return EMPTY_ELEMENTS;
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

    /**
     * It does nothing.
     */
    @Override
    public void breakableForEach(@SuppressWarnings("unused") final BreakableFunction1<? super E> function)
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
    public EmptyImmutableArrayList<E> subList(@SuppressWarnings("unused") final int fromIndex,
        @SuppressWarnings("unused") final int toIndex)
    {
      return this;
    }

    @Override
    public int hashCode()
    {
      return 0;
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
  }

  private static class DefaultImmutableArrayList<E> extends ImmutableArrayList<E>
  {
    private final Object[] elements;

    private final int length;

    DefaultImmutableArrayList(final Collection<? extends E> collection)
    {
      final int length = collection.size();
      final Object[] elements = collection.toArray();
      this.elements = new Object[length];
      System.arraycopy(elements, 0, this.elements, 0, length);
      this.length = length;
    }

    DefaultImmutableArrayList(final Kollection<? extends E> kollection)
    {
      final int length = kollection.length();
      final Object[] elements = kollection.toArray();
      this.elements = new Object[length];
      System.arraycopy(elements, 0, this.elements, 0, length);
      this.length = length;
    }

    DefaultImmutableArrayList(final Object[] elements, final int howMany)
    {
      final int length = Math.min(elements.length, howMany);
      this.elements = new Object[length];
      System.arraycopy(elements, 0, this.elements, 0, length);
      this.length = length;
    }

    DefaultImmutableArrayList(final Object... elements)
    {
      final int length = elements.length;
      this.elements = new Object[length];
      System.arraycopy(elements, 0, this.elements, 0, length);
      this.length = length;
    }

    @Override
    public boolean isEmpty()
    {
      return false;
    }

    @Override
    public boolean isNotEmpty()
    {
      return true;
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
      return 0 <= indexOf0(element, 0);
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
    public void breakableForEach(final BreakableFunction1<? super E> function)
    {
      for (final Object object : this.elements)
      {
        @SuppressWarnings("unchecked")
        final E element = (E) object;
        if (BreakOrContinue.BREAK == function.apply(element))
        {
          break;
        }
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

  }
}