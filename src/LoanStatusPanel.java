import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoanStatusPanel extends JPanel implements ActionListener {

    private JTextArea statusArea;
    private JButton backButton;
    private User user;
    private JPanel internalPanel;
    private CardLayout internalCardLayout;

    public LoanStatusPanel(User user, JPanel internalPanel, CardLayout internalCardLayout) {
        this.user = user;
        this.internalPanel = internalPanel;
        this.internalCardLayout = internalCardLayout;
        this.setLayout(new BorderLayout(20, 20));
        this.setOpaque(false);

        JLabel title = new JLabel("Loan Status", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(new Color(144, 238, 144));
        this.add(title, BorderLayout.NORTH);

        statusArea = new JTextArea(15, 40);
        statusArea.setEditable(false);
        statusArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        statusArea.setOpaque(false);
        statusArea.setForeground(new Color(0,0,0));
        //statusArea.setBackground(new Color(0,0,0,0));
        statusArea.setBorder(BorderFactory.createTitledBorder("Your Loan Applications"));

        this.add(new JScrollPane(statusArea), BorderLayout.CENTER);

        backButton = new JButton("Back");
        styleButton(backButton, Color.RED);
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setOpaque(false);
        backPanel.add(backButton);
        this.add(backPanel, BorderLayout.SOUTH);

        backButton.addActionListener(this);
    }

    private void styleButton(JButton button, Color bg) {
        button.setBackground(bg);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        button.setFocusPainted(false);
    }

    public void refreshUser(User user) {
        this.user = user;
        statusArea.setText(""); // Clear the status area
        if (user == null || user.getBankAccount() == null) {
            statusArea.setText("⚠️ Error loading bank account info.\n");
            return;
        }

        ArrayList<Loan> loans = (ArrayList<Loan>) user.getBankAccount().getLoans(); // Updated method name (if you're using getLoanDetails())

        if (loans == null || loans.isEmpty()) {
            statusArea.append("🚫 No loan applications found.\n");
            return;
        }

        for (Loan loan : loans) {
            loan.evaluateStatus(); // ✅ Auto-approve after 24 hours

            statusArea.append("📌 Amount Requested: ₹" + loan.getPrincipal() + "\n");
            statusArea.append("🕒 Tenure: " + loan.getTenureMonths() + " months\n");
            statusArea.append("📋 Reason: " + loan.getReason() + "\n");
            statusArea.append("💰 Interest Rate: " + loan.getInterest() + "%\n");
            statusArea.append("🧾 Interest Amount: ₹" + String.format("%.2f", loan.getInterest()) + "\n");
            statusArea.append("💳 Total Payable: ₹" + String.format("%.2f", loan.getTotalPayableAmount()) + "\n");
            statusArea.append("⏳ Applied On: " + loan.getApplicationTime() + "\n");
            statusArea.append("✅ Current Status: " + loan.getStatus() + "\n");
            statusArea.append("------------------------------------------------------------\n\n");
        }
    }


    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            refreshUser(user);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            internalCardLayout.show(internalPanel, "DefaultHome");
        }
    }
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        Color glassColor = new Color(155, 89, 182, 100); // semi-transparent black
        g2.setColor(glassColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2.dispose();
        super.paintComponent(g);
    }
}