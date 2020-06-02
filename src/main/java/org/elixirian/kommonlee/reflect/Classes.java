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
package org.elixirian.kommonlee.reflect;

import static org.elixirian.kommonlee.validation.Assertions.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
 * @version 0.0.1 (2010-09-17)
 */
public final class Classes
{
  /* @formatter:off */
  public static final Class<?>[] EMPTY_CLASS_ARRAY  = new Class<?>[0];
  public static final Object[]   EMPTY_OBJECT_ARRAY = new Object[0];
  /* @formatter:on */

  private Classes() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  private static <T, A extends Annotation> boolean hasAnySuperClassAnnotatedWith0(final Class<?> targetClass,
      final Class<? super T> beforeClass, final Class<? extends A> annotation,
      final Class<? extends A>... remainingAnnotations)
  {
    if (null == targetClass || targetClass.equals(beforeClass) || targetClass.equals(Object.class))
    {
      return false;
    }
    if (targetClass.isAnnotationPresent(annotation))
    {
      return true;
    }
    for (final Class<? extends A> eachAnnotation : remainingAnnotations)
    {
      if (targetClass.isAnnotationPresent(eachAnnotation))
      {
        return true;
      }
    }
    /* @formatter:off */
    return hasAnySuperClassAnnotatedWith0(targetClass
        .getSuperclass(), beforeClass, annotation, remainingAnnotations);
    /* @formatter:on */
  }

  public static <T, A extends Annotation> boolean hasAnySuperClassAnnotatedWith(final Class<T> targetClass,
      final Class<? super T> beforeClass, final boolean includeTargetClass, final Class<? extends A> annotation,
      final Class<? extends A>... remainingAnnotations)
  {
    /* @formatter:off */
    return includeTargetClass ?
        hasAnySuperClassAnnotatedWith0(targetClass,
                                       beforeClass,
                                       annotation, remainingAnnotations) :
        hasAnySuperClassAnnotatedWith0(targetClass
                                          .getSuperclass(),
                                       beforeClass,
                                       annotation, remainingAnnotations);
    /* @formatter:on */
  }

  private static <T> void extractAllClassesInSubToSuperOrder(final Class<?> targetClass,
      final Class<? super T> beforeClass, final Collection<Class<?>> classCollection)
  {
    if (null == targetClass || targetClass.equals(beforeClass) || targetClass.equals(Object.class))
    {
      return;
    }
    classCollection.add(targetClass);
    extractAllClassesInSubToSuperOrder(targetClass.getSuperclass(), beforeClass, classCollection);
  }

  public static <T> void extractSuperClassesInSubToSuperOrder(final Class<T> targetClass,
      final Class<? super T> beforeClass, final boolean includeTargetClass, final Collection<Class<?>> classCollection)
  {
    if (includeTargetClass)
    {
      extractAllClassesInSubToSuperOrder(targetClass, beforeClass, classCollection);
    }
    else
    {
      extractAllClassesInSubToSuperOrder(targetClass.getSuperclass(), beforeClass, classCollection);
    }
  }

  public static <T> List<Class<?>> extractSuperClassesInSubToSuperOrder(final Class<T> targetClass,
      final Class<? super T> beforeClass, final boolean includeTargetClass)
  {
    final List<Class<?>> classList = new ArrayList<Class<?>>();
    extractSuperClassesInSubToSuperOrder(targetClass, beforeClass, includeTargetClass, classList);
    return classList;
  }

  private static <T> void extractAllClassesInSuperToSubOrder(final Class<?> targetClass,
      final Class<? super T> beforeClass, final Collection<Class<?>> classCollection)
  {
    if (null == targetClass || targetClass.equals(beforeClass) || targetClass.equals(Object.class))
    {
      return;
    }
    extractAllClassesInSuperToSubOrder(targetClass.getSuperclass(), beforeClass, classCollection);
    classCollection.add(targetClass);
  }

  public static <T> void extractSuperClassesInSuperToSubOrder(final Class<T> targetClass,
      final Class<? super T> beforeClass, final boolean includeTargetClass, final Collection<Class<?>> classCollection)
  {
    if (includeTargetClass)
    {
      extractAllClassesInSuperToSubOrder(targetClass, beforeClass, classCollection);
    }
    else
    {
      extractAllClassesInSuperToSubOrder(targetClass.getSuperclass(), beforeClass, classCollection);
    }
  }

  public static <T> List<Class<?>> extractSuperClassesInSuperToSubOrder(final Class<T> targetClass,
      final Class<? super T> beforeClass, final boolean includeTargetClass)
  {
    final List<Class<?>> classList = new ArrayList<Class<?>>();
    extractSuperClassesInSuperToSubOrder(targetClass, beforeClass, includeTargetClass, classList);
    return classList;
  }

  private static <T, A extends Annotation> void extractClassesWithAnnotationsInSubToSuperOrder0(
      final Class<?> targetClass, final Class<? super T> beforeClass, final Class<A> annotation,
      final Collection<Class<?>> classCollection)
  {
    if (null == targetClass || targetClass.equals(beforeClass) || targetClass.equals(Object.class))
    {
      return;
    }
    if (targetClass.isAnnotationPresent(annotation))
    {
      classCollection.add(targetClass);
    }
    extractClassesWithAnnotationsInSubToSuperOrder0(targetClass.getSuperclass(), beforeClass, annotation,
        classCollection);
  }

  private static <T, A extends Annotation> void extractClassesWithAnnotationsInSubToSuperOrder0(
      final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
      final Collection<Class<?>> classCollection, final Class<A> annotation)
  {
    assertNotNull(annotation, "The annotation must not be null.");
    if (includeTargetClass)
    {
      extractClassesWithAnnotationsInSubToSuperOrder0(targetClass, beforeClass, annotation, classCollection);
    }
    else
    {
      extractClassesWithAnnotationsInSubToSuperOrder0(targetClass.getSuperclass(), beforeClass, annotation,
          classCollection);
    }
  }

  public static <T, A extends Annotation> void extractClassesWithAnnotationsInSubToSuperOrder(
      final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
      final Collection<Class<?>> classCollection, final Class<A> annotation)
  {
    extractClassesWithAnnotationsInSubToSuperOrder0(targetClass, beforeClass, includeTargetClass, classCollection,
        annotation);
  }

  public static <T, A extends Annotation> List<Class<?>> extractClassesWithAnnotationsInSubToSuperOrder(
      final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
      final Class<A> annotation)
  {
    final List<Class<?>> classList = new ArrayList<Class<?>>();
    extractClassesWithAnnotationsInSubToSuperOrder0(targetClass, beforeClass, includeTargetClass, classList, annotation);
    return classList;
  }

  private static <T, A extends Annotation> void extractClassesWithAnnotationsInSubToSuperOrder0(
      final Class<?> targetClass, final Class<? super T> beforeClass, final Class<? extends A> annotation,
      final Class<? extends A>[] remainingAnnotations, final Collection<Class<?>> classCollection)
  {
    if (null == targetClass || targetClass.equals(beforeClass) || targetClass.equals(Object.class))
    {
      return;
    }
    if (targetClass.isAnnotationPresent(annotation))
    {
      classCollection.add(targetClass);
    }
    else
    {
      for (final Class<? extends A> eachAnnotation : remainingAnnotations)
      {
        if (targetClass.isAnnotationPresent(eachAnnotation))
        {
          classCollection.add(targetClass);
          break;
        }
      }
    }
    extractClassesWithAnnotationsInSubToSuperOrder0(targetClass.getSuperclass(), beforeClass, annotation,
        remainingAnnotations, classCollection);
  }

  private static <T, A extends Annotation> void extractClassesWithAnnotationsInSubToSuperOrder0(
      final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
      final Collection<Class<?>> classCollection, final Class<? extends A> annotation,
      final Class<? extends A>... remainingAnnotations)
  {
    if (includeTargetClass)
    {
      extractClassesWithAnnotationsInSubToSuperOrder0(targetClass, beforeClass, annotation, remainingAnnotations,
          classCollection);
    }
    else
    {
      extractClassesWithAnnotationsInSubToSuperOrder0(targetClass.getSuperclass(), beforeClass, annotation,
          remainingAnnotations, classCollection);
    }
  }

  public static <T, A extends Annotation> void extractClassesWithAnnotationsInSubToSuperOrder(
      final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
      final Collection<Class<?>> classCollection, final Class<? extends A> annotation,
      final Class<? extends A>... remainingAnnotations)
  {
    extractClassesWithAnnotationsInSubToSuperOrder0(targetClass, beforeClass, includeTargetClass, classCollection,
        annotation, remainingAnnotations);
  }

  public static <T, A extends Annotation> List<Class<?>> extractClassesWithAnnotationsInSubToSuperOrder(
      final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
      final Class<? extends A> annotation, final Class<? extends A>... remainingAnnotations)
  {
    final List<Class<?>> classList = new ArrayList<Class<?>>();
    extractClassesWithAnnotationsInSubToSuperOrder0(targetClass, beforeClass, includeTargetClass, classList,
        annotation, remainingAnnotations);
    return classList;
  }

  private static <T, A extends Annotation> void extractClassesWithAnnotationsInSuperToSubOrder0(
      final Class<?> targetClass, final Class<? super T> beforeClass, final Class<A> annotation,
      final Collection<Class<?>> classCollection)
  {
    if (null == targetClass || targetClass.equals(beforeClass) || targetClass.equals(Object.class))
    {
      return;
    }
    extractClassesWithAnnotationsInSuperToSubOrder0(targetClass.getSuperclass(), beforeClass, annotation,
        classCollection);
    if (targetClass.isAnnotationPresent(annotation))
    {
      classCollection.add(targetClass);
    }
  }

  private static <T, A extends Annotation> void extractClassesWithAnnotationsInSuperToSubOrder0(
      final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
      final Collection<Class<?>> classCollection, final Class<A> annotation)
  {
    assertNotNull(annotation, "The annotation must not be null.");
    if (includeTargetClass)
    {
      extractClassesWithAnnotationsInSuperToSubOrder0(targetClass, beforeClass, annotation, classCollection);
    }
    else
    {
      extractClassesWithAnnotationsInSuperToSubOrder0(targetClass.getSuperclass(), beforeClass, annotation,
          classCollection);
    }
  }

  public static <T, A extends Annotation> void extractClassesWithAnnotationsInSuperToSubOrder(
      final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
      final Collection<Class<?>> classCollection, final Class<A> annotation)
  {
    extractClassesWithAnnotationsInSuperToSubOrder0(targetClass, beforeClass, includeTargetClass, classCollection,
        annotation);
  }

  public static <T, A extends Annotation> List<Class<?>> extractClassesWithAnnotationsInSuperToSubOrder(
      final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
      final Class<A> annotation)
  {
    final List<Class<?>> classList = new ArrayList<Class<?>>();
    extractClassesWithAnnotationsInSuperToSubOrder0(targetClass, beforeClass, includeTargetClass, classList, annotation);
    return classList;
  }

  private static <T, A extends Annotation> void extractClassesWithAnnotationsInSuperToSubOrder0(
      final Class<?> targetClass, final Class<? super T> beforeClass, final Class<? extends A> annotation,
      final Class<? extends A>[] remainingAnnotations, final Collection<Class<?>> classCollection)
  {
    if (null == targetClass || targetClass.equals(beforeClass) || targetClass.equals(Object.class))
    {
      return;
    }
    extractClassesWithAnnotationsInSuperToSubOrder0(targetClass.getSuperclass(), beforeClass, annotation,
        remainingAnnotations, classCollection);
    if (targetClass.isAnnotationPresent(annotation))
    {
      classCollection.add(targetClass);
    }
    else
    {
      for (final Class<? extends A> eachAnnotation : remainingAnnotations)
      {
        if (targetClass.isAnnotationPresent(eachAnnotation))
        {
          classCollection.add(targetClass);
          break;
        }
      }
    }
  }

  private static <T, A extends Annotation> void extractClassesWithAnnotationsInSuperToSubOrder0(
      final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
      final Collection<Class<?>> classCollection, final Class<? extends A> annotation,
      final Class<? extends A>... remainingAnnotations)
  {
    if (includeTargetClass)
    {
      extractClassesWithAnnotationsInSuperToSubOrder0(targetClass, beforeClass, annotation, remainingAnnotations,
          classCollection);
    }
    else
    {
      extractClassesWithAnnotationsInSuperToSubOrder0(targetClass.getSuperclass(), beforeClass, annotation,
          remainingAnnotations, classCollection);
    }
  }

  public static <T, A extends Annotation> void extractClassesWithAnnotationsInSuperToSubOrder(
      final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
      final Collection<Class<?>> classCollection, final Class<? extends A> annotation,
      final Class<? extends A>... remainingAnnotations)
  {
    extractClassesWithAnnotationsInSuperToSubOrder0(targetClass, beforeClass, includeTargetClass, classCollection,
        annotation, remainingAnnotations);
  }

  public static <T, A extends Annotation> List<Class<?>> extractClassesWithAnnotationsInSuperToSubOrder(
      final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
      final Class<? extends A> annotation, final Class<? extends A>... remainingAnnotations)
  {
    final List<Class<?>> classList = new ArrayList<Class<?>>();
    extractClassesWithAnnotationsInSuperToSubOrder0(targetClass, beforeClass, includeTargetClass, classList,
        annotation, remainingAnnotations);
    return classList;
  }

  /**
   * @param <T>
   * @param targetClass
   * @param parameterTypes
   * @return
   */
  public static <T> Constructor<T> findConstructor(final Class<T> targetClass, final Class<?>... parameterTypes)
  {
    try
    {
      return targetClass.getDeclaredConstructor(parameterTypes);
    }
    catch (final SecurityException e)
    {
      /* Only for debugging */
      // final StringBuildWriter stringBuildWriter = new StringBuildWriter();
      // e.printStackTrace(new PrintWriter(stringBuildWriter));
      // System.out.println(stringBuildWriter.toString());
    }
    catch (final NoSuchMethodException e)
    {
      /* Only for debugging */
      // final StringBuildWriter stringBuildWriter = new StringBuildWriter();
      // e.printStackTrace(new PrintWriter(stringBuildWriter));
      // System.out.println(stringBuildWriter.toString());
    }
    return null;
  }

  private static <T, A extends Annotation> Constructor<T> findConstructorWithAnnotation0(final Class<T> targetClass,
      final Class<A> annotation)
  {
    assertNotNull(annotation, "The annotation must not be null.");
    for (final Constructor<?> constructor : targetClass.getDeclaredConstructors())
    {
      if (constructor.isAnnotationPresent(annotation))
      {
        @SuppressWarnings("unchecked")
        final Constructor<T> constructorOfT = (Constructor<T>) constructor;
        return constructorOfT;
      }
    }
    return null;
  }

  public static <T, A extends Annotation> Constructor<T> findConstructorWithAnnotation(final Class<T> targetClass,
      final Class<A> annotation)
  {
    return findConstructorWithAnnotation0(targetClass, annotation);
  }

  private static <T, A extends Annotation> Constructor<T> findConstructorWithAnnotation0(final Class<T> targetClass,
      final Class<? extends A> annotation, final Class<? extends A>... remainingAnnotations)
  {
    for (final Constructor<?> constructor : targetClass.getDeclaredConstructors())
    {
      if (constructor.isAnnotationPresent(annotation))
      {
        @SuppressWarnings("unchecked")
        final Constructor<T> constructorOfT = (Constructor<T>) constructor;
        return constructorOfT;
      }
      for (final Class<? extends A> eachAnnotation : remainingAnnotations)
      {
        if (constructor.isAnnotationPresent(eachAnnotation))
        {
          @SuppressWarnings("unchecked")
          final Constructor<T> constructorOfT = (Constructor<T>) constructor;
          return constructorOfT;
        }
      }
    }
    return null;
  }

  public static <T, A extends Annotation> Constructor<T> findConstructorWithAnnotation(final Class<T> targetClass,
      final Class<? extends A> annotation, final Class<? extends A>... remainingAnnotations)
  {
    return findConstructorWithAnnotation0(targetClass, annotation, remainingAnnotations);
  }

  private static <T, A extends Annotation> Set<Constructor<T>> findAllConstructorsWithAnnotation0(
      final Class<T> targetClass, final Class<A> annotation)
  {
    assertNotNull(annotation, "The annotation must not be null.");
    final Set<Constructor<T>> constructorSet = new HashSet<Constructor<T>>();

    for (final Constructor<?> constructor : targetClass.getDeclaredConstructors())
    {
      if (constructor.isAnnotationPresent(annotation))
      {
        @SuppressWarnings("unchecked")
        final Constructor<T> constructorOfT = (Constructor<T>) constructor;
        constructorSet.add(constructorOfT);
      }
    }
    return constructorSet;
  }

  public static <T, A extends Annotation> Set<Constructor<T>> findAllConstructorsWithAnnotation(
      final Class<T> targetClass, final Class<A> annotation)
  {
    return findAllConstructorsWithAnnotation0(targetClass, annotation);
  }

  private static <T, A extends Annotation> Set<Constructor<T>> findAllConstructorsWithAnnotation0(
      final Class<T> targetClass, final Class<? extends A> annotation, final Class<? extends A>... remainingAnnotations)
  {
    final Set<Constructor<T>> constructorSet = new HashSet<Constructor<T>>();

    for (final Constructor<?> constructor : targetClass.getDeclaredConstructors())
    {
      if (constructor.isAnnotationPresent(annotation))
      {
        @SuppressWarnings("unchecked")
        final Constructor<T> constructorOfT = (Constructor<T>) constructor;
        constructorSet.add(constructorOfT);
        continue;
      }
      for (final Class<? extends A> eachAnnotation : remainingAnnotations)
      {
        if (constructor.isAnnotationPresent(eachAnnotation))
        {
          @SuppressWarnings("unchecked")
          final Constructor<T> constructorOfT = (Constructor<T>) constructor;
          constructorSet.add(constructorOfT);
          break;
        }
      }
    }
    return constructorSet;
  }

  public static <T, A extends Annotation> Set<Constructor<T>> findAllConstructorsWithAnnotation(
      final Class<T> targetClass, final Class<? extends A> annotation, final Class<? extends A>... remainingAnnotations)
  {
    return findAllConstructorsWithAnnotation0(targetClass, annotation, remainingAnnotations);
  }

  public static Class<?>[] classArrayOf()
  {
    return EMPTY_CLASS_ARRAY;
  }

  public static Class<?>[] classArrayOf(final Class<?>... classes)
  {
    return classes;
  }

  public static Object[] objectArrayOf()
  {
    return EMPTY_OBJECT_ARRAY;
  }

  public static Object[] objectArrayOf(final Object... objects)
  {
    return objects;
  }
}
