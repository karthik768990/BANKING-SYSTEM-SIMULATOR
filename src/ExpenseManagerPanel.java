import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class ExpenseManagerPanel extends JPanel implements ActionListener {
    private double monthlyLimit = 0;
    private double currentExpenses = 0;
    private int lastMonth = -1;
    private User user;
    private JPanel internalPanel;
    private CardLayout internalCardLayout;


    private JLabel limitLabel, expenseLabel, statusLabel;
    private JTextField limitField;
    private JButton setLimitButton, simulateExpenseButton;
    private JProgressBar expenseBar;

    private JButton withdrawButton, transferButton, backButton;

    public ExpenseManagerPanel(User user,JPanel internalPanel,CardLayout internalCardLayout,JButton withdrawButton, JButton transferButton) {
        this.withdrawButton = withdrawButton;
        this.transferButton = transferButton;
        this.user = user;
        this.user.getBankAccount().setExpenseManagerPanel(this);
        this.internalPanel = internalPanel;
        this.internalCardLayout = internalCardLayout;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false); // Make panel transparent

        Font labelFont = new Font("Inter", Font.PLAIN, 18);
        Font buttonFont = new Font("Inter", Font.BOLD, 16);

        // Labels
        limitLabel = new JLabel("💰 Set Monthly Expense Limit (₹):");
        limitLabel.setFont(labelFont);
        limitLabel.setForeground(Color.WHITE);

        expenseLabel = new JLabel("🧾 Current Expenses: ₹0");
        expenseLabel.setFont(labelFont);
        expenseLabel.setForeground(Color.WHITE);

        statusLabel = new JLabel("📋 Status: No limit set");
        statusLabel.setFont(labelFont);
        statusLabel.setForeground(Color.YELLOW);

        // Input field
        limitField = new JTextField(10);
        limitField.setMaximumSize(new Dimension(200, 30));
        limitField.setFont(new Font("Inter", Font.PLAIN, 16));

        // Buttons
        setLimitButton = createStyledButton("Set Limit", buttonFont);
        simulateExpenseButton = createStyledButton("Simulate Expense (₹50)", buttonFont);

        // Back Button (New)
        backButton = createStyledButton("Back", buttonFont);
        backButton.setFont(buttonFont);  // Adjust size if needed

        // Progress Bar
        expenseBar = new JProgressBar(0, 100); // default
        expenseBar.setValue(0);
        expenseBar.setStringPainted(true);
        expenseBar.setForeground(new Color(52, 152, 219)); // Blue
        expenseBar.setBackground(new Color(236, 240, 241)); // Light gray
        expenseBar.setPreferredSize(new Dimension(300, 25));
        expenseBar.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        expenseBar.setFont(new Font("Inter", Font.BOLD, 14));

        // Action Listeners
        setLimitButton.addActionListener(this);

        simulateExpenseButton.addActionListener(this);

        // Back Button Action Listener
        backButton.addActionListener(this);

        // Add components
        this.add(Box.createVerticalStrut(20));
        this.addCentered(limitLabel);
        this.add(Box.createVerticalStrut(10));
        this.addCentered(limitField);
        this.add(Box.createVerticalStrut(10));
        this.addCentered(setLimitButton);
        this.add(Box.createVerticalStrut(20));
        this.addCentered(simulateExpenseButton);
        this.add(Box.createVerticalStrut(20));
        this.addCentered(expenseLabel);
        this.add(Box.createVerticalStrut(10));
        this.addCentered(expenseBar);
        this.add(Box.createVerticalStrut(10));
        this.addCentered(statusLabel);
        this.add(Box.createVerticalGlue());

        // Adding the back button at the bottom left
        JPanel backPanel = new JPanel();
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // Align left
        backPanel.setOpaque(false);  // Make sure the background is transparent
        backPanel.add(backButton);  // Add back button to this panel
        this.add(backPanel);

        // Set up the timer to check if the month has changed
        Timer timer = new Timer(1000 * 60 * 60 * 24, new ActionListener() { // Checks every day
            @Override
            public void actionPerformed(ActionEvent e) {
                checkMonthChange();
            }
        });
        timer.start();
    }

    private JButton createStyledButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(44, 62, 80));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private void addCentered(JComponent comp) {
        comp.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(comp);
    }

    public void addExpense(double amount) {
        if (monthlyLimit == 0) {
            JOptionPane.showMessageDialog(this, "Please set a monthly limit first.");
            return;
        }

        currentExpenses += amount;
        expenseLabel.setText("🧾 Current Expenses: ₹" + currentExpenses);
        expenseBar.setValue((int) currentExpenses);
        expenseBar.setString("₹" + currentExpenses + " / ₹" + monthlyLimit);

        double remaining = monthlyLimit - currentExpenses;

        if (remaining <= 0) {
            disableButtons();
            statusLabel.setText("⚠️ Status: Limit reached! Transactions disabled.");
            JOptionPane.showMessageDialog(this, "⚠️ Expense limit reached! Withdraw and Transfer are now disabled.");
        } else if (remaining <= 200 && remaining % 50 == 0) {
            JOptionPane.showMessageDialog(this, "⚠️ Warning: Only ₹" + remaining + " remaining before hitting your limit.");
        }
    }

    private void disableButtons() {
        if (withdrawButton != null) withdrawButton.setEnabled(false);
        if (transferButton != null) transferButton.setEnabled(false);
    }

    private void enableButtons() {
        if (withdrawButton != null) withdrawButton.setEnabled(true);
        if (transferButton != null) transferButton.setEnabled(true);
    }

    // Method to check if the month has changed
    private void checkMonthChange() {
        Calendar currentDate = Calendar.getInstance();
        int currentMonth = currentDate.get(Calendar.MONTH);

        // If the month has changed, reset the expenses
        if (currentMonth != lastMonth) {
            lastMonth = currentMonth;
            resetExpenseTracker();
        }
    }

    // Reset expense tracker and prompt the user to set a new limit
    private void resetExpenseTracker() {
        currentExpenses = 0;
        expenseLabel.setText("🧾 Current Expenses: ₹0");
        expenseBar.setValue(0);
        expenseBar.setString("₹0 / ₹" + monthlyLimit);
        statusLabel.setText("📋 Status: Limit reset for new month.");

        JOptionPane.showMessageDialog(this, "It's a new month! Please set your monthly expense limit.");
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Transparent panel style
        Graphics2D g2 = (Graphics2D) g.create();
        Color glassColor = new Color(0, 0, 0, 150); // semi-transparent black
        g2.setColor(glassColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2.dispose();
        super.paintComponent(g);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() ==setLimitButton){
            try {
                monthlyLimit = Integer.parseInt(limitField.getText().trim());
                currentExpenses = 0;
                expenseLabel.setText("🧾 Current Expenses: ₹0");
                statusLabel.setText("📋 Status: Limit set to ₹" + monthlyLimit);
                expenseBar.setMaximum((int) monthlyLimit);
                expenseBar.setValue(0);
                expenseBar.setString("₹0 / ₹" + monthlyLimit);
                enableButtons();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for limit.");
            }
        }
        if(e.getSource() == simulateExpenseButton){
            this.addExpense(50);
            this.user.getBankAccount().withdraw(50);

        }
        if(e.getSource() == backButton){
            internalCardLayout.show(internalPanel,"DefaultHome");
        }
    }
}