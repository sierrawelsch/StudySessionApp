package cs3500.pa02;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents reading a sr File and looking for questions
 */
public class ReadSrFile {
  private ArrayList<Question> questions;
  private Scanner srScanner;

  private Path srFile;


  /**
   * initializes reading a sr File, the path to the file,
   * and an empty array list of questions to be added to
   *
   * @param srFile represents the path to the file
   *
   * @throws IOException if an error occurs (the file does not exist)
   */
  ReadSrFile(Path srFile) throws IOException {
    this.srFile = srFile;
    this.questions = new ArrayList<Question>();
  }

  /**
   * reads the file an instantiates question objects and adds them to a list of questions
   *
   * @return a list of Question objects that represent all the questions listed in the file
   *
   * @throws IOException if an error occurs (the file does not exist)
   */
  public ArrayList<Question> extractQuestions() throws IOException {
    srScanner = new Scanner(srFile);
    while (srScanner.hasNextLine()) {
      String line = srScanner.nextLine();
      String question = line.substring(0, line.indexOf(":::"));
      String answer = line.substring(line.indexOf(":::") + 3, line.indexOf("<"));
      String difficulty = line.substring(line.indexOf("<") + 1, line.indexOf(">"));
      Question newQuesAndAns = new Question(question, answer, Level.valueOf(difficulty));
      questions.add(newQuesAndAns);
    }
    return questions;
  }
}

