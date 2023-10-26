package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents test for sorting a list of questions from hard to easy
 */
class SortQuestionsTest {

  ArrayList<Question> questions;
  ArrayList<Question> sortedFromHardToEasy;
  Question q1;
  Question q2;
  Question q3;
  SortQuestions sort;

  /**
   * Initializes a list of questions, a list of questions
   * sorted from hard to easy (expected output),
   * and a sortQuestion object to test
   */
  @BeforeEach
  void setup() {
    questions = new ArrayList<Question>();
    sortedFromHardToEasy = new ArrayList<Question>();
    q1 = new Question("What is the capital of Canada?", "The capital is Ottawa. ", Level.HARD);
    q2 = new Question("Which country is known as the Land of the Rising Sun?", "Japan. ",
        Level.EASY);
    q3 = new Question("What is the largest river in Africa? ",
        " The largest river is the Nile River. ", Level.HARD);
    questions.add(q1);
    questions.add(q2);
    questions.add(q3);
    sort = new SortQuestions(questions);
    sortedFromHardToEasy.add(q3);
    sortedFromHardToEasy.add(q1);
    sortedFromHardToEasy.add(q2);
  }

  /**
   * Tests for if a list of questions is properly sorted with hard questions in the beginning
   * and easy questions at the end and that the list of questions mutates
   */
  @Test
  void sortFromHardToEasy() {
    int index = 0;
    assertEquals(questions.size(), 3);
    sort.sortFromHardToEasy();
    assertEquals(questions.size(), 3);
    for (Question q : questions) {
      assertEquals(q.getQuestion(), sortedFromHardToEasy.get(index).getQuestion());
      assertEquals(q.getAnswer(), sortedFromHardToEasy.get(index).getAnswer());
      assertEquals(q.getDifficulty(), sortedFromHardToEasy.get(index).getDifficulty());
      index++;
    }
  }

  /**
   * tests for if the number of hard questions in a list is correct
   */
  @Test
  void getHardQuestions() {
    sort.sortFromHardToEasy();
    assertEquals(sort.getHardQuestions(), 2);
  }

  /**
   * tests for if the number of easy questions in a list is correct
   */
  @Test
  void getEasyQuestions() {
    sort.sortFromHardToEasy();
    assertEquals(sort.getEasyQuestions(), 1);
  }
}