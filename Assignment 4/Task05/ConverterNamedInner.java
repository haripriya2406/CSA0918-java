import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class ConverterNamedInner extends JFrame {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target = new JComboBox<>(new String[]{"USD", "EUR"});

    public ConverterNamedInner() {
        super("Converter - Named Inner Class");
        setLayout(new FlowLayout());
        JButton button = new JButton("Convert");
        add(new JLabel("INR")); add(amount); add(target); add(button);
        ConvertHandler handler = new ConvertHandler();
        button.addActionListener(handler);
        amount.addActionListener(handler);
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    private class ConvertHandler implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
            try {
                double value = Double.parseDouble(amount.getText().trim());
                if (!Double.isFinite(value) || value < 0) throw new NumberFormatException();
                String code = (String) target.getSelectedItem();
                double rate = code.equals("USD") ? 0.012 : 0.011;
                JOptionPane.showMessageDialog(ConverterNamedInner.this,
                        String.format("%.2f %s", value * rate, code));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ConverterNamedInner.this, "Invalid amount",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConverterNamedInner().setVisible(true));
    }
}
