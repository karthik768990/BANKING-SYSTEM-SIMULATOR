import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class BankAccount {
    final private String accountNumber;
    final private String accountHolderName;
    private double balance;
    private List<Transaction> transactionHistory;
    private long phoneNumber;
    final private String dateOfBirth;
    private String accountType;
    private boolean isActive;
    private String creationDate;
    private String date ;
    private String password;
    private Loan loan;
    private String fatherName;
    private boolean hasLoan = false;
    private List<Loan> loanDetails = new ArrayList<>();
    private ExpenseManagerPanel expenseManagerPanel;



    BankAccount(String accountNumber,
                String accountHolderName,
                double intialBalance,
                String accountType,
                String creatingDate,
                long phoneNumber,
                String password,
                String dateOfBirth,String fatherName){
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = intialBalance;
        this.accountType = accountType;
        this.isActive = true;
        this.creationDate = creatingDate;
        this.phoneNumber= phoneNumber;
        this.password = password;
        this.dateOfBirth=dateOfBirth;
        this.fatherName = fatherName;
        this.transactionHistory = new ArrayList<>();
    }
    public String getUserName(){return this.accountHolderName;}
    public double getBalance(){
        return this.balance;
    }
    public boolean isActive(){return this.isActive;}
    public String getAccountType(){return this.accountType;}
    public void deposit(double amount){
        this.balance +=amount;
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateAsString = today.format(formatter);
        transactionHistory.add(new Transaction(dateAsString, "Deposit", amount, balance));

    }
    //Setting the expense manager so that it will get access to the withdrawal and the transfer amounts
    public void setExpenseManagerPanel(ExpenseManagerPanel expenseManagerPanel){
        this.expenseManagerPanel = expenseManagerPanel;
    }


    public void withdraw(double amount){
        if(amount  > this.balance){
            JOptionPane.showMessageDialog(null,"Withdrawal Error","Your balance is pretty low You can't withdraw the designated amount !",JOptionPane.ERROR_MESSAGE );
        }else{
           this.balance -=amount;
           expenseManagerPanel.addExpense(amount);
           LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String dateAsString = today.format(formatter);
            transactionHistory.add(new Transaction(dateAsString, "Withdrawal", amount, balance));
        }
    }
    public String getCreationDate(){
        return this.creationDate;
    }
    public void setPhoneNumber(long newNumber){
        this.phoneNumber = newNumber;
    }
    private void setPassword(String newPassword){
        this.password = newPassword;
    }
    protected String getPassword(){return this.password;}
    String getAccountNumber(){return this.accountNumber;}
    String getFatherName(){
        return this.fatherName;
    }

    //These are the other methods that should be added so far
    //removeAccount()

    //applyForLoan(double amount , int tenure)
    public boolean applyLoan(double principal,int tenureMonths,String reason){
        this.hasLoan = true;
        if(principal<=0 || tenureMonths<=0 ){
            return false;
        }else if(this.balance < 260){
            return false;
        }else{
            this.loan = new Loan(principal , tenureMonths,reason);
            //loan.evaluateStatus();
            loanDetails.add(loan);
            return true;
        }

    }
    public Loan getLoanDetails(){return this.loan;}
    public List<Loan> getLoans() {
        return loanDetails;
    }
    public String getDateOfBirth(){
        String tempDOb = this.dateOfBirth;
        return tempDOb;
    }
    public String getPhoneNumber(){
        String tempPhone =  Long.toString(this.phoneNumber);
        return tempPhone;
    }
    boolean hasLoan(){
        if(hasLoan){return true;}
        else{return false;}
    }
    //This is a functionn to perform the transfer method


 public void transfer(String receiverAccountNumber, String receiverName, double amount, String note, ArrayList<User> allUsers) {
    if (amount <= 0) {
        JOptionPane.showMessageDialog(null, "Transfer amount cannot be negative", "INVALID TRANSFER AMOUNT", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (this.balance < amount) {
        JOptionPane.showMessageDialog(null, "INSUFFICIENT BALANCE", "BALANCE ERROR", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Find the recipient
    BankAccount receiverAccount = null;
    for (User user : allUsers) {
        if (user.getBankAccount().getAccountNumber().equals(receiverAccountNumber)
                && user.getName().equalsIgnoreCase(receiverName)) {
            receiverAccount = user.getBankAccount();
            break;
        }
    }

    if (receiverAccount == null) {
        JOptionPane.showMessageDialog(null, "Receiver not found", "INVALID RECEIVER", JOptionPane.ERROR_MESSAGE);
        return;
    }

    //  All checks passed: Proceed with transfer
    this.balance -= amount;
    receiverAccount.balance += amount;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String tempDate = (LocalDateTime.now()).format(formatter);

    transactionHistory.add(new Transaction(tempDate, "Transfer", amount, balance));
    expenseManagerPanel.addExpense(amount);

    //  Only show success message if everything above succeeded
    String message = "Transfer successful to " + receiverName + " [" + receiverAccountNumber + "].";
    if (!note.trim().isEmpty()) {
        message += "\nNote sent successfully.";
    }

    JOptionPane.showMessageDialog(null, message, "Transfer Complete", JOptionPane.INFORMATION_MESSAGE);
}



    public ArrayList<Transaction> showTransactionHistory() {
        String[] columnNames = {"Date", "Type", "Amount", "Balance"};
        Object[][] data = new Object[transactionHistory.size()][4];

        for (int i = 0; i < transactionHistory.size(); i++) {
            Transaction t = transactionHistory.get(i);
            data[i][0] = t.getDate();
            data[i][1] = t.getType();
            data[i][2] = t.getAmount();
            data[i][3] = t.getBalanceAfterTransaction();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 200));

        //JOptionPane.showMessageDialog(null, scrollPane, "Transaction History", JOptionPane.PLAIN_MESSAGE);
        return (ArrayList<Transaction>) transactionHistory;
    }

}
