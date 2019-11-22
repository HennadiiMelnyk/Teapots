package com.test.melnyk.internetshop.transaction;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Supplier;

public class TransactionManager {
    private static final Logger LOGGER = Logger.getLogger(TransactionManager.class);
    private ConnectionManager connectionManager;

    public TransactionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public <T> T doTransaction(Supplier<T> supplier) {
        Connection connection = connectionManager.getConnection();
        ConnectionHolder.getThreadLocal().set(connection);
        try {
            connection.setAutoCommit(false);
            T res = supplier.get();
            connection.commit();
            return res;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.error("fail to execute rollback", e);
                throw new RuntimeException(e);
            }
            LOGGER.error("fail to execute transaction", e);
            throw new RuntimeException(e);
        } finally {
            ConnectionHolder.getThreadLocal().remove();
            closeConnection(connection);
        }
    }


    /***
     * Close connection, handling SQLException
     * @param connection
     */
    private static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.info("cannot close a connection", e);
        }
    }
}
