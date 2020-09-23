package Account;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, UnknownAccountException, NotEnoughMoneyException {
        AccountManagment accountManagment = new AccountManagment();
        accountManagment.startAccountManagment();
    }
}
