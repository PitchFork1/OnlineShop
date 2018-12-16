package ua.kh.epam.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import ua.kh.epam.dao.db.*;
import ua.kh.epam.dao.db.memory.ProductDAOImpl;
import ua.kh.epam.dao.db.memory.UserDAOImpl;
import ua.kh.epam.exception.DBException;
import ua.kh.epam.exception.Messages;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactory {

    private static final Logger LOGGER = Logger.getRootLogger();

    private static ComboPooledDataSource comboPooledDataSource;

    private static void initPoolConnections() throws DBException {
        if (comboPooledDataSource == null) {
            initPoolConnections();

            comboPooledDataSource = new ComboPooledDataSource();
            try {
                comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
                comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/online_shop_db?useSSL=false");
                comboPooledDataSource.setUser("root");
                comboPooledDataSource.setPassword("bydlocoder1");
                comboPooledDataSource.setMinPoolSize(3);
                comboPooledDataSource.setAcquireIncrement(1);
                comboPooledDataSource.setMaxPoolSize(300);
            } catch (PropertyVetoException e) {
                LOGGER.error(Messages.ERROR_CANNOT_INIT_POOL_CONNECTIONS);
                throw new DBException(Messages.ERROR_CANNOT_INIT_POOL_CONNECTIONS, e);
            }
        }
    }

    private Connection getConnection() throws DBException {
        initPoolConnections();
        Connection connection;
        try {
            connection = comboPooledDataSource.getConnection();
        } catch (SQLException ex) {
            LOGGER.error(Messages.ERROR_CANNOT_OBTAIN_CONNECTION, ex);
            throw new DBException(Messages.ERROR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return connection;
    }

    public ua.kh.epam.dao.db.UserDAO getUserDAO() throws DBException {
        return new UserDAOImpl();
    }

    public ProductDAO getProductDAO() throws DBException {
        return new ProductDAOImpl();
    }

}
