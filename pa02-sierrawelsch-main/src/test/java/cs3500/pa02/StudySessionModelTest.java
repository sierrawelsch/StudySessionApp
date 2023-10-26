package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents test for the big model class in terms of MVC that stores output
 * from other model type classes and performs tasks contributing to making a Study Session
 */
class StudySessionModelTest {

  StudySessionModel model;
  ArrayList<Question> questionsInFile;

  ArrayList<Question> questionsToStudy;
  SortQuestions sort;

  ArrayList<Question> questionsHardToEasy;
  StoreStats userProgress;
  Question q1;
  Question q2;
  Question q3;
  Path pathToSrFile;
  Path pathToUpdate;
  Random rand;

  /**
   * Initializes a StudySessionModel object, Question objects,
   * a list of questions in file (expected output),
   * a list of questions that represents them sorted from hard
   * to easy (expected ouptut), a list of
   * questions that is the size of the amount the user wants to study
   * (expected output), a sortQuestion object
   * a StoreStats object, a path to the sr file,
   */
  @BeforeEach
  void setup() {
    model = new StudySessionModel();
    questionsInFile = new ArrayList<Question>();
    q1 = new Question("What is the capital of Canada? ", " The capital is Ottawa. ", Level.HARD);
    q2 = new Question("Which country is known as the Land of the Rising Sun?", "Japan. ",
        Level.HARD);
    q3 = new Question("What is the largest river in Africa? ",
        "The largest river is the Nile River. ", Level.HARD);
    questionsInFile.add(q1);
    questionsInFile.add(q2);
    questionsInFile.add(q3);
    questionsHardToEasy = new ArrayList<Question>();
    questionsHardToEasy.add(q1);
    questionsHardToEasy.add(q3);
    questionsHardToEasy.add(q2);
    questionsToStudy = new ArrayList<>();
    questionsToStudy.add(q1);
    questionsToStudy.add(q3);
    sort = new SortQuestions(questionsInFile);
    userProgress = new StoreStats(sort.getHardQuestions(), sort.getEasyQuestions());
    pathToSrFile = Path.of("./StudyGuide/sample.sr");
    pathToUpdate = Path.of("./StudyGuide/testing.sr");
    rand = new Random(5);
  }

  /**
   * Restores the file back to its original state so that all the questions are considered
   * hard to prevent mutation errors
   */
  @AfterEach
  void resetFile() {
    String contents = "What is the capital of Canada? ::: The capital is Ottawa. <HARD>\n"
        + "Which country is known as the Land of the Rising Sun?:::Japan. <HARD>\n"
        + "What is the largest river in Africa? :::The largest river is the Nile River. <HARD>\n";
    try (FileWriter writer = new FileWriter("./StudyGuide/sample.sr")) {
      writer.write(contents);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * tests that questions from a sr file were properly initialized into Question objects
   */
  @Test
  void readSrFile() {
    try {
      model.readSrFile(pathToSrFile);
    } catch (IOException e) {
      fail();
    }
    int index = 0;
    for (Question q : model.getQuestionsInFile()) {
      assertEquals(q.getQuestion(), questionsInFile.get(index).getQuestion());
      assertEquals(q.getAnswer(), questionsInFile.get(index).getAnswer());
      assertEquals(q.getDifficulty(), questionsInFile.get(index).getDifficulty());
      index++;
    }
  }

  /**
   * tests that questions from a sr file were properly sorted from hard to easy
   */
  @Test
  void sortQuestions() {
    try {
      model.readSrFile(pathToSrFile);
    } catch (IOException e) {
      fail();
    }
    model.sortQuestions(rand);
    int index = 0;
    for (Question q : model.getQuestionsInFile()) {
      assertEquals(q.getQuestion(), questionsHardToEasy.get(index).getQuestion());
      assertEquals(q.getAnswer(), questionsHardToEasy.get(index).getAnswer());
      assertEquals(q.getDifficulty(), questionsHardToEasy.get(index).getDifficulty());
      index++;
    }
  }

  /**
   * tests that from a list of questions from a sr file,
   * a list of questions of a size of a given amount was outputted
   */
  @Test
  void generatedQuesAndAns() {
    try {
      model.readSrFile(pathToSrFile);
    } catch (IOException e) {
      fail();
    }
    model.sortQuestions(rand);
    model.generateQuesAndAns(2);
    int index = 0;
    for (Question q : model.getQuestionsToStudy()) {
      assertEquals(q.getQuestion(), questionsToStudy.get(index).getQuestion());
      assertEquals(q.getAnswer(), questionsToStudy.get(index).getAnswer());
      assertEquals(q.getDifficulty(), questionsToStudy.get(index).getDifficulty());
      index++;
    }
  }

  /**
   * tests that updating a questions level in a file affects a user's stored
   * statistics properly
   */
  @Test
  void update() {
    try {
      model.readSrFile(pathToSrFile);
    } catch (IOException e) {
      fail();
    }
    model.sortQuestions(rand);
    try {
      model.update(Level.HARD, q1, pathToSrFile);
    } catch (IOException e) {
      fail();
    }
    assertEquals(model.getUserProgress().getEasyToHard(), 0);
    assertEquals(model.getUserProgress().getHardToEasy(), 0);
    assertEquals(model.getUserProgress().getQuestionsAnswered(), 1);
    try {
      model.update(Level.EASY, q1, pathToSrFile);
    } catch (IOException e) {
      fail();
    }
    assertEquals(model.getUserProgress().getEasyToHard(), 0);
    assertEquals(model.getUserProgress().getHardToEasy(), 1);
    assertEquals(model.getUserProgress().getQuestionsAnswered(), 2);
    try {
      model.update(Level.HARD, q1, pathToUpdate);
    } catch (IOException e) {
      fail();
    }
    assertEquals(model.getUserProgress().getEasyToHard(), 1);
    assertEquals(model.getUserProgress().getHardToEasy(), 1);
    assertEquals(model.getUserProgress().getQuestionsAnswered(), 3);
  }

  /**
   * tests that a list of questions in a file gotten matches the list of questions that is expected
   */
  @Test
  void getQuestionsInFile() {
    try {
      model.readSrFile(pathToSrFile);
    } catch (IOException e) {
      fail();
    }
    int index = 0;
    for (Question q : model.getQuestionsInFile()) {
      assertEquals(q.getQuestion(), questionsInFile.get(index).getQuestion());
      assertEquals(q.getAnswer(), questionsInFile.get(index).getAnswer());
      assertEquals(q.getDifficulty(), questionsInFile.get(index).getDifficulty());
      index++;
    }
  }

  /**
   * tests that a list of questions of a given amount
   * gotten matches the list of questions that is expected
   */
  @Test
  void getQuestionsToStudy() {
    try {
      model.readSrFile(pathToSrFile);
    } catch (IOException e) {
      fail();
    }
    int index = 0;
    for (Question q : model.getQuestionsInFile()) {
      assertEquals(q.getQuestion(), questionsInFile.get(index).getQuestion());
      assertEquals(q.getAnswer(), questionsInFile.get(index).getAnswer());
      assertEquals(q.getDifficulty(), questionsInFile.get(index).getDifficulty());
      index++;
    }
  }

  /**
   * tests that a StoreStats object gotten matches an expected initialized StoreStats object
   */
  @Test
  void getUserProgress() {
    try {
      model.readSrFile(pathToSrFile);
    } catch (IOException e) {
      fail();
    }
    model.sortQuestions(rand);
    StoreStats progress = model.getUserProgress();
    assertEquals(progress.getQuestionsAnswered(), 0);
    assertEquals(progress.getEasyToHard(), 0);
    assertEquals(progress.getHardToEasy(), 0);
    assertEquals(progress.getQuestionsAnswered(), 0);
  }
}