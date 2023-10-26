package cs3500.pa01;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the process of reading a file
 */
public class FileReader {
  private StringBuilder content;
  private ArrayList<MarkdownFile> mdFiles;

  /**
   * Instantiates the process of reading a file and initialing the variables that will be utilized
   *
   * @param mdFiles represents a list of markdown files in the directory sorted by the ordering flag
   */
  FileReader(ArrayList<MarkdownFile> mdFiles) {
    this.mdFiles = mdFiles;
    this.content = new StringBuilder();
  }

  /**
   * for each file in the list of markdown files, it puts the contents of each file into a String
   * and appends the content needed for the study guide into a StringBuilder
   *
   * @return a StringBuilder with the desired content for the study guide
   */
  public StringBuilder readFile() throws FileNotFoundException {
    String allContent;
    for (MarkdownFile f : mdFiles) {
      allContent = "";
      Scanner input = new Scanner(f.getFile());
      while (input.hasNextLine()) {
        String line = input.nextLine();
        allContent += line;
      }
      int currentPoint = 0;
      while (currentPoint < allContent.length() - 1) {
        if (allContent.substring(currentPoint, currentPoint + 1).equals("#")) {
          if (currentPoint != 0 || (currentPoint == 0 && !(f.equals(mdFiles.get(0))))) {
            content.append("\n");
            content.append("\n");
          }
          while (!allContent.substring(currentPoint, currentPoint + 1).equals("-")) {
            content.append(allContent.substring(currentPoint, currentPoint + 1));
            currentPoint++;
          }
        } else if (allContent.substring(currentPoint, currentPoint + 2).equals("[[")) {
          currentPoint += 2;
          content.append("\n");
          content.append("- ");
          while (!allContent.substring(currentPoint, currentPoint + 2).equals("]]")) {
            content.append(allContent.substring(currentPoint, currentPoint + 1));
            currentPoint++;
          }
        } else {
          currentPoint++;
        }
      }
    }
    return content;
  }
}