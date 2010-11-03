/**
 * 
 */
package com.elixirian.common.io;

import java.nio.charset.Charset;

import com.elixirian.common.util.CommonConstants;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-16)
 */
public final class IoCommonConstants
{
	/**
	 * 8 Ki (kibi): 8 * 1024 == 8192;
	 */
	public static final int BUFFER_SIZE_8Ki = 8192;

	/**
	 * 16 Ki (kibi): 16 * 1024 == 16384;
	 */
	public static final int BUFFER_SIZE_16Ki = 16384;

	/**
	 * 32 Ki (kibi): 32 * 1024 == 32768;
	 */
	public static final int BUFFER_SIZE_32Ki = 32768;

	/**
	 * 64 Ki (kibi): 64 * 1024 == 65536;
	 */
	public static final int BUFFER_SIZE_64Ki = 65536;

	/**
	 * 128 Ki (kibi): 128 * 1024 == 131072
	 */
	public static final int BUFFER_SIZE_128Ki = 131072;

	/**
	 * 256 Ki (kibi): 256 * 1024 == 262144
	 */
	public static final int BUFFER_SIZE_256Ki = 262144;

	/**
	 * 512 Ki (kibi): 512 * 1024 == 524288
	 */
	public static final int BUFFER_SIZE_512Ki = 524288;

	/**
	 * 1 Mi (mebi): 1024 * 1024 == 1048576
	 */
	public static final int BUFFER_SIZE_1Mi = 1048576;

	/**
	 * The same as Charset.forName("UTF-8")
	 */
	public static final Charset UTF_8 = Charset.forName("UTF-8");

	private IoCommonConstants()
	{
		throw new IllegalStateException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
	}
}
