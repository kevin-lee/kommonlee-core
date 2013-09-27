package org.elixirian.kommonlee.util.string;

import java.util.regex.Pattern;

import static org.fest.assertions.api.Assertions.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegularExpressionUtilTest
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
  public final void testNullSafeReplaceAll() throws Exception
  {
    /* given */
    final String expected = "appName"; 
    final String text = expected + "-0.0.1";
    final Pattern pattern = Pattern.compile("-[\\d]*(.[\\d])*");

    /* when */
    final String actual = RegularExpressionUtil.nullSafeReplaceAll(text, pattern, "");

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIndexOfStringPattern() throws Exception
  {
    /* given */
    final String version = "-0.0.1";
    final String text = "appName" + version + " something else-1.0.5";
    final int expected = text.indexOf(version); 
    final Pattern pattern = Pattern.compile("-[\\d]*(.[\\d])*");

    /* when */
    final int actual = RegularExpressionUtil.indexOf(text, pattern);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIndexOfStringPatternInt() throws Exception
  {
    /* given */
    final String appName = "appName";
    final String version = "-0.0.1";
    final String anotherVersion = "-1.0.15";
    final String text = appName + version + " something else" + anotherVersion + " blah blah-2.3.19";
    final int expected = text.indexOf(anotherVersion); 
    final Pattern pattern = Pattern.compile("-[\\d]*(.[\\d])*");

    /* when */
    final int actual = RegularExpressionUtil.indexOf(text, pattern, appName.length() + version.length());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

}
