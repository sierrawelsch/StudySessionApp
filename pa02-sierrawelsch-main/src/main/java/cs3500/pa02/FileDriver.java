package cs3500.pa02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * This is one of the driver's of this project which essentially does the work of
 * creating the Study Guide and a sr file which contains questions/answers
 */
public class FileDriver implements ProgramDriver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  private Path pathToNotesRoot;
  private OrderingFlag order;
  private Path destination;
  private Path pathToSr;
  private ArrayList<MarkdownFile> unsortedMdFiles;
  private ArrayList<MarkdownFile> sortedMdFiles;
  private StringBuilder notesContent;

  private StringBuilder questionContent;

  /**
   * Instantiates the process of creating the study guide and sr file and
   * initializing all the variables that will be utilized
   *
   * @param pathToRoot represents the Path to the starting directory
   * @param order      represents the way the user desires the files to be sorted by
   * @param dest       represents the Path to where the notesContent of
   *                   the Study Guide should be written
   */
  FileDriver(Path pathToRoot, OrderingFlag order, Path dest) {
    this.pathToNotesRoot = pathToRoot;
    this.order = order;
    this.destination = dest;
    getPathToSrFile();
    unsortedMdFiles = new ArrayList<MarkdownFile>();
    sortedMdFiles = new ArrayList<MarkdownFile>();
    notesContent = new StringBuilder();
    questionContent = new StringBuilder();
  }

  /**
   * initiates the process of creating the StudyGuide by instantiating objects of each class
   * and calling the appropriate methods on them
   */
  public void drive() throws IOException {
    MyFileVisitor visitor = new MyFileVisitor();
    Files.walkFileTree(pathToNotesRoot, visitor);
    unsortedMdFiles = visitor.getMdFiles();
    Sort sorting = new Sort(order, unsortedMdFiles);
    sortedMdFiles = sorting.sortByComparator();
    FileReader read = new FileReader(sortedMdFiles);
    read.createFile();
    notesContent = read.getNotesContent();
    questionContent = read.getQuestionContent();
    StudyGuideWriter writeStudyGuide = new StudyGuideWriter(notesContent, destination);
    StudyGuideWriter writeQuestionAndAnswer = new StudyGuideWriter(questionContent, pathToSr);
    writeStudyGuide.writeFile();
    writeQuestionAndAnswer.writeFile();
  }

  /**
   * takes the path to the markdown file and uses substring to create the path to the sr file
   */
  private void getPathToSrFile() {
    String destPathToString = destination.toString();
    int mdIndex = destPathToString.indexOf(".md");
    String pathToDestFolder = destPathToString.substring(0, mdIndex) + ".sr";
    pathToSr = Path.of(pathToDestFolder);
  }
}