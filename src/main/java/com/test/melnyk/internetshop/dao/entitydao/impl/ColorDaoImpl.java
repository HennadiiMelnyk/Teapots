package com.test.melnyk.internetshop.dao.entitydao.impl;

import com.test.melnyk.internetshop.dao.CrudDao;
import com.test.melnyk.internetshop.model.product.Color;
import com.test.melnyk.internetshop.transaction.ConnectionHolder;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

public class ColorDaoImpl implements CrudDao<Color> {
    private static final Logger LOGGER = Logger.getLogger(ColorDaoImpl.class);
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private PropertyResourceBundle propertyResourceBundle;

    public ColorDaoImpl(PropertyResourceBundle propertyResourceBundle) {
        this.propertyResourceBundle = propertyResourceBundle;
    }

    @Override
    public Color select(int id) {
        return null;
    }

    @Override
    public boolean create(Color color) {
        return false;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Color update(Color color) {
        return null;
    }

    @Override
    public List<Color> findAll() {
        List<Color> colors = new ArrayList<>();
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try {
            preparedStatement = connection.prepareStatement(propertyResourceBundle.getString("SQL_GET_ALL_COLORS"));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                colors.add(extractColor(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colors;
    }

    private Color extractColor(ResultSet resultSet) throws SQLException {
        Color color = new Color();
        color.setId(resultSet.getInt(1));
        color.setColor(resultSet.getString(2));
        return color;
    }
}
