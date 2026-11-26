public class FeeService {
    public String validateAndPay(String regNo, String amountText) {
        if (regNo == null || regNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Registration number is required");
        }

        final double amount;
        try {
            amount = Double.parseDouble(amountText.trim());
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Enter a valid amount");
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        // Replace this demonstration result with a JDBC PreparedStatement call.
        // Keep the database operation outside the GUI class.
        return "Payment request accepted for " + regNo.trim()
                + " : Rs. " + String.format("%.2f", amount);
    }
}
