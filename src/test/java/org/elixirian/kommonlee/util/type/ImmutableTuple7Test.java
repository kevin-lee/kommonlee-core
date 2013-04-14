package org.elixirian.kommonlee.util.type;

import static org.elixirian.kommonlee.util.Objects.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.elixirian.kommonlee.type.Tuple7;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ImmutableTuple7Test
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
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> expected =
      new ImmutableTuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin", "Lee"));

    /* when */
    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> actual =
      Tuples.tuple7(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"));

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));
  }

  @Test
  public final void testImmutableTuple7()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> expected =
      new ImmutableTuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin", "Lee"));

    /* when */
    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> actual =
      Tuples.tuple7(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"));

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
    assertThat(actual.getValue4(), is(equalTo(expected.getValue4())));
    assertThat(actual.getValue5(), is(equalTo(expected.getValue5())));
    assertThat(actual.getValue6(), is(equalTo(expected.getValue6())));
    assertThat(actual.getValue7(), is(equalTo(expected.getValue7())));
  }

  @Test
  public final void testGetValue1()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> expected =
      new ImmutableTuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin", "Lee"));

    /* when */
    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> actual =
      Tuples.tuple7(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"));

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
    assertThat(actual.getValue4(), is(equalTo(expected.getValue4())));
    assertThat(actual.getValue5(), is(equalTo(expected.getValue5())));
    assertThat(actual.getValue6(), is(equalTo(expected.getValue6())));
    assertThat(actual.getValue7(), is(equalTo(expected.getValue7())));
  }

  @Test
  public final void testGetValue2()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> expected =
      new ImmutableTuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin", "Lee"));

    /* when */
    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> actual =
      Tuples.tuple7(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"));

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
    assertThat(actual.getValue4(), is(equalTo(expected.getValue4())));
    assertThat(actual.getValue5(), is(equalTo(expected.getValue5())));
    assertThat(actual.getValue6(), is(equalTo(expected.getValue6())));
    assertThat(actual.getValue7(), is(equalTo(expected.getValue7())));
  }

  @Test
  public final void testGetValue3()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> expected =
      new ImmutableTuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin", "Lee"));

    /* when */
    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> actual =
      Tuples.tuple7(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"));

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
    assertThat(actual.getValue4(), is(equalTo(expected.getValue4())));
    assertThat(actual.getValue5(), is(equalTo(expected.getValue5())));
    assertThat(actual.getValue6(), is(equalTo(expected.getValue6())));
    assertThat(actual.getValue7(), is(equalTo(expected.getValue7())));
  }

  @Test
  public final void testGetValue4()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> expected =
      new ImmutableTuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin", "Lee"));

    /* when */
    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> actual =
      Tuples.tuple7(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"));

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
    assertThat(actual.getValue4(), is(equalTo(expected.getValue4())));
    assertThat(actual.getValue5(), is(equalTo(expected.getValue5())));
    assertThat(actual.getValue6(), is(equalTo(expected.getValue6())));
    assertThat(actual.getValue7(), is(equalTo(expected.getValue7())));
  }

  @Test
  public final void testGetValue5()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> expected =
      new ImmutableTuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin", "Lee"));

    /* when */
    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> actual =
      Tuples.tuple7(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"));

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
    assertThat(actual.getValue4(), is(equalTo(expected.getValue4())));
    assertThat(actual.getValue5(), is(equalTo(expected.getValue5())));
    assertThat(actual.getValue6(), is(equalTo(expected.getValue6())));
    assertThat(actual.getValue7(), is(equalTo(expected.getValue7())));
  }

  @Test
  public final void testGetValue6()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> expected =
      new ImmutableTuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin", "Lee"));

    /* when */
    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> actual =
      Tuples.tuple7(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"));

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
    assertThat(actual.getValue4(), is(equalTo(expected.getValue4())));
    assertThat(actual.getValue5(), is(equalTo(expected.getValue5())));
    assertThat(actual.getValue6(), is(equalTo(expected.getValue6())));
    assertThat(actual.getValue7(), is(equalTo(expected.getValue7())));
  }

  @Test
  public final void testGetValue7()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> expected =
      new ImmutableTuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin", "Lee"));

    /* when */
    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> actual =
      Tuples.tuple7(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"));

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
    assertThat(actual.getValue4(), is(equalTo(expected.getValue4())));
    assertThat(actual.getValue5(), is(equalTo(expected.getValue5())));
    assertThat(actual.getValue6(), is(equalTo(expected.getValue6())));
    assertThat(actual.getValue7(), is(equalTo(expected.getValue7())));
  }

  @Test
  public final void testEqualsObject()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> expected =
      new ImmutableTuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin", "Lee"));

    /* when */
    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> actual =
      Tuples.tuple7(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"));

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
    assertThat(actual.getValue4(), is(equalTo(expected.getValue4())));
    assertThat(actual.getValue5(), is(equalTo(expected.getValue5())));
    assertThat(actual.getValue6(), is(equalTo(expected.getValue6())));
    assertThat(actual.getValue7(), is(equalTo(expected.getValue7())));
  }

  @Test
  public final void testToString()
  {
    /* given */
    final Date date = new Date();

    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> expected =
      new ImmutableTuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"), date, Arrays.asList("Name", "Kevin", "Lee"));

    /* when */
    @SuppressWarnings("boxing")
    final Tuple7<Integer, String, Boolean, BigInteger, BigDecimal, Date, List<String>> actual =
      Tuples.tuple7(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date,
          Arrays.asList("Name", "Kevin", "Lee"));

    /* then */
    @SuppressWarnings("boxing")
    final Integer actualHashCode = actual.hashCode();
    @SuppressWarnings("boxing")
    final Integer expectedHashCode =
      hash(expected.getValue1(), expected.getValue2(), expected.getValue3(), expected.getValue4(),
          expected.getValue5(), expected.getValue6(), expected.getValue7());
    assertThat(actualHashCode, is(equalTo(expectedHashCode)));

    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
    assertThat(actual.getValue4(), is(equalTo(expected.getValue4())));
    assertThat(actual.getValue5(), is(equalTo(expected.getValue5())));
    assertThat(actual.getValue6(), is(equalTo(expected.getValue6())));
    assertThat(actual.getValue7(), is(equalTo(expected.getValue7())));
  }

}
