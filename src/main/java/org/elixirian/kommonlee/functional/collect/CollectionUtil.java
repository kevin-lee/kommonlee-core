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

  private static final ArrayToArrayListSelector<?> ARRAY_TO_ARRAY_LIST_SELECTOR =
    new ArrayToArrayListSelector<Object>();

  public static <E> ArrayToArrayListSelector<E> arrayToArrayListSelector()
  {
    @SuppressWarnings("unchecked")
    final ArrayToArrayListSelector<E> arrayToArrayListSelector =
      (ArrayToArrayListSelector<E>) ARRAY_TO_ARRAY_LIST_SELECTOR;
    return arrayToArrayListSelector;
  }

  private static final ArrayToHashSetSelector<?> ARRAY_TO_HASH_SET_SELECTOR = new ArrayToHashSetSelector<Object>();

  public static <E> ArrayToHashSetSelector<E> arrayToHashSetSelector()
  {
    @SuppressWarnings("unchecked")
    final ArrayToHashSetSelector<E> arrayToHashSetSelector = (ArrayToHashSetSelector<E>) ARRAY_TO_HASH_SET_SELECTOR;
    return arrayToHashSetSelector;
  }

  private static final ArrayToArraySelector<?, ? extends Condition1<?>> ARRAY_TO_ARRAY_SELECTOR =
    new ArrayToArraySelector<Object, Condition1<Object>>();

  public static <E, C extends Condition1<? super E>> ArrayToArraySelector<E, C> arrayToArraySelector()
  {
    @SuppressWarnings("unchecked")
    final ArrayToArraySelector<E, C> arrayToArraySelector = (ArrayToArraySelector<E, C>) ARRAY_TO_ARRAY_SELECTOR;
    return arrayToArraySelector;
  }

  private final static ElementCountSelector<?> ELEMENT_COUNT_SELECTOR = new ElementCountSelector<Object>();

  public static <E> ElementCountSelector<E> elementCountSelector()
  {
    @SuppressWarnings("unchecked")
    final ElementCountSelector<E> elementCountSelector = (ElementCountSelector<E>) ELEMENT_COUNT_SELECTOR;
    return elementCountSelector;
  }

  private static final IterableToArrayListSelector<Object> ITERABLE_TO_ARRAY_LIST_SELECTOR =
    IterableToArrayListSelector.newInstance(ArrayListCreator.getInstance());

  public static <E> IterableToArrayListSelector<E> iterableToArrayListSelector()
  {
    @SuppressWarnings("unchecked")
    final IterableToArrayListSelector<E> iterableToArrayListSelector =
      (IterableToArrayListSelector<E>) ITERABLE_TO_ARRAY_LIST_SELECTOR;
    return iterableToArrayListSelector;
  }

  private static final IterableToHashSetSelector<Object> ITERABLE_TO_HASH_SET_SELECTOR =
    IterableToHashSetSelector.newInstance(HashSetCreator.getInstance());

  public static <E> IterableToHashSetSelector<E> iterableToHashSetSelector()
  {
    @SuppressWarnings("unchecked")
    final IterableToHashSetSelector<E> iterableToHashSetSelector =
      (IterableToHashSetSelector<E>) ITERABLE_TO_HASH_SET_SELECTOR;
    return iterableToHashSetSelector;
  }

  // public static final IterableToCollectionMapper<Object, ? extends Iterable<Object>, Object, ? extends
  // Function1<Object, Object>, ? extends List<Object>> ITERABLE_TO_COLLECTION_MAPPER =
  // IterableToCollectionMapper.newInstance(ArrayListCreator.getInstance());

  private static final ArrayToArrayMapper<?, ? extends Function1<?, ?>, ?> ARRAY_TO_ARRAY_MAPPER =
    new ArrayToArrayMapper<Object, Function1<Object, Object>, Object>();

  public static <E, F extends Function1<? super E, NE>, NE> ArrayToArrayMapper<E, F, NE> arrayToArrayMapper()
  {
    @SuppressWarnings("unchecked")
    final ArrayToArrayMapper<E, F, NE> arrayToArrayMapper = (ArrayToArrayMapper<E, F, NE>) ARRAY_TO_ARRAY_MAPPER;
    return arrayToArrayMapper;
  }

  private static final ArrayToCollectionMapper<?, ?, ? extends Function1<?, ?>, ? extends ArrayList<?>> ARRAY_TO_ARRAY_LIST_MAPPER =
    ArrayToCollectionMapper.<Object, Object, Function1<Object, Object>, ArrayList<Object>, ArrayListCreator<Object>> newInstance(ArrayListCreator.getInstance());

  public static <E, NE, F extends Function1<? super E, NE>> ArrayToCollectionMapper<E, NE, F, ArrayList<NE>> arrayToArrayListMapper()
  {
    @SuppressWarnings("unchecked")
    final ArrayToCollectionMapper<E, NE, F, ArrayList<NE>> arrayToArrayListMapper =
      (ArrayToCollectionMapper<E, NE, F, ArrayList<NE>>) ARRAY_TO_ARRAY_LIST_MAPPER;
    return arrayToArrayListMapper;
  }

  private static final ArrayToCollectionMapper<?, ?, ? extends Function1<?, ?>, ? extends HashSet<?>> ARRAY_TO_HASH_SET_MAPPER =
    ArrayToCollectionMapper.<Object, Object, Function1<Object, Object>, HashSet<Object>, HashSetCreator<Object>> newInstance(HashSetCreator.getInstance());

  public static <E, NE, F extends Function1<? super E, NE>> ArrayToCollectionMapper<E, NE, F, HashSet<NE>> arrayToHashSetMapper()
  {
    @SuppressWarnings("unchecked")
    final ArrayToCollectionMapper<E, NE, F, HashSet<NE>> arrayToArrayListMapper =
      (ArrayToCollectionMapper<E, NE, F, HashSet<NE>>) ARRAY_TO_HASH_SET_MAPPER;
    return arrayToArrayListMapper;
  }

  private static final ArrayToMapMapper<?, ?, ?, ? extends Function1<?, ? extends Pair<?, ?>>, ? extends HashMap<?, ?>> ARRAY_TO_HASH_MAP_MAPPER =
    ArrayToMapMapper.<Object, Object, Object, Function1<Object, Pair<Object, Object>>, HashMap<Object, Object>, HashMapCreator<Object, Object>> newInstance(HashMapCreator.getInstance());

  public static <E, NK, NE, F extends Function1<? super E, ? extends Pair<NK, NE>>, R extends Map<NK, NE>> ArrayToMapMapper<E, NK, NE, F, R> arrayToMapMapper()
  {
    @SuppressWarnings("unchecked")
    final ArrayToMapMapper<E, NK, NE, F, R> arrayToHashMapMapper =
      (ArrayToMapMapper<E, NK, NE, F, R>) ARRAY_TO_HASH_MAP_MAPPER;
    return arrayToHashMapMapper;
  }

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

  private static final ArrayToHashMapSelectableMapper<?, ?, ?, ? extends Condition1<?>, ? extends Function1<?, ? extends Pair<?, ?>>> ARRAY_TO_HASH_MAP_SELECTABLE_MAPPER =
    ArrayToHashMapSelectableMapper.<Object, Object, Object, Condition1<Object>, Function1<Object, Pair<Object, Object>>> newInstance(HashMapCreator.getInstance());

  private static final IterableToArrayListSelectableMapper<?, ?> ITERABLE_TO_ARRAY_LIST_SELECTABLE_MAPPER =
    IterableToArrayListSelectableMapper.newInstance(ArrayListCreator.getInstance());

  private static final IterableToHashSetSelectableMapper<?, ?> ITERABLE_TO_HASH_SET_SELECTABLE_MAPPER =
    IterableToHashSetSelectableMapper.newInstance(HashSetCreator.getInstance());

  private static final IterableToHashMapSelectableMapper<?, ? extends Iterable<Object>, ? extends Condition1<?>, ?, ?, ? extends Function1<?, ? extends Pair<?, ?>>> ITERABLE_TO_HASH_MAP_SELECTABLE_MAPPER =
    IterableToHashMapSelectableMapper.<Object, Iterable<Object>, Condition1<Object>, Object, Object, Function1<Object, Pair<Object, Object>>> newInstance(HashMapCreator.getInstance());

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

  public enum ArrayToArraySelectorFunction
  {
    INSTANCE;

    public <E, C extends Condition1<? super E>> E[] select(final E[] source, final C condition)
    {
      @SuppressWarnings("unchecked")
      final ArrayToArraySelector<E, C> arrayToArraySelector = (ArrayToArraySelector<E, C>) ARRAY_TO_ARRAY_SELECTOR;
      return arrayToArraySelector.select(source, condition);
    }
  }

  public enum ArrayToArrayListSelectorFunction
  {
    INSTANCE;

    public <E> ArrayList<E> select(final E[] source, final Condition1<? super E> condition)
    {
      final ArrayToArrayListSelector<E> arrayToArrayListSelector = arrayToArrayListSelector();
      return arrayToArrayListSelector.select(source, condition);
    }
  }

  public enum ArrayToHashSelectorFunction
  {
    INSTANCE;

    public <E> HashSet<E> select(final E[] source, final Condition1<? super E> condition)
    {
      final ArrayToHashSetSelector<E> arrayToArrayListSelector = arrayToHashSetSelector();
      return arrayToArrayListSelector.select(source, condition);
    }
  }

  public static class IterableToArrayListSelectorFunction
  {
    static final IterableToArrayListSelectorFunction INSTANCE = new IterableToArrayListSelectorFunction();

    private IterableToArrayListSelectorFunction()
    {
    }

    public <E, T extends Iterable<? extends E>, C extends Condition1<? super E>> ArrayList<E> select(final T source,
        final C condition)
    {
      @SuppressWarnings("unchecked")
      final IterableToArrayListSelector<E> listToArrayListSelector =
        (IterableToArrayListSelector<E>) ITERABLE_TO_ARRAY_LIST_SELECTOR;
      return listToArrayListSelector.select(source, condition);
    }
  }

  public static class IterableToHashSetSelectorFunction
  {
    static final IterableToHashSetSelectorFunction INSTANCE = new IterableToHashSetSelectorFunction();

    private IterableToHashSetSelectorFunction()
    {
    }

    public <E, T extends Iterable<? extends E>, C extends Condition1<? super E>> HashSet<E> select(final T source,
        final C condition)
    {
      @SuppressWarnings("unchecked")
      final IterableToHashSetSelector<E> iterableToHashSetSelector =
        (IterableToHashSetSelector<E>) ITERABLE_TO_HASH_SET_SELECTOR;
      return iterableToHashSetSelector.select(source, condition);
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

    public ArrayToHashSelectorFunction toHashSet()
    {
      return ArrayToHashSelectorFunction.INSTANCE;
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

    public <E, F extends Function1<? super E, NE>, NE> NE[] map(final Class<NE> toArrayOf, final E[] source,
        final F function)
    {
      @SuppressWarnings("unchecked")
      final ArrayToArrayMapper<E, Function1<? super E, NE>, NE> arrayToArrayMapper =
        (ArrayToArrayMapper<E, Function1<? super E, NE>, NE>) ARRAY_TO_ARRAY_MAPPER;
      return arrayToArrayMapper.apply(toArrayOf, source, function);
    }
  }

  public static class ArrayToArrayListMapperFunction
  {
    static final ArrayToArrayListMapperFunction INSTANCE = new ArrayToArrayListMapperFunction();

    private ArrayToArrayListMapperFunction()
    {
    }

    public <E, NE, F extends Function1<? super E, NE>> ArrayList<NE> map(final E[] source, final F function)
    {
      @SuppressWarnings("unchecked")
      final ArrayToCollectionMapper<E, NE, F, ArrayList<NE>> arrayToArrayListMapper =
        (ArrayToCollectionMapper<E, NE, F, ArrayList<NE>>) ARRAY_TO_ARRAY_LIST_MAPPER;
      return arrayToArrayListMapper.apply(source, function);
    }
  }

  public static class ArrayToHashSetMapperFunction
  {
    static final ArrayToHashSetMapperFunction INSTANCE = new ArrayToHashSetMapperFunction();

    private ArrayToHashSetMapperFunction()
    {
    }

    public <E, NE, F extends Function1<? super E, NE>> HashSet<NE> map(final E[] source, final F function)
    {
      @SuppressWarnings("unchecked")
      final ArrayToCollectionMapper<E, NE, F, HashSet<NE>> arrayToHashSetMapper =
        (ArrayToCollectionMapper<E, NE, F, HashSet<NE>>) ARRAY_TO_HASH_SET_MAPPER;
      return arrayToHashSetMapper.apply(source, function);
    }
  }

  public static class ArrayToHashMapMapperFunction
  {
    static final ArrayToHashMapMapperFunction INSTANCE = new ArrayToHashMapMapperFunction();

    private ArrayToHashMapMapperFunction()
    {
    }

    public <E, NE, NK, F extends Function1<? super E, ? extends Pair<NK, NE>>> HashMap<NK, NE> map(final E[] source,
        final F function)
    {
      @SuppressWarnings("unchecked")
      final ArrayToMapMapper<E, NK, NE, F, HashMap<NK, NE>> arrayToHashMapMapper =
        (ArrayToMapMapper<E, NK, NE, F, HashMap<NK, NE>>) ARRAY_TO_HASH_MAP_MAPPER;
      return arrayToHashMapMapper.apply(source, function);
    }
  }

  public static class IterableToArrayListMapperFunction
  {
    static final IterableToArrayListMapperFunction INSTANCE = new IterableToArrayListMapperFunction();

    private IterableToArrayListMapperFunction()
    {
    }

    public <E, T extends Iterable<? extends E>, NE, F extends Function1<? super E, NE>> ArrayList<NE> map(
        final T source, final F function)
    {
      @SuppressWarnings("unchecked")
      final IterableToArrayListMapper<E, NE> iterableToArrayListMapper =
        (IterableToArrayListMapper<E, NE>) ITERABLE_TO_ARRAY_LIST_MAPPER;
      return iterableToArrayListMapper.apply(source, function);
    }
  }

  public static class IterableToHashSetMapperFunction
  {
    static final IterableToHashSetMapperFunction INSTANCE = new IterableToHashSetMapperFunction();

    private IterableToHashSetMapperFunction()
    {
    }

    public <E, T extends Iterable<? extends E>, NE, F extends Function1<? super E, NE>> HashSet<NE> map(final T source,
        final F function)
    {
      @SuppressWarnings("unchecked")
      final IterableToHashSetMapper<E, NE> iterableToHashSetMapper =
        (IterableToHashSetMapper<E, NE>) ITERABLE_TO_HASH_SET_MAPPER;
      return iterableToHashSetMapper.apply(source, function);
    }
  }

  public static class IterableToHashMapMapperFunction
  {
    static final IterableToHashMapMapperFunction INSTANCE = new IterableToHashMapMapperFunction();

    private IterableToHashMapMapperFunction()
    {
    }

    public <E, T extends Iterable<? extends E>, NK, NE, F extends Function1<? super E, ? extends Pair<NK, NE>>> HashMap<NK, NE> map(
        final T source, final F function)
    {
      @SuppressWarnings("unchecked")
      final IterableToMapMapper<E, T, NK, NE, F, HashMap<NK, NE>> iterableToHashMapMapper =
        (IterableToMapMapper<E, T, NK, NE, F, HashMap<NK, NE>>) ITERABLE_TO_HASH_MAP_MAPPER;
      return iterableToHashMapMapper.apply(source, function);
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
      return mapToMapWithNewKeyMapper.apply(inputMap, function);
    }
  }

  public static class MapToHashMapWithNewValueMapperFunction
  {
    static final MapToHashMapWithNewValueMapperFunction INSTANCE = new MapToHashMapWithNewValueMapperFunction();

    private MapToHashMapWithNewValueMapperFunction()
    {
    }

    public <K, E, T extends Map<? extends K, ? extends E>, NE, F extends Function1<? super E, NE>> HashMap<K, NE> map(
        final T inputMap, final F function)
    {
      @SuppressWarnings("unchecked")
      final MapToHashMapWithNewValueMapper<K, E, T, NE, F> mapToHashMapWithNewValueMapper =
        (MapToHashMapWithNewValueMapper<K, E, T, NE, F>) MAP_TO_MAP_WITH_NEW_VALUE_MAPPER;
      return mapToHashMapWithNewValueMapper.apply(inputMap, function);
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
        final T inputMap, final F function)
    {
      @SuppressWarnings("unchecked")
      final MapToHashMapWithNewKeyNewValueMapper<K, E, T, NK, NE, F> mapToHashMapWithNewKeyNewValueMapper =
        (MapToHashMapWithNewKeyNewValueMapper<K, E, T, NK, NE, F>) MAP_TO_MAP_WITH_NEW_KEY_NEW_VALUE_MAPPER;
      return mapToHashMapWithNewKeyNewValueMapper.apply(inputMap, function);
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
        final Class<NE> toArrayOf, final E[] source, final C condition, final F function)
    {
      @SuppressWarnings("unchecked")
      final ArrayToArraySelectableMapper<E, C, NE, F> arrayToArrayListSelectableMapper =
        (ArrayToArraySelectableMapper<E, C, NE, F>) ARRAY_TO_ARRAY_SELECTABLE_MAPPER;
      return arrayToArrayListSelectableMapper.apply(toArrayOf, source, condition, function);
    }
  }

  public static class ArrayToArrayListSelectableMapperFunction
  {
    static final ArrayToArrayListSelectableMapperFunction INSTANCE = new ArrayToArrayListSelectableMapperFunction();

    private ArrayToArrayListSelectableMapperFunction()
    {
    }

    public <E, C extends Condition1<? super E>, NE, F extends Function1<? super E, NE>> ArrayList<NE> mapSelectively(
        final E[] source, final C condition, final F function)
    {
      @SuppressWarnings("unchecked")
      final ArrayToCollectionSelectableMapper<E, C, NE, F, ArrayList<NE>> arrayToArrayListSelectableMapper =
        (ArrayToCollectionSelectableMapper<E, C, NE, F, ArrayList<NE>>) ARRAY_TO_ARRAY_LIST_SELECTABLE_MAPPER;
      return arrayToArrayListSelectableMapper.apply(source, condition, function);
    }
  }

  public static class ArrayToHashSetSelectableMapperFunction
  {
    static final ArrayToHashSetSelectableMapperFunction INSTANCE = new ArrayToHashSetSelectableMapperFunction();

    private ArrayToHashSetSelectableMapperFunction()
    {
    }

    public <E, C extends Condition1<? super E>, NE, F extends Function1<? super E, NE>> HashSet<NE> mapSelectively(
        final E[] source, final C condition, final F function)
    {
      @SuppressWarnings("unchecked")
      final ArrayToCollectionSelectableMapper<E, C, NE, F, HashSet<NE>> arrayToHashSetSelectableMapper =
        (ArrayToCollectionSelectableMapper<E, C, NE, F, HashSet<NE>>) ARRAY_TO_HASH_SET_SELECTABLE_MAPPER;
      return arrayToHashSetSelectableMapper.apply(source, condition, function);
    }
  }

  public static class ArrayToHashMapSelectableMapperFunction
  {
    static final ArrayToHashMapSelectableMapperFunction INSTANCE = new ArrayToHashMapSelectableMapperFunction();

    private ArrayToHashMapSelectableMapperFunction()
    {
    }

    public <E, C extends Condition1<? super E>, NK, NE, F extends Function1<? super E, ? extends Pair<NK, NE>>> HashMap<NK, NE> mapSelectively(
        final E[] source, final C condition, final F function)
    {
      @SuppressWarnings("unchecked")
      final ArrayToHashMapSelectableMapper<E, NK, NE, C, F> arrayToHashSetSelectableMapper =
        (ArrayToHashMapSelectableMapper<E, NK, NE, C, F>) ARRAY_TO_HASH_MAP_SELECTABLE_MAPPER;
      return arrayToHashSetSelectableMapper.apply(source, condition, function);
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

    public ArrayToHashMapSelectableMapperFunction toHashMap()
    {
      return ArrayToHashMapSelectableMapperFunction.INSTANCE;
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
        final T source, final C condition, final F function)
    {
      @SuppressWarnings("unchecked")
      final IterableToArrayListSelectableMapper<E, NE> iterableToArrayListMapper =
        (IterableToArrayListSelectableMapper<E, NE>) ITERABLE_TO_ARRAY_LIST_SELECTABLE_MAPPER;
      return iterableToArrayListMapper.apply(source, condition, function);
    }
  }

  public static class IterableToHashSetSelectableMapperFunction
  {
    static final IterableToHashSetSelectableMapperFunction INSTANCE = new IterableToHashSetSelectableMapperFunction();

    private IterableToHashSetSelectableMapperFunction()
    {
    }

    public <E, T extends Iterable<? extends E>, C extends Condition1<? super E>, NE, F extends Function1<? super E, NE>> HashSet<NE> mapSelectively(
        final T source, final C condition, final F function)
    {
      @SuppressWarnings("unchecked")
      final IterableToHashSetSelectableMapper<E, NE> iterableToHashSetSelectableMapper =
        (IterableToHashSetSelectableMapper<E, NE>) ITERABLE_TO_HASH_SET_SELECTABLE_MAPPER;
      return iterableToHashSetSelectableMapper.apply(source, condition, function);
    }
  }

  public static class IterableToHashMapSelectableMapperFunction
  {
    static final IterableToHashMapSelectableMapperFunction INSTANCE = new IterableToHashMapSelectableMapperFunction();

    private IterableToHashMapSelectableMapperFunction()
    {
    }

    public <E, T extends Iterable<? extends E>, C extends Condition1<? super E>, NK, NE, F extends Function1<? super E, ? extends Pair<NK, NE>>> HashMap<NK, NE> mapSelectively(
        final T source, final C condition, final F function)
    {
      @SuppressWarnings("unchecked")
      final IterableToHashMapSelectableMapper<E, T, C, NK, NE, F> iterableToHashMapSelectableMapper =
        (IterableToHashMapSelectableMapper<E, T, C, NK, NE, F>) ITERABLE_TO_HASH_MAP_SELECTABLE_MAPPER;
      return iterableToHashMapSelectableMapper.apply(source, condition, function);
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

    public IterableToHashMapSelectableMapperFunction toHashMap()
    {
      return IterableToHashMapSelectableMapperFunction.INSTANCE;
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
      final T source, final C condition)
  {
    @SuppressWarnings("unchecked")
    final IterableToArrayListSelector<E> listToArrayListSelector =
      (IterableToArrayListSelector<E>) ITERABLE_TO_ARRAY_LIST_SELECTOR;
    return listToArrayListSelector.select(source, condition);
  }

  public static <E, T extends List<? extends E>, C extends Condition1<? super E>> ArrayList<E> select(final T source,
      final C condition)
  {
    @SuppressWarnings("unchecked")
    final IterableToArrayListSelector<E> listToArrayListSelector =
      (IterableToArrayListSelector<E>) ITERABLE_TO_ARRAY_LIST_SELECTOR;
    return listToArrayListSelector.select(source, condition);
  }

  public static <E, T extends Set<? extends E>, C extends Condition1<? super E>> HashSet<E> select(final T source,
      final C condition)
  {
    @SuppressWarnings("unchecked")
    final IterableToHashSetSelector<E> listToArrayListSelector =
      (IterableToHashSetSelector<E>) ITERABLE_TO_HASH_SET_SELECTOR;
    return listToArrayListSelector.select(source, condition);
  }

  public static <E> List<E> select(final E[] source, final Condition1<? super E> condition)
  {
    @SuppressWarnings("unchecked")
    final ArrayToArrayListSelector<E> arrayToListSelector = (ArrayToArrayListSelector<E>) ARRAY_TO_ARRAY_LIST_SELECTOR;
    return arrayToListSelector.select(source, condition);
  }

  public static <K, E, T extends Map<? extends K, ? extends E>, NK, F extends Function1<? super K, NK>> HashMap<NK, E> map(
      final T source, final F function)
  {
    @SuppressWarnings("unchecked")
    final MapToHashMapWithNewKeyMapper<K, E, T, NK, F> mapToMapWithNewKeyMapper =
      (MapToHashMapWithNewKeyMapper<K, E, T, NK, F>) MAP_TO_MAP_WITH_NEW_KEY_MAPPER;
    return mapToMapWithNewKeyMapper.apply(source, function);
  }

  public static <E, T extends Iterable<? extends E>, F extends VoidFunction1<? super E>> void forEach(final T source,
      final F function)
  {
    @SuppressWarnings("unchecked")
    final ForEachInIterable<E, T, F> forEachInIterable = (ForEachInIterable<E, T, F>) FOR_EACH_IN_ITERABLE;
    forEachInIterable.forEach(source, function);
  }

  public static <E, T extends Iterable<? extends E>, F extends BreakableFunction1<? super E>> void forEach(
      final T source, final F function)
  {
    @SuppressWarnings("unchecked")
    final BreakableForEachInIterable<E, T, F> forEachInIterable =
      (BreakableForEachInIterable<E, T, F>) BREAKABLE_FOR_EACH_IN_ITERABLE;
    forEachInIterable.forEach(source, function);
  }

  public static <E, F extends VoidFunction1<? super E>> void forEach(final E[] source, final F function)
  {
    @SuppressWarnings("unchecked")
    final ForEachInArray<E, F> forEachInIterable = (ForEachInArray<E, F>) FOR_EACH_IN_ARRAY;
    forEachInIterable.forEach(source, function);
  }

  public static <E, F extends BreakableFunction1<? super E>> void forEach(final E[] source, final F function)
  {
    @SuppressWarnings("unchecked")
    final BreakableForEachInArray<E, F> forEachInIterable = (BreakableForEachInArray<E, F>) BREAKABLE_FOR_EACH_IN_ARRAY;
    forEachInIterable.forEach(source, function);
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

  public static <T> GenericVarargsSelector<T, Condition1<T>, List<T>> genericVarargsToListSelector()
  {
    @SuppressWarnings("unchecked")
    final GenericVarargsSelector<T, Condition1<T>, List<T>> genericVarargsFilter =
      (GenericVarargsSelector<T, Condition1<T>, List<T>>) GENERIC_VARARGS_SELECTOR;
    return genericVarargsFilter;
  }

}
