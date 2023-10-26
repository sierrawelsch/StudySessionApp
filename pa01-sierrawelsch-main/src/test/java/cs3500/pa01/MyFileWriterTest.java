package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for writing a file
 */
class MyFileWriterTest {

  Path destination;
  StringBuilder content;
  MyFileWriter writer;
  String studyGuideContent;

  /**
   * Initializes the path to where the study guide should be written, the StringBuilder that
   * contains the content that should be in the study guide, and the contents actually in
   * the file to test writing a file
   */
  public void setup() {
    destination =
        Path.of("./StudyGuide/studyguide.md");
    content = new StringBuilder("# Java Arrays\n"
        + "- An **array** is a collection of variables of the same type\n"
        + "\n"
        + "## Declaring an Array\n"
        + "- General Form: type[] arrayName;\n"
        + "- only creates a reference\n"
        + "- no array has  actually been created yet\n"
        + "\n"
        + "## Creating an Array (Instantiation)\n"
        + "- General form: arrayName = new type[numberOfElements];\n"
        + "- numberOfElements must be a positive Integer.\n"
        + "- Gotcha: Array size is not  modifiable once instantiated.\n"
        + "\n"
        + "# Vectors\n"
        + "- Vectors act like resizable arrays\n"
        + "\n"
        + "## Declaring a vector\n"
        + "- General Form: Vector<type> v = new Vector();\n"
        + "- type needs to be a valid reference type\n"
        + "\n"
        + "## Adding an element to a vector\n"
        + "- v.add(object of type);");

    writer = new MyFileWriter(content, destination);
    try {
      studyGuideContent = Files.readString(destination);
    } catch (IOException e) {
      fail();
    }
  }


  /**
   * Tests that the study guide file is properly written and contains the correct content
   */
  @Test
  void writeFile() {
    setup();
    try {
      writer.writeFile();
    } catch (IOException e) {
      fail();
    }
    try {
      assertEquals(Files.readString(destination), content.toString());
    } catch (IOException e) {
      fail();
    }
  }
}