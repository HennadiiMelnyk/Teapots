<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
   <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="css/styles.css">
<script type="text/javascript" src="/js/add.js"></script>
<script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
    </head>
    <body>
    <%@ taglib prefix="login" tagdir="/WEB-INF/tags" %>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Teapot Shop</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto" >

                <li class="nav-item active">
                    <a class="nav-link" href="/">Home Page <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="registration">Sign Up</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="items">Teapots</a>

                </li>
                <li class="nav-item">
                    <a class="nav-link" href="">Personal Account</a>

                </li>
                <li class="nav-item">
            <a class="nav-link" href="cart"><img src="images/cart.png" width="30" height="30" > Cart</a>
            </li>

                    <li class="nav-item">
                        <login:login />
                </li>

            </ul>
        </div>
    </nav>
<div class="container">
<div class="row">
    <form action="buy" method="post">

        <div class="input-field col s6">
            <input id="card" name="card" type="text" class="validate">
          <label for="card" id="card_error">Enter card number</label>
         </div>
         <label class="error-label" id="card_error_message"></label>
        </div>

           <div class="input-field col s8" display=" inline;">
             <input id="cvv" name="cvv" type="cvv" class="validate">
             <label for="cvv" id="cvv_error">Enter you CVV code</label>
           </div>
           <label class="error-label" id="cvv_error_message"></label>

           <div class="input-field col s8" display=" inline;">
             <input id="delivery" name="delivery" type="delivery" class="validate">
             <label for="delivery" id="cvv_error">enter delivery details</label>
           </div>
           <label class="error-label" id="cvv_error_message"></label>


             <button class="btn btn-primary mb-2" type="submit" name="action">Submit</button>

            <button class="btn btn-primary mb-2" type="submit" name="action">Back to cart</button>
            <button class="btn btn-primary mb-2" type="submit" name="action">Cancel order</button>
    </form>
    </div>
</body>
</html>