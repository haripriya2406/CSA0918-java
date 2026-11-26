import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LibrarySearchScreen extends JFrame {
    private final JTextField searchText = new JTextField(22);
    private final JComboBox<String> category =
            new JComboBox<>(new String[]{"All", "Book", "Journal", "Thesis"});
    private final JLabel status = new JLabel("Ready");

    public LibrarySearchScreen() {
        super("Campus Library Search");
        setLayout(new BorderLayout(8, 8));

        JLabel title = new JLabel("LIBRARY SEARCH", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchText);
        searchPanel.add(category);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchButton);

        String[] columns = {"Book ID", "Title", "Category", "Availability"};
        Object[][] rows = {
            {"B101", "Java Programming", "Book", "Available"},
            {"J202", "Computer Science Review", "Journal", "Issued"},
            {"T303", "AI in Education", "Thesis", "Available"}
        };
        JTable table = new JTable(new DefaultTableModel(rows, columns));
        JScrollPane results = new JScrollPane(table);

        JPanel footer = new JPanel(new BorderLayout());
        JPanel buttons = new JPanel(new FlowLayout());
        JButton prev = new JButton("Prev");
        JButton next = new JButton("Next");
        JButton reserve = new JButton("Reserve");
        buttons.add(prev);
        buttons.add(next);
        buttons.add(reserve);
        footer.add(buttons, BorderLayout.CENTER);
        footer.add(status, BorderLayout.SOUTH);

        JPanel center = new JPanel(new BorderLayout());
        center.add(searchPanel, BorderLayout.NORTH);
        center.add(results, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> status.setText(
                "Searching for: " + searchText.getText().trim()
                + " | Category: " + category.getSelectedItem()));
        prev.addActionListener(e -> status.setText("Previous page selected"));
        next.addActionListener(e -> status.setText("Next page selected"));
        reserve.addActionListener(e -> status.setText("Select an available book to reserve"));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 320));
        setSize(760, 480);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibrarySearchScreen().setVisible(true));
    }
}
