package ua.kh.epam.service.captcha.storage;

import ua.kh.epam.service.captcha.CaptchaHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class CaptchaStorageService {

    private Map<String, CaptchaHolder> registrations = new HashMap<>();
    protected AtomicInteger atomicInteger = new AtomicInteger(100);

    public abstract void add(HttpServletRequest request, HttpServletResponse response, String captchaValueCode);

    public String get(HttpServletRequest request) {
        String captchaValueCode = getCaptchaValueCode(request);

        return registrations.get(captchaValueCode).getCaptchaValue();
    }

    public long getCaptchaGenerationTime(HttpServletRequest request) {
        String captchaValueCode = getCaptchaValueCode(request);

        return registrations.get(captchaValueCode).getCaptchaGenerationTime();
    }

    public abstract void remove(HttpServletRequest request);

    protected abstract String getCaptchaValueCode(HttpServletRequest request);

    protected void updateRegistrations(String captchaValue, String captchaValueCode) {
        long captchaGenerationTime = System.currentTimeMillis();
        CaptchaHolder captchaHolder = new CaptchaHolder(captchaValue, captchaValueCode, captchaGenerationTime);

        registrations.put(captchaValueCode, captchaHolder);
    }

    protected void removeFromRegistrations(HttpServletRequest request) {
        String captchaValueCode = getCaptchaValueCode(request);

        registrations.remove(captchaValueCode);
    }

}
