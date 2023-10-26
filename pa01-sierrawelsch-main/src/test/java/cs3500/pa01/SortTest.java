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
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for sorting a list of markdown files based on the orderingFlag
 */
class SortTest {


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

  long modifiedSoBlank;
  long createdSoBlank;

  String soblankAbsolutePath;
  File soblankMd;
  MarkdownFile mockFileSoBlank;

  ArrayList<MarkdownFile> mdFiles;

  Sort sortingFileName;
  Sort sortingCreated;
  Sort sortingModified;

  /**
   * Initializes markdown files and their attributes and Sort objects with
   * different orderingFlags to test sorting a list of markdown files
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

    soblankAbsolutePath =
        "./Notes/compsci/s/soblank.md";
    soblankMd = new File(soblankAbsolutePath);

    modifiedSoBlank = FileTime.from(Instant.parse("2023-05-14T02:09:39.427874871Z")).toMillis();
    createdSoBlank = FileTime.from(Instant.parse("2023-05-14T02:09:39Z")).toMillis();
    mockFileSoBlank = new MarkdownFile(soblankMd, "soblank.md", modifiedSoBlank, createdSoBlank);

    mdFiles =
        new ArrayList<MarkdownFile>(Arrays.asList(mockFileVector, mockFileArrays, mockFileSoBlank));

    sortingFileName = new Sort(OrderingFlag.FILENAME, mdFiles);
    sortingCreated = new Sort(OrderingFlag.CREATED, mdFiles);
    sortingModified = new Sort(OrderingFlag.MODIFIED, mdFiles);
  }

  /**
   * Initializes the unsorted list of markdown files to properly test how each
   * orderingFlag sorts the list differently
   */
  @BeforeEach
  void reset() {
    mdFiles =
        new ArrayList<MarkdownFile>(Arrays.asList(mockFileVector, mockFileArrays, mockFileSoBlank));
    ;
  }

  /**
   * Tests that the list of markdown files is sorted properly based on
   * each different orderingFlag
   */
  @Test
  void sortByComparatorTest() {
    reset();
    assertEquals(sortingFileName.sortByComparator(),
        new ArrayList<MarkdownFile>(
            Arrays.asList(mockFileArrays, mockFileSoBlank, mockFileVector)));
    reset();
    assertEquals(sortingModified.sortByComparator(),
        new ArrayList<MarkdownFile>(
            Arrays.asList(mockFileVector, mockFileSoBlank, mockFileArrays)));
    reset();
    assertEquals(sortingCreated.sortByComparator(),
        new ArrayList<MarkdownFile>(
            Arrays.asList(mockFileVector, mockFileArrays, mockFileSoBlank)));
  }
}