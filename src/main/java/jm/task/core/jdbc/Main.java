package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        final UserService userService = new UserServiceImpl();
        final String testName = "Ivan";
        final String testLastName = "Ivanov";
        final byte testAge = 5;
        userService.dropUsersTable();//удаление т
        userService.dropUsersTable();
        userService.createUsersTable();//добавление т
        userService.createUsersTable();
        userService.saveUser(testName, testLastName, testAge);
        User user = userService.getAllUsers().get(0);
        if (!testName.equals(user.getName())
                || !testLastName.equals(user.getLastName())
                || testAge != user.getAge()
        ) {
            System.out.println("User был некорректно добавлен в базу данных");
        }
        userService.removeUserById(1);
        //System.out.println(testAge == user.getAge());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }


}
