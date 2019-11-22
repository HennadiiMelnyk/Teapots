package com.test.melnyk.internetshop.servlet;

import com.test.melnyk.internetshop.cache.UserCacheProxy;
import com.test.melnyk.internetshop.model.FormBean;
import com.test.melnyk.internetshop.model.User;
import com.test.melnyk.internetshop.service.UserService;
import com.test.melnyk.internetshop.service.impl.UserServiceImpl;
import com.test.melnyk.internetshop.strategy.CaptchaStrategy;
import com.test.melnyk.internetshop.transaction.ConnectionManager;
import com.test.melnyk.internetshop.transaction.TransactionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_STRATEGY;
import static com.test.melnyk.internetshop.consts.Const.DUPLICATE_LOGIN;
import static com.test.melnyk.internetshop.consts.Const.ERRORS;
import static com.test.melnyk.internetshop.consts.Const.FORM_BEAN;
import static com.test.melnyk.internetshop.consts.Const.LOGIN_WITH_THE_SAME_NAME_ALREADY_OCCUPY;
import static com.test.melnyk.internetshop.consts.Const.STRATEGY_MAP;
import static com.test.melnyk.internetshop.consts.Const.UNIVERSALLY_UNIQUE_IDENTIFIER;
import static com.test.melnyk.internetshop.consts.Path.INDEX_PAGE;
import static com.test.melnyk.internetshop.consts.Path.LOGO_DIRECTORY;
import static com.test.melnyk.internetshop.consts.Path.REGISTRATION_PAGE;

@MultipartConfig
@WebServlet("/registration")
public class RegisterServlet extends HttpServlet {

    private UserService userService;
    private Map<String, String> errors;

    @Override
    public void init() {
        ConnectionManager connectionManager = new ConnectionManager();
        TransactionManager transactionManager = new TransactionManager(connectionManager);
        UserCacheProxy userCacheProxy = (UserCacheProxy) getServletContext().getAttribute("userCacheProxy");
        userService = new UserServiceImpl(transactionManager, userCacheProxy);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = java.util.UUID.randomUUID();
        req.getSession().setAttribute(UNIVERSALLY_UNIQUE_IDENTIFIER, uuid);
        req.getRequestDispatcher(REGISTRATION_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormBean formBean = userService.extractParams(req);
        errors = new HashMap<>();
        Map<String, CaptchaStrategy> map = (Map<String, CaptchaStrategy>) req.getServletContext().getAttribute(STRATEGY_MAP);
        CaptchaStrategy captchaStrategy = map.get(getServletContext().getInitParameter(CAPTCHA_STRATEGY));
        captchaStrategy.validate(errors, req);
        checkUserIsExist(formBean);
        if (errors.isEmpty()) {
            User user = userService.transformFromFormBeanToUser(formBean);
            userService.createUser(user.getLogin(), user);
            Part part = req.getPart("file");
            part.write(LOGO_DIRECTORY + user.getLogin() + ".jpg");
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(INDEX_PAGE);
        } else {
            req.setAttribute(ERRORS, errors);
            req.setAttribute(FORM_BEAN, formBean);
            req.getRequestDispatcher(REGISTRATION_PAGE).forward(req, resp);
        }
    }

    private void checkUserIsExist(FormBean formBean) {
        String login = formBean.getLogin();
        if (userService.isUserExist(login)) {
            errors.put(DUPLICATE_LOGIN, LOGIN_WITH_THE_SAME_NAME_ALREADY_OCCUPY);
        }
    }
}

