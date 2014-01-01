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
package org.elixirian.kommonlee.io.util;

import static org.elixirian.kommonlee.util.Strings.*;

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
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-01-01)
 */
public final class FileUtil
{
  public FileUtil() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  public static int indexOfExtension(final String filename)
  {
    final int dotIndex = filename.lastIndexOf(".");
    return dotIndex <= 0 || dotIndex == filename.length() - 1 ? -1 : dotIndex + 1;
  }

  public static boolean hasExtension(final String filename)
  {
    final String trimmedFilename = nullSafeTrim(filename);
    final int indexOfExtension = indexOfExtension(trimmedFilename);
    return indexOfExtension > 0;
  }

  public static String findExtension(final String filename)
  {
    final String trimmedFilename = nullSafeTrim(filename);
    final int indexOfExtension = indexOfExtension(trimmedFilename);
    if (indexOfExtension > 0)
    {
      return trimmedFilename.substring(indexOfExtension);
    }
    return "";
  }

  public static String addBeforeExtensionIfExtensionExists(final String filename, final String beforeExtension)
  {
    final String trimmedFilename = nullSafeTrim(filename);
    final int indexOfExtension = indexOfExtension(trimmedFilename);
    if (indexOfExtension > 0)
    {
      final int extensionDotIndex = indexOfExtension - 1;
      return trimmedFilename.substring(0, extensionDotIndex) + beforeExtension
          + trimmedFilename.substring(extensionDotIndex);
    }
    return trimmedFilename;
  }
  
  public static String addBeforeExtensionOrTheEndIfNoExtension(final String filename, final String beforeExtension)
  {
    final String trimmedFilename = nullSafeTrim(filename);
    final int indexOfExtension = indexOfExtension(trimmedFilename);
    if (indexOfExtension > 0)
    {
      final int extensionDotIndex = indexOfExtension - 1;
      return trimmedFilename.substring(0, extensionDotIndex) + beforeExtension
          + trimmedFilename.substring(extensionDotIndex);
    }
    return trimmedFilename + beforeExtension;
  }
}
