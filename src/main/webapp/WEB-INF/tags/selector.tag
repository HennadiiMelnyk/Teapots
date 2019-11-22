<%@ tag language="java" isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<div class="dropdown">
<fmt:setBundle basename = "lang" var = "lang"/>
<fmt:message key="language" bundle="${lang}"/>
<div class="top_bar_menu">
    <ul class="standard_dropdown top_bar_dropdown">
           <a href="?lang=en">English</a>
           <a href="?lang=ru">Russian</a>

    </ul>
</div>
<div>
