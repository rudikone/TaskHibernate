package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        User user1 = new User("Ivan", "Ivanov", (byte) 35);
        User user2 = new User("Petr", "Petrov", (byte) 20);
        User user3 = new User("Alex", "Alexeev", (byte) 33);
        User user4 = new User("John", "Johnes", (byte) 35);

        userService.createUsersTable();
        userService.saveUser(user1.getName(),user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(),user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(),user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(),user4.getLastName(), user4.getAge());

        List<User> allUsers = userService.getAllUsers();
        allUsers.toString();

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
