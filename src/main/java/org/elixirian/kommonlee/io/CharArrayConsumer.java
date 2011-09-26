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
 *     ___  _____
 *    /   \/    /_________  ___ ____ __ ______
 *   /        / /  ___ \  \/  //___// //     /
 *  /        \ /  _____/\    //   //   __   /
 * /____/\____\\_____/   \__//___//___/ /__/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-13)
 */
public interface CharArrayConsumer
{
  /**
   * It consumes the chars in the given byte array. How the chars are consumed is all up to the implementation of this
   * interface.
   * 
   * @param chars
   *          an array of char to be consumed.
   * @param offset
   *          the index of the first char to be consumed.
   * @param count
   *          the number of chars to be consumed
   * @throws IOException
   */
  void consume(char[] chars, int offset, int count) throws IOException;
}
