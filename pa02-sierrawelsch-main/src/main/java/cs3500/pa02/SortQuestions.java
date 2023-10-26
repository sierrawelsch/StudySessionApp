package cs3500.pa02;

import java.util.ArrayList;

/**
 * Represents sorting a list of questions from hard to easy
 */
public class SortQuestions {
  private ArrayList<Question> questions;
  private int hardQuestions;
  private int easyQuestions;

  /**
   * initialzies sorting a list of questions from hard to easy
   *
   * @param questions represents a list of questions from a file
   */
  SortQuestions(ArrayList<Question> questions) {
    this.questions = questions;
    this.hardQuestions = 0;
    this.easyQuestions = 0;
  }

  /**
   * sorts/mutates a list of questions from hard to easy by checking their difficulty level
   */
  public void sortFromHardToEasy() {
    int newIndex = 0;
    Question q;
    for (int index = 0; index < questions.size(); index++) {
      q = questions.get(index);
      if (q.getDifficulty().equals(Level.HARD)) {
        if (index != newIndex) {
          questions.remove(q);
          questions.add(newIndex, q);
          newIndex++;
        }
        hardQuestions++;
      }
    }
    easyQuestions = questions.size() - hardQuestions;
  }

  /**
   * returns how many questions are considered hard in a list of questions
   *
   * @return an integer which represents how many questions are
   *     considered hard in a list of questions
   */
  public int getHardQuestions() {
    return hardQuestions;
  }

  /**
   * returns how many questions are considered easy in a list of questions
   *
   * @return an integer which represents how many questions are considered easy in a
   *     list of questions
   */
  public int getEasyQuestions() {
    return easyQuestions;
  }
}
