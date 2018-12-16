package ua.kh.epam.web.servlet;

import ua.kh.epam.exception.Messages;
import ua.kh.epam.service.captcha.generator.CaptchaGenerator;
import ua.kh.epam.service.captcha.generator.SixDigitsCaptchaGeneratorImpl;
import ua.kh.epam.service.captcha.storage.CaptchaStorageService;
import ua.kh.epam.service.UserService;
import ua.kh.epam.service.validator.InputValidator;
import ua.kh.epam.entity.User;
import ua.kh.epam.entity.UserRegistrationBean;
import ua.kh.epam.web.Path;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/registration_servlet")
public class RegistrationServlet extends HttpServlet {

    private UserService userService;
    private CaptchaStorageService captchaStorageService;
    private CaptchaGenerator captchaGenerator;
    private InputValidator inputValidator;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
        captchaStorageService = (CaptchaStorageService) getServletContext().getAttribute("captchaStorageService");
        captchaGenerator = new SixDigitsCaptchaGeneratorImpl();
        inputValidator = new InputValidator(userService, captchaStorageService);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String captchaValue = captchaGenerator.generateCaptchaValue();

        captchaStorageService.add(request, response, captchaValue);

        request.getRequestDispatcher(Path.PAGE_REGISTRATION).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserRegistrationBean userBean = getUserBean(request);
        Map<String, String> registrationErrors = inputValidator.validateRegistration(request, userBean);

        if (registrationErrors.isEmpty()) {
            handleValidRegistration(userBean);
            response.sendRedirect(Path.PAGE_MAIN);
        } else {
            if (registrationErrors.containsValue(Messages.EXCEPTION_REGISTRATION_TIMEOUT)) {
                captchaStorageService.remove(request);
            } else {
                request.setAttribute("userBean", userBean);
            }

            request.setAttribute("errors", registrationErrors);
            doGet(request, response);
        }
    }

    private UserRegistrationBean getUserBean(HttpServletRequest request) {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        return new UserRegistrationBean(firstName, lastName, login, email, password);
    }

    private void handleValidRegistration(UserRegistrationBean userBean) {
        User user = new User(userBean.getFirstName(), userBean.getLastName(), userBean.getLogin(), userBean.getEmail(), userBean.getPassword());
        userService.add(user);
    }

}
