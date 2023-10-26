package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the process of reading a file
 */
class FileReaderTest {
  String arraysAbsolutePath;
  File arraysMd;
  BasicFileAttributes arraysAttrs;
  String vectorAbsolutePath;
  File vectorMd;
  BasicFileAttributes vectorAttrs;


  MarkdownFile md1;
  MarkdownFile md2;
  MarkdownFile md3;

  File soblankMd;
  String soblankAbsolutePath;

  BasicFileAttributes soblankAttrs;

  ArrayList<MarkdownFile> sortedByFileName;

  FileReader reader;

  /**
   * Initializes the markdown files, their attributes, and a createFile object
   * to test reading the file
   */
  @BeforeEach
  public void setup() {
    arraysAbsolutePath =
        "./Notes/compsci/arrays.md";
    arraysMd = new File(arraysAbsolutePath);
    try {
      arraysAttrs = Files.readAttributes(
          Path.of(arraysAbsolutePath), BasicFileAttributes.class);
    } catch (IOException e) {
      fail();
    }
    vectorAbsolutePath =
        "./Notes/vector.md";
    vectorMd = new File(vectorAbsolutePath);
    try {
      vectorAttrs = Files.readAttributes(
          Path.of(vectorAbsolutePath), BasicFileAttributes.class);
    } catch (IOException e) {
      fail();
    }

    soblankAbsolutePath =
        "./Notes/compsci/s/soblank.md";
    soblankMd = new File(soblankAbsolutePath);
    try {
      soblankAttrs = Files.readAttributes(
          Path.of(soblankAbsolutePath), BasicFileAttributes.class);
    } catch (IOException e) {
      fail();
    }

    md1 = new MarkdownFile(arraysMd, arraysAttrs);
    md2 = new MarkdownFile(vectorMd, vectorAttrs);
    md3 = new MarkdownFile(soblankMd, soblankAttrs);

    sortedByFileName = new ArrayList<MarkdownFile>(Arrays.asList(md1, md3, md2));
    reader = new FileReader(sortedByFileName);
  }

  /**
   * Tests the createFile method to make sure the StringBuilder has all the correct content needed
   * for the study guide and the sr file
   */
  @Test
  void readFileTest() {
    setup();
    try {
      reader.createFile();
    } catch (FileNotFoundException e) {
      fail();
    }
    assertEquals(reader.getNotesContent().toString(),
        "# Java Arrays\n"
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
            + "### Important Questions\n"
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
    assertEquals(reader.getQuestionContent().toString(),
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
            +
            "The tallest mountain is Denali (also known as Mount McKinley). <HARD>\n"
            +
            "Which continent is known as the \"Roof of the World\"?:::Asia. <HARD>\n"
            +
            "What is the largest desert in Asia? ::: "
            +
            "The largest desert is the Gobi Desert. <HARD>\n"
            +
            "Which country is the smallest in terms of land area?::: "
            +
            "The smallest country is Vatican City. <HARD>\n"
            +
            "What is the official language of Brazil? :::"
            +
            "The official language is Portuguese. <HARD>\n"
            +
            "What is the official language of Japan?:::"
            +
            "The official language is Japanese. <HARD>\n"
            +
            "Which country is the driest inhabited continent on Earth?:::Australia. <HARD>\n"
            +
            "What is the largest coral reef system in the world? ::: "
            +
            "The largest coral reef system is the Great Barrier Reef in Australia. <HARD>\n"
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
  }
}