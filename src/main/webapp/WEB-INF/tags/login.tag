<%@ tag language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<ct:if est="${sessionScope.user == null}">
<form action="/login" method="POST" class="form-inline">
<div class="form-group mx-sm-3 mb-2">
<input type="text" class="form-control" name="login" id="loginId" placeholder="Login">
 </div>
 <div class="form-group mx-sm-3 mb-2">
<input type="password" class="form-control" name="password" placeholder="Password">
</div>
<button class="btn btn-primary mb-2" type="submit" style="margin-left: 10px;">Submit</button>
<div style="background-color:red">
 ${requestScope.errorMap.login}
</div>
 </form>

</c:if>
<c:if test="${sessionScope.user != null}">
    <form action="/login" method="get">
    <img src="/logo" width="42" height="42" style="border-radius: 50%;">
        <input type='hidden' name='action' id='logoutId' value='logout' />
        <b>Welcome ${sessionScope.user.surname}</b>
        <button type="submit" class="btn btn-primary mb-2">Logout</button>
    </form>
</c:if>