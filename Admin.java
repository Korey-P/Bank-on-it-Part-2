public class Admin extends User {
    public Admin() {
        setUserName("admin");
        setPIN("0000");
    }

    public String menu() {
        return "\nAdmin Menu\n" +
               "0) Exit this menu\n" +
               "1) Full customer report\n" +
               "2) Add user\n" +
               "3) Apply interest to savings accounts\n" +
               "Action: ";
    }

    public void start() {
        // Admin actions handled in Bank
    }

    public String getReport() {
        return "Admin: " + getUserName();
    }
}
