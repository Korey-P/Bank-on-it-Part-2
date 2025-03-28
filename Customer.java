import java.util.Scanner;
import java.io.Serializable;

public class Customer extends User implements Serializable {
    private CheckingAccount checking;
    private SavingsAccount savings;

    public Customer() {
        setUserName("Alice");
        setPIN("0000");
        checking = new CheckingAccount();
        savings = new SavingsAccount();
    }

    public Customer(String userName, String PIN) {
        setUserName(userName);
        setPIN(PIN);
        checking = new CheckingAccount();
        savings = new SavingsAccount();
    }

    public static void main(String[] args) {
        Customer customer = new Customer();
        if (customer.login()) {
            System.out.println("Login Successful\n");
            customer.start();
        } else {
            System.out.println("Login Failed.");
        }
    }

    public String menu() {
        return "\nCustomer Menu\n" +
               "0) Exit\n" +
               "1) Manage Checking Account\n" +
               "2) Manage Savings Account\n" +
               "3) Change PIN\n" +
               "Action (0-3): ";
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            System.out.print(menu());
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) checking.start();
                else if (choice == 2) savings.start();
                else if (choice == 3) changePin();
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }
    }

    public void changePin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new PIN: ");
        String newPin = scanner.nextLine();
        setPIN(newPin);
        System.out.println("PIN changed successfully.");
    }

    public String getReport() {
        return "Customer: " + getUserName() +
               "\nChecking Balance: " + checking.getBalanceString() +
               "\nSavings Balance: " + savings.getBalanceString();
    }
}

