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
package org.elixirian.kommonlee.io;

import static org.elixirian.kommonlee.util.Strings.*;
import static org.elixirian.kommonlee.util.collect.Lists.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.elixirian.kommonlee.type.functional.Function1;
import org.elixirian.kommonlee.type.functional.Function2;

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
 * @version 0.0.1 (2010-07-13)
 */
public class DefaultStringConsumingContainer implements StringConsumingContainer
{
  private static final Function1<List<String>, String> ADD_NOTHING_TO_THE_END = new Function1<List<String>, String>() {
    @Override
    public String apply(final List<String> stringList)
    {
      final StringBuilder stringBuilder = new StringBuilder();
      for (final String line : stringList)
      {
        stringBuilder.append(line);
      }
      return stringBuilder.toString();
    }
  };

  private static final Function2<String, List<String>, String> ADD_TO_THE_END =
    new Function2<String, List<String>, String>() {
      @Override
      public String apply(final String addIt, final List<String> stringList)
      {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final String line : stringList)
        {
          stringBuilder.append(line)
              .append(addIt);
        }
        final int length = stringBuilder.length();
        if (0 < length)
        {
          stringBuilder.delete(length - addIt.length(), length);
        }
        return stringBuilder.toString();
      }
    };

  private final List<String> stringList = newArrayList();

  @Override
  public void consume(final String value) throws IOException
  {
    stringList.add(value);
  }

  @Override
  public List<String> getDataList()
  {
    return Collections.unmodifiableList(stringList);
  }

  @Override
  public String toString()
  {
    return ADD_TO_THE_END.apply("\n", stringList);
  }

  @Override
  public String toString(final String withAddingItToTheEndOfEach)
  {
    /* @formatter:off */
    return isNullOrEmptyString(withAddingItToTheEndOfEach) ?
            ADD_NOTHING_TO_THE_END.apply(stringList) :
            ADD_TO_THE_END.apply(withAddingItToTheEndOfEach, stringList);
    /* @formatter:on */
  }
}
