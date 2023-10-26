package cs3500.pa01;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;


/**
 * represents the process of visiting a file in a directory
 */
public class MyFileVisitor implements FileVisitor<Path> {
  private ArrayList<MarkdownFile> mdFiles;

  /**
   * Instantiates the process of visiting a file in a directory
   * and initializing the list of markdown files
   */
  MyFileVisitor() {
    mdFiles = new ArrayList<MarkdownFile>();
  }

  /**
   * Allows us to continue through this directory after first entering it
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return a FileVisitResult that allows us to continue through this directory
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return FileVisitResult.CONTINUE;
  }

  /**
   * Allows us to continue to the next directory
   * if there is one after reaching the end of this directory
   *
   * @param dir a reference to the directory
   * @param exc {@code null} if the iteration of the directory completes without
   *            an error; otherwise the I/O exception that caused the iteration
   *            of the directory to complete prematurely
   * @return a FileVisitResult that allows us to continue through this directory
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    return FileVisitResult.CONTINUE;
  }

  /**
   * Visits this file and checks if its a regular file and a markdown file,
   * if it is it converts it to a markdown file adds it to a list of markdown files
   *
   * @param file  a reference to the file
   * @param attrs the file's basic attributes
   * @return a FileVisitResult that allows us to continue through this directory
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    if (attrs.isRegularFile()) {
      String extension = file.getFileName().toString();
      if (extension.contains(".md")) {
        mdFiles.add(new MarkdownFile(file.toFile(), attrs));
      }
    }

    return FileVisitResult.CONTINUE;
  }

  /**
   * Prints an exception if the file can't be visited
   *
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return a FileVisitResult that allows us to continue through this directory
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    // Handle the error
    System.err.println(exc);
    return FileVisitResult.CONTINUE;
  }

  /**
   * gets the markdown files visited when visiting all the files in the directory
   *
   * @return a list of markdown files visited when visiting all the files in the directory
   */
  public ArrayList<MarkdownFile> getMdFiles() {
    return this.mdFiles;
  }

}
