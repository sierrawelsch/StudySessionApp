package cs3500.pa02;

import java.io.IOException;

/**
 * appends the user's statistics of their study session to an appendable
 */
public class DisplayStats implements Display {

  private StoreStats statistics;
  Appendable out;

  /**
   * initializes the process of appending the user's statistics of
   * their study session to an appendable
   *
   * @param statistics represents an object that stores
   *                   the user's statistics during the study session
   *
   * @param out represents an appendable that appends Strings
   */
  DisplayStats(StoreStats statistics, Appendable out) {
    this.out = out;
    this.statistics = statistics;
  }

  /**
   * appends the users statistics one the study session is complete to an appendable
   *
   * @throws IOException if an error occurs
   */
  public void display() throws IOException {
    out.append("Your Study Session is complete! Here are your stats:\n");
    out.append("You answered " + statistics.getQuestionsAnswered() + " questions.\n");
    out.append(statistics.getEasyToHard() + " questions went from easy to hard.\n");
    out.append(statistics.getHardToEasy() + " questions went from hard to easy\n");
    out.append("Current Question Bank:\n");
    out.append(statistics.getHardQuestions() + " Hard Questions\n");
    out.append(statistics.getEasyQuestions() + " Easy Questions\n");
  }
}
