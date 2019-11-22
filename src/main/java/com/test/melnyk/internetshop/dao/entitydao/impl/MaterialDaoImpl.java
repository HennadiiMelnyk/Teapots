package com.test.melnyk.internetshop.dao.entitydao.impl;

import com.test.melnyk.internetshop.dao.CrudDao;
import com.test.melnyk.internetshop.model.product.Material;
import com.test.melnyk.internetshop.transaction.ConnectionHolder;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

public class MaterialDaoImpl implements CrudDao<Material> {
    private static final Logger LOGGER = Logger.getLogger(MaterialDaoImpl.class);
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private PropertyResourceBundle propertyResourceBundle;


    public MaterialDaoImpl(PropertyResourceBundle propertyResourceBundle) {
        this.propertyResourceBundle = propertyResourceBundle;
    }

    @Override
    public Material select(int id) {
        return null;
    }

    @Override
    public boolean create(Material material) {
        return false;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Material update(Material material) {
        return null;
    }

    @Override
    public List<Material> findAll() {
        List<Material> materials = new ArrayList<>();
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try {
            preparedStatement = connection.prepareStatement(propertyResourceBundle.getString("SQL_GET_ALL_MATERIALS"));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                materials.add(extractMaterial(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materials;
    }

    private Material extractMaterial(ResultSet resultset) throws SQLException {
        Material material = new Material();
        material.setId(resultset.getInt(1));
        material.setMaterial(resultset.getString(2));
        return material;
    }
}
