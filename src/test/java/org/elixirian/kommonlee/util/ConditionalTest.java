/**
 * 
 */
package org.elixirian.kommonlee.util;

import static org.elixirian.kommonlee.util.Conditional.*;
import static org.elixirian.kommonlee.util.Objects.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-10-31)
 */
public class ConditionalTest
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
	public final void testMixedAndsOrs()
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
					    or(equal(surname1, surname2), 
						   equal(surname2, givenName2)),
					   and(equal(int1A, int1B), 
						   equal(int2A, int2B), 
						   equal(int3A, int3B), 
						   equal(int4A, int4B))
		));

		assertTrue(or(
					   and(equal(surname1, givenName1), 
						   equal(givenName1, givenName2)),
					    or(equal(int1A, int2A), 
						   equal(int3A, int3B))
		));

		assertTrue(and(
					   and(equal(surname1, surname2), 
						   equal(givenName1, givenName2)),
						or(equal(int1A, int2A), 
						   equal(int1A, int1B),
						or(equal(surname1, givenName1), 
						   equal(surname2, givenName2)),
					   and(equal(int1A, int1B), 
						   equal(int2A, int2B), 
						   equal(int3A, int3B), 
						   equal(int5A, int1B)))
		));
		
		assertTrue(or(
					  and(equal(surname1, surname2), 
						  equal(givenName1, givenName2),
						  equal(surname1, givenName1)),
					   or(equal(int1A, int3A), 
						  equal(int2A, int5B), 
						  equal(int3A, int2B),
					  and(equal(int1A, int1B),
						  equal(int2A, int4B),
						  equal(int5A, int1B),
						  equal(int3A, int2B)),
					   or(equal(int1A, int1B),
						  equal(surname1, surname1),
						  equal(givenName1, givenName2),
						  equal(int2A, int2B)))
		));
		
		assertTrue(and(
					   and(equal(surname1, surname2), 
						   equal(givenName1, givenName2), 
						      or(equal(int1A, int1B), 
							     equal(givenName1, givenName2), 
							     equal(surname1, surname2), 
							     equal(int5A, int1B), 
							     equal("", "Kevin"))),
					    or(equal(int1A, int2A),
						or(equal(int1A, int5B), 
						   equal(surname1, surname2)))
		));
		/* @formatter:on */
	}

	@Test
	public final void testTernary()
	{
		final Object object1 = new Object();
		final Object object2 = new Object();
		assertThat(ternary(true, object1, object2), is(equalTo(object1)));
		assertThat(ternary(true, object1, object2), is(not(equalTo(object2))));
		assertThat(ternary(false, object1, object2), is(equalTo(object2)));
		assertThat(ternary(false, object1, object2), is(not(equalTo(object1))));
	}
}
