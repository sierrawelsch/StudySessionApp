package cs3500.pa02;


import java.io.IOException;


/**
 * Represents something that can write to a file
 */
public interface MyFileWriter {

  /**
   * writes to a given file
   *
   * @throws IOException if an error occurs (file doesnt exist)
   */
  void writeFile() throws IOException;
}
