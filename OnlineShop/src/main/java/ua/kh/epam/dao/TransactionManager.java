package ua.kh.epam.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import ua.kh.epam.exception.DBException;
import ua.kh.epam.exception.Messages;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    private ComboPooledDataSource comboPooledDataSource;

    public TransactionManager() {
        this.comboPooledDataSource = new ComboPooledDataSource();
    }

    private final Logger LOGGER = Logger.getRootLogger();

    public <T> T doTransaction(Operation<T> operation) {
        T result = null;

        Connection connection = getConnection();
        ConnectionHolder connectionHolder = new ConnectionHolder(connection);
        try {
            result = operation.execute(connection);
        } catch (SQLException e) {
            processSQLException(connectionHolder, operation, e);
        } finally {
            connectionHolder.close();
        }

        return result;
    }

    private void processSQLException(ConnectionHolder connectionHolder, Operation operation, SQLException e) {
        connectionHolder.rollback();
        LOGGER.error("" + operation.toString(), e);
        try {
            throw new DBException("", e);
        } catch (DBException e1) {

        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = comboPooledDataSource.getConnection();
        } catch (SQLException ex) {
            LOGGER.error(Messages.ERROR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return connection;
    }

}
