package org.elixirian.kommonlee.collect;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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
  private Maps()
  {
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

  public static <K, V> HashMap<K, V> newLinkedHashMap(final Map<? extends K, ? extends V> map)
  {
    return new LinkedHashMap<K, V>(map);
  }

  public static <K, V> HashMap<K, V> newLinkedHashMapWithInitialCapacity(final int initialCapacity)
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