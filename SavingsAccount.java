import java.io.Serializable;
public class SavingsAccount extends CheckingAccount implements Serializable {
    private double interestRate;

    public SavingsAccount() {
        super();
        interestRate = 0.0;
    }

    public SavingsAccount(double balance, double interestRate) {
        super(balance);
        this.interestRate = interestRate;
    }

    public static void main(String[] args) {
        SavingsAccount account = new SavingsAccount();
        account.start();
    }

    public void setInterestRate(double rate) {
        this.interestRate = rate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void calcInterest() {
        double interest = getBalance() * interestRate;
        setBalance(getBalance() + interest);
    }
}
