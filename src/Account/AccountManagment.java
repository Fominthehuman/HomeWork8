package Account;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AccountManagment {

    AccountService startService = new AccountService();
    Scanner in = new Scanner(System.in);
    FileController fileController = new FileController();

    public AccountManagment() throws FileNotFoundException, UnknownAccountException {
    }

    public void startAccountManagment() throws IOException, UnknownAccountException, NotEnoughMoneyException {

        fileController.isFileExistChecker();
        //fileController.getFileName();
        System.out.println("Введите команду");
        String s = in.nextLine();
        String words[] = s.split(" ");

        switch (words[0]) {
            case "balance":
                startService.balance(Integer.parseInt(words[1]));
                break;
            case "withdraw":
                startService.withdraw(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                break;
            case "deposit":
                startService.deposit(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                break;
            case "transfer":
                startService.transfer(Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]));
                break;
            default:
                System.out.println("Команда не найдена");
                startAccountManagment();
        }

    }

}
