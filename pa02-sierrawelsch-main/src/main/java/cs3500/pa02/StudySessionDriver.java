package cs3500.pa02;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a Controller in terms of MVC that essentially creates the Study Session
 */
public class StudySessionDriver implements ProgramDriver {
  private Path pathToQuesAndAns;
  private Readable in;
  private Appendable out;

  private int amountOfQuestions;

  private int chosenOption;
  private Random rand;

  /**
   * initializes a Controller in terms of MVC that essentially creates the Study Session
   *
   * @param in represents a Readable for input
   *
   * @param out represents an Appendable for output
   *
   * @param rand represents a Random object
   */
  StudySessionDriver(Readable in, Appendable out, Random rand) {
    this.in = in;
    this.out = out;
    this.rand = rand;
  }


  /**
   * takes in user input and passes that in to model and display class methods
   * and calls them to create the study session
   *
   * @throws IOException if an error occurs
   */
  public void drive() throws IOException {
    DisplayQuesAndAns viewer = new DisplayQuesAndAns(out);
    Scanner input = new Scanner(this.in);
    viewer.display();
    viewer.displayStatement("Please type in the SR File you would like to practice from:");
    pathToQuesAndAns = Path.of(input.nextLine());
    if (isValidSrFile(pathToQuesAndAns)) {
      StudySessionModel model = new StudySessionModel();
      model.readSrFile(pathToQuesAndAns);
      model.sortQuestions(rand);
      viewer.displayStatement("How many questions would you like to practice today?");
      amountOfQuestions = Integer.parseInt(input.nextLine());
      model.generateQuesAndAns(amountOfQuestions);
      selectOption(model.getQuestionsToStudy(), viewer, model, input);
      DisplayStats endOfGame = new DisplayStats(model.getUserProgress(), out);
      endOfGame.display();
      input.close();
    }
  }

  /**
   * helper for drive, takes in the user input after they see a question
   * and calls appropiate
   * display and model class methods to either update the file or display
   *
   * @param questionsToStudy represents all the questions the user will see in the study session
   * @param viewer represents content being displayed to the user
   * @param model represents a StudySessionModel object which
   *              calls the updateFile method if necessary
   * @param input represents the Readable that reads input
   * @throws IOException if an error occurs
   */
  private void selectOption(ArrayList<Question> questionsToStudy, DisplayQuesAndAns viewer,
                            StudySessionModel model, Scanner input)
      throws IOException {
    for (Question q : questionsToStudy) {
      viewer.displayStatement(q.getQuestion());
      viewer.displayOptions();
      chosenOption = Integer.parseInt(input.nextLine());
      if (chosenOption < 1 || chosenOption > 4) {
        throw new IllegalArgumentException("You must type in a number between 1-4");
      } else {
        if (chosenOption == 1) {
          model.update(Level.HARD, q, pathToQuesAndAns);
        } else if (chosenOption == 2) {
          model.update(Level.EASY, q, pathToQuesAndAns);
        } else if (chosenOption == 3) {
          viewer.displayStatement(q.getAnswer());
          chosenOption = Integer.parseInt(input.nextLine());
          if (chosenOption == 1) {
            model.update(Level.HARD, q, pathToQuesAndAns);
          } else if (chosenOption == 2) {
            model.update(Level.EASY, q, pathToQuesAndAns);
          } else if (chosenOption == 4) {
            break;
          }
        } else {
          break;
        }
      }
    }
  }


  /**
   * helper for drive, determines if a given file is valid meaning
   * it is of type sr and has questions in it
   * if not illegalArgumentExceptions are thrown
   *
   * @param file represents a given path to a file
   *
   * @return a boolean that determines if a given file is a valid sr file
   *
   * @throws IOException if an error occurs
   */
  private boolean isValidSrFile(Path file) throws IOException {
    if (!file.toString().contains(".sr")) {
      throw new IllegalArgumentException(
          "The path you provided does not lead to a file type of .sr");
    }
    Scanner input = new Scanner(file);
    if (!input.hasNext()) {
      throw new IllegalArgumentException("The path you provided leads to an empty file");
    }
    String line = input.nextLine();
    if (!line.contains("?") && !line.contains(":::")) {
      throw new IllegalArgumentException(
          "The path you provided leads to a file with no questions.");
    }
    return true;
  }
}
