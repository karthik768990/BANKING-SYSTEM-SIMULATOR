import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class BalanceAndLoanPanel extends JPanel implements ActionListener {

    private JLabel balanceLabel;
    private JLabel loanPrincipalLabel;
    private JLabel interestRateLabel;
    private JLabel totalRepaymentLabel;
    private JLabel loanDurationLabel;
    private JButton backButton;
    private CardLayout internalCardLayout;
    private JPanel internalPanel;
    private User user;
    public BalanceAndLoanPanel(User user,JPanel internalPanel,CardLayout internalCardLayout) {
        this.user = user;
        this.internalPanel = internalPanel;
        this.internalCardLayout = internalCardLayout;
        this.setLayout(new BorderLayout(20, 20));
        this.setOpaque(false);

        // 🔷 Title
        JLabel title = new JLabel("Account Summary", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(173, 216, 230)); // Light Blue
        this.add(title, BorderLayout.NORTH);

        // 🟩 Balance Info
        balanceLabel = new JLabel("Balance: ₹ " + format(this.user.getBankAccount().getBalance()), SwingConstants.LEFT);
        balanceLabel.setFont(new Font("Montserrat", Font.BOLD, 22));
        balanceLabel.setForeground(new Color(144, 238, 144)); // Light Green

        // 🟦 Loan Info
        loanPrincipalLabel = new JLabel();
        interestRateLabel = new JLabel();
        totalRepaymentLabel = new JLabel();
        loanDurationLabel = new JLabel();

        Font infoFont = new Font("Tahoma", Font.ITALIC, 18);
        Color infoColor = new Color(230, 230, 250); // Lavender

        for (JLabel label : new JLabel[]{loanPrincipalLabel, interestRateLabel, totalRepaymentLabel, loanDurationLabel}) {
            label.setFont(infoFont);
            label.setForeground(infoColor);
            //label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
        }

        if (user.getBankAccount().hasLoan() ) {
            double principal = user.getBankAccount().getLoanDetails().getPrincipal();
            double interest = user.getBankAccount().getLoanDetails().getInterest(); // e.g., 8.5
            int duration = user.getBankAccount().getLoanDetails().getTenureMonths(); // in months
            double totalRepayment = principal + (principal * interest / 100.0);

            loanPrincipalLabel.setText("Loan Principal: ₹ " + format(principal));
            interestRateLabel.setText("Interest Rate: " + interest + " %");
            totalRepaymentLabel.setText("Total Repayment: ₹ " + format(totalRepayment));
            loanDurationLabel.setText("Duration: " + duration + " months");
        } else {
            loanPrincipalLabel.setText("Loan Principal : null");
            interestRateLabel.setText("Interest Rate : null");
            totalRepaymentLabel.setText("Total Repayment : null");
            loanDurationLabel.setText("Duration : null");
        }

        // 📦 Center content panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        infoPanel.add(balanceLabel);
        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(loanPrincipalLabel);
        infoPanel.add(interestRateLabel);
        infoPanel.add(totalRepaymentLabel);
        infoPanel.add(loanDurationLabel);

        this.add(infoPanel, BorderLayout.CENTER);

        // 🔴 Back Button Panel (South)
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.setOpaque(false);
        backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);

        southPanel.add(backButton);
        this.add(southPanel, BorderLayout.SOUTH);
    }

  /*  @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image balanceBg = new ImageIcon("C:\\Users\\karth\\OOPS_FOLDER(BANKER)\\LoanDetails.jpg").getImage();
        g.drawImage(balanceBg, 0, 0, getWidth(), getHeight(), this);
    }*/

    private String format(double amount) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(amount);
    }

    public void refreshUser(User user) {
        this.user = user; // Update the internal reference

        // 🔁 Update balance if needed
        if (balanceLabel != null) {
            balanceLabel.setText("Balance: ₹ " + format(user.getBankAccount().getBalance()));
        }

        // 🔁 Update loan details if available
        if (loanPrincipalLabel != null && interestRateLabel != null && totalRepaymentLabel != null && loanDurationLabel != null) {
            if (user.getBankAccount().hasLoan()) {
                double principal = user.getBankAccount().getLoanDetails().getPrincipal();
                double interest = user.getBankAccount().getLoanDetails().getInterest();
                int duration = user.getBankAccount().getLoanDetails().getTenureMonths();
                double totalRepayment = principal + (principal * interest / 100.0);

                loanPrincipalLabel.setText("Loan Principal: ₹ " + format(principal));
                interestRateLabel.setText("Interest Rate: " + interest + " %");
                totalRepaymentLabel.setText("Total Repayment: ₹ " + format(totalRepayment));
                loanDurationLabel.setText("Duration: " + duration + " months");
            } else {
                loanPrincipalLabel.setText("Loan Principal: Not available");
                interestRateLabel.setText("Interest Rate: Not available");
                totalRepaymentLabel.setText("Total Repayment: Not available");
                loanDurationLabel.setText("Duration: Not available");
            }
        }

        // ✅ Add more UI updates if this panel shows other user-specific data
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == backButton){
            internalCardLayout.show(internalPanel,"DefaultHome");
        }
    }
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            refreshUser(user); // update when shown
        }
    }



}