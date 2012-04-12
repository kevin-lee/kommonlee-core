/**
 * 
 */
package org.elixirian.kommonlee.util;

import java.io.IOException;

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
 * @version 0.0.1 (2010-07-06)
 */
public abstract class SimpleAppendingAction implements AppendingAction
{
  static final AppendingAction APPENDING_ACTION_WITHOUT_SEPARATOR = new AppendingActionWithoutSeparator();

  @Override
  public abstract <A extends Appendable, T> A append(A appendable, T t) throws IOException;

  public static final class AppendingActionWithoutSeparator extends SimpleAppendingAction
  {
    private AppendingActionWithoutSeparator()
    {
    }

    @Override
    public <A extends Appendable, T> A append(final A appendable, final T t) throws IOException
    {
      appendable.append(String.valueOf(t));
      return appendable;
    }
  }

  public static final class AppendingActionWithSeparator extends SimpleAppendingAction
  {
    private final String separator;

    private AppendingActionWithSeparator(final String separator)
    {
      this.separator = separator;
    }

    @Override
    public <A extends Appendable, T> A append(final A appendable, final T t) throws IOException
    {
      appendable.append(separator);
      appendable.append(String.valueOf(t));
      return appendable;
    }
  }

  public static AppendingAction withoutSeparator()
  {
    return APPENDING_ACTION_WITHOUT_SEPARATOR;
  }

  public static AppendingAction with(final String separator)
  {
    /* @formatter:off */
    return null == separator || separator.isEmpty() ?
              APPENDING_ACTION_WITHOUT_SEPARATOR :
              new AppendingActionWithSeparator(separator);
    /* @formatter:off */
  }
}
