package Account;

import org.postgresql.util.PSQLException;

import java.io.*;
import java.sql.*;

public class DbConnectController {

    protected String urlHostname;
    protected String userName;
    protected String userPass;
    protected String createTableSQL;
    protected String balanceSelect;
    protected String balanceUpdate;
    protected String accountInsert;

    public DbConnectController() throws SQLException {
        urlHostname = "jdbc:postgresql://localhost/gkretail";
        userName = "USER";
        userPass = "PASS";
        createTableSQL = "CREATE TABLE IF NOT EXISTS public.\"AccountsTest\" " +
                "(\"accountId\" integer NOT NULL, " +
                "amount double precision, " +
                "PRIMARY KEY (\"accountId\"));";
        balanceSelect = "SELECT \"accountId\", amount FROM public.\"AccountsTest\" WHERE \"accountId\" = ";
        balanceUpdate = "UPDATE public.\"AccountsTest\" SET amount = ? where \"accountId\" = ?";
        accountInsert = "Insert into public.\"AccountsTest\" (\"accountId\", amount) VALUES ( 3246, 20000)";
    }

    public Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(urlHostname, userName, userPass);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public void isDbExistChecker() throws SQLException {
        try {
            Connection dbConnection = getDBConnection();
            PreparedStatement statement = dbConnection.prepareStatement(createTableSQL);
            statement.executeUpdate();
            /*int a = 0;
            int b = 10000;
            for (int i = 0; i < 10; i++) {
                int randomId = a + (int) (Math.random() * b);
                int randomAmount = (i + 1) * b;
                statement = dbConnection.prepareStatement("Insert into public.\"AccountsTest\" (\"accountId\", amount) " +
                        "VALUES (" + randomId + "," + randomAmount + ")");
                statement.executeUpdate();
            }*/
        } catch (PSQLException e) {
            e.printStackTrace();
        }
    }

    public Integer findAccountInDb(int accountId) {
        Statement statement = null;
        Connection dbConnection = null;
        ResultSet rs = null;
        String amount = null;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(balanceSelect + accountId);
            while (rs.next()) {
                amount = rs.getString("amount");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(amount);
    }

    public void updateAccountInDb(int accountId, int finalAmount) throws SQLException {
        try {
            Connection dbConnection = getDBConnection();
            balanceUpdate = "UPDATE public.\"AccountsTest\" SET amount = " + finalAmount + " where \"accountId\" = " + accountId;
            PreparedStatement statement = dbConnection.prepareStatement(balanceUpdate);
            //через statement пока не вышло
            //statement.setString(1, String.valueOf((finalAmount)));
            //statement.setString(2, String.valueOf(accountId));
            statement.executeUpdate();
        } catch (PSQLException e) {
            e.printStackTrace();
        }
    }

}
