import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class User {

        public String name;
        protected String password;
        public String aadharNumber;
        private int age;
        private char gender;
        private String iittpRollNumber;
        double intialDeposit;
        public String email;
        private Address address ;
        private BankAccount bankAccount;
        //Below the bank account and the street addition methods are added refer to it

        User(String name, String password, String aadharNumber, int age, char gender, String iittpRollNumber, String email) {
                this.name = name;
                this.password = password;
                this.aadharNumber = aadharNumber;
                this.age = age;
                this.gender = gender;
                this.iittpRollNumber = iittpRollNumber;
                this.email = email;


        }

        public String getName() {
                return this.name;
        }

        public String getAadhar() {
                return this.aadharNumber;
        }

        public int getAge() {
                return this.age;
        }

        public char getGender() {
                return this.gender;
        }

        public String get_Gender(){
                String temp = "" ;
                if(Character.toLowerCase(this.gender) == 'm'){
                        temp = "Male";
                }else if(Character.toLowerCase(this.gender)=='f'){
                        temp = "Female";
                }else if(Character.toLowerCase(this.gender)=='o'){
                        temp =  "Other";
                }
                return temp;
        }

        public String getRollNumber(){
                return this.iittpRollNumber;
        }
        public String getEmail(){return this.email;}
        private static final Set<String> generatedAccountNumbers = new HashSet<>();

        public static String generateAccountNumber() {
                Random random = new Random();
                String accountNumber;

                do {
                        int firstDigit = random.nextInt(9) + 1; // 1-9
                        long remaining = (long)(random.nextDouble() * 1_000_000_000L); // 9 digits
                        accountNumber = String.valueOf(firstDigit) + String.format("%09d", remaining);

                } while (generatedAccountNumbers.contains(accountNumber));

                generatedAccountNumbers.add(accountNumber);
                return accountNumber;
        }
        public BankAccount getBankAccount() {
                return this.bankAccount;
        }
        public void createBankAccount(BankAccount bankAccount) {
                this.bankAccount = bankAccount;
        }




        public void addAddress(String street , String landMark,String state,String doorNo,String townName,int pincode){
                this.address = new Address(street,landMark,state,doorNo,townName,pincode);
        }
        public void addAddress(Address address){this.address = address;}
        public User getUser(){return this;}
        public Address getAddress() {
                return address;
        }



}