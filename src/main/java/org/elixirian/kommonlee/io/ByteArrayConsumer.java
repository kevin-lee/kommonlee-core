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
 * @version 0.0.1 (2010-07-13)
 */
public interface ByteArrayConsumer
{
  /**
   * It consumes the bytes in the given byte array. How the bytes are consumed is all up to the implementation of this
   * interface.
   * 
   * @param bytes
   *          an array of bytes to be consumed.
   * @param offset
   *          the index of the first byte to be consumed.
   * @param count
   *          the number of bytes to be consumed
   */
  void consume(byte[] bytes, int offset, int count) throws IOException;
}
