/**
 * 
 */
package org.elixirian.common.util;

import java.io.IOException;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-06)
 */
public abstract class SimpleAppendingAction implements AppendingAction
{
	static final AppendingAction APPENDING_ACTION_WITHOUT_SEPARATOR = new AppendingActionWithoutSeparator();

	/*
	 * (non-Javadoc)
	 * @see org.elixirian.common.util.AppendingAction#glue(java.lang.Appendable, java.lang.Object)
	 */
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
		return Strings.isEmpty(separator) ? APPENDING_ACTION_WITHOUT_SEPARATOR : new AppendingActionWithSeparator(
				separator);
	}
}
