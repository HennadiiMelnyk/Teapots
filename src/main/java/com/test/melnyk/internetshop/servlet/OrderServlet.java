package com.test.melnyk.internetshop.servlet;

import com.test.melnyk.internetshop.model.Item;
import com.test.melnyk.internetshop.model.Order;
import com.test.melnyk.internetshop.model.User;
import com.test.melnyk.internetshop.service.CartService;
import com.test.melnyk.internetshop.service.OrderService;
import com.test.melnyk.internetshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/buy")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("WEB-INF/jsp/order.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        OrderService orderService = (OrderService) httpServletRequest.getServletContext().getAttribute("orderService");
        UserService userService = (UserService) httpServletRequest.getServletContext().getAttribute("userService");
        Map<Item, Integer> items = (Map<Item, Integer>) httpServletRequest.getSession().getAttribute("cartItems");
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        Order order = orderService.getOrder(user, items);
        orderService.insertNewOrder(order);
        httpServletRequest.getSession().setAttribute("order", order);
        CartService cartService = (CartService) httpServletRequest.getServletContext().getAttribute("cartService");
        cartService.removeBatch(order.getOrderedItem());
        httpServletResponse.sendRedirect("/cheque");
    }
}
