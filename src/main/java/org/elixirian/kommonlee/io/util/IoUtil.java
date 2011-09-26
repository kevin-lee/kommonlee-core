/**
 * 
 */
package org.elixirian.kommonlee.io.util;

import static org.elixirian.kommonlee.util.MessageFormatter.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.Charset;

import org.elixirian.kommonlee.io.ByteArrayConsumer;
import org.elixirian.kommonlee.io.ByteArrayProducer;
import org.elixirian.kommonlee.io.CharArrayConsumer;
import org.elixirian.kommonlee.io.CharArrayProducer;
import org.elixirian.kommonlee.io.StringConsumer;
import org.elixirian.kommonlee.io.exception.RuntimeFileNotFoundException;
import org.elixirian.kommonlee.io.exception.RuntimeIoException;
import org.elixirian.kommonlee.util.CommonConstants;
import org.elixirian.kommonlee.validation.Assertions;

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
 *     ___  _____
 *    /   \/    /_________  ___ ____ __ ______
 *   /        / /  ___ \  \/  //___// //     /
 *  /        \ /  _____/\    //   //   __   /
 * /____/\____\\_____/   \__//___//___/ /__/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-04-02)
 * @version 0.0.2 (2010-11-03) moved from the elixirian-common-filemanager package.
 */
public final class IoUtil
{
  private IoUtil()
  {
    throw new AssertionError(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
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
      System.out.println(format("log from %s (%s.java:%s)\nClosing [%s] has failed.\n", IoUtil.class,
          IoUtil.class.getSimpleName(), Integer.valueOf(Thread.currentThread()
              .getStackTrace()[1].getLineNumber()), closeable));
      e.printStackTrace();
    }
  }

  public static void assertBufferSize(final int bufferSize)
  {
    Assertions.assertTrue(0 < bufferSize, "The buffer size must be greater than 0. [given size: %s]",
        String.valueOf(bufferSize));
  }

  private static Charset assertCharsetNotNull(final Charset charset)
  {
    return Assertions.assertNotNull(charset, "Charset cannot be null.");
  }

  public static void readInputStream(final InputStream inputStream, final int bufferSize,
      final ByteArrayConsumer byteArrayConsumer)
  {
    assertBufferSize(bufferSize);
    BufferedInputStream bufferedInputStream = null;

    try
    {
      bufferedInputStream = new BufferedInputStream(inputStream);
      readAllBytes(bufferedInputStream, bufferSize, byteArrayConsumer);
    }
    catch (final IOException e)
    {
      /* @formatter:off */
      throw new RuntimeIoException(
          format("InputStream inputStream: %s\n" +
                 "int bufferSize: %s\n" +
                 "ByteArrayConsumer byteArrayConsumer: %s",
                 inputStream,
                 String.valueOf(bufferSize),
                 byteArrayConsumer), e);
      /* @formatter:on */
    }
    finally
    {
      closeQuietly(bufferedInputStream);
      closeQuietly(inputStream);
    }
  }

  private static void readAllBytes(final InputStream inputStream, final int bufferSize,
      final ByteArrayConsumer byteArrayConsumer) throws IOException
  {
    final byte[] buffer = new byte[bufferSize];
    int bytesRead = inputStream.read(buffer);

    while (-1 < bytesRead)
    {
      byteArrayConsumer.consume(buffer, 0, bytesRead);
      bytesRead = inputStream.read(buffer);
    }
  }

  public static void readInputStream(final InputStream inputStream, final int bufferSize, final Charset charset,
      final CharArrayConsumer charArrayConsumer)
  {
    assertBufferSize(bufferSize);
    assertCharsetNotNull(charset);
    InputStreamReader inputStreamReader = null;

    try
    {
      inputStreamReader = new InputStreamReader(inputStream, charset);
      readAllChars(inputStreamReader, bufferSize, charArrayConsumer);
    }
    catch (final IOException e)
    {
      /* @formatter:off */
      throw new RuntimeIoException(
          format("InputStream inputStream: %s\n" +
                 "int bufferSize: %s\n" +
                 "Charset charset: %s\n" +
                 "CharArrayConsumer charArrayConsumer: %s\n" +
                 "InputStreamReader inputStreamReader: %s",
                 inputStream,
                 String.valueOf(bufferSize),
                 charset,
                 charArrayConsumer,
                 inputStreamReader), e);
      /* @formatter:on */
    }
    finally
    {
      closeQuietly(inputStreamReader);
      closeQuietly(inputStream);
    }
  }

  private static void readAllChars(final Reader reader, final int bufferSize, final CharArrayConsumer charArrayConsumer)
      throws IOException
  {
    final char[] buffer = new char[bufferSize];
    int bytesRead = reader.read(buffer);

    while (-1 < bytesRead)
    {
      charArrayConsumer.consume(buffer, 0, bytesRead);
      bytesRead = reader.read(buffer);
    }
  }

  public static void readInputStream(final InputStream inputStream, final int bufferSize, final Charset charset,
      final StringConsumer stringConsumer)
  {
    assertBufferSize(bufferSize);
    assertCharsetNotNull(charset);

    InputStreamReader inputStreamReader = null;
    BufferedReader bufferedReader = null;

    try
    {
      inputStreamReader = new InputStreamReader(inputStream, charset);
      bufferedReader = new BufferedReader(inputStreamReader, bufferSize);
      readString(bufferedReader, stringConsumer);
    }
    catch (final IOException e)
    {
      /* @formatter:off */
      throw new RuntimeIoException(
          format("InputStream inputStream: %s\n" +
                 "int bufferSize: %s\n" +
                 "Charset charset: %s\n" +
                 "StringConsumer stringConsumer: %s\n" +
                 "InputStreamReader inputStreamReader: %s\n" +
                 "BufferedReader bufferedReader: %s",
                 inputStream,
                 String.valueOf(bufferSize),
                 charset,
                 stringConsumer,
                 inputStreamReader,
                 bufferedReader), e);
      /* @formatter:on */
    }
    finally
    {
      closeQuietly(bufferedReader);
      closeQuietly(inputStreamReader);
      closeQuietly(inputStream);
    }
  }

  private static void readString(final BufferedReader bufferedReader, final StringConsumer stringConsumer)
      throws IOException
  {
    String line = bufferedReader.readLine();
    while (null != line)
    {
      stringConsumer.consume(line);
      line = bufferedReader.readLine();
    }
  }

  public static void readFile(final File file, final int bufferSize, final ByteArrayConsumer byteArrayConsumer)
  {
    assertBufferSize(bufferSize);
    InputStream inputStream = null;

    try
    {
      inputStream = new FileInputStream(file);
      readAllBytes(inputStream, bufferSize, byteArrayConsumer);
    }
    catch (final FileNotFoundException e)
    {
      /* @formatter:off */
      throw new RuntimeFileNotFoundException(
          format("File file: %s\n" +
                 "int bufferSize: %s\n" +
                 "ByteArrayConsumer byteArrayConsumer: %s\n" +
                 "local InputStream inputStream: %s",
                 file,
                 String.valueOf(bufferSize),
                 byteArrayConsumer,
                 inputStream), e);
      /* @formatter:on */
    }
    catch (final IOException e)
    {
      /* @formatter:off */
      throw new RuntimeIoException(
          format("File file: %s\n" +
                 "int bufferSize: %s\n" +
                 "ByteArrayConsumer byteArrayConsumer: %s\n" +
                 "local InputStream inputStream: %s",
                 file,
                 String.valueOf(bufferSize),
                 byteArrayConsumer,
                 inputStream), e);
      /* @formatter:on */
    }
    finally
    {
      closeQuietly(inputStream);
    }
  }

  public static void writeOutputStream(final OutputStream outputStream, final int bufferSize,
      final ByteArrayProducer byteArrayProducer)
  {
    assertBufferSize(bufferSize);

    try
    {
      final byte[] buffer = new byte[bufferSize];
      int bytesRead = byteArrayProducer.produce(buffer);

      while (-1 < bytesRead)
      {
        outputStream.write(buffer, 0, bytesRead);
        bytesRead = byteArrayProducer.produce(buffer);
      }
    }
    catch (final IOException e)
    {
      /* @formatter:off */
      throw new RuntimeIoException(
          format("OutputStream outputStream: %s\n" +
                 "int bufferSize: %s\n" +
                 "ByteArrayProducer byteArrayProducer: %s\n" +
                 outputStream,
                 String.valueOf(bufferSize),
                 byteArrayProducer), e);
      /* @formatter:on */
    }
    finally
    {
      closeQuietly(outputStream);
    }
  }

  public static void writeOutputStream(final OutputStream outputStream, final int bufferSize, final Charset charset,
      final CharArrayProducer charArrayProducer)
  {
    assertBufferSize(bufferSize);
    assertCharsetNotNull(charset);

    OutputStreamWriter outputStreamWriter = null;

    try
    {
      outputStreamWriter = new OutputStreamWriter(outputStream, charset);

      final char[] buffer = new char[bufferSize];
      int charsRead = charArrayProducer.produce(buffer);

      while (-1 < charsRead)
      {
        outputStreamWriter.write(buffer, 0, charsRead);
        charsRead = charArrayProducer.produce(buffer);
      }
    }
    catch (final IOException e)
    {
      /* @formatter:off */
      throw new RuntimeIoException(
          format("OutputStream outputStream: %s\n" +
              "int bufferSize: %s\n" +
              "Charset charset: %s\n" +
              "CharArrayProducer charArrayProducer: %s\n" +
              "local OutputStreamWriter outputStreamWriter: %s",
              outputStream,
              String.valueOf(bufferSize),
              charset,
              charArrayProducer,
              outputStreamWriter), e);
      /* @formatter:on */
    }
    finally
    {
      closeQuietly(outputStreamWriter);
      closeQuietly(outputStream);
    }
  }

  public static void writeFile(final File file, final int bufferSize, final ByteArrayProducer byteArrayProducer)
  {
    assertBufferSize(bufferSize);

    OutputStream outputStream = null;

    try
    {
      outputStream = new FileOutputStream(file);

      final byte[] buffer = new byte[bufferSize];
      int bytesRead = byteArrayProducer.produce(buffer);

      while (-1 < bytesRead)
      {
        outputStream.write(buffer, 0, bytesRead);
        bytesRead = byteArrayProducer.produce(buffer);
      }
    }
    catch (final FileNotFoundException e)
    {
      /* @formatter:off */
      throw new RuntimeFileNotFoundException(
          format("File file: %s\n" +
                 "int bufferSize: %s\n" +
                 "ByteArrayProducer byteArrayProducer: %s\n" +
                 "local OutputStream outputStream: %s",
                 file,
                 String.valueOf(bufferSize),
                 byteArrayProducer,
                 outputStream), e);
      /* @formatter:on */
    }
    catch (final IOException e)
    {
      /* @formatter:off */
      throw new RuntimeIoException(
          format("File file: %s\n" +
                 "int bufferSize: %s\n" +
                 "ByteArrayProducer byteArrayProducer: %s\n" +
                 "local OutputStream outputStream: %s",
                 file,
                 String.valueOf(bufferSize),
                 byteArrayProducer,
                 outputStream), e);
      /* @formatter:on */
    }
    finally
    {
      closeQuietly(outputStream);
    }
  }

  public static void writeFile(final File file, final int bufferSize, final Charset charset,
      final CharArrayProducer charArrayProducer)
  {
    assertBufferSize(bufferSize);
    assertCharsetNotNull(charset);

    OutputStream outputStream = null;
    OutputStreamWriter outputStreamWriter = null;

    try
    {
      outputStream = new FileOutputStream(file);
      outputStreamWriter = new OutputStreamWriter(outputStream, charset);

      final char[] buffer = new char[bufferSize];
      int bytesRead = charArrayProducer.produce(buffer);

      while (-1 < bytesRead)
      {
        outputStreamWriter.write(buffer, 0, bytesRead);
        bytesRead = charArrayProducer.produce(buffer);
      }
    }
    catch (final FileNotFoundException e)
    {
      /* @formatter:off */
      throw new RuntimeFileNotFoundException(
          format("File file: %s\n" +
              "int bufferSize: %s\n" +
              "Charset charset: %s\n" +
              "CharArrayProducer charArrayProducer: %s\n" +
              "local OutputStream outputStream: %s\n" +
              "local OutputStreamWriter outputStreamWriter: %s",
              file,
              String.valueOf(bufferSize),
              charset,
              charArrayProducer,
              outputStream,
              outputStreamWriter), e);
      /* @formatter:on */
    }
    catch (final IOException e)
    {
      /* @formatter:off */
      throw new RuntimeIoException(
          format("File file: %s\n" +
              "int bufferSize: %s\n" +
              "Charset charset: %s\n" +
              "CharArrayProducer charArrayProducer: %s\n" +
              "local OutputStream outputStream: %s\n" +
              "local OutputStreamWriter outputStreamWriter: %s",
              file,
              String.valueOf(bufferSize),
              charset,
              charArrayProducer,
              outputStream,
              outputStreamWriter), e);
      /* @formatter:on */
    }
    finally
    {
      closeQuietly(outputStreamWriter);
      closeQuietly(outputStream);
    }
  }

  public static void copyFile(final File sourceFile, final File targetFile, final int bufferSize)
      throws RuntimeIoException
  {
    assertBufferSize(bufferSize);

    InputStream inputStream = null;
    OutputStream outputStream = null;

    try
    {
      inputStream = new FileInputStream(sourceFile);
      outputStream = new FileOutputStream(targetFile);

      final byte[] buffer = new byte[bufferSize];
      int bytesRead = inputStream.read(buffer);

      while (-1 != bytesRead)
      {
        outputStream.write(buffer, 0, bytesRead);
        bytesRead = inputStream.read(buffer);
      }
    }
    catch (final FileNotFoundException e)
    {
      /* @formatter:off */
      throw new RuntimeFileNotFoundException(
          format("File sourceFileL %s\n" +
                 "File targetFile: %s\n" +
                 "int bufferSize: %s\n" +
                 "local InputStream inputStream: %s\n" +
                 "local OutputStream outputStream: %s",
                 sourceFile,
                 targetFile,
                 String.valueOf(bufferSize),
                 inputStream,
                 outputStream), e);
      /* @formatter:on */
    }
    catch (final IOException e)
    {
      /* @formatter:off */
      throw new RuntimeIoException(
          format("File sourceFileL %s\n" +
          		   "File targetFile: %s\n" +
                 "int bufferSize: %s\n" +
                 "local InputStream inputStream: %s\n" +
                 "local OutputStream outputStream: %s",
                 sourceFile,
                 targetFile,
                 String.valueOf(bufferSize),
                 inputStream,
                 outputStream), e);
      /* @formatter:on */
    }
    finally
    {
      closeQuietly(outputStream);
      closeQuietly(inputStream);
    }
  }

}
