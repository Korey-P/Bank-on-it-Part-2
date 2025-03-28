import java.io.Serializable;
import java.util.Scanner;

public abstract class User implements HasMenu, Serializable {
    private String userName;
    private String PIN;

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("User name: ");
        String name = scanner.nextLine();
        System.out.print("PIN: ");
        String pin = scanner.nextLine();
        return login(name, pin);
    }

    public boolean login(String userName, String PIN) {
        return this.userName.equals(userName) && this.PIN.equals(PIN);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getPIN() {
        return PIN;
    }

    public abstract String getReport();
}
