package ua.kh.epam.dao.memory;

import ua.kh.epam.dao.UserDAO;
import ua.kh.epam.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public List<User> get() {
        return Collections.unmodifiableList(users);
    }

}
