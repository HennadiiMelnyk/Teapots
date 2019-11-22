package com.test.melnyk.internetshop.cache;

import com.test.melnyk.internetshop.dao.entitydao.UserDao;
import com.test.melnyk.internetshop.model.User;
import com.test.melnyk.internetshop.transaction.ConnectionManager;
import com.test.melnyk.internetshop.transaction.TransactionManager;

import java.util.List;

/**
 * class which encapsulate cash storage for list of users
 */
public class UserCacheProxy implements UserDao {

    private List<User> userList;
    private UserDao userDao;
    private TransactionManager transactionManager;
    private ConnectionManager connectionManager;

    public UserCacheProxy(UserDao userDao, TransactionManager transactionManager, ConnectionManager connectionManager) {
        this.transactionManager = transactionManager;
        this.connectionManager = connectionManager;
        this.userDao = userDao;
        userList = transactionManager.doTransaction(userDao::findAll);
    }

    @Override
    public User getUserByLogin(String login) {
        User user = userList.stream().filter(user1 -> user1.getLogin().equals(login)).findFirst().orElse(null);
        return user;
    }

    public boolean isUserExist(String login) {
        this.userList = userDao.findAll();
        User user = getUserByLogin(login);
        return userList.contains(user);
    }

    @Override
    public boolean createUser(String login, User user) {
        if (!userDao.createUser(login, user)) {
            return false;
        } else {
            this.userList = userDao.findAll();
            return true;
        }
    }

    @Override
    public List<User> findAll() {
        return this.userList;
    }

    @Override
    public String getUserRoleByLogin(String login) {
        return  userDao.getUserRoleByLogin(login);
    }
}
