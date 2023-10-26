package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for visiting a file
 */
class MyFileVisitorTest {

  String samepleInputsDirectory;

  String arraysAbsolutePath;
  File arraysMd;
  BasicFileAttributes arraysAttrs;
  String vectorAbsolutePath;
  File vectorMd;
  BasicFileAttributes vectorAttrs;

  String soblankAbsolutePath;
  File soblankMd;
  BasicFileAttributes soblankAttrs;


  MarkdownFile md1;
  MarkdownFile md2;

  MarkdownFile md3;

  // build list of expected file paths
  ArrayList<MarkdownFile> expectedFiles;

  // get list of traversed Markdown file paths
  ArrayList<MarkdownFile> actualFiles;

  Sort sortExpected;
  Sort sortActual;

  MyFileVisitor mfv;

  /**
   * Initializes the markdown files, attributes,
   * list of expected and actual markdown files visited and a
   * MyFileVisitor object to test the visitor
   */
  public void setup() {
    mfv = new MyFileVisitor();
    samepleInputsDirectory =
        "./Notes";

    arraysAbsolutePath =
        "./Notes/compsci/arrays.md";
    arraysMd = new File(arraysAbsolutePath);
    arraysAttrs = null;
    try {
      arraysAttrs = Files.readAttributes(
          Path.of(arraysAbsolutePath), BasicFileAttributes.class);
    } catch (IOException e) {
      fail();
    }
    vectorAbsolutePath =
        "./Notes/vector.md";
    vectorMd = new File(vectorAbsolutePath);
    vectorAttrs = null;
    try {
      vectorAttrs = Files.readAttributes(
          Path.of(vectorAbsolutePath), BasicFileAttributes.class);
    } catch (IOException e) {
      fail();
    }

    soblankAbsolutePath =
        "./Notes/compsci/s/soblank.md";
    soblankMd = new File(soblankAbsolutePath);
    soblankAttrs = null;
    try {
      soblankAttrs = Files.readAttributes(
          Path.of(soblankAbsolutePath), BasicFileAttributes.class);
    } catch (IOException e) {
      fail();
    }


    md1 = new MarkdownFile(arraysMd, arraysAttrs);
    md2 = new MarkdownFile(vectorMd, vectorAttrs);
    md3 = new MarkdownFile(soblankMd, soblankAttrs);

    // build list of expected file paths
    expectedFiles = new ArrayList<>();
    expectedFiles.add(md1);
    expectedFiles.add(md2);
    expectedFiles.add(md3);

    // get list of traversed Markdown file paths
    actualFiles = mfv.getMdFiles();
  }

  /**
   * Tests building a list of all valid Markdown paths in a directory
   */
  @Test
  public void testGetMarkdownPaths() {
    setup();
    try {
      Files.walkFileTree(Path.of(samepleInputsDirectory), mfv);
    } catch (IOException e) {
      fail();
    }

    sortExpected = new Sort(OrderingFlag.FILENAME, expectedFiles);
    sortExpected.sortByComparator();
    sortActual = new Sort(OrderingFlag.FILENAME, actualFiles);
    sortActual.sortByComparator();
    // compare both lists
    assertEquals(3, actualFiles.size());
    assertEquals(expectedFiles.get(0).getFileName(), actualFiles.get(0).getFileName());
    assertEquals(expectedFiles.get(1).getFileName(), actualFiles.get(1).getFileName());
    assertEquals(expectedFiles.get(2).getFileName(), actualFiles.get(2).getFileName());

    IOException exc = new IOException();
    assertEquals(mfv.visitFileFailed(Path.of(arraysAbsolutePath), exc), FileVisitResult.CONTINUE);
  }
}