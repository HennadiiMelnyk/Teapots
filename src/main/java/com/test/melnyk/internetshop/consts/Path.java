package com.test.melnyk.internetshop.consts;

public final class Path {

    public static final String INDEX_PAGE = "index.jsp";
    public static final String LOGIN_PAGE = "WEB-INF/jsp/login.jsp";
    public static final String ITEM_PAGE = "WEB-INF/jsp/item.jsp";
    public static final String CART_PAGE = "WEB-INF/jsp/cart.jsp";
    public static final String REGISTRATION_PAGE = "WEB-INF/jsp/registration.jsp";
    public static final String LOGO_DIRECTORY = System.getProperty( "catalina.base" ) + "/images";
    private Path() {
    }
}
