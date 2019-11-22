<%@ tag language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:forEach items="${requestScope.pageItems}" var="item">

<div class="col-lg-4 col-md-6 mb-4" style="height:400px">
  <form action="/items">
  <div class="card h-100">
  
  <a href="/images/carousel-3.jpg"><img class="card-img-top" alt=""></a>
            <div class="card-body">
                <h4 class="card-title">
                    <a href="#">${item.name}</a>
                </h4>
                <h5>${item.price}$</h5>
                <h5>${item.weight}g</h5>
                <h5>${item.age}years</h5>
                <p class="card-text">${item.color} <br>${item.material} <br>${item.location} <br>${item.type}</p>
            </div>
             <div class="card-footer">

                            <input type="button" onclick="addToCart(${item.id})" class="buyButton" name="buyButton" value="BUY">
                        </div>
                        </div>
                         <script type="text/javascript" src="/js/add.js"></script>
</form>
</div>

</c:forEach>