/**
 * 
 */
package org.elixirian.common.type;

/**
 * This interface is to create a Pair object which stores two objects in order to pass as a parameter or to return as a
 * result or to store in the data structure or just to have two different or the same types in one object for any
 * purposes.
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-09-14)
 */
public interface Pair<L, R>
{
	/**
	 * Returns the first object (L) in this {@link Pair}.
	 * 
	 * @return the first object (L) in this {@link Pair}.
	 */
	L getLeft();

	/**
	 * Returns the second object (R) in this {@link Pair}.
	 * 
	 * @return the second object (R) in this {@link Pair}.
	 */
	R getRight();
}