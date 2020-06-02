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

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.elixirian.kommonlee.type.checkable.EmptinessCheckable;

/**
 * <pre>
 *     ___  _____                                              _____
 *    /   \/    / ______ __________________  ______ __ ______ /    /   ______  ______  
 *   /        / _/ __  // /  /   / /  /   /_/ __  // //     //    /   /  ___ \/  ___ \ 
 *  /        \ /  /_/ _/  _  _  /  _  _  //  /_/ _/   __   //    /___/  _____/  _____/
 * /____/\____\/_____//__//_//_/__//_//_/ /_____//___/ /__//________/\_____/ \_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2012-06-05)
 */
public class ParamInfo<T> implements EmptinessCheckable
{
  public static final List<ParamInfo<?>> EMPTY_PARAM_LIST = Collections.emptyList();

  public static final List<Class<? extends Annotation>> EMPTY_ANNOTATION_LIST = Collections.emptyList();

  public static final ParamInfo<?> EMPTY_PARAM_INFO =
    new ParamInfo<Object>(null, "", null, EMPTY_ANNOTATION_LIST, null) {
      @Override
      public int hashCode()
      {
        return 0;
      }

      @Override
      public boolean isEmpty()
      {
        return true;
      }
    };

  private final Class<T> paramType;

  private final String paramName;

  private final T paramValue;

  private final List<Class<? extends Annotation>> paramAnnotationList;

  private final String note;

  public static class Builder<T> implements GenericBuilder<ParamInfo<T>>
  {
    final Class<T> paramType;

    final String paramName;

    T paramValue;

    final List<Class<? extends Annotation>> paramAnnotationList;

    String note;

    public Builder(final Class<T> paramType, final String paramName)
    {
      this.paramType = paramType;
      this.paramName = paramName;
      this.paramAnnotationList = new ArrayList<Class<? extends Annotation>>();
    }

    public Builder<T> paramValue(final T paramValue)
    {
      this.paramValue = paramValue;
      return this;
    }

    public <A extends Annotation> Builder<T> addParamAnnotation(final Class<A> annotation)
    {
      this.paramAnnotationList.add(annotation);
      return this;
    }

    public <A extends Annotation> Builder<T> addAllParamAnnotation(final Class<? extends A>... annotation)
    {
      this.paramAnnotationList.addAll(Arrays.asList(annotation));
      return this;
    }

    public Builder<T> note(final String note)
    {
      this.note = note;
      return this;
    }

    @Override
    public ParamInfo<T> build()
    {
      /* @formatter:off */
      if (null == paramType &&
          (null == paramName ||
            paramName.isEmpty()) &&
          null == paramValue &&
          paramAnnotationList.isEmpty() &&
          (null == note ||
            note.isEmpty()))
      {
        /* @formatter:on */
        @SuppressWarnings("unchecked")
        final ParamInfo<T> emptyParamInfo = (ParamInfo<T>) EMPTY_PARAM_INFO;
        return emptyParamInfo;
      }
      return new ParamInfo<T>(this);
    }
  }

  public static <T> Builder<T> builder(final Class<T> paramType, final String paramName)
  {
    return new Builder<T>(paramType, paramName);
  }

  private ParamInfo(final Builder<T> builder)
  {
    this(builder.paramType, builder.paramName, builder.paramValue, builder.paramAnnotationList, builder.note);
  }

  private ParamInfo(final Class<T> paramType, final String paramName, final T paramValue,
      final List<Class<? extends Annotation>> paramAnnotationList, final String note)
  {
    this.paramType = paramType;
    this.paramName = paramName;
    this.paramValue = paramValue;
    /* @formatter:off */
      this.paramAnnotationList =
        null == paramAnnotationList || paramAnnotationList.isEmpty() ?
            EMPTY_ANNOTATION_LIST :
            Collections.unmodifiableList(paramAnnotationList);
    /* @formatter:on */
    this.note = note;
  }

  /**
   * Returns the paramType.
   * 
   * @return the type ({@link Class}) of the parameter.
   */
  public Class<?> getParamType()
  {
    return paramType;
  }

  /**
   * Returns paramName
   * 
   * @return the name of the parameter.
   */
  public String getParamName()
  {
    return paramName;
  }

  /**
   * Returns paramValue.
   * 
   * @return the parameter value.
   */
  public T getParamValue()
  {
    return paramValue;
  }

  /**
   * Returns paramAnnotationList.
   * 
   * @return the {@link List} of {@link Class} that extends {@link Annotation} with which the parameter is annotated.
   */
  public List<Class<? extends Annotation>> getParamAnnotationList()
  {
    return paramAnnotationList;
  }

  public String getNote()
  {
    return note;
  }

  @Override
  public int hashCode()
  {
    int result = 1;
    result = 31 * result + (null == paramName ? 0 : paramName.hashCode());
    result = 31 * result + (null == paramType ? 0 : paramType.hashCode());
    result = 31 * result + (null == paramValue ? 0 : paramValue.hashCode());
    result = 31 * result + (null == paramAnnotationList ? 0 : paramAnnotationList.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object paramInfo)
  {
    if (this == paramInfo)
    {
      return true;
    }
    if (!(paramInfo instanceof ParamInfo))
    {
      return false;
    }
    final ParamInfo<?> that = (ParamInfo<?>) paramInfo;
    /* @formatter:off */
    return (this.paramName == that.getParamName() ||
              (null != this.paramName &&
               this.paramName.equals(that.getParamName()))) &&
           (this.paramType == that.getParamType() ||
              (null != this.paramType &&
               this.paramType.equals(that.getParamType()))) &&
           (this.paramValue == that.getParamValue() ||
              (null != this.paramValue &&
               this.paramValue.equals(that.getParamValue()))) &&
           (this.paramAnnotationList == that.getParamAnnotationList() ||
              (null != this.paramAnnotationList &&
              this.paramAnnotationList.equals(that.getParamAnnotationList())));
    /* @formatter:on */
  }

  @Override
  public String toString()
  {
    if (isEmpty())
    {
      return "{EMPTY}";
    }
    /* @formatter:off */
    return new StringBuilder(this.getClass().getSimpleName())
        .append("{")
          .append("paramName=")
          .append(paramName)
        .append(", ")
          .append("paramType=")
          .append(paramType)
        .append(", ")
          .append("paramValue=")
          .append(paramValue)
        .append(", ")
          .append("paramAnnotationList=")
          .append(paramAnnotationList)
        .append(", ")
          .append("note=")
          .append(note)
        .append("}")
        .toString();
    /* @formatter:on */
  }

  @Override
  public boolean isEmpty()
  {
    return EMPTY_PARAM_INFO.equals(this);
  }
}