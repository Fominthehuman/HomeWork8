package Account;

import java.io.FileNotFoundException;

public class AccountService {

    protected String accountInfo;
    protected String accountInfo2;
    protected int finalAmount;
    protected int finalAmount2;

    public AccountService() throws FileNotFoundException, UnknownAccountException {

    }

    FileController fileController = new FileController();

    void balance(int accountId) throws UnknownAccountException, FileNotFoundException {
        accountInfo = fileController.findAccountInFile(accountId);
        System.out.println("Id и balance: " + accountInfo);
    }

    void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException, FileNotFoundException {
        accountInfo = fileController.findAccountInFile(accountId);
        System.out.println("Id и balance до снятия: " + accountInfo);
        String words[] = accountInfo.split(" ");
        finalAmount = Integer.parseInt(words[1]) - amount;
        System.out.println("Id и итоговый balance: " + accountId + " " + finalAmount);
        fileController.updateAccountInFile(accountId,finalAmount);
        System.out.println("Информация об аккаунте обновлена");
    }

    void deposit(int accountId, int amount) throws FileNotFoundException, NotEnoughMoneyException, UnknownAccountException {
        accountInfo = fileController.findAccountInFile(accountId);
        System.out.println("Id и balance до зачисления: " + accountInfo);
        String words[] = accountInfo.split(" ");
        finalAmount = Integer.parseInt(words[1]) + amount;
        System.out.println("Id и итоговый balance: " + accountId + " " + finalAmount);
        fileController.updateAccountInFile(accountId,finalAmount);
        System.out.println("Информация об аккаунте обновлена");
    }

    void transfer(int fromAccountId, int toAccountId, int amount) throws NotEnoughMoneyException, UnknownAccountException, FileNotFoundException {
        accountInfo = fileController.findAccountInFile(fromAccountId); // from
        System.out.println("Id и balance до списания: " + accountInfo);
        String words[] = accountInfo.split(" ");
        finalAmount = Integer.parseInt(words[1]) - amount; // from
        fileController.updateAccountInFile(fromAccountId,finalAmount);

        accountInfo2 = fileController.findAccountInFile(toAccountId); // to
        System.out.println("Id и balance до зачисления: " + accountInfo2);
        String words2[] = accountInfo2.split(" ");
        finalAmount2 = Integer.parseInt(words2[1]) + amount; // to
        fileController.updateAccountInFile(toAccountId,finalAmount2);

        System.out.println("Информация об аккаунте обновлена");
        System.out.println("Id и balance после списания: " + fromAccountId + " " + finalAmount);
        System.out.println("Id и balance после зачисления: " + toAccountId + " " + finalAmount2);

    }

}