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
package org.elixirian.kommonlee.type;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParamInfoTest
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

  @Documented
  @Target(ElementType.PARAMETER)
  @Retention(RetentionPolicy.RUNTIME)
  static @interface TestParam
  {
    String value() default "Test";
  }

  @Documented
  @Target(ElementType.PARAMETER)
  @Retention(RetentionPolicy.RUNTIME)
  static @interface AnotherTestParam
  {
    String value() default "Another Test";
  }

  @SuppressWarnings("boxing")
  @Test
  public final void testHashCode()
  {
    /* given */
    final ParamInfo<String> expectedParamInfo = ParamInfo.builder(String.class, "testParam")
        .paramValue("Kevin")
        .addParamAnnotation(TestParam.class)
        .addParamAnnotation(AnotherTestParam.class)
        .build();

    final int expected = expectedParamInfo.hashCode();

    /* when */
    final ParamInfo<String> actual = ParamInfo.builder(String.class, "testParam")
        .paramValue("Kevin")
        .addParamAnnotation(TestParam.class)
        .addParamAnnotation(AnotherTestParam.class)
        .build();

    /* then */
    assertThat(actual.hashCode(), is(equalTo(expected)));

    /* given */
    final int expected2 = ParamInfo.EMPTY_PARAM_INFO.hashCode();

    /* when */
    final ParamInfo<?> actual2 = ParamInfo.builder(null, null)
        .build();

    /* then */
    assertThat(actual2.hashCode(), is(equalTo(expected2)));
  }

  @SuppressWarnings("boxing")
  @Test
  public final void testHashCode1()
  {
    /* given */
    final int expected = ParamInfo.EMPTY_PARAM_INFO.hashCode();

    /* when */
    final ParamInfo<?> actual = ParamInfo.builder(null, null)
        .build();

    /* then */
    assertThat(actual.hashCode(), is(equalTo(expected)));
  }

  @SuppressWarnings("boxing")
  @Test
  public final void testHashCode2()
  {
    /* given */
    final ParamInfo<String> expectedParamInfo = ParamInfo.builder(String.class, "testParam")
        .paramValue("Kevin")
        .addParamAnnotation(TestParam.class)
        .build();

    final int expected = expectedParamInfo.hashCode();

    /* when */
    final ParamInfo<String> actual = ParamInfo.builder(String.class, "testParam")
        .paramValue("Kevin")
        .addParamAnnotation(AnotherTestParam.class)
        .build();

    /* then */
    assertThat(actual.hashCode(), is(not(equalTo(expected))));
  }

  @Test
  public final void testGetParamType()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expected = String.class;

    final ParamInfo<String> paramInfo = ParamInfo.builder(String.class, "testParam")
        .paramValue("Kevin")
        .addParamAnnotation(TestParam.class)
        .addParamAnnotation(AnotherTestParam.class)
        .build();

    /* when */
    @SuppressWarnings("rawtypes")
    final Class actual = paramInfo.getParamType();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testGetParamType1()
  {
    /* given */
    @SuppressWarnings("rawtypes")
    final Class expected = null;

    final ParamInfo<?> paramInfo = ParamInfo.builder(null, null)
        .build();

    /* when */
    @SuppressWarnings("rawtypes")
    final Class actual = paramInfo.getParamType();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    assertThat(actual, is(nullValue()));
  }

  @Test
  public final void testGetParamName()
  {
    /* given */
    final String expected = "supplierName";
    final ParamInfo<String> paramInfo = ParamInfo.builder(String.class, expected)
        .build();

    /* when */
    final String actual = paramInfo.getParamName();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testGetParamValue()
  {
    /* given */
    final String expected = "Kevin Lee";
    final ParamInfo<String> paramInfo = ParamInfo.builder(String.class, "name")
        .paramValue(expected)
        .build();

    /* when */
    final String actual = paramInfo.getParamValue();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testGetParamAnnotationList()
  {
    /* given */
    @SuppressWarnings("unchecked")
    final List<Class<? extends Annotation>> expected = Arrays.asList(TestParam.class, AnotherTestParam.class);

    final ParamInfo.Builder<Long> paramInfoBuilder = ParamInfo.builder(Long.class, "id")
        .paramValue(Long.valueOf(100L));

    for (final Class<? extends Annotation> annotationClass : expected)
    {
      paramInfoBuilder.addParamAnnotation(annotationClass);
    }
    final ParamInfo<Long> paramInfo = paramInfoBuilder.build();

    /* when */
    final List<Class<? extends Annotation>> actual = paramInfo.getParamAnnotationList();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testGetNote()
  {
    /* given */
    final String expected = "Please read it carefully.";
    final ParamInfo<Long> paramInfo = ParamInfo.builder(Long.class, "id")
        .paramValue(Long.valueOf(100L))
        .note(expected)
        .build();

    /* when */
    final String actual = paramInfo.getNote();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @SuppressWarnings("boxing")
  @Test
  public final void testEqualsObject()
  {
    /* given */
    final boolean expected = true;
    final ParamInfo<Long> paramInfo1 = ParamInfo.builder(Long.class, "id")
        .paramValue(Long.valueOf(100L))
        .build();
    final ParamInfo<Long> paramInfo2 = ParamInfo.builder(Long.class, "id")
        .paramValue(Long.valueOf(100L))
        .build();

    /* when */
    final boolean actual = paramInfo1.equals(paramInfo2);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @SuppressWarnings("boxing")
  @Test
  public final void testEqualsObject1()
  {
    /* given */
    final boolean expected = false;
    final ParamInfo<Long> paramInfo1 = ParamInfo.builder(Long.class, "id")
        .paramValue(Long.valueOf(999L))
        .build();
    final ParamInfo<Long> paramInfo2 = ParamInfo.builder(Long.class, "id")
        .paramValue(Long.valueOf(100L))
        .build();

    /* when */
    final boolean actual = paramInfo1.equals(paramInfo2);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @SuppressWarnings("boxing")
  @Test
  public final void testEqualsObject2()
  {
    /* given */
    final boolean expected = true;
    final ParamInfo<Long> paramInfo1 = ParamInfo.builder(Long.class, "id")
        .paramValue(Long.valueOf(100L))
        .note("Please read it carefully.")
        .build();
    final ParamInfo<Long> paramInfo2 = ParamInfo.builder(Long.class, "id")
        .paramValue(Long.valueOf(100L))
        .note("another note")
        .build();

    /* when */
    final boolean actual = paramInfo1.equals(paramInfo2);

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testToString()
  {
    /* given */
    final String expected =
      "ParamInfo{paramName=id, paramType=class java.lang.Long, paramValue=999, paramAnnotationList=[interface org.elixirian.kommonlee.type.ParamInfoTest$TestParam, interface org.elixirian.kommonlee.type.ParamInfoTest$AnotherTestParam], note=Please read it carefully.}";
    @SuppressWarnings("unchecked")
    final ParamInfo<Long> paramInfo = ParamInfo.builder(Long.class, "id")
        .paramValue(Long.valueOf(999L))
        .addAllParamAnnotation(TestParam.class, AnotherTestParam.class)
        .note("Please read it carefully.")
        .build();

    /* when */
    final String actual = paramInfo.toString();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @SuppressWarnings({ "boxing", "rawtypes" })
  @Test
  public final void testIsEmpty()
  {
    /* given */
    final Boolean expected = Boolean.TRUE;

    /* when */
    final ParamInfo<?> actual = ParamInfo.builder(null, null)
        .build();

    /* then */
    assertThat(actual.isEmpty(), is(equalTo(expected)));
    assertThat(actual, is(equalTo(((ParamInfo) ParamInfo.EMPTY_PARAM_INFO))));
  }

}
