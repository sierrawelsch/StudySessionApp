package cs3500.pa01;

import java.io.File;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Represents a markdown file
 */
public class MarkdownFile {
  private File file;

  private String fileName;
  private long modified;
  private long created;


  /**
   * Instantiates a markdown file with the original file and the files attributes
   *
   * @param file  represents a file on intelliJ
   * @param attrs represents the attributes of a file
   */
  MarkdownFile(File file, BasicFileAttributes attrs) {
    this.file = file;
    this.fileName = file.getName();
    this.modified = attrs.lastModifiedTime().toMillis();
    this.created = attrs.creationTime().toMillis();
  }

  MarkdownFile(File file, String fileName, long modified, long created) {
    this.file = file;
    this.fileName = fileName;
    this.modified = modified;
    this.created = created;
  }

  /**
   * gets the original file on intelliJ
   *
   * @return the original file
   */
  public File getFile() {
    return file;
  }

  /**
   * gets the name of the markdown file
   *
   * @return the name of the file
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * gets the long of when the markdown file was last modified
   *
   * @return the long of when the markdown file was last modified
   */
  public long getModified() {
    return modified;
  }

  /**
   * gets the long of when the markdown file was created
   *
   * @return the long of when the markdown file was created
   */
  public long getCreated() {
    return created;
  }
}
