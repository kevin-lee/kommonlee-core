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
package org.elixirian.kommonlee.type;

import static org.elixirian.kommonlee.util.Objects.*;

import org.elixirian.kommonlee.validation.Assertions;

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
 * @version 0.0.1 (2014-04-19)
 */
public class Options
{
  enum None implements Option<Object>
  {
    INSTANCE;

    @Override
    public boolean isNull()
    {
      return true;
    }

    @Override
    public Object get()
    {
      return null;
    }

    @Override
    public String toString()
    {
      return "None()";
    }
  }

  static class Some<T> implements Option<T>
  {
    private final T t;

    Some(final T t)
    {
      Assertions.assertNotNull(t,
          "Some type Option cannot contain null. Please use the Options.option() method to create an instance of Option type.");
      this.t = t;
    }

    @Override
    public boolean isNull()
    {
      return false;
    }

    @Override
    public T get()
    {
      return t;
    }

    @Override
    public int hashCode()
    {
      return hash(t);
    }

    @Override
    public boolean equals(final Object option)
    {
      if (this == option)
      {
        return true;
      }
      final Some<?> that = castIfInstanceOf(Some.class, option);
      /* @formatter:off */
      return that != null &&
              equal(this.t, that.get());
      /* @formatter:on */
    }

    @Override
    public String toString()
    {
      return "Some(" + t + ")";
    }
  }

  public static <T> Option<T> option(final T t)
  {
    @SuppressWarnings("unchecked")
    final Option<T> option = (Option<T>) (t == null ? None.INSTANCE : new Some<T>(t));
    return option;
  }
}
