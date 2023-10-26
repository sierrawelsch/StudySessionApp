package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for comparing the times the markdown files were last modified
 */
class ComparatorForModifiedTest {

  String arraysAbsolutePath;
  File arraysMd;
  String vectorAbsolutePath;
  File vectorMd;

  long modifiedArrays;
  long createdArrays;
  MarkdownFile mockFileArrays;

  long modifiedVector;
  long createdVector;
  MarkdownFile mockFileVector;
  ComparatorForModified cffn;


  /**
   * Initializes markdown files with given files on IntelliJ and their attributes for testing
   * the comparator on the modified markdown file times
   */
  @BeforeEach
  public void setup() {
    arraysAbsolutePath =
        "./Notes/compsci/arrays.md";
    arraysMd = new File(arraysAbsolutePath);

    modifiedArrays = FileTime.from(Instant.parse("2023-05-15T19:39:32.305777281Z")).toMillis();
    createdArrays = FileTime.from(Instant.parse("2023-05-13T01:42:02Z")).toMillis();
    mockFileArrays = new MarkdownFile(arraysMd, "arrays.md", modifiedArrays, createdArrays);

    vectorAbsolutePath =
        "./Notes/vector.md";
    vectorMd = new File(vectorAbsolutePath);

    modifiedVector = FileTime.from(Instant.parse("2023-05-13T01:21:53.791631392Z")).toMillis();
    createdVector = FileTime.from(Instant.parse("2023-05-13T01:20:27Z")).toMillis();
    mockFileVector = new MarkdownFile(vectorMd, "vector.md", modifiedVector, createdVector);
    cffn = new ComparatorForModified();
  }

  /**
   * Tests the comparator for comparing times the markdown files were created
   */
  @Test
  public void compareTest() {
    assertEquals(cffn.compare(mockFileArrays, mockFileVector), 1);
    assertEquals(cffn.compare(mockFileVector, mockFileArrays), -1);
    assertEquals(cffn.compare(mockFileVector, mockFileVector), 0);
  }
}