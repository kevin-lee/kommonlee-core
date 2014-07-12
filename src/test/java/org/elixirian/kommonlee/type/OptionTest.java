package org.elixirian.kommonlee.type;

import static org.assertj.core.api.Assertions.*;
import static org.elixirian.kommonlee.util.Objects.*;

import java.util.NoSuchElementException;

import org.elixirian.kommonlee.functional.VoidFunction1;
import org.elixirian.kommonlee.test.CauseCheckableExpectedException;
import org.elixirian.kommonlee.type.functional.Condition1;
import org.elixirian.kommonlee.type.functional.Function1;
import org.elixirian.kommonlee.util.Objects;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class OptionTest
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
    final Option<Object> expected = Option.NONE;

    /* when */
    final Option<Object> actual = Option.optionOf(null);

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual.isNull()).isTrue();
    assertThat(actual.toString()).isEqualTo(expected.toString());
  }

  @Test
  public final void testNoneWithException()
  {
    /* given */
    final Option<Object> option = Option.optionOf(null);

    /* expected */
    expectedException.expect(NoSuchElementException.class)
        .expectMessage(CoreMatchers.equalTo("There is no value but null."));

    /* when */
    option.get();

    /* otherwise-fail */
    fail("It did not throw " + NoSuchElementException.class.getSimpleName() + " when there is no value but null.");
  }

  @Test
  public final void testSome()
  {
    /* given */
    final Option<Integer> expected = new Option<Integer>(999);

    /* when */
    final Option<Integer> actual = Option.optionOf(999);

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
    final int expected = new Option<Integer>(999).hashCode();

    /* when */
    final int actual = Option.optionOf(999)
        .hashCode();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIsNull()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(null);

    /* when */
    final boolean actual = option.isNull();

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNullWithNotNull()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(999);

    /* when */
    final boolean actual = option.isNull();

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNotNull()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(999);

    /* when */
    final boolean actual = option.isNotNull();

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNotNullWithNull()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(null);

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
    final Option<Integer> option = Option.optionOf(999);

    /* when */
    final Integer actual = option.get();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetWithNull()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(null);

    /* expected */
    expectedException.expect(NoSuchElementException.class)
        .expectMessage(CoreMatchers.equalTo("There is no value but null."));

    /* when */
    option.get();

    /* otherwise-fail */
    fail("It did not throw " + NoSuchElementException.class.getSimpleName() + " when there is no value but null.");
  }

  @Test
  public final void testOrElseUseWithNull()
  {
    /* given */
    final Integer expected = null;
    final Option<Integer> option = Option.optionOf(null);

    /* when */
    final Integer actual = option.orElseUse(null);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetOrUse()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Option.optionOf(999);

    /* when */
    final Integer actual = option.orElseUse(111);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetOrUseWithNull()
  {
    /* given */
    final Integer expected = 111;
    final Option<Integer> option = Option.optionOf(null);

    /* when */
    final Integer actual = option.orElseUse(111);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testGetOrGetAnother()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Option.optionOf(999);

    /* when */
    final Integer actual = option.orElseGet(new Suppliable<Integer>()
    {
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
    final Option<Integer> option = Option.optionOf(null);

    /* when */
    final Integer actual = option.orElseGet(new Suppliable<Integer>()
    {
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
    @SuppressWarnings("unused")
    final Integer expected = 999;
    final Option<Integer> option = Option.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class);

    /* when */
    @SuppressWarnings("unused")
    final Integer actual = option.orElseGet(null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Suppliable was null.");
  }

  @Test
  public final void testIfTrue()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Option.optionOf(999);

    /* when */
    final Integer actual = option.ifTrue(new Condition1<Integer>()
    {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 100;
      }
    })
        .get();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIfTrueForFalse()
  {
    /* given */
    final Option<Integer> actual = Option.optionOf(999)
        .ifTrue(new Condition1<Integer>()
        {
          @Override
          public boolean isMet(final Integer input)
          {
            return input < 100;
          }
        });

    /* expected */
    expectedException.expect(NoSuchElementException.class)
        .expectMessage(CoreMatchers.equalTo("There is no value but null."));

    /* when */
    actual.get();

    /* otherwise-fail */
    fail("It did not throw " + NoSuchElementException.class.getSimpleName() + " when there is no value but null.");
  }

  @Test
  public final void testIfTrueWithNullCondition()
  {
    /* given */
    @SuppressWarnings("unused")
    final Integer expected = 999;
    final Option<Integer> option = Option.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class);

    /* when */
    @SuppressWarnings("unused")
    final Integer actual = option.ifTrue(null)
        .get();

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Condition1 was null.");
  }

  @Test
  public final void testIfThenGetOrUse()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Option.optionOf(999);

    /* when */
    final Integer actual = option.ifTrue(new Condition1<Integer>()
    {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 100;
      }
    })
        .orElseUse(111);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIfThenGetOrUseForFalse()
  {
    /* given */
    final Integer expected = 111;
    final Option<Integer> option = Option.optionOf(9);

    /* when */
    final Integer actual = option.ifTrue(new Condition1<Integer>()
    {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 100;
      }
    })
        .orElseUse(111);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIfThenOrGetAnother()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Option.optionOf(999);

    /* when */
    final Integer actual = option.ifTrue(new Condition1<Integer>()
    {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 100;
      }
    })
        .orElseGet(new Suppliable<Integer>()
        {
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
  public final void testIfThenOrGetAnotherForFalse()
  {
    /* given */
    final Integer expected = 111;
    final Option<Integer> option = Option.optionOf(10);

    /* when */
    final Integer actual = option.ifTrue(new Condition1<Integer>()
    {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 100;
      }
    })
        .orElseGet(new Suppliable<Integer>()
        {
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
  public final void testIfThenOrGetAnotherWithNullCondition()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("condition must not be null");

    /* when */
    @SuppressWarnings("unused")
    final Integer actual = option.ifTrue(null)
        .orElseGet(new Suppliable<Integer>()
        {
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
  public final void testIfThenOrGetAnotherWithNullSuppliable()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("suppliable must not be null");

    /* when */
    @SuppressWarnings("unused")
    final Integer actual = option.ifTrue(new Condition1<Integer>()
    {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 100;
      }
    })
        .orElseGet(null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Suppliable was null.");
  }

  @Test
  public final void testMap()
  {
    /* given */
    final int number = 999;
    final String expected = "(" + number + ")";
    final Option<Integer> option = Option.optionOf(number);

    /* when */
    final String actual = option.map(new Function1<Integer, String>()
    {
      @Override
      public String apply(final Integer input)
      {
        return "(" + input + ")";
      }
    })
        .get();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testMapWithNull()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(null);

    final Option<String> actual = option.map(new Function1<Integer, String>()
    {
      @Override
      public String apply(final Integer input)
      {
        return "(" + input + ")";
      }
    });

    /* expected */
    expectedException.expect(NoSuchElementException.class)
        .expectMessage(CoreMatchers.equalTo("There is no value but null."));

    /* when */
    actual.get();

    /* otherwise-fail */
    fail("It did not throw " + NoSuchElementException.class.getSimpleName() + " when there is no value but null.");
  }

  @Test
  public final void testMapWithNullMapper()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("mapper must not be null");

    /* when */
    @SuppressWarnings("unused")
    final Object actual = option.map(null)
        .get();

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Function1 was null.");
  }

  static class SomeBean
  {
    Long id;
    String name;

    public SomeBean(final Long id, final String name)
    {
      this.id = id;
      this.name = name;
    }

    @Override
    public int hashCode()
    {
      return Objects.hash(id, name);
    }

    @Override
    public boolean equals(final Object obj)
    {
      if (this == obj)
      {
        return true;
      }
      final SomeBean that = castIfInstanceOf(SomeBean.class, obj);
      return that != null && (equal(this.id, that.id) && equal(this.name, that.name));
    }
  }

  @Test
  public final void testMapIfTrue()
  {
    /* given */
    final SomeBean expected = new SomeBean(null, "Unknown");
    final Option<SomeBean> option = Option.optionOf(new SomeBean(null, null));

    /* when */
    final SomeBean actual = option.ifTrue(new Condition1<SomeBean>()
    {
      @Override
      public boolean isMet(final SomeBean input)
      {
        return input.id == null;
      }
    })
        .doThis(new VoidFunction1<SomeBean>()
        {
          @Override
          public void apply(final SomeBean input)
          {
            input.name = "Unknown";
          }
        })
        .get();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testMapIfFalse()
  {
    /* given */
    final SomeBean expected = new SomeBean(null, "Unknown");
    final Option<SomeBean> option = Option.optionOf(new SomeBean(null, null));

    /* when */
    final SomeBean actual = option.ifFalse(new Condition1<SomeBean>()
    {
      @Override
      public boolean isMet(final SomeBean input)
      {
        return input.id != null;
      }
    })
        .doThis(new VoidFunction1<SomeBean>()
        {
          @Override
          public void apply(final SomeBean input)
          {
            input.name = "Unknown";
          }
        })
        .get();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIfFalseWithNullCondition()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(7);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("condition must not be null");

    /* when */
    @SuppressWarnings("unused")
    final Option<?> actual = option.ifFalse(null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Condition1 was null.");
  }

  @Test
  public final void testIfTrueMapOrElseGet()
  {
    /* given */
    final int number = 7;
    final String expected = "#" + number;
    final Option<Integer> option = Option.optionOf(number);

    /* when */
    final String actual = option.ifTrue(new Condition1<Integer>()
    {
      @Override
      public boolean isMet(final Integer input)
      {
        return input < 10;
      }
    })
        .map(new Function1<Integer, String>()
        {
          @Override
          public String apply(final Integer input)
          {
            return "#" + input;
          }
        })
        .orElseGet(new Suppliable<String>()
        {
          @Override
          public String supply()
          {
            return "Nothing";
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIfTrueMapOrElseGet2()
  {
    /* given */
    final int number = 17;
    final String expected = "Nothing";
    final Option<Integer> option = Option.optionOf(number);

    /* when */
    final String actual = option.ifTrue(new Condition1<Integer>()
    {
      @Override
      public boolean isMet(final Integer input)
      {
        return input < 10;
      }
    })
        .map(new Function1<Integer, String>()
        {
          @Override
          public String apply(final Integer input)
          {
            return "#" + input;
          }
        })
        .orElseGet(new Suppliable<String>()
        {
          @Override
          public String supply()
          {
            return "Nothing";
          }
        });

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testDoThis()
  {
    /* given */
    final int expected = 999 * 123;
    final Option<Integer> option = Option.optionOf(999);
    final int[] number = { 123 };

    /* when */
    option.ifTrue(new Condition1<Integer>()
    {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 0;
      }
    })
        .doThis(new VoidFunction1<Integer>()
        {
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
  public final void testDoThis2()
  {
    /* given */
    final int expected = 123;
    final Option<Integer> option = Option.optionOf(0);
    final int[] number = { 123 };

    /* when */
    option.ifTrue(new Condition1<Integer>()
    {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 0;
      }
    })
        .doThis(new VoidFunction1<Integer>()
        {
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
    final Option<Integer> option = Option.optionOf(999);
    final int[] number = { 123 };

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("condition must not be null");

    /* when */
    option.ifTrue(null)
        .doThis(new VoidFunction1<Integer>()
        {
          @Override
          public void apply(final Integer input)
          {
            number[0] = number[0] * input;
          }
        });

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when Condition1 was null.");
  }

  @Test
  public final void testIfTrueDoThisWithNullVoidFunction()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("function must not be null");

    /* when */
    option.ifTrue(new Condition1<Integer>()
    {
      @Override
      public boolean isMet(final Integer input)
      {
        return input > 0;
      }
    })
        .doThis(null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when VoidFunction1 was null.");
  }

  @Test
  public final void testOrElseDoIfNotNull()
  {
    /* given */
    final int two = 2;
    final int expected = 123;
    final Option<Integer> option = Option.optionOf(999);
    final int[] number = { expected };

    /* when */
    option.orElseDo(new Runnable()
    {
      @Override
      public void run()
      {
        number[0] = number[0] * two;
      }
    });
    final int actual = number[0];

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testOrElseDoWithNull()
  {
    /* given */
    final int two = 2;
    final int expected = 123 * two;
    final Option<Integer> option = Option.optionOf(null);
    final int[] number = { 123 };

    /* when */
    option.orElseDo(new Runnable()
    {
      @Override
      public void run()
      {
        number[0] = number[0] * two;
      }
    });
    final int actual = number[0];

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testOrElseDoWithNullFunction()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("The runnable must not be null");

    /* when */
    option.orElseDo(null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when VoidFunction1 was null.");
  }

  @Test
  public final void testDoIfNotNullWithNullAndNullFunction()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(null);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessageContains("The runnable must not be null");

    /* when */
    option.orElseDo(null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when VoidFunction1 was null.");
  }

  @Test
  public final void testGetOrThrow()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Option.optionOf(999);

    /* when */
    final Integer actual = option.getOrThrow(new Suppliable<IllegalArgumentException>()
    {
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
    final Option<Integer> option = Option.optionOf(null);

    /* expected */
    expectedException.expect(IllegalArgumentException.class)
        .expectMessage(CoreMatchers.equalTo("The value must not be null."));

    /* when */
    @SuppressWarnings("unused")
    final Integer actual = option.getOrThrow(new Suppliable<IllegalArgumentException>()
    {
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
  public final void testIfThenOrThrow()
  {
    /* given */
    final Integer expected = 999;
    final Option<Integer> option = Option.optionOf(999);

    /* when */
    final Integer actual = option.ifTrue(new Condition1<Integer>()
    {

      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() > 0;
      }
    })
        .getOrThrow(new Suppliable<IllegalArgumentException>()
        {
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
  public final void testIfThenOrThrowWithFalse()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(999);

    /* expected */
    expectedException.expect(IllegalArgumentException.class)
        .expectMessage(CoreMatchers.equalTo("The value must be a negative integer."));

    /* when */
    @SuppressWarnings("unused")
    final Integer actual = option.ifTrue(new Condition1<Integer>()
    {

      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() < 0;
      }
    })
        .getOrThrow(new Suppliable<IllegalArgumentException>()
        {
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
  public final void testIfThenOrThrowWithNullCondition()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessage(CoreMatchers.equalTo("The condition must not be null."));

    /* when */
    @SuppressWarnings("unused")
    final Integer actual = option.ifTrue(null)
        .getOrThrow(new Suppliable<IllegalArgumentException>()
        {
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
  public final void testIfThenOrThrowWithNullThrowableSupplier()
  {
    /* given */
    final Option<Integer> option = Option.optionOf(999);

    /* expected */
    expectedException.expect(NullPointerException.class)
        .expectMessage(CoreMatchers.equalTo("The exceptionSuppliable must not be null."));

    /* when */
    @SuppressWarnings("unused")
    final Integer actual = option.ifTrue(new Condition1<Integer>()
    {
      @Override
      public boolean isMet(final Integer input)
      {
        return input.intValue() > 0;
      }
    })
        .getOrThrow((Suppliable<RuntimeException>) null);

    /* otherwise-fail */
    fail("It did not throw " + NullPointerException.class.getSimpleName() + " when exceptionSuppliable was null.");
  }

  @Test
  public final void testEqualsObjectForNone()
  {
    /* given */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    final Option<Integer> another = (Option) Option.NONE;
    final Option<Integer> option = Option.optionOf(null);

    /* when */
    final boolean actual = option.equals(another);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testEqualsObjectForNone2()
  {
    /* given */
    final Option<Integer> another = new Option<Integer>(999);
    final Option<Integer> option = Option.optionOf(null);

    /* when */
    final boolean actual = option.equals(another);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testEqualsObjectForSome()
  {
    /* given */
    final Option<Integer> another = new Option<Integer>(999);
    final Option<Integer> option = Option.optionOf(999);

    /* when */
    final boolean actual = option.equals(another);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testEqualsObjectForSome2()
  {
    /* given */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    final Option<Integer> another = (Option) Option.NONE;
    final Option<Integer> option = Option.optionOf(999);

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
    final Option<Integer> option = Option.optionOf(null);

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
    final Option<Integer> option = Option.optionOf(999);

    /* when */
    final String actual = option.toString();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }
}
