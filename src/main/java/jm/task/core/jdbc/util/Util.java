package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tableuser", "root", "Shikarin2022!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("EEEEEEs");
        return conn;
    }
}
