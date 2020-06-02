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

import org.elixirian.kommonlee.type.GenericBuilder;
import org.junit.Test;

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
 * This test is to demonstrate how to use the {@link GenericBuilder} interface.
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-14)
 */
public class GenericBuilderTest
{
  private static class SomePojo
  {
    private final String surname;
    private final String givenName;
    private final int number;
    private final String email;

    private SomePojo(final String surname, final String givenName, final int number, final String email)
    {
      this.surname = surname;
      this.givenName = givenName;
      this.number = number;
      this.email = email;
    }

    private SomePojo(final SomePojoBuilder somePojoBuilder)
    {
      this.surname = somePojoBuilder.surname;
      this.givenName = somePojoBuilder.givenName;
      this.number = somePojoBuilder.number;
      this.email = somePojoBuilder.email;
    }

    /* @formatter:off */
    public String getSurname()   { return surname; }
    public String getGivenName() { return givenName; }
    public int getNumber() { return number; }
    public String getEmail() { return email; }
    /* @formatter:on */

    @Override
    public int hashCode()
    {
      final int prime = 31;
      int result = 1;
      result = prime * result + (null == surname ? 0 : surname.hashCode());
      result = prime * result + (null == givenName ? 0 : givenName.hashCode());
      result = prime * result + number;
      result = prime * result + (null == email ? 0 : email.hashCode());
      return result;
    }

    @Override
    public boolean equals(final Object obj)
    {
      /* @formatter:off */
      if (this == obj) return true;
      if (!(obj instanceof SomePojo)) return false;
      final SomePojo that = (SomePojo) obj;
      return (this.surname == that.getSurname() ||
            (null != this.surname && this.surname.equals(that.getSurname()))) &&
          (this.givenName == that.getGivenName() ||
            (null != this.givenName && this.givenName.equals(that.getGivenName()))) &&
          this.number == that.getNumber() &&
          (this.email == that.getEmail() ||
            (null != this.email && this.email.equals(that.getEmail())));
      /* @formatter:on */
    }
  }

  private static class SomePojoBuilder implements GenericBuilder<SomePojo>
  {
    private String surname;
    private String givenName;
    private int number;
    private String email;

    public SomePojoBuilder setSurname(final String surname)
    {
      this.surname = surname;
      return this;
    }

    public SomePojoBuilder setGivenName(final String givenName)
    {
      this.givenName = givenName;
      return this;
    }

    public SomePojoBuilder setNumber(final int number)
    {
      this.number = number;
      return this;
    }

    public SomePojoBuilder setEmail(final String email)
    {
      this.email = email;
      return this;
    }

    @Override
    public SomePojo build()
    {
      return new SomePojo(this);
    }
  }

  @Test
  public final void testBuild()
  {
    final String surname = "Lee";
    final String givenName = "Kevin";
    final int number = 123;
    final String email = "kevin@test.test";

    final SomePojo expected = new SomePojo(surname, givenName, number, email);
    final SomePojo result = new SomePojoBuilder().setSurname(surname)
        .setGivenName(givenName)
        .setNumber(number)
        .setEmail(email)
        .build();
    assertThat(result, is(notNullValue()));
    assertThat(result, is(equalTo(expected)));

    assertThat(result.getSurname(), is(equalTo(expected.getSurname())));
    assertThat(result.getGivenName(), is(equalTo(expected.getGivenName())));
    assertEquals(result.getNumber(), expected.getNumber());
    assertThat(result.getEmail(), is(equalTo(expected.getEmail())));
  }

}
