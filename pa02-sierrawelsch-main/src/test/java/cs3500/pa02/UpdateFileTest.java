package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents testing that a file was properly rewritten/updated
 */
class UpdateFileTest {

  Path pathToSample;
  Question questionToChange;
  Level newLevel;
  UpdateFile update;
  ReadSrFile read;
  ArrayList<Question> questionsInFile;
  StudySessionModel model;

  /**
   * initializes a path to a sr file, a Level that is possibly
   * different from a question's original Level,
   * a Question in a file, and a UpdateFile object for testing
   */
  @BeforeEach
  void setup() {
    pathToSample = Path.of("./StudyGuide/sample.sr");
    newLevel = Level.EASY;
    questionToChange =
        new Question("What is the capital of Canada?", "The capital is Ottawa. ", Level.HARD);
    questionToChange.setLevel(Level.EASY);
    update = new UpdateFile(pathToSample, questionToChange, newLevel);
  }

  /**
   * tests that if a question's level in a file is different from a new level the file is rewritten
   * so the new level is associated with the question in the file (updating)
   */
  @Test
  void writeFile() {
    Scanner fileReader = null;
    String line;
    try {
      fileReader = new Scanner(pathToSample);
    } catch (IOException e) {
      fail();
    }
    while (fileReader.hasNextLine()) {
      line = fileReader.nextLine();
      if (line.substring(0, line.indexOf(":::")).equals(questionToChange.getQuestion())) {
        assertEquals(line.substring(line.indexOf("<") + 1, line.indexOf(">")), "HARD");
      }
    }
    try {
      update.writeFile();
    } catch (IOException e) {
      fail();
    }
    try {
      fileReader = new Scanner(pathToSample);
    } catch (IOException e) {
      fail();
    }
    while (fileReader.hasNextLine()) {
      line = fileReader.nextLine();
      if (line.substring(0, line.indexOf(":::")).equals(questionToChange.getQuestion())) {
        assertEquals(line.substring(line.indexOf("<") + 1, line.indexOf(">")), "EASY");
      }
    }
  }
}