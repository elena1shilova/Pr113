package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static void main(String[] args) {
        getConnection();
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tableuser", "root", "Shikarin2022!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // работа с базой данных
        System.out.println("EEEEEEs");
        return conn;
    }

}
