package cs3500.pa02;

/**
 * Represents a Question in an sr File
 */
public class Question {
  private String question;
  private String answer;
  private Level difficulty;

  /**
   * initializes a question in an sr File
   *
   * @param question represents a question
   *
   * @param answer represents an answer to a question
   *
   * @param difficulty represents how difficult a question is considered
   */
  Question(String question, String answer, Level difficulty) {
    this.question = question;
    this.answer = answer;
    this.difficulty = difficulty;
  }

  /**
   * sets the Level to the given Level
   *
   * @param newLevel the new difficulty of this question
   */
  public void setLevel(Level newLevel) {
    this.difficulty = newLevel;
  }

  /**
   * returns a question
   *
   * @return a String that represents a question
   */
  public String getQuestion() {
    return question;
  }

  /**
   * returns an answer
   *
   * @return a String that represents an answer
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * returns the difficulty level of a question
   *
   * @return an enum Level that represents the difficulty level of a question
   */
  public Level getDifficulty() {
    return difficulty;
  }


}
