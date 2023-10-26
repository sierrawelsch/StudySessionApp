package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for if a user's statistics are updated properly and kept track of during
 * a study session
 */
class StoreStatsTest {

  StoreStats testForStats;

  /**
   * initializes a StoreStats object with the original amount of hard and easy questions
   * in the question bank for testing
   */
  @BeforeEach
  void setup() {
    testForStats = new StoreStats(20, 5);
  }

  /**
   * tests that the easyToHard variable is incremented properly along with
   * the amount of hard and easy questions changing
   */
  @Test
  void updateEasyToHard() {
    assertEquals(testForStats.getEasyToHard(), 0);
    assertEquals(testForStats.getEasyQuestions(), 5);
    assertEquals(testForStats.getHardQuestions(), 20);
    testForStats.updateEasyToHard();
    testForStats.updateQuestionsAnswered();
    assertEquals(testForStats.getEasyQuestions(), 4);
    assertEquals(testForStats.getHardQuestions(), 21);
    assertEquals(testForStats.getEasyToHard(), 1);
    assertEquals(testForStats.getHardToEasy(), 0);
    assertEquals(testForStats.getQuestionsAnswered(), 1);
  }

  /**
   * tests that the hardToEasy field is incremented properly along with
   * the amount of hard and easy questions changing
   */
  @Test
  void updateHardToEasy() {
    assertEquals(testForStats.getHardToEasy(), 0);
    assertEquals(testForStats.getEasyQuestions(), 5);
    assertEquals(testForStats.getHardQuestions(), 20);
    testForStats.updateHardToEasy();
    testForStats.updateQuestionsAnswered();
    testForStats.updateHardToEasy();
    testForStats.updateQuestionsAnswered();
    assertEquals(testForStats.getEasyQuestions(), 7);
    assertEquals(testForStats.getHardQuestions(), 18);
    assertEquals(testForStats.getEasyToHard(), 0);
    assertEquals(testForStats.getHardToEasy(), 2);
    assertEquals(testForStats.getQuestionsAnswered(), 2);
  }

  /**
   * tests that the questionsAnswered field is incremented and initialized properly
   */
  @Test
  void updateQuestionsAnswered() {
    assertEquals(testForStats.getQuestionsAnswered(), 0);
    testForStats.updateQuestionsAnswered();
    testForStats.updateQuestionsAnswered();
    testForStats.updateQuestionsAnswered();
    assertEquals(testForStats.getQuestionsAnswered(), 3);
  }

  /**
   * tests that the easyToHard variable has the correct value
   */
  @Test
  void getEasyToHard() {
    assertEquals(testForStats.getEasyToHard(), 0);
    testForStats.updateEasyToHard();
    testForStats.updateEasyToHard();
    testForStats.updateEasyToHard();
    assertEquals(testForStats.getEasyToHard(), 3);
    testForStats.updateQuestionsAnswered();
    assertEquals(testForStats.getEasyToHard(), 3);
  }

  /**
   * tests that the hardToEasy variable has the correct value
   */
  @Test
  void getHardToEasy() {
    assertEquals(testForStats.getHardToEasy(), 0);
    testForStats.updateHardToEasy();
    testForStats.updateHardToEasy();
    assertEquals(testForStats.getHardToEasy(), 2);
    testForStats.updateQuestionsAnswered();
    assertEquals(testForStats.getHardToEasy(), 2);
  }

  /**
   * tests that the number of hard questions has the correct value
   */
  @Test
  void getHardQuestions() {
    assertEquals(testForStats.getHardQuestions(), 20);
    testForStats.updateHardToEasy();
    assertEquals(testForStats.getHardQuestions(), 19);
    testForStats.updateEasyToHard();
    assertEquals(testForStats.getHardQuestions(), 20);
    testForStats.updateQuestionsAnswered();
    assertEquals(testForStats.getHardQuestions(), 20);
  }

  /**
   * tests that the number of easy questions has the correct value
   */
  @Test
  void getEasyQuestions() {
    assertEquals(testForStats.getEasyQuestions(), 5);
    testForStats.updateHardToEasy();
    assertEquals(testForStats.getEasyQuestions(), 6);
    testForStats.updateEasyToHard();
    assertEquals(testForStats.getEasyQuestions(), 5);
    testForStats.updateQuestionsAnswered();
    assertEquals(testForStats.getEasyQuestions(), 5);
  }

  /**
   * tests that the number of questions answered has the correct value
   */
  @Test
  void getQuestionsAnswered() {
    assertEquals(testForStats.getQuestionsAnswered(), 0);
    testForStats.updateQuestionsAnswered();
    testForStats.updateQuestionsAnswered();
    testForStats.updateQuestionsAnswered();
    testForStats.updateQuestionsAnswered();
    testForStats.updateQuestionsAnswered();
    assertEquals(testForStats.getQuestionsAnswered(), 5);
    assertEquals(testForStats.getQuestionsAnswered(), 5);

  }
}