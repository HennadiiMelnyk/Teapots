package com.test.melnyk.internetshop.dao;

import com.test.melnyk.internetshop.dao.entitydao.UserDao;
import com.test.melnyk.internetshop.dao.entitydao.impl.UserDaoImpl;
import com.test.melnyk.internetshop.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class UserDaoTest {

    private UserDao userDao;
    private Map<String, User> users;
    private User user ;

    @Before
    public void setUp(){
        userDao = new UserDaoImpl();
        users = new HashMap<>();
        user = new User(1,"hennadii", "melnyk", "hmelnyk", "fffF12345", "hennadii.melnyk@gmail.com","1.jpg");
        users.put("hmelnyk", user);
    }

    @Test
    public void getUserByLoginTest(){
        String expectedUserLogin = "hmelnyk";
        String key = "hmelnyk";
        String actual= users.get(key).getLogin();
        Assert.assertEquals(expectedUserLogin, actual);
    }
}