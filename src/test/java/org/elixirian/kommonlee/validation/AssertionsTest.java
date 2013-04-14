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

import static org.elixirian.kommonlee.test.CommonTestHelper.*;
import static org.elixirian.kommonlee.util.MessageFormatter.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.elixirian.kommonlee.test.CauseCheckableExpectedException;
import org.elixirian.kommonlee.test.CommonTestHelper.Accessibility;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

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
 * @version 0.0.1 (2009-12-29)
 */
public class AssertionsTest
{
  /**
   * @throws java.lang.Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception
  {
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception
  {
  }

  @Test(expected = IllegalAccessException.class)
  public final void testAssertions() throws Exception
  {
    testNotAccessibleConstructor(Assertions.class, this, Accessibility.PRIVATE, classArrayOf(), objectArrayOf());
  }

  @Rule
  public CauseCheckableExpectedException causeCheckableExpectedException = CauseCheckableExpectedException.none();

  @Test
  public final void testAssertNotNull()
  {
    /* given / when / then: nothing should happen */
    Assertions.assertNotNull(new Object(), "It is null!!!");

    /* expect */
    causeCheckableExpectedException.expect(IllegalArgumentException.class);

    /* when / then the expected exception should be thrown. */
    Assertions.assertNotNull(null, "It is null!!!");

    /* otherwise */
    fail(format("The expected exception [%s] is not thrown", IllegalArgumentException.class));
  }

  @Test
  public final void testAssertNull()
  {
    /* given / when / then: nothing should happen */
    Assertions.assertNull(null, "It is not null!");

    /* expect */
    causeCheckableExpectedException.expect(IllegalArgumentException.class);

    /* when / then the expected exception should be thrown. */
    Assertions.assertNull(new Object(), "It is not null!");

    /* otherwise */
    fail(format("The expected exception [%s] is not thrown", IllegalArgumentException.class));
  }

  @Test
  public final void testAssertNotEmptyForNull()
  {
    /* given */
    final String notEmpty = "something";
    final String nullReference = null;

    /* when / then: nothing should happen */
    Assertions.assertNotEmpty(notEmpty, "It is empty!");

    /* expect */
    causeCheckableExpectedException.expect(IllegalArgumentException.class);

    /* when / then the expected exception should be thrown. */
    Assertions.assertNotEmpty(nullReference, "It is empty!");

    /* otherwise */
    fail(format("The expected exception [%s] is not thrown", IllegalArgumentException.class));
  }

  @Test
  public final void testAssertNotEmptyForEmpty()
  {
    /* given */
    final String notEmpty = "something";
    final String emptyString = "";

    /* when / then: nothing should happen */
    Assertions.assertNotEmpty(notEmpty, "It is empty!");

    /* expect */
    causeCheckableExpectedException.expect(IllegalArgumentException.class);

    /* when / then the expected exception should be thrown. */
    Assertions.assertNotEmpty(emptyString, "It is empty!");

    /* otherwise */
    fail(format("The expected exception [%s] is not thrown", IllegalArgumentException.class));
  }

  @Test
  public final void testAssertEmpty()
  {
    /* given */
    final String nullReference = null;
    final String emptyString = "";
    final String notEmpty = "something";

    /* when / then: nothing should happen */
    assertThat(Assertions.assertEmpty(nullReference, "It is not empty!"), is(""));
    assertThat(Assertions.assertEmpty(emptyString, "It is not empty!"), is(""));

    /* expect */
    causeCheckableExpectedException.expect(IllegalArgumentException.class);

    /* when / then the expected exception should be thrown. */
    Assertions.assertEmpty(notEmpty, "It is not empty!");

    /* otherwise */
    fail(format("The expected exception [%s] is not thrown", IllegalArgumentException.class));
  }

  @Test
  public final void testAssertTrue()
  {
    /* given / when / then: nothing should happen */
    Assertions.assertTrue(new Integer(2).equals(new Integer(2)), "It is not true.");

    /* expect */
    causeCheckableExpectedException.expect(IllegalArgumentException.class);

    /* when / then the expected exception should be thrown. */
    Assertions.assertTrue(new Integer(3).equals(new Integer(1)), "It is not true.");

    /* otherwise */
    fail(format("The expected exception [%s] is not thrown", IllegalArgumentException.class));
  }

  @Test
  public final void testAssertFalse()
  {
    /* given / when / then: nothing should happen */
    Assertions.assertFalse(new Integer(2).equals(new Integer(3)), "It is not false.");

    /* expect */
    causeCheckableExpectedException.expect(IllegalArgumentException.class);

    /* when / then the expected exception should be thrown. */
    Assertions.assertFalse(new Integer(5).equals(new Integer(5)), "It is not false.");

    /* otherwise */
    fail(format("The expected exception [%s] is not thrown", IllegalArgumentException.class));
  }
}
