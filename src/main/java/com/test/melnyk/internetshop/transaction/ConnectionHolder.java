package com.test.melnyk.internetshop.transaction;

import java.sql.Connection;

public class ConnectionHolder {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static ThreadLocal<Connection> getThreadLocal() {
        return threadLocal;
    }

    public Connection get(){
        return threadLocal.get();
    }

    public void set(Connection connection){
        set(connection);
    }
}
