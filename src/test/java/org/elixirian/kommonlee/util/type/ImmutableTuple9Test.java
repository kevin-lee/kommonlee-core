package org.elixirian.kommonlee.util.type;

import static org.assertj.core.api.Assertions.*;
import static org.elixirian.kommonlee.util.Objects.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.elixirian.kommonlee.type.Tuple9;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ImmutableTuple9Test
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
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> expected =
      new ImmutableTuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer>(999,
          "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin",
              "Lee"), "Some value", 123);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> actual =
      Tuples.tuple9(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"), "Some value", 123);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7(), expected.getValue8(), expected.getValue9());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);
  }

  @Test
  public final void testImmutableTuple9()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> expected =
      new ImmutableTuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer>(999,
          "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin",
              "Lee"), "Some value", 123);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> actual =
      Tuples.tuple9(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"), "Some value", 123);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7(), expected.getValue8(), expected.getValue9());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());
    assertThat(actual.getValue7()).isEqualTo(expected.getValue7());
    assertThat(actual.getValue8()).isEqualTo(expected.getValue8());
    assertThat(actual.getValue9()).isEqualTo(expected.getValue9());

    assertThat(actual._1()).isEqualTo(expected._1());
    assertThat(actual._2()).isEqualTo(expected._2());
    assertThat(actual._3()).isEqualTo(expected._3());
    assertThat(actual._4()).isEqualTo(expected._4());
    assertThat(actual._5()).isEqualTo(expected._5());
    assertThat(actual._6()).isEqualTo(expected._6());
    assertThat(actual._7()).isEqualTo(expected._7());
    assertThat(actual._8()).isEqualTo(expected._8());
    assertThat(actual._9()).isEqualTo(expected._9());
  }

  @Test
  public final void testGetValue1()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> expected =
      new ImmutableTuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer>(999,
          "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin",
              "Lee"), "Some value", 123);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> actual =
      Tuples.tuple9(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"), "Some value", 123);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7(), expected.getValue8(), expected.getValue9());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());
    assertThat(actual.getValue7()).isEqualTo(expected.getValue7());
    assertThat(actual.getValue8()).isEqualTo(expected.getValue8());
    assertThat(actual.getValue9()).isEqualTo(expected.getValue9());

    assertThat(actual._1()).isEqualTo(expected._1());
    assertThat(actual._2()).isEqualTo(expected._2());
    assertThat(actual._3()).isEqualTo(expected._3());
    assertThat(actual._4()).isEqualTo(expected._4());
    assertThat(actual._5()).isEqualTo(expected._5());
    assertThat(actual._6()).isEqualTo(expected._6());
    assertThat(actual._7()).isEqualTo(expected._7());
    assertThat(actual._8()).isEqualTo(expected._8());
    assertThat(actual._9()).isEqualTo(expected._9());
  }

  @Test
  public final void testGetValue2()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> expected =
      new ImmutableTuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer>(999,
          "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin",
              "Lee"), "Some value", 123);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> actual =
      Tuples.tuple9(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"), "Some value", 123);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7(), expected.getValue8(), expected.getValue9());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());
    assertThat(actual.getValue7()).isEqualTo(expected.getValue7());
    assertThat(actual.getValue8()).isEqualTo(expected.getValue8());
    assertThat(actual.getValue9()).isEqualTo(expected.getValue9());

    assertThat(actual._1()).isEqualTo(expected._1());
    assertThat(actual._2()).isEqualTo(expected._2());
    assertThat(actual._3()).isEqualTo(expected._3());
    assertThat(actual._4()).isEqualTo(expected._4());
    assertThat(actual._5()).isEqualTo(expected._5());
    assertThat(actual._6()).isEqualTo(expected._6());
    assertThat(actual._7()).isEqualTo(expected._7());
    assertThat(actual._8()).isEqualTo(expected._8());
    assertThat(actual._9()).isEqualTo(expected._9());
  }

  @Test
  public final void testGetValue3()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> expected =
      new ImmutableTuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer>(999,
          "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin",
              "Lee"), "Some value", 123);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> actual =
      Tuples.tuple9(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"), "Some value", 123);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7(), expected.getValue8(), expected.getValue9());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());
    assertThat(actual.getValue7()).isEqualTo(expected.getValue7());
    assertThat(actual.getValue8()).isEqualTo(expected.getValue8());
    assertThat(actual.getValue9()).isEqualTo(expected.getValue9());

    assertThat(actual._1()).isEqualTo(expected._1());
    assertThat(actual._2()).isEqualTo(expected._2());
    assertThat(actual._3()).isEqualTo(expected._3());
    assertThat(actual._4()).isEqualTo(expected._4());
    assertThat(actual._5()).isEqualTo(expected._5());
    assertThat(actual._6()).isEqualTo(expected._6());
    assertThat(actual._7()).isEqualTo(expected._7());
    assertThat(actual._8()).isEqualTo(expected._8());
    assertThat(actual._9()).isEqualTo(expected._9());
  }

  @Test
  public final void testGetValue4()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> expected =
      new ImmutableTuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer>(999,
          "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin",
              "Lee"), "Some value", 123);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> actual =
      Tuples.tuple9(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"), "Some value", 123);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7(), expected.getValue8(), expected.getValue9());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());
    assertThat(actual.getValue7()).isEqualTo(expected.getValue7());
    assertThat(actual.getValue8()).isEqualTo(expected.getValue8());
    assertThat(actual.getValue9()).isEqualTo(expected.getValue9());

    assertThat(actual._1()).isEqualTo(expected._1());
    assertThat(actual._2()).isEqualTo(expected._2());
    assertThat(actual._3()).isEqualTo(expected._3());
    assertThat(actual._4()).isEqualTo(expected._4());
    assertThat(actual._5()).isEqualTo(expected._5());
    assertThat(actual._6()).isEqualTo(expected._6());
    assertThat(actual._7()).isEqualTo(expected._7());
    assertThat(actual._8()).isEqualTo(expected._8());
    assertThat(actual._9()).isEqualTo(expected._9());
  }

  @Test
  public final void testGetValue5()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> expected =
      new ImmutableTuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer>(999,
          "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin",
              "Lee"), "Some value", 123);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> actual =
      Tuples.tuple9(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"), "Some value", 123);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7(), expected.getValue8(), expected.getValue9());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());
    assertThat(actual.getValue7()).isEqualTo(expected.getValue7());
    assertThat(actual.getValue8()).isEqualTo(expected.getValue8());
    assertThat(actual.getValue9()).isEqualTo(expected.getValue9());

    assertThat(actual._1()).isEqualTo(expected._1());
    assertThat(actual._2()).isEqualTo(expected._2());
    assertThat(actual._3()).isEqualTo(expected._3());
    assertThat(actual._4()).isEqualTo(expected._4());
    assertThat(actual._5()).isEqualTo(expected._5());
    assertThat(actual._6()).isEqualTo(expected._6());
    assertThat(actual._7()).isEqualTo(expected._7());
    assertThat(actual._8()).isEqualTo(expected._8());
    assertThat(actual._9()).isEqualTo(expected._9());
  }

  @Test
  public final void testGetValue6()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> expected =
      new ImmutableTuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer>(999,
          "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin",
              "Lee"), "Some value", 123);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> actual =
      Tuples.tuple9(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"), "Some value", 123);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7(), expected.getValue8(), expected.getValue9());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());
    assertThat(actual.getValue7()).isEqualTo(expected.getValue7());
    assertThat(actual.getValue8()).isEqualTo(expected.getValue8());
    assertThat(actual.getValue9()).isEqualTo(expected.getValue9());

    assertThat(actual._1()).isEqualTo(expected._1());
    assertThat(actual._2()).isEqualTo(expected._2());
    assertThat(actual._3()).isEqualTo(expected._3());
    assertThat(actual._4()).isEqualTo(expected._4());
    assertThat(actual._5()).isEqualTo(expected._5());
    assertThat(actual._6()).isEqualTo(expected._6());
    assertThat(actual._7()).isEqualTo(expected._7());
    assertThat(actual._8()).isEqualTo(expected._8());
    assertThat(actual._9()).isEqualTo(expected._9());
  }

  @Test
  public final void testGetValue7()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> expected =
      new ImmutableTuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer>(999,
          "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin",
              "Lee"), "Some value", 123);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> actual =
      Tuples.tuple9(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"), "Some value", 123);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7(), expected.getValue8(), expected.getValue9());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());
    assertThat(actual.getValue7()).isEqualTo(expected.getValue7());
    assertThat(actual.getValue8()).isEqualTo(expected.getValue8());
    assertThat(actual.getValue9()).isEqualTo(expected.getValue9());

    assertThat(actual._1()).isEqualTo(expected._1());
    assertThat(actual._2()).isEqualTo(expected._2());
    assertThat(actual._3()).isEqualTo(expected._3());
    assertThat(actual._4()).isEqualTo(expected._4());
    assertThat(actual._5()).isEqualTo(expected._5());
    assertThat(actual._6()).isEqualTo(expected._6());
    assertThat(actual._7()).isEqualTo(expected._7());
    assertThat(actual._8()).isEqualTo(expected._8());
    assertThat(actual._9()).isEqualTo(expected._9());
  }

  @Test
  public final void testEqualsObject()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> expected =
      new ImmutableTuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer>(999,
          "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin",
              "Lee"), "Some value", 123);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> actual =
      Tuples.tuple9(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"), "Some value", 123);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7(), expected.getValue8(), expected.getValue9());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());
    assertThat(actual.getValue7()).isEqualTo(expected.getValue7());
    assertThat(actual.getValue8()).isEqualTo(expected.getValue8());
    assertThat(actual.getValue9()).isEqualTo(expected.getValue9());

    assertThat(actual._1()).isEqualTo(expected._1());
    assertThat(actual._2()).isEqualTo(expected._2());
    assertThat(actual._3()).isEqualTo(expected._3());
    assertThat(actual._4()).isEqualTo(expected._4());
    assertThat(actual._5()).isEqualTo(expected._5());
    assertThat(actual._6()).isEqualTo(expected._6());
    assertThat(actual._7()).isEqualTo(expected._7());
    assertThat(actual._8()).isEqualTo(expected._8());
    assertThat(actual._9()).isEqualTo(expected._9());
  }

  @Test
  public final void testToString()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> expected =
      new ImmutableTuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer>(999,
          "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin",
              "Lee"), "Some value", 123);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple9<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>, String, Integer> actual =
      Tuples.tuple9(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"), "Some value", 123);

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7(), expected.getValue8(), expected.getValue9());
    assertThat(actualHashCode).isEqualTo(expectedHashCode);

    assertThat(actual).isEqualTo(expected);

    assertThat(actual.getValue1()).isEqualTo(expected.getValue1());
    assertThat(actual.getValue2()).isEqualTo(expected.getValue2());
    assertThat(actual.getValue3()).isEqualTo(expected.getValue3());
    assertThat(actual.getValue4()).isEqualTo(expected.getValue4());
    assertThat(actual.getValue5()).isEqualTo(expected.getValue5());
    assertThat(actual.getValue6()).isEqualTo(expected.getValue6());
    assertThat(actual.getValue7()).isEqualTo(expected.getValue7());
    assertThat(actual.getValue8()).isEqualTo(expected.getValue8());
    assertThat(actual.getValue9()).isEqualTo(expected.getValue9());

    assertThat(actual._1()).isEqualTo(expected._1());
    assertThat(actual._2()).isEqualTo(expected._2());
    assertThat(actual._3()).isEqualTo(expected._3());
    assertThat(actual._4()).isEqualTo(expected._4());
    assertThat(actual._5()).isEqualTo(expected._5());
    assertThat(actual._6()).isEqualTo(expected._6());
    assertThat(actual._7()).isEqualTo(expected._7());
    assertThat(actual._8()).isEqualTo(expected._8());
    assertThat(actual._9()).isEqualTo(expected._9());
  }

}
