package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for obtaining the command line arguments
 * and restoring them to their proper types
 */
class MainTest {

  Main main;


  /**
   * Initializes a main object for testing the main method
   */
  @BeforeEach
  public void setup() {
    main = new Main();
  }

  /**
   * Tests that the command line arguments contain valid Paths and a valid orderingFlag
   * and if not an exception is thrown
   */
  @Test
  void main() {
    setup();
    String[] arguments = {"./Notes", "FILENAME",
        "./StudyGuide/studyguide.md" };
    assertDoesNotThrow(() -> main.main(arguments));

    String[] argumentsWithNotEnoughElementsException =
          {"./Notes", "FILENAME" };
    ArrayIndexOutOfBoundsException exception = assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> main.main(argumentsWithNotEnoughElementsException));
    assertEquals("Index 2 out of bounds for length 2", exception.getMessage());

    String[] argumentsWithNotOrderingFlagException =
          {"./Notes", "kind",
              "./StudyGuide/studyguide.md" };
    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
        () -> main.main(argumentsWithNotOrderingFlagException));
    assertEquals("No enum constant cs3500.pa01.OrderingFlag.KIND", exception2.getMessage());
  }
}