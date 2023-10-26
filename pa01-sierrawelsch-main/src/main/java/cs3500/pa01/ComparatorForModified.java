package cs3500.pa01;

import java.util.Comparator;

/**
 * Represents a Comparator for comparing the times markdownFiles were modified
 */
public class ComparatorForModified implements Comparator<MarkdownFile> {
  /**
   * @param md1 A MarkdownFile
   * @param md2 Another MarkdownFile
   * @return an integer that represents if m1 modified time is greater, less than,
   *     or equal to m2 modified time
   */
  public int compare(MarkdownFile md1, MarkdownFile md2) {
    if (md1.getModified() - md2.getModified() < 0) {
      return -1;
    } else if (md1.getModified() - md2.getModified() == 0) {
      return 0;
    } else {
      return 1;
    }
  }
}
