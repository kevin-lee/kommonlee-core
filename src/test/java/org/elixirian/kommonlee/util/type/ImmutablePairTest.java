package org.elixirian.kommonlee.util.type;

import static org.assertj.core.api.Assertions.*;
import static org.elixirian.kommonlee.util.Objects.*;

import org.elixirian.kommonlee.type.Pair;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ImmutablePairTest
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
    final Pair<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Pair<Integer, String> actual = Tuples.pair(999, "Kevin");

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);
  }

  @Test
  public final void testImmutablePair()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Pair<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Pair<Integer, String> actual = Tuples.pair(999, "Kevin");

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
  }

  @Test
  public final void testGetValue1()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Pair<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Pair<Integer, String> actual = Tuples.pair(999, "Kevin");

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
  }

  @Test
  public final void testGetValue2()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Pair<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Pair<Integer, String> actual = Tuples.pair(999, "Kevin");

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
  }

  @Test
  public final void testGetValue3()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Pair<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Pair<Integer, String> actual = Tuples.pair(999, "Kevin");

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
  }

  @Test
  public final void testGetValue4()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Pair<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Pair<Integer, String> actual = Tuples.pair(999, "Kevin");

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
  }

  @Test
  public final void testGetValue5()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Pair<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Pair<Integer, String> actual = Tuples.pair(999, "Kevin");

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
  }

  @Test
  public final void testGetValue6()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Pair<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Pair<Integer, String> actual = Tuples.pair(999, "Kevin");

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
  }

  @Test
  public final void testGetValue7()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Pair<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Pair<Integer, String> actual = Tuples.pair(999, "Kevin");

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
  }

  @Test
  public final void testEqualsObject()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Pair<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Pair<Integer, String> actual = Tuples.pair(999, "Kevin");

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
  }

  @Test
  public final void testToString()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Pair<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Pair<Integer, String> actual = Tuples.pair(999, "Kevin");

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
  }

}
