/**
 * 
 */
package org.elixirian.kommonlee.io;

import static org.elixirian.kommonlee.test.CommonTestHelper.*;

import org.elixirian.kommonlee.test.CommonTestHelper.Accessibility;
import org.junit.Test;

/**
 * <pre>
 *     ____________    ___________  ____   _______ _________ _______ _______________  ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /     \/   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /          /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
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
