package com.test.melnyk.internetshop.servlet;

import com.test.melnyk.internetshop.cache.UserCacheProxy;
import com.test.melnyk.internetshop.service.UserService;
import com.test.melnyk.internetshop.transaction.ConnectionManager;
import com.test.melnyk.internetshop.transaction.TransactionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.test.melnyk.internetshop.consts.Const.CONNECTION_MANAGER;
import static com.test.melnyk.internetshop.consts.Const.ERROR_MAP;
import static com.test.melnyk.internetshop.consts.Const.LOGIN;
import static com.test.melnyk.internetshop.consts.Const.PASSWORD;
import static com.test.melnyk.internetshop.consts.Const.TRANSACTION_MANAGER;
import static com.test.melnyk.internetshop.consts.Const.USER_CACHE_PROXY;
import static com.test.melnyk.internetshop.consts.Const.USER_SERVICE;
import static com.test.melnyk.internetshop.consts.Path.INDEX_PAGE;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    ConnectionManager connectionManager;
    TransactionManager transactionManager;
    UserCacheProxy userCacheProxy;
    private UserService userService;
    private Map<String, String> errors;

    @Override
    public void init() throws ServletException {
        connectionManager = (ConnectionManager) getServletContext().getAttribute(CONNECTION_MANAGER);
        transactionManager = (TransactionManager) getServletContext().getAttribute(TRANSACTION_MANAGER);
        userCacheProxy = (UserCacheProxy) getServletContext().getAttribute(USER_CACHE_PROXY);
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("logout".equals(req.getParameter("action"))) {
            req.getSession().invalidate();
            req.getServletContext().setAttribute("userRole", "guest");
            resp.sendRedirect(INDEX_PAGE);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter(PASSWORD);
        String login = req.getParameter(LOGIN);
        if (userService.login(login, password, req)) {
            req.getServletContext().setAttribute("userRole", userService.getUserRoleByLogin(login));
           // resp.sendRedirect("filter");
            req.getRequestDispatcher(INDEX_PAGE).forward(req, resp);
        } else {
            errors = (Map<String, String>) req.getAttribute(ERROR_MAP);
            req.setAttribute(ERROR_MAP, errors);
            req.getRequestDispatcher(INDEX_PAGE).forward(req, resp);
        }
    }
}

