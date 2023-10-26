package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents testing appending to an appendable
 */
class DisplayQuesAndAnsTest {

  DisplayQuesAndAns display;
  Appendable out;

  /**
   * Initializes the appendable and the DisplayQA object to test
   */
  @BeforeEach
  void setup() {
    out = new StringBuilder();
    display = new DisplayQuesAndAns(out);

  }

  /**
   * represents the test for appending the welcome message
   */
  @Test
  void display() {
    assertEquals(out.toString(), "");
    try {
      display.display();
    } catch (IOException e) {
      fail();
    }
    assertEquals(out.toString(), "Welcome to Study Session Mode!\n");
  }

  /**
   * represents the test for appending a given String to an appendable
   */
  @Test
   void displayStatement() {
    assertEquals(out.toString(), "");
    try {
      display.displayStatement("How many questions would you like to practice today?");
    } catch (IOException e) {
      fail();
    }
    assertEquals(out.toString(), "How many questions would you like to practice today?\n");
  }

  /**
   * represents the test for appending the options a user
   * can choose when presented a question to an appendable
   */
  @Test
  void displayOptions() {
    assertEquals(out.toString(), "");
    try {
      display.displayOptions();
    } catch (IOException e) {
      fail();
    }
    assertEquals(out.toString(), "Press 1 For Hard\n"
        + "Press 2 For Easy\n"
        + "Press 3 For See Answer\n"
        + "Press 4 For Exit\n");
  }
}