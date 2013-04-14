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

import static org.elixirian.kommonlee.validation.Assertions.*;

import java.io.Writer;

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
 * @version 0.0.1 (2010-09-18)
 */
public final class StringBuildWriter extends Writer
{
  private final StringBuilder builder;

  public StringBuildWriter()
  {
    this.builder = new StringBuilder();
    lock = builder;
  }

  public StringBuildWriter(final int initialSize)
  {
    assertTrue(0 < initialSize, "Negative int [%s] is entered as a builder initial size", Integer.valueOf(initialSize));
    builder = new StringBuilder(initialSize);
    lock = builder;
  }

  private void write0(final int c)
  {
    builder.append((char) c);

  }

  @Override
  public void write(final int c)
  {
    write0(c);
  }

  @Override
  public void write(final char[] cbuf)
  {
    builder.append(cbuf);
  }

  @Override
  public void write(final char[] cbuf, final int off, final int len)
  {
    if ((0 > off) || (cbuf.length < off) || (0 > len) || (cbuf.length < (off + len)) || (0 > (off + len)))
    {
      throw new IndexOutOfBoundsException();
    }
    else if (0 == len)
    {
      return;
    }
    builder.append(cbuf, off, len);

  }

  private void write0(final String str)
  {
    builder.append(str);

  }

  @Override
  public void write(final String str)
  {
    write0(str);
  }

  @Override
  public void write(final String str, final int off, final int len)
  {
    builder.append(str.substring(off, off + len));
  }

  @Override
  public StringBuildWriter append(final CharSequence csq)
  {
    write0(String.valueOf(csq));
    return this;
  }

  @Override
  public StringBuildWriter append(final CharSequence csq, final int start, final int end)
  {
    final CharSequence charSequence = null == csq ? "null" : csq;
    write0(charSequence.subSequence(start, end)
        .toString());
    return this;
  }

  @Override
  public StringBuildWriter append(final char c)
  {
    write0(c);
    return this;
  }

  @Override
  public String toString()
  {
    return builder.toString();
  }

  public StringBuilder getStringBuilder()
  {
    return builder;
  }

  @Override
  public void flush()
  {
  }

  @Override
  public void close()
  {
  }

}
