<%@ tag isELIgnored="false" %>
<%@ attribute name ="captcha" required="true" %>
<input type="captcha" placeholder="Captcha" name="registration_captcha" id="registration_captcha"/>
<div class="incorrectData" id="captcha_error">Captcha must contain at least 6 characters!</div>
<img src="/view_captcha?captcha=${captchaValueCode}" alt="Captcha">
<br/>
<br/>
<c:if test="${not empty captchaValueCode}">
    <input type="hidden" name="captcha" value="${captchaValueCode}">
</c:if>
