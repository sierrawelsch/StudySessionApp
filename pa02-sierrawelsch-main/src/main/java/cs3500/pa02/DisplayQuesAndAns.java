package cs3500.pa02;

import java.io.IOException;

/**
 * represents displaying/appending Strings to an appendable
 */
public class DisplayQuesAndAns implements Display {

  Appendable out;

  /**
   * initializes the process of appending Strings to an appendable
   *
   * @param out represents an appendable object used for storing text
   */
  DisplayQuesAndAns(Appendable out) {
    this.out = out;
  }

  /**
   * appends the welcome message to the user to an appendable
   *
   * @throws IOException if an error occurs
   */
  public void display() throws IOException {
    out.append("Welcome to Study Session Mode!\n");
  }

  /**
   * appends a given String to an appendable
   *
   * @param phrase represents something to be appeneded
   *
   * @throws IOException if an error occurs
   */
  public void displayStatement(String phrase) throws IOException {
    out.append(phrase + "\n");
  }

  /**
   * appends the options a user can follow to an appendable
   *
   * @throws IOException if an error occurs
   */
  public void displayOptions() throws IOException {
    out.append("Press 1 For Hard\n");
    out.append("Press 2 For Easy\n");
    out.append("Press 3 For See Answer\n");
    out.append("Press 4 For Exit\n");
  }
}
