package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    //Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() { //создать табл пользователей

    }

    public void dropUsersTable() { //удалить табл пользователей

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException { //сохр пользователя
        PreparedStatement preparedStatement = null;
        String mysql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try {
            preparedStatement = getConnection().prepareStatement(mysql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (getConnection() != null) {
                getConnection().close();
            }
        }
        /*String mysql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES (name, lastName, age)";
        Statement statement = null;
        try {
            statement = Util.getConnection().createStatement();
            int rows = statement.executeUpdate(mysql);
            //System.out.println("gggg");
            System.out.printf("Added %d rows", rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

    }

    public void removeUserById(long id) { //удалить юзера по ид

    }

    public List<User> getAllUsers() {
        return null;
    } //список всех адресов


    public void cleanUsersTable() { //чистая т ю

    }
}
