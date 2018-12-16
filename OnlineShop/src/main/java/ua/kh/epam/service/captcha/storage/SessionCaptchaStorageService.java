package ua.kh.epam.service.captcha.storage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionCaptchaStorageService extends CaptchaStorageService {

    @Override
    public void add(HttpServletRequest request, HttpServletResponse response, String captchaValue) {
        HttpSession session = request.getSession();

        String captchaValueCode = String.valueOf(atomicInteger.incrementAndGet());
        session.setAttribute("captchaValueCode", captchaValueCode);

        updateRegistrations(captchaValue, captchaValueCode);
    }

    @Override
    public String getCaptchaValueCode(HttpServletRequest request) {
        HttpSession session = request.getSession();

        return (String) session.getAttribute("captchaValueCode");
    }

    @Override
    public void remove(HttpServletRequest request) {
        removeFromRegistrations(request);

        request.getSession().removeAttribute("captchaValueCode");
    }

}
