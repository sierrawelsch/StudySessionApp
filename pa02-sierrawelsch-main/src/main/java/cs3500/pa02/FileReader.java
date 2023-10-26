package cs3500.pa02;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the process of reading a file
 */
public class FileReader {
  private StringBuilder notesContent;
  private StringBuilder questionContent;
  private ArrayList<MarkdownFile> mdFiles;

  /**
   * Instantiates the process of reading a file and initialing the variables that will be utilized
   *
   * @param mdFiles represents a list of markdown files in the directory sorted by the ordering flag
   */
  FileReader(ArrayList<MarkdownFile> mdFiles) {
    this.mdFiles = mdFiles;
    this.notesContent = new StringBuilder();
    this.questionContent = new StringBuilder();
  }

  /**
   * for each file in the list of markdown files, it appends the question/answer content
   * and the content for the study guide into 2 separate StringBuilders
   *
   * @throws FileNotFoundException if the files it is scanning are not found
   */
  public void createFile() throws FileNotFoundException {
    String allContent;
    int startBrackets;
    int endBrackets;
    for (MarkdownFile f : mdFiles) {
      allContent = readEntireFile(f);
      int currentPoint = 0;
      while (currentPoint < allContent.length() - 1) {
        if (allContent.substring(currentPoint, currentPoint + 1).equals("#")) {
          if (currentPoint != 0 || (currentPoint == 0 && !(f.equals(mdFiles.get(0))))) {
            notesContent.append("\n");
            notesContent.append("\n");
          }
          while (currentPoint < allContent.length() - 1
              && !allContent.substring(currentPoint, currentPoint + 2).equals("]]")) {
            notesContent.append(allContent.substring(currentPoint, currentPoint + 1));
            currentPoint++;
          }
        } else if (allContent.substring(currentPoint, currentPoint + 2).equals("[[")) {
          startBrackets = currentPoint + 2;
          endBrackets = allContent.substring(startBrackets, allContent.length()).indexOf("]]")
             + startBrackets;
          if (allContent.substring(startBrackets, endBrackets).contains(":::")) {
            readQuesAndAns(allContent.substring(startBrackets, endBrackets));
            currentPoint++;
          } else {
            currentPoint += 2;
            notesContent.append("\n");
            notesContent.append("- ");
            while (!allContent.substring(currentPoint, currentPoint + 2).equals("]]")) {
              notesContent.append(allContent.substring(currentPoint, currentPoint + 1));
              currentPoint++;
            }
          }
        } else {
          currentPoint++;
        }
      }
    }
  }

  /**
   * scans the given file and returns a String which contains all the contents of the file
   *
   * @param f represents a markdown file
   * @return a String that represents all the content in a file
   * @throws FileNotFoundException if the markdown file is not found
   */
  private String readEntireFile(MarkdownFile f) throws FileNotFoundException {
    String allContent = "";
    Scanner input = new Scanner(f.getFile());
    while (input.hasNextLine()) {
      String line = input.nextLine();
      if (line.startsWith("#")) {
        allContent += line + "]]";
      } else {
        allContent += line;
      }
    }
    return allContent;
  }

  /**
   * appends a given phrase to a StringBuilder which represents question and answer
   * and the default level of difficulty (HARD)
   *
   * @param phrase represents a String being appended to a StringBuilder
   */
  private void readQuesAndAns(String phrase) {
    questionContent.append(phrase + " <HARD>");
    questionContent.append("\n");
  }

  /**
   * gets the StringBuilder that contains all the questions and answers in the files
   *
   * @return a StringBuilder that contains all the questions and answers in the files
   */
  public StringBuilder getQuestionContent() {
    return questionContent;
  }

  /**
   * gets the StringBuilder that contains all the content needed for the study guide in the files
   *
   * @return a StringBuilder that contains all the content needed for the study guide in the files
   */
  public StringBuilder getNotesContent() {
    return notesContent;
  }
}