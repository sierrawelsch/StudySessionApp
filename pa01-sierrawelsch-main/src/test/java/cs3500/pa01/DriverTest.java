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
 * Represents test for the driver method which essentially creates the study guide
 */
class DriverTest {
  Path pathToNotesRoot;
  OrderingFlag order;
  Path pathToStudyGuide;

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

  String studyguideeeeAbsolutePath;
  File studyguideeeeMd;

  Driver theDriver;

  long modifiedStudyGuideeee;
  long createdStudyGuideeee;

  MarkdownFile mockFileStudyGuideeee;


  /**
   * Initializes markdown files with given files on IntelliJ and their attributes for testing
   * that the driver method works appropriately
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


    studyguideeeeAbsolutePath = "./StudyGuide/studyguideeee.md";
    studyguideeeeMd = new File(studyguideeeeAbsolutePath);

    modifiedStudyGuideeee =
        FileTime.from(Instant.parse("2023-05-16T01:28:56.910455173Z")).toMillis();
    createdStudyGuideeee = FileTime.from(Instant.parse("2023-05-15T22:54:35Z")).toMillis();
    mockFileStudyGuideeee =
        new MarkdownFile(studyguideeeeMd, "studyguideeee.md", modifiedStudyGuideeee,
            createdStudyGuideeee);
  }

  /**
   * Initializes the path to the starting directory, the orderingFlag (filename),
   * and the path to where the study guide should be written
   */
  @BeforeEach
  void resetFileName() {
    pathToNotesRoot = Path.of("./Notes");
    order = OrderingFlag.FILENAME;
    pathToStudyGuide =
        Path.of("./StudyGuide/studyguide.md");
    theDriver = new Driver(pathToNotesRoot, order, pathToStudyGuide);
  }

  /**
   * Initializes the path to the starting directory, the orderingFlag (created),
   * and the path to where the study guide should be written
   */
  @BeforeEach
  void resetCreated() {
    pathToNotesRoot = Path.of("./Notes");
    order = OrderingFlag.CREATED;
    pathToStudyGuide =
        Path.of("./StudyGuide/studyguide.md");
    theDriver = new Driver(pathToNotesRoot, order, pathToStudyGuide);
  }

  /**
   * Initializes the path to the starting directory, the orderingFlag(created),
   * and the path to where the study guide should be written (the file will be created)
   */
  @BeforeEach
  void newStudyGuideFile() {
    pathToNotesRoot = Path.of("./Notes");
    order = OrderingFlag.CREATED;
    pathToStudyGuide =
        Path.of("./StudyGuide/studyguideeee.md");
    theDriver = new Driver(pathToNotesRoot, order, pathToStudyGuide);
  }

  /**
   * Initializes the path to the starting directory, the orderingFlag (modifed),
   * and the path to where the study guide should be written
   */
  @BeforeEach
  void resetModified() {
    pathToNotesRoot = Path.of("./Notes");
    order = OrderingFlag.MODIFIED;
    pathToStudyGuide =
        Path.of("./StudyGuide/studyguide.md");
    theDriver = new Driver(pathToNotesRoot, order, pathToStudyGuide);
  }

  /**
   * Tests that the driver class given the path to the starting directory, the orderingFlag, and
   * the path to where the study guide should be written has the proper content in the study guide
   */
  @Test
  void contentInStudyGuideTest() {
    setup();
    resetFileName();
    try {
      theDriver.drive();
    } catch (IOException e) {
      fail();
    }
    assertEquals(theDriver.content.toString(),
        "# Java Arrays\n"
            + "- An **array** is a collection of variables of the same type\n"
            + "\n"
            + "## Declaring an Array\n"
            + "- General Form: type[] arrayName;\n"
            + "- only creates a reference\n"
            + "- no array has  actually been created yet\n"
            + "\n"
            + "## Creating an Array (Instantiation)\n"
            + "- General form: arrayName = new type[numberOfElements];\n"
            + "- numberOfElements must be a positive Integer.\n"
            + "- Gotcha: Array size is not  modifiable once instantiated.\n"
            + "\n"
            + "# Vectors\n"
            + "- Vectors act like resizable arrays\n"
            + "\n"
            + "## Declaring a vector\n"
            + "- General Form: Vector<type> v = new Vector();\n"
            + "- type needs to be a valid reference type\n"
            + "\n"
            + "## Adding an element to a vector\n"
            + "- v.add(object of type);");
    try {
      assertEquals(Files.readString(theDriver.destination), theDriver.content.toString());
    } catch (IOException e) {
      fail();
    }

    resetCreated();
    try {
      theDriver.drive();
    } catch (IOException e) {
      fail();
    }
    assertEquals(theDriver.content.toString(),
        "# Vectors\n"
            + "- Vectors act like resizable arrays\n"
            + "\n"
            + "## Declaring a vector\n"
            + "- General Form: Vector<type> v = new Vector();\n"
            + "- type needs to be a valid reference type\n"
            + "\n"
            + "## Adding an element to a vector\n"
            + "- v.add(object of type);" + "\n"
            + "\n"
            + "# Java Arrays\n"
            + "- An **array** is a collection of variables of the same type\n"
            + "\n"
            + "## Declaring an Array\n"
            + "- General Form: type[] arrayName;\n"
            + "- only creates a reference\n"
            + "- no array has  actually been created yet\n"
            + "\n"
            + "## Creating an Array (Instantiation)\n"
            + "- General form: arrayName = new type[numberOfElements];\n"
            + "- numberOfElements must be a positive Integer.\n"
            + "- Gotcha: Array size is not  modifiable once instantiated.");
    try {
      assertEquals(Files.readString(theDriver.destination), theDriver.content.toString());
    } catch (IOException e) {
      fail();
    }

    resetModified();
    try {
      theDriver.drive();
    } catch (IOException e) {
      fail();
    }
    assertEquals(theDriver.content.toString(),
        "# Vectors\n"
            + "- Vectors act like resizable arrays\n"
            + "\n"
            + "## Declaring a vector\n"
            + "- General Form: Vector<type> v = new Vector();\n"
            + "- type needs to be a valid reference type\n"
            + "\n"
            + "## Adding an element to a vector\n"
            + "- v.add(object of type);" + "\n"
            + "\n"
            + "# Java Arrays\n"
            + "- An **array** is a collection of variables of the same type\n"
            + "\n"
            + "## Declaring an Array\n"
            + "- General Form: type[] arrayName;\n"
            + "- only creates a reference\n"
            + "- no array has  actually been created yet\n"
            + "\n"
            + "## Creating an Array (Instantiation)\n"
            + "- General form: arrayName = new type[numberOfElements];\n"
            + "- numberOfElements must be a positive Integer.\n"
            + "- Gotcha: Array size is not  modifiable once instantiated.");
    try {
      assertEquals(Files.readString(theDriver.destination), theDriver.content.toString());
    } catch (IOException e) {
      fail();
    }

    //this checks that a file that does not exist can be created and the correct content of
    // what should be written in the study guide will be written in this newly created file
    newStudyGuideFile();
    try {
      theDriver.drive();
    } catch (IOException e) {
      fail();
    }
    assertEquals(theDriver.content.toString(),
        "# Vectors\n"
            + "- Vectors act like resizable arrays\n"
            + "\n"
            + "## Declaring a vector\n"
            + "- General Form: Vector<type> v = new Vector();\n"
            + "- type needs to be a valid reference type\n"
            + "\n"
            + "## Adding an element to a vector\n"
            + "- v.add(object of type);" + "\n"
            + "\n"
            + "# Java Arrays\n"
            + "- An **array** is a collection of variables of the same type\n"
            + "\n"
            + "## Declaring an Array\n"
            + "- General Form: type[] arrayName;\n"
            + "- only creates a reference\n"
            + "- no array has  actually been created yet\n"
            + "\n"
            + "## Creating an Array (Instantiation)\n"
            + "- General form: arrayName = new type[numberOfElements];\n"
            + "- numberOfElements must be a positive Integer.\n"
            + "- Gotcha: Array size is not  modifiable once instantiated.");
    try {
      assertEquals(Files.readString(theDriver.destination), theDriver.content.toString());
    } catch (IOException e) {
      fail();
    }

  }

  /**
   * Tests that the all the markdown files in a starting directory were added to a list
   * of markdown files and the list of files was sorted properly based on the orderingFlag
   */
  @Test
  void unsortedAndSortedFilesTest() {
    setup();
    resetFileName();
    MyFileVisitor visitor = new MyFileVisitor();
    try {
      Files.walkFileTree(pathToNotesRoot, visitor);
    } catch (IOException e) {
      fail();
    }
    theDriver.unsortedMdFiles =
        new ArrayList<MarkdownFile>(Arrays.asList(mockFileSoBlank, mockFileArrays, mockFileVector));
    assertEquals(theDriver.unsortedMdFiles.get(0).getFileName(), mockFileSoBlank.getFileName());
    assertEquals(theDriver.unsortedMdFiles.get(1).getFileName(), mockFileArrays.getFileName());
    assertEquals(theDriver.unsortedMdFiles.get(2).getFileName(), mockFileVector.getFileName());
    Sort sorting = new Sort(order, theDriver.unsortedMdFiles);
    theDriver.sortedMdFiles = sorting.sortByComparator();
    assertEquals(theDriver.sortedMdFiles.get(0).getFileName(), mockFileArrays.getFileName());
    assertEquals(theDriver.sortedMdFiles.get(1).getFileName(), mockFileSoBlank.getFileName());
    assertEquals(theDriver.sortedMdFiles.get(2).getFileName(), mockFileVector.getFileName());

    resetCreated();
    visitor = new MyFileVisitor();
    try {
      Files.walkFileTree(pathToNotesRoot, visitor);
    } catch (IOException e) {
      fail();
    }
    theDriver.unsortedMdFiles =
        new ArrayList<MarkdownFile>(Arrays.asList(mockFileSoBlank, mockFileArrays, mockFileVector));
    assertEquals(theDriver.unsortedMdFiles.get(0).getFileName(), mockFileSoBlank.getFileName());
    assertEquals(theDriver.unsortedMdFiles.get(1).getFileName(), mockFileArrays.getFileName());
    assertEquals(theDriver.unsortedMdFiles.get(2).getFileName(), mockFileVector.getFileName());
    sorting = new Sort(order, theDriver.unsortedMdFiles);
    theDriver.sortedMdFiles = sorting.sortByComparator();
    assertEquals(theDriver.sortedMdFiles.get(0).getFileName(), mockFileVector.getFileName());
    assertEquals(theDriver.sortedMdFiles.get(1).getFileName(), mockFileArrays.getFileName());
    assertEquals(theDriver.sortedMdFiles.get(2).getFileName(), mockFileSoBlank.getFileName());

    resetModified();
    visitor = new MyFileVisitor();
    try {
      Files.walkFileTree(pathToNotesRoot, visitor);
    } catch (IOException e) {
      fail();
    }
    theDriver.unsortedMdFiles =
        new ArrayList<MarkdownFile>(Arrays.asList(mockFileSoBlank, mockFileArrays, mockFileVector));
    assertEquals(theDriver.unsortedMdFiles.get(0).getFileName(), mockFileSoBlank.getFileName());
    assertEquals(theDriver.unsortedMdFiles.get(1).getFileName(), mockFileArrays.getFileName());
    assertEquals(theDriver.unsortedMdFiles.get(2).getFileName(), mockFileVector.getFileName());
    sorting = new Sort(order, theDriver.unsortedMdFiles);
    theDriver.sortedMdFiles = sorting.sortByComparator();
    assertEquals(theDriver.sortedMdFiles.get(0).getFileName(), mockFileVector.getFileName());
    assertEquals(theDriver.sortedMdFiles.get(1).getFileName(), mockFileSoBlank.getFileName());
    assertEquals(theDriver.sortedMdFiles.get(2).getFileName(), mockFileArrays.getFileName());
  }
}