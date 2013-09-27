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
package org.elixirian.kommonlee.util.string;

import static org.elixirian.kommonlee.util.Strings.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * @version 0.0.1 (2012-04-12)
 */
public final class RegularExpressionUtil
{
  private RegularExpressionUtil() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  public static String nullSafeReplaceAll(final String text, final Pattern pattern, final String replacement)
  {
    final String textToUse = nullThenEmpty(text);
    if (textToUse.isEmpty())
    {
      return "";
    }
    /* @formatter:off */
    return pattern.matcher(text)
        .replaceAll(replacement);
    /* @formatter:on */
  }

  /**
   * Returns the index within the given string of the first occurrence of the specified pattern. If no matching pattern
   * is found, then {@code -1} is returned.
   * 
   * @param text
   *          the given String
   * @param pattern
   *          the pattern to search for
   * @return the index of the first occurrence of the specified pattern, or {@code -1} if there is no such occurrence.
   */
  public static int indexOf(final String text, final Pattern pattern)
  {
    final Matcher matcher = pattern.matcher(text);
    if (matcher.find())
    {
      return matcher.start();
    }
    return -1;
  }

  /**
   * Returns the index within the given string of the first occurrence of the specified pattern, starting at the
   * specified index.
   * 
   * @param text
   *          the given String
   * @param pattern
   *          the pattern to search for
   * @param startIndex
   *          the index from which to start the search.
   * @return the index of the first occurrence of the specified pattern, starting at the specified index, or {@code -1}
   *         if there is no such occurrence.
   */
  public static int indexOf(final String text, final Pattern pattern, final int startIndex)
  {
    if (null == text)
    {
      return -1;
    }

    if (text.length() <= startIndex)
    {
      return -1;
    }

    final Matcher matcher = pattern.matcher(text.substring(0 > startIndex ? 0 : startIndex));
    if (matcher.find())
    {
      return matcher.start() + startIndex;
    }
    return -1;
  }
}
