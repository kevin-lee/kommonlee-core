package org.elixirian.kommonlee.util.type;

import static org.assertj.core.api.Assertions.*;
import static org.elixirian.kommonlee.util.Objects.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.elixirian.kommonlee.type.Tuple6;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ImmutableTuple6Test
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
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> expected =
      new ImmutableTuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> actual =
      Tuples.tuple(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);
  }

  @Test
  public final void testImmutableTuple6()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> expected =
      new ImmutableTuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> actual =
      Tuples.tuple(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());

  }

  @Test
  public final void testGetValue1()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> expected =
      new ImmutableTuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> actual =
      Tuples.tuple(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());

  }

  @Test
  public final void testGetValue2()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> expected =
      new ImmutableTuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> actual =
      Tuples.tuple(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());

  }

  @Test
  public final void testGetValue3()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> expected =
      new ImmutableTuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> actual =
      Tuples.tuple(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());

  }

  @Test
  public final void testGetValue4()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> expected =
      new ImmutableTuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> actual =
      Tuples.tuple(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());

  }

  @Test
  public final void testGetValue5()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> expected =
      new ImmutableTuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> actual =
      Tuples.tuple(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());

  }

  @Test
  public final void testGetValue6()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> expected =
      new ImmutableTuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> actual =
      Tuples.tuple(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());

  }

  @Test
  public final void testGetValue7()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> expected =
      new ImmutableTuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> actual =
      Tuples.tuple(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());

  }

  @Test
  public final void testEqualsObject()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> expected =
      new ImmutableTuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> actual =
      Tuples.tuple(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());

  }

  @Test
  public final void testToString()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> expected =
      new ImmutableTuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple6<Integer, String, Boolean, BigInteger, BigDecimal, Date> actual =
      Tuples.tuple(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());

  }

}
