package ua.kh.epam.service.validator;

import ua.kh.epam.exception.Messages;
import ua.kh.epam.service.captcha.storage.CaptchaStorageService;
import ua.kh.epam.entity.User;
import ua.kh.epam.entity.UserRegistrationBean;
import ua.kh.epam.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InputValidator {

    private UserService userService;
    private CaptchaStorageService captchaStorageService;

    public InputValidator(UserService userService, CaptchaStorageService captchaStorageService) {
        this.userService = userService;
        this.captchaStorageService = captchaStorageService;
    }

    public Map<String, String> validateRegistration(HttpServletRequest request, UserRegistrationBean userBean) throws ServletException, IOException {
        Map<String, String> registrationErrors = new LinkedHashMap<>();

        boolean timeoutExpired = validateRegistrationTime(request);
        if (timeoutExpired) {
            registrationErrors.put("timeout=30s", Messages.EXCEPTION_REGISTRATION_TIMEOUT);
        } else {
            Map<String, String> userDataErrors = validateUser(userBean);
            registrationErrors.putAll(userDataErrors);

            boolean isCaptchaValid = validateCaptcha(request);
            if (!isCaptchaValid) {
                registrationErrors.put("captcha", Messages.EXCEPTION_CAPTCHA_WRONG);
            }
        }

        return registrationErrors;
    }

    private boolean validateRegistrationTime(HttpServletRequest request) throws ServletException, IOException {
        long registrationEndTime = System.currentTimeMillis();

        long registrationStartTime = captchaStorageService.getCaptchaGenerationTime(request);

        ServletContext servletContext = request.getServletContext();
        long registrationTimeout = (long) servletContext.getAttribute("registrationTimeout");

        return (registrationEndTime - registrationStartTime) > registrationTimeout;
    }

    private Map<String, String> validateUser(UserRegistrationBean userBean) {
        Map<String, String> userDataErrors = new HashMap<>();

        List<User> users = userService.get();
        for (User user : users) {
            String login = userBean.getLogin();
            if (user.getLogin().equals(login)) {
                userDataErrors.put(login, Messages.EXCEPTION_REGISTRATION_LOGIN_EXISTS);
            }

            String email = userBean.getEmail();
            if (user.getEmail().equals(email)) {
                userDataErrors.put(email, Messages.EXCEPTION_REGISTRATION_EMAIL_EXISTS);
            }

            String password = userBean.getPassword();
            if (user.getPassword().equals(password)) {
                userDataErrors.put("password", Messages.EXCEPTION_REGISTRATION_PASSWORD_EXISTS);
            }
        }

        return userDataErrors;
    }

    private boolean validateCaptcha(HttpServletRequest request) {
        String captchaInput = request.getParameter("registration_captcha");
        String captchaValue = captchaStorageService.get(request);

        return captchaInput.equals(captchaValue);
    }

}
