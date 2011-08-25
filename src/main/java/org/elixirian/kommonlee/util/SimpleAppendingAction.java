/**
 * 
 */
package org.elixirian.kommonlee.util;

import java.io.IOException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * <pre>
 *     ____________    ___________  ____   _______ _________ _______ _______________  ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /     \/   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /          /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
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
