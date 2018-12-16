package ua.kh.epam.dao.db;

import ua.kh.epam.exception.DBException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CRUDDAO<T, ID> {

    void add(Connection connection, T t) throws SQLException;

    T getById(Connection connection, ID id) throws DBException;

    List<T> getAll(Connection connection) throws DBException;

    void remove(Connection connection, ID id) throws DBException;

    void update(Connection connection, T t) throws DBException;

}
