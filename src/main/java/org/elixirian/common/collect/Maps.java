package org.elixirian.common.collect;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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