
package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for my controller and that it ouputs correctly based on input
 */
class StudySessionDriverTest {
  StudySessionDriver ssDriver;
  String userInput;
  Readable input;
  Appendable output;
  Scanner inputScan;
  Random rand;
  StudySessionModel model;
  static String contents;

  /**
   * Initializes mock user input, a Readable (input), Appendable (output), a random object
   * a StudySessionDriver object, a Scanner object, and a StudySessionModel object to test
   */
  @BeforeEach
  void setup() {
    userInput =
        "./StudyGuide/sample.sr\n"
            + "5\n"
            + "1\n"
            + "2\n"
            + "3\n"
            + "4\n";
    input = new StringReader(userInput);
    output = new StringBuilder();
    rand = new Random(5);
    ssDriver = new StudySessionDriver(input, output, rand);
    inputScan = new Scanner(input);
    model = new StudySessionModel();
  }

  /**
   * Restores the file back to its original state so that all the questions are considered
   * hard to prevent mutation errors
   */
  @AfterEach
  void resetFile() {
    contents = "What is the capital of Canada? ::: The capital is Ottawa. <HARD>\n"
        +
        "Which country is known as the Land of the Rising Sun?:::Japan. <HARD>\n"
        +
        "What is the largest river in Africa? :::The largest river is the Nile River. <HARD>\n";
    try (FileWriter writer = new FileWriter("./StudyGuide/sample.sr")) {
      writer.write(contents);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * tests that the drive method appends the proper content to the appendable
   * based on the input that does not result in any exceptions being thrown
   */
  @Test
  void testDrive() {
    try {
      ssDriver.drive();
    } catch (IOException e) {
      fail();
    }
    assertEquals(output.toString(), "Welcome to Study Session Mode!\n"
        + "Please type in the SR File you would like to practice from:\n"
        + "How many questions would you like to practice today?\n"
        + "What is the capital of Canada? \n"
        + "Press 1 For Hard\n"
        + "Press 2 For Easy\n"
        + "Press 3 For See Answer\n"
        + "Press 4 For Exit\n"
        + "What is the largest river in Africa? \n"
        + "Press 1 For Hard\n"
        + "Press 2 For Easy\n"
        + "Press 3 For See Answer\n"
        + "Press 4 For Exit\n"
        + "Which country is known as the Land of the Rising Sun?\n"
        + "Press 1 For Hard\n"
        + "Press 2 For Easy\n"
        + "Press 3 For See Answer\n"
        + "Press 4 For Exit\n"
        + "Japan. \n"
        + "Your Study Session is complete! Here are your stats:\n"
        + "You answered 2 questions.\n"
        + "0 questions went from easy to hard.\n"
        + "1 questions went from hard to easy\n"
        + "Current Question Bank:\n"
        + "2 Hard Questions\n"
        + "1 Easy Questions\n");
  }

  /**
   * tests that the drive method appends the proper content to the appendable
   * based on the input that covers many cases of what the user could input
   * that does not result in any exceptions being thrown
   */
  @Test
  void testDriveWithMoreCases() {
    userInput =
        "./StudyGuide/sample.sr\n"
            + "5\n"
            + "1\n"
            + "3\n"
            + "1\n"
            + "3\n"
            + "2\n";
    input = new StringReader(userInput);
    ssDriver = new StudySessionDriver(input, output, rand);
    try {
      ssDriver.drive();
    } catch (IOException e) {
      fail();
    }
    assertEquals(output.toString(), "Welcome to Study Session Mode!\n"
        + "Please type in the SR File you would like to practice from:\n"
        + "How many questions would you like to practice today?\n"
        + "What is the capital of Canada? \n"
        + "Press 1 For Hard\n"
        + "Press 2 For Easy\n"
        + "Press 3 For See Answer\n"
        + "Press 4 For Exit\n"
        + "What is the largest river in Africa? \n"
        + "Press 1 For Hard\n"
        + "Press 2 For Easy\n"
        + "Press 3 For See Answer\n"
        + "Press 4 For Exit\n"
        + "The largest river is the Nile River. \n"
        + "Which country is known as the Land of the Rising Sun?\n"
        + "Press 1 For Hard\n"
        + "Press 2 For Easy\n"
        + "Press 3 For See Answer\n"
        + "Press 4 For Exit\n"
        + "Japan. \n"
        + "Your Study Session is complete! Here are your stats:\n"
        + "You answered 3 questions.\n"
        + "0 questions went from easy to hard.\n"
        + "1 questions went from hard to easy\n"
        + "Current Question Bank:\n"
        + "2 Hard Questions\n"
        + "1 Easy Questions\n");
  }

  /**
   * tests that an IllegalArgumentException is thrown if the user
   * provides enters a number that is not between 1-4 (inclusive) to react to a question
   */
  @Test
  void testDriveWithException() {
    userInput =
        "./StudyGuide/sample.sr\n"
            + "5\n" + "6\n";
    input = new StringReader(userInput);
    ssDriver = new StudySessionDriver(input, output, rand);
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> ssDriver.drive());
    assertEquals("You must type in a number between 1-4", exception.getMessage());
    userInput =
        "./StudyGuide/sample.sr\n"
            + "5\n"
            + "0\n";
    input = new StringReader(userInput);
    ssDriver = new StudySessionDriver(input, output, rand);
    exception = assertThrows(IllegalArgumentException.class,
        () -> ssDriver.drive());
    assertEquals("You must type in a number between 1-4", exception.getMessage());
  }

  /**
   * tests that an IllegalArgumentException is thrown if the user
   * provides a file that is not of type sr
   */
  @Test
  void testDriverWithFileExceptionSr() {
    userInput =
        "/Users/sierrawelsch/Desktop/CS3500/pa02-sierrawelsch/StudyGuide/sample.md\n";
    input = new StringReader(userInput);
    ssDriver = new StudySessionDriver(input, output, rand);
    try {
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> ssDriver.drive());
      assertEquals("The path you provided does not lead to a file type of .sr",
          exception.getMessage());
    } catch (NoSuchElementException e) {
      //catch the error
    }
  }

  /**
   * tests that an IllegalArgumentException is thrown if the user
   * provides a file that is empty
   */
  @Test
  void testDriverWithFileExceptionEmpty() {
    userInput =
        "./StudyGuide/testingInput.sr\n";
    input = new StringReader(userInput);
    ssDriver = new StudySessionDriver(input, output, rand);
    try {
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> ssDriver.drive());
      assertEquals("The path you provided leads to an empty file", exception.getMessage());
    } catch (NoSuchElementException e) {
      //catch the error
    }
  }

  /**
   * tests that an IllegalArgumentException is thrown if the user
   * provides a file that does not contain any questions
   */
  @Test
  void testDriverWithFileExceptionNoQuestions() {
    userInput =
        "./StudyGuide/incorrect.sr\n";
    input = new StringReader(userInput);
    ssDriver = new StudySessionDriver(input, output, rand);
    try {
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> ssDriver.drive());
      assertEquals("The path you provided leads to a file with no questions.",
          exception.getMessage());
    } catch (NoSuchElementException e) {
      //catch the error
    }
  }

}
