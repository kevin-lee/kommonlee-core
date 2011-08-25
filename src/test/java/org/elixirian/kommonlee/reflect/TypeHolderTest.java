/**
 * 
 */
package org.elixirian.kommonlee.reflect;

import static org.elixirian.kommonlee.util.Conditional.*;
import static org.elixirian.kommonlee.util.Objects.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-12-07)
 */
public class TypeHolderTest
{
  @Test
  public final void testTypeHolder()
  {
    final TypeHolder<String> stringTypeHolder = new TypeHolder<String>() {};
    assertThat(stringTypeHolder.getType(), is(equalTo((Type) String.class)));
  }

  private static class ParameterizedTypeForTesting implements ParameterizedType
  {
    private final Type rawType;

    private final Type[] actualTypeArguments;

    public ParameterizedTypeForTesting(Type rawType, Type[] actualTypeArguments)
    {
      this.rawType = rawType;
      this.actualTypeArguments = actualTypeArguments;
    }

    @Override
    public Type getRawType()
    {
      return rawType;
    }

    @Override
    public Type getOwnerType()
    {
      throw new UnsupportedOperationException();
    }

    @Override
    public Type[] getActualTypeArguments()
    {
      return actualTypeArguments;
    }

    @Override
    public int hashCode()
    {
      return hash(hash(actualTypeArguments), rawType);
    }

    @Override
    public boolean equals(Object obj)
    {
      if (identical(this, obj))
        return true;

      final ParameterizedType that = castIfInstanceOf(ParameterizedType.class, obj);
      return isNotNull(that)
          && and(equal(this.rawType, that.getRawType()),
              deepEqual(this.actualTypeArguments, that.getActualTypeArguments()));
    }
  }

  @Test
  public final void testGetType()
  {
    final TypeHolder<String> stringTypeHolder = new TypeHolder<String>() {};
    assertThat(stringTypeHolder.getType(), is(equalTo((Type) String.class)));

    final TypeHolder<Integer> typeHolder = new TypeHolder<Integer>() {};
    assertThat(typeHolder.getType(), is(equalTo((Type) Integer.class)));

    final TypeHolder<List<List<String>>> nestedGenericType = new TypeHolder<List<List<String>>>() {};
    assertThat(nestedGenericType.getType(), is(equalTo((Type) new ParameterizedTypeForTesting(List.class,
        new Type[] { new ParameterizedTypeForTesting(List.class, new Type[] { String.class }) }))));

    final TypeHolder<Map<String, List<List<Integer>>>> nestedGenericType2 =
      new TypeHolder<Map<String, List<List<Integer>>>>() {};
    assertThat(nestedGenericType2.getType(), is(equalTo((Type) new ParameterizedTypeForTesting(Map.class, new Type[] {
        String.class,
        new ParameterizedTypeForTesting(List.class, new Type[] { new ParameterizedTypeForTesting(List.class,
            new Type[] { Integer.class }) }) }))));
  }

  @Test
  public final void testToString()
  {
    final TypeHolder<String> stringTypeHolder = new TypeHolder<String>() {};
    assertThat(stringTypeHolder.toString(), is(equalTo("{type=class java.lang.String}")));

    final TypeHolder<Integer> typeHolder = new TypeHolder<Integer>() {};
    assertThat(typeHolder.toString(), is(equalTo("{type=class java.lang.Integer}")));

    final TypeHolder<List<List<String>>> nestedGenericType = new TypeHolder<List<List<String>>>() {};
    assertThat(nestedGenericType.getType(), is(equalTo((Type) new ParameterizedTypeForTesting(List.class,
        new Type[] { new ParameterizedTypeForTesting(List.class, new Type[] { String.class }) }))));
    assertThat(nestedGenericType.toString(), is(equalTo("{type=java.util.List<java.util.List<java.lang.String>>}")));

    final TypeHolder<Map<String, List<List<Integer>>>> nestedGenericType2 =
      new TypeHolder<Map<String, List<List<Integer>>>>() {};
    assertThat(nestedGenericType2.toString(),
        is(equalTo("{type=java.util.Map<java.lang.String, java.util.List<java.util.List<java.lang.Integer>>>}")));
  }

}
