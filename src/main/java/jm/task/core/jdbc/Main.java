package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        //Util util = new Util();
        //Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        //userDao.createUsersTable();
        //userDao.saveUser("Name1", "LastName1", (byte) 1);
        //userDao.saveUser("Name2", "LastName2", (byte) 2);
        //userDao.saveUser("Name3", "LastName3", (byte) 3);
        //userDao.saveUser("Name5", "LastName4", (byte) 4);

        //userDao.removeUserById(14);
        //userDao.getAllUsers();
        userDao.cleanUsersTable();
        //userDao.dropUsersTable();
    }


}
