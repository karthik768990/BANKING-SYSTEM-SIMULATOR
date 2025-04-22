import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

//This is a note to remid you to change the textcolor and font according to the background


public class LoginPanel extends JPanel implements ActionListener {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private ArrayList<User> users;

    private JTextField userNamefield;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;

    public LoginPanel(JPanel mainPanel, CardLayout cardLayout, ArrayList<User> users) {


        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.users = users;

        this.setLayout(new BorderLayout());
        this.setBackground(null);

        // Title
        JLabel titleLabel = new JLabel("Welcome to Login Page", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Brush Script MT", Font.BOLD, 40));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        titleLabel.setForeground(Color.pink);
        this.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setOpaque(false);

        // Center form
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(null);
        centerPanel.setOpaque(false);

        JLabel accLabel = new JLabel("Account Holder's Name:");
        accLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        accLabel.setFont(new Font("Montserrat", Font.PLAIN, 20));
        accLabel.setOpaque(false);
        accLabel.setForeground(new Color(255,255,224));
        centerPanel.add(accLabel);

        userNamefield = new JTextField(15);
        userNamefield.setMaximumSize(new Dimension(250, 30));
        userNamefield.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNamefield.setFont(new Font("Montserrat", Font.PLAIN, 18));
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(userNamefield);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passLabel.setForeground(new Color(255,255,224));
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(passLabel);

            //This is the place for adding the other panels using the layouts




        passwordField = new JPasswordField(15);
        passwordField.setMaximumSize(new Dimension(250, 30));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFont(new Font("Poppins", Font.BOLD, 18));
        loginButton.setBackground(new Color(255,215,0));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(loginButton);

        this.add(centerPanel, BorderLayout.CENTER);

        // Back button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setOpaque(false);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Lato", Font.PLAIN, 16));
        backButton.setBackground( Color.red);
        backButton.setForeground(Color.black);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        // Making the back button 20% larger (increase the width and height)
        Dimension newButtonSize = new Dimension(
                (int) (backButton.getPreferredSize().width * 1.8),
                (int) (backButton.getPreferredSize().height * 1.8)
        );
        backButton.setPreferredSize(newButtonSize);

        bottomPanel.add(backButton, BorderLayout.WEST);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image loginBackground = new ImageIcon(Objects.requireNonNull(getClass().getResource("/loginPageBackground.jpg"))).getImage();
        super.paintComponent(g);
        g.drawImage(loginBackground, 0, 0, getWidth(), getHeight(), null);

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userName = userNamefield.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            boolean loginSuccess = false;
            if (userName.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ACCOUNT NUMBER OR PASSWORD CAN'T BE EMPTY", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (User user : users) {
                if (user.getBankAccount().getUserName().equals(userName) && user.getBankAccount().getPassword().equals(password)) {
                    loginSuccess = true;
                    break;
                }
            }

            if (loginSuccess) {
                JOptionPane.showMessageDialog(this, "Login successful!");

                User currentUser = null;
                for (User user : users) {
                    if (user.getBankAccount().getUserName().equals(userName) && user.getBankAccount().getPassword().equals(password)) {

                        currentUser = user;
                        break;
                    }
                }

                if (currentUser != null) {

                    mainPanel.add(new HomePagePanel(currentUser.getUser(),mainPanel,cardLayout,this.users), "HomePage");
                    cardLayout.show(mainPanel, "HomePage");
                }


            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            cardLayout.show(mainPanel, "main");
        }
    }
}