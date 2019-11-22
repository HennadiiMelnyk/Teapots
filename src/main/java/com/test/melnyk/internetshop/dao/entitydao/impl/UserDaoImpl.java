package com.test.melnyk.internetshop.dao.entitydao.impl;

import com.test.melnyk.internetshop.dao.entitydao.UserDao;
import com.test.melnyk.internetshop.exception.UserDaoException;
import com.test.melnyk.internetshop.model.User;
import com.test.melnyk.internetshop.transaction.ConnectionHolder;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;


public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private PropertyResourceBundle propertyResourceBundle;

    public UserDaoImpl(PropertyResourceBundle propertyResourceBundle) {
        this.propertyResourceBundle = propertyResourceBundle;
    }

    public UserDaoImpl() {

    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try {
            preparedStatement = connection.prepareStatement(propertyResourceBundle.getString("SQL_SELECT_USER_BY_LOGIN"));
            preparedStatement.setString(4, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException e) {

        }

        return user;
    }

    @Override
    public boolean createUser(String login, User user) {
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try {
            int columnIndex = 1;
            preparedStatement = connection.prepareStatement(propertyResourceBundle.getString("SQL_CREATE_USER"));
            preparedStatement.setString(columnIndex++, user.getUsername());
            preparedStatement.setString(columnIndex++, user.getSurname());
            preparedStatement.setString(columnIndex++, user.getLogin());
            preparedStatement.setString(columnIndex++, user.getPassword());
            preparedStatement.setString(columnIndex++, user.getEmail());
            preparedStatement.setString(columnIndex++, user.getLogo());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.info("cannot create a user", e);
            throw new UserDaoException("Error while creating a user");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.info("cannot close resources");
            }
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        Connection connection = ConnectionHolder.getThreadLocal().get();
        try {
            preparedStatement = connection.prepareStatement(propertyResourceBundle.getString("SQL_GET_ALL_USERS"));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(extractUser(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.info("cannot get list of Users", e);
            throw new UserDaoException("Error while getting list of Users");
        }
        return userList;
    }

    @Override
    public String getUserRoleByLogin(String login) {
        String role = null;
        Connection connection = ConnectionHolder.getThreadLocal().get();

        try {
            preparedStatement = connection.prepareStatement(propertyResourceBundle.getString("SQL_GET_USER_ROLE"));
            int k = 1;
            preparedStatement.setString(k, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = resultSet.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    /**
     * Get user from resultSet
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setUsername(resultSet.getString(2));
        user.setSurname(resultSet.getString(3));
        user.setLogin(resultSet.getString(4));
        user.setPassword(resultSet.getString(5));
        user.setEmail(resultSet.getString(6));
        user.setLogo(resultSet.getString(7));
        return user;
    }
}

