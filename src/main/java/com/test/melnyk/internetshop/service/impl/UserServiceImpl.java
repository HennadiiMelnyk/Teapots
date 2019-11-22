package com.test.melnyk.internetshop.service.impl;

import com.test.melnyk.internetshop.cache.UserCacheProxy;
import com.test.melnyk.internetshop.model.FormBean;
import com.test.melnyk.internetshop.model.User;
import com.test.melnyk.internetshop.service.UserService;
import com.test.melnyk.internetshop.transaction.TransactionManager;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.test.melnyk.internetshop.consts.Const.CONFIRM_PASSWORD;
import static com.test.melnyk.internetshop.consts.Const.EMAIL;
import static com.test.melnyk.internetshop.consts.Const.EMAIL_OR_PASSWORD_IF_NOT_CORRECT;
import static com.test.melnyk.internetshop.consts.Const.ERROR_MAP;
import static com.test.melnyk.internetshop.consts.Const.FILE;
import static com.test.melnyk.internetshop.consts.Const.LOGIN;
import static com.test.melnyk.internetshop.consts.Const.PASSWORD;
import static com.test.melnyk.internetshop.consts.Const.USER;
import static com.test.melnyk.internetshop.consts.Const.USER_NAME;
import static com.test.melnyk.internetshop.consts.Const.USER_SURNAME;
import static com.test.melnyk.internetshop.consts.Const.USER_WITH_THIS_LOGIN_DOES_NOT_EXIST;

public class UserServiceImpl implements UserService {

    private TransactionManager transactionManager;
    private UserCacheProxy userCacheProxy;

    public UserServiceImpl(TransactionManager transactionManager, UserCacheProxy userCacheProxy) {
        this.transactionManager = transactionManager;
        this.userCacheProxy = userCacheProxy;
    }

    @Override
    public User getUserByLogin(String login, HttpServletRequest httpServletRequest) {
        User user = userCacheProxy.getUserByLogin(login);
        if (user == null) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put(LOGIN, USER_WITH_THIS_LOGIN_DOES_NOT_EXIST);
            httpServletRequest.setAttribute(ERROR_MAP, errorMap);
        }
        return user;
    }

    @Override
    public boolean isUserExist(String login) {
        return transactionManager.doTransaction(() -> userCacheProxy.isUserExist(login));
    }

    @Override
    public boolean createUser(String login, User user) {
        return transactionManager.doTransaction(() -> userCacheProxy.createUser(login, user));
    }

    @Override
    public boolean login(String login, String password, HttpServletRequest httpServletRequest) {
        User user = getUserByLogin(login, httpServletRequest);
        return processLogin(user, login, password, httpServletRequest);
    }

    private boolean processLogin(User user, String login, String password, HttpServletRequest request) {
        if (user != null) {
            if (user.getPassword().equals(password)) {
                request.getSession().setAttribute(USER, user);
                return true;
            }
        }
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(LOGIN, EMAIL_OR_PASSWORD_IF_NOT_CORRECT);
        request.setAttribute(ERROR_MAP, errorMap);
        return false;
    }

    public FormBean extractParams(HttpServletRequest httpServletRequest) {
        String userName = httpServletRequest.getParameter(USER_NAME);
        String surname = httpServletRequest.getParameter(USER_SURNAME);
        String login = httpServletRequest.getParameter(LOGIN);
        String email = httpServletRequest.getParameter(EMAIL);
        String password = httpServletRequest.getParameter(PASSWORD);
        String logo = httpServletRequest.getParameter(FILE);
        String confirmationPassword = httpServletRequest.getParameter(CONFIRM_PASSWORD);
        return new FormBean(userName, surname, login, email, password, confirmationPassword, logo);
    }

    public User transformFromFormBeanToUser(FormBean formBean) {
        return new User(1, formBean.getUserName(), formBean.getSurname(), formBean.getLogin(), formBean.getPassword(), formBean.getEmail(), formBean.getLogo());
    }

    @Override
    public String getUserRoleByLogin(String login) {
        return transactionManager.doTransaction(()->userCacheProxy.getUserRoleByLogin(login));
    }
}

