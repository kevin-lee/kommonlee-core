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
package org.elixirian.kommonlee.nio.util;

import static org.elixirian.kommonlee.io.IoCommonConstants.*;
import static org.elixirian.kommonlee.util.MessageFormatter.*;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.elixirian.kommonlee.io.ByteArrayConsumer;
import org.elixirian.kommonlee.io.ByteArrayConsumingContainer;
import org.elixirian.kommonlee.io.ByteArrayProducer;
import org.elixirian.kommonlee.io.CharArrayConsumer;
import org.elixirian.kommonlee.io.DataConsumers;
import org.elixirian.kommonlee.io.IoCommonConstants;
import org.elixirian.kommonlee.io.exception.RuntimeFileNotFoundException;
import org.elixirian.kommonlee.io.exception.RuntimeIoException;
import org.elixirian.kommonlee.io.util.IoUtil;
import org.elixirian.kommonlee.util.CommonConstants;

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
 * @version 0.0.1 (2010-07-13)
 * @version 0.0.2 (2010-11-03) moved from the elixirian-common-filemanager package.
 */
public final class NioUtil
{
  /**
   * the value of {@link org.elixirian.kommonlee.io.IoCommonConstants#BUFFER_SIZE_128Ki}
   */
  public static final int DEFAULT_BUFFER_SIZE = BUFFER_SIZE_128Ki;

  private NioUtil() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  /**
   * Calls close() method on any {@link Closeable} object without throwing {@link IOException}.
   * 
   * @param closeable
   *          the given {@link Closeable} object the close() method of which is to be called by this method.
   */
  public static void closeQuietly(final Closeable closeable)
  {
    try
    {
      if (null != closeable)
        closeable.close();
    }
    catch (final IOException e)
    {
      /* just ignore this exception. */
      // System.out.println(format("log from %s (%s.java:%s)\nClosing [%s] has failed.\nCaused at %s",
      // NioUtil.class, NioUtil.class.getSimpleName(), Integer.valueOf(Thread.currentThread()
      // .getStackTrace()[1].getLineNumber()), closeable, e.getStackTrace()[0]));
      System.out.println(format("log from %s (%s.java:%s)\nClosing [%s] has failed.\n", NioUtil.class,
          NioUtil.class.getSimpleName(), Integer.valueOf(Thread.currentThread()
              .getStackTrace()[1].getLineNumber()), closeable));
      e.printStackTrace();
    }
  }

  public static void assertBufferSize(final int bufferSize)
  {
    IoUtil.assertBufferSize(bufferSize);
  }

  public static byte[] readInputStreamToByteArray(final InputStream inputStream, final int bufferSize)
  {
    final ByteArrayConsumingContainer byteArrayConsumingContainer =
      DataConsumers.newByteArrayConsumingContainer(bufferSize);
    readInputStream(inputStream, bufferSize, byteArrayConsumingContainer);
    return byteArrayConsumingContainer.toByteArray();
  }

  public static void readInputStream(final InputStream inputStream, final int bufferSize,
      final ByteArrayConsumer byteArrayConsumer)
  {
    assertBufferSize(bufferSize);
    ReadableByteChannel readableByteChannel = null;
    try
    {
      readableByteChannel = Channels.newChannel(inputStream);
      final byte[] buffer = new byte[bufferSize];
      final ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
      int byteRead = readableByteChannel.read(byteBuffer);
      while (-1 != byteRead)
      {
        byteArrayConsumer.consume(buffer, 0, byteRead);
        byteBuffer.clear();
        byteRead = readableByteChannel.read(byteBuffer);
      }
    }
    catch (final IOException e)
    {
      throw new RuntimeIoException(e);
    }
    finally
    {
      closeQuietly(readableByteChannel);
      closeQuietly(inputStream);
    }
  }

  public static void readInputStream(final InputStream inputStream, final int bufferSize, final Charset charset,
      final CharArrayConsumer charArrayConsumer)
  {
    assertBufferSize(bufferSize);
    ReadableByteChannel readableByteChannel = null;
    Reader reader = null;
    try
    {
      readableByteChannel = Channels.newChannel(inputStream);
      final CharsetDecoder charsetDecoder = charset.newDecoder();
      reader = Channels.newReader(readableByteChannel, charsetDecoder, bufferSize);
      final char[] buffer = new char[bufferSize];
      final CharBuffer charBuffer = CharBuffer.wrap(buffer);
      int charsRead = reader.read(charBuffer);
      while (-1 != charsRead)
      {
        charArrayConsumer.consume(buffer, 0, charsRead);
        charBuffer.clear();
        charsRead = reader.read(charBuffer);
      }
    }
    catch (final IOException e)
    {
      throw new RuntimeIoException(e);
    }
    finally
    {
      closeQuietly(reader);
      closeQuietly(readableByteChannel);
      closeQuietly(inputStream);
    }
  }

  public static byte[] readFileToByteArray(final File file, final int bufferSize)
  {
    final ByteArrayConsumingContainer byteArrayConsumingContainer = DataConsumers.newByteArrayConsumingContainer();
    readFile(file, bufferSize, byteArrayConsumingContainer);
    return byteArrayConsumingContainer.toByteArray();
  }

  public static void readFile(final File file, final int bufferSize, final ByteArrayConsumer byteArrayConsumer)
  {
    assertBufferSize(bufferSize);

    FileInputStream fileInputStream = null;
    FileChannel fileChannel = null;

    try
    {
      fileInputStream = new FileInputStream(file);
      fileChannel = fileInputStream.getChannel();
      readFile0(fileChannel, bufferSize, byteArrayConsumer);
    }
    catch (final FileNotFoundException e)
    {
      throw new RuntimeFileNotFoundException(e);
    }
    catch (final IOException e)
    {
      throw new RuntimeIoException(e);
    }
    finally
    {
      closeQuietly(fileChannel);
      closeQuietly(fileInputStream);
    }
  }

  private static void readFile0(final FileChannel fileChannel, final int bufferSize,
      final ByteArrayConsumer byteArrayConsumer) throws IOException
  {
    final byte[] buffer = new byte[bufferSize];
    final ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
    int bytesRead = fileChannel.read(byteBuffer);
    while (-1 != bytesRead)
    {
      byteArrayConsumer.consume(buffer, 0, bytesRead);
      byteBuffer.clear();
      bytesRead = fileChannel.read(byteBuffer);
    }

  }

  public static void readFile(final File file, final int bufferSize, final Charset charset,
      final CharArrayConsumer charArrayConsumer)
  {
    assertBufferSize(bufferSize);

    FileInputStream fileInputStream = null;
    FileChannel fileChannel = null;
    Reader reader = null;

    try
    {
      fileInputStream = new FileInputStream(file);
      fileChannel = fileInputStream.getChannel();
      final CharsetDecoder charsetDecoder = charset.newDecoder();
      reader = Channels.newReader(fileChannel, charsetDecoder, bufferSize);
      readFile0(reader, bufferSize, charArrayConsumer);
    }
    catch (final FileNotFoundException e)
    {
      throw new RuntimeFileNotFoundException(e);
    }
    catch (final IOException e)
    {
      throw new RuntimeIoException(e);
    }
    finally
    {
      closeQuietly(reader);
      closeQuietly(fileChannel);
      closeQuietly(fileInputStream);
    }
  }

  private static void readFile0(final Reader reader, final int bufferSize, final CharArrayConsumer charArrayConsumer)
      throws IOException
  {
    final char[] buffer = new char[bufferSize];
    final CharBuffer charBuffer = CharBuffer.wrap(buffer);
    int charsRead = reader.read(charBuffer);
    while (-1 != charsRead)
    {
      charArrayConsumer.consume(buffer, 0, charsRead);
      charBuffer.clear();
      charsRead = reader.read(charBuffer);
    }

  }

  public static void writeOutputStream(final OutputStream outputStream, final int bufferSize,
      final ByteArrayProducer byteArrayProducer)
  {
    assertBufferSize(bufferSize);

    WritableByteChannel writableByteChannel = null;

    try
    {
      writableByteChannel = Channels.newChannel(outputStream);
      writeWritableByteChannel(writableByteChannel, bufferSize, byteArrayProducer);
    }
    catch (final FileNotFoundException e)
    {
      throw new RuntimeFileNotFoundException(e);
    }
    catch (final IOException e)
    {
      throw new RuntimeIoException(e);
    }
    finally
    {
      closeQuietly(writableByteChannel);
      closeQuietly(outputStream);
    }
  }

  private static void writeWritableByteChannel(final WritableByteChannel writableByteChannel, final int bufferSize,
      final ByteArrayProducer byteArrayProducer) throws IOException
  {
    final int bytesLength = byteArrayProducer.length();
    final int actualBufferSize = bytesLength < bufferSize ? bytesLength : bufferSize;
    final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(actualBufferSize);
    final byte[] bytes = new byte[actualBufferSize];

    int count = 0;
    count = byteArrayProducer.produce(bytes);
    while (-1 < count)
    {
      byteBuffer.put(bytes, 0, count);

      byteBuffer.flip();
      while (byteBuffer.hasRemaining())
        writableByteChannel.write(byteBuffer);

      byteBuffer.clear();

      count = byteArrayProducer.produce(bytes);
    }
  }

  public static void writeFile(final File file, final int bufferSize, final ByteArrayProducer byteArrayProducer)
  {
    assertBufferSize(bufferSize);

    FileOutputStream fileOutputStream = null;
    FileChannel fileChannel = null;

    try
    {
      fileOutputStream = new FileOutputStream(file);
      fileChannel = fileOutputStream.getChannel();
      writeFileChannel(fileChannel, bufferSize, byteArrayProducer);
    }
    catch (final FileNotFoundException e)
    {
      throw new RuntimeFileNotFoundException(e);
    }
    catch (final IOException e)
    {
      throw new RuntimeIoException(e);
    }
    finally
    {
      closeQuietly(fileChannel);
      closeQuietly(fileOutputStream);
    }
  }

  private static void writeFileChannel(final FileChannel fileChannel, final int bufferSize,
      final ByteArrayProducer byteArrayProducer) throws IOException
  {
    final int bytesLength = byteArrayProducer.length();
    final int actualBufferSize = bytesLength < bufferSize ? bytesLength : bufferSize;
    final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(actualBufferSize);

    final byte[] bytes = new byte[actualBufferSize];
    int count = 0;
    count = byteArrayProducer.produce(bytes);
    while (-1 < count)
    {
      byteBuffer.put(bytes, 0, count);

      byteBuffer.flip();
      while (byteBuffer.hasRemaining())
        fileChannel.write(byteBuffer);

      byteBuffer.clear();

      count = byteArrayProducer.produce(bytes);
    }
  }

  public static void copy(final InputStream inputStream, final OutputStream outputStream)
  {
    copy(IoCommonConstants.BUFFER_SIZE_512Ki, inputStream, outputStream);
  }

  public static void copy(final int bufferSize, final InputStream inputStream, final OutputStream outputStream)
  {
    assertBufferSize(bufferSize);

    ReadableByteChannel readableByteChannel = null;
    WritableByteChannel writableByteChannel = null;
    try
    {
      readableByteChannel = Channels.newChannel(inputStream);
      writableByteChannel = Channels.newChannel(outputStream);

      copyChannel(bufferSize, readableByteChannel, writableByteChannel);
    }
    catch (final FileNotFoundException e)
    {
      throw new RuntimeFileNotFoundException(e);
    }
    catch (final IOException e)
    {
      throw new RuntimeIoException(e);
    }
    finally
    {
      closeQuietly(writableByteChannel);
      closeQuietly(readableByteChannel);
      closeQuietly(outputStream);
      closeQuietly(inputStream);
    }
  }

  private static void copyChannel(final int bufferSize, final ReadableByteChannel readableByteChannel,
      final WritableByteChannel writableByteChannel) throws IOException
  {
    final ByteBuffer buffer = ByteBuffer.allocate(bufferSize);

    int read = readableByteChannel.read(buffer);

    while (-1 < read)
    {
      buffer.flip();
      writableByteChannel.write(buffer);

      while (buffer.hasRemaining())
        writableByteChannel.write(buffer);

      buffer.clear();
      read = readableByteChannel.read(buffer);
    }
  }

  public static void copyFile(final File sourceFile, final File targetFile)
  {
    FileInputStream sourceFileInputStream = null;
    FileOutputStream targetFileOutputStream = null;
    FileChannel sourceFileChannel = null;
    FileChannel targetFileChannel = null;
    try
    {
      sourceFileInputStream = new FileInputStream(sourceFile);
      targetFileOutputStream = new FileOutputStream(targetFile);
      sourceFileChannel = sourceFileInputStream.getChannel();
      targetFileChannel = targetFileOutputStream.getChannel();

      copyFileChannel(sourceFileChannel, targetFileChannel);
    }
    catch (final FileNotFoundException e)
    {
      throw new RuntimeFileNotFoundException(e);
    }
    catch (final IOException e)
    {
      throw new RuntimeIoException(e);
    }
    finally
    {
      closeQuietly(targetFileChannel);
      closeQuietly(sourceFileChannel);
      closeQuietly(targetFileOutputStream);
      closeQuietly(sourceFileInputStream);
    }
  }

  private static void copyFileChannel(final FileChannel sourceFileChannel, final FileChannel targetFileChannel)
      throws IOException
  {
    final long size = sourceFileChannel.size();
    long read = 0;
    read += targetFileChannel.transferFrom(sourceFileChannel, read, size - read);
    while (size > read)
    {
      read += targetFileChannel.transferFrom(sourceFileChannel, read, size - read);
    }
  }
}
