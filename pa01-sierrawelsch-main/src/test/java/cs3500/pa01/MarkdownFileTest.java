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
 * Represents tests for markdown files
 */
class MarkdownFileTest {

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


  /**
   * Initializes markdown files with given files on IntelliJ and their attributes for testing
   * that the value of the fields are correct
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
  }


  /**
   * Tests that the markdown file matches the appropiate file in IntelliJ
   */
  @Test
  void getFile() {
    setup();
    assertEquals(mockFileArrays.getFile(), arraysMd);
    assertEquals(mockFileVector.getFile(), vectorMd);
  }

  /**
   * Tests that the markdown file name matches the appropiate file name in IntelliJ
   */
  @Test
  void getFileName() {
    setup();
    assertEquals(mockFileArrays.getFileName(), "arrays.md");
    assertEquals(mockFileVector.getFileName(), "vector.md");
  }

  /**
   * Tests that the markdown file last modified time matches the appropriate
   * file last modified time in IntelliJ
   */
  @Test
  void getModified() {
    assertEquals(mockFileArrays.getModified(), modifiedArrays);
    assertEquals(mockFileVector.getModified(), modifiedVector);
  }

  /**
   * Tests that the markdown file created time matches the appropriate
   * file created time in IntelliJ
   */
  @Test
  void getCreated() {
    assertEquals(mockFileArrays.getCreated(), createdArrays);
    assertEquals(mockFileVector.getCreated(), createdVector);
  }
}