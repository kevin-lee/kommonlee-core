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
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-10-28)
 */
public interface CharArrayProducer
{
  /**
   * It produces chars and store into the given char array. How the chars are produced is all up to the implementation
   * of this interface.
   * 
   * @param chars
   *          the given char array to store the chars from this {@link CharArrayProducer}.
   * @return the total number of chars written into the given char array.
   * @throws IOException
   */
  int produce(char[] chars) throws IOException;

  /**
   * Returns the total number of chars this {@link CharArrayProducer} has.
   * 
   * @return the total number of chars this {@link CharArrayProducer} has.
   */
  int length();
}
