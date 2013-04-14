/**
 * This project is licensed under the Apache License, Version 2.0
 * if the following condition is met:
 * (otherwise it cannot be used by anyone but the author, Kevin, only)
 *
 * The original KommonLee project is owned by Lee, Seong Hyun (Kevin).
 *
 * -What does it mean to you?
 * Nothing, unless you want to take the ownership of
 * "the original project" (not yours or forked & modified one).
 * You are free to use it for both non-commercial and commercial projects
 * and free to modify it as the Apache License allows.
 *
 * -So why is this condition necessary?
 * It is only to protect the original project (See the case of Java).
 *
 *
 * Copyright 2009 Lee, Seong Hyun (Kevin)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.elixirian.kommonlee.reflect;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestGenericsTest
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

	class SomeClass
	{
		@SuppressWarnings("unused")
		private Map<String, Integer> map;
	}

	@Test
	public final void testExtractFromParameterizedType() throws SecurityException, NoSuchFieldException
	{
		/* given */
		final Type expected0 = String.class;
		final Type expected1 = Integer.class;
		final Field field = SomeClass.class.getDeclaredField("map");

		/* when */
		final Type actual0 = Generics.extractFromParameterizedType(field.getGenericType(), 0);
		final Type actual1 = Generics.extractFromParameterizedType(field.getGenericType(), 1);

		/* then */
		assertThat(actual0, is(equalTo(expected0)));
		assertThat(actual1, is(equalTo(expected1)));
	}

	abstract class GenerifiedClass<T, E>
	{
	}

	@Test
	public final void testExtractFromParameterizedType2() throws SecurityException, NoSuchFieldException
	{
		/* given */
		final Type expected0 = Date.class;
		final Type expected1 = BigDecimal.class;
		final Type genericSuperClass = new GenerifiedClass<Date, BigDecimal>() {}.getClass()
				.getGenericSuperclass();

		/* when */
		final Type actual0 = Generics.extractFromParameterizedType(genericSuperClass, 0);
		final Type actual1 = Generics.extractFromParameterizedType(genericSuperClass, 1);

		/* then */
		assertThat(actual0, is(equalTo(expected0)));
		assertThat(actual1, is(equalTo(expected1)));
	}
}
