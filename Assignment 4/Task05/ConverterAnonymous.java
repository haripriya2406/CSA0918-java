import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class ConverterAnonymous extends JFrame {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target = new JComboBox<>(new String[]{"USD", "EUR"});

    public ConverterAnonymous() {
        super("Converter - Anonymous Class");
        setLayout(new FlowLayout());
        JButton button = new JButton("Convert");
        add(new JLabel("INR")); add(amount); add(target); add(button);

        ActionListener handler = new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                try {
                    double value = Double.parseDouble(amount.getText().trim());
                    if (!Double.isFinite(value) || value < 0) throw new NumberFormatException();
                    String code = (String) target.getSelectedItem();
                    double rate = code.equals("USD") ? 0.012 : 0.011;
                    JOptionPane.showMessageDialog(ConverterAnonymous.this,
                            String.format("%.2f %s", value * rate, code));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ConverterAnonymous.this, "Invalid amount",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        button.addActionListener(handler);
        amount.addActionListener(handler);
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConverterAnonymous().setVisible(true));
    }
}
