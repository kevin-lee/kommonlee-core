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
package org.elixirian.kommonlee.collect.primitive;

import org.elixirian.kommonlee.type.functional.primitive.BreakableByteFunction1;
import org.elixirian.kommonlee.type.functional.primitive.ByteCondition1;
import org.elixirian.kommonlee.type.functional.primitive.ByteToByteFunction1;
import org.elixirian.kommonlee.type.functional.primitive.VoidByteFunction1;

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
 * @version 0.0.1 (2013-07-13)
 */
public interface ByteList extends PrimitiveCollection<Byte>
{
  byte get(int index);

  /**
   * @param element
   * @param fromIndex
   *          toIndex (inclusive)
   * @return
   */
  int indexOf(byte element, int fromIndex);

  int indexOf(byte element);

  /**
   * @param element
   * @param toIndex
   *          toIndex (exclusive) so calling {@link #lastIndexOf(Object, int)} with the length of this list is the same
   *          as {@link #lastIndexOf(Object)}
   * 
   *          <pre>
   *          e.g.)
   *          length = 5
   *          lastIndexOf(someObject, 5) == lastIndexOf(someObject)
   * </pre>
   * @return
   */
  int lastIndexOf(byte element, int toIndex);

  int lastIndexOf(byte element);

  boolean exists(ByteCondition1 seeker);

  boolean contains(byte element);

  boolean containsAll(byte[] elements);

  boolean containsAll(ByteList elements);

  ByteList select(ByteCondition1 condition);

  // <R> ReadableList<R> map(ByteFunction1<R> function);

  ByteList mapToByte(ByteToByteFunction1 function);

  // <R> ReadableList<R> mapSelectively(ByteCondition1 condition, ByteFunction1<R> function);

  ByteList mapToByteSelectively(ByteCondition1 condition, ByteToByteFunction1 function);

  void forEach(VoidByteFunction1 function);
  
  void forEach(ByteToByteFunction1 function);

  void breakableForEach(BreakableByteFunction1 function);

  int howMany(ByteCondition1 conditionToMeet);

  ByteList subList(int fromIndex, int toIndex);

  byte[] toArray();
}
