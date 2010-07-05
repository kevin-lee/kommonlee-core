package com.elixirian.common.util;

import static com.elixirian.common.util.Objects.*;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-05)
 */
public final class IterableToAppendableGlue implements ToAppendableGlue<Iterable<?>>
{
	private final AppendingAction appendingAction;

	private IterableToAppendableGlue(final String separator)
	{
		this.appendingAction = AbstractAppendingAction.newGlue(separator);
	}

	AppendingAction getGlue()
	{
		return appendingAction;
	}

	public static IterableToAppendableGlue newAppenderGlue(final String separator)
	{
		return new IterableToAppendableGlue(separator);
	}

	private <A extends Appendable, E> A glue0(final A appendable, final Iterable<E> iterable) throws IOException
	{
		final Iterator<E> iterator = iterable.iterator();
		if (iterator.hasNext())
		{
			AbstractAppendingAction.APPENDING_ACTION_WITHOUT_SEPARATOR.append(appendable, iterator.next());
			while (iterator.hasNext())
			{
				appendingAction.append(appendable, iterator.next());
			}
		}
		return appendable;
	}

	@Override
	public <A extends Appendable> A glue(A appendable, Iterable<?> iterable)
	{
		try
		{
			return glue0(nonNull(appendable),
			/* the comment is added to separate the line to see which one is null. */
			nonNull(iterable));
		}
		catch (IOException e)
		{
			throw new AssertionError(e);
		}
	}
}