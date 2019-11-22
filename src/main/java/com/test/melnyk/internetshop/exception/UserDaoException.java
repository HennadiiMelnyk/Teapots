package com.test.melnyk.internetshop.exception;

/**
 * UserDaoException
 */
public class UserDaoException extends RuntimeException {
    /**
     * Specified message for every operation with database in UserDaoImpl
     *
     * @param message
     */
    public UserDaoException(String message) {
        super(message);
    }
}
