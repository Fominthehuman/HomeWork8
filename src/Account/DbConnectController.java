package Account;

import org.postgresql.util.PSQLException;

import java.io.*;
import java.sql.*;

public class DbConnectController {

    protected String urlHostname;
    protected String userName;
    protected String userPass;
    protected String createTableSQL;
    String selectTableSQL = "select * from xrg_item";
    protected String balanceSelect;
    protected String balanceUpdate;

    public DbConnectController() throws FileNotFoundException, SQLException {
        urlHostname = "jdbc:postgresql://localhost/gkretail";
        userName = "user";
        userPass = "pass";
        createTableSQL = "CREATE TABLE IF NOT EXISTS public.\"AccountsTest2\" " +
                "(\"accountId\" integer NOT NULL, " +
                "amount double precision, " +
                "PRIMARY KEY (\"accountId\"));";
        balanceSelect = "SELECT \"accountId\", amount FROM public.\"AccountsTest\" WHERE \"accountId\" = ";
        balanceUpdate = "UPDATE public.\"AccountsTest\" SET amount = ? where \"accountId\" = ?";

    }

    /*public void dbConnection() throws IOException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection(urlHostname, userName, userPass);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }*/

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

    public void isDbExistChecker() throws IOException, SQLException {

    }

    public Integer findAccountInDb(int accountId) throws FileNotFoundException {
        Statement statement = null;
        Connection dbConnection = null;
        ResultSet rs = null;
        String amount = "0";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            rs = statement.executeQuery(balanceSelect + accountId);
            while (rs.next()) {
                amount = rs.getString("amount");
                //System.out.println(accountId + " " + amount);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(amount);
    }//balance 1564

    public void updateAccountInDb(int accountId, int finalAmount) throws FileNotFoundException, UnknownAccountException, SQLException {
        try {
            Connection dbConnection = getDBConnection();
            balanceUpdate = "UPDATE public.\"AccountsTest\" SET amount = " + finalAmount + " where \"accountId\" = "+ accountId;
            PreparedStatement statement = dbConnection.prepareStatement(balanceUpdate);
        /*statement.setString(1, String.valueOf(accountId));
        statement.setString(2, String.valueOf(finalAmount));*/
            statement.executeUpdate();
        } catch (PSQLException e) {
            e.printStackTrace();
        }
    }

}
