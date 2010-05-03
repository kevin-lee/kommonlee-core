package com.elixirian.common.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * This class is created based on <a
 * href="http://download.java.net/jdk7/docs/api/java/util/Objects.html">http://download.java.net/jdk7/docs/api/java/util/Objects.html</a>
 * and <a
 * href="http://blogs.sun.com/darcy/entry/java_util_objects_and_friends">http://blogs.sun.com/darcy/entry/java_util_objects_and_friends</a>
 * It is supposed to be replaced by {@code Objects} class in JDK 7 when it is released.
 * <p>
 * This class consists of {@code static} utility methods for operating on objects. These utilities include {@code null}-safe or <span>
 * {@code null}-tolerant</span> methods for computing the hash code of an object, returning a string for an object, and comparing two
 * objects.
 * </p>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-04-06)
 */
public final class Objects
{
	private Objects()
	{
		throw new IllegalStateException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
	}

	/**
	 * Returns {@code true} if the arguments are equal to each other and {@code false} otherwise. Consequently, if both arguments are
	 * {@code null}, {@code true} is returned and if exactly one argument is {@code null}, {@code false} is returned. Otherwise, equality is
	 * determined by using the {@link Object#equals(Object) equals} method of the first argument.
	 * 
	 * @param a
	 *            an object
	 * @param b
	 *            an object to be compared with a for equality
	 * @return true if the arguments are equal to each other and false otherwise
	 * @see {@link Object#equals(Object)}
	 */
	public static boolean equals(final Object a, final Object b)
	{
		return (a == b) || (null != a && a.equals(b));
	}

	/**
	 * Returns {@code true} if the arguments are deeply equal to each other and {@code false} otherwise. Two {@code null} values are deeply
	 * equal. If both arguments are arrays, the algorithm in {@link Arrays#deepEquals(Object[], Object[]) Arrays.deepEquals} is used to
	 * determine equality. Otherwise, equality is determined by using the {@link Object#equals(Object) equals} method of the first argument.
	 * 
	 * @param a
	 *            an object
	 * @param b
	 *            an object to be compared with a for deep equality
	 * @return {@code true} if the arguments are deeply equal to each other and {@code false} otherwise
	 * @see {@link Arrays#deepEquals(Object[], Object[])}, {@link #equals(Object, Object)}
	 */
	public static boolean deepEquals(final Object a, final Object b)
	{
		return (a == b) || ((null != a && null != b) && ((a.getClass()
				.isArray() && b.getClass()
				.isArray()) ? Arrays.deepEquals((Object[]) a, (Object[]) b) : a.equals(b)));
	}

	/**
	 * Returns the hash code of a non-{@code null} argument and 0 for a {@code null} argument.
	 * 
	 * @param o
	 *            an object
	 * @return the hash code of a non-{@code null} argument and 0 for a {@code null} argument
	 * @see {@link Object#hashCode()}
	 */
	public static int hashCode(final Object o)
	{
		return (null == o ? 0 : o.hashCode());
	}

	/**
	 * <p>
	 * Generates a hash code for a sequence of input values. The hash code is generated as if all the input values were placed into an
	 * array, and that array were hashed by calling {@link Arrays#hashCode(Object[])}.
	 * </p>
	 * <p>
	 * This method is useful for implementing {@link Object#hashCode()} on objects containing multiple fields. For example, if an object
	 * that has three fields, {@code x}, {@code y}, and {@code z}, one could write:
	 * </p>
	 * 
	 * <pre>
	 * &#064;Override
	 * public int hashCode()
	 * {
	 * 	return Objects.hash(x, y, z);
	 * }
	 * </pre>
	 * <p>
	 * <strong>Warning: When a single object reference is supplied, the returned value does not equal the hash code of that object
	 * reference.</strong> This value can be computed by calling {@link #hashCode(Object)}.
	 * </p>
	 * 
	 * @param values
	 *            the values to be hashed
	 * @return a hash value of the sequence of input values
	 * @see {@link Arrays#hashCode(Object[])}, {@link List#hashCode()}
	 */
	public static int hash(final Object... values)
	{
		return Arrays.hashCode(values);
	}

	/**
	 * Returns the result of calling {@code toString} for a non-{@code null} argument and "{@code null}" for a {@code null} argument.
	 * 
	 * @param o
	 *            an object
	 * @return the result of calling {@code toString} for a non-{@code null} argument and "{@code null}" for a {@code null} argument
	 * @see {@link Object#toString()}, {@link String#valueOf(Object)}
	 */
	public static String toString(final Object o)
	{
		return (null == o ? "null" : o.toString());
	}

	/**
	 * Returns the result of calling {@code toString} on the first argument if the first argument is not {@code null} and returns the second
	 * argument otherwise.
	 * 
	 * @param o
	 *            an object
	 * @param nullDefault
	 *            string to return if the first argument is {@code null}
	 * @return the result of calling {@code toString} on the first argument if it is not {@code null} and the second argument otherwise.
	 * @see {@link #toString(Object)}
	 */
	public static String toString(final Object o, final String nullDefault)
	{
		return (null == o ? nullDefault : o.toString());
	}

	/**
	 * <p>
	 * Returns 0 if the arguments are identical and {@code c.compare(a, b)} otherwise. Consequently, if both arguments are {@code null} 0 is
	 * returned.
	 * </p>
	 * <p>
	 * Note that if one of the arguments is {@code null}, a {@code NullPointerException} may or may not be thrown depending on what ordering
	 * policy, if any, the {@link Comparator} chooses to have for {@code null} values.
	 * </p>
	 * 
	 * @param <T>
	 *            the type of the objects being compared
	 * @param a
	 *            an object
	 * @param b
	 *            an object to be compared with a
	 * @param c
	 *            the {@code Comparator} to compare the first two arguments
	 * @return 0 if the arguments are identical and {@code c.compare(a, b)} otherwise.
	 * @see {@link Comparable}, {@link Comparator}
	 */
	public static <T> int compare(final T a, final T b, final Comparator<? super T> c)
	{
		return (a == b ? 0 : c.compare(a, b));
	}

	/**
	 * Checks that the specified object reference is not {@code null}. This method is designed primarily for doing parameter validation in
	 * methods and constructors, as demonstrated below:
	 * 
	 * <pre>
	 * public Foo(Bar bar)
	 * {
	 * 	this.bar = Objects.nonNull(bar);
	 * }
	 * </pre>
	 * 
	 * @param <T>
	 *            the type of the reference
	 * @param obj
	 *            the object reference to check for nullity
	 * @return obj if not {@code null}
	 * @throws NullPointerException
	 *             if {@code obj} is {@code null}
	 */
	public static <T> T nonNull(final T obj)
	{
		if (null == obj)
		{
			throw new NullPointerException();
		}
		return obj;
	}

	/**
	 * Checks that the specified object reference is not {@code null} and throws a customized {@link NullPointerException} if it is. This
	 * method is designed primarily for doing parameter validation in methods and constructors with multiple parameters, as demonstrated
	 * below:
	 * 
	 * <pre>
	 * public Foo(Bar bar, Baz baz)
	 * {
	 * 	this.bar = Objects.nonNull(bar, &quot;bar must not be null&quot;);
	 * 	this.baz = Objects.nonNull(baz, &quot;baz must not be null&quot;);
	 * }
	 * </pre>
	 * 
	 * @param <T>
	 *            the type of the reference
	 * @param obj
	 *            the object reference to check for nullity
	 * @param message
	 *            detail message to be used in the event that a {@code NullPointerException} is thrown
	 * @return {@code obj} if not {@code null}
	 * @throws NullPointerException
	 *             if obj is null
	 */
	public static <T> T nonNull(final T obj, final String message)
	{
		if (null == obj)
		{
			throw new NullPointerException(message);
		}
		return obj;
	}
}