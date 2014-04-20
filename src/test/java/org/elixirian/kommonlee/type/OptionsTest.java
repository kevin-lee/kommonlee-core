package org.elixirian.kommonlee.type;

import static org.assertj.core.api.Assertions.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OptionsTest
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
  public final void testNone()
  {
    /* given */
    final Option<Object> expected = Options.None.INSTANCE;

    /* when */
    final Option<Object> actual = Options.option(null);

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual.get()).isEqualTo(expected.get());
    assertThat(actual.get()).isNull();
    assertThat(actual.isNull()).isTrue();
    assertThat(actual.toString()).isEqualTo(expected.toString());
  }

  @Test
  public final void testSome()
  {
    /* given */
    final Option<Integer> expected = new Options.Some<Integer>(999);

    /* when */
    final Option<Integer> actual = Options.option(999);

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual.get()).isEqualTo(expected.get());
    assertThat(actual.get()).isNotNull();
    assertThat(actual.isNull()).isFalse();
    assertThat(actual.hashCode()).isEqualTo(expected.hashCode());
    assertThat(actual.toString()).isEqualTo(expected.toString());
  }

}
