/**
 * 
 */
package com.elixirian.common.io;

import static com.elixirian.common.test.CommonTestHelper.*;

import org.junit.Test;

/**
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
	@Test(expected = IllegalStateException.class)
	public final void testIoCommonConstants() throws Exception
	{
		testNotAccessibleConstructor(IoCommonConstants.class, classArrayOf(), objectArrayOf());
	}

}
