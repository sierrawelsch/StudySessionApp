package cs3500.pa02;

/**
 * represents keeping track of a users statistics during a study session
 */
public class StoreStats {
  int easyToHard;
  int hardToEasy;
  int hardQuestions;
  int easyQuestions;
  int questionsAnswered;

  /**
   * initializes most of the data to 0 besides the amount of hard and easy questions
   * there are in a file
   *
   * @param hardQuestions represents how many hard questions are in a file
   *
   * @param easyQuestions represents how many easy questions are in a file
   */
  StoreStats(int hardQuestions, int easyQuestions) {
    easyToHard = 0;
    hardToEasy = 0;
    questionsAnswered = 0;
    this.hardQuestions = hardQuestions;
    this.easyQuestions = easyQuestions;
  }

  /**
   * increments the amount of questions that change from easy to hard by 1
   * and decrements the amount of easy questions and increments the amount of
   * hard questions by 1
   */
  public void updateEasyToHard() {
    easyToHard++;
    hardQuestions++;
    easyQuestions--;
  }

  /**
   * increments the amount of questions that change from hard to easy by 1
   * and decrements the amount of hardQuestions and increments the amount of
   * easy questions by 1
   */
  public void updateHardToEasy() {
    hardToEasy++;
    easyQuestions++;
    hardQuestions--;
  }

  /**
   * increments the amount of questions the user has seen by 1
   */
  public void updateQuestionsAnswered() {
    questionsAnswered++;
  }

  /**
   * returns the amount of questions that changed from easy level of difficulty to hard
   *
   * @return an integer that represents the amount of questions that
   *     changed from easy level of difficulty to hard
   */
  public int getEasyToHard() {
    return easyToHard;
  }

  /**
   * returns the amount of questions that changed from hard level of difficulty to easy
   *
   * @return an integer that represents the amount of questions
   *     that changed from hard level of difficulty to easy
   */
  public int getHardToEasy() {
    return hardToEasy;
  }

  /**
   * returns the amount of questions that are considered hard
   *
   * @return an integer that represents the amount of questions that are considered hard
   */
  public int getHardQuestions() {
    return hardQuestions;
  }

  /**
   * returns the amount of questions that are considered easy
   *
   * @return an integer that represents the amount of questions that are considered easy
   */
  public int getEasyQuestions() {
    return easyQuestions;
  }

  /**
   * returns the amount of questions the user has seen
   *
   * @return an integer that represents the amount of questions the user has seen
   */
  public int getQuestionsAnswered() {
    return questionsAnswered;
  }
}
