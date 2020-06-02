/**
 * This project is licensed under the Apache License, Version 2.0
 * if the following condition is met:
 * (otherwise it cannot be used by anyone but the author, Kevin, only)
 *
 * The original KommonLee project is owned by Lee, Seong Hyun (Kevin).
 *
 * -What does it mean to you?
 * Nothing, unless you want to take the ownership of
 * "the original project" (not yours or forked & modified one).
 * You are free to use it for both non-commercial and commercial projects
 * and free to modify it as the Apache License allows.
 *
 * -So why is this condition necessary?
 * It is only to protect the original project (See the case of Java).
 *
 *
 * Copyright 2009 Lee, Seong Hyun (Kevin)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
public final class MapToAppendableGlue implements ToAppendableGlue<Map<?, ?>>
{
  private final AppendingAction keyValueGlue;
  private final AppendingAction entryGlue;

  private MapToAppendableGlue(final String keyValueSeparator, final String entrySeparator)
  {
    this.keyValueGlue = SimpleAppendingAction.with(mustNotBeNull(keyValueSeparator));
    this.entryGlue = SimpleAppendingAction.with(mustNotBeNull(entrySeparator));
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
      return glue0(mustNotBeNull(appendable),
      /* the comment is added to separate the line to see which one is null. */
      mustNotBeNull(map));
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
