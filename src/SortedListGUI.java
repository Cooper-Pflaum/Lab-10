import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SortedListGUI extends JFrame implements ActionListener {
  private SortedList sortedList;
  private JTextField addTextField;
  private JTextField searchTextField;
  private JTextArea outputArea;
  private JButton addButton;
  private JButton searchButton;

  public SortedListGUI() {
    super("Sorted List GUI");

    sortedList = new SortedList();

    setLayout(new BorderLayout());

    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new FlowLayout());

    addTextField = new JTextField(15);
    addButton = new JButton("Add String");
    addButton.addActionListener(this);

    searchTextField = new JTextField(15);
    searchButton = new JButton("Search String");
    searchButton.addActionListener(this);

    inputPanel.add(new JLabel("Add String:"));
    inputPanel.add(addTextField);
    inputPanel.add(addButton);

    inputPanel.add(new JLabel("Search String:"));
    inputPanel.add(searchTextField);
    inputPanel.add(searchButton);

    add(inputPanel, BorderLayout.NORTH);

    outputArea = new JTextArea(15, 30);
    outputArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(outputArea);
    add(scrollPane, BorderLayout.CENTER);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 400);
    setLocationRelativeTo(null);
    setVisible(true);

    updateOutputArea("Sorted List is empty.");
  }

  private void updateOutputArea(String message) {
    outputArea.append(message + "\n");
    outputArea.append("Current List: " + sortedList.toString() + "\n\n");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == addButton) {
      String textToAdd = addTextField.getText().trim();
      if (!textToAdd.isEmpty()) {
        sortedList.add(textToAdd);
        updateOutputArea("Added: \"" + textToAdd + "\"");
        addTextField.setText("");
      } else {
        updateOutputArea("Cannot add empty string.");
      }
    } else if (e.getSource() == searchButton) {
      String textToSearch = searchTextField.getText().trim();
      if (!textToSearch.isEmpty()) {
        int result = sortedList.search(textToSearch);
        if (result >= 0) {
          updateOutputArea("Search for \"" + textToSearch + "\": Found at index " + result + " (\"" + sortedList.get(result) + "\").");
        } else {
          int insertionPoint = sortedList.getInsertionPoint(result);
          updateOutputArea("Search for \"" + textToSearch + "\": Not found. Would be inserted at index " + insertionPoint + ".");
        }
        searchTextField.setText("");
      } else {
        updateOutputArea("Cannot search for empty string.");
      }
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new SortedListGUI();
      }
    });
  }
}
