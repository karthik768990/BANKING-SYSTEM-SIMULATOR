import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TransactionHistoryPanel extends JPanel implements ActionListener {

    private User user;
    private JPanel internalPanel;
    private CardLayout internalCardLayout;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton backButton;

    public TransactionHistoryPanel(User user, JPanel internalPanel, CardLayout internalCardLayout) {
        this.user = user;
        this.internalPanel = internalPanel;
        this.internalCardLayout = internalCardLayout;

        this.setLayout(new BorderLayout(20, 20));
        this.setOpaque(false);

        JLabel title = new JLabel("Transaction History", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(new Color(255, 228, 181));
        this.add(title, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"Date", "Type", "Amount", "Balance"};
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        }; // Empty model
        table = new JTable(tableModel);
        table.setFont(new Font("Consolas", Font.PLAIN, 14));
        table.setRowHeight(24);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(240, 248, 255));
        //table.setBackground(new Color(255, 255, 255, 230));
        table.setForeground(Color.DARK_GRAY);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        this.add(scrollPane, BorderLayout.CENTER);

        // Back button
        backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.BLACK);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        backButton.addActionListener(this);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false);
        bottomPanel.add(backButton);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // Initial load
        refreshTableData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            internalCardLayout.show(internalPanel, "DefaultHome");
        }
    }

    public void refreshUser(User user) {
        this.user = user;
        refreshTableData();
    }

    public void refreshTableData() {
        tableModel.setRowCount(0); // Clear existing rows

        ArrayList<Transaction> transactions = user.getBankAccount().showTransactionHistory();
        for (Transaction t : transactions) {
            Object[] row = {
                    t.getDate(),
                    t.getType(),
                    "₹" + t.getAmount(),
                    "₹" + t.getBalanceAfterTransaction()
            };
            tableModel.addRow(row);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        // Transparent panel style
        Graphics2D g2 = (Graphics2D) g.create();
        Color glassColor = new Color(44, 62, 80, 100); // semi-transparent black
        g2.setColor(glassColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2.dispose();
        super.paintComponent(g);
    }
}