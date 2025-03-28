import java.util.Scanner;
import java.io.Serializable;

public class CheckingAccount implements HasMenu,Serializable {
    private double balance;

    public CheckingAccount() {
        this.balance = 0.0;
    }

    public CheckingAccount(double balance) {
        this.balance = balance;
    }

    public static void main(String[] args) {
        CheckingAccount account = new CheckingAccount();
        account.start();
    }

    public double getBalance() {
        return balance;
    }

    public String getBalanceString() {
        return String.format("$%.2f", balance);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void checkBalance() {
        System.out.println("Current balance: " + getBalanceString());
    }

    private double getDouble() {
        Scanner scanner = new Scanner(System.in);
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input. Defaulting to 0.");
            return 0.0;
        }
    }

    public void makeDeposit() {
        System.out.println("Making a deposit...");
        System.out.print("How much to deposit? ");
        double amount = getDouble();
        balance += amount;
        System.out.println("New balance: " + getBalanceString());
    }

    public void makeWithdrawal() {
        System.out.println("Making a withdrawal...");
        System.out.print("How much to withdraw? ");
        double amount = getDouble();
        balance -= amount;
        System.out.println("New balance: " + getBalanceString());
    }

    public String menu() {
        return "\nAccount menu\n" +
               "0) quit\n" +
               "1) check balance\n" +
               "2) make a deposit\n" +
               "3) make a withdrawal\n" +
               "Please enter 0-3: ";
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.print(menu());
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) checkBalance();
                else if (choice == 2) makeDeposit();
                else if (choice == 3) makeWithdrawal();
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }
    }
}
