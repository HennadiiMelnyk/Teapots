<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="Teapot-store" uri="teapotstore" %>
<%@ taglib prefix="captcha" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Form Validation Example</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.js"></script>

</head>

<body>
    <div class="container-fluid py-3">
        <div class="row">
            <div class="col-md-6 mx-auto">
            <c:forEach items="${errors}" var="error">
                        <h6 style = "color: #800000">${error.value} </h6>

            </c:forEach>
                <form action="/registration" id="registration-form" method="post" type="post" enctype="multipart/form-data" novalidate>
                    <h3 class="text-center mb-4">Sign-up</h3>
                    <fieldset>
                        <div class="form-group">
                            <input class="form-control input-lg" placeholder="User Name" id="user_name" name="userName" value="${formBean.userName}"
                                type="text">
                            <div class="invalid-feedback"></div>
                        </div>

                        <div class="form-group">
                            <input class="form-control input-lg" placeholder="Surname" id="user_surname" name="userSurname" value="${formBean.surname}"
                                type="text">
                            <div class="invalid-feedback"></div>
                        </div>

                        <div class="form-group">
                                <input class="form-control input-lg" placeholder="Login" id="user_login" name="login"
                                    type="text">
                                <div class="invalid-feedback"></div>
                            </div>

                        <div class="form-group">
                            <input class="form-control input-lg" placeholder="E-mail Address" id="user_email" value="${formBean.email}"
                                name="email" type="text">
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-group">
                            <input class="form-control input-lg" placeholder="Password" id="user_password"
                                name="password" type="password">
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-group">
                            <input class="form-control input-lg" placeholder="Confirm Password" id="confirm_password" name="confirmPassword"
                                type="password">
                            <div class="invalid-feedback"></div>
                        </div>
                       <captcha:captcha uuidCaptcha="${sessionScope.uuid}"/>

                        <div class="form-group">
                            <div class="form-check">
                                <input class="form-check-input" name="terms" id="terms-checkbox" type="checkbox" value="${terms}">
                                <label class="form-check-label" for="terms-checkbox">
                                    Agree to terms and conditions
                                </label>
                                <div class="invalid-feedback"></div>
                            </div>
                        </div>
                        <input type="file" name="file" size="50"/>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
        <script src="js/const.js"></script>
        <script src="js/validation-form.js"></script>
</body>

</html>