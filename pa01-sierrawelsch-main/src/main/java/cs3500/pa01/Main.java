package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Represents the class that takes in the command line arguments
 */
public class Main {
  /**
   * Converts all the command line arguments from Strings to their actual
   * types and passes them into a Driver object
   *
   * @param args the arguments the user passed in to the command line
   */
  public static void main(String[] args) throws IOException {
    Path pathToNotesRoot = Path.of(args[0]);
    OrderingFlag order = OrderingFlag.valueOf(args[1].toUpperCase());
    Path destination = Path.of(args[2]);
    Driver mdDriver = new Driver(pathToNotesRoot, order, destination);
    mdDriver.drive();
  }
}
