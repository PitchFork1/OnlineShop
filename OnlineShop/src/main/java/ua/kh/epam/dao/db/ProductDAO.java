package ua.kh.epam.dao.db;

import ua.kh.epam.entity.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductDAO extends CRUDDAO<Product, Integer> {

    @Override
    void add(Connection connection, Product product);

    @Override
    Product getById(Connection connection, Integer integer);

    @Override
    List<Product> getAll(Connection connection);

    @Override
    void remove(Connection connection, Integer integer);

    @Override
    void update(Connection connection, Product product);

}
