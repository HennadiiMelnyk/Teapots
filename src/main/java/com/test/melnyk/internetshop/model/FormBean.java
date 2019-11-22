package com.test.melnyk.internetshop.model;

import java.util.Objects;

public class FormBean {

    private String userName;
    private String surname;
    private String login;
    private String email;
    private String password;
    private String confirmPassword;
    private String logo;

    public FormBean() {
    }


    public FormBean(String userName, String surname, String login, String email, String password, String confirmPassword, String logo) {
        this.userName = userName;
        this.surname = surname;
        this.login = login;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.logo = logo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
        FormBean fromBean = (FormBean) o;
        return userName.equals(fromBean.userName) &&
                surname.equals(fromBean.surname) &&
                login.equals(fromBean.login) &&
                email.equals(fromBean.email) &&
                password.equals(fromBean.password) &&
                confirmPassword.equals(fromBean.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, surname, login, email, password, confirmPassword);
    }

    @Override
    public String toString() {
        return "FormBean{" +
                "userName='" + userName + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}

