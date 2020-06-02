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

import static org.elixirian.kommonlee.test.CommonTestHelper.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.elixirian.kommonlee.test.CommonTestHelper.Accessibility;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
 * @version 0.0.1 (2010-11-09)
 */
public class PrimitivesTest
{

  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
  }

  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test(expected = IllegalAccessException.class)
  public final void testPrimitives() throws Exception
  {
    testNotAccessibleConstructor(Primitives.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
  }

  @Test
  public final void testGetPrimitiveDefaultValueObject()
  {
    assertThat(Primitives.getPrimitiveDefaultValueObject(byte.class), is(equalTo(Byte.valueOf((byte) 0))));
    assertThat(Primitives.getPrimitiveDefaultValueObject(short.class), is(equalTo(Short.valueOf((short) 0))));
    assertThat(Primitives.getPrimitiveDefaultValueObject(int.class), is(equalTo(Integer.valueOf(0))));
    assertThat(Primitives.getPrimitiveDefaultValueObject(long.class), is(equalTo(Long.valueOf(0L))));
    assertThat(Primitives.getPrimitiveDefaultValueObject(float.class), is(equalTo(Float.valueOf(0.0f))));
    assertThat(Primitives.getPrimitiveDefaultValueObject(double.class), is(equalTo(Double.valueOf(0.0d))));
    assertThat(Primitives.getPrimitiveDefaultValueObject(char.class), is(equalTo(Character.valueOf('\u0000'))));
    assertThat(Primitives.getPrimitiveDefaultValueObject(boolean.class), is(equalTo(Boolean.FALSE)));
  }

  @Test(expected = IllegalArgumentException.class)
  public final void testGetPrimitiveDefaultValueObjectWithNonPrimitive()
  {
    Primitives.getPrimitiveDefaultValueObject(Object.class);
  }

  @Test
  public final void testGetBoxedPrimitiveOf()
  {
    assertThat(Primitives.getBoxedPrimitiveOf(null), is(nullValue()));
    assertThat(Primitives.getBoxedPrimitiveOf(Object.class), is(nullValue()));

    assertThat(Primitives.getBoxedPrimitiveOf(byte.class), is(equalTo(Byte.class)));
    assertThat(Primitives.getBoxedPrimitiveOf(Byte.class), is(equalTo(Byte.class)));

    assertThat(Primitives.getBoxedPrimitiveOf(short.class), is(equalTo(Short.class)));
    assertThat(Primitives.getBoxedPrimitiveOf(Short.class), is(equalTo(Short.class)));

    assertThat(Primitives.getBoxedPrimitiveOf(int.class), is(equalTo(Integer.class)));
    assertThat(Primitives.getBoxedPrimitiveOf(Integer.class), is(equalTo(Integer.class)));

    assertThat(Primitives.getBoxedPrimitiveOf(long.class), is(equalTo(Long.class)));
    assertThat(Primitives.getBoxedPrimitiveOf(Long.class), is(equalTo(Long.class)));

    assertThat(Primitives.getBoxedPrimitiveOf(float.class), is(equalTo(Float.class)));
    assertThat(Primitives.getBoxedPrimitiveOf(Float.class), is(equalTo(Float.class)));

    assertThat(Primitives.getBoxedPrimitiveOf(double.class), is(equalTo(Double.class)));
    assertThat(Primitives.getBoxedPrimitiveOf(Double.class), is(equalTo(Double.class)));

    assertThat(Primitives.getBoxedPrimitiveOf(char.class), is(equalTo(Character.class)));
    assertThat(Primitives.getBoxedPrimitiveOf(Character.class), is(equalTo(Character.class)));

    assertThat(Primitives.getBoxedPrimitiveOf(boolean.class), is(equalTo(Boolean.class)));
    assertThat(Primitives.getBoxedPrimitiveOf(Boolean.class), is(equalTo(Boolean.class)));
  }

  @Test
  public final void testGetPrimitiveOf()
  {
    assertThat(Primitives.getPrimitiveOf(null), is(nullValue()));
    assertThat(Primitives.getPrimitiveOf(Object.class), is(nullValue()));

    assertThat(Primitives.getPrimitiveOf(byte.class), is(equalTo(byte.class)));
    assertThat(Primitives.getPrimitiveOf(Byte.class), is(equalTo(byte.class)));

    assertThat(Primitives.getPrimitiveOf(short.class), is(equalTo(short.class)));
    assertThat(Primitives.getPrimitiveOf(Short.class), is(equalTo(short.class)));

    assertThat(Primitives.getPrimitiveOf(int.class), is(equalTo(int.class)));
    assertThat(Primitives.getPrimitiveOf(Integer.class), is(equalTo(int.class)));

    assertThat(Primitives.getPrimitiveOf(long.class), is(equalTo(long.class)));
    assertThat(Primitives.getPrimitiveOf(Long.class), is(equalTo(long.class)));

    assertThat(Primitives.getPrimitiveOf(float.class), is(equalTo(float.class)));
    assertThat(Primitives.getPrimitiveOf(Float.class), is(equalTo(float.class)));

    assertThat(Primitives.getPrimitiveOf(double.class), is(equalTo(double.class)));
    assertThat(Primitives.getPrimitiveOf(Double.class), is(equalTo(double.class)));

    assertThat(Primitives.getPrimitiveOf(char.class), is(equalTo(char.class)));
    assertThat(Primitives.getPrimitiveOf(Character.class), is(equalTo(char.class)));

    assertThat(Primitives.getPrimitiveOf(boolean.class), is(equalTo(boolean.class)));
    assertThat(Primitives.getPrimitiveOf(Boolean.class), is(equalTo(boolean.class)));
  }

  @Test
  public final void testIsPrimitive()
  {
    assertFalse(Primitives.isPrimitive(null));
    assertFalse(Primitives.isPrimitive(Object.class));

    assertTrue(Primitives.isPrimitive(byte.class));
    assertFalse(Primitives.isPrimitive(Byte.class));

    assertTrue(Primitives.isPrimitive(short.class));
    assertFalse(Primitives.isPrimitive(Short.class));

    assertTrue(Primitives.isPrimitive(int.class));
    assertFalse(Primitives.isPrimitive(Integer.class));

    assertTrue(Primitives.isPrimitive(long.class));
    assertFalse(Primitives.isPrimitive(Long.class));

    assertTrue(Primitives.isPrimitive(float.class));
    assertFalse(Primitives.isPrimitive(Float.class));

    assertTrue(Primitives.isPrimitive(double.class));
    assertFalse(Primitives.isPrimitive(Double.class));

    assertTrue(Primitives.isPrimitive(char.class));
    assertFalse(Primitives.isPrimitive(Character.class));

    assertTrue(Primitives.isPrimitive(boolean.class));
    assertFalse(Primitives.isPrimitive(Boolean.class));
  }

  @Test
  public final void testIsBoxedPrimitive()
  {
    assertFalse(Primitives.isBoxedPrimitive(null));
    assertFalse(Primitives.isBoxedPrimitive(Object.class));

    assertFalse(Primitives.isBoxedPrimitive(byte.class));
    assertTrue(Primitives.isBoxedPrimitive(Byte.class));

    assertFalse(Primitives.isBoxedPrimitive(short.class));
    assertTrue(Primitives.isBoxedPrimitive(Short.class));

    assertFalse(Primitives.isBoxedPrimitive(int.class));
    assertTrue(Primitives.isBoxedPrimitive(Integer.class));

    assertFalse(Primitives.isBoxedPrimitive(long.class));
    assertTrue(Primitives.isBoxedPrimitive(Long.class));

    assertFalse(Primitives.isBoxedPrimitive(float.class));
    assertTrue(Primitives.isBoxedPrimitive(Float.class));

    assertFalse(Primitives.isBoxedPrimitive(double.class));
    assertTrue(Primitives.isBoxedPrimitive(Double.class));

    assertFalse(Primitives.isBoxedPrimitive(char.class));
    assertTrue(Primitives.isBoxedPrimitive(Character.class));

    assertFalse(Primitives.isBoxedPrimitive(boolean.class));
    assertTrue(Primitives.isBoxedPrimitive(Boolean.class));
  }

}
