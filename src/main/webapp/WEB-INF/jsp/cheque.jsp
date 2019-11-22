<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="login" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="icon" href="#">
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/cardsize.css"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
</head>
<body>
<header>
    <nav>
        <div class="nav-wrapper">
            <a href="/" class="brand-logo">Logo</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <login:login/>
            </ul>
        </div>
    </nav>
</header>
<div class="container">
<div class="row">
<div style="text-align:center;">
    Username: ${sessionScope.user.username}
    UserLastName: ${sessionScope.user.surname}
    Status: ${sessionScope.order.orderStatus}
    Details: ${sessionScope.order.details}
    Time: ${sessionScope.order.localDateTime}
    <c:forEach items="${sessionScope.orderedItems}" var="ordereditems">
        item name-> ${ordereditems.item.name}
        <br>
        order time-> ${ordereditems.item.price}
        <br>
       quantity-> ${ordereditems.quantity}
    </c:forEach>
    Username: ${sessionScope.user.username}
    </div>
</div>
</div>
</body>
</html>