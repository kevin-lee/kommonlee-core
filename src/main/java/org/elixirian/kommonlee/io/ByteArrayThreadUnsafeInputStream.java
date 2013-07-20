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
package org.elixirian.kommonlee.io;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import org.elixirian.kommonlee.type.checkable.EmptinessCheckable;
import org.elixirian.kommonlee.type.checkable.LengthCheckable;
import org.elixirian.kommonlee.type.checkable.NotEmptinessCheckable;

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
 * @version 0.0.1 (2013-07-17)
 */
public class ByteArrayThreadUnsafeInputStream extends ByteArrayInputStream implements LengthCheckable,
    EmptinessCheckable, NotEmptinessCheckable
{
  public ByteArrayThreadUnsafeInputStream(final byte[] bytes)
  {
    this(bytes, 0, bytes.length);
  }

  public ByteArrayThreadUnsafeInputStream(final byte[] bytes, final int offset, final int length)
  {
    super(bytes, offset, length);
  }

  @SuppressWarnings("sync-override")
  @Override
  public int read()
  {
    if (pos < count)
    {
      final int b = buf[pos] & 0xFF;
      pos++;
      return b;
    }
    return -1;
  }

  @Override
  public int read(final byte[] b)
  {
    if (null == b)
    {
      throw new NullPointerException("The given byte array b is null.");
    }
    return read(b, 0, b.length);
  }

  @SuppressWarnings("sync-override")
  @Override
  public int read(final byte[] b, final int off, final int len) throws IndexOutOfBoundsException
  {
    if (null == b)
    {
      throw new NullPointerException("The given byte array b is null.");
    }
    if (0 > off || 0 > len || len > b.length - off)
    {
      throw new IndexOutOfBoundsException(
          "off and len must be non-negative int and len must not be greater than b.length - off. [b.length: "
              + b.length + ", off: " + off + ", len: " + len + "]");
    }
    if (0 >= len)
    {
      return 0;
    }
    if (count <= pos)
    {
      return -1;
    }

    final int length = len + pos > count ? count - pos : len;
    System.arraycopy(this.buf, pos, b, off, length);
    pos += length;
    return length;
  }

  @SuppressWarnings("sync-override")
  @Override
  public long skip(final long n)
  {
    long skip = count - pos;
    if (skip > n)
    {
      skip = 0 > n ? 0 : n;
    }
    pos += skip;
    return skip;
  }

  @SuppressWarnings("sync-override")
  @Override
  public int available()
  {
    return count - pos;
  }

  /**
   * This method does NOTHING.
   */
  @Override
  public void close()
  {
  }

  /**
   * Set the current marked position in the stream. ByteArrayInputStream objects are marked at position zero by default
   * when constructed. They may be marked at another position within the buffer by this method.
   * <p>
   * If no mark has been set, then the value of the mark is the offset passed to the constructor (or 0 if the offset was
   * not supplied).
   * <p>
   * <strong> Note: The <code>readlimit</code> parameter is not used at all.</strong>
   */
  @Override
  public void mark(@SuppressWarnings("unused") final int readlimit)
  {
    mark = pos;
  }

  /**
   * Resets the current position of the byte array to the marked position. The marked position is 0 unless another
   * position was marked or an offset was specified in the constructor.
   */
  @SuppressWarnings("sync-override")
  @Override
  public void reset()
  {
    pos = mark;
  }

  @Override
  public boolean markSupported()
  {
    return true;
  }

  public byte[] toByteArrayStartingFromMark()
  {
    final int length = this.count - mark;
    final byte[] bytes = new byte[length];
    System.arraycopy(this.buf, mark, bytes, 0, length);
    return bytes;
  }

  public byte[] toByteArray()
  {
    return Arrays.copyOf(this.buf, this.count);
  }

  public int getCurrentPosition()
  {
    return pos;
  }

  public int getMark()
  {
    return mark;
  }

  @Override
  public boolean isNotEmpty()
  {
    return !isEmpty();
  }

  @Override
  public boolean isEmpty()
  {
    return 0 == count;
  }

  @Override
  public int length()
  {
    return count;
  }
}
