import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;

public class AccountDetailsPanel extends JPanel implements ActionListener {
        private CardLayout internalCardLayout;
        private JPanel internalPanel;
        private User user;
    public AccountDetailsPanel(User user,JPanel internalPanel,CardLayout internalCardLayout) {
        this.user = user;
        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        this.internalCardLayout = internalCardLayout;
        this.internalPanel = internalPanel;
         JButton backButton;

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));



        Font labelFont = new Font("Verdana", Font.BOLD, 16);
        Font valueFont = new Font("Segoe UI", Font.PLAIN, 16);

        contentPanel.add(createTitle("Account Details", new Font("Segoe UI Semibold", Font.BOLD, 32), new Color(255, 215, 0)));

        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(createInfoRow("Full Name:", user.getName(), labelFont, valueFont));
        contentPanel.add(createInfoRow("Roll Number:", user.getRollNumber(), labelFont, valueFont));
        contentPanel.add(createInfoRow("Gender:", user.get_Gender(), labelFont, valueFont));
        contentPanel.add(createInfoRow("Date of Birth:", user.getBankAccount().getDateOfBirth(), labelFont, valueFont));
        contentPanel.add(createInfoRow("Phone Number:", user.getBankAccount().getPhoneNumber(), labelFont, valueFont));
        contentPanel.add(createInfoRow("Father's Name:", user.getBankAccount().getFatherName(), labelFont, valueFont));
        contentPanel.add(createInfoRow("Aadhar Number:", user.getAadhar(), labelFont, valueFont));

        BankAccount account = user.getBankAccount();
        if (account != null) {
            contentPanel.add(createInfoRow("Account Number:", account.getAccountNumber(), labelFont, valueFont));
            contentPanel.add(createInfoRow("Account Type:", account.getAccountType(), labelFont, valueFont));
            contentPanel.add(createInfoRow("Current Balance:", "₹" + account.getBalance(), labelFont, valueFont));
        }

        Address address = user.getAddress();
        if (address != null) {
            contentPanel.add(createInfoRow("Address:", address.getDoorNo() + ", " + address.getStreet(), labelFont, valueFont));
            contentPanel.add(createInfoRow("Town/City:", address.getTownName(), labelFont, valueFont));
            contentPanel.add(createInfoRow("Pincode:", Integer.toString(address.getPinCode()), labelFont, valueFont));
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backButton.setBackground(new Color(220, 20, 60)); // Crimson Red
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

// Optional: add action to go back
        backButton.addActionListener(this);

// Panel to align button bottom-left
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 0));
        bottomPanel.add(backButton, BorderLayout.WEST);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setPreferredSize(null);
        this.revalidate();


    }

    private JPanel createInfoRow(String labelText, String valueText, Font labelFont, Font valueFont) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(new Color(176,224,230));

        JLabel value = new JLabel(valueText);
        value.setFont(valueFont);
        value.setForeground(Color.WHITE);

        row.add(label);
        row.add(Box.createHorizontalStrut(10));
        row.add(value);

        return row;
    }

    private JLabel createTitle(String text, Font font, Color color) {
        JLabel title = new JLabel(text, SwingConstants.CENTER);
        title.setFont(font);
        title.setForeground(color);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        return title;
    }
    @Override
    public void actionPerformed(ActionEvent e){

        internalCardLayout.show(internalPanel,"DefaultHome");

    }
    public void refreshUser(User user) {
        this.removeAll(); // Clear the panel

        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        Font labelFont = new Font("Verdana", Font.BOLD, 16);
        Font valueFont = new Font("Segoe UI", Font.PLAIN, 16);

        contentPanel.add(createTitle("Account Details", new Font("Segoe UI Semibold", Font.BOLD, 32), new Color(255, 215, 0)));

        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(createInfoRow("Full Name:", user.getName(), labelFont, valueFont));
        contentPanel.add(createInfoRow("Roll Number:", user.getRollNumber(), labelFont, valueFont));
        contentPanel.add(createInfoRow("Gender:", user.get_Gender(), labelFont, valueFont));
        contentPanel.add(createInfoRow("Date of Birth:", user.getBankAccount().getDateOfBirth(), labelFont, valueFont));
        contentPanel.add(createInfoRow("Phone Number:", user.getBankAccount().getPhoneNumber(), labelFont, valueFont));
        contentPanel.add(createInfoRow("Father's Name:", user.getBankAccount().getFatherName(), labelFont, valueFont));
        contentPanel.add(createInfoRow("Aadhar Number:", user.getAadhar(), labelFont, valueFont));
        contentPanel.add(createInfoRow("Creation Date",user.getBankAccount().getCreationDate(),labelFont,valueFont));

        BankAccount account = user.getBankAccount();
        if (account != null) {
            contentPanel.add(createInfoRow("Account Number:", account.getAccountNumber(), labelFont, valueFont));
            contentPanel.add(createInfoRow("Account Type:", account.getAccountType(), labelFont, valueFont));
            contentPanel.add(createInfoRow("Current Balance:", "₹" + account.getBalance(), labelFont, valueFont));
        }

        Address address = user.getAddress();
        if (address != null) {
            contentPanel.add(createInfoRow("Address:", address.getDoorNo() + ", " + address.getStreet(), labelFont, valueFont));
            contentPanel.add(createInfoRow("Town/City:", address.getTownName(), labelFont, valueFont));
            contentPanel.add(createInfoRow("Pincode:", Integer.toString(address.getPinCode()), labelFont, valueFont));
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backButton.setBackground(new Color(220, 20, 60)); // Crimson Red
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        backButton.addActionListener(this);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 0));
        bottomPanel.add(backButton, BorderLayout.WEST);

        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            // Call refresh with latest user info
            refreshUser(this.user); // Make sure `this.user` is defined and current
        }
    }

   /* @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image bgAccDetails = new ImageIcon("C:\\Users\\karth\\OOPS_FOLDER(BANKER)\\PersonalDetails.jpg").getImage();
        g.drawImage(bgAccDetails, 0, 0, getWidth(), getHeight(), this);
    }*/

}