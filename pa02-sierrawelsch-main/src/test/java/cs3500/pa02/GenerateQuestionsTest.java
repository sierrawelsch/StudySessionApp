package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for generating a certain amount of questions
 */
class GenerateQuestionsTest {

  GenerateQuestions generateQ;
  ArrayList<Question> questions;
  Question q1;
  Question q2;
  Question q3;

  ArrayList<Question> generatedQuestions;
  ArrayList<Question> expectedQuestions;


  /**
   * Initializes two list of questions that should be outputted, question objects,
   * and a GenerateQuestion object to test
   */
  @BeforeEach
  void setup() {
    questions = new ArrayList<Question>();
    q1 = new Question("What is the capital of Canada?", "The capital is Ottawa. ", Level.HARD);
    q2 = new Question("Which country is known as the Land of the Rising Sun?", "Japan. ",
        Level.HARD);
    q3 = new Question("What is the largest river in Africa? ",
        " The largest river is the Nile River. ", Level.EASY);
    questions.add(q1);
    questions.add(q2);
    questions.add(q3);

    generateQ = new GenerateQuestions(questions, 2);
    generatedQuestions = new ArrayList<>();
    expectedQuestions = new ArrayList<>();
    expectedQuestions.add(q1);
    expectedQuestions.add(q2);
    expectedQuestions.add(q3);
  }

  /**
   * tests that depending on different amounts of questions asked for,
   * it outputs the correct amount of questions
   * in the order of hard to easy
   */
  @Test
  void getQuestions() {
    int index = 0;
    generatedQuestions = generateQ.getQuestions(5);
    for (Question q : generatedQuestions) {
      assertEquals(q.getQuestion(), expectedQuestions.get(index).getQuestion());
      assertEquals(q.getAnswer(), expectedQuestions.get(index).getAnswer());
      assertEquals(q.getDifficulty(), expectedQuestions.get(index).getDifficulty());
      index++;
    }
    index = 0;
    generatedQuestions = generateQ.getQuestions(3);
    for (Question q : generatedQuestions) {
      assertEquals(q.getQuestion(), expectedQuestions.get(index).getQuestion());
      assertEquals(q.getAnswer(), expectedQuestions.get(index).getAnswer());
      assertEquals(q.getDifficulty(), expectedQuestions.get(index).getDifficulty());
      index++;
    }
    index = 0;
    generatedQuestions = generateQ.getQuestions(2);
    expectedQuestions = new ArrayList<>();
    expectedQuestions.add(q1);
    expectedQuestions.add(q2);
    for (Question q : generatedQuestions) {
      assertEquals(q.getQuestion(), expectedQuestions.get(index).getQuestion());
      assertEquals(q.getAnswer(), expectedQuestions.get(index).getAnswer());
      assertEquals(q.getDifficulty(), expectedQuestions.get(index).getDifficulty());
      index++;
    }
  }

}