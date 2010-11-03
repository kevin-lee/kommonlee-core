/**
 * 
 */
package com.elixirian.common.io;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-10-28)
 */
public interface ByteArrayProducer
{
	/**
	 * It produces bytes and store into the given byte array. How the bytes are produced is all up to the implementation
	 * of this interface.
	 * 
	 * @param bytes
	 *            the given byte array to store the bytes from this {@link ByteArrayProducer}.
	 * @return the total number of bytes read into the given byte array.
	 */
	int produce(byte[] bytes);

	/**
	 * Returns the total number of bytes this {@link ByteArrayProducer} has.
	 * 
	 * @return the total number of bytes this {@link ByteArrayProducer} has.
	 */
	int length();
}
