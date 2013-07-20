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
package org.elixirian.kommonlee.collect.primitive.mutable;

import static org.elixirian.kommonlee.collect.KollectionUtil.*;
import static org.elixirian.kommonlee.util.Objects.*;
import static org.elixirian.kommonlee.util.collect.Lists.*;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.elixirian.kommonlee.collect.primitive.ByteList;
import org.elixirian.kommonlee.type.functional.BreakOrContinue;
import org.elixirian.kommonlee.type.functional.primitive.BreakableByteFunction1;
import org.elixirian.kommonlee.type.functional.primitive.ByteCondition1;
import org.elixirian.kommonlee.type.functional.primitive.ByteToByteFunction1;
import org.elixirian.kommonlee.type.functional.primitive.VoidByteFunction1;
import org.elixirian.kommonlee.util.NeoArrays;
import org.elixirian.kommonlee.util.Objects;

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
 * @version 0.0.1 (2013-07-13)
 */
public class MutableByteArrayList implements MutableByteList
{
  public static final int DEFAULT_INITIAL_LENGTH = 8;

  public static final int DEFAULT_INCREASE_BY = 16;

  private byte[] elements;

  private int length;

  private final int initialCapacity;

  private int increaseBy;

  private int modCount = 0;

  public MutableByteArrayList(final byte[] elements, final int length, final int increaseBy)
  {
    checkCapacityAndLength(elements.length, length);
    this.elements = Arrays.copyOf(elements, length);
    this.length = length;
    this.initialCapacity = length;
    this.increaseBy = increaseBy;
  }

  public MutableByteArrayList(final int initialCapacity, final int increaseBy)
  {
    checkInitialLength(initialCapacity);
    this.elements = new byte[initialCapacity];
    this.length = 0;
    this.initialCapacity = initialCapacity;
    this.increaseBy = increaseBy;
  }

  public MutableByteArrayList(final byte[] elements)
  {
    this(elements, elements.length, DEFAULT_INCREASE_BY);
  }

  public MutableByteArrayList()
  {
    this(DEFAULT_INITIAL_LENGTH, DEFAULT_INCREASE_BY);
  }

  @Override
  public byte get(final int index)
  {
    checkIndex(length, index);
    return elements[index];
  }

  @Override
  public int indexOf(final byte element, final int fromIndex)
  {
    checkIndex(length, fromIndex);
    for (int i = fromIndex; i < length; i++)
      if (element == this.elements[i])
      {
        return i;
      }
    return -1;
  }

  @Override
  public int indexOf(final byte element)
  {
    return indexOf(element, 0);
  }

  @Override
  public int lastIndexOf(final byte element, final int toIndex)
  {
    final int toIndexToUse = toIndex - 1;
    checkIndex(length, toIndexToUse);
    for (int i = toIndexToUse; i >= 0; i--)
    {
      if (element == this.elements[i])
      {
        return i;
      }
    }
    return -1;
  }

  @Override
  public int lastIndexOf(final byte element)
  {
    return lastIndexOf(element, length);
  }

  @Override
  public boolean exists(final ByteCondition1 seeker)
  {
    for (int i = 0; i < length; i++)
    {
      if (seeker.isMet(elements[i]))
      {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean contains(final byte element)
  {
    for (int i = 0; i < length; i++)
    {
      if (element == elements[i])
      {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsAll(final byte[] elements)
  {
    for (final byte b : elements)
    {
      if (!contains(b))
      {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean containsAll(final ByteList elements)
  {
    for (final byte b : elements.toArray())
    {
      if (!contains(b))
      {
        return false;
      }
    }
    return true;
  }

  @Override
  public MutableByteList select(final ByteCondition1 condition)
  {
    final MutableByteList byteList = new MutableByteArrayList(length, DEFAULT_INCREASE_BY);
    for (int i = 0; i < length; i++)
    {
      final byte b = this.elements[i];
      if (condition.isMet(b))
      {
        byteList.add(b);
      }
    }
    byteList.trim();
    return byteList;
  }

  @Override
  public MutableByteList mapToByte(final ByteToByteFunction1 function)
  {
    final MutableByteList byteList = new MutableByteArrayList(length, DEFAULT_INCREASE_BY);
    for (int i = 0; i < length; i++)
    {
      /* @formatter:off */
      byteList.add(function
                  .apply(this.elements[i]));
      /* @formatter:on */
    }
    return byteList;
  }

  @Override
  public MutableByteList mapToByteSelectively(final ByteCondition1 condition, final ByteToByteFunction1 function)
  {
    final MutableByteList byteList = new MutableByteArrayList(length, DEFAULT_INCREASE_BY);
    for (int i = 0; i < length; i++)
    {
      final byte b = this.elements[i];
      if (condition.isMet(b))
      {
        /* @formatter:off */
        byteList.add(function
                    .apply(b));
        /* @formatter:on */
      }
    }
    byteList.trim();
    return byteList;
  }

  @Override
  public void forEach(final VoidByteFunction1 function)
  {
    for (int i = 0; i < length; i++)
    {
      function.apply(this.elements[i]);
    }
  }

  @Override
  public void forEach(final ByteToByteFunction1 function)
  {
    for (int i = 0; i < length; i++)
    {
      this.elements[i] = function.apply(this.elements[i]);
    }
  }

  @Override
  public void breakableForEach(final BreakableByteFunction1 function)
  {
    for (int i = 0; i < length; i++)
    {
      if (BreakOrContinue.BREAK == function.apply(this.elements[i]))
      {
        return;
      }
    }
  }

  @Override
  public int howMany(final ByteCondition1 conditionToMeet)
  {
    int count = 0;
    for (int i = 0; i < length; i++)
    {
      if (conditionToMeet.isMet(this.elements[i]))
      {
        count++;
      }
    }
    return count;
  }

  @Override
  public MutableByteList subList(final int fromIndex, final int toIndex)
  {
    checkRange(length, fromIndex, toIndex);
    final int newLength = toIndex - fromIndex;
    final byte[] newElements = new byte[newLength];
    System.arraycopy(this.elements, fromIndex, newElements, 0, newLength);

    return new MutableByteArrayList(newElements);
  }

  @Override
  public byte[] toArray()
  {
    final byte[] newElements = new byte[length];
    System.arraycopy(this.elements, 0, newElements, 0, length);
    return newElements;
  }

  private class InternalIterator implements Iterator<Byte>
  {

    int current = 0;
    int last = -1;
    int expectedModCount = modCount;

    private void checkForComodification()
    {
      if (modCount != expectedModCount)
      {
        throw new ConcurrentModificationException();
      }
    }

    @Override
    public void remove()
    {
      if (0 > last)
      {
        throw new IllegalStateException();
      }
      checkForComodification();
      try
      {
        MutableByteArrayList.this.removeAt(last);
        current = last;
        last = -1;
        expectedModCount = modCount;
      }
      catch (final IndexOutOfBoundsException e)
      {
        throw new ConcurrentModificationException(
            "ConcurrentModificationException is thrown when removing an element at the index, " + last + ". cause: "
                + e.toString());
      }
    }

    @Override
    public Byte next()
    {
      checkForComodification();

      final int index = current;
      if (index >= length)
      {
        throw new NoSuchElementException();
      }
      final byte[] elements = MutableByteArrayList.this.elements;
      if (index >= elements.length)
      {
        throw new ConcurrentModificationException("index >= capacity [index: " + index + ", capacity: "
            + elements.length + "]");
      }
      @SuppressWarnings("boxing")
      final Byte next = elements[index];
      current = index + 1;
      last = index;
      return next;
    }

    @Override
    public boolean hasNext()
    {
      return current < length;
    }
  }

  @Override
  public Iterator<Byte> iterator()
  {
    return new InternalIterator();
  }

  @Override
  public int length()
  {
    return length;
  }

  @Override
  public int size()
  {
    return length();
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
  public Byte[] toBoxedArray()
  {
    final Byte[] boxedElements = new Byte[length];
    for (int i = 0; i < length; i++)
    {
      boxedElements[i] = Byte.valueOf(this.elements[i]);
    }
    return boxedElements;
  }

  @Override
  public List<Byte> convertTo()
  {
    return newArrayList(toBoxedArray());
  }

  private void ensureCapacity(final int lengthAfterAdding)
  {
    modCount++;
    if (0 < lengthAfterAdding - elements.length)
    {
      int newCapacity = elements.length + increaseBy;
      if (0 > newCapacity - lengthAfterAdding)
      {
        newCapacity = lengthAfterAdding;
      }
      if (0 > newCapacity)
      {
        if (0 > lengthAfterAdding)
        {
          throw new OutOfMemoryError();
        }
        newCapacity = Integer.MAX_VALUE;
      }
      this.elements = Arrays.copyOf(this.elements, newCapacity);
    }
  }

  @Override
  public boolean add(final byte element)
  {
    ensureCapacity(length + 1);
    this.elements[length] = element;
    length++;
    return true;
  }

  @Override
  public boolean addAll(final byte[] elements)
  {
    final int oldLength = length;
    final int newElementLength = elements.length;
    ensureCapacity(oldLength + newElementLength);
    System.arraycopy(elements, 0, this.elements, oldLength, newElementLength);
    length += newElementLength;
    return oldLength != length;
  }

  @Override
  public boolean addAll(final ByteList elements)
  {
    return addAll(elements.toArray());
  }

  @Override
  public boolean remove(final byte element)
  {
    for (int i = 0; i < length; i++)
    {
      if (this.elements[i] == element)
      {
        remove0(i);
        return true;
      }
    }
    return false;
  }

  private void remove0(final int index)
  {
    modCount++;
    final int howManyToMove = length - index - 1;
    if (0 < howManyToMove)
    {
      System.arraycopy(this.elements, index + 1, this.elements, index, howManyToMove);
    }
    length--;
    elements[length] = 0;
  }

  @Override
  public byte removeAt(final int index)
  {
    checkIndex(this.length, index);
    final byte b = this.elements[index];
    remove0(index);
    return b;
  }

  @Override
  public int getIncreaseBy()
  {
    return increaseBy;
  }

  int getCapacity()
  {
    return this.elements.length;
  }

  @Override
  public MutableByteArrayList trim()
  {
    modCount++;
    if (length != elements.length)
    {
      this.elements = Arrays.copyOf(this.elements, length);
    }
    return this;
  }

  @Override
  public void clear()
  {
    modCount++;
    elements = new byte[initialCapacity];
    length = 0;
  }

  @Override
  public int hashCode()
  {
    return NeoArrays.hash(this.elements, 0, length);
  }

  @Override
  public boolean equals(final Object mutableByteArrayList)
  {
    if (this == mutableByteArrayList)
    {
      return true;
    }
    final ByteList that = castIfInstanceOf(ByteList.class, mutableByteArrayList);
    if (null == that)
    {
      return false;
    }
    final int thisLength = this.length;
    final int thatLength = that.length();
    if (thisLength != thatLength)
    {
      return false;
    }

    int thisIndex = 0;
    int thatIndex = 0;
    while (thisLength > thisIndex && thatLength > thatIndex)
    {
      if (this.elements[thisIndex] != that.get(thatIndex))
      {
        return false;
      }
      thisIndex++;
      thatIndex++;
    }
    return true;
  }

  @Override
  public String toString()
  {
    return Objects.toStringOf(this.elements);
  }
}
