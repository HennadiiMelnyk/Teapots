package com.test.melnyk.internetshop.service;

import com.test.melnyk.internetshop.cache.UserCacheProxy;
import com.test.melnyk.internetshop.dao.entitydao.UserDao;
import com.test.melnyk.internetshop.dao.entitydao.impl.UserDaoImpl;
import com.test.melnyk.internetshop.model.User;
import com.test.melnyk.internetshop.service.impl.UserServiceImpl;
import com.test.melnyk.internetshop.transaction.TransactionManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final String TEST_LOGIN = "hmelnyk";
    @Mock
    TransactionManager transactionManager;
    private UserService userService;
    private User user;
    @Mock
    private UserCacheProxy userCacheProxy;
private UserDao userDao;
    @Before
    public void setUp() {
        userDao = new UserDaoImpl();
        userService = new UserServiceImpl(transactionManager, userCacheProxy);
        user = new User(1, "hennadii", "melnyk", "hmelnyk", "fffF12345", "hennadii.melnyk@gmail.com", "fffff.jpg");
    }

    //@Test
    public void checkIfTheUserExistWithThisLoginTest() {
        when(userCacheProxy.isUserExist(TEST_LOGIN)).thenReturn(true);
        when(transactionManager.doTransaction(userDao::findAll)).thenReturn(anyList());
        userService.isUserExist(TEST_LOGIN);
        verify(transactionManager).doTransaction(userDao::findAll);
        verify(userCacheProxy).isUserExist(TEST_LOGIN);
    }
}