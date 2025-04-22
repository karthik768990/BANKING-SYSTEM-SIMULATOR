import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Myframe extends JFrame implements ActionListener{
    JLabel label;
    JPanel panel;
    JButton enterButton;
    JButton exitButton;

    Myframe() {
        // LABEL SETUP
        label = new JLabel("BANKING SIMULATION PROGRAM", SwingConstants.CENTER);
        label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 60));
        label.setForeground(new Color(192,192,192));
        ImageIcon logo = new ImageIcon(getClass().getResource("/logo.jpeg"));
        this.setIconImage(logo.getImage());

        // BUTTON SETUP
        enterButton = new JButton("ENTER");
        exitButton = new JButton("EXIT");

        setupButton(enterButton, new Color(50, 205, 50), Color.black, new Font("Roboto", Font.BOLD, 20));
        setupButton(exitButton, Color.red, Color.black, new Font("Lato", Font.BOLD, 20));

        //Setting up the sizes of the enter and the exit buttons
        enterButton.setPreferredSize(new Dimension(210,56));
        exitButton.setPreferredSize(new Dimension(210,56));

        //Frame dimension Retiriver
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();




        // BACKGROUND IMAGE PANEL
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Mainbg.jpg"))).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Set layout to center label
        panel.setLayout(new GridBagLayout());
        panel.setSize(screenSize.width, screenSize.height);

        // GridBagConstraints to center label
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label, gbc);

        // BUTTON PANEL (Bottom Layout)
        JPanel buttonPanel = new JPanel(new BorderLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image leftbuttonLabelImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images.jpeg"))).getImage();
                g.drawImage(leftbuttonLabelImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        buttonPanel.setOpaque(false);

        // Wrapping buttons in panels to ensure correct alignment
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image leftbuttonLabelImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images.jpeg"))).getImage();
                g.drawImage(leftbuttonLabelImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image rightbuttonLabelImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images.jpeg"))).getImage();
                g.drawImage(rightbuttonLabelImage, 0, 0, getWidth(), getHeight(), this);
            }
        };


        leftPanel.setOpaque(false);
        rightPanel.setOpaque(false);
        //ActionListener for the enter and the exitbuttons
        enterButton.addActionListener(this);
        exitButton.addActionListener(this);



        leftPanel.add(exitButton);
        rightPanel.add(enterButton);

        buttonPanel.add(leftPanel, BorderLayout.WEST);
        buttonPanel.add(rightPanel, BorderLayout.EAST);




        // FRAME SETUP
                this.setUndecorated(true);
                this.setExtendedState(JFrame.MAXIMIZED_BOTH);
                this.setBackground(Color.black);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setLayout(new BorderLayout());
                this.add(panel, BorderLayout.CENTER);
                this.add(buttonPanel, BorderLayout.SOUTH);
                this.setSize(screenSize.width, screenSize.height);
                this.setLocationRelativeTo(null);
                this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent  e){
        if(e.getSource()==exitButton){
            int choice  = JOptionPane.showConfirmDialog(this,"Are you sure you want to quit !","Quit confirmation message !",JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION) {
                System.exit(0);
                exitButton.setEnabled(false);
            }

        }else if(e.getSource()==enterButton){
            new SignUpandLoginFrame();


        }


    }


    private void setupButton(JButton button, Color bgColor, Color fgColor, Font font) {
        button.setFont(font);
        button.setFocusable(false);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
    }
}