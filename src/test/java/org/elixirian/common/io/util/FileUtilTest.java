/**
 * 
 */
package org.elixirian.common.io.util;

import static org.elixirian.common.test.CommonTestHelper.*;
import static org.elixirian.common.util.MessageFormatter.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elixirian.common.io.ByteArrayConsumer;
import org.elixirian.common.io.ByteArrayProducer;
import org.elixirian.common.io.exception.RuntimeIoException;
import org.elixirian.common.io.util.FileUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-04-02)
 */
public class FileUtilTest
{
	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private List<Byte> byteList;
	private StringBuilder stringBuilder;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		byteList = new ArrayList<Byte>();
		stringBuilder = new StringBuilder();
		readFile(getTestFile(), byteList, stringBuilder);

	}

	private void readFile(File file, List<Byte> byteList, StringBuilder stringBuilder)
	{
		FileInputStream fileInputStream = null;
		try
		{
			fileInputStream = new FileInputStream(file);
			final byte[] buffer = new byte[8192];
			int bytesRead = fileInputStream.read(buffer);
			while (-1 != bytesRead)
			{
				for (int i = 0; i < bytesRead; i++)
				{
					byteList.add(Byte.valueOf(buffer[i]));
					stringBuilder.append((char) buffer[i]);
				}
				bytesRead = fileInputStream.read(buffer);
			}
		}
		catch (FileNotFoundException e)
		{
			throw new AssertionError(e);
		}
		catch (IOException e)
		{
			throw new AssertionError(e);
		}
		finally
		{
			if (null != fileInputStream)
			{
				try
				{
					fileInputStream.close();
				}
				catch (IOException e)
				{
				}
			}
		}
	}

	private File getTestFile()
	{
		return new File(this.getClass()
				.getResource("/file4testing.txt")
				.getFile()
				.replace("%20", " "));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	@Test(expected = IllegalAccessException.class)
	public void testFileUtil() throws Exception
	{
		testNotAccessibleConstructor(FileUtil.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
	}

	@Test
	public void testCloseQuietly()
	{
		final boolean[] called = { false };
		Closeable closeable = new Closeable() {
			@Override
			public void close() throws IOException
			{
				called[0] = true;
			}
		};

		assertFalse(called[0]);
		FileUtil.closeQuietly(closeable);
		assertTrue(called[0]);

		called[0] = false;
		closeable = new Closeable() {
			@Override
			public void close() throws IOException
			{
				throw new IOException("for testing") {
					private static final long serialVersionUID = 1L;

					@Override
					public void printStackTrace()
					{
						System.out.println(getMessage());
						called[0] = true;
					}
				};
			}
		};

		assertFalse(called[0]);
		FileUtil.closeQuietly(closeable);
		assertTrue(called[0]);
	}

	@Test
	public void testAssertBufferSize()
	{
		for (int i = 1; i < 51; i++)
			FileUtil.assertBufferSize(i);
	}

	@Test
	public void testAssertBufferSizeWithNonPositiveInt()
	{
		for (int i = -50; i < 1; i++)
		{
			boolean exceptionThrown = false;
			try
			{
				FileUtil.assertBufferSize(i);
			}
			catch (IllegalArgumentException e)
			{
				exceptionThrown = true;
			}
			assertTrue(format("expected exception [%s] is not thrown.", IllegalArgumentException.class),
					exceptionThrown);
		}
	}

	private static class ByteArrayConsumer4Testing implements ByteArrayConsumer
	{
		private final List<Byte> byteList;
		private final StringBuilder stringBuilder;

		public ByteArrayConsumer4Testing(final List<Byte> byteList, final StringBuilder stringBuilder)
		{
			this.byteList = byteList;
			this.stringBuilder = stringBuilder;
		}

		@Override
		public void consume(final byte[] bytes, final int offset, final int count)
		{
			for (int i = offset, size = offset + count; i < size; i++)
			{
				byteList.add(Byte.valueOf(bytes[i]));
				stringBuilder.append((char) bytes[i]);
			}
		}

	}

	@Test
	public void testReadFile()
	{
		for (int bufferSize = 1; bufferSize < 128; bufferSize++)
		{
			final ByteArrayConsumer4Testing byteArrayConsumer =
				new ByteArrayConsumer4Testing(new ArrayList<Byte>(), new StringBuilder());

			/* test */
			FileUtil.readFile(new File(this.getClass()
					.getResource("/file4testing.txt")
					.getFile()
					.replace("%20", " ")), bufferSize, byteArrayConsumer);

			assertThat(byteArrayConsumer.byteList, is(equalTo(this.byteList)));
			assertThat(byteArrayConsumer.stringBuilder.toString(), is(equalTo(this.stringBuilder.toString())));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReadFileWith0SizeBuffer() throws Exception
	{
		ByteArrayConsumer byteArrayConsumer = null;

		try
		{
			byteArrayConsumer = mock(ByteArrayConsumer.class);
			doAnswer(new Answer<Void>() {
				@Override
				public Void answer(@SuppressWarnings("unused") InvocationOnMock invocation) throws Throwable
				{
					return null;
				}
			}).when(byteArrayConsumer)
					.consume(Mockito.any(byte[].class), Mockito.anyInt(), Mockito.anyInt());

			FileUtil.readFile(getTestFile(), 1, byteArrayConsumer);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No exception should be thrown here!");
		}

		/* test */
		FileUtil.readFile(getTestFile(), 0, byteArrayConsumer);
	}

	private static class ByteArrayProducer4Testing implements ByteArrayProducer
	{
		private final byte[] byteArray;
		private int position;
		private int left;

		public ByteArrayProducer4Testing(byte[] byteArray)
		{
			this.byteArray = byteArray;
			this.left = byteArray.length;
		}

		public void reset()
		{
			this.position = 0;
			this.left = byteArray.length;
		}

		@Override
		public int produce(byte[] bytes)
		{
			if (0 < left)
			{
				final int byteLength = bytes.length;
				final int count = byteLength < left ? byteLength : left;
				System.arraycopy(byteArray, position, bytes, 0, count);
				position += count;
				left -= count;
				return count;
			}
			return -1;
		}

		@Override
		public int length()
		{
			return byteArray.length;
		}
	}

	@Test
	public void testWriteFile()
	{
		final File file = new File(temporaryFolder.getRoot(), "file4testing2.txt");

		final String expected = this.stringBuilder.toString();
		final byte[] byteArray = new byte[expected.length()];
		final ByteArrayProducer4Testing byteArrayProducer = new ByteArrayProducer4Testing(byteArray);
		for (int i = 0, size = expected.length(); i < size; i++)
		{
			byteArray[i] = (byte) expected.charAt(i);
		}
		for (int bufferSize = 1; bufferSize < 128; bufferSize++)
		{
			/* test */
			FileUtil.writeFile(file, bufferSize, byteArrayProducer);

			final List<Byte> byteList = new ArrayList<Byte>();
			final StringBuilder stringBuilder = new StringBuilder();

			readFile(file, byteList, stringBuilder);
			assertThat(byteList, is(equalTo(this.byteList)));
			assertThat(stringBuilder.toString(), is(equalTo(this.stringBuilder.toString())));

			assertTrue(file.delete());
			byteArrayProducer.reset();
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWriteFileWith0SizeBuffer()
	{
		File file = null;
		byte[] byteArray = null;
		try
		{
			file = new File(temporaryFolder.getRoot(), "file4testing2.txt");

			final String expected = this.stringBuilder.toString();
			byteArray = new byte[expected.length()];
			for (int i = 0, size = expected.length(); i < size; i++)
			{
				byteArray[i] = (byte) expected.charAt(i);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("No exception should be thrown here!");
		}

		final ByteArrayProducer byteArrayProducer = new ByteArrayProducer4Testing(byteArray);
		/* test */
		FileUtil.writeFile(file, 0, byteArrayProducer);
	}

	/**
	 * Test method for {@link org.elixirian.common.io.util.FileUtil#copyFile(java.io.File, java.io.File, int)}.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testCopyFile() throws IOException
	{
		final int bufferSize = 4096;
		final File folder = temporaryFolder.newFolder("testFolder");

		final File sourceFile = getTestFile();

		final File targetFile = new File(folder, "copyOfTestFile");

		FileUtil.copyFile(sourceFile, targetFile, bufferSize);
		assertEquals(sourceFile.length(), targetFile.length());

		final List<Byte> expectedLineList = new ArrayList<Byte>();
		final StringBuilder expectedStringBuilder = new StringBuilder();
		readFile(sourceFile, expectedLineList, expectedStringBuilder);

		final List<Byte> resultList = new ArrayList<Byte>();
		final StringBuilder resultStringBuilder = new StringBuilder();
		readFile(targetFile, resultList, resultStringBuilder);

		assertThat(expectedLineList, is(equalTo(resultList)));
		assertThat(expectedStringBuilder.toString(), is(equalTo(resultStringBuilder.toString())));
	}

	/**
	 * Test method for {@link org.elixirian.common.io.util.FileUtil#copyFile(java.io.File, java.io.File, int)}.
	 * 
	 * @throws IOException
	 */
	@Test(expected = RuntimeIoException.class)
	public void testCopyFileWithNotExistingFile() throws IOException
	{
		FileUtil.copyFile(new File(temporaryFolder.newFolder("testFolder"), "someFileWhichDoesNotExist"), null, 1024);
	}
}
