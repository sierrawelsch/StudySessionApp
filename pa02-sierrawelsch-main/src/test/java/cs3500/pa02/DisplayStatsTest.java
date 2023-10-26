package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents a test for appending a user's statistics to an appendable
 */
class DisplayStatsTest {
  Appendable out;
  StoreStats stats;
  DisplayStats displayStatistics;

  /**
   * initializes the StringBuilder a StoreStats object and a DisplayStatistics object for testing
   */
  @BeforeEach
  void setup() {
    out = new StringBuilder();
    stats = new StoreStats(20, 5);
    displayStatistics = new DisplayStats(stats, out);
  }

  /**
   * tests that the proper data and words were appended to the appendable
   * when a StoreStats object was updated in many ways
   */
  @Test
  void display() {
    assertEquals(out.toString(), "");
    stats.updateQuestionsAnswered();
    stats.updateHardToEasy();
    stats.updateQuestionsAnswered();
    stats.updateQuestionsAnswered();
    stats.updateEasyToHard();
    stats.updateQuestionsAnswered();
    stats.updateHardToEasy();
    stats.updateQuestionsAnswered();
    try {
      displayStatistics.display();
    } catch (IOException e) {
      fail();
    }
    assertEquals(out.toString(), "Your Study Session is complete! Here are your stats:\n"
        + "You answered " + 5 + " questions.\n"
        + 1 + " questions went from easy to hard.\n"
        + 2 + " questions went from hard to easy\n"
        + "Current Question Bank:\n"
        + 19 + " Hard Questions\n"
        + 6 + " Easy Questions\n");
  }
}