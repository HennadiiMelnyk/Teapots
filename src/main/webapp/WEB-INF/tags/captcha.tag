<%@ tag language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="uuidCaptcha" required="true" %>
<img src="http://localhost:8080/captcha_id?uuid=${uuidCaptcha}">
<div class="input-field col s6">
	<input id="captcha_text" name="captcha_text" type="text">
	<input type="hidden" name="hidden_id" value="${uuidCaptcha}">
	<label for="captcha_text" id="captcha_error">Captcha</label>
</div>