package ua.kh.epam.dao.db.memory;

import ua.kh.epam.dao.db.ProductDAO;
import ua.kh.epam.entity.Product;

import java.sql.Connection;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public void add(Connection connection, Product product) {

    }

    @Override
    public Product getById(Connection connection, Integer integer) {
        return null;
    }

    @Override
    public List<Product> getAll(Connection connection) {
        return null;
    }

    @Override
    public void remove(Connection connection, Integer integer) {

    }

    @Override
    public void update(Connection connection, Product product) {

    }

}
