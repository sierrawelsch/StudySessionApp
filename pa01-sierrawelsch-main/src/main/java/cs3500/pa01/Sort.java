package cs3500.pa01;

import java.util.ArrayList;

/**
 * represents the process of sorting a list based off the orderingFlag
 */
public class Sort {
  private OrderingFlag order;
  private ArrayList<MarkdownFile> mdFiles;


  /**
   * Instantiates the process of sorting a list based off the orderingFlag
   *
   * @param order   represents the inputted orderingFlag that determines what a list of
   *                markdown files should be sorted by
   * @param mdFiles represents a list of unsorted markdown files
   */
  Sort(OrderingFlag order, ArrayList<MarkdownFile> mdFiles) {
    this.order = order;
    this.mdFiles = mdFiles;
  }

  /**
   * determines what the inputted orderingFlag is and sorts the list based on that
   *
   * @return the sorted markdown files sorted based on the inputted orderingFlag
   */
  public ArrayList<MarkdownFile> sortByComparator() {
    if (order.equals(OrderingFlag.FILENAME)) {
      mdFiles.sort(new ComparatorForFileName());
      return mdFiles;
    } else if (order.equals(OrderingFlag.CREATED)) {
      mdFiles.sort(new ComparatorForCreated());
      return mdFiles;
    } else {
      mdFiles.sort(new ComparatorForModified());
      return mdFiles;
    }
  }

}
