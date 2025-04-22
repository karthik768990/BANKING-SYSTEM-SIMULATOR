import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class SIGNUPPANEL extends JPanel implements ActionListener {
    JButton signUpButton;
    JButton backButton;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private ArrayList<User> users;
    JTextField fullNameField;
    JTextField dobField;
    JTextField doorNoField;
    JTextField landMArkField;
    JTextField stateField;
    JTextField iittpField;
    JTextField balanceField;
    JComboBox<String> accTypeField;
    JPasswordField passwordField;
    JPasswordField confirmpasswordField;
    JTextField townField;
    JTextField streetField;
    JTextField phoneNumberField;
    JTextField pincodeField;
    JTextField aadharField;
    JTextField fatherNameField;
    JComboBox<String> genderBox;



    public SIGNUPPANEL(JPanel mainPanel , CardLayout cardLayout, ArrayList<User> users) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.setLayout(new BorderLayout());
        this.users = users;



        //creation of the respective fields for the texts and the other things
        fullNameField = createTextField();
        dobField = createTextField();
        doorNoField = createTextField();
        landMArkField = createTextField();
        stateField = createTextField();
        iittpField = createTextField();
        balanceField = createTextField();

        accTypeField = new JComboBox<>(new String[]{"DIGITAL SAVINGS","STUDENT"});
        townField = createTextField();
        streetField = createTextField();
        phoneNumberField = createTextField();
        pincodeField = createTextField();
        confirmpasswordField = createPasswordField();
        aadharField = createTextField();
        fatherNameField = createTextField();
        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderBox.setFont(new Font("Arial", Font.PLAIN, 16));











        JLabel titleLabel = new JLabel("Bank Account Signup", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        this.add(titleLabel, BorderLayout.NORTH);

        // ==== Form Panel ====
        JPanel formPanel = new JPanel(new GridLayout(12, 3, 20, 20)){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Image bgImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/signUp.jpeg"))).getImage();
                g.drawImage(bgImage,0,0,getWidth(),getHeight(),this);
            }
        };
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        // ==== Account Details ====
        formPanel.add(sectionLabel("Account Details"));
        formPanel.add(new JLabel());

        formPanel.add(createLabel(" IITTP ROLL NUMBER(Ex : CSxxB0xx):"));
        formPanel.add(iittpField);

        formPanel.add(createLabel("Full name "));
        formPanel.add(fullNameField);

        formPanel.add(createLabel("Initial Balance:"));
        formPanel.add(balanceField);

        // ==== Balance Hint ====
        formPanel.add(new JLabel());
        formPanel.add(createHint("💡 Minimum balance should be ₹260/-"));

        formPanel.add(createLabel("Account Type(Digital Saving/Student's):"));
        formPanel.add(accTypeField);

        formPanel.add(createLabel("Date of Birth(DD/MM/YYYY):"));
        formPanel.add(dobField);

        formPanel.add(createLabel("Password:"));
        passwordField = createPasswordField();
        formPanel.add(passwordField);

        // ==== Password Hint ====
        formPanel.add(new JLabel());
        formPanel.add(createHint("🔹Password must be 8+ chars,1 uppercase,1 lowercase,1 digit"));

        formPanel.add(createLabel("Confirm Password:"));
        formPanel.add(confirmpasswordField);

        // ==== Personal Address ====
        formPanel.add(sectionLabel("Personal Address"));
        formPanel.add(new JLabel());

        formPanel.add(createLabel("Phone Number :"));
        formPanel.add(phoneNumberField);

        formPanel.add(createLabel("Door Number:"));
        formPanel.add(doorNoField);

        formPanel.add(createLabel("Street:"));
        formPanel.add(streetField);

        formPanel.add(createLabel("Landmark(Ex.SBI BANK):"));
        formPanel.add(landMArkField);

        formPanel.add(createLabel("VILLAGE/TOWN/CITY:"));
        formPanel.add(townField);

        formPanel.add(createLabel("State:"));
        formPanel.add(stateField);

        formPanel.add(createLabel("Pincode:"));
        formPanel.add(pincodeField);

        formPanel.add(createLabel("Aadhar Number:"));
        formPanel.add(aadharField);

        formPanel.add(createLabel("Father's Name:"));
        formPanel.add(fatherNameField);

        formPanel.add(createLabel("Gender:"));
        formPanel.add(genderBox);


        this.add(formPanel, BorderLayout.CENTER);

        // ==== Buttons (Retro Style) ====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Image bgImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/signUp.jpeg"))).getImage();
                g.drawImage(bgImage,0,0,getWidth(),getHeight(),this);
            }
        };
        signUpButton = createRetroButton("SIGN UP");
        signUpButton.addActionListener(this);
       // signUpButton.setEnabled(false);
         backButton = createRetroButton("BACK");
         backButton.addActionListener(this);
        backButton.setBackground(Color.lightGray);
        backButton.setForeground(Color.darkGray);
        buttonPanel.add(backButton);
        buttonPanel.add(signUpButton);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Arial", Font.PLAIN, 18));
        return field;
    }

    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField(20);
        field.setFont(new Font("Arial", Font.PLAIN, 18));
        return field;
    }

    private JLabel sectionLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.LEFT);
        label.setFont(new Font("Montserrat", Font.BOLD, 24));
        return label;
    }

    private JLabel createHint(String text) {
        JLabel hintLabel = new JLabel(text, SwingConstants.LEFT);
        hintLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        hintLabel.setForeground(Color.GRAY);
        return hintLabel;
    }

    private JButton createRetroButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(180, 50));
        button.setFont(new Font("Courier New", Font.BOLD, 20));
        button.setFocusable(false);
        button.setBackground(Color.green);
        button.setForeground(Color.white);
        return button;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Image bgImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/signUp.jpeg"))).getImage();
        g.drawImage(bgImage,0,0,getWidth(),getHeight(),this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            cardLayout.show(mainPanel,"main");

        }else if(e.getSource() == signUpButton){



             boolean oneBox = false;
             boolean twoBox = false;
             boolean threeBox  =false;
             boolean fourBox = false;
             boolean fiveBox = false;
             boolean sixBox = false;
             boolean sevenBox = false;
             boolean eightBox = false;
             boolean nineBox = false;
             boolean tenBox = false;
             boolean elevenBox = false;
             boolean twelveBox  = false;
             boolean thirteenBox= false;
             boolean fourteenBox= false;
             boolean fifteenBox =false;


            //A TEST FOR THE VALID NAME !
            if(!isValidName(fullNameField.getText().trim())){

                JOptionPane.showMessageDialog(null,
                        " Only Alphabets are allowed [a-z] & [A - Z]",
                        "INVALID FULL NAME ",
                        JOptionPane.ERROR_MESSAGE,
                        null);
            }else{oneBox = true;}
            //A TEST FOR THE DATE OF BIRTH TEXT FIELD
            if (!isValidDOB(dobField.getText().trim())) {
                JOptionPane.showMessageDialog(
                        null,
                        "Invalid Date of Birth format.\nPlease use dd/MM/yyyy (e.g., 05/04/2025).",
                        "Invalid DOB",
                        JOptionPane.ERROR_MESSAGE
                );
            }else{twoBox = true;}
            //A TEST FOR THE STATE FIELD
            if (!isValidName(stateField.getText().trim())) {
                JOptionPane.showMessageDialog(
                        null,
                        "Invalid State Name ! They should only have the alphabets ",
                        "Invalid STATE",
                        JOptionPane.ERROR_MESSAGE
                );
            }else{threeBox = true;}

            //A TEST FOR THE  IITTP ROLL NUMBER
            if (!isValidRollNumber(iittpField.getText().trim())) {
                JOptionPane.showMessageDialog(
                        null,
                        " Enter a valid IITTP Roll Number (e.g., CS24B0## or EE24B0##).",
                        "INVALID ROLL NUMBER ",
                        JOptionPane.ERROR_MESSAGE
                );
            }else {fourBox = true;}
            //A TEST FOR THE Initial balance validator
            double tempBalance = 0;
            try{
                tempBalance = Double.parseDouble(balanceField.getText().trim());

            }catch(NumberFormatException newE){
                JOptionPane.showMessageDialog(null,"Only the numbers are allowed in the Balance field","Invalid Balance error",JOptionPane.ERROR_MESSAGE);
            }
            if (!validInitialBalance(tempBalance)){
                JOptionPane.showMessageDialog(
                        null,
                        " THE MINIMUM BALANCE SHOULD BE ₹ 260",
                        "INVALID BALANCE ",
                        JOptionPane.ERROR_MESSAGE
                );
            }else{fiveBox = true;}
            //A TEST FOR THE ACCOUNT TYPE
            if (!isValidName((String)accTypeField.getSelectedItem())) {
                JOptionPane.showMessageDialog(
                        null,
                        " ACCOUNT CAN ONLY BE ALPHABETS ",
                        "INVALID ACCOUNT NAME  ",
                        JOptionPane.ERROR_MESSAGE
                );
            }else{sixBox = true;}
            //A TEST FR THE PASSWORD  CHECKING
            if (!isValidPassword(new String(passwordField.getPassword()).trim())) {
                JOptionPane.showMessageDialog(
                        null,
                        " PASSWORD SHOULD CONTAIN ONE LOWER ONE UPPER AND ONE DIGIT ",
                        "INVALID PASSWORD ",
                        JOptionPane.ERROR_MESSAGE
                );
            }else{sevenBox = true;}

            //A TEST FOR THE CONFIRM PASSWORD
            char[] passwordChars = confirmpasswordField.getPassword(); // safer way
            String password = new String(passwordChars);
            if (!isValidPassword(password) ){
                JOptionPane.showMessageDialog(
                        null,
                        " PASSWORD SHOULD CONTAIN ONE LOWER ONE UPPER AND ONE DIGIT ",
                        "INVALID PASSWORD ",
                        JOptionPane.ERROR_MESSAGE
                );
            }else{eightBox = true;}


            //A CHECK WHETHER BOTH THE PASSWORD MATCH OR NOT
            if(!(new String(passwordField.getPassword()).trim().equals(password))){
                JOptionPane.showMessageDialog(null,"PASSWORDS DO NOT MATCH ","PASSWROD ERROR ",JOptionPane.ERROR_MESSAGE);
            }else{nineBox = true;}
            //A CHECK  FOR THE PHONE NUMBER
            if(!isValidPhoneNumber((phoneNumberField.getText().trim()))){
                JOptionPane.showMessageDialog(null,"Invalid Phone Number it should only have digits ","Invalid phone number ",JOptionPane.ERROR_MESSAGE);

            }else{tenBox = true;}
            //TEST FOR THE VALID PIN CODE
            if(!isValidPincode(pincodeField.getText().trim())){
                JOptionPane.showMessageDialog(null,"Invalid pincode  Please enter the valid code ","Pincode error",JOptionPane.ERROR_MESSAGE);
            }else{elevenBox = true;}

            if (!aadharField.getText().trim().matches("\\d{12}")) {
                JOptionPane.showMessageDialog(null, "Aadhar number must be exactly 12 digits", "Invalid Aadhar", JOptionPane.ERROR_MESSAGE);
            } else { twelveBox = true; }

            if (!isValidName(fatherNameField.getText().trim())) {
                JOptionPane.showMessageDialog(null, "Father's name should contain only alphabets", "Invalid Name", JOptionPane.ERROR_MESSAGE);
            } else { thirteenBox = true; }


            //The below line is so much mandatory bruh !
            //A check to confirm whether the user has enetered the digits in the textBox or not !

            long tempPhoneNumber = 0;

            try {
                tempPhoneNumber = Long.parseLong(phoneNumberField.getText().trim());
                fourteenBox = true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,"The phone number must be an integer","Invalid phone number",JOptionPane.ERROR_MESSAGE);
            }
            int tempPincode = 0;
            try{
                tempPincode = Integer.parseInt(pincodeField.getText().trim());
                fifteenBox = true;
            }catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(null,"The pincode must consists of digits","Invalid pincode",JOptionPane.ERROR_MESSAGE);
            }

            if(oneBox && twoBox && threeBox && fourBox && fiveBox && sixBox && sevenBox && eightBox && nineBox && tenBox && elevenBox && twelveBox && thirteenBox && fourteenBox && fifteenBox){

               // signUpButton.setEnabled(true);
                String fullName = fullNameField.getText().trim();
                String dateOfBirth = dobField.getText().trim();
                String doorNumber = doorNoField.getText().trim();
                String landMark = landMArkField.getText().trim();
                String state = stateField.getText().trim();
                String IITTPRollNumber = iittpField.getText().trim();
                double balance = Double.parseDouble(balanceField.getText().trim());
                String accountType = (String)accTypeField.getSelectedItem();
                String password_actual = new String(passwordField.getPassword()).trim();
                String confirmPassword = new String(confirmpasswordField.getPassword()).trim();
                long phoneNumber =  Long.parseLong(phoneNumberField.getText().trim());
                String street  = streetField.getText().trim();
                String Village = townField.getText().trim();
                int pincode = Integer.parseInt(pincodeField.getText().trim());
                String aadharNumber = aadharField.getText().trim();
                String fatherName = fatherNameField.getText().trim();
                String gender = (String) genderBox.getSelectedItem();
                assert gender != null;
                char genderChar = gender.charAt(0);

                int age = calculateAge(dateOfBirth);
                Address address = new Address(street,landMark,state,doorNumber,Village,pincode);

                User user = new User(fullName,password_actual,aadharNumber,age,genderChar,IITTPRollNumber,emailGenerator(IITTPRollNumber));
                user.addAddress(address);
                String creationDate = LocalDate.now().toString();
                BankAccount bankAccount = new BankAccount(user.generateAccountNumber(),fullName,balance,accountType,creationDate,phoneNumber,confirmPassword,dateOfBirth,fatherName);
                user.createBankAccount(bankAccount);
                SignUpandLoginFrame.userList.add(user);
                ImageIcon image = new ImageIcon("C:\\Users\\karth\\OOPS_FOLDER(BANKER)\\SignUpSuccesfull.jpeg");
                JOptionPane.showMessageDialog(this,"USER REGISTERED SUCCESFULLY","User authenticator",JOptionPane.INFORMATION_MESSAGE,image);
                cardLayout.show(mainPanel,"main");



            }else{
                    JOptionPane.showMessageDialog(null,"Something in the given information is not right ","Info error",JOptionPane.ERROR_MESSAGE);
            }



            //Write your login logic here bro ok na !
           /* ImageIcon image = new ImageIcon("C:\\Users\\karth\\OOPS_FOLDER(BANKER)\\SignUpSuccessful.jpeg");
            JOptionPane.showMessageDialog(this,"USER REGISTERED SUCCESSFULLY","User authenticator",JOptionPane.INFORMATION_MESSAGE,image);
            cardLayout.show(mainPanel,"main");*/
        }

    }
    //ENSURES THAT THE password has 8 digits and also it should contain one capital one small and one digit

    private boolean isValidPassword(String password){
        if(password.length() < 8){return false;}
        if(password == null || password.isEmpty()){return false;}
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        for(char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }

                if (hasUpper && hasLower && hasDigit) {
                    return true;
                }
            }
            return false;
        }
        //Checks whether the roll number is correct and ensures that it is of the form CS24B046
        private boolean isValidRollNumber(String rollNumber) {

            if (rollNumber.length() == 8) {
                boolean oneTwo = false;
                boolean threeFour = false;
                boolean five = false;
                boolean sixSevenEight = false;
                String local = rollNumber.toLowerCase();
                char[] c = local.toCharArray();
                if (Character.isLowerCase(c[0]) && Character.isLowerCase(c[1])) {
                    oneTwo = true;
                }
                if (Character.isDigit(c[2]) && Character.isDigit(c[3])) {
                    threeFour = true;
                }
                if (Character.isLowerCase(c[4])) {
                    five = true;
                }
                if (Character.isDigit(c[5]) && Character.isDigit(c[6]) && Character.isDigit(c[7])) {
                    sixSevenEight = true;
                }
                if(oneTwo && threeFour && five && sixSevenEight){
                    return true;
                }else{
                    return false;
                }
            } else {
                return false;
            }

        }
        //This method ensures that the phone number is of 10 dgits and it should not start with 0
        private boolean isValidPhoneNumber(String phoneNumber) {
            return phoneNumber != null && phoneNumber.matches("^[1-9][0-9]{9}$");
        }
        //Ensures that the name has all the letters only
        private boolean isValidName(String name) {
            return name != null && name.matches("^[a-zA-Z ]+$");
        }
        private boolean isValidDOB(String dob) {
            if (dob == null || dob.length() != 10) {
                return false;
            }


                return dob.matches("\\d{2}/\\d{2}/\\d{4}");
            }
        private boolean validInitialBalance(double number){
        if(number<0 || number <260){
            return false;
        }else{return true;}
        }


    private int calculateAge(String dob) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // Parse the date of birth string into a Date object
            Date birthDate = sdf.parse(dob);

            // Get the current date
            Calendar currentDate = Calendar.getInstance();

            // Get the birth year, month, and day
            Calendar birthCalendar = Calendar.getInstance();
            birthCalendar.setTime(birthDate);

            int birthYear = birthCalendar.get(Calendar.YEAR);
            int birthMonth = birthCalendar.get(Calendar.MONTH);
            int birthDay = birthCalendar.get(Calendar.DAY_OF_MONTH);

            // Get the current year, month, and day
            int currentYear = currentDate.get(Calendar.YEAR);
            int currentMonth = currentDate.get(Calendar.MONTH);
            int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);

            // Calculate the age
            int age = currentYear - birthYear;

            // Adjust age if the current date is before the birthday in the current year
            if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
                age--;
            }

            return age;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return -1 in case of an error
        }
    }
    private String emailGenerator(String s){
        return s.trim().toLowerCase() + "@iittp.ac.in";
    }
    private boolean isValidPincode(String s){
        return s.charAt(0) != 0 && s.length() == 6;
    }

}