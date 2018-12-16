package ua.kh.epam.service.captcha.storage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookiesCaptchaStorageService extends CaptchaStorageService {

    @Override
    public void add(HttpServletRequest request, HttpServletResponse response, String captchaValue) {
        String captchaValueCode = String.valueOf(atomicInteger.incrementAndGet());

        Cookie captchaCookie = new Cookie("captchaValueCode", captchaValueCode);

        response.addCookie(captchaCookie);

        updateRegistrations(captchaValue, captchaValueCode);
    }

    @Override
    public String getCaptchaValueCode(HttpServletRequest request) {
        String captchaValueCode = "";

        for (Cookie cookie : request.getCookies()) {
            if ("captchaValueCode".equals(cookie.getName())) {
                captchaValueCode = cookie.getValue();
                break;
            }
        }

        return captchaValueCode;
    }

    @Override
    public void remove(HttpServletRequest request) {
        removeFromRegistrations(request);

        for (Cookie cookie : request.getCookies()) {
            if ("captchaValueCode".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                break;
            }
        }
    }

}
