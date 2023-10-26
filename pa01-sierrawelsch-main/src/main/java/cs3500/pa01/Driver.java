package cs3500.pa01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * This is the main driver of this project which essentially does the work of
 * creating the Study Guide
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  Path pathToNotesRoot;
  OrderingFlag order;
  Path destination;
  ArrayList<MarkdownFile> unsortedMdFiles;
  ArrayList<MarkdownFile> sortedMdFiles;
  StringBuilder content;

  /**
   * Instantiates the process of creating the study guide and
   * initializing all the variables that will be utilized
   *
   * @param pathToRoot represents the Path to the starting directory
   * @param order      represents the way the user desires the files to be sorted by
   * @param dest       represents the Path to where the content of the Study Guide should be written
   */
  Driver(Path pathToRoot, OrderingFlag order, Path dest) {
    this.pathToNotesRoot = pathToRoot;
    this.order = order;
    this.destination = dest;
    unsortedMdFiles = new ArrayList<MarkdownFile>();
    sortedMdFiles = new ArrayList<MarkdownFile>();
    content = new StringBuilder();
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
    content = read.readFile();
    MyFileWriter write = new MyFileWriter(content, destination);
    write.writeFile();
  }
}