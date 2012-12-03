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
package org.elixirian.kommonlee.functional.collect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.elixirian.kommonlee.functional.BreakableFunction1;
import org.elixirian.kommonlee.functional.GenericVarargsSelector;
import org.elixirian.kommonlee.functional.VoidFunction1;
import org.elixirian.kommonlee.functional.string.PrefixAndSuffixAdder;
import org.elixirian.kommonlee.functional.string.StringArrayToTrimmedStringListSelector;
import org.elixirian.kommonlee.type.Pair;
import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.functional.Function1;
import org.elixirian.kommonlee.type.functional.Function2;
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
 * @version 0.0.1 (2011-02-26)
 */
public final class CollectionUtil
{
  public static final StringArrayToTrimmedStringListSelector STRING_ARRAY_TO_LIST_TRIM_SELECTOR =
    new StringArrayToTrimmedStringListSelector();

  private static final ArrayToArrayListSelector<?, ? extends Condition1<?>> ARRAY_TO_ARRAY_LIST_SELECTOR =
    new ArrayToArrayListSelector<Object, Condition1<Object>>();

  private static final ArrayToArraySelector<?, ? extends Condition1<?>> ARRAY_TO_ARRAY_SELECTOR =
    new ArrayToArraySelector<Object, Condition1<Object>>();

  private final static ElementCountSelector<?> ELEMENT_COUNT_SELECTOR = new ElementCountSelector<Object>();

  private static final IterableToArrayListSelector<Object> ITERABLE_TO_ARRAY_LIST_SELECTOR =
    IterableToArrayListSelector.newInstance(ArrayListCreator.getInstance());

  private static final IterableToHashSetSelector<Object> ITERABLE_TO_HASH_SET_SELECTOR =
    IterableToHashSetSelector.newInstance(HashSetCreator.getInstance());

  // public static final IterableToCollectionMapper<Object, ? extends Iterable<Object>, Object, ? extends
  // Function1<Object, Object>, ? extends List<Object>> ITERABLE_TO_COLLECTION_MAPPER =
  // IterableToCollectionMapper.newInstance(ArrayListCreator.getInstance());

  private static final ArrayToArrayMapper<?, ?, ? extends Function1<?, ?>> ARRAY_TO_ARRAY_MAPPER =
    new ArrayToArrayMapper<Object, Object, Function1<Object, Object>>();

  private static final ArrayToCollectionMapper<?, ?, ? extends Function1<?, ?>, ? extends ArrayList<?>> ARRAY_TO_ARRAY_LIST_MAPPER =
    ArrayToCollectionMapper.<Object, Object, Function1<Object, Object>, ArrayList<Object>, ArrayListCreator<Object>> newInstance(ArrayListCreator.getInstance());

  private static final ArrayToCollectionMapper<?, ?, ? extends Function1<?, ?>, ? extends HashSet<?>> ARRAY_TO_HASH_SET_MAPPER =
    ArrayToCollectionMapper.<Object, Object, Function1<Object, Object>, HashSet<Object>, HashSetCreator<Object>> newInstance(HashSetCreator.getInstance());

  private static final ArrayToMapMapper<?, ?, ?, ? extends Function1<?, ? extends Pair<?, ?>>, ? extends HashMap<?, ?>> ARRAY_TO_HASH_MAP_MAPPER =
    ArrayToMapMapper.<Object, Object, Object, Function1<Object, Pair<Object, Object>>, HashMap<Object, Object>, HashMapCreator<Object, Object>> newInstance(HashMapCreator.getInstance());

  public static class IterableToArrayListMapper<E, NE> extends
      IterableToCollectionMapper<E, Iterable<? extends E>, NE, Function1<? super E, NE>, ArrayList<NE>>
  {
    protected IterableToArrayListMapper(final ArrayListCreator<NE> collectionCreator)
    {
      super(collectionCreator);
    }

    public static <E, NE> IterableToArrayListMapper<E, NE> newInstance(final ArrayListCreator<NE> collectionCreator)
    {
      return new IterableToArrayListMapper<E, NE>(collectionCreator);
    }
  }

  private static final IterableToArrayListMapper<?, ?> ITERABLE_TO_ARRAY_LIST_MAPPER =
    IterableToArrayListMapper.newInstance(ArrayListCreator.getInstance());

  public static class IterableToHashSetMapper<E, NE> extends
      IterableToCollectionMapper<E, Iterable<? extends E>, NE, Function1<? super E, NE>, HashSet<NE>>
  {
    protected IterableToHashSetMapper(final HashSetCreator<NE> collectionCreator)
    {
      super(collectionCreator);
    }

    public static <E, NE> IterableToHashSetMapper<E, NE> newInstance(final HashSetCreator<NE> collectionCreator)
    {
      return new IterableToHashSetMapper<E, NE>(collectionCreator);
    }
  }

  private static final IterableToHashSetMapper<?, ?> ITERABLE_TO_HASH_SET_MAPPER =
    IterableToHashSetMapper.newInstance(HashSetCreator.getInstance());

  public static class IterableToArrayListSelectableMapper<E, NE>
      extends
      IterableToCollectionSelectableMapper<E, Iterable<? extends E>, Condition1<? super E>, NE, Function1<? super E, NE>, ArrayList<NE>>
  {

    public IterableToArrayListSelectableMapper(final ArrayListCreator<NE> collectionCreator)
    {
      super(collectionCreator);
    }

    public static <E, NE> IterableToArrayListSelectableMapper<E, NE> newInstance(
        final ArrayListCreator<NE> collectionCreator)
    {
      return new IterableToArrayListSelectableMapper<E, NE>(collectionCreator);
    }
  }

  public static class IterableToHashSetSelectableMapper<E, NE>
      extends
      IterableToCollectionSelectableMapper<E, Iterable<? extends E>, Condition1<? super E>, NE, Function1<? super E, NE>, HashSet<NE>>
  {

    public IterableToHashSetSelectableMapper(final HashSetCreator<NE> collectionCreator)
    {
      super(collectionCreator);
    }

    public static <E, NE> IterableToHashSetSelectableMapper<E, NE> newInstance(
        final HashSetCreator<NE> collectionCreator)
    {
      return new IterableToHashSetSelectableMapper<E, NE>(collectionCreator);
    }
  }

  private static final IterableToMapMapper<?, ? extends Iterable<?>, ?, ?, ? extends Function1<?, ? extends Pair<?, ?>>, ? extends HashMap<?, ?>> ITERABLE_TO_HASH_MAP_MAPPER =
    IterableToMapMapper.<Object, Iterable<Object>, Object, Object, Function1<Object, Pair<Object, Object>>, HashMap<Object, Object>, HashMapCreator<Object, Object>> newInstance(HashMapCreator.getInstance());

  private static final ArrayToArraySelectableMapper<?, ? extends Condition1<?>, ?, ? extends Function1<?, ?>> ARRAY_TO_ARRAY_SELECTABLE_MAPPER =
    new ArrayToArraySelectableMapper<Object, Condition1<Object>, Object, Function1<Object, Object>>();

  private static final ArrayToCollectionSelectableMapper<?, ? extends Condition1<?>, ?, ? extends Function1<?, ?>, ? extends ArrayList<?>> ARRAY_TO_ARRAY_LIST_SELECTABLE_MAPPER =
    ArrayToCollectionSelectableMapper.<Object, Condition1<Object>, Object, Function1<Object, Object>, ArrayList<Object>, ArrayListCreator<Object>> newInstance(ArrayListCreator.getInstance());

  private static final ArrayToCollectionSelectableMapper<?, ? extends Condition1<?>, ?, ? extends Function1<?, ?>, ? extends HashSet<?>> ARRAY_TO_HASH_SET_SELECTABLE_MAPPER =
    ArrayToCollectionSelectableMapper.<Object, Condition1<Object>, Object, Function1<Object, Object>, HashSet<Object>, HashSetCreator<Object>> newInstance(HashSetCreator.getInstance());

  private static final IterableToArrayListSelectableMapper<?, ?> ITERABLE_TO_ARRAY_LIST_SELECTABLE_MAPPER =
    IterableToArrayListSelectableMapper.newInstance(ArrayListCreator.getInstance());

  private static final IterableToHashSetSelectableMapper<?, ?> ITERABLE_TO_HASH_SET_SELECTABLE_MAPPER =
    IterableToHashSetSelectableMapper.newInstance(HashSetCreator.getInstance());

  private static final GenericVarargsSelector<?, ?, ?> GENERIC_VARARGS_SELECTOR =
    new GenericVarargsSelector<Object, Condition1<Object>, List<Object>>(ArrayListCreator.getInstance());

  private static final ForEachInIterable<?, ? extends Iterable<?>, ? extends VoidFunction1<?>> FOR_EACH_IN_ITERABLE =
    new ForEachInIterable<Object, Iterable<Object>, VoidFunction1<Object>>();

  private static final BreakableForEachInIterable<?, ? extends Iterable<Object>, ? extends BreakableFunction1<?>> BREAKABLE_FOR_EACH_IN_ITERABLE =
    new BreakableForEachInIterable<Object, Iterable<Object>, BreakableFunction1<Object>>();

  private static final ForEachInArray<?, ? extends VoidFunction1<?>> FOR_EACH_IN_ARRAY =
    new ForEachInArray<Object, VoidFunction1<Object>>();

  private static final BreakableForEachInArray<?, ? extends BreakableFunction1<?>> BREAKABLE_FOR_EACH_IN_ARRAY =
    new BreakableForEachInArray<Object, BreakableFunction1<Object>>();

  private static final MapToHashMapWithNewKeyMapper<?, ?, ? extends Map<?, ?>, ?, ? extends Function1<?, ?>> MAP_TO_MAP_WITH_NEW_KEY_MAPPER =
    MapToHashMapWithNewKeyMapper.<Object, Object, Map<Object, Object>, Object, Function1<Object, Object>> newInstance(HashMapCreator.getInstance());

  private static final MapToHashMapWithNewValueMapper<?, ?, ? extends Map<?, ?>, ?, ? extends Function1<?, ?>> MAP_TO_MAP_WITH_NEW_VALUE_MAPPER =
    MapToHashMapWithNewValueMapper.<Object, Object, Map<Object, Object>, Object, Function1<Object, Object>> newInstance(HashMapCreator.getInstance());

  private static final MapToHashMapWithNewKeyNewValueMapper<?, ?, ? extends Map<?, ?>, ?, ?, ? extends Function2<?, ?, ? extends Pair<?, ?>>> MAP_TO_MAP_WITH_NEW_KEY_NEW_VALUE_MAPPER =
    MapToHashMapWithNewKeyNewValueMapper.<Object, Object, Map<Object, Object>, Object, Object, Function2<Object, Object, Pair<Object, Object>>> newInstance(HashMapCreator.getInstance());

  // ///////////////////////// SELECTORS

  public static class ArrayToArraySelectorFunction
  {
    static final ArrayToArraySelectorFunction INSTANCE = new ArrayToArraySelectorFunction();

    private ArrayToArraySelectorFunction()
    {
    }

    public <E, C extends Condition1<? super E>> E[] select(final C condition, final E[] source)
    {
      @SuppressWarnings("unchecked")
      final ArrayToArraySelector<E, C> arrayToArraySelector = (ArrayToArraySelector<E, C>) ARRAY_TO_ARRAY_SELECTOR;
      return arrayToArraySelector.select(condition, source);
    }
  }

  public static class ArrayToArrayListSelectorFunction
  {
    static final ArrayToArrayListSelectorFunction INSTANCE = new ArrayToArrayListSelectorFunction();

    private ArrayToArrayListSelectorFunction()
    {
    }

    public <E, C extends Condition1<? super E>> ArrayList<E> select(final C condition, final E[] source)
    {
      @SuppressWarnings("unchecked")
      final ArrayToArrayListSelector<E, C> arrayToArrayListSelector =
        (ArrayToArrayListSelector<E, C>) ARRAY_TO_ARRAY_LIST_SELECTOR;
      return arrayToArrayListSelector.select(condition, source);
    }
  }

  public static class IterableToArrayListSelectorFunction
  {
    static final IterableToArrayListSelectorFunction INSTANCE = new IterableToArrayListSelectorFunction();

    private IterableToArrayListSelectorFunction()
    {
    }

    public <E, T extends Iterable<? extends E>, C extends Condition1<? super E>> ArrayList<E> select(final C condition,
        final T source)
    {
      @SuppressWarnings("unchecked")
      final IterableToArrayListSelector<E> listToArrayListSelector =
        (IterableToArrayListSelector<E>) ITERABLE_TO_ARRAY_LIST_SELECTOR;
      return listToArrayListSelector.select(condition, source);
    }
  }

  public static class IterableToHashSetSelectorFunction
  {
    static final IterableToHashSetSelectorFunction INSTANCE = new IterableToHashSetSelectorFunction();

    private IterableToHashSetSelectorFunction()
    {
    }

    public <E, T extends Iterable<? extends E>, C extends Condition1<? super E>> HashSet<E> select(final C condition,
        final T source)
    {
      @SuppressWarnings("unchecked")
      final IterableToHashSetSelector<E> iterableToHashSetSelector =
        (IterableToHashSetSelector<E>) ITERABLE_TO_HASH_SET_SELECTOR;
      return iterableToHashSetSelector.select(condition, source);
    }
  }

  public static class SelectorFromIterable
  {
    static final SelectorFromIterable INSTANCE = new SelectorFromIterable();

    private SelectorFromIterable()
    {
    }

    public IterableToArrayListSelectorFunction toArrayList()
    {
      return IterableToArrayListSelectorFunction.INSTANCE;
    }

    public IterableToHashSetSelectorFunction toHashSet()
    {
      return IterableToHashSetSelectorFunction.INSTANCE;
    }
  }

  public static class SelectorFromArray
  {
    static final SelectorFromArray INSTANCE = new SelectorFromArray();

    private SelectorFromArray()
    {
    }

    public ArrayToArraySelectorFunction toArray()
    {
      return ArrayToArraySelectorFunction.INSTANCE;
    }

    public ArrayToArrayListSelectorFunction toArrayList()
    {
      return ArrayToArrayListSelectorFunction.INSTANCE;
    }
  }

  public static class Selectors
  {
    static final Selectors INSTANCE = new Selectors();

    private Selectors()
    {
    }

    public SelectorFromArray fromArray()
    {
      return SelectorFromArray.INSTANCE;
    }

    public SelectorFromIterable fromIterable()
    {
      return SelectorFromIterable.INSTANCE;
    }
  }

  // ////////////////// MAPPERS

  public static class ArrayToArrayMapperFunction
  {
    static final ArrayToArrayMapperFunction INSTANCE = new ArrayToArrayMapperFunction();

    private ArrayToArrayMapperFunction()
    {
    }

    public <E, NE, F extends Function1<? super E, NE>> NE[] map(final Class<NE> toArrayOf, final F function,
        final E[] source)
    {
      @SuppressWarnings("unchecked")
      final ArrayToArrayMapper<E, NE, Function1<? super E, NE>> arrayToArrayMapper =
        (ArrayToArrayMapper<E, NE, Function1<? super E, NE>>) ARRAY_TO_ARRAY_MAPPER;
      return arrayToArrayMapper.apply(toArrayOf, function, source);
    }
  }

  public static class ArrayToArrayListMapperFunction
  {
    static final ArrayToArrayListMapperFunction INSTANCE = new ArrayToArrayListMapperFunction();

    private ArrayToArrayListMapperFunction()
    {
    }

    public <E, NE, F extends Function1<? super E, NE>> ArrayList<NE> map(final F function, final E[] source)
    {
      @SuppressWarnings("unchecked")
      final ArrayToCollectionMapper<E, NE, F, ArrayList<NE>> arrayToArrayListMapper =
        (ArrayToCollectionMapper<E, NE, F, ArrayList<NE>>) ARRAY_TO_ARRAY_LIST_MAPPER;
      return arrayToArrayListMapper.apply(function, source);
    }
  }

  public static class ArrayToHashSetMapperFunction
  {
    static final ArrayToHashSetMapperFunction INSTANCE = new ArrayToHashSetMapperFunction();

    private ArrayToHashSetMapperFunction()
    {
    }

    public <E, NE, F extends Function1<? super E, NE>> HashSet<NE> map(final F function, final E[] source)
    {
      @SuppressWarnings("unchecked")
      final ArrayToCollectionMapper<E, NE, F, HashSet<NE>> arrayToHashSetMapper =
        (ArrayToCollectionMapper<E, NE, F, HashSet<NE>>) ARRAY_TO_HASH_SET_MAPPER;
      return arrayToHashSetMapper.apply(function, source);
    }
  }

  public static class ArrayToHashMapMapperFunction
  {
    static final ArrayToHashMapMapperFunction INSTANCE = new ArrayToHashMapMapperFunction();

    private ArrayToHashMapMapperFunction()
    {
    }

    public <E, NE, NK, F extends Function1<? super E, ? extends Pair<NK, NE>>> HashMap<NK, NE> map(final F function,
        final E[] source)
    {
      @SuppressWarnings("unchecked")
      final ArrayToMapMapper<E, NK, NE, F, HashMap<NK, NE>> arrayToHashMapMapper =
        (ArrayToMapMapper<E, NK, NE, F, HashMap<NK, NE>>) ARRAY_TO_HASH_MAP_MAPPER;
      return arrayToHashMapMapper.apply(function, source);
    }
  }

  public static class IterableToArrayListMapperFunction
  {
    static final IterableToArrayListMapperFunction INSTANCE = new IterableToArrayListMapperFunction();

    private IterableToArrayListMapperFunction()
    {
    }

    public <E, T extends Iterable<? extends E>, NE, F extends Function1<? super E, NE>> ArrayList<NE> map(
        final F function, final T source)
    {
      @SuppressWarnings("unchecked")
      final IterableToArrayListMapper<E, NE> iterableToArrayListMapper =
        (IterableToArrayListMapper<E, NE>) ITERABLE_TO_ARRAY_LIST_MAPPER;
      return iterableToArrayListMapper.apply(function, source);
    }
  }

  public static class IterableToHashSetMapperFunction
  {
    static final IterableToHashSetMapperFunction INSTANCE = new IterableToHashSetMapperFunction();

    private IterableToHashSetMapperFunction()
    {
    }

    public <E, T extends Iterable<? extends E>, NE, F extends Function1<? super E, NE>> HashSet<NE> map(
        final F function, final T source)
    {
      @SuppressWarnings("unchecked")
      final IterableToHashSetMapper<E, NE> iterableToHashSetMapper =
        (IterableToHashSetMapper<E, NE>) ITERABLE_TO_HASH_SET_MAPPER;
      return iterableToHashSetMapper.apply(function, source);
    }
  }

  public static class IterableToHashMapMapperFunction
  {
    static final IterableToHashMapMapperFunction INSTANCE = new IterableToHashMapMapperFunction();

    private IterableToHashMapMapperFunction()
    {
    }

    public <E, T extends Iterable<? extends E>, NK, NE, F extends Function1<? super E, ? extends Pair<NK, NE>>> HashMap<NK, NE> map(
        final F function, final T source)
    {
      @SuppressWarnings("unchecked")
      final IterableToMapMapper<E, T, NK, NE, F, HashMap<NK, NE>> iterableToHashMapMapper =
        (IterableToMapMapper<E, T, NK, NE, F, HashMap<NK, NE>>) ITERABLE_TO_HASH_MAP_MAPPER;
      return iterableToHashMapMapper.apply(function, source);
    }
  }

  public static class MapToHashMapWithNewKeyMapperFunction
  {
    static final MapToHashMapWithNewKeyMapperFunction INSTANCE = new MapToHashMapWithNewKeyMapperFunction();

    private MapToHashMapWithNewKeyMapperFunction()
    {
    }

    public <K, E, T extends Map<? extends K, ? extends E>, NK, F extends Function1<? super K, NK>> HashMap<NK, E> map(
        final F function, final T inputMap)
    {
      @SuppressWarnings("unchecked")
      final MapToHashMapWithNewKeyMapper<K, E, T, NK, F> mapToMapWithNewKeyMapper =
        (MapToHashMapWithNewKeyMapper<K, E, T, NK, F>) MAP_TO_MAP_WITH_NEW_KEY_MAPPER;
      return mapToMapWithNewKeyMapper.apply(function, inputMap);
    }
  }

  public static class MapToHashMapWithNewValueMapperFunction
  {
    static final MapToHashMapWithNewValueMapperFunction INSTANCE = new MapToHashMapWithNewValueMapperFunction();

    private MapToHashMapWithNewValueMapperFunction()
    {
    }

    public <K, E, T extends Map<? extends K, ? extends E>, NE, F extends Function1<? super E, NE>> HashMap<K, NE> map(
        final F function, final T inputMap)
    {
      @SuppressWarnings("unchecked")
      final MapToHashMapWithNewValueMapper<K, E, T, NE, F> mapToHashMapWithNewValueMapper =
        (MapToHashMapWithNewValueMapper<K, E, T, NE, F>) MAP_TO_MAP_WITH_NEW_VALUE_MAPPER;
      return mapToHashMapWithNewValueMapper.apply(function, inputMap);
    }
  }

  public static class MapToHashMapWithNewKeyNewValueMapperFunction
  {
    static final MapToHashMapWithNewKeyNewValueMapperFunction INSTANCE =
      new MapToHashMapWithNewKeyNewValueMapperFunction();

    private MapToHashMapWithNewKeyNewValueMapperFunction()
    {
    }

    public <K, E, T extends Map<? extends K, ? extends E>, NK, NE, F extends Function2<? super K, ? super E, Pair<NK, NE>>> HashMap<NK, NE> map(
        final F function, final T inputMap)
    {
      @SuppressWarnings("unchecked")
      final MapToHashMapWithNewKeyNewValueMapper<K, E, T, NK, NE, F> mapToHashMapWithNewKeyNewValueMapper =
        (MapToHashMapWithNewKeyNewValueMapper<K, E, T, NK, NE, F>) MAP_TO_MAP_WITH_NEW_KEY_NEW_VALUE_MAPPER;
      return mapToHashMapWithNewKeyNewValueMapper.apply(function, inputMap);
    }
  }

  public static class MapperFromArray
  {
    static final MapperFromArray INSTANCE = new MapperFromArray();

    private MapperFromArray()
    {
    }

    public ArrayToArrayMapperFunction toArray()
    {
      return ArrayToArrayMapperFunction.INSTANCE;
    }

    public ArrayToArrayListMapperFunction toArrayList()
    {
      return ArrayToArrayListMapperFunction.INSTANCE;
    }

    public ArrayToHashSetMapperFunction toHashSet()
    {
      return ArrayToHashSetMapperFunction.INSTANCE;
    }

    public ArrayToHashMapMapperFunction toHashMap()
    {
      return ArrayToHashMapMapperFunction.INSTANCE;
    }
  }

  public static class MapperFromIterable
  {
    static final MapperFromIterable INSTANCE = new MapperFromIterable();

    private MapperFromIterable()
    {
    }

    public IterableToArrayListMapperFunction toArrayList()
    {
      return IterableToArrayListMapperFunction.INSTANCE;
    }

    public IterableToHashSetMapperFunction toHashSet()
    {
      return IterableToHashSetMapperFunction.INSTANCE;
    }

    public IterableToHashMapMapperFunction toHashMap()
    {
      return IterableToHashMapMapperFunction.INSTANCE;
    }
  }

  public static class MapperFromMapToHashMap
  {
    static final MapperFromMapToHashMap INSTANCE = new MapperFromMapToHashMap();

    private MapperFromMapToHashMap()
    {
    }

    public MapToHashMapWithNewKeyMapperFunction withNewKey()
    {
      return MapToHashMapWithNewKeyMapperFunction.INSTANCE;
    }

    public MapToHashMapWithNewValueMapperFunction withNewValue()
    {
      return MapToHashMapWithNewValueMapperFunction.INSTANCE;
    }

    public MapToHashMapWithNewKeyNewValueMapperFunction withNewKeyNewValue()
    {
      return MapToHashMapWithNewKeyNewValueMapperFunction.INSTANCE;
    }
  }

  public static class MapperFromMap
  {
    static final MapperFromMap INSTANCE = new MapperFromMap();

    private MapperFromMap()
    {
    }

    public MapperFromMapToHashMap toHashMap()
    {
      return MapperFromMapToHashMap.INSTANCE;
    }
  }

  public static class Mappers
  {
    static final Mappers INSTANCE = new Mappers();

    private Mappers()
    {
    }

    public MapperFromArray fromArray()
    {
      return MapperFromArray.INSTANCE;
    }

    public MapperFromIterable fromIterable()
    {
      return MapperFromIterable.INSTANCE;
    }

    public MapperFromMap fromMap()
    {
      return MapperFromMap.INSTANCE;
    }
  }

  // ////////////////// SELECTABLE MAPPERS

  public static class ArrayToArraySelectableMapperFunction
  {
    static final ArrayToArraySelectableMapperFunction INSTANCE = new ArrayToArraySelectableMapperFunction();

    private ArrayToArraySelectableMapperFunction()
    {
    }

    public <E, C extends Condition1<? super E>, NE, F extends Function1<? super E, NE>> NE[] mapSelectively(
        final Class<NE> toArrayOf, final C condition, final F function, final E[] source)
    {
      @SuppressWarnings("unchecked")
      final ArrayToArraySelectableMapper<E, C, NE, F> arrayToArrayListSelectableMapper =
        (ArrayToArraySelectableMapper<E, C, NE, F>) ARRAY_TO_ARRAY_SELECTABLE_MAPPER;
      return arrayToArrayListSelectableMapper.apply(toArrayOf, condition, function, source);
    }
  }

  public static class ArrayToArrayListSelectableMapperFunction
  {
    static final ArrayToArrayListSelectableMapperFunction INSTANCE = new ArrayToArrayListSelectableMapperFunction();

    private ArrayToArrayListSelectableMapperFunction()
    {
    }

    public <E, C extends Condition1<? super E>, NE, F extends Function1<? super E, NE>> ArrayList<NE> mapSelectively(
        final C condition, final F function, final E[] source)
    {
      @SuppressWarnings("unchecked")
      final ArrayToCollectionSelectableMapper<E, C, NE, F, ArrayList<NE>> arrayToArrayListSelectableMapper =
        (ArrayToCollectionSelectableMapper<E, C, NE, F, ArrayList<NE>>) ARRAY_TO_ARRAY_LIST_SELECTABLE_MAPPER;
      return arrayToArrayListSelectableMapper.apply(condition, function, source);
    }
  }

  public static class ArrayToHashSetSelectableMapperFunction
  {
    static final ArrayToHashSetSelectableMapperFunction INSTANCE = new ArrayToHashSetSelectableMapperFunction();

    private ArrayToHashSetSelectableMapperFunction()
    {
    }

    public <E, C extends Condition1<? super E>, NE, F extends Function1<? super E, NE>> HashSet<NE> mapSelectively(
        final C condition, final F function, final E[] source)
    {
      @SuppressWarnings("unchecked")
      final ArrayToCollectionSelectableMapper<E, C, NE, F, HashSet<NE>> arrayToHashSetSelectableMapper =
        (ArrayToCollectionSelectableMapper<E, C, NE, F, HashSet<NE>>) ARRAY_TO_HASH_SET_SELECTABLE_MAPPER;
      return arrayToHashSetSelectableMapper.apply(condition, function, source);
    }
  }

  public static class SelectableMapperFromArray
  {
    static final SelectableMapperFromArray INSTANCE = new SelectableMapperFromArray();

    private SelectableMapperFromArray()
    {
    }

    public ArrayToArraySelectableMapperFunction toArray()
    {
      return ArrayToArraySelectableMapperFunction.INSTANCE;
    }

    public ArrayToArrayListSelectableMapperFunction toArrayList()
    {
      return ArrayToArrayListSelectableMapperFunction.INSTANCE;
    }

    public ArrayToHashSetSelectableMapperFunction toHashSet()
    {
      return ArrayToHashSetSelectableMapperFunction.INSTANCE;
    }
  }

  public static class IterableToArrayListSelectableMapperFunction
  {
    static final IterableToArrayListSelectableMapperFunction INSTANCE =
      new IterableToArrayListSelectableMapperFunction();

    private IterableToArrayListSelectableMapperFunction()
    {
    }

    public <E, T extends Iterable<? extends E>, C extends Condition1<? super E>, NE, F extends Function1<? super E, NE>> ArrayList<NE> mapSelectively(
        final C condition, final F function, final T source)
    {
      @SuppressWarnings("unchecked")
      final IterableToArrayListSelectableMapper<E, NE> iterableToArrayListMapper =
        (IterableToArrayListSelectableMapper<E, NE>) ITERABLE_TO_ARRAY_LIST_SELECTABLE_MAPPER;
      return iterableToArrayListMapper.apply(condition, function, source);
    }
  }

  public static class IterableToHashSetSelectableMapperFunction
  {
    static final IterableToHashSetSelectableMapperFunction INSTANCE = new IterableToHashSetSelectableMapperFunction();

    private IterableToHashSetSelectableMapperFunction()
    {
    }

    public <E, T extends Iterable<? extends E>, C extends Condition1<? super E>, NE, F extends Function1<? super E, NE>> HashSet<NE> mapSelectively(
        final C condition, final F function, final T source)
    {
      @SuppressWarnings("unchecked")
      final IterableToHashSetSelectableMapper<E, NE> iterableToHashSetSelectableMapper =
        (IterableToHashSetSelectableMapper<E, NE>) ITERABLE_TO_HASH_SET_SELECTABLE_MAPPER;
      return iterableToHashSetSelectableMapper.apply(condition, function, source);
    }
  }

  public static class SelectableMapperFromIterable
  {
    static final SelectableMapperFromIterable INSTANCE = new SelectableMapperFromIterable();

    private SelectableMapperFromIterable()
    {
    }

    public IterableToArrayListSelectableMapperFunction toArrayList()
    {
      return IterableToArrayListSelectableMapperFunction.INSTANCE;
    }

    public IterableToHashSetSelectableMapperFunction toHashSet()
    {
      return IterableToHashSetSelectableMapperFunction.INSTANCE;
    }
  }

  public static class SelectableMappers
  {
    static final SelectableMappers INSTANCE = new SelectableMappers();

    private SelectableMappers()
    {
    }

    public SelectableMapperFromArray fromArray()
    {
      return SelectableMapperFromArray.INSTANCE;
    }

    public SelectableMapperFromIterable fromIterable()
    {
      return SelectableMapperFromIterable.INSTANCE;
    }
  }

  private CollectionUtil() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  public static Selectors selector()
  {
    return Selectors.INSTANCE;
  }

  public static Mappers mapper()
  {
    return Mappers.INSTANCE;
  }

  public static SelectableMappers selectableMapper()
  {
    return SelectableMappers.INSTANCE;
  }

  public static StringArrayToTrimmedStringListSelector stringArrayToTrimmedStringListSelector()
  {
    return STRING_ARRAY_TO_LIST_TRIM_SELECTOR;
  }

  public static <E> ElementCountSelector<E> getElementCountSelector()
  {
    @SuppressWarnings("unchecked")
    final ElementCountSelector<E> elementCountSelector = (ElementCountSelector<E>) ELEMENT_COUNT_SELECTOR;
    return elementCountSelector;
  }

  public static <E, T extends Iterable<? extends E>, C extends Condition1<? super E>> ArrayList<E> select(
      final C condition, final T source)
  {
    @SuppressWarnings("unchecked")
    final IterableToArrayListSelector<E> listToArrayListSelector =
      (IterableToArrayListSelector<E>) ITERABLE_TO_ARRAY_LIST_SELECTOR;
    return listToArrayListSelector.select(condition, source);
  }

  public static <E, T extends List<? extends E>, C extends Condition1<? super E>> ArrayList<E> select(
      final C condition, final T source)
  {
    @SuppressWarnings("unchecked")
    final IterableToArrayListSelector<E> listToArrayListSelector =
      (IterableToArrayListSelector<E>) ITERABLE_TO_ARRAY_LIST_SELECTOR;
    return listToArrayListSelector.select(condition, source);
  }

  public static <E, T extends Set<? extends E>, C extends Condition1<? super E>> HashSet<E> select(final C condition,
      final T source)
  {
    @SuppressWarnings("unchecked")
    final IterableToHashSetSelector<E> listToArrayListSelector =
      (IterableToHashSetSelector<E>) ITERABLE_TO_HASH_SET_SELECTOR;
    return listToArrayListSelector.select(condition, source);
  }

  public static <E, C extends Condition1<? super E>> List<E> select(final C condition, final E[] source)
  {
    @SuppressWarnings("unchecked")
    final ArrayToArrayListSelector<E, C> arrayToListSelector =
      (ArrayToArrayListSelector<E, C>) ARRAY_TO_ARRAY_LIST_SELECTOR;
    return arrayToListSelector.select(condition, source);
  }

  public static <K, E, T extends Map<? extends K, ? extends E>, NK, F extends Function1<? super K, NK>> HashMap<NK, E> map(
      final F function, final T source)
  {
    @SuppressWarnings("unchecked")
    final MapToHashMapWithNewKeyMapper<K, E, T, NK, F> mapToMapWithNewKeyMapper =
      (MapToHashMapWithNewKeyMapper<K, E, T, NK, F>) MAP_TO_MAP_WITH_NEW_KEY_MAPPER;
    return mapToMapWithNewKeyMapper.apply(function, source);
  }

  public static <E, T extends Iterable<? extends E>, F extends VoidFunction1<? super E>> void forEach(final F function,
      final T source)
  {
    @SuppressWarnings("unchecked")
    final ForEachInIterable<E, T, F> forEachInIterable = (ForEachInIterable<E, T, F>) FOR_EACH_IN_ITERABLE;
    forEachInIterable.forEach(function, source);
  }

  public static <E, T extends Iterable<? extends E>, F extends BreakableFunction1<? super E>> void forEach(
      final F function, final T source)
  {
    @SuppressWarnings("unchecked")
    final BreakableForEachInIterable<E, T, F> forEachInIterable =
      (BreakableForEachInIterable<E, T, F>) BREAKABLE_FOR_EACH_IN_ITERABLE;
    forEachInIterable.forEach(function, source);
  }

  public static <E, F extends VoidFunction1<? super E>> void forEach(final F function, final E[] source)
  {
    @SuppressWarnings("unchecked")
    final ForEachInArray<E, F> forEachInIterable = (ForEachInArray<E, F>) FOR_EACH_IN_ARRAY;
    forEachInIterable.forEach(function, source);
  }

  public static <E, F extends BreakableFunction1<? super E>> void forEach(final F function, final E[] source)
  {
    @SuppressWarnings("unchecked")
    final BreakableForEachInArray<E, F> forEachInIterable = (BreakableForEachInArray<E, F>) BREAKABLE_FOR_EACH_IN_ARRAY;
    forEachInIterable.forEach(function, source);
  }

  public static <E, NE> IterableToArrayListMapper<E, NE> iterableToArrayListMapper()
  {
    @SuppressWarnings("unchecked")
    final IterableToArrayListMapper<E, NE> iterableToArrayListMapper =
      (IterableToArrayListMapper<E, NE>) ITERABLE_TO_ARRAY_LIST_MAPPER;
    return iterableToArrayListMapper;
  }

  public static <E, NE> IterableToArrayListSelectableMapper<E, NE> iterableToArrayListSelectableMapper()
  {
    @SuppressWarnings("unchecked")
    final IterableToArrayListSelectableMapper<E, NE> iterableToArrayListSelectableMapper =
      (IterableToArrayListSelectableMapper<E, NE>) ITERABLE_TO_ARRAY_LIST_SELECTABLE_MAPPER;
    return iterableToArrayListSelectableMapper;
  }

  public static PrefixAndSuffixAdder newPrefixAndSuffixAdder(final String prefix, final String suffix)
  {
    return new PrefixAndSuffixAdder(prefix, suffix);
  }

  public static <T> GenericVarargsSelector<T, Condition1<T>, List<T>> genericVarargsToListSelector()
  {
    @SuppressWarnings("unchecked")
    final GenericVarargsSelector<T, Condition1<T>, List<T>> genericVarargsFilter =
      (GenericVarargsSelector<T, Condition1<T>, List<T>>) GENERIC_VARARGS_SELECTOR;
    return genericVarargsFilter;
  }

}
