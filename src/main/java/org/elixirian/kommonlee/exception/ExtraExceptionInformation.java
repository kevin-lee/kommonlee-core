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
package org.elixirian.kommonlee.exception;

import static org.elixirian.kommonlee.util.Objects.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.elixirian.kommonlee.type.GenericBuilder;
import org.elixirian.kommonlee.type.ParamInfo;

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
public class ExtraExceptionInformation
{
	private static final List<String> EMPTY_SUGGESTION_LIST = Collections.emptyList();

	public static ExtraExceptionInformation EMPTY_EXTRA_EXCEPTION_INFORMATION = new ExtraExceptionInformation(
			ParamInfo.EMPTY_PARAM_LIST, ParamInfo.EMPTY_PARAM_LIST, "", EMPTY_SUGGESTION_LIST);

	private final List<ParamInfo<?>> paramInfoList;

	private final List<ParamInfo<?>> otherObjectInfoList;

	private final String possibleReason;

	private final List<String> suggestionList;

	public static class Builder implements GenericBuilder<ExtraExceptionInformation>
	{
		final List<ParamInfo<?>> paramInfoList;
		final List<ParamInfo<?>> otherObjectInfoList;
		String possibleReason;
		final List<String> suggestionList;

		public Builder()
		{
			paramInfoList = new ArrayList<ParamInfo<?>>();
			otherObjectInfoList = new ArrayList<ParamInfo<?>>();
			suggestionList = new ArrayList<String>();
		}

		public <T> Builder addParamInfo(final ParamInfo<T> paramInfo)
		{
			this.paramInfoList.add(paramInfo);
			return this;
		}

		public <T> Builder addParamInfo(final Class<T> paramType, final String paramName, final T paramValue)
		{
			/* @formatter:off */
      this.paramInfoList.add(ParamInfo
          .builder(paramType, paramName)
          .paramValue(paramValue)
          .build());
      /* @formatter:on */
			return this;
		}

		public <T> Builder addParamInfo(final Class<T> paramType, final String paramName, final T paramValue,
				final String note)
		{
			/* @formatter:off */
      this.paramInfoList.add(ParamInfo
          .builder(paramType, paramName)
          .paramValue(paramValue)
          .note(note)
          .build());
      /* @formatter:on */
			return this;
		}

		public Builder addAllParamInfo(final ParamInfo<?>... paramInfo)
		{
			this.paramInfoList.addAll(Arrays.asList(paramInfo));
			return this;
		}

		public <T> Builder addAllParamInfo(final List<ParamInfo<?>> paramInfoList)
		{
			this.paramInfoList.addAll(paramInfoList);
			return this;
		}

		public Builder clearParamInfoList()
		{
			this.paramInfoList.clear();
			return this;
		}

		public <T> Builder addOtherObjectInfo(final ParamInfo<T> otherObjectInfo)
		{
			this.otherObjectInfoList.add(otherObjectInfo);
			return this;
		}

		public <T> Builder addOtherObjectInfo(final Class<? super T> paramType, final String paramName, final T paramValue)
		{
			/* @formatter:off */
      this.otherObjectInfoList.add(ParamInfo
          .builder(paramType, paramName)
          .paramValue(paramValue)
          .build());
      /* @formatter:on */
			return this;
		}

		public <T> Builder addOtherObjectInfo(final Class<? super T> paramType, final String paramName, final T paramValue,
				final String note)
		{
			/* @formatter:off */
      this.otherObjectInfoList.add(ParamInfo
          .builder(paramType, paramName)
          .paramValue(paramValue)
          .note(note)
          .build());
      /* @formatter:on */
			return this;
		}

		public Builder addAllOtherObjectInfo(final ParamInfo<?>... otherObjectInfo)
		{
			this.otherObjectInfoList.addAll(Arrays.asList(otherObjectInfo));
			return this;
		}

		public Builder addAllOtherObjectInfo(final List<ParamInfo<?>> otherObjectInfoList)
		{
			this.otherObjectInfoList.addAll(otherObjectInfoList);
			return this;
		}

		public Builder clearOtherObjectInfoList()
		{
			this.otherObjectInfoList.clear();
			return this;
		}

		public Builder possibleReason(final String possibleReason)
		{
			this.possibleReason = possibleReason;
			return this;
		}

		public Builder addSuggestion(final String suggestion)
		{
			this.suggestionList.add(suggestion);
			return this;
		}

		public Builder addAllSuggestions(final String... suggestions)
		{
			this.suggestionList.addAll(Arrays.asList(suggestions));
			return this;
		}

		public Builder addAllSuggestions(final List<String> suggestionList)
		{
			this.suggestionList.addAll(suggestionList);
			return this;
		}

		public Builder clearSuggestionList()
		{
			this.suggestionList.clear();
			return this;
		}

		@Override
		public ExtraExceptionInformation build()
		{
			return new ExtraExceptionInformation(this);
		}
	}

	public static Builder builder()
	{
		return new Builder();
	}

	public ExtraExceptionInformation(final Builder builder)
	{
		this(builder.paramInfoList, builder.otherObjectInfoList, builder.possibleReason, builder.suggestionList);
	}

	private ExtraExceptionInformation(final List<ParamInfo<?>> paramInfoList,
			final List<ParamInfo<?>> otherObjectInfoList, final String possibleReason, final List<String> suggestionList)
	{
		/* @formatter:off */
    this.paramInfoList =
      null == paramInfoList ||
          paramInfoList.isEmpty() ?
          ParamInfo.EMPTY_PARAM_LIST :
          Collections.unmodifiableList(paramInfoList);
    this.otherObjectInfoList =
      null == otherObjectInfoList ||
      otherObjectInfoList.isEmpty() ?
          ParamInfo.EMPTY_PARAM_LIST :
          Collections.unmodifiableList(otherObjectInfoList);
    /* @formatter:on */
		this.possibleReason = possibleReason;
		/* @formatter:off */
    this.suggestionList =
      null == suggestionList ||
      suggestionList.isEmpty() ?
          EMPTY_SUGGESTION_LIST :
          Collections.unmodifiableList(suggestionList);
    /* @formatter:on */
	}

	public List<ParamInfo<?>> getParamInfoList()
	{
		return paramInfoList;
	}

	public List<ParamInfo<?>> getOtherObjectInfoList()
	{
		return otherObjectInfoList;
	}

	public String getPossibleReason()
	{
		return possibleReason;
	}

	public List<String> getSuggestionList()
	{
		return suggestionList;
	}

	@Override
	public int hashCode()
	{
		return hash(paramInfoList, otherObjectInfoList, possibleReason, suggestionList);
	}

	@Override
	public boolean equals(final Object extraExceptionInformation)
	{
		if (this == extraExceptionInformation)
		{
			return true;
		}
		final ExtraExceptionInformation that = castIfInstanceOf(ExtraExceptionInformation.class, extraExceptionInformation);
		/* @formatter:off */
    return isNotNull(that) &&
            (equal(this.paramInfoList,        that.getParamInfoList()) &&
             equal(this.otherObjectInfoList,  that.getOtherObjectInfoList()) &&
             equal(this.possibleReason,   that.getPossibleReason()) &&
             equal(this.suggestionList,   that.getSuggestionList()));
    /* @formatter:on */
	}

	@Override
	public String toString()
	{
		/* @formatter:off */
    return toStringBuilder(this)
        .add("paramInfoList",       paramInfoList)
        .add("otherObjectInfoList", otherObjectInfoList)
        .add("possibleReason",      possibleReason)
        .add("suggestionList",      suggestionList)
        .toString();
    /* @formatter:on */
	}
}
