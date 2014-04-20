package org.elixirian.kommonlee.util.type;

import static org.assertj.core.api.Assertions.*;
import static org.elixirian.kommonlee.util.Objects.*;
import static org.elixirian.kommonlee.util.type.Tuples.*;

import java.util.HashMap;
import java.util.Map;

import org.elixirian.kommonlee.type.Tuple2;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ImmutableTuple2Test
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
    final Tuple2<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Tuple2<Integer, String> actual = Tuples.tuple(999, "Kevin");

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode = hash(expected.getValue1(), expected.getValue2());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);
  }

  @Test
  public final void testImmutableTuple2()
  {
    /* given */

    @SuppressWarnings("boxing")
    final Tuple2<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Tuple2<Integer, String> actual = Tuples.tuple(999, "Kevin");

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
    final Tuple2<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Tuple2<Integer, String> actual = tuple(999, "Kevin");

    final Map<String, Tuple2<String, Tuple2<Integer, String>>> map =
      new HashMap<String, Tuple2<String, Tuple2<Integer, String>>>();
    map.put("test", tuple("Lee", tuple(1, "Kevin")));

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
    final Tuple2<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Tuple2<Integer, String> actual = Tuples.tuple(999, "Kevin");

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
    final Tuple2<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Tuple2<Integer, String> actual = Tuples.tuple(999, "Kevin");

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
    final Tuple2<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Tuple2<Integer, String> actual = Tuples.tuple(999, "Kevin");

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
    final Tuple2<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Tuple2<Integer, String> actual = Tuples.tuple(999, "Kevin");

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
    final Tuple2<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Tuple2<Integer, String> actual = Tuples.tuple(999, "Kevin");

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
    final Tuple2<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Tuple2<Integer, String> actual = Tuples.tuple(999, "Kevin");

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
    final Tuple2<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Tuple2<Integer, String> actual = Tuples.tuple(999, "Kevin");

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
    final Tuple2<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Tuple2<Integer, String> actual = Tuples.tuple(999, "Kevin");

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
