/**
 * 
 */
package org.elixirian.common.io.util;

import static org.elixirian.common.util.MessageFormatter.*;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.elixirian.common.io.ByteArrayConsumer;
import org.elixirian.common.io.ByteArrayProducer;
import org.elixirian.common.io.exception.RuntimeFileNotFoundException;
import org.elixirian.common.io.exception.RuntimeIoException;
import org.elixirian.common.validation.Assertions;

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
 * @version 0.0.1 (2010-04-02)
 * @version 0.0.2 (2010-11-03) moved from the elixirian-common-filemanager package.
 */
public final class FileUtil
{
	private FileUtil()
	{
	}

	/**
	 * Calls close() method on any {@link Closeable} object without throwing {@link IOException}.
	 * 
	 * @param closeable
	 *          the given {@link Closeable} object the close() method of which is to be called by this method.
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
			System.out.println(format("log from %s (%s.java:%s)\nClosing [%s] has failed.\n", FileUtil.class,
					FileUtil.class.getSimpleName(), Integer.valueOf(Thread.currentThread()
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

		InputStream inputStream = null;

		try
		{
			inputStream = new FileInputStream(file);

			final byte[] buffer = new byte[bufferSize];
			int bytesRead = inputStream.read(buffer);

			while (-1 < bytesRead)
			{
				byteArrayConsumer.consume(buffer, 0, bytesRead);
				bytesRead = inputStream.read(buffer);
			}
		}
		catch (FileNotFoundException e)
		{
			throw new RuntimeFileNotFoundException(e);
		}
		catch (IOException e)
		{
			throw new RuntimeIoException(e);
		}
		finally
		{
			closeQuietly(inputStream);
		}
	}

	public static void writeFile(final File file, final int bufferSize, final ByteArrayProducer byteArrayProducer)
	{
		assertBufferSize(bufferSize);

		InputStream inputStream = null;
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
		catch (FileNotFoundException e)
		{
			throw new RuntimeFileNotFoundException(e);
		}
		catch (IOException e)
		{
			throw new RuntimeIoException(e);
		}
		finally
		{
			closeQuietly(inputStream);
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
		catch (FileNotFoundException e)
		{
			throw new RuntimeFileNotFoundException(e);
		}
		catch (IOException e)
		{
			throw new RuntimeIoException(e);
		}
		finally
		{
			closeQuietly(inputStream);
			closeQuietly(outputStream);
		}
	}
}
