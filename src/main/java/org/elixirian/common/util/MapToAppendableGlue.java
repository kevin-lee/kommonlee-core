/**
 * 
 */
package org.elixirian.common.util;

import static org.elixirian.common.util.Objects.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-06)
 */
public final class MapToAppendableGlue implements ToAppendableGlue<Map<?, ?>>
{
	private final AppendingAction keyValueGlue;
	private final AppendingAction entryGlue;

	private MapToAppendableGlue(String keyValueSeparator, String entrySeparator)
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

	private <A extends Appendable, K, V> A glue0(A appendable, Map<K, V> map) throws IOException
	{
		Iterator<Entry<K, V>> entryIterator = map.entrySet()
				.iterator();
		if (entryIterator.hasNext())
		{
			Entry<K, V> entry = entryIterator.next();
			SimpleAppendingAction.APPENDING_ACTION_WITHOUT_SEPARATOR.append(appendable,
					String.valueOf(entry.getKey()));
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
	public <A extends Appendable> A glue(A appendable, Map<?, ?> map)
	{
		try
		{
			return glue0(notNull(appendable),
			/* the comment is added to separate the line to see which one is null. */
			notNull(map));
		}
		catch (IOException e)
		{
			throw new AssertionError(e);
		}
	}

	public static MapToAppendableGlue newMapToAppendableGlue(String keyValueSeparator, String entrySeparator)
	{
		return new MapToAppendableGlue(keyValueSeparator, entrySeparator);
	}
}
