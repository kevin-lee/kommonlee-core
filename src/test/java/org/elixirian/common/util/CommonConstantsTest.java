/**
 * 
 */
package org.elixirian.common.util;

import static org.elixirian.common.test.CommonTestHelper.*;

import org.elixirian.common.util.CommonConstants;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-05)
 */
public class CommonConstantsTest
{

	@Test(expected = IllegalStateException.class)
	public void testCommonConstants() throws Exception
	{
		testNotAccessibleConstructor(CommonConstants.class, classArrayOf(), objectArrayOf());
	}
}
