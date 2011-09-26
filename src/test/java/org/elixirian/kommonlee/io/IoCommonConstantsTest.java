/**
 * 
 */
package org.elixirian.kommonlee.io;

import static org.elixirian.kommonlee.test.CommonTestHelper.*;

import org.elixirian.kommonlee.test.CommonTestHelper.Accessibility;
import org.junit.Test;

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
 * @version 0.0.1 (2010-07-16)
 */
public class IoCommonConstantsTest
{
  /**
   * Test method for {@link java.lang.Object#Object()}.
   * 
   * @throws Exception
   */
  @Test(expected = IllegalAccessException.class)
  public final void testIoCommonConstants() throws Exception
  {
    testNotAccessibleConstructor(IoCommonConstants.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
  }

}
