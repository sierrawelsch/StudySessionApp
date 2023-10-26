package cs3500.pa02;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the process of returning a certain amount of questions from a list of questions
 */
public class GenerateQuestions {
  private ArrayList<Question> questions;

  private int hardQuestions;


  /**
   * initializes the process of returning a certain amount of questions from a list of questions
   *
   * @param questions represents a list of questions from a file
   * @param hardQuestions represents how many questions are labelled hard in that file
   */
  GenerateQuestions(ArrayList<Question> questions, int hardQuestions) {
    this.questions = questions;
    this.hardQuestions = hardQuestions;
  }

  /**
   * returns a list of questions of a given amount
   *
   * @param amount represents how many questions are wanted to study from
   *
   * @return a list of questions equal to the size of amount
   */
  public ArrayList<Question> getQuestions(int amount) {
    ArrayList<Question> questionsToStudy = new ArrayList<>();
    if (amount > questions.size()) {
      amount = questions.size();
    }
    for (int i = 0; i < amount; i++) {
      questionsToStudy.add(questions.get(i));
    }
    return questionsToStudy;
  }

}
