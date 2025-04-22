public class Transaction {
    private String date;
    private String type;
    private double amount;
    private double balanceAfterTransaction;

    public Transaction(String date, String type, double amount, double balanceAfterTransaction) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }
}