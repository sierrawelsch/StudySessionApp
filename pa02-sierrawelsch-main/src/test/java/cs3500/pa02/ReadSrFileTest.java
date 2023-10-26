package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for reading a sr file and creating Question objects
 */
class ReadSrFileTest {
  ArrayList<Question> expectedQuestions;
  Question q1;
  Question q2;
  Question q3;
  ReadSrFile readSr;

  Path pathToSrFile;
  ReadSrFile read;
  ArrayList<Question> questionsInFile;


  /**
   * Initializes a list of questions that is expected to be outputted along with Question objects
   * and a Path to the file being read to test
   */
  @BeforeEach
  void setup() {
    expectedQuestions = new ArrayList<Question>();
    q1 = new Question("What is the capital of Canada? ", " The capital is Ottawa. ", Level.HARD);
    q2 = new Question("Which country is known as the Land of the Rising Sun?", "Japan. ",
        Level.HARD);
    q3 = new Question("What is the largest river in Africa? ",
        "The largest river is the Nile River. ", Level.HARD);
    expectedQuestions.add(q1);
    expectedQuestions.add(q2);
    expectedQuestions.add(q3);
    pathToSrFile = Path.of("./StudyGuide/sample.sr");
    try {
      readSr = new ReadSrFile(pathToSrFile);
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * resets the questions in a file so that their level
   * is kept as hard (prevents mutuation errors)
   */
  @BeforeEach
  void resetFile() {
    try {
      read = new ReadSrFile(pathToSrFile);
    } catch (IOException e) {
      fail();
    }
    try {
      questionsInFile = read.extractQuestions();
    } catch (IOException e) {
      fail();
    }
    for (Question q : questionsInFile) {
      q.setLevel(Level.HARD);
    }
  }

  /**
   * tests that after reading a sr file the
   * correct list of questions are generated in the proper order with the right contents
   */
  @Test
  void extractQuestions() {
    ArrayList<Question> actualQuestions = new ArrayList<>();
    int index = 0;
    try {
      actualQuestions = readSr.extractQuestions();
    } catch (IOException e) {
      fail();
    }
    for (Question q : actualQuestions) {
      assertEquals(q.getQuestion(), expectedQuestions.get(index).getQuestion());
      assertEquals(q.getAnswer(), expectedQuestions.get(index).getAnswer());
      assertEquals(q.getDifficulty(), expectedQuestions.get(index).getDifficulty());
      index++;
    }
  }
}