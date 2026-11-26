import java.awt.*;
import java.awt.event.*;

public class FeeWindow extends Frame {
    private final TextField regNo = new TextField(18);
    private final TextField amount = new TextField(18);
    private final Label status = new Label("Enter fee details");
    private final FeeService service = new FeeService();

    public FeeWindow() {
        super("Student Fee Payment");
        setLayout(new BorderLayout(8, 8));

        Panel form = new Panel(new GridLayout(2, 2, 6, 6));
        form.add(new Label("Registration No:"));
        form.add(regNo);
        form.add(new Label("Amount (Rs.):"));
        form.add(amount);

        Panel actions = new Panel(new FlowLayout());
        Button pay = new Button("Pay");
        Button clear = new Button("Clear");
        Button receipt = new Button("Receipt");
        actions.add(pay);
        actions.add(clear);
        actions.add(receipt);

        add(form, BorderLayout.CENTER);
        add(actions, BorderLayout.NORTH);
        add(status, BorderLayout.SOUTH);

        pay.addActionListener(e -> {
            try {
                status.setText(service.validateAndPay(regNo.getText(), amount.getText()));
            } catch (IllegalArgumentException ex) {
                status.setText(ex.getMessage());
            }
        });
        clear.addActionListener(e -> {
            regNo.setText("");
            amount.setText("");
            status.setText("Fields cleared");
        });
        receipt.addActionListener(e ->
                status.setText("Receipt is available after payment confirmation."));

        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setSize(430, 190);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new FeeWindow().setVisible(true));
    }
}
