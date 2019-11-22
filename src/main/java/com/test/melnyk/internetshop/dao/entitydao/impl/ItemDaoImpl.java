package com.test.melnyk.internetshop.dao.entitydao.impl;

import com.test.melnyk.internetshop.dao.entitydao.ItemDao;
import com.test.melnyk.internetshop.model.Item;
import com.test.melnyk.internetshop.model.product.Color;
import com.test.melnyk.internetshop.model.product.Location;
import com.test.melnyk.internetshop.model.product.Material;
import com.test.melnyk.internetshop.model.product.Type;
import com.test.melnyk.internetshop.transaction.ConnectionHolder;
import com.test.melnyk.internetshop.util.SQLBuilder;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;

public class ItemDaoImpl implements ItemDao {
    private static final Logger LOGGER = Logger.getLogger(ItemDaoImpl.class);
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private PropertyResourceBundle propertyResourceBundle;
    private SQLBuilder sqlBuilder;

    public ItemDaoImpl(PropertyResourceBundle propertyResourceBundle, SQLBuilder sqlBuilder) {
        this.propertyResourceBundle = propertyResourceBundle;
        this.sqlBuilder = sqlBuilder;
    }

    @Override
    public Item select(int id) {
        Item item = null;
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try {
            preparedStatement = connection.prepareStatement((propertyResourceBundle.getString("SELECT_ITEM_BY_ID")));
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                item = extractItem(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean create(Item item) {
        return false;
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public Item update(Item item) {
        return null;
    }

    @Override
    public List<Item> findAll() {
        List<Item> itemList = new ArrayList<>();
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try {
            preparedStatement = connection.prepareStatement(propertyResourceBundle.getString("SQL_GET_ALL_ITEMS"));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                itemList.add(extractItem(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.info("cannot get a list of item", e);
        }
        return itemList;
    }

    public List<Item> getFilteredItems(int page, int perPage, Map<String, String[]> allFilters) {
        List<Item> filterList = new ArrayList<>();
        Connection connection = ConnectionHolder.getThreadLocal().get();
        String query = sqlBuilder.getFilteredItems(page, perPage, allFilters);
        try {
            preparedStatement = connection.prepareStatement(query);
            System.out.println(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                filterList.add(extractItem(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.info("error while filtering list ", e);
        }
        return filterList;
    }

    public int getNumberOfItems(Map parameters) {
        Connection connection = ConnectionHolder.getThreadLocal().get();
        int numberOfItems = 0;
        String query = sqlBuilder.getNumberOfFilteredItemsQuery(parameters);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                numberOfItems = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfItems;
    }

    private Item extractItem(ResultSet resultset) throws SQLException {
        Item item = new Item();
        item.setId(resultset.getInt(1));
        item.setName(resultset.getString(2));
        item.setPrice(resultset.getBigDecimal(3));
        item.setWeight(resultset.getInt(4));
        item.setAge(resultset.getInt(5));
        item.setLocation(new Location(resultset.getInt(6), resultset.getString(7)));
        item.setMaterial(new Material(resultset.getInt(8), resultset.getString(9)));
        item.setType(new Type(resultset.getInt(10), resultset.getString(11)));
        item.setColor(new Color(resultset.getInt(12), resultset.getString(13)));
        return item;
    }
}
