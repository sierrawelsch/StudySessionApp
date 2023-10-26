package cs3500.pa02;

import java.io.IOException;

/**
 * Represents a controller in terms of MVC that takes in user input a
 * and interacts with other classes
 */
public interface ProgramDriver {
  /**
   * takes in user input and interacts with model and viewer classes to produce output
   *
   * @throws IOException if an error occurs
   */
  public void drive() throws IOException;
}
