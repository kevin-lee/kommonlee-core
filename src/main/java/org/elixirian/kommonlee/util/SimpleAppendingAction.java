/**
 * 
 */
package org.elixirian.kommonlee.util;

import java.io.IOException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

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
 * @version 0.0.1 (2010-07-06)
 */
public abstract class SimpleAppendingAction implements AppendingAction
{
  static final AppendingAction APPENDING_ACTION_WITHOUT_SEPARATOR = new AppendingActionWithoutSeparator();

  @NonNull
  @Override
  public abstract <A extends Appendable, T> A append(A appendable, T t) throws IOException;

  public static final class AppendingActionWithoutSeparator extends SimpleAppendingAction
  {
    private AppendingActionWithoutSeparator()
    {
    }

    @NonNull
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

    private AppendingActionWithSeparator(@NonNull final String separator)
    {
      this.separator = separator;
    }

    @NonNull
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

  public static AppendingAction with(@Nullable final String separator)
  {
    /* @formatter:off */
    return null == separator || separator.isEmpty() ?
              APPENDING_ACTION_WITHOUT_SEPARATOR :
              new AppendingActionWithSeparator(separator);
    /* @formatter:off */
  }
}
