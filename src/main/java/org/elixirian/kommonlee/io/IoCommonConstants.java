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

import java.nio.charset.Charset;

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
 * @version 0.0.1 (2010-07-16)
 * @version 0.0.2 (2010-11-03) moved from the elixirian-common-filemanager package.
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
	 * <pre>
	 * The same as Charset.forName(&quot;UTF-8&quot;)
	 * </pre>
	 *
	 * UTF-8 Eight-bit UCS Transformation Format
	 */
	public static final Charset UTF_8 = Charset.forName("UTF-8");

	/**
	 * <pre>
	 * Charset.forName(&quot;US-ASCII&quot;)
	 * </pre>
	 *
	 * Seven-bit ASCII, a.k.a. ISO646-US, a.k.a. the Basic Latin block of the Unicode character set
	 */
	public static final Charset US_ASCII = Charset.forName("US-ASCII");

	/**
	 * <pre>
	 * Charset.forName(&quot;ISO-8859-1&quot;)
	 * </pre>
	 *
	 * ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1
	 */
	public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

	/**
	 * <pre>
	 * Charset.forName(&quot;UTF-16BE&quot;)
	 * </pre>
	 *
	 * Sixteen-bit UCS Transformation Format, big-endian byte order
	 */
	public static final Charset UTF_16BE = Charset.forName("UTF-16BE");

	/**
	 * <pre>
	 * Charset.forName(&quot;UTF-16LE&quot;)
	 * </pre>
	 *
	 * Sixteen-bit UCS Transformation Format, little-endian byte order
	 */
	public static final Charset UTF_16LE = Charset.forName("UTF-16LE");

	/**
	 * <pre>
	 * Charset.forName(&quot;UTF-16&quot;)
	 * </pre>
	 *
	 * Sixteen-bit UCS Transformation Format, byte order identified by an optional byte-order mark
	 */
	public static final Charset UTF_16 = Charset.forName("UTF-16");

	private IoCommonConstants() throws IllegalAccessException
	{
		throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
	}
}
