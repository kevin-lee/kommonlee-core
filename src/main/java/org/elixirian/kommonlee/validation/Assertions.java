/**
 * 
 */
package org.elixirian.kommonlee.validation;

import static org.elixirian.kommonlee.util.MessageFormatter.*;

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
 * @version 0.0.1 (2009-11-28)
 * @version 0.0.2 (2010-07-03)
 *          <p>
 *          The class name is changed from AssertIt to Assertions. The names of its methods are also changed from isXxx
 *          to assertXxx.
 *          </p>
 *          <p>
 *          {@link #assertEmpty(String, String, Object...)} always returns an empty String ("") if the given String
 *          value is either <code>null</code> or empty.
 *          </p>
 */
public final class Assertions
{
  private Assertions()
  {
  }

  /**
   * Returns the given object if it is NOT <code>null</code>. Otherwise it throws IllegalArgumentException with the
   * given message.
   * 
   * @param <T>
   *          the given object type.
   * @param object
   *          the given object.
   * @param message
   *          the message to be used when throwing IllegalArgumentException.
   * @param args
   *          the argument objects for the message.
   * @return the given object if it is NOT <code>null</code>. Otherwise it throws IllegalArgumentException with the
   *         given message.
   * @throws IllegalArgumentException
   *           If the given object parameter contains <code>null</code> reference.
   */
  public static <T> T assertNotNull(final T object, final String message, final Object... args)
      throws IllegalArgumentException
  {
    if (null == object)
    {
      throw new IllegalArgumentException(format(message, args));
    }
    return object;
  }

  /**
   * Returns the given object if the object parameter contains <code>null</code> which means it returns
   * <code>null</code>. Otherwise it throws IllegalArgumentException with the given message.
   * 
   * @param <T>
   *          the given object type.
   * @param object
   *          the given object.
   * @param message
   *          the message to be used when throwing IllegalArgumentException.
   * @param args
   *          the argument objects for the message.
   * @return <code>null</code> if the given object parameter contains <code>null</code> reference. Otherwise it throws
   *         IllegalArgumentException with the given message.
   * @throws IllegalArgumentException
   *           If the given object parameter does not contain <code>null</code> reference which means it actually has
   *           some reference other than <code>null</code> reference.
   */
  public static <T> T assertNull(final T object, final String message, final Object... args)
      throws IllegalArgumentException
  {
    if (null != object)
    {
      throw new IllegalArgumentException(format(message, args));
    }
    return object;
  }

  /**
   * Returns the given String value if it is neither <code>null</code> nor empty (String object the length of which is
   * 0). Otherwise it throws IllegalArgumentException with the given message.
   * 
   * @param value
   *          the given String value.
   * @param message
   *          the message to be used when throwing IllegalArgumentException.
   * @param args
   *          the argument objects for the message.
   * @return the given String value if it is neither <code>null</code> nor empty (String object the length of which is
   *         0). Otherwise it throws IllegalArgumentException with the given message.
   * @throws IllegalArgumentException
   *           if the given String value parameter contains <code>null</code> reference or an empty String.
   */
  public static String assertNotEmpty(final String value, final String message, final Object... args)
      throws IllegalArgumentException
  {
    if (null == value || 0 == value.length())
    {
      throw new IllegalArgumentException(format(message, args));
    }
    return value;
  }

  /**
   * Returns an empty String ("") if the given String value is either <code>null</code> or empty (String object the
   * length of which is 0). Otherwise it throws IllegalArgumentException with the given message.
   * 
   * @param value
   *          the given String value.
   * @param message
   *          the message to be used when throwing IllegalArgumentException.
   * @param args
   *          the argument objects for the message.
   * @return an empty String ("") if the given String value is either <code>null</code> or empty (String object the
   *         length of which is 0). Otherwise it throws IllegalArgumentException with the given message.
   * @throws IllegalArgumentException
   *           If the given String value neither <code>null</code> nor empty which means it has some String value the
   *           length of which is greater than 0.
   */
  public static String assertEmpty(final String value, final String message, final Object... args)
      throws IllegalArgumentException
  {
    if (null != value && 0 != value.length())
    {
      throw new IllegalArgumentException(format(message, args));
    }
    return "";
  }

  /**
   * Asserts that the given expression is true. Otherwise it throws IllegalArgumentException with the given message.
   * 
   * @param expression
   *          the given boolean expression.
   * @param message
   *          the message to be used when throwing IllegalArgumentException.
   * @param args
   *          the argument objects for the message.
   * @throws IllegalArgumentException
   *           If the given expression is false.
   */
  public static void assertTrue(final boolean expression, final String message, final Object... args)
      throws IllegalArgumentException
  {
    if (!expression)
    {
      throw new IllegalArgumentException(format(message, args));
    }
  }

  /**
   * Asserts that the given expression is false. Otherwise it throws IllegalArgumentException with the given message.
   * 
   * @param expression
   *          the given boolean expression.
   * @param message
   *          the message to be used when throwing IllegalArgumentException.
   * @param args
   *          the argument objects for the message.
   * @throws IllegalArgumentException
   *           If the given expression is true.
   */
  public static void assertFalse(final boolean expression, final String message, final Object... args)
      throws IllegalArgumentException
  {
    if (expression)
    {
      throw new IllegalArgumentException(format(message, args));
    }
  }
}
