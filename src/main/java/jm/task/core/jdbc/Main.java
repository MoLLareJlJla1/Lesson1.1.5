package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Sergei", "Safronov", (byte) 39);
        us.saveUser("Sergei", "Safronov", (byte) 39);
        us.saveUser("Sergei", "Safronov", (byte) 39);
        us.removeUserById(2);

    }
}
