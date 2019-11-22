package com.test.melnyk.internetshop.model;

import java.util.Objects;

/**
 * User Model
 */
public class User implements Comparable<User> {

    private long id;
    private String username;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String logo;

    public User() {
    }

    public User(long id, String username, String surname, String login, String password, String email, String logo) {
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.logo = logo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                username.equals(user.username) &&
                surname.equals(user.surname) &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                email.equals(user.email) &&
                logo.equals(user.logo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, surname, login, password, email, logo);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }

    @Override
    public int compareTo(User o) {
        int result = this.getLogin().compareTo(o.getLogin());
        return result;
    }
}