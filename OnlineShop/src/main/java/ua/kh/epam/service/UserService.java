package ua.kh.epam.service;

import ua.kh.epam.dao.Operation;
import ua.kh.epam.dao.TransactionManager;
import ua.kh.epam.dao.memory.UserDAOImpl;
import ua.kh.epam.entity.User;
import ua.kh.epam.exception.DBException;

import java.sql.Connection;
import java.util.List;

public class UserService {

    private UserDAOImpl userDAO;

    private TransactionManager transactionManager;

    public UserService(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public void add(User user) {
        transactionManager.doTransaction(
                new Operation<Void>() {

                    @Override
                    public Void execute(Connection connection) {
                        userDAO.add(user);
                        return null;
                    }

                }
        );
    }

    public List<User> get() {
        return userDAO.get();
    }

}
