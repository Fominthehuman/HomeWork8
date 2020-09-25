package Account;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, UnknownAccountException, NotEnoughMoneyException, SQLException {
        AccountManagment accountManagment = new AccountManagment();
        accountManagment.startAccountManagment();
    }
}
