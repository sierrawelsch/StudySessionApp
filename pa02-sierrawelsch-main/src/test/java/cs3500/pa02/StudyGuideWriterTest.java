package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for writing a file
 */
class StudyGuideWriterTest {

  Path destinationToMd;
  Path destinationToSr;
  StringBuilder notesContent;
  StringBuilder questionContent;
  StudyGuideWriter mdWriter;
  StudyGuideWriter srWriter;
  String studyGuideContent;

  /**
   * Initializes the path to where the study guide should be written, the StringBuilder that
   * contains the content that should be in the study guide, and the contents actually in
   * the file to test writing a file
   */
  @BeforeEach
  public void setup() {
    destinationToMd =
        Path.of("./StudyGuide/studyguide.md");
    notesContent = new StringBuilder("# Java Arrays\n"
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

    mdWriter = new StudyGuideWriter(notesContent, destinationToMd);
    try {
      studyGuideContent = Files.readString(destinationToMd);
    } catch (IOException e) {
      fail();
    }

    destinationToSr = Path.of("./StudyGuide/studyguide.sr");
    questionContent = new StringBuilder(
        "Why did the chicken cross the road? ::: To get to the other side! <HARD>\n"
            +
            "Which country is known as the Land of a Thousand Lakes? ::: Finland. <HARD>\n"
            +
            "Which country is famous for its fjords? ::: Norway. <HARD>\n"
            +
            "What is the capital of Mexico? ::: The capital is Mexico City. <HARD>\n"
            +
            "What is the largest lake in Africa?::: The largest lake is Lake Victoria. <HARD>\n"
            +
            "What is the tallest mountain in North America?:::"
            + "The tallest mountain is Denali (also known as Mount McKinley). <HARD>\n"
            +
            "Which continent is known as the \"Roof of the World\"?:::Asia. <HARD>\n"
            +
            "What is the largest desert in Asia? ::: "
            + "The largest desert is the Gobi Desert. <HARD>\n"
            +
            "Which country is the smallest in terms of land area?::: "
            + "The smallest country is Vatican City. <HARD>\n"
            +
            "What is the official language of Brazil? :::"
            + "The official language is Portuguese. <HARD>\n"
            +
            "What is the official language of Japan?:::The official language is Japanese. <HARD>\n"
            +
            "Which country is the driest inhabited continent "
            + "on Earth?:::Australia. <HARD>\n"
            +
            "What is the largest coral reef system in the world? "
            + "::: The largest coral reef system is the Great Barrier Reef in Australia. <HARD>\n"
            +
            "What is the capital of Argentina?:::The capital is Buenos Aires. <HARD>\n"
            +
            "What is the capital of Canada?:::The capital is Ottawa. <HARD>\n"
            +
            "Which country is known as the Land of the Rising Sun?:::Japan. <HARD>\n"
            +
            "What is the largest river in Africa? ::: The largest river is the Nile River. <HARD>\n"
            +
            "Which country is famous for its tulips and windmills?:::The Netherlands. <HARD>\n");
    srWriter = new StudyGuideWriter(questionContent, destinationToSr);
  }


  /**
   * Tests that the study guide file and the
   * sr file are properly written and contain the correct content
   */
  @Test
  void writeFile() {
    try {
      mdWriter.writeFile();
    } catch (IOException e) {
      fail();
    }
    try {
      assertEquals(Files.readString(destinationToMd), notesContent.toString());
    } catch (IOException e) {
      fail();
    }
    try {
      srWriter.writeFile();
    } catch (IOException e) {
      fail();
    }
    try {
      assertEquals(Files.readString(destinationToSr), questionContent.toString());
    } catch (IOException e) {
      fail();
    }
  }
}