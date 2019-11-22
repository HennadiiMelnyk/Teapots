package com.test.melnyk.internetshop.servlet;

import com.test.melnyk.internetshop.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import static com.test.melnyk.internetshop.consts.Path.CART_PAGE;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        CartService cartService = (CartService) httpServletRequest.getServletContext().getAttribute("cartService");
        httpServletRequest.getSession().setAttribute("cartItems", cartService.getCartItems());
        httpServletRequest.getSession().setAttribute("totalPrice", cartService.getTotal());
        //httpServletResponse.sendRedirect("cart");
        httpServletRequest.getRequestDispatcher(CART_PAGE).forward(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        CartService cartService = (CartService) httpServletRequest.getServletContext().getAttribute("cartService");
        if (httpServletRequest.getParameter("id").equals("removeAll")) {
            cartService.clearCart();
            httpServletRequest.getSession().setAttribute("cartItems", cartService.getCartItems());
            httpServletRequest.getSession().setAttribute("totalPrice", cartService.getTotal());
        } else {
            int itemId = Integer.parseInt(httpServletRequest.getParameter("id"));
            cartService.removeItemById(itemId);
            httpServletRequest.getSession().setAttribute("cartItems", cartService.getCartItems());
            httpServletRequest.getSession().setAttribute("totalPrice", cartService.getTotal());
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.println("Total price: " + cartService.getTotal());
        }
    }

    @Override
    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        int itemId = Integer.parseInt(httpServletRequest.getParameter("id"));
        CartService cartService = (CartService) httpServletRequest.getServletContext().getAttribute("cartService");
        cartService.updateItemQuantity(itemId, Integer.parseInt(httpServletRequest.getParameter("quantity")));
        httpServletRequest.getSession().setAttribute("cartItems", cartService.getCartItems());
        BigDecimal totalPrice = cartService.getTotal();
        httpServletRequest.getSession().setAttribute("totalPrice", totalPrice);
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.println("Total price: " + totalPrice);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user") == null) {
            httpServletResponse.sendRedirect("/login");
        } else {
            httpServletResponse.sendRedirect("/buy");
        }
    }
}
