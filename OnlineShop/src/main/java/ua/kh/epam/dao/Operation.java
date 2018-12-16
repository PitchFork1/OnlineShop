package ua.kh.epam.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface Operation<T> {

    T execute(Connection connection) throws SQLException;

}
