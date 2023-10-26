package cs3500.pa02;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * represents a Model class in terms of MVC, that initializes objects of other model type classes
 * and stores the data of the appropiate methods called to create a Study Session
 */
public class StudySessionModel {

  private ArrayList<Question> questionsInFile;

  private ArrayList<Question> questionsToStudy;
  private SortQuestions sort;

  private StoreStats userProgress;


  /**
   * initializes the process of initializes objects of other model type classes
   * and stores the data of the appropriate methods called to create a Study Session
   */
  StudySessionModel() {
  }

  /**
   * initializes an object of the ReadSrFile class and calls the method
   * extractQuestions and stores the output (a list of questions from the file)
   *
   * @param pathToQuesAndAns represents a path to the desired sr file
   *
   * @throws IOException if an error occurs
   */
  public void readSrFile(Path pathToQuesAndAns) throws IOException {
    ReadSrFile srFileReader = new ReadSrFile(pathToQuesAndAns);
    questionsInFile = srFileReader.extractQuestions();
  }

  /**
   * shuffles the questionsInFile randomly, initializes an object of
   *     the SortQuestions class and calls
   * the method sortFromHardToEasy that mutates questionsInFile and initializes a
   * StoreStats object to be used later
   *
   * @param rand represents a Random object to shuffle
   */
  public void sortQuestions(Random rand) {
    Collections.shuffle(questionsInFile, rand);
    sort = new SortQuestions(questionsInFile);
    sort.sortFromHardToEasy();
    userProgress = new StoreStats(sort.getHardQuestions(), sort.getEasyQuestions());
  }

  /**
   * initializes an object of the GenerateQuestions class and calls the method generateQuesAndAns
   * that generates a list of a given amountOfQuestions from a given list and stores it
   *
   * @param amountOfQuestions represents the amount of questions that should be in a list
   */
  public void generateQuesAndAns(int amountOfQuestions) {
    GenerateQuestions generateQuesAndAns =
        new GenerateQuestions(questionsInFile, sort.getHardQuestions());
    questionsToStudy = generateQuesAndAns.getQuestions(amountOfQuestions);
  }

  /**
   * takes in a Level of difficulty and compares it to a question's level of
   * difficulty to see if the file with that question in it needs to
   *     be updated if they are different and updates the users statistics
   *
   * @param newDifficulty represents the chosen level of difficulty
   *
   * @param q represents a question the user has seen
   *
   * @param pathToQuesAndAns represents a path to the file containing the questions
   *
   * @throws IOException if an error occurs (the path doesnt exist)
   */
  public void update(Level newDifficulty, Question q, Path pathToQuesAndAns)
      throws IOException {
    UpdateFile updateContent;
    if (!newDifficulty.equals(q.getDifficulty())) {
      q.setLevel(newDifficulty);
      updateContent = new UpdateFile(pathToQuesAndAns, q, newDifficulty);
      updateContent.writeFile();
      if (newDifficulty.equals(Level.HARD)) {
        userProgress.updateEasyToHard();
      } else {
        userProgress.updateHardToEasy();
      }
    }
    userProgress.updateQuestionsAnswered();
  }

  /**
   * gets a list of questions that are in a file
   *
   * @return a list of questions that are in a file
   */
  public ArrayList<Question> getQuestionsInFile() {
    return questionsInFile;
  }

  /**
   * gets a list of questions that will be displayed to the user
   *
   * @return a list of questions that will be displayed to the user
   */
  public ArrayList<Question> getQuestionsToStudy() {
    return questionsToStudy;
  }

  /**
   * gets a user's statistics of a study session after a study session has been completed
   *
   * @return a StoreStats object that contains the user's statistics of a study session
   */
  public StoreStats getUserProgress() {
    return userProgress;
  }

}
