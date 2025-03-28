import java.util.*;
import java.io.*;

public class Bank implements HasMenu {
    private Admin admin;
    private CustomerList customers;

    public Bank() {
        admin = new Admin();
        // Uncomment these two lines to reset the customer list with sample data
        // loadSampleCustomers();
        // saveCustomers();

        loadCustomers(); // Try loading from file
        start();         // Launch the main menu
        saveCustomers(); // Save updated data
    }

    public static void main(String[] args) {
        new Bank();
    }

    public String menu() {
        return "\nBank Menu\n" +
               "0) Exit system\n" +
               "1) Login as admin\n" +
               "2) Login as customer\n" +
               "Action: ";
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.print(menu());
            try {
                choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    if (admin.login()) {
                        System.out.println("Admin login successful.");
                        startAdmin();
                    } else {
                        System.out.println("Admin login failed.");
                    }
                } else if (choice == 2) {
                    loginAsCustomer();
                }
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }
    }

    public void startAdmin() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.print(admin.menu());
            try {
                choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    fullCustomerReport();
                } else if (choice == 2) {
                    addUser();
                } else if (choice == 3) {
                    applyInterest();
                }
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }
    }

    public void fullCustomerReport() {
        System.out.println("Full customer report:");
        for (Customer c : customers) {
            System.out.println(c.getReport());
        }
    }

    public void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("PIN: ");
        String pin = scanner.nextLine();

        Customer c = new Customer(name, pin);
        customers.add(c);
        System.out.println("Customer added.");
    }

    public void applyInterest() {
        for (Customer c : customers) {
            c.getSavings().calcInterest();
            System.out.println("New balance: " + c.getSavings().getBalanceString());
        }
    }

    public void loginAsCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String name = scanner.nextLine();
        System.out.print("PIN: ");
        String pin = scanner.nextLine();

        Customer currentCustomer = null;

        for (Customer c : customers) {
            if (c.login(name, pin)) {
                currentCustomer = c;
                break;
            }
        }

        if (currentCustomer != null) {
            System.out.println("Login successful.");
            currentCustomer.start();
        } else {
            System.out.println("Login failed.");
        }
    }

    public void loadSampleCustomers() {
        customers = new CustomerList();
        customers.add(new Customer("Alice", "0000"));
        customers.add(new Customer("Bob", "1111"));
        customers.add(new Customer("Cindy", "2222"));
    }

    public void saveCustomers() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("customers.ser"));
            out.writeObject(customers);
            out.close();
        } catch (Exception e) {
            System.out.println("Error saving customers.");
        }
    }

    public void loadCustomers() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("customers.ser"));
            customers = (CustomerList) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println("No saved data found. Loading sample data.");
            loadSampleCustomers();
        }
    }
}
