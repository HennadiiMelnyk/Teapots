package com.test.melnyk.internetshop.servlet;

import com.test.melnyk.internetshop.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cheque")
public class ChequeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Order order = (Order) httpServletRequest.getSession().getAttribute("order");
        httpServletRequest.getSession().setAttribute("orderedItems", order.getOrderedItem());
        httpServletRequest.getSession().setAttribute("userCred", order.getCustomer());
        httpServletRequest.getRequestDispatcher("WEB-INF/jsp/cheque.jsp").forward(httpServletRequest, httpServletResponse);
    }
}