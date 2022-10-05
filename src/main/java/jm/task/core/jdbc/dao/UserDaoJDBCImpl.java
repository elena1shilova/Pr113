package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }
    public void createUsersTable() { //создать табл пользователей
        PreparedStatement preparedStatement = null;
        String mysql = "CREATE TABLE IF NOT EXISTS `tableuser`.`users` (\n" +
                "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `NAME` VARCHAR(45) NOT NULL,\n" +
                "  `LASTNAME` VARCHAR(45) NOT NULL,\n" +
                "  `AGE` INT NOT NULL,\n" +
                "        PRIMARY KEY (`ID`, `NAME`, `LASTNAME`, `AGE`))\n" +
                "        ENGINE = InnoDB\n" +
                "        DEFAULT CHARACTER SET = utf8";
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(mysql);
            preparedStatement.executeUpdate();
            System.out.println("Успех создания");
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Таблица уже существует");
            try {
                connection.rollback();
                preparedStatement.close();
                connection.close();
            } catch (Exception ignore) {
                e.printStackTrace();
            }
        }
    }
    public void dropUsersTable() { //удалить табл пользователей
        PreparedStatement preparedStatement = null;
        String mysql = "DROP TABLE IF EXISTS users";
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(mysql);
            preparedStatement.executeUpdate();
            System.out.println("Успех удаления");
            connection.commit();
        } catch (Throwable e) {
            System.out.println("Таблица не существует");
            try {
                preparedStatement.close();
                connection.close();
                connection.rollback();
            } catch (Exception ignore) {
                e.printStackTrace();
            }
        }
    }
    public void saveUser(String name, String lastName, byte age) { //сохр пользователя
        PreparedStatement preparedStatement = null;
        String mysql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(mysql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println("Успех добавления");
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Ошибка добавления");
            try {
                connection.rollback();
                preparedStatement.close();
                connection.close();
            } catch (Exception ignore) {
                e.printStackTrace();
            }
        }
    }
    public void removeUserById(long id) { //удалить юзера по ид
        PreparedStatement preparedStatement = null;
        String mysql = "DELETE FROM users WHERE ID = ?";
        try {
            preparedStatement = connection.prepareStatement(mysql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String mysql = "SELECT * FROM users";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(mysql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //System.out.println(Arrays.toString(userList.toArray()));
        return userList;
    } //список всех адресов


    public void cleanUsersTable() { //чистая т ю
        PreparedStatement preparedStatement = null;
        String mysql = "TRUNCATE TABLE users";
        try {
            preparedStatement = connection.prepareStatement(mysql);
            //preparedStatement.setLong();
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
