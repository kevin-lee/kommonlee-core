package org.elixirian.kommonlee.util.type;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.elixirian.kommonlee.type.Pair;
import org.elixirian.kommonlee.type.Tuple2;
import org.elixirian.kommonlee.type.Tuple3;
import org.elixirian.kommonlee.type.Tuple4;
import org.elixirian.kommonlee.type.Tuple5;
import org.elixirian.kommonlee.type.Tuple6;
import org.elixirian.kommonlee.type.Tuple7;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
 * @version 0.0.1 (2013-03-25)
 */
public class TuplesTest
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
  public final void testPair()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Pair<Integer, String> expected = new ImmutablePair<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Pair<Integer, String> actual = Tuples.pair(999, "Kevin");

    /* then */
    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
  }

  @Test
  public final void testTuple2()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Tuple2<Integer, String> expected = new ImmutableTuple2<Integer, String>(999, "Kevin");

    /* when */
    @SuppressWarnings("boxing")
    final Tuple2<Integer, String> actual = Tuples.tuple2(999, "Kevin");

    /* then */
    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
  }

  @Test
  public final void testTuple3()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> expected = new ImmutableTuple3<Integer, String, Boolean>(999, "Kevin", true);

    /* when */
    @SuppressWarnings("boxing")
    final Tuple3<Integer, String, Boolean> actual = Tuples.tuple3(999, "Kevin", true);

    /* then */
    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
  }

  @Test
  public final void testTuple4()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Tuple4<Integer, String, Boolean, BigInteger> expected =
      new ImmutableTuple4<Integer, String, Boolean, BigInteger>(999, "Kevin", true, BigInteger.valueOf(555L));

    /* when */
    @SuppressWarnings("boxing")
    final Tuple4<Integer, String, Boolean, BigInteger> actual =
      Tuples.tuple4(999, "Kevin", true, BigInteger.valueOf(555L));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
    assertThat(actual.getValue4(), is(equalTo(expected.getValue4())));
  }

  @Test
  public final void testTuple5()
  {
    /* given */
    @SuppressWarnings("boxing")
    final Tuple5<Integer, String, Boolean, BigInteger, BigDecimal> expected =
      new ImmutableTuple5<Integer, String, Boolean, BigInteger, BigDecimal>(999, "Kevin", true,
          BigInteger.valueOf(555L), new BigDecimal("1234.59"));

    /* when */
    @SuppressWarnings("boxing")
    final Tuple5<Integer, String, Boolean, BigInteger, BigDecimal> actual =
      Tuples.tuple5(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"));

    /* then */
    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
    assertThat(actual.getValue4(), is(equalTo(expected.getValue4())));
    assertThat(actual.getValue5(), is(equalTo(expected.getValue5())));
  }

  @Test
  public final void testTuple6()
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
      Tuples.tuple6(999, "Kevin", true, BigInteger.valueOf(555L), new BigDecimal("1234.59"), date);

    /* then */
    assertThat(actual, is(equalTo(expected)));

    assertThat(actual.getValue1(), is(equalTo(expected.getValue1())));
    assertThat(actual.getValue2(), is(equalTo(expected.getValue2())));
    assertThat(actual.getValue3(), is(equalTo(expected.getValue3())));
    assertThat(actual.getValue4(), is(equalTo(expected.getValue4())));
    assertThat(actual.getValue5(), is(equalTo(expected.getValue5())));
    assertThat(actual.getValue6(), is(equalTo(expected.getValue6())));
  }

  @Test
  public final void testTuple7()
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
