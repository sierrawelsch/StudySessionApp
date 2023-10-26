package cs3500.pa02;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * represents the process of writing a file
 */
public class StudyGuideWriter implements MyFileWriter {
  StringBuilder content;
  Path destination;

  /**
   * Instantiates the process of writing a file
   *
   * @param content     represents the notesContent in all the markdown files that should go in
   *                    the study guide
   * @param destination represents the path of where the study guide should be written
   */
  public StudyGuideWriter(StringBuilder content, Path destination) {
    this.content = content;
    this.destination = destination;
  }

  /**
   * converts the StringBuilder with all the notesContent
   * that should go in the study guide to a string
   * and writes that notesContent onto the study guide file
   */
  public void writeFile() throws IOException {
    FileWriter writer = new FileWriter(destination.toFile());
    String notesContentToWrite = content.toString();
    writer.write(notesContentToWrite);
    writer.close();
  }
}
