package org.example;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class DBHandler {

    //public static final String driver = "com.mysql.cj.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/atm";
    public static final String userName = "root";
    public static final String password = "root";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver() {
                @Override
                public Connection connect(String url, Properties info) throws SQLException {
                    return null;
                }

                @Override
                public boolean acceptsURL(String url) throws SQLException {
                    return false;
                }

                @Override
                public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
                    return new DriverPropertyInfo[0];
                }

                @Override
                public int getMajorVersion() {
                    return 0;
                }

                @Override
                public int getMinorVersion() {
                    return 0;
                }

                @Override
                public boolean jdbcCompliant() {
                    return false;
                }

                @Override
                public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                    return null;
                }
            });
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
    }

    public AccountModel extractUserFromResultSet(ResultSet rs) throws SQLException {
        AccountModel account = new AccountModel();

        account.setId(rs.getInt("idAccount"));
        account.setUserName(rs.getString("name"));
        account.setPassword(rs.getString("password"));
        account.setBalance(rs.getDouble("balance"));

        return account;
    }

    public AccountModel getUserByUserNameAndPassword(String name, String password) throws SQLException {
        Connection connection = DBHandler.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM account WHERE name=? AND password=?");
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public AccountModel getBalanceByName(String name){
        Connection connection = DBHandler.getConnection();
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM account WHERE name=" + name);

            if(rs.next()){
                return extractUserFromResultSet(rs);
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void updateBalance(int id, double balance) {
        String query = "UPDATE `atm`.`account` SET `balance` = '" + balance + "' WHERE (`idaccount` = '" + id + "');";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
