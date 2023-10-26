package cs3500.pa02;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;

/**
 * Represents test for the driver method which essentially creates the study guide
 */
class FileDriverTest {
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

  FileDriver theDriver;

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
    theDriver = new FileDriver(pathToNotesRoot, order, pathToStudyGuide);
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
    theDriver = new FileDriver(pathToNotesRoot, order, pathToStudyGuide);
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
    theDriver = new FileDriver(pathToNotesRoot, order, pathToStudyGuide);
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
    theDriver = new FileDriver(pathToNotesRoot, order, pathToStudyGuide);
  }

}