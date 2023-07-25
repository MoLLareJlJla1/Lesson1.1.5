package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.cleanUsersTable();
        userService.saveUser("sergei", "safronov", (byte) 30);
        userService.saveUser("igor", "igorev", (byte) 41);
        userService.saveUser("gfdgdf", "gfdgdf", (byte) 45);
        userService.removeUserById(1);
        List<User> users = userService.getAllUsers();
        System.out.println(users);


    }


}
