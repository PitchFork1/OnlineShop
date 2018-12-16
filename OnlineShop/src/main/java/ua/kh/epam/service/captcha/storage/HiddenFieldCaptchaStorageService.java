package ua.kh.epam.service.captcha.storage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HiddenFieldCaptchaStorageService extends CaptchaStorageService {

    @Override
    public void add(HttpServletRequest request, HttpServletResponse response, String captchaValue) {
        String captchaValueCode = String.valueOf(atomicInteger.incrementAndGet());

        request.setAttribute("captchaValueCode", captchaValueCode);

        updateRegistrations(captchaValue, captchaValueCode);
    }

    @Override
    public String getCaptchaValueCode(HttpServletRequest request) {
        return request.getParameter("captcha");
    }

    @Override
    public void remove(HttpServletRequest request) {
        removeFromRegistrations(request);

        request.removeAttribute("captchaValueCode");
    }

}
