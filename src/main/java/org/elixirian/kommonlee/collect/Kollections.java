/**
 * 
 */
package org.elixirian.kommonlee.collect;

import static org.elixirian.kommonlee.util.Objects.*;

import java.util.Iterator;

import org.elixirian.kommonlee.type.Function1;
import org.elixirian.kommonlee.util.CommonConstants;
import org.elixirian.kommonlee.util.IterableToAppendableGlue;
import org.elixirian.kommonlee.util.Objects;

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
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2011-10-14)
 */
public final class Kollections
{
  private Kollections() throws InstantiationException
  {
    throw new InstantiationException(getClass() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  public static int hashIterator(final Iterator<?> iterator)
  {
    if (null == iterator)
      return 0;

    int result = Objects.HASH_SEED;
    while (iterator.hasNext())
    {
      final Object element = iterator.next();
      result = hash(result, element);
    }
    return result;
  }

  public static boolean equalReadableLists(final ReadableList<?> first, final ReadableList<?> second)
  {
    if (first.length() != second.length())
    {
      return false;
    }
    final int length = first.length();
    for (int i = 0; i < length; i++)
    {
      if (notEqual(first.get(i), second.get(i)))
      {
        return false;
      }
    }
    return true;
  }

  public static boolean equalElements(final Iterator<?> first, final Iterator<?> second)
  {
    while (first.hasNext() && second.hasNext())
    {
      if (notEqual(first.next(), second.next()))
      {
        return false;
      }
    }
    return !(first.hasNext() || second.hasNext());
  }

  private static final IterableToAppendableGlue ITERABLE_TO_APPENDABLE_GLUE =
    IterableToAppendableGlue.withSeparator(", ");

  public static String toStringOf(final Kollection<?> kollection)
  {
    final Kollection<?> functionAppliedKollection = kollection.map(new Function1<Object, Object>() {
      @Override
      public Object apply(final Object input)
      {
        return kollection == input ? "(this Kollection)" : input;
      }
    });

    final StringBuilder stringBuilder = new StringBuilder("[");
    return ITERABLE_TO_APPENDABLE_GLUE.glue(stringBuilder, functionAppliedKollection)
        .append("]")
        .toString();
  }
}
