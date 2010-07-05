/**
 * 
 */
package com.elixirian.common.util;

import static com.elixirian.common.test.CommonTestHelper.*;

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
