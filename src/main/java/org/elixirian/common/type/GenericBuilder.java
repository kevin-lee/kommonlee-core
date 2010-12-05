/**
 * 
 */
package org.elixirian.common.type;

/**
 * This is an interface to implement GenericBuilder which create an instance of the type generified in this interface.
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-01-07)
 */
public interface GenericBuilder<T>
{
	/**
	 * Create an instance of the type T.
	 * 
	 * @return an instance of the type T.
	 */
	T build();
}