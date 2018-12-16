package ua.kh.epam.dao;

import ua.kh.epam.entity.User;

import java.util.List;

public interface UserDAO {

    void add(User user);

    List<User> get();

}
