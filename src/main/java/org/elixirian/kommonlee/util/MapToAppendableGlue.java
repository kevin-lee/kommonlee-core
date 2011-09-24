/**
 * 
 */
package org.elixirian.kommonlee.util;

import static org.elixirian.kommonlee.util.Objects.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
public final class MapToAppendableGlue implements ToAppendableGlue<Map<?, ?>>
{
	private final AppendingAction keyValueGlue;
	private final AppendingAction entryGlue;

	private MapToAppendableGlue(final String keyValueSeparator, final String entrySeparator)
	{
		this.keyValueGlue = SimpleAppendingAction.with(notNull(keyValueSeparator));
		this.entryGlue = SimpleAppendingAction.with(notNull(entrySeparator));
	}

	AppendingAction getKeyValueGlue()
	{
		return keyValueGlue;
	}

	AppendingAction getEntryGlue()
	{
		return entryGlue;
	}

	private <A extends Appendable, K, V> A glue0(final A appendable, final Map<K, V> map) throws IOException
	{
		final Iterator<Entry<K, V>> entryIterator = map.entrySet()
				.iterator();
		if (entryIterator.hasNext())
		{
			Entry<K, V> entry = entryIterator.next();
			SimpleAppendingAction.APPENDING_ACTION_WITHOUT_SEPARATOR.append(appendable, String.valueOf(entry.getKey()));
			keyValueGlue.append(appendable, (String.valueOf(entry.getValue())));
			while (entryIterator.hasNext())
			{
				entry = entryIterator.next();
				entryGlue.append(appendable, String.valueOf(entry.getKey()));
				keyValueGlue.append(appendable, (String.valueOf(entry.getValue())));
			}
		}
		return appendable;
	}

	@Override
	public <A extends Appendable> A glue(final A appendable, final Map<?, ?> map)
	{
		try
		{
			return glue0(notNull(appendable),
			/* the comment is added to separate the line to see which one is null. */
			notNull(map));
		}
		catch (final IOException e)
		{
			throw new IllegalArgumentException(e);
		}
	}

	public static MapToAppendableGlue newMapToAppendableGlue(final String keyValueSeparator, final String entrySeparator)
	{
		return new MapToAppendableGlue(keyValueSeparator, entrySeparator);
	}
}
