/**
 * 
 */
package org.elixirian.common.io;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-13)
 */
public interface ByteArrayConsumer
{
	/**
	 * It consumes the bytes in the given byte array. How the bytes are consumed is all up to the implementation of this interface.
	 * 
	 * @param bytes
	 *            an array of bytes to be consumed.
	 * @param offset
	 *            the index of the first byte to be consumed.
	 * @param count
	 *            the number of bytes to be consumed
	 */
	void consume(byte[] bytes, int offset, int count);
}
