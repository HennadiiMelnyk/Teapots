<%@ include file="/webapp/jspFragments/head.jspf" %>
<form action="/" method="post">
    <input type='hidden' name='command' id='command' value='login' />
    <div class="form-group">
        <label for="loginEmail">login</label>
        <input type="text" name='login' class="form-control" id="loginId" aria-describedby="LoginHelp" placeholder="Enter login">
    </div>
    <div class="form-group">
        <label for="loginPassword">Password</label>
        <input class="form-control" type="password" name='password'  id="loginPassword" placeholder="Password">
    </div>
    <div class="alert alert-danger" role="alert">
        ${requestScope.errorMap.login}
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<%@ include file="/jspFragments/footer.jspf" %>