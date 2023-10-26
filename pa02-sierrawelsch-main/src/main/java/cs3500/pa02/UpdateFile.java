package cs3500.pa02;


import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * represents the process of rewriting a file/changing the contents of a file
 */
public class UpdateFile implements MyFileWriter {
  private Path destination;
  private Question question;
  private Scanner srScanner;
  private Level newLevel;

  private StringBuilder content;

  /**
   * initializes the process of rewriting a file/changing the contents of a file
   *
   * @param destination represents the Path to a file
   *
   * @param question represents a question in a file
   *
   * @param newLevel represents a potential new level the user labelled a question
   */
  UpdateFile(Path destination, Question question, Level newLevel) {
    this.destination = destination;
    this.question = question;
    this.newLevel = newLevel;
    this.content = new StringBuilder();
  }

  /**
   * writes the potential new StringBuilder to a file
   *
   * @throws IOException if an error occurs (the file does not exist)
   */
  @Override
  public void writeFile() throws IOException {
    checkFile();
    StudyGuideWriter sgw = new StudyGuideWriter(content, destination);
    sgw.writeFile();
  }

  /**
   * checks if a question level matches the associated question level in a file,
   * if not it appends a new line with the new level to the StringBuilder to eventually
   * write to a file
   *
   * @throws IOException if an error occurs (the file does not exist)
   */
  private void checkFile() throws IOException {
    this.srScanner = new Scanner(destination);
    String line;
    String replacedLine;
    while (srScanner.hasNextLine()) {
      line = srScanner.nextLine();
      if (line.substring(0, line.indexOf(":::")).equals(question.getQuestion())) {
        replacedLine =
            question.getQuestion() + ":::" + question.getAnswer() + "<" + newLevel + ">";
        content.append(replacedLine);
      } else {
        content.append(line);
      }
      content.append("\n");
    }
  }
}


