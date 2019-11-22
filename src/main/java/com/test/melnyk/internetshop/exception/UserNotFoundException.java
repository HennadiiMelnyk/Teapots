package com.test.melnyk.internetshop.exception;

/**
 * UserNotFoundException
 * occur if user with specific login not found
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }
}
