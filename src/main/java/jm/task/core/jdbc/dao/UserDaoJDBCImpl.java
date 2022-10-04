package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() { //создать табл пользователей

    }

    public void dropUsersTable() { //удалить табл пользователей

    }

    public void saveUser(String name, String lastName, byte age) { //сохр пользователя

        String mysql = "INSERT INTO users (name, lastName, age) VALUES (name, lastName, age)";
        try {
            Statement statement = connection.createStatement();
            statement.execute(mysql);
            System.out.println("gggg");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) { //удалить юзера по ид

    }

    public List<User> getAllUsers() {
        return null;
    } //список всех адресов


    public void cleanUsersTable() { //чистая т ю

    }
}
