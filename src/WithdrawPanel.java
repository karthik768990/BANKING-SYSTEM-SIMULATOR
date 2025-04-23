import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WithdrawPanel extends JPanel implements ActionListener {

    private JTextField amountField;
    private JPasswordField passwordField;
    private JComboBox<String> accountTypeCombo;
    private JButton withdrawButton;
    private JButton backButton;
    private User user;
    private JPanel internalPanel;
    private CardLayout internalCardLayout;

    public WithdrawPanel(User user, JPanel internalPanel, CardLayout internalCardLayout) {
        this.user = user;
        this.internalPanel = internalPanel;
        this.internalCardLayout = internalCardLayout;
        this.setLayout(new BorderLayout(20, 20));
        this.setOpaque(false);

        Color transparentWhite = new Color(255, 255, 255, 200);

        // 🔷 Title
        JLabel titleLabel = new JLabel("Withdraw Money", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(206, 147, 216));
        this.add(titleLabel, BorderLayout.NORTH);

        // 🧱 Center Form Panel (Vertical)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        // 💡 Input Fields
        formPanel.add(createAlignedRow("Withdraw Amount:", this.amountField = new JTextField(10)));
        this.amountField.setFont(new Font("Verdana",Font.TRUETYPE_FONT,14));
        formPanel.add(Box.createVerticalStrut(15));
        amountField.setBackground(transparentWhite);

        formPanel.add(createAlignedRow("Password:", this.passwordField = new JPasswordField(10)));
        this.passwordField.setFont(new Font("Verdana",Font.TRUETYPE_FONT,14));
        formPanel.add(Box.createVerticalStrut(15));
        passwordField.setBackground(transparentWhite);

        formPanel.add(createAlignedRow("Account Type:", this.accountTypeCombo = new JComboBox<>(new String[]{"Digital Savings", "Student"})));
        this.accountTypeCombo.setFont(new Font("Verdana",Font.TRUETYPE_FONT,14));
        formPanel.add(Box.createVerticalStrut(20));
        accountTypeCombo.setBackground(transparentWhite);

        // ➖ Withdraw Button
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        withdrawButton.setBackground(new Color(0, 153, 76));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        formPanel.add(withdrawButton);



        this.add(formPanel, BorderLayout.CENTER);

        // 🔙 Back Button at bottom left
        backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.BLACK);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setFocusable(false);



        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setOpaque(false);
        backPanel.add(backButton);

        this.add(backPanel, BorderLayout.SOUTH);

        // 🔗 Add Listeners
        withdrawButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            internalCardLayout.show(internalPanel, "DefaultHome");
        }

         if (e.getSource() == withdrawButton) {
            String password = new String(passwordField.getPassword()).trim();
            if (password.equals(this.user.getBankAccount().getPassword())) {
                String amountText = amountField.getText().trim();
                try {
                    double amount = Double.parseDouble(amountText);

                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(this, "Amount must be greater than zero.", "Invalid Amount", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    //  Only withdraw if parsing succeeded
                    this.user.getBankAccount().withdraw(amount);

                    JOptionPane.showMessageDialog(this, "Withdraw successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    internalCardLayout.show(internalPanel, "DefaultHome");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Withdraw amount cannot be Alphabet or character!", "Invalid withdraw amount", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect password!", "Authentication Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private JPanel createAlignedRow(String labelText, JComponent field) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setForeground(new Color(178, 235, 242));
        label.setFont(new Font("Tahoma", Font.BOLD, 17));
        label.setPreferredSize(new Dimension(160, 25));

        field.setPreferredSize(new Dimension(160, 28));
        row.add(label, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);

        return row;
    }

    public void refreshUser(User user) {
        this.user = user;
        amountField.setText("");
        passwordField.setText("");
        accountTypeCombo.setSelectedIndex(0);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            refreshUser(user);
        }
    }
}
