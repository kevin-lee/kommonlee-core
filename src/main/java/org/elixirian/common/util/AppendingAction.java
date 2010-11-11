package org.elixirian.common.util;

import java.io.IOException;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-06)
 */
public interface AppendingAction
{
	<A extends Appendable, T> A append(A appendable, T t) throws IOException;
}