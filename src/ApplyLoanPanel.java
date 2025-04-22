import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyLoanPanel extends JPanel implements ActionListener {

    private JTextField loanAmountField;
    private JTextField tenureField;
    private JTextArea reasonArea;
    private JPasswordField passwordField;
    private JButton applyButton, backButton;
    private User user;
    private JPanel internalPanel;
    private CardLayout internalCardLayout;

    public ApplyLoanPanel(User user, JPanel internalPanel, CardLayout internalCardLayout) {
        this.user = user;
        this.internalPanel = internalPanel;
        this.internalCardLayout = internalCardLayout;
        setLayout(new BorderLayout(20, 20));
        setOpaque(false);

        JLabel title = new JLabel("Apply for a Loan", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 34));
        title.setForeground(new Color(255, 204, 153));
        add(title, BorderLayout.NORTH);

        Color fieldColor = new Color(255, 255, 255, 200);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        // Loan Amount
        loanAmountField = new JTextField(10);
        loanAmountField.setFont(new Font("Verdana", Font.PLAIN, 14));
        loanAmountField.setBackground(fieldColor);
        formPanel.add(createAlignedRow("Loan Amount:", loanAmountField));
        formPanel.add(Box.createVerticalStrut(15));

        // Loan Tenure
        tenureField = new JTextField(10);
        tenureField.setFont(new Font("Verdana", Font.PLAIN, 14));
        tenureField.setBackground(fieldColor);
        formPanel.add(createAlignedRow("Tenure (months):", tenureField));
        formPanel.add(Box.createVerticalStrut(15));

        // Reason
        reasonArea = new JTextArea(3, 10);
        reasonArea.setLineWrap(true);
        reasonArea.setWrapStyleWord(true);
        reasonArea.setFont(new Font("Verdana", Font.PLAIN, 14));
        reasonArea.setBackground(fieldColor);
        JScrollPane scrollPane = new JScrollPane(reasonArea);
        scrollPane.setPreferredSize(new Dimension(160, 60));
        formPanel.add(createAlignedRow("Loan Reason:", scrollPane));
        formPanel.add(Box.createVerticalStrut(15));

        // Password
        passwordField = new JPasswordField(10);
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 14));
        passwordField.setBackground(fieldColor);
        formPanel.add(createAlignedRow("Password:", passwordField));
        formPanel.add(Box.createVerticalStrut(20));

        // Apply Button
        applyButton = new JButton("Apply");
        styleButton(applyButton, new Color(0, 153, 76), Color.WHITE);
        applyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(applyButton);

        add(formPanel, BorderLayout.CENTER);

        // Back Button
        backButton = new JButton("Back");
        styleButton(backButton, Color.RED, Color.BLACK);
        backButton.setFocusable(false);

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setOpaque(false);
        backPanel.add(backButton);
        add(backPanel, BorderLayout.SOUTH);

        applyButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    private JPanel createAlignedRow(String labelText, JComponent field) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        label.setForeground(new Color(255, 160, 122));
        label.setPreferredSize(new Dimension(140, 25));

        field.setPreferredSize(new Dimension(160, 28));
        row.add(label, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);

        return row;
    }

    private void styleButton(JButton button, Color bg, Color fg) {
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFont(new Font("Tahoma", Font.BOLD, 15));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            internalCardLayout.show(internalPanel, "DefaultHome");
        }

        if (e.getSource() == applyButton) {
            String password = new String(passwordField.getPassword()).trim();
            if (password.equals(user.getBankAccount().getPassword())) {
                try {
                    double loanAmount = Double.parseDouble(loanAmountField.getText().trim());
                    int tenureMonths = Integer.parseInt(tenureField.getText().trim());
                    String reason = reasonArea.getText().trim();

                    if (loanAmount <= 0 || tenureMonths <= 0 || reason.isEmpty()) {
                        throw new IllegalArgumentException();
                    }

                    // Apply for loan
                    user.getBankAccount().applyLoan(loanAmount,tenureMonths, reason); // You can extend this method to take tenure if needed
                    JOptionPane.showMessageDialog(this, "Loan application submitted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    internalCardLayout.show(internalPanel, "DefaultHome");

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid amount, tenure and reason.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void refreshUser(User user) {
        this.user = user;
        loanAmountField.setText("");
        tenureField.setText("");
        reasonArea.setText("");
        passwordField.setText("");
    }
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            refreshUser(this.user);
        }
    }


}