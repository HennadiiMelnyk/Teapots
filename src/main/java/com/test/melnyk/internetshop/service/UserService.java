package com.test.melnyk.internetshop.service;

import com.test.melnyk.internetshop.exception.UserNotFoundException;
import com.test.melnyk.internetshop.model.FormBean;
import com.test.melnyk.internetshop.model.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    User getUserByLogin(String login, HttpServletRequest httpServletRequest) throws UserNotFoundException;

    boolean isUserExist(String login);

    boolean createUser(String login, User user);

    boolean login(String login, String password, HttpServletRequest httpServletRequest);

    FormBean extractParams(HttpServletRequest httpServletRequest);

    User transformFromFormBeanToUser(FormBean formBean);

    String getUserRoleByLogin(String login);
}

