package org.elixirian.kommonlee.type;

import static org.assertj.core.api.Assertions.*;

import org.elixirian.kommonlee.functional.VoidFunction1;
import org.elixirian.kommonlee.test.CauseCheckableExpectedException;
import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.functional.Function1;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class OptionsTest
{
  @Rule
  public CauseCheckableExpectedException expectedException = CauseCheckableExpectedException.none();

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
    final Option<Object> expected = Options.NONE;

    /* when */
    final Option<Object> actual = Options.optionOf(null);

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
    final Option<Integer> actual = Options.optionOf(999);

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual.get()).isEqualTo(expected.get());
    assertThat(actual.get()).isNotNull();
    assertThat(actual.isNull()).isFalse();
    assertThat(actual.hashCode()).isEqualTo(expected.hashCode());
    assertThat(actual.toString()).isEqualTo(expected.toString());
  }

  @Test
  public final void testHashCode()
  {
    /* given */
    final int expected = new Options.Some<Integer>(999).hashCode();

    /* when */
    final int actual = Options.optionOf(999)
        .hashCode();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIsNull()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(null);

    /* when */
    final boolean actual = option.isNull();

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNullWithNotNull()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final boolean actual = option.isNull();

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNotNull()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final boolean actual = option.isNotNull();

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNotNullWithNull()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(null);

    /* when */
    final boolean actual = option.isNotNull();

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testGet()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final Integer actual = option.get();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetWithNull()
  {
    /* given */
    final Integer expected = null;
    final Option<Integer> option = Options.optionOf(null);

    /* when */
    final Integer actual = option.get();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetOrUse()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final Integer actual = option.getOrUse(111);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetOrUseWithNull()
  {
    /* given */
    final Integer expected = 111;
    final Option<Integer> option = Options.optionOf(null);

    /* when */
    final Integer actual = option.getOrUse(111);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetOrGetAnother()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final Integer actual = option.getOrGetAnother(new Suppliable<Integer>() {
      @Override
      public Integer supply()
      {
        return 111;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetOrGetAnotherWithNull()
  {
    /* given */
    final Integer expected = 111;
    final Option<Integer> option = Options.optionOf(null);

    /* when */
    final Integer actual = option.getOrGetAnother(new Suppliable<Integer>() {
      @Override
      public Integer supply()
      {
        return 111;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetOrGetAnotherWithNullSuppliable()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Options.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class);

    /* when */
    final Integer actual = option.getOrGetAnother(null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Suppliable was null.");
  }

  @Test
  public final void testGetIf()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final Integer actual = option.getIf(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 100;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetIfForFalse()
  {
    /* given */
    final Integer expected = null;
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final Integer actual = option.getIf(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input < 100;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetIfWithNullCondition()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Options.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class);

    /* when */
    final Integer actual = option.getIf(null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Condition1 was null.");
  }

  @Test
  public final void testGetIfOrUse()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final Integer actual = option.getIfOrUse(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 100;
      }
    }, 111);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetIfOrUseForFalse()
  {
    /* given */
    final Integer expected = 111;
    final Option<Integer> option = Options.optionOf(9);

    /* when */
    final Integer actual = option.getIfOrUse(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 100;
      }
    }, 111);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetIfOrGetAnother()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final Integer actual = option.getIfOrGetAnother(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 100;
      }
    }, new Suppliable<Integer>() {
      @Override
      public Integer supply()
      {
        return 111;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetIfOrGetAnotherForFalse()
  {
    /* given */
    final Integer expected = 111;
    final Option<Integer> option = Options.optionOf(10);

    /* when */
    final Integer actual = option.getIfOrGetAnother(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 100;
      }
    }, new Suppliable<Integer>() {
      @Override
      public Integer supply()
      {
        return 111;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetIfOrGetAnotherWithNullCondition()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("condition must not be null");

    /* when */
    final Integer actual = option.getIfOrGetAnother(null, new Suppliable<Integer>() {
      @Override
      public Integer supply()
      {
        return 111;
      }
    });

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Condition1 was null.");
  }

  @Test
  public final void testGetIfOrGetAnotherWithNullSuppliable()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("suppliable must not be null");

    /* when */
    final Integer actual = option.getIfOrGetAnother(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 100;
      }
    }, null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Suppliable was null.");
  }

  @Test
  public final void testMap()
  {
    /* given */
    final int number = 999;
    final String expected = "(" + number + ")";
    final Option<Integer> option = Options.optionOf(number);

    /* when */
    final String actual = option.map(new Function1<Integer, String>() {
      @Override
      public String apply(final Integer input)
      {
        return "(" + input + ")";
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testMapWithNull()
  {
    /* given */
    final String expected = "(" + null + ")";
    final Option<Integer> option = Options.optionOf(null);

    /* when */
    final String actual = option.map(new Function1<Integer, String>() {
      @Override
      public String apply(final Integer input)
      {
        return "(" + input + ")";
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testMapWithNullMapper()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("mapper must not be null");

    /* when */
    final String actual = option.map(null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Function1 was null.");
  }

  @Test
  public final void testMapIf()
  {
    /* given */
    final String expected = "007";
    final Option<Integer> option = Options.optionOf(7);

    /* when */
    final String actual = option.mapIf(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input < 10;
      }
    }, new Function1<Integer, String>() {
      @Override
      public String apply(final Integer input)
      {
        return "00" + input;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testMapIfForFalse()
  {
    /* given */
    final String expected = null;
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final String actual = option.mapIf(new Condition1<Integer>() {

      @Override
      public boolean isMet(final Integer input)
      {
        return input < 10;
      }
    }, new Function1<Integer, String>() {
      @Override
      public String apply(final Integer input)
      {
        return "00" + input;
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testMapIfWithNullCondition()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(7);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("condition must not be null");

    /* when */
    final String actual = option.mapIf(null, new Function1<Integer, String>() {
      @Override
      public String apply(final Integer input)
      {
        return "00" + input;
      }
    });

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Condition1 was null.");
  }

  @Test
  public final void testMapIfWithNullMapper()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(7);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("mapper must not be null");

    /* when */
    final String actual = option.mapIf(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input < 10;
      }
    }, null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Function1 was null.");
  }

  @Test
  public final void testDoIf()
  {
    /* given */
    final int expected = 999 * 123;
    final Option<Integer> option = Options.optionOf(999);
    final int[] number = { 123 };

    /* when */
    option.doIf(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 0;
      }
    }, new VoidFunction1<Integer>() {
      @Override
      public void apply(final Integer input)
      {
        number[0] = number[0] * input;
      }
    });
    final int actual = number[0];

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testDoIf2()
  {
    /* given */
    final int expected = 123;
    final Option<Integer> option = Options.optionOf(0);
    final int[] number = { 123 };

    /* when */
    option.doIf(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 0;
      }
    }, new VoidFunction1<Integer>() {
      @Override
      public void apply(final Integer input)
      {
        number[0] = number[0] * input;
      }
    });
    final int actual = number[0];

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testDoIfWithNullCondition()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(999);
    final int[] number = { 123 };

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("condition must not be null");

    /* when */
    option.doIf(null, new VoidFunction1<Integer>() {
      @Override
      public void apply(final Integer input)
      {
        number[0] = number[0] * input;
      }
    });
    final int actual = number[0];

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Condition1 was null.");
  }

  @Test
  public final void testDoIfWithNullVoidFunction()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(999);
    final int[] number = { 123 };

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("function must not be null");

    /* when */
    option.doIf(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 0;
      }
    }, null);
    final int actual = number[0];

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when VoidFunction1 was null.");
  }

  @Test
  public final void testDoIfNotNull()
  {
    /* given */
    final int expected = 999 * 123;
    final Option<Integer> option = Options.optionOf(999);
    final int[] number = { 123 };

    /* when */
    option.doIfNotNull(new VoidFunction1<Integer>() {
      @Override
      public void apply(final Integer input)
      {
        number[0] = number[0] * input;
      }
    });
    final int actual = number[0];

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testDoIfNotNullWithNull()
  {
    /* given */
    final int expected = 123;
    final Option<Integer> option = Options.optionOf(null);
    final int[] number = { 123 };

    /* when */
    option.doIfNotNull(new VoidFunction1<Integer>() {
      @Override
      public void apply(final Integer input)
      {
        number[0] = number[0] * input;
      }
    });
    final int actual = number[0];

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testDoIfNotNullWithNullFunction()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("function must not be null");

    /* when */
    option.doIfNotNull(null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when VoidFunction1 was null.");
  }

  @Test
  public final void testDoIfNotNullWithNullAndNullFunction()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(null);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("function must not be null");

    /* when */
    option.doIfNotNull(null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when VoidFunction1 was null.");
  }

  @Test
  public final void testGetOrThrow()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final Integer actual = option.getOrThrow(new Suppliable<IllegalArgumentException>() {
      @Override
      public IllegalArgumentException supply()
      {
        return new IllegalArgumentException("The value must not be null.");
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetOrThrowWithNull()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(null);

    /* expected */
    expectedException.expect(IllegalArgumentException.class)
        .expectMessage(CoreMatchers.equalTo("The value must not be null."));

    /* when */
    final Integer actual = option.getOrThrow(new Suppliable<IllegalArgumentException>() {
      @Override
      public IllegalArgumentException supply()
      {
        return new IllegalArgumentException("The value must not be null.");
      }
    });

    /* otherwise-fail */
    fail("It did not throw " + IllegalArgumentException.class.getSimpleName() + " when it was expected.");
  }

  @Test
  public final void testGetIfOrThrow()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final Integer actual = option.getIfOrThrow(new Condition1<Integer>() {

      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() > 0;
      }
    }, new Suppliable<IllegalArgumentException>() {
      @Override
      public IllegalArgumentException supply()
      {
        return new IllegalArgumentException("The value must not be null.");
      }
    });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetIfOrThrowWithFalse()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(999);

    /* expected */
    expectedException.expect(IllegalArgumentException.class)
        .expectMessage(CoreMatchers.equalTo("The value must be a negative integer."));

    /* when */
    final Integer actual = option.getIfOrThrow(new Condition1<Integer>() {

      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() < 0;
      }
    }, new Suppliable<IllegalArgumentException>() {
      @Override
      public IllegalArgumentException supply()
      {
        return new IllegalArgumentException("The value must be a negative integer.");
      }
    });

    /* otherwise-fail */
    fail("It did not throw " + IllegalArgumentException.class.getSimpleName() + " when it was expected.");
  }

  @Test
  public final void testGetIfOrThrowWithNullCondition()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessage(CoreMatchers.equalTo("The condition must not be null."));

    /* when */
    final Integer actual = option.getIfOrThrow(null, new Suppliable<IllegalArgumentException>() {
      @Override
      public IllegalArgumentException supply()
      {
        return new IllegalArgumentException("The value must not be null.");
      }
    });

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Condition1 was null.");
  }

  @Test
  public final void testGetIfOrThrowWithNullThrowableSupplier()
  {
    /* given */
    final Option<Integer> option = Options.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessage(CoreMatchers.equalTo("The exceptionSuppliable must not be null."));

    /* when */
    final Integer actual = option.getIfOrThrow(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() > 0;
      }
    }, (Suppliable<RuntimeException>) null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when exceptionSuppliable was null.");
  }

  @Test
  public final void testEqualsObjectForNone()
  {
    /* given */
    final Option<Integer> another = (Option) Options.NONE;
    final Option<Integer> option = Options.optionOf(null);

    /* when */
    final boolean actual = option.equals(another);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testEqualsObjectForNone2()
  {
    /* given */
    final Option<Integer> another = new Options.Some<Integer>(999);
    final Option<Integer> option = Options.optionOf(null);

    /* when */
    final boolean actual = option.equals(another);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testEqualsObjectForSome()
  {
    /* given */
    final Option<Integer> another = new Options.Some<Integer>(999);
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final boolean actual = option.equals(another);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testEqualsObjectForSome2()
  {
    /* given */
    final Option<Integer> another = (Option) Options.NONE;
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final boolean actual = option.equals(another);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testToStringForNone()
  {
    /* given */
    final String expected = "None()";
    final Option<Integer> option = Options.optionOf(null);

    /* when */
    final String actual = option.toString();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testToStringForSome()
  {
    /* given */
    final String expected = "Some(999)";
    final Option<Integer> option = Options.optionOf(999);

    /* when */
    final String actual = option.toString();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }
}
