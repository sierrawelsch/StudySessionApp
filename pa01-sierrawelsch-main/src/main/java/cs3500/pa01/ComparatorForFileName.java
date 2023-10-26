package cs3500.pa01;

import java.util.Comparator;

/**
 * Represents a Comparator for comparing the file names of markdownFiles
 */
public class ComparatorForFileName implements Comparator<MarkdownFile> {
  /**
   * @param md1 A MarkdownFile
   * @param md2 Another MarkdownFile
   * @return an integer that represents if m1 file name is greater, less than,
   *     or equal to m2 created time
   */
  @Override
  public int compare(MarkdownFile md1, MarkdownFile md2) {
    return md1.getFileName().compareTo(md2.getFileName());
  }
}
