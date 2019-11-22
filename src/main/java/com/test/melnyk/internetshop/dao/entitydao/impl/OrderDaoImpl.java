package com.test.melnyk.internetshop.dao.entitydao.impl;

import com.test.melnyk.internetshop.dao.entitydao.OrderDao;
import com.test.melnyk.internetshop.model.Order;
import com.test.melnyk.internetshop.model.OrderedItem;
import com.test.melnyk.internetshop.transaction.ConnectionHolder;
import com.test.melnyk.internetshop.util.SQLBuilder;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.PropertyResourceBundle;

public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private PropertyResourceBundle propertyResourceBundle;
    private SQLBuilder sqlBuilder;

    public OrderDaoImpl(PropertyResourceBundle propertyResourceBundle) {
        this.propertyResourceBundle = propertyResourceBundle;
    }

    @Override
    public Order select(int id) {
        return null;
    }

    @Override
    public boolean create(Order order) {
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try {
            preparedStatement = connection.prepareStatement(propertyResourceBundle.getString("INSERT_NEW_ORDER"), Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            preparedStatement.setString(k++, String.valueOf(order.getOrderStatus()));
            preparedStatement.setString(k++, order.getDetails());
            preparedStatement.setTimestamp(k++, Timestamp.valueOf(order.getLocalDateTime()));
            preparedStatement.setLong(k, order.getCustomer().getId());
            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            }
            insertOrderedItems(connection, order);
        } catch (SQLException e) {
            LOGGER.error("Cannot create an order", e);
        }
        return false;
    }

    private void insertOrderedItems(Connection connection, Order order) {
        List<OrderedItem> orderedItems = order.getOrderedItem();
        try {
            preparedStatement = connection.prepareStatement(propertyResourceBundle.getString("INSERT_NEW_ORDERED_ITEM"));
            int k = 1;
            for (OrderedItem orderedItem : orderedItems) {
                preparedStatement.setInt(k++, orderedItem.getItem().getId());
                preparedStatement.setInt(k++, order.getId());
                preparedStatement.setInt(k++, orderedItem.getQuantity());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }
}
