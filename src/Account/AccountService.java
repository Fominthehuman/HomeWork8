package Account;

import java.sql.SQLException;

public class AccountService {

    protected int finalAmount;
    protected int finalAmount2;

    public AccountService() throws SQLException {

    }

    DbConnectController dbConnectController = new DbConnectController();

    void balance(int accountId) throws UnknownAccountException {
        try {
            System.out.println(accountId + " " + dbConnectController.findAccountInDb(accountId));
        } catch (NullPointerException | NumberFormatException e) {
            throw new UnknownAccountException();
        }

    }

    void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException, SQLException {
        try {
            finalAmount = (dbConnectController.findAccountInDb(accountId) - amount);
        } catch (NullPointerException | NumberFormatException e) {
            throw new UnknownAccountException();
        }
        if (amount > dbConnectController.findAccountInDb(accountId)) {
            throw new NotEnoughMoneyException();
        }
        dbConnectController.updateAccountInDb(accountId, finalAmount);
        System.out.println("Balance " + accountId + ": " + finalAmount);
    }

    void deposit(int accountId, int amount) throws UnknownAccountException, SQLException { //здесь NotEnoughMoneyException не нужен
        try {
            finalAmount = (dbConnectController.findAccountInDb(accountId) + amount);
        } catch (NullPointerException | NumberFormatException e) {
            throw new UnknownAccountException();
        }
        dbConnectController.updateAccountInDb(accountId, finalAmount);
        System.out.println("Balance " + accountId + ": " + finalAmount);
    }

    void transfer(int fromAccountId, int toAccountId, int amount) throws NotEnoughMoneyException, UnknownAccountException, SQLException {
        try {
            finalAmount = (dbConnectController.findAccountInDb(fromAccountId) - amount);
            finalAmount2 = (dbConnectController.findAccountInDb(toAccountId) + amount);
        } catch (NullPointerException | NumberFormatException e) {
            throw new UnknownAccountException();
        }
        if (amount > dbConnectController.findAccountInDb(fromAccountId)) {
            throw new NotEnoughMoneyException();
        }
        dbConnectController.updateAccountInDb(fromAccountId, finalAmount);
        dbConnectController.updateAccountInDb(toAccountId, finalAmount2);
        System.out.println("Balance " + fromAccountId + ": " + finalAmount);
        System.out.println("Balance " + toAccountId + ": " + finalAmount2);
    }

}