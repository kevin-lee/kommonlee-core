/**
 * 
 */
package org.elixirian.common.reflect;

import static org.elixirian.common.util.Objects.*;

import java.lang.reflect.Type;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-10-04)
 */
public abstract class TypeHolder<T>
{
	protected final Type type;

	protected TypeHolder()
	{
		this.type = Generics.extractFromParameterizedType(getClass().getGenericSuperclass(), 0);
	}

	public Type getType()
	{
		return type;
	}

	@Override
	public String toString()
	{
		return toStringBuilder(this).add("type", type)
				.toString();
	}
}
