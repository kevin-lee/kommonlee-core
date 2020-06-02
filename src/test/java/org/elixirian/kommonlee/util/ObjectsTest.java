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
package org.elixirian.kommonlee.util;

import static org.assertj.core.api.Assertions.*;
import static org.elixirian.kommonlee.test.CommonTestHelper.*;
import static org.elixirian.kommonlee.util.MessageFormatter.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import org.elixirian.kommonlee.test.CauseCheckableExpectedException;
import org.elixirian.kommonlee.test.CommonTestHelper.Accessibility;
import org.elixirian.kommonlee.type.Suppliable;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-05-03)
 */
public class ObjectsTest
{
  private static class SomeObject
  {
    private final String name;

    public SomeObject(final String name)
    {
      this.name = name;
    }

    @Override
    public boolean equals(final Object someObject)
    {
      if (this == someObject)
      {
        return true;
      }
      if (null == someObject)
      {
        return false;
      }
      if (!(someObject instanceof SomeObject))
      {
        return false;
      }
      final SomeObject that = (SomeObject) someObject;
      return (this.name == that.name || (null != this.name && this.name.equals(that.name)));
    }

    @Override
    public int hashCode()
    {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      return result;
    }

    @Override
    public String toString()
    {
      return "I am " + name + ".";
    }
  }

  @Rule
  public CauseCheckableExpectedException expectedException = CauseCheckableExpectedException.none();

  @Test(expected = IllegalAccessException.class)
  public final void testObjects() throws Exception
  {
    testNotAccessibleConstructor(Objects.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
  }

  @Test
  public final void testEqualByteByte()
  {
    for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE; i < size; i++)
    {
      assertThat(Objects.equal((byte) i, (byte) i)).isTrue();
    }
    for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 1; i < size; i++)
    {
      assertThat(Objects.equal((byte) i, (byte) (i + 1))).isEqualTo(Boolean.FALSE);
    }
  }

  @Test
  public final void testEqualCharChar()
  {
    for (int i = Character.MIN_VALUE, size = Character.MAX_VALUE; i < size; i++)
    {
      assertThat(Objects.equal((char) i, (char) i)).isEqualTo(Boolean.TRUE);
    }
    for (int i = Character.MIN_VALUE, size = Character.MAX_VALUE - 1; i < size; i++)
    {
      assertThat(Objects.equal((char) i, (char) (i + 1))).isEqualTo(Boolean.FALSE);
    }
  }

  @Test
  public final void testEqualShortShort()
  {
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
    {
      assertThat(Objects.equal((short) i, (short) i)).isEqualTo(Boolean.TRUE);
    }
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
    {
      assertThat(Objects.equal((short) i, (short) (i + 1))).isEqualTo(Boolean.FALSE);
    }
  }

  @Test
  public final void testEqualIntInt()
  {
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
    {
      assertThat(Objects.equal(i, i)).isEqualTo(Boolean.TRUE);
    }
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
    {
      assertThat(Objects.equal(i, i + 1)).isEqualTo(Boolean.FALSE);
    }
  }

  @Test
  public final void testEqualLongLong()
  {
    for (long i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
    {
      assertThat(Objects.equal(i, i)).isEqualTo(Boolean.TRUE);
    }
    for (long i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
    {
      assertThat(Objects.equal(i, i + 1)).isEqualTo(Boolean.FALSE);
    }
  }

  @Test
  public final void testEqualFloatFloat()
  {
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
    {
      assertThat(Objects.equal(i + 0.50F, i + 0.50F)).isEqualTo(Boolean.TRUE);
    }
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
    {
      assertThat(Objects.equal(i + 0.50F, i + 0.50F + 1)).isEqualTo(Boolean.FALSE);
    }
  }

  @Test
  public final void testEqualDoubleDouble()
  {
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
    {
      assertThat(Objects.equal(i + 0.1234D, i + 0.1234D)).isEqualTo(Boolean.TRUE);
    }
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
    {
      assertThat(Objects.equal(i + 0.1234D, i + 0.1234D + 1)).isEqualTo(Boolean.FALSE);
    }
  }

  @Test
  public final void testEqualBooleanBoolean()
  {
    assertThat(Objects.equal(false, false)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.equal(false, true)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(true, false)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(true, true)).isEqualTo(Boolean.TRUE);
  }

  @Test
  public final void testEqualObjectObject()
  {
    assertThat(Objects.equal(null, null)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.equal("", "")).isEqualTo(Boolean.TRUE);
    assertThat(Objects.equal("test", "test")).isEqualTo(Boolean.TRUE);
    assertThat(Objects.equal(new String("1234"), new String("1234"))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.equal(new SomeObject("Kevin"), new SomeObject("Kevin"))).isEqualTo(Boolean.TRUE);

    assertThat(Objects.equal(Boolean.TRUE, Boolean.TRUE)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.equal(new Byte((byte) 11), new Byte((byte) 11))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.equal(new Short((short) 100), new Short((short) 100))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.equal(new Integer(999), new Integer(999))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.equal(new Long(99999), new Long(99999))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.equal(new Character('a'), new Character('a'))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.equal(new Float(1.12F), new Float(1.12F))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.equal(new Double(123.99123D), new Double(123.99123D))).isEqualTo(Boolean.TRUE);

    assertThat(Objects.equal(null, new Object())).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal("", "1")).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal("test1", "test2")).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new String("1234"), new String("4321"))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new SomeObject("Kevin"), new SomeObject("Lee"))).isEqualTo(Boolean.FALSE);

    assertThat(Objects.equal(null, Boolean.TRUE)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(null, new Byte((byte) 11))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(null, new Short((short) 100))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(null, new Integer(999))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(null, new Long(99999))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(null, new Character('a'))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(null, new Float(1.12F))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(null, new Double(123.99123D))).isEqualTo(Boolean.FALSE);

    assertThat(Objects.equal(Boolean.TRUE, null)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new Byte((byte) 11), null)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new Short((short) 100), null)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new Integer(999), null)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new Long(99999), null)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new Character('a'), null)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new Float(1.12F), null)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new Double(123.99123D), null)).isEqualTo(Boolean.FALSE);

    assertThat(Objects.equal(Boolean.TRUE, Boolean.FALSE)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new Byte((byte) 11), new Byte((byte) 5))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new Short((short) 100), new Short((short) 50))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new Integer(999), new Integer(333))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new Long(99999), new Long(33333))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new Character('a'), new Character('z'))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new Float(1.12F), new Float(8.88F))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.equal(new Double(123.99123D), new Double(555.1234D))).isEqualTo(Boolean.FALSE);
  }

  @Test
  public final void testDeepEqual()
  {
    assertThat(Objects.deepEqual(null, null)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.deepEqual("", "")).isEqualTo(Boolean.TRUE);
    assertThat(Objects.deepEqual("test", "test")).isEqualTo(Boolean.TRUE);
    assertThat(Objects.deepEqual(new String("1234"), new String("1234"))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.deepEqual(new SomeObject("Kevin"), new SomeObject("Kevin"))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.deepEqual(new String[] { "Hello", "Kevin", "Lee" }, new String[] { "Hello", "Kevin", "Lee" })).isEqualTo(
        Boolean.TRUE);
    assertThat(
        Objects.deepEqual(new String[][] { new String[] { "Hello", "Kevin", "Lee" },
            new String[] { "Goodbye", "See you", "Later" } }, new String[][] {
            new String[] { "Hello", "Kevin", "Lee" }, new String[] { "Goodbye", "See you", "Later" } })).isEqualTo(
        Boolean.TRUE);
    assertThat(
        Objects.deepEqual(new SomeObject[] { new SomeObject("Kevin"), new SomeObject("Lee"),
            new SomeObject("SeongHyun") }, new SomeObject[] { new SomeObject("Kevin"), new SomeObject("Lee"),
            new SomeObject("SeongHyun") })).isEqualTo(Boolean.TRUE);

    assertThat(Objects.deepEqual(null, new Object())).isEqualTo(Boolean.FALSE);
    assertThat(Objects.deepEqual("", "1")).isEqualTo(Boolean.FALSE);
    assertThat(Objects.deepEqual("test1", "test2")).isEqualTo(Boolean.FALSE);
    assertThat(Objects.deepEqual(new String("1234"), new String("4321"))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.deepEqual(new SomeObject("Kevin"), new SomeObject("Tom"))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.deepEqual(new String[] { "Hello", "Lee", "Kevin" }, new String[] { "Hello", "Kevin", "Lee" })).isEqualTo(
        Boolean.FALSE);
    assertThat(
        Objects.deepEqual(new String[][] { new String[] { "Hello", "Kevin", "Lee" },
            new String[] { "Goodbye", "See you", "Later!" } }, new String[][] {
            new String[] { "Hello", "Kevin", "Lee" }, new String[] { "Goodbye", "See you", "Later" } })).isEqualTo(
        Boolean.FALSE);
    assertThat(
        Objects.deepEqual(new SomeObject[] { new SomeObject("SeongHyun"), new SomeObject("Lee"),
            new SomeObject("Kevin") }, new SomeObject[] { new SomeObject("Kevin"), new SomeObject("Lee"),
            new SomeObject("SeongHyun") })).isEqualTo(Boolean.FALSE);

    assertThat(Objects.deepEqual(new int[] { 1, 2, 3, 4, 5 }, new int[] { 1, 2, 3, 4, 5 })).isEqualTo(Boolean.TRUE);

    assertThat(
        Objects.deepEqual(new boolean[] { true, true, false, true, false }, new boolean[] { true, true, false, true,
            false })).isEqualTo(Boolean.TRUE);

    assertThat(Objects.deepEqual(new int[] { 1, 2, 3, 4, 5 }, new int[] { 1, 2, 3, 4, 6 })).isEqualTo(Boolean.FALSE);

    assertThat(
        Objects.deepEqual(new boolean[] { true, true, false, true, false }, new boolean[] { true, true, false, true,
            true })).isEqualTo(Boolean.FALSE);
  }

  @Test
  public final void testNotEqualByteByte()
  {
    for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE; i < size; i++)
    {
      assertThat(Objects.notEqual((byte) i, (byte) i)).isEqualTo(Boolean.FALSE);
    }
    for (int i = Byte.MIN_VALUE, size = Byte.MAX_VALUE - 1; i < size; i++)
    {
      assertThat(Objects.notEqual((byte) i, (byte) (i + 1))).isEqualTo(Boolean.TRUE);
    }
  }

  @Test
  public final void testNotEqualCharChar()
  {
    for (int i = Character.MIN_VALUE, size = Character.MAX_VALUE; i < size; i++)
    {
      assertThat(Objects.notEqual((char) i, (char) i)).isEqualTo(Boolean.FALSE);
    }
    for (int i = Character.MIN_VALUE, size = Character.MAX_VALUE - 1; i < size; i++)
    {
      assertThat(Objects.notEqual((char) i, (char) (i + 1))).isEqualTo(Boolean.TRUE);
    }
  }

  @Test
  public final void testNotEqualShortShort()
  {
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
    {
      assertThat(Objects.notEqual((short) i, (short) i)).isEqualTo(Boolean.FALSE);
    }
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
    {
      assertThat(Objects.notEqual((short) i, (short) (i + 1))).isEqualTo(Boolean.TRUE);
    }
  }

  @Test
  public final void testNotEqualIntInt()
  {
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
    {
      assertThat(Objects.notEqual(i, i)).isEqualTo(Boolean.FALSE);
    }
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
    {
      assertThat(Objects.notEqual(i, i + 1)).isEqualTo(Boolean.TRUE);
    }
  }

  @Test
  public final void testNotEqualLongLong()
  {
    for (long i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
    {
      assertThat(Objects.notEqual(i, i)).isEqualTo(Boolean.FALSE);
    }
    for (long i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
    {
      assertThat(Objects.notEqual(i, i + 1)).isEqualTo(Boolean.TRUE);
    }
  }

  @Test
  public final void testNotEqualFloatFloat()
  {
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
    {
      assertThat(Objects.notEqual(i + 0.50F, i + 0.50F)).isEqualTo(Boolean.FALSE);
    }
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
    {
      assertThat(Objects.notEqual(i + 0.50F, i + 0.50F + 1)).isEqualTo(Boolean.TRUE);
    }
  }

  @Test
  public final void testNotEqualDoubleDouble()
  {
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE; i < size; i++)
    {
      assertThat(Objects.notEqual(i + 0.1234D, i + 0.1234D)).isEqualTo(Boolean.FALSE);
    }
    for (int i = Short.MIN_VALUE, size = Short.MAX_VALUE - 1; i < size; i++)
    {
      assertThat(Objects.notEqual(i + 0.1234D, i + 0.1234D + 1)).isEqualTo(Boolean.TRUE);
    }
  }

  @Test
  public final void testNotEqualBooleanBoolean()
  {
    assertThat(Objects.notEqual(false, false)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notEqual(false, true)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(true, false)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(true, true)).isEqualTo(Boolean.FALSE);
  }

  @Test
  public final void testNotEqualObjectObject()
  {
    assertThat(Objects.notEqual(null, null)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notEqual("", "")).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notEqual("test", "test")).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notEqual(new String("1234"), new String("1234"))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notEqual(new SomeObject("Kevin"), new SomeObject("Kevin"))).isEqualTo(Boolean.FALSE);

    assertThat(Objects.notEqual(Boolean.TRUE, Boolean.TRUE)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notEqual(new Byte((byte) 11), new Byte((byte) 11))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notEqual(new Short((short) 100), new Short((short) 100))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notEqual(new Integer(999), new Integer(999))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notEqual(new Long(99999), new Long(99999))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notEqual(new Character('a'), new Character('a'))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notEqual(new Float(1.12F), new Float(1.12F))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notEqual(new Double(123.99123D), new Double(123.99123D))).isEqualTo(Boolean.FALSE);

    assertThat(Objects.notEqual(null, new Object())).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual("", "1")).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual("test1", "test2")).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new String("1234"), new String("4321"))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new SomeObject("Kevin"), new SomeObject("Lee"))).isEqualTo(Boolean.TRUE);

    assertThat(Objects.notEqual(null, Boolean.TRUE)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(null, new Byte((byte) 11))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(null, new Short((short) 100))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(null, new Integer(999))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(null, new Long(99999))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(null, new Character('a'))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(null, new Float(1.12F))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(null, new Double(123.99123D))).isEqualTo(Boolean.TRUE);

    assertThat(Objects.notEqual(Boolean.TRUE, null)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new Byte((byte) 11), null)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new Short((short) 100), null)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new Integer(999), null)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new Long(99999), null)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new Character('a'), null)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new Float(1.12F), null)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new Double(123.99123D), null)).isEqualTo(Boolean.TRUE);

    assertThat(Objects.notEqual(Boolean.TRUE, Boolean.FALSE)).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new Byte((byte) 11), new Byte((byte) 5))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new Short((short) 100), new Short((short) 50))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new Integer(999), new Integer(333))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new Long(99999), new Long(33333))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new Character('a'), new Character('z'))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new Float(1.12F), new Float(8.88F))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notEqual(new Double(123.99123D), new Double(555.1234D))).isEqualTo(Boolean.TRUE);
  }

  @Test
  public final void testNotDeepEqual()
  {
    assertThat(Objects.notDeepEqual(null, null)).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notDeepEqual("", "")).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notDeepEqual("test", "test")).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notDeepEqual(new String("1234"), new String("1234"))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notDeepEqual(new SomeObject("Kevin"), new SomeObject("Kevin"))).isEqualTo(Boolean.FALSE);
    assertThat(Objects.notDeepEqual(new String[] { "Hello", "Kevin", "Lee" }, new String[] { "Hello", "Kevin", "Lee" })).isEqualTo(
        Boolean.FALSE);
    assertThat(
        Objects.notDeepEqual(new SomeObject[] { new SomeObject("Kevin"), new SomeObject("Lee"),
            new SomeObject("SeongHyun") }, new SomeObject[] { new SomeObject("Kevin"), new SomeObject("Lee"),
            new SomeObject("SeongHyun") })).isEqualTo(Boolean.FALSE);

    assertThat(Objects.notDeepEqual(null, new Object())).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notDeepEqual("", "1")).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notDeepEqual("test1", "test2")).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notDeepEqual(new String("1234"), new String("4321"))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notDeepEqual(new SomeObject("Kevin"), new SomeObject("Tom"))).isEqualTo(Boolean.TRUE);
    assertThat(Objects.notDeepEqual(new String[] { "Hello", "Lee", "Kevin" }, new String[] { "Hello", "Kevin", "Lee" })).isEqualTo(
        Boolean.TRUE);
    assertThat(
        Objects.notDeepEqual(new SomeObject[] { new SomeObject("SeongHyun"), new SomeObject("Lee"),
            new SomeObject("Kevin") }, new SomeObject[] { new SomeObject("Kevin"), new SomeObject("Lee"),
            new SomeObject("SeongHyun") })).isEqualTo(Boolean.TRUE);

    assertThat(Objects.notDeepEqual(new int[] { 1, 2, 3, 4, 5 }, new int[] { 1, 2, 3, 4, 6 })).isEqualTo(Boolean.TRUE);

    assertThat(
        Objects.notDeepEqual(new boolean[] { true, true, false, true, false }, new boolean[] { true, true, false, true,
            true })).isEqualTo(Boolean.TRUE);

    assertThat(Objects.notDeepEqual(new int[] { 1, 2, 3, 4, 5 }, new int[] { 1, 2, 3, 4, 5 })).isEqualTo(Boolean.FALSE);

    assertThat(
        Objects.notDeepEqual(new boolean[] { true, true, false, true, false }, new boolean[] { true, true, false, true,
            false })).isEqualTo(Boolean.FALSE);
  }

  @Test
  public final void testHashCodeOfObject()
  {
    assertThat(Objects.hashCodeOf((byte) 64)).isEqualTo(64);
    assertThat(Objects.hashCodeOf((short) (Byte.MAX_VALUE + 10))).isEqualTo(Byte.MAX_VALUE + 10);
    assertThat(Objects.hashCodeOf((char) (Character.MAX_VALUE - 100))).isEqualTo(Character.MAX_VALUE - 100);
    assertThat(Objects.hashCodeOf(Short.MAX_VALUE + 1000)).isEqualTo(Short.MAX_VALUE + 1000);
    assertThat(Objects.hashCodeOf(Integer.MAX_VALUE + 1000L)).isEqualTo(
        (int) ((Integer.MAX_VALUE + 1000L) ^ ((Integer.MAX_VALUE + 1000L) >>> 32)));
    assertThat(Objects.hashCodeOf(10.10F)).isEqualTo(Float.floatToIntBits(10.10F));
    final long longBits = Double.doubleToLongBits(Float.MAX_VALUE + 100.1234D);
    assertThat(Objects.hashCodeOf(Float.MAX_VALUE + 100.1234D)).isEqualTo((int) (longBits ^ (longBits >>> 32)));
    assertThat(Objects.hashCodeOf(true)).isEqualTo(Boolean.TRUE.hashCode());
    assertThat(Objects.hashCodeOf(false)).isEqualTo(Boolean.FALSE.hashCode());
    assertThat(Objects.hashCodeOf(null)).isEqualTo(0);
    assertThat(Objects.hashCodeOf("test")).isEqualTo("test".hashCode());
    assertThat(Objects.hashCodeOf(new String("test"))).isEqualTo(new String("test").hashCode());
    assertThat(Objects.hashCodeOf(new SomeObject("Kevin"))).isEqualTo(new SomeObject("Kevin").hashCode());
  }

  @Test
  public final void testHash()
  {
    assertThat(Objects.hash((Object[]) null)).isEqualTo(Arrays.hashCode((Object[]) null));
    assertThat(Objects.hash((Object) null)).isEqualTo(Arrays.hashCode(new Object[] { null }));
    assertThat(Objects.hash("test")).isEqualTo(Arrays.hashCode(new String[] { "test" }));
    assertThat(Objects.hash(new String("test"))).isEqualTo(Arrays.hashCode(new String[] { new String("test") }));
    assertThat(Objects.hash(new SomeObject("Kevin"))).isEqualTo(
        Arrays.hashCode(new SomeObject[] { new SomeObject("Kevin") }));
    assertThat(Objects.hash("test", new String("something else"), new SomeObject("Kevin"))).isEqualTo(
        Arrays.hashCode(new Object[] { "test", new String("something else"), new SomeObject("Kevin") }));
  }

  @SuppressWarnings("boxing")
  @Test
  public final void testHashes()
  {
    int result = Objects.HASH_PRIME * Objects.HASH_SEED + 1;
    result = Objects.HASH_PRIME * result + 120;
    result = Objects.HASH_PRIME * result + 12345;
    result = Objects.HASH_PRIME * result + (int) (9999L ^ (9999L >>> 32));
    result = Objects.HASH_PRIME * result + Float.floatToIntBits(12.10F);
    final long bits = Double.doubleToLongBits(100.12345D);
    result = Objects.HASH_PRIME * result + (int) (bits ^ (bits >>> 32));
    result = Objects.HASH_PRIME * result + 1231;
    result = Objects.HASH_PRIME * result + "Kevin Lee".hashCode();

    /* @formatter:off */
          assertThat(
              Objects.hash(
              (byte) 1,
              (short) 120,
              12345,
              9999L,
              12.10F,
              100.12345D,
              true,
              "Kevin Lee")).isEqualTo(result);
          assertThat(
              Objects.hash(
              (byte) 1,
              (short) 120,
              12345,
              9999L,
              12.10F,
              100.12345D,
              true,
              "Kevin Lee")).isEqualTo(result);
          /* @formatter:on */
  }

  @Test
  public final void testHashByte()
  {
    final byte first = 3;
    final byte value = 5;
    assertThat(Objects.hash(value)).isEqualTo(Objects.HASH_PRIME * Objects.HASH_SEED + value);
    assertThat(Objects.hashBytes(first, value)).isEqualTo(
        Objects.HASH_PRIME * (Objects.HASH_PRIME * Objects.HASH_SEED + first) + value);

    final byte[] values = { value, 6, 8, 10, 123 };
    int result = Objects.HASH_PRIME * Objects.HASH_SEED + values[0];
    result = Objects.HASH_PRIME * result + values[1];
    result = Objects.HASH_PRIME * result + values[2];
    result = Objects.HASH_PRIME * result + values[3];
    result = Objects.HASH_PRIME * result + values[4];
    assertThat(Objects.hashBytes(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hashBytes(first, (byte[]) null)).isEqualTo(Objects.HASH_PRIME * Objects.HASH_SEED + first);
    assertThat(Objects.hash(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hash(values)).isEqualTo(result);
    assertThat(Objects.hash((byte[]) null)).isEqualTo(0);
  }

  @Test
  public final void testHashChar()
  {
    final char first = 3;
    final char value = Character.MAX_VALUE - 100;
    assertThat(Objects.hash(value)).isEqualTo(Objects.HASH_PRIME * Objects.HASH_SEED + value);
    assertThat(Objects.hashChars(first, value)).isEqualTo(
        Objects.HASH_PRIME * (Objects.HASH_PRIME * Objects.HASH_SEED + first) + value);

    final char[] values = { value, 6, 8, 10, 123 };
    int result = Objects.HASH_PRIME * Objects.HASH_SEED + values[0];
    result = Objects.HASH_PRIME * result + values[1];
    result = Objects.HASH_PRIME * result + values[2];
    result = Objects.HASH_PRIME * result + values[3];
    result = Objects.HASH_PRIME * result + values[4];
    assertThat(Objects.hashChars(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hashChars(first, (char[]) null)).isEqualTo(Objects.HASH_PRIME * Objects.HASH_SEED + first);
    assertThat(Objects.hash(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hash(values)).isEqualTo(result);
    assertThat(Objects.hash((char[]) null)).isEqualTo(0);
  }

  @Test
  public final void testHashShort()
  {
    final short first = 3;
    final short value = Byte.MAX_VALUE + 100;
    assertThat(Objects.hash(value)).isEqualTo(Objects.HASH_PRIME * Objects.HASH_SEED + value);
    assertThat(Objects.hashShorts(first, value)).isEqualTo(
        Objects.HASH_PRIME * (Objects.HASH_PRIME * Objects.HASH_SEED + first) + value);

    final short[] values = { value, 6, 8, 10, 123 };
    int result = Objects.HASH_PRIME * Objects.HASH_SEED + values[0];
    result = Objects.HASH_PRIME * result + values[1];
    result = Objects.HASH_PRIME * result + values[2];
    result = Objects.HASH_PRIME * result + values[3];
    result = Objects.HASH_PRIME * result + values[4];
    assertThat(Objects.hashShorts(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hashShorts(first, (short[]) null)).isEqualTo(Objects.HASH_PRIME * Objects.HASH_SEED + first);
    assertThat(Objects.hash(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hash(values)).isEqualTo(result);
    assertThat(Objects.hash((short[]) null)).isEqualTo(0);
  }

  @Test
  public final void testHashInt()
  {
    final int first = 3;
    final int value = Short.MAX_VALUE + 100;
    assertThat(Objects.hash(value)).isEqualTo(Objects.HASH_PRIME * Objects.HASH_SEED + value);
    assertThat(Objects.hashInts(first, value)).isEqualTo(
        Objects.HASH_PRIME * (Objects.HASH_PRIME * Objects.HASH_SEED + first) + value);

    final int[] values = { value, 6, 8, 10, 123 };
    int result = Objects.HASH_PRIME * Objects.HASH_SEED + values[0];
    result = Objects.HASH_PRIME * result + values[1];
    result = Objects.HASH_PRIME * result + values[2];
    result = Objects.HASH_PRIME * result + values[3];
    result = Objects.HASH_PRIME * result + values[4];
    assertThat(Objects.hashInts(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hashInts(first, (int[]) null)).isEqualTo(Objects.HASH_PRIME * Objects.HASH_SEED + first);
    assertThat(Objects.hash(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hash(values)).isEqualTo(result);
    assertThat(Objects.hash((int[]) null)).isEqualTo(0);
  }

  @Test
  public final void testHashLong()
  {
    final long first = 3;
    final long value = Integer.MAX_VALUE + 100L;
    assertThat(Objects.hash(value)).isEqualTo(Objects.HASH_PRIME * Objects.HASH_SEED + (int) (value ^ (value >>> 32)));
    assertThat(Objects.hashLongs(first, value)).isEqualTo(
        Objects.HASH_PRIME * (Objects.HASH_PRIME * Objects.HASH_SEED + (int) (first ^ (first >>> 32)))
            + (int) (value ^ (value >>> 32)));

    final long[] values = { 5, 6, 8, 10, 123 };
    int result = Objects.HASH_PRIME * Objects.HASH_SEED + (int) (values[0] ^ (values[0] >>> 32));
    result = Objects.HASH_PRIME * result + (int) (values[1] ^ (values[1] >>> 32));
    result = Objects.HASH_PRIME * result + (int) (values[2] ^ (values[2] >>> 32));
    result = Objects.HASH_PRIME * result + (int) (values[3] ^ (values[3] >>> 32));
    result = Objects.HASH_PRIME * result + (int) (values[4] ^ (values[4] >>> 32));
    assertThat(Objects.hashLongs(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hashLongs(first, (long[]) null)).isEqualTo(
        Objects.HASH_PRIME * Objects.HASH_SEED + (int) (first ^ (first >>> 32)));
    assertThat(Objects.hash(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hash(values)).isEqualTo(result);
    assertThat(Objects.hash((long[]) null)).isEqualTo(0);
  }

  @Test
  public final void testHashFloat()
  {
    final float first = 3;
    final float value = 5.0F;
    assertThat(Objects.hash(value)).isEqualTo(Objects.HASH_PRIME * Objects.HASH_SEED + Float.floatToIntBits(value));
    assertThat(Objects.hashFloats(first, value)).isEqualTo(
        Objects.HASH_PRIME * (Objects.HASH_PRIME * Objects.HASH_SEED + Float.floatToIntBits(first))
            + Float.floatToIntBits(value));

    final float[] values = { 5, 6, 8, 10, 123 };
    int result = Objects.HASH_PRIME * Objects.HASH_SEED + Float.floatToIntBits(values[0]);
    result = Objects.HASH_PRIME * result + Float.floatToIntBits(values[1]);
    result = Objects.HASH_PRIME * result + Float.floatToIntBits(values[2]);
    result = Objects.HASH_PRIME * result + Float.floatToIntBits(values[3]);
    result = Objects.HASH_PRIME * result + Float.floatToIntBits(values[4]);
    assertThat(Objects.hashFloats(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hashFloats(first, (float[]) null)).isEqualTo(

    Objects.HASH_PRIME * Objects.HASH_SEED + Float.floatToIntBits(first));
    assertThat(Objects.hash(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hash(values)).isEqualTo(result);
    assertThat(Objects.hash((float[]) null)).isEqualTo(0);
  }

  @Test
  public final void testHashDouble()
  {
    final double value = Float.MAX_VALUE + 100D;
    long doubleToLongBits = Double.doubleToLongBits(value);
    final int first = 3;
    assertThat(Objects.hash(value)).isEqualTo(
        Objects.HASH_PRIME * Objects.HASH_SEED + (int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    /* @formatter:off */
    assertThat(
        Objects.hashDoubles(first, value)).isEqualTo(
            Objects.HASH_PRIME *
            (Objects.HASH_PRIME *
                Objects.HASH_SEED +
                (int) (Double.doubleToLongBits(first) ^ (Double.doubleToLongBits(first) >>> 32))
            ) +
            (int) (doubleToLongBits ^ (doubleToLongBits >>> 32))
            );
    /* @formatter:on */

    final double[] values = { value, 6, 8, 10, 123 };
    doubleToLongBits = Double.doubleToLongBits(values[0]);
    int result = Objects.HASH_PRIME * Objects.HASH_SEED + (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    doubleToLongBits = Double.doubleToLongBits(values[1]);
    result = Objects.HASH_PRIME * result + (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    doubleToLongBits = Double.doubleToLongBits(values[2]);
    result = Objects.HASH_PRIME * result + (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    doubleToLongBits = Double.doubleToLongBits(values[3]);
    result = Objects.HASH_PRIME * result + (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    doubleToLongBits = Double.doubleToLongBits(values[4]);
    result = Objects.HASH_PRIME * result + (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    assertThat(Objects.hashDoubles(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hashDoubles(first, (double[]) null)).isEqualTo(
        Objects.HASH_PRIME * Objects.HASH_SEED
            + (int) (Double.doubleToLongBits(first) ^ (Double.doubleToLongBits(first) >>> 32)));
    assertThat(Objects.hash(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hash(values)).isEqualTo(result);
    assertThat(Objects.hash((double[]) null)).isEqualTo(0);
  }

  @Test
  public final void testHashBoolean()
  {
    final boolean first = false;
    assertThat(Objects.hash(true)).isEqualTo(Objects.HASH_PRIME * Objects.HASH_SEED + Boolean.TRUE.hashCode());
    assertThat(Objects.hash(first, true)).isEqualTo(
        Objects.HASH_PRIME * (Objects.HASH_PRIME * Objects.HASH_SEED + ((Boolean) first).hashCode())
            + Boolean.TRUE.hashCode());

    final boolean[] values = { false, true, true, false, true };
    int result = Objects.HASH_PRIME * Objects.HASH_SEED + (values[0] ? 1231 : 1237);
    result = Objects.HASH_PRIME * result + (values[1] ? 1231 : 1237);
    result = Objects.HASH_PRIME * result + (values[2] ? 1231 : 1237);
    result = Objects.HASH_PRIME * result + (values[3] ? 1231 : 1237);
    result = Objects.HASH_PRIME * result + (values[4] ? 1231 : 1237);
    assertThat(Objects.hashBooleans(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hashBooleans(first, (boolean[]) null)).isEqualTo(
        Objects.HASH_PRIME * Objects.HASH_SEED + ((Boolean) first).hashCode());
    assertThat(Objects.hash(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hash(values)).isEqualTo(result);
    assertThat(Objects.hash((boolean[]) null)).isEqualTo(0);
  }

  @Test
  public final void testHashObject()
  {
    final String first = "3";
    final String value = "Kevin";
    assertThat(Objects.hash(value)).isEqualTo(Objects.HASH_PRIME * Objects.HASH_SEED + value.hashCode());
    assertThat(Objects.hash(first, value)).isEqualTo(
        Objects.HASH_PRIME * (Objects.HASH_PRIME * Objects.HASH_SEED + first.hashCode()) + value.hashCode());

    final Object[] values = { value, "Lee", new Object(), new Date(), new ArrayList<String>() };
    int result = Objects.HASH_PRIME * Objects.HASH_SEED + values[0].hashCode();
    result = Objects.HASH_PRIME * result + values[1].hashCode();
    result = Objects.HASH_PRIME * result + values[2].hashCode();
    result = Objects.HASH_PRIME * result + values[3].hashCode();
    result = Objects.HASH_PRIME * result + values[4].hashCode();
    assertThat(Objects.hash(values[0], values[1], values[2], values[3], values[4])).isEqualTo(result);
    assertThat(Objects.hash(first, (Object[]) null)).isEqualTo(
        Objects.HASH_PRIME * Objects.HASH_SEED + first.hashCode());
    assertThat(Objects.hash(values)).isEqualTo(result);
    assertThat(Objects.hash((Object[]) null)).isEqualTo(0);
  }

  @Test
  public final void testToStringOfObject()
  {
    assertThat(Objects.toStringOf(null)).isEqualTo("null");
    assertThat(Objects.toStringOf("test")).isEqualTo("test");
    assertThat(Objects.toStringOf(new String("something else"))).isEqualTo("something else");
    final Object object = new Object();
    assertThat(Objects.toStringOf(object)).isEqualTo(object.toString());
    assertThat(Objects.toStringOf(new SomeObject("Kevin"))).isEqualTo(new SomeObject("Kevin").toString());
    final SomeObject someObject = new SomeObject("Lee");
    assertThat(Objects.toStringOf(someObject)).isEqualTo(someObject.toString());
  }

  @Test
  public final void testToStringOfArray()
  {
    assertThat(Objects.toStringOf(null)).isEqualTo("null");
    assertThat(Objects.toStringOf(new String[] { "test" })).isEqualTo("[test]");
    assertThat(Objects.toStringOf(new String[] { "test", "something else" })).isEqualTo("[test, something else]");
    assertThat(
        Objects.toStringOf(new int[] { -100, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 100 })).isEqualTo(
        "[-100, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 100]");

    final Object[] objects = new Object[] { new Object(), new Object() };
    assertThat(Objects.toStringOf(objects)).isEqualTo("[" + objects[0].toString() + ", " + objects[1].toString() + "]");

    assertThat(Objects.toStringOf(new SomeObject[] { new SomeObject("Kevin"), new SomeObject("Lee") })).isEqualTo(
        "[" + new SomeObject("Kevin").toString() + ", " + new SomeObject("Lee") + "]");

    final SomeObject[] someObjects =
      new SomeObject[] { new SomeObject("Lee"), new SomeObject("Kevin Lee"), new SomeObject("Kevin") };
    assertThat(Objects.toStringOf(someObjects)).isEqualTo(
        "[" + someObjects[0].toString() + ", " + someObjects[1].toString() + ", " + someObjects[2].toString() + "]");
  }

  @Test
  public final void testToStringOfObjectString()
  {
    final String nullDefault = "It is null!!!";
    assertThat(Objects.toStringOf(null, nullDefault)).isEqualTo(nullDefault);
    assertThat(Objects.toStringOf("test", nullDefault)).isEqualTo("test");
    assertThat(Objects.toStringOf(new String("something else"), nullDefault)).isEqualTo("something else");
    final Object object = new Object();
    assertThat(Objects.toStringOf(object, nullDefault)).isEqualTo(object.toString());
    assertThat(Objects.toStringOf(new SomeObject("Kevin"), nullDefault)).isEqualTo(new SomeObject("Kevin").toString());
    final SomeObject someObject = new SomeObject("Lee");
    assertThat(Objects.toStringOf(someObject, nullDefault)).isEqualTo(someObject.toString());
  }

  @Test
  public final void testCompare()
  {
    final Comparator<SomeObject> comparator = new Comparator<SomeObject>() {
      @Override
      public int compare(final SomeObject o1, final SomeObject o2)
      {
        return null == o1 ? -1 : null == o2 ? 1 : compareEach(o1.name, o2.name);
      }

      private int compareEach(final String name1, final String name2)
      {
        if (null == name1)
        {
          return -1;
        }
        for (int i = 0, size = name1.length(); i < size; i++)
        {
          if (i >= name2.length())
          {
            return 1;
          }
          else if (name1.charAt(i) < name2.charAt(i))
          {
            return -1;
          }
          else if (name1.charAt(i) > name2.charAt(i))
          {
            return 1;
          }
        }
        return (name1.length() < name2.length() ? -1 : 0);
      }
    };
    assertThat(Objects.compare(null, null, comparator)).isEqualTo(0);

    SomeObject someObject1 = new SomeObject("Kevin");
    SomeObject someObject2 = new SomeObject("Kevin");
    assertThat(Objects.compare(someObject1, someObject2, comparator)).isEqualTo(
        comparator.compare(someObject1, someObject2));

    someObject1 = new SomeObject("Kevin");
    someObject2 = someObject1;
    assertThat(Objects.compare(someObject1, someObject2, comparator)).isEqualTo(
        comparator.compare(someObject1, someObject2));

    someObject1 = null;
    someObject2 = new SomeObject("Kevin");
    assertThat(Objects.compare(someObject1, someObject2, comparator)).isEqualTo(
        comparator.compare(someObject1, someObject2));

    someObject1 = new SomeObject("Kevin");
    someObject2 = null;
    assertThat(Objects.compare(someObject1, someObject2, comparator)).isEqualTo(
        comparator.compare(someObject1, someObject2));

    someObject1 = new SomeObject("KevinLee");
    someObject2 = new SomeObject("Kevin");
    assertThat(Objects.compare(someObject1, someObject2, comparator)).isEqualTo(
        comparator.compare(someObject1, someObject2));

    someObject1 = new SomeObject("Kevin");
    someObject2 = new SomeObject("Lee");
    assertThat(Objects.compare(someObject1, someObject2, comparator)).isEqualTo(
        comparator.compare(someObject1, someObject2));
  }

  @Test
  public final void testMustNotBeNullT()
  {
    /* when / then */
    assertThat(Objects.mustNotBeNull("test")).isEqualTo("test");
    assertThat(Objects.mustNotBeNull(new String("something else"))).isEqualTo(new String("something else"));
    assertThat(Objects.mustNotBeNull(new SomeObject("Kevin"))).isEqualTo(new SomeObject("Kevin"));

    /* expect */
    expectedException.expect(NullPointerException.class);

    /* when / then the expected exception should be thrown. */
    Objects.mustNotBeNull(null);

    /* otherwise */
    fail(format("The expected exception [%s] is not thrown.", NullPointerException.class));
  }

  @Test
  public final void testMustNotBeNullTString()
  {
    final String message = "It is null!!!!!";
    assertThat(Objects.mustNotBeNull("test", message)).isEqualTo("test");
    assertThat(Objects.mustNotBeNull(new String("something else"), message)).isEqualTo(new String("something else"));
    assertThat(Objects.mustNotBeNull(new SomeObject("Kevin"), message)).isEqualTo(new SomeObject("Kevin"));

    /* expect */
    expectedException.expect(NullPointerException.class)
        .expectMessage(CoreMatchers.equalTo(message));

    /* when / then the expected exception should be thrown. */
    Objects.mustNotBeNull(null, message);

    /* otherwise */
    fail(format("The expected exception [%s] is not thrown.", NullPointerException.class));
  }

  @Test
  public final void testNullThenUse()
  {
    final Object nullReference = null;
    final Object object1 = new Object();
    final Object object2 = new Object();
    final Object value1 = Objects.nullThenUse(nullReference, object1);
    final Object value2 = Objects.nullThenUse(object1, object2);
    assertThat(value1).isEqualTo(object1);
    assertThat(value2).isEqualTo(object1);

    final String string1 = new String("Kevin");
    final String string2 = new String("Lee");
    final String stringValue1 = Objects.nullThenUse((String) null, string1);
    final String stringValue2 = Objects.nullThenUse(string1, string2);
    assertThat(stringValue1).isEqualTo(string1);
    assertThat(stringValue2).isEqualTo(string1);

    final Date date1 = new Date();
    final Date date2 = new Date();
    final Date dateValue1 = Objects.nullThenUse((Date) null, date1);
    final Date dateValue2 = Objects.nullThenUse(date1, date2);
    assertThat(dateValue1).isEqualTo(date1);
    assertThat(dateValue2).isEqualTo(date1);

    final Integer integer1 = new Integer(100);
    final Integer integer2 = new Integer(999);
    final Integer integerValue1 = Objects.nullThenUse((Integer) null, integer1);
    final Integer integerValue2 = Objects.nullThenUse(integer1, integer2);
    assertThat(integerValue1).isEqualTo(integer1);
    assertThat(integerValue2).isEqualTo(integer1);
  }

  @Test
  public final void testNullThenGet()
  {
    final Object nullReference = null;
    final Object object1 = new Object();
    final Object object2 = new Object();
    final Object value1 = Objects.nullThenGet(nullReference, new Suppliable<Object>() {
      @Override
      public Object supply()
      {
        return object1;
      }
    });
    final Object value2 = Objects.nullThenGet(object1, new Suppliable<Object>() {
      @Override
      public Object supply()
      {
        return object2;
      }
    });
    assertThat(value1).isEqualTo(object1);
    assertThat(value2).isEqualTo(object1);

    final String string1 = new String("Kevin");
    final String string2 = new String("Lee");
    final String stringValue1 = Objects.nullThenGet((String) null, new Suppliable<String>() {
      @Override
      public String supply()
      {
        return string1;
      }
    });
    final String stringValue2 = Objects.nullThenGet(string1, new Suppliable<String>() {
      @Override
      public String supply()
      {
        return string2;
      }
    });
    assertThat(stringValue1).isEqualTo(string1);
    assertThat(stringValue2).isEqualTo(string1);

    final Date date1 = new Date();
    final Date date2 = new Date();
    final Date dateValue1 = Objects.nullThenGet((Date) null, new Suppliable<Date>() {
      @Override
      public Date supply()
      {
        return date1;
      }
    });
    final Date dateValue2 = Objects.nullThenGet(date1, new Suppliable<Date>() {
      @Override
      public Date supply()
      {
        return date2;
      }
    });
    assertThat(dateValue1).isEqualTo(date1);
    assertThat(dateValue2).isEqualTo(date1);

    final Integer integer1 = new Integer(100);
    final Integer integer2 = new Integer(999);
    final Integer integerValue1 = Objects.nullThenGet((Integer) null, new Suppliable<Integer>() {
      @Override
      public Integer supply()
      {
        return integer1;
      }
    });
    final Integer integerValue2 = Objects.nullThenGet(integer1, new Suppliable<Integer>() {
      @Override
      public Integer supply()
      {
        return integer2;
      }
    });
    assertThat(integerValue1).isEqualTo(integer1);
    assertThat(integerValue2).isEqualTo(integer1);
  }

  @Test
  public final void testNullThenGetWithNullSuppliable()
  {
    /* given */
    final Object nullReference = null;

    /* expected */
    expectedException.expectMessageContains("defaultValueSupplier must not be null")
        .expect(NullPointerException.class);

    /* when */
    Objects.nullThenGet(nullReference, null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName()
        + " when Suppliable<T> defaultValueSupplier was null.");
  }

  @Test
  public final void testNullThenGetWithNullSuppliable2()
  {
    /* given */
    final Object object1 = new Object();

    /* expected */
    expectedException.expectMessageContains("defaultValueSupplier must not be null")
        .expect(NullPointerException.class);

    /* when */
    Objects.nullThenGet(object1, null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName()
        + " when Suppliable<T> defaultValueSupplier was null.");
  }

  @Test
  public final void testIsNull()
  {
    assertThat(Objects.isNull(null)).isTrue();
    final Object nullObject = null;
    assertThat(Objects.isNull(nullObject)).isTrue();
    assertThat(Objects.isNull("")).isFalse();
    assertThat(Objects.isNull("Kevin")).isFalse();
    assertThat(Objects.isNull(new String("test"))).isFalse();
    assertThat(Objects.isNull(new Object())).isFalse();
  }

  @Test
  public final void testIsNotNull()
  {
    assertThat(Objects.isNotNull(null)).isFalse();
    final Object nullObject = null;
    assertThat(Objects.isNotNull(nullObject)).isFalse();
    assertThat(Objects.isNotNull("")).isTrue();
    assertThat(Objects.isNotNull("Kevin")).isTrue();
    assertThat(Objects.isNotNull(new String("test"))).isTrue();
    assertThat(Objects.isNotNull(new Object())).isTrue();
  }

  @Test
  public final void testIdentical()
  {
    final Object object1 = new Object();
    final Object object2 = object1;
    final Object object3 = new Object();
    assertThat(Objects.identical(object1, object2)).isTrue();
    assertThat(Objects.identical(object1, object3)).isFalse();

    assertThat(Objects.identical("Kevin", "Kevin")).isTrue();
    assertThat(Objects.identical("Kevin", new String("Kevin"))).isFalse();
  }

  @Test
  public final void testNotIdentical()
  {
    final Object object1 = new Object();
    final Object object2 = object1;
    final Object object3 = new Object();
    assertThat(Objects.notIdentical(object1, object2)).isFalse();
    assertThat(Objects.notIdentical(object1, object3)).isTrue();

    assertThat(Objects.notIdentical("Kevin", "Kevin")).isFalse();
    assertThat(Objects.notIdentical("Kevin", new String("Kevin"))).isTrue();
  }

  @Test
  public void testCastIfInstanceOf()
  {
    final Object object1 = "Kevin Lee";
    final String string1 = Objects.castIfInstanceOf(String.class, object1);
    assertThat(string1).isNotNull();
    assertThat(string1).isEqualTo("Kevin Lee");

    final Integer integer = new Integer(1234);
    final Object object2 = new Integer(1234);
    final Integer integer1 = Objects.castIfInstanceOf(Integer.class, object2);
    assertThat(integer1).isNotNull();
    assertThat(integer1).isEqualTo(integer);

    final Number number1 = Objects.castIfInstanceOf(Number.class, object2);
    assertThat(number1).isNotNull();
    assertThat(number1).isEqualTo(integer);

    final Integer integer2 = Objects.castIfInstanceOf(Integer.class, object1);
    assertThat(integer2).isNull();

    final Number number2 = Objects.castIfInstanceOf(Number.class, object1);
    assertThat(number2).isNull();

    final Integer integer3 = Objects.castIfInstanceOf(Integer.class, new Number() {
      /* @formatter:off */
      private static final long serialVersionUID = 1L;
      @Override public long longValue() { return 0; }
      @Override public int intValue() { return 0; }
      @Override public float floatValue() { return 0; }
      @Override public double doubleValue()   { return 0; }
      /* @formatter:on */
    });
    assertThat(integer3).isNull();
  }

  private static class SomePojo
  {
    private final Long id;
    private final String name;
    private final boolean registered;
    private final byte byteValue;
    private final short shortValue;
    private final int intValue;
    private final long longValue;
    private final float floatValue;
    private final double doubleValue;

    public SomePojo(final Long id, final String name, final boolean registered, final byte byteValue,
        final short shortValue, final int intValue, final long longValue, final float floatValue,
        final double doubleValue)
    {
      this.id = id;
      this.name = name;
      this.registered = registered;
      this.byteValue = byteValue;
      this.shortValue = shortValue;
      this.intValue = intValue;
      this.longValue = longValue;
      this.floatValue = floatValue;
      this.doubleValue = doubleValue;
    }

    @Override
    public String toString()
    {
      return Objects.toStringBuilder(this)
          .add("id", id)
          .add("name", name)
          .add("registered", registered)
          .add("byteValue", byteValue)
          .add("shortValue", shortValue)
          .add("intValue", intValue)
          .add("longValue", longValue)
          .add("floatValue", floatValue)
          .add("doubleValue", doubleValue)
          .toString();
    }
  }

  @Test
  public final void testToStringBuilder()
  {
    /* @formatter:off */
    final String expected = "SomePojo{id=100, " +
                             "name=Kevin, " +
                             "registered=true, " +
                             "byteValue=10, " +
                             "shortValue=20, " +
                             "intValue=100, " +
                             "longValue=1000000, " +
                             "floatValue=12.34, " +
                             "doubleValue=56.78}";
    /* @formatter:on */

    final SomePojo somePojo =
      new SomePojo(Long.valueOf(100L), "Kevin", true, (byte) 10, (short) 20, 100, 1000000L, 12.34F, 56.78D);
    assertThat(somePojo.toString()).isEqualTo(expected);

    /* @formatter:off */
    final String expected2 = "ObjectsTest{id=999, " +
                              "name=Kevin Lee, " +
                              "registered=false, " +
                              "byteValue=11, " +
                              "shortValue=123, " +
                              "intValue=123456, " +
                              "longValue=11111111111, " +
                              "floatValue=10.1, " +
                              "doubleValue=12345.12345}";
    /* @formatter:on */

    final Long id = Long.valueOf(999L);
    final String name = "Kevin Lee";
    final boolean registered = false;
    final byte byteValue = 11;
    final short shortValue = 123;
    final int intValue = 123456;
    final long longValue = 11111111111L;
    final float floatValue = 10.10F;
    final double doubleValue = 12345.12345;
    assertThat(Objects.toStringBuilder(this)
        .add("id", id)
        .add("name", name)
        .add("registered", registered)
        .add("byteValue", byteValue)
        .add("shortValue", shortValue)
        .add("intValue", intValue)
        .add("longValue", longValue)
        .add("floatValue", floatValue)
        .add("doubleValue", doubleValue)
        .toString()).isEqualTo(expected2);

    assertThat(Objects.toStringBuilder(this)
        .value("")
        .toString()).isEqualTo(getClass().getSimpleName() + "{}");

    assertThat(Objects.toStringBuilder(this)
        .toString()).isEqualTo(getClass().getSimpleName() + "{}");

    assertThat(Objects.toStringBuilder(this)
        .getFieldSeparator()).isEqualTo(Objects.ToStringBuilder.DEFAULT_FIELD_SEPARATOR);
    assertThat(Objects.toStringBuilder(this)
        .getNameValueSeparator()).isEqualTo(Objects.ToStringBuilder.DEFAULT_NAME_VALUE_SEPARATOR);
  }

  @Test
  public final void testToStringBuilderWithToStringThenAddNewLine()
  {
    /* given */
    /* @formatter:off */
    final String expected = "SomePojo{id=100, " +
        "name=Kevin, " +
        "registered=true, " +
        "byteValue=10, " +
        "shortValue=20, " +
        "intValue=100, " +
        "longValue=1000000, " +
        "floatValue=12.34, " +
        "doubleValue=56.78}" + System.getProperty("line.separator");
    /* @formatter:on */
    System.out.println("expected:");
    System.out.println(expected);

    /* when */
    final String actual =
      Objects.toStringBuilder(
          new SomePojo(Long.valueOf(100L), "Kevin", true, (byte) 10, (short) 20, 100, 1000000L, 12.34F, 56.78D))
          .add("id", 100)
          .add("name", "Kevin")
          .add("registered", true)
          .add("byteValue", 10)
          .add("shortValue", 20)
          .add("intValue", 100)
          .add("longValue", 1000000)
          .add("floatValue", 12.34)
          .add("doubleValue", 56.78)
          .toStringThenAddNewLine();

    System.out.println("actual:");
    System.out.println(actual);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToStringBuilderWithExtraValue()
  {
    /* @formatter:off */
    final String expected2 = "ObjectsTest{Some extra information. blah blah blah, " +
                              "id=999, " +
                              "name=Kevin Lee, " +
                              "registered=false, " +
                              "byteValue=11, " +
                              "shortValue=123, " +
                              "intValue=123456, " +
                              "longValue=11111111111, " +
                              "floatValue=10.1, " +
                              "doubleValue=12345.12345}";
    /* @formatter:on */

    final Long id = Long.valueOf(999L);
    final String name = "Kevin Lee";
    final boolean registered = false;
    final byte byteValue = 11;
    final short shortValue = 123;
    final int intValue = 123456;
    final long longValue = 11111111111L;
    final float floatValue = 10.10F;
    final double doubleValue = 12345.12345;
    assertThat(Objects.toStringBuilder(this)
        .value("Some extra information. blah blah blah")
        .add("id", id)
        .add("name", name)
        .add("registered", registered)
        .add("byteValue", byteValue)
        .add("shortValue", shortValue)
        .add("intValue", intValue)
        .add("longValue", longValue)
        .add("floatValue", floatValue)
        .add("doubleValue", doubleValue)
        .toString()).isEqualTo(expected2);
  }

  @Test
  public final void testToStringBuilderWithExtraValueAndNewLines()
  {
    /* @formatter:off */
    final String expected2 = "Object{Some extra information. blah blah blah, \n" +
                              "id=999, " +
                              "name=Kevin Lee, " +
                              "registered=false, \n" +
                              "byteValue=11, " +
                              "shortValue=123, " +
                              "intValue=123456, " +
                              "longValue=11111111111, " +
                              "floatValue=10.1, " +
                              "doubleValue=12345.12345}";
    /* @formatter:on */

    final Long id = Long.valueOf(999L);
    final String name = "Kevin Lee";
    final boolean registered = false;
    final byte byteValue = 11;
    final short shortValue = 123;
    final int intValue = 123456;
    final long longValue = 11111111111L;
    final float floatValue = 10.10F;
    final double doubleValue = 12345.12345;
    assertThat(Objects.toStringBuilder(new Object())
        .value("Some extra information. blah blah blah")
        .newLine()
        .add("id", id)
        .add("name", name)
        .add("registered", registered)
        .newLine()
        .add("byteValue", byteValue)
        .add("shortValue", shortValue)
        .add("intValue", intValue)
        .add("longValue", longValue)
        .add("floatValue", floatValue)
        .add("doubleValue", doubleValue)
        .toString()).isEqualTo(expected2);
  }

  @Test
  public final void testToStringBuilderWithExtraValuesWithoutSeparatorAndNewLines()
  {
    /* @formatter:off */
    final String expected2 = "ObjectsTest{Some extra information. blah blah blah\n" +
                              "id=999, " +
                              "name=Kevin Lee, " +
                              "registered=false, \n" +
                              "byteValue=11, " +
                              "shortValue=123, " +
                              "intValue=123456, " +
                              "longValue=11111111111, " +
                              "floatValue=10.1, " +
                              "doubleValue=12345.12345}";
    /* @formatter:on */

    final Long id = Long.valueOf(999L);
    final String name = "Kevin Lee";
    final boolean registered = false;
    final byte byteValue = 11;
    final short shortValue = 123;
    final int intValue = 123456;
    final long longValue = 11111111111L;
    final float floatValue = 10.10F;
    final double doubleValue = 12345.12345;
    assertThat(Objects.toStringBuilder(this)
        .valueWithNoSeparator("Some extra information. blah blah blah")
        .newLine()
        .add("id", id)
        .add("name", name)
        .add("registered", registered)
        .newLine()
        .add("byteValue", byteValue)
        .add("shortValue", shortValue)
        .add("intValue", intValue)
        .add("longValue", longValue)
        .add("floatValue", floatValue)
        .add("doubleValue", doubleValue)
        .toString()).isEqualTo(expected2);
  }

  @Test(expected = NullPointerException.class)
  public final void testToStringBuilderWithNullObject()
  {
    Objects.toStringBuilder(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public final void testToStringBuilderWithNullName()
  {
    Objects.toStringBuilder(this)
        .add(null, "Kevin")
        .toString();
  }

  @Test(expected = IllegalArgumentException.class)
  public final void testToStringBuilderWithEmptyName()
  {
    Objects.toStringBuilder(this)
        .add("", "Kevin")
        .toString();
  }

  @Test
  public final void testToStringBuilderObjectStringString()
  {
    /* @formatter:off */
    final String expected = "ObjectsTest{id: 999 | " +
                             "name: Kevin Lee | " +
                             "registered: false | " +
                             "byteValue: 11 | " +
                             "shortValue: 123 | " +
                             "intValue: 123456 | " +
                             "longValue: 11111111111 | " +
                             "floatValue: 10.1 | " +
                             "doubleValue: 12345.12345}";
    /* @formatter:on */

    final Long id = Long.valueOf(999L);
    final String name = "Kevin Lee";
    final boolean registered = false;
    final byte byteValue = 11;
    final short shortValue = 123;
    final int intValue = 123456;
    final long longValue = 11111111111L;
    final float floatValue = 10.10F;
    final double doubleValue = 12345.12345;
    assertThat(Objects.toStringBuilder(this, " | ", ": ")
        .add("id", id)
        .add("name", name)
        .add("registered", registered)
        .add("byteValue", byteValue)
        .add("shortValue", shortValue)
        .add("intValue", intValue)
        .add("longValue", longValue)
        .add("floatValue", floatValue)
        .add("doubleValue", doubleValue)
        .toString()).isEqualTo(expected);
  }

  @Test(expected = NullPointerException.class)
  public final void testToStringBuilderObjectStringStringWithNullObject()
  {
    Objects.toStringBuilder(null, " | ", ": ");
  }

  @Test(expected = NullPointerException.class)
  public final void testToStringBuilderObjectStringStringWithNullFieldSeparator()
  {
    Objects.toStringBuilder(this, null, ": ");
  }

  @Test(expected = NullPointerException.class)
  public final void testToStringBuilderObjectStringStringWithNullNameValueSeparator()
  {
    Objects.toStringBuilder(this, " | ", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public final void testToStringBuilderObjectStringStringWithNullName()
  {
    Objects.toStringBuilder(this, " | ", ": ")
        .add(null, "Kevin");
  }

  @Test(expected = IllegalArgumentException.class)
  public final void testToStringBuilderObjectStringStringWithEmptyName()
  {
    Objects.toStringBuilder(this, " | ", ": ")
        .add("", "Kevin");
  }
}
