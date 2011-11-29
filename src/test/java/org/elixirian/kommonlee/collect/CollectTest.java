package org.elixirian.kommonlee.collect;

import static org.junit.Assert.*;

import org.elixirian.kommonlee.type.Condition1;
import org.elixirian.kommonlee.type.Function1;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CollectTest
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
  public final void test()
  {
    final ReadableList<String> list =
      new ReadableArrayList<String>("Kevin", "Lee", "blah", "blah blah", null, "not null");
    final ReadableList<Integer> list2 =
      new ReadableArrayList<Integer>(1, 2, 3, 4, 5, 6, 7, 88, 999, 4, 5, 6, 7, 88, 999, 2, 3, 4, 5, 6, 7, 5, 5, 6, 232,
          8);
    final ReadableList<?> list3 =
      new ReadableArrayList<Object>(1, 2, 3, 4, 5, 6, 7, 88, 999, 2, 3, 4, 5, 6, 7, 5, 5, 6, 232, 8);

    System.out.println(list);
    System.out.println(list2);
    System.out.println(list3);

    System.out.println(list2.select(new Condition1<Integer>() {
      @Override
      public boolean isMet(final Integer input)
      {
        return 5 < input.intValue();
      }
    }));

    /* @formatter:off */
    System.out.println(list2.map(new Function1<Integer, Boolean>()
      { @Override public Boolean apply(final Integer input) { return 5 < input.intValue(); } }));
    /* @formatter:on */

    /* @formatter:off */
    System.out.println(list2.mapSelectively(new Condition1<Integer>()
      { @Override public boolean isMet(final Integer input) { return 5 < input.intValue(); } },
      new Function1<Integer, String>()
      { @Override public String apply(final Integer input) { return "{Yeah bigger than five: " + input + "}"; } }));
    /* @formatter:on */
  }
}
