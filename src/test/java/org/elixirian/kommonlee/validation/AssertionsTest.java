/**
 * 
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
