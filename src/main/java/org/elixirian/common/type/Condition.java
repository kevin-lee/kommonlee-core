/**
 * 
 */
package org.elixirian.common.type;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-13)
 */
public interface Condition<T>
{
	boolean isApplicable(T input);
}
