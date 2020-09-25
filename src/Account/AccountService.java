package Account;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class AccountService {

    //protected String accountInfo;
    //protected String accountInfo2;
    protected int finalAmount;
    protected int finalAmount2;

    public AccountService() throws FileNotFoundException, UnknownAccountException, SQLException {

    }

    DbConnectController dbConnectController = new DbConnectController();

    void balance(int accountId) throws UnknownAccountException, FileNotFoundException, SQLException {
        System.out.println(accountId + " " + dbConnectController.findAccountInDb(accountId));
    }

    void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException, FileNotFoundException, SQLException {
        finalAmount = (dbConnectController.findAccountInDb(accountId) - amount);
        dbConnectController.updateAccountInDb(accountId, finalAmount);
        System.out.println("Balance " + accountId + ": " + finalAmount);
    }

    void deposit(int accountId, int amount) throws FileNotFoundException, UnknownAccountException, SQLException { //здесь NotEnoughMoneyException не нужен
        finalAmount = (dbConnectController.findAccountInDb(accountId) + amount);
        dbConnectController.updateAccountInDb(accountId, finalAmount);
        System.out.println("Balance " + accountId + ": " + finalAmount);
    }

    void transfer(int fromAccountId, int toAccountId, int amount) throws NotEnoughMoneyException, UnknownAccountException, FileNotFoundException, SQLException {
        finalAmount = (dbConnectController.findAccountInDb(fromAccountId) - amount);
        finalAmount2 = (dbConnectController.findAccountInDb(toAccountId) + amount);
        dbConnectController.updateAccountInDb(fromAccountId, finalAmount);
        dbConnectController.updateAccountInDb(toAccountId, finalAmount2);
        System.out.println("Balance " + fromAccountId + ": " + finalAmount);
        System.out.println("Balance " + toAccountId + ": " + finalAmount2);
    }

}