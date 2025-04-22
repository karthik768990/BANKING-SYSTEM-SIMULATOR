import java.time.Duration;
import java.time.LocalDateTime;

public class Loan {
    private double principal =0;
    private int tenureMonths = 0;
    private double interestRate = 0;
    private double totalPayableAmount = 0;
    private String reason;
    private  LocalDateTime applicationTime;
    private boolean approved ;
    private String status ;


    Loan(double principal, int tenureMonths,String reason){
        this.approved = false;
        this.status = "Pending";
        this.reason = reason;
        this.principal = principal;
        this.tenureMonths = tenureMonths;
        this.applicationTime = LocalDateTime.now();
        if(principal <= 1000){
        this.interestRate =5;
        }else if(principal <= 2000 && principal > 1000){
            this.interestRate = 3;
        }else if(principal >2000 && principal < 4000){
            this.interestRate = 1.2;
        }
    }


    double getPrincipal(){return this.principal;}


    double getTotalPayableAmount(){
        return principal*(1+(interestRate*tenureMonths/1200));
    }


    double getInterest(){
        return (principal*interestRate*tenureMonths)/1200;
    }


    int getTenureMonths(){return this.tenureMonths;}


    String getReason(){return this.reason;}


    public void evaluateStatus() {
        if (!approved && Duration.between(applicationTime, LocalDateTime.now()).toHours() >= 24) {
            this.status = "Approved";
            this.approved = true;
        }
    }
    public boolean isApproved(){return this.approved;}


    public String getStatus(){return this.status;}

    public LocalDateTime getApplicationTime(){return this.applicationTime;}

}