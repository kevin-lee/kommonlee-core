/**
 * 
 */
package org.elixirian.kommonlee.util;

import static org.elixirian.kommonlee.test.CommonTestHelper.*;

import org.elixirian.kommonlee.test.CommonTestHelper.Accessibility;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-05)
 */
public class CommonConstantsTest
{

  @Test(expected = IllegalAccessException.class)
  public void testCommonConstants() throws Exception
  {
    testNotAccessibleConstructor(CommonConstants.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
  }
}
