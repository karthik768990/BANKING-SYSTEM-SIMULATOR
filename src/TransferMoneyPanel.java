import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TransferMoneyPanel extends JPanel implements ActionListener {

    private JTextField receiverAccountField;
    private JTextField receiverNameField;
    private JTextField amountField;
    private JTextField noteField;
    private JPasswordField passwordField;
    private JButton transferButton;
    private JButton backButton;
    private User user;
    private JPanel internalPanel;
    private CardLayout internalCardLayout;
    private ArrayList<User> users;

    public TransferMoneyPanel(User user, JPanel internalPanel, CardLayout internalCardLayout,ArrayList<User> users) {
        this.users = users;
        this.user = user;
        this.internalPanel = internalPanel;
        this.internalCardLayout = internalCardLayout;

        this.setLayout(new BorderLayout(20, 20));
        this.setOpaque(false);

        Color transparentWhite = new Color(255, 255, 255, 200);

        // 🔝 Title
        JLabel titleLabel = new JLabel("Transfer Money", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(100, 255, 218));
        this.add(titleLabel, BorderLayout.NORTH);

        // 🧱 Center Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        // Compact input boxes
        this.receiverAccountField = new JTextField();
        this.receiverNameField = new JTextField();
        this.amountField = new JTextField();
        this.noteField = new JTextField();
        this.passwordField = new JPasswordField();

        this.setFieldStyle(receiverAccountField, transparentWhite);
        this.setFieldStyle(receiverNameField, transparentWhite);
        this.setFieldStyle(amountField, transparentWhite);
        this.setFieldStyle(noteField, transparentWhite);
        this.setFieldStyle(passwordField, transparentWhite);

        formPanel.add(createAlignedRow("Receiver Account No:", receiverAccountField));
        formPanel.add(Box.createVerticalStrut(12));
        formPanel.add(createAlignedRow("Receiver Full Name:", receiverNameField));
        formPanel.add(Box.createVerticalStrut(12));
        formPanel.add(createAlignedRow("Amount:", amountField));
        formPanel.add(Box.createVerticalStrut(12));
        formPanel.add(createAlignedRow("Note (optional):", noteField));
        formPanel.add(Box.createVerticalStrut(12));
        formPanel.add(createAlignedRow("Your Password:", passwordField));
        formPanel.add(Box.createVerticalStrut(20));

        // 🚀 Transfer Button
        transferButton = new JButton("Transfer");
        transferButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        transferButton.setBackground(new Color(98, 0, 238));
        transferButton.setForeground(Color.WHITE);
        transferButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        transferButton.addActionListener(this);
        formPanel.add(transferButton);

        this.add(formPanel, BorderLayout.CENTER);

        // 🔙 Back Button
        backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.BLACK);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setOpaque(false);
        backPanel.add(backButton);

        this.add(backPanel, BorderLayout.SOUTH);
    }

    private void setFieldStyle(JTextField field, Color bgColor) {
        field.setPreferredSize(new Dimension(160, 28));
        field.setFont(new Font("Verdana", Font.PLAIN, 14));
        field.setBackground(bgColor);
        field.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    private JPanel createAlignedRow(String labelText, JComponent field) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setForeground(new Color(255, 64, 128));
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(160, 25));

        row.add(label, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);

        return row;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            internalCardLayout.show(internalPanel, "DefaultHome");
        } else if (e.getSource() == transferButton) {
            // You can hook up the actual transfer logic here.
            String receiverAcc = receiverAccountField.getText().trim();
            String receiverName = receiverNameField.getText().trim();
            String note = noteField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();

            if (!pass.equals(user.getBankAccount().getPassword())) {
                JOptionPane.showMessageDialog(this, "Incorrect Password!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double amt = Double.parseDouble(amountField.getText().trim());
                // TRANSFER LOGIC HANDLING
                user.getBankAccount().transfer(receiverAcc, receiverName, amt,note ,this.users);
                JOptionPane.showMessageDialog(this, "Transfer Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                internalCardLayout.show(internalPanel, "DefaultHome");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Amount!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void refreshUser(User user) {
        this.user = user;

        // Clear all input fields
        receiverAccountField.setText("");
        receiverNameField.setText("");
        amountField.setText("");
        noteField.setText("");
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