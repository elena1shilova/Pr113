package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    public static void main(String[] args) {
        getConnection();
    }

    public static void getConnection() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tableuser", "root", "Shikarin2022!")){
            // работа с базой данных
            System.out.println("EEEEEEs");
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
