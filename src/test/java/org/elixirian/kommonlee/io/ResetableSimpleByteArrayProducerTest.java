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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.elixirian.kommonlee.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
 * @version 0.0.1 (2012-10-18)
 */
public class ResetableSimpleByteArrayProducerTest
{
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

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

  @Test
  public final void testResetableSimpleByteArrayProducerWithNullByteArray()
  {
    /* given */
    final byte[] expected = null;

    /* expected */
    expectedException.expect(IllegalArgumentException.class);

    /* when */
    new ResetableSimpleByteArrayProducer(expected);

    /* otherwise-fail */
    fail();
  }

  @Test
  public final void testResetableSimpleByteArrayProducer()
  {
    System.out.println("\nResetableSimpleByteArrayProducerTest.testResetableSimpleByteArrayProducer() {");
    /* given */
    final byte[] expected = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    @SuppressWarnings("boxing")
    final Integer expectedLength = expected.length;
    System.out.println("  expected:");
    System.out.println("  " + Objects.toStringOf(expected));

    /* when */
    final ResetableSimpleByteArrayProducer resetableSimpleByteArrayProducer =
      new ResetableSimpleByteArrayProducer(expected);
    final byte[] actual = resetableSimpleByteArrayProducer.byteArray;
    System.out.println("\n  actual:");
    System.out.println("  " + Objects.toStringOf(actual));
    @SuppressWarnings("boxing")
    final Integer actualLength = actual.length;

    /* then */
    assertThat(actualLength, is(equalTo(expectedLength)));
    assertThat(actual, is(equalTo(expected)));

    System.out.println("}");
  }

  @Test
  public final void testReset() throws IOException
  {
    System.out.println("\nResetableSimpleByteArrayProducerTest.testReset() {");
    /* given */
    final byte[] byteArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    final ResetableSimpleByteArrayProducer resetableSimpleByteArrayProducer =
      new ResetableSimpleByteArrayProducer(byteArray);

    @SuppressWarnings("boxing")
    final Integer expectedPosition = resetableSimpleByteArrayProducer.position;
    @SuppressWarnings("boxing")
    final Integer expectedLength = resetableSimpleByteArrayProducer.length;

    final byte[] bytes = new byte[3];

    int count = resetableSimpleByteArrayProducer.produce(bytes);

    @SuppressWarnings("boxing")
    final Integer countInteger = count;
    assertThat(countInteger, is(not(equalTo(Integer.valueOf(-1)))));

    while (-1 != count)
    {
      count = resetableSimpleByteArrayProducer.produce(bytes);
    }

    @SuppressWarnings("boxing")
    final Integer positionBeforeReset = resetableSimpleByteArrayProducer.position;
    @SuppressWarnings("boxing")
    final Integer lengthBeforeReset = resetableSimpleByteArrayProducer.length();
    System.out.println("  positionBeforeReset:");
    System.out.println("  " + positionBeforeReset);
    System.out.println("  lengthBeforeReset:");
    System.out.println("  " + lengthBeforeReset);
    assertThat(positionBeforeReset, is(equalTo(Integer.valueOf(byteArray.length))));
    assertThat(lengthBeforeReset, is(expectedLength));

    /* when */
    resetableSimpleByteArrayProducer.reset();

    /* then */
    System.out.println("\n  expectedPosition:");
    System.out.println("  " + expectedPosition);
    System.out.println("  expectedLength:");
    System.out.println("  " + expectedLength);

    @SuppressWarnings("boxing")
    final Integer actualPosition = resetableSimpleByteArrayProducer.position;
    @SuppressWarnings("boxing")
    final Integer actualLength = resetableSimpleByteArrayProducer.length();
    System.out.println("\n  actualPosition:");
    System.out.println("  " + actualPosition);
    System.out.println("  actualLength:");
    System.out.println("  " + actualLength);

    assertThat(actualPosition, is(equalTo(expectedPosition)));
    assertThat(actualLength, is(equalTo(expectedLength)));
    System.out.println("}");
  }

}
