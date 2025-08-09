package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mysql {

    private static final String Database = "sadIn";
    private static final String Username = "root";
    private static final String Password = "password";
    public static Connection connection = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database, Username, Password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String query) throws Exception {
        return connection.createStatement().executeQuery(query);
    }

    public static int executeUpdate(String query) throws Exception {
        return connection.createStatement().executeUpdate(query);
    }

}
