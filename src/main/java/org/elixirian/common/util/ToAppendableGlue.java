/**
 * 
 */
package org.elixirian.common.util;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-06)
 */
public interface ToAppendableGlue<E>
{
	<A extends Appendable> A glue(final A appendable, final E e);
}
