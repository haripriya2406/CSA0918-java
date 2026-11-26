import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EventRegistrationApp extends JFrame {
    private final JTextField regNo = new JTextField(12);
    private final JTextField studentName = new JTextField(14);
    private final JTextField email = new JTextField(16);
    private final JComboBox<String> event = new JComboBox<>(
            new String[]{"Coding Contest", "Cultural Fest", "Technical Symposium"});
    private final JComboBox<String> filter = new JComboBox<>(
            new String[]{"All Events", "Coding Contest", "Cultural Fest", "Technical Symposium"});
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"Register No", "Student Name", "Email", "Event"}, 0);
    private final JTable table = new JTable(model);
    private final List<String[]> records = new ArrayList<>();

    public EventRegistrationApp() {
        super("Campus Event Registration");
        setLayout(new BorderLayout(8, 8));

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exportItem = new JMenuItem("Export CSV");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exportItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        JPanel form = new JPanel(new GridLayout(4, 2, 6, 6));
        form.setBorder(BorderFactory.createTitledBorder("Student Registration"));
        form.add(new JLabel("Register No:")); form.add(regNo);
        form.add(new JLabel("Student Name:")); form.add(studentName);
        form.add(new JLabel("Email:")); form.add(email);
        form.add(new JLabel("Event:")); form.add(event);
        JButton register = new JButton("Register");
        form.add(new JLabel()); form.add(register);

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controls.add(new JLabel("Filter event:"));
        controls.add(filter);
        JButton filterButton = new JButton("Filter");
        JButton exportButton = new JButton("Export CSV");
        controls.add(filterButton);
        controls.add(exportButton);

        JPanel top = new JPanel(new BorderLayout());
        top.add(form, BorderLayout.NORTH);
        top.add(controls, BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        register.addActionListener(e -> registerStudent(register));
        filterButton.addActionListener(e -> filterRows(filterButton));
        exportButton.addActionListener(e -> exportCsv());
        exportItem.addActionListener(e -> exportCsv());
        exitItem.addActionListener(e -> dispose());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 520);
        setLocationRelativeTo(null);
    }

    private void registerStudent(JButton button) {
        String id = regNo.getText().trim();
        String name = studentName.getText().trim();
        String mail = email.getText().trim();
        String selectedEvent = (String) event.getSelectedItem();

        if (id.isEmpty() || name.isEmpty() || mail.isEmpty()
                || !mail.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Enter all fields and a valid email.",
                    "Invalid input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        button.setEnabled(false);
        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            @Override protected Boolean doInBackground() {
                // Demonstration repository. Replace with JDBC DAO call.
                for (String[] row : records) {
                    if (row[0].equalsIgnoreCase(id) && row[3].equals(selectedEvent)) {
                        return false;
                    }
                }
                records.add(new String[]{id, name, mail, selectedEvent});
                return true;
            }
            @Override protected void done() {
                button.setEnabled(true);
                try {
                    if (get()) {
                        JOptionPane.showMessageDialog(EventRegistrationApp.this,
                                "Registration successful.");
                        regNo.setText(""); studentName.setText(""); email.setText("");
                        filterRows(null);
                    } else {
                        JOptionPane.showMessageDialog(EventRegistrationApp.this,
                                "This student is already registered for this event.",
                                "Duplicate", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(EventRegistrationApp.this,
                            ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }

    private void filterRows(JButton ignored) {
        String selected = (String) filter.getSelectedItem();
        SwingWorker<List<String[]>, Void> worker = new SwingWorker<List<String[]>, Void>() {
            @Override protected List<String[]> doInBackground() {
                List<String[]> found = new ArrayList<>();
                for (String[] row : records) {
                    if ("All Events".equals(selected) || row[3].equals(selected)) {
                        found.add(row);
                    }
                }
                return found;
            }
            @Override protected void done() {
                try {
                    model.setRowCount(0);
                    for (String[] row : get()) model.addRow(row);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(EventRegistrationApp.this, ex.getMessage());
                }
            }
        };
        worker.execute();
    }

    private void exportCsv() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("registrations.csv"));
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
        try (FileWriter writer = new FileWriter(chooser.getSelectedFile())) {
            writer.write("Register No,Student Name,Email,Event\n");
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    if (j > 0) writer.write(",");
                    writer.write("\"" + String.valueOf(model.getValueAt(i, j)).replace("\"", "\"\"") + "\"");
                }
                writer.write("\n");
            }
            JOptionPane.showMessageDialog(this, "CSV exported successfully.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Export failed: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventRegistrationApp().setVisible(true));
    }
}
