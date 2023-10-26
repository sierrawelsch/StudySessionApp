package cs3500.pa02;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Random;

/**
 * Represents the class that takes in the command line arguments
 */
public class Driver {
  /**
   * Converts all the command line arguments from Strings to their actual
   * types and passes them into a Driver object
   *
   * @param args the arguments the user passed in to the command line
   * @throws IOException if an error occurs
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      Readable in = new InputStreamReader(System.in);
      Appendable out = new PrintStream(System.out);
      StudySessionDriver ssDriver = new StudySessionDriver(in, out, new Random());
      ssDriver.drive();
    } else {
      Path pathToNotesRoot = Path.of(args[0]);
      OrderingFlag order = OrderingFlag.valueOf(args[1].toUpperCase());
      Path destination = Path.of(args[2]);
      FileDriver mdDriver = new FileDriver(pathToNotesRoot, order, destination);
      mdDriver.drive();
    }
  }
}
