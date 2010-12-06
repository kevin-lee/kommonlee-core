/**
 * 
 */
package org.elixirian.common.reflect;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-09-19)
 */
public class ClassesTest
{
	private static class FirstSuperClass
	{
	}

	private static class SecondSuperClass extends FirstSuperClass
	{
	}

	private static class ThirdSuperClass extends SecondSuperClass
	{
	}

	private static class SubClass extends ThirdSuperClass
	{
	}

	@Target({ ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	private static @interface FirstTestAnnotation
	{
	}

	@Target({ ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	private static @interface SecondTestAnnotation
	{
	}

	@SecondTestAnnotation
	private static class FirstClassWithSecondTestAnnotation extends SubClass
	{
	}

	@FirstTestAnnotation
	@SecondTestAnnotation
	private static class SecondClassWithFirstAndSecondTestAnnotations extends FirstClassWithSecondTestAnnotation
	{
	}

	private static class ThirdClassWithNoTestAnnotation extends SecondClassWithFirstAndSecondTestAnnotations
	{
	}

	@SecondTestAnnotation
	private static class FourthClassWithSecondTestAnnotation extends ThirdClassWithNoTestAnnotation
	{
	}

	@FirstTestAnnotation
	private static class SubClassWithFirstTestAnnotation extends FourthClassWithSecondTestAnnotation
	{
	}

	@FirstTestAnnotation
	@SecondTestAnnotation
	private static class SubClassWithFirstAndSecondTestAnnotations extends FourthClassWithSecondTestAnnotation
	{
	}

	private static final List<Class<? super SubClass>> CLASS_LIST_IN_SUB_TO_SUPER_ORDER;
	private static final List<Class<? super SubClass>> CLASS_LIST_IN_SUPER_TO_SUB_ORDER;

	private static final List<Class<? super SubClassWithFirstTestAnnotation>> CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION;
	private static final List<Class<? super SubClassWithFirstTestAnnotation>> CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION;

	private static final List<Class<? super SubClassWithFirstTestAnnotation>> CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION;
	private static final List<Class<? super SubClassWithFirstTestAnnotation>> CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION;

	private static final List<Class<? super SubClassWithFirstAndSecondTestAnnotations>> CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS;
	private static final List<Class<? super SubClassWithFirstAndSecondTestAnnotations>> CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS;

	private static final List<Class<? super SubClassWithFirstAndSecondTestAnnotations>> CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS;
	private static final List<Class<? super SubClassWithFirstAndSecondTestAnnotations>> CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS;

	private static final List<Class<? super SubClassWithFirstTestAnnotation>> CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION;
	private static final List<Class<? super SubClassWithFirstTestAnnotation>> CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION;

	private static final List<Class<? super SubClassWithFirstAndSecondTestAnnotations>> CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS;
	private static final List<Class<? super SubClassWithFirstAndSecondTestAnnotations>> CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS;

	static
	{
		CLASS_LIST_IN_SUB_TO_SUPER_ORDER = newArrayList();
		CLASS_LIST_IN_SUB_TO_SUPER_ORDER.add(SubClass.class);
		CLASS_LIST_IN_SUB_TO_SUPER_ORDER.add(ThirdSuperClass.class);
		CLASS_LIST_IN_SUB_TO_SUPER_ORDER.add(SecondSuperClass.class);
		CLASS_LIST_IN_SUB_TO_SUPER_ORDER.add(FirstSuperClass.class);

		CLASS_LIST_IN_SUPER_TO_SUB_ORDER = newArrayList();
		CLASS_LIST_IN_SUPER_TO_SUB_ORDER.add(FirstSuperClass.class);
		CLASS_LIST_IN_SUPER_TO_SUB_ORDER.add(SecondSuperClass.class);
		CLASS_LIST_IN_SUPER_TO_SUB_ORDER.add(ThirdSuperClass.class);
		CLASS_LIST_IN_SUPER_TO_SUB_ORDER.add(SubClass.class);

		/* @FirstTestAnnotation for SubClassWithFirstTestAnnotation */
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION =
			newArrayList();
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(SubClassWithFirstTestAnnotation.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(SecondClassWithFirstAndSecondTestAnnotations.class);

		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION =
			newArrayList();
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(SecondClassWithFirstAndSecondTestAnnotations.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(SubClassWithFirstTestAnnotation.class);

		/* @SecondTestAnnotation for SubClassWithFirstTestAnnotation */
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION =
			newArrayList();
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(FourthClassWithSecondTestAnnotation.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(SecondClassWithFirstAndSecondTestAnnotations.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(FirstClassWithSecondTestAnnotation.class);

		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION =
			newArrayList();
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(FirstClassWithSecondTestAnnotation.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(SecondClassWithFirstAndSecondTestAnnotations.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(FourthClassWithSecondTestAnnotation.class);

		/* @FirstTestAnnotation for SubClassWithFirstAndSecondTestAnnotations */
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS =
			newArrayList();
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(SubClassWithFirstAndSecondTestAnnotations.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(SecondClassWithFirstAndSecondTestAnnotations.class);

		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS =
			newArrayList();
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(SecondClassWithFirstAndSecondTestAnnotations.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(SubClassWithFirstAndSecondTestAnnotations.class);

		/* @SecondTestAnnotation for SubClassWithFirstAndSecondTestAnnotations */
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS =
			newArrayList();
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(SubClassWithFirstAndSecondTestAnnotations.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(FourthClassWithSecondTestAnnotation.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(SecondClassWithFirstAndSecondTestAnnotations.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(FirstClassWithSecondTestAnnotation.class);

		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS =
			newArrayList();
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(FirstClassWithSecondTestAnnotation.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(SecondClassWithFirstAndSecondTestAnnotations.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(FourthClassWithSecondTestAnnotation.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(SubClassWithFirstAndSecondTestAnnotations.class);

		/* @FirstTestAnnotation or @SecondTestAnnotation for SubClassWithFirstTestAnnotation */
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION =
			newArrayList();
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(SubClassWithFirstTestAnnotation.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(FourthClassWithSecondTestAnnotation.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(SecondClassWithFirstAndSecondTestAnnotations.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(FirstClassWithSecondTestAnnotation.class);

		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION =
			newArrayList();
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(FirstClassWithSecondTestAnnotation.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(SecondClassWithFirstAndSecondTestAnnotations.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(FourthClassWithSecondTestAnnotation.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.add(SubClassWithFirstTestAnnotation.class);

		/* @FirstTestAnnotation or @SecondTestAnnotation for SubClassWithFirstAndSecondTestAnnotations */
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS =
			newArrayList();
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(SubClassWithFirstAndSecondTestAnnotations.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(FourthClassWithSecondTestAnnotation.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(SecondClassWithFirstAndSecondTestAnnotations.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(FirstClassWithSecondTestAnnotation.class);

		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS =
			newArrayList();
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(FirstClassWithSecondTestAnnotation.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(SecondClassWithFirstAndSecondTestAnnotations.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(FourthClassWithSecondTestAnnotation.class);
		CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.add(SubClassWithFirstAndSecondTestAnnotations.class);
	}

	private static <E> ArrayList<Class<? super E>> newArrayList()
	{
		return new ArrayList<Class<? super E>>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * Test method for
	 * {@link com.lckymn.kevin.common.reflect.Classes#extractSuperClassesInSubToSuperOrder(java.lang.Class, java.lang.Class, boolean, java.util.Collection)}
	 * .
	 */
	@Test
	public final void testExtractSuperClassesInSubToSuperOrderClassOfTClassOfSBooleanCollectionOfClassOfQsuperT()
	{
		final List<Class<?>> classList = new ArrayList<Class<?>>();
		Classes.extractSuperClassesInSubToSuperOrder(SubClass.class, Object.class, true, classList);
		assertEquals(CLASS_LIST_IN_SUB_TO_SUPER_ORDER.size(), classList.size());
		for (int i = 0, size = CLASS_LIST_IN_SUB_TO_SUPER_ORDER.size(); i < size; i++)
		{
			assertThat(classList.get(i), is(equalTo((Object) CLASS_LIST_IN_SUB_TO_SUPER_ORDER.get(i))));
		}

		final Deque<Class<?>> classDeque = new ArrayDeque<Class<?>>();
		Classes.extractSuperClassesInSubToSuperOrder(SubClass.class, Object.class, true, classDeque);
		assertEquals(CLASS_LIST_IN_SUB_TO_SUPER_ORDER.size(), classDeque.size());
		for (Class<? super SubClass> aClass : CLASS_LIST_IN_SUB_TO_SUPER_ORDER)
		{
			assertThat(classDeque.pop(), is(equalTo((Object) aClass)));
		}
	}

	/**
	 * Test method for
	 * {@link com.lckymn.kevin.common.reflect.Classes#extractSuperClassesInSubToSuperOrder(java.lang.Class, java.lang.Class, boolean)}
	 * .
	 */
	@Test
	public final void testExtractSuperClassesInSubToSuperOrderClassOfTClassOfSBoolean()
	{
		final List<Class<?>> classList =
			Classes.extractSuperClassesInSubToSuperOrder(SubClass.class, Object.class, true);
		assertEquals(CLASS_LIST_IN_SUB_TO_SUPER_ORDER.size(), classList.size());
		for (int i = 0, size = CLASS_LIST_IN_SUB_TO_SUPER_ORDER.size(); i < size; i++)
		{
			assertThat(classList.get(i), is(equalTo((Object) CLASS_LIST_IN_SUB_TO_SUPER_ORDER.get(i))));
		}
	}

	/**
	 * Test method for
	 * {@link com.lckymn.kevin.common.reflect.Classes#extractSuperClassesInSuperToSubOrder(java.lang.Class, java.lang.Class, boolean, java.util.Collection)}
	 * .
	 */
	@Test
	public final void testExtractSuperClassesInSuperToSubOrderClassOfTClassOfSBooleanCollectionOfClassOfQsuperT()
	{
		final List<Class<?>> classList =
			Classes.extractSuperClassesInSuperToSubOrder(SubClass.class, Object.class, true);
		assertEquals(CLASS_LIST_IN_SUPER_TO_SUB_ORDER.size(), classList.size());
		for (int i = 0, size = CLASS_LIST_IN_SUPER_TO_SUB_ORDER.size(); i < size; i++)
		{
			assertThat(classList.get(i), is(equalTo((Object) CLASS_LIST_IN_SUPER_TO_SUB_ORDER.get(i))));
		}

		final Deque<Class<?>> classDeque = new ArrayDeque<Class<?>>();
		Classes.extractSuperClassesInSuperToSubOrder(SubClass.class, Object.class, true, classDeque);
		assertEquals(CLASS_LIST_IN_SUPER_TO_SUB_ORDER.size(), classDeque.size());
		for (int i = 0, size = CLASS_LIST_IN_SUPER_TO_SUB_ORDER.size(); i < size; i++)
		{
			assertThat(classDeque.pop(), is(equalTo((Object) CLASS_LIST_IN_SUPER_TO_SUB_ORDER.get(i))));
		}
	}

	/**
	 * Test method for
	 * {@link com.lckymn.kevin.common.reflect.Classes#extractSuperClassesInSuperToSubOrder(java.lang.Class, java.lang.Class, boolean)}
	 * .
	 */
	@Test
	public final void testExtractSuperClassesInSuperToSubOrderClassOfTClassOfSBoolean()
	{
		final List<Class<?>> classList =
			Classes.extractSuperClassesInSuperToSubOrder(SubClass.class, Object.class, true);
		assertEquals(CLASS_LIST_IN_SUPER_TO_SUB_ORDER.size(), classList.size());
		for (int i = 0, size = CLASS_LIST_IN_SUPER_TO_SUB_ORDER.size(); i < size; i++)
		{
			assertThat(classList.get(i), is(equalTo((Object) CLASS_LIST_IN_SUPER_TO_SUB_ORDER.get(i))));
		}
	}

	/**
	 * Test method for {@link
	 * com.lckymn.kevin.common.reflect.Classes#extractClssesWithAnnotationsInSubToSubOrder(java.lang.Class,
	 * java.lang.Class, boolean, java.util.Collection, java.lang.Class<? extends A>[])}.
	 */
	@Test
	public final void testExtractClssesWithAnnotationsInSubToSuperOrderClassOfTClassOfSBooleanCollectionOfClassOfQsuperTClassOfQextendsAArray0()
	{
		final List<Class<?>> classList1_1 = new ArrayList<Class<?>>();
		Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstTestAnnotation.class, Object.class,
				true, classList1_1, FirstTestAnnotation.class);
		final int size1_1 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.size();
		assertEquals(size1_1, classList1_1.size());
		for (int i = 0; i < size1_1; i++)
		{
			assertThat(
					classList1_1.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.get(i))));
		}

		final Deque<Class<?>> classDeque1_1 = new ArrayDeque<Class<?>>();
		Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstTestAnnotation.class, Object.class,
				true, classDeque1_1, FirstTestAnnotation.class);
		assertEquals(size1_1, classDeque1_1.size());
		for (Class<? super SubClassWithFirstTestAnnotation> aClass : CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION)
		{
			assertThat(classDeque1_1.pop(), is(equalTo((Object) aClass)));
		}
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSubToSuperOrderClassOfTClassOfSBooleanCollectionOfClassOfQsuperTClassOfQextendsAArray1()
	{
		/* */
		final List<Class<?>> classList1_2 = new ArrayList<Class<?>>();
		Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstTestAnnotation.class, Object.class,
				true, classList1_2, SecondTestAnnotation.class);
		final int size1_2 =
			CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.size();
		assertEquals(size1_2, classList1_2.size());
		for (int i = 0; i < size1_2; i++)
		{
			assertThat(
					classList1_2.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.get(i))));
		}

		final Deque<Class<?>> classDeque1_2 = new ArrayDeque<Class<?>>();
		Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstTestAnnotation.class, Object.class,
				true, classDeque1_2, SecondTestAnnotation.class);
		assertEquals(size1_2, classDeque1_2.size());
		for (Class<? super SubClassWithFirstTestAnnotation> aClass : CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION)
		{
			assertThat(classDeque1_2.pop(), is(equalTo((Object) aClass)));
		}
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSubToSuperOrderClassOfTClassOfSBooleanCollectionOfClassOfQsuperTClassOfQextendsAArray2()
	{
		/* */
		final List<Class<?>> classList2_1 = new ArrayList<Class<?>>();
		Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstAndSecondTestAnnotations.class,
				Object.class, true, classList2_1, FirstTestAnnotation.class);
		final int size2_1 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.size();
		assertEquals(size2_1, classList2_1.size());
		for (int i = 0; i < size2_1; i++)
		{
			assertThat(
					classList2_1.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.get(i))));
		}

		final Deque<Class<?>> classDeque2_1 = new ArrayDeque<Class<?>>();
		Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstAndSecondTestAnnotations.class,
				Object.class, true, classDeque2_1, FirstTestAnnotation.class);
		assertEquals(size2_1, classDeque2_1.size());
		for (Class<? super SubClassWithFirstAndSecondTestAnnotations> aClass : CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS)
		{
			assertThat(classDeque2_1.pop(), is(equalTo((Object) aClass)));
		}
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSubToSuperOrderClassOfTClassOfSBooleanCollectionOfClassOfQsuperTClassOfQextendsAArray3()
	{
		final List<Class<?>> classList2_2 = new ArrayList<Class<?>>();
		Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstAndSecondTestAnnotations.class,
				Object.class, true, classList2_2, SecondTestAnnotation.class);
		final int size2_2 =
			CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.size();
		assertEquals(size2_2, classList2_2.size());
		for (int i = 0; i < size2_2; i++)
		{
			assertThat(
					classList2_2.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.get(i))));
		}

		final Deque<Class<?>> classDeque2_2 = new ArrayDeque<Class<?>>();
		Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstAndSecondTestAnnotations.class,
				Object.class, true, classDeque2_2, SecondTestAnnotation.class);
		assertEquals(size2_2, classDeque2_2.size());
		for (Class<? super SubClassWithFirstAndSecondTestAnnotations> aClass : CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS)
		{
			assertThat(classDeque2_2.pop(), is(equalTo((Object) aClass)));
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public final void testExtractClssesWithAnnotationsInSubToSuperOrderClassOfTClassOfSBooleanCollectionOfClassOfQsuperTClassOfQextendsAArray4()
	{
		/* */
		final List<Class<?>> classList3_1 = new ArrayList<Class<?>>();
		Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstTestAnnotation.class, Object.class,
				true, classList3_1, FirstTestAnnotation.class, SecondTestAnnotation.class);
		final int size3_1 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.size();
		assertEquals(size3_1, classList3_1.size());
		for (int i = 0; i < size3_1; i++)
		{
			assertThat(
					classList3_1.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.get(i))));
		}

		final Deque<Class<?>> classDeque3_1 = new ArrayDeque<Class<?>>();
		Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstTestAnnotation.class, Object.class,
				true, classDeque3_1, FirstTestAnnotation.class, SecondTestAnnotation.class);
		assertEquals(size3_1, classDeque3_1.size());
		for (Class<? super SubClassWithFirstTestAnnotation> aClass : CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION)
		{
			assertThat(classDeque3_1.pop(), is(equalTo((Object) aClass)));
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public final void testExtractClssesWithAnnotationsInSubToSuperOrderClassOfTClassOfSBooleanCollectionOfClassOfQsuperTClassOfQextendsAArray5()
	{
		/* */
		final List<Class<?>> classList3_2 = new ArrayList<Class<?>>();
		Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstAndSecondTestAnnotations.class,
				Object.class, true, classList3_2, FirstTestAnnotation.class, SecondTestAnnotation.class);
		final int size3_2 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.size();
		assertEquals(size3_2, classList3_2.size());
		for (int i = 0; i < size3_2; i++)
		{
			assertThat(
					classList3_2.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.get(i))));
		}

		final Deque<Class<?>> classDeque3_2 = new ArrayDeque<Class<?>>();
		Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstAndSecondTestAnnotations.class,
				Object.class, true, classDeque3_2, FirstTestAnnotation.class, SecondTestAnnotation.class);
		assertEquals(size3_2, classDeque3_2.size());
		for (Class<? super SubClassWithFirstAndSecondTestAnnotations> aClass : CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS)
		{
			assertThat(classDeque3_2.pop(), is(equalTo((Object) aClass)));
		}
	}

	/**
	 * Test method for {@link
	 * com.lckymn.kevin.common.reflect.Classes#extractClssesWithAnnotationsInSubToSuperOrder(java.lang.Class,
	 * java.lang.Class, boolean, java.lang.Class<? extends A>[])}.
	 */
	@Test
	public final void testExtractClssesWithAnnotationsInSubToSuperOrderClassOfTClassOfSBooleanClassOfQextendsAArray0()
	{
		final List<Class<?>> classList1_1 =
			Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstTestAnnotation.class, Object.class,
					true, FirstTestAnnotation.class);
		final int size1_1 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.size();
		assertEquals(size1_1, classList1_1.size());
		for (int i = 0; i < size1_1; i++)
		{
			assertThat(
					classList1_1.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.get(i))));
		}

		final Collection<Class<?>> classCollection1_1 =
			Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstTestAnnotation.class, Object.class,
					true, FirstTestAnnotation.class);

		assertEquals(size1_1, classCollection1_1.size());
		assertThat(
				classCollection1_1,
				is(equalTo((Collection) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION)));
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSubToSuperOrderClassOfTClassOfSBooleanClassOfQextendsAArray1()
	{
		/* */
		final List<Class<?>> classList1_2 =
			Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstTestAnnotation.class, Object.class,
					true, SecondTestAnnotation.class);
		final int size1_2 =
			CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.size();
		assertEquals(size1_2, classList1_2.size());
		for (int i = 0; i < size1_2; i++)
		{
			assertThat(
					classList1_2.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.get(i))));
		}

		final Collection<Class<?>> classCollection1_2 =
			Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstTestAnnotation.class, Object.class,
					true, SecondTestAnnotation.class);
		assertEquals(size1_2, classCollection1_2.size());
		assertThat(
				classCollection1_2,
				is(equalTo((Collection) CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION)));
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSubToSuperOrderClassOfTClassOfSBooleanClassOfQextendsAArray2()
	{
		/* */
		final List<Class<?>> classList2_1 =
			Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstAndSecondTestAnnotations.class,
					Object.class, true, FirstTestAnnotation.class);
		final int size2_1 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.size();
		assertEquals(size2_1, classList2_1.size());
		for (int i = 0; i < size2_1; i++)
		{
			assertThat(
					classList2_1.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.get(i))));
		}

		final Collection<Class<?>> classCollection2_1 =
			Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstAndSecondTestAnnotations.class,
					Object.class, true, FirstTestAnnotation.class);
		assertEquals(size2_1, classCollection2_1.size());
		assertThat(
				classCollection2_1,
				is(equalTo((Collection) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS)));
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSubToSuperOrderClassOfTClassOfSBooleanClassOfQextendsAArray3()
	{
		final List<Class<?>> classList2_2 =
			Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstAndSecondTestAnnotations.class,
					Object.class, true, SecondTestAnnotation.class);
		final int size2_2 =
			CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.size();
		assertEquals(size2_2, classList2_2.size());
		for (int i = 0; i < size2_2; i++)
		{
			assertThat(
					classList2_2.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.get(i))));
		}

		final Collection<Class<?>> classCollection2_2 =
			Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstAndSecondTestAnnotations.class,
					Object.class, true, SecondTestAnnotation.class);
		assertEquals(size2_2, classCollection2_2.size());
		assertThat(
				classCollection2_2,
				is(equalTo((Collection) CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS)));
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSubToSuperOrderClassOfTClassOfSBooleanClassOfQextendsAArray4()
	{
		/* */
		@SuppressWarnings("unchecked")
		final List<Class<?>> classList3_1 =
			Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstTestAnnotation.class, Object.class,
					true, FirstTestAnnotation.class, SecondTestAnnotation.class);
		final int size3_1 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.size();
		assertEquals(size3_1, classList3_1.size());
		for (int i = 0; i < size3_1; i++)
		{
			assertThat(
					classList3_1.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.get(i))));
		}

		@SuppressWarnings("unchecked")
		final Collection<Class<?>> classCollection3_1 =
			Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstTestAnnotation.class, Object.class,
					true, FirstTestAnnotation.class, SecondTestAnnotation.class);
		assertEquals(size3_1, classCollection3_1.size());
		assertThat(
				classCollection3_1,
				is(equalTo((Collection) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION)));
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSubToSuperOrderClassOfTClassOfSBooleanClassOfQextendsAArray5()
	{
		/* */
		@SuppressWarnings("unchecked")
		final List<Class<?>> classList3_2 =
			Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstAndSecondTestAnnotations.class,
					Object.class, true, FirstTestAnnotation.class, SecondTestAnnotation.class);
		final int size3_2 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.size();
		assertEquals(size3_2, classList3_2.size());
		for (int i = 0; i < size3_2; i++)
		{
			assertThat(
					classList3_2.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.get(i))));
		}

		@SuppressWarnings("unchecked")
		final Collection<Class<?>> classCollection3_2 =
			Classes.extractClssesWithAnnotationsInSubToSuperOrder(SubClassWithFirstAndSecondTestAnnotations.class,
					Object.class, true, FirstTestAnnotation.class, SecondTestAnnotation.class);
		assertEquals(size3_2, classCollection3_2.size());
		assertThat(
				classCollection3_2,
				is(equalTo((Collection) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUB_TO_SUPER_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS)));
	}

	/**
	 * Test method for {@link
	 * com.lckymn.kevin.common.reflect.Classes#extractClssesWithAnnotationsInSuperToSubOrder(java.lang.Class,
	 * java.lang.Class, boolean, java.util.Collection, java.lang.Class<? extends A>[])}.
	 */
	@Test
	public final void testExtractClssesWithAnnotationsInSuperToSubOrderClassOfTClassOfSBooleanCollectionOfClassOfQsuperTClassOfQextendsAArray0()
	{
		final List<Class<?>> classList1_1 = new ArrayList<Class<?>>();
		Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstTestAnnotation.class, Object.class,
				true, classList1_1, FirstTestAnnotation.class);
		final int size1_1 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.size();
		assertEquals(size1_1, classList1_1.size());
		for (int i = 0; i < size1_1; i++)
		{
			assertThat(
					classList1_1.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.get(i))));
		}

		final Deque<Class<?>> classDeque1_1 = new ArrayDeque<Class<?>>();
		Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstTestAnnotation.class, Object.class,
				true, classDeque1_1, FirstTestAnnotation.class);
		assertEquals(size1_1, classDeque1_1.size());
		for (Class<? super SubClassWithFirstTestAnnotation> aClass : CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION)
		{
			assertThat(classDeque1_1.pop(), is(equalTo((Object) aClass)));
		}
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSuperToSubOrderClassOfTClassOfSBooleanCollectionOfClassOfQsuperTClassOfQextendsAArray1()
	{
		/* */
		final List<Class<?>> classList1_2 = new ArrayList<Class<?>>();
		Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstTestAnnotation.class, Object.class,
				true, classList1_2, SecondTestAnnotation.class);
		final int size1_2 =
			CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.size();
		assertEquals(size1_2, classList1_2.size());
		for (int i = 0; i < size1_2; i++)
		{
			assertThat(
					classList1_2.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.get(i))));
		}

		final Deque<Class<?>> classDeque1_2 = new ArrayDeque<Class<?>>();
		Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstTestAnnotation.class, Object.class,
				true, classDeque1_2, SecondTestAnnotation.class);
		assertEquals(size1_2, classDeque1_2.size());
		for (Class<? super SubClassWithFirstTestAnnotation> aClass : CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION)
		{
			assertThat(classDeque1_2.pop(), is(equalTo((Object) aClass)));
		}
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSuperToSubOrderClassOfTClassOfSBooleanCollectionOfClassOfQsuperTClassOfQextendsAArray2()
	{
		/* */
		final List<Class<?>> classList2_1 = new ArrayList<Class<?>>();
		Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstAndSecondTestAnnotations.class,
				Object.class, true, classList2_1, FirstTestAnnotation.class);
		final int size2_1 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.size();
		assertEquals(size2_1, classList2_1.size());
		for (int i = 0; i < size2_1; i++)
		{
			assertThat(
					classList2_1.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.get(i))));
		}

		final Deque<Class<?>> classDeque2_1 = new ArrayDeque<Class<?>>();
		Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstAndSecondTestAnnotations.class,
				Object.class, true, classDeque2_1, FirstTestAnnotation.class);
		assertEquals(size2_1, classDeque2_1.size());
		for (Class<? super SubClassWithFirstAndSecondTestAnnotations> aClass : CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS)
		{
			assertThat(classDeque2_1.pop(), is(equalTo((Object) aClass)));
		}
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSuperToSubOrderClassOfTClassOfSBooleanCollectionOfClassOfQsuperTClassOfQextendsAArray3()
	{
		final List<Class<?>> classList2_2 = new ArrayList<Class<?>>();
		Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstAndSecondTestAnnotations.class,
				Object.class, true, classList2_2, SecondTestAnnotation.class);
		final int size2_2 =
			CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.size();
		assertEquals(size2_2, classList2_2.size());
		for (int i = 0; i < size2_2; i++)
		{
			assertThat(
					classList2_2.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.get(i))));
		}

		final Deque<Class<?>> classDeque2_2 = new ArrayDeque<Class<?>>();
		Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstAndSecondTestAnnotations.class,
				Object.class, true, classDeque2_2, SecondTestAnnotation.class);
		assertEquals(size2_2, classDeque2_2.size());
		for (Class<? super SubClassWithFirstAndSecondTestAnnotations> aClass : CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS)
		{
			assertThat(classDeque2_2.pop(), is(equalTo((Object) aClass)));
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public final void testExtractClssesWithAnnotationsInSuperToSubOrderClassOfTClassOfSBooleanCollectionOfClassOfQsuperTClassOfQextendsAArray4()
	{
		/* */
		final List<Class<?>> classList3_1 = new ArrayList<Class<?>>();
		Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstTestAnnotation.class, Object.class,
				true, classList3_1, FirstTestAnnotation.class, SecondTestAnnotation.class);
		final int size3_1 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.size();
		assertEquals(size3_1, classList3_1.size());
		for (int i = 0; i < size3_1; i++)
		{
			assertThat(
					classList3_1.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.get(i))));
		}

		final Deque<Class<?>> classDeque3_1 = new ArrayDeque<Class<?>>();
		Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstTestAnnotation.class, Object.class,
				true, classDeque3_1, FirstTestAnnotation.class, SecondTestAnnotation.class);
		assertEquals(size3_1, classDeque3_1.size());
		for (Class<? super SubClassWithFirstTestAnnotation> aClass : CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION)
		{
			assertThat(classDeque3_1.pop(), is(equalTo((Object) aClass)));
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public final void testExtractClssesWithAnnotationsInSuperToSubOrderClassOfTClassOfSBooleanCollectionOfClassOfQsuperTClassOfQextendsAArray5()
	{
		/* */
		final List<Class<?>> classList3_2 = new ArrayList<Class<?>>();
		Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstAndSecondTestAnnotations.class,
				Object.class, true, classList3_2, FirstTestAnnotation.class, SecondTestAnnotation.class);
		final int size3_2 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.size();
		assertEquals(size3_2, classList3_2.size());
		for (int i = 0; i < size3_2; i++)
		{
			assertThat(
					classList3_2.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.get(i))));
		}

		final Deque<Class<?>> classDeque3_2 = new ArrayDeque<Class<?>>();
		Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstAndSecondTestAnnotations.class,
				Object.class, true, classDeque3_2, FirstTestAnnotation.class, SecondTestAnnotation.class);
		assertEquals(size3_2, classDeque3_2.size());
		for (Class<? super SubClassWithFirstAndSecondTestAnnotations> aClass : CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS)
		{
			assertThat(classDeque3_2.pop(), is(equalTo((Object) aClass)));
		}
	}

	/**
	 * Test method for {@link
	 * com.lckymn.kevin.common.reflect.Classes#extractClssesWithAnnotationsInSuperToSubOrder(java.lang.Class,
	 * java.lang.Class, boolean, java.lang.Class<? extends A>[])}.
	 */
	@Test
	public final void testExtractClssesWithAnnotationsInSuperToSubOrderClassOfTClassOfSBooleanClassOfQextendsAArray0()
	{
		final List<Class<?>> classList1_1 =
			Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstTestAnnotation.class, Object.class,
					true, FirstTestAnnotation.class);
		final int size1_1 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.size();
		assertEquals(size1_1, classList1_1.size());
		for (int i = 0; i < size1_1; i++)
		{
			assertThat(
					classList1_1.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.get(i))));
		}

		final Collection<Class<?>> classCollection1_1 =
			Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstTestAnnotation.class, Object.class,
					true, FirstTestAnnotation.class);

		assertEquals(size1_1, classCollection1_1.size());
		assertThat(
				classCollection1_1,
				is(equalTo((Collection) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION)));
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSuperToSubOrderClassOfTClassOfSBooleanClassOfQextendsAArray1()
	{
		/* */
		final List<Class<?>> classList1_2 =
			Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstTestAnnotation.class, Object.class,
					true, SecondTestAnnotation.class);
		final int size1_2 =
			CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.size();
		assertEquals(size1_2, classList1_2.size());
		for (int i = 0; i < size1_2; i++)
		{
			assertThat(
					classList1_2.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.get(i))));
		}

		final Collection<Class<?>> classCollection1_2 =
			Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstTestAnnotation.class, Object.class,
					true, SecondTestAnnotation.class);
		assertEquals(size1_2, classCollection1_2.size());
		assertThat(
				classCollection1_2,
				is(equalTo((Collection) CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION)));
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSuperToSubOrderClassOfTClassOfSBooleanClassOfQextendsAArray2()
	{
		/* */
		final List<Class<?>> classList2_1 =
			Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstAndSecondTestAnnotations.class,
					Object.class, true, FirstTestAnnotation.class);
		final int size2_1 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.size();
		assertEquals(size2_1, classList2_1.size());
		for (int i = 0; i < size2_1; i++)
		{
			assertThat(
					classList2_1.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.get(i))));
		}

		final Collection<Class<?>> classCollection2_1 =
			Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstAndSecondTestAnnotations.class,
					Object.class, true, FirstTestAnnotation.class);
		assertEquals(size2_1, classCollection2_1.size());
		assertThat(
				classCollection2_1,
				is(equalTo((Collection) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS)));
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSuperToSubOrderClassOfTClassOfSBooleanClassOfQextendsAArray3()
	{
		final List<Class<?>> classList2_2 =
			Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstAndSecondTestAnnotations.class,
					Object.class, true, SecondTestAnnotation.class);
		final int size2_2 =
			CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.size();
		assertEquals(size2_2, classList2_2.size());
		for (int i = 0; i < size2_2; i++)
		{
			assertThat(
					classList2_2.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.get(i))));
		}

		final Collection<Class<?>> classCollection2_2 =
			Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstAndSecondTestAnnotations.class,
					Object.class, true, SecondTestAnnotation.class);
		assertEquals(size2_2, classCollection2_2.size());
		assertThat(
				classCollection2_2,
				is(equalTo((Collection) CLASS_LIST_OF_SUB_CLASS_WITH_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS)));
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSuperToSubOrderClassOfTClassOfSBooleanClassOfQextendsAArray4()
	{
		/* */
		@SuppressWarnings("unchecked")
		final List<Class<?>> classList3_1 =
			Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstTestAnnotation.class, Object.class,
					true, FirstTestAnnotation.class, SecondTestAnnotation.class);
		final int size3_1 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.size();
		assertEquals(size3_1, classList3_1.size());
		for (int i = 0; i < size3_1; i++)
		{
			assertThat(
					classList3_1.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION.get(i))));
		}

		@SuppressWarnings("unchecked")
		final Collection<Class<?>> classCollection3_1 =
			Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstTestAnnotation.class, Object.class,
					true, FirstTestAnnotation.class, SecondTestAnnotation.class);
		assertEquals(size3_1, classCollection3_1.size());
		assertThat(
				classCollection3_1,
				is(equalTo((Collection) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_TEST_ANNOTATION)));
	}

	@Test
	public final void testExtractClssesWithAnnotationsInSuperToSubOrderClassOfTClassOfSBooleanClassOfQextendsAArray5()
	{
		/* */
		@SuppressWarnings("unchecked")
		final List<Class<?>> classList3_2 =
			Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstAndSecondTestAnnotations.class,
					Object.class, true, FirstTestAnnotation.class, SecondTestAnnotation.class);
		final int size3_2 =
			CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.size();
		assertEquals(size3_2, classList3_2.size());
		for (int i = 0; i < size3_2; i++)
		{
			assertThat(
					classList3_2.get(i),
					is(equalTo((Object) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS.get(i))));
		}

		@SuppressWarnings("unchecked")
		final Collection<Class<?>> classCollection3_2 =
			Classes.extractClssesWithAnnotationsInSuperToSubOrder(SubClassWithFirstAndSecondTestAnnotations.class,
					Object.class, true, FirstTestAnnotation.class, SecondTestAnnotation.class);
		assertEquals(size3_2, classCollection3_2.size());
		assertThat(
				classCollection3_2,
				is(equalTo((Collection) CLASS_LIST_OF_SUB_CLASS_WITH_FIRST_OR_SECOND_TEST_ANNOTATION_IN_SUPER_TO_SUB_ORDER_FOR_SUB_CLASS_WITH_FIRST_AND_SECOND_TEST_ANNOTATIONS)));
	}

	private static final int DEFAULT_NUMBER = 100;

	private static final String DEFAULT_NAME = "Kevin";

	private static final Double DEFAULT_DOUBLE_NUMBER = Double.valueOf(999.99);

	private static class ClassWithManyConstructors
	{
		private final int number;

		private final String name;

		private final Double doubleNumber;

		public ClassWithManyConstructors()
		{
			this.number = DEFAULT_NUMBER;
			this.name = DEFAULT_NAME;
			this.doubleNumber = DEFAULT_DOUBLE_NUMBER;
		}

		public ClassWithManyConstructors(int number)
		{
			this.number = number;
			this.name = DEFAULT_NAME + number;
			this.doubleNumber = DEFAULT_DOUBLE_NUMBER;
		}

		public ClassWithManyConstructors(String name)
		{
			this.number = name.hashCode();
			this.name = name;
			this.doubleNumber = DEFAULT_DOUBLE_NUMBER;
		}

		public ClassWithManyConstructors(Double doubleNumber)
		{
			this.number = DEFAULT_NUMBER;
			this.name = DEFAULT_NAME;
			this.doubleNumber = doubleNumber;
		}

		public ClassWithManyConstructors(int number, String name)
		{
			this.number = number;
			this.name = name;
			this.doubleNumber = DEFAULT_DOUBLE_NUMBER;
		}

		public ClassWithManyConstructors(String name, Double doubleNumber)
		{
			this.number = DEFAULT_NUMBER;
			this.name = name;
			this.doubleNumber = doubleNumber;
		}

		public ClassWithManyConstructors(int number, Double doubleNumber)
		{
			this.number = number;
			this.name = DEFAULT_NAME;
			this.doubleNumber = doubleNumber;
		}

		public ClassWithManyConstructors(int number, String name, Double doubleNumber)
		{
			this.number = number;
			this.name = name;
			this.doubleNumber = doubleNumber;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + number;
			result = prime * result + (name == null ? 0 : name.hashCode());
			result = prime * result + (doubleNumber == null ? 0 : doubleNumber.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
			{
				return true;
			}
			if (!(obj instanceof ClassWithManyConstructors))
			{
				return false;
			}
			final ClassWithManyConstructors that = (ClassWithManyConstructors) obj;
			return this.number == that.number
					&& (this.name == that.name || (null != this.name && this.name.equals(that.name)))
					&& (this.doubleNumber == that.doubleNumber || (null != this.doubleNumber && this.doubleNumber.equals(that.doubleNumber)));
		}
	}

	/**
	 * Test method for {@link com.lckymn.kevin.common.reflect.Classes#findConstructor(java.lang.Class,
	 * java.lang.Class<?>[])}.
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("boxing")
	@Test
	public final void testFindConstructor() throws IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException
	{
		assertThat(Classes.findConstructor(ClassWithManyConstructors.class, java.lang.Math.class), is(nullValue()));

		final Constructor<ClassWithManyConstructors> constructor1 =
			Classes.findConstructor(ClassWithManyConstructors.class);
		final ClassWithManyConstructors classWithManyConstructors1_1 = new ClassWithManyConstructors();
		final ClassWithManyConstructors classWithManyConstructors1_2 = constructor1.newInstance();
		assertThat(classWithManyConstructors1_1.hashCode(), is(equalTo(classWithManyConstructors1_2.hashCode())));
		assertThat(classWithManyConstructors1_1, is(equalTo(classWithManyConstructors1_2)));

		final Constructor<ClassWithManyConstructors> constructor2 =
			Classes.findConstructor(ClassWithManyConstructors.class, int.class);
		final ClassWithManyConstructors classWithManyConstructors2_1 = new ClassWithManyConstructors(999);
		final ClassWithManyConstructors classWithManyConstructors2_2 = constructor2.newInstance(Integer.valueOf(999));
		assertThat(classWithManyConstructors2_1.hashCode(), is(equalTo(classWithManyConstructors2_2.hashCode())));
		assertThat(classWithManyConstructors2_1, is(equalTo(classWithManyConstructors2_2)));

		final Constructor<ClassWithManyConstructors> constructor3 =
			Classes.findConstructor(ClassWithManyConstructors.class, String.class);
		final ClassWithManyConstructors classWithManyConstructors3_1 = new ClassWithManyConstructors("Lee");
		final ClassWithManyConstructors classWithManyConstructors3_2 = constructor3.newInstance("Lee");
		assertThat(classWithManyConstructors3_1.hashCode(), is(equalTo(classWithManyConstructors3_2.hashCode())));
		assertThat(classWithManyConstructors3_1, is(equalTo(classWithManyConstructors3_2)));

		final Constructor<ClassWithManyConstructors> constructor4 =
			Classes.findConstructor(ClassWithManyConstructors.class, Double.class);
		final ClassWithManyConstructors classWithManyConstructors4_1 =
			new ClassWithManyConstructors(Double.valueOf(1234.5678));
		final ClassWithManyConstructors classWithManyConstructors4_2 =
			constructor4.newInstance(Double.valueOf(1234.5678));
		assertThat(classWithManyConstructors4_1.hashCode(), is(equalTo(classWithManyConstructors4_2.hashCode())));
		assertThat(classWithManyConstructors4_1, is(equalTo(classWithManyConstructors4_2)));

		final Constructor<ClassWithManyConstructors> constructor5 =
			Classes.findConstructor(ClassWithManyConstructors.class, int.class, String.class);
		final ClassWithManyConstructors classWithManyConstructors5_1 = new ClassWithManyConstructors(999, "Nobody");
		final ClassWithManyConstructors classWithManyConstructors5_2 =
			constructor5.newInstance(Integer.valueOf(999), "Nobody");
		assertThat(classWithManyConstructors5_1.hashCode(), is(equalTo(classWithManyConstructors5_2.hashCode())));
		assertThat(classWithManyConstructors5_1, is(equalTo(classWithManyConstructors5_2)));

		final Constructor<ClassWithManyConstructors> constructor6 =
			Classes.findConstructor(ClassWithManyConstructors.class, String.class, Double.class);
		final ClassWithManyConstructors classWithManyConstructors6_1 =
			new ClassWithManyConstructors("Kevin Lee", Double.valueOf(1234.5678));
		final ClassWithManyConstructors classWithManyConstructors6_2 =
			constructor6.newInstance("Kevin Lee", Double.valueOf(1234.5678));
		assertThat(classWithManyConstructors6_1.hashCode(), is(equalTo(classWithManyConstructors6_2.hashCode())));
		assertThat(classWithManyConstructors6_1, is(equalTo(classWithManyConstructors6_2)));

		final Constructor<ClassWithManyConstructors> constructor7 =
			Classes.findConstructor(ClassWithManyConstructors.class, int.class, Double.class);
		final ClassWithManyConstructors classWithManyConstructors7_1 =
			new ClassWithManyConstructors(2010, Double.valueOf(555.777));
		final ClassWithManyConstructors classWithManyConstructors7_2 =
			constructor7.newInstance(Integer.valueOf(2010), Double.valueOf(555.777));
		assertThat(classWithManyConstructors7_1.hashCode(), is(equalTo(classWithManyConstructors7_2.hashCode())));
		assertThat(classWithManyConstructors7_1, is(equalTo(classWithManyConstructors7_2)));

		final Constructor<ClassWithManyConstructors> constructor8 =
			Classes.findConstructor(ClassWithManyConstructors.class, int.class, String.class, Double.class);
		final ClassWithManyConstructors classWithManyConstructors8_1 =
			new ClassWithManyConstructors(11111, "Kevin SeongHyun Lee", Double.valueOf(98765.4321));
		final ClassWithManyConstructors classWithManyConstructors8_2 =
			constructor8.newInstance(Integer.valueOf(11111), "Kevin SeongHyun Lee", Double.valueOf(98765.4321));
		assertThat(classWithManyConstructors8_1.hashCode(), is(equalTo(classWithManyConstructors8_2.hashCode())));
		assertThat(classWithManyConstructors8_1, is(equalTo(classWithManyConstructors8_2)));
	}
}
