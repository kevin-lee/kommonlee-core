/**
 *
 */
package org.elixirian.kommonlee.reflect;

import static org.elixirian.kommonlee.util.Objects.*;

import java.lang.reflect.Type;

/**
 * <pre>
 *     ___  _____                                              _____
 *    /   \/    / ______ __________________  ______ __ ______ /    /   ______  ______  
 *   /        / _/ __  // /  /   / /  /   /_/ __  // //     //    /   /  ___ \/  ___ \ 
 *  /        \ /  /_/ _/  _  _  /  _  _  //  /_/ _/   __   //    /___/  _____/  _____/
 * /____/\____\/_____//__//_//_/__//_//_/ /_____//___/ /__//________/\_____/ \_____/
 * </pre>
 *
 * <pre>
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-10-04)
 */
public abstract class TypeHolder<T>
{
  protected final Type type;

  protected TypeHolder()
  {
    /* @formatter:off */
    this.type = Generics.extractFromParameterizedType(getClass()
        .getGenericSuperclass(), 0);
    /* @formatter:on */
  }

  public Type getType()
  {
    return type;
  }

  @Override
  public String toString()
  {
    /* @formatter:off */
		return toStringBuilder(this)
		    .add("type", type)
				.toString();
		/* @formatter:on */
  }
}
