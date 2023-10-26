package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Represents tests for comparing the file names of markdown files
 */
class ComparatorForFileNameTest {
  String arraysAbsolutePath;
  File arraysMd;
  BasicFileAttributes arraysAttrs;
  String vectorAbsolutePath;
  File vectorMd;
  BasicFileAttributes vectorAttrs;


  MarkdownFile md1;
  MarkdownFile md2;

  ComparatorForFileName cffn;

  /**
   * Initializes markdown files and their attributes for the testing
   * the comparator on the created markdown file names
   */
  @BeforeEach
  public void setup() {
    arraysAbsolutePath =
        "./Notes/compsci/arrays.md";
    arraysMd = new File(arraysAbsolutePath);
    try {
      arraysAttrs = Files.readAttributes(
          Path.of(arraysAbsolutePath), BasicFileAttributes.class);
    } catch (IOException e) {
      fail();
    }
    vectorAbsolutePath =
        "./Notes/vector.md";
    vectorMd = new File(vectorAbsolutePath);
    try {
      vectorAttrs = Files.readAttributes(
          Path.of(vectorAbsolutePath), BasicFileAttributes.class);
    } catch (IOException e) {
      fail();
    }


    md1 = new MarkdownFile(arraysMd, arraysAttrs);
    md2 = new MarkdownFile(vectorMd, vectorAttrs);

    cffn = new ComparatorForFileName();
  }

  /**
   * Tests the comparator for comparing file names of markdown files
   */
  @Test
  public void compareTest() {
    setup();
    assertEquals(cffn.compare(md1, md2), -21);
    assertEquals(cffn.compare(md2, md1), 21);
    assertEquals(cffn.compare(md1, md1), 0);
  }
}