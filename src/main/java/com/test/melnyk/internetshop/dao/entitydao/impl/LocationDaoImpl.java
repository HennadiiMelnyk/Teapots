package com.test.melnyk.internetshop.dao.entitydao.impl;

import com.test.melnyk.internetshop.dao.CrudDao;
import com.test.melnyk.internetshop.model.product.Location;
import com.test.melnyk.internetshop.transaction.ConnectionHolder;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

public class LocationDaoImpl implements CrudDao<Location> {
    private static final Logger LOGGER = Logger.getLogger(LocationDaoImpl.class);
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private PropertyResourceBundle propertyResourceBundle;

    public LocationDaoImpl(PropertyResourceBundle propertyResourceBundle) {
        this.propertyResourceBundle = propertyResourceBundle;
    }

    @Override
    public Location select(int id) {
        return null;
    }

    @Override
    public boolean create(Location location) {
        return false;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Location update(Location location) {
        return null;
    }

    @Override
    public List<Location> findAll() {
        List<Location> locations = new ArrayList<>();
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try {
            preparedStatement = connection.prepareStatement(propertyResourceBundle.getString("SQL_GET_ALL_LOCATION"));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                locations.add(extractLocation(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    private Location extractLocation(ResultSet resultSet) throws SQLException {
        Location location = new Location();
        location.setId(resultSet.getInt(1));
        location.setLocation(resultSet.getString(2));
        return location;
    }
}
