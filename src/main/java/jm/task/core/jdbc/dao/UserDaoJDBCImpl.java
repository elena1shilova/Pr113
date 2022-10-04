package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
            //preparedStatement.executeUpdate();
            System.out.println(preparedStatement.executeUpdate());//убрать печать перед отправкой!!!!!!!!!
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
    }

    public void removeUserById(long id) throws SQLException { //удалить юзера по ид
        PreparedStatement preparedStatement = null;
        String mysql = "DELETE FROM users WHERE ID = ?";
        try {
            preparedStatement = getConnection().prepareStatement(mysql);
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement.executeUpdate());;
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
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String mysql = "SELECT * FROM users";
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
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
                statement.close();
            }
            if (getConnection() != null) {
                getConnection().close();
            }
        }
        //System.out.println(Arrays.toString(userList.toArray()));
        return userList;
    } //список всех адресов


    public void cleanUsersTable() throws SQLException { //чистая т ю
        PreparedStatement preparedStatement = null;
        String mysql = "TRUNCATE users";
        try {
            preparedStatement = getConnection().prepareStatement(mysql);
            //preparedStatement.setLong();
            System.out.println(preparedStatement.executeUpdate());;
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
    }
}
