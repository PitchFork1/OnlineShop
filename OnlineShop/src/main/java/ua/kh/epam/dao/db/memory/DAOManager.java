package ua.kh.epam.dao.db.memory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import ua.kh.epam.dao.db.ProductDAO;
import ua.kh.epam.exception.DBException;
import ua.kh.epam.exception.Messages;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOManager {

    private static DAOManager daoManagerInstance;

    private final Logger LOGGER = Logger.getRootLogger();

    private ComboPooledDataSource comboPooledDataSource;

    public static DAOManager getInstance() throws DBException {
        return daoManagerInstance == null ? new DAOManager() : daoManagerInstance;
    }

    private DAOManager() throws DBException {
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

    public Connection getConnection() throws DBException {
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

    public void rollback(Connection con) throws DBException {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOGGER.error(Messages.ERROR_CANNOT_ROLLBACK_TRANSACTION, ex);
                throw new DBException(Messages.ERROR_CANNOT_ROLLBACK_TRANSACTION, ex);
            }
        }
    }

    public void close(Connection con) throws DBException {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOGGER.error(Messages.ERROR_CANNOT_CLOSE_CONNECTION, ex);
                throw new DBException(Messages.ERROR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    public void close(Statement stmt) throws DBException {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOGGER.error(Messages.ERROR_CANNOT_CLOSE_STATEMENT, ex);
                throw new DBException(Messages.ERROR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }

    public void close(ResultSet rs) throws DBException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOGGER.error(Messages.ERROR_CANNOT_CLOSE_RESULT_SET, ex);
                throw new DBException(Messages.ERROR_CANNOT_CLOSE_RESULT_SET, ex);
            }
        }
    }

    public void close(ResultSet rs, Statement stmt, Connection con) throws DBException {
        close(rs);
        close(stmt);
        close(con);
    }

}
