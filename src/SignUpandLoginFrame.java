    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
    import java.util.*;


    public class SignUpandLoginFrame extends JFrame implements ActionListener {
        public JPanel mainPanel;
        private JButton signUpButton;
        private JButton loginButton;
        private JButton backButton;
        private JPanel signUpPanel;
        private JPanel loginPanel;
        public static ArrayList<User> userList = new ArrayList<>();
        public SignUpandLoginFrame() {
            ImageIcon logo2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/newLogo.jpg")));
            this.setIconImage(logo2.getImage());
            // Setting up the JFrame
            this.setUndecorated(true);
            this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            this.setLayout(new BorderLayout());

            // Setting the frame to  full-screen size
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.setLocationRelativeTo(null);


            //Panels for the background

            BackgroundPanel backgroundPanel = new BackgroundPanel();
            backgroundPanel.setLayout(new BorderLayout());

           //Custom Buttons
            signUpButton = createCustomButton("Sign Up", 30, new Color(50, 205, 50), 300, 80);
            loginButton = createCustomButton("Login", 30, new Color(30, 144, 255), 300, 80);
            backButton = createCustomButton("Back", 20, Color.RED, 210, 56);





            //Addittion of actionListener here !! for the three custom buttons

            signUpButton.addActionListener(this);
            loginButton.addActionListener(this);
            backButton.addActionListener(this);



            //New panel for cenetring the buttons
            JPanel centerPanel = new JPanel(new GridBagLayout());
            centerPanel.setOpaque(false);

           //Panel for adding the exit button

            JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
            buttonRow.setOpaque(false);
            buttonRow.setBackground(Color.blue);
            //Image icons for the new and existing user buttons
            ImageIcon newUserIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/newUser.jpeg")));
            ImageIcon existingUserIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/existingUser.png")));

            // Create Sign Up panel with image
            signUpPanel = new JPanel();
            signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));
            signUpPanel.setOpaque(true);
            signUpPanel.setBackground(new Color(255,255,0,100));

            JLabel newUserImage = new JLabel(newUserIcon);
            newUserImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            signUpPanel.add(newUserImage);
            signUpPanel.add(Box.createVerticalStrut(10));
            signUpPanel.add(signUpButton);

            // Create Login panel with image
            loginPanel = new JPanel();
            loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
            loginPanel.setOpaque(true);
            loginPanel.setBackground(new Color(0, 255, 255, 100));

            JLabel existingUserImage = new JLabel(existingUserIcon);
            existingUserImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            loginPanel.add(existingUserImage);
            loginPanel.add(Box.createVerticalStrut(10));
            loginPanel.add(loginButton);


            buttonRow.add(signUpPanel);
            buttonRow.add(loginPanel);

            centerPanel.add(buttonRow);

            // Panel for adding the backButton at the bottom left !
            JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
            backButtonPanel.setOpaque(false);
            backButtonPanel.add(backButton);


            // Add panels to background panel
            backgroundPanel.add(centerPanel, BorderLayout.CENTER);
            backgroundPanel.add(backButtonPanel, BorderLayout.SOUTH);

            //Adding the mainPanel so that we can switch between the  new Windows without getting into the actual frames
            CardLayout cardLayout = new CardLayout();
            mainPanel = new JPanel(cardLayout);
            mainPanel.add(backgroundPanel,"main");
            mainPanel.add(new SIGNUPPANEL(mainPanel ,cardLayout ,userList),"Sign up");
            mainPanel.add(new LoginPanel(mainPanel, cardLayout, userList), "Login");

            cardLayout.show(mainPanel,"main");

            this.setSize(screenSize.width, screenSize.height);

            //this place is left intentionally to add the other panel to the cardLayout

           // this.add(backgroundPanel, BorderLayout.CENTER);
            this.add(mainPanel, BorderLayout.CENTER);
            this.setVisible(true);

        }

        // Custom JPanel to handle background image
        class BackgroundPanel extends JPanel {
            private Image backgroundImage;

            public BackgroundPanel() {
                backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/loginBg2.jpg"))).getImage();
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }

        // Method to create custom buttons with specified size
        public  JButton createCustomButton(String text, int fontSize, Color bgColor, int width, int height) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, fontSize));
            button.setBackground(bgColor);
            button.setForeground(Color.black);
            button.setFocusable(false);
            button.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
            button.setPreferredSize(new Dimension(width, height));
            button.setContentAreaFilled(true);
            button.setOpaque(true);
            return button;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==backButton){
                this.dispose();
            }else if(e.getSource() == signUpButton){

                System.out.println("You have pressed the signUp Button ");
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "Sign up");


            }else if(e.getSource()==loginButton){
                CardLayout cl2 = (CardLayout) mainPanel.getLayout();
                cl2.show(mainPanel, "Login");

            System.out.println("You have pressed the login Button ");


            }
        }
    }