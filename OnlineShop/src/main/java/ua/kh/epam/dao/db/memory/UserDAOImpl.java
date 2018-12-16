package ua.kh.epam.dao.db.memory;

import org.apache.log4j.Logger;
import ua.kh.epam.dao.db.UserDAO;
import ua.kh.epam.entity.User;
import ua.kh.epam.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.kh.epam.dao.db.memory.Fields.*;
import static ua.kh.epam.dao.db.memory.SQLQueriesConstants.*;
import static ua.kh.epam.exception.Messages.*;

public class UserDAOImpl implements UserDAO {

    private DAOManager daoManager;

    private final Logger LOGGER = Logger.getRootLogger();

    public UserDAOImpl() throws DBException {
        this.daoManager = DAOManager.getInstance();
    }

    @Override
    public void add(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_CREATE_USER);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getPassword());

            preparedStatement.executeUpdate();
        } finally {
            daoManager.close(preparedStatement);
            daoManager.close(connection);
        }
    }

    @Override
    public User getById(Connection connection, String login) throws DBException {
        User user = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_USER_BY_LOGIN);
            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                LOGGER.error(ERROR_CANNOT_OBTAIN_USER_BY_LOGIN);
            } else {
                user = extractUser(resultSet);
            }

            connection.commit();
        } catch (SQLException e) {
            daoManager.rollback(connection);
            LOGGER.error(ERROR_CANNOT_OBTAIN_USER_BY_LOGIN);
            throw new DBException(ERROR_CANNOT_OBTAIN_USER_BY_LOGIN, e);
        } finally {
            daoManager.close(resultSet, preparedStatement, connection);
        }
        return user;
    }

    @Override
    public List<User> getAll(Connection connection) throws DBException {
        List<User> users = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL_GET_ALL_USERS);
            if (!resultSet.next()) {
                LOGGER.error(ERROR_CANNOT_OBTAIN_USERS);
            } else {
                resultSet.previous();
                while (resultSet.next()) {
                    users.add(extractUser(resultSet));
                }
            }

            connection.commit();
        } catch (SQLException e) {
            daoManager.rollback(connection);
            LOGGER.error(ERROR_CANNOT_OBTAIN_USERS, e);
            throw new DBException(ERROR_CANNOT_OBTAIN_USERS, e);
        } finally {
            daoManager.close(resultSet, statement, connection);
        }
        return users;
    }

    @Override
    public void update(Connection connection, User user) throws DBException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());

            if (preparedStatement.executeUpdate() == 0) {
                LOGGER.error(ERROR_CANNOT_UPDATE_USER);
            }

            connection.commit();
        } catch (SQLException e) {
            daoManager.rollback(connection);
            LOGGER.error(ERROR_CANNOT_UPDATE_USER, e);
            throw new DBException(ERROR_CANNOT_UPDATE_USER, e);
        } finally {
            daoManager.close(connection);
            daoManager.close(preparedStatement);
        }
    }

    @Override
    public void remove(Connection connection, String login) throws DBException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setString(1, login);

            if (preparedStatement.executeUpdate() == 0) {
                LOGGER.error(ERROR_CANNOT_DELETE_USER);
            }

            connection.commit();
        } catch (SQLException e) {
            daoManager.rollback(connection);
            LOGGER.error(ERROR_CANNOT_DELETE_USER, e);
            throw new DBException(ERROR_CANNOT_DELETE_USER, e);
        } finally {
            daoManager.close(connection);
            daoManager.close(preparedStatement);
        }
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        String firstName = resultSet.getString(USER_FIRST_NAME);
        String lastName = resultSet.getString(USER_LAST_NAME);
        String login = resultSet.getString(USER_LOGIN);
        String email = resultSet.getString(USER_EMAIL);
        String password = resultSet.getString(USER_PASSWORD);

        return new User(firstName, lastName, login, email, password);
    }

}
