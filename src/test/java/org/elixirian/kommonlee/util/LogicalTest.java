package org.elixirian.kommonlee.util;

import static org.elixirian.kommonlee.util.Logical.*;
import static org.elixirian.kommonlee.util.Objects.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-12-04)
 */
public class LogicalTest
{
  int int1A = 1;
  int int1B = 1;
  int int2A = 10;
  int int2B = 10;
  int int3A = 100;
  int int3B = 100;
  int int4A = 1000;
  int int4B = 1000;
  int int5A = 10000;
  int int5B = 10000;
  int int6A = 100000;
  int int6B = 100000;
  int int7A = 1000000;
  int int7B = 1000000;
  int int8A = 10000000;
  int int8B = 10000000;

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
  public final void testAndBooleanBoolean()
  {
    assertTrue(and(true, true));

    /* @formatter:off */
		assertTrue(and(int1A == int1B, int2A == int2B));
		assertTrue(and(equal("Kevin", "Kevin"), equal("Lee", "Lee")));
		assertTrue(and(equal(int1A, int1B), equal(int2A, int2B)));
		assertTrue(and(equal(10F, 10F), equal(10.1234D, 10.1234D)));

		for (int i = 0, size = 4 - 1; i < size; i++)
		{
			assertFalse(and(((i >>> 1) & 1) == 1, 
							(i & 1) == 1));
		}

		assertFalse(and(int1A == int2A, int1B == int2B));
		assertFalse(and(equal("Kevin", "Lee"), equal("Lee", "Kevin")));
		assertFalse(and(equal(int1A, int2A), equal(int1B, int2B)));
		assertFalse(and(equal(10F, 99F), equal(10.1234D, 1234.10D)));
		/* @formatter:on */
  }

  @Test
  public final void testAndBooleanBooleanBoolean()
  {
    assertTrue(and(true, true, true));

    /* @formatter:off */
		assertTrue(and(int1A == int1B, int2A == int2B, int3A == int3B));
		assertTrue(and(equal("Kevin", "Kevin"), equal("SeongHyun", "SeongHyun"), equal("Lee", "Lee")));
		assertTrue(and(equal("Kevin", "Kevin"), equal(int1A, int1B), equal(int2A, int2B)));

		for (int i = 0, size = 8 - 1; i < size; i++)
		{
			assertFalse(and(((i >>> 2) & 1) == 1, 
							((i >>> 1) & 1) == 1, 
							(i & 1) == 1));
		}

		assertFalse(and(int1A == int2A, int2A == int2B, 1 == int1A));
		assertFalse(and(equal("Kevin", "Lee"), equal("SeongHyun", "SeongHyun"), equal("Lee", "Lee")));
		assertFalse(and(equal("Kevin", "Kevin"), equal("SeongHyun", "Kevin"), equal("Lee", "Lee")));
		assertFalse(and(equal("Kevin", "Kevin"), equal("SeongHyun", "SeongHyun"), equal("SeongHyun", "Lee")));
		/* @formatter:on */
  }

  @Test
  public final void testAndBooleanBooleanBooleanBoolean()
  {
    assertTrue(and(true, true, true, true));

    /* @formatter:off */
		assertTrue(and(int1A == int1B, 
					   int2A == int2B, 
					   int3A == int3B, 
					   1 == int1A));
		assertTrue(and(equal("Kevin", "Kevin"), 
					   equal("SeongHyun", "SeongHyun"), 
					   equal("Lee", "Lee"),
					   equal("Kevin Lee", "Kevin Lee")));
		assertTrue(and(equal("Kevin", "Kevin"), 
					   equal(int1A, int1B), 
					   equal(int2A, int2B), 
					   equal(1, int1A)));

		for (int i = 0, size = 16 - 1; i < size; i++)
		{
			assertFalse(and(((i >>> 3) & 1) == 1,
							((i >>> 2) & 1) == 1, 
							((i >>> 1) & 1) == 1, 
							(i & 1) == 1));
		}

		assertFalse(and(int1A == int2A, 
						int2A == int2B, 
						1 == int1A, 
						int1A == int1B));

		assertFalse(and(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee")));

		assertFalse(and(equal("Kevin", "Kevin"), 
						equal("SeongHyun", "Kevin"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee")));

		assertFalse(and(equal("Kevin", "Kevin"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("SeongHyun", "Lee"),
						equal("Kevin Lee", "Kevin Lee")));
		/* @formatter:on */
  }

  @Test
  public final void testAndBooleanBooleanBooleanBooleanBoolean()
  {
    assertTrue(and(true, true, true, true, true));

    /* @formatter:off */
		assertTrue(and(int1A == int1B, 
					   int2A == int2B, 
					   int3A == int3B, 
					   int4A == int4B, 
					   int5A == int5B));

		assertTrue(and(equal("Kevin", "Kevin"), 
					   equal("SeongHyun", "SeongHyun"), 
					   equal("Lee", "Lee"),
					   equal("Kevin Lee", "Kevin Lee"), 
					   equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee")));

		assertTrue(and(equal("Kevin", "Kevin"), 
					   equal(int1A, int1B), 
					   equal(int2A, int2B), 
					   equal(int3A, int3B),
					   equal(int4A, int4B)));

		for (int i = 0, size = 32 - 1; i < size; i++)
		{
			assertFalse(and(((i >>> 4) & 1) == 1, 
							((i >>> 3) & 1) == 1,
							((i >>> 2) & 1) == 1, 
							((i >>> 1) & 1) == 1, 
							(i & 1) == 1));
		}
		
		assertFalse(and(int1A == int2A, 
						int2A == int2B, 
						int3A == int3B, 
						int4A == int4B, 
						int5A == int5B));

		assertFalse(and(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee")));

		assertFalse(and(equal("Kevin", "Kevin"), 
						equal("SeongHyun", "Kevin"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee")));

		assertFalse(and(equal("Kevin", "Kevin"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("SeongHyun", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee")));
		/* @formatter:on */
  }

  @Test
  public final void testAndBooleanBooleanBooleanBooleanBooleanBooleanArray()
  {
    assertTrue(and(true, true, true, true, true, true, true, true));

    /* @formatter:off */
		assertTrue(and(int1A == int1B, 
					   int2A == int2B, 
					   int3A == int3B, 
					   int4A == int4B, 
					   int5A == int5B, 
					   int6A == int6B, 
					   int7A == int7B, 
					   int8A == int8B));

		assertTrue(and(equal("Kevin", "Kevin"), 
					   equal("SeongHyun", "SeongHyun"), 
					   equal("Lee", "Lee"),
					   equal("Kevin Lee", "Kevin Lee"), 
					   equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
					   equal(int1A, int1B), 
					   equal(false, false), 
					   equal(Boolean.TRUE, Boolean.TRUE)));

		assertTrue(and(equal("Kevin", "Kevin"), 
					   equal(int1A, int1B), 
					   equal(int2A, int2B), 
					   equal(int3A, int3B),
					   equal(int4A, int4B),
					   equal(int5A, int5B),
					   equal(int6A, int6B),
					   equal(int7A, int7B),
					   equal(int8A, int8B)));
		/* @formatter:on */

    /* @formatter:off */
		for (int i = 0, size = 256 - 1; i < size; i++)
		{
			assertFalse(and(((i >>> 7) & 1) == 1,
							((i >>> 6) & 1) == 1, 
							((i >>> 5) & 1) == 1, 
							((i >>> 4) & 1) == 1, 
							((i >>> 3) & 1) == 1,
							((i >>> 2) & 1) == 1, 
							((i >>> 1) & 1) == 1, 
							(i & 1) == 1));
		}

		assertFalse(and(int1A == int2A, 
						int2A == int2B, 
						int3A == int3B, 
						int4A == int4B, 
						int5A == int5B, 
						int6A == int6B, 
						int7A == int7B, 
						int8A == int8B));

		assertFalse(and(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
						equal(int1A, int1B), 
						equal(false, false), 
						equal(Boolean.TRUE, Boolean.TRUE)));

		assertFalse(and(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
						equal(int1A, int1B), 
						equal(false, true), 
						equal(Boolean.TRUE, Boolean.TRUE)));

		assertFalse(and(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
						equal(int1A, int1B), 
						equal(false, false), 
						equal(Boolean.TRUE, Boolean.FALSE)));
		/* @formatter:on */
  }

  @Test
  public final void testOrBooleanBoolean()
  {
    /* @formatter:off */
		/* true */
		assertTrue(or(true, 
					  true));

		assertTrue(or(int1A == int1B, 
					  int2A == int2B));

		assertTrue(or(equal("Kevin", "Kevin"), 
					  equal("Lee", "Lee")));

		assertTrue(or(equal("Kevin", "Kevin"), 
					  equal(int1A, int1B)));

		for (int i = 1, size = 4; i < size; i++)
		{
			assertTrue(or(((i >>> 1) & 1) == 1, 
						  (i & 1) == 1));
		}
		
		assertTrue(or(int1A == int1B, 
					  int2A == int3B));

		assertTrue(or(int1A == int2A, 
					  int2A == int2B));

		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("Kevin Lee", "Kevin Lee")));
		
		assertTrue(or(equal("Kevin", "Lee"), 
					  equal(int5A, int5B)));

		assertTrue(or(equal("Kevin Lee", "Kevin Lee"), 
					  equal(false, true)));

		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("Kevin Lee", "Kevin Lee")));

		/* false */
		assertFalse(or(false,
					   false));

		assertFalse(or(equal("Kevin", "Lee"), 
					   equal("Kevin Lee", "Kevin SeongHyun Lee")));
		/* @formatter:on */
  }

  @Test
  public final void testOrBooleanBooleanBoolean()
  {
    /* @formatter:off */
		/* true */
		assertTrue(or(true, 
					  true, 
					  true));

		assertTrue(or(int1A == int1B, 
					  int2A == int2B, 
					  int3A == int3B));

		assertTrue(or(equal("Kevin", "Kevin"), 
					  equal("SeongHyun", "SeongHyun"), 
					  equal("Lee", "Lee")));

		assertTrue(or(equal("Kevin", "Kevin"), 
					  equal(int1A, int1B), 
					  equal(int2A, int2B)));

		for (int i = 1, size = 8; i < size; i++)
		{
			assertTrue(or(((i >>> 2) & 1) == 1, 
						  ((i >>> 1) & 1) == 1, 
						  (i & 1) == 1));
		}
		
		assertTrue(or(int1A == int1B, 
					  int2A == int3B, 
					  int3A == int2B));

		assertTrue(or(int1A == int2A, 
					  int2A == int2B, 
					  int3A == int3B));

		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("Lee", "Lee"), 
					  equal("Kevin Lee", "Kevin Lee")));
		
		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("SeongHyun", ""), 
					  equal(int5A, int5B)));

		assertTrue(or(equal("Kevin Lee", "Kevin Lee"), 
					  equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
					  equal(false, true)));

		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("Lee", "Lee"), 
					  equal("Kevin Lee", "Kevin Lee")));

		/* false */
		assertFalse(or(false,
					   false, 
					   false));

		assertFalse(or(equal("Kevin", "Lee"), 
					   equal("SeongHyun", "Kevin"), 
					   equal("Kevin Lee", "Kevin SeongHyun Lee")));
		/* @formatter:on */
  }

  @Test
  public final void testOrBooleanBooleanBooleanBoolean()
  {
    /* @formatter:off */
		/* true */
		assertTrue(or(true, 
					  true, 
					  true, 
					  true));

		assertTrue(or(int1A == int1B, 
					  int2A == int2B, 
					  int3A == int3B, 
					  int4A == int4B));

		assertTrue(or(equal("Kevin", "Kevin"), 
					  equal("SeongHyun", "SeongHyun"), 
					  equal("Lee", "Lee"), 
					  equal("Kevin Lee", "Kevin Lee")));

		assertTrue(or(equal("Kevin", "Kevin"), 
					  equal(int1A, int1B), 
					  equal(int2A, int2B), 
					  equal(int3A, int3B)));

		for (int i = 1, size = 16; i < size; i++)
		{
			assertTrue(or(((i >>> 3) & 1) == 1,
						  ((i >>> 2) & 1) == 1, 
						  ((i >>> 1) & 1) == 1, 
						  (i & 1) == 1));
		}
		
		assertTrue(or(int1A == int4A, 
					  int2A == int3B, 
					  int3A == int2B, 
					  int4A == int4B));

		assertTrue(or(int1A == int2A, 
					  int2A == int2B, 
					  int3A == int3B, 
					  int4A == int4B));

		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("Lee", "Lee"), 
					  equal("Kevin Lee", "Kevin Lee"), 
					  equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee")));
		
		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("SeongHyun", ""), 
					  equal("Lee", "Kevin Lee"), 
					  equal(int5A, int5B)));

		assertTrue(or(equal("Kevin Lee", "Kevin Lee"), 
					  equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
					  equal(int1A, int1B), 
					  equal(false, true)));

		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("SeongHyun", "SeongHyun"), 
					  equal("Lee", "Lee"), 
					  equal("Kevin Lee", "Kevin Lee")));

		/* false */
		assertFalse(or(false,
					   false, 
					   false, 
					   false));

		assertFalse(or(equal("Kevin", "Lee"), 
					   equal("SeongHyun", "Kevin"), 
					   equal("Lee", "Kevin SeongHyun Lee"),
					   equal("Kevin Lee", "Kevin SeongHyun Lee")));
		/* @formatter:on */
  }

  @Test
  public final void testOrBooleanBooleanBooleanBooleanBoolean()
  {
    /* @formatter:off */
		/* true */
		assertTrue(or(true, 
					  true, 
					  true, 
					  true, 
					  true));

		assertTrue(or(int1A == int1B, 
					  int2A == int2B, 
					  int3A == int3B, 
					  int4A == int4B, 
					  int5A == int5B));

		assertTrue(or(equal("Kevin", "Kevin"), 
					  equal("SeongHyun", "SeongHyun"), 
					  equal("Lee", "Lee"), 
					  equal("Kevin Lee", "Kevin Lee"), 
					  equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee")));

		assertTrue(or(equal("Kevin", "Kevin"), 
					  equal(int1A, int1B), 
					  equal(int2A, int2B), 
					  equal(int3A, int3B),
					  equal(int4A, int4B)));

		for (int i = 1, size = 32; i < size; i++)
		{
			assertTrue(or(((i >>> 4) & 1) == 1, 
						  ((i >>> 3) & 1) == 1,
						  ((i >>> 2) & 1) == 1, 
						  ((i >>> 1) & 1) == 1, 
						  (i & 1) == 1));
		}

		assertTrue(or(int1A == int2A, 
					  int2A == int2B, 
					  int3A == int3B, 
					  int4A == int4B, 
					  int5A == int5B));

		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("Lee", "Lee"), 
					  equal("Kevin Lee", "Kevin Lee"), 
					  equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
					  equal(int1A, int1B)));
		
		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("SeongHyun", ""), 
					  equal("Lee", "Kevin Lee"), 
					  equal(int5B, int1B), 
					  equal(Boolean.TRUE, Boolean.TRUE)));

		assertTrue(or(equal("Kevin Lee", "Kevin Lee"), 
					  equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
					  equal(int1A, int1B), 
					  equal(false, true), 
					  equal(Boolean.TRUE, Boolean.TRUE)));

		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("SeongHyun", "SeongHyun"), 
					  equal("Lee", "Lee"), 
					  equal("Kevin Lee", "Kevin Lee"), 
					  equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee")));

		/* false */
		assertFalse(or(false,
					   false, 
					   false, 
					   false, 
					   false));

		assertFalse(or(equal("Kevin", "Lee"), 
					   equal("SeongHyun", "Kevin"), 
					   equal("Lee", "Kevin SeongHyun Lee"),
					   equal("Kevin Lee", ""), 
					   equal("Kevin Lee", "Kevin SeongHyun Lee")));
		/* @formatter:on */
  }

  @Test
  public final void testOrBooleanBooleanBooleanBooleanBooleanBooleanArray()
  {
    /* @formatter:off */
		/* true */
		assertTrue(or(true, 
					  true, 
					  true, 
					  true, 
					  true, 
					  true, 
					  true, 
					  true));

		assertTrue(or(int1A == int1B, 
					  int2A == int2B, 
					  int3A == int3B, 
					  int4A == int4B, 
					  int5A == int5B, 
					  int6A == int6B, 
					  int7A == int7B, 
					  int8A == int8B));

		assertTrue(or(equal("Kevin", "Kevin"), 
					  equal("SeongHyun", "SeongHyun"), 
					  equal("Lee", "Lee"),
					  equal("Kevin Lee", "Kevin Lee"), 
					  equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
					  equal(int1A, int1B), 
					  equal(false, false), 
					  equal(Boolean.TRUE, Boolean.TRUE)));

		assertTrue(or(equal("Kevin", "Kevin"), 
					  equal(int1A, int1B), 
					  equal(int2A, int2B), 
					  equal(int3A, int3B),
					  equal(int4A, int4B),
					  equal(int5A, int5B),
					  equal(int6A, int6B),
					  equal(int7A, int7B),
					  equal(int8A, int8B)));
		/* @formatter:on */

    /* @formatter:off */
		for (int i = 1, size = 256; i < size; i++)
		{
			assertTrue(or(((i >>> 7) & 1) == 1,
						  ((i >>> 6) & 1) == 1, 
						  ((i >>> 5) & 1) == 1, 
						  ((i >>> 4) & 1) == 1, 
						  ((i >>> 3) & 1) == 1,
						  ((i >>> 2) & 1) == 1, 
						  ((i >>> 1) & 1) == 1, 
						  (i & 1) == 1));
		}

		assertTrue(or(int1A == int2A, 
					  int2A == int2B, 
					  int3A == int3B, 
					  int4A == int4B, 
					  int5A == int5B, 
					  int6A == int6B, 
					  int7A == int7B, 
					  int8A == int8B));

		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("SeongHyun", "SeongHyun"), 
					  equal("Lee", "Lee"),
					  equal("Kevin Lee", "Kevin Lee"), 
					  equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
					  equal(int1A, int1B), 
					  equal(false, false), 
					  equal(Boolean.TRUE, Boolean.TRUE)));
		
		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("SeongHyun", ""), 
					  equal("Lee", "Kevin Lee"),
					  equal("Kevin SeongHyun Lee", "Kevin Lee"), 
					  equal("Kevin", "Kevin SeongHyun Lee"), 
					  equal(int5B, int1B), 
					  equal(false, true), 
					  equal(Boolean.TRUE, Boolean.TRUE)));

		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("SeongHyun", "SeongHyun"), 
					  equal("Lee", "Lee"),
					  equal("Kevin Lee", "Kevin Lee"), 
					  equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
					  equal(int1A, int1B), 
					  equal(false, true), 
					  equal(Boolean.TRUE, Boolean.TRUE)));

		assertTrue(or(equal("Kevin", "Lee"), 
					  equal("SeongHyun", "SeongHyun"), 
					  equal("Lee", "Lee"),
					  equal("Kevin Lee", "Kevin Lee"), 
					  equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
					  equal(int1A, int1B), 
					  equal(false, false), 
					  equal(Boolean.TRUE, Boolean.FALSE)));

		/* flase */
		assertFalse(or(false,
					   false, 
					   false, 
					   false, 
					   false,
					   false, 
					   false, 
					   false));

		assertFalse(or(equal("Kevin", "Lee"), 
					   equal("SeongHyun", "Kevin"), 
					   equal("Lee", "Kevin SeongHyun Lee"),
					   equal("Kevin Lee", ""), 
					   equal("Kevin Lee", "Kevin SeongHyun Lee"), 
					   equal(int1A, int7A), 
					   equal(false, true), 
					   equal(Boolean.FALSE, Boolean.TRUE)));
		/* @formatter:on */
  }

  @Test
  public final void testXorBooleanBoolean()
  {
    /* @formatter:off */
		/* false */
		assertFalse(xor(true, 
						true));

		assertFalse(xor(int1A == int1B, 
						int2A == int2B));

		assertFalse(xor(equal("Kevin", "Kevin"), 
						equal(false, false)));

		assertFalse(xor(equal("Kevin", "Kevin"), 
						equal(int1A, int1B)));

		/* true and false */
		for (int i = 1, size = 4 - 1; i < size; i++)
		{
			final boolean b1 = ((i >>> 1) & 1) == 1; 
			final boolean b2 = (i & 1) == 1;
			if (b1 ^ b2)
			{
				assertTrue(xor(b1, b2));
			}
			else
			{
				assertFalse(xor(b1, b2));
			}
		}

		/* true */
		assertTrue(xor(int1A == int2A,		// false 
					   int2A == int2B));	// true

		assertTrue(xor(equal("Kevin", "Lee"), 	// false
					   equal(int1A, int1B)));	// true
		
		assertTrue(xor(equal(int5B, int1B), 					// false
					   equal(Boolean.TRUE, Boolean.TRUE)));	// true

		assertTrue(xor(equal("SeongHyun", "SeongHyun"), 		// true
					   equal(false, true)));				// false

		assertTrue(xor(equal("Kevin Lee", "Kevin Lee"), 			// true
					   equal(Boolean.TRUE, Boolean.FALSE)));		// false

		/* false */
		assertFalse(xor(false,
						false));

		assertFalse(xor(equal("Kevin", "Lee"), 
						equal(Boolean.FALSE, Boolean.TRUE)));
		/* @formatter:on */
  }

  @Test
  public final void testXorBooleanBooleanBoolean()
  {
    /* @formatter:off */
		/* true */
		assertTrue(xor(true, 
					   true, 
					   true));

		assertTrue(xor(int1A == int1B, 
					   int2A == int2B, 
					   int3A == int3B));

		assertTrue(xor(equal("Kevin", "Kevin"), 
					   equal(false, false), 
					   equal(Boolean.TRUE, Boolean.TRUE)));

		assertTrue(xor(equal("Kevin", "Kevin"), 
					   equal(int1A, int1B), 
					   equal(int2A, int2B)));
		
		/* false */
		assertFalse(xor(true, 
						true, 
						false));

		assertFalse(xor(int1A == int5B,		// false
						int4A == int4B,		// true
						int5A == int5B));	// true
		
		assertFalse(xor(equal("Kevin Lee", "Lee"), 				// false
						equal(false, false), 					// true
						equal(Boolean.TRUE, Boolean.TRUE)));	// true
		
		assertFalse(xor(equal("Kevin", "Kevin"), 	// true
						equal(int1A, int1B), 		// true
						equal(int2A, int7B)));		// false

		/* true and false */
		for (int i = 0, size = 8; i < size; i++)
		{
			final boolean b1 = ((i >>> 2) & 1) == 1;
			final boolean b2 = ((i >>> 1) & 1) == 1;
			final boolean b3 = (i & 1) == 1;
			if (b1 ^ b2 ^ b3)
			{
				assertTrue(xor(b1, b2, b3));
			}
			else
			{
				assertFalse(xor(b1, b2, b3));
			}
		}

		/* true */
		assertTrue(xor(int1A == int2A,		// false
					   int2A == int2B, 		// true
					   int5A == int7B));	// false

		assertTrue(xor(equal("Kevin", "Kevin"), 					// true
					   equal("Lee", "Lee"),						// true
					   equal(int1A, int1B)));					// true
		
		assertTrue(xor(equal(int5B, int1B), 						// false
					   equal(false, true), 					// false
					   equal(Boolean.TRUE, Boolean.TRUE)));		// true

		/* false */
		assertFalse(xor(equal("Kevin", "Lee"), 					// false
						equal(false, false), 					// true
						equal(int2A, int2B)));					// true

		assertFalse(xor(equal("Lee", "Lee"),						// true
						equal(int1A, int1B), 						// true
						equal(Boolean.TRUE, Boolean.FALSE)));		// false

		assertFalse(xor(false,
						false, 
						false));

		assertFalse(xor(equal("Kevin", "Lee"), 
						equal(int1A, int7A), 
						equal(Boolean.FALSE, Boolean.TRUE)));
		/* @formatter:on */
  }

  @Test
  public final void testXorBooleanBooleanBooleanBoolean()
  {
    /* @formatter:off */
		/* false */
		assertFalse(xor(true, 
						true, 
						true, 
						true));

		assertFalse(xor(int1A == int1B, 
						int2A == int2B, 
						int3A == int3B, 
						int4A == int4B));

		assertFalse(xor(equal("Kevin", "Kevin"), 
						equal(int1A, int1B), 
						equal(false, false), 
						equal(Boolean.TRUE, Boolean.TRUE)));

		assertFalse(xor(equal("Kevin", "Kevin"), 
						equal(int1A, int1B), 
						equal(int2A, int2B), 
						equal(int3A, int3B)));
		
		/* true */
		assertTrue(xor(true, 
					   true, 
					   true, 
					   false));

		assertTrue(xor(int1A == int1B, 		// true
					   int2A == int7B, 		// false
					   int3A == int3B, 		// true
					   int4A == int4B));	// true
		
		assertTrue(xor(equal("Kevin", "Kevin Lee"),				// false 
					   equal(int1A, int1B), 					// true
					   equal(false, false), 					// true
					   equal(Boolean.TRUE, Boolean.TRUE)));		// true
		
		assertTrue(xor(equal("Kevin", "Kevin"), 					// true
					   equal(int7A, int1B), 					// false
					   equal(int2A, int2B), 					// true
					   equal(int3A, int3B)));					// true

		/* true and false */
		for (int i = 0, size = 16; i < size; i++)
		{
			final boolean b1 = ((i >>> 3) & 1) == 1;
			final boolean b2 = ((i >>> 2) & 1) == 1;
			final boolean b3 = ((i >>> 1) & 1) == 1;
			final boolean b4 = (i & 1) == 1;
			if (b1 ^ b2 ^ b3 ^ b4)
			{
				assertTrue(xor(b1, b2, b3, b4));
			}
			else
			{
				assertFalse(xor(b1, b2, b3, b4));
			}
		}

		/* true */
		assertTrue(xor(int1A == int2A,		// false
					   int2A == int2B, 		// true
					   int3A == int3B, 		// true
					   int4A == int4B));	// true

		assertTrue(xor(equal("Kevin", "Lee"), 								// false
					   equal(int1A, int1B), 								// true
					   equal(false, false), 								// true
					   equal(Boolean.TRUE, Boolean.TRUE)));					// true
		
		assertTrue(xor(equal("Kevin", "Lee"), 								// false
					   equal("SeongHyun", ""), 								// false
					   equal(false, true), 								// false
					   equal(Boolean.TRUE, Boolean.TRUE)));					// true

		/* false */
		assertFalse(xor(equal("Kevin", "Lee"),	 							// false
						equal("Lee", "Lee"),								// true
						equal(int1A, int1B), 								// true
						equal(false, true)));								// false

		assertFalse(xor(equal("Kevin", "Kevin"), 							// true
						equal("Lee", "Lee"),								// true
						equal("Kevin SeongHyun Lee", "SeongHyun Lee"), 		// false
					   	equal(Boolean.TRUE, Boolean.FALSE)));				// false

		assertFalse(xor(false,
						false, 
						false, 
						false));

		assertFalse(xor(equal("Kevin", "Lee"), 
						equal(int1A, int7A), 
						equal(false, true), 
						equal(Boolean.FALSE, Boolean.TRUE)));
		/* @formatter:on */
  }

  @Test
  public final void testXorBooleanBooleanBooleanBooleanBoolean()
  {
    /* @formatter:off */
		/* true */
		assertTrue(xor(true, 
					   true, 
					   true, 
					   true, 
					   true));

		assertTrue(xor(int1A == int1B, 
					   int2A == int2B, 
					   int3A == int3B, 
					   int4A == int4B, 
					   int5A == int5B));

		assertTrue(xor(equal("Kevin", "Kevin"), 
					   equal("Kevin Lee", "Kevin Lee"), 
					   equal(int1A, int1B), 
					   equal(false, false), 
					   equal(Boolean.TRUE, Boolean.TRUE)));

		assertTrue(xor(equal("Kevin", "Kevin"), 
					   equal(int1A, int1B), 
					   equal(int2A, int2B), 
					   equal(int3A, int3B),
					   equal(int4A, int4B)));
		
		/* false */
		assertFalse(xor(true, 
						true, 
						true, 
						true, 
						false));

		assertFalse(xor(int1A == int5B,		// false
						int2A == int2B,		// true
						int3A == int3B,		// true
						int4A == int4B,		// true
						int5A == int5B));	// true
		
		assertFalse(xor(equal("Kevin", "Kevin"),				// true 
						equal("Kevin Lee", "Lee"), 				// false
						equal(int1A, int1B), 					// true
						equal(false, false), 					// true
						equal(Boolean.TRUE, Boolean.TRUE)));	// true
		
		assertFalse(xor(equal("Kevin", "Kevin"), 	// true
						equal(int1A, int1B), 		// true
						equal(int2A, int7B),		// false
						equal(int3A, int3B),		// true
						equal(int4A, int4B)));		// true

		/* true and false */
		for (int i = 0, size = 32; i < size; i++)
		{
			final boolean b1 = ((i >>> 4) & 1) == 1;
			final boolean b2 = ((i >>> 3) & 1) == 1;
			final boolean b3 = ((i >>> 2) & 1) == 1;
			final boolean b4 = ((i >>> 1) & 1) == 1;
			final boolean b5 = (i & 1) == 1;
			if (b1 ^ b2 ^ b3 ^ b4 ^ b5)
			{
				assertTrue(xor(b1, b2, b3, b4, b5));
			}
			else
			{
				assertFalse(xor(b1, b2, b3, b4, b5));
			}
		}

		/* true */
		assertTrue(xor(int1A == int2A,		// false
					   int2A == int2B, 		// true
					   int3A == int3B, 		// true
					   int4A == int4B, 		// true
					   int5A == int7B));	// false

		assertTrue(xor(equal("Kevin", "Lee"), 					// false
					   equal("SeongHyun", "SeongHyun"),			// true 
					   equal("Lee", "Lee"),						// true
					   equal("Kevin Lee", "SeongHyun Lee"), 	// false
					   equal(int1A, int1B)));					// true
		
		assertTrue(xor(equal("Kevin", "Lee"), 					// false
					   equal("SeongHyun", ""), 					// false
					   equal(int5B, int1B), 					// false
					   equal(false, true), 					// false
					   equal(Boolean.TRUE, Boolean.TRUE)));		// true

		/* false */
		assertFalse(xor(equal("Kevin", "Lee"), 					// false
						equal("Kevin Lee", "Kevin Lee"), 		// true
						equal(int1A, int1B), 					// true
						equal(false, false), 					// true
						equal(int2A, int2B)));					// true

		assertFalse(xor(equal("Lee", "Lee"),						// true
						equal("Kevin Lee", "Kevin Lee"), 			// true
						equal("SeongHyun Lee", "SeongHyun Lee"), 	// true
						equal(int1A, int1B), 						// true
						equal(Boolean.TRUE, Boolean.FALSE)));		// false

		assertFalse(xor(false,
						false, 
						false, 
						false, 
						false));

		assertFalse(xor(equal("Kevin", "Lee"), 
						equal("Lee", "Kevin SeongHyun Lee"),
						equal(int1A, int7A), 
						equal(false, true), 
						equal(Boolean.FALSE, Boolean.TRUE)));
		/* @formatter:on */
  }

  @Test
  public final void testXorBooleanBooleanBooleanBooleanBooleanBooleanArray()
  {
    /* @formatter:off */
		/* false */
		assertFalse(xor(true, 
						true, 
						true, 
						true, 
						true, 
						true, 
						true, 
						true));

		assertFalse(xor(int1A == int1B, 
						int2A == int2B, 
						int3A == int3B, 
						int4A == int4B, 
						int5A == int5B, 
						int6A == int6B, 
						int7A == int7B, 
						int8A == int8B));

		assertFalse(xor(equal("Kevin", "Kevin"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
						equal(int1A, int1B), 
						equal(false, false), 
						equal(Boolean.TRUE, Boolean.TRUE)));

		assertFalse(xor(equal("Kevin", "Kevin"), 
						equal(int1A, int1B), 
						equal(int2A, int2B), 
						equal(int3A, int3B),
						equal(int4A, int4B),
						equal(int5A, int5B),
						equal(int6A, int6B),
						equal(int7A, int7B)));
		
		/* true */
		assertTrue(xor(true, 
					   true, 
					   true, 
					   true, 
					   true, 
					   true, 
					   true));

		assertTrue(xor(int1A == int1B, 		// true
					   int2A == int2B, 		// true
					   int3A == int3B, 		// true
					   int4A == int4B, 		// true
					   int5A == int5B, 		// true
					   int6A == int6B, 		// true
					   int7A == int7B));	// true
		
		assertTrue(xor(equal("Kevin", "Kevin"), 
					   equal("Lee", "Lee"),
					   equal("Kevin Lee", "Kevin Lee"), 
					   equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
					   equal(int1A, int1B), 
					   equal(false, false), 
					   equal(Boolean.TRUE, Boolean.TRUE)));
		
		assertTrue(xor(equal("Kevin", "Kevin"), 
						equal(int1A, int1B), 
						equal(int2A, int2B), 
						equal(int3A, int3B),
						equal(int4A, int4B),
						equal(int5A, int5B),
						equal(int6A, int6B),
						equal(int7A, int7B),
						equal(int8A, int8B)));

		/* true and false */
		for (int i = 0, size = 256; i < size; i++)
		{
			final boolean b1 = ((i >>> 7) & 1) == 1;
			final boolean b2 = ((i >>> 6) & 1) == 1;
			final boolean b3 = ((i >>> 5) & 1) == 1;
			final boolean b4 = ((i >>> 4) & 1) == 1;
			final boolean b5 = ((i >>> 3) & 1) == 1;
			final boolean b6 = ((i >>> 2) & 1) == 1;
			final boolean b7 = ((i >>> 1) & 1) == 1;
			final boolean b8 = (i & 1) == 1;
			if (b1 ^ b2 ^ b3 ^ b4 ^ b5 ^ b6 ^ b7 ^ b8)
			{
				assertTrue(xor(b1, b2, b3, b4, b5, b6, b7, b8));
			}
			else
			{
				assertFalse(xor(b1, b2, b3, b4, b5, b6, b7, b8));
			}
		}

		/* true */
		assertTrue(xor(int1A == int2A,		// false
					   int2A == int2B, 		// true
					   int3A == int3B, 		// true
					   int4A == int4B, 		// true
					   int5A == int5B, 		// true
					   int6A == int6B, 		// true
					   int7A == int7B, 		// true
					   int8A == int8B));	// true

		assertTrue(xor(equal("Kevin", "Lee"), 								// false
					   equal("SeongHyun", "SeongHyun"),						// true 
					   equal("Lee", "Lee"),									// true
					   equal("Kevin Lee", "Kevin Lee"), 					// true
					   equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), // true
					   equal(int1A, int1B), 								// true
					   equal(false, false), 								// true
					   equal(Boolean.TRUE, Boolean.TRUE)));					// true
		
		assertTrue(xor(equal("Kevin", "Lee"), 								// false
					   equal("SeongHyun", ""), 								// false
					   equal("Lee", "Kevin Lee"),							// false
					   equal("Kevin SeongHyun Lee", "Kevin Lee"), 			// false
					   equal("Kevin", "Kevin SeongHyun Lee"), 				// flase
					   equal(int5B, int1B), 								// false
					   equal(false, true), 								// false
					   equal(Boolean.TRUE, Boolean.TRUE)));					// true

		/* false */
		assertFalse(xor(equal("Kevin", "Lee"),	 							// false
						equal("SeongHyun", "SeongHyun"), 					// true
						equal("Lee", "Lee"),								// true
						equal("Kevin Lee", "Kevin Lee"), 					// true
						equal(int1A, int1B), 								// true
						equal(false, true), 								// false
						equal(int2A, int2B),								// true
						equal(Boolean.TRUE, Boolean.TRUE)));				// true

		assertFalse(xor(equal("Kevin", "Lee"), 								// false
						equal("Lee", "Lee"),								// true
						equal("Kevin Lee", "Kevin Lee"), 					// true
						equal("Kevin SeongHyun Lee", "SeongHyun Lee"), 		// false
						equal(int1A, int1B), 								// true
						equal(false, false), 								// true
					   	equal(Boolean.TRUE, Boolean.FALSE)));				// false

		assertFalse(xor(false,
						false, 
						false, 
						false, 
						false,
						false, 
						false, 
						false));

		assertFalse(xor(equal("Kevin", "Lee"), 
						equal("SeongHyun", "Kevin"), 
						equal("Lee", "Kevin SeongHyun Lee"),
						equal("Kevin Lee", ""), 
						equal("Kevin Lee", "Kevin SeongHyun Lee"), 
						equal(int1A, int7A), 
						equal(false, true), 
						equal(Boolean.FALSE, Boolean.TRUE)));
		/* @formatter:on */
  }

  @Test
  public final void testNorBooleanBoolean()
  {
    final boolean b1 = true;
    final boolean b2 = false;
    final boolean b3 = true;

    assertTrue(or(b1, b2) == nor(nor(b1, b2), nor(b1, b2)));
    assertTrue(and(b1, b2) == nor(nor(b1, b1), nor(b2, b2)));
    assertTrue(!b1 == nor(b1, b1));

    assertTrue(or(b2, b3) == nor(nor(b2, b3), nor(b2, b3)));
    assertTrue(and(b2, b3) == nor(nor(b2, b2), nor(b3, b3)));
    assertTrue(!b2 == nor(b2, b2));

    assertTrue(or(b1, b3) == nor(nor(b1, b3), nor(b1, b3)));
    assertTrue(and(b1, b3) == nor(nor(b1, b1), nor(b3, b3)));
    assertTrue(!b3 == nor(b3, b3));

    /* @formatter:off */
		/* false */
		assertFalse(nor(true, 
						true));

		assertFalse(nor(int1A == int1B, 
						int2A == int2B));

		assertFalse(nor(equal("Kevin", "Kevin"), 
						equal("Lee", "Lee")));

		assertFalse(nor(equal("Kevin", "Kevin"), 
						equal(int1A, int1B)));

		for (int i = 1, size = 4; i < size; i++)
		{
			assertFalse(nor(((i >>> 1) & 1) == 1, 
							(i & 1) == 1));
		}

		assertFalse(nor(int1A == int2A, 
						int2A == int2B));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun")));
		
		assertFalse(nor(equal("Kevin", "Kevin"), 
						equal("SeongHyun", "")));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("Lee", "Lee")));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal(int1A, int1B)));

		/* true */
		assertTrue(nor(false,
					   false));

		assertTrue(nor(equal("Kevin", "Lee"), 
					   equal(int1A, int7A)));
		/* @formatter:on */
  }

  @Test
  public final void testNorBooleanBooleanBoolean()
  {
    /* @formatter:off */
		/* false */
		assertFalse(nor(true, 
						true, 
						true));

		assertFalse(nor(int1A == int1B, 
						int2A == int2B, 
						int3A == int3B));

		assertFalse(nor(equal("Kevin", "Kevin"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee")));

		assertFalse(nor(equal("Kevin", "Kevin"), 
						equal(int1A, int1B), 
						equal(int2A, int2B)));

		for (int i = 1, size = 8; i < size; i++)
		{
			assertFalse(nor(((i >>> 2) & 1) == 1, 
							((i >>> 1) & 1) == 1, 
							(i & 1) == 1));
		}

		assertFalse(nor(int1A == int2A, 
						int2A == int2B, 
						int3A == int3B));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee")));
		
		assertFalse(nor(equal("Kevin", "Kevin"), 
						equal("SeongHyun", ""), 
						equal("Lee", "Kevin Lee")));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee")));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal(int1A, int1B)));

		/* true */
		assertTrue(nor(false,
					   false, 
					   false));

		assertTrue(nor(equal("Kevin", "Lee"), 
					   equal(int1A, int7A), 
					   equal(Boolean.FALSE, Boolean.TRUE)));
		/* @formatter:on */
  }

  @Test
  public final void testNorBooleanBooleanBooleanBoolean()
  {
    /* @formatter:off */
		/* false */
		assertFalse(nor(true, 
						true, 
						true, 
						true));

		assertFalse(nor(int1A == int1B, 
						int2A == int2B, 
						int3A == int3B, 
						int4A == int4B));

		assertFalse(nor(equal("Kevin", "Kevin"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee")));

		assertFalse(nor(equal("Kevin", "Kevin"), 
						equal(int1A, int1B), 
						equal(int2A, int2B), 
						equal(int3A, int3B)));

		for (int i = 1, size = 16; i < size; i++)
		{
			assertFalse(nor(((i >>> 3) & 1) == 1,
							((i >>> 2) & 1) == 1, 
							((i >>> 1) & 1) == 1, 
							(i & 1) == 1));
		}

		assertFalse(nor(int1A == int2A, 
						int2A == int2B, 
						int3A == int3B, 
						int4A == int4B));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee")));
		
		assertFalse(nor(equal("Kevin", "Kevin"), 
						equal("SeongHyun", ""), 
						equal("Lee", "Kevin Lee"),
						equal("Kevin SeongHyun Lee", "Kevin Lee")));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee")));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal(int1A, int1B)));

		/* true */
		assertTrue(nor(false,
					   false, 
					   false, 
					   false));

		assertTrue(nor(equal("Kevin", "Lee"), 
					   equal(int1A, int7A), 
					   equal(false, true), 
					   equal(Boolean.FALSE, Boolean.TRUE)));
		/* @formatter:on */
  }

  @Test
  public final void testNorBooleanBooleanBooleanBooleanBoolean()
  {
    /* @formatter:off */
		/* false */
		assertFalse(nor(true, 
						true, 
						true, 
						true, 
						true));

		assertFalse(nor(int1A == int1B, 
						int2A == int2B, 
						int3A == int3B, 
						int4A == int4B, 
						int5A == int5B));

		assertFalse(nor(equal("Kevin", "Kevin"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee")));

		assertFalse(nor(equal("Kevin", "Kevin"), 
						equal(int1A, int1B), 
						equal(int2A, int2B), 
						equal(int3A, int3B),
						equal(int4A, int4B)));

		for (int i = 1, size = 32; i < size; i++)
		{
			assertFalse(nor(((i >>> 4) & 1) == 1, 
							((i >>> 3) & 1) == 1,
							((i >>> 2) & 1) == 1, 
							((i >>> 1) & 1) == 1, 
							(i & 1) == 1));
		}

		assertFalse(nor(int1A == int2A, 
						int2A == int2B, 
						int3A == int3B, 
						int4A == int4B, 
						int5A == int5B));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee")));
		
		assertFalse(nor(equal("Kevin", "Kevin"), 
						equal("SeongHyun", ""), 
						equal("Lee", "Kevin Lee"),
						equal("Kevin SeongHyun Lee", "Kevin Lee"), 
						equal("Kevin", "Kevin SeongHyun Lee")));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee")));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee")));

		/* true */
		assertTrue(nor(false,
					   false, 
					   false, 
					   false, 
					   false));

		assertTrue(nor(equal("Kevin", "Lee"), 
					   equal("SeongHyun", "Kevin"), 
					   equal(int1A, int7A), 
					   equal(false, true), 
					   equal(Boolean.FALSE, Boolean.TRUE)));
		/* @formatter:on */
  }

  @Test
  public final void testNorBooleanBooleanBooleanBooleanBooleanBooleanArray()
  {
    /* @formatter:off */
		/* false */
		assertFalse(nor(true, 
						true, 
						true, 
						true, 
						true, 
						true, 
						true, 
						true));

		assertFalse(nor(int1A == int1B, 
						int2A == int2B, 
						int3A == int3B, 
						int4A == int4B, 
						int5A == int5B, 
						int6A == int6B, 
						int7A == int7B, 
						int8A == int8B));

		assertFalse(nor(equal("Kevin", "Kevin"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
						equal(int1A, int1B), 
						equal(false, false), 
						equal(Boolean.TRUE, Boolean.TRUE)));

		assertFalse(nor(equal("Kevin", "Kevin"), 
						equal(int1A, int1B), 
						equal(int2A, int2B), 
						equal(int3A, int3B),
						equal(int4A, int4B),
						equal(int5A, int5B),
						equal(int6A, int6B),
						equal(int7A, int7B),
						equal(int8A, int8B)));

		for (int i = 1, size = 256; i < size; i++)
		{
			assertFalse(nor(((i >>> 7) & 1) == 1,
							((i >>> 6) & 1) == 1, 
							((i >>> 5) & 1) == 1, 
							((i >>> 4) & 1) == 1, 
							((i >>> 3) & 1) == 1,
							((i >>> 2) & 1) == 1, 
							((i >>> 1) & 1) == 1, 
							(i & 1) == 1));
		}

		assertFalse(nor(int1A == int2A, 
						int2A == int2B, 
						int3A == int3B, 
						int4A == int4B, 
						int5A == int5B, 
						int6A == int6B, 
						int7A == int7B, 
						int8A == int8B));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
						equal(int1A, int1B), 
						equal(false, false), 
						equal(Boolean.TRUE, Boolean.TRUE)));
		
		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("SeongHyun", ""), 
						equal("Lee", "Kevin Lee"),
						equal("Kevin SeongHyun Lee", "Kevin Lee"), 
						equal("Kevin", "Kevin SeongHyun Lee"), 
						equal(int5B, int1B), 
						equal(false, true), 
						equal(Boolean.TRUE, Boolean.TRUE)));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
						equal(int1A, int1B), 
						equal(false, true), 
						equal(Boolean.TRUE, Boolean.TRUE)));

		assertFalse(nor(equal("Kevin", "Lee"), 
						equal("SeongHyun", "SeongHyun"), 
						equal("Lee", "Lee"),
						equal("Kevin Lee", "Kevin Lee"), 
						equal("Kevin SeongHyun Lee", "Kevin SeongHyun Lee"), 
						equal(int1A, int1B), 
						equal(false, false), 
						equal(Boolean.TRUE, Boolean.FALSE)));

		/* true */
		assertTrue(nor(false,
					   false, 
					   false, 
					   false, 
					   false,
					   false, 
					   false, 
					   false));

		assertTrue(nor(equal("Kevin", "Lee"), 
					   equal("SeongHyun", "Kevin"), 
					   equal("Lee", "Kevin SeongHyun Lee"),
					   equal("Kevin Lee", ""), 
					   equal("Kevin Lee", "Kevin SeongHyun Lee"), 
					   equal(int1A, int7A), 
					   equal(false, true), 
					   equal(Boolean.FALSE, Boolean.TRUE)));
		/* @formatter:on */
  }

  @Test
  public final void testMixedAndsOrsNorsXors()
  {
    String surname1 = "Lee";
    String surname2 = "Lee";
    String givenName1 = "Kevin";
    String givenName2 = "Kevin";

    /* @formatter:off */
		assertTrue(and(
					   and(equal(surname1, surname2), 
						   equal(givenName1, givenName2)),
						or(equal(int1A, int2A), 
						   equal(int1A, int1B))
		));

		assertTrue(and(
					   nor(equal(surname1, givenName1), 
						   equal(surname2, givenName2)),
					   xor(equal(int1A, int1B), 
						   equal(int2A, int2B), 
						   equal(int3A, int3B), 
						   equal(int4A, int1B))
		));

		assertTrue(or(
					   and(equal(surname1, givenName1), 
						   equal(givenName1, givenName2)),
					   xor(equal(int1A, int2A), 
						   equal(int3A, int3B))
		));

		assertTrue(and(
					   and(equal(surname1, surname2), 
						   equal(givenName1, givenName2)),
						or(equal(int1A, int2A), 
						   equal(int1A, int1B),
					   nor(equal(surname1, givenName1), 
						   equal(surname2, givenName2)),
					   xor(equal(int1A, int1B), 
						   equal(int2A, int2B), 
						   equal(int3A, int3B), 
						   equal(int5A, int1B)))
		));
		
		assertTrue(nor(
					   and(equal(surname1, surname2), 
						   equal(givenName1, givenName2),
						   equal(surname1, givenName1)),
						or(equal(int1A, int3A), 
						   equal(int2A, int5B), 
						   equal(int3A, int2B),
					   nor(equal(int1A, int1B),
						   equal(int2A, int4B),
						   equal(int5A, int1B),
						   equal(int3A, int2B)),
					   xor(equal(int1A, int1B),
						   equal(surname1, surname1),
						   equal(givenName1, givenName2),
						   equal(int2A, int2B)))
		));
		
		assertTrue(and(
					   and(equal(surname1, surname2), 
						   equal(givenName1, givenName2), 
						   xor(equal(int1A, int1B), 
							   equal(givenName1, givenName2), 
							   equal(surname1, surname2), 
							   equal(int5A, int1B), 
							   equal("", "Kevin"))),
					   nor(equal(int1A, int2A),
						   or(equal(int1A, int5B), 
							  equal(surname1, givenName2)))
		));
		/* @formatter:on */
  }
}
