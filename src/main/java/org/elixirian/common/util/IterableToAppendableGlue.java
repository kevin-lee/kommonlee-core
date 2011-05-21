package org.elixirian.common.util;

import static org.elixirian.common.util.Objects.*;

import java.io.IOException;
import java.util.Iterator;

/**
 * <pre>
 *     ____________    ___________  ____   _______ _________ _______ _____________    ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /   \  /   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /     \/   /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____  __________  ___________ ___    ____
 *    /   \/    / /      \   \/   /_    _//   \  /   /
 *   /        /  /    ___/\      / /   / /     \/   /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-05)
 */
public final class IterableToAppendableGlue implements ToAppendableGlue<Iterable<?>>
{
	private final AppendingAction appendingAction;

	private IterableToAppendableGlue(final AppendingAction appendingAction)
	{
		this.appendingAction = appendingAction;
	}

	AppendingAction getGlue()
	{
		return appendingAction;
	}

	public static IterableToAppendableGlue withSeparator(final String separator)
	{
		return new IterableToAppendableGlue(SimpleAppendingAction.with(separator));
	}

	public static IterableToAppendableGlue withoutSeparator()
	{
		return new IterableToAppendableGlue(SimpleAppendingAction.withoutSeparator());
	}

	private <A extends Appendable, E> A glue0(final A appendable, final Iterable<E> iterable) throws IOException
	{
		final Iterator<E> iterator = iterable.iterator();
		if (iterator.hasNext())
		{
			SimpleAppendingAction.APPENDING_ACTION_WITHOUT_SEPARATOR.append(appendable, iterator.next());
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
			/* @formatter:off */
			return glue0(notNull(appendable),
						  notNull(iterable));
			/* @formatter:on */
		}
		catch (IOException e)
		{
			throw new AssertionError(e);
		}
	}
}