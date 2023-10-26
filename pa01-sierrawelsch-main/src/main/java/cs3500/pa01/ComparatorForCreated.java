package cs3500.pa01;

import java.util.Comparator;

/**
 * Represents a Comparator for comparing the times markdownFiles were created
 */
class ComparatorForCreated implements Comparator<MarkdownFile> {
  /**
   * @param md1 A MarkdownFile
   * @param md2 Another MarkdownFile
   * @return an integer that represents if m1 created time is greater, less than,
   *     or equal to m2 created time
   */
  public int compare(MarkdownFile md1, MarkdownFile md2) {
    if (md1.getCreated() - md2.getCreated() < 0) {
      return -1;
    } else if (md1.getCreated() - md2.getCreated() > 0) {
      return 1;
    } else {
      return 0;
    }
  }
}
