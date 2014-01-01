package org.elixirian.kommonlee.io.util;

import static org.assertj.core.api.Assertions.*;

import org.elixirian.kommonlee.util.CommonConstants;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FileUtilTest
{
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

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
  public final void testFileUtil() throws Exception
  {
    /* given */
    /* expected */
    expectedException.expect(IllegalAccessException.class);
    expectedException.expectMessage(FileUtil.class.getName() + CommonConstants.CANNOT_BE_INSTANTIATED);

    /* when */
    new FileUtil();

    /* otherwise-fail */
    fail("IllegalAccessException was not thrown!");
  }

  @Test
  public final void testIndexOfExtension() throws Exception
  {
    /* given */
    final String filename = "test-file.json";
    final int expected = filename.lastIndexOf(".") + 1;

    /* when */
    final int actual = FileUtil.indexOfExtension(filename);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIndexOfExtensionOfFileHavingMultipleDots() throws Exception
  {
    /* given */
    final String filename = "this.is.a.test.file.json";
    final int expected = filename.lastIndexOf(".") + 1;

    /* when */
    final int actual = FileUtil.indexOfExtension(filename);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIndexOfExtensionOfDotFile() throws Exception
  {
    /* given */
    final String filename = ".bashrc";
    final int expected = -1;

    /* when */
    final int actual = FileUtil.indexOfExtension(filename);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIndexOfExtensionOfFileEndsWithDot() throws Exception
  {
    /* given */
    final String filename = "test-file.";
    final int expected = -1;

    /* when */
    final int actual = FileUtil.indexOfExtension(filename);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testIndexOfExtensionOfFileWithoutExtension() throws Exception
  {
    /* given */
    final String filename = "test-file";
    final int expected = -1;

    /* when */
    final int actual = FileUtil.indexOfExtension(filename);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testHasExtension() throws Exception
  {
    /* given */
    final String filename = "test-file.json";

    /* when */
    final boolean actual = FileUtil.hasExtension(filename);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testHasExtensionForFileWithMultipleDots() throws Exception
  {
    /* given */
    final String filename = "this.is.a.test.file.json";

    /* when */
    final boolean actual = FileUtil.hasExtension(filename);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testHasExtensionForDotFile() throws Exception
  {
    /* given */
    final String filename = ".bashrc";

    /* when */
    final boolean actual = FileUtil.hasExtension(filename);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testHasExtensionForFileEndsWithDot() throws Exception
  {
    /* given */
    final String filename = "test-file.";

    /* when */
    final boolean actual = FileUtil.hasExtension(filename);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testHasExtensionFileWithoutExtension() throws Exception
  {
    /* given */
    final String filename = "test-file";

    /* when */
    final boolean actual = FileUtil.hasExtension(filename);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testFindExtension() throws Exception
  {
    /* given */
    final String filename = "test-file.json";
    final String expected = "json";

    /* when */
    final String actual = FileUtil.findExtension(filename);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFindExtensionOfFileHavingMultipleDots() throws Exception
  {
    /* given */
    final String filename = "this.is.a.test.file.json";
    final String expected = "json";

    /* when */
    final String actual = FileUtil.findExtension(filename);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFindExtensionOfDotFile() throws Exception
  {
    /* given */
    final String filename = ".bashrc";
    final String expected = "";

    /* when */
    final String actual = FileUtil.findExtension(filename);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFindExtensionOfFileEndsWithDot() throws Exception
  {
    /* given */
    final String filename = "test-file.";
    final String expected = "";

    /* when */
    final String actual = FileUtil.findExtension(filename);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testFindExtensionOfFileWithoutExtension() throws Exception
  {
    /* given */
    final String filename = "test-file";
    final String expected = "";

    /* when */
    final String actual = FileUtil.findExtension(filename);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testAddBeforeExtensionIfExtensionExists() throws Exception
  {
    /* given */
    final String filename = "test-file.json";
    final String beforeExtension = "-1234567";
    final String expected = "test-file" + beforeExtension + ".json";

    /* when */
    final String actual = FileUtil.addBeforeExtensionIfExtensionExists(filename, beforeExtension);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testAddBeforeExtensionIfExtensionExistsForFileWithoutExtension() throws Exception
  {
    /* given */
    final String filename = "test-file";
    final String beforeExtension = "-1234567";
    final String expected = "test-file";

    /* when */
    final String actual = FileUtil.addBeforeExtensionIfExtensionExists(filename, beforeExtension);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }
  
  @Test
  public final void testAddBeforeExtensionOrTheEndIfNoExtension() throws Exception
  {
    /* given */
    final String filename = "test-file.json";
    final String beforeExtension = "-1234567";
    final String expected = "test-file" + beforeExtension + ".json";
    
    /* when */
    final String actual = FileUtil.addBeforeExtensionOrTheEndIfNoExtension(filename, beforeExtension);
    
    /* then */
    assertThat(actual).isEqualTo(expected);
  }
  
  @Test
  public final void testAddBeforeExtensionOrTheEndIfNoExtensionForFileWithoutExtension() throws Exception
  {
    /* given */
    final String filename = "test-file";
    final String beforeExtension = "-1234567";
    final String expected = "test-file" + beforeExtension;
    
    /* when */
    final String actual = FileUtil.addBeforeExtensionOrTheEndIfNoExtension(filename, beforeExtension);
    
    /* then */
    assertThat(actual).isEqualTo(expected);
  }

}
