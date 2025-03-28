# Bank-on-it-Part-2

## Pseudocode

INTERFACE HasMenu

METHOD menu() RETURNS string
METHOD start()

CLASS CheckingAccount IMPLEMENTS HasMenu, Serializable

VARIABLE balance AS double

METHOD CheckingAccount()
    SET balance TO 0.0

METHOD CheckingAccount(balance)
    SET this.balance TO balance

METHOD getBalance() RETURNS double
    RETURN balance

METHOD getBalanceString() RETURNS string
    RETURN formatted balance as "$x.xx"

METHOD setBalance(newBalance)
    SET balance TO newBalance

METHOD checkBalance()
    PRINT current balance

METHOD getDouble() RETURNS double
    TRY to read user input as double
    IF invalid THEN RETURN 0

METHOD makeDeposit()
    ASK user for deposit amount
    CALL getDouble()
    ADD amount to balance
    PRINT new balance

METHOD makeWithdrawal()
    ASK user for withdrawal amount
    CALL getDouble()
    SUBTRACT amount from balance
    PRINT new balance

METHOD menu() RETURNS string
    RETURN options: check balance, deposit, withdraw, quit

METHOD start()
    LOOP until user selects quit
        DISPLAY menu
        GET user choice
        CALL corresponding method

CLASS SavingsAccount EXTENDS CheckingAccount IMPLEMENTS Serializable

VARIABLE interestRate AS double

METHOD SavingsAccount()
    CALL super()
    SET interestRate TO 0.0

METHOD SavingsAccount(balance, rate)
    CALL super(balance)
    SET interestRate TO rate

METHOD setInterestRate(rate)
    SET interestRate TO rate

METHOD getInterestRate() RETURNS double
    RETURN interestRate

METHOD calcInterest()
    CALCULATE interest = balance * interestRate
    ADD interest to balance

ABSTRACT CLASS User IMPLEMENTS HasMenu, Serializable

VARIABLE userName AS string
VARIABLE PIN AS string

METHOD login() RETURNS boolean
    PROMPT user for name and PIN
    RETURN login(name, PIN)

METHOD login(name, PIN) RETURNS boolean
    RETURN true if name and PIN match object's data

METHOD setUserName(name)
    SET userName TO name

METHOD getUserName() RETURNS string
    RETURN userName

METHOD setPIN(PIN)
    SET this.PIN TO PIN

METHOD getPIN() RETURNS string
    RETURN PIN

ABSTRACT METHOD getReport() RETURNS string

CLASS Customer EXTENDS User IMPLEMENTS Serializable

VARIABLE checking AS CheckingAccount
VARIABLE savings AS SavingsAccount

METHOD Customer()
    SET userName TO "Alice"
    SET PIN TO "0000"
    INITIALIZE checking and savings accounts

METHOD Customer(name, PIN)
    SET userName and PIN
    INITIALIZE checking and savings accounts

METHOD getReport() RETURNS string
    RETURN formatted string with account balances

METHOD changePin()
    ASK user for new PIN
    SET PIN

METHOD menu() RETURNS string
    RETURN options: manage checking, manage savings, change PIN, quit

METHOD start()
    LOOP until user selects exit
        DISPLAY menu
        GET user choice
        IF manage checking THEN call checking.start()
        IF manage savings THEN call savings.start()
        IF change PIN THEN call changePin()

METHOD main()
    CREATE a new Customer object
    IF login is successful THEN CALL customer.start()
    ELSE PRINT login failed

CLASS Admin EXTENDS User

METHOD Admin()
    SET userName TO "admin"
    SET PIN TO "0000"

METHOD menu() RETURNS string
    RETURN options: full customer report, add user, apply interest, exit

METHOD start()
    (Leave empty — Bank handles admin actions)

METHOD getReport() RETURNS string
    RETURN admin name (optional)

CLASS CustomerList EXTENDS ArrayList<Customer> IMPLEMENTS Serializable

(No additional methods — used to wrap ArrayList for saving/loading)

CLASS Bank IMPLEMENTS HasMenu

VARIABLE admin AS Admin
VARIABLE customers AS CustomerList

METHOD Bank()
    CREATE admin object
    CALL loadCustomers()
    CALL start()
    CALL saveCustomers()

METHOD main()
    CREATE a new Bank object

METHOD menu() RETURNS string
    RETURN options: login as admin, login as customer, exit

METHOD start()
    LOOP until user selects exit
        DISPLAY main menu
        GET user choice
        IF login as admin
            CALL admin.login()
            IF successful THEN CALL startAdmin()
            ELSE PRINT login failed
        IF login as customer
            CALL loginAsCustomer()

METHOD startAdmin()
    LOOP until admin exits
        DISPLAY admin menu
        GET choice
        IF 1 THEN CALL fullCustomerReport()
        IF 2 THEN CALL addUser()
        IF 3 THEN CALL applyInterest()

METHOD fullCustomerReport()
    FOR EACH customer IN customers
        CALL customer.getReport()
        PRINT result

METHOD addUser()
    ASK for username and PIN
    CREATE new Customer object
    ADD to customers

METHOD applyInterest()
    FOR EACH customer IN customers
        CALL customer.savings.calcInterest()
        PRINT new balance

METHOD loginAsCustomer()
    PROMPT for userName and PIN
    SEARCH customers list
    IF match found THEN CALL customer.start()
    ELSE PRINT login failed

METHOD loadSampleCustomers()
    CREATE new CustomerList
    ADD Alice, Bob, Cindy with PINs

METHOD saveCustomers()
    SERIALIZE customers to "customers.ser"

METHOD loadCustomers()
    ATTEMPT to deserialize "customers.ser"
    IF fails THEN
        CALL loadSampleCustomers()
