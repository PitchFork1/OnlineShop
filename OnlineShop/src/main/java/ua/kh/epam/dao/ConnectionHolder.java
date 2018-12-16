package ua.kh.epam.dao;

import org.apache.log4j.Logger;
import ua.kh.epam.exception.DBException;
import ua.kh.epam.exception.Messages;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionHolder implements AutoCloseable {

    private Connection connection;

    private final Logger LOGGER = Logger.getRootLogger();

    public ConnectionHolder(Connection connection) {
        this.connection = connection;
    }

    public void rollback() {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.error(Messages.ERROR_CANNOT_ROLLBACK_TRANSACTION, ex);
            }
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOGGER.error(Messages.ERROR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

}
