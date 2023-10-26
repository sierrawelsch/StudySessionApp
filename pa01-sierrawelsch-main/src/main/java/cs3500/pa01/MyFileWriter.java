package cs3500.pa01;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * represents the process of writing a file
 */
public class MyFileWriter {
  StringBuilder content;
  Path destination;

  /**
   * Instantiates the process of writing a file
   *
   * @param content     represents the content in all the markdown files that should go in
   *                    the study guide
   * @param destination represents the path of where the study guide should be written
   */
  MyFileWriter(StringBuilder content, Path destination) {
    this.content = content;
    this.destination = destination;
  }

  /**
   * converts the StringBuilder with all the content that should go in the study guide to a string
   * and writes that content onto the study guide file
   */
  public void writeFile() throws IOException {
    FileWriter writer = null;
    writer = new FileWriter(destination.toFile());
    String contentToWrite = content.toString();
    writer.write(contentToWrite);
    writer.close();
  }
}
