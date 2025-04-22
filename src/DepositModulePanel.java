import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositModulePanel extends JPanel implements ActionListener{

    private JTextField amountField;
    private JPasswordField passwordField;
    private JComboBox<String> accountTypeBox;
    private JButton depositButton;
    private JButton backButton;
    private User user;
    private JPanel internalPanel;
    private CardLayout internalCardLayout;

    public DepositModulePanel(User user,JPanel internalPanel,CardLayout internalCardLayout) {
        this.internalPanel = internalPanel;
        this.internalCardLayout = internalCardLayout;
        this.user = user;
        this.setLayout(new BorderLayout(20, 20));
        this.setOpaque(false);

        //A transparent colour for the boxes in the deosit module
        Color transparentWhite = new Color(255, 255, 255, 200);
        // 🔷 Title
        JLabel titleLabel = new JLabel("Deposit Funds", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(173, 216, 230));
        this.add(titleLabel, BorderLayout.NORTH);

        // 🧱 Center Form Panel (Vertical)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        // Utility: Creates aligned label-field rows
        formPanel.add(createAlignedRow("Deposit Amount:", this.amountField = new JTextField(10)));
        this.amountField.setFont(new Font("Verdana",Font.TRUETYPE_FONT,14));
        formPanel.add(Box.createVerticalStrut(15));
        amountField.setBackground(transparentWhite);
        formPanel.add(createAlignedRow("Password:", this.passwordField = new JPasswordField(10)));
        this.passwordField.setFont(new Font("Verdana",Font.TRUETYPE_FONT,14));
        formPanel.add(Box.createVerticalStrut(15));
        passwordField.setBackground(transparentWhite);
        formPanel.add(createAlignedRow("Account Type:", this.accountTypeBox = new JComboBox<>(new String[]{"Digital Savings", "Student "})));
        this.accountTypeBox.setFont(new Font("Verdana",Font.TRUETYPE_FONT,14));
        formPanel.add(Box.createVerticalStrut(20));
        accountTypeBox.setBackground(transparentWhite);

        // ➕ Deposit Button (centered)
        depositButton = new JButton("Deposit");
        depositButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        depositButton.setBackground(new Color(0, 153, 76));
        depositButton.setForeground(Color.WHITE);
        depositButton.setFont(new Font("Tahoma", Font.BOLD, 15));


        formPanel.add(depositButton);

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
        depositButton.addActionListener(this);
        backButton.addActionListener(this);

        this.add(backPanel, BorderLayout.SOUTH);


    }
    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==depositButton){
            String password = new String(this.passwordField.getPassword());
            if(password.equals(this.user.getBankAccount().getPassword()) /*You can add the other like the account type match in the later devolopment ok na ! */){
                try{
                    double amount  = Double.parseDouble(this.amountField.getText().trim());
                    this.user.getBankAccount().deposit(amount);
                    JOptionPane.showMessageDialog(this,"Deposit Successful , Check the balance !","Success",JOptionPane.INFORMATION_MESSAGE);
                    //internalCardLayout.show(internalPanel,"Check Balance");
                    internalCardLayout.show(internalPanel,"DefaultHome");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,"Amount cannot be alphabet or any other character ","Invalid amount ",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(this,"Wrong Password Please try again !","Invalid password",JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == backButton){
            internalCardLayout.show(internalPanel,"DefaultHome");
        }
    }


    private JPanel createAlignedRow(String labelText, JComponent field) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setForeground(new Color(255,64,128));
        label.setFont(new Font("Tahoma", Font.BOLD, 17));
        label.setPreferredSize(new Dimension(160, 25));

        field.setPreferredSize(new Dimension(160, 28));
        row.add(label, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);

        return row;
    }
    public void refreshUser(User user) {
        this.user = user;
        this.amountField.setText(""); // clear previous input
        this.passwordField.setText(""); // clear previous input
        this.accountTypeBox.setSelectedIndex(0); // reset dropdown
    }
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            refreshUser(user); // update when shown
        }
    }
}