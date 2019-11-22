package com.test.melnyk.internetshop.transaction;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static com.test.melnyk.internetshop.consts.Const.CANNOT_FIND_A_CONTEXT;
import static com.test.melnyk.internetshop.consts.Const.CANNOT_GET_CONNECTION;
import static com.test.melnyk.internetshop.consts.Const.JAVA_COMP_ENV_JDBC_INTERNETSHOP;

public class ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);
    private DataSource dataSource;

    public ConnectionManager() {
        try {
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup(JAVA_COMP_ENV_JDBC_INTERNETSHOP);
        } catch (NamingException e) {
            LOGGER.error(CANNOT_FIND_A_CONTEXT);
        }
    }


    public Connection getConnection() {
        try {
            return dataSource.getConnection();

        } catch (SQLException e) {
            LOGGER.error(CANNOT_GET_CONNECTION, e);
            throw new RuntimeException(CANNOT_GET_CONNECTION, e);
        }
    }
}
