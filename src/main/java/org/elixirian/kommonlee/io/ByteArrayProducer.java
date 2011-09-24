/**
 * 
 */
package org.elixirian.kommonlee.io;

import java.io.IOException;

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
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 * 
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
   *          the given byte array to store the bytes from this {@link ByteArrayProducer}.
   * @return the total number of bytes read into the given byte array.
   */
  int produce(byte[] bytes) throws IOException;

  /**
   * Returns the total number of bytes this {@link ByteArrayProducer} has.
   * 
   * @return the total number of bytes this {@link ByteArrayProducer} has.
   */
  int length();
}
