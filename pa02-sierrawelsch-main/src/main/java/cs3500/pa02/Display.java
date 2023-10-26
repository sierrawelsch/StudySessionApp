package cs3500.pa02;

import java.io.IOException;

/**
 * Represents something that can display content to the console, represents a viewer
 * in MVC
 */
public interface Display {
  /**
   * displays text to the console
   *
   * @throws IOException if an error occurs
   */
  public void display() throws IOException;
}
