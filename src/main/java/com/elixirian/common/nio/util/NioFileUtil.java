/**
 * 
 */
package com.elixirian.common.nio.util;

import static com.elixirian.common.io.IoCommonConstants.*;
import static com.elixirian.common.util.MessageFormatter.*;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.elixirian.common.io.ByteArrayConsumer;
import com.elixirian.common.io.ByteArrayProducer;
import com.elixirian.common.io.exception.ElixirianFileNotFoundException;
import com.elixirian.common.io.exception.ElixirianIoException;
import com.elixirian.common.util.CommonConstants;
import com.elixirian.common.validation.Assertions;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-13)
 * @version 0.0.2 (2010-11-03) moved from the elixirian-common-filemanager package.
 */
public final class NioFileUtil
{
	/**
	 * the value of {@link com.elixirian.common.io.IoCommonConstants#BUFFER_SIZE_128Ki}
	 */
	public static final int DEFAULT_BUFFER_SIZE = BUFFER_SIZE_128Ki;

	private NioFileUtil()
	{
		throw new IllegalStateException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
	}

	/**
	 * Calls close() method on any {@link Closeable} object without throwing {@link IOException}.
	 * 
	 * @param closeable
	 *            the given {@link Closeable} object the close() method of which is to be called by this method.
	 */
	public static void closeQuietly(Closeable closeable)
	{
		try
		{
			if (null != closeable)
				closeable.close();
		}
		catch (IOException e)
		{
			/* just ignore this exception. */
			// System.out.println(format("log from %s (%s.java:%s)\nClosing [%s] has failed.\nCaused at %s",
			// NioFileUtil.class, NioFileUtil.class.getSimpleName(), Integer.valueOf(Thread.currentThread()
			// .getStackTrace()[1].getLineNumber()), closeable, e.getStackTrace()[0]));
			System.out.println(format("log from %s (%s.java:%s)\nClosing [%s] has failed.\n", NioFileUtil.class,
					NioFileUtil.class.getSimpleName(), Integer.valueOf(Thread.currentThread()
							.getStackTrace()[1].getLineNumber()), closeable));
			e.printStackTrace();
		}
	}

	public static void assertBufferSize(final int bufferSize)
	{
		Assertions.assertTrue(0 < bufferSize, "The buffer size must be greater than 0. [given size: %s]",
				Integer.valueOf(bufferSize));
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
		catch (FileNotFoundException e)
		{
			throw new ElixirianFileNotFoundException(e);
		}
		catch (IOException e)
		{
			throw new ElixirianIoException(e);
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
		byte[] buffer = new byte[bufferSize];
		final ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
		int bytesRead = fileChannel.read(byteBuffer);
		while (-1 != bytesRead)
		{
			byteArrayConsumer.consume(buffer, 0, bytesRead);
			byteBuffer.clear();
			bytesRead = fileChannel.read(byteBuffer);
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
			writeFile0(fileChannel, bufferSize, byteArrayProducer);
		}
		catch (FileNotFoundException e)
		{
			throw new ElixirianFileNotFoundException(e);
		}
		catch (IOException e)
		{
			throw new ElixirianIoException(e);
		}
		finally
		{
			closeQuietly(fileChannel);
			closeQuietly(fileOutputStream);
		}
	}

	private static void writeFile0(final FileChannel fileChannel, final int bufferSize,
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

			copyFile0(sourceFileChannel, targetFileChannel);
		}
		catch (FileNotFoundException e)
		{
			throw new ElixirianFileNotFoundException(e);
		}
		catch (IOException e)
		{
			throw new ElixirianIoException(e);
		}
		finally
		{
			closeQuietly(targetFileChannel);
			closeQuietly(sourceFileChannel);
			closeQuietly(targetFileOutputStream);
			closeQuietly(sourceFileInputStream);
		}
	}

	private static void copyFile0(FileChannel sourceFileChannel, FileChannel targetFileChannel) throws IOException
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
