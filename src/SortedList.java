import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class SortedList {
  private List<String> data;

  public SortedList() {
    data = new ArrayList<>();
  }

  private int binarySearch(String element) {
    int low = 0;
    int high = data.size() - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      String midVal = data.get(mid);
      int cmp = element.compareTo(midVal);

      if (cmp > 0) {
        low = mid + 1;
      } else if (cmp < 0) {
        high = mid - 1;
      } else {
        return mid;
      }
    }
    return -(low) - 1;
  }

  public void add(String element) {
    if (element == null) {
      throw new IllegalArgumentException("Cannot add null elements");
    }
    int index = binarySearch(element);

    int insertionPoint = (index < 0) ? -(index + 1) : index;

    if (index >= 0) {
      insertionPoint = index;
    } else {
      insertionPoint = -(index + 1);
    }

    data.add(insertionPoint, element);
  }

  public int search(String element) {
    if (element == null) {
      return -1;
    }
    return binarySearch(element);
  }

  public String get(int index) {
    if (index < 0 || index >= data.size()) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + data.size());
    }
    return data.get(index);
  }

  public int size() {
    return data.size();
  }

  @Override
  public String toString() {
    return data.toString();
  }

  public int getInsertionPoint(int searchResult) {
    if (searchResult >= 0) {
      return searchResult;
    } else {
      return -(searchResult + 1);
    }
  }
}