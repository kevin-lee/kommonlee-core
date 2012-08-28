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
package org.elixirian.kommonlee.validation;

import static org.elixirian.kommonlee.util.MessageFormatter.*;

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
