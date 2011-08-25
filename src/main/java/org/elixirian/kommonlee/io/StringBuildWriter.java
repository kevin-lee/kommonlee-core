/**
 * 
 */
package org.elixirian.kommonlee.io;

import static org.elixirian.kommonlee.validation.Assertions.*;

import java.io.Writer;

/**
 * <pre>
 *     ____________    ___________  ____   _______ _________ _______ _______________  ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /     \/   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /          /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
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

  private void write0(int c)
  {
    builder.append((char) c);

  }

  @Override
  public void write(int c)
  {
    write0(c);
  }

  @Override
  public void write(char[] cbuf)
  {
    builder.append(cbuf);
  }

  @Override
  public void write(char[] cbuf, int off, int len)
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

  private void write0(String str)
  {
    builder.append(str);

  }

  @Override
  public void write(String str)
  {
    write0(str);
  }

  @Override
  public void write(String str, int off, int len)
  {
    builder.append(str.substring(off, off + len));
  }

  @Override
  public StringBuildWriter append(CharSequence csq)
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
  public StringBuildWriter append(char c)
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
