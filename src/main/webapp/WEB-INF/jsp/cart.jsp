<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="login" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="icon" href="#">
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="/css/materialize.min.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="/css/cardsize.css"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
    <script type="text/javascript" src="js/cart.js"></script>
</head>
<body>
<header>
    <nav>
        <div class="nav-wrapper">
            <a href="/" class="brand-logo"><fmt:message key="teapots_cart" bundle="${lang}</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <login:login/>
            </ul>
        </div>
    </nav>
</header>
<table class="highlight">
    <thead>
    <tr>
        <th>Model</th>
        <th>weight</th>
        <th>Item Price</th>
        <th>Quantity</th>
        <th>Total price</th>
        <th>
            <button class="btn waves-effect waves-light red" onclick="removeAll()" type="submit" name="action">Clean
                cart
            </button>
        </th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${sessionScope.cartItems}" var="item">
        <tr id="${item.key.id}">
            <td>${item.key.name}</td>
            <td>${item.key.weight}</td>
            <td><input type="hidden" name="price" id="price" value="${item.key.price}"/>${item.key.price}</td>
            <td><input type="number" class="quantity" id="quantity${item.key.id}"
                       onchange="quantity(${item.key.id}, ${item.key.price})" name="quantity" value="${item.value}"/>
            </td>
            <td>
                <div id="totalItem${item.key.id}">${item.key.price * item.value}</div>
            </td>
            <td>
                <button class="btn waves-effect waves-light red" onclick="removeFromCart(${item.key.id})" type="submit"
                        name="action">Remove
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="cart" method="post">
    <button class="btn waves-effect waves-light teal" type="submit" name="action">Buy</button>
</form>
<div class="center" id="totalPrice"><fmt:message key="total_price" bundle="${lang} ${sessionScope.totalPrice}</div>
</body>
</html>
