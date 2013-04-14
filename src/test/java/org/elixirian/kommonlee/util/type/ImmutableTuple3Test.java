package org.elixirian.kommonlee.util.type;

import static org.elixirian.kommonlee.util.Objects.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.elixirian.kommonlee.type.Tuple3;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ImmutableTuple3Test
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

  @Test
  public final void testHashCode()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> expected = new ImmutableTuple3<Integer, String, Boolean>(999, "Kevin", true);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> actual = Tuples.tuple3(999, "Kevin", true);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2(), expected.getValue3());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));
  }

  @Test
  public final void testImmutableTuple3()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> expected = new ImmutableTuple3<Integer, String, Boolean>(999, "Kevin", true);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> actual = Tuples.tuple3(999, "Kevin", true);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2(), expected.getValue3());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
  }

  @Test
  public final void testGetValue1()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> expected = new ImmutableTuple3<Integer, String, Boolean>(999, "Kevin", true);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> actual = Tuples.tuple3(999, "Kevin", true);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2(), expected.getValue3());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
  }

  @Test
  public final void testGetValue2()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> expected = new ImmutableTuple3<Integer, String, Boolean>(999, "Kevin", true);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> actual = Tuples.tuple3(999, "Kevin", true);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2(), expected.getValue3());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
  }

  @Test
  public final void testGetValue3()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> expected = new ImmutableTuple3<Integer, String, Boolean>(999, "Kevin", true);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> actual = Tuples.tuple3(999, "Kevin", true);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2(), expected.getValue3());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
  }

  @Test
  public final void testGetValue4()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> expected = new ImmutableTuple3<Integer, String, Boolean>(999, "Kevin", true);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> actual = Tuples.tuple3(999, "Kevin", true);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2(), expected.getValue3());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
  }

  @Test
  public final void testGetValue5()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> expected = new ImmutableTuple3<Integer, String, Boolean>(999, "Kevin", true);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> actual = Tuples.tuple3(999, "Kevin", true);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2(), expected.getValue3());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
  }

  @Test
  public final void testGetValue6()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> expected = new ImmutableTuple3<Integer, String, Boolean>(999, "Kevin", true);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> actual = Tuples.tuple3(999, "Kevin", true);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2(), expected.getValue3());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
  }

  @Test
  public final void testGetValue7()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> expected = new ImmutableTuple3<Integer, String, Boolean>(999, "Kevin", true);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> actual = Tuples.tuple3(999, "Kevin", true);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2(), expected.getValue3());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
  }

  @Test
  public final void testEqualsObject()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> expected = new ImmutableTuple3<Integer, String, Boolean>(999, "Kevin", true);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> actual = Tuples.tuple3(999, "Kevin", true);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2(), expected.getValue3());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
  }

  @Test
  public final void testToString()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> expected = new ImmutableTuple3<Integer, String, Boolean>(999, "Kevin", true);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> actual = Tuples.tuple3(999, "Kevin", true);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2(), expected.getValue3());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
  }

}
