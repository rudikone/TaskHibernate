package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.sql.*;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=UTC";
    private static final String USERNAME = "mudod";
    private static final String PASSWORD = "mudoda51";
//    private Driver driver;

//        try {
//            this.driver = new FabricMySQLDriver();
//            DriverManager.registerDriver(driver);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

    public static Connection getMyConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}
