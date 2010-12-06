/**
 * 
 */
package org.elixirian.common.reflect;

import static org.elixirian.common.validation.Assertions.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-09-17)
 */
public final class Classes
{
	private Classes()
	{
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

	public static <T> void extractSuperClassesInSubToSuperOrder(Class<T> targetClass, Class<? super T> beforeClass,
			boolean includeTargetClass, Collection<Class<?>> classCollection)
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
			final Class<? super T> beforeClass, final boolean includeTargetClass,
			final Collection<Class<?>> classCollection)
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

	private static <T, A extends Annotation> void extractClssesWithAnnotationsInSubToSuperOrder0(
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
		extractClssesWithAnnotationsInSubToSuperOrder0(targetClass.getSuperclass(), beforeClass, annotation,
				classCollection);
	}

	private static <T, A extends Annotation> void extractClssesWithAnnotationsInSubToSuperOrder0(
			final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
			final Collection<Class<?>> classCollection, final Class<A> annotation)
	{
		assertNotNull(annotation, "The annotation must not be null.");
		if (includeTargetClass)
		{
			extractClssesWithAnnotationsInSubToSuperOrder0(targetClass, beforeClass, annotation, classCollection);
		}
		else
		{
			extractClssesWithAnnotationsInSubToSuperOrder0(targetClass.getSuperclass(), beforeClass, annotation,
					classCollection);
		}
	}

	public static <T, A extends Annotation> void extractClssesWithAnnotationsInSubToSuperOrder(
			final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
			final Collection<Class<?>> classCollection, final Class<A> annotation)
	{
		extractClssesWithAnnotationsInSubToSuperOrder0(targetClass, beforeClass, includeTargetClass, classCollection,
				annotation);
	}

	public static <T, A extends Annotation> List<Class<?>> extractClssesWithAnnotationsInSubToSuperOrder(
			final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
			final Class<A> annotation)
	{
		final List<Class<?>> classList = new ArrayList<Class<?>>();
		extractClssesWithAnnotationsInSubToSuperOrder0(targetClass, beforeClass, includeTargetClass, classList,
				annotation);
		return classList;
	}

	private static <T, A extends Annotation> void extractClssesWithAnnotationsInSubToSuperOrder0(
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
		extractClssesWithAnnotationsInSubToSuperOrder0(targetClass.getSuperclass(), beforeClass, annotation,
				remainingAnnotations, classCollection);
	}

	private static <T, A extends Annotation> void extractClssesWithAnnotationsInSubToSuperOrder0(
			final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
			final Collection<Class<?>> classCollection, final Class<? extends A> annotation,
			final Class<? extends A>... remainingAnnotations)
	{
		if (includeTargetClass)
		{
			extractClssesWithAnnotationsInSubToSuperOrder0(targetClass, beforeClass, annotation, remainingAnnotations,
					classCollection);
		}
		else
		{
			extractClssesWithAnnotationsInSubToSuperOrder0(targetClass.getSuperclass(), beforeClass, annotation,
					remainingAnnotations, classCollection);
		}
	}

	public static <T, A extends Annotation> void extractClssesWithAnnotationsInSubToSuperOrder(
			final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
			final Collection<Class<?>> classCollection, final Class<? extends A> annotation,
			final Class<? extends A>... remainingAnnotations)
	{
		extractClssesWithAnnotationsInSubToSuperOrder0(targetClass, beforeClass, includeTargetClass, classCollection,
				annotation, remainingAnnotations);
	}

	public static <T, A extends Annotation> List<Class<?>> extractClssesWithAnnotationsInSubToSuperOrder(
			final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
			final Class<? extends A> annotation, final Class<? extends A>... remainingAnnotations)
	{
		final List<Class<?>> classList = new ArrayList<Class<?>>();
		extractClssesWithAnnotationsInSubToSuperOrder0(targetClass, beforeClass, includeTargetClass, classList,
				annotation, remainingAnnotations);
		return classList;
	}

	private static <T, A extends Annotation> void extractClssesWithAnnotationsInSuperToSubOrder0(
			final Class<?> targetClass, final Class<? super T> beforeClass, final Class<A> annotation,
			final Collection<Class<?>> classCollection)
	{
		if (null == targetClass || targetClass.equals(beforeClass) || targetClass.equals(Object.class))
		{
			return;
		}
		extractClssesWithAnnotationsInSuperToSubOrder0(targetClass.getSuperclass(), beforeClass, annotation,
				classCollection);
		if (targetClass.isAnnotationPresent(annotation))
		{
			classCollection.add(targetClass);
		}
	}

	private static <T, A extends Annotation> void extractClssesWithAnnotationsInSuperToSubOrder0(
			final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
			final Collection<Class<?>> classCollection, final Class<A> annotation)
	{
		assertNotNull(annotation, "The annotation must not be null.");
		if (includeTargetClass)
		{
			extractClssesWithAnnotationsInSuperToSubOrder0(targetClass, beforeClass, annotation, classCollection);
		}
		else
		{
			extractClssesWithAnnotationsInSuperToSubOrder0(targetClass.getSuperclass(), beforeClass, annotation,
					classCollection);
		}
	}

	public static <T, A extends Annotation> void extractClssesWithAnnotationsInSuperToSubOrder(
			final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
			final Collection<Class<?>> classCollection, final Class<A> annotation)
	{
		extractClssesWithAnnotationsInSuperToSubOrder0(targetClass, beforeClass, includeTargetClass, classCollection,
				annotation);
	}

	public static <T, A extends Annotation> List<Class<?>> extractClssesWithAnnotationsInSuperToSubOrder(
			final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
			final Class<A> annotation)
	{
		final List<Class<?>> classList = new ArrayList<Class<?>>();
		extractClssesWithAnnotationsInSuperToSubOrder0(targetClass, beforeClass, includeTargetClass, classList,
				annotation);
		return classList;
	}

	private static <T, A extends Annotation> void extractClssesWithAnnotationsInSuperToSubOrder0(
			final Class<?> targetClass, final Class<? super T> beforeClass, final Class<? extends A> annotation,
			final Class<? extends A>[] remainingAnnotations, final Collection<Class<?>> classCollection)
	{
		if (null == targetClass || targetClass.equals(beforeClass) || targetClass.equals(Object.class))
		{
			return;
		}
		extractClssesWithAnnotationsInSuperToSubOrder0(targetClass.getSuperclass(), beforeClass, annotation,
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

	private static <T, A extends Annotation> void extractClssesWithAnnotationsInSuperToSubOrder0(
			final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
			final Collection<Class<?>> classCollection, final Class<? extends A> annotation,
			final Class<? extends A>... remainingAnnotations)
	{
		if (includeTargetClass)
		{
			extractClssesWithAnnotationsInSuperToSubOrder0(targetClass, beforeClass, annotation, remainingAnnotations,
					classCollection);
		}
		else
		{
			extractClssesWithAnnotationsInSuperToSubOrder0(targetClass.getSuperclass(), beforeClass, annotation,
					remainingAnnotations, classCollection);
		}
	}

	public static <T, A extends Annotation> void extractClssesWithAnnotationsInSuperToSubOrder(
			final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
			final Collection<Class<?>> classCollection, final Class<? extends A> annotation,
			final Class<? extends A>... remainingAnnotations)
	{
		extractClssesWithAnnotationsInSuperToSubOrder0(targetClass, beforeClass, includeTargetClass, classCollection,
				annotation, remainingAnnotations);
	}

	public static <T, A extends Annotation> List<Class<?>> extractClssesWithAnnotationsInSuperToSubOrder(
			final Class<T> targetClass, final Class<? super T> beforeClass, final boolean includeTargetClass,
			final Class<? extends A> annotation, final Class<? extends A>... remainingAnnotations)
	{
		final List<Class<?>> classList = new ArrayList<Class<?>>();
		extractClssesWithAnnotationsInSuperToSubOrder0(targetClass, beforeClass, includeTargetClass, classList,
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
		catch (SecurityException e)
		{
			/* Only for debugging */
			// final StringBuildWriter stringBuildWriter = new StringBuildWriter();
			// e.printStackTrace(new PrintWriter(stringBuildWriter));
			// System.out.println(stringBuildWriter.toString());
		}
		catch (NoSuchMethodException e)
		{
			/* Only for debugging */
			// final StringBuildWriter stringBuildWriter = new StringBuildWriter();
			// e.printStackTrace(new PrintWriter(stringBuildWriter));
			// System.out.println(stringBuildWriter.toString());
		}
		return null;
	}
}
