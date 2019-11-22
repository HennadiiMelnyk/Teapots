package com.test.melnyk.internetshop.dao.entitydao.impl;

import com.test.melnyk.internetshop.dao.CrudDao;
import com.test.melnyk.internetshop.model.product.Type;
import com.test.melnyk.internetshop.transaction.ConnectionHolder;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

public class TypeDaoImpl implements CrudDao<Type> {
    private static final Logger LOGGER = Logger.getLogger(TypeDaoImpl.class);
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private PropertyResourceBundle propertyResourceBundle;

    public TypeDaoImpl(PropertyResourceBundle propertyResourceBundle) {
        this.propertyResourceBundle = propertyResourceBundle;
    }

    @Override
    public Type select(int id) {
        return null;
    }

    @Override
    public boolean create(Type type) {
        return false;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Type update(Type type) {
        return null;
    }

    @Override
    public List<Type> findAll() {
        List<Type> types = new ArrayList<>();
        Connection connection = ConnectionHolder.getThreadLocal().get();

        try {
            preparedStatement = connection.prepareStatement(propertyResourceBundle.getString("SQL_GET_ALL_TYPES"));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                types.add(extractType(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;
    }

    private Type extractType(ResultSet resultSet) throws SQLException {
        Type type = new Type();
        type.setId(resultSet.getInt(1));
        type.setType(resultSet.getString(2));
        return type;
    }
}
