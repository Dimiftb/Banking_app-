import bankApp.Bank;
import bankApp.Customer;
import bankApp.Employee;

public class BankTester {

    private static Customer alice;
    private static Customer ben;
    private static Employee employeeChris;
    private static Employee employeeDan;

    public BankTester() throws InterruptedException {
        System.out.println("\nOpening accounts for customers.\n");
        Bank bank = new Bank();
        alice = new Customer(1, "Alice", bank);
        ben = new Customer(2, "Ben", bank);
        employeeChris = new Employee(7, "Chris", bank);
        employeeDan = new Employee(10, "Dan", bank);

        employeeChris.openCreditAccount(1, alice);
        employeeChris.openDebitAccount(2, ben);
        employeeChris.openJointAccount(3, alice, ben);

        Thread.sleep(500);
        test1();
        Thread.sleep(500);
        test2();
        Thread.sleep(500);
        test3();
        Thread.sleep(500);
        test4();
        Thread.sleep(1000);
        test5();
        Thread.sleep(6500);
        test6();
        Thread.sleep(500);
        test7();
        Thread.sleep(1500);
        test8();
    }

    //The two account holders are trying to	check the balance simultaneously.
    private static void test1() {
        System.out.println("\nTest 1 – The two account holders are trying to check the balance simultaneously.\n");
        alice.checkBalance(3);
        ben.checkBalance(3);
    }

    //One account holder tries to check	the balance while the other is depositing/withdrawing money.
    private static void test2() {
        System.out.println("\nTest 2 – One account holder tries to check the balance while the other is depositing/withdrawing money.\n");
        ben.deposit(3, 400.0);
        alice.checkBalance(3);
    }

    //The two account holders are trying simultaneously to deposit/withdraw	 money & then check the	balance.
    private static void test3() throws InterruptedException {
        System.out.println("\nTest 3 – The two account holders are trying simultaneously to deposit/withdraw money & then check the balance.\n");
        ben.withdraw(3, 500);
        alice.deposit(3, 100);

        Thread.sleep(100);

        ben.checkBalance(3);
        alice.checkBalance(3);
    }

    //The two account holders are trying simultaneously to deposit/withdraw	 money & then check the	balance.
    //Simultaneously an employee is in the process of completing a transfer.
    private static void test4() throws InterruptedException {
        System.out.println("\nTest 4 – The two account holders are trying simultaneously to deposit/withdraw money & then check the balance." +
                "\n\t\t Simultaneously an employee is in the process of completing a transfer.\n");
        alice.deposit(3, 500);
        ben.withdraw(3, 450);
        employeeChris.transfer(3, 2, 50);


        Thread.sleep(100);

        ben.checkBalance(3);
        ben.checkBalance(2);
        alice.checkBalance(3);
    }

    //Insufficient funds to complete a withdraw.
    private static void test5() throws InterruptedException {
        System.out.println("\nTest 5 – Insufficient funds to complete a withdraw.\n");
        alice.withdraw(3, 2000);

        Thread.sleep(1000);

        ben.deposit(3, 3000);
        alice.withdraw(3, 2000);}

    //Two bank employees trying simultaneously to modify the details of a bank account.
    private static void test6() throws InterruptedException {
        System.out.println("\nTest 6 – Two bank employees trying simultaneously to modify the details of a bank account.\n");
        employeeDan.changeWithdrawLimit(3, 700);
        employeeChris.changeWithdrawLimit(3, 200);

        Thread.sleep(100);

        employeeChris.printAccountDetails(3);
    }

    //One account holder is trying to withdraw and simultaneously an employee is trying to complete a transfer.
    //Both transactions have to wait until the second account holder deposits enough money.
    private static void test7() throws InterruptedException {
        System.out.println("\nTest 7 – One account holder is trying to withdraw and simultaneously an employee is trying to complete a transfer." +
                "\n\t\t Both transactions have to wait until the second account holder deposits enough money.");

        alice.withdraw(3, 1300);
        employeeChris.transfer(3, 2, 1100);

        Thread.sleep(1000);

        ben.deposit(3, 1400);

        Thread.sleep(100);

        employeeChris.checkBalance(2);
        alice.checkBalance(3);
    }
    //An account holder tries to transfer money from one account to the other.
    //Simultaneously, he wants to transfer that same money from the second account to the first.
    //Neither of these accounts have enough balance for these transactions to complete.
    private static void test8(){
        System.out.println("\nTest 8 - An account holder tries to transfer money from one account to the other. " +
                "\n\t\t Simultaneously, he wants to transfer that same money from the second account to the first." +
                "\n\t\t Neither of these accounts have enough balance for these transactions to complete.");

        alice.transfer(3,1,500);
        alice.transfer(1,3,500);
    }
}
