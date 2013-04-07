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
package org.elixirian.kommonlee.util.collect;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.elixirian.kommonlee.type.GenericBuilder;
import org.elixirian.kommonlee.util.CommonConstants;

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
 * @version 0.0.1 (2011-02-21)
 */
public final class Maps
{
  public static class HashMapBuilder<K, V> implements GenericBuilder<HashMap<K, V>>
  {
    private Map<K, V> map;

    public HashMapBuilder()
    {
      this.map = newHashMap();
    }

    public HashMapBuilder<K, V> put(final K key, final V value)
    {
      map.put(key, value);
      return this;
    }

    public <KT extends K, VT extends V> HashMapBuilder<K, V> putAll(final Map<KT, VT> map)
    {
      map.putAll(map);
      return this;
    }

    @Override
    public HashMap<K, V> build()
    {
      return newHashMap(map);
    }
  }

  public static <K, V> HashMapBuilder<K, V> hashMapBuilder()
  {
    return new HashMapBuilder<K, V>();
  }

  public static class LinkedHashMapBuilder<K, V> implements GenericBuilder<LinkedHashMap<K, V>>
  {
    private Map<K, V> map;

    public LinkedHashMapBuilder()
    {
      this.map = newLinkedHashMap();
    }

    public LinkedHashMapBuilder<K, V> put(final K key, final V value)
    {
      map.put(key, value);
      return this;
    }

    public <KT extends K, VT extends V> LinkedHashMapBuilder<K, V> putAll(final Map<KT, VT> map)
    {
      map.putAll(map);
      return this;
    }

    @Override
    public LinkedHashMap<K, V> build()
    {
      return newLinkedHashMap(map);
    }
  }

  public static <K, V> LinkedHashMapBuilder<K, V> linkedHashMapBuilder()
  {
    return new LinkedHashMapBuilder<K, V>();
  }

  public static class TreeMapBuilder<K, V> implements GenericBuilder<TreeMap<K, V>>
  {
    private NavigableMap<K, V> map;

    public TreeMapBuilder()
    {
      this.map = newTreeMap();
    }

    public TreeMapBuilder(final Comparator<? super K> comparator)
    {
      this.map = newTreeMap(comparator);
    }

    public TreeMapBuilder<K, V> put(final K key, final V value)
    {
      map.put(key, value);
      return this;
    }

    public <KT extends K, VT extends V> TreeMapBuilder<K, V> putAll(final Map<KT, VT> map)
    {
      map.putAll(map);
      return this;
    }

    @Override
    public TreeMap<K, V> build()
    {
      return newTreeMap(map.comparator(), map);
    }
  }

  public static <K, V> TreeMapBuilder<K, V> treeMapBuilder()
  {
    return new TreeMapBuilder<K, V>();
  }

  public static <K, V> TreeMapBuilder<K, V> treeMapBuilder(final Comparator<? super K> comparator)
  {
    return new TreeMapBuilder<K, V>(comparator);
  }

  private Maps() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  /**
   * Returns an immutable empty map returned from Collections.emptyMap() if the given map parameter contains a null
   * reference. Otherwise it returns the parameter map back to the caller.
   * 
   * @param map
   *          the given Map object. It can be null.
   * @return an immutable empty map returned from Collections.emptyMap() if the given map parameter contains a null
   *         reference. Otherwise it returns the parameter map back to the caller.
   */
  public static <K, V> Map<K, V> immutableEmptyMapIfNull(final Map<K, V> map)
  {
    return null == map ? Collections.<K, V> emptyMap() : map;
  }

  public static <K, V> HashMap<K, V> newHashMap()
  {
    return new HashMap<K, V>();
  }

  public static <K, V> HashMap<K, V> newHashMap(final Map<? extends K, ? extends V> map)
  {
    return new HashMap<K, V>(map);
  }

  public static <K, V> HashMap<K, V> newHashMapWithInitialCapacity(final int initialCapacity)
  {
    return new HashMap<K, V>(initialCapacity);
  }

  public static <K, V> LinkedHashMap<K, V> newLinkedHashMap()
  {
    return new LinkedHashMap<K, V>();
  }

  public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(final Map<? extends K, ? extends V> map)
  {
    return new LinkedHashMap<K, V>(map);
  }

  public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithInitialCapacity(final int initialCapacity)
  {
    return new LinkedHashMap<K, V>(initialCapacity);
  }

  public static <K, V> TreeMap<K, V> newTreeMap()
  {
    return new TreeMap<K, V>();
  }

  public static <K, V> TreeMap<K, V> newTreeMap(final Comparator<? super K> comparator)
  {
    return new TreeMap<K, V>(comparator);
  }

  public static <K, V> TreeMap<K, V> newTreeMapFromSortedMap(final SortedMap<K, ? extends V> sortedMap)
  {
    return new TreeMap<K, V>(sortedMap);
  }

  public static <K extends Comparable<K>, V> TreeMap<K, V> newTreeMapFromMap(final Map<? extends K, ? extends V> map)
  {
    return new TreeMap<K, V>(map);
  }

  public static <K, V> TreeMap<K, V> newTreeMap(final Comparator<? super K> comparator,
      final Map<? extends K, ? extends V> map)
  {
    final TreeMap<K, V> treeMap = new TreeMap<K, V>(comparator);
    treeMap.putAll(map);
    return treeMap;
  }
}