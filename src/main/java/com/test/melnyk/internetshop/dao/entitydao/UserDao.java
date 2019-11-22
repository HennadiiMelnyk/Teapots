package com.test.melnyk.internetshop.dao.entitydao;

import com.test.melnyk.internetshop.model.User;

import java.util.List;

public interface UserDao {

    User getUserByLogin(String login);

    boolean createUser(String login, User user);

    /**
     * get List parametrized Object
     *
     * @return list of Users
     */
    List<User> findAll();

    String getUserRoleByLogin(String login);

}
