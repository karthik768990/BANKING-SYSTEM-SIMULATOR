import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

public class HomePagePanel extends JPanel implements ActionListener {

        private User user;
        private CardLayout internalCardLayout;
        private JPanel internalPanel;
        private JButton[] buttons;
        private JButton logoutButton;
        private JPanel mainPanel;
        private CardLayout cardLayout;
        private AccountDetailsPanel accountDetails;
        private  BalanceAndLoanPanel balanceAndLoanPanel;
        private  DepositModulePanel depositModulePanel;
        private WithdrawPanel withdrawPanel;
        private ArrayList<User> users;
        private TransferMoneyPanel transferMoneyPanel;
        private ApplyLoanPanel applyLoanPanel;
        private  LoanStatusPanel loanStatusPanel;
        private  TransactionHistoryPanel transactionHistoryPanel;
        private  ExpenseManagerPanel expenseManagerPanel;



        private final String[] actions = {
                "View Account Details", "Show Balance", "Deposit Money",
                "Withdraw Money", "Transfer Money", "Apply for Loan",
                "Loan Status", "Transaction History", "Expense Manager"
        };
        private Image backgroundImage;
       // private ActionListener mainListener;

        public HomePagePanel(User user,JPanel mainPanel,CardLayout cardLayout,ArrayList<User> users) {
            this.users = users;
            this.user = user;
            this.mainPanel = mainPanel;
            this.cardLayout = cardLayout;

            this.setLayout(new BorderLayout());
            this.setOpaque(false);

            backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/homePagePanelBg.jpg"))).getImage();


            // 🟡 Main Welcome Line
            JLabel welcomeLabel = new JLabel("Welcome, " + user.getName() + "!", SwingConstants.LEFT);
            welcomeLabel.setFont(new Font("Lucida Bright", Font.BOLD, 36));
            welcomeLabel.setForeground(new Color(255, 215, 0));

    // 🧾 Roll number + Account type label
            String roll = user.getRollNumber();  // Assuming you have this getter
            String accountType = user.getBankAccount().getAccountType(); // You should have this too
            JLabel subInfoLabel = new JLabel("Roll No: " + roll.toUpperCase() + "  |  Account Type: " + accountType.toUpperCase(), SwingConstants.LEFT);
            subInfoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            subInfoLabel.setForeground(Color.WHITE);

    // 🕒 Clock label on the right
            JLabel clockLabel = new JLabel("09 Apr 2025, 04:35 PM", SwingConstants.RIGHT);
            clockLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
            clockLabel.setForeground(Color.white);
            // 🕒 Make clock update in real-time
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    java.time.LocalDateTime now = java.time.LocalDateTime.now();
                    java.time.format.DateTimeFormatter formatter =
                            java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a");
                    clockLabel.setText(now.format(formatter));
                }
            });
            timer.start();


            // 🧱 Stack welcome + subInfo vertically
            JPanel leftTextPanel = new JPanel();
            leftTextPanel.setLayout(new BoxLayout(leftTextPanel, BoxLayout.Y_AXIS));
            leftTextPanel.setOpaque(false);
            leftTextPanel.add(welcomeLabel);
            leftTextPanel.add(Box.createVerticalStrut(5));
            leftTextPanel.add(subInfoLabel);

    // 🟦 Top panel to hold both left and right sections
            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setOpaque(false);
            topPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 30, 20));  // padding
            topPanel.add(leftTextPanel, BorderLayout.WEST);
            topPanel.add(clockLabel, BorderLayout.EAST);

            this.add(topPanel, BorderLayout.NORTH);  // Replaces the earlier welcomeLabel line


            // Center button grid
            JPanel centerPanel = new JPanel(new GridLayout(3, 3, 20, 20));
            centerPanel.setOpaque(false);
            centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

            buttons = new JButton[actions.length];

            for (int i = 0; i < actions.length; i++) {
                buttons[i] = createAnimatedButton(actions[i]);
                buttons[i].setToolTipText("Click to " + actions[i].toLowerCase());
                buttons[i].addActionListener(this);
                centerPanel.add(buttons[i]);
            }



            // Logout
            logoutButton = new JButton("Logout");
            logoutButton.setFont(new Font("Pacifico", Font.BOLD, 14));
            logoutButton.setFocusPainted(false);
            logoutButton.setBackground(new Color(220, 20, 60));
            logoutButton.setForeground(Color.WHITE);
            logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            logoutButton.setActionCommand("Logout");
            logoutButton.addActionListener(this);

            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.setOpaque(false);
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 10));
            bottomPanel.add(logoutButton, BorderLayout.WEST);
            this.add(bottomPanel, BorderLayout.SOUTH);

            // 🔁 Internal CardLayout
            internalCardLayout = new CardLayout();
            internalPanel = new JPanel(internalCardLayout);
            internalPanel.setOpaque(false);

            // Placeholder panels for now (replace with your own)
            internalPanel.add(new JLabel("Account Details Panel Placeholder", SwingConstants.CENTER), "View Account Details");
            internalPanel.add(new JLabel("Balance Panel Placeholder", SwingConstants.CENTER), "Show Balance");
            internalPanel.add(new JLabel("Deposit Panel Placeholder", SwingConstants.CENTER), "Deposit Money");
            internalPanel.add(new JLabel("Withdraw Panel Placeholder", SwingConstants.CENTER), "Withdraw Money");
            internalPanel.add(new JLabel("Transfer Panel Placeholder", SwingConstants.CENTER), "Transfer Money");
            internalPanel.add(new JLabel("Loan Application Panel Placeholder", SwingConstants.CENTER), "Apply for Loan");
            internalPanel.add(new JLabel("Loan Status Panel Placeholder", SwingConstants.CENTER), "Loan Status");
            internalPanel.add(new JLabel("Transaction History Panel Placeholder", SwingConstants.CENTER), "Transaction History");
            internalPanel.add(new JLabel("Expense Manager Panel Placeholder", SwingConstants.CENTER), "Expense Manager");




            //This is the place for the panels and all
            accountDetails = new AccountDetailsPanel(this.user, this.internalPanel, this.internalCardLayout);
            FadePanel fadeWrappedPanel = new FadePanel(accountDetails);
            this.internalPanel.add(fadeWrappedPanel, "View Account Details");
            JPanel defaultPanel = new JPanel();
            defaultPanel.setOpaque(false);
            defaultPanel.add(new JLabel(" The dashboard!", SwingConstants.CENTER));
            this.internalPanel.add(defaultPanel, "DefaultHome");
            //
            balanceAndLoanPanel = new BalanceAndLoanPanel(this.user,this.internalPanel,this.internalCardLayout);
            this.internalPanel.add(new FadePanel(balanceAndLoanPanel),"Check Balance");


            //This is the addition of the deposit module
            depositModulePanel = new DepositModulePanel(user,internalPanel,internalCardLayout);
            this.internalPanel.add(new FadePanel(depositModulePanel),"Deposit module");

            //This is the addition of the withdraw panel
            withdrawPanel = new WithdrawPanel(this.user,this.internalPanel,this.internalCardLayout);
            this.internalPanel.add(new FadePanel(withdrawPanel),"Withdraw module");

            //This is the addtion of the transfer panel
            transferMoneyPanel = new TransferMoneyPanel(this.user,this.internalPanel,this.internalCardLayout,this.users);
            this.internalPanel.add(new FadePanel(this.transferMoneyPanel),"Transfer module");

            //This is the addition of the apply loan panel
            applyLoanPanel = new ApplyLoanPanel(this.user,this.internalPanel,this.internalCardLayout);
            this.internalPanel.add(new FadePanel(this.applyLoanPanel),"Apply Loan module");

            //This is the addition of the loan status panel
            loanStatusPanel = new LoanStatusPanel(this.user,this.internalPanel,this.internalCardLayout);
            this.internalPanel.add(new FadePanel(this.loanStatusPanel),"Loan Status module");

            //This is the addition of the transaction history panel
            transactionHistoryPanel = new TransactionHistoryPanel(this.user,this.internalPanel,this.internalCardLayout);
            this.internalPanel.add(new FadePanel(this.transactionHistoryPanel),"Transaction History");

            //This is the addition of the expense manager panel
            expenseManagerPanel = new ExpenseManagerPanel(this.user,this.internalPanel,this.internalCardLayout,this.buttons[3],this.buttons[4]);
            this.internalPanel.add(new FadePanel(this.expenseManagerPanel),"Expense Manager Panel");

          /*  JPanel centerWrapperPanel = new JPanel();
            centerWrapperPanel.setLayout(new BorderLayout());
            centerWrapperPanel.setOpaque(false);

// Add button grid at the top
            centerWrapperPanel.add(centerPanel, BorderLayout.NORTH);

// Add internal panel below the buttons
            centerWrapperPanel.add(internalPanel, BorderLayout.CENTER);

// Final add to this main HomePagePanel
            this.add(centerWrapperPanel, BorderLayout.CENTER);*/
            //The above two lines is the most important parts of the code and now im trying the other parts
          //this.add(centerPanel, BorderLayout.CENTER);
           // this.add(internalPanel, BorderLayout.EAST); // You can reposition this if needed
            JPanel centerWrapper = new JPanel(new BorderLayout());
            centerWrapper.setOpaque(false);
            centerWrapper.add(centerPanel, BorderLayout.NORTH); // buttons
            centerWrapper.add(internalPanel, BorderLayout.CENTER); // switchable content
            this.add(centerWrapper, BorderLayout.CENTER);



        }

        private JButton createAnimatedButton(String text) {
            JButton button = new JButton(text);
            button.setFont(new Font("Inter", Font.BOLD, 16));
            button.setFocusPainted(false);
            button.setBackground(new Color(72, 61, 139));
            button.setForeground(Color.WHITE);
            button.setActionCommand(text);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15))
            );

            Color base = button.getBackground();
            Color hover = new Color(123, 104, 238);
            button.addMouseListener(new MouseAdapter() {
                Timer hoverTimer;
                float[] ratio = {0};

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (hoverTimer != null) hoverTimer.stop();
                    ratio[0] = 0;
                    hoverTimer = new Timer(15, evt -> {
                        ratio[0] += 0.05f;
                        button.setBackground(blendColors(base, hover, ratio[0]));
                        if (ratio[0] >= 1) hoverTimer.stop();
                    });
                    hoverTimer.start();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (hoverTimer != null) hoverTimer.stop();
                    ratio[0] = 0;
                    hoverTimer = new Timer(15, evt -> {
                        ratio[0] += 0.05f;
                        button.setBackground(blendColors(hover, base, ratio[0]));
                        if (ratio[0] >= 1) hoverTimer.stop();
                    });
                    hoverTimer.start();
                }
            });

            return button;
        }

        private Color blendColors(Color c1, Color c2, float ratio) {
            int r = (int) (c1.getRed() + ratio * (c2.getRed() - c1.getRed()));
            int g = (int) (c1.getGreen() + ratio * (c2.getGreen() - c1.getGreen()));
            int b = (int) (c1.getBlue() + ratio * (c2.getBlue() - c1.getBlue()));
            return new Color(r, g, b);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == logoutButton) {
                int choice = JOptionPane.showConfirmDialog(this,"Are you sure you want to log out ","Confirm Logout",JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION){
                   cardLayout.show(mainPanel,"Login");
                   mainPanel.remove(this);
                    }
                }
            if(source == buttons[0]){

                accountDetails.refreshUser(user);
                internalCardLayout.show(internalPanel,"View Account Details");
                internalPanel.revalidate();
                internalPanel.repaint();

            }
            if(source == buttons[1]){
                balanceAndLoanPanel.refreshUser(user);
                internalCardLayout.show(internalPanel,"Check Balance");
                internalPanel.revalidate();
                internalPanel.repaint();
            }
            if(source == buttons[2]){
                depositModulePanel.refreshUser(user);
                internalCardLayout.show(internalPanel,"Deposit module");
                internalPanel.revalidate();
                internalPanel.repaint();
            }
            if(source == buttons[3]){
                withdrawPanel.refreshUser(user);
                internalCardLayout.show(internalPanel,"Withdraw module");
                internalPanel.revalidate();
                internalPanel.repaint();

            }
            if(source == buttons[4]){
                transferMoneyPanel.refreshUser(user);
                internalCardLayout.show(internalPanel,"Transfer module");
                internalPanel.revalidate();
                internalPanel.repaint();
            }
            if(source == buttons[5]){
                applyLoanPanel.refreshUser(user);
                internalCardLayout.show(internalPanel,"Apply Loan module");
                internalPanel.revalidate();
                internalPanel.repaint();
            }
            if(source == buttons[6]){
                loanStatusPanel.refreshUser(user);
                internalCardLayout.show(internalPanel,"Loan Status module");
                internalPanel.revalidate();
                internalPanel.repaint();
            }
            if(source == buttons[7]){
                transactionHistoryPanel.refreshUser(user);
                internalCardLayout.show(internalPanel,"Transaction History");
                internalPanel.revalidate();
                internalPanel.repaint();
            }
            if(source == buttons[8]){
                internalCardLayout.show(internalPanel,"Expense Manager Panel");
                internalPanel.revalidate();
                internalPanel.repaint();
            }

            }

        }